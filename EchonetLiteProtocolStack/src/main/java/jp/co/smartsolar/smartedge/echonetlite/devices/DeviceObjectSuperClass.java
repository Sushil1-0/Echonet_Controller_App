package jp.co.smartsolar.smartedge.echonetlite.devices;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

/**
 * 機器オブジェクトスーパークラス
 */
public class DeviceObjectSuperClass extends ElClassBase {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "DeviceObjectSuperClass";

	/**
	 * EPC : 動作状態
	 * ON/OFFの状態を示す。
	 */
	public static final String EPC_OPERATION_STATUS = "80";

	/**
	 * EDT : 動作状態（ON）
	 */
	public static final String EDT_OPERATION_STATUS_ON = "30";
	/**
	 * EDT : 動作状態（OFF）
	 */
	public static final String EDT_OPERATION_STATUS_OFF = "31";

	/**
	 * EPC : 設置場所
	 * 設置場所を示す。
	 */
	public static final String EPC_INSTALLATION_LOCATION = "81";

	/**
	 * EPC : 規格バージョン情報
	 * 対応するAPPENDIXのリリース番号を示す。
	 */
	public static final String EPC_STANDARD_VERSION_INFO = "82";

	/**
	 * EPC : 識別番号
	 * オブジェクトを固有に識別する番号。
	 */
	public static final String EPC_IDENTIFICATION_NUM = "83";

	/**
	 * EPC : 瞬時消費電力計測値
	 * 機器の瞬時消費電力をＷで示す。（0x0000~0xFFFD (=0~65533W)）
	 */
	public static final String EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION = "84";

	/**
	 * EPC : 積算消費電力計測値
	 * 機器の精算消費電力をkWhで示す。（0x00000000~0x3B9AC9FF(=0~999,999.999kWh)）
	 */
	public static final String EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION = "85";

	/**
	 * EPC : メーカー異常コード
	 * 各メーカー独自の異常コードを示す。
	 */
	public static final String EPC_MAKER_ERROR_CODE = "86";

	/**
	 * EPC : 電流制限設定
	 * 電流制限の設定値を示す。
	 */
	public static final String EPC_CURRENT_LIMIT_SETTING = "87";

	/**
	 * EPC : 異常発生状態
	 * 何らかの異常（センサトラブル等）の発生状況を示す。
	 */
	public static final String EPC_ABNORMAL_STATE = "88";
	/**
	 * EDT : 異常発生状態（異常発生有）
	 */
	public static final String EDT_ABNORMAL_STATE_TRUE = "41";
	/**
	 * EDT : 異常発生状態（異常発生無）
	 */
	public static final String EDT_ABNORMAL_STATE_FALSE = "42";

	/**
	 * EPC : 異常内容
	 */
	public static final String EPC_ABNORMAL_CONTENT = "89";

	/**
	 * EPC : メーカーコード
	 * 3バイトで指定。
	 */
	public static final String EPC_MAKER_CODE = "8A";

	/**
	 * EPC : 事業場コード
	 * 3バイトで指定。
	 */
	public static final String EPC_PLANT_CODE = "8B";

	/**
	 * EPC : 商品コード
	 * ASCIIコードで指定。
	 */
	public static final String EPC_PRODUCT_CODE = "8C";

	/**
	 * EPC : 製造番号
	 * ASCIIコードで指定。
	 */
	public static final String EPC_SERIAL_NUMBER = "8D";

	/**
	 * EPC : 製造年月日
	 * 4バイトで指定。
	 */
	public static final String EPC_MADE_YMD = "8E";

	/**
	 * EPC : 節電動作設定
	 * 機器の節電動作状態を示す。
	 */
	public static final String EPC_POWER_SAVING_OPERATION_SETTING = "8F";
	/**
	 * EDT : 節電動作設定（節電動作中）
	 */
	public static final String EDT_POWER_SAVING_OPERATION_SETTING_SAVING = "41";
	/**
	 * EDT : 節電動作設定（通常動作中）
	 */
	public static final String EDT_POWER_SAVING_OPERATION_SETTING_NORMAL = "42";

	/**
	 * EPC : 遠隔操作設定
	 * 公衆回線を介した操作か否かを示す。（0x41,0x42）
	 * 通信回線の状態が正常か否かを示す。（0x61,0x62）
	 */
	public static final String EPC_REMOTE_OPERATION_SETTING = "93";
	/**
	 * EDT : 遠隔操作設定（公衆回線未経由操作）
	 */
	public static final String EDT_REMOTE_OPERATION_SETTING_NO_USE_PUBLIC_LINE = "41";
	/**
	 * EDT : 遠隔操作設定（公衆回線経由操作）
	 */
	public static final String EDT_REMOTE_OPERATION_SETTING_USE_PUBLIC_LINE = "42";
	/**
	 * EDT : 遠隔操作設定（通信回線正常（公衆回線経由の操作不可））
	 */
	public static final String EDT_REMOTE_OPERATION_SETTING_USE_PUBLIC_LINE_IMPOSIBLE = "61";
	/**
	 * EDT : 通信回線正常（公衆回線経由の操作可能）
	 */
	public static final String EDT_REMOTE_OPERATION_SETTING_USE_PUBLIC_LINE_POSIBLE = "62";


	/**
	 * EPC : 現在時刻設定
	 * 現在時刻HH：MM
	 * 0x00～0x17 : 0x00～0x3B
	 * （＝0～23）：（＝0～59）
	 */
	public static final String EPC_CURRENT_TIME_SETTING = "97";

	/**
	 * EPC : 現在年月日設定
	 * 現在年月日YYYY：MM：DD
	 * 1～0x270F：1～0x0C：1～0x1F
	 * (=1～9999)：(=1～12)：(=1～31)
	 */
	public static final String EPC_CURRENT_YMD_SETTING = "98";


	/**
	 * EPC : 電力制限設定
	 * 電力制限の設定値をW で示す。
	 * 0x0000~0xFFFF（0～65535W）
	 */
	public static final String EPC_POWER_LIMIT_SETTING = "99";


	/**
	 * EPC : 積算運転時間
	 * 現在までの運転時間の積算値を単位1 バイト、時間4 バイトで示す。
	 * 1 バイト目：単位を示す
	 * 秒：0x41、分：0x42
	 * 時：0x43、日:0x44
	 * 2～5 バイト目：
	 * 1 バイト目に示される時間単位における経過時間を示す
	 * 0x00000000～0xFFFFFFFF
	 * (0～4294967295)
	 */
	public static final String EPC_INTEGRATED_OPERATING_TIME = "9A";


//	/**
//	 * EPC : SetMプロパティマップ
//	 */
//	public static final String EPC_SET_M_PROP_MAP = "9B"; // ※ECHONET Lite 機器は搭載不可
//
//	/**
//	 * EPC : GetMプロパティマップ
//	 */
//	public static final String EPC_GET_M_PROP_MAP = "9C"; // ※ECHONET Lite 機器は搭載不可
//
	/**
	 * EPC : 状変アナウンスプロパティマップ
	 */
	public static final String EPC_CONV_ANNOUNCE_PROP_MAP = "9D";

	/**
	 * EPC : Setプロパティマップ
	 */
	public static final String EPC_SET_PROP_MAP = "9E";

	/**
	 * EPC : Getプロパティマップ
	 */
	public static final String EPC_GET_PROP_MAP = "9F";


	/**
	 * 規格Version情報
	 */
	public static final String EDT_STANDARD_VERSION_INFORMATION = "00004B00";

	/**
	 * コンストラクタ
	 */
	public DeviceObjectSuperClass() {
		super();
	}
	/**
	 * コンストラクタ
	 * @param classGroupCode クラスグループコード（16進数文字列）
	 * @param classCode クラスコード（16進数文字列）
	 */
	public DeviceObjectSuperClass( String classGroupCode, String classCode  ) {
		this();
		this.setStrClassGroupCode(classGroupCode);
		this.setStrClassCode(classCode);
	}
	/**
	 * コンストラクタ
	 * @param classGroupCode クラスグループコード（16進数文字列）
	 * @param classCode クラスコード（16進数文字列）
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public DeviceObjectSuperClass( String classGroupCode, String classCode, String entityCode  ) {
		this(classGroupCode, classCode);
		this.setStrEntityCode(entityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

		this.addMapGetProps(EPC_OPERATION_STATUS);
		this.addMapGetProps(EPC_INSTALLATION_LOCATION);
		this.addMapGetProps(EPC_STANDARD_VERSION_INFO);
		this.addMapGetProps(EPC_IDENTIFICATION_NUM); // 必須ではない
//		this.addMapGetProps(EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION); // 必須ではない
//		this.addMapGetProps(EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION); // 必須ではない
//		this.addMapGetProps(EPC_MAKER_ERROR_CODE); // 必須ではない
//		this.addMapGetProps(EPC_CURRENT_LIMIT_SETTING); // 必須ではない
		this.addMapGetProps(EPC_ABNORMAL_STATE);
//		this.addMapGetProps(EPC_ABNORMAL_CONTENT); // 必須ではない
		this.addMapGetProps(EPC_MAKER_CODE);
//		this.addMapGetProps(EPC_PLANT_CODE); // 必須ではない
		this.addMapGetProps(EPC_PRODUCT_CODE); // 必須ではない
//		this.addMapGetProps(EPC_SERIAL_NUMBER); // 必須ではない
//		this.addMapGetProps(EPC_MADE_YMD); // 必須ではない
//		this.addMapGetProps(EPC_POWER_SAVING_OPERATION_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_REMOTE_OPERATION_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_CURRENT_TIME_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_CURRENT_YMD_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_POWER_LIMIT_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_INTEGRATED_OPERATING_TIME); // 必須ではない
//		this.addMapGetProps(EPC_SET_M_PROP_MAP); // ※ECHONET Lite 機器は搭載不可
//		this.addMapGetProps(EPC_GET_M_PROP_MAP); // ※ECHONET Lite 機器は搭載不可
		this.addMapGetProps(EPC_CONV_ANNOUNCE_PROP_MAP);
		this.addMapGetProps(EPC_SET_PROP_MAP);
		this.addMapGetProps(EPC_GET_PROP_MAP);

//		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
		this.addMapSetProps(EPC_INSTALLATION_LOCATION);
//		this.addMapSetProps(EPC_CURRENT_LIMIT_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_POWER_SAVING_OPERATION_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_REMOTE_OPERATION_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_CURRENT_TIME_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_CURRENT_YMD_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_POWER_LIMIT_SETTING); // 必須ではない


		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
		this.addMapConvAnnounceProps(EPC_INSTALLATION_LOCATION);
		this.addMapConvAnnounceProps(EPC_ABNORMAL_STATE);
	}


	/**
	 * {@inheritDoc}
	 */
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewDeviceObjectFound(this);
	}


	/**
	 * Set系処理の集約クラス
	 * 0x60 : プロパティ値書込み要求（応答不要）
	 * 0x61 : プロパティ値書込み要求（応答要）
	 */
	public static class ElSetProps extends ElClassBase.ElSetProps{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "DeviceObjectSuperClass.ElSetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElSetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ON＝0x30，OFF＝0x31<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
			return this;
		}
		/**
		 * 設置場所<br>
		 * EPC                 : 0x81<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 設置場所を示す。<br>
		 * 2.2 設置場所プロパティ参照<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetInstallationLocation(String strEdt){
			listProperty.add(new ElProp(EPC_INSTALLATION_LOCATION, strEdt));
			return this;
		}
		/**
		 * 電流制限設定<br>
		 * EPC                 : 0x87<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流制限の設定値を示す(0～100%)。<br>
		 * 0x00～0x64 （=0～100%）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetCurrentLimitSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CURRENT_LIMIT_SETTING, strEdt));
			return this;
		}
		/**
		 * 節電動作設定<br>
		 * EPC                 : 0x8F<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の節電動作状態を示す。<br>
		 * 節電動作中=0x41<br>
		 * 通常動作中=0x42<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetPowerSavingOperationSetting(String strEdt){
			listProperty.add(new ElProp(EPC_POWER_SAVING_OPERATION_SETTING, strEdt));
			return this;
		}
		/**
		 * 遠隔操作設定<br>
		 * EPC                 : 0x93<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
		 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
		 * 公衆回線未経由操作＝0x41<br>
		 * 公衆回線経由操作＝0x42<br>
		 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
		 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
		 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetRemoteOperationSetting(String strEdt){
			listProperty.add(new ElProp(EPC_REMOTE_OPERATION_SETTING, strEdt));
			return this;
		}
		/**
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B<br>
		 * （＝0～23）：（＝0～59）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetCurrentTimeSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CURRENT_TIME_SETTING, strEdt));
			return this;
		}
		/**
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetCurrentYmdSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING, strEdt));
			return this;
		}
		/**
		 * 電力制限設定<br>
		 * EPC                 : 0x99<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力制限の設定値をW で示す。<br>
		 * 0x0000~0xFFFF（0～65535W）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElSetProps reqSetPowerLimitSetting(String strEdt){
			listProperty.add(new ElProp(EPC_POWER_LIMIT_SETTING, strEdt));
			return this;
		}
	}

	/**
	 * Get系処理の集約クラス
	 * 0x62 : プロパティ値読出し要求
	 */
	public static class ElGetProps extends ElClassBase.ElGetProps {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "DeviceObjectSuperClass.ElGetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElGetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ON＝0x30，OFF＝0x31<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 設置場所<br>
		 * EPC                 : 0x81<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 設置場所を示す。<br>
		 * 2.2 設置場所プロパティ参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetInstallationLocation(){
			listProperty.add(new ElProp(EPC_INSTALLATION_LOCATION));
			return this;
		}
		/**
		 * 規格Version 情報<br>
		 * EPC                 : 0x82<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 対応するAPPENDIXのリリース番号を示す。<br>
		 * 1 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 2 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 3 ﾊﾞｲﾄ目：リリース順.をASCIIで示す。<br>
		 * 4 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetStandardVersionInfo(){
			listProperty.add(new ElProp(EPC_STANDARD_VERSION_INFO));
			return this;
		}
		/**
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk<br>
		 * 0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット<br>
		 * 0x91～0x9F：IEEE802.11/11b<br>
		 * 0xA1：電灯線c 方式<br>
		 * 0xB1：IPv6/Ethernet<br>
		 * 0xB2：IPv6/6LoWPAN<br>
		 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
		 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
		 * 0x00：識別番号未設定<br>
		 * <br>
		 * 2 バイト目以降：固有番号フィールド<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetIdentificationNum(){
			listProperty.add(new ElProp(EPC_IDENTIFICATION_NUM));
			return this;
		}
		/**
		 * 瞬時消費電力計測値<br>
		 * EPC                 : 0x84<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の瞬時消費電力をWで示す。<br>
		 * 0x0000～0xFFFD（0～65533W）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetInstantElectricPowerConsumption(){
			listProperty.add(new ElProp(EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION));
			return this;
		}
		/**
		 * 積算消費電力計測値<br>
		 * EPC                 : 0x85<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の積算消費電力を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF（0～999,999.999kWh）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetIntegrationElectricPowerConsumption(){
			listProperty.add(new ElProp(EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION));
			return this;
		}
		/**
		 * メーカ異常コード<br>
		 * EPC                 : 0x86<br>
		 * データタイプ        : unsigned char×(MAX)225<br>
		 * サイズ              : Max 225 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>各メーカ独自の異常コードを示す。<br>
		 * 1 ﾊﾞｲﾄ目：異常コード部のデータサイズを示す。<br>
		 * 	2～4 ﾊﾞｲﾄ目：メーカコード<br>
		 * 5 ﾊﾞｲﾄ目以降：各メーカ独自の異常コード部<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetMakerErrorCode(){
			listProperty.add(new ElProp(EPC_MAKER_ERROR_CODE));
			return this;
		}
		/**
		 * 電流制限設定<br>
		 * EPC                 : 0x87<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流制限の設定値を示す(0～100%)。<br>
		 * 0x00～0x64 （=0～100%）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetCurrentLimitSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_LIMIT_SETTING));
			return this;
		}
		/**
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 何らかの異常（センサトラブル等）の発生状況を示す。<br>
		 * 異常発生有＝0x41，異常発生無＝0x42<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetAbnormalState(){
			listProperty.add(new ElProp(EPC_ABNORMAL_STATE));
			return this;
		}
		/**
		 * 異常内容<br>
		 * EPC                 : 0x89<br>
		 * データタイプ        : unsigned　short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 異常内容<br>
		 * 2.5異常内容プロパティ参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetAbnormalContent(){
			listProperty.add(new ElProp(EPC_ABNORMAL_CONTENT));
			return this;
		}
		/**
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトで指定<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetMakerCode(){
			listProperty.add(new ElProp(EPC_MAKER_CODE));
			return this;
		}
		/**
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetPlantCode(){
			listProperty.add(new ElProp(EPC_PLANT_CODE));
			return this;
		}
		/**
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetProductCode(){
			listProperty.add(new ElProp(EPC_PRODUCT_CODE));
			return this;
		}
		/**
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetSerialNumber(){
			listProperty.add(new ElProp(EPC_SERIAL_NUMBER));
			return this;
		}
		/**
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetMadeYmd(){
			listProperty.add(new ElProp(EPC_MADE_YMD));
			return this;
		}
		/**
		 * 節電動作設定<br>
		 * EPC                 : 0x8F<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の節電動作状態を示す。<br>
		 * 節電動作中=0x41<br>
		 * 通常動作中=0x42<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetPowerSavingOperationSetting(){
			listProperty.add(new ElProp(EPC_POWER_SAVING_OPERATION_SETTING));
			return this;
		}
		/**
		 * 遠隔操作設定<br>
		 * EPC                 : 0x93<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
		 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
		 * 公衆回線未経由操作＝0x41<br>
		 * 公衆回線経由操作＝0x42<br>
		 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
		 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
		 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetRemoteOperationSetting(){
			listProperty.add(new ElProp(EPC_REMOTE_OPERATION_SETTING));
			return this;
		}
		/**
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B<br>
		 * （＝0～23）：（＝0～59）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetCurrentTimeSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_TIME_SETTING));
			return this;
		}
		/**
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetCurrentYmdSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING));
			return this;
		}
		/**
		 * 電力制限設定<br>
		 * EPC                 : 0x99<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力制限の設定値をW で示す。<br>
		 * 0x0000~0xFFFF（0～65535W）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetPowerLimitSetting(){
			listProperty.add(new ElProp(EPC_POWER_LIMIT_SETTING));
			return this;
		}
		/**
		 * 積算運転時間<br>
		 * EPC                 : 0x9A<br>
		 * データタイプ        : unsigned char＋unsigned long<br>
		 * サイズ              : 1+4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在までの運転時間の積算値を単位1 バイト、時間4 バイトで示す。<br>
		 * 1 バイト目：単位を示す<br>
		 * 秒：0x41、分：0x42<br>
		 * 時：0x43、日:0x44<br>
		 * 2～5 バイト目：1 バイト目に示される時間単位における経過時間を示す<br>
		 * 0x00000000～0xFFFFFFFF<br>
		 * (0～4294967295)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetIntegratedOperatingTime(){
			listProperty.add(new ElProp(EPC_INTEGRATED_OPERATING_TIME));
			return this;
		}
		/**
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetConvAnnouncePropMap(){
			listProperty.add(new ElProp(EPC_CONV_ANNOUNCE_PROP_MAP));
			return this;
		}
		/**
		 * Set プロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetSetPropMap(){
			listProperty.add(new ElProp(EPC_SET_PROP_MAP));
			return this;
		}
		/**
		 * Get プロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElGetProps reqGetGetPropMap(){
			listProperty.add(new ElProp(EPC_GET_PROP_MAP));
			return this;
		}

	}

	/**
	 * 通知系処理の集約クラス
	 * 0x73 : プロパティ値通知
	 * 0x63 : プロパティ値通知要求
	 */
	public static class ElInformProps extends ElClassBase.ElInformProps {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "DeviceObjectSuperClass.ElInformProps";

		/**
		 * {@inheritDoc}
		 */
		public ElInformProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ON＝0x30，OFF＝0x31<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 設置場所<br>
		 * EPC                 : 0x81<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 設置場所を示す。<br>
		 * 2.2 設置場所プロパティ参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfInstallationLocation(){
			listProperty.add(new ElProp(EPC_INSTALLATION_LOCATION));
			return this;
		}
		/**
		 * 規格Version 情報<br>
		 * EPC                 : 0x82<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 対応するAPPENDIXのリリース番号を示す。<br>
		 * 1 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 2 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 3 ﾊﾞｲﾄ目：リリース順.をASCIIで示す。<br>
		 * 4 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfStandardVersionInfo(){
			listProperty.add(new ElProp(EPC_STANDARD_VERSION_INFO));
			return this;
		}
		/**
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk<br>
		 * 0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット<br>
		 * 0x91～0x9F：IEEE802.11/11b<br>
		 * 0xA1：電灯線c 方式<br>
		 * 0xB1：IPv6/Ethernet<br>
		 * 0xB2：IPv6/6LoWPAN<br>
		 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
		 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
		 * 0x00：識別番号未設定<br>
		 * <br>
		 * 2 バイト目以降：固有番号フィールド<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfIdentificationNum(){
			listProperty.add(new ElProp(EPC_IDENTIFICATION_NUM));
			return this;
		}
		/**
		 * 瞬時消費電力計測値<br>
		 * EPC                 : 0x84<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の瞬時消費電力をWで示す。<br>
		 * 0x0000～0xFFFD（0～65533W）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfInstantElectricPowerConsumption(){
			listProperty.add(new ElProp(EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION));
			return this;
		}
		/**
		 * 積算消費電力計測値<br>
		 * EPC                 : 0x85<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の積算消費電力を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF（0～999,999.999kWh）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfIntegrationElectricPowerConsumption(){
			listProperty.add(new ElProp(EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION));
			return this;
		}
		/**
		 * メーカ異常コード<br>
		 * EPC                 : 0x86<br>
		 * データタイプ        : unsigned char×(MAX)225<br>
		 * サイズ              : Max 225 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>各メーカ独自の異常コードを示す。<br>
		 * 1 ﾊﾞｲﾄ目：異常コード部のデータサイズを示す。<br>
		 * 	2～4 ﾊﾞｲﾄ目：メーカコード<br>
		 * 5 ﾊﾞｲﾄ目以降：各メーカ独自の異常コード部<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfMakerErrorCode(){
			listProperty.add(new ElProp(EPC_MAKER_ERROR_CODE));
			return this;
		}
		/**
		 * 電流制限設定<br>
		 * EPC                 : 0x87<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流制限の設定値を示す(0～100%)。<br>
		 * 0x00～0x64 （=0～100%）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfCurrentLimitSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_LIMIT_SETTING));
			return this;
		}
		/**
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 何らかの異常（センサトラブル等）の発生状況を示す。<br>
		 * 異常発生有＝0x41，異常発生無＝0x42<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfAbnormalState(){
			listProperty.add(new ElProp(EPC_ABNORMAL_STATE));
			return this;
		}
		/**
		 * 異常内容<br>
		 * EPC                 : 0x89<br>
		 * データタイプ        : unsigned　short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 異常内容<br>
		 * 2.5異常内容プロパティ参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfAbnormalContent(){
			listProperty.add(new ElProp(EPC_ABNORMAL_CONTENT));
			return this;
		}
		/**
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトで指定<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfMakerCode(){
			listProperty.add(new ElProp(EPC_MAKER_CODE));
			return this;
		}
		/**
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfPlantCode(){
			listProperty.add(new ElProp(EPC_PLANT_CODE));
			return this;
		}
		/**
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfProductCode(){
			listProperty.add(new ElProp(EPC_PRODUCT_CODE));
			return this;
		}
		/**
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfSerialNumber(){
			listProperty.add(new ElProp(EPC_SERIAL_NUMBER));
			return this;
		}
		/**
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfMadeYmd(){
			listProperty.add(new ElProp(EPC_MADE_YMD));
			return this;
		}
		/**
		 * 節電動作設定<br>
		 * EPC                 : 0x8F<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の節電動作状態を示す。<br>
		 * 節電動作中=0x41<br>
		 * 通常動作中=0x42<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfPowerSavingOperationSetting(){
			listProperty.add(new ElProp(EPC_POWER_SAVING_OPERATION_SETTING));
			return this;
		}
		/**
		 * 遠隔操作設定<br>
		 * EPC                 : 0x93<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
		 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
		 * 公衆回線未経由操作＝0x41<br>
		 * 公衆回線経由操作＝0x42<br>
		 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
		 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
		 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfRemoteOperationSetting(){
			listProperty.add(new ElProp(EPC_REMOTE_OPERATION_SETTING));
			return this;
		}
		/**
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B<br>
		 * （＝0～23）：（＝0～59）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfCurrentTimeSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_TIME_SETTING));
			return this;
		}
		/**
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfCurrentYmdSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING));
			return this;
		}
		/**
		 * 電力制限設定<br>
		 * EPC                 : 0x99<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力制限の設定値をW で示す。<br>
		 * 0x0000~0xFFFF（0～65535W）<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfPowerLimitSetting(){
			listProperty.add(new ElProp(EPC_POWER_LIMIT_SETTING));
			return this;
		}
		/**
		 * 積算運転時間<br>
		 * EPC                 : 0x9A<br>
		 * データタイプ        : unsigned char＋unsigned long<br>
		 * サイズ              : 1+4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在までの運転時間の積算値を単位1 バイト、時間4 バイトで示す。<br>
		 * 1 バイト目：単位を示す<br>
		 * 秒：0x41、分：0x42<br>
		 * 時：0x43、日:0x44<br>
		 * 2～5 バイト目：1 バイト目に示される時間単位における経過時間を示す<br>
		 * 0x00000000～0xFFFFFFFF<br>
		 * (0～4294967295)<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfIntegratedOperatingTime(){
			listProperty.add(new ElProp(EPC_INTEGRATED_OPERATING_TIME));
			return this;
		}
		/**
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfConvAnnouncePropMap(){
			listProperty.add(new ElProp(EPC_CONV_ANNOUNCE_PROP_MAP));
			return this;
		}
		/**
		 * Set プロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfSetPropMap(){
			listProperty.add(new ElProp(EPC_SET_PROP_MAP));
			return this;
		}
		/**
		 * Get プロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
		 */
		public DeviceObjectSuperClass.ElInformProps reqInfGetPropMap(){
			listProperty.add(new ElProp(EPC_GET_PROP_MAP));
			return this;
		}

	}

	/**
	 * SetIサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElSetProps setI(){
		return  setI(getLocalNodeProfile(getElProcess()));
	}


	/**
	 * SetIサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElSetProps setI(ElClassBase seoj){
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETI);
	}

	/**
	 * SetCサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElSetProps setC(){
		return  setC(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * SetCサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return DeviceObjectSuperClass.ElSetPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElSetProps setC(ElClassBase seoj){
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETC);
	}

	/**
	 * Getサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElGetProps get(){
		return get(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * Getサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return DeviceObjectSuperClass.ElGetPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElGetProps get(ElClassBase seoj){
		return new ElGetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_GET);

	}

	/**
	 * INF_REQサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElInformProps infReq(){
		return this.infReq(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * INF_REQサービスの処理を指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElInformProps infReq(ElClassBase seoj){
		return new ElInformProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_INF_REQ);
	}

	/**
	 * INFサービスの処理を指定（マルチキャスト）<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF<br>
	 * DEOJ : NodeProfileオブジェクト（一般ノード）<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElInformProps inf(){
		return inf(new NodeProfileClass());// INSTANCE_CODE_GENERAL
	}

	/**
	 * INFサービスの処理を指定<br>
	 * リモートIP : DEOJに紐づくIPアドレスまたは、マルチキャストアドレス<br>
	 * ただし、DEOJに紐づくIPアドレスが取得できない場合はマルチキャストアドレスとなる<br>
	 * ESV : INF<br>
	 * DEOJ : ＜パラメータ指定＞<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @param deoj 宛先EOJ
	 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElInformProps inf(ElClassBase deoj){

		boolean isWithMuticalst = false;
		// deojで所属するノードのIPアドレスが取得できない場合は、無条件にマルチキャストとする。
		if(deoj.getNodeBelongsTo() == null
				|| deoj.getNodeBelongsTo().getIpAddress() == null
				|| deoj.getNodeBelongsTo().getIpAddress().isEmpty()) {
			isWithMuticalst = true;
		}

		// 宛先IP
		String sIp = getElProcess().getChannel().getMultiCastAddress();
		ElClassBase objDeoj = deoj;
		if( isWithMuticalst) {
			// DEOJのエンティティコード部分を00に変更する。
			// 下記により、管理下のDEOJオブジェクトを変更せずに新規でEOJを生成する
			// objDeoj = ElClassBase.instanceEojByCode(deoj.getStrClassGroupCode(), deoj.getStrClassCode(), ElClassBase.INSTANCE_CODE_ALL);
		} else {
			sIp = deoj.getNodeBelongsTo().getIpAddress();
		}
		return new ElInformProps(getElProcess(), sIp, this, objDeoj, ElFrame.ESV_INF);
	}

	/**
	 * INFCサービスの処理を指定<br>
	 * INFCは規格上個別通知のみとなる。<br>
	 * リモートIP : ＜パラメータ指定＞ <br>
	 * ESV : INFC<br>
	 * DEOJ : NodeProfileオブジェクト（一般ノード）<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @param remoteIpAddress 宛先のIPアドレス
	 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElInformProps infC(String remoteIpAddress){
		return infC(remoteIpAddress, new NodeProfileClass());// INSTANCE_CODE_GENERAL
	}

	/**
	 * INFCサービスの処理を指定<br>
	 * INFCは規格上個別通知のみとなる。<br>
	 * リモートIP : ＜パラメータ指定＞ <br>
	 * ESV : INFC<br>
	 * DEOJ : ＜パラメータ指定＞<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @param remoteIpAddress 宛先のIPアドレス
	 * @param deoj 宛先EOJ
	 * @return DeviceObjectSuperClass.ElInformPropsオブジェクト
	 */
	public DeviceObjectSuperClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
		return new ElInformProps(getElProcess(), remoteIpAddress, this, deoj, ElFrame.ESV_INFC);
	}


	/**
	 * 応答、通知系フレームの受信時の処理用クラス
	 */
	public static class ReportProcessor extends ElClassBase.ReportProcessor{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "DeviceObjectSuperClass.ReportProcessor";

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfSet(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfSet( seoj,  strTid,  strEsv,  objProp,  isSuccess);
			switch(objProp.getStrEpc()) {
			case EPC_OPERATION_STATUS:
				onSetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_INSTALLATION_LOCATION:
				onSetInstallationLocation(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_CURRENT_LIMIT_SETTING:
				onSetCurrentLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_POWER_SAVING_OPERATION_SETTING:
				onSetPowerSavingOperationSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_REMOTE_OPERATION_SETTING:
				onSetRemoteOperationSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_CURRENT_TIME_SETTING:
				onSetCurrentTimeSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_CURRENT_YMD_SETTING:
				onSetCurrentYmdSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_POWER_LIMIT_SETTING:
				onSetPowerLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			}
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfGetOrInform(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfGetOrInform( seoj,  strTid,  strEsv,  objProp,  isSuccess);
			switch(objProp.getStrEpc()) {
			case EPC_OPERATION_STATUS:
				onGetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_INSTALLATION_LOCATION:
				onGetInstallationLocation(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_STANDARD_VERSION_INFO:
				onGetStandardVersionInfo(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_IDENTIFICATION_NUM:
				onGetIdentificationNum(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION:
				onGetInstantElectricPowerConsumption(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION:
				onGetIntegrationElectricPowerConsumption(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_MAKER_ERROR_CODE:
				onGetMakerErrorCode(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_CURRENT_LIMIT_SETTING:
				onGetCurrentLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_ABNORMAL_STATE:
				onGetAbnormalState(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_ABNORMAL_CONTENT:
				onGetAbnormalContent(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_MAKER_CODE:
				onGetMakerCode(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_PLANT_CODE:
				onGetPlantCode(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_PRODUCT_CODE:
				onGetProductCode(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_SERIAL_NUMBER:
				onGetSerialNumber(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_MADE_YMD:
				onGetMadeYmd(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_POWER_SAVING_OPERATION_SETTING:
				onGetPowerSavingOperationSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_REMOTE_OPERATION_SETTING:
				onGetRemoteOperationSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_CURRENT_TIME_SETTING:
				onGetCurrentTimeSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_CURRENT_YMD_SETTING:
				onGetCurrentYmdSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_POWER_LIMIT_SETTING:
				onGetPowerLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_INTEGRATED_OPERATING_TIME:
				onGetIntegratedOperatingTime(seoj, strTid, strEsv, objProp, isSuccess);
				return;
//			case EPC_SET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				onGetSetMPropMap(seoj, strTid, strEsv, objProp, isSuccess);
//				return;
//			case EPC_GET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				onGetGetMPropMap(seoj, strTid, strEsv, objProp, isSuccess);
//				return;
			case EPC_CONV_ANNOUNCE_PROP_MAP:
				onGetConvAnnouncePropMap(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_SET_PROP_MAP:
				onGetSetPropMap(seoj, strTid, strEsv, objProp, isSuccess);
				return;
			case EPC_GET_PROP_MAP:
				onGetGetPropMap(seoj, strTid, strEsv, objProp, isSuccess);
				return;
				default:
					return;
			}
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfInfC(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
			super.onReceivePropertyReportOfInfC( seoj,  strTid,  strEsv,  objProp);
			switch(objProp.getStrEpc()) {
			case EPC_OPERATION_STATUS:
				onInformOperationStatus(seoj, strTid, strEsv, objProp);
				return;
			case EPC_INSTALLATION_LOCATION:
				onInformInstallationLocation(seoj, strTid, strEsv, objProp);
				return;
			case EPC_STANDARD_VERSION_INFO:
				onInformStandardVersionInfo(seoj, strTid, strEsv, objProp);
				return;
			case EPC_IDENTIFICATION_NUM:
				onInformIdentificationNum(seoj, strTid, strEsv, objProp);
				return;
			case EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION:
				onInformInstantElectricPowerConsumption(seoj, strTid, strEsv, objProp);
				return;
			case EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION:
				onInformIntegrationElectricPowerConsumption(seoj, strTid, strEsv, objProp);
				return;
			case EPC_MAKER_ERROR_CODE:
				onInformMakerErrorCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_CURRENT_LIMIT_SETTING:
				onInformCurrentLimitSetting(seoj, strTid, strEsv, objProp);
				return;
			case EPC_ABNORMAL_STATE:
				onInformAbnormalState(seoj, strTid, strEsv, objProp);
				return;
			case EPC_ABNORMAL_CONTENT:
				onInformAbnormalContent(seoj, strTid, strEsv, objProp);
				return;
			case EPC_MAKER_CODE:
				onInformMakerCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_PLANT_CODE:
				onInformPlantCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_PRODUCT_CODE:
				onInformProductCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_SERIAL_NUMBER:
				onInformSerialNumber(seoj, strTid, strEsv, objProp);
				return;
			case EPC_MADE_YMD:
				onInformMadeYmd(seoj, strTid, strEsv, objProp);
				return;
			case EPC_POWER_SAVING_OPERATION_SETTING:
				onInformPowerSavingOperationSetting(seoj, strTid, strEsv, objProp);
				return;
			case EPC_REMOTE_OPERATION_SETTING:
				onInformRemoteOperationSetting(seoj, strTid, strEsv, objProp);
				return;
			case EPC_CURRENT_TIME_SETTING:
				onInformCurrentTimeSetting(seoj, strTid, strEsv, objProp);
				return;
			case EPC_CURRENT_YMD_SETTING:
				onInformCurrentYmdSetting(seoj, strTid, strEsv, objProp);
				return;
			case EPC_POWER_LIMIT_SETTING:
				onInformPowerLimitSetting(seoj, strTid, strEsv, objProp);
				return;
			case EPC_INTEGRATED_OPERATING_TIME:
				onInformIntegratedOperatingTime(seoj, strTid, strEsv, objProp);
				return;
//			case EPC_SET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				onInformSetMPropMap(seoj, strTid, strEsv, objProp);
//				return;
//			case EPC_GET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				onInformGetMPropMap(seoj, strTid, strEsv, objProp);
//				return;
			case EPC_CONV_ANNOUNCE_PROP_MAP:
				onInformConvAnnouncePropMap(seoj, strTid, strEsv, objProp);
				return;
			case EPC_SET_PROP_MAP:
				onInformSetPropMap(seoj, strTid, strEsv, objProp);
				return;
			case EPC_GET_PROP_MAP:
				onInformGetPropMap(seoj, strTid, strEsv, objProp);
				return;

			default:
				return;
			}
		}

		/**
		 * Set関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ON＝0x30，OFF＝0x31<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 設置場所<br>
		 * EPC                 : 0x81<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 設置場所を示す。<br>
		 * 2.2 設置場所プロパティ参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetInstallationLocation(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 電流制限設定<br>
		 * EPC                 : 0x87<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流制限の設定値を示す(0～100%)。<br>
		 * 0x00～0x64 （=0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetCurrentLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 節電動作設定<br>
		 * EPC                 : 0x8F<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の節電動作状態を示す。<br>
		 * 節電動作中=0x41<br>
		 * 通常動作中=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetPowerSavingOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 遠隔操作設定<br>
		 * EPC                 : 0x93<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
		 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
		 * 公衆回線未経由操作＝0x41<br>
		 * 公衆回線経由操作＝0x42<br>
		 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
		 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
		 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetRemoteOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B<br>
		 * （＝0～23）：（＝0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetCurrentTimeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetCurrentYmdSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 電力制限設定<br>
		 * EPC                 : 0x99<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力制限の設定値をW で示す。<br>
		 * 0x0000~0xFFFF（0～65535W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetPowerLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

		/**
		 * Get,Inf関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ON＝0x30，OFF＝0x31<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 設置場所<br>
		 * EPC                 : 0x81<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 設置場所を示す。<br>
		 * 2.2 設置場所プロパティ参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetInstallationLocation(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 規格Version 情報<br>
		 * EPC                 : 0x82<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 対応するAPPENDIXのリリース番号を示す。<br>
		 * 1 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 2 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 3 ﾊﾞｲﾄ目：リリース順.をASCIIで示す。<br>
		 * 4 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetStandardVersionInfo(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk<br>
		 * 0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット<br>
		 * 0x91～0x9F：IEEE802.11/11b<br>
		 * 0xA1：電灯線c 方式<br>
		 * 0xB1：IPv6/Ethernet<br>
		 * 0xB2：IPv6/6LoWPAN<br>
		 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
		 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
		 * 0x00：識別番号未設定<br>
		 * <br>
		 * 2 バイト目以降：固有番号フィールド<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIdentificationNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 瞬時消費電力計測値<br>
		 * EPC                 : 0x84<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の瞬時消費電力をWで示す。<br>
		 * 0x0000～0xFFFD（0～65533W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetInstantElectricPowerConsumption(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算消費電力計測値<br>
		 * EPC                 : 0x85<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の積算消費電力を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF（0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegrationElectricPowerConsumption(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * メーカ異常コード<br>
		 * EPC                 : 0x86<br>
		 * データタイプ        : unsigned char×(MAX)225<br>
		 * サイズ              : Max 225 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>各メーカ独自の異常コードを示す。<br>
		 * 1 ﾊﾞｲﾄ目：異常コード部のデータサイズを示す。<br>
		 * 	2～4 ﾊﾞｲﾄ目：メーカコード<br>
		 * 5 ﾊﾞｲﾄ目以降：各メーカ独自の異常コード部<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMakerErrorCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 電流制限設定<br>
		 * EPC                 : 0x87<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流制限の設定値を示す(0～100%)。<br>
		 * 0x00～0x64 （=0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetCurrentLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 何らかの異常（センサトラブル等）の発生状況を示す。<br>
		 * 異常発生有＝0x41，異常発生無＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAbnormalState(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 異常内容<br>
		 * EPC                 : 0x89<br>
		 * データタイプ        : unsigned　short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 異常内容<br>
		 * 2.5異常内容プロパティ参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAbnormalContent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトで指定<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMakerCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetPlantCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetProductCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSerialNumber(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMadeYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 節電動作設定<br>
		 * EPC                 : 0x8F<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の節電動作状態を示す。<br>
		 * 節電動作中=0x41<br>
		 * 通常動作中=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetPowerSavingOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 遠隔操作設定<br>
		 * EPC                 : 0x93<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
		 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
		 * 公衆回線未経由操作＝0x41<br>
		 * 公衆回線経由操作＝0x42<br>
		 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
		 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
		 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRemoteOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B<br>
		 * （＝0～23）：（＝0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetCurrentTimeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetCurrentYmdSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 電力制限設定<br>
		 * EPC                 : 0x99<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力制限の設定値をW で示す。<br>
		 * 0x0000~0xFFFF（0～65535W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetPowerLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算運転時間<br>
		 * EPC                 : 0x9A<br>
		 * データタイプ        : unsigned char＋unsigned long<br>
		 * サイズ              : 1+4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在までの運転時間の積算値を単位1 バイト、時間4 バイトで示す。<br>
		 * 1 バイト目：単位を示す<br>
		 * 秒：0x41、分：0x42<br>
		 * 時：0x43、日:0x44<br>
		 * 2～5 バイト目：1 バイト目に示される時間単位における経過時間を示す<br>
		 * 0x00000000～0xFFFFFFFF<br>
		 * (0～4294967295)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegratedOperatingTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetConvAnnouncePropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			Set<String> map = seoj.getMapConvAnnounceProps();
			map.clear();
			String[] properties = ElUtil.convHexStrToEachPropValues(objProp.getStrEdt());
			for (String p : properties) {
				map.add(p);
			}
		};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * Set プロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			Set<String> map = seoj.getMapSetProps();
			map.clear();
			String[] properties = ElUtil.convHexStrToEachPropValues(objProp.getStrEdt());
			for (String p : properties) {
				map.add(p);
			}
		};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * Get プロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetGetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			Set<String> map = seoj.getMapGetProps();
			map.clear();
			String[] properties = ElUtil.convHexStrToEachPropValues(objProp.getStrEdt());
			for (String p : properties) {
				map.add(p);
			}
		};

		/**
		 * InfC関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ON＝0x30，OFF＝0x31<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 設置場所<br>
		 * EPC                 : 0x81<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 設置場所を示す。<br>
		 * 2.2 設置場所プロパティ参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformInstallationLocation(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 規格Version 情報<br>
		 * EPC                 : 0x82<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 対応するAPPENDIXのリリース番号を示す。<br>
		 * 1 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 2 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * 3 ﾊﾞｲﾄ目：リリース順.をASCIIで示す。<br>
		 * 4 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformStandardVersionInfo(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk<br>
		 * 0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット<br>
		 * 0x91～0x9F：IEEE802.11/11b<br>
		 * 0xA1：電灯線c 方式<br>
		 * 0xB1：IPv6/Ethernet<br>
		 * 0xB2：IPv6/6LoWPAN<br>
		 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
		 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
		 * 0x00：識別番号未設定<br>
		 * <br>
		 * 2 バイト目以降：固有番号フィールド<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIdentificationNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 瞬時消費電力計測値<br>
		 * EPC                 : 0x84<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の瞬時消費電力をWで示す。<br>
		 * 0x0000～0xFFFD（0～65533W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformInstantElectricPowerConsumption(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算消費電力計測値<br>
		 * EPC                 : 0x85<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の積算消費電力を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF（0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegrationElectricPowerConsumption(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * メーカ異常コード<br>
		 * EPC                 : 0x86<br>
		 * データタイプ        : unsigned char×(MAX)225<br>
		 * サイズ              : Max 225 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>各メーカ独自の異常コードを示す。<br>
		 * 1 ﾊﾞｲﾄ目：異常コード部のデータサイズを示す。<br>
		 * 	2～4 ﾊﾞｲﾄ目：メーカコード<br>
		 * 5 ﾊﾞｲﾄ目以降：各メーカ独自の異常コード部<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMakerErrorCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 電流制限設定<br>
		 * EPC                 : 0x87<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流制限の設定値を示す(0～100%)。<br>
		 * 0x00～0x64 （=0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCurrentLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 何らかの異常（センサトラブル等）の発生状況を示す。<br>
		 * 異常発生有＝0x41，異常発生無＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAbnormalState(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 異常内容<br>
		 * EPC                 : 0x89<br>
		 * データタイプ        : unsigned　short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 異常内容<br>
		 * 2.5異常内容プロパティ参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAbnormalContent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトで指定<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMakerCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 3 バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformPlantCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformProductCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSerialNumber(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMadeYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 節電動作設定<br>
		 * EPC                 : 0x8F<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器の節電動作状態を示す。<br>
		 * 節電動作中=0x41<br>
		 * 通常動作中=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformPowerSavingOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 遠隔操作設定<br>
		 * EPC                 : 0x93<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
		 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
		 * 公衆回線未経由操作＝0x41<br>
		 * 公衆回線経由操作＝0x42<br>
		 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
		 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
		 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRemoteOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B<br>
		 * （＝0～23）：（＝0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCurrentTimeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCurrentYmdSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 電力制限設定<br>
		 * EPC                 : 0x99<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力制限の設定値をW で示す。<br>
		 * 0x0000~0xFFFF（0～65535W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformPowerLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算運転時間<br>
		 * EPC                 : 0x9A<br>
		 * データタイプ        : unsigned char＋unsigned long<br>
		 * サイズ              : 1+4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在までの運転時間の積算値を単位1 バイト、時間4 バイトで示す。<br>
		 * 1 バイト目：単位を示す<br>
		 * 秒：0x41、分：0x42<br>
		 * 時：0x43、日:0x44<br>
		 * 2～5 バイト目：1 バイト目に示される時間単位における経過時間を示す<br>
		 * 0x00000000～0xFFFFFFFF<br>
		 * (0～4294967295)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegratedOperatingTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformConvAnnouncePropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * Set プロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * Get プロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max.17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 付録1．参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformGetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized String getEdtValueFromApp(String epc) {
		String retEdt =super.getEdtValueFromApp(epc);
		if(retEdt != null) {
			return retEdt;
		}
		switch(epc) {
			case EPC_OPERATION_STATUS:
				return this.getFromAppOperationStatus();
			case EPC_INSTALLATION_LOCATION:
				return this.getFromAppInstallationLocation();
			case EPC_STANDARD_VERSION_INFO:
				return this.getFromAppStandardVersionInfo();
			case EPC_IDENTIFICATION_NUM:
				return this.getFromAppIdentificationNum();
			case EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION:
				return this.getFromAppInstantElectricPowerConsumption();
			case EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION:
				return this.getFromAppIntegrationElectricPowerConsumption();
			case EPC_MAKER_ERROR_CODE:
				return this.getFromAppMakerErrorCode();
			case EPC_CURRENT_LIMIT_SETTING:
				return this.getFromAppCurrentLimitSetting();
			case EPC_ABNORMAL_STATE:
				return this.getFromAppAbnormalState();
			case EPC_ABNORMAL_CONTENT:
				return this.getFromAppAbnormalContent();
			case EPC_MAKER_CODE:
				return this.getFromAppMakerCode();
			case EPC_PLANT_CODE:
				return this.getFromAppPlantCode();
			case EPC_PRODUCT_CODE:
				return this.getFromAppProductCode();
			case EPC_SERIAL_NUMBER:
				return this.getFromAppSerialNumber();
			case EPC_MADE_YMD:
				return this.getFromAppMadeYmd();
			case EPC_POWER_SAVING_OPERATION_SETTING:
				return this.getFromAppPowerSavingOperationSetting();
			case EPC_REMOTE_OPERATION_SETTING:
				return this.getFromAppRemoteOperationSetting();
			case EPC_CURRENT_TIME_SETTING:
				return this.getFromAppCurrentTimeSetting();
			case EPC_CURRENT_YMD_SETTING:
				return this.getFromAppCurrentYmdSetting();
			case EPC_POWER_LIMIT_SETTING:
				return this.getFromAppPowerLimitSetting();
			case EPC_INTEGRATED_OPERATING_TIME:
				return this.getFromAppIntegratedOperatingTime();
//			case EPC_SET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				return this.getFromAppSetMPropMap();
//			case EPC_GET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				return this.getFromAppGetMPropMap();
			case EPC_CONV_ANNOUNCE_PROP_MAP:
				return this.getFromAppConvAnnouncePropMap();
			case EPC_SET_PROP_MAP:
				return this.getFromAppSetPropMap();
			case EPC_GET_PROP_MAP:
				return this.getFromAppGetPropMap();
			default:
				return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized boolean isValidEdtValue(ElProp prop) {
		boolean bResult =super.isValidEdtValue(prop);
		if(bResult == true) {
			return true;
		}
		String epc = prop.getStrEpc();
		String edt = prop.getStrEdt();
		switch (epc) {
			case EPC_OPERATION_STATUS:
				return this.isValidEdtOperationStatus(edt);
			case EPC_INSTALLATION_LOCATION:
				return this.isValidEdtInstallationLocation(edt);
			case EPC_STANDARD_VERSION_INFO:
				return this.isValidEdtStandardVersionInfo(edt);
			case EPC_IDENTIFICATION_NUM:
				return this.isValidEdtIdentificationNum(edt);
			case EPC_INSTANT_ELECTRIC_POWER_CONSUMPTION:
				return this.isValidEdtInstantElectricPowerConsumption(edt);
			case EPC_INTEGRATION_ELECTRIC_POWER_CONSUMPTION:
				return this.isValidEdtIntegrationElectricPowerConsumption(edt);
			case EPC_MAKER_ERROR_CODE:
				return this.isValidEdtMakerErrorCode(edt);
			case EPC_CURRENT_LIMIT_SETTING:
				return this.isValidEdtCurrentLimitSetting(edt);
			case EPC_ABNORMAL_STATE:
				return this.isValidEdtAbnormalState(edt);
			case EPC_ABNORMAL_CONTENT:
				return this.isValidEdtAbnormalContent(edt);
			case EPC_MAKER_CODE:
				return this.isValidEdtMakerCode(edt);
			case EPC_PLANT_CODE:
				return this.isValidEdtPlantCode(edt);
			case EPC_PRODUCT_CODE:
				return this.isValidEdtProductCode(edt);
			case EPC_SERIAL_NUMBER:
				return this.isValidEdtSerialNumber(edt);
			case EPC_MADE_YMD:
				return this.isValidEdtMadeYmd(edt);
			case EPC_POWER_SAVING_OPERATION_SETTING:
				return this.isValidEdtPowerSavingOperationSetting(edt);
			case EPC_REMOTE_OPERATION_SETTING:
				return this.isValidEdtRemoteOperationSetting(edt);
			case EPC_CURRENT_TIME_SETTING:
				return this.isValidEdtCurrentTimeSetting(edt);
			case EPC_CURRENT_YMD_SETTING:
				return this.isValidEdtCurrentYmdSetting(edt);
			case EPC_POWER_LIMIT_SETTING:
				return this.isValidEdtPowerLimitSetting(edt);
			case EPC_INTEGRATED_OPERATING_TIME:
				return this.isValidEdtIntegratedOperatingTime(edt);
//			case EPC_SET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				return this.isValidEdtSetMPropMap(edt);
//			case EPC_GET_M_PROP_MAP: // ※ECHONET Lite 機器は搭載不可
//				return this.isValidEdtGetMPropMap(edt);
			case EPC_CONV_ANNOUNCE_PROP_MAP:
				return this.isValidEdtConvAnnouncePropMap(edt);
			case EPC_SET_PROP_MAP:
				return this.isValidEdtSetPropMap(edt);
			case EPC_GET_PROP_MAP:
				return this.isValidEdtGetPropMap(edt);
			default:
				return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized boolean setEdtValueToApp(ElProp prop) {
		boolean bResult =super.setEdtValueToApp(prop);
		if(bResult == true) {
			return true;
		}
		String epc = prop.getStrEpc();
		String edt = prop.getStrEdt();
		switch (epc) {
			case EPC_OPERATION_STATUS:
				return this.setToAppOperationStatus(edt);
			case EPC_INSTALLATION_LOCATION:
				return this.setToAppInstallationLocation(edt);
			case EPC_CURRENT_LIMIT_SETTING:
				return this.setToAppCurrentLimitSetting(edt);
			case EPC_POWER_SAVING_OPERATION_SETTING:
				return this.setToAppPowerSavingOperationSetting(edt);
			case EPC_REMOTE_OPERATION_SETTING:
				return this.setToAppRemoteOperationSetting(edt);
			case EPC_CURRENT_TIME_SETTING:
				return this.setToAppCurrentTimeSetting(edt);
			case EPC_CURRENT_YMD_SETTING:
				return this.setToAppCurrentYmdSetting(edt);
			case EPC_POWER_LIMIT_SETTING:
				return this.setToAppPowerLimitSetting(edt);
			default:
				return false;
		}
	}

	 // アプリケーションより値を取得するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ON＝0x30，OFF＝0x31<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOperationStatus(){return null;}
	/**
	 * 設置場所<br>
	 * EPC                 : 0x81<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 or 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 設置場所を示す。<br>
	 * 2.2 設置場所プロパティ参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppInstallationLocation(){return null;}
	/**
	 * 規格Version 情報<br>
	 * EPC                 : 0x82<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 対応するAPPENDIXのリリース番号を示す。<br>
	 * 1 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
	 * 2 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
	 * 3 ﾊﾞｲﾄ目：リリース順.をASCIIで示す。<br>
	 * 4 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppStandardVersionInfo(){
		return EDT_STANDARD_VERSION_INFORMATION;
	}
	/**
	 * 識別番号<br>
	 * EPC                 : 0x83<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 9 or 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * オブジェクトを固有に識別する番号。<br>
	 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
	 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONET Lite では使用しない）<br>
	 * 0x11～0x1F：電灯線a,d 方式<br>
	 * 0x31～0x3F：特定小電力無線<br>
	 * 0x41～0x4F：拡張HBS<br>
	 * 0x51～0x5F：IrDA<br>
	 * 0x61～0x6F：LonTalk<br>
	 * 0x71～0x7F：Bluetooth<br>
	 * 0x81～0x8F：イーサネット<br>
	 * 0x91～0x9F：IEEE802.11/11b<br>
	 * 0xA1：電灯線c 方式<br>
	 * 0xB1：IPv6/Ethernet<br>
	 * 0xB2：IPv6/6LoWPAN<br>
	 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
	 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
	 * 0x00：識別番号未設定<br>
	 * <br>
	 * 2 バイト目以降：固有番号フィールド<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIdentificationNum(){return null;}
	/**
	 * 瞬時消費電力計測値<br>
	 * EPC                 : 0x84<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器の瞬時消費電力をWで示す。<br>
	 * 0x0000～0xFFFD（0～65533W）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppInstantElectricPowerConsumption(){return null;}
	/**
	 * 積算消費電力計測値<br>
	 * EPC                 : 0x85<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器の積算消費電力を0.001kWh で示す。<br>
	 * 0x00000000～0x3B9AC9FF（0～999,999.999kWh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegrationElectricPowerConsumption(){return null;}
	/**
	 * メーカ異常コード<br>
	 * EPC                 : 0x86<br>
	 * データタイプ        : unsigned char×(MAX)225<br>
	 * サイズ              : Max 225 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>各メーカ独自の異常コードを示す。<br>
	 * 1 ﾊﾞｲﾄ目：異常コード部のデータサイズを示す。<br>
	 * 	2～4 ﾊﾞｲﾄ目：メーカコード<br>
	 * 5 ﾊﾞｲﾄ目以降：各メーカ独自の異常コード部<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMakerErrorCode(){return null;}
	/**
	 * 電流制限設定<br>
	 * EPC                 : 0x87<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流制限の設定値を示す(0～100%)。<br>
	 * 0x00～0x64 （=0～100%）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppCurrentLimitSetting(){return null;}
	/**
	 * 異常発生状態<br>
	 * EPC                 : 0x88<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 何らかの異常（センサトラブル等）の発生状況を示す。<br>
	 * 異常発生有＝0x41，異常発生無＝0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAbnormalState(){return null;}
	/**
	 * 異常内容<br>
	 * EPC                 : 0x89<br>
	 * データタイプ        : unsigned　short<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 異常内容<br>
	 * 2.5異常内容プロパティ参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAbnormalContent(){return null;}
	/**
	 * メーカコード<br>
	 * EPC                 : 0x8A<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 3 バイトで指定<br>
	 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMakerCode(){return null;}
	/**
	 * 事業場コード<br>
	 * EPC                 : 0x8B<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 3 バイトの事業場コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppPlantCode(){return null;}
	/**
	 * 商品コード<br>
	 * EPC                 : 0x8C<br>
	 * データタイプ        : unsigned char×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ASCII コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppProductCode(){return null;}
	/**
	 * 製造番号<br>
	 * EPC                 : 0x8D<br>
	 * データタイプ        : unsigned char×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ASCII コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSerialNumber(){return null;}
	/**
	 * 製造年月日<br>
	 * EPC                 : 0x8E<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 4 バイトで指定。<br>
	 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
	 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
	 * M：月(12 月の場合＝0x0C)<br>
	 * D：日(20 日の場合＝0x14)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMadeYmd(){return null;}
	/**
	 * 節電動作設定<br>
	 * EPC                 : 0x8F<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器の節電動作状態を示す。<br>
	 * 節電動作中=0x41<br>
	 * 通常動作中=0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppPowerSavingOperationSetting(){return null;}
	/**
	 * 遠隔操作設定<br>
	 * EPC                 : 0x93<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
	 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
	 * 公衆回線未経由操作＝0x41<br>
	 * 公衆回線経由操作＝0x42<br>
	 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
	 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
	 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRemoteOperationSetting(){return null;}
	/**
	 * 現在時刻設定<br>
	 * EPC                 : 0x97<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00～0x17：0x00～0x3B<br>
	 * （＝0～23）：（＝0～59）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppCurrentTimeSetting(){return null;}
	/**
	 * 現在年月日設定<br>
	 * EPC                 : 0x98<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日YYYY：MM：DD<br>
	 * 1～0x270F：1～0x0C：1～0x1F<br>
	 * (=1～9999)：(=1～12)：(=1～31)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppCurrentYmdSetting(){return null;}
	/**
	 * 電力制限設定<br>
	 * EPC                 : 0x99<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電力制限の設定値をW で示す。<br>
	 * 0x0000~0xFFFF（0～65535W）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppPowerLimitSetting(){return null;}
	/**
	 * 積算運転時間<br>
	 * EPC                 : 0x9A<br>
	 * データタイプ        : unsigned char＋unsigned long<br>
	 * サイズ              : 1+4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在までの運転時間の積算値を単位1 バイト、時間4 バイトで示す。<br>
	 * 1 バイト目：単位を示す<br>
	 * 秒：0x41、分：0x42<br>
	 * 時：0x43、日:0x44<br>
	 * 2～5 バイト目：1 バイト目に示される時間単位における経過時間を示す<br>
	 * 0x00000000～0xFFFFFFFF<br>
	 * (0～4294967295)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegratedOperatingTime(){return null;}
//	protected String getFromAppSetMPropMap(){return null;} // ※ECHONET Lite 機器は搭載不可
//	protected String getFromAppGetMPropMap(){return null;} // ※ECHONET Lite 機器は搭載不可
	/**
	 * 状変アナウンスプロパティマップ<br>
	 * EPC                 : 0x9D<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max.17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 付録1．参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppConvAnnouncePropMap(){
		Set<String> propMapSet = this.getMapConvAnnounceProps();
		return ElUtil.convPropMapSetToHexStr(propMapSet);
	}
	/**
	 * Set プロパティマップ<br>
	 * EPC                 : 0x9E<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max.17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 付録1．参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSetPropMap(){
		Set<String> propMapSet = this.getMapSetProps();
		return ElUtil.convPropMapSetToHexStr(propMapSet);
	}
	/**
	 * Get プロパティマップ<br>
	 * EPC                 : 0x9F<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max.17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 付録1．参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppGetPropMap(){
		Set<String> propMapSet = this.getMapGetProps();
		return ElUtil.convPropMapSetToHexStr(propMapSet);
	}

	// アプリケーションから取得した値の妥当性を検証するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ON＝0x30，OFF＝0x31<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOperationStatus(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_OPERATION_STATUS_ON) && ! edt.equalsIgnoreCase(EDT_OPERATION_STATUS_OFF)) return false;
		return true;
	}
	/**
	 * 設置場所<br>
	 * EPC                 : 0x81<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 or 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 設置場所を示す。<br>
	 * 2.2 設置場所プロパティ参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtInstallationLocation(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 規格Version 情報<br>
	 * EPC                 : 0x82<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 対応するAPPENDIXのリリース番号を示す。<br>
	 * 1 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
	 * 2 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
	 * 3 ﾊﾞｲﾄ目：リリース順.をASCIIで示す。<br>
	 * 4 ﾊﾞｲﾄ目：0x00 固定(for future reserved)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtStandardVersionInfo(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		return true;
	}
	/**
	 * 識別番号<br>
	 * EPC                 : 0x83<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 9 or 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * オブジェクトを固有に識別する番号。<br>
	 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
	 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONET Lite では使用しない）<br>
	 * 0x11～0x1F：電灯線a,d 方式<br>
	 * 0x31～0x3F：特定小電力無線<br>
	 * 0x41～0x4F：拡張HBS<br>
	 * 0x51～0x5F：IrDA<br>
	 * 0x61～0x6F：LonTalk<br>
	 * 0x71～0x7F：Bluetooth<br>
	 * 0x81～0x8F：イーサネット<br>
	 * 0x91～0x9F：IEEE802.11/11b<br>
	 * 0xA1：電灯線c 方式<br>
	 * 0xB1：IPv6/Ethernet<br>
	 * 0xB2：IPv6/6LoWPAN<br>
	 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
	 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
	 * 0x00：識別番号未設定<br>
	 * <br>
	 * 2 バイト目以降：固有番号フィールド<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIdentificationNum(String edt){
		if(edt == null || !(edt.length() == 9 * 2 || edt.length() == 17 * 2)) return false;
		return true;
	}
	/**
	 * 瞬時消費電力計測値<br>
	 * EPC                 : 0x84<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器の瞬時消費電力をWで示す。<br>
	 * 0x0000～0xFFFD（0～65533W）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtInstantElectricPowerConsumption(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 積算消費電力計測値<br>
	 * EPC                 : 0x85<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器の積算消費電力を0.001kWh で示す。<br>
	 * 0x00000000～0x3B9AC9FF（0～999,999.999kWh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegrationElectricPowerConsumption(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * メーカ異常コード<br>
	 * EPC                 : 0x86<br>
	 * データタイプ        : unsigned char×(MAX)225<br>
	 * サイズ              : Max 225 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>各メーカ独自の異常コードを示す。<br>
	 * 1 ﾊﾞｲﾄ目：異常コード部のデータサイズを示す。<br>
	 * 	2～4 ﾊﾞｲﾄ目：メーカコード<br>
	 * 5 ﾊﾞｲﾄ目以降：各メーカ独自の異常コード部<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMakerErrorCode(String edt){
		if(edt == null || !(edt.length() <= 225 * 2)) return false;
		return true;
	}
	/**
	 * 電流制限設定<br>
	 * EPC                 : 0x87<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流制限の設定値を示す(0～100%)。<br>
	 * 0x00～0x64 （=0～100%）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtCurrentLimitSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * 異常発生状態<br>
	 * EPC                 : 0x88<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 何らかの異常（センサトラブル等）の発生状況を示す。<br>
	 * 異常発生有＝0x41，異常発生無＝0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAbnormalState(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_ABNORMAL_STATE_TRUE) && ! edt.equalsIgnoreCase(EDT_ABNORMAL_STATE_FALSE)) return false;

		return true;
	}
	/**
	 * 異常内容<br>
	 * EPC                 : 0x89<br>
	 * データタイプ        : unsigned　short<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 異常内容<br>
	 * 2.5異常内容プロパティ参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAbnormalContent(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * メーカコード<br>
	 * EPC                 : 0x8A<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 3 バイトで指定<br>
	 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMakerCode(String edt){
		if(edt == null || !(edt.length() == 3 * 2)) return false;
		return true;
	}
	/**
	 * 事業場コード<br>
	 * EPC                 : 0x8B<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 3 バイトの事業場コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtPlantCode(String edt){
		if(edt == null || !(edt.length() == 3 * 2)) return false;
		return true;
	}
	/**
	 * 商品コード<br>
	 * EPC                 : 0x8C<br>
	 * データタイプ        : unsigned char×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ASCII コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtProductCode(String edt){
		if(edt == null || !(edt.length() == 12 * 2)) return false;
		return true;
	}
	/**
	 * 製造番号<br>
	 * EPC                 : 0x8D<br>
	 * データタイプ        : unsigned char×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ASCII コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSerialNumber(String edt){
		if(edt == null || !(edt.length() == 12 * 2)) return false;
		return true;
	}
	/**
	 * 製造年月日<br>
	 * EPC                 : 0x8E<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 4 バイトで指定。<br>
	 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
	 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
	 * M：月(12 月の場合＝0x0C)<br>
	 * D：日(20 日の場合＝0x14)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMadeYmd(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! ElUtil.checkEdtYmdFormat(edt)) return false;
		return true;
	}
	/**
	 * 節電動作設定<br>
	 * EPC                 : 0x8F<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器の節電動作状態を示す。<br>
	 * 節電動作中=0x41<br>
	 * 通常動作中=0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtPowerSavingOperationSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_POWER_SAVING_OPERATION_SETTING_SAVING) && ! edt.equalsIgnoreCase(EDT_POWER_SAVING_OPERATION_SETTING_NORMAL)) return false;
		return true;
	}
	/**
	 * 遠隔操作設定<br>
	 * EPC                 : 0x93<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
	 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
	 * 公衆回線未経由操作＝0x41<br>
	 * 公衆回線経由操作＝0x42<br>
	 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
	 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
	 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRemoteOperationSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_REMOTE_OPERATION_SETTING_NO_USE_PUBLIC_LINE)
				&& ! edt.equalsIgnoreCase(EDT_REMOTE_OPERATION_SETTING_USE_PUBLIC_LINE)
				&& ! edt.equalsIgnoreCase(EDT_REMOTE_OPERATION_SETTING_USE_PUBLIC_LINE_IMPOSIBLE)
				&& ! edt.equalsIgnoreCase(EDT_REMOTE_OPERATION_SETTING_USE_PUBLIC_LINE_POSIBLE)
				) return false;
		return true;
	}
	/**
	 * 現在時刻設定<br>
	 * EPC                 : 0x97<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00～0x17：0x00～0x3B<br>
	 * （＝0～23）：（＝0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtCurrentTimeSetting(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! ElUtil.checkEdtHmFormat(edt)) return false;
		return true;
	}
	/**
	 * 現在年月日設定<br>
	 * EPC                 : 0x98<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日YYYY：MM：DD<br>
	 * 1～0x270F：1～0x0C：1～0x1F<br>
	 * (=1～9999)：(=1～12)：(=1～31)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtCurrentYmdSetting(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! ElUtil.checkEdtYmdSetFormat(edt)) return false;
		return true;
	}
	/**
	 * 電力制限設定<br>
	 * EPC                 : 0x99<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電力制限の設定値をW で示す。<br>
	 * 0x0000~0xFFFF（0～65535W）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtPowerLimitSetting(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 積算運転時間<br>
	 * EPC                 : 0x9A<br>
	 * データタイプ        : unsigned char＋unsigned long<br>
	 * サイズ              : 1+4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在までの運転時間の積算値を単位1 バイト、時間4 バイトで示す。<br>
	 * 1 バイト目：単位を示す<br>
	 * 秒：0x41、分：0x42<br>
	 * 時：0x43、日:0x44<br>
	 * 2～5 バイト目：1 バイト目に示される時間単位における経過時間を示す<br>
	 * 0x00000000～0xFFFFFFFF<br>
	 * (0～4294967295)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegratedOperatingTime(String edt){
		if(edt == null || !(edt.length() == 5 * 2)) return false;
		return true;
	}
//	protected boolean isValidEdtSetMPropMap(String edt){return false;} // ※ECHONET Lite 機器は搭載不可
//	protected boolean isValidEdtGetMPropMap(String edt){return false;} // ※ECHONET Lite 機器は搭載不可
	/**
	 * 状変アナウンスプロパティマップ<br>
	 * EPC                 : 0x9D<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max.17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 付録1．参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtConvAnnouncePropMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}
	/**
	 * Set プロパティマップ<br>
	 * EPC                 : 0x9E<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max.17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 付録1．参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSetPropMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}
	/**
	 * Get プロパティマップ<br>
	 * EPC                 : 0x9F<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max.17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 付録1．参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtGetPropMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}

	// アプリケーションの設置する値の妥当性を検証するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ON＝0x30，OFF＝0x31<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOperationStatus(String edt){return false;}
	/**
	 * 設置場所<br>
	 * EPC                 : 0x81<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 or 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 設置場所を示す。<br>
	 * 2.2 設置場所プロパティ参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppInstallationLocation(String edt){return false;}
	/**
	 * 電流制限設定<br>
	 * EPC                 : 0x87<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流制限の設定値を示す(0～100%)。<br>
	 * 0x00～0x64 （=0～100%）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppCurrentLimitSetting(String edt){return false;}
	/**
	 * 節電動作設定<br>
	 * EPC                 : 0x8F<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器の節電動作状態を示す。<br>
	 * 節電動作中=0x41<br>
	 * 通常動作中=0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppPowerSavingOperationSetting(String edt){return false;}
	/**
	 * 遠隔操作設定<br>
	 * EPC                 : 0x93<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 公衆回線を介した操作か否かを示す。（0x41、0x42）<br>
	 * 通信回線の状態が正常か否かを示す。（0x61、0x62）<br>
	 * 公衆回線未経由操作＝0x41<br>
	 * 公衆回線経由操作＝0x42<br>
	 * 通信回線正常（公衆回線経由の操作不可）= 0x61<br>
	 * 通信回線正常（公衆回線経由の操作可能）= 0x62<br>
	 * ※なお、上記（0x61、0x62）の値はSet 要求受信時に機器はその値を上書きしてはならない。<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppRemoteOperationSetting(String edt){return false;}
	/**
	 * 現在時刻設定<br>
	 * EPC                 : 0x97<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00～0x17：0x00～0x3B<br>
	 * （＝0～23）：（＝0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppCurrentTimeSetting(String edt){return false;}
	/**
	 * 現在年月日設定<br>
	 * EPC                 : 0x98<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日YYYY：MM：DD<br>
	 * 1～0x270F：1～0x0C：1～0x1F<br>
	 * (=1～9999)：(=1～12)：(=1～31)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppCurrentYmdSetting(String edt){return false;}
	/**
	 * 電力制限設定<br>
	 * EPC                 : 0x99<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電力制限の設定値をW で示す。<br>
	 * 0x0000~0xFFFF（0～65535W）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppPowerLimitSetting(String edt){return false;}


	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80", "動作状態");
			put("81", "設置場所");
			put("82", "規格Version情報");
			put("83", "識別番号");
			put("84", "瞬時消費電力計測値");
			put("85", "積算消費電力計測値");
			put("86", "メーカ異常コード");
			put("87", "電流制限設定");
			put("88", "異常発生状態");
			put("89", "異常内容");
			put("8A", "メーカコード");
			put("8B", "事業場コード");
			put("8C", "商品コード");
			put("8D", "製造番号");
			put("8E", "製造年月日");
			put("8F", "節電動作設定");
			put("93", "遠隔操作設定");
			put("97", "現在時刻設定");
			put("98", "現在年月日設定");
			put("99", "電力制限設定");
			put("9A", "積算運転時間");
			put("9B", "SetM プロパティマップ");
			put("9C", "GetM プロパティマップ");
			put("9D", "状変アナウンスプロパティマップ");
			put("9E", "Set プロパティマップ");
			put("9F", "Get プロパティマップ");
		}
	};

	/**
	 * Edtにおける年,月,日,時,分,秒,計測値のフォーマットをチェック（年,月,日,時,分,秒,計測値をチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkMeasuredValueFormat(String strEdt) {
		String strYmd = strEdt.substring(0, 8);
		String strHms = strEdt.substring(8, 14);
		String strVal = strEdt.substring(14, 22);

		if( ! ElUtil.checkEdtYmdSetFormat(strYmd)) return false;
		if( ! ElUtil.checkEdtHmsFormat(strHms)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", strVal ) <= 0 && ElUtil.compElUnsignedLong(strVal , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(strVal , "FFFFFFFE") == 0 )) return false;
		return true;
	}


	/**
	 * Edtにおける計測値履歴フォーマットをチェック
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkMeasuredLogValueFormat(String strEdt) {
		String strDate = strEdt.substring(0, 4);
		String strValues = strEdt.substring(4);

		if(! (ElUtil.compElUnsignedShort("0000", strDate ) <= 0 && ElUtil.compElUnsignedShort(strDate , "0063") <= 0 )) return false;

		for(int i = 0 ; i < 48; i++) {
			String strVal = strValues.substring(i * 4 * 2, (i+ 1) * 4 * 2);
			if(! (ElUtil.compElUnsignedLong("00000000", strVal ) <= 0 && ElUtil.compElUnsignedLong(strVal , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(strVal , "FFFFFFFE") == 0 )) return false;
		}
		return true;
	}

}
