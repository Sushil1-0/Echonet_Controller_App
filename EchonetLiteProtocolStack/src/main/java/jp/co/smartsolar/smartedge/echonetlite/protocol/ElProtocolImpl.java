package jp.co.smartsolar.smartedge.echonetlite.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElLog;
import jp.co.smartsolar.smartedge.echonetlite.ElOneNode;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess.ElEventListener;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

public abstract class ElProtocolImpl implements ElProtocol{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElProtocolImpl";

	/**
	 * ローカルIPアドレス
	 */
	protected String localIpAddress;

	/**
	 * 所属するELプロセスオブジェクト
	 */
	private ElProcess elProcess;

    /**
     * 受信フレームの処理待ち用キュー
     */
    private LinkedBlockingQueue<ElJobImpl> frameQueue = new LinkedBlockingQueue<ElJobImpl>();

    /**
     * Echonetフレーム受信時の処理用スレッド
     */
    private Thread threadEchonet;

	/**
     * Echonetの処理ループの有効無効制御フラグ
     */
    private boolean isEchonetEnabled;

	/**
	 * コンストラクタ
	 * @param ipAddress アサインされたローカルIPアドレス
	 */
	public  ElProtocolImpl(String ipAddress) {
		this.localIpAddress = ipAddress;
	}


    /**
     * ローカルIPアドレスを返す
     * @return IPアドレスの文字列表現
     */
    public String getLocalIpAddress() {
    	return this.localIpAddress;
    }

	/**
	 * 所属するELプロセスのオブジェクトを返す
	 * @return ELプロセスオブジェクト
	 */
	public ElProcess getElProcess() {
		return this.elProcess;
	}

	/**
	 * 所属するELプロセスのオブジェクトを設定する
	 * @param elProcess ELプロセスオブジェクト
	 */
	public void setElProcess(ElProcess elProcess) {
		this.elProcess = elProcess;
	}

	/**
	 * ジョブをキューに登録する
	 * @param job フレーム処理のジョブ
	 */
	protected synchronized void pushJobToQueue(ElJobImpl job) {
		this.frameQueue.offer(job);
	}


	/**
	 * {@inheritDoc}
	 */
    @Override
	public void startElRcvThread() {
		isEchonetEnabled = true;

		threadEchonet= new Thread(new Runnable() {
			@Override
			synchronized public void run() {
				while (isEchonetEnabled) {
					try {
						// フレームの取り出し
						ElJobImpl job =null;
						try {
							job = frameQueue.take(); // キューが空の場合はここで処理が待ち状態になる。
						} catch( InterruptedException ex ) { // 空でデータ投入待ちの状態でスレッドを停止すると生じる例外
							ElLog.w(TAG,"Echonet受信フレーム処理用キューを強制終了します。", true);
						}
						// ジョブの実行
						if(job != null) {
							job.business();
						}
					} catch( Exception ex ) {
						ElEventListener listener = elProcess.getElBulkEventProcessor();
						if(listener != null) {
							listener.onCatchException(ex); // リスナが設定されている場合は、メインスレッドに例外オブジェクトを渡す。
						} else {
							ex.printStackTrace();// リスナが無い場合はこの場でスタックトレースを出力する。
						}
					}
				}
			}
		});
		threadEchonet.start();

	}

	/**
	 * {@inheritDoc}
	 */
    @Override
	public synchronized void stopElRcvThread() {

		isEchonetEnabled = false;
		// 待ち受けスレッドの停止
		if (threadEchonet != null) {

			threadEchonet.interrupt();
			try {
				threadEchonet.join();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			threadEchonet = null;
		}
		ElLog.w(TAG,"Echonet受信フレーム処理用スレッドを停止しました。", true);

	}


	/**
	 * ECHONET tLiteフレーム受信時の処理を記述
	 */
	public abstract class ElJobImpl implements ElJob{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElProtocol.ElJob";

		/**
		 * 受信したフレームの16進数鵜文字列表記
		 */
		private String hexStrFrame;

		/**
		 *  リモートのホスト名(ＩＰアドレス)
		 */
		private String remoteHostName;

		/**
		 * 所属するELプロセスオブジェクト
		 */
		private ElProcess elProcess;

		/**
		 * コンストラクタ
		 * @param hexStrFrame フレームデータ（16進数文字列）
		 * @param remoteHostName リモートホスト名（IPアドレス）
		 * @param elProcess ELプロセスオブジェクト
		 */
		public ElJobImpl(String hexStrFrame, String remoteHostName, ElProcess elProcess) {
			this.hexStrFrame = hexStrFrame;
			this.remoteHostName = remoteHostName;
			this.elProcess = elProcess;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void business() throws IOException{

			/**
			 * 受信内容の確認
			 */
			ElFrame rcvFrame = null;
			try {
				rcvFrame = ElFrame.parseFrame(this.hexStrFrame);
			} catch(Exception ex) {
				ElEventListener listener = getElProcess().getElBulkEventProcessor();
				if(listener != null) {
					listener.onCatchExceptionOnRequest(ex);
				}
				return;
			}
			// フレームの取り込みと妥当性チェック
			if( rcvFrame == null || ! this.isValidRcvFrame(rcvFrame) ) {
				return ;
			}
			rcvFrame.setRemoteHostName(this.remoteHostName); // リモートホスト名をフレームオブジェクト内に設定

			// フレーム受信時のイベントハンドリング
			this.elProcess.getElBulkEventProcessor().onReceiveFrame(rcvFrame);

			// 受信フレームの新規性確認登録
			this.checkNoveltyOfRcvFrame(rcvFrame);

			/**
			 * フレームの処理
			 */
			// 応答及び通知系フレーム
			if( this.isReportingFrame(rcvFrame)) {
				// 内容解析とコールバック処理の実行
				// 内容に応じて、保持しているremoteNodeから該当するオブジェクトを引っ張りだして、
				// そのReciever内の登録処理を実行する。
				this.processReportingFrame(rcvFrame);

			}
			// 要求系フレーム
			if( this.isRequestingFrame(rcvFrame)){
				// デバイス側としてのアプローチ（要求・通知）されたフレーム処理を実行
				// 応答フレームの作成
				List<ElFrame> resFrameList = this.processRequestingFrame(rcvFrame);
				// 内容解析とコールバック処理の実行
				for(ElFrame resFrame : resFrameList) {
					// 応答送信処理
					this.sendResponseFrame(resFrame);
				}
			}

		}

		/**
		 * 受信フレームの妥当性チェック
		 * @param frame フレームオブジェクト
		 * @return 判定結果（treu : OK , false : NG）
		 */
		private boolean isValidRcvFrame(ElFrame frame) {
			if(! ElFrame.EHD1.equalsIgnoreCase(frame.getStrEHD1())){
				return false;
			}
			if(! ElFrame.EHD2.equalsIgnoreCase(frame.getStrEHD2())){
				return false;
			}
			return true;
		}

		/**
		 * 受信フレームの新規性確認、登録、検出系コールバックキック<br>
		 * 対向ノードのIPアドレス(ホスト名)、グループコード、クラスコード、エンティティコードの情報を
		 * 受信フレームから判断の付く限り情報を取得し、手持ちのノード情報一覧を最新化する
		 * また、検出系のコールバック処理をキックして、APIユーザの登録処理を実施する。<br>
		 * 「ECHONET-Lite System Design Guidelines_1st edition.pdf」
		 * 「第４章 ノード検出・発見手順の指針」（準拠）
		 *
		 * @param rcvFrame 受信フレームオブジェクト
		 */
		private void checkNoveltyOfRcvFrame(ElFrame rcvFrame){
			// memo : 登録先がMAPなので、毎回更新しても問題ない。
			// 受信フレームから判別できる情報をかき集めて手持ちのノード情報一覧の精度を高めていく
			String remoteHostName = rcvFrame.getRemoteHostName();
			if(this.elProcess.getLocalNode().getIpAddress().equals(remoteHostName)) {
				// 自ノードの為新規性判断対象外
				return ;
			}

			// ノードの新規性判定フラグ
			boolean isNewNode = false;
			// デバイスの新規性判定フラグ
			boolean isNewDevice = false;

			/**
			 * ノードの新規性確認と登録
			 */
			// 既存のリモートノードマップに該当するIPが既に登録されているかチェック
			ElOneNode remoteNode = this.elProcess.getRemoteNodeMap().get(remoteHostName);
			if ( remoteNode == null ) { // 既存がない = 新規である

				// 新規ノードに登録する為のノードプロファイルを新規作成
				// デフォルトでエンティティコード0x01(一般ノード)を採用する。
				NodeProfileClass newNodeProfileClassObject = new NodeProfileClass(NodeProfileClass.INSTANCE_CODE_GENERAL);

				// 新規ノードを作成
				// memo : デバイスは１つ以上が必須だが、この時点では不明の為nullとする。
				remoteNode = new ElOneNode(this.elProcess, remoteHostName, newNodeProfileClassObject, null);

				// ノードプロファイルの中にノードを登録する処理。（相互参照）
				newNodeProfileClassObject.setNodeBelongsTo(remoteNode);

				this.elProcess.getRemoteNodeMap().put(remoteHostName, remoteNode);
				isNewNode = true; // ノードの新規性をＯＮ
			}
			
			// 受信フレームがSEOJがNodeProfileクラスであった場合、そのエンティティコードが0x02（送信専用）であった場合はその旨を上書き登録
			if(NodeProfileClass.CLASS_GROUP_CODE.equalsIgnoreCase(rcvFrame.getStrSrcGroupCd()) &&
					NodeProfileClass.CLASS_CODE.equalsIgnoreCase(rcvFrame.getStrSrcClassCd()) &&
					NodeProfileClass.INSTANCE_CODE_SEND_ONLY.equalsIgnoreCase(rcvFrame.getStrSrcEntityCd())){
				remoteNode.getNodeProfile().setStrEntityCode(NodeProfileClass.INSTANCE_CODE_SEND_ONLY);
			}

			/**
			 * デバイスの新規性確認と登録
			 */
			// ノードの新規性の有無にかかわらず、受信フレームのSEOJが新規デバイスであった場合の確認登録処理
			ElClassBase registeredEoj =  remoteNode.getEoj(rcvFrame.getStrSrcGroupCd(),
					rcvFrame.getStrSrcClassCd(),
					rcvFrame.getStrSrcEntityCd());

			// 登録済みのノードプロファイルまたはデバイスであるか？
			if(registeredEoj == null) { // ノード内にデバイスが無い場合
				isNewDevice = true;	// デバイスの新規性ＯＮ

				//デバイスを追加登録したオブジェクトを受け取る。
				registeredEoj = remoteNode.addDeviceEojByCode(
						rcvFrame.getStrSrcGroupCd(),
						rcvFrame.getStrSrcClassCd(),
						rcvFrame.getStrSrcEntityCd());

				// ノードプロファイルの中にノードを登録する処理。（相互参照）
				if(registeredEoj != null) {
					registeredEoj.setNodeBelongsTo(remoteNode);
				}

			}

			// それでも、registeredObjがnullの場合、登録失敗（＝未知のノードプロファイルだった（エンティティコード違い）、もしくはサポートしてないデバイスだった。）
			if(registeredEoj == null) {
				// ノード検出系コールバック処理のコール
				this.processCallbackForNodeDetection(remoteNode, isNewNode);

				return; // その場合はここまで
				// memo : デバイスの検出時の処理は実施しない。
			}

			// memo : ここを通過できるのは、受信フレームのSEOJが、
			// ノードプロファイルまたはサポート対象デバイス

			// SEOJがノードプロファイルだった場合
			if( NodeProfileClass.CLASS_GROUP_CODE.equalsIgnoreCase(rcvFrame.getStrSrcGroupCd()) &&
					NodeProfileClass.CLASS_CODE.equalsIgnoreCase(rcvFrame.getStrSrcClassCd()) &&
					(
							ElFrame.ESV_INF.equalsIgnoreCase(rcvFrame.getStrEsv()) ||
							ElFrame.ESV_INFC.equalsIgnoreCase(rcvFrame.getStrEsv()) ||
							ElFrame.ESV_INF_SNA.equalsIgnoreCase(rcvFrame.getStrEsv()) || // 途中まで回答している場合がある為
							ElFrame.ESV_GET_RES.equalsIgnoreCase(rcvFrame.getStrEsv()) ||
							ElFrame.ESV_GET_SNA.equalsIgnoreCase(rcvFrame.getStrEsv()) // // 途中まで回答している場合がある為
							)
					) {

				/**
				 * 登録
				 */
				ArrayList<Boolean> newlyFoundFlgs = new ArrayList<Boolean>();
				ArrayList<DeviceObjectSuperClass> foundDevList = new ArrayList<DeviceObjectSuperClass>();

				// そのフレーム内のプロパティを検索
				List<ElProp> scvFrameProps = rcvFrame.getLstEchonetProp();
				for (ElProp prop : scvFrameProps) {

					// 更にそのEPCが0xD5（インスタンスリスト通知）または、
					// 0xD6（自ノードインスタンスリスト）が見つかった場合
					// かつPDCが0でない場合
					if((NodeProfileClass.EPC_SELF_NODE_INSTANCE_LIST_S.equalsIgnoreCase(prop.getStrEpc()) ||
							NodeProfileClass.EPC_INSTANCE_LIST_NOTIFICATION.equalsIgnoreCase(prop.getStrEpc())) &&
							! "00".equals(prop.getStrPdc())){

						// その中に含まれるインスタンスをチェック
						String strEdt = prop.getStrEdt();
						String strInstanceCnt = strEdt.substring(0,2);
						Integer intInstanceCnt = ElUtil.hexStringToInt(strInstanceCnt);
						String workList = strEdt.substring(2); // エンティティリスト部分を別途格納
						for(int i = 0 ; i < intInstanceCnt; i++) {
							String strClassGroupCode = workList.substring(0,2);
							String strClassCode = workList.substring(2,4);
							String strEntityCode = workList.substring(4,6);
							workList = workList.substring(6);//残りの部分を格納し直す

							//一つ一つの新規性を判断
							DeviceObjectSuperClass device = remoteNode.getDeviceEoj(strClassGroupCode, strClassCode, strEntityCode);
							if (device ==null) {
								//新規の場合は、EOJを生成登録
								DeviceObjectSuperClass addedEoj = remoteNode.addDeviceEojByCode(strClassGroupCode, strClassCode, strEntityCode);
								newlyFoundFlgs.add(true);
								// ノードプロファイルの中にノードを登録する処理。（相互参照）
								if(addedEoj != null) {
									addedEoj.setNodeBelongsTo(remoteNode);
								}
								foundDevList.add(addedEoj);
							} else {
								newlyFoundFlgs.add(false);
								foundDevList.add(device);
							}
						}
						
					}
				}
				// memo : インスタンスの登録をし終わってから、検出時のコールバックをキックする。
				// そうでないと、コールバック内で最新の状態のオブジェクトを扱えなくなる為。

				/**
				 * コールバックキック
				 */
				// ノード検出系コールバック処理のコール
				this.processCallbackForNodeDetection(remoteNode, isNewNode);

				// デバイス検出系のコールバックをキック
				this.processCallbackForDeviceDetection(registeredEoj, isNewDevice);

				// デバイス検出系のコールバックをキック（インスタンスリスト系情報より）
				for( int i = 0 ; i < foundDevList.size(); i++) {
					// デバイス検出系のコールバックをキック
					this.processCallbackForDeviceDetection(foundDevList.get(i), newlyFoundFlgs.get(i));
				}
			}
			// SEOJが登録可能なデバイスだった場合
			else {
				// ノード検出系コールバック処理のコール
				this.processCallbackForNodeDetection(remoteNode, isNewNode);

				// デバイス検出系のコールバックをキック
				this.processCallbackForDeviceDetection(registeredEoj, isNewDevice);
			}

		}

		/**
		 * ノード検出系のコールバックメソッドを処理
		 * @param remoteNode リモートノードオブジェクト
		 * @param isNewNode 新規性判定フラグ（true : 新規、false : 既存）
		 */
		private void processCallbackForNodeDetection(ElOneNode remoteNode, boolean isNewNode) {
			if(isNewNode) {
				// 新規ノード発見時のコールバック処理を起動
				remoteNode.onNewNodeFound();
			}
			// ノード検出時（新規性の有無に関わらない）のコールバック処理を起動
			remoteNode.onNodeFound();

			return;
		}

		/**
		 * デバイスEOJ検出時コールバックメソッドを処理
		 * @param eoj EOJオブジェクト
		 * @param isNewDevice 新規性判定フラグ（true : 新規、false : 既存）
		 */
		private void processCallbackForDeviceDetection(ElClassBase eoj, boolean isNewDevice) {
			if(isNewDevice) {
				// 新規デバイス発見時のコールバック処理を起動
				eoj.onNewEojFound();
			}
			// デバイス検出時（新規性の有無に関わらない）のコールバック処理を起動
			eoj.onEoJFound();

			return;
		}

		/**
		 * 応答系、通知系サービスのフレームであるか否かの判定を実施
		 * @param frame 受信フレーム
		 * @return boolean 応答、通知系サービスであるか否かの判定（true : 応答通知系、false : 非応答通知系）
		 */
		private boolean isReportingFrame(ElFrame frame) {
			if(
					// 要求系
					// 応答・通知系
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SET_RES) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_GET_RES) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INF) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INFC_RES) ||
//					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SET_GET_RES) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INFC) ||
					// 不可応答系
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETI_SNA) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETC_SNA) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_GET_SNA) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INF_SNA)
//					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SET_GET_SNA)
					) {
				return true;
			}
			return false;
		}

		/**
		 * 要求系フレームか否かの判定を実施
		 * @param frame 受信フレーム
		 * @return  boolean 要求であるか否かの判定（true:要求、false:非要求）
		 */
		private boolean isRequestingFrame(ElFrame frame) {
			if(
					//要求系
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETI) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETC) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_GET) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INF_REQ) ||
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SET_GET) ||
					// 応答・通知系
					frame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INFC)
					) {
				return true;
			}
			return true;
		}

		/**
		 * 応答及び通知系サービスフレームの処理
		 * @param rcvFrame フレームオブジェクト
		 */
		private void processReportingFrame(ElFrame rcvFrame) {
			// 対象のノードオブジェクトを取得する
			ElOneNode objNode = this.getNode(rcvFrame.getRemoteHostName());
			if(objNode == null) {
				return ;
			}

			ElClassBase seoj = objNode.getEoj(rcvFrame.getStrSrcGroupCd(), rcvFrame.getStrSrcClassCd(), rcvFrame.getStrSrcEntityCd());
			//送信元のSEOJが存在しない場合、
			if (seoj == null) {
				// サポート対象外なので対応しない。
				return ;
			}

			// 送信元エンティティ用のレシーバーを実行する。
			ElClassBase.ReportProcessor reportProcessor = seoj.getReportProcessor();
			if(reportProcessor != null) { // レシーバー登録されていない場合は処理を実施しない
				reportProcessor.onReceiveReportingFrame(seoj, rcvFrame);
			}
			return;
		}

		/**
		 * 要求系フレームの処理
		 * @param rcvFrame フレームオブジェクト
		 * @return List<ElFrame> 応答用フレームオブジェクトのリストオブジェクト
		 * @throws IOException
		 */
		private List<ElFrame> processRequestingFrame(ElFrame rcvFrame) throws IOException{

			// レスポンス用フレームの格納用配列をインスタンス
			List<ElFrame> resFrameList = new ArrayList<ElFrame>();

			ElOneNode localNode = this.elProcess.getLocalNode();
			if(localNode == null) {
				return resFrameList;
			}

			String strClassGroupCode = rcvFrame.getStrDstGroupCd();
			String strClassCode = rcvFrame.getStrDstClassCd();
			String strEntityCode = rcvFrame.getStrDstEntityCd();
			// 一斉同報向け（同一クラスの全エンティティが対象）の場合
			if (ElClassBase.INSTANCE_CODE_ALL.equalsIgnoreCase(strEntityCode)) {

				// NodeProfileの場合
				if(NodeProfileClass.CLASS_GROUP_CODE.equalsIgnoreCase(strClassGroupCode)
						&& NodeProfileClass.CLASS_CODE.equalsIgnoreCase(strClassCode)) {

					ElClassBase rcvDeoj = this.elProcess.getLocalNode().getNodeProfile();
					if(rcvDeoj == null) {
							return resFrameList;
					} else {

						// 受信フレームのコピー
						ElFrame cpiedFrame = rcvFrame.copy();
						cpiedFrame.setStrDstEntityCd(rcvDeoj.getStrEntityCode());
						rcvDeoj.onReceiveRequestFrame(cpiedFrame, resFrameList); // resFrameListに応答用フレームを格納
					}

				}
				// NodeProfile以外の場合
				else {

					List<DeviceObjectSuperClass> rcvDeojList = this.elProcess.getLocalNode().getDeviceEojList(strClassGroupCode, strClassCode);
					for(DeviceObjectSuperClass rcvDeoj : rcvDeojList) {

						// 一斉同報として送られてきたフレームを個別のエンティティ宛てのフレームに変換する

						// 受信フレームのコピー
						ElFrame cpiedFrame = rcvFrame.copy();
						// 一斉同報の場合Entitiyコードがが00となっているので
						// それを自ノードの個別のエンティティコードに置き換える
						cpiedFrame.setStrDstEntityCd(rcvDeoj.getStrEntityCode());
						rcvDeoj.onReceiveRequestFrame(cpiedFrame, resFrameList); // resFrameListに応答用フレームを格納
					}
				}


			}
			// 個別インスタンス宛てのフレームの場合
			else {
				ElClassBase rcvDeoj = this.elProcess.getLocalNode().getEoj(strClassGroupCode, strClassCode, strEntityCode);
				if(rcvDeoj == null) {
					return resFrameList;
				} else {
					rcvDeoj.onReceiveRequestFrame(rcvFrame, resFrameList); // resFrameListに応答用フレームを格納
				}
			}

			return resFrameList;
		}

		/**
		 * 応答フレームの送信処理
		 * @param resFrame 応答用フレームオブジェクト
		 * @throws IOException
		 */
		private void sendResponseFrame(ElFrame resFrame) throws IOException{

			if(ElFrame.ESV_INF.equalsIgnoreCase(resFrame.getStrEsv())){
				resFrame.setRemoteHostName(getElProcess().getChannel().getMultiCastAddress());
			}
			this.send(resFrame);
			return;
		}

		/**
		 * ホスト名（IPアドレス）に対応するノードオブジェクトを返す
		 * @param ipAddress
		 * @return ElOneNode ノードオブジェクト
		 */
		private ElOneNode getNode(String ipAddress) {
			ElOneNode remoteNode = null;
			if(localIpAddress.equals(ipAddress)){
				remoteNode = this.elProcess.getLocalNode();
			} else {
				remoteNode = this.elProcess.getRemoteNodeMap().get(ipAddress);
			}
			return remoteNode;
		}

		/**
		 * 応答送信処理<br>
		 * 下位レイヤ―依存の送信処理を実施するメソッド。<br>
		 * 継承先でオーバーライドし、下位レイヤーに依存する処理を記述する。
		 * @param resFrame 応答用フレームオブジェクト
		 * @throws IOException 入出力例外
		 */
		protected abstract void send(ElFrame resFrame) throws IOException;

	}

	/**
	 * ECHONET Liteプロトコルに関するイベント処理ハンドラ
	 */
	public static interface ElProtocolEvent {

		/**
		 * フレーム受信時の処理ハンドラ
		 * @param frame フレームオブジェクト
		 */
		public void onReceiveFrame(ElFrame frame);

		/**
		 * フレーム送信時の処理ハンドラ
		 * @param frame フレームオブジェクト
		 */
		public void onSendFrame(ElFrame frame);

	}

}
