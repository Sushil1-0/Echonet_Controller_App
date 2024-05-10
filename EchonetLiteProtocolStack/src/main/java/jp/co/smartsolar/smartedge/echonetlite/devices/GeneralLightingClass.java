package jp.co.smartsolar.smartedge.echonetlite.devices;

import java.util.HashMap;
import java.util.Map;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

/**
 * 一般照明クラス
 */
public class GeneralLightingClass extends DeviceObjectSuperClass{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "GeneralLightingClass";

	/**
	 *  クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "02";
	/**
	 *  クラスコード
	 */
	public static final String CLASS_CODE = "90";

	/**
	 * EPC : 動作状態
	 */
	public static final String EPC_OPERATION_STATUS = "80";
	/**
	 * EDT : 動作状態（ON）
	 */
	public static final String EDT_OPERATION_STATUS_ON = "30";
	/**
	 * EDT: 動作状態（OFF）
	 */
	public static final String EDT_OPERATION_STATUS_OFF = "31";



	/**
	 * EPC : 照度レベル設定
	 */
	public static final String EPC_ILLUMINANCE_LEVEL = "B0";

	/**
	 * EPC : 光色設定
	 */
	public static final String EPC_LIGHT_COLOR_SETTING = "B1";
	/**
	 * EDT : 光色設定(電球色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_INCANDESENT_COLOR = "41";
	/**
	 * EDT : 光色設定(白色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_WHITE = "42";
	/**
	 * EDT : 光色設定(昼白色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_DAYLIGHT_WHITE = "43";
	/**
	 * EDT : 光色設定(昼光色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_DAYLIGHT_COLOR = "44";
	/**
	 * EDT : 光色設定(その他)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_OTHER = "40";






	/**
	 * EPC : 照度レベル段階設定
	 */
	public static final String EPC_ILLUMINANCE_LEVEL_STEP_SETTING = "B2";


	/**
	 * EPC : 光色レベル段階設定
	 */
	public static final String EPC_LIGHT_COLOR_STEP_SETTING = "B3";


	/**
	 * EPC : 設定可能レベル最大値
	 */
	public static final String EPC_MAXIMUM_SPECIFIABLE_VALUES = "B4";


	/**
	 * EPC : 常夜灯設定可能レベル最大値
	 */
	public static final String EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING = "B5";


	/**
	 * EPC : 点灯モード設定
	 */
	public static final String EPC_LIGHTING_MODE_SETTING = "B6";
	/**
	 * EDT : 点灯モード設定(自動)
	 */
	public static final String EDT_LIGHTING_MODE_SETTING_AUTO = "41";
	/**
	 * EDT : 点灯モード設定(通常灯)
	 */
	public static final String EDT_LIGHTING_MODE_SETTING_MAIN_LIGHTING = "42";
	/**
	 * EDT : 点灯モード設定(常夜灯)
	 */
	public static final String EDT_LIGHTING_MODE_SETTING_NIGHT_LIGHTING = "43";
	/**
	 * EDT : 点灯モード設定(カラー灯)
	 */
	public static final String EDT_LIGHTING_MODE_SETTING_COLOR_LIGHTING = "44";


	/**
	 * EPC : 通常灯モード時照度レベル設定
	 */
	public static final String EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING = "B7";


	/**
	 * EPC : 通常灯モード時照度レベル段階設定
	 */
	public static final String EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING = "B8";


	/**
	 * EPC : 常夜灯モード時照度レベル設定
	 */
	public static final String EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING = "B9";


	/**
	 * EPC : 常夜灯モード時照度レベル段数設定
	 */
	public static final String EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING = "BA";


	/**
	 * EPC : 通常灯モード時光色設定
	 */
	public static final String EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING = "BB";
	/**
	 * EDT : 通常灯モード時光色設定(電球色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_INCANDESENT_COLOR = "41";
	/**
	 * EDT : 通常灯モード時光色設定(白色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_WHITE = "42";
	/**
	 * EDT : 通常灯モード時光色設定(昼白色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_DAYLIGHT_WHITE = "43";
	/**
	 * EDT : 通常灯モード時光色設定(昼光色)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_DAYLIGHT_COLOR = "44";
	/**
	 * EDT : 通常灯モード時光色設定(その他)
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_OTHER = "40";


	/**
	 * EPC : 通常灯モード時光色レベル段数設定
	 */
	public static final String EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING = "BC";


	/**
	 * EPC : 常夜灯モード時光色設定
	 */
	public static final String EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING = "BD";
	/**
	 * EDT : 常夜灯モード時光色設定（電球色）
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_INCANDESENT_COLOR = "41";
	/**
	 * EDT : 常夜灯モード時光色設定（白色）
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_WHITE = "42";
	/**
	 * EDT : 常夜灯モード時光色設定（昼白色）
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_DAYLIGHT_WHITE = "43";
	/**
	 * EDT : 常夜灯モード時光色設定（昼光色）
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_DAYLIGHT_COLOR = "44";
	/**
	 * EDT : 常夜灯モード時光色設定（その他）
	 */
	public static final String EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_OTHER = "40";


	/**
	 * EPC : 常夜灯モード時光色レベル段数設定
	 */
	public static final String EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING = "BE";


	/**
	 * EPC : 自動モード時点灯モード状態
	 */
	public static final String EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE = "BF";
	/**
	 * EDT : 自動モード時点灯モード状態（通常灯）
	 */
	public static final String EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_MAIN_LIGHTING = "42";
	/**
	 * EDT : 自動モード時点灯モード状態（常夜灯）
	 */
	public static final String EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_NIGHT_LIGHTING = "43";
	/**
	 * EDT : 自動モード時点灯モード状態（消灯）
	 */
	public static final String EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_OFF = "44";
	/**
	 * EDT : 自動モード時点灯モード状態（カラー灯）
	 */
	public static final String EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_COLOR_LIGHTING = "45";


	/**
	 * EPC : カラー灯モード時RGB 設定
	 */
	public static final String EPC_RGB_SETTING_FOR_COLOR_LIGHTNING = "C0";


	/**
	 * EPC : ON タイマ予約設定
	 */
	public static final String EPC_ON_TIMER_RESERVATION_SETTING = "90";
	/**
	 * EDT : ON タイマ予約設定（予約入）
	 */
	public static final String EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_ON = "41";
	/**
	 * EDT : ON タイマ予約設定（予約切）
	 */
	public static final String EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_OFF = "42";

	/**
	 * EPC : ON タイマ時刻設定
	 */
	public static final String EPC_ON_TIMER_SETTING = "91";

	/**
	 * EPC : OFF タイマ予約設定
	 */
	public static final String EPC_OFF_TIMER_RESERVATION_SETTING = "94";
	/**
	 * EDT : OFF タイマ予約設定（予約入）
	 */
	public static final String EDT_OFF_TIMER_RESERVATION_SETTING_RESERVATION_ON = "41";
	/**
	 * EDT : OFF タイマ予約設定（予約切）
	 */
	public static final String EDT_OFF_TIMER_RESERVATION_SETTING_RESERVATION_OFF = "42";

	/**
	 * EPC : OFF タイマ時刻設定
	 */
	public static final String EPC_OFF_TIMER_SETTING = "95";



	/**
	 * コンストラクタ
	 */
	public GeneralLightingClass() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
	}
	/**
	 * コンストラクタ
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public GeneralLightingClass(String entityCode) {
		super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

		this.addMapGetProps(EPC_OPERATION_STATUS);
//		this.addMapGetProps(EPC_ILLUMINANCE_LEVEL); // 必須ではない
//		this.addMapGetProps(EPC_LIGHT_COLOR_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_ILLUMINANCE_LEVEL_STEP_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_LIGHT_COLOR_STEP_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_MAXIMUM_SPECIFIABLE_VALUES); // 必須ではない
//		this.addMapGetProps(EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING); // 必須ではない
		this.addMapGetProps(EPC_LIGHTING_MODE_SETTING);
//		this.addMapGetProps(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING); // 必須ではない
//		this.addMapGetProps(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING); // 必須ではない
//		this.addMapGetProps(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING); // 必須ではない
//		this.addMapGetProps(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING); // 必須ではない
//		this.addMapGetProps(EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING); // 必須ではない
//		this.addMapGetProps(EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING); // 必須ではない
//		this.addMapGetProps(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING); // 必須ではない
//		this.addMapGetProps(EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE); // 必須ではない
//		this.addMapGetProps(EPC_RGB_SETTING_FOR_COLOR_LIGHTNING); // 必須ではない
//		this.addMapGetProps(EPC_ON_TIMER_RESERVATION_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_ON_TIMER_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_OFF_TIMER_RESERVATION_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_OFF_TIMER_SETTING); // 必須ではない

		this.addMapSetProps(EPC_OPERATION_STATUS);
//		this.addMapSetProps(EPC_ILLUMINANCE_LEVEL); // 必須ではない
//		this.addMapSetProps(EPC_LIGHT_COLOR_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_ILLUMINANCE_LEVEL_STEP_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_LIGHT_COLOR_STEP_SETTING); // 必須ではない
		this.addMapSetProps(EPC_LIGHTING_MODE_SETTING);
//		this.addMapSetProps(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING); // 必須ではない
//		this.addMapSetProps(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING); // 必須ではない
//		this.addMapSetProps(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING); // 必須ではない
//		this.addMapSetProps(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING); // 必須ではない
//		this.addMapSetProps(EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING); // 必須ではない
//		this.addMapSetProps(EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING); // 必須ではない
//		this.addMapSetProps(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING); // 必須ではない
//		this.addMapSetProps(EPC_RGB_SETTING_FOR_COLOR_LIGHTNING); // 必須ではない
//		this.addMapSetProps(EPC_ON_TIMER_RESERVATION_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_ON_TIMER_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_OFF_TIMER_RESERVATION_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_OFF_TIMER_SETTING); // 必須ではない

		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewGeneralLightingFound(this);
	}



	/**
	 * Set系処理の集約クラス
	 * 0x60 : プロパティ値書込み要求（応答不要）
	 * 0x61 : プロパティ値書込み要求（応答要）
	 */
	public static class ElSetProps extends DeviceObjectSuperClass.ElSetProps {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "GeneralLightingClass.ElSetProps";

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
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
			return this;
		}
		/**
		 * 照度レベル設定<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetIlluminanceLevel(String strEdt){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL, strEdt));
			return this;
		}
		/**
		 * 光色設定<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetLightColorSetting(String strEdt){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING, strEdt));
			return this;
		}
		/**
		 * 照度レベル段数設定<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetIlluminanceLevelStepSetting(String strEdt){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING, strEdt));
			return this;
		}
		/**
		 * 光色レベル段数設定<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetLightColorStepSetting(String strEdt){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_STEP_SETTING, strEdt));
			return this;
		}
		/**
		 * 点灯モード設定<br>
		 * EPC                 : 0xB6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動／通常灯／常夜灯／カラー灯<br>
		 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetLightingModeSetting(String strEdt){
			listProperty.add(new ElProp(EPC_LIGHTING_MODE_SETTING, strEdt));
			return this;
		}
		/**
		 * 通常灯モード時照度レベル設定<br>
		 * EPC                 : 0xB7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetIlluminanceLevelSettingForMainLightning(String strEdt){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING, strEdt));
			return this;
		}
		/**
		 * 通常灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xB8<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetIlluminanceLevelStepSettingForMainLightning(String strEdt){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING, strEdt));
			return this;
		}
		/**
		 * 常夜灯モード時照度レベル設定<br>
		 * EPC                 : 0xB9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetIlluminanceLevelSettingForNightLighting(String strEdt){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING, strEdt));
			return this;
		}
		/**
		 * 常夜灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xBA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetIlluminanceLevelStepSettingForNightLighting(String strEdt){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING, strEdt));
			return this;
		}
		/**
		 * 通常灯モード時光色設定<br>
		 * EPC                 : 0xBB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetLightColorSettingForLightSetting(String strEdt){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING, strEdt));
			return this;
		}
		/**
		 * 通常灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetLightColorLevelStepSettingForMainLightning(String strEdt){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING, strEdt));
			return this;
		}
		/**
		 * 常夜灯モード時光色設定<br>
		 * EPC                 : 0xBD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetLightColorSettingForNightLightning(String strEdt){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING, strEdt));
			return this;
		}
		/**
		 * 常夜灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBE<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetLightColorLevelStepSettingForNightLightning(String strEdt){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING, strEdt));
			return this;
		}
		/**
		 * カラー灯モード時RGB 設定<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
		 * 1Byte 目:R<br>
		 * 2Byte 目:G<br>
		 * 3Byte 目:B<br>
		 * 0x00～0xFF(0～255)<br>
		 * 最低輝度=0x00,最高輝度=0xFF<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetRgbSettingForColorLightning(String strEdt){
			listProperty.add(new ElProp(EPC_RGB_SETTING_FOR_COLOR_LIGHTNING, strEdt));
			return this;
		}
		/**
		 * ON タイマ予約設定<br>
		 * EPC                 : 0x90<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetOnTimerReservationSetting(String strEdt){
			listProperty.add(new ElProp(EPC_ON_TIMER_RESERVATION_SETTING, strEdt));
			return this;
		}
		/**
		 * ON タイマ時刻設定値<br>
		 * EPC                 : 0x91<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetOnTimerSetting(String strEdt){
			listProperty.add(new ElProp(EPC_ON_TIMER_SETTING, strEdt));
			return this;
		}
		/**
		 * OFF タイマ予約設定<br>
		 * EPC                 : 0x94<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetOffTimerReservationSetting(String strEdt){
			listProperty.add(new ElProp(EPC_OFF_TIMER_RESERVATION_SETTING, strEdt));
			return this;
		}
		/**
		 * OFF タイマ時刻設定値<br>
		 * EPC                 : 0x95<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
 		 * @param strEdt EDT値（16進数文字列）
		 * @return GeneralLightingClass.ElSetPropsオブジェクト
		 */
		public GeneralLightingClass.ElSetProps reqSetOffTimerSetting(String strEdt){
			listProperty.add(new ElProp(EPC_OFF_TIMER_SETTING, strEdt));
			return this;
		}
	}

	/**
	 * Get系処理の集約クラス
	 * 0x62 : プロパティ値読出し要求
	 */
	public static class ElGetProps extends DeviceObjectSuperClass.ElGetProps  {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "GeneralLightingClass.ElGetProps";

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
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 照度レベル設定<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetIlluminanceLevel(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL));
			return this;
		}
		/**
		 * 光色設定<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightColorSetting(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING));
			return this;
		}
		/**
		 * 照度レベル段数設定<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetIlluminanceLevelStepSetting(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING));
			return this;
		}
		/**
		 * 光色レベル段数設定<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightColorStepSetting(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_STEP_SETTING));
			return this;
		}
		/**
		 * 設定可能レベル最大値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetMaximumSpecifiableValues(){
			listProperty.add(new ElProp(EPC_MAXIMUM_SPECIFIABLE_VALUES));
			return this;
		}
		/**
		 * 常夜灯設定可能レベル最大値<br>
		 * EPC                 : 0xB5<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetMaximumValueOfSettableLevelForNightLightning(){
			listProperty.add(new ElProp(EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING));
			return this;
		}
		/**
		 * 点灯モード設定<br>
		 * EPC                 : 0xB6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動／通常灯／常夜灯／カラー灯<br>
		 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightingModeSetting(){
			listProperty.add(new ElProp(EPC_LIGHTING_MODE_SETTING));
			return this;
		}
		/**
		 * 通常灯モード時照度レベル設定<br>
		 * EPC                 : 0xB7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetIlluminanceLevelSettingForMainLightning(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING));
			return this;
		}
		/**
		 * 通常灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xB8<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetIlluminanceLevelStepSettingForMainLightning(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING));
			return this;
		}
		/**
		 * 常夜灯モード時照度レベル設定<br>
		 * EPC                 : 0xB9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetIlluminanceLevelSettingForNightLighting(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING));
			return this;
		}
		/**
		 * 常夜灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xBA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetIlluminanceLevelStepSettingForNightLighting(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING));
			return this;
		}
		/**
		 * 通常灯モード時光色設定<br>
		 * EPC                 : 0xBB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightColorSettingForLightSetting(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING));
			return this;
		}
		/**
		 * 通常灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightColorLevelStepSettingForMainLightning(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING));
			return this;
		}
		/**
		 * 常夜灯モード時光色設定<br>
		 * EPC                 : 0xBD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightColorSettingForNightLightning(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING));
			return this;
		}
		/**
		 * 常夜灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBE<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightColorLevelStepSettingForNightLightning(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING));
			return this;
		}
		/**
		 * 自動モード時点灯モード状態<br>
		 * EPC                 : 0xBF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動モード時の点灯モード状態を取得する<br>
		 * 通常灯=0x42, 常夜灯=0x43,消灯=0x44, カラー灯=0x45<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetLightningModeStatusInAutoMode(){
			listProperty.add(new ElProp(EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE));
			return this;
		}
		/**
		 * カラー灯モード時RGB 設定<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
		 * 1Byte 目:R<br>
		 * 2Byte 目:G<br>
		 * 3Byte 目:B<br>
		 * 0x00～0xFF(0～255)<br>
		 * 最低輝度=0x00,最高輝度=0xFF<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetRgbSettingForColorLightning(){
			listProperty.add(new ElProp(EPC_RGB_SETTING_FOR_COLOR_LIGHTNING));
			return this;
		}
		/**
		 * ON タイマ予約設定<br>
		 * EPC                 : 0x90<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetOnTimerReservationSetting(){
			listProperty.add(new ElProp(EPC_ON_TIMER_RESERVATION_SETTING));
			return this;
		}
		/**
		 * ON タイマ時刻設定値<br>
		 * EPC                 : 0x91<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetOnTimerSetting(){
			listProperty.add(new ElProp(EPC_ON_TIMER_SETTING));
			return this;
		}
		/**
		 * OFF タイマ予約設定<br>
		 * EPC                 : 0x94<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetOffTimerReservationSetting(){
			listProperty.add(new ElProp(EPC_OFF_TIMER_RESERVATION_SETTING));
			return this;
		}
		/**
		 * OFF タイマ時刻設定値<br>
		 * EPC                 : 0x95<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @return GeneralLightingClass.ElGetPropsオブジェクト
		 */
		public GeneralLightingClass.ElGetProps reqGetOffTimerSetting(){
			listProperty.add(new ElProp(EPC_OFF_TIMER_SETTING));
			return this;
		}
	}

	/**
	 * 通知系処理の集約クラス
	 * 0x73 : プロパティ値通知
	 * 0x63 : プロパティ値通知要求
	 */
	public static class ElInformProps extends DeviceObjectSuperClass.ElInformProps  {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "GeneralLightingClass.ElInformProps";

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
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 照度レベル設定<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfIlluminanceLevel(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL));
			return this;
		}
		/**
		 * 光色設定<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightColorSetting(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING));
			return this;
		}
		/**
		 * 照度レベル段数設定<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfIlluminanceLevelStepSetting(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING));
			return this;
		}
		/**
		 * 光色レベル段数設定<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightColorStepSetting(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_STEP_SETTING));
			return this;
		}
		/**
		 * 設定可能レベル最大値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfMaximumSpecifiableValues(){
			listProperty.add(new ElProp(EPC_MAXIMUM_SPECIFIABLE_VALUES));
			return this;
		}
		/**
		 * 常夜灯設定可能レベル最大値<br>
		 * EPC                 : 0xB5<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfMaximumValueOfSettableLevelForNightLightning(){
			listProperty.add(new ElProp(EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING));
			return this;
		}
		/**
		 * 点灯モード設定<br>
		 * EPC                 : 0xB6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動／通常灯／常夜灯／カラー灯<br>
		 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightingModeSetting(){
			listProperty.add(new ElProp(EPC_LIGHTING_MODE_SETTING));
			return this;
		}
		/**
		 * 通常灯モード時照度レベル設定<br>
		 * EPC                 : 0xB7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfIlluminanceLevelSettingForMainLightning(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING));
			return this;
		}
		/**
		 * 通常灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xB8<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfIlluminanceLevelStepSettingForMainLightning(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING));
			return this;
		}
		/**
		 * 常夜灯モード時照度レベル設定<br>
		 * EPC                 : 0xB9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfIlluminanceLevelSettingForNightLighting(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING));
			return this;
		}
		/**
		 * 常夜灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xBA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfIlluminanceLevelStepSettingForNightLighting(){
			listProperty.add(new ElProp(EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING));
			return this;
		}
		/**
		 * 通常灯モード時光色設定<br>
		 * EPC                 : 0xBB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightColorSettingForLightSetting(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING));
			return this;
		}
		/**
		 * 通常灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightColorLevelStepSettingForMainLightning(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING));
			return this;
		}
		/**
		 * 常夜灯モード時光色設定<br>
		 * EPC                 : 0xBD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightColorSettingForNightLightning(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING));
			return this;
		}
		/**
		 * 常夜灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBE<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightColorLevelStepSettingForNightLightning(){
			listProperty.add(new ElProp(EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING));
			return this;
		}
		/**
		 * 自動モード時点灯モード状態<br>
		 * EPC                 : 0xBF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動モード時の点灯モード状態を取得する<br>
		 * 通常灯=0x42, 常夜灯=0x43,消灯=0x44, カラー灯=0x45<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfLightningModeStatusInAutoMode(){
			listProperty.add(new ElProp(EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE));
			return this;
		}
		/**
		 * カラー灯モード時RGB 設定<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
		 * 1Byte 目:R<br>
		 * 2Byte 目:G<br>
		 * 3Byte 目:B<br>
		 * 0x00～0xFF(0～255)<br>
		 * 最低輝度=0x00,最高輝度=0xFF<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfRgbSettingForColorLightning(){
			listProperty.add(new ElProp(EPC_RGB_SETTING_FOR_COLOR_LIGHTNING));
			return this;
		}
		/**
		 * ON タイマ予約設定<br>
		 * EPC                 : 0x90<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfOnTimerReservationSetting(){
			listProperty.add(new ElProp(EPC_ON_TIMER_RESERVATION_SETTING));
			return this;
		}
		/**
		 * ON タイマ時刻設定値<br>
		 * EPC                 : 0x91<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfOnTimerSetting(){
			listProperty.add(new ElProp(EPC_ON_TIMER_SETTING));
			return this;
		}
		/**
		 * OFF タイマ予約設定<br>
		 * EPC                 : 0x94<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfOffTimerReservationSetting(){
			listProperty.add(new ElProp(EPC_OFF_TIMER_RESERVATION_SETTING));
			return this;
		}
		/**
		 * OFF タイマ時刻設定値<br>
		 * EPC                 : 0x95<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @return GeneralLightingClass.ElInformPropsオブジェクト
		 */
		public GeneralLightingClass.ElInformProps reqInfOffTimerSetting(){
			listProperty.add(new ElProp(EPC_OFF_TIMER_SETTING));
			return this;
		}
	}


	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setIAll(ElProcess elProcess) {
		return setIAll(elProcess, INSTANCE_CODE_ALL);
	}

	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
		return setIAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
		return setIAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new GeneralLightingClass(instanceCode), ElFrame.ESV_SETI);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setCAll(ElProcess elProcess) {
		return setCAll(elProcess, INSTANCE_CODE_ALL);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
		return setCAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
		return setCAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new GeneralLightingClass(instanceCode), ElFrame.ESV_SETC);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return GeneralLightingClass.ElGetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElGetProps getAll(ElProcess elProcess) {
		return getAll( elProcess,  INSTANCE_CODE_ALL);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : EOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return GeneralLightingClass.ElGetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
		return getAll( elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElGetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
		return getAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElGetPropsオブジェクト
	 */
	public static GeneralLightingClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new GeneralLightingClass(instanceCode), ElFrame.ESV_GET);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	public static GeneralLightingClass.ElInformProps infReqAll(ElProcess elProcess) {
		return infReqAll(elProcess, INSTANCE_CODE_ALL);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : EOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	public static GeneralLightingClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
		return infReqAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	public static GeneralLightingClass.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
		return infReqAll( elProcess,  getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	public static GeneralLightingClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new GeneralLightingClass(instanceCode), ElFrame.ESV_INF_REQ);
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
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElSetProps setI(){
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
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElSetProps setI(ElClassBase seoj){
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
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElSetProps setC(){
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
	 * @return GeneralLightingClass.ElSetPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElSetProps setC(ElClassBase seoj){
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
	 * @return GeneralLightingClass.ElGetPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElGetProps get(){
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
	 * @return GeneralLightingClass.ElGetPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElGetProps get(ElClassBase seoj){
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
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElInformProps infReq(){
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
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElInformProps infReq(ElClassBase seoj){
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
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElInformProps inf(){
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
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElInformProps inf(ElClassBase deoj){

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
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	public GeneralLightingClass.ElInformProps infC(String remoteIpAddress){
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
	 * @return GeneralLightingClass.ElInformPropsオブジェクト
	 */
	@Override
	public GeneralLightingClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
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
		private static final String TAG = "GeneralLightingClass.ReportProcessor";

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfSet(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfSet(seoj,  strTid,  strEsv,  objProp,  isSuccess);
			switch(objProp.getStrEpc()) {
				case EPC_OPERATION_STATUS:
					onSetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL:
					onSetIlluminanceLevel(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_SETTING:
					onSetLightColorSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING:
					onSetIlluminanceLevelStepSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_STEP_SETTING:
					onSetLightColorStepSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHTING_MODE_SETTING:
					onSetLightingModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING:
					onSetIlluminanceLevelSettingForMainLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
					onSetIlluminanceLevelStepSettingForMainLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING:
					onSetIlluminanceLevelSettingForNightLighting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING:
					onSetIlluminanceLevelStepSettingForNightLighting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING:
					onSetLightColorSettingForLightSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
					onSetLightColorLevelStepSettingForMainLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING:
					onSetLightColorSettingForNightLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING:
					onSetLightColorLevelStepSettingForNightLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RGB_SETTING_FOR_COLOR_LIGHTNING:
					onSetRgbSettingForColorLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ON_TIMER_RESERVATION_SETTING:
					onSetOnTimerReservationSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ON_TIMER_SETTING:
					onSetOnTimerSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OFF_TIMER_RESERVATION_SETTING:
					onSetOffTimerReservationSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OFF_TIMER_SETTING:
					onSetOffTimerSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;

				default:
					return;
			}
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfGetOrInform(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfGetOrInform(seoj,  strTid,  strEsv,  objProp,  isSuccess);
			switch(objProp.getStrEpc()) {
				case EPC_OPERATION_STATUS:
					onGetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL:
					onGetIlluminanceLevel(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_SETTING:
					onGetLightColorSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING:
					onGetIlluminanceLevelStepSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_STEP_SETTING:
					onGetLightColorStepSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MAXIMUM_SPECIFIABLE_VALUES:
					onGetMaximumSpecifiableValues(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING:
					onGetMaximumValueOfSettableLevelForNightLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHTING_MODE_SETTING:
					onGetLightingModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING:
					onGetIlluminanceLevelSettingForMainLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
					onGetIlluminanceLevelStepSettingForMainLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING:
					onGetIlluminanceLevelSettingForNightLighting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING:
					onGetIlluminanceLevelStepSettingForNightLighting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING:
					onGetLightColorSettingForLightSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
					onGetLightColorLevelStepSettingForMainLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING:
					onGetLightColorSettingForNightLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING:
					onGetLightColorLevelStepSettingForNightLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE:
					onGetLightningModeStatusInAutoMode(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RGB_SETTING_FOR_COLOR_LIGHTNING:
					onGetRgbSettingForColorLightning(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ON_TIMER_RESERVATION_SETTING:
					onGetOnTimerReservationSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ON_TIMER_SETTING:
					onGetOnTimerSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OFF_TIMER_RESERVATION_SETTING:
					onGetOffTimerReservationSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OFF_TIMER_SETTING:
					onGetOffTimerSetting(seoj, strTid, strEsv, objProp, isSuccess);
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
			super.onReceivePropertyReportOfInfC(seoj,  strTid,  strEsv,  objProp);
			switch(objProp.getStrEpc()) {
				case EPC_OPERATION_STATUS:
					onInformOperationStatus(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ILLUMINANCE_LEVEL:
					onInformIlluminanceLevel(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHT_COLOR_SETTING:
					onInformLightColorSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING:
					onInformIlluminanceLevelStepSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHT_COLOR_STEP_SETTING:
					onInformLightColorStepSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MAXIMUM_SPECIFIABLE_VALUES:
					onInformMaximumSpecifiableValues(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING:
					onInformMaximumValueOfSettableLevelForNightLightning(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHTING_MODE_SETTING:
					onInformLightingModeSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING:
					onInformIlluminanceLevelSettingForMainLightning(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
					onInformIlluminanceLevelStepSettingForMainLightning(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING:
					onInformIlluminanceLevelSettingForNightLighting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING:
					onInformIlluminanceLevelStepSettingForNightLighting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING:
					onInformLightColorSettingForLightSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
					onInformLightColorLevelStepSettingForMainLightning(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING:
					onInformLightColorSettingForNightLightning(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING:
					onInformLightColorLevelStepSettingForNightLightning(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE:
					onInformLightningModeStatusInAutoMode(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RGB_SETTING_FOR_COLOR_LIGHTNING:
					onInformRgbSettingForColorLightning(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ON_TIMER_RESERVATION_SETTING:
					onInformOnTimerReservationSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ON_TIMER_SETTING:
					onInformOnTimerSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OFF_TIMER_RESERVATION_SETTING:
					onInformOffTimerReservationSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OFF_TIMER_SETTING:
					onInformOffTimerSetting(seoj, strTid, strEsv, objProp);
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
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
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
		 * 照度レベル設定<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIlluminanceLevel(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 光色設定<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLightColorSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 照度レベル段数設定<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIlluminanceLevelStepSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 光色レベル段数設定<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLightColorStepSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 点灯モード設定<br>
		 * EPC                 : 0xB6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動／通常灯／常夜灯／カラー灯<br>
		 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLightingModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 通常灯モード時照度レベル設定<br>
		 * EPC                 : 0xB7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIlluminanceLevelSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 通常灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xB8<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIlluminanceLevelStepSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 常夜灯モード時照度レベル設定<br>
		 * EPC                 : 0xB9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIlluminanceLevelSettingForNightLighting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 常夜灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xBA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIlluminanceLevelStepSettingForNightLighting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 通常灯モード時光色設定<br>
		 * EPC                 : 0xBB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLightColorSettingForLightSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 通常灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLightColorLevelStepSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 常夜灯モード時光色設定<br>
		 * EPC                 : 0xBD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLightColorSettingForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 常夜灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBE<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLightColorLevelStepSettingForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * カラー灯モード時RGB 設定<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
		 * 1Byte 目:R<br>
		 * 2Byte 目:G<br>
		 * 3Byte 目:B<br>
		 * 0x00～0xFF(0～255)<br>
		 * 最低輝度=0x00,最高輝度=0xFF<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetRgbSettingForColorLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * ON タイマ予約設定<br>
		 * EPC                 : 0x90<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOnTimerReservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * ON タイマ時刻設定値<br>
		 * EPC                 : 0x91<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOnTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * OFF タイマ予約設定<br>
		 * EPC                 : 0x94<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOffTimerReservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * OFF タイマ時刻設定値<br>
		 * EPC                 : 0x95<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOffTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

		/**
		 * Get,Inf関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
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
		 * 照度レベル設定<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIlluminanceLevel(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 光色設定<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightColorSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 照度レベル段数設定<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIlluminanceLevelStepSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 光色レベル段数設定<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightColorStepSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 設定可能レベル最大値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMaximumSpecifiableValues(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 常夜灯設定可能レベル最大値<br>
		 * EPC                 : 0xB5<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMaximumValueOfSettableLevelForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 点灯モード設定<br>
		 * EPC                 : 0xB6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動／通常灯／常夜灯／カラー灯<br>
		 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightingModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 通常灯モード時照度レベル設定<br>
		 * EPC                 : 0xB7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIlluminanceLevelSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 通常灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xB8<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIlluminanceLevelStepSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 常夜灯モード時照度レベル設定<br>
		 * EPC                 : 0xB9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIlluminanceLevelSettingForNightLighting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 常夜灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xBA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIlluminanceLevelStepSettingForNightLighting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 通常灯モード時光色設定<br>
		 * EPC                 : 0xBB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightColorSettingForLightSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 通常灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightColorLevelStepSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 常夜灯モード時光色設定<br>
		 * EPC                 : 0xBD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightColorSettingForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 常夜灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBE<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightColorLevelStepSettingForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 自動モード時点灯モード状態<br>
		 * EPC                 : 0xBF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動モード時の点灯モード状態を取得する<br>
		 * 通常灯=0x42, 常夜灯=0x43,消灯=0x44, カラー灯=0x45<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLightningModeStatusInAutoMode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * カラー灯モード時RGB 設定<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
		 * 1Byte 目:R<br>
		 * 2Byte 目:G<br>
		 * 3Byte 目:B<br>
		 * 0x00～0xFF(0～255)<br>
		 * 最低輝度=0x00,最高輝度=0xFF<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRgbSettingForColorLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * ON タイマ予約設定<br>
		 * EPC                 : 0x90<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOnTimerReservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * ON タイマ時刻設定値<br>
		 * EPC                 : 0x91<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOnTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * OFF タイマ予約設定<br>
		 * EPC                 : 0x94<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOffTimerReservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * OFF タイマ時刻設定値<br>
		 * EPC                 : 0x95<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOffTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

		/**
		 * Inform関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 照度レベル設定<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIlluminanceLevel(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 光色設定<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightColorSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 照度レベル段数設定<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIlluminanceLevelStepSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 光色レベル段数設定<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightColorStepSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 設定可能レベル最大値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMaximumSpecifiableValues(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 常夜灯設定可能レベル最大値<br>
		 * EPC                 : 0xB5<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
		 * 1Byte 目:照度<br>
		 * 2Byte 目:光色<br>
		 * 0x01～0xFF(1～255 段階)<br>
		 * 0x00(機能を搭載していない場合)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMaximumValueOfSettableLevelForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 点灯モード設定<br>
		 * EPC                 : 0xB6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動／通常灯／常夜灯／カラー灯<br>
		 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightingModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 通常灯モード時照度レベル設定<br>
		 * EPC                 : 0xB7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIlluminanceLevelSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 通常灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xB8<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIlluminanceLevelStepSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 常夜灯モード時照度レベル設定<br>
		 * EPC                 : 0xB9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時の照度レベルを%で示す。<br>
		 * 0x00～0x64(0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIlluminanceLevelSettingForNightLighting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 常夜灯モード時照度レベル段数設定<br>
		 * EPC                 : 0xBA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIlluminanceLevelStepSettingForNightLighting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 通常灯モード時光色設定<br>
		 * EPC                 : 0xBB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightColorSettingForLightSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 通常灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightColorLevelStepSettingForMainLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 常夜灯モード時光色設定<br>
		 * EPC                 : 0xBD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色を設定する<br>
		 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightColorSettingForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 常夜灯モード時光色レベル段数設定<br>
		 * EPC                 : 0xBE<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
		 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightColorLevelStepSettingForNightLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 自動モード時点灯モード状態<br>
		 * EPC                 : 0xBF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 自動モード時の点灯モード状態を取得する<br>
		 * 通常灯=0x42, 常夜灯=0x43,消灯=0x44, カラー灯=0x45<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLightningModeStatusInAutoMode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * カラー灯モード時RGB 設定<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
		 * 1Byte 目:R<br>
		 * 2Byte 目:G<br>
		 * 3Byte 目:B<br>
		 * 0x00～0xFF(0～255)<br>
		 * 最低輝度=0x00,最高輝度=0xFF<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRgbSettingForColorLightning(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * ON タイマ予約設定<br>
		 * EPC                 : 0x90<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOnTimerReservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * ON タイマ時刻設定値<br>
		 * EPC                 : 0x91<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOnTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * OFF タイマ予約設定<br>
		 * EPC                 : 0x94<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 予約入／予約切<br>
		 * 予約入＝0x41,予約切＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOffTimerReservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * OFF タイマ時刻設定値<br>
		 * EPC                 : 0x95<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * タイマ値HH:MM<br>
		 * 0～0x17: 0～0x3B<br>
		 * (=0～23):(=0～59）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOffTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

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
			case EPC_ILLUMINANCE_LEVEL:
				return this.getFromAppIlluminanceLevel();
			case EPC_LIGHT_COLOR_SETTING:
				return this.getFromAppLightColorSetting();
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING:
				return this.getFromAppIlluminanceLevelStepSetting();
			case EPC_LIGHT_COLOR_STEP_SETTING:
				return this.getFromAppLightColorStepSetting();
			case EPC_MAXIMUM_SPECIFIABLE_VALUES:
				return this.getFromAppMaximumSpecifiableValues();
			case EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING:
				return this.getFromAppMaximumValueOfSettableLevelForNightLightning();
			case EPC_LIGHTING_MODE_SETTING:
				return this.getFromAppLightingModeSetting();
			case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING:
				return this.getFromAppIlluminanceLevelSettingForMainLightning();
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
				return this.getFromAppIlluminanceLevelStepSettingForMainLightning();
			case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING:
				return this.getFromAppIlluminanceLevelSettingForNightLighting();
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING:
				return this.getFromAppIlluminanceLevelStepSettingForNightLighting();
			case EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING:
				return this.getFromAppLightColorSettingForLightSetting();
			case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
				return this.getFromAppLightColorLevelStepSettingForMainLightning();
			case EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING:
				return this.getFromAppLightColorSettingForNightLightning();
			case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING:
				return this.getFromAppLightColorLevelStepSettingForNightLightning();
			case EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE:
				return this.getFromAppLightningModeStatusInAutoMode();
			case EPC_RGB_SETTING_FOR_COLOR_LIGHTNING:
				return this.getFromAppRgbSettingForColorLightning();
			case EPC_ON_TIMER_RESERVATION_SETTING:
				return this.getFromAppOnTimerReservationSetting();
			case EPC_ON_TIMER_SETTING:
				return this.getFromAppOnTimerSetting();
			case EPC_OFF_TIMER_RESERVATION_SETTING:
				return this.getFromAppOffTimerReservationSetting();
			case EPC_OFF_TIMER_SETTING:
				return this.getFromAppOffTimerSetting();
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
			case EPC_ILLUMINANCE_LEVEL:
				return this.isValidEdtIlluminanceLevel(edt);
			case EPC_LIGHT_COLOR_SETTING:
				return this.isValidEdtLightColorSetting(edt);
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING:
				return this.isValidEdtIlluminanceLevelStepSetting(edt);
			case EPC_LIGHT_COLOR_STEP_SETTING:
				return this.isValidEdtLightColorStepSetting(edt);
			case EPC_MAXIMUM_SPECIFIABLE_VALUES:
				return this.isValidEdtMaximumSpecifiableValues(edt);
			case EPC_MAXIMUM_VALUE_OF_SETTABLE_LEVEL_FOR_NIGHT_LIGHTNING:
				return this.isValidEdtMaximumValueOfSettableLevelForNightLightning(edt);
			case EPC_LIGHTING_MODE_SETTING:
				return this.isValidEdtLightingModeSetting(edt);
			case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING:
				return this.isValidEdtIlluminanceLevelSettingForMainLightning(edt);
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
				return this.isValidEdtIlluminanceLevelStepSettingForMainLightning(edt);
			case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING:
				return this.isValidEdtIlluminanceLevelSettingForNightLighting(edt);
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING:
				return this.isValidEdtIlluminanceLevelStepSettingForNightLighting(edt);
			case EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING:
				return this.isValidEdtLightColorSettingForLightSetting(edt);
			case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
				return this.isValidEdtLightColorLevelStepSettingForMainLightning(edt);
			case EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING:
				return this.isValidEdtLightColorSettingForNightLightning(edt);
			case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING:
				return this.isValidEdtLightColorLevelStepSettingForNightLightning(edt);
			case EPC_LIGHTNING_MODE_STATUS_IN_AUTO_MODE:
				return this.isValidEdtLightningModeStatusInAutoMode(edt);
			case EPC_RGB_SETTING_FOR_COLOR_LIGHTNING:
				return this.isValidEdtRgbSettingForColorLightning(edt);
			case EPC_ON_TIMER_RESERVATION_SETTING:
				return this.isValidEdtOnTimerReservationSetting(edt);
			case EPC_ON_TIMER_SETTING:
				return this.isValidEdtOnTimerSetting(edt);
			case EPC_OFF_TIMER_RESERVATION_SETTING:
				return this.isValidEdtOffTimerReservationSetting(edt);
			case EPC_OFF_TIMER_SETTING:
				return this.isValidEdtOffTimerSetting(edt);
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
			case EPC_ILLUMINANCE_LEVEL:
				return this.setToAppIlluminanceLevel(edt);
			case EPC_LIGHT_COLOR_SETTING:
				return this.setToAppLightColorSetting(edt);
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING:
				return this.setToAppIlluminanceLevelStepSetting(edt);
			case EPC_LIGHT_COLOR_STEP_SETTING:
				return this.setToAppLightColorStepSetting(edt);
			case EPC_LIGHTING_MODE_SETTING:
				return this.setToAppLightingModeSetting(edt);
			case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_MAIN_LIGHTNING:
				return this.setToAppIlluminanceLevelSettingForMainLightning(edt);
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
				return this.setToAppIlluminanceLevelStepSettingForMainLightning(edt);
			case EPC_ILLUMINANCE_LEVEL_SETTING_FOR_NIGHT_LIGHTING:
				return this.setToAppIlluminanceLevelSettingForNightLighting(edt);
			case EPC_ILLUMINANCE_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTING:
				return this.setToAppIlluminanceLevelStepSettingForNightLighting(edt);
			case EPC_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING:
				return this.setToAppLightColorSettingForLightSetting(edt);
			case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_MAIN_LIGHTNING:
				return this.setToAppLightColorLevelStepSettingForMainLightning(edt);
			case EPC_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING:
				return this.setToAppLightColorSettingForNightLightning(edt);
			case EPC_LIGHT_COLOR_LEVEL_STEP_SETTING_FOR_NIGHT_LIGHTNING:
				return this.setToAppLightColorLevelStepSettingForNightLightning(edt);
			case EPC_RGB_SETTING_FOR_COLOR_LIGHTNING:
				return this.setToAppRgbSettingForColorLightning(edt);
			case EPC_ON_TIMER_RESERVATION_SETTING:
				return this.setToAppOnTimerReservationSetting(edt);
			case EPC_ON_TIMER_SETTING:
				return this.setToAppOnTimerSetting(edt);
			case EPC_OFF_TIMER_RESERVATION_SETTING:
				return this.setToAppOffTimerReservationSetting(edt);
			case EPC_OFF_TIMER_SETTING:
				return this.setToAppOffTimerSetting(edt);
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
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
//	protected String getFromAppOperationStatus(){return null;}
	/**
	 * 照度レベル設定<br>
	 * EPC                 : 0xB0<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIlluminanceLevel(){return null;}
	/**
	 * 光色設定<br>
	 * EPC                 : 0xB1<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightColorSetting(){return null;}
	/**
	 * 照度レベル段数設定<br>
	 * EPC                 : 0xB2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIlluminanceLevelStepSetting(){return null;}
	/**
	 * 光色レベル段数設定<br>
	 * EPC                 : 0xB3<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightColorStepSetting(){return null;}
	/**
	 * 設定可能レベル最大値<br>
	 * EPC                 : 0xB4<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
	 * 1Byte 目:照度<br>
	 * 2Byte 目:光色<br>
	 * 0x01～0xFF(1～255 段階)<br>
	 * 0x00(機能を搭載していない場合)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMaximumSpecifiableValues(){return null;}
	/**
	 * 常夜灯設定可能レベル最大値<br>
	 * EPC                 : 0xB5<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
	 * 1Byte 目:照度<br>
	 * 2Byte 目:光色<br>
	 * 0x01～0xFF(1～255 段階)<br>
	 * 0x00(機能を搭載していない場合)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMaximumValueOfSettableLevelForNightLightning(){return null;}
	/**
	 * 点灯モード設定<br>
	 * EPC                 : 0xB6<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 自動／通常灯／常夜灯／カラー灯<br>
	 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightingModeSetting(){return null;}
	/**
	 * 通常灯モード時照度レベル設定<br>
	 * EPC                 : 0xB7<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時の照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIlluminanceLevelSettingForMainLightning(){return null;}
	/**
	 * 通常灯モード時照度レベル段数設定<br>
	 * EPC                 : 0xB8<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIlluminanceLevelStepSettingForMainLightning(){return null;}
	/**
	 * 常夜灯モード時照度レベル設定<br>
	 * EPC                 : 0xB9<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時の照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIlluminanceLevelSettingForNightLighting(){return null;}
	/**
	 * 常夜灯モード時照度レベル段数設定<br>
	 * EPC                 : 0xBA<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIlluminanceLevelStepSettingForNightLighting(){return null;}
	/**
	 * 通常灯モード時光色設定<br>
	 * EPC                 : 0xBB<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightColorSettingForLightSetting(){return null;}
	/**
	 * 通常灯モード時光色レベル段数設定<br>
	 * EPC                 : 0xBC<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightColorLevelStepSettingForMainLightning(){return null;}
	/**
	 * 常夜灯モード時光色設定<br>
	 * EPC                 : 0xBD<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightColorSettingForNightLightning(){return null;}
	/**
	 * 常夜灯モード時光色レベル段数設定<br>
	 * EPC                 : 0xBE<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightColorLevelStepSettingForNightLightning(){return null;}
	/**
	 * 自動モード時点灯モード状態<br>
	 * EPC                 : 0xBF<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 自動モード時の点灯モード状態を取得する<br>
	 * 通常灯=0x42, 常夜灯=0x43,消灯=0x44, カラー灯=0x45<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLightningModeStatusInAutoMode(){return null;}
	/**
	 * カラー灯モード時RGB 設定<br>
	 * EPC                 : 0xC0<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
	 * 1Byte 目:R<br>
	 * 2Byte 目:G<br>
	 * 3Byte 目:B<br>
	 * 0x00～0xFF(0～255)<br>
	 * 最低輝度=0x00,最高輝度=0xFF<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRgbSettingForColorLightning(){return null;}
	/**
	 * ON タイマ予約設定<br>
	 * EPC                 : 0x90<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 予約入／予約切<br>
	 * 予約入＝0x41,予約切＝0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOnTimerReservationSetting(){return null;}
	/**
	 * ON タイマ時刻設定値<br>
	 * EPC                 : 0x91<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * タイマ値HH:MM<br>
	 * 0～0x17: 0～0x3B<br>
	 * (=0～23):(=0～59）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOnTimerSetting(){return null;}
	/**
	 * OFF タイマ予約設定<br>
	 * EPC                 : 0x94<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 予約入／予約切<br>
	 * 予約入＝0x41,予約切＝0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOffTimerReservationSetting(){return null;}
	/**
	 * OFF タイマ時刻設定値<br>
	 * EPC                 : 0x95<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * タイマ値HH:MM<br>
	 * 0～0x17: 0～0x3B<br>
	 * (=0～23):(=0～59）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOffTimerSetting(){return null;}


	// アプリケーションから取得した値の妥当性を検証するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
//	protected boolean isValidEdtOperationStatus(String edt){return false;}
	/**
	 * 照度レベル設定<br>
	 * EPC                 : 0xB0<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIlluminanceLevel(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * 光色設定<br>
	 * EPC                 : 0xB1<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightColorSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_INCANDESENT_COLOR)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_WHITE)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_DAYLIGHT_WHITE)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_DAYLIGHT_COLOR)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_OTHER)
				) return false;
		return true;
	}
	/**
	 * 照度レベル段数設定<br>
	 * EPC                 : 0xB2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIlluminanceLevelStepSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 光色レベル段数設定<br>
	 * EPC                 : 0xB3<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightColorStepSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 設定可能レベル最大値<br>
	 * EPC                 : 0xB4<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
	 * 1Byte 目:照度<br>
	 * 2Byte 目:光色<br>
	 * 0x01～0xFF(1～255 段階)<br>
	 * 0x00(機能を搭載していない場合)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMaximumSpecifiableValues(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 常夜灯設定可能レベル最大値<br>
	 * EPC                 : 0xB5<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時の照度及び光色設定可能レベル最大値を取得する。<br>
	 * 1Byte 目:照度<br>
	 * 2Byte 目:光色<br>
	 * 0x01～0xFF(1～255 段階)<br>
	 * 0x00(機能を搭載していない場合)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMaximumValueOfSettableLevelForNightLightning(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 点灯モード設定<br>
	 * EPC                 : 0xB6<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 自動／通常灯／常夜灯／カラー灯<br>
	 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightingModeSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_LIGHTING_MODE_SETTING_AUTO)
				&& ! edt.equalsIgnoreCase(EDT_LIGHTING_MODE_SETTING_MAIN_LIGHTING)
				&& ! edt.equalsIgnoreCase(EDT_LIGHTING_MODE_SETTING_NIGHT_LIGHTING)
				&& ! edt.equalsIgnoreCase(EDT_LIGHTING_MODE_SETTING_COLOR_LIGHTING)
				) return false;
		return true;
	}
	/**
	 * 通常灯モード時照度レベル設定<br>
	 * EPC                 : 0xB7<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時の照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIlluminanceLevelSettingForMainLightning(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * 通常灯モード時照度レベル段数設定<br>
	 * EPC                 : 0xB8<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIlluminanceLevelStepSettingForMainLightning(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 常夜灯モード時照度レベル設定<br>
	 * EPC                 : 0xB9<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時の照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIlluminanceLevelSettingForNightLighting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * 常夜灯モード時照度レベル段数設定<br>
	 * EPC                 : 0xBA<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIlluminanceLevelStepSettingForNightLighting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 通常灯モード時光色設定<br>
	 * EPC                 : 0xBB<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightColorSettingForLightSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_INCANDESENT_COLOR)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_WHITE)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_DAYLIGHT_WHITE)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_DAYLIGHT_COLOR)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_LIGHT_SETTING_OTHER)
				) return false;
		return true;
	}
	/**
	 * 通常灯モード時光色レベル段数設定<br>
	 * EPC                 : 0xBC<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightColorLevelStepSettingForMainLightning(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 常夜灯モード時光色設定<br>
	 * EPC                 : 0xBD<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightColorSettingForNightLightning(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_INCANDESENT_COLOR)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_WHITE)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_DAYLIGHT_WHITE)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_DAYLIGHT_COLOR)
				&& ! edt.equalsIgnoreCase(EDT_LIGHT_COLOR_SETTING_FOR_NIGHT_LIGHTNING_OTHER)
				) return false;
		return true;
	}
	/**
	 * 常夜灯モード時光色レベル段数設定<br>
	 * EPC                 : 0xBE<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightColorLevelStepSettingForNightLightning(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 自動モード時点灯モード状態<br>
	 * EPC                 : 0xBF<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 自動モード時の点灯モード状態を取得する<br>
	 * 通常灯=0x42, 常夜灯=0x43,消灯=0x44, カラー灯=0x45<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLightningModeStatusInAutoMode(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_MAIN_LIGHTING)
				&& ! edt.equalsIgnoreCase(EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_NIGHT_LIGHTING)
				&& ! edt.equalsIgnoreCase(EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_OFF)
				&& ! edt.equalsIgnoreCase(EDT_LIGHTNING_MODE_STATUS_IN_AUTO_MODE_COLOR_LIGHTING)
				) return false;
		return true;
	}
	/**
	 * カラー灯モード時RGB 設定<br>
	 * EPC                 : 0xC0<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
	 * 1Byte 目:R<br>
	 * 2Byte 目:G<br>
	 * 3Byte 目:B<br>
	 * 0x00～0xFF(0～255)<br>
	 * 最低輝度=0x00,最高輝度=0xFF<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRgbSettingForColorLightning(String edt){
		if(edt == null || !(edt.length() == 3 * 2)) return false;
		return true;
	}
	/**
	 * ON タイマ予約設定<br>
	 * EPC                 : 0x90<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 予約入／予約切<br>
	 * 予約入＝0x41,予約切＝0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOnTimerReservationSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_ON)
				&& ! edt.equalsIgnoreCase(EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_OFF)
				) return false;
		return true;
	}
	/**
	 * ON タイマ時刻設定値<br>
	 * EPC                 : 0x91<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * タイマ値HH:MM<br>
	 * 0～0x17: 0～0x3B<br>
	 * (=0～23):(=0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOnTimerSetting(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! ElUtil.checkEdtHmFormat(edt)) return false;
		return true;
	}
	/**
	 * OFF タイマ予約設定<br>
	 * EPC                 : 0x94<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 予約入／予約切<br>
	 * 予約入＝0x41,予約切＝0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOffTimerReservationSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_OFF_TIMER_RESERVATION_SETTING_RESERVATION_ON)
				&& ! edt.equalsIgnoreCase(EDT_OFF_TIMER_RESERVATION_SETTING_RESERVATION_OFF)
				) return false;
		return true;
	}
	/**
	 * OFF タイマ時刻設定値<br>
	 * EPC                 : 0x95<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * タイマ値HH:MM<br>
	 * 0～0x17: 0～0x3B<br>
	 * (=0～23):(=0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOffTimerSetting(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! ElUtil.checkEdtHmFormat(edt)) return false;
		return true;
	}

	// アプリケーションの設置する値の妥当性を検証するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
//	protected boolean setToAppOperationStatus(String edt){return false;}
	/**
	 * 照度レベル設定<br>
	 * EPC                 : 0xB0<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIlluminanceLevel(String edt){return false;}
	/**
	 * 光色設定<br>
	 * EPC                 : 0xB1<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLightColorSetting(String edt){return false;}
	/**
	 * 照度レベル段数設定<br>
	 * EPC                 : 0xB2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIlluminanceLevelStepSetting(String edt){return false;}
	/**
	 * 光色レベル段数設定<br>
	 * EPC                 : 0xB3<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLightColorStepSetting(String edt){return false;}
	/**
	 * 点灯モード設定<br>
	 * EPC                 : 0xB6<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 自動／通常灯／常夜灯／カラー灯<br>
	 * 自動=0x41, 通常灯=0x42, 常夜灯=0x43, カラー灯=0x45<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLightingModeSetting(String edt){return false;}
	/**
	 * 通常灯モード時照度レベル設定<br>
	 * EPC                 : 0xB7<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時の照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIlluminanceLevelSettingForMainLightning(String edt){return false;}
	/**
	 * 通常灯モード時照度レベル段数設定<br>
	 * EPC                 : 0xB8<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIlluminanceLevelStepSettingForMainLightning(String edt){return false;}
	/**
	 * 常夜灯モード時照度レベル設定<br>
	 * EPC                 : 0xB9<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時の照度レベルを%で示す。<br>
	 * 0x00～0x64(0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIlluminanceLevelSettingForNightLighting(String edt){return false;}
	/**
	 * 常夜灯モード時照度レベル段数設定<br>
	 * EPC                 : 0xBA<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時照度レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能照度レベル最大値 (暗～明)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIlluminanceLevelStepSettingForNightLighting(String edt){return false;}
	/**
	 * 通常灯モード時光色設定<br>
	 * EPC                 : 0xBB<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLightColorSettingForLightSetting(String edt){return false;}
	/**
	 * 通常灯モード時光色レベル段数設定<br>
	 * EPC                 : 0xBC<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLightColorLevelStepSettingForMainLightning(String edt){return false;}
	/**
	 * 常夜灯モード時光色設定<br>
	 * EPC                 : 0xBD<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時光色を設定する<br>
	 * 電球色＝0x41, 白色＝0x42, 昼白色＝0x43, 昼光色＝0x44,その他=0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLightColorSettingForNightLightning(String edt){return false;}
	/**
	 * 常夜灯モード時光色レベル段数設定<br>
	 * EPC                 : 0xBE<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 常夜灯モード時光色レベルを段数で設定し、設定状態を取得する。<br>
	 * 0x01～設定可能光色レベル最大値 (電球色～白色)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLightColorLevelStepSettingForNightLightning(String edt){return false;}
	/**
	 * カラー灯モード時RGB 設定<br>
	 * EPC                 : 0xC0<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * カラー灯モード時のRGB値を設定し、設定状態を取得する。<br>
	 * 1Byte 目:R<br>
	 * 2Byte 目:G<br>
	 * 3Byte 目:B<br>
	 * 0x00～0xFF(0～255)<br>
	 * 最低輝度=0x00,最高輝度=0xFF<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppRgbSettingForColorLightning(String edt){return false;}
	/**
	 * ON タイマ予約設定<br>
	 * EPC                 : 0x90<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 予約入／予約切<br>
	 * 予約入＝0x41,予約切＝0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOnTimerReservationSetting(String edt){return false;}
	/**
	 * ON タイマ時刻設定値<br>
	 * EPC                 : 0x91<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * タイマ値HH:MM<br>
	 * 0～0x17: 0～0x3B<br>
	 * (=0～23):(=0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOnTimerSetting(String edt){return false;}
	/**
	 * OFF タイマ予約設定<br>
	 * EPC                 : 0x94<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 予約入／予約切<br>
	 * 予約入＝0x41,予約切＝0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOffTimerReservationSetting(String edt){return false;}
	/**
	 * OFF タイマ時刻設定値<br>
	 * EPC                 : 0x95<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * タイマ値HH:MM<br>
	 * 0～0x17: 0～0x3B<br>
	 * (=0～23):(=0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOffTimerSetting(String edt){return false;}

	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80", "動作状態");
			put("B0", "照度レベル設定");
			put("B1", "光色設定");
			put("B2", "照度レベル段数設定");
			put("B3", "光色レベル段数設定");
			put("B4", "設定可能レベル最大値");
			put("B5", "常夜灯設定可能レベル最大値");
			put("B6", "点灯モード設定");
			put("B7", "通常灯モード時照度レベル設定");
			put("B8", "通常灯モード時照度レベル段数設定");
			put("B9", "常夜灯モード時照度レベル設定");
			put("BA", "常夜灯モード時照度レベル段数設定");
			put("BB", "通常灯モード時光色設定");
			put("BC", "通常灯モード時光色レベル段数設定");
			put("BD", "常夜灯モード時光色設定");
			put("BE", "常夜灯モード時光色レベル段数設定");
			put("BF", "自動モード時点灯モード状態");
			put("C0", "カラー灯モード時RGB 設定");
			put("90", "ON タイマ予約設定");
			put("91", "ON タイマ時刻設定値");
			put("94", "OFF タイマ予約設定");
			put("95", "OFF タイマ時刻設定値");
		}
	};
}
