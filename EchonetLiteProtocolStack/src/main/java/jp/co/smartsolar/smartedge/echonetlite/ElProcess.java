package jp.co.smartsolar.smartedge.echonetlite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.smartsolar.smartedge.echonetlite.channel.ElChannelBase;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.ElectricWaterHeaterClass;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;
import jp.co.smartsolar.smartedge.echonetlite.protocol.ElProtocolImpl;


/**
 * ECHONET Liteプロセス全般の管理クラス
 */
public class ElProcess {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElProcess";

	/**
	 *  ローカルノード（自ノード）の情報を保持
	 */
	private ElOneNode localNode = null;

	/**
	 * リモートノード（他ノード）情報一覧を保持
	 * ホスト名（IP）とノードオブジェクトのマッピング
	 */
	private static Map<String, ElOneNode> remoteNodeMap = new HashMap<String, ElOneNode>();

	/**
	 * 通信チャンネルオブジェクト
	 */
	private ElChannelBase channel = null;


	/**
	 * 起動確認用フラグ<br>
	 * true: 起動中、false: 停止中
	 */
	private boolean isRunning = false;

	/**
	 * ローカルノード（自ノード）の情報を取得
	 * @return ElOneNode ローカルノードのノードオブジェクト
	 */
	public ElOneNode getLocalNode() {
		return this.localNode;
	}


	/**
	 * リモートノード（他ノード）情報の一覧を取得
	 * @return remoteNodeMap IPとリモートノードのノードオブジェクトのマップ
	 */
	public Map<String, ElOneNode> getRemoteNodeMap() {
//		return this.remoteNodeMap;
		return ElProcess.remoteNodeMap;
	}


	/**
	 * 通信チャンネルオブジェクトを返す
	 * @return channel 通信チャンネルオブジェクト
	 */
	public ElChannelBase getChannel() {
		return this.channel;
	}

	/**
	 * ElProcessインスタンスのマップ
	 * ホスト名（IP）をキーにこのElProcessオブジェクトをマッピング
	 */
	public static Map<String, ElProcess> elProcessMap = new HashMap<String, ElProcess>();

	/**
	 * ElProcessのマップを返す<br>
	 * ホスト名（IP）と管理オブジェクトのマップを返す
	 * @return ホスト名（IP）と管理オブジェクトのマップ
	 */
	public static Map<String, ElProcess> getElProcessMap() {
		return elProcessMap;
	}

	/**
	 * ElProcessのマップ要素を追加
	 * @param localIpAddress ローカルＩＰアドレス
	 * @param elProcess 管理オブジェクト
	 */
	private synchronized static void addElProcessMap(String localIpAddress, ElProcess elProcess) {
		ElProcess.elProcessMap.put(localIpAddress, elProcess);
	}
	/**
	 * 指定したIPアドレスをキーに管理オブジェクトが存在するか確認する
	 * @param localIpAddress ローカルIPアドレス
	 * @return 管理オブジェクトの存在判定結果（true : 存在、false : なし）
	 */
	private static boolean isExistedElProcess(String localIpAddress) {
		if(ElProcess.elProcessMap.containsKey(localIpAddress)) {
			ElProcess elProcess = getElProcessMap().get(localIpAddress);
			if(elProcess == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}


	/**
	 * 起動中であるか否かを返す<br>
	 * @return boolean true: 起動中、false: 停止中
	 */
	public boolean isRunning() {
		return isRunning;
	}


	/**
	 * 	コンストラクタ
	 * @param objChannel 通信チャンネルオブジェクト
	 * @param thisNodeProfile ローカルノード（自ノード）用ノードプロファイルオブジェクト
	 * @param devideArray ローカルオード（自ノード）用デバイスオブジェクトの配列
	 */
	private ElProcess(ElChannelBase objChannel , NodeProfileClass thisNodeProfile, DeviceObjectSuperClass[] devideArray) {

		/**
		 * チャンネルオブジェクトを設定
		 */
		this.channel = objChannel;

		/**
		 * 自身のノードの設定
		 */
		String localIpAddress = objChannel.getLocalIpAddress();
		localNode = this.createThisNode(localIpAddress, thisNodeProfile, Arrays.asList(devideArray));
		// 所属するノードへの参照を登録
		thisNodeProfile.setNodeBelongsTo(localNode);
		for (ElClassBase device : localNode.getDeviceEojList()){
			device.setNodeBelongsTo(localNode);
		}

		/**
		 * イベントリスナ登録用変数初期化処理
		 */
		elEventListenerList = new ArrayList<ElEventListener>();
		elBulkEventProcessor = new ElBulkEventProcessor();

	}

	/**
	 * 自ノードの開始
	 * @param objChannel 通信チャンネルオブジェクト
	 * @param thisNodeProfile ローカルノードプロファイルオブジェクト
	 * @param devideArray デバイスオブジェクト配列
	 * @return ELプロセス管理オブジェクト
	 * @throws Exception 任意の例外
	 */
	public synchronized static ElProcess generate(ElChannelBase objChannel , NodeProfileClass thisNodeProfile, DeviceObjectSuperClass[] devideArray) throws Exception{

		if( objChannel == null ) {
			throw new Exception("チャンネルオブジェクトが設定されていません。");
		}
		if( thisNodeProfile == null ) {
			throw new Exception("ローカルノード（自ノード）のノードプロファイルオブジェクトが設定されていません。");
		}
		if( devideArray == null || devideArray.length == 0) {
			throw new Exception("ローカルノード（自ノード）のデバイスオブジェクトが１つ以上設定されている必要があります。");
		}

		/**
		 * チャンネルからローカルIPアドレスを取得
		 */
		String localIpAddress = objChannel.getLocalIpAddress();

		ElProcess elProcess = null;
		/**
		 * 管理オブジェクトの生成登録
		 */
		if(! isExistedElProcess(localIpAddress)) {
			// インスタンスした管理オブジェクトをプロセスマップへ登録
			elProcess = new ElProcess(objChannel , thisNodeProfile, devideArray);
			objChannel.setElProcess(elProcess); // 相互参照の為コンストラクタではなくここでセットする
			ElProcess.addElProcessMap(localIpAddress, elProcess);
		} else {
			elProcess = getElProcessMap().get(localIpAddress);
		}
		return elProcess;
	}
	/**
	 * 立ち上げ時の処理
	 * @return ELプロセス管理オブジェクト
	 * @throws Exception 任意の例外
	 */
	public synchronized ElProcess start() throws Exception{

		try {

			/**
			 * ソケットの開始
			 */
			this.channel.open();
			/**
			 * Echonetフレーム受信処理用スレッドの立ち上げ
			 */
			this.channel.startElRcvThread();
			/**
			 * 受信用スレッドの立ち上げ
			 */
			this.channel.startSocketRcvThread();

			/**
			 * 自ノード及び配下のデバイスの検出イベントをコール
			 */
			this.localNode.onNewNodeFound();
			this.localNode.onNodeFound();
			this.localNode.getNodeProfile().onNewEojFound();
			this.localNode.getNodeProfile().onEoJFound();
			for(ElClassBase device : this.localNode.getDeviceEojList()) {
				device.onNewEojFound();
				device.onEoJFound();
			}

			/**
			 * 自ノードの情報を一斉同報通知
			 */
			this.localNode.getNodeProfile().inf().reqInfInstanceListNotification().send();

			this.isRunning = true; // 起動中であることを設定
		}catch(Exception ex) {
			this.stop();
			throw ex;
		}
		return this;
	}

	/**
	 * このノード自身のノード情報を作成
	 * @param ipAddress ローカルノードのホスト名（IPアドレス）
	 * @param nodeProfile ノードプロファイルオブジェクト
	 * @param deviceObject デバイスオブジェクトのリスト
	 * @return ノードオブジェクト
	 */
	private ElOneNode createThisNode(String ipAddress , NodeProfileClass nodeProfile, List<DeviceObjectSuperClass> deviceObject) {
		return new ElOneNode(this, ipAddress , nodeProfile, deviceObject);
	}

	/**
	 * 自ノードの停止
	 * @throws Exception 任意の例外
	 */
	public synchronized void stop() throws Exception{

		/**
		 * ソケットのクローズ
		 */
		this.channel.close();
		/**
		 * 受信用スレッドの停止
		 */
		this.channel.stopSocketRcvThread();
		/**
		 * Echonetフレーム受信処理用スレッドの停止
		 */
		this.channel.stopElRcvThread();

		this.isRunning = false; // 停止中であることを設定

	}

	/**
	 * イベント処理用コールバック集約オブジェクト
	 */
	public List<ElEventListener> elEventListenerList = new ArrayList<ElEventListener>();

	/**
	 * イベント処理用コールバック集約オブジェクト一括実行用
	 */
	public ElBulkEventProcessor elBulkEventProcessor = null;

	/**
	 * イベント処理登録
	 * @param elProtocolEventHandler イベントリスナ
	 * @return プロセス管理オブジェクト
	 */
	public ElProcess addEventListenerToList( ElEventListener elProtocolEventHandler ) {
		this.elEventListenerList.add(elProtocolEventHandler);
		return this;
	}

	/**
	 * イベント初期化
	 * @return プロセス管理オブジェクト
	 */
	public ElProcess initEventList() {
		this.elEventListenerList = new ArrayList<ElEventListener>();
		return this;
	}

	/**
	 * イベント処理取得
	 * @return イベントリスナ
	 */
	public List<ElEventListener> getElProtocolEventList() {
		return this.elEventListenerList;
	}

	/**
	 * イベント処理取得一括実行用オブジェクトの取得
	 * @return イベントリスナ(一括処理用)
	 */
	public ElBulkEventProcessor getElBulkEventProcessor() {
		return this.elBulkEventProcessor;
	}


	/**
	 * イベント処理用コールバックメソッドの集約クラス
	 */
	public static abstract class ElEventListener implements ElProtocolImpl.ElProtocolEvent, ElOneNode.ElNodeEvent, ElClassBase.ElClassBaseEvent{

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onReceiveFrame(ElFrame frame) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onSendFrame(ElFrame frame) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewNodeFound(ElOneNode node) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNodeFound(ElOneNode node) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewEojFound(ElClassBase eoj) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onEojFound(ElClassBase eoj) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onCatchException(Exception ex) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onCatchExceptionOnRequest(Exception ex) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertyValidation(ElClassBase eoj , ElProp prop, boolean isValid) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertySetting(ElClassBase eoj, ElProp prop, boolean isValid) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertyGetting(ElClassBase eoj, ElProp prop) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewProfileObjectFound(ElClassBase eoj) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewNodeProfileFound(ElClassBase eoj) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewDeviceObjectFound(ElClassBase eoj) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewControllerFound(ElClassBase eoj) {};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewCurrentValueSensorFound(ElClassBase eoj){};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewGeneralLightingFound(ElClassBase eoj){};

		/**
		 * {@inheritDoc}
		 */

		@Override
		public void onNewHighVoltageSmartMeterFound(ElClassBase eoj){};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewHouseholdSolarPowerGenerationFound(ElClassBase eoj){};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewLowVoltageSmartMeterFound(ElClassBase eoj){};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewStorageBatteryFound(ElClassBase eoj){};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewPowerDistributionBoardMeterFound(ElClassBase eoj){};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewElectricWaterHeaterFound(ElClassBase eoj){};
	}

	/**
	 * イベント処理用コールバックメソッドの集約クラス<br>
	 * イベント処理の一括実行用
	 */
	public class ElBulkEventProcessor extends ElEventListener{

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onReceiveFrame(ElFrame frame) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onReceiveFrame(frame);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onSendFrame(ElFrame frame) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onSendFrame(frame);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewNodeFound(ElOneNode node) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewNodeFound(node);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNodeFound(ElOneNode node) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNodeFound(node);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewEojFound(ElClassBase eoj) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewEojFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onEojFound(ElClassBase eoj) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onEojFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onCatchException(Exception ex) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onCatchException(ex);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onCatchExceptionOnRequest(Exception ex) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onCatchExceptionOnRequest(ex);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertyValidation(ElClassBase eoj , ElProp prop, boolean isValid) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onAfterPropertyValidation(eoj, prop, isValid);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertySetting(ElClassBase eoj, ElProp prop, boolean isValid) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onAfterPropertySetting(eoj, prop, isValid);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertyGetting(ElClassBase eoj, ElProp prop) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onAfterPropertyGetting(eoj, prop);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewProfileObjectFound(ElClassBase eoj) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewProfileObjectFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewNodeProfileFound(ElClassBase eoj) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewNodeProfileFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewDeviceObjectFound(ElClassBase eoj) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewDeviceObjectFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewControllerFound(ElClassBase eoj) {
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewControllerFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewCurrentValueSensorFound(ElClassBase eoj){
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewCurrentValueSensorFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewGeneralLightingFound(ElClassBase eoj){
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewGeneralLightingFound(eoj);
			}
		};


		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewElectricWaterHeaterFound(ElClassBase eoj){
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewElectricWaterHeaterFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewHighVoltageSmartMeterFound(ElClassBase eoj){
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewHighVoltageSmartMeterFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewHouseholdSolarPowerGenerationFound(ElClassBase eoj){
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewHouseholdSolarPowerGenerationFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewLowVoltageSmartMeterFound(ElClassBase eoj){
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewLowVoltageSmartMeterFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewStorageBatteryFound(ElClassBase eoj){
			for(ElEventListener handlers : elEventListenerList) {
				handlers.onNewStorageBatteryFound(eoj);
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewPowerDistributionBoardMeterFound(ElClassBase eoj) {
			for (ElEventListener handlers : elEventListenerList) {
				handlers.onNewPowerDistributionBoardMeterFound(eoj);
			}
			;
		}
	}

	/**
	 * イベント処理用コールバックメソッドの集約クラス<br>
	 * イベント処理の確認用ログ出力の実装版
	 */
	public static class ElEventLogger extends ElEventListener{

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onReceiveFrame(ElFrame frame) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log
					.appendLine("[onReceiveFrame(フレームを受信)]  ")
					.appendLine(" REMOTE HOST : [" + frame.getRemoteHostName() + "]")
					.appendLine(frame.getFrameStructureFormatWithTitle())
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onSendFrame(ElFrame frame) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log
					.appendLine("[onSendFrame(フレームを送信)]  ")
					.appendLine(" REMOTE HOST : [" + frame.getRemoteHostName() + "]")
					.appendLine(frame.getFrameStructureFormatWithTitle())
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewNodeFound(ElOneNode node) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewNodeFound(新規ノードを検出)]  ")
					.append(node.getIpAddress())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNodeFound(ElOneNode node) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNodeFound(ノードを検出)]  ")
					.append(node.getIpAddress())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewEojFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewEojFound(新規EOJを検出)]  ")
					.append("remote : [" + eoj.getNodeBelongsTo().getIpAddress() + "]")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onEojFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onEojFound(EOJを検出)]  ")
					.append("remote : [" + eoj.getNodeBelongsTo().getIpAddress() + "]")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onCatchException(Exception ex) {
			ElLog.Builder log = ElLog.getBuilder().e(TAG);
			log.appendHeader()
					.append("[onCatchException(例外検出)]  ")
					.append(ex.toString())
					.appendNewLine();
			for(StackTraceElement stack : ex.getStackTrace()) {
				log.append(stack.toString())
						.appendNewLine();
			}
			log.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onCatchExceptionOnRequest(Exception ex) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onCatchExceptionOnRequest(例外検出)]  ")
					.append(ex.toString())
					.appendNewLine();
			for(StackTraceElement stack : ex.getStackTrace()) {
				log.append(stack.toString())
						.appendNewLine();
			}
			log.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertyValidation(ElClassBase eoj , ElProp prop, boolean isValid) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onAfterPropertyValidation(プロパティのバリデーション実施後)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.append(prop.getPropStructureFormatWithTitle())
					.append("結果:[" + String.valueOf(isValid) + "]")
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertySetting(ElClassBase eoj, ElProp prop, boolean isValid) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onAfterPropertySetting(SET要求に対するアプリケーションの対応実施後)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.append(prop.getPropStructureFormatWithTitle())
					.append("結果:[" + String.valueOf(isValid) + "]")
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onAfterPropertyGetting(ElClassBase eoj, ElProp prop) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onAfterPropertyGetting(GET要求に対するアプリケーションの対応実施後)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.append(prop.getPropStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewProfileObjectFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewDeviceObjectFound(新規プロファイルオブジェクトクラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewNodeProfileFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewNodeProfileFound(新規ノードプロファイルクラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewDeviceObjectFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewDeviceObjectFound(新規デバイスオブジェクトクラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewControllerFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewControllerFound(新規コントローラクラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewCurrentValueSensorFound(ElClassBase eoj){
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewCurrentValueSensorFound(新規電流値センサクラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewGeneralLightingFound(ElClassBase eoj){
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewGeneralLightingFound(新規一般照明クラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewHighVoltageSmartMeterFound(ElClassBase eoj){
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewHighVoltageSmartMeterFound(新規高圧スマート電力量メータクラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewHouseholdSolarPowerGenerationFound(ElClassBase eoj){
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewHouseholdSolarPowerGenerationFound(新規住宅用太陽光発電クラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewLowVoltageSmartMeterFound(ElClassBase eoj){
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewLowVoltageSmartMeterFound(新規低圧スマート電力量メータクラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewStorageBatteryFound(ElClassBase eoj){
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewStorageBatteryFound(新規蓄電池クラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewPowerDistributionBoardMeterFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[onNewPowerDistributionBoardMeterFound(新規分電盤メータークラスを検出)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onNewElectricWaterHeaterFound(ElClassBase eoj) {
			ElLog.Builder log = ElLog.getBuilder().d(TAG);
			log.appendHeader()
					.append("[NewElectricWaterHeaterFound(新しい電気温水器を発見)]  ")
					.append(eoj.getEojCodeStructureFormatWithTitle())
					.appendNewLine()
					.flush();
		};
	}

}