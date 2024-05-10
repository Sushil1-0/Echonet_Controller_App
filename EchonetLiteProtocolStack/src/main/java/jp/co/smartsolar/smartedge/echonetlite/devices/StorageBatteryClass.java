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
 * 蓄電池クラス
 */
public class StorageBatteryClass extends DeviceObjectSuperClass{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "StorageBatteryClass";

	/**
	 *  クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "02";
	/**
	 *  クラスコード
	 */
	public static final String CLASS_CODE = "7D";

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
	 * EPC : 識別番号
	 * オブジェクトを固有に識別する番号。
	 */
	public static final String EPC_IDENTIFICATION_NUM = "83";

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
	 * EPC : AC実効容量（充電）
	 */
	public static final String EPC_AC_EFFECTIVE_CAPACITY_CHARGING = "A0";

	/**
	 * EPC : AC実効容量（放電）
	 */
	public static final String EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING = "A1";


	/**
	 * EPC : AC充電可能容量
	 */
	public static final String EPC_AC_CHARGIABLE_CAPACITY = "A2";

	/**
	 * EPC : AC放電可能容量
	 */
	public static final String EPC_AC_DISCHARGIABLE_CAPACITY = "A3";

	/**
	 * EPC : AC充電可能量
	 */
	public static final String EPC_AC_CHARGIABLE_ELECTRIC_ENERGY = "A4";

	/**
	 * EPC : AC放電可能量
	 */
	public static final String EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY = "A5";

	/**
	 * EPC : AC充電上限設定
	 */
	public static final String EPC_AC_CHARGE_UPPER_LIMIT_SETTING = "A6";

	/**
	 * EPC : AC放電下限設定
	 */
	public static final String EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING = "A7";

	/**
	 * EPC : AC積算充電電力量計測値
	 */
	public static final String EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY = "A8";

	/**
	 * EPC : AC積算放電電力量計測値
	 */
	public static final String EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY = "A9";

	/**
	 * EPC : AC充電量設定値
	 */
	public static final String EPC_AC_CHARGE_AMOUNT_SETTING_VALUE = "AA";

	/**
	 * EPC : AC放電量設定値
	 */
	public static final String EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE = "AB";

	/**
	 * EPC : 最小最大充電電力値
	 */
	public static final String EPC_MIN_MAX_CHARGING_ELECTRIC_POWER = "C8";

	/**
	 * EPC : 最小最大放電電力値
	 */
	public static final String EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER = "C9";

	/**
	 * EPC : 最小最大充電電流値
	 */
	public static final String EPC_MIN_MAX_CHARGING_CURRENT = "CA";

	/**
	 * EPC : 最小最大放電電流値
	 */
	public static final String EPC_MIN_MAX_DISCHARGING_CURRENT = "CB";

	/**
	 * EPC : 再連系許可設定
	 */
	public static final String EPC_RE_INTERCONNECTION_PERMISSION_SETTING = "CC";
	/**
	 * EDT : 再連系許可設定（許可）
	 */
	public static final String EDT_RE_INTERCONNECTION_PERMISSION_SETTING_PERMITTED = "41";
	/**
	 * EDT : 再連系許可設定（禁止）
	 */
	public static final String EDT_RE_INTERCONNECTION_PERMISSION_SETTING_PROHIBITED = "42";

	/**
	 * EPC : 運転許可設定
	 */
	public static final String EPC_OPERATION_PERMISSION_SETTING = "CD";
	/**
	 * EDT : 運転許可設定（許可）
	 */
	public static final String EDT_OPERATION_PERMISSION_SETTING_PERMITTED = "41";
	/**
	 * EDT : 運転許可設定（禁止）
	 */
	public static final String EDT_OPERATION_PERMISSION_SETTING_PROHIBITED = "42";

	/**
	 * EPC : 自立運転許可設定
	 */
	public static final String EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING = "CE";
	/**
	 * EDT : 自立運転許可設定（許可）
	 */
	public static final String EDT_INDEPENDENT_OPERATION_PERMISSION_SETTING_PREMITTED = "41";
	/**
	 * EDT : 自立運転許可設定（禁止）
	 */
	public static final String EDT_INDEPENDENT_OPERATION_PERMISSION_SETTING_PROHIBITED = "42";

	/**
	 * EPC : 運転動作状態
	 */
	public static final String EPC_WORKING_OPERATION_STATUS = "CF";
	/**
	 * EDT : 運転動作状態（急速充電）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_RAPID_CHARGING = "41";
	/**
	 * EDT : 運転動作状態（充電）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_CHARGING = "42";
	/**
	 * EDT : 運転動作状態（放電）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_DISCHARGING = "43";
	/**
	 * EDT : 運転動作状態（待機）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_STANDBY = "44";
	/**
	 * EDT : 運転動作状態（テスト）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_TEST = "45";
	/**
	 * EDT : 運転動作状態（自動）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_AUTOMATIC = "46";
	/**
	 * EDT : 運転動作状態（再起動）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_RESTART = "48";
	/**
	 * EDT : 運転動作状態（実効容量再計算処理）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_EFFECTIVE_CAPACITY_RECALCULATION_PROCESSING = "49";
	/**
	 * EDT : 運転動作状態（その他）
	 */
	public static final String EDT_WORKING_OPERATION_STATUS_OTHER = "40";

	/**
	 * EPC : AC定格電力量
	 */
	public static final String EPC_AC_RATED_ELECTRIC_ENERGY = "C7";

	/**
	 * EPC : 定格電力量
	 */
	public static final String EPC_RATED_ELECTRIC_ENERGY = "D0";

	/**
	 * EPC : 定格容量
	 */
	public static final String EPC_RATED_CAPACITY = "D1";

	/**
	 * EPC : 定格電圧
	 */
	public static final String EPC_RATED_VOLTAGE = "D2";

	/**
	 * EPC : 瞬時充放電電力計測値
	 */
	public static final String EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER = "D3";

	/**
	 * EPC : 瞬時充放電電流計測値
	 */
	public static final String EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT = "D4";

	/**
	 * EPC : 瞬時充放電電圧計測値
	 */
	public static final String EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE = "D5";

	/**
	 * EPC : 積算放電電力量計測値
	 */
	public static final String EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY = "D6";

	/**
	 * EPC : 積算放電電力量リセット設定
	 */
	public static final String EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING = "D7";
	/**
	 * EDT : 積算放電電力量リセット設定（リセット）
	 */
	public static final String EDT_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING_RESET = "00";

	/**
	 * EPC : 積算充電電力量計測値
	 */
	public static final String EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY = "D8";

	/**
	 * EPC : 積算充電電力量リセット設定
	 */
	public static final String EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING = "D9";
	/**
	 * EDT : 積算充電電力量リセット設定（リセット）
	 */
	public static final String EDT_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING_RESET = "00";

	/**
	 * EPC : 運転モード設定
	 */
	public static final String EPC_OPERATION_MODE_SETTING = "DA";
	/**
	 * EDT : 運転モード設定（急速充電）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_RAPID_CHARGING = "41";
	/**
	 * EDT : 運転モード設定（充電）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_CHARGING = "42";
	/**
	 * EDT : 運転モード設定（放電）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_DISCHARGING = "43";
	/**
	 * EDT : 運転モード設定（待機）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_STANDBY = "44";
	/**
	 * EDT : 運転モード設定（テスト）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_TEST = "45";
	/**
	 * EDT : 運転モード設定（自動）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_AUTO = "46";
	/**
	 * EDT : 運転モード設定（再起動）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_RESTART = "48";
	/**
	 * EDT : 運転モード設定（実効容量再計算処理）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_EFFECTIVE_CAPACITY_RECALCULATION_PROCESSING  = "49";
	/**
	 * EDT : 運転モード設定（その他）
	 */
	public static final String EDT_OPERATION_MODE_SETTING_OTHERS = "40";

	/**
	 * EPC : 系統連系状態
	 */
	public static final String EPC_SYSTEM_INTERCONNECTED_TYPE = "DB";
	/**
	 * EDT : 系統連系状態（系統連系（逆潮流可））
	 */
	public static final String EDT_SYSTEM_INTERCONNECTED_TYPE_SYSTEM_INTERCONNECTION_REVERSE_POWER_FLOW_ACCEPTABLE = "00";
	/**
	 * EDT : 系統連系状態（独立）
	 */
	public static final String EDT_SYSTEM_INTERCONNECTED_TYPE_INDEPENDENT_TYPE = "01";
	/**
	 * EDT : 系統連系状態（系統連系（逆潮流不可））
	 */
	public static final String EDT_SYSTEM_INTERCONNECTED_TYPE_SYSTEM_INTERCONNECTION_REVERSE_POWER_FLOW_NOT_ACCEPTABLE = "02";

	/**
	 * EPC : 最小最大充電電力値（独立時）
	 */
	public static final String EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT = "DC";

	/**
	 * EPC : 最小最大放電電力値（独立時）
	 */
	public static final String EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT = "DD";

	/**
	 * EPC : 最小最大充電電流値（独立時）
	 */
	public static final String EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT = "DE";

	/**
	 * EPC : 最小最大放電電流値（独立時）
	 */
	public static final String EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT = "DF";

	/**
	 * EPC : 充放電量設定値1
	 */
	public static final String EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1 = "E0";

	/**
	 * EPC : 充放電量設定値2
	 */
	public static final String EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2 = "E1";

	/**
	 * EPC : 蓄電残量1
	 */
	public static final String EPC_REMAINING_STORED_ELECTRICITY_1 = "E2";

	/**
	 * EPC : 蓄電残量2
	 */
	public static final String EPC_REMAINING_STORED_ELECTRICITY_2 = "E3";

	/**
	 * EPC : 蓄電残量3
	 */
	public static final String EPC_REMAINING_STORED_ELECTRICITY_3 = "E4";

	/**
	 * EPC : 劣化状態
	 */
	public static final String EPC_BATTERY_STATE_OF_HEALTH = "E5";

	/**
	 * EPC : 蓄電池タイプ
	 */
	public static final String EPC_BATTERY_TYPE = "E6";

	/**
	 * EPC : 充電量設定値1
	 */
	public static final String EPC_CHARGING_AMOUNT_SETTING_1 = "E7";

	/**
	 * EPC : 放電量設定値1
	 */
	public static final String EPC_DISCHARGING_AMOUNT_SETTING_1 = "E8";

	/**
	 * EPC : 充電量設定値2
	 */
	public static final String EPC_CHARGING_AMOUNT_SETTING_2 = "E9";

	/**
	 * EPC : 放電量設定値2
	 */
	public static final String EPC_DISCHARGING_AMOUNT_SETTING_2 = "EA";

	/**
	 * EPC : 充電電力設定値
	 */
	public static final String EPC_CHARGING_ELECTRIC_POWER_SETTING = "EB";

	/**
	 * EPC : 放電電力設定値
	 */
	public static final String EPC_DISCHARGING_ELECTRIC_POWER_SETTING = "EC";

	/**
	 * EPC : 充電電流設定値
	 */
	public static final String EPC_CHARGING_CURRENT_SETTING = "ED";

	/**
	 * EPC : 放電電流設定値
	 */
	public static final String EPC_DISCHARGING_CURRENT_SETTING = "EE";

	/**
	 * EPC : 定格電圧（独立時）
	 */
	public static final String EPC_RATED_VOLTAGE_INDEPENDENT = "EF";


	/**
	 * コンストラクタ
	 */
	public StorageBatteryClass() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
	}
	/**
	 * コンストラクタ
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public StorageBatteryClass(String entityCode) {
		super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

		this.addMapGetProps(EPC_OPERATION_STATUS);
		this.addMapGetProps(EPC_IDENTIFICATION_NUM);
		this.addMapGetProps(EPC_CURRENT_TIME_SETTING);
		this.addMapGetProps(EPC_CURRENT_YMD_SETTING);
		this.addMapGetProps(EPC_AC_EFFECTIVE_CAPACITY_CHARGING);
		this.addMapGetProps(EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING);
		this.addMapGetProps(EPC_AC_CHARGIABLE_CAPACITY);
		this.addMapGetProps(EPC_AC_DISCHARGIABLE_CAPACITY);
		this.addMapGetProps(EPC_AC_CHARGIABLE_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY);
//		this.addMapGetProps(EPC_AC_CHARGE_UPPER_LIMIT_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING); // 必須ではない
		this.addMapGetProps(EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_AC_CHARGE_AMOUNT_SETTING_VALUE);
		this.addMapGetProps(EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE);
		this.addMapGetProps(EPC_MIN_MAX_CHARGING_ELECTRIC_POWER);
		this.addMapGetProps(EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER);
//		this.addMapGetProps(EPC_MIN_MAX_CHARGING_CURRENT); // 必須ではない
//		this.addMapGetProps(EPC_MIN_MAX_DISCHARGING_CURRENT); // 必須ではない
//		this.addMapGetProps(EPC_RE_INTERCONNECTION_PERMISSION_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_OPERATION_PERMISSION_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING); // 必須ではない
		this.addMapGetProps(EPC_WORKING_OPERATION_STATUS); // ※6「プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。」
//		this.addMapGetProps(EPC_AC_RATED_ELECTRIC_ENERGY); // 必須ではない
//		this.addMapGetProps(EPC_RATED_ELECTRIC_ENERGY); // 必須ではない
//		this.addMapGetProps(EPC_RATED_CAPACITY); // 必須ではない
//		this.addMapGetProps(EPC_RATED_VOLTAGE); // 必須ではない
		this.addMapGetProps(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER); // 必須ではない			// uncommented for lixil echonetTest 
//		this.addMapGetProps(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT); // 必須ではない
//		this.addMapGetProps(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE); // 必須ではない
		/**スレスタ変更3/16**/
		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY); // 必須ではない
		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY); // 必須ではない
		/**スレスタ変更3/16**/
		this.addMapGetProps(EPC_OPERATION_MODE_SETTING);
		this.addMapGetProps(EPC_SYSTEM_INTERCONNECTED_TYPE);
//		this.addMapGetProps(EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT); // 必須ではない
//		this.addMapGetProps(EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT); // 必須ではない
//		this.addMapGetProps(EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT); // 必須ではない
//		this.addMapGetProps(EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT); // 必須ではない
//		this.addMapGetProps(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1); // 必須ではない
//		this.addMapGetProps(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2); // 必須ではない
		this.addMapGetProps(EPC_REMAINING_STORED_ELECTRICITY_1); // ※2 「蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。」
//		this.addMapGetProps(EPC_REMAINING_STORED_ELECTRICITY_2); // ※2 「蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。」
		this.addMapGetProps(EPC_REMAINING_STORED_ELECTRICITY_3); // ※2 「蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。」
//		this.addMapGetProps(EPC_BATTERY_STATE_OF_HEALTH); // 必須ではない
		this.addMapGetProps(EPC_BATTERY_TYPE);
//		this.addMapGetProps(EPC_CHARGING_AMOUNT_SETTING_1); // 必須ではない
//		this.addMapGetProps(EPC_DISCHARGING_AMOUNT_SETTING_1); // 必須ではない
//		this.addMapGetProps(EPC_CHARGING_AMOUNT_SETTING_2); // 必須ではない
//		this.addMapGetProps(EPC_DISCHARGING_AMOUNT_SETTING_2); // 必須ではない
		this.addMapGetProps(EPC_CHARGING_ELECTRIC_POWER_SETTING); // 必須ではない
		this.addMapGetProps(EPC_DISCHARGING_ELECTRIC_POWER_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_CHARGING_CURRENT_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_DISCHARGING_CURRENT_SETTING); // 必須ではない
//		this.addMapGetProps(EPC_RATED_VOLTAGE_INDEPENDENT); // 必須ではない


		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
//		this.addMapSetProps(EPC_CURRENT_TIME_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_CURRENT_YMD_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_AC_CHARGE_UPPER_LIMIT_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING); // 必須ではない
		this.addMapSetProps(EPC_AC_CHARGE_AMOUNT_SETTING_VALUE);
		this.addMapSetProps(EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE);
//		this.addMapSetProps(EPC_RE_INTERCONNECTION_PERMISSION_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_OPERATION_PERMISSION_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING); // 必須ではない
		this.addMapSetProps(EPC_OPERATION_MODE_SETTING); // ※6「プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。」
//		this.addMapSetProps(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1); // 必須ではない
//		this.addMapSetProps(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2); // 必須ではない
//		this.addMapSetProps(EPC_CHARGING_AMOUNT_SETTING_1); // 必須ではない
//		this.addMapSetProps(EPC_DISCHARGING_AMOUNT_SETTING_1); // 必須ではない
//		this.addMapSetProps(EPC_CHARGING_AMOUNT_SETTING_2); // 必須ではない
//		this.addMapSetProps(EPC_DISCHARGING_AMOUNT_SETTING_2); // 必須ではない
		this.addMapSetProps(EPC_CHARGING_ELECTRIC_POWER_SETTING); // 必須ではない
		this.addMapSetProps(EPC_DISCHARGING_ELECTRIC_POWER_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_CHARGING_CURRENT_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_DISCHARGING_CURRENT_SETTING); // 必須ではない


		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
		this.addMapConvAnnounceProps(EPC_AC_CHARGE_AMOUNT_SETTING_VALUE);
		this.addMapConvAnnounceProps(EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE);
		this.addMapConvAnnounceProps(EPC_WORKING_OPERATION_STATUS);
		this.addMapConvAnnounceProps(EPC_OPERATION_MODE_SETTING);

	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewStorageBatteryFound(this);
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
		private static final String TAG = "StorageBatteryClass.ElSetProps";

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
		 * Set                 : オプション<br>
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
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
			return this;
		}
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetCurrentTimeSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CURRENT_TIME_SETTING, strEdt));
			return this;
		}
		/**
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
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
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetCurrentYmdSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING, strEdt));
			return this;
		}
		/**
		 * AC 充電上限設定<br>
		 * EPC                 : 0xA6<br>
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
		 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetAcChargeUpperLimitSetting(String strEdt){
			listProperty.add(new ElProp(EPC_AC_CHARGE_UPPER_LIMIT_SETTING, strEdt));
			return this;
		}
		/**
		 * AC 放電下限設定<br>
		 * EPC                 : 0xA7<br>
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
		 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetAcDischargeLowerLimitSetting(String strEdt){
			listProperty.add(new ElProp(EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING, strEdt));
			return this;
		}
		/**
		 * AC 充電量設定値<br>
		 * EPC                 : 0xAA<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetAcChargeAmountSettingValue(String strEdt){
			listProperty.add(new ElProp(EPC_AC_CHARGE_AMOUNT_SETTING_VALUE, strEdt));
			return this;
		}
		/**
		 * AC 放電量設定値<br>
		 * EPC                 : 0xAB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetAcDischargeAmountSettingValue(String strEdt){
			listProperty.add(new ElProp(EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE, strEdt));
			return this;
		}
		/**
		 * 再連系許可設定<br>
		 * EPC                 : 0xCC<br>
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
		 * 系統への連系の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetReInterconnectionPermissionSetting(String strEdt){
			listProperty.add(new ElProp(EPC_RE_INTERCONNECTION_PERMISSION_SETTING, strEdt));
			return this;
		}
		/**
		 * 運転許可設定<br>
		 * EPC                 : 0xCD<br>
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
		 * 蓄電池運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetOperationPermissionSetting(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_PERMISSION_SETTING, strEdt));
			return this;
		}
		/**
		 * 自立運転許可設定<br>
		 * EPC                 : 0xCE<br>
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
		 * 蓄電池の自立運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetIndependentOperationPermissionSetting(String strEdt){
			listProperty.add(new ElProp(EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING, strEdt));
			return this;
		}
		/**
		 * 積算放電電力量リセット設定<br>
		 * EPC                 : 0xD7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetMeasuredCumulativeDischargingElectricEnergyResetSetting(String strEdt){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING, strEdt));
			return this;
		}
		/**
		 * 積算充電電力量リセット設定<br>
		 * EPC                 : 0xD9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetMeasuredCumulativeChargingElectricEnergyResetSetting(String strEdt){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING, strEdt));
			return this;
		}
		/**
		 * 運転モード設定<br>
		 * EPC                 : 0xDA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetOperationModeSetting(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_MODE_SETTING, strEdt));
			return this;
		}
		/**
		 * 充放電量設定値1<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の電力量を±Wh (DC)で指定する<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetChargingDischargingAmountSetting1(String strEdt){
			listProperty.add(new ElProp(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1, strEdt));
			return this;
		}
		/**
		 * 充放電量設定値2<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetChargingDischargingAmountSetting2(String strEdt){
			listProperty.add(new ElProp(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2, strEdt));
			return this;
		}
		/**
		 * 充電量設定値1<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetChargingAmountSetting1(String strEdt){
			listProperty.add(new ElProp(EPC_CHARGING_AMOUNT_SETTING_1, strEdt));
			return this;
		}
		/**
		 * 放電量設定値1<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetDischargingAmountSetting1(String strEdt){
			listProperty.add(new ElProp(EPC_DISCHARGING_AMOUNT_SETTING_1, strEdt));
			return this;
		}
		/**
		 * 充電量設定値2<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetChargingAmountSetting2(String strEdt){
			listProperty.add(new ElProp(EPC_CHARGING_AMOUNT_SETTING_2, strEdt));
			return this;
		}
		/**
		 * 放電量設定値2<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetDischargingAmountSetting2(String strEdt){
			listProperty.add(new ElProp(EPC_DISCHARGING_AMOUNT_SETTING_2, strEdt));
			return this;
		}
		/**
		 * 充電電力設定値<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetChargingElectricPowerSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CHARGING_ELECTRIC_POWER_SETTING, strEdt));
			return this;
		}
		/**
		 * 放電電力設定値<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetDischargingElectricPowerSetting(String strEdt){
			listProperty.add(new ElProp(EPC_DISCHARGING_ELECTRIC_POWER_SETTING, strEdt));
			return this;
		}
		/**
		 * 充電電流設定値<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetChargingCurrentSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CHARGING_CURRENT_SETTING, strEdt));
			return this;
		}
		/**
		 * 放電電流設定値<br>
		 * EPC                 : 0xEE<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return StorageBatteryClass.ElSetPropsオブジェクト
		 */
		public StorageBatteryClass.ElSetProps reqSetDischargingCurrentSetting(String strEdt){
			listProperty.add(new ElProp(EPC_DISCHARGING_CURRENT_SETTING, strEdt));
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
		private static final String TAG = "StorageBatteryClass.ElGetProps";

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
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * <br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONETLite では使用しない）<br>
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
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetIdentificationNum(){
			listProperty.add(new ElProp(EPC_IDENTIFICATION_NUM));
			return this;
		}
		
		
		
		//added for echonetTest tool
//        public StorageBatteryClass.ElGetProps reqGetDynamicOperation(String EPC) {
//            listProperty.add(new ElProp(EPC));
//            return this;
//        }
        
        
        
        
        
        
		/**
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetCurrentTimeSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_TIME_SETTING));
			return this;
		}
		/**
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetCurrentYmdSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING));
			return this;
		}
		/**
		 * AC実効容量（充電）<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 空の蓄電池より充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcEffectiveCapacityCharging(){
			listProperty.add(new ElProp(EPC_AC_EFFECTIVE_CAPACITY_CHARGING));
			return this;
		}
		/**
		 * AC実効容量（放電）<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 満充電の蓄電池より放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcEffectiveCapacityDischarging(){
			listProperty.add(new ElProp(EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING));
			return this;
		}
		/**
		 * AC 充電可能容量<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcChargiableCapacity(){
			listProperty.add(new ElProp(EPC_AC_CHARGIABLE_CAPACITY));
			return this;
		}
		/**
		 * AC 放電可能容量<br>
		 * EPC                 : 0xA3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcDischargiableCapacity(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGIABLE_CAPACITY));
			return this;
		}
		/**
		 * AC 充電可能量<br>
		 * EPC                 : 0xA4<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcChargiableElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_CHARGIABLE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC 放電可能量<br>
		 * EPC                 : 0xA5<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh)<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcDischargiableElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC 充電上限設定<br>
		 * EPC                 : 0xA6<br>
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
		 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcChargeUpperLimitSetting(){
			listProperty.add(new ElProp(EPC_AC_CHARGE_UPPER_LIMIT_SETTING));
			return this;
		}
		/**
		 * AC 放電下限設定<br>
		 * EPC                 : 0xA7<br>
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
		 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcDischargeLowerLimitSetting(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING));
			return this;
		}
		/**
		 * AC積算充電電力量計測値<br>
		 * EPC                 : 0xA8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcMeasuredCumulativeChargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC積算放電電力量計測値<br>
		 * EPC                 : 0xA9<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcMeasuredCumulativeDischargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC 充電量設定値<br>
		 * EPC                 : 0xAA<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcChargeAmountSettingValue(){
			listProperty.add(new ElProp(EPC_AC_CHARGE_AMOUNT_SETTING_VALUE));
			return this;
		}
		/**
		 * AC 放電量設定値<br>
		 * EPC                 : 0xAB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcDischargeAmountSettingValue(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE));
			return this;
		}
		/**
		 * 最小最大充電電力値<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電力の最小値および最大値を、それぞれW (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxChargingElectricPower(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_ELECTRIC_POWER));
			return this;
		}
		/**
		 * 最小最大放電電力値<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電力の最小値および最大値を、それぞれW(AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxDischargingElectricPower(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER));
			return this;
		}
		/**
		 * 最小最大充電電流値<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxChargingCurrent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_CURRENT));
			return this;
		}
		/**
		 * 最小最大放電電流値<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小放電電流値：最大放電電流値<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxDischargingCurrent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_CURRENT));
			return this;
		}
		/**
		 * 再連系許可設定<br>
		 * EPC                 : 0xCC<br>
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
		 * 系統への連系の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetReInterconnectionPermissionSetting(){
			listProperty.add(new ElProp(EPC_RE_INTERCONNECTION_PERMISSION_SETTING));
			return this;
		}
		/**
		 * 運転許可設定<br>
		 * EPC                 : 0xCD<br>
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
		 * 蓄電池運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetOperationPermissionSetting(){
			listProperty.add(new ElProp(EPC_OPERATION_PERMISSION_SETTING));
			return this;
		}
		/**
		 * 自立運転許可設定<br>
		 * EPC                 : 0xCE<br>
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
		 * 蓄電池の自立運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetIndependentOperationPermissionSetting(){
			listProperty.add(new ElProp(EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING));
			return this;
		}
		/**
		 * 運転動作状態<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の動作状態を示す。充電状態、放電状態、待機状態の各運転動作状態は必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetWorkingOperationStatus(){
			listProperty.add(new ElProp(EPC_WORKING_OPERATION_STATUS));
			return this;
		}
		/**
		 * AC 定格電力量<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh（AC）で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetAcRatedElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_RATED_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 定格電力量<br>
		 * EPC                 : 0xD0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetRatedElectricEnergy(){
			listProperty.add(new ElProp(EPC_RATED_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 定格容量<br>
		 * EPC                 : 0xD1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格充電容量を0.1Ah(DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetRatedCapacity(){
			listProperty.add(new ElProp(EPC_RATED_CAPACITY));
			return this;
		}
		/**
		 * 定格電圧<br>
		 * EPC                 : 0xD2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電圧をV (DC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetRatedVoltage(){
			listProperty.add(new ElProp(EPC_RATED_VOLTAGE));
			return this;
		}
		/**
		 * 瞬時充放電電力計測値<br>
		 * EPC                 : 0xD3<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電力を±W (AC)で示す<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999W）：充電時（プラス値） 、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999W）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMeasuredInstantChargeDischargeElectricPower(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER));
			return this;
		}
		/**
		 * 瞬時充放電電流計測値<br>
		 * EPC                 : 0xD4<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電流を±0.1A (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6A）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7A）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMeasuredInstantChargeDischargeElectricCurrent(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT));
			return this;
		}
		/**
		 * 瞬時充放電電圧計測値<br>
		 * EPC                 : 0xD5<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電圧を±V (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 1 ～32,766V）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 1 ～ －32,767V）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMeasuredInstantChargeDischargeElectricVoltage(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE));
			return this;
		}
		/**
		 * 積算放電電力量計測値<br>
		 * EPC                 : 0xD6<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMeasuredCumulativeDischargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 積算充電電力量計測値<br>
		 * EPC                 : 0xD8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMeasuredCumulativeChargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 運転モード設定<br>
		 * EPC                 : 0xDA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetOperationModeSetting(){
			listProperty.add(new ElProp(EPC_OPERATION_MODE_SETTING));
			return this;
		}
		/**
		 * 系統連系状態<br>
		 * EPC                 : 0xDB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系状態のタイプを示す。<br>
		 * 系統連系（逆潮流可）＝0x00<br>
		 * 独立＝0x01<br>
		 * 系統連系（逆潮流不可）＝0x02<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetSystemInterconnectedType(){
			listProperty.add(new ElProp(EPC_SYSTEM_INTERCONNECTED_TYPE));
			return this;
		}
		/**
		 * 最小最大充電電力値（独立時）<br>
		 * EPC                 : 0xDC<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxChargingElectricPowerIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT));
			return this;
		}
		/**
		 * 最小最大放電電力値（独立時）<br>
		 * EPC                 : 0xDD<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxDischargingElectricPowerIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT));
			return this;
		}
		/**
		 * 最小最大充電電流値（独立時）<br>
		 * EPC                 : 0xDE<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxChargingCurrentIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT));
			return this;
		}
		/**
		 * 最小最大放電電流値（独立時）<br>
		 * EPC                 : 0xDF<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetMinMaxDischargingCurrentIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT));
			return this;
		}
		/**
		 * 充放電量設定値1<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の電力量を±Wh (DC)で指定する<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetChargingDischargingAmountSetting1(){
			listProperty.add(new ElProp(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1));
			return this;
		}
		/**
		 * 充放電量設定値2<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetChargingDischargingAmountSetting2(){
			listProperty.add(new ElProp(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2));
			return this;
		}
		/**
		 * 蓄電残量1<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetRemainingStoredElectricity1(){
			listProperty.add(new ElProp(EPC_REMAINING_STORED_ELECTRICITY_1));
			return this;
		}
		/**
		 * 蓄電残量2<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を0.1Ah (DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetRemainingStoredElectricity2(){
			listProperty.add(new ElProp(EPC_REMAINING_STORED_ELECTRICITY_2));
			return this;
		}
		/**
		 * 蓄電残量3<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetRemainingStoredElectricity3(){
			listProperty.add(new ElProp(EPC_REMAINING_STORED_ELECTRICITY_3));
			return this;
		}
		/**
		 * 劣化状態<br>
		 * EPC                 : 0xE5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の劣化状態（健康状態）を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetBatteryStateOfHealth(){
			listProperty.add(new ElProp(EPC_BATTERY_STATE_OF_HEALTH));
			return this;
		}
		/**
		 * 蓄電池タイプ<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電池の種類を示す<br>
		 * タイプ=0x00～0xFF<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetBatteryType(){
			listProperty.add(new ElProp(EPC_BATTERY_TYPE));
			return this;
		}
		/**
		 * 充電量設定値1<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetChargingAmountSetting1(){
			listProperty.add(new ElProp(EPC_CHARGING_AMOUNT_SETTING_1));
			return this;
		}
		/**
		 * 放電量設定値1<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetDischargingAmountSetting1(){
			listProperty.add(new ElProp(EPC_DISCHARGING_AMOUNT_SETTING_1));
			return this;
		}
		/**
		 * 充電量設定値2<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetChargingAmountSetting2(){
			listProperty.add(new ElProp(EPC_CHARGING_AMOUNT_SETTING_2));
			return this;
		}
		/**
		 * 放電量設定値2<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetDischargingAmountSetting2(){
			listProperty.add(new ElProp(EPC_DISCHARGING_AMOUNT_SETTING_2));
			return this;
		}
		/**
		 * 充電電力設定値<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetChargingElectricPowerSetting(){
			listProperty.add(new ElProp(EPC_CHARGING_ELECTRIC_POWER_SETTING));
			return this;
		}
		/**
		 * 放電電力設定値<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetDischargingElectricPowerSetting(){
			listProperty.add(new ElProp(EPC_DISCHARGING_ELECTRIC_POWER_SETTING));
			return this;
		}
		/**
		 * 充電電流設定値<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetChargingCurrentSetting(){
			listProperty.add(new ElProp(EPC_CHARGING_CURRENT_SETTING));
			return this;
		}
		/**
		 * 放電電流設定値<br>
		 * EPC                 : 0xEE<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetDischargingCurrentSetting(){
			listProperty.add(new ElProp(EPC_DISCHARGING_CURRENT_SETTING));
			return this;
		}
		/**
		 * 定格電圧（独立時）<br>
		 * EPC                 : 0xEF<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池の定格電圧をV(AC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @return StorageBatteryClass.ElGetPropsオブジェクト
		 */
		public StorageBatteryClass.ElGetProps reqGetRatedVoltageIndependent(){
			listProperty.add(new ElProp(EPC_RATED_VOLTAGE_INDEPENDENT));
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
		private static final String TAG = "StorageBatteryClass.ElInformProps";

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
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * <br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONETLite では使用しない）<br>
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
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfIdentificationNum(){
			listProperty.add(new ElProp(EPC_IDENTIFICATION_NUM));
			return this;
		}
		/**
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfCurrentTimeSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_TIME_SETTING));
			return this;
		}
		/**
		 * 現在年月日設定<br>
		 * EPC                 : 0x98<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1～0x270F：1～0x0C：1～0x1F<br>
		 * (=1～9999)：(=1～12)：(=1～31)<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfCurrentYmdSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING));
			return this;
		}
		/**
		 * AC実効容量（充電）<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 空の蓄電池より充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcEffectiveCapacityCharging(){
			listProperty.add(new ElProp(EPC_AC_EFFECTIVE_CAPACITY_CHARGING));
			return this;
		}
		/**
		 * AC実効容量（放電）<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 満充電の蓄電池より放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcEffectiveCapacityDischarging(){
			listProperty.add(new ElProp(EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING));
			return this;
		}
		/**
		 * AC 充電可能容量<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcChargiableCapacity(){
			listProperty.add(new ElProp(EPC_AC_CHARGIABLE_CAPACITY));
			return this;
		}
		/**
		 * AC 放電可能容量<br>
		 * EPC                 : 0xA3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcDischargiableCapacity(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGIABLE_CAPACITY));
			return this;
		}
		/**
		 * AC 充電可能量<br>
		 * EPC                 : 0xA4<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcChargiableElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_CHARGIABLE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC 放電可能量<br>
		 * EPC                 : 0xA5<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh)<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcDischargiableElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC 充電上限設定<br>
		 * EPC                 : 0xA6<br>
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
		 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcChargeUpperLimitSetting(){
			listProperty.add(new ElProp(EPC_AC_CHARGE_UPPER_LIMIT_SETTING));
			return this;
		}
		/**
		 * AC 放電下限設定<br>
		 * EPC                 : 0xA7<br>
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
		 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcDischargeLowerLimitSetting(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING));
			return this;
		}
		/**
		 * AC積算充電電力量計測値<br>
		 * EPC                 : 0xA8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcMeasuredCumulativeChargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC積算放電電力量計測値<br>
		 * EPC                 : 0xA9<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcMeasuredCumulativeDischargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * AC 充電量設定値<br>
		 * EPC                 : 0xAA<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcChargeAmountSettingValue(){
			listProperty.add(new ElProp(EPC_AC_CHARGE_AMOUNT_SETTING_VALUE));
			return this;
		}
		/**
		 * AC 放電量設定値<br>
		 * EPC                 : 0xAB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcDischargeAmountSettingValue(){
			listProperty.add(new ElProp(EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE));
			return this;
		}
		/**
		 * 最小最大充電電力値<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電力の最小値および最大値を、それぞれW (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxChargingElectricPower(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_ELECTRIC_POWER));
			return this;
		}
		/**
		 * 最小最大放電電力値<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電力の最小値および最大値を、それぞれW(AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxDischargingElectricPower(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER));
			return this;
		}
		/**
		 * 最小最大充電電流値<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxChargingCurrent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_CURRENT));
			return this;
		}
		/**
		 * 最小最大放電電流値<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小放電電流値：最大放電電流値<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxDischargingCurrent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_CURRENT));
			return this;
		}
		/**
		 * 再連系許可設定<br>
		 * EPC                 : 0xCC<br>
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
		 * 系統への連系の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfReInterconnectionPermissionSetting(){
			listProperty.add(new ElProp(EPC_RE_INTERCONNECTION_PERMISSION_SETTING));
			return this;
		}
		/**
		 * 運転許可設定<br>
		 * EPC                 : 0xCD<br>
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
		 * 蓄電池運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfOperationPermissionSetting(){
			listProperty.add(new ElProp(EPC_OPERATION_PERMISSION_SETTING));
			return this;
		}
		/**
		 * 自立運転許可設定<br>
		 * EPC                 : 0xCE<br>
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
		 * 蓄電池の自立運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfIndependentOperationPermissionSetting(){
			listProperty.add(new ElProp(EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING));
			return this;
		}
		/**
		 * 運転動作状態<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の動作状態を示す。充電状態、放電状態、待機状態の各運転動作状態は必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfWorkingOperationStatus(){
			listProperty.add(new ElProp(EPC_WORKING_OPERATION_STATUS));
			return this;
		}
		/**
		 * AC 定格電力量<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh（AC）で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfAcRatedElectricEnergy(){
			listProperty.add(new ElProp(EPC_AC_RATED_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 定格電力量<br>
		 * EPC                 : 0xD0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfRatedElectricEnergy(){
			listProperty.add(new ElProp(EPC_RATED_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 定格容量<br>
		 * EPC                 : 0xD1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格充電容量を0.1Ah(DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfRatedCapacity(){
			listProperty.add(new ElProp(EPC_RATED_CAPACITY));
			return this;
		}
		/**
		 * 定格電圧<br>
		 * EPC                 : 0xD2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電圧をV (DC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfRatedVoltage(){
			listProperty.add(new ElProp(EPC_RATED_VOLTAGE));
			return this;
		}
		/**
		 * 瞬時充放電電力計測値<br>
		 * EPC                 : 0xD3<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電力を±W (AC)で示す<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999W）：充電時（プラス値） 、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999W）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMeasuredInstantChargeDischargeElectricPower(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER));
			return this;
		}
		/**
		 * 瞬時充放電電流計測値<br>
		 * EPC                 : 0xD4<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電流を±0.1A (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6A）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7A）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMeasuredInstantChargeDischargeElectricCurrent(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT));
			return this;
		}
		/**
		 * 瞬時充放電電圧計測値<br>
		 * EPC                 : 0xD5<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電圧を±V (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 1 ～32,766V）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 1 ～ －32,767V）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMeasuredInstantChargeDischargeElectricVoltage(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE));
			return this;
		}
		/**
		 * 積算放電電力量計測値<br>
		 * EPC                 : 0xD6<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMeasuredCumulativeDischargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 積算放電電力量リセット設定<br>
		 * EPC                 : 0xD7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMeasuredCumulativeDischargingElectricEnergyResetSetting(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING));
			return this;
		}
		/**
		 * 積算充電電力量計測値<br>
		 * EPC                 : 0xD8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMeasuredCumulativeChargingElectricEnergy(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 積算充電電力量リセット設定<br>
		 * EPC                 : 0xD9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMeasuredCumulativeChargingElectricEnergyResetSetting(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING));
			return this;
		}
		/**
		 * 運転モード設定<br>
		 * EPC                 : 0xDA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfOperationModeSetting(){
			listProperty.add(new ElProp(EPC_OPERATION_MODE_SETTING));
			return this;
		}
		/**
		 * 系統連系状態<br>
		 * EPC                 : 0xDB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系状態のタイプを示す。<br>
		 * 系統連系（逆潮流可）＝0x00<br>
		 * 独立＝0x01<br>
		 * 系統連系（逆潮流不可）＝0x02<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfSystemInterconnectedType(){
			listProperty.add(new ElProp(EPC_SYSTEM_INTERCONNECTED_TYPE));
			return this;
		}
		/**
		 * 最小最大充電電力値（独立時）<br>
		 * EPC                 : 0xDC<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxChargingElectricPowerIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT));
			return this;
		}
		/**
		 * 最小最大放電電力値（独立時）<br>
		 * EPC                 : 0xDD<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxDischargingElectricPowerIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT));
			return this;
		}
		/**
		 * 最小最大充電電流値（独立時）<br>
		 * EPC                 : 0xDE<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxChargingCurrentIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT));
			return this;
		}
		/**
		 * 最小最大放電電流値（独立時）<br>
		 * EPC                 : 0xDF<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfMinMaxDischargingCurrentIndipendent(){
			listProperty.add(new ElProp(EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT));
			return this;
		}
		/**
		 * 充放電量設定値1<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の電力量を±Wh (DC)で指定する<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfChargingDischargingAmountSetting1(){
			listProperty.add(new ElProp(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1));
			return this;
		}
		/**
		 * 充放電量設定値2<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfChargingDischargingAmountSetting2(){
			listProperty.add(new ElProp(EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2));
			return this;
		}
		/**
		 * 蓄電残量1<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfRemainingStoredElectricity1(){
			listProperty.add(new ElProp(EPC_REMAINING_STORED_ELECTRICITY_1));
			return this;
		}
		/**
		 * 蓄電残量2<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を0.1Ah (DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfRemainingStoredElectricity2(){
			listProperty.add(new ElProp(EPC_REMAINING_STORED_ELECTRICITY_2));
			return this;
		}
		/**
		 * 蓄電残量3<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfRemainingStoredElectricity3(){
			listProperty.add(new ElProp(EPC_REMAINING_STORED_ELECTRICITY_3));
			return this;
		}
		/**
		 * 劣化状態<br>
		 * EPC                 : 0xE5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の劣化状態（健康状態）を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfBatteryStateOfHealth(){
			listProperty.add(new ElProp(EPC_BATTERY_STATE_OF_HEALTH));
			return this;
		}
		/**
		 * 蓄電池タイプ<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電池の種類を示す<br>
		 * タイプ=0x00～0xFF<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfBatteryType(){
			listProperty.add(new ElProp(EPC_BATTERY_TYPE));
			return this;
		}
		/**
		 * 充電量設定値1<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfChargingAmountSetting1(){
			listProperty.add(new ElProp(EPC_CHARGING_AMOUNT_SETTING_1));
			return this;
		}
		/**
		 * 放電量設定値1<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfDischargingAmountSetting1(){
			listProperty.add(new ElProp(EPC_DISCHARGING_AMOUNT_SETTING_1));
			return this;
		}
		/**
		 * 充電量設定値2<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfChargingAmountSetting2(){
			listProperty.add(new ElProp(EPC_CHARGING_AMOUNT_SETTING_2));
			return this;
		}
		/**
		 * 放電量設定値2<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfDischargingAmountSetting2(){
			listProperty.add(new ElProp(EPC_DISCHARGING_AMOUNT_SETTING_2));
			return this;
		}
		/**
		 * 充電電力設定値<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfChargingElectricPowerSetting(){
			listProperty.add(new ElProp(EPC_CHARGING_ELECTRIC_POWER_SETTING));
			return this;
		}
		/**
		 * 放電電力設定値<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfDischargingElectricPowerSetting(){
			listProperty.add(new ElProp(EPC_DISCHARGING_ELECTRIC_POWER_SETTING));
			return this;
		}
		/**
		 * 充電電流設定値<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfChargingCurrentSetting(){
			listProperty.add(new ElProp(EPC_CHARGING_CURRENT_SETTING));
			return this;
		}
		/**
		 * 放電電流設定値<br>
		 * EPC                 : 0xEE<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfDischargingCurrentSetting(){
			listProperty.add(new ElProp(EPC_DISCHARGING_CURRENT_SETTING));
			return this;
		}
		/**
		 * 定格電圧（独立時）<br>
		 * EPC                 : 0xEF<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池の定格電圧をV(AC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @return StorageBatteryClass.ElInformPropsオブジェクト
		 */
		public StorageBatteryClass.ElInformProps reqInfRatedVoltageIndependent(){
			listProperty.add(new ElProp(EPC_RATED_VOLTAGE_INDEPENDENT));
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setIAll(ElProcess elProcess) {
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new StorageBatteryClass(instanceCode), ElFrame.ESV_SETI);
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setCAll(ElProcess elProcess) {
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new StorageBatteryClass(instanceCode), ElFrame.ESV_SETC);
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
	 * @return StorageBatteryClass.ElGetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElGetProps getAll(ElProcess elProcess) {
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
	 * @return StorageBatteryClass.ElGetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return StorageBatteryClass.ElGetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
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
	 * @return StorageBatteryClass.ElGetPropsオブジェクト
	 */
	public static StorageBatteryClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new StorageBatteryClass(instanceCode), ElFrame.ESV_GET);
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	public static StorageBatteryClass.ElInformProps infReqAll(ElProcess elProcess) {
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	public static StorageBatteryClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	public static StorageBatteryClass.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	public static StorageBatteryClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new StorageBatteryClass(instanceCode), ElFrame.ESV_INF_REQ);
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElSetProps setI(){
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElSetProps setI(ElClassBase seoj){
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElSetProps setC(){
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
	 * @return StorageBatteryClass.ElSetPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElSetProps setC(ElClassBase seoj){
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
	 * @return StorageBatteryClass.ElGetPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElGetProps get(){
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
	 * @return StorageBatteryClass.ElGetPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElGetProps get(ElClassBase seoj){
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElInformProps infReq(){
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElInformProps infReq(ElClassBase seoj){
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElInformProps inf(){
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElInformProps inf(ElClassBase deoj){

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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	public StorageBatteryClass.ElInformProps infC(String remoteIpAddress){
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
	 * @return StorageBatteryClass.ElInformPropsオブジェクト
	 */
	@Override
	public StorageBatteryClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
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
		private static final String TAG = "StorageBatteryClass.ReportProcessor";

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
				case EPC_CURRENT_TIME_SETTING:
					onSetCurrentTimeSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CURRENT_YMD_SETTING:
					onSetCurrentYmdSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_CHARGE_UPPER_LIMIT_SETTING:
					onSetAcChargeUpperLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING:
					onSetAcDischargeLowerLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_CHARGE_AMOUNT_SETTING_VALUE:
					onSetAcChargeAmountSettingValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE:
					onSetAcDischargeAmountSettingValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RE_INTERCONNECTION_PERMISSION_SETTING:
					onSetReInterconnectionPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OPERATION_PERMISSION_SETTING:
					onSetOperationPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING:
					onSetIndependentOperationPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING:
					onSetMeasuredCumulativeDischargingElectricEnergyResetSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING:
					onSetMeasuredCumulativeChargingElectricEnergyResetSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OPERATION_MODE_SETTING:
					onSetOperationModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1:
					onSetChargingDischargingAmountSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2:
					onSetChargingDischargingAmountSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_AMOUNT_SETTING_1:
					onSetChargingAmountSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_AMOUNT_SETTING_1:
					onSetDischargingAmountSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_AMOUNT_SETTING_2:
					onSetChargingAmountSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_AMOUNT_SETTING_2:
					onSetDischargingAmountSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_ELECTRIC_POWER_SETTING:
					onSetChargingElectricPowerSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_ELECTRIC_POWER_SETTING:
					onSetDischargingElectricPowerSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_CURRENT_SETTING:
					onSetChargingCurrentSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_CURRENT_SETTING:
					onSetDischargingCurrentSetting(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_IDENTIFICATION_NUM:
					onGetIdentificationNum(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CURRENT_TIME_SETTING:
					onGetCurrentTimeSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CURRENT_YMD_SETTING:
					onGetCurrentYmdSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_EFFECTIVE_CAPACITY_CHARGING:
					onGetAcEffectiveCapacityCharging(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING:
					onGetAcEffectiveCapacityDischarging(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_CHARGIABLE_CAPACITY:
					onGetAcChargiableCapacity(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_DISCHARGIABLE_CAPACITY:
					onGetAcDischargiableCapacity(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_CHARGIABLE_ELECTRIC_ENERGY:
					onGetAcChargiableElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY:
					onGetAcDischargiableElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_CHARGE_UPPER_LIMIT_SETTING:
					onGetAcChargeUpperLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING:
					onGetAcDischargeLowerLimitSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
					onGetAcMeasuredCumulativeChargingElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
					onGetAcMeasuredCumulativeDischargingElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_CHARGE_AMOUNT_SETTING_VALUE:
					onGetAcChargeAmountSettingValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE:
					onGetAcDischargeAmountSettingValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER:
					onGetMinMaxChargingElectricPower(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER:
					onGetMinMaxDischargingElectricPower(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_CHARGING_CURRENT:
					onGetMinMaxChargingCurrent(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_DISCHARGING_CURRENT:
					onGetMinMaxDischargingCurrent(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RE_INTERCONNECTION_PERMISSION_SETTING:
					onGetReInterconnectionPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OPERATION_PERMISSION_SETTING:
					onGetOperationPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING:
					onGetIndependentOperationPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_WORKING_OPERATION_STATUS:
					onGetWorkingOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_AC_RATED_ELECTRIC_ENERGY:
					onGetAcRatedElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_ELECTRIC_ENERGY:
					onGetRatedElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_CAPACITY:
					onGetRatedCapacity(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_VOLTAGE:
					onGetRatedVoltage(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER:
					onGetMeasuredInstantChargeDischargeElectricPower(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT:
					onGetMeasuredInstantChargeDischargeElectricCurrent(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE:
					onGetMeasuredInstantChargeDischargeElectricVoltage(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
					onGetMeasuredCumulativeDischargingElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING:
					onGetMeasuredCumulativeDischargingElectricEnergyResetSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
					onGetMeasuredCumulativeChargingElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING:
					onGetMeasuredCumulativeChargingElectricEnergyResetSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OPERATION_MODE_SETTING:
					onGetOperationModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SYSTEM_INTERCONNECTED_TYPE:
					onGetSystemInterconnectedType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT:
					onGetMinMaxChargingElectricPowerIndipendent(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT:
					onGetMinMaxDischargingElectricPowerIndipendent(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT:
					onGetMinMaxChargingCurrentIndipendent(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT:
					onGetMinMaxDischargingCurrentIndipendent(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1:
					onGetChargingDischargingAmountSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2:
					onGetChargingDischargingAmountSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_REMAINING_STORED_ELECTRICITY_1:
					onGetRemainingStoredElectricity1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_REMAINING_STORED_ELECTRICITY_2:
					onGetRemainingStoredElectricity2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_REMAINING_STORED_ELECTRICITY_3:
					onGetRemainingStoredElectricity3(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_BATTERY_STATE_OF_HEALTH:
					onGetBatteryStateOfHealth(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_BATTERY_TYPE:
					onGetBatteryType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_AMOUNT_SETTING_1:
					onGetChargingAmountSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_AMOUNT_SETTING_1:
					onGetDischargingAmountSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_AMOUNT_SETTING_2:
					onGetChargingAmountSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_AMOUNT_SETTING_2:
					onGetDischargingAmountSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_ELECTRIC_POWER_SETTING:
					onGetChargingElectricPowerSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_ELECTRIC_POWER_SETTING:
					onGetDischargingElectricPowerSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHARGING_CURRENT_SETTING:
					onGetChargingCurrentSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DISCHARGING_CURRENT_SETTING:
					onGetDischargingCurrentSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_VOLTAGE_INDEPENDENT:
					onGetRatedVoltageIndependent(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_IDENTIFICATION_NUM:
					onInformIdentificationNum(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CURRENT_TIME_SETTING:
					onInformCurrentTimeSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CURRENT_YMD_SETTING:
					onInformCurrentYmdSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_EFFECTIVE_CAPACITY_CHARGING:
					onInformAcEffectiveCapacityCharging(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING:
					onInformAcEffectiveCapacityDischarging(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_CHARGIABLE_CAPACITY:
					onInformAcChargiableCapacity(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_DISCHARGIABLE_CAPACITY:
					onInformAcDischargiableCapacity(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_CHARGIABLE_ELECTRIC_ENERGY:
					onInformAcChargiableElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY:
					onInformAcDischargiableElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_CHARGE_UPPER_LIMIT_SETTING:
					onInformAcChargeUpperLimitSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING:
					onInformAcDischargeLowerLimitSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
					onInformAcMeasuredCumulativeChargingElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
					onInformAcMeasuredCumulativeDischargingElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_CHARGE_AMOUNT_SETTING_VALUE:
					onInformAcChargeAmountSettingValue(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE:
					onInformAcDischargeAmountSettingValue(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER:
					onInformMinMaxChargingElectricPower(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER:
					onInformMinMaxDischargingElectricPower(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_CHARGING_CURRENT:
					onInformMinMaxChargingCurrent(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_DISCHARGING_CURRENT:
					onInformMinMaxDischargingCurrent(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RE_INTERCONNECTION_PERMISSION_SETTING:
					onInformReInterconnectionPermissionSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OPERATION_PERMISSION_SETTING:
					onInformOperationPermissionSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING:
					onInformIndependentOperationPermissionSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_WORKING_OPERATION_STATUS:
					onInformWorkingOperationStatus(seoj, strTid, strEsv, objProp);
					return;
				case EPC_AC_RATED_ELECTRIC_ENERGY:
					onInformAcRatedElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RATED_ELECTRIC_ENERGY:
					onInformRatedElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RATED_CAPACITY:
					onInformRatedCapacity(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RATED_VOLTAGE:
					onInformRatedVoltage(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER:
					onInformMeasuredInstantChargeDischargeElectricPower(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT:
					onInformMeasuredInstantChargeDischargeElectricCurrent(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE:
					onInformMeasuredInstantChargeDischargeElectricVoltage(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
					onInformMeasuredCumulativeDischargingElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING:
					onInformMeasuredCumulativeDischargingElectricEnergyResetSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
					onInformMeasuredCumulativeChargingElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING:
					onInformMeasuredCumulativeChargingElectricEnergyResetSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OPERATION_MODE_SETTING:
					onInformOperationModeSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_SYSTEM_INTERCONNECTED_TYPE:
					onInformSystemInterconnectedType(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT:
					onInformMinMaxChargingElectricPowerIndipendent(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT:
					onInformMinMaxDischargingElectricPowerIndipendent(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT:
					onInformMinMaxChargingCurrentIndipendent(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT:
					onInformMinMaxDischargingCurrentIndipendent(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1:
					onInformChargingDischargingAmountSetting1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2:
					onInformChargingDischargingAmountSetting2(seoj, strTid, strEsv, objProp);
					return;
				case EPC_REMAINING_STORED_ELECTRICITY_1:
					onInformRemainingStoredElectricity1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_REMAINING_STORED_ELECTRICITY_2:
					onInformRemainingStoredElectricity2(seoj, strTid, strEsv, objProp);
					return;
				case EPC_REMAINING_STORED_ELECTRICITY_3:
					onInformRemainingStoredElectricity3(seoj, strTid, strEsv, objProp);
					return;
				case EPC_BATTERY_STATE_OF_HEALTH:
					onInformBatteryStateOfHealth(seoj, strTid, strEsv, objProp);
					return;
				case EPC_BATTERY_TYPE:
					onInformBatteryType(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CHARGING_AMOUNT_SETTING_1:
					onInformChargingAmountSetting1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_DISCHARGING_AMOUNT_SETTING_1:
					onInformDischargingAmountSetting1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CHARGING_AMOUNT_SETTING_2:
					onInformChargingAmountSetting2(seoj, strTid, strEsv, objProp);
					return;
				case EPC_DISCHARGING_AMOUNT_SETTING_2:
					onInformDischargingAmountSetting2(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CHARGING_ELECTRIC_POWER_SETTING:
					onInformChargingElectricPowerSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_DISCHARGING_ELECTRIC_POWER_SETTING:
					onInformDischargingElectricPowerSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CHARGING_CURRENT_SETTING:
					onInformChargingCurrentSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_DISCHARGING_CURRENT_SETTING:
					onInformDischargingCurrentSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RATED_VOLTAGE_INDEPENDENT:
					onInformRatedVoltageIndependent(seoj, strTid, strEsv, objProp);
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
		 * Set                 : オプション<br>
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
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
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
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
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
		 * AC 充電上限設定<br>
		 * EPC                 : 0xA6<br>
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
		 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetAcChargeUpperLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * AC 放電下限設定<br>
		 * EPC                 : 0xA7<br>
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
		 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetAcDischargeLowerLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * AC 充電量設定値<br>
		 * EPC                 : 0xAA<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetAcChargeAmountSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * AC 放電量設定値<br>
		 * EPC                 : 0xAB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetAcDischargeAmountSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 再連系許可設定<br>
		 * EPC                 : 0xCC<br>
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
		 * 系統への連系の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetReInterconnectionPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 運転許可設定<br>
		 * EPC                 : 0xCD<br>
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
		 * 蓄電池運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOperationPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 自立運転許可設定<br>
		 * EPC                 : 0xCE<br>
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
		 * 蓄電池の自立運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIndependentOperationPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 積算放電電力量リセット設定<br>
		 * EPC                 : 0xD7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetMeasuredCumulativeDischargingElectricEnergyResetSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 積算充電電力量リセット設定<br>
		 * EPC                 : 0xD9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetMeasuredCumulativeChargingElectricEnergyResetSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 運転モード設定<br>
		 * EPC                 : 0xDA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 充放電量設定値1<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の電力量を±Wh (DC)で指定する<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetChargingDischargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 充放電量設定値2<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetChargingDischargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 充電量設定値1<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetChargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 放電量設定値1<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetDischargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 充電量設定値2<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetChargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 放電量設定値2<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetDischargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 充電電力設定値<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetChargingElectricPowerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 放電電力設定値<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetDischargingElectricPowerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 充電電流設定値<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetChargingCurrentSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 放電電流設定値<br>
		 * EPC                 : 0xEE<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetDischargingCurrentSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

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
		 * Set                 : オプション<br>
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
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * <br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONETLite では使用しない）<br>
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
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
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
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
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
		 * AC実効容量（充電）<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 空の蓄電池より充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcEffectiveCapacityCharging(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC実効容量（放電）<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 満充電の蓄電池より放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcEffectiveCapacityDischarging(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 充電可能容量<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcChargiableCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 放電可能容量<br>
		 * EPC                 : 0xA3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcDischargiableCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 充電可能量<br>
		 * EPC                 : 0xA4<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcChargiableElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 放電可能量<br>
		 * EPC                 : 0xA5<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcDischargiableElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 充電上限設定<br>
		 * EPC                 : 0xA6<br>
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
		 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcChargeUpperLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 放電下限設定<br>
		 * EPC                 : 0xA7<br>
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
		 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcDischargeLowerLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC積算充電電力量計測値<br>
		 * EPC                 : 0xA8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcMeasuredCumulativeChargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC積算放電電力量計測値<br>
		 * EPC                 : 0xA9<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcMeasuredCumulativeDischargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 充電量設定値<br>
		 * EPC                 : 0xAA<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcChargeAmountSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 放電量設定値<br>
		 * EPC                 : 0xAB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcDischargeAmountSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大充電電力値<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電力の最小値および最大値を、それぞれW (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxChargingElectricPower(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大放電電力値<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電力の最小値および最大値を、それぞれW(AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxDischargingElectricPower(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大充電電流値<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxChargingCurrent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大放電電流値<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小放電電流値：最大放電電流値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxDischargingCurrent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 再連系許可設定<br>
		 * EPC                 : 0xCC<br>
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
		 * 系統への連系の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetReInterconnectionPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 運転許可設定<br>
		 * EPC                 : 0xCD<br>
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
		 * 蓄電池運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOperationPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 自立運転許可設定<br>
		 * EPC                 : 0xCE<br>
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
		 * 蓄電池の自立運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIndependentOperationPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 運転動作状態<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の動作状態を示す。充電状態、放電状態、待機状態の各運転動作状態は必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetWorkingOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * AC 定格電力量<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh（AC）で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAcRatedElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定格電力量<br>
		 * EPC                 : 0xD0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRatedElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定格容量<br>
		 * EPC                 : 0xD1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格充電容量を0.1Ah(DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRatedCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定格電圧<br>
		 * EPC                 : 0xD2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電圧をV (DC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRatedVoltage(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 瞬時充放電電力計測値<br>
		 * EPC                 : 0xD3<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電力を±W (AC)で示す<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999W）：充電時（プラス値） 、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999W）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredInstantChargeDischargeElectricPower(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 瞬時充放電電流計測値<br>
		 * EPC                 : 0xD4<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電流を±0.1A (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6A）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7A）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredInstantChargeDischargeElectricCurrent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 瞬時充放電電圧計測値<br>
		 * EPC                 : 0xD5<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電圧を±V (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 1 ～32,766V）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 1 ～ －32,767V）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredInstantChargeDischargeElectricVoltage(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算放電電力量計測値<br>
		 * EPC                 : 0xD6<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeDischargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算放電電力量リセット設定<br>
		 * EPC                 : 0xD7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeDischargingElectricEnergyResetSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算充電電力量計測値<br>
		 * EPC                 : 0xD8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeChargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算充電電力量リセット設定<br>
		 * EPC                 : 0xD9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeChargingElectricEnergyResetSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 運転モード設定<br>
		 * EPC                 : 0xDA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 系統連系状態<br>
		 * EPC                 : 0xDB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系状態のタイプを示す。<br>
		 * 系統連系（逆潮流可）＝0x00<br>
		 * 独立＝0x01<br>
		 * 系統連系（逆潮流不可）＝0x02<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSystemInterconnectedType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大充電電力値（独立時）<br>
		 * EPC                 : 0xDC<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxChargingElectricPowerIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大放電電力値（独立時）<br>
		 * EPC                 : 0xDD<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxDischargingElectricPowerIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大充電電流値（独立時）<br>
		 * EPC                 : 0xDE<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxChargingCurrentIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 最小最大放電電流値（独立時）<br>
		 * EPC                 : 0xDF<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMinMaxDischargingCurrentIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 充放電量設定値1<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の電力量を±Wh (DC)で指定する<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetChargingDischargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 充放電量設定値2<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetChargingDischargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 蓄電残量1<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRemainingStoredElectricity1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 蓄電残量2<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を0.1Ah (DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRemainingStoredElectricity2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 蓄電残量3<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRemainingStoredElectricity3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 劣化状態<br>
		 * EPC                 : 0xE5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の劣化状態（健康状態）を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetBatteryStateOfHealth(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 蓄電池タイプ<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電池の種類を示す<br>
		 * タイプ=0x00～0xFF<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetBatteryType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 充電量設定値1<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetChargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 放電量設定値1<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDischargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 充電量設定値2<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetChargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 放電量設定値2<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDischargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 充電電力設定値<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetChargingElectricPowerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 放電電力設定値<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDischargingElectricPowerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 充電電流設定値<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetChargingCurrentSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 放電電流設定値<br>
		 * EPC                 : 0xEE<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDischargingCurrentSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定格電圧（独立時）<br>
		 * EPC                 : 0xEF<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池の定格電圧をV(AC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRatedVoltageIndependent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

		/**
		 * InfC関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
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
		 * 識別番号<br>
		 * EPC                 : 0x83<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 9 or 17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * オブジェクトを固有に識別する番号。<br>
		 * <br>
		 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONETLite では使用しない）<br>
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
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
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
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
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
		 * AC実効容量（充電）<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 空の蓄電池より充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcEffectiveCapacityCharging(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC実効容量（放電）<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
		 * <br>
		 * 満充電の蓄電池より放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcEffectiveCapacityDischarging(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 充電可能容量<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcChargiableCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 放電可能容量<br>
		 * EPC                 : 0xA3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 通常時において、放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcDischargiableCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 充電可能量<br>
		 * EPC                 : 0xA4<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での充電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcChargiableElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 放電可能量<br>
		 * EPC                 : 0xA5<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現時点での放電可能な電力量（AC）<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcDischargiableElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 充電上限設定<br>
		 * EPC                 : 0xA6<br>
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
		 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcChargeUpperLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 放電下限設定<br>
		 * EPC                 : 0xA7<br>
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
		 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcDischargeLowerLimitSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC積算充電電力量計測値<br>
		 * EPC                 : 0xA8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcMeasuredCumulativeChargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC積算放電電力量計測値<br>
		 * EPC                 : 0xA9<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量（ AC ） を0.001kWh で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcMeasuredCumulativeDischargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 充電量設定値<br>
		 * EPC                 : 0xAA<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcChargeAmountSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 放電量設定値<br>
		 * EPC                 : 0xAB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : 必須<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力量（AC）をWh で指定する<br>
		 * 0x00000000: 未設定<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcDischargeAmountSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大充電電力値<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電力の最小値および最大値を、それぞれW (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxChargingElectricPower(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大放電電力値<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電力の最小値および最大値を、それぞれW(AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * 最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxDischargingElectricPower(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大充電電流値<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池への充電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxChargingCurrent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大放電電流値<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小放電電流値：最大放電電流値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxDischargingCurrent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 再連系許可設定<br>
		 * EPC                 : 0xCC<br>
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
		 * 系統への連系の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformReInterconnectionPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 運転許可設定<br>
		 * EPC                 : 0xCD<br>
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
		 * 蓄電池運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOperationPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 自立運転許可設定<br>
		 * EPC                 : 0xCE<br>
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
		 * 蓄電池の自立運転の許可、禁止を設定する。<br>
		 * 許可 = 0x41、禁止 = 0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIndependentOperationPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 運転動作状態<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の動作状態を示す。充電状態、放電状態、待機状態の各運転動作状態は必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformWorkingOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * AC 定格電力量<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh（AC）で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAcRatedElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定格電力量<br>
		 * EPC                 : 0xD0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電力量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRatedElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定格容量<br>
		 * EPC                 : 0xD1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格充電容量を0.1Ah(DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRatedCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定格電圧<br>
		 * EPC                 : 0xD2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の定格電圧をV (DC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRatedVoltage(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 瞬時充放電電力計測値<br>
		 * EPC                 : 0xD3<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電力を±W (AC)で示す<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999W）：充電時（プラス値） 、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999W）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredInstantChargeDischargeElectricPower(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 瞬時充放電電流計測値<br>
		 * EPC                 : 0xD4<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電流を±0.1A (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6A）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7A）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredInstantChargeDischargeElectricCurrent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 瞬時充放電電圧計測値<br>
		 * EPC                 : 0xD5<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時充放電電圧を±V (AC)で示す<br>
		 * 0x0001 ～ 0x7FFE （ 1 ～32,766V）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 1 ～ －32,767V）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredInstantChargeDischargeElectricVoltage(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算放電電力量計測値<br>
		 * EPC                 : 0xD6<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeDischargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算放電電力量リセット設定<br>
		 * EPC                 : 0xD7<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算放電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeDischargingElectricEnergyResetSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算充電電力量計測値<br>
		 * EPC                 : 0xD8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.001 kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量を0.001kWhで示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeChargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算充電電力量リセット設定<br>
		 * EPC                 : 0xD9<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : -<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算充電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeChargingElectricEnergyResetSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 運転モード設定<br>
		 * EPC                 : 0xDA<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
		 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 系統連系状態<br>
		 * EPC                 : 0xDB<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系状態のタイプを示す。<br>
		 * 系統連系（逆潮流可）＝0x00<br>
		 * 独立＝0x01<br>
		 * 系統連系（逆潮流不可）＝0x02<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSystemInterconnectedType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大充電電力値（独立時）<br>
		 * EPC                 : 0xDC<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小充電電力値：最大充電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxChargingElectricPowerIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大放電電力値（独立時）<br>
		 * EPC                 : 0xDD<br>
		 * データタイプ        : unsigned long×2<br>
		 * サイズ              : 8 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小放電電力値：最大放電電力値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxDischargingElectricPowerIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大充電電流値（独立時）<br>
		 * EPC                 : 0xDE<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池への充電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * 0x0000～0x7FFE（0～3,276.6A）<br>
		 * 最小充電電流値：最大充電電流値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxChargingCurrentIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 最小最大放電電流値（独立時）<br>
		 * EPC                 : 0xDF<br>
		 * データタイプ        : unsigned short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMinMaxDischargingCurrentIndipendent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 充放電量設定値1<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の電力量を±Wh (DC)で指定する<br>
		 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
		 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformChargingDischargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 充放電量設定値2<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : signed short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
		 * <br>
		 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
		 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
		 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformChargingDischargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 蓄電残量1<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量をWh (DC)で示す<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRemainingStoredElectricity1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 蓄電残量2<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を0.1Ah (DC)で示す<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRemainingStoredElectricity2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 蓄電残量3<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電残量を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRemainingStoredElectricity3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 劣化状態<br>
		 * EPC                 : 0xE5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 蓄電池の劣化状態（健康状態）を%で示す<br>
		 * 0x00～0x64 (0～100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformBatteryStateOfHealth(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 蓄電池タイプ<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電池の種類を示す<br>
		 * タイプ=0x00～0xFF<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformBatteryType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 充電量設定値1<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformChargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 放電量設定値1<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : Wh<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の電力量をWh (DC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDischargingAmountSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 充電量設定値2<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 充電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformChargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 放電量設定値2<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 Ah<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
		 * <br>
		 * 放電の容量を0.1Ah (DC)で指定する<br>
		 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDischargingAmountSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 充電電力設定値<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformChargingElectricPowerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 放電電力設定値<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電力をW (AC)で指定する<br>
		 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDischargingElectricPowerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 充電電流設定値<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 充電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformChargingCurrentSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 放電電流設定値<br>
		 * EPC                 : 0xEE<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 放電の電流を0.1A (AC)で指定する<br>
		 * 0x0000～0xFFFD（0～6,553.3A）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDischargingCurrentSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定格電圧（独立時）<br>
		 * EPC                 : 0xEF<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の蓄電池の定格電圧をV(AC)で示す<br>
		 * 0x0000～0x7FFE（0～32,766V）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRatedVoltageIndependent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

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
			case EPC_IDENTIFICATION_NUM:
				return this.getFromAppIdentificationNum();
			case EPC_CURRENT_TIME_SETTING:
				return this.getFromAppCurrentTimeSetting();
			case EPC_CURRENT_YMD_SETTING:
				return this.getFromAppCurrentYmdSetting();
			case EPC_AC_EFFECTIVE_CAPACITY_CHARGING:
				return this.getFromAppAcEffectiveCapacityCharging();
			case EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING:
				return this.getFromAppAcEffectiveCapacityDischarging();
			case EPC_AC_CHARGIABLE_CAPACITY:
				return this.getFromAppAcChargiableCapacity();
			case EPC_AC_DISCHARGIABLE_CAPACITY:
				return this.getFromAppAcDischargiableCapacity();
			case EPC_AC_CHARGIABLE_ELECTRIC_ENERGY:
				return this.getFromAppAcChargiableElectricEnergy();
			case EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY:
				return this.getFromAppAcDischargiableElectricEnergy();
			case EPC_AC_CHARGE_UPPER_LIMIT_SETTING:
				return this.getFromAppAcChargeUpperLimitSetting();
			case EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING:
				return this.getFromAppAcDischargeLowerLimitSetting();
			case EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
				return this.getFromAppAcMeasuredCumulativeChargingElectricEnergy();
			case EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
				return this.getFromAppAcMeasuredCumulativeDischargingElectricEnergy();
			case EPC_AC_CHARGE_AMOUNT_SETTING_VALUE:
				return this.getFromAppAcChargeAmountSettingValue();
			case EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE:
				return this.getFromAppAcDischargeAmountSettingValue();
			case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER:
				return this.getFromAppMinMaxChargingElectricPower();
			case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER:
				return this.getFromAppMinMaxDischargingElectricPower();
			case EPC_MIN_MAX_CHARGING_CURRENT:
				return this.getFromAppMinMaxChargingCurrent();
			case EPC_MIN_MAX_DISCHARGING_CURRENT:
				return this.getFromAppMinMaxDischargingCurrent();
			case EPC_RE_INTERCONNECTION_PERMISSION_SETTING:
				return this.getFromAppReInterconnectionPermissionSetting();
			case EPC_OPERATION_PERMISSION_SETTING:
				return this.getFromAppOperationPermissionSetting();
			case EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING:
				return this.getFromAppIndependentOperationPermissionSetting();
			case EPC_WORKING_OPERATION_STATUS:
				return this.getFromAppWorkingOperationStatus();
			case EPC_AC_RATED_ELECTRIC_ENERGY:
				return this.getFromAppAcRatedElectricEnergy();
			case EPC_RATED_ELECTRIC_ENERGY:
				return this.getFromAppRatedElectricEnergy();
			case EPC_RATED_CAPACITY:
				return this.getFromAppRatedCapacity();
			case EPC_RATED_VOLTAGE:
				return this.getFromAppRatedVoltage();
			case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER:
				return this.getFromAppMeasuredInstantChargeDischargeElectricPower();
			case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT:
				return this.getFromAppMeasuredInstantChargeDischargeElectricCurrent();
			case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE:
				return this.getFromAppMeasuredInstantChargeDischargeElectricVoltage();
			case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
				return this.getFromAppMeasuredCumulativeDischargingElectricEnergy();
			case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
				return this.getFromAppMeasuredCumulativeChargingElectricEnergy();
			case EPC_OPERATION_MODE_SETTING:
				return this.getFromAppOperationModeSetting();
			case EPC_SYSTEM_INTERCONNECTED_TYPE:
				return this.getFromAppSystemInterconnectedType();
			case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT:
				return this.getFromAppMinMaxChargingElectricPowerIndipendent();
			case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT:
				return this.getFromAppMinMaxDischargingElectricPowerIndipendent();
			case EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT:
				return this.getFromAppMinMaxChargingCurrentIndipendent();
			case EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT:
				return this.getFromAppMinMaxDischargingCurrentIndipendent();
			case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1:
				return this.getFromAppChargingDischargingAmountSetting1();
			case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2:
				return this.getFromAppChargingDischargingAmountSetting2();
			case EPC_REMAINING_STORED_ELECTRICITY_1:
				return this.getFromAppRemainingStoredElectricity1();
			case EPC_REMAINING_STORED_ELECTRICITY_2:
				return this.getFromAppRemainingStoredElectricity2();
			case EPC_REMAINING_STORED_ELECTRICITY_3:
				return this.getFromAppRemainingStoredElectricity3();
			case EPC_BATTERY_STATE_OF_HEALTH:
				return this.getFromAppBatteryStateOfHealth();
			case EPC_BATTERY_TYPE:
				return this.getFromAppBatteryType();
			case EPC_CHARGING_AMOUNT_SETTING_1:
				return this.getFromAppChargingAmountSetting1();
			case EPC_DISCHARGING_AMOUNT_SETTING_1:
				return this.getFromAppDischargingAmountSetting1();
			case EPC_CHARGING_AMOUNT_SETTING_2:
				return this.getFromAppChargingAmountSetting2();
			case EPC_DISCHARGING_AMOUNT_SETTING_2:
				return this.getFromAppDischargingAmountSetting2();
			case EPC_CHARGING_ELECTRIC_POWER_SETTING:
				return this.getFromAppChargingElectricPowerSetting();
			case EPC_DISCHARGING_ELECTRIC_POWER_SETTING:
				return this.getFromAppDischargingElectricPowerSetting();
			case EPC_CHARGING_CURRENT_SETTING:
				return this.getFromAppChargingCurrentSetting();
			case EPC_DISCHARGING_CURRENT_SETTING:
				return this.getFromAppDischargingCurrentSetting();
			case EPC_RATED_VOLTAGE_INDEPENDENT:
				return this.getFromAppRatedVoltageIndependent();

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
			case EPC_IDENTIFICATION_NUM:
				return this.isValidEdtIdentificationNum(edt);
			case EPC_CURRENT_TIME_SETTING:
				return this.isValidEdtCurrentTimeSetting(edt);
			case EPC_CURRENT_YMD_SETTING:
				return this.isValidEdtCurrentYmdSetting(edt);
			case EPC_AC_EFFECTIVE_CAPACITY_CHARGING:
				return this.isValidEdtAcEffectiveCapacityCharging(edt);
			case EPC_AC_EFFECTIVE_CAPACITY_DISCHARGING:
				return this.isValidEdtAcEffectiveCapacityDischarging(edt);
			case EPC_AC_CHARGIABLE_CAPACITY:
				return this.isValidEdtAcChargiableCapacity(edt);
			case EPC_AC_DISCHARGIABLE_CAPACITY:
				return this.isValidEdtAcDischargiableCapacity(edt);
			case EPC_AC_CHARGIABLE_ELECTRIC_ENERGY:
				return this.isValidEdtAcChargiableElectricEnergy(edt);
			case EPC_AC_DISCHARGIABLE_ELECTRIC_ENERGY:
				return this.isValidEdtAcDischargiableElectricEnergy(edt);
			case EPC_AC_CHARGE_UPPER_LIMIT_SETTING:
				return this.isValidEdtAcChargeUpperLimitSetting(edt);
			case EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING:
				return this.isValidEdtAcDischargeLowerLimitSetting(edt);
			case EPC_AC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
				return this.isValidEdtAcMeasuredCumulativeChargingElectricEnergy(edt);
			case EPC_AC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
				return this.isValidEdtAcMeasuredCumulativeDischargingElectricEnergy(edt);
			case EPC_AC_CHARGE_AMOUNT_SETTING_VALUE:
				return this.isValidEdtAcChargeAmountSettingValue(edt);
			case EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE:
				return this.isValidEdtAcDischargeAmountSettingValue(edt);
			case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER:
				return this.isValidEdtMinMaxChargingElectricPower(edt);
			case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER:
				return this.isValidEdtMinMaxDischargingElectricPower(edt);
			case EPC_MIN_MAX_CHARGING_CURRENT:
				return this.isValidEdtMinMaxChargingCurrent(edt);
			case EPC_MIN_MAX_DISCHARGING_CURRENT:
				return this.isValidEdtMinMaxDischargingCurrent(edt);
			case EPC_RE_INTERCONNECTION_PERMISSION_SETTING:
				return this.isValidEdtReInterconnectionPermissionSetting(edt);
			case EPC_OPERATION_PERMISSION_SETTING:
				return this.isValidEdtOperationPermissionSetting(edt);
			case EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING:
				return this.isValidEdtIndependentOperationPermissionSetting(edt);
			case EPC_WORKING_OPERATION_STATUS:
				return this.isValidEdtWorkingOperationStatus(edt);
			case EPC_AC_RATED_ELECTRIC_ENERGY:
				return this.isValidEdtAcRatedElectricEnergy(edt);
			case EPC_RATED_ELECTRIC_ENERGY:
				return this.isValidEdtRatedElectricEnergy(edt);
			case EPC_RATED_CAPACITY:
				return this.isValidEdtRatedCapacity(edt);
			case EPC_RATED_VOLTAGE:
				return this.isValidEdtRatedVoltage(edt);
			case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_POWER:
				return this.isValidEdtMeasuredInstantChargeDischargeElectricPower(edt);
			case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_CURRENT:
				return this.isValidEdtMeasuredInstantChargeDischargeElectricCurrent(edt);
			case EPC_MEASURED_INSTANT_CHARGE_DISCHARGE_ELECTRIC_VOLTAGE:
				return this.isValidEdtMeasuredInstantChargeDischargeElectricVoltage(edt);
			case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY:
				return this.isValidEdtMeasuredCumulativeDischargingElectricEnergy(edt);
			case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING:
				return this.isValidEdtMeasuredCumulativeDischargingElectricEnergyResetSetting(edt);
			case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY:
				return this.isValidEdtMeasuredCumulativeChargingElectricEnergy(edt);
			case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING:
				return this.isValidEdtMeasuredCumulativeChargingElectricEnergyResetSetting(edt);
			case EPC_OPERATION_MODE_SETTING:
				return this.isValidEdtOperationModeSetting(edt);
			case EPC_SYSTEM_INTERCONNECTED_TYPE:
				return this.isValidEdtSystemInterconnectedType(edt);
			case EPC_MIN_MAX_CHARGING_ELECTRIC_POWER_INDIPENDENT:
				return this.isValidEdtMinMaxChargingElectricPowerIndipendent(edt);
			case EPC_MIN_MAX_DISCHARGING_ELECTRIC_POWER_INDIPENDENT:
				return this.isValidEdtMinMaxDischargingElectricPowerIndipendent(edt);
			case EPC_MIN_MAX_CHARGING_CURRENT_INDIPENDENT:
				return this.isValidEdtMinMaxChargingCurrentIndipendent(edt);
			case EPC_MIN_MAX_DISCHARGING_CURRENT_INDIPENDENT:
				return this.isValidEdtMinMaxDischargingCurrentIndipendent(edt);
			case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1:
				return this.isValidEdtChargingDischargingAmountSetting1(edt);
			case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2:
				return this.isValidEdtChargingDischargingAmountSetting2(edt);
			case EPC_REMAINING_STORED_ELECTRICITY_1:
				return this.isValidEdtRemainingStoredElectricity1(edt);
			case EPC_REMAINING_STORED_ELECTRICITY_2:
				return this.isValidEdtRemainingStoredElectricity2(edt);
			case EPC_REMAINING_STORED_ELECTRICITY_3:
				return this.isValidEdtRemainingStoredElectricity3(edt);
			case EPC_BATTERY_STATE_OF_HEALTH:
				return this.isValidEdtBatteryStateOfHealth(edt);
			case EPC_BATTERY_TYPE:
				return this.isValidEdtBatteryType(edt);
			case EPC_CHARGING_AMOUNT_SETTING_1:
				return this.isValidEdtChargingAmountSetting1(edt);
			case EPC_DISCHARGING_AMOUNT_SETTING_1:
				return this.isValidEdtDischargingAmountSetting1(edt);
			case EPC_CHARGING_AMOUNT_SETTING_2:
				return this.isValidEdtChargingAmountSetting2(edt);
			case EPC_DISCHARGING_AMOUNT_SETTING_2:
				return this.isValidEdtDischargingAmountSetting2(edt);
			case EPC_CHARGING_ELECTRIC_POWER_SETTING:
				return this.isValidEdtChargingElectricPowerSetting(edt);
			case EPC_DISCHARGING_ELECTRIC_POWER_SETTING:
				return this.isValidEdtDischargingElectricPowerSetting(edt);
			case EPC_CHARGING_CURRENT_SETTING:
				return this.isValidEdtChargingCurrentSetting(edt);
			case EPC_DISCHARGING_CURRENT_SETTING:
				return this.isValidEdtDischargingCurrentSetting(edt);
			case EPC_RATED_VOLTAGE_INDEPENDENT:
				return this.isValidEdtRatedVoltageIndependent(edt);

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
			case EPC_CURRENT_TIME_SETTING:
				return this.setToAppCurrentTimeSetting(edt);
			case EPC_CURRENT_YMD_SETTING:
				return this.setToAppCurrentYmdSetting(edt);
			case EPC_AC_CHARGE_UPPER_LIMIT_SETTING:
				return this.setToAppAcChargeUpperLimitSetting(edt);
			case EPC_AC_DISCHARGE_LOWER_LIMIT_SETTING:
				return this.setToAppAcDischargeLowerLimitSetting(edt);
			case EPC_AC_CHARGE_AMOUNT_SETTING_VALUE:
				return this.setToAppAcChargeAmountSettingValue(edt);
			case EPC_AC_DISCHARGE_AMOUNT_SETTING_VALUE:
				return this.setToAppAcDischargeAmountSettingValue(edt);
			case EPC_RE_INTERCONNECTION_PERMISSION_SETTING:
				return this.setToAppReInterconnectionPermissionSetting(edt);
			case EPC_OPERATION_PERMISSION_SETTING:
				return this.setToAppOperationPermissionSetting(edt);
			case EPC_INDEPENDENT_OPERATION_PERMISSION_SETTING:
				return this.setToAppIndependentOperationPermissionSetting(edt);
			case EPC_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING:
				return this.setToAppMeasuredCumulativeDischargingElectricEnergyResetSetting(edt);
			case EPC_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING:
				return this.setToAppMeasuredCumulativeChargingElectricEnergyResetSetting(edt);
			case EPC_OPERATION_MODE_SETTING:
				return this.setToAppOperationModeSetting(edt);
			case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_1:
				return this.setToAppChargingDischargingAmountSetting1(edt);
			case EPC_CHARGING_DISCHARGING_AMOUNT_SETTING_2:
				return this.setToAppChargingDischargingAmountSetting2(edt);
			case EPC_CHARGING_AMOUNT_SETTING_1:
				return this.setToAppChargingAmountSetting1(edt);
			case EPC_DISCHARGING_AMOUNT_SETTING_1:
				return this.setToAppDischargingAmountSetting1(edt);
			case EPC_CHARGING_AMOUNT_SETTING_2:
				return this.setToAppChargingAmountSetting2(edt);
			case EPC_DISCHARGING_AMOUNT_SETTING_2:
				return this.setToAppDischargingAmountSetting2(edt);
			case EPC_CHARGING_ELECTRIC_POWER_SETTING:
				return this.setToAppChargingElectricPowerSetting(edt);
			case EPC_DISCHARGING_ELECTRIC_POWER_SETTING:
				return this.setToAppDischargingElectricPowerSetting(edt);
			case EPC_CHARGING_CURRENT_SETTING:
				return this.setToAppChargingCurrentSetting(edt);
			case EPC_DISCHARGING_CURRENT_SETTING:
				return this.setToAppDischargingCurrentSetting(edt);

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
	 * Set                 : オプション<br>
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
	 * 識別番号<br>
	 * EPC                 : 0x83<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 9 or 17 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * オブジェクトを固有に識別する番号。<br>
	 * <br>
	 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
	 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONETLite では使用しない）<br>
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
//	protected String getFromAppIdentificationNum(){return null;}
	/**
	 * 現在時刻設定<br>
	 * EPC                 : 0x97<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
//	protected String getFromAppCurrentTimeSetting(){return null;}
	/**
	 * 現在年月日設定<br>
	 * EPC                 : 0x98<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
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
//	protected String getFromAppCurrentYmdSetting(){return null;}
	/**
	 * AC実効容量（充電）<br>
	 * EPC                 : 0xA0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
	 * <br>
	 * 空の蓄電池より充電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcEffectiveCapacityCharging(){return null;}
	/**
	 * AC実効容量（放電）<br>
	 * EPC                 : 0xA1<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
	 * <br>
	 * 満充電の蓄電池より放電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcEffectiveCapacityDischarging(){return null;}
	/**
	 * AC 充電可能容量<br>
	 * EPC                 : 0xA2<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常時において、充電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcChargiableCapacity(){return null;}
	/**
	 * AC 放電可能容量<br>
	 * EPC                 : 0xA3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常時において、放電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcDischargiableCapacity(){return null;}
	/**
	 * AC 充電可能量<br>
	 * EPC                 : 0xA4<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現時点での充電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcChargiableElectricEnergy(){return null;}
	/**
	 * AC 放電可能量<br>
	 * EPC                 : 0xA5<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現時点での放電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcDischargiableElectricEnergy(){return null;}
	/**
	 * AC 充電上限設定<br>
	 * EPC                 : 0xA6<br>
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
	 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcChargeUpperLimitSetting(){return null;}
	/**
	 * AC 放電下限設定<br>
	 * EPC                 : 0xA7<br>
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
	 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcDischargeLowerLimitSetting(){return null;}
	/**
	 * AC積算充電電力量計測値<br>
	 * EPC                 : 0xA8<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算充電電力量（ AC ） を0.001kWh で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcMeasuredCumulativeChargingElectricEnergy(){return null;}
	/**
	 * AC積算放電電力量計測値<br>
	 * EPC                 : 0xA9<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算放電電力量（ AC ） を0.001kWh で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcMeasuredCumulativeDischargingElectricEnergy(){return null;}
	/**
	 * AC 充電量設定値<br>
	 * EPC                 : 0xAA<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電力量（AC）をWh で指定する<br>
	 * 0x00000000: 未設定<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcChargeAmountSettingValue(){return null;}
	/**
	 * AC 放電量設定値<br>
	 * EPC                 : 0xAB<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電力量（AC）をWh で指定する<br>
	 * 0x00000000: 未設定<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcDischargeAmountSettingValue(){return null;}
	/**
	 * 最小最大充電電力値<br>
	 * EPC                 : 0xC8<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池への充電電力の最小値および最大値を、それぞれW (AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * 最小充電電力値：最大充電電力値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxChargingElectricPower(){return null;}
	/**
	 * 最小最大放電電力値<br>
	 * EPC                 : 0xC9<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池からの放電電力の最小値および最大値を、それぞれW(AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * 最小放電電力値：最大放電電力値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxDischargingElectricPower(){return null;}
	/**
	 * 最小最大充電電流値<br>
	 * EPC                 : 0xCA<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池への充電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
	 * 0x0000～0x7FFE（0～3,276.6A）<br>
	 * 最小充電電流値：最大充電電流値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxChargingCurrent(){return null;}
	/**
	 * 最小最大放電電流値<br>
	 * EPC                 : 0xCB<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
	 * 0x0000～0x7FFE（0～3,276.6A）<br>
	 * 最小放電電流値：最大放電電流値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxDischargingCurrent(){return null;}
	/**
	 * 再連系許可設定<br>
	 * EPC                 : 0xCC<br>
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
	 * 系統への連系の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppReInterconnectionPermissionSetting(){return null;}
	/**
	 * 運転許可設定<br>
	 * EPC                 : 0xCD<br>
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
	 * 蓄電池運転の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOperationPermissionSetting(){return null;}
	/**
	 * 自立運転許可設定<br>
	 * EPC                 : 0xCE<br>
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
	 * 蓄電池の自立運転の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIndependentOperationPermissionSetting(){return null;}
	/**
	 * 運転動作状態<br>
	 * EPC                 : 0xCF<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の動作状態を示す。充電状態、放電状態、待機状態の各運転動作状態は必須とする。<br>
	 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppWorkingOperationStatus(){return null;}
	/**
	 * AC 定格電力量<br>
	 * EPC                 : 0xC7<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格電力量をWh（AC）で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAcRatedElectricEnergy(){return null;}
	/**
	 * 定格電力量<br>
	 * EPC                 : 0xD0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格電力量をWh (DC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRatedElectricEnergy(){return null;}
	/**
	 * 定格容量<br>
	 * EPC                 : 0xD1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格充電容量を0.1Ah(DC)で示す<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRatedCapacity(){return null;}
	/**
	 * 定格電圧<br>
	 * EPC                 : 0xD2<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格電圧をV (DC)で示す<br>
	 * 0x0000～0x7FFE（0～32,766V）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRatedVoltage(){return null;}
	/**
	 * 瞬時充放電電力計測値<br>
	 * EPC                 : 0xD3<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時充放電電力を±W (AC)で示す<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999W）：充電時（プラス値） 、<br>
	 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999W）：放電時（マイナス値）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredInstantChargeDischargeElectricPower(){return null;}
	/**
	 * 瞬時充放電電流計測値<br>
	 * EPC                 : 0xD4<br>
	 * データタイプ        : signed short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時充放電電流を±0.1A (AC)で示す<br>
	 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6A）：充電時（プラス値）、<br>
	 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7A）：放電時（マイナス値）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredInstantChargeDischargeElectricCurrent(){return null;}
	/**
	 * 瞬時充放電電圧計測値<br>
	 * EPC                 : 0xD5<br>
	 * データタイプ        : signed short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時充放電電圧を±V (AC)で示す<br>
	 * 0x0001 ～ 0x7FFE （ 1 ～32,766V）：充電時（プラス値）、<br>
	 * 0xFFFF ～ 0x8001 （ － 1 ～ －32,767V）：放電時（マイナス値）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredInstantChargeDischargeElectricVoltage(){return null;}
	/**
	 * 積算放電電力量計測値<br>
	 * EPC                 : 0xD6<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算放電電力量を0.001kWhで示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCumulativeDischargingElectricEnergy(){return null;}
	/**
	 * 積算充電電力量計測値<br>
	 * EPC                 : 0xD8<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算充電電力量を0.001kWhで示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCumulativeChargingElectricEnergy(){return null;}
	/**
	 * 運転モード設定<br>
	 * EPC                 : 0xDA<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
	 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOperationModeSetting(){return null;}
	/**
	 * 系統連系状態<br>
	 * EPC                 : 0xDB<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 系統連系状態のタイプを示す。<br>
	 * 系統連系（逆潮流可）＝0x00<br>
	 * 独立＝0x01<br>
	 * 系統連系（逆潮流不可）＝0x02<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSystemInterconnectedType(){return null;}
	/**
	 * 最小最大充電電力値（独立時）<br>
	 * EPC                 : 0xDC<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池への充電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小充電電力値：最大充電電力値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxChargingElectricPowerIndipendent(){return null;}
	/**
	 * 最小最大放電電力値（独立時）<br>
	 * EPC                 : 0xDD<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池からの放電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小放電電力値：最大放電電力値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxDischargingElectricPowerIndipendent(){return null;}
	/**
	 * 最小最大充電電流値（独立時）<br>
	 * EPC                 : 0xDE<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池への充電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
	 * 0x0000～0x7FFE（0～3,276.6A）<br>
	 * 最小充電電流値：最大充電電流値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxChargingCurrentIndipendent(){return null;}
	/**
	 * 最小最大放電電流値（独立時）<br>
	 * EPC                 : 0xDF<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMinMaxDischargingCurrentIndipendent(){return null;}
	/**
	 * 充放電量設定値1<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
	 * <br>
	 * 充電/放電の電力量を±Wh (DC)で指定する<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
	 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppChargingDischargingAmountSetting1(){return null;}
	/**
	 * 充放電量設定値2<br>
	 * EPC                 : 0xE1<br>
	 * データタイプ        : signed short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
	 * <br>
	 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
	 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
	 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppChargingDischargingAmountSetting2(){return null;}
	/**
	 * 蓄電残量1<br>
	 * EPC                 : 0xE2<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電残量をWh (DC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRemainingStoredElectricity1(){return null;}
	/**
	 * 蓄電残量2<br>
	 * EPC                 : 0xE3<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電残量を0.1Ah (DC)で示す<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRemainingStoredElectricity2(){return null;}
	/**
	 * 蓄電残量3<br>
	 * EPC                 : 0xE4<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電残量を%で示す<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRemainingStoredElectricity3(){return null;}
	/**
	 * 劣化状態<br>
	 * EPC                 : 0xE5<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の劣化状態（健康状態）を%で示す<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppBatteryStateOfHealth(){return null;}
	/**
	 * 蓄電池タイプ<br>
	 * EPC                 : 0xE6<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電池の種類を示す<br>
	 * タイプ=0x00～0xFF<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppBatteryType(){return null;}
	/**
	 * 充電量設定値1<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 充電の電力量をWh (DC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppChargingAmountSetting1(){return null;}
	/**
	 * 放電量設定値1<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 放電の電力量をWh (DC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDischargingAmountSetting1(){return null;}
	/**
	 * 充電量設定値2<br>
	 * EPC                 : 0xE9<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 充電の容量を0.1Ah (DC)で指定する<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppChargingAmountSetting2(){return null;}
	/**
	 * 放電量設定値2<br>
	 * EPC                 : 0xEA<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 放電の容量を0.1Ah (DC)で指定する<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDischargingAmountSetting2(){return null;}
	/**
	 * 充電電力設定値<br>
	 * EPC                 : 0xEB<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電力をW (AC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppChargingElectricPowerSetting(){return null;}
	/**
	 * 放電電力設定値<br>
	 * EPC                 : 0xEC<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電力をW (AC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDischargingElectricPowerSetting(){return null;}
	/**
	 * 充電電流設定値<br>
	 * EPC                 : 0xED<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電流を0.1A (AC)で指定する<br>
	 * 0x0000～0xFFFD（0～6,553.3A）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppChargingCurrentSetting(){return null;}
	/**
	 * 放電電流設定値<br>
	 * EPC                 : 0xEE<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電流を0.1A (AC)で指定する<br>
	 * 0x0000～0xFFFD（0～6,553.3A）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDischargingCurrentSetting(){return null;}
	/**
	 * 定格電圧（独立時）<br>
	 * EPC                 : 0xEF<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池の定格電圧をV(AC)で示す<br>
	 * 0x0000～0x7FFE（0～32,766V）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRatedVoltageIndependent(){return null;}




	// アプリケーションから取得した値の妥当性を検証するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
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
	 * 識別番号<br>
	 * EPC                 : 0x83<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 9 or 17 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * オブジェクトを固有に識別する番号。<br>
	 * <br>
	 * 1 バイト目：下位通信層ID ﾌｨｰﾙﾄﾞ<br>
	 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定（ECHONETLite では使用しない）<br>
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
//	protected boolean isValidEdtIdentificationNum(String edt){return false;}
	/**
	 * 現在時刻設定<br>
	 * EPC                 : 0x97<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
//	protected boolean isValidEdtCurrentTimeSetting(String edt){return false;}
	/**
	 * 現在年月日設定<br>
	 * EPC                 : 0x98<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
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
//	protected boolean isValidEdtCurrentYmdSetting(String edt){return false;}
	/**
	 * AC実効容量（充電）<br>
	 * EPC                 : 0xA0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
	 * <br>
	 * 空の蓄電池より充電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcEffectiveCapacityCharging(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC実効容量（放電）<br>
	 * EPC                 : 0xA1<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※1:実効容量の定義に関しては、現在時点の実効容量とする。なお、実効容量の計算に関しては、機器毎に計算仕様が異なり本仕様書制作時点においては共通化していない。本プロパティ利用者は、機器毎の情報を別途入手することを推奨する。<br>
	 * <br>
	 * 満充電の蓄電池より放電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcEffectiveCapacityDischarging(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC 充電可能容量<br>
	 * EPC                 : 0xA2<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常時において、充電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcChargiableCapacity(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC 放電可能容量<br>
	 * EPC                 : 0xA3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 通常時において、放電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcDischargiableCapacity(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC 充電可能量<br>
	 * EPC                 : 0xA4<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現時点での充電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcChargiableElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC 放電可能量<br>
	 * EPC                 : 0xA5<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現時点での放電可能な電力量（AC）<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcDischargiableElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC 充電上限設定<br>
	 * EPC                 : 0xA6<br>
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
	 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcChargeUpperLimitSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * AC 放電下限設定<br>
	 * EPC                 : 0xA7<br>
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
	 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcDischargeLowerLimitSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * AC積算充電電力量計測値<br>
	 * EPC                 : 0xA8<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算充電電力量（ AC ） を0.001kWh で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcMeasuredCumulativeChargingElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC積算放電電力量計測値<br>
	 * EPC                 : 0xA9<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算放電電力量（ AC ） を0.001kWh で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcMeasuredCumulativeDischargingElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC 充電量設定値<br>
	 * EPC                 : 0xAA<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電力量（AC）をWh で指定する<br>
	 * 0x00000000: 未設定<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcChargeAmountSettingValue(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * AC 放電量設定値<br>
	 * EPC                 : 0xAB<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電力量（AC）をWh で指定する<br>
	 * 0x00000000: 未設定<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcDischargeAmountSettingValue(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 最小最大充電電力値<br>
	 * EPC                 : 0xC8<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池への充電電力の最小値および最大値を、それぞれW (AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * 最小充電電力値：最大充電電力値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxChargingElectricPower(String edt){
	    if(edt == null || !(edt.length() == 8 * 2)) return false;
	    String min = edt.substring(0, 8);
	    String max = edt.substring(8, 16);
	    if(! (((ElUtil.compElUnsignedLong(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedLong("00000000", min ) <= 0 || ElUtil.compElUnsignedLong(min , "FFFFFFFF") == 0) &&
	            (ElUtil.compElUnsignedLong(max , "3B9AC9FF") <= 0 || ElUtil.compElUnsignedLong(max , "FFFFFFFF") == 0))))
	    {
	        return false;
	    }
	    return true;
	}
	/**
	 * 最小最大放電電力値<br>
	 * EPC                 : 0xC9<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池からの放電電力の最小値および最大値を、それぞれW(AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * 最小放電電力値：最大放電電力値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxDischargingElectricPower(String edt){
		if(edt == null || !(edt.length() == 8 * 2)) return false;
	    String min = edt.substring(0, 8);
	    String max = edt.substring(8, 16);
	    if(! (((ElUtil.compElUnsignedLong(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedLong("00000000", min ) <= 0 || ElUtil.compElUnsignedLong(min , "FFFFFFFF") == 0) &&
	            (ElUtil.compElUnsignedLong(max , "3B9AC9FF") <= 0 || ElUtil.compElUnsignedLong(max , "FFFFFFFF") == 0))))
	    {
	        return false;
	    }
		return true;
	}
	/**
	 * 最小最大充電電流値<br>
	 * EPC                 : 0xCA<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池への充電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
	 * 0x0000～0x7FFE（0～3,276.6A）<br>
	 * 最小充電電流値：最大充電電流値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxChargingCurrent(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
	    String min = edt.substring(0, 4);
	    String max = edt.substring(4, 8);
	    if(! (((ElUtil.compElUnsignedShort(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedShort("0000", min ) <= 0 || ElUtil.compElUnsignedShort(min , "FFFF") == 0) &&
	            (ElUtil.compElUnsignedShort(max , "7FFE") <= 0 || ElUtil.compElUnsignedShort(max , "FFFF") == 0))))
	    {
	        return false;
	    }
		return true;
	}
	/**
	 * 最小最大放電電流値<br>
	 * EPC                 : 0xCB<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A(AC)で示す<br>
	 * 0x0000～0x7FFE（0～3,276.6A）<br>
	 * 最小放電電流値：最大放電電流値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxDischargingCurrent(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
	    String min = edt.substring(0, 4);
	    String max = edt.substring(4, 8);
	    if(! (((ElUtil.compElUnsignedShort(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedShort("0000", min ) <= 0 || ElUtil.compElUnsignedShort(min , "FFFF") == 0) &&
	            (ElUtil.compElUnsignedShort(max , "7FFE") <= 0 || ElUtil.compElUnsignedShort(max , "FFFF") == 0))))
	    {
	        return false;
	    }
		return true;
	}
	/**
	 * 再連系許可設定<br>
	 * EPC                 : 0xCC<br>
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
	 * 系統への連系の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtReInterconnectionPermissionSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_RE_INTERCONNECTION_PERMISSION_SETTING_PERMITTED)
				&& ! edt.equalsIgnoreCase(EDT_RE_INTERCONNECTION_PERMISSION_SETTING_PROHIBITED)) return false;
		return true;
	}
	/**
	 * 運転許可設定<br>
	 * EPC                 : 0xCD<br>
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
	 * 蓄電池運転の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOperationPermissionSetting(String edt){
		if(edt == null || !(edt.length() == 1* 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_OPERATION_PERMISSION_SETTING_PERMITTED)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_PERMISSION_SETTING_PROHIBITED)) return false;
		return true;
	}
	/**
	 * 自立運転許可設定<br>
	 * EPC                 : 0xCE<br>
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
	 * 蓄電池の自立運転の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIndependentOperationPermissionSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_INDEPENDENT_OPERATION_PERMISSION_SETTING_PREMITTED)
				&& ! edt.equalsIgnoreCase(EDT_INDEPENDENT_OPERATION_PERMISSION_SETTING_PROHIBITED)) return false;
		return true;
	}
	/**
	 * 運転動作状態<br>
	 * EPC                 : 0xCF<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の動作状態を示す。充電状態、放電状態、待機状態の各運転動作状態は必須とする。<br>
	 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtWorkingOperationStatus(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_RAPID_CHARGING)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_CHARGING)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_DISCHARGING)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_STANDBY)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_TEST)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_AUTOMATIC)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_RESTART)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_EFFECTIVE_CAPACITY_RECALCULATION_PROCESSING)
				&& ! edt.equalsIgnoreCase(EDT_WORKING_OPERATION_STATUS_OTHER)
				) return false;

		return true;
	}
	/**
	 * AC 定格電力量<br>
	 * EPC                 : 0xC7<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格電力量をWh（AC）で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAcRatedElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 定格電力量<br>
	 * EPC                 : 0xD0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格電力量をWh (DC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRatedElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 定格容量<br>
	 * EPC                 : 0xD1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格充電容量を0.1Ah(DC)で示す<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRatedCapacity(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
		return true;
	}
	/**
	 * 定格電圧<br>
	 * EPC                 : 0xD2<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の定格電圧をV (DC)で示す<br>
	 * 0x0000～0x7FFE（0～32,766V）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRatedVoltage(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
		return true;
	}
	/**
	 * 瞬時充放電電力計測値<br>
	 * EPC                 : 0xD3<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時充放電電力を±W (AC)で示す<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999W）：充電時（プラス値） 、<br>
	 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999W）：放電時（マイナス値）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
//	protected boolean isValidEdtMeasuredInstantChargeDischargeElectricPower(String edt){
//		if(edt == null || !(edt.length() == 4 * 2)) return false;
//		if(! (ElUtil.compElSignedLong("00000001", edt ) <= 0 && ElUtil.compElSignedLong(edt , "3B9AC9FF") <= 0
//				|| ElUtil.compElSignedLong("FFFFFFFF", edt ) <= 0 && ElUtil.compElSignedLong(edt , "C4653601") <= 0
//				|| ElUtil.compElSignedLong("00000000", edt ) == 0
//				|| ElUtil.compElSignedLong("7FFFFFFF", edt ) == 0
//				|| ElUtil.compElSignedLong("80000000", edt ) == 0
//				)) return false;
//		return true;
//	}
	
	//changed 20220325 to support negative values as well.
	protected boolean isValidEdtMeasuredInstantChargeDischargeElectricPower(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		int value = (int)Long.parseLong(edt, 16);

		if (value < 0) {
			edt = Integer.toHexString((-1 * value));
		}
		if(! (ElUtil.compElSignedLong("00000001", edt ) <= 0 && ElUtil.compElSignedLong(edt , "3B9AC9FF") <= 0
				|| ElUtil.compElSignedLong("FFFFFFFF", edt ) <= 0 && ElUtil.compElSignedLong(edt , "C4653601") <= 0
				|| ElUtil.compElSignedLong("00000000", edt ) == 0
				|| ElUtil.compElSignedLong("7FFFFFFF", edt ) == 0
				|| ElUtil.compElSignedLong("80000000", edt ) == 0
				)) return false;
		return true;
	}
	
	/**
	 * 瞬時充放電電流計測値<br>
	 * EPC                 : 0xD4<br>
	 * データタイプ        : signed short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時充放電電流を±0.1A (AC)で示す<br>
	 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6A）：充電時（プラス値）、<br>
	 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7A）：放電時（マイナス値）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredInstantChargeDischargeElectricCurrent(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElSignedShort("0001", edt ) <= 0 && ElUtil.compElSignedShort(edt , "7FFE") <= 0
				|| ElUtil.compElSignedShort("FFFF", edt ) <= 0 && ElUtil.compElSignedShort(edt , "8001") <= 0
				|| ElUtil.compElSignedShort("0000", edt ) == 0
				|| ElUtil.compElSignedShort("7FFF", edt ) == 0
				|| ElUtil.compElSignedShort("8000", edt ) == 0
				)) return false;
		return true;
	}
	/**
	 * 瞬時充放電電圧計測値<br>
	 * EPC                 : 0xD5<br>
	 * データタイプ        : signed short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時充放電電圧を±V (AC)で示す<br>
	 * 0x0001 ～ 0x7FFE （ 1 ～32,766V）：充電時（プラス値）、<br>
	 * 0xFFFF ～ 0x8001 （ － 1 ～ －32,767V）：放電時（マイナス値）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredInstantChargeDischargeElectricVoltage(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElSignedShort("0001", edt ) <= 0 && ElUtil.compElSignedShort(edt , "7FFE") <= 0
				|| ElUtil.compElSignedShort("FFFF", edt ) <= 0 && ElUtil.compElSignedShort(edt , "8001") <= 0
				|| ElUtil.compElSignedShort("0000", edt ) == 0
				|| ElUtil.compElSignedShort("7FFF", edt ) == 0
				|| ElUtil.compElSignedShort("8000", edt ) == 0
				)) return false;
		return true;
	}
	/**
	 * 積算放電電力量計測値<br>
	 * EPC                 : 0xD6<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算放電電力量を0.001kWhで示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeDischargingElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 積算放電電力量リセット設定<br>
	 * EPC                 : 0xD7<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : -<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算放電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeDischargingElectricEnergyResetSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_MEASURED_CUMULATIVE_DISCHARGING_ELECTRIC_ENERGY_RESET_SETTING_RESET)) return false;
		return true;
	}
	/**
	 * 積算充電電力量計測値<br>
	 * EPC                 : 0xD8<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.001 kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算充電電力量を0.001kWhで示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999.999kWh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeChargingElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 積算充電電力量リセット設定<br>
	 * EPC                 : 0xD9<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : -<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算充電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeChargingElectricEnergyResetSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_MEASURED_CUMULATIVE_CHARGING_ELECTRIC_ENERGY_RESET_SETTING_RESET)) return false;
				return true;
	}
	/**
	 * 運転モード設定<br>
	 * EPC                 : 0xDA<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
	 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOperationModeSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_RAPID_CHARGING)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_CHARGING)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_DISCHARGING)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_STANDBY)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_TEST)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_AUTO)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_RESTART)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_EFFECTIVE_CAPACITY_RECALCULATION_PROCESSING)
				&& ! edt.equalsIgnoreCase(EDT_OPERATION_MODE_SETTING_OTHERS)
				) return false;
		return true;
	}
	/**
	 * 系統連系状態<br>
	 * EPC                 : 0xDB<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 系統連系状態のタイプを示す。<br>
	 * 系統連系（逆潮流可）＝0x00<br>
	 * 独立＝0x01<br>
	 * 系統連系（逆潮流不可）＝0x02<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSystemInterconnectedType(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_SYSTEM_INTERCONNECTED_TYPE_SYSTEM_INTERCONNECTION_REVERSE_POWER_FLOW_ACCEPTABLE)
				&& ! edt.equalsIgnoreCase(EDT_SYSTEM_INTERCONNECTED_TYPE_INDEPENDENT_TYPE)
				&& ! edt.equalsIgnoreCase(EDT_SYSTEM_INTERCONNECTED_TYPE_SYSTEM_INTERCONNECTION_REVERSE_POWER_FLOW_NOT_ACCEPTABLE)
				) return false;

		return true;
	}
	/**
	 * 最小最大充電電力値（独立時）<br>
	 * EPC                 : 0xDC<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池への充電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小充電電力値：最大充電電力値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxChargingElectricPowerIndipendent(String edt){
		if(edt == null || !(edt.length() == 8 * 2)) return false;
	    String min = edt.substring(0, 8);
	    String max = edt.substring(8, 16);
	    if(! (((ElUtil.compElUnsignedLong(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedLong("00000000", min ) <= 0 || ElUtil.compElUnsignedLong(min , "FFFFFFFF") == 0) &&
	            (ElUtil.compElUnsignedLong(max , "3B9AC9FF") <= 0 || ElUtil.compElUnsignedLong(max , "FFFFFFFF") == 0))))
	    {
	        return false;
	    }
		return true;
	}
	/**
	 * 最小最大放電電力値（独立時）<br>
	 * EPC                 : 0xDD<br>
	 * データタイプ        : unsigned long×2<br>
	 * サイズ              : 8 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池からの放電電力の最小値および最大値を、それぞれＷ (AC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）最小放電電力値：最大放電電力値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxDischargingElectricPowerIndipendent(String edt){
		if(edt == null || !(edt.length() == 8 * 2)) return false;
	    String min = edt.substring(0, 8);
	    String max = edt.substring(8, 16);
	    if(! (((ElUtil.compElUnsignedLong(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedLong("00000000", min ) <= 0 || ElUtil.compElUnsignedLong(min , "FFFFFFFF") == 0) &&
	            (ElUtil.compElUnsignedLong(max , "3B9AC9FF") <= 0 || ElUtil.compElUnsignedLong(max , "FFFFFFFF") == 0))))
	    {
	        return false;
	    }
		return true;
	}
	/**
	 * 最小最大充電電流値（独立時）<br>
	 * EPC                 : 0xDE<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池への充電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
	 * 0x0000～0x7FFE（0～3,276.6A）<br>
	 * 最小充電電流値：最大充電電流値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxChargingCurrentIndipendent(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
	    String min = edt.substring(0, 4);
	    String max = edt.substring(4, 8);
	    if(! (((ElUtil.compElUnsignedShort(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedShort("0000", min ) <= 0 || ElUtil.compElUnsignedShort(min , "FFFF") == 0) &&
	            (ElUtil.compElUnsignedShort(max , "7FFE") <= 0 || ElUtil.compElUnsignedShort(max , "FFFF") == 0))))
	    {
	        return false;
	    }
		return true;
	}
	/**
	 * 最小最大放電電流値（独立時）<br>
	 * EPC                 : 0xDF<br>
	 * データタイプ        : unsigned short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池からの放電電流の最小値および最大値を、それぞれ0.1A (AC)で示す<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMinMaxDischargingCurrentIndipendent(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
	    String min = edt.substring(0, 4);
	    String max = edt.substring(4, 8);
	    if(! (((ElUtil.compElUnsignedShort(min, max ) <= 0) &&
	            (ElUtil.compElUnsignedShort("0000", min ) <= 0 || ElUtil.compElUnsignedShort(min , "FFFF") == 0) &&
	            (ElUtil.compElUnsignedShort(max , "7FFE") <= 0 || ElUtil.compElUnsignedShort(max , "FFFF") == 0))))
	    {
	        return false;
	    }
		return true;
	}
	/**
	 * 充放電量設定値1<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
	 * <br>
	 * 充電/放電の電力量を±Wh (DC)で指定する<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
	 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtChargingDischargingAmountSetting1(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElSignedLong("00000001", edt ) <= 0 && ElUtil.compElSignedLong(edt , "3B9AC9FF") <= 0
				|| ElUtil.compElSignedLong("FFFFFFFF", edt ) <= 0 && ElUtil.compElSignedLong(edt , "C4653601") <= 0)) return false;
		return true;
	}
	/**
	 * 充放電量設定値2<br>
	 * EPC                 : 0xE1<br>
	 * データタイプ        : signed short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
	 * <br>
	 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
	 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
	 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtChargingDischargingAmountSetting2(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElSignedShort("0001", edt ) <= 0 && ElUtil.compElSignedShort(edt , "7FFE") <= 0
				|| ElUtil.compElSignedShort("FFFF", edt ) <= 0 && ElUtil.compElSignedShort(edt , "8001") <= 0)) return false;
		return true;
	}
	/**
	 * 蓄電残量1<br>
	 * EPC                 : 0xE2<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電残量をWh (DC)で示す<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRemainingStoredElectricity1(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 蓄電残量2<br>
	 * EPC                 : 0xE3<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電残量を0.1Ah (DC)で示す<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRemainingStoredElectricity2(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
		return true;
	}
	/**
	 * 蓄電残量3<br>
	 * EPC                 : 0xE4<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※2:蓄電残量1、蓄電残量2、蓄電残量3 はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電残量を%で示す<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRemainingStoredElectricity3(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}

	/**
	 * 劣化状態<br>
	 * EPC                 : 0xE5<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 蓄電池の劣化状態（健康状態）を%で示す<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtBatteryStateOfHealth(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * 蓄電池タイプ<br>
	 * EPC                 : 0xE6<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電池の種類を示す<br>
	 * タイプ=0x00～0xFF<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtBatteryType(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 充電量設定値1<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 充電の電力量をWh (DC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtChargingAmountSetting1(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 放電量設定値1<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 放電の電力量をWh (DC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDischargingAmountSetting1(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 充電量設定値2<br>
	 * EPC                 : 0xE9<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 充電の容量を0.1Ah (DC)で指定する<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtChargingAmountSetting2(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
		return true;
	}
	/**
	 * 放電量設定値2<br>
	 * EPC                 : 0xEA<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 放電の容量を0.1Ah (DC)で指定する<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDischargingAmountSetting2(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
		return true;
	}
	/**
	 * 充電電力設定値<br>
	 * EPC                 : 0xEB<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電力をW (AC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtChargingElectricPowerSetting(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 放電電力設定値<br>
	 * EPC                 : 0xEC<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電力をW (AC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDischargingElectricPowerSetting(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 充電電流設定値<br>
	 * EPC                 : 0xED<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電流を0.1A (AC)で指定する<br>
	 * 0x0000～0xFFFD（0～6,553.3A）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtChargingCurrentSetting(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 放電電流設定値<br>
	 * EPC                 : 0xEE<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電流を0.1A (AC)で指定する<br>
	 * 0x0000～0xFFFD（0～6,553.3A）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDischargingCurrentSetting(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 定格電圧（独立時）<br>
	 * EPC                 : 0xEF<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の蓄電池の定格電圧をV(AC)で示す<br>
	 * 0x0000～0x7FFE（0～32,766V）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRatedVoltageIndependent(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
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
	 * Set                 : オプション<br>
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
	 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
	 * 現在時刻設定<br>
	 * EPC                 : 0x97<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00～0x17：0x00～0x3B（＝0～23）：（＝0～59）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
//	protected boolean setToAppCurrentTimeSetting(String edt){return false;}
	/**
	 * 現在年月日設定<br>
	 * EPC                 : 0x98<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
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
//	protected boolean setToAppCurrentYmdSetting(String edt){return false;}
	/**
	 * AC 充電上限設定<br>
	 * EPC                 : 0xA6<br>
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
	 * 充電可能容量に対して、充電の上限値を％で設定する（AC）<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppAcChargeUpperLimitSetting(String edt){return false;}
	/**
	 * AC 放電下限設定<br>
	 * EPC                 : 0xA7<br>
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
	 * 放電可能容量に対して、放電の下限値を％で設定する（AC）<br>
	 * 0x00～0x64 (0～100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppAcDischargeLowerLimitSetting(String edt){return false;}
	/**
	 * AC 充電量設定値<br>
	 * EPC                 : 0xAA<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電力量（AC）をWh で指定する<br>
	 * 0x00000000: 未設定<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppAcChargeAmountSettingValue(String edt){return false;}
	/**
	 * AC 放電量設定値<br>
	 * EPC                 : 0xAB<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : 必須<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電力量（AC）をWh で指定する<br>
	 * 0x00000000: 未設定<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppAcDischargeAmountSettingValue(String edt){return false;}
	/**
	 * 再連系許可設定<br>
	 * EPC                 : 0xCC<br>
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
	 * 系統への連系の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppReInterconnectionPermissionSetting(String edt){return false;}
	/**
	 * 運転許可設定<br>
	 * EPC                 : 0xCD<br>
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
	 * 蓄電池運転の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOperationPermissionSetting(String edt){return false;}
	/**
	 * 自立運転許可設定<br>
	 * EPC                 : 0xCE<br>
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
	 * 蓄電池の自立運転の許可、禁止を設定する。<br>
	 * 許可 = 0x41、禁止 = 0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIndependentOperationPermissionSetting(String edt){return false;}
	/**
	 * 積算放電電力量リセット設定<br>
	 * EPC                 : 0xD7<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : -<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算放電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppMeasuredCumulativeDischargingElectricEnergyResetSetting(String edt){return false;}
	/**
	 * 積算充電電力量リセット設定<br>
	 * EPC                 : 0xD9<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : -<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算充電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppMeasuredCumulativeChargingElectricEnergyResetSetting(String edt){return false;}
	/**
	 * 運転モード設定<br>
	 * EPC                 : 0xDA<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * Get                 : 必須(※6:プロパティ内容のうち、充電、放電、待機の各運転モードについては、搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 急速充電/充電/放電/待機/テスト/自動/その他の運転モードを設定する。充電、放電、待機の各運転モードを必須とする。<br>
	 * 急速充電＝0x41, 充電＝0x42, 放電＝0x43, 待機＝0x44, テスト=0x45, 自動＝ 0x46, 再起動＝0x48, 実効容量再計算処理＝0x49, その他＝0x40<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOperationModeSetting(String edt){return false;}
	/**
	 * 充放電量設定値1<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
	 * <br>
	 * 充電/放電の電力量を±Wh (DC)で指定する<br>
	 * 0x00000001 ～ 0x3B9AC9FF（ 1～999,999,999Wh）：充電時（プラス値）、<br>
	 * 0xFFFFFFFF ～0xC4653601 ( － 1 ～ －999,999,999Wh）：放電時（マイナス値）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppChargingDischargingAmountSetting1(String edt){return false;}
	/**
	 * 充放電量設定値2<br>
	 * EPC                 : 0xE1<br>
	 * データタイプ        : signed short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※3:充放電量設定値１（または２）を使用する場合は、充放電量設定値２（または１）、充電量設定値１、放電量設定値１、充電量設定値２、放電量設定値２を使用しない。<br>
	 * <br>
	 * 充電/放電の容量を±0.1Ah (DC)で指定する<br>
	 * 0x0001 ～ 0x7FFE （ 0.1 ～3,276.6Ah）：充電時（プラス値）、<br>
	 * 0xFFFF ～ 0x8001 （ － 0.1 ～ －3,276.7Ah）：放電時（マイナス値）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppChargingDischargingAmountSetting2(String edt){return false;}
	/**
	 * 充電量設定値1<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 充電の電力量をWh (DC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppChargingAmountSetting1(String edt){return false;}
	/**
	 * 放電量設定値1<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : Wh<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 放電の電力量をWh (DC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999Wh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppDischargingAmountSetting1(String edt){return false;}
	/**
	 * 充電量設定値2<br>
	 * EPC                 : 0xE9<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※4:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 充電の容量を0.1Ah (DC)で指定する<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppChargingAmountSetting2(String edt){return false;}
	/**
	 * 放電量設定値2<br>
	 * EPC                 : 0xEA<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 Ah<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※5:充電量設定値１（または２）を使用する場合は、充電量設定値２（または１）、充放電量設定値１、２を使用しない。<br>
	 * <br>
	 * 放電の容量を0.1Ah (DC)で指定する<br>
	 * 0x0000 ～ 0x7FFE （ 0 ～3,276.6Ah）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppDischargingAmountSetting2(String edt){return false;}
	/**
	 * 充電電力設定値<br>
	 * EPC                 : 0xEB<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電力をW (AC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppChargingElectricPowerSetting(String edt){return false;}
	/**
	 * 放電電力設定値<br>
	 * EPC                 : 0xEC<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電力をW (AC)で指定する<br>
	 * 0x00000000 ～ 0x3B9AC9FF（ 0～999,999,999W）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppDischargingElectricPowerSetting(String edt){return false;}
	/**
	 * 充電電流設定値<br>
	 * EPC                 : 0xED<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 充電の電流を0.1A (AC)で指定する<br>
	 * 0x0000～0xFFFD（0～6,553.3A）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppChargingCurrentSetting(String edt){return false;}
	/**
	 * 放電電流設定値<br>
	 * EPC                 : 0xEE<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 放電の電流を0.1A (AC)で指定する<br>
	 * 0x0000～0xFFFD（0～6,553.3A）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppDischargingCurrentSetting(String edt){return false;}


	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80","動作状態");
			put("83","識別番号");
			put("97","現在時刻設定");
			put("98","現在年月日設定");
			put("A0","AC実効容量（充電）");
			put("A1","AC実効容量（放電）");
			put("A2","AC 充電可能容量");
			put("A3","AC 放電可能容量");
			put("A4","AC 充電可能量");
			put("A5","AC 放電可能量");
			put("A6","AC 充電上限設定");
			put("A7","AC 放電下限設定");
			put("A8","AC積算充電電力量計測値");
			put("A9","AC積算放電電力量計測値");
			put("AA","AC 充電量設定値");
			put("AB","AC 放電量設定値");
			put("C8","最小最大充電電力値");
			put("C9","最小最大放電電力値");
			put("CA","最小最大充電電流値");
			put("CB","最小最大放電電流値");
			put("CC","再連系許可設定");
			put("CD","運転許可設定");
			put("CE","自立運転許可設定");
			put("CF","運転動作状態");
			put("C7","AC 定格電力量");
			put("D0","定格電力量");
			put("D1","定格容量");
			put("D2","定格電圧");
			put("D3","瞬時充放電電力計測値");
			put("D4","瞬時充放電電流計測値");
			put("D5","瞬時充放電電圧計測値");
			put("D6","積算放電電力量計測値");
			put("D7","積算放電電力量リセット設定");
			put("D8","積算充電電力量計測値");
			put("D9","積算充電電力量リセット設定");
			put("DA","運転モード設定");
			put("DB","系統連系状態");
			put("DC","最小最大充電電力値（独立時）");
			put("DD","最小最大放電電力値（独立時）");
			put("DE","最小最大充電電流値（独立時）");
			put("DF","最小最大放電電流値（独立時）");
			put("E0","充放電量設定値1");
			put("E1","充放電量設定値2");
			put("E2","蓄電残量1");
			put("E3","蓄電残量2");
			put("E4","蓄電残量3");
			put("E5","劣化状態");
			put("E6","蓄電池タイプ");
			put("E7","充電量設定値1");
			put("E8","放電量設定値1");
			put("E9","充電量設定値2");
			put("EA","放電量設定値2");
			put("EB","充電電力設定値");
			put("EC","放電電力設定値");
			put("ED","充電電流設定値");
			put("EE","放電電流設定値");
			put("EF","定格電圧（独立時）");
		}
	};
}
