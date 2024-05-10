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
 * 高圧スマート電力量メータクラス
 */
public class HighVoltageSmartMeter extends DeviceObjectSuperClass{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "HighVoltageSmartMeter";


	/**
	 * クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "02";
	/**
	 * クラスコード
	 */
	public static final String CLASS_CODE = "8A";

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
	 * EPC : 係数
	 */
	public static final String EPC_COEFFICIENT_VALUE = "D3";

	/**
	 * EPC : 係数の倍率
	 */
	public static final String EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT = "D4";

	/**
	 * EPC : 確定日
	 */
	public static final String EPC_FIXED_DATE = "E0";

	/**
	 * EPC : 積算履歴収集日
	 */
	public static final String EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG = "E1";

	/**
	 * EPC : 積算有効電力量計測値
	 */
	public static final String EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY = "E2";

	/**
	 * EPC : 定時積算有効電力量計測値
	 */
	public static final String EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME = "E3";

	/**
	 * EPC : 力測積算有効電力量計測値
	 */
	public static final String EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT = "E4";

	/**
	 * EPC : 積算有効電力量有効桁数
	 */
	public static final String EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY = "E5";

	/**
	 * EPC : 積算有効電力量単位
	 */
	public static final String EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY = "E6";

	/**
	 * EPC : 積算有効電力量計測値履歴
	 */
	public static final String EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY = "E7";

	/**
	 * EPC : 月間最大需要電力
	 */
	public static final String EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND = "C1";

	/**
	 * EPC : 累積最大需要電力
	 */
	public static final String EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND = "C2";

	/**
	 * EPC : 定時需要電力(30分平均電力)
	 */
	public static final String EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE = "C3";

	/**
	 * EPC : 需要電力有効桁数
	 */
	public static final String EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND = "C4";

	/**
	 * EPC : 需要電力単位
	 */
	public static final String EPC_UNIT_OF_ELECTRIC_POWER_DEMAND = "C5";

	/**
	 * EPC : 需要電力計測値履歴
	 */
	public static final String EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND = "C6";

	/**
	 * EPC : 累積最大需要電力単位
	 */
	public static final String EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND = "C7";

	/**
	 * EPC : 力測積算無効電力量(遅れ)計測値
	 */
	public static final String EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT = "CA";

	/**
	 * EPC : 定時力測積算無効電力量(遅れ)計測値
	 */
	public static final String EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE = "CB";

	/**
	 * EPC : 力測積算無効電力量(遅れ)有効桁数
	 */
	public static final String EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT = "CC";

	/**
	 * EPC : 力測積算無効電力量(遅れ)単位
	 */
	public static final String EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG = "CD";

	/**
	 * EPC : 力測積算無効電力量(遅れ)計測値履歴
	 */
	public static final String EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT = "CE";


	/**
	 * コンストラクタ
	 */
	public HighVoltageSmartMeter() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
	}
	/**
	 * コンストラクタ
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public HighVoltageSmartMeter(String entityCode) {
		super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

		this.addMapGetProps(EPC_OPERATION_STATUS);
		this.addMapGetProps(EPC_COEFFICIENT_VALUE);
		this.addMapGetProps(EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT);
		this.addMapGetProps(EPC_FIXED_DATE);
		this.addMapGetProps(EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG);
		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME);
//		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT); // 必須ではない
		this.addMapGetProps(EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND);
//		this.addMapGetProps(EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND); // 必須ではない
		this.addMapGetProps(EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE);
		this.addMapGetProps(EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND);
		this.addMapGetProps(EPC_UNIT_OF_ELECTRIC_POWER_DEMAND);
		this.addMapGetProps(EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND);
//		this.addMapGetProps(EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND); // 必須ではない
//		this.addMapGetProps(EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT); // 必須ではない
//		this.addMapGetProps(EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE); // 必須ではない
//		this.addMapGetProps(EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT); // 必須ではない
//		this.addMapGetProps(EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG); // 必須ではない
//		this.addMapGetProps(EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT); // 必須ではない


//		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
		this.addMapSetProps(EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG);

		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewHighVoltageSmartMeterFound(this);
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
		private static final String TAG = "HighVoltageSmartMeter.ElSetProps";

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
		 * 状変アナウンス      :必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
			return this;
		}
		/**
		 * 積算履歴収集日<br>
		 * EPC                 : E1<br>
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
		 * 30分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63(0～99)<br>
		 * 0：当日 1～99：前日の日数<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElSetProps reqSetDayForRetreivingCumulativeLog(String strEdt){
			listProperty.add(new ElProp(EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG, strEdt));
			return this;
		}
	}

	/**
	 * Get系処理の集約クラス
	 * 0x62 : プロパティ値読出し要求
	 * 0x63 : プロパティ値通知要求
	 */
	public static class ElGetProps extends DeviceObjectSuperClass.ElGetProps  {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "HighVoltageSmartMeter.ElGetProps";

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
		 * 状変アナウンス      :必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}

		/**
		 * 係数<br>
		 * EPC                 : D3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 計器の指示値を実使用kWh, kvarh, kWに換算する係数を10進表記において6桁で示す。<br>
		 * 0x00000000～0x000F423F<br>
		 * (000000～999999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetCoefficientValue(){
			listProperty.add(new ElProp(EPC_COEFFICIENT_VALUE));
			return this;
		}

		/**
		 * 係数の倍率<br>
		 * EPC                 : D4<br>
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
		 * 係数の倍率を示す。<br>
		 * 0x00：×1<br>
		 * 0x01：×0.1<br>
		 * 0x02：×0.01<br>
		 * 0x03：×0.001<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetMultiplyingFactorForCoefficient(){
			listProperty.add(new ElProp(EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT));
			return this;
		}

		/**
		 * 確定日<br>
		 * EPC                 : E0<br>
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
		 * 月間使用量等の確定日を示す。<br>
		 * 0x01～0x1F(1～31)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetFixedDate(){
			listProperty.add(new ElProp(EPC_FIXED_DATE));
			return this;
		}

		/**
		 * 積算履歴収集日<br>
		 * EPC                 : E1<br>
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
		 * 30分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63(0～99)<br>
		 * 0：当日 1～99：前日の日数<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetDayForRetreivingCumulativeLog(){
			listProperty.add(new ElProp(EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG));
			return this;
		}

		/**
		 * 積算有効電力量計測値<br>
		 * EPC                 : E2<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における積算有効電力量の計器指示値を10進表記において、最大8桁で示す。計測年月日を4バイト、計測時刻を3バイト、積算有効電力量を4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～4バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetMeasuredCumulativeAmountOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}

		/**
		 * 定時積算有効電力量計測値<br>
		 * EPC                 : E3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetCumulativeAmountOfActiveElectricEnergyAtFixedTime(){
			listProperty.add(new ElProp(EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME));
			return this;
		}

		/**
		 * 力測積算有効電力量計測値<br>
		 * EPC                 : E4<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT));
			return this;
		}

		/**
		 * 積算有効電力量有効桁数<br>
		 * EPC                 : E5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算有効電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}

		/**
		 * 積算有効電力量単位<br>
		 * EPC                 : E6<br>
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
		 * 積算有効電力量計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kWh<br>
		 * 	0x01：0.1kWh<br>
		 * 0x02：0.01kWh<br>
		 * 0x03：0.001kWh<br>
		 * 0x04：0.0001kWh<br>
		 * 0x0A：10kWh<br>
		 * 0x0B：100kWh<br>
		 * 0x0C：1000kWh<br>
		 * 0x0D：10000kWh<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetUnitForCumulativeAmountsOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}

		/**
		 * 積算有効電力量計測値履歴<br>
		 * EPC                 : E7<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算有効電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}

		/**
		 * 月間最大需要電力<br>
		 * EPC                 : C1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 前回確定日から最新の計測時刻までに記録した需要電力の計器指示値の最大値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetMonthlyMaximumElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND));
			return this;
		}

		/**
		 * 累積最大需要電力<br>
		 * EPC                 : C2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力会社との契約上確定した最大需要電力の計器指示値の累積値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetCumulativeMaximumElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND));
			return this;
		}

		/**
		 * 定時需要電力(30分平均電力)<br>
		 * EPC                 : C3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における需要電力(30分平均電力) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、需要電力(30分平均電力)4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・需要電力 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetElectricPowerDemandAtFixedTime30MinAverage(){
			listProperty.add(new ElProp(EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE));
			return this;
		}

		/**
		 * 需要電力有効桁数<br>
		 * EPC                 : C4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 需要電力の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetEffectiveDigitsOfElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND));
			return this;
		}

		/**
		 * 需要電力単位<br>
		 * EPC                 : C5<br>
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
		 * 月間最大需要電力、定時需要電力(30分平均電力)の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetUnitOfElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_UNIT_OF_ELECTRIC_POWER_DEMAND));
			return this;
		}

		/**
		 * 需要電力計測値履歴<br>
		 * EPC                 : C6<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時需要電力(30分平均電力)の計器指示値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetLogDataOfMeasuredElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND));
			return this;
		}

		/**
		 * 累積最大需要電力単位<br>
		 * EPC                 : C7<br>
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
		 * 累積最大需要電力の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetUnitOfCumulativeMaximumElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND));
			return this;
		}

		/**
		 * 力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CA<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算無効電力量(遅れ) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT));
			return this;
		}

		/**
		 * 定時力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CB<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における力測積算無効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(){
			listProperty.add(new ElProp(EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE));
			return this;
		}

		/**
		 * 力測積算無効電力量(遅れ)有効桁数<br>
		 * EPC                 : CC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算無効電力量(遅れ)計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT));
			return this;
		}

		/**
		 * 力測積算無効電力量(遅れ)単位<br>
		 * EPC                 : CD<br>
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
		 * 積算無効電力量(遅れ)計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kvarh<br>
		 * 0x01：0.1kvarh<br>
		 * 0x02：0.01kvarh<br>
		 * 0x03：0.001kvarh<br>
		 * 0x04：0.0001kvarh<br>
		 * 0x0A：10kvarh<br>
		 * 0x0B：100kvarh<br>
		 * 0x0C：1000kvarh<br>
		 * 0x0D：10000kvarh<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(){
			listProperty.add(new ElProp(EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG));
			return this;
		}

		/**
		 * 力測積算無効電力量(遅れ)計測値履歴<br>
		 * EPC                 : CE<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算無効電力量(遅れ)計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElGetProps reqGetLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT));
			return this;
		}

	}

	/**
	 * 通知系処理の集約クラス
	 * 0x73 : プロパティ値通知
	 * 0x74 : プロパティ値通知（応答要）
	 */
	public static class ElInformProps extends DeviceObjectSuperClass.ElInformProps  {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "HighVoltageSmartMeter.ElInformProps";

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
		 * 状変アナウンス      :必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 係数<br>
		 * EPC                 : D3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 計器の指示値を実使用kWh, kvarh, kWに換算する係数を10進表記において6桁で示す。<br>
		 * 0x00000000～0x000F423F<br>
		 * (000000～999999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfCoefficientValue(){
			listProperty.add(new ElProp(EPC_COEFFICIENT_VALUE));
			return this;
		}
		/**
		 * 係数の倍率<br>
		 * EPC                 : D4<br>
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
		 * 係数の倍率を示す。<br>
		 * 0x00：×1<br>
		 * 0x01：×0.1<br>
		 * 0x02：×0.01<br>
		 * 0x03：×0.001<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfMultiplyingFactorForCoefficient(){
			listProperty.add(new ElProp(EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT));
			return this;
		}
		/**
		 * 確定日<br>
		 * EPC                 : E0<br>
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
		 * 月間使用量等の確定日を示す。<br>
		 * 0x01～0x1F(1～31)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfFixedDate(){
			listProperty.add(new ElProp(EPC_FIXED_DATE));
			return this;
		}
		/**
		 * 積算履歴収集日<br>
		 * EPC                 : E1<br>
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
		 * 30分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63(0～99)<br>
		 * 0：当日 1～99：前日の日数<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfDayForRetreivingCumulativeLog(){
			listProperty.add(new ElProp(EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG));
			return this;
		}
		/**
		 * 積算有効電力量計測値<br>
		 * EPC                 : E2<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における積算有効電力量の計器指示値を10進表記において、最大8桁で示す。計測年月日を4バイト、計測時刻を3バイト、積算有効電力量を4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～4バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfMeasuredCumulativeAmountOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 定時積算有効電力量計測値<br>
		 * EPC                 : E3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfCumulativeAmountOfActiveElectricEnergyAtFixedTime(){
			listProperty.add(new ElProp(EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME));
			return this;
		}
		/**
		 * 力測積算有効電力量計測値<br>
		 * EPC                 : E4<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT));
			return this;
		}
		/**
		 * 積算有効電力量有効桁数<br>
		 * EPC                 : E5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算有効電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 積算有効電力量単位<br>
		 * EPC                 : E6<br>
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
		 * 積算有効電力量計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kWh<br>
		 * 	0x01：0.1kWh<br>
		 * 0x02：0.01kWh<br>
		 * 0x03：0.001kWh<br>
		 * 0x04：0.0001kWh<br>
		 * 0x0A：10kWh<br>
		 * 0x0B：100kWh<br>
		 * 0x0C：1000kWh<br>
		 * 0x0D：10000kWh<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfUnitForCumulativeAmountsOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 積算有効電力量計測値履歴<br>
		 * EPC                 : E7<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算有効電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(){
			listProperty.add(new ElProp(EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY));
			return this;
		}
		/**
		 * 月間最大需要電力<br>
		 * EPC                 : C1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 前回確定日から最新の計測時刻までに記録した需要電力の計器指示値の最大値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfMonthlyMaximumElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND));
			return this;
		}
		/**
		 * 累積最大需要電力<br>
		 * EPC                 : C2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力会社との契約上確定した最大需要電力の計器指示値の累積値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfCumulativeMaximumElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND));
			return this;
		}
		/**
		 * 定時需要電力(30分平均電力)<br>
		 * EPC                 : C3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における需要電力(30分平均電力) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、需要電力(30分平均電力)4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・需要電力 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfElectricPowerDemandAtFixedTime30MinAverage(){
			listProperty.add(new ElProp(EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE));
			return this;
		}
		/**
		 * 需要電力有効桁数<br>
		 * EPC                 : C4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 需要電力の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfEffectiveDigitsOfElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND));
			return this;
		}
		/**
		 * 需要電力単位<br>
		 * EPC                 : C5<br>
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
		 * 月間最大需要電力、定時需要電力(30分平均電力)の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfUnitOfElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_UNIT_OF_ELECTRIC_POWER_DEMAND));
			return this;
		}
		/**
		 * 需要電力計測値履歴<br>
		 * EPC                 : C6<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時需要電力(30分平均電力)の計器指示値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfLogDataOfMeasuredElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND));
			return this;
		}
		/**
		 * 累積最大需要電力単位<br>
		 * EPC                 : C7<br>
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
		 * 累積最大需要電力の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfUnitOfCumulativeMaximumElectricPowerDemand(){
			listProperty.add(new ElProp(EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND));
			return this;
		}
		/**
		 * 力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CA<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算無効電力量(遅れ) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT));
			return this;
		}
		/**
		 * 定時力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CB<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における力測積算無効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(){
			listProperty.add(new ElProp(EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE));
			return this;
		}
		/**
		 * 力測積算無効電力量(遅れ)有効桁数<br>
		 * EPC                 : CC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算無効電力量(遅れ)計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT));
			return this;
		}
		/**
		 * 力測積算無効電力量(遅れ)単位<br>
		 * EPC                 : CD<br>
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
		 * 積算無効電力量(遅れ)計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kvarh<br>
		 * 0x01：0.1kvarh<br>
		 * 0x02：0.01kvarh<br>
		 * 0x03：0.001kvarh<br>
		 * 0x04：0.0001kvarh<br>
		 * 0x0A：10kvarh<br>
		 * 0x0B：100kvarh<br>
		 * 0x0C：1000kvarh<br>
		 * 0x0D：10000kvarh<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(){
			listProperty.add(new ElProp(EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG));
			return this;
		}
		/**
		 * 力測積算無効電力量(遅れ)計測値履歴<br>
		 * EPC                 : CE<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算無効電力量(遅れ)計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public HighVoltageSmartMeter.ElInformProps reqInfLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(){
			listProperty.add(new ElProp(EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT));
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess) {
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HighVoltageSmartMeter(instanceCode), ElFrame.ESV_SETI);
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess) {
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HighVoltageSmartMeter(instanceCode), ElFrame.ESV_SETC);
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
	 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess) {
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
	 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HighVoltageSmartMeter(instanceCode), ElFrame.ESV_GET);
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess) {
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static HighVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new HighVoltageSmartMeter(instanceCode), ElFrame.ESV_INF_REQ);
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElSetProps setI(){
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElSetProps setI(ElClassBase seoj){
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElSetProps setC(){
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
	 * @return HighVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElSetProps setC(ElClassBase seoj){
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
	 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElGetProps get(){
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
	 * @return HighVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElGetProps get(ElClassBase seoj){
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElInformProps infReq(){
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElInformProps infReq(ElClassBase seoj){
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElInformProps inf(){
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElInformProps inf(ElClassBase deoj){

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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public HighVoltageSmartMeter.ElInformProps infC(String remoteIpAddress){
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
	 * @return HighVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public HighVoltageSmartMeter.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
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
		private static final String TAG = "HighVoltageSmartMeter.ReportProcessor";

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
				case EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG:
					onSetDayForRetreivingCumulativeLog(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_COEFFICIENT_VALUE:
					onGetCoefficientValue(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT:
					onGetMultiplyingFactorForCoefficient(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_FIXED_DATE:
					onGetFixedDate(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG:
					onGetDayForRetreivingCumulativeLog(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
					onGetMeasuredCumulativeAmountOfActiveElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME:
					onGetCumulativeAmountOfActiveElectricEnergyAtFixedTime(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT:
					onGetMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
					onGetEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY:
					onGetUnitForCumulativeAmountsOfActiveElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
					onGetLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND:
					onGetMonthlyMaximumElectricPowerDemand(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
					onGetCumulativeMaximumElectricPowerDemand(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE:
					onGetElectricPowerDemandAtFixedTime30MinAverage(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND:
					onGetEffectiveDigitsOfElectricPowerDemand(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_UNIT_OF_ELECTRIC_POWER_DEMAND:
					onGetUnitOfElectricPowerDemand(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND:
					onGetLogDataOfMeasuredElectricPowerDemand(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
					onGetUnitOfCumulativeMaximumElectricPowerDemand(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
					onGetMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE:
					onGetMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
					onGetEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG:
					onGetUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
					onGetLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_COEFFICIENT_VALUE:
					onInformCoefficientValue(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT:
					onInformMultiplyingFactorForCoefficient(seoj, strTid, strEsv, objProp);
					return;
				case EPC_FIXED_DATE:
					onInformFixedDate(seoj, strTid, strEsv, objProp);
					return;
				case EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG:
					onInformDayForRetreivingCumulativeLog(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
					onInformMeasuredCumulativeAmountOfActiveElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME:
					onInformCumulativeAmountOfActiveElectricEnergyAtFixedTime(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT:
					onInformMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(seoj, strTid, strEsv, objProp);
					return;
				case EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
					onInformEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY:
					onInformUnitForCumulativeAmountsOfActiveElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
					onInformLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND:
					onInformMonthlyMaximumElectricPowerDemand(seoj, strTid, strEsv, objProp);
					return;
				case EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
					onInformCumulativeMaximumElectricPowerDemand(seoj, strTid, strEsv, objProp);
					return;
				case EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE:
					onInformElectricPowerDemandAtFixedTime30MinAverage(seoj, strTid, strEsv, objProp);
					return;
				case EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND:
					onInformEffectiveDigitsOfElectricPowerDemand(seoj, strTid, strEsv, objProp);
					return;
				case EPC_UNIT_OF_ELECTRIC_POWER_DEMAND:
					onInformUnitOfElectricPowerDemand(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND:
					onInformLogDataOfMeasuredElectricPowerDemand(seoj, strTid, strEsv, objProp);
					return;
				case EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
					onInformUnitOfCumulativeMaximumElectricPowerDemand(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
					onInformMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE:
					onInformMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(seoj, strTid, strEsv, objProp);
					return;
				case EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
					onInformEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(seoj, strTid, strEsv, objProp);
					return;
				case EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG:
					onInformUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(seoj, strTid, strEsv, objProp);
					return;
				case EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
					onInformLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(seoj, strTid, strEsv, objProp);
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
		 * 状変アナウンス      :必須<br>
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
		 * 積算履歴収集日<br>
		 * EPC                 : E1<br>
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
		 * 30分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63(0～99)<br>
		 * 0：当日 1～99：前日の日数<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetDayForRetreivingCumulativeLog(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

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
		 * 状変アナウンス      :必須<br>
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
		 * 係数<br>
		 * EPC                 : D3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 計器の指示値を実使用kWh, kvarh, kWに換算する係数を10進表記において6桁で示す。<br>
		 * 0x00000000～0x000F423F<br>
		 * (000000～999999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetCoefficientValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 係数の倍率<br>
		 * EPC                 : D4<br>
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
		 * 係数の倍率を示す。<br>
		 * 0x00：×1<br>
		 * 0x01：×0.1<br>
		 * 0x02：×0.01<br>
		 * 0x03：×0.001<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMultiplyingFactorForCoefficient(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 確定日<br>
		 * EPC                 : E0<br>
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
		 * 月間使用量等の確定日を示す。<br>
		 * 0x01～0x1F(1～31)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetFixedDate(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算履歴収集日<br>
		 * EPC                 : E1<br>
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
		 * 30分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63(0～99)<br>
		 * 0：当日 1～99：前日の日数<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDayForRetreivingCumulativeLog(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算有効電力量計測値<br>
		 * EPC                 : E2<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における積算有効電力量の計器指示値を10進表記において、最大8桁で示す。計測年月日を4バイト、計測時刻を3バイト、積算有効電力量を4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～4バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeAmountOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定時積算有効電力量計測値<br>
		 * EPC                 : E3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetCumulativeAmountOfActiveElectricEnergyAtFixedTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 力測積算有効電力量計測値<br>
		 * EPC                 : E4<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算有効電力量有効桁数<br>
		 * EPC                 : E5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算有効電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算有効電力量単位<br>
		 * EPC                 : E6<br>
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
		 * 積算有効電力量計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kWh<br>
		 * 	0x01：0.1kWh<br>
		 * 0x02：0.01kWh<br>
		 * 0x03：0.001kWh<br>
		 * 0x04：0.0001kWh<br>
		 * 0x0A：10kWh<br>
		 * 0x0B：100kWh<br>
		 * 0x0C：1000kWh<br>
		 * 0x0D：10000kWh<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetUnitForCumulativeAmountsOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算有効電力量計測値履歴<br>
		 * EPC                 : E7<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算有効電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 月間最大需要電力<br>
		 * EPC                 : C1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 前回確定日から最新の計測時刻までに記録した需要電力の計器指示値の最大値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMonthlyMaximumElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 累積最大需要電力<br>
		 * EPC                 : C2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力会社との契約上確定した最大需要電力の計器指示値の累積値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetCumulativeMaximumElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定時需要電力(30分平均電力)<br>
		 * EPC                 : C3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における需要電力(30分平均電力) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、需要電力(30分平均電力)4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・需要電力 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetElectricPowerDemandAtFixedTime30MinAverage(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 需要電力有効桁数<br>
		 * EPC                 : C4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 需要電力の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetEffectiveDigitsOfElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 需要電力単位<br>
		 * EPC                 : C5<br>
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
		 * 月間最大需要電力、定時需要電力(30分平均電力)の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetUnitOfElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 需要電力計測値履歴<br>
		 * EPC                 : C6<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時需要電力(30分平均電力)の計器指示値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLogDataOfMeasuredElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 累積最大需要電力単位<br>
		 * EPC                 : C7<br>
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
		 * 累積最大需要電力の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetUnitOfCumulativeMaximumElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CA<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算無効電力量(遅れ) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定時力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CB<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における力測積算無効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 力測積算無効電力量(遅れ)有効桁数<br>
		 * EPC                 : CC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算無効電力量(遅れ)計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 力測積算無効電力量(遅れ)単位<br>
		 * EPC                 : CD<br>
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
		 * 積算無効電力量(遅れ)計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kvarh<br>
		 * 0x01：0.1kvarh<br>
		 * 0x02：0.01kvarh<br>
		 * 0x03：0.001kvarh<br>
		 * 0x04：0.0001kvarh<br>
		 * 0x0A：10kvarh<br>
		 * 0x0B：100kvarh<br>
		 * 0x0C：1000kvarh<br>
		 * 0x0D：10000kvarh<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 力測積算無効電力量(遅れ)計測値履歴<br>
		 * EPC                 : CE<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算無効電力量(遅れ)計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

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
		 * 状変アナウンス      :必須<br>
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
		 * 係数<br>
		 * EPC                 : D3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 計器の指示値を実使用kWh, kvarh, kWに換算する係数を10進表記において6桁で示す。<br>
		 * 0x00000000～0x000F423F<br>
		 * (000000～999999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCoefficientValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 係数の倍率<br>
		 * EPC                 : D4<br>
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
		 * 係数の倍率を示す。<br>
		 * 0x00：×1<br>
		 * 0x01：×0.1<br>
		 * 0x02：×0.01<br>
		 * 0x03：×0.001<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMultiplyingFactorForCoefficient(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 確定日<br>
		 * EPC                 : E0<br>
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
		 * 月間使用量等の確定日を示す。<br>
		 * 0x01～0x1F(1～31)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformFixedDate(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算履歴収集日<br>
		 * EPC                 : E1<br>
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
		 * 30分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63(0～99)<br>
		 * 0：当日 1～99：前日の日数<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDayForRetreivingCumulativeLog(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算有効電力量計測値<br>
		 * EPC                 : E2<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における積算有効電力量の計器指示値を10進表記において、最大8桁で示す。計測年月日を4バイト、計測時刻を3バイト、積算有効電力量を4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～4バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeAmountOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定時積算有効電力量計測値<br>
		 * EPC                 : E3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCumulativeAmountOfActiveElectricEnergyAtFixedTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 力測積算有効電力量計測値<br>
		 * EPC                 : E4<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算有効電力量 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算有効電力量有効桁数<br>
		 * EPC                 : E5<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算有効電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算有効電力量単位<br>
		 * EPC                 : E6<br>
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
		 * 積算有効電力量計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kWh<br>
		 * 	0x01：0.1kWh<br>
		 * 0x02：0.01kWh<br>
		 * 0x03：0.001kWh<br>
		 * 0x04：0.0001kWh<br>
		 * 0x0A：10kWh<br>
		 * 0x0B：100kWh<br>
		 * 0x0C：1000kWh<br>
		 * 0x0D：10000kWh<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformUnitForCumulativeAmountsOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算有効電力量計測値履歴<br>
		 * EPC                 : E7<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算有効電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算有効電力量<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 月間最大需要電力<br>
		 * EPC                 : C1<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 前回確定日から最新の計測時刻までに記録した需要電力の計器指示値の最大値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMonthlyMaximumElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 累積最大需要電力<br>
		 * EPC                 : C2<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力会社との契約上確定した最大需要電力の計器指示値の累積値を示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCumulativeMaximumElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定時需要電力(30分平均電力)<br>
		 * EPC                 : C3<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における需要電力(30分平均電力) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、需要電力(30分平均電力)4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・需要電力 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformElectricPowerDemandAtFixedTime30MinAverage(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 需要電力有効桁数<br>
		 * EPC                 : C4<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 需要電力の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformEffectiveDigitsOfElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 需要電力単位<br>
		 * EPC                 : C5<br>
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
		 * 月間最大需要電力、定時需要電力(30分平均電力)の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformUnitOfElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 需要電力計測値履歴<br>
		 * EPC                 : C6<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kW<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時需要電力(30分平均電力)の計器指示値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：需要電力<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLogDataOfMeasuredElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 累積最大需要電力単位<br>
		 * EPC                 : C7<br>
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
		 * 累積最大需要電力の単位を示す。<br>
		 * 0x00：1kW<br>
		 * 0x01：0.1kW<br>
		 * 0x02：0.01kW<br>
		 * 0x03：0.001kW<br>
		 * 0x04：0.0001kW<br>
		 * 0x0A：10kW<br>
		 * 0x0B：100kW<br>
		 * 0x0C：1000kW<br>
		 * 0x0D：10000kW<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformUnitOfCumulativeMaximumElectricPowerDemand(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CA<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の計測時刻における力測積算無効電力量(遅れ) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定時力測積算無効電力量(遅れ)計測値<br>
		 * EPC                 : CB<br>
		 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日, 時分秒, kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30分毎の計測時刻における力測積算無効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 * YYYY:0x0001～0x270F(1～9999)<br>
		 * MM:0x01～0x0C(1～12)<br>
		 * DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 * hh:0x00～0x17(0～23)<br>
		 * mm:0x00～0x3B(0～59)<br>
		 * ss:0x00～0x3B(0～59)<br>
		 * 8～11バイト目：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 力測積算無効電力量(遅れ)有効桁数<br>
		 * EPC                 : CC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : 桁<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算無効電力量(遅れ)計測値の有効桁数を示す。<br>
		 * 0x01～0x08<br>
		 * (1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 力測積算無効電力量(遅れ)単位<br>
		 * EPC                 : CD<br>
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
		 * 積算無効電力量(遅れ)計測値、履歴の単位(乗率)を示す。<br>
		 * 0x00：1kvarh<br>
		 * 0x01：0.1kvarh<br>
		 * 0x02：0.01kvarh<br>
		 * 0x03：0.001kvarh<br>
		 * 0x04：0.0001kvarh<br>
		 * 0x0A：10kvarh<br>
		 * 0x0B：100kvarh<br>
		 * 0x0C：1000kvarh<br>
		 * 0x0D：10000kvarh<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 力測積算無効電力量(遅れ)計測値履歴<br>
		 * EPC                 : CE<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kvarh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算無効電力量(遅れ)計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3バイト目以降：積算無効電力量(遅れ)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

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
			case EPC_COEFFICIENT_VALUE:
				return this.getFromAppCoefficientValue();
			case EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT:
				return this.getFromAppMultiplyingFactorForCoefficient();
			case EPC_FIXED_DATE:
				return this.getFromAppFixedDate();
			case EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG:
				return this.getFromAppDayForRetreivingCumulativeLog();
			case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.getFromAppMeasuredCumulativeAmountOfActiveElectricEnergy();
			case EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME:
				return this.getFromAppCumulativeAmountOfActiveElectricEnergyAtFixedTime();
			case EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT:
				return this.getFromAppMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement();
			case EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.getFromAppEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy();
			case EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.getFromAppUnitForCumulativeAmountsOfActiveElectricEnergy();
			case EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.getFromAppLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy();
			case EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND:
				return this.getFromAppMonthlyMaximumElectricPowerDemand();
			case EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
				return this.getFromAppCumulativeMaximumElectricPowerDemand();
			case EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE:
				return this.getFromAppElectricPowerDemandAtFixedTime30MinAverage();
			case EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND:
				return this.getFromAppEffectiveDigitsOfElectricPowerDemand();
			case EPC_UNIT_OF_ELECTRIC_POWER_DEMAND:
				return this.getFromAppUnitOfElectricPowerDemand();
			case EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND:
				return this.getFromAppLogDataOfMeasuredElectricPowerDemand();
			case EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
				return this.getFromAppUnitOfCumulativeMaximumElectricPowerDemand();
			case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
				return this.getFromAppMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement();
			case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE:
				return this.getFromAppMeasuredDataOfReactiveElectricPowerLagAtFixedTimie();
			case EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
				return this.getFromAppEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement();
			case EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG:
				return this.getFromAppUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag();
			case EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
				return this.getFromAppLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement();
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
			case EPC_COEFFICIENT_VALUE:
				return this.isValidEdtCoefficientValue(edt);
			case EPC_MULTIPLYING_FACTOR_FOR_COEFFICIENT:
				return this.isValidEdtMultiplyingFactorForCoefficient(edt);
			case EPC_FIXED_DATE:
				return this.isValidEdtFixedDate(edt);
			case EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG:
				return this.isValidEdtDayForRetreivingCumulativeLog(edt);
			case EPC_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.isValidEdtMeasuredCumulativeAmountOfActiveElectricEnergy(edt);
			case EPC_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY_AT_FIXED_TIME:
				return this.isValidEdtCumulativeAmountOfActiveElectricEnergyAtFixedTime(edt);
			case EPC_MEASURED_CUMULATIVE_VALUE_OF_ACTIVE_ELECTRIC_ENERGY_FOR_POWER_FACTOR_MEASUREMENT:
				return this.isValidEdtMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(edt);
			case EPC_EFFECTIVE_DIGITS_FOR_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.isValidEdtEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(edt);
			case EPC_UNIT_FOR_CUMULATIVE_AMOUNTS_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.isValidEdtUnitForCumulativeAmountsOfActiveElectricEnergy(edt);
			case EPC_LOG_DATA_OF_MEASURED_CUMULATIVE_AMOUNT_OF_ACTIVE_ELECTRIC_ENERGY:
				return this.isValidEdtLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(edt);
			case EPC_MONTHLY_MAXIMUM_ELECTRIC_POWER_DEMAND:
				return this.isValidEdtMonthlyMaximumElectricPowerDemand(edt);
			case EPC_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
				return this.isValidEdtCumulativeMaximumElectricPowerDemand(edt);
			case EPC_ELECTRIC_POWER_DEMAND_AT_FIXED_TIME_30_MIN_AVERAGE:
				return this.isValidEdtElectricPowerDemandAtFixedTime30MinAverage(edt);
			case EPC_EFFECTIVE_DIGITS_OF_ELECTRIC_POWER_DEMAND:
				return this.isValidEdtEffectiveDigitsOfElectricPowerDemand(edt);
			case EPC_UNIT_OF_ELECTRIC_POWER_DEMAND:
				return this.isValidEdtUnitOfElectricPowerDemand(edt);
			case EPC_LOG_DATA_OF_MEASURED_ELECTRIC_POWER_DEMAND:
				return this.isValidEdtLogDataOfMeasuredElectricPowerDemand(edt);
			case EPC_UNIT_OF_CUMULATIVE_MAXIMUM_ELECTRIC_POWER_DEMAND:
				return this.isValidEdtUnitOfCumulativeMaximumElectricPowerDemand(edt);
			case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
				return this.isValidEdtMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(edt);
			case EPC_MEASURED_DATA_OF_REACTIVE_ELECTRIC_POWER_LAG_AT_FIXED_TIMIE:
				return this.isValidEdtMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(edt);
			case EPC_EFFECTIVE_DIGITS_FOR_MEASURED_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
				return this.isValidEdtEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(edt);
			case EPC_UNIT_OF_MEASUREMENT_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG:
				return this.isValidEdtUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(edt);
			case EPC_LOG_DATA_OF_CUMULATIVE_AMOUNT_OF_REACTIVE_ELECTRIC_POWER_LAG_FOR_POWER_FACTOR_MEASUREMENT:
				return this.isValidEdtLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(edt);
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
			case EPC_DAY_FOR_RETREIVING_CUMULATIVE_LOG:
				return this.setToAppDayForRetreivingCumulativeLog(edt);
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
	 * 状変アナウンス      :必須<br>
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
	 * 係数<br>
	 * EPC                 : D3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 計器の指示値を実使用kWh, kvarh, kWに換算する係数を10進表記において6桁で示す。<br>
	 * 0x00000000～0x000F423F<br>
	 * (000000～999999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppCoefficientValue(){return null;}
	/**
	 * 係数の倍率<br>
	 * EPC                 : D4<br>
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
	 * 係数の倍率を示す。<br>
	 * 0x00：×1<br>
	 * 0x01：×0.1<br>
	 * 0x02：×0.01<br>
	 * 0x03：×0.001<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMultiplyingFactorForCoefficient(){return null;}
	/**
	 * 確定日<br>
	 * EPC                 : E0<br>
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
	 * 月間使用量等の確定日を示す。<br>
	 * 0x01～0x1F(1～31)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppFixedDate(){return null;}
	/**
	 * 積算履歴収集日<br>
	 * EPC                 : E1<br>
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
	 * 30分毎の計測値履歴データを収集する日を示す。<br>
	 * 0x00～0x63(0～99)<br>
	 * 0：当日 1～99：前日の日数<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDayForRetreivingCumulativeLog(){return null;}
	/**
	 * 積算有効電力量計測値<br>
	 * EPC                 : E2<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日,時分秒,kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の計測時刻における積算有効電力量の計器指示値を10進表記において、最大8桁で示す。計測年月日を4バイト、計測時刻を3バイト、積算有効電力量を4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算有効電力量 10進表記で最大8桁<br>
	 * 1～4バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCumulativeAmountOfActiveElectricEnergy(){return null;}
	/**
	 * 定時積算有効電力量計測値<br>
	 * EPC                 : E3<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30分毎の計測時刻における積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算有効電力量 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppCumulativeAmountOfActiveElectricEnergyAtFixedTime(){return null;}
	/**
	 * 力測積算有効電力量計測値<br>
	 * EPC                 : E4<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の計測時刻における力測積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算有効電力量 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(){return null;}
	/**
	 * 積算有効電力量有効桁数<br>
	 * EPC                 : E5<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : 桁<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算有効電力量計測値の有効桁数を示す。<br>
	 * 0x01～0x08<br>
	 * (1～8)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(){return null;}
	/**
	 * 積算有効電力量単位<br>
	 * EPC                 : E6<br>
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
	 * 積算有効電力量計測値、履歴の単位(乗率)を示す。<br>
	 * 0x00：1kWh<br>
	 * 	0x01：0.1kWh<br>
	 * 0x02：0.01kWh<br>
	 * 0x03：0.001kWh<br>
	 * 0x04：0.0001kWh<br>
	 * 0x0A：10kWh<br>
	 * 0x0B：100kWh<br>
	 * 0x0C：1000kWh<br>
	 * 0x0D：10000kWh<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppUnitForCumulativeAmountsOfActiveElectricEnergy(){return null;}
	/**
	 * 積算有効電力量計測値履歴<br>
	 * EPC                 : E7<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算有効電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3バイト目以降：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(){return null;}
	/**
	 * 月間最大需要電力<br>
	 * EPC                 : C1<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 前回確定日から最新の計測時刻までに記録した需要電力の計器指示値の最大値を示す。<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMonthlyMaximumElectricPowerDemand(){return null;}
	/**
	 * 累積最大需要電力<br>
	 * EPC                 : C2<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電力会社との契約上確定した最大需要電力の計器指示値の累積値を示す。<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppCumulativeMaximumElectricPowerDemand(){return null;}
	/**
	 * 定時需要電力(30分平均電力)<br>
	 * EPC                 : C3<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30分毎の計測時刻における需要電力(30分平均電力) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、需要電力(30分平均電力)4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・需要電力 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：需要電力<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppElectricPowerDemandAtFixedTime30MinAverage(){return null;}
	/**
	 * 需要電力有効桁数<br>
	 * EPC                 : C4<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : 桁<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 需要電力の有効桁数を示す。<br>
	 * 0x01～0x08<br>
	 * (1～8)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppEffectiveDigitsOfElectricPowerDemand(){return null;}
	/**
	 * 需要電力単位<br>
	 * EPC                 : C5<br>
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
	 * 月間最大需要電力、定時需要電力(30分平均電力)の単位を示す。<br>
	 * 0x00：1kW<br>
	 * 0x01：0.1kW<br>
	 * 0x02：0.01kW<br>
	 * 0x03：0.001kW<br>
	 * 0x04：0.0001kW<br>
	 * 0x0A：10kW<br>
	 * 0x0B：100kW<br>
	 * 0x0C：1000kW<br>
	 * 0x0D：10000kW<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppUnitOfElectricPowerDemand(){return null;}
	/**
	 * 需要電力計測値履歴<br>
	 * EPC                 : C6<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時需要電力(30分平均電力)の計器指示値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3バイト目以降：需要電力<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLogDataOfMeasuredElectricPowerDemand(){return null;}
	/**
	 * 累積最大需要電力単位<br>
	 * EPC                 : C7<br>
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
	 * 累積最大需要電力の単位を示す。<br>
	 * 0x00：1kW<br>
	 * 0x01：0.1kW<br>
	 * 0x02：0.01kW<br>
	 * 0x03：0.001kW<br>
	 * 0x04：0.0001kW<br>
	 * 0x0A：10kW<br>
	 * 0x0B：100kW<br>
	 * 0x0C：1000kW<br>
	 * 0x0D：10000kW<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppUnitOfCumulativeMaximumElectricPowerDemand(){return null;}
	/**
	 * 力測積算無効電力量(遅れ)計測値<br>
	 * EPC                 : CA<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kvarh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の計測時刻における力測積算無効電力量(遅れ) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算無効電力量(遅れ)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(){return null;}
	/**
	 * 定時力測積算無効電力量(遅れ)計測値<br>
	 * EPC                 : CB<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kvarh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30分毎の計測時刻における力測積算無効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算無効電力量(遅れ)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(){return null;}
	/**
	 * 力測積算無効電力量(遅れ)有効桁数<br>
	 * EPC                 : CC<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : 桁<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算無効電力量(遅れ)計測値の有効桁数を示す。<br>
	 * 0x01～0x08<br>
	 * (1～8)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(){return null;}
	/**
	 * 力測積算無効電力量(遅れ)単位<br>
	 * EPC                 : CD<br>
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
	 * 積算無効電力量(遅れ)計測値、履歴の単位(乗率)を示す。<br>
	 * 0x00：1kvarh<br>
	 * 0x01：0.1kvarh<br>
	 * 0x02：0.01kvarh<br>
	 * 0x03：0.001kvarh<br>
	 * 0x04：0.0001kvarh<br>
	 * 0x0A：10kvarh<br>
	 * 0x0B：100kvarh<br>
	 * 0x0C：1000kvarh<br>
	 * 0x0D：10000kvarh<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(){return null;}
	/**
	 * 力測積算無効電力量(遅れ)計測値履歴<br>
	 * EPC                 : CE<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kvarh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算無効電力量(遅れ)計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3バイト目以降：積算無効電力量(遅れ)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(){return null;}


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
	 * 状変アナウンス      :必須<br>
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
	 * 係数<br>
	 * EPC                 : D3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 計器の指示値を実使用kWh, kvarh, kWに換算する係数を10進表記において6桁で示す。<br>
	 * 0x00000000～0x000F423F<br>
	 * (000000～999999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtCoefficientValue(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "000F423F") <= 0)) return false;
		return true;
	}
	/**
	 * 係数の倍率<br>
	 * EPC                 : D4<br>
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
	 * 係数の倍率を示す。<br>
	 * 0x00：×1<br>
	 * 0x01：×0.1<br>
	 * 0x02：×0.01<br>
	 * 0x03：×0.001<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMultiplyingFactorForCoefficient(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "03") <= 0)) return false;
		return true;
	}
	/**
	 * 確定日<br>
	 * EPC                 : E0<br>
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
	 * 月間使用量等の確定日を示す。<br>
	 * 0x01～0x1F(1～31)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtFixedDate(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("01", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "1F") <= 0)) return false;
		return true;
	}
	/**
	 * 積算履歴収集日<br>
	 * EPC                 : E1<br>
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
	 * 30分毎の計測値履歴データを収集する日を示す。<br>
	 * 0x00～0x63(0～99)<br>
	 * 0：当日 1～99：前日の日数<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDayForRetreivingCumulativeLog(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "63") <= 0 ||  ElUtil.compElUnsignedChar(edt , "FF") == 0)) return false;
		return true;
	}
	/**
	 * 積算有効電力量計測値<br>
	 * EPC                 : E2<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日,時分秒,kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の計測時刻における積算有効電力量の計器指示値を10進表記において、最大8桁で示す。計測年月日を4バイト、計測時刻を3バイト、積算有効電力量を4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算有効電力量 10進表記で最大8桁<br>
	 * 1～4バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeAmountOfActiveElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 定時積算有効電力量計測値<br>
	 * EPC                 : E3<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30分毎の計測時刻における積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算有効電力量 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtCumulativeAmountOfActiveElectricEnergyAtFixedTime(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 力測積算有効電力量計測値<br>
	 * EPC                 : E4<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の計測時刻における力測積算有効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算有効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算有効電力量 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCumulativeValueOfActiveElectricEnergyForPowerFactorMeasurement(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 積算有効電力量有効桁数<br>
	 * EPC                 : E5<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : 桁<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算有効電力量計測値の有効桁数を示す。<br>
	 * 0x01～0x08<br>
	 * (1～8)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtEffectiveDigitsForCumulativeAmountOfActiveElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("01", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "08") <= 0)) return false;
		return true;
	}
	/**
	 * 積算有効電力量単位<br>
	 * EPC                 : E6<br>
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
	 * 積算有効電力量計測値、履歴の単位(乗率)を示す。<br>
	 * 0x00：1kWh<br>
	 * 	0x01：0.1kWh<br>
	 * 0x02：0.01kWh<br>
	 * 0x03：0.001kWh<br>
	 * 0x04：0.0001kWh<br>
	 * 0x0A：10kWh<br>
	 * 0x0B：100kWh<br>
	 * 0x0C：1000kWh<br>
	 * 0x0D：10000kWh<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtUnitForCumulativeAmountsOfActiveElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "04") <= 0
				|| ElUtil.compElUnsignedChar("0A", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "0D") <= 0)) return false;
		return true;
	}
	/**
	 * 積算有効電力量計測値履歴<br>
	 * EPC                 : E7<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算有効電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3バイト目以降：積算有効電力量<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLogDataOfMeasuredCumulativeAmountOfActiveElectricEnergy(String edt){
		if(edt == null || !(edt.length() == 194 * 2)) return false;
		if(! checkMeasuredLogValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 月間最大需要電力<br>
	 * EPC                 : C1<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 前回確定日から最新の計測時刻までに記録した需要電力の計器指示値の最大値を示す。<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMonthlyMaximumElectricPowerDemand(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(edt , "FFFFFFFE") == 0)) return false;
		return true;
	}
	/**
	 * 累積最大需要電力<br>
	 * EPC                 : C2<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電力会社との契約上確定した最大需要電力の計器指示値の累積値を示す。<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtCumulativeMaximumElectricPowerDemand(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(edt , "FFFFFFFE") == 0)) return false;
		return true;
	}
	/**
	 * 定時需要電力(30分平均電力)<br>
	 * EPC                 : C3<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30分毎の計測時刻における需要電力(30分平均電力) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、需要電力(30分平均電力)4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・需要電力 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：需要電力<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtElectricPowerDemandAtFixedTime30MinAverage(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 需要電力有効桁数<br>
	 * EPC                 : C4<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : 桁<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 需要電力の有効桁数を示す。<br>
	 * 0x01～0x08<br>
	 * (1～8)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtEffectiveDigitsOfElectricPowerDemand(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("01", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "08") <= 0)) return false;
		return true;
	}
	/**
	 * 需要電力単位<br>
	 * EPC                 : C5<br>
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
	 * 月間最大需要電力、定時需要電力(30分平均電力)の単位を示す。<br>
	 * 0x00：1kW<br>
	 * 0x01：0.1kW<br>
	 * 0x02：0.01kW<br>
	 * 0x03：0.001kW<br>
	 * 0x04：0.0001kW<br>
	 * 0x0A：10kW<br>
	 * 0x0B：100kW<br>
	 * 0x0C：1000kW<br>
	 * 0x0D：10000kW<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtUnitOfElectricPowerDemand(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "04") <= 0
				|| ElUtil.compElUnsignedChar("0A", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "0D") <= 0)) return false;
		return true;
	}
	/**
	 * 需要電力計測値履歴<br>
	 * EPC                 : C6<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kW<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時需要電力(30分平均電力)の計器指示値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3バイト目以降：需要電力<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLogDataOfMeasuredElectricPowerDemand(String edt){
		if(edt == null || !(edt.length() == 194 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(edt , "FFFFFFFE") == 0)) return false;
		return true;
	}
	/**
	 * 累積最大需要電力単位<br>
	 * EPC                 : C7<br>
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
	 * 累積最大需要電力の単位を示す。<br>
	 * 0x00：1kW<br>
	 * 0x01：0.1kW<br>
	 * 0x02：0.01kW<br>
	 * 0x03：0.001kW<br>
	 * 0x04：0.0001kW<br>
	 * 0x0A：10kW<br>
	 * 0x0B：100kW<br>
	 * 0x0C：1000kW<br>
	 * 0x0D：10000kW<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtUnitOfCumulativeMaximumElectricPowerDemand(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "04") <= 0
				|| ElUtil.compElUnsignedChar("0A", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "0D") <= 0)) return false;
		return true;
	}
	/**
	 * 力測積算無効電力量(遅れ)計測値<br>
	 * EPC                 : CA<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kvarh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の計測時刻における力測積算無効電力量(遅れ) の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算無効電力量(遅れ)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredDataOfReactiveElectricPowerLagForPowerFactorMeasurement(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 定時力測積算無効電力量(遅れ)計測値<br>
	 * EPC                 : CB<br>
	 * データタイプ        : unsigned short＋unsigned char×2＋unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日, 時分秒, kvarh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30分毎の計測時刻における力測積算無効電力量の計器指示値を、計測年月日を4バイト、計測時刻を3バイト、積算無効電力量4バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算無効電力量(遅れ) 10進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 * YYYY:0x0001～0x270F(1～9999)<br>
	 * MM:0x01～0x0C(1～12)<br>
	 * DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 * hh:0x00～0x17(0～23)<br>
	 * mm:0x00～0x3B(0～59)<br>
	 * ss:0x00～0x3B(0～59)<br>
	 * 8～11バイト目：積算無効電力量(遅れ)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredDataOfReactiveElectricPowerLagAtFixedTimie(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 力測積算無効電力量(遅れ)有効桁数<br>
	 * EPC                 : CC<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : 桁<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算無効電力量(遅れ)計測値の有効桁数を示す。<br>
	 * 0x01～0x08<br>
	 * (1～8)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtEffectiveDigitsForMeasuredDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("01", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "08") <= 0)) return false;
		return true;
	}
	/**
	 * 力測積算無効電力量(遅れ)単位<br>
	 * EPC                 : CD<br>
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
	 * 積算無効電力量(遅れ)計測値、履歴の単位(乗率)を示す。<br>
	 * 0x00：1kvarh<br>
	 * 0x01：0.1kvarh<br>
	 * 0x02：0.01kvarh<br>
	 * 0x03：0.001kvarh<br>
	 * 0x04：0.0001kvarh<br>
	 * 0x0A：10kvarh<br>
	 * 0x0B：100kvarh<br>
	 * 0x0C：1000kvarh<br>
	 * 0x0D：10000kvarh<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtUnitOfMeasurementDataOfCumulativeAmountOfReactiveElectricPowerLag(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "04") <= 0
				|| ElUtil.compElUnsignedChar("0A", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "0D") <= 0)) return false;
		return true;
	}
	/**
	 * 力測積算無効電力量(遅れ)計測値履歴<br>
	 * EPC                 : CE<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kvarh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日で指定した当該収集日の24時間48コマ分(0時0分～23時30分)の定時積算無効電力量(遅れ)計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3バイト目以降：積算無効電力量(遅れ)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtLogDataOfCumulativeAmountOfReactiveElectricPowerLagForPowerFactorMeasurement(String edt){
		if(edt == null || !(edt.length() == 194 * 2)) return false;
		if(! checkMeasuredLogValueFormat(edt)) return false;
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
	 * 状変アナウンス      :必須<br>
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
	 * 積算履歴収集日<br>
	 * EPC                 : E1<br>
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
	 * 30分毎の計測値履歴データを収集する日を示す。<br>
	 * 0x00～0x63(0～99)<br>
	 * 0：当日 1～99：前日の日数<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppDayForRetreivingCumulativeLog(String edt){return false;}

	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80", "動作状態");
			put("D3", "係数");
			put("D4", "係数の倍率");
			put("E0", "確定日");
			put("E1", "積算履歴収集日");
			put("E2", "積算有効電力量計測値");
			put("E3", "定時積算有効電力量計測値");
			put("E4", "力測積算有効電力量計測値");
			put("E5","積算有効電力量有効桁数");
			put("E6","積算有効電力量単位");
			put("E7","積算有効電力量計測値履歴");
			put("C1","月間最大需要電力");
			put("C2","累積最大需要電力");
			put("C3","定時需要電力(30分平均電力)");
			put("C4","需要電力有効桁数");
			put("C5","需要電力単位");
			put("C6","需要電力計測値履歴");
			put("C7","累積最大需要電力単位");
			put("CA","力測積算無効電力量(遅れ)計測値");
			put("CB","定時力測積算無効電力量(遅れ)計測値");
			put("CC","力測積算無効電力量(遅れ)有効桁数");
			put("CD","力測積算無効電力量(遅れ)単位");
			put("CE","力測積算無効電力量(遅れ)計測値履歴");
		}
	};
}
