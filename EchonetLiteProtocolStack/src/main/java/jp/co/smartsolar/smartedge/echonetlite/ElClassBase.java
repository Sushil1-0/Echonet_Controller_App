package jp.co.smartsolar.smartedge.echonetlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess.ElEventListener;
import jp.co.smartsolar.smartedge.echonetlite.devices.ControllerClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.CurrentValueSensorClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.GeneralLightingClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.ElectricWaterHeaterClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.HighVoltageSmartMeter;
import jp.co.smartsolar.smartedge.echonetlite.devices.HouseholdSolarPowerGenerationClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.LowVoltageSmartMeter;
import jp.co.smartsolar.smartedge.echonetlite.devices.PowerDistributionBoardMetering;
import jp.co.smartsolar.smartedge.echonetlite.devices.StorageBatteryClass;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;
import jp.co.smartsolar.smartedge.echonetlite.profile.ProfileObjectSuperClass;

public class ElClassBase{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElClassBase";

	/**
	 *  インスタンスコード（全ノード）
	 */
	public static final String INSTANCE_CODE_ALL = "00";

	/**
	 * TIDの最大値
	 */
	public static final int TID_MAX = 65535;


	/**
	 * EOJオブジェクト化したときのグループコード
	 */
	private String strClassGroupCode;

	/**
	 * EOJオブジェクト化したときのクラスコード
	 */
	private String strClassCode;

	/**
	 * EOJオブジェクト化したときのエンティティコード
	 */
	private String strEntityCode;

	/**
	 * EOJオブジェクト化したときの上位ノードへの参照
	 */
	private ElOneNode nodeBelongsTo;

	/**
	 * 所属するELプロセスオブジェクト
	 */
	private ElProcess elProcess;

	/**
	 * 状変アナウンスプロパティマップ
	 */
	private Set<String> mapConvAnnounceProps;
	/**
	 * Setプロパティマップ
	 */
	private Set<String> mapSetProps;
	/**
	 * Getプロパティマップ
	 */
	private Set<String> mapGetProps;

	/**
	 * ReportProcessorのオブジェクト
	 */
	private ReportProcessor reportProcessor;

	/**
	 * 状変アナウンスプロパティマップの取得
	 * @return 状変アナウンスプロパティマップ
	 */
	public Set<String> getMapConvAnnounceProps() {
		return mapConvAnnounceProps;
	}
	/**
	 * 状変アナウンスプロパティマップの設定
	 * @param mapConvAnnounceProps 状変アナウンスプロパティマップ
	 */
	public void setMapConvAnnounceProps(Set<String> mapConvAnnounceProps) {
		this.mapConvAnnounceProps = mapConvAnnounceProps;
	}
	/**
	 * SETプロパティマップの取得
	 * @return SETプロパティマップ
	 */
	public Set<String> getMapSetProps() {
		return mapSetProps;
	}
	/**
	 * SETプロパティマップの設定
	 * @param mapSetProps SETプロパティマップ
	 */
	public void setMapSetProps(Set<String> mapSetProps) {
		this.mapSetProps = mapSetProps;
	}
	/**
	 * GETプロパティマップの取得
	 * @return GETプロパティマップ
	 */
	public Set<String> getMapGetProps() {
		return mapGetProps;
	}
	/**
	 * GETプロパティマップの設定
	 * @param mapGetProps GETプロパティマップ
	 */
	public void setMapGetProps(Set<String> mapGetProps) {
		this.mapGetProps = mapGetProps;
	}

	/**
	 * 状変アナウンスプロパティマップに値を追加<br>
	 * 規格書において状態変更時アナウンスに○が付いているものは必須
	 * @param strEpc EPC値（16進数文字列）
	 */
	public void addMapConvAnnounceProps(String strEpc) {
		this.mapConvAnnounceProps.add(strEpc);
	}
	/**
	 * Setプロパティマップに値を追加<br>
	 * 規格書の必須欄に○が付いているものは必須
	 * @param strEpc EPC値（16進数文字列）
	 */
	public void addMapSetProps(String strEpc) {
		this.mapSetProps.add(strEpc);
	}
	/**
	 * Getプロパティマップに値を追加<br>
	 * 規格書の必須欄に○が付いているものは必須
	 * @param strEpc EPC値（16進数文字列）
	 */
	public void addMapGetProps(String strEpc) {
		this.mapGetProps.add(strEpc);
	}
	/**
	 * プロパティマップの初期化
	 */
	public void initPorpMaps() {}


	/**
	 * クラスグループコードの取得
	 * @return String クラスグループコード（16進数文字列）
	 */
	public String getStrClassGroupCode() {
		return strClassGroupCode;
	}

	/**
	 * クラスグループコードの設定
	 * @param strClassGroupCode クラスグループコード（16進数文字列）
	 */
	protected void setStrClassGroupCode(String strClassGroupCode) {
		this.strClassGroupCode = strClassGroupCode;
	}

	/**
	 * クラスコードの取得
	 * @return String クラスコード（16進数文字列）
	 */
	public String getStrClassCode() {
		return strClassCode;
	}

	/**
	 * クラスコードの設定
	 * @param strClassCode クラスコード（16進数文字列）
	 */
	protected void setStrClassCode(String strClassCode) {
		this.strClassCode = strClassCode;
	}

	/**
	 * エンティティコードの取得
	 * @return String エンティティコード
	 */
	public String getStrEntityCode() {
		return strEntityCode;
	}
	/**
	 * エンティティコードの設定
	 * @param strEntityCode エンティティコード
	 */
	public void setStrEntityCode(String strEntityCode) {
		this.strEntityCode = strEntityCode;
	}
	/**
	 * EOJが所属するノードの設定
	 * @return 所属するノードオブジェクト
	 */
	public ElOneNode getNodeBelongsTo() {
		return nodeBelongsTo;
	}
	/**
	 * EOJ所属するノードの取得
	 * インスタンスした後に設定する前提（相互参照の為後から参照を設定する）
	 * @param nodeBelongsTo 所属するノードオブジェクト
	 */
	public void setNodeBelongsTo(ElOneNode nodeBelongsTo) {
		this.nodeBelongsTo = nodeBelongsTo;
		this.elProcess = this.nodeBelongsTo.getElProcess();
	}

	/**
	 * 所属するELプロセスのオブジェクトを返す
	 * @return ElProcess ELプロセスオブジェクト
	 */
	public ElProcess getElProcess() {
		return this.elProcess;
	}

	/**
	 * ローカルノードプロファイルのEOJの取得
	 * @param elProcess ElProcessオブジェクト
	 * @return NodeProfileClassオブジェクト
	 */
	public static NodeProfileClass getLocalNodeProfile(ElProcess elProcess){
		// 送信元情報
		ElOneNode localNode = elProcess.getLocalNode();
		return localNode.getNodeProfile();
	}

	/**
	 * EOJコードの取得
	 * @return String EOJコード（グループコード＋クラスコード＋エンティティコード）の16進数文字列
	 */
	public String getStrEojCode() {
		return this.getStrClassGroupCode() + this.getStrClassCode() + this.getStrEntityCode();
	}


	/**
	 * コンストラクタ
	 */
	public ElClassBase() {

		// 状変アナウンスプロパティマップ
	    this.mapConvAnnounceProps = new TreeSet<String>();
		// Setプロパティマップ
		this.mapSetProps = new TreeSet<String>();
		// Getプロパティマップ
		this.mapGetProps = new TreeSet<String>();
		// プロパティマップの初期化
		initPorpMaps();
	}


	/**
	 * TIDの数値型を16進数文字列に変換する（2バイト）
	 * @param intTid TID（10進数）
	 * @return String TIDの16進数文字列表記（2バイト分=4文字）
	 */
	public static String convTidDecToHexString(Integer intTid) {
		return String.format("%04X", intTid);
	}


	/**
	 * 新規EOJ検出時のコールバック処理
	 */
	public void onNewEojFound(){
		getElProcess().getElBulkEventProcessor().onNewEojFound(this);
	}

	/**
	 * EOJ検出時のコールバック処理（新規性の有無に関わらずコールされる）
	 */
	public void onEoJFound(){
		getElProcess().getElBulkEventProcessor().onEojFound(this);
	}

	/**
	 * EOJに関するイベント処理ハンドラ
	 */
	public static interface ElClassBaseEvent{

		/**
		 * 新規EOJ検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewEojFound(ElClassBase eoj);

		/**
		 * EOJ検出時の処理ハンドラ（新規性の有無に関わらずコールされる）
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onEojFound(ElClassBase eoj);

		/**
		 * 例外検出時の処理
		 * サブスレッド内で例外が補足された場合にコールされるメソッド
		 * @param ex throwされた例外オブジェクト
		 */
		public void onCatchException(Exception ex);


		/**
		 * 例外検出時の処理（要求受信時のアプリケーションアクセスの場合）<br>
		 * サブスレッド内で例外が補足された場合にコールされるメソッド<br>
		 * <br>
		 * onReceiveSetRequestとonReceiveGetRequest処理時の例外について、連携する為の例外ハンドラ<br>
		 * リクエスト処理時にはアプリケーションに処理を連携するがその際に例外が発生した場合でも、<br>
		 * 応答フレームの作成処理は継続する必要がある。<br>
		 * よってコールバックメソッド内の処理で例外が連携された場合処理を継続することが望ましい。
		 * @param ex throwされた例外オブジェクト
		 */
		public void onCatchExceptionOnRequest(Exception ex);


		/**
		 * プロパティの妥当性確認後の処理
		 * @param eoj EOJオブジェクト
		 * @param prop プロパティオブジェクト
		 * @param isValid バリデーション実施後の結果
		 */
		public void onAfterPropertyValidation(ElClassBase eoj, ElProp prop, boolean isValid);


		/**
		 * プロパティの設定処理後の処理
		 * @param eoj EOJオブジェクト
		 * @param prop プロパティオブジェクト
		 * @param isValid SET要求に対するアプリケーション側の処理実施結果
		 */
		public void onAfterPropertySetting(ElClassBase eoj, ElProp prop, boolean isValid);

		/**
		 * プロパティの値取得後の処理
		 * @param eoj EOJオブジェクト
		 * @param prop プロパティオブジェクト
		 */
		public void onAfterPropertyGetting(ElClassBase eoj, ElProp prop);



		/**
		 * 新規DeviceObject検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewProfileObjectFound(ElClassBase eoj);

		/**
		 * 新規DeviceObject検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewNodeProfileFound(ElClassBase eoj);

		/**
		 * 新規DeviceObject検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewDeviceObjectFound(ElClassBase eoj);

		/**
		 * 新規Controller検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewControllerFound(ElClassBase eoj);

		/**
		 * 新規CurrentValueSensor検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewCurrentValueSensorFound(ElClassBase eoj);

		/**
		 * 新規GeneralLighting検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewGeneralLightingFound(ElClassBase eoj);

		/**
		 * 新規ElectricWaterHeater検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewElectricWaterHeaterFound(ElClassBase eoj);

		/**
		 * 新規HighVoltageSmartMeterFound検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewHighVoltageSmartMeterFound(ElClassBase eoj);

		/**
		 * 新規HouseholdSolarPowerGeneration検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewHouseholdSolarPowerGenerationFound(ElClassBase eoj);

		/**
		 * 新規LowVoltageSmartMeter検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewLowVoltageSmartMeterFound(ElClassBase eoj);

		/**
		 * 新規StorageBattery検出時の処理ハンドラ
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewStorageBatteryFound(ElClassBase eoj);
		
		/**
		 * 新規PowerDistributionBoardMeter検出時の処理ハンドラ
		 * 
		 * @param eoj 検出されたEOJオブジェクト
		 */
		public void onNewPowerDistributionBoardMeterFound(ElClassBase eoj);
	}

	/**
	 * ReportProcessorオブジェクトの取得<br>
	 * （応答、通知系フレームの処理用オブジェクト）
	 * @return ReportProcessorオブジェクト
	 */
	public ReportProcessor getReportProcessor() {
		return this.reportProcessor;
	}

	/**
	 * ReportProcessorオブジェクトの登録<br>
	 * （応答、通知系フレームの処理用オブジェクト）
	 * @param reportProcessor ReportProcessorオブジェクト
	 */
	public void setReportProcessor(ReportProcessor reportProcessor) {
		this.reportProcessor = reportProcessor;
	}

	/**
	 * 応答、通知系フレームの受信時の処理用クラス
	 */
	public static class ReportProcessor {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElClassBase.ReportProcessor";

		/**
		 * 応答、通知系フレームの受信処理
		 * @param seoj 受信フレーム内のSEOJ
		 * @param frame 受信フレームオブジェクト
		 */
		public void onReceiveReportingFrame(ElClassBase seoj, ElFrame frame){
			// 受信時共通のイベント処理
			//this.onReportingFrameReceive(seoj, frame);

			// GET系リクエストに対する回答受信の場合
			// または、通知を受けた場合
			// とにかく対向ノードの状態を教えてもらった場合
			if(ElFrame.ESV_GET_RES.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfGetOrInform(seoj, frame);
			}
			if(ElFrame.ESV_GET_SNA.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfGetOrInform(seoj, frame);
			}
			if(ElFrame.ESV_INF.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfGetOrInform(seoj, frame);
			}
			if(ElFrame.ESV_INF_SNA.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfGetOrInform(seoj, frame);
			}
			if(ElFrame.ESV_INFC.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfGetOrInform(seoj, frame);
			}

			// SET系リクエストに対する回答受信の場合
			if(ElFrame.ESV_SET_RES.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfSet(seoj, frame);
			}
			if(ElFrame.ESV_SETI_SNA.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfSet(seoj, frame);
			}
			if(ElFrame.ESV_SETC_SNA.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfSet(seoj, frame);
			}

			// 個別通知（応答要）リクエストに対する回答受信の場合
			if(ElFrame.ESV_INFC_RES.equalsIgnoreCase(frame.getStrEsv())){
				this.onReceiveReportOfInfC(seoj, frame);
			}

		}


		/**
		 * 情報取得要求に対する回答フレームまたは通知フレームの処理
		 * @param seoj 受信フレーム内のSEOJ
		 * @param frame 受信フレームオブジェクト
		 */
		private void onReceiveReportOfGetOrInform(ElClassBase seoj, ElFrame frame) {
			for(ElProp prop : frame.getLstEchonetProp()) {
				onReceivePropertyReportOfGetOrInform(seoj, frame.getStrTid(), frame.getStrEsv(), prop, ! "00".equals(prop.getStrPdc()));
				// memo : Get系,Inf系のリクエストに対し、対向ノードが要求を受理した場合は、受理できたプロパティのPDCが0以外つまりEDTに何等かの値が入った状態で応答する。
			}
			return;
		}

		/**
		 * 情報取得要求に対する回答フレームまたは通知フレームの処理のプロパティ単位の処理
		 * @param seoj 受信フレーム内のSEOJ
		 * @param strTid TID値（16進数文字列表記）
		 * @param strEsv ESV値（16進数文字列表記）
		 * @param objProp プロパティオブジェクト
		 * @param isSuccess 受理の成否判定（true: 受理、false:受理不可）
		 */
		protected void onReceivePropertyReportOfGetOrInform(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			return;
		}

		/**
		 * 設定要求に対する応答フレームの処理
		 * @param seoj 受信フレーム内のSEOJ
		 * @param frame 受信フレームオブジェクト
		 */
		private void onReceiveReportOfSet(ElClassBase seoj, ElFrame frame) {
			for(ElProp prop : frame.getLstEchonetProp()) {
				onReceivePropertyReportOfSet(
						seoj,
						frame.getStrTid(),
						frame.getStrEsv(),
						prop,
						"00".equals(prop.getStrPdc()));
				// memo : Set系のリクエストに対し、対向ノードが要求を受理した場合は、受理できたプロパティのPDCが0つまりEDT無しにして応答する。
			}
			return;
		}

		/**
		 * 情報設定要求に対する応答フレームのプロパティ単位の処理
		 * @param seoj 受信フレーム内のSEOJ
		 * @param strTid TID値（16進数文字列表記）
		 * @param strEsv ESV値（16進数文字列表記）
		 * @param objProp プロパティオブジェクト
		 * @param isSuccess 受理の成否判定（true: 受理、false:未受理）
		 */
		protected void onReceivePropertyReportOfSet(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			return;
		}

		/**
		 * 情報通知要求に対する応答フレームの処理
		 * @param seoj 受信フレーム内のSEOJ
		 * @param frame 受信フレームオブジェクト
		 */
		private void onReceiveReportOfInfC(ElClassBase seoj, ElFrame frame) {
			for(ElProp prop : frame.getLstEchonetProp()) {
				onReceivePropertyReportOfInfC(
						seoj,
						frame.getStrTid(),
						frame.getStrEsv(),
						prop);
			}
			return;
		}

		/**
		 * 情報通知要求に対する回答フレームのプロパティ単位の処理
		 * @param seoj 受信フレーム内のSEOJ
		 * @param strTid TID値（16進数文字列表記）
		 * @param strEsv ESV値（16進数文字列表記）
		 * @param objProp プロパティオブジェクト
		 */
		protected void onReceivePropertyReportOfInfC(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
			return;
		}
	}

	/**
	 * 要求系フレーム受信時の処理<br>
	 * 応答用フレームを作成
	 * @param rcvFrame 受信フレームオブジェクト
	 * @param resFrameList 応答フレームを格納用リスト
	 */
	public void onReceiveRequestFrame(ElFrame rcvFrame, List<ElFrame> resFrameList) {
		/**
		 * 応答用フレームの初期化
		 */
		ElFrame resFrame = rcvFrame.generateResponseFrame();

		/**
		 * 各プロパティの処理
		 */
		// 受信フレームがSET系の要求フレームだった場合
		if(rcvFrame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETI) ||
				rcvFrame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETC)){
			List<ElProp> propList = rcvFrame.getLstEchonetProp();
			for(ElProp prop : propList) {
				this.onReceiveSetRequest(prop, resFrame);
			}
		}
		// 受信フレームがGET系の要求フレームだった場合
		if(rcvFrame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_GET) ||
				rcvFrame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INF_REQ)){
			List<ElProp> propList = rcvFrame.getLstEchonetProp();
			for(ElProp prop : propList) {
				this.onReceiveGetRequest(prop, resFrame);
			}
		}
		// memo:  通知内容に対する処理はReportProcessor内で実施済みの前提となる。
		if(rcvFrame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INFC)) {
			List<ElProp> propList = rcvFrame.getLstEchonetProp();
			for(ElProp prop : propList) {
				this.onReceiveInformRequest(prop, resFrame);
			}
		}

		// SetGetは未サポートの為、不可応答を返す
		// OPCSet に0、OPCGet に0 を格納し、「不可応答」(0x5E)を応答として返すものとする。
		if(rcvFrame.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SET_GET)) {
			resFrame.setStrEsv(ElFrame.ESV_SET_GET_SNA);
			resFrame.getLstEchonetPropSet().clear();
			resFrame.getLstEchonetPropGet().clear();
		}

		/**
		 * 応答用バッファへ格納
		 */
		if (! ElFrame.ESV_NO_RES.equals(resFrame.getStrEsv())) { // ESV_NO_RESは応答不要とする。
			resFrameList.add(resFrame);
		}

		return;
	}

	/**
	 * Set系リクエスト受信時のハンドラ
	 * @param prop 要求フレーム内のプロパティオブジェクト
	 * @param resFrame 応答用フレームのひな形（プロパティ部分以外設定済み）
	 */
	public void onReceiveSetRequest(ElProp prop, ElFrame resFrame) {
		
		/**
		 * プロパティ値の設定処理
		 */
		boolean isSuccess = false;
		ElEventListener listener = getElProcess().getElBulkEventProcessor();
		// プロパティリストに含まれているか？
		try {
			// プロパティマップ内に記述がある
			if(this.getMapSetProps().contains(prop.getStrEpc())) {
				// プロパティの妥当性を検査
				boolean isValid = this.isValidEdtValue(prop);
				// 妥当性検査後の処理
				if(listener != null ) {
					listener.onAfterPropertyValidation(this, prop, isValid);
				}
				if( isValid ) {
					isSuccess = this.setEdtValueToApp(prop);
					if(listener != null ) {
						listener.onAfterPropertySetting(this, prop, isValid);
					}
				} else {
					isSuccess = false;
				}
			}
			// 含まれていない場合
			else {
				ElLog.t(TAG,"指定されたSetPropertyはプロパティマップの中に含まれていません。");
				isSuccess = false;
			}
		}catch(Exception ex) {
			isSuccess = false;
			if(listener != null) {
				listener.onCatchExceptionOnRequest(ex);
			}
		}
		/**
		 * レスポンス用フレームのプロパティを設定
		 */
		if(isSuccess) {
			resFrame.addPropForResponse(prop.getStrEpc());
		} else {
			resFrame.addPropForResponse(prop.getStrEpc(), prop.getStrEdt());
		}
	}
	/**
	 * Get系リクエスト受信時のハンドラ
	 * @param prop 要求フレーム内のプロパティオブジェクト
	 * @param resFrame 応答用フレームのひな形（プロパティ部分以外設定済み）
	 */
	public void onReceiveGetRequest(ElProp prop, ElFrame resFrame) {
		
		/**
		 * プロパティ値の取得処理
		 */
		String strEdt = null;
		ElProp newProp = new ElProp(prop.getStrEpc());
		ElEventListener listener = getElProcess().getElBulkEventProcessor();
		try {
			// プロパティ値の取得
			if (ElFrame.ESV_GET_RES.equalsIgnoreCase(resFrame.getStrEsv()) ||
					ElFrame.ESV_GET_SNA.equalsIgnoreCase(resFrame.getStrEsv())) {
				if(this.getMapGetProps().contains(prop.getStrEpc())) {
					strEdt = this.getEdtValueFromApp(prop.getStrEpc());
				} else {
					ElLog.t(TAG,"指定されたGetPropertyはプロパティマップの中に含まれていません。");
					strEdt = null;
				}
			} else {
				strEdt = this.getEdtValueFromApp(prop.getStrEpc());
			}
			newProp.setStrEdt(strEdt);
			if(listener != null ) {
				listener.onAfterPropertyGetting(this, newProp);
			}
			// プロパティの妥当性を検査
			boolean isValid = this.isValidEdtValue(newProp);
			// 妥当性検査後の処理
			if(listener != null ) {
				listener.onAfterPropertyValidation(this, newProp, isValid);
			}
			if(! isValid) {
				strEdt = null;
			}
		}catch(Exception ex) {
			strEdt = null;
			if(listener != null) {
				listener.onCatchExceptionOnRequest(ex);
			}
		}

		/**
		 * レスポンス用フレームのプロパティを設定
		 */
		if(strEdt == null || strEdt.equals("")) {
			resFrame.addPropForResponse(newProp.getStrEpc());
		} else {
			resFrame.addPropForResponse(newProp.getStrEpc(), newProp.getStrEdt());
		}

	}
	/**
	 * Inform系リクエスト受信時のハンドラ
	 * @param prop 要求フレーム内のプロパティオブジェクト
	 * @param resFrame 応答用フレームのひな形（プロパティ部分以外設定済み）
	 */
	public void onReceiveInformRequest(ElProp prop, ElFrame resFrame) {
		// EPCのみセット。PDCは0x00、EDTはブランクとなる。
		resFrame.getLstEchonetProp().add(new ElProp(prop.getStrEpc()));
	}

	/**
	 * リクエスト送信用処理の集約クラス
	 */
	public static class Sender{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElClassBase.Sender";

		/**
		 * 所属するELプロセスオブジェクト
		 */
		private ElProcess elProcess;

	    /**
	     * TIDカウンタ（EchoFrame内のTID要素）
	     */
	    private static int TID_NEXT = 1;

		/**
		 * 送信先ホスト名（ＩＰアドレス）
		 */
		protected String remoteHostName;

		/**
		 * 送信用フレームオブジェクト
		 */
		protected ElFrame elFrame;

	    /**
	     * TIDの値を保持する
	     */
	    public Integer intTid;

		/**
		 * SEOJオブジェクト（フレーム構築用）
		 */
		protected ElClassBase objSeoj;

		/**
		 * DEOJオブジェクト（フレーム構築用）
		 */
		protected ElClassBase objDeoj;

		/**
		 * DEOJオブジェクト（フレーム構築用）
		 */
		protected String strEsv;

		/**
	     * リクエストフレーム構築用のプロパティリスト
		 */
		protected List<ElProp> listProperty;


		/**
		 * コンストラクタ
		 * @param elProcess ELプロセスオブジェクト
		 * @param remoteHostName リモート（対向ノード）ホスト名称（IPアドレス）
		 * @param objSeoj SEOJオブジェクト
		 * @param objDeoj DEOJオブジェクト
		 * @param strEsv ESVコード（16進数文字列）
		 */
		public Sender(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			this.elProcess = elProcess;
			this.remoteHostName = remoteHostName;
			this.objSeoj = objSeoj;
			this.objDeoj = objDeoj;
			this.strEsv = strEsv;
			this.listProperty = new ArrayList<ElProp>();
		}

		/**
		 * 所属するELプロセスのオブジェクトを返す
		 * @return ElProcess ELプロセスオブジェクト
		 */
		public ElProcess getElProcess() {
			return this.elProcess;
		}

		/**
		 * ElFrameオブジェクトを取得
		 * @return ElFrameオブジェクト
		 */
		public ElFrame getElFrame() {
			return elFrame;
		}
		public ElFrame getFrame() throws Exception {
			if(this.elFrame == null || this.elFrame.getStrTid().equals(ElFrame.DEFAULT_TID)) {
				this.setup();
			}
			return elFrame;
		}
		/**
		 * リモートホスト名(IPアドレス)の設置値の取得
		 * @return String リモートホストのホスト名（IPアドレス）
		 */
		public String getRemoteHostName() {
			return remoteHostName;
		}

		/**
		 * リモートホスト名(IPアドレス)の設置値を設定
		 * @param remoteHostName リモートホストのホスト名（IPアドレス）
		 * @return Sender Senderオブジェクト
		 */
		public Sender setRemoteHostName(String remoteHostName) {
			this.remoteHostName = remoteHostName;
			return this;
		}

		/**
		 * フレームのセットアップ処理（プロパティ部分以外）
		 */
		private void setupFrameBase() {

			/**
			 * 送信用フレームの構築
			 */
			this.intTid  = this.getNextTid(); // TIDの採番

			/**
			 * フレームを組立てる（内部でOPCを算出）
			 */
			this.elFrame = new ElFrame();
			this.elFrame.setRemoteHostName(this.remoteHostName); // 一斉同報の為
			this.elFrame.setStrTid(convTidDecToHexString(intTid));
			this.elFrame.setStrSeoj(this.objSeoj.getStrEojCode());
			this.elFrame.setStrDeoj(this.objDeoj.getStrEojCode());
			this.elFrame.setStrEsv(this.strEsv);
		}


		/**
		 * 次のTIDを取得する
		 * @return int
		 */
		private synchronized int getNextTid() {
			if(TID_NEXT == TID_MAX) {
				TID_NEXT = 1;
				return TID_MAX;
			}
			return TID_NEXT++;
		}


		/**
		 * プロパティのセットアップ処理
		 */
		protected void setupPropsToFrame() {
			// ECP,PDC,EDTのプロパティセット
			this.elFrame.setLstEchonetProp(this.listProperty);
		}

		/**
		 * フレームオブジェクトのセットアップ処理
		 * @return ElClassBase.Senderオブジェクト
		 * @throws Exception 任意の例外
		 */
		public ElClassBase.Sender setup() throws Exception{

			/**
			 * フレームオブジェクトのセットアップ処理
			 */
			this.setupFrameBase();

			/**
			 * フレームへプロパティ情報のセットアップ
			 */
			this.setupPropsToFrame();


			return this;

		}

		/**
		 * 送信処理
		 * @return 送信したフレームオブジェクト
		 * @throws Exception 任意の例外
		 */
		public ElFrame send() throws Exception{


			// フレームオブジェクトのセットアップ処理
			// TIDがNULLまたはデフォルトのままであればセットアップはされていないと判断。
			// TIDが採番されているのであれば別処理でsetup済みと判断。
			if(this.elFrame == null || this.elFrame.getStrTid().equals(ElFrame.DEFAULT_TID)) {
				this.setup();
			}

			// フレームの生成（16進数文字列）
			// （デバッグ用出力）
			String strFrame = this.elFrame.buildEchonetFrameHexStr();
			ElLog.t(TAG,"Request Message : [" + strFrame+ "]", true);

			/**
			 *  送信処理の実行
			 */
			elProcess.getChannel().send(elFrame);

			return this.elFrame;
		}
	}

	/**
	 * Set系処理の集約クラス
 	 * 0x60 : プロパティ値書込み要求（応答不要）
	 * 0x61 : プロパティ値書込み要求（応答要）
	 */
	public static class ElSetProps extends ElClassBase.Sender{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElClassBase.ElSetProps";

		/**
		 * コンストラクタ
		 * @param elProcess ELプロセスオブジェクト
		 * @param remoteHostName リモート（対向ノード）ホスト名称（IPアドレス）
		 * @param objSeoj SEOJオブジェクト
		 * @param objDeoj DEOJオブジェクト
		 * @param strEsv ESVコード（16進数文字列）
		 */
		public ElSetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}
	}

	/**
	 * Get系処理の集約クラス
	 * 0x62 : プロパティ値読出し要求
	 */
	public static class ElGetProps extends ElClassBase.Sender {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElClassBase.ElGetProps";

		/**
		 * コンストラクタ
		 * @param elProcess ELプロセスオブジェクト
		 * @param remoteHostName リモート（対向ノード）ホスト名称（IPアドレス）
		 * @param objSeoj SEOJオブジェクト
		 * @param objDeoj DEOJオブジェクト
		 * @param strEsv ESVコード（16進数文字列）
		 */
		public ElGetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}
	}

	/**
	 * 通知系処理の集約クラス
 	 * 0x73 : プロパティ値通知
	 * 0x63 : プロパティ値通知要求
	 */
	public static class ElInformProps extends ElClassBase.Sender {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElClassBase.ElInformProps";

		/**
		 * コンストラクタ
		 * @param elProcess ELプロセスオブジェクト
		 * @param remoteHostName リモート（対向ノード）ホスト名称（IPアドレス）
		 * @param objSeoj SEOJオブジェクト
		 * @param objDeoj DEOJオブジェクト
		 * @param strEsv ESVコード（16進数文字列）
		 */
		public ElInformProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * プロパティのセットアップ処理
		 */
		@Override
		public void setupPropsToFrame() {

			if(ElFrame.ESV_INF_REQ.equalsIgnoreCase(this.strEsv)) {
				super.setupPropsToFrame();
			}
			else { // INF,INFCの場合
				/**
				 *  プロパティに載せるパラメータを取得してセットする。
				 */

				// TODO : seojを自ノードから改めて取得するべきか？
				ElClassBase objSeoj = this.objSeoj;

				for(ElProp prop : this.listProperty) {
					String strEdt = objSeoj.getEdtValueFromApp(prop.getStrEpc());
					if(objSeoj.isValidEdtValue(new ElProp(prop.getStrEpc(), strEdt))) {
						prop.setStrEdt(strEdt);
					}
				}
				super.setupPropsToFrame();
			}
		}
	}

	/**
	 * アプリケーションサイドからEDTの値を取得するコールバックメソッド<br>
	 * アプリケーションは受け取ったEPCの値に対応するEDT値を戻り値として設定する。
	 * 受理不可の場合は、nullをセットする。
	 * @param epc EPCの値
	 * @return String アプリケーションから設定するEDTの値（16進数文字列）
	 */
	protected synchronized String getEdtValueFromApp(String epc) {
		return null;
	}
	/**
	 * アプリケーションサイドが入力したEDTの値の妥当性を判定するコールバック<br>
	 * プロパティオブジェクトを受け取り内部のEPCとEDTの対応から妥当性を判定し返す為のバリデーション
	 * アプリケーションは受け取ったオブジェクト内容を確認し判定値を戻り値に設定する。
	 * @param prop プロパティオブジェクト
	 * @return boolean 判定値をアプリケーションがセットする（true: 妥当、false:非妥当）
	 */
	protected synchronized boolean isValidEdtValue(ElProp prop) {
		return false;
	}
	/**
	 * アプリケーションサイドに値を設定する際に呼ばれるコールバック<br>
	 * アプリケーションは受け取ったプロパティオブジェクトの内容を確認し、
	 * EPCに対応するEDT値を元に機器側を操作する。<br>
	 * また、受理の可否を戻り値にセットする。
	 * @param prop プロパティオブジェクト
	 * @return boolean 設定の結果をアプリケーションがセットする（ture: 受理、false: 受理不可）
	 */
	protected synchronized boolean setEdtValueToApp(ElProp prop) {
		return false;
	}

	/**
	 * このEOJがローカルノード所属のEOJであるか否かをIPの比較で判定する
	 * @return boolean 判定結果（true: 所属、fase: 非所属）
	 */
	public boolean isLocalEoj() {
		ElOneNode thisNode = this.getNodeBelongsTo();
		if(thisNode == null) {
			return false;
		}
		ElOneNode localNode = getElProcess().getLocalNode();
		if(localNode == null) {
			return false;
		}
		return thisNode.getIpAddress().equals(thisNode.getIpAddress());
	}
	/**
	 * EOJの基本情報を文字列にて返す。（[]付）
	 * @return EOJ基本情報の文字列表現
	 */
	public String getEojCodeStructureFormat() {
		String msg = "";
		msg +="[" + this.getStrClassGroupCode()+"]";
		msg +="[" + this.getStrClassCode()+"]";
		msg +="[" + this.getStrEntityCode()+"]";
		return msg;
	}
	/**
	 * EOJの基本情報を文字列にて返す。（見出し付、[]付）
	 * @return EOJ基本情報の文字列表現
	 */
	public String getEojCodeStructureFormatWithTitle() {
		String msg = "";
		msg +=" Group:[" + this.getStrClassGroupCode()+"]";
		msg +=" Class:[" + this.getStrClassCode()+"]";
		msg +=" Entity:[" + this.getStrEntityCode()+"]";
		return msg;
	}


	/**
	 * コードからEOJオブジェクトを生成する。（ノードプロファイルを含む）
	 * @param strClassGroupCode クラスグループコード
	 * @param strClassCode クラスコード
	 * @param strEntityCode エンティティコード
	 * @return ElClassBase 各種OEJオブジェクト
	 */
	public static synchronized ElClassBase instanceEojByCode(String strClassGroupCode, String strClassCode, String strEntityCode) {

		String strCode = strClassGroupCode + strClassCode;
		switch (strCode) {
			// ノードプロファイルクラス
			case NodeProfileClass.CLASS_GROUP_CODE + NodeProfileClass.CLASS_CODE:
				return new NodeProfileClass(strEntityCode);
			default:
				return instanceDeviceEojByCode(strClassGroupCode, strClassCode, strEntityCode);
		}
	}

	/**
	 * コードからデバイス系EOJオブジェクトを生成する。
	 * @param strClassGroupCode クラスグループコード
	 * @param strClassCode クラスコード
	 * @param strEntityCode エンティティコード
	 * @return DeviceObjectSuperClass 各種デバイス系OEJオブジェクト
	 */
	public static synchronized DeviceObjectSuperClass instanceDeviceEojByCode(String strClassGroupCode, String strClassCode, String strEntityCode) {

		String strCode = strClassGroupCode + strClassCode;
		switch (strCode) {
			// 一般照明クラス
			case GeneralLightingClass.CLASS_GROUP_CODE + GeneralLightingClass.CLASS_CODE:
				return new GeneralLightingClass(strEntityCode);
			//  電気温水器クラス ElectricWaterHeater
			case ElectricWaterHeaterClass.CLASS_GROUP_CODE + ElectricWaterHeaterClass.CLASS_CODE:
				return new ElectricWaterHeaterClass(strEntityCode);
			// コントローラクラス
			case ControllerClass.CLASS_GROUP_CODE + ControllerClass.CLASS_CODE:
				return new ControllerClass(strEntityCode);
			// 電流値メータクラス
			case CurrentValueSensorClass.CLASS_GROUP_CODE + CurrentValueSensorClass.CLASS_CODE:
				return new CurrentValueSensorClass(strEntityCode);
			// 高圧スマート電力量メータクラス
			case HighVoltageSmartMeter.CLASS_GROUP_CODE + HighVoltageSmartMeter.CLASS_CODE:
				return new HighVoltageSmartMeter(strEntityCode);
			// 住宅用太陽光発電クラス
			case HouseholdSolarPowerGenerationClass.CLASS_GROUP_CODE + HouseholdSolarPowerGenerationClass.CLASS_CODE:
				return new HouseholdSolarPowerGenerationClass(strEntityCode);
			// 低圧スマート電力量メータクラス
			case LowVoltageSmartMeter.CLASS_GROUP_CODE + LowVoltageSmartMeter.CLASS_CODE:
				return new LowVoltageSmartMeter(strEntityCode);
			// 蓄電池クラス
			case StorageBatteryClass.CLASS_GROUP_CODE + StorageBatteryClass.CLASS_CODE:
				return new StorageBatteryClass(strEntityCode);
			// 未知のクラス
			// 分電盤メータリングクラス
			case PowerDistributionBoardMetering.CLASS_GROUP_CODE + PowerDistributionBoardMetering.CLASS_CODE:
				 return new PowerDistributionBoardMetering(strEntityCode);
			default :
					return new DeviceObjectSuperClass(strClassGroupCode, strClassCode, strEntityCode);
		}
	}

	/**
	 * クラスコードマップ
	 */
	public static Map<String, String> MAP_CLASS_NAME = new HashMap<String, String>() {
		{
			put(NodeProfileClass.CLASS_GROUP_CODE + NodeProfileClass.CLASS_CODE, "ノードプロファイルクラス");

			put(GeneralLightingClass.CLASS_GROUP_CODE + GeneralLightingClass.CLASS_CODE, "一般照明クラス");
			put(ElectricWaterHeaterClass.CLASS_GROUP_CODE + ElectricWaterHeaterClass.CLASS_CODE, " 電気温水器クラス");
			put(ControllerClass.CLASS_GROUP_CODE + ControllerClass.CLASS_CODE, "コントローラクラス");
			put(CurrentValueSensorClass.CLASS_GROUP_CODE + CurrentValueSensorClass.CLASS_CODE, "電流値メータクラス");
			put(HighVoltageSmartMeter.CLASS_GROUP_CODE + HighVoltageSmartMeter.CLASS_CODE, "高圧スマート電力量メータクラス");
			put(HouseholdSolarPowerGenerationClass.CLASS_GROUP_CODE + HouseholdSolarPowerGenerationClass.CLASS_CODE, "住宅用太陽光発電クラス");
			put(LowVoltageSmartMeter.CLASS_GROUP_CODE + LowVoltageSmartMeter.CLASS_CODE, "低圧スマート電力量メータクラス");
			put(StorageBatteryClass.CLASS_GROUP_CODE + StorageBatteryClass.CLASS_CODE, "蓄電池クラス");
			put(PowerDistributionBoardMetering.CLASS_GROUP_CODE + PowerDistributionBoardMetering.CLASS_CODE, "分電盤メータリングクラス");
		}
	};


	/**
	 * EPCの名称を取得
	 * @param strClassGroupCode クラスグループコード（16進数文字列）
	 * @param strClassCode クラスコード（16進数文字列）
	 * @param strEpc EPC値（16進数文字列）
	 * @return String EPCの名称
	 */
	public static String getEpcName(String strClassGroupCode, String strClassCode, String strEpc) {
		String strEojClassCode = strClassGroupCode + strClassCode;
		String retStr = null;
		switch (strEojClassCode) {
			// ノードプロファイルクラス
			case NodeProfileClass.CLASS_GROUP_CODE + NodeProfileClass.CLASS_CODE:
				retStr = getNodeProfileEpcName(strEojClassCode, strEpc);
				break;
			// 機器オブジェクト
			default:
				retStr = getDeviceEpcName(strEojClassCode, strEpc);
		}
		if(retStr == null) {
			retStr = "該当なし";
		}
		return retStr;
	}

	/**
	 * ノードプロファイルのEPCの名称を取得
	 * @param strEojClassCode クラスグループコード+クラスコード（16進数文字列）
	 * @param strEpc EPC値（16進数文字列）
	 * @return String EPCの名称
	 */
	private static String getNodeProfileEpcName(String strEojClassCode, String strEpc) {
		String retStr = null;
		retStr = NodeProfileClass.MAP_EPC_NAME.get(strEpc);
		if(retStr == null) {
			retStr = ProfileObjectSuperClass.MAP_EPC_NAME.get(strEpc);
		}
		return retStr;
	}

	/**
	 * 機器クラスのEPCの名称を取得
	 * @param strEojClassCode クラスグループコード+クラスコード（16進数文字列）
	 * @param strEpc EPC値（16進数文字列）
	 * @return String EPCの名称
	 */
	private static String getDeviceEpcName(String strEojClassCode, String strEpc) {
		String retStr = null;
		switch (strEojClassCode) {
				// 一般照明クラス
				case GeneralLightingClass.CLASS_GROUP_CODE + GeneralLightingClass.CLASS_CODE:
					retStr = GeneralLightingClass.MAP_EPC_NAME.get(strEpc);
					break;
                 // 電気温水器クラス ElectricWaterHeaterClass
			    case ElectricWaterHeaterClass.CLASS_GROUP_CODE + ElectricWaterHeaterClass.CLASS_CODE:
			    	retStr = ElectricWaterHeaterClass.MAP_EPC_NAME.get(strEpc);
				break;

				// コントローラクラス
				case ControllerClass.CLASS_GROUP_CODE + ControllerClass.CLASS_CODE:
					retStr = ControllerClass.MAP_EPC_NAME.get(strEpc);
					break;
				// 電流値メータクラス
				case CurrentValueSensorClass.CLASS_GROUP_CODE + CurrentValueSensorClass.CLASS_CODE:
					retStr = CurrentValueSensorClass.MAP_EPC_NAME.get(strEpc);
					break;
				// 高圧スマート電力量メータクラス
				case HighVoltageSmartMeter.CLASS_GROUP_CODE + HighVoltageSmartMeter.CLASS_CODE:
					retStr = HighVoltageSmartMeter.MAP_EPC_NAME.get(strEpc);
					break;
				// 住宅用太陽光発電クラス
				case HouseholdSolarPowerGenerationClass.CLASS_GROUP_CODE + HouseholdSolarPowerGenerationClass.CLASS_CODE:
					retStr = HouseholdSolarPowerGenerationClass.MAP_EPC_NAME.get(strEpc);
					break;
				// 低圧スマート電力量メータクラス
				case LowVoltageSmartMeter.CLASS_GROUP_CODE + LowVoltageSmartMeter.CLASS_CODE:
					retStr = LowVoltageSmartMeter.MAP_EPC_NAME.get(strEpc);
					break;
				// 蓄電池クラス
				case StorageBatteryClass.CLASS_GROUP_CODE + StorageBatteryClass.CLASS_CODE:
					retStr = StorageBatteryClass.MAP_EPC_NAME.get(strEpc);
					break;
				//分電盤クラス
				case PowerDistributionBoardMetering.CLASS_GROUP_CODE + PowerDistributionBoardMetering.CLASS_CODE:
					retStr = PowerDistributionBoardMetering._MAP_EPC_NAME.get(strEpc);
					break;
				default:
					retStr = null;
		}
		if(retStr == null) {
			retStr = DeviceObjectSuperClass.MAP_EPC_NAME.get(strEpc);
		}
		return retStr;
	}
}
