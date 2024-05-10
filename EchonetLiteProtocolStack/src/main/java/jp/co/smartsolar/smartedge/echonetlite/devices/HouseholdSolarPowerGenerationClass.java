package jp.co.smartsolar.smartedge.echonetlite.devices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

/**
 * 住宅用太陽光発電クラス
 */
public class HouseholdSolarPowerGenerationClass extends DeviceObjectSuperClass{

	/**
	 * ログ出力用タグ
	 */
	public static final String TAG = "HouseholdSolarPowerGenerationClass";

	/**
	 *  クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "02";
	/**
	 *  クラスコード
	 */
	public static final String CLASS_CODE = "79";

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
	 * EPC : 出力制御設定１
	 */
	public static final String EPC_OUTPUT_LIMIT_SETTING_1 = "A0";

	/**
	 * EPC : 出力制御設定２
	 */
	public static final String EPC_OUTPUT_LIMIT_SETTING_2 = "A1";

	/**
	 * EPC : 余剰買取制御機能設定
	 */
	public static final String EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING = "A2";
	/**
	 * EDT : 余剰買取制御機能設定（有効）
	 */
	public static final String EDT_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING_ENABLED = "41";
	/**
	 * EDT : 余剰買取制御機能設定（無効）
	 */
	public static final String EDT_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING_DISABLED = "42";

	/**
	 * EPC : 出力制御スケジュール
	 */
	public static final String EPC_OUTPUT_CONTROL_SCHEDULE = "B0";

	/**
	 * EPC : 次回アクセス日時
	 */
	public static final String EPC_NEXT_ACCESS_DATE_TIME = "B1";

	/**
	 * EPC : 余剰買取制御機能タイプ
	 */
	public static final String EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE = "B2";
	/**
	 * EDT : 余剰買取制御機能タイプ（有効）
	 */
	public static final String EDT_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE_ENABLED = "41";
	/**
	 * EDT : 余剰買取制御機能タイプ（無効）
	 */
	public static final String EDT_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE_DISABLED = "42";

	/**
	 * EPC : 出力変化時間設定値
	 */
	public static final String EPC_OUTPUT_CHANGE_TIME_SET_VALUE = "B3";

	/**
	 * EPC : 上限クリップ設定値
	 */
	public static final String EPC_UPPER_LIMIT_CLIP_SETTING_VALUE = "B4";

	/**
	 * EPC : 運転力率設定値
	 */
	public static final String EPC_DRIVING_POWER_FACTOR_SET_VALUE = "C0";

	/**
	 * EPC : FIT 契約タイプ
	 */
	public static final String EPC_FIT_CONTRACT_TYPE = "C1";
	/**
	 * EDT : FIT 契約タイプ（FIT）
	 */
	public static final String EDT_FIT_CONTRACT_TYPE_FIT = "41";
	/**
	 * EDT : FIT 契約タイプ（非FIT）
	 */
	public static final String EDT_FIT_CONTRACT_TYPE_NOT_FIT = "42";
	/**
	 * EDT : FIT 契約タイプ（未設定）
	 */
	public static final String EDT_FIT_CONTRACT_TYPE_UNSET = "43";

	/**
	 * EPC : 自家消費タイプ
	 */
	public static final String EPC_SELF_CONSUMPTION_TYPE = "C2";
	/**
	 * EDT : 自家消費タイプ（自家消費有）
	 */
	public static final String EDT_SELF_CONSUMPTION_TYPE_YES = "41";
	/**
	 * EDT : 自家消費タイプ（自家消費無）
	 */
	public static final String EDT_SELF_CONSUMPTION_TYPE_NO = "42";
	/**
	 * EDT : 自家消費タイプ（不明）
	 */
	public static final String EDT_SELF_CONSUMPTION_TYPE_UNKNOWN = "43";

	/**
	 * EPC : 設備認定容量
	 */
	public static final String EPC_EQUIPMENT_CERTIFICATION_CAPACITY = "C3";

	/**
	 * EPC : 換算係数
	 */
	public static final String EPC_CONVERSION_FACTOR = "C4";

	/**
	 * EPC : 系統連系状態
	 */
	public static final String EPC_SYSTEM_INTERCONNECTED_TYPE = "D0";
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
	 * EDT : 系統連系状態（不明）
	 */
	public static final String EDT_SYSTEM_INTERCONNECTED_TYPE_UNKNOWN= "03";

	/**
	 * EPC : 出力抑制状態
	 */
	public static final String EPC_OUTPUT_SUPPRESSION_STATSU = "D1";
	/**
	 * EDT : 出力抑制状態（抑制中(出力制御)）
	 */
	public static final String EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSING_OUTPUT_CONTROL = "41";
	/**
	 * EDT : 出力抑制状態（抑制中(出力制御以外)）
	 */
	public static final String EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSING_OTHER_THAN_THE_OUTPUT_CONTROL = "42";
	/**
	 * EDT : 出力抑制状態（抑制中(抑制要因不明)）
	 */
	public static final String EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSING_RESTRAINTS_UNKNOWN = "43";
	/**
	 * EDT : 出力抑制状態（抑制未実施）
	 */
	public static final String EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSION_UNEXECUTED = "44";
	/**
	 * EDT : 出力抑制状態（不明）
	 */
	public static final String EDT_OUTPUT_SUPPRESSION_STATSU_UNKNOWN = "45";

	/**
	 * EPC : 瞬時発電電力計測値
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED = "E0";

	/**
	 * EPC : 積算発電電力量計測値
	 */
	public static final String EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED = "E1";

	/**
	 * EPC : 積算発電電力量リセット設定
	 */
	public static final String EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED = "E2";
	/**
	 * EDT : 積算発電電力量リセット設定（リセット）
	 */
	public static final String EDT_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED_RESET = "00";

	/**
	 * EPC : 積算売電電力量計測値
	 */
	public static final String EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD = "E3";

	/**
	 * EPC : 積算売電電力量リセット設定
	 */
	public static final String EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD = "E4";
	/**
	 * EDT : 積算売電電力量リセット設定（リセット）
	 */
	public static final String EDT_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD_RESET = "00";

	/**
	 * EPC : 発電電力制限設定１
	 */
	public static final String EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1 = "E5";

	/**
	 * EPC : 発電電力制限設定２
	 */
	public static final String EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2 = "E6";

	/**
	 * EPC : 売電電力制限設定
	 */
	public static final String EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD = "E7";

	/**
	 * EPC : 定格発電電力値（系統連系時）
	 */
	public static final String EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED= "E8";

	/**
	 * EPC : 定格発電電力値（独立時）
	 */
	public static final String EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED= "E9";



	/**
	 * コンストラクタ
	 */
	public HouseholdSolarPowerGenerationClass() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
	}
	/**
	 * コンストラクタ
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public HouseholdSolarPowerGenerationClass(String entityCode) {
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
		this.addMapGetProps(EPC_OUTPUT_LIMIT_SETTING_1);
		this.addMapGetProps(EPC_OUTPUT_LIMIT_SETTING_2);
		this.addMapGetProps(EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING);
		this.addMapGetProps(EPC_OUTPUT_CONTROL_SCHEDULE);
		this.addMapGetProps(EPC_NEXT_ACCESS_DATE_TIME);
		this.addMapGetProps(EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE);
//		this.addMapGetProps(EPC_OUTPUT_CHANGE_TIME_SET_VALUE); // 必須ではない
		this.addMapGetProps(EPC_UPPER_LIMIT_CLIP_SETTING_VALUE);
//		this.addMapGetProps(EPC_DRIVING_POWER_FACTOR_SET_VALUE); // 必須ではない
		this.addMapGetProps(EPC_FIT_CONTRACT_TYPE);
		this.addMapGetProps(EPC_SELF_CONSUMPTION_TYPE);
		this.addMapGetProps(EPC_EQUIPMENT_CERTIFICATION_CAPACITY);
		this.addMapGetProps(EPC_CONVERSION_FACTOR);
		this.addMapGetProps(EPC_SYSTEM_INTERCONNECTED_TYPE);
		this.addMapGetProps(EPC_OUTPUT_SUPPRESSION_STATSU);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED);
		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED);
//		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD); // 必須ではない
//		this.addMapGetProps(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1); // 必須ではない
//		this.addMapGetProps(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2); // 必須ではない
//		this.addMapGetProps(EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD); // 必須ではない
		this.addMapGetProps(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED);
//		this.addMapGetProps(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED); // 必須ではない


//		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
//		this.addMapSetProps(EPC_CURRENT_TIME_SETTING); // 必須ではない
//		this.addMapSetProps(EPC_CURRENT_YMD_SETTING); // 必須ではない
		this.addMapSetProps(EPC_OUTPUT_LIMIT_SETTING_1);
		this.addMapSetProps(EPC_OUTPUT_LIMIT_SETTING_2);
		this.addMapSetProps(EPC_FIT_CONTRACT_TYPE);
//		this.addMapSetProps(EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED); // 必須ではない
//		this.addMapSetProps(EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD); // 必須ではない
//		this.addMapSetProps(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1); // 必須ではない
//		this.addMapSetProps(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2); // 必須ではない
//		this.addMapSetProps(EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD); // 必須ではない
//		this.addMapSetProps(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED); // 必須ではない
//		this.addMapSetProps(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED); // 必須ではない


		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
		this.addMapConvAnnounceProps(EPC_NEXT_ACCESS_DATE_TIME);


	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewHouseholdSolarPowerGenerationFound(this);
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
		private static final String TAG = "HouseholdSolarPowerGenerationClass.ElSetProps";

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
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00~0x17：0x00~0x3B<br>
		 * (=0~23)：(=0~59)<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetCurrentTimeSetting(String strEdt){
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1~0x270F：1~0x0C：1~0x1F<br>
		 * (=1~9999)：(=1~12)：(=1~31)<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetCurrentYmdSetting(String strEdt){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING, strEdt));
			return this;
		}
		/**
		 * 発電電力制限設定１<br>
		 * EPC                 : 0xE5<br>
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
		 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetOutputLimitSetting1(String strEdt){
			listProperty.add(new ElProp(EPC_OUTPUT_LIMIT_SETTING_1, strEdt));
			return this;
		}
		/**
		 * 出力制御設定２<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetOutputLimitSetting2(String strEdt){
			listProperty.add(new ElProp(EPC_OUTPUT_LIMIT_SETTING_2, strEdt));
			return this;
		}
		/**
		 * 余剰買取制御機能タイプ<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetSurplusPurchaseControlFunctinoSetting(String strEdt){
			listProperty.add(new ElProp(EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING, strEdt));
			return this;
		}
		/**
		 * FIT 契約タイプ<br>
		 * EPC                 : 0xC1<br>
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
		 * FIT 契約のタイプを示す<br>
		 * FIT=0x41<br>
		 * 非FIT=0x42<br>
		 * 未設定=0x43<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetFitContractType(String strEdt){
			listProperty.add(new ElProp(EPC_FIT_CONTRACT_TYPE, strEdt));
			return this;
		}
		/**
		 * 積算発電電力量リセット設定<br>
		 * EPC                 : 0xE2<br>
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
		 * 0x00 を書き込むことにより積算発電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetResettingCumulativeAmountOfEnergyGeneragted(String strEdt){
			listProperty.add(new ElProp(EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED, strEdt));
			return this;
		}
		/**
		 * 積算売電電力量リセット設定<br>
		 * EPC                 : 0xE4<br>
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
		 * 0x00 を書き込むことにより積算買電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetResettingCumulativeAmountOfElectricEnergySold(String strEdt){
			listProperty.add(new ElProp(EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD, strEdt));
			return this;
		}
		/**
		 * 発電電力制限設定１<br>
		 * EPC                 : 0xE5<br>
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
		 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetPowerGenerationOutputLimitSetting1(String strEdt){
			listProperty.add(new ElProp(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1, strEdt));
			return this;
		}
		/**
		 * 発電電力制限設定２<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 発電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetPowerGenerationOutputLimitSetting2(String strEdt){
			listProperty.add(new ElProp(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2, strEdt));
			return this;
		}
		/**
		 * 売電電力制限設定<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 売電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetLimitSettingForTheAmountOfElectricitySold(String strEdt){
			listProperty.add(new ElProp(EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD, strEdt));
			return this;
		}
		/**
		 * 定格発電電力値（系統連系時）<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系時の定格発電電力値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetRatedPowerGenerationOutputSystemInterconnected(String strEdt){
			listProperty.add(new ElProp(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED, strEdt));
			return this;
		}
		/**
		 * 定格発電電力値（独立時）<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の定格発電電力値をWで示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElSetProps reqSetRatedPowerGenerationOutputSystemIndepended(String strEdt){
			listProperty.add(new ElProp(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED, strEdt));
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
		private static final String TAG = "HouseholdSolarPowerGenerationClass.ElGetProps";

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
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetOperationStatus(){
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
		 * 1 バイト目：下位通信層ID フィールド<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定<br>
		 * （ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット0x91～0x9F：IEEE802.11/11b<br>
		 * 0xA1：電灯線c 方式<br>
		 * 0xB1：IPv6/Ethernet<br>
		 * 0xB2：IPv6/6LoWPAN<br>
		 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
		 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
		 * 0x00：識別番号未設定<br>
		 * <br>
		 * 2 バイト目以降：固有番号フィールド<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetIdentificationNum(){
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00~0x17：0x00~0x3B<br>
		 * (=0~23)：(=0~59)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetCurrentTimeSetting(){
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1~0x270F：1~0x0C：1~0x1F<br>
		 * (=1~9999)：(=1~12)：(=1~31)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetCurrentYmdSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING));
			return this;
		}
		/**
		 * 出力制御設定１<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値を設備認定容量の％で設定し、設定状態を取得する<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetOutputLimitSetting1(){
			listProperty.add(new ElProp(EPC_OUTPUT_LIMIT_SETTING_1));
			return this;
		}
		/**
		 * 出力制御設定２<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetOutputLimitSetting2(){
			listProperty.add(new ElProp(EPC_OUTPUT_LIMIT_SETTING_2));
			return this;
		}
		/**
		 * 余剰買取制御機能設定<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 余剰買取制御機能を示す<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetSurplusPurchaseControlFunctinoSetting(){
			listProperty.add(new ElProp(EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING));
			return this;
		}
		/**
		 * 出力制御スケジュール<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char×4 + unsigned char×96<br>
		 * サイズ              : 100 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日と現在年月日翌日の30 分毎の出力制御率を示す<br>
		 * 現在年月日(YYYY：MM：DD) + 0x00~0x64(0~100%) × 96(30分毎の値、2 日分)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetOutputControlSchedule(){
			listProperty.add(new ElProp(EPC_OUTPUT_CONTROL_SCHEDULE));
			return this;
		}
		/**
		 * 次回アクセス日時<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char×7<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールの次回更新日時を示す<br>
		 * YYYYMMDDhhmmss<br>
		 * 0x01～0x270F：0x01～0x0C：0x01 ～ 0x1F： 0x00 ～ 0x17 ：0x00～0x3B：0x00～0x3B<br>
		 * (=1～9999)：(=1～12)：(=1～31)：(=0~23)：(=0～59)：(=0～59)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetNextAccessDateTime(){
			listProperty.add(new ElProp(EPC_NEXT_ACCESS_DATE_TIME));
			return this;
		}
		/**
		 * 余剰買取制御機能タイプ<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetSurplusPurchaseControlFunctionType(){
			listProperty.add(new ElProp(EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE));
			return this;
		}
		/**
		 * 出力変化時間設定値<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 秒<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールにより発電電力を定格発電電力値（系統連系時）の100~0%（0~100%）に変化させる時間を秒で示す<br>
		 * 0x0000～0xFFFD(0～65533)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetOutputChangeTimeSetValue(){
			listProperty.add(new ElProp(EPC_OUTPUT_CHANGE_TIME_SET_VALUE));
			return this;
		}
		/**
		 * 上限クリップ設定値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 上限クリップ制御を行う場合の設定値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）0xFFFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetUpperLimitClipSettingValue(){
			listProperty.add(new ElProp(EPC_UPPER_LIMIT_CLIP_SETTING_VALUE));
			return this;
		}
		/**
		 * 運転力率設定値<br>
		 * EPC                 : 0xC0<br>
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
		 * 運転力率設定値を%で示す<br>
		 * 0x00~0x64(0~100%)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetDrivingPowerFactorSetValue(){
			listProperty.add(new ElProp(EPC_DRIVING_POWER_FACTOR_SET_VALUE));
			return this;
		}
		/**
		 * FIT 契約タイプ<br>
		 * EPC                 : 0xC1<br>
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
		 * FIT 契約のタイプを示す<br>
		 * FIT=0x41<br>
		 * 非FIT=0x42<br>
		 * 未設定=0x43<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetFitContractType(){
			listProperty.add(new ElProp(EPC_FIT_CONTRACT_TYPE));
			return this;
		}
		/**
		 * 自家消費タイプ<br>
		 * EPC                 : 0xC2<br>
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
		 * 自家消費のタイプを示す<br>
		 * 自家消費有=0x41<br>
		 * 自消消費無=0x42<br>
		 * 不明=0x43<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetSelfConsumptionType(){
			listProperty.add(new ElProp(EPC_SELF_CONSUMPTION_TYPE));
			return this;
		}
		/**
		 * 設備認定容量<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetEquipmentCertificationCapacity(){
			listProperty.add(new ElProp(EPC_EQUIPMENT_CERTIFICATION_CAPACITY));
			return this;
		}
		/**
		 * 換算係数<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 定格発電電力値（系統連系時）から設備認定容量への換算率<br>
		 * 0x00～0x64（0～100%）<br>
		 * 0xFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetConversionFactor(){
			listProperty.add(new ElProp(EPC_CONVERSION_FACTOR));
			return this;
		}
		/**
		 * 系統連系状態<br>
		 * EPC                 : 0xD0<br>
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
		 * 不明=0x03<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetSystemInterconnectedType(){
			listProperty.add(new ElProp(EPC_SYSTEM_INTERCONNECTED_TYPE));
			return this;
		}
		/**
		 * 出力抑制状態<br>
		 * EPC                 : 0xD1<br>
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
		 * 出力抑制の実施状態を示す<br>
		 * 抑制中(出力制御)=0x41<br>
		 * 抑制中(出力制御以外)=0x42<br>
		 * 抑制中(抑制要因不明)=0x43<br>
		 * 抑制未実施=0x44<br>
		 * 不明=0x45<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetOutputSuppressionStatsu(){
			listProperty.add(new ElProp(EPC_OUTPUT_SUPPRESSION_STATSU));
			return this;
		}
		/**
		 * 瞬時発電電力計測値<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時発電電力をW で示す。<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetMeasuredInstantaneousAmountOfElectricityGenerated(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED));
			return this;
		}
		/**
		 * 積算発電電力量計測値<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetMeasuredCumulativeAmountOfEnergyGenerated(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED));
			return this;
		}
		/**
		 * 積算売電電力量計測値<br>
		 * EPC                 : 0xE3<br>
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
		 * 売電電力の積算値を0.001kWhで示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetMeasuredCumulativeAmountOfEnergySold(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD));
			return this;
		}
		/**
		 * 発電電力制限設定１<br>
		 * EPC                 : 0xE5<br>
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
		 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetPowerGenerationOutputLimitSetting1(){
			listProperty.add(new ElProp(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1));
			return this;
		}
		/**
		 * 発電電力制限設定２<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 発電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetPowerGenerationOutputLimitSetting2(){
			listProperty.add(new ElProp(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2));
			return this;
		}
		/**
		 * 売電電力制限設定<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 売電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetLimitSettingForTheAmountOfElectricitySold(){
			listProperty.add(new ElProp(EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD));
			return this;
		}
		/**
		 * 定格発電電力値（系統連系時）<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系時の定格発電電力値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetRatedPowerGenerationOutputSystemInterconnected(){
			listProperty.add(new ElProp(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED));
			return this;
		}
		/**
		 * 定格発電電力値（独立時）<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の定格発電電力値をWで示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElGetProps reqGetRatedPowerGenerationOutputSystemIndepended(){
			listProperty.add(new ElProp(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED));
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
		private static final String TAG = "HouseholdSolarPowerGenerationClass.ElInformProps";

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
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfOperationStatus(){
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
		 * 1 バイト目：下位通信層ID フィールド<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定<br>
		 * （ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット0x91～0x9F：IEEE802.11/11b<br>
		 * 0xA1：電灯線c 方式<br>
		 * 0xB1：IPv6/Ethernet<br>
		 * 0xB2：IPv6/6LoWPAN<br>
		 * 0xFE：2～17 バイトをメーカ規定。形式により設定（詳細説明参照）<br>
		 * 0xFF：2～9 バイトを乱数により生成するプロトコルを下位通信層で使用する場合に設定<br>
		 * 0x00：識別番号未設定<br>
		 * <br>
		 * 2 バイト目以降：固有番号フィールド<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfIdentificationNum(){
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00~0x17：0x00~0x3B<br>
		 * (=0~23)：(=0~59)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfCurrentTimeSetting(){
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1~0x270F：1~0x0C：1~0x1F<br>
		 * (=1~9999)：(=1~12)：(=1~31)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfCurrentYmdSetting(){
			listProperty.add(new ElProp(EPC_CURRENT_YMD_SETTING));
			return this;
		}
		/**
		 * 出力制御設定１<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値を設備認定容量の％で設定し、設定状態を取得する<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfOutputLimitSetting1(){
			listProperty.add(new ElProp(EPC_OUTPUT_LIMIT_SETTING_1));
			return this;
		}
		/**
		 * 出力制御設定２<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfOutputLimitSetting2(){
			listProperty.add(new ElProp(EPC_OUTPUT_LIMIT_SETTING_2));
			return this;
		}
		/**
		 * 余剰買取制御機能設定<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 余剰買取制御機能を示す<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfSurplusPurchaseControlFunctinoSetting(){
			listProperty.add(new ElProp(EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING));
			return this;
		}
		/**
		 * 出力制御スケジュール<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char×4 + unsigned char×96<br>
		 * サイズ              : 100 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日と現在年月日翌日の30 分毎の出力制御率を示す<br>
		 * 現在年月日(YYYY：MM：DD) + 0x00~0x64(0~100%) × 96(30分毎の値、2 日分)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfOutputControlSchedule(){
			listProperty.add(new ElProp(EPC_OUTPUT_CONTROL_SCHEDULE));
			return this;
		}
		/**
		 * 次回アクセス日時<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char×7<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールの次回更新日時を示す<br>
		 * YYYYMMDDhhmmss<br>
		 * 0x01～0x270F：0x01～0x0C：0x01 ～ 0x1F： 0x00 ～ 0x17 ：0x00～0x3B：0x00～0x3B<br>
		 * (=1～9999)：(=1～12)：(=1～31)：(=0~23)：(=0～59)：(=0～59)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfNextAccessDateTime(){
			listProperty.add(new ElProp(EPC_NEXT_ACCESS_DATE_TIME));
			return this;
		}
		/**
		 * 余剰買取制御機能タイプ<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfSurplusPurchaseControlFunctionType(){
			listProperty.add(new ElProp(EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE));
			return this;
		}
		/**
		 * 出力変化時間設定値<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 秒<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールにより発電電力を定格発電電力値（系統連系時）の100~0%（0~100%）に変化させる時間を秒で示す<br>
		 * 0x0000～0xFFFD(0～65533)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfOutputChangeTimeSetValue(){
			listProperty.add(new ElProp(EPC_OUTPUT_CHANGE_TIME_SET_VALUE));
			return this;
		}
		/**
		 * 上限クリップ設定値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 上限クリップ制御を行う場合の設定値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）0xFFFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfUpperLimitClipSettingValue(){
			listProperty.add(new ElProp(EPC_UPPER_LIMIT_CLIP_SETTING_VALUE));
			return this;
		}
		/**
		 * 運転力率設定値<br>
		 * EPC                 : 0xC0<br>
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
		 * 運転力率設定値を%で示す<br>
		 * 0x00~0x64(0~100%)<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfDrivingPowerFactorSetValue(){
			listProperty.add(new ElProp(EPC_DRIVING_POWER_FACTOR_SET_VALUE));
			return this;
		}
		/**
		 * FIT 契約タイプ<br>
		 * EPC                 : 0xC1<br>
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
		 * FIT 契約のタイプを示す<br>
		 * FIT=0x41<br>
		 * 非FIT=0x42<br>
		 * 未設定=0x43<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfFitContractType(){
			listProperty.add(new ElProp(EPC_FIT_CONTRACT_TYPE));
			return this;
		}
		/**
		 * 自家消費タイプ<br>
		 * EPC                 : 0xC2<br>
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
		 * 自家消費のタイプを示す<br>
		 * 自家消費有=0x41<br>
		 * 自消消費無=0x42<br>
		 * 不明=0x43<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfSelfConsumptionType(){
			listProperty.add(new ElProp(EPC_SELF_CONSUMPTION_TYPE));
			return this;
		}
		/**
		 * 設備認定容量<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfEquipmentCertificationCapacity(){
			listProperty.add(new ElProp(EPC_EQUIPMENT_CERTIFICATION_CAPACITY));
			return this;
		}
		/**
		 * 換算係数<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 定格発電電力値（系統連系時）から設備認定容量への換算率<br>
		 * 0x00～0x64（0～100%）<br>
		 * 0xFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfConversionFactor(){
			listProperty.add(new ElProp(EPC_CONVERSION_FACTOR));
			return this;
		}
		/**
		 * 系統連系状態<br>
		 * EPC                 : 0xD0<br>
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
		 * 不明=0x03<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfSystemInterconnectedType(){
			listProperty.add(new ElProp(EPC_SYSTEM_INTERCONNECTED_TYPE));
			return this;
		}
		/**
		 * 出力抑制状態<br>
		 * EPC                 : 0xD1<br>
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
		 * 出力抑制の実施状態を示す<br>
		 * 抑制中(出力制御)=0x41<br>
		 * 抑制中(出力制御以外)=0x42<br>
		 * 抑制中(抑制要因不明)=0x43<br>
		 * 抑制未実施=0x44<br>
		 * 不明=0x45<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfOutputSuppressionStatsu(){
			listProperty.add(new ElProp(EPC_OUTPUT_SUPPRESSION_STATSU));
			return this;
		}
		/**
		 * 瞬時発電電力計測値<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時発電電力をW で示す。<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfMeasuredInstantaneousAmountOfElectricityGenerated(){
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED));
			return this;
		}
		/**
		 * 積算発電電力量計測値<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfMeasuredCumulativeAmountOfEnergyGenerated(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED));
			return this;
		}
		/**
		 * 積算発電電力量リセット設定<br>
		 * EPC                 : 0xE2<br>
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
		 * 0x00 を書き込むことにより積算発電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfResettingCumulativeAmountOfEnergyGeneragted(){
			listProperty.add(new ElProp(EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED));
			return this;
		}
		/**
		 * 積算売電電力量計測値<br>
		 * EPC                 : 0xE3<br>
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
		 * 売電電力の積算値を0.001kWhで示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfMeasuredCumulativeAmountOfEnergySold(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD));
			return this;
		}
		/**
		 * 積算売電電力量リセット設定<br>
		 * EPC                 : 0xE4<br>
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
		 * 0x00 を書き込むことにより積算買電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfResettingCumulativeAmountOfElectricEnergySold(){
			listProperty.add(new ElProp(EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD));
			return this;
		}
		/**
		 * 発電電力制限設定１<br>
		 * EPC                 : 0xE5<br>
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
		 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfPowerGenerationOutputLimitSetting1(){
			listProperty.add(new ElProp(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1));
			return this;
		}
		/**
		 * 発電電力制限設定２<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 発電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfPowerGenerationOutputLimitSetting2(){
			listProperty.add(new ElProp(EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2));
			return this;
		}
		/**
		 * 売電電力制限設定<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 売電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfLimitSettingForTheAmountOfElectricitySold(){
			listProperty.add(new ElProp(EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD));
			return this;
		}
		/**
		 * 定格発電電力値（系統連系時）<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系時の定格発電電力値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfRatedPowerGenerationOutputSystemInterconnected(){
			listProperty.add(new ElProp(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED));
			return this;
		}
		/**
		 * 定格発電電力値（独立時）<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の定格発電電力値をWで示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
		 */
		public HouseholdSolarPowerGenerationClass.ElInformProps reqInfRatedPowerGenerationOutputSystemIndepended(){
			listProperty.add(new ElProp(EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED));
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setIAll(ElProcess elProcess) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HouseholdSolarPowerGenerationClass(instanceCode), ElFrame.ESV_SETI);
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setCAll(ElProcess elProcess) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HouseholdSolarPowerGenerationClass(instanceCode), ElFrame.ESV_SETC);
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
	 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElGetProps getAll(ElProcess elProcess) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HouseholdSolarPowerGenerationClass(instanceCode), ElFrame.ESV_GET);
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElInformProps infReqAll(ElProcess elProcess) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	public static HouseholdSolarPowerGenerationClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HouseholdSolarPowerGenerationClass(instanceCode), ElFrame.ESV_INF_REQ);
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElSetProps setI(){
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElSetProps setI(ElClassBase seoj){
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElSetProps setC(){
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
	 * @return HouseholdSolarPowerGenerationClass.ElSetPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElSetProps setC(ElClassBase seoj){
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
	 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElGetProps get(){
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
	 * @return HouseholdSolarPowerGenerationClass.ElGetPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElGetProps get(ElClassBase seoj){
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElInformProps infReq(){
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElInformProps infReq(ElClassBase seoj){
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElInformProps inf(){
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElInformProps inf(ElClassBase deoj){

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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	public HouseholdSolarPowerGenerationClass.ElInformProps infC(String remoteIpAddress){
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
	 * @return HouseholdSolarPowerGenerationClass.ElInformPropsオブジェクト
	 */
	@Override
	public HouseholdSolarPowerGenerationClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
		return new ElInformProps(getElProcess(), remoteIpAddress, this, deoj, ElFrame.ESV_INFC);
	}

	/**
	 * 応答、通知系フレームの受信時の処理用クラス
	 */
	public static class ReportProcessor extends ElClassBase.ReportProcessor{

		/**
		 * ログ出力用タグ
		 */
		public static final String TAG = "HouseholdSolarPowerGenerationClass.ReportProcessor";

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
				case EPC_OUTPUT_LIMIT_SETTING_1:
					onSetOutputLimitSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OUTPUT_LIMIT_SETTING_2:
					onSetOutputLimitSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING:
					onSetSurplusPurchaseControlFunctinoSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_FIT_CONTRACT_TYPE:
					onSetFitContractType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED:
					onSetResettingCumulativeAmountOfEnergyGeneragted(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD:
					onSetResettingCumulativeAmountOfElectricEnergySold(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1:
					onSetPowerGenerationOutputLimitSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2:
					onSetPowerGenerationOutputLimitSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD:
					onSetLimitSettingForTheAmountOfElectricitySold(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED:
					onSetRatedPowerGenerationOutputSystemInterconnected(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED:
					onSetRatedPowerGenerationOutputSystemIndepended(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_OUTPUT_LIMIT_SETTING_1:
					onGetOutputLimitSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OUTPUT_LIMIT_SETTING_2:
					onGetOutputLimitSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING:
					onGetSurplusPurchaseControlFunctinoSetting(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OUTPUT_CONTROL_SCHEDULE:
					onGetOutputControlSchedule(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_NEXT_ACCESS_DATE_TIME:
					onGetNextAccessDateTime(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE:
					onGetSurplusPurchaseControlFunctionType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OUTPUT_CHANGE_TIME_SET_VALUE:
					onGetOutputChangeTimeSetValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_UPPER_LIMIT_CLIP_SETTING_VALUE:
					onGetUpperLimitClipSettingValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DRIVING_POWER_FACTOR_SET_VALUE:
					onGetDrivingPowerFactorSetValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_FIT_CONTRACT_TYPE:
					onGetFitContractType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SELF_CONSUMPTION_TYPE:
					onGetSelfConsumptionType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_EQUIPMENT_CERTIFICATION_CAPACITY:
					onGetEquipmentCertificationCapacity(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CONVERSION_FACTOR:
					onGetConversionFactor(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SYSTEM_INTERCONNECTED_TYPE:
					onGetSystemInterconnectedType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_OUTPUT_SUPPRESSION_STATSU:
					onGetOutputSuppressionStatsu(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED:
					onGetMeasuredInstantaneousAmountOfElectricityGenerated(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED:
					onGetMeasuredCumulativeAmountOfEnergyGenerated(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED:
					onGetResettingCumulativeAmountOfEnergyGeneragted(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD:
					onGetMeasuredCumulativeAmountOfEnergySold(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD:
					onGetResettingCumulativeAmountOfElectricEnergySold(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1:
					onGetPowerGenerationOutputLimitSetting1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2:
					onGetPowerGenerationOutputLimitSetting2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD:
					onGetLimitSettingForTheAmountOfElectricitySold(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED:
					onGetRatedPowerGenerationOutputSystemInterconnected(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED:
					onGetRatedPowerGenerationOutputSystemIndepended(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_OUTPUT_LIMIT_SETTING_1:
					onInformOutputLimitSetting1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OUTPUT_LIMIT_SETTING_2:
					onInformOutputLimitSetting2(seoj, strTid, strEsv, objProp);
					return;
				case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING:
					onInformSurplusPurchaseControlFunctinoSetting(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OUTPUT_CONTROL_SCHEDULE:
					onInformOutputControlSchedule(seoj, strTid, strEsv, objProp);
					return;
				case EPC_NEXT_ACCESS_DATE_TIME:
					onInformNextAccessDateTime(seoj, strTid, strEsv, objProp);
					return;
				case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE:
					onInformSurplusPurchaseControlFunctionType(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OUTPUT_CHANGE_TIME_SET_VALUE:
					onInformOutputChangeTimeSetValue(seoj, strTid, strEsv, objProp);
					return;
				case EPC_UPPER_LIMIT_CLIP_SETTING_VALUE:
					onInformUpperLimitClipSettingValue(seoj, strTid, strEsv, objProp);
					return;
				case EPC_DRIVING_POWER_FACTOR_SET_VALUE:
					onInformDrivingPowerFactorSetValue(seoj, strTid, strEsv, objProp);
					return;
				case EPC_FIT_CONTRACT_TYPE:
					onInformFitContractType(seoj, strTid, strEsv, objProp);
					return;
				case EPC_SELF_CONSUMPTION_TYPE:
					onInformSelfConsumptionType(seoj, strTid, strEsv, objProp);
					return;
				case EPC_EQUIPMENT_CERTIFICATION_CAPACITY:
					onInformEquipmentCertificationCapacity(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CONVERSION_FACTOR:
					onInformConversionFactor(seoj, strTid, strEsv, objProp);
					return;
				case EPC_SYSTEM_INTERCONNECTED_TYPE:
					onInformSystemInterconnectedType(seoj, strTid, strEsv, objProp);
					return;
				case EPC_OUTPUT_SUPPRESSION_STATSU:
					onInformOutputSuppressionStatsu(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED:
					onInformMeasuredInstantaneousAmountOfElectricityGenerated(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED:
					onInformMeasuredCumulativeAmountOfEnergyGenerated(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED:
					onInformResettingCumulativeAmountOfEnergyGeneragted(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD:
					onInformMeasuredCumulativeAmountOfEnergySold(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD:
					onInformResettingCumulativeAmountOfElectricEnergySold(seoj, strTid, strEsv, objProp);
					return;
				case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1:
					onInformPowerGenerationOutputLimitSetting1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2:
					onInformPowerGenerationOutputLimitSetting2(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD:
					onInformLimitSettingForTheAmountOfElectricitySold(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED:
					onInformRatedPowerGenerationOutputSystemInterconnected(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED:
					onInformRatedPowerGenerationOutputSystemIndepended(seoj, strTid, strEsv, objProp);
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
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 現在時刻設定<br>
		 * EPC                 : 0x97<br>
		 * データタイプ        : unsigned char×2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00~0x17：0x00~0x3B<br>
		 * (=0~23)：(=0~59)<br>
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1~0x270F：1~0x0C：1~0x1F<br>
		 * (=1~9999)：(=1~12)：(=1~31)<br>
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
		 * 出力制御設定１<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値を設備認定容量の％で設定し、設定状態を取得する<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOutputLimitSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 出力制御設定２<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOutputLimitSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 余剰買取制御機能設定<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 余剰買取制御機能を示す<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetSurplusPurchaseControlFunctinoSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * FIT 契約タイプ<br>
		 * EPC                 : 0xC1<br>
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
		 * FIT 契約のタイプを示す<br>
		 * FIT=0x41<br>
		 * 非FIT=0x42<br>
		 * 未設定=0x43<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetFitContractType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 積算発電電力量リセット設定<br>
		 * EPC                 : 0xE2<br>
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
		 * 0x00 を書き込むことにより積算発電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetResettingCumulativeAmountOfEnergyGeneragted(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 積算売電電力量リセット設定<br>
		 * EPC                 : 0xE4<br>
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
		 * 0x00 を書き込むことにより積算買電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetResettingCumulativeAmountOfElectricEnergySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 発電電力制限設定１<br>
		 * EPC                 : 0xE5<br>
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
		 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetPowerGenerationOutputLimitSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 発電電力制限設定２<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 発電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetPowerGenerationOutputLimitSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 売電電力制限設定<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 売電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetLimitSettingForTheAmountOfElectricitySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 定格発電電力値（系統連系時）<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系時の定格発電電力値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetRatedPowerGenerationOutputSystemInterconnected(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 定格発電電力値（独立時）<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の定格発電電力値をWで示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetRatedPowerGenerationOutputSystemIndepended(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

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
		 * 1 バイト目：下位通信層ID フィールド<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定<br>
		 * （ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット0x91～0x9F：IEEE802.11/11b<br>
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00~0x17：0x00~0x3B<br>
		 * (=0~23)：(=0~59)<br>
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1~0x270F：1~0x0C：1~0x1F<br>
		 * (=1~9999)：(=1~12)：(=1~31)<br>
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
		 * 出力制御設定１<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値を設備認定容量の％で設定し、設定状態を取得する<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOutputLimitSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 出力制御設定２<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOutputLimitSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 余剰買取制御機能設定<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 余剰買取制御機能を示す<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSurplusPurchaseControlFunctinoSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 出力制御スケジュール<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char×4 + unsigned char×96<br>
		 * サイズ              : 100 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日と現在年月日翌日の30 分毎の出力制御率を示す<br>
		 * 現在年月日(YYYY：MM：DD) + 0x00~0x64(0~100%) × 96(30分毎の値、2 日分)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOutputControlSchedule(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 次回アクセス日時<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char×7<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールの次回更新日時を示す<br>
		 * YYYYMMDDhhmmss<br>
		 * 0x01～0x270F：0x01～0x0C：0x01 ～ 0x1F： 0x00 ～ 0x17 ：0x00～0x3B：0x00～0x3B<br>
		 * (=1～9999)：(=1～12)：(=1～31)：(=0~23)：(=0～59)：(=0～59)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetNextAccessDateTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 余剰買取制御機能タイプ<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSurplusPurchaseControlFunctionType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 出力変化時間設定値<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 秒<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールにより発電電力を定格発電電力値（系統連系時）の100~0%（0~100%）に変化させる時間を秒で示す<br>
		 * 0x0000～0xFFFD(0～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOutputChangeTimeSetValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 上限クリップ設定値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 上限クリップ制御を行う場合の設定値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）0xFFFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetUpperLimitClipSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 運転力率設定値<br>
		 * EPC                 : 0xC0<br>
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
		 * 運転力率設定値を%で示す<br>
		 * 0x00~0x64(0~100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDrivingPowerFactorSetValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * FIT 契約タイプ<br>
		 * EPC                 : 0xC1<br>
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
		 * FIT 契約のタイプを示す<br>
		 * FIT=0x41<br>
		 * 非FIT=0x42<br>
		 * 未設定=0x43<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetFitContractType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 自家消費タイプ<br>
		 * EPC                 : 0xC2<br>
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
		 * 自家消費のタイプを示す<br>
		 * 自家消費有=0x41<br>
		 * 自消消費無=0x42<br>
		 * 不明=0x43<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSelfConsumptionType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 設備認定容量<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetEquipmentCertificationCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 換算係数<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 定格発電電力値（系統連系時）から設備認定容量への換算率<br>
		 * 0x00～0x64（0～100%）<br>
		 * 0xFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetConversionFactor(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 系統連系状態<br>
		 * EPC                 : 0xD0<br>
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
		 * 不明=0x03<br>
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
		 * 出力抑制状態<br>
		 * EPC                 : 0xD1<br>
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
		 * 出力抑制の実施状態を示す<br>
		 * 抑制中(出力制御)=0x41<br>
		 * 抑制中(出力制御以外)=0x42<br>
		 * 抑制中(抑制要因不明)=0x43<br>
		 * 抑制未実施=0x44<br>
		 * 不明=0x45<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOutputSuppressionStatsu(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 瞬時発電電力計測値<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時発電電力をW で示す。<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredInstantaneousAmountOfElectricityGenerated(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算発電電力量計測値<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeAmountOfEnergyGenerated(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算発電電力量リセット設定<br>
		 * EPC                 : 0xE2<br>
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
		 * 0x00 を書き込むことにより積算発電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetResettingCumulativeAmountOfEnergyGeneragted(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算売電電力量計測値<br>
		 * EPC                 : 0xE3<br>
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
		 * 売電電力の積算値を0.001kWhで示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeAmountOfEnergySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算売電電力量リセット設定<br>
		 * EPC                 : 0xE4<br>
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
		 * 0x00 を書き込むことにより積算買電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetResettingCumulativeAmountOfElectricEnergySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 発電電力制限設定１<br>
		 * EPC                 : 0xE5<br>
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
		 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetPowerGenerationOutputLimitSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 発電電力制限設定２<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 発電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetPowerGenerationOutputLimitSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 売電電力制限設定<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 売電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLimitSettingForTheAmountOfElectricitySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定格発電電力値（系統連系時）<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系時の定格発電電力値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRatedPowerGenerationOutputSystemInterconnected(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定格発電電力値（独立時）<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の定格発電電力値をWで示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRatedPowerGenerationOutputSystemIndepended(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

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
		 * 1 バイト目：下位通信層ID フィールド<br>
		 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定<br>
		 * （ECHONET Lite では使用しない）<br>
		 * 0x11～0x1F：電灯線a,d 方式<br>
		 * 0x31～0x3F：特定小電力無線<br>
		 * 0x41～0x4F：拡張HBS<br>
		 * 0x51～0x5F：IrDA<br>
		 * 0x61～0x6F：LonTalk0x71～0x7F：Bluetooth<br>
		 * 0x81～0x8F：イーサネット0x91～0x9F：IEEE802.11/11b<br>
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在時刻HH：MM<br>
		 * 0x00~0x17：0x00~0x3B<br>
		 * (=0~23)：(=0~59)<br>
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
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日YYYY：MM：DD<br>
		 * 1~0x270F：1~0x0C：1~0x1F<br>
		 * (=1~9999)：(=1~12)：(=1~31)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCurrentYmdSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 出力制御設定１<br>
		 * EPC                 : 0xA0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値を設備認定容量の％で設定し、設定状態を取得する<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOutputLimitSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 出力制御設定２<br>
		 * EPC                 : 0xA1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御設定値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOutputLimitSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 余剰買取制御機能設定<br>
		 * EPC                 : 0xA2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 余剰買取制御機能を示す<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSurplusPurchaseControlFunctinoSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 出力制御スケジュール<br>
		 * EPC                 : 0xB0<br>
		 * データタイプ        : unsigned char×4 + unsigned char×96<br>
		 * サイズ              : 100 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 現在年月日と現在年月日翌日の30 分毎の出力制御率を示す<br>
		 * 現在年月日(YYYY：MM：DD) + 0x00~0x64(0~100%) × 96(30分毎の値、2 日分)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOutputControlSchedule(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 次回アクセス日時<br>
		 * EPC                 : 0xB1<br>
		 * データタイプ        : unsigned char×7<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールの次回更新日時を示す<br>
		 * YYYYMMDDhhmmss<br>
		 * 0x01～0x270F：0x01～0x0C：0x01 ～ 0x1F： 0x00 ～ 0x17 ：0x00～0x3B：0x00～0x3B<br>
		 * (=1～9999)：(=1～12)：(=1～31)：(=0~23)：(=0～59)：(=0～59)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformNextAccessDateTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 余剰買取制御機能タイプ<br>
		 * EPC                 : 0xB2<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
		 * 有効=0x41、無効=0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSurplusPurchaseControlFunctionType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 出力変化時間設定値<br>
		 * EPC                 : 0xB3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : 秒<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 出力制御スケジュールにより発電電力を定格発電電力値（系統連系時）の100~0%（0~100%）に変化させる時間を秒で示す<br>
		 * 0x0000～0xFFFD(0～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOutputChangeTimeSetValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 上限クリップ設定値<br>
		 * EPC                 : 0xB4<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 上限クリップ制御を行う場合の設定値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）0xFFFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformUpperLimitClipSettingValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 運転力率設定値<br>
		 * EPC                 : 0xC0<br>
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
		 * 運転力率設定値を%で示す<br>
		 * 0x00~0x64(0~100%)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDrivingPowerFactorSetValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * FIT 契約タイプ<br>
		 * EPC                 : 0xC1<br>
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
		 * FIT 契約のタイプを示す<br>
		 * FIT=0x41<br>
		 * 非FIT=0x42<br>
		 * 未設定=0x43<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformFitContractType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 自家消費タイプ<br>
		 * EPC                 : 0xC2<br>
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
		 * 自家消費のタイプを示す<br>
		 * 自家消費有=0x41<br>
		 * 自消消費無=0x42<br>
		 * 不明=0x43<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSelfConsumptionType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 設備認定容量<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformEquipmentCertificationCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 換算係数<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : %<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 定格発電電力値（系統連系時）から設備認定容量への換算率<br>
		 * 0x00～0x64（0～100%）<br>
		 * 0xFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformConversionFactor(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 系統連系状態<br>
		 * EPC                 : 0xD0<br>
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
		 * 不明=0x03<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSystemInterconnectedType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 出力抑制状態<br>
		 * EPC                 : 0xD1<br>
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
		 * 出力抑制の実施状態を示す<br>
		 * 抑制中(出力制御)=0x41<br>
		 * 抑制中(出力制御以外)=0x42<br>
		 * 抑制中(抑制要因不明)=0x43<br>
		 * 抑制未実施=0x44<br>
		 * 不明=0x45<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOutputSuppressionStatsu(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 瞬時発電電力計測値<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 瞬時発電電力をW で示す。<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredInstantaneousAmountOfElectricityGenerated(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算発電電力量計測値<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量を0.001kWh で示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeAmountOfEnergyGenerated(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算発電電力量リセット設定<br>
		 * EPC                 : 0xE2<br>
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
		 * 0x00 を書き込むことにより積算発電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformResettingCumulativeAmountOfEnergyGeneragted(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算売電電力量計測値<br>
		 * EPC                 : 0xE3<br>
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
		 * 売電電力の積算値を0.001kWhで示す。<br>
		 * 0x00000000～0x3B9AC9FF<br>
		 * (0～999,999.999kWh）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeAmountOfEnergySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算売電電力量リセット設定<br>
		 * EPC                 : 0xE4<br>
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
		 * 0x00 を書き込むことにより積算買電電力量をリセットする<br>
		 * リセット＝0x00<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformResettingCumulativeAmountOfElectricEnergySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 発電電力制限設定１<br>
		 * EPC                 : 0xE5<br>
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
		 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
		 * 0x00～0x64（0～100%）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformPowerGenerationOutputLimitSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 発電電力制限設定２<br>
		 * EPC                 : 0xE6<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 発電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformPowerGenerationOutputLimitSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 売電電力制限設定<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 売電電力制限値をWで設定し、設定状態を取得する<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLimitSettingForTheAmountOfElectricitySold(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定格発電電力値（系統連系時）<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 系統連系時の定格発電電力値をW で示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * 0xFFFF は未設定を示す<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRatedPowerGenerationOutputSystemInterconnected(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定格発電電力値（独立時）<br>
		 * EPC                 : 0xE9<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 独立時の定格発電電力値をWで示す<br>
		 * 0x0000～0xFFFD（0～65533）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRatedPowerGenerationOutputSystemIndepended(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
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
			case EPC_OUTPUT_LIMIT_SETTING_1:
				return this.getFromAppOutputLimitSetting1();
			case EPC_OUTPUT_LIMIT_SETTING_2:
				return this.getFromAppOutputLimitSetting2();
			case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING:
				return this.getFromAppSurplusPurchaseControlFunctinoSetting();
			case EPC_OUTPUT_CONTROL_SCHEDULE:
				return this.getFromAppOutputControlSchedule();
			case EPC_NEXT_ACCESS_DATE_TIME:
				return this.getFromAppNextAccessDateTime();
			case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE:
				return this.getFromAppSurplusPurchaseControlFunctionType();
			case EPC_OUTPUT_CHANGE_TIME_SET_VALUE:
				return this.getFromAppOutputChangeTimeSetValue();
			case EPC_UPPER_LIMIT_CLIP_SETTING_VALUE:
				return this.getFromAppUpperLimitClipSettingValue();
			case EPC_DRIVING_POWER_FACTOR_SET_VALUE:
				return this.getFromAppDrivingPowerFactorSetValue();
			case EPC_FIT_CONTRACT_TYPE:
				return this.getFromAppFitContractType();
			case EPC_SELF_CONSUMPTION_TYPE:
				return this.getFromAppSelfConsumptionType();
			case EPC_EQUIPMENT_CERTIFICATION_CAPACITY:
				return this.getFromAppEquipmentCertificationCapacity();
			case EPC_CONVERSION_FACTOR:
				return this.getFromAppConversionFactor();
			case EPC_SYSTEM_INTERCONNECTED_TYPE:
				return this.getFromAppSystemInterconnectedType();
			case EPC_OUTPUT_SUPPRESSION_STATSU:
				return this.getFromAppOutputSuppressionStatsu();
			case EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED:
				return this.getFromAppMeasuredInstantaneousAmountOfElectricityGenerated();
			case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED:
				return this.getFromAppMeasuredCumulativeAmountOfEnergyGenerated();
			case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD:
				return this.getFromAppMeasuredCumulativeAmountOfEnergySold();
			case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1:
				return this.getFromAppPowerGenerationOutputLimitSetting1();
			case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2:
				return this.getFromAppPowerGenerationOutputLimitSetting2();
			case EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD:
				return this.getFromAppLimitSettingForTheAmountOfElectricitySold();
			case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED:
				return this.getFromAppRatedPowerGenerationOutputSystemInterconnected();
			case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED:
				return this.getFromAppRatedPowerGenerationOutputSystemIndepended();
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
			case EPC_OUTPUT_LIMIT_SETTING_1:
				return this.isValidEdtOutputLimitSetting1(edt);
			case EPC_OUTPUT_LIMIT_SETTING_2:
				return this.isValidEdtOutputLimitSetting2(edt);
			case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING:
				return this.isValidEdtSurplusPurchaseControlFunctinoSetting(edt);
			case EPC_OUTPUT_CONTROL_SCHEDULE:
				return this.isValidEdtOutputControlSchedule(edt);
			case EPC_NEXT_ACCESS_DATE_TIME:
				return this.isValidEdtNextAccessDateTime(edt);
			case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE:
				return this.isValidEdtSurplusPurchaseControlFunctionType(edt);
			case EPC_OUTPUT_CHANGE_TIME_SET_VALUE:
				return this.isValidEdtOutputChangeTimeSetValue(edt);
			case EPC_UPPER_LIMIT_CLIP_SETTING_VALUE:
				return this.isValidEdtUpperLimitClipSettingValue(edt);
			case EPC_DRIVING_POWER_FACTOR_SET_VALUE:
				return this.isValidEdtDrivingPowerFactorSetValue(edt);
			case EPC_FIT_CONTRACT_TYPE:
				return this.isValidEdtFitContractType(edt);
			case EPC_SELF_CONSUMPTION_TYPE:
				return this.isValidEdtSelfConsumptionType(edt);
			case EPC_EQUIPMENT_CERTIFICATION_CAPACITY:
				return this.isValidEdtEquipmentCertificationCapacity(edt);
			case EPC_CONVERSION_FACTOR:
				return this.isValidEdtConversionFactor(edt);
			case EPC_SYSTEM_INTERCONNECTED_TYPE:
				return this.isValidEdtSystemInterconnectedType(edt);
			case EPC_OUTPUT_SUPPRESSION_STATSU:
				return this.isValidEdtOutputSuppressionStatsu(edt);
			case EPC_MEASURED_INSTANTANEOUS_AMOUNT_OF_ELECTRICITY_GENERATED:
				return this.isValidEdtMeasuredInstantaneousAmountOfElectricityGenerated(edt);
			case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_GENERATED:
				return this.isValidEdtMeasuredCumulativeAmountOfEnergyGenerated(edt);
			case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED:
				return this.isValidEdtResettingCumulativeAmountOfEnergyGeneragted(edt);
			case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ENERGY_SOLD:
				return this.isValidEdtMeasuredCumulativeAmountOfEnergySold(edt);
			case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD:
				return this.isValidEdtResettingCumulativeAmountOfElectricEnergySold(edt);
			case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1:
				return this.isValidEdtPowerGenerationOutputLimitSetting1(edt);
			case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2:
				return this.isValidEdtPowerGenerationOutputLimitSetting2(edt);
			case EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD:
				return this.isValidEdtLimitSettingForTheAmountOfElectricitySold(edt);
			case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED:
				return this.isValidEdtRatedPowerGenerationOutputSystemInterconnected(edt);
			case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED:
				return this.isValidEdtRatedPowerGenerationOutputSystemIndepended(edt);
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
			case EPC_OUTPUT_LIMIT_SETTING_1:
				return this.setToAppOutputLimitSetting1(edt);
			case EPC_OUTPUT_LIMIT_SETTING_2:
				return this.setToAppOutputLimitSetting2(edt);
			case EPC_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING:
				return this.setToAppSurplusPurchaseControlFunctinoSetting(edt);
			case EPC_FIT_CONTRACT_TYPE:
				return this.setToAppFitContractType(edt);
			case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED:
				return this.setToAppResettingCumulativeAmountOfEnergyGeneragted(edt);
			case EPC_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD:
				return this.setToAppResettingCumulativeAmountOfElectricEnergySold(edt);
			case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_1:
				return this.setToAppPowerGenerationOutputLimitSetting1(edt);
			case EPC_POWER_GENERATION_OUTPUT_LIMIT_SETTING_2:
				return this.setToAppPowerGenerationOutputLimitSetting2(edt);
			case EPC_LIMIT_SETTING_FOR_THE_AMOUNT_OF_ELECTRICITY_SOLD:
				return this.setToAppLimitSettingForTheAmountOfElectricitySold(edt);
			case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INTERCONNECTED:
				return this.setToAppRatedPowerGenerationOutputSystemInterconnected(edt);
			case EPC_RATED_POWER_GENERATION_OUTPUT_SYSTEM_INDEPENDED:
				return this.setToAppRatedPowerGenerationOutputSystemIndepended(edt);

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
	 * 1 バイト目：下位通信層ID フィールド<br>
	 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定<br>
	 * （ECHONET Lite では使用しない）<br>
	 * 0x11～0x1F：電灯線a,d 方式<br>
	 * 0x31～0x3F：特定小電力無線<br>
	 * 0x41～0x4F：拡張HBS<br>
	 * 0x51～0x5F：IrDA<br>
	 * 0x61～0x6F：LonTalk0x71～0x7F：Bluetooth<br>
	 * 0x81～0x8F：イーサネット0x91～0x9F：IEEE802.11/11b<br>
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
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00~0x17：0x00~0x3B<br>
	 * (=0~23)：(=0~59)<br>
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
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日YYYY：MM：DD<br>
	 * 1~0x270F：1~0x0C：1~0x1F<br>
	 * (=1~9999)：(=1~12)：(=1~31)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
//	protected String getFromAppCurrentYmdSetting(){return null;}
	/**
	 * 出力制御設定１<br>
	 * EPC                 : 0xA0<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御設定値を設備認定容量の％で設定し、設定状態を取得する<br>
	 * 0x00～0x64（0～100%）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOutputLimitSetting1(){return null;}
	/**
	 * 出力制御設定２<br>
	 * EPC                 : 0xA1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御設定値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOutputLimitSetting2(){return null;}
	/**
	 * 余剰買取制御機能設定<br>
	 * EPC                 : 0xA2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 余剰買取制御機能を示す<br>
	 * 有効=0x41、無効=0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSurplusPurchaseControlFunctinoSetting(){return null;}
	/**
	 * 出力制御スケジュール<br>
	 * EPC                 : 0xB0<br>
	 * データタイプ        : unsigned char×4 + unsigned char×96<br>
	 * サイズ              : 100 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日と現在年月日翌日の30 分毎の出力制御率を示す<br>
	 * 現在年月日(YYYY：MM：DD) + 0x00~0x64(0~100%) × 96(30分毎の値、2 日分)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOutputControlSchedule(){return null;}
	/**
	 * 次回アクセス日時<br>
	 * EPC                 : 0xB1<br>
	 * データタイプ        : unsigned char×7<br>
	 * サイズ              : 7 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御スケジュールの次回更新日時を示す<br>
	 * YYYYMMDDhhmmss<br>
	 * 0x01～0x270F：0x01～0x0C：0x01 ～ 0x1F： 0x00 ～ 0x17 ：0x00～0x3B：0x00～0x3B<br>
	 * (=1～9999)：(=1～12)：(=1～31)：(=0~23)：(=0～59)：(=0～59)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppNextAccessDateTime(){return null;}
	/**
	 * 余剰買取制御機能タイプ<br>
	 * EPC                 : 0xB2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
	 * 有効=0x41、無効=0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSurplusPurchaseControlFunctionType(){return null;}
	/**
	 * 出力変化時間設定値<br>
	 * EPC                 : 0xB3<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 秒<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御スケジュールにより発電電力を定格発電電力値（系統連系時）の100~0%（0~100%）に変化させる時間を秒で示す<br>
	 * 0x0000～0xFFFD(0～65533)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOutputChangeTimeSetValue(){return null;}
	/**
	 * 上限クリップ設定値<br>
	 * EPC                 : 0xB4<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 上限クリップ制御を行う場合の設定値をW で示す<br>
	 * 0x0000～0xFFFD（0～65533）0xFFFF は未設定を示す<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppUpperLimitClipSettingValue(){return null;}
	/**
	 * 運転力率設定値<br>
	 * EPC                 : 0xC0<br>
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
	 * 運転力率設定値を%で示す<br>
	 * 0x00~0x64(0~100%)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDrivingPowerFactorSetValue(){return null;}
	/**
	 * FIT 契約タイプ<br>
	 * EPC                 : 0xC1<br>
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
	 * FIT 契約のタイプを示す<br>
	 * FIT=0x41<br>
	 * 非FIT=0x42<br>
	 * 未設定=0x43<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppFitContractType(){return null;}
	/**
	 * 自家消費タイプ<br>
	 * EPC                 : 0xC2<br>
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
	 * 自家消費のタイプを示す<br>
	 * 自家消費有=0x41<br>
	 * 自消消費無=0x42<br>
	 * 不明=0x43<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSelfConsumptionType(){return null;}
	/**
	 * 設備認定容量<br>
	 * EPC                 : 0xC3<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * 0xFFFF は未設定を示す<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppEquipmentCertificationCapacity(){return null;}
	/**
	 * 換算係数<br>
	 * EPC                 : 0xC4<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 定格発電電力値（系統連系時）から設備認定容量への換算率<br>
	 * 0x00～0x64（0～100%）<br>
	 * 0xFF は未設定を示す<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppConversionFactor(){return null;}
	/**
	 * 系統連系状態<br>
	 * EPC                 : 0xD0<br>
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
	 * 不明=0x03<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSystemInterconnectedType(){return null;}
	/**
	 * 出力抑制状態<br>
	 * EPC                 : 0xD1<br>
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
	 * 出力抑制の実施状態を示す<br>
	 * 抑制中(出力制御)=0x41<br>
	 * 抑制中(出力制御以外)=0x42<br>
	 * 抑制中(抑制要因不明)=0x43<br>
	 * 抑制未実施=0x44<br>
	 * 不明=0x45<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppOutputSuppressionStatsu(){return null;}
	/**
	 * 瞬時発電電力計測値<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時発電電力をW で示す。<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredInstantaneousAmountOfElectricityGenerated(){return null;}
	/**
	 * 積算発電電力量計測値<br>
	 * EPC                 : 0xE1<br>
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
	 * 積算電力量を0.001kWh で示す。<br>
	 * 0x00000000～0x3B9AC9FF<br>
	 * (0～999,999.999kWh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCumulativeAmountOfEnergyGenerated(){return null;}
	/**
	 * 積算売電電力量計測値<br>
	 * EPC                 : 0xE3<br>
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
	 * 売電電力の積算値を0.001kWhで示す。<br>
	 * 0x00000000～0x3B9AC9FF<br>
	 * (0～999,999.999kWh）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCumulativeAmountOfEnergySold(){return null;}
	/**
	 * 発電電力制限設定１<br>
	 * EPC                 : 0xE5<br>
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
	 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
	 * 0x00～0x64（0～100%）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppPowerGenerationOutputLimitSetting1(){return null;}
	/**
	 * 発電電力制限設定２<br>
	 * EPC                 : 0xE6<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 発電電力制限値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppPowerGenerationOutputLimitSetting2(){return null;}
	/**
	 * 売電電力制限設定<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 売電電力制限値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLimitSettingForTheAmountOfElectricitySold(){return null;}
	/**
	 * 定格発電電力値（系統連系時）<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 系統連系時の定格発電電力値をW で示す<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * 0xFFFF は未設定を示す<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRatedPowerGenerationOutputSystemInterconnected(){return null;}
	/**
	 * 定格発電電力値（独立時）<br>
	 * EPC                 : 0xE9<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の定格発電電力値をWで示す<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRatedPowerGenerationOutputSystemIndepended(){return null;}


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
	 * 1 バイト目：下位通信層ID フィールド<br>
	 * 0x01～0xFD：下位通信層で使用される通信プロトコルで固有の番号が振られている場合、プロトコル種別に応じて、任意に設定<br>
	 * （ECHONET Lite では使用しない）<br>
	 * 0x11～0x1F：電灯線a,d 方式<br>
	 * 0x31～0x3F：特定小電力無線<br>
	 * 0x41～0x4F：拡張HBS<br>
	 * 0x51～0x5F：IrDA<br>
	 * 0x61～0x6F：LonTalk0x71～0x7F：Bluetooth<br>
	 * 0x81～0x8F：イーサネット0x91～0x9F：IEEE802.11/11b<br>
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
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00~0x17：0x00~0x3B<br>
	 * (=0~23)：(=0~59)<br>
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
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日YYYY：MM：DD<br>
	 * 1~0x270F：1~0x0C：1~0x1F<br>
	 * (=1~9999)：(=1~12)：(=1~31)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
//	protected boolean isValidEdtCurrentYmdSetting(String edt){return false;}
	/**
	 * 出力制御設定１<br>
	 * EPC                 : 0xA0<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御設定値を設備認定容量の％で設定し、設定状態を取得する<br>
	 * 0x00～0x64（0～100%）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOutputLimitSetting1(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * 出力制御設定２<br>
	 * EPC                 : 0xA1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御設定値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOutputLimitSetting2(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "FFFD") <= 0)) return false;
		return true;
	}
	/**
	 * 余剰買取制御機能設定<br>
	 * EPC                 : 0xA2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 余剰買取制御機能を示す<br>
	 * 有効=0x41、無効=0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSurplusPurchaseControlFunctinoSetting(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING_ENABLED)
				&& ! edt.equalsIgnoreCase(EDT_SURPLUS_PURCHASE_CONTROL_FUNCTINO_SETTING_DISABLED)) return false;
		return true;
	}
	/**
	 * 出力制御スケジュール<br>
	 * EPC                 : 0xB0<br>
	 * データタイプ        : unsigned char×4 + unsigned char×96<br>
	 * サイズ              : 100 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日と現在年月日翌日の30 分毎の出力制御率を示す<br>
	 * 現在年月日(YYYY：MM：DD) + 0x00~0x64(0~100%) × 96(30分毎の値、2 日分)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOutputControlSchedule(String edt){
		if(edt == null || !(edt.length() == 100 * 2)) return false;
		if(! checkOutputScheduleFormat(edt)) return false;
		return true;
	}
	/**
	 * 次回アクセス日時<br>
	 * EPC                 : 0xB1<br>
	 * データタイプ        : unsigned char×7<br>
	 * サイズ              : 7 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御スケジュールの次回更新日時を示す<br>
	 * YYYYMMDDhhmmss<br>
	 * 0x01～0x270F：0x01～0x0C：0x01 ～ 0x1F： 0x00 ～ 0x17 ：0x00～0x3B：0x00～0x3B<br>
	 * (=1～9999)：(=1～12)：(=1～31)：(=0~23)：(=0～59)：(=0～59)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtNextAccessDateTime(String edt){
		if(edt == null || !(edt.length() == 7 * 2)) return false;
		if(! checkNextAccessDtFormat(edt)) return false;
		return true;
	}
	/**
	 * 余剰買取制御機能タイプ<br>
	 * EPC                 : 0xB2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
	 * 有効=0x41、無効=0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSurplusPurchaseControlFunctionType(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE_ENABLED)
				&& ! edt.equalsIgnoreCase(EDT_SURPLUS_PURCHASE_CONTROL_FUNCTION_TYPE_DISABLED)) return false;

		return true;
	}
	/**
	 * 出力変化時間設定値<br>
	 * EPC                 : 0xB3<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : 秒<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御スケジュールにより発電電力を定格発電電力値（系統連系時）の100~0%（0~100%）に変化させる時間を秒で示す<br>
	 * 0x0000～0xFFFD(0～65533)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOutputChangeTimeSetValue(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 上限クリップ設定値<br>
	 * EPC                 : 0xB4<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 上限クリップ制御を行う場合の設定値をW で示す<br>
	 * 0x0000～0xFFFD（0～65533）0xFFFF は未設定を示す<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtUpperLimitClipSettingValue(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 運転力率設定値<br>
	 * EPC                 : 0xC0<br>
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
	 * 運転力率設定値を%で示す<br>
	 * 0x00~0x64(0~100%)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDrivingPowerFactorSetValue(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0)) return false;
		return true;
	}
	/**
	 * FIT 契約タイプ<br>
	 * EPC                 : 0xC1<br>
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
	 * FIT 契約のタイプを示す<br>
	 * FIT=0x41<br>
	 * 非FIT=0x42<br>
	 * 未設定=0x43<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtFitContractType(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_FIT_CONTRACT_TYPE_FIT)
				&& ! edt.equalsIgnoreCase(EDT_FIT_CONTRACT_TYPE_NOT_FIT)
				&& ! edt.equalsIgnoreCase(EDT_FIT_CONTRACT_TYPE_UNSET)
				) return false;

		return true;
	}
	/**
	 * 自家消費タイプ<br>
	 * EPC                 : 0xC2<br>
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
	 * 自家消費のタイプを示す<br>
	 * 自家消費有=0x41<br>
	 * 自消消費無=0x42<br>
	 * 不明=0x43<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSelfConsumptionType(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_SELF_CONSUMPTION_TYPE_YES)
				&& ! edt.equalsIgnoreCase(EDT_SELF_CONSUMPTION_TYPE_NO)
				&& ! edt.equalsIgnoreCase(EDT_SELF_CONSUMPTION_TYPE_UNKNOWN)
				) return false;

		return true;
	}
	/**
	 * 設備認定容量<br>
	 * EPC                 : 0xC3<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * 0xFFFF は未設定を示す<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtEquipmentCertificationCapacity(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 換算係数<br>
	 * EPC                 : 0xC4<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : %<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※3:設備認定用容量、換算係数はいずれかの搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 定格発電電力値（系統連系時）から設備認定容量への換算率<br>
	 * 0x00～0x64（0～100%）<br>
	 * 0xFF は未設定を示す<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtConversionFactor(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0 || ElUtil.compElUnsignedChar(edt , "FF") == 0)) return false;
		return true;
	}
	/**
	 * 系統連系状態<br>
	 * EPC                 : 0xD0<br>
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
	 * 不明=0x03<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSystemInterconnectedType(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_SYSTEM_INTERCONNECTED_TYPE_SYSTEM_INTERCONNECTION_REVERSE_POWER_FLOW_ACCEPTABLE)
				&& ! edt.equalsIgnoreCase(EDT_SYSTEM_INTERCONNECTED_TYPE_INDEPENDENT_TYPE)
				&& ! edt.equalsIgnoreCase(EDT_SYSTEM_INTERCONNECTED_TYPE_SYSTEM_INTERCONNECTION_REVERSE_POWER_FLOW_NOT_ACCEPTABLE)
				&& ! edt.equalsIgnoreCase(EDT_SYSTEM_INTERCONNECTED_TYPE_UNKNOWN)
				) return false;

		return true;
	}
	/**
	 * 出力抑制状態<br>
	 * EPC                 : 0xD1<br>
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
	 * 出力抑制の実施状態を示す<br>
	 * 抑制中(出力制御)=0x41<br>
	 * 抑制中(出力制御以外)=0x42<br>
	 * 抑制中(抑制要因不明)=0x43<br>
	 * 抑制未実施=0x44<br>
	 * 不明=0x45<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtOutputSuppressionStatsu(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSING_OUTPUT_CONTROL)
				&& ! edt.equalsIgnoreCase(EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSING_OTHER_THAN_THE_OUTPUT_CONTROL)
				&& ! edt.equalsIgnoreCase(EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSING_RESTRAINTS_UNKNOWN)
				&& ! edt.equalsIgnoreCase(EDT_OUTPUT_SUPPRESSION_STATSU_SUPPRESSION_UNEXECUTED)
				&& ! edt.equalsIgnoreCase(EDT_OUTPUT_SUPPRESSION_STATSU_UNKNOWN)
				) return false;

		return true;
	}
	/**
	 * 瞬時発電電力計測値<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 瞬時発電電力をW で示す。<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredInstantaneousAmountOfElectricityGenerated(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 積算発電電力量計測値<br>
	 * EPC                 : 0xE1<br>
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
	 * 積算電力量を0.001kWh で示す。<br>
	 * 0x00000000～0x3B9AC9FF<br>
	 * (0～999,999.999kWh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeAmountOfEnergyGenerated(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 積算発電電力量リセット設定<br>
	 * EPC                 : 0xE2<br>
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
	 * 0x00 を書き込むことにより積算発電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtResettingCumulativeAmountOfEnergyGeneragted(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_RESETTING_CUMULATIVE_AMOUNT_OF_ENERGY_GENERAGTED_RESET)) return false;
		return true;
	}
	/**
	 * 積算売電電力量計測値<br>
	 * EPC                 : 0xE3<br>
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
	 * 売電電力の積算値を0.001kWhで示す。<br>
	 * 0x00000000～0x3B9AC9FF<br>
	 * (0～999,999.999kWh）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeAmountOfEnergySold(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "3B9AC9FF") <= 0)) return false;
		return true;
	}
	/**
	 * 積算売電電力量リセット設定<br>
	 * EPC                 : 0xE4<br>
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
	 * 0x00 を書き込むことにより積算買電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtResettingCumulativeAmountOfElectricEnergySold(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_RESETTING_CUMULATIVE_AMOUNT_OF_ELECTRIC_ENERGY_SOLD_RESET)) return false;
		return true;
	}
	/**
	 * 発電電力制限設定１<br>
	 * EPC                 : 0xE5<br>
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
	 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
	 * 0x00～0x64（0～100%）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtPowerGenerationOutputLimitSetting1(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "64") <= 0 || ElUtil.compElUnsignedChar(edt , "FF") == 0)) return false;
		return true;
	}
	/**
	 * 発電電力制限設定２<br>
	 * EPC                 : 0xE6<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 発電電力制限値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtPowerGenerationOutputLimitSetting2(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 売電電力制限設定<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 売電電力制限値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLimitSettingForTheAmountOfElectricitySold(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 定格発電電力値（系統連系時）<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 系統連系時の定格発電電力値をW で示す<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * 0xFFFF は未設定を示す<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRatedPowerGenerationOutputSystemInterconnected(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 定格発電電力値（独立時）<br>
	 * EPC                 : 0xE9<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の定格発電電力値をWで示す<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRatedPowerGenerationOutputSystemIndepended(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
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
	 * 現在時刻設定<br>
	 * EPC                 : 0x97<br>
	 * データタイプ        : unsigned char×2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在時刻HH：MM<br>
	 * 0x00~0x17：0x00~0x3B<br>
	 * (=0~23)：(=0~59)<br>
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
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 現在年月日YYYY：MM：DD<br>
	 * 1~0x270F：1~0x0C：1~0x1F<br>
	 * (=1~9999)：(=1~12)：(=1~31)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
//	protected boolean setToAppCurrentYmdSetting(String edt){return false;}
	/**
	 * 発電電力制限設定１<br>
	 * EPC                 : 0xE5<br>
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
	 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
	 * 0x00～0x64（0～100%）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOutputLimitSetting1(String edt){return false;}
	/**
	 * 出力制御設定２<br>
	 * EPC                 : 0xA1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * Get                 : 必須(※2:出力制御可能な太陽光発電の場合は、出力制御設定１、出力制御設定２のいずれか及び、余剰買取制御機能設定の搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御設定値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppOutputLimitSetting2(String edt){return false;}
	/**
	 * 余剰買取制御機能タイプ<br>
	 * EPC                 : 0xB2<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:太陽光発電が持つ出力制御スケジュールにより出力制御を行う場合は搭載を必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 出力制御スケジュールによる出力制御時の余剰買取制御機能タイプを取得する<br>
	 * 有効=0x41、無効=0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppSurplusPurchaseControlFunctinoSetting(String edt){return false;}
	/**
	 * FIT 契約タイプ<br>
	 * EPC                 : 0xC1<br>
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
	 * FIT 契約のタイプを示す<br>
	 * FIT=0x41<br>
	 * 非FIT=0x42<br>
	 * 未設定=0x43<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppFitContractType(String edt){return false;}
	/**
	 * 積算発電電力量リセット設定<br>
	 * EPC                 : 0xE2<br>
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
	 * 0x00 を書き込むことにより積算発電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppResettingCumulativeAmountOfEnergyGeneragted(String edt){return false;}
	/**
	 * 積算売電電力量リセット設定<br>
	 * EPC                 : 0xE4<br>
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
	 * 0x00 を書き込むことにより積算買電電力量をリセットする<br>
	 * リセット＝0x00<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppResettingCumulativeAmountOfElectricEnergySold(String edt){return false;}
	/**
	 * 発電電力制限設定１<br>
	 * EPC                 : 0xE5<br>
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
	 * 発電電力制限値を定格発電電力値の％で設定し、設定状態を取得する。<br>
	 * 0x00～0x64（0～100%）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppPowerGenerationOutputLimitSetting1(String edt){return false;}
	/**
	 * 発電電力制限設定２<br>
	 * EPC                 : 0xE6<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 発電電力制限値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppPowerGenerationOutputLimitSetting2(String edt){return false;}
	/**
	 * 売電電力制限設定<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 売電電力制限値をWで設定し、設定状態を取得する<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppLimitSettingForTheAmountOfElectricitySold(String edt){return false;}
	/**
	 * 定格発電電力値（系統連系時）<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 系統連系時の定格発電電力値をW で示す<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * 0xFFFF は未設定を示す<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppRatedPowerGenerationOutputSystemInterconnected(String edt){return false;}
	/**
	 * 定格発電電力値（独立時）<br>
	 * EPC                 : 0xE9<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 独立時の定格発電電力値をWで示す<br>
	 * 0x0000～0xFFFD（0～65533）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppRatedPowerGenerationOutputSystemIndepended(String edt){return false;}


	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80","動作状態");
			put("83","識別番号");
			put("97","現在時刻設定");
			put("98","現在年月日設定");
			put("A0","出力制御設定１");
			put("A1","出力制御設定２");
			put("A2","余剰買取制御機能設定");
			put("B0","出力制御スケジュール");
			put("B1","次回アクセス日時");
			put("B2","余剰買取制御機能タイプ");
			put("B3","出力変化時間設定値");
			put("B4","上限クリップ設定値");
			put("C0","運転力率設定値");
			put("C1","FIT 契約タイプ");
			put("C2","自家消費タイプ");
			put("C3","設備認定容量");
			put("C4","換算係数");
			put("D0","系統連系状態");
			put("D1","出力抑制状態");
			put("E0","瞬時発電電力計測値");
			put("E1","積算発電電力量計測値");
			put("E2","積算発電電力量リセット設定");
			put("E3","積算売電電力量計測値");
			put("E4","積算売電電力量リセット設定");
			put("E5","発電電力制限設定１");
			put("E6","発電電力制限設定２");
			put("E7","売電電力制限設定");
			put("E8","定格発電電力値（系統連系時）");
			put("E9","定格発電電力値（独立時）");
		}
	};

	/**
	 * Edtにおける次回アクセス日時フォーマットをチェック（年,月,日,時,分,秒をチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkNextAccessDtFormat(String strEdt) {
		String strYmd = strEdt.substring(0, 8);
		String strHms = strEdt.substring(8, 14);
		String strHour = strHms.substring(0, 2);
		String strMin = strHms.substring(2, 4);
		String strSec = strHms.substring(4, 6);

		if( ! (ElUtil.checkEdtYmdSetFormat(strYmd) || ElUtil.compElUnsignedLong("FFFFFFFF", strYmd) == 0)) return false;
		if( ! (ElUtil.checkEdtHmsFormat(strHms) || (ElUtil.compElUnsignedChar("FF", strHour) == 0 && ElUtil.compElUnsignedChar("FF", strMin) == 0 && ElUtil.compElUnsignedChar("FF", strSec) == 0))) return false;
		return true;
	}

	/**
	 * Edtにおける出力制御スケジュールフォーマットをチェック
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkOutputScheduleFormat(String strEdt) {
		String strYmd = strEdt.substring(0, 8);
		String strVals = strEdt.substring(8);
		if( ! (ElUtil.checkEdtYmdSetFormat(strYmd) || ElUtil.compElUnsignedLong("FFFFFFFF", strYmd) == 0)) return false;
		for(int i = 0; i < 96; i++) {
			String strVal = strVals.substring(i * 2, (i + 1) * 2);
			if(! (ElUtil.compElUnsignedChar("00", strVal ) <= 0 && ElUtil.compElUnsignedChar(strVal , "64") <= 0 || ElUtil.compElUnsignedChar(strVal , "FF") == 0)) return false;
		}
		return true;
	}

}
