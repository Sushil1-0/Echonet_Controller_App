package jp.co.smartsolar.smartedge.echonetlite.devices;

import jp.co.smartsolar.smartedge.echonetlite.*;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;
import java.util.HashMap;
import java.util.Map;

/**
 * Power Distribution Board Metering Class 分電盤メータリングクラス
 *
 * @author aashish
 */
public class PowerDistributionBoardMetering extends DeviceObjectSuperClass {

	/**
	 * Log output tag ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "PowerDistributionBoardMetering";

	/**
	 * Class group code クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "02";

	/**
	 * Class code クラスコード
	 */
	public static final String CLASS_CODE = "87";

	/****************************************
	 * PowerDistributionBoardMetering class property codes
	 ****************************************/

	/**
	 * EPC : 動作状態 (operation state)
	 */
	public static final String EPC_OPERATION_STATUS = "80";

	/**
	 * EDT : 計測チャンネル 1（ON）
	 */
	public static final String EDT_OPERATION_STATUS_ON = "30";

	/**
	 * EDT: 計測チャンネル 1（OFF）
	 */
	public static final String EDT_OPERATION_STATUS_OFF = "31";

	/**
	 * EPC: 積算電力量計測値（正方向） (measured cumulative amount of electric energy - normal
	 * direction)
	 */
	public static final String EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION = "C0";

	/**
	 * EPC: 積算電力量計測値（逆方向） (measured cumulative amount of electric energy - reverse
	 * direction)
	 */
	public static final String EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION = "C1";

	/**
	 * EPC:積算電力量単位 (unit for cumulative amount of electric energy)
	 */
	public static final String EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY = "C2";

	/**
	 * EPC: 積算電力量計測値履歴（正方向） (Historical data of measured cumulative amounts of
	 * electric energy (normal direction))
	 */
	public static final String EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION = "C3";

	/**
	 * EPC: 積算電力量計測値履歴（逆方向） (Historical data of measured cumulative amounts of
	 * electric energy (reverse direction) )
	 */
	public static final String EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION = "C4";

	/**
	 * EPC: 積算履歴収集日 (Day for which the historical data of measured cumulative
	 * amounts of electric energy is to be retrieved )
	 */
	public static final String EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY = "C5";

	/**
	 * EPC: 瞬時電力計測値 ( Measured instantaneous amount of electric energy )
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY = "C6";

	/**
	 * EPC: 瞬時電流計測値 (Measured instantaneous currents )
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_CURRENTS = "C7";

	/**
	 * EPC: 瞬時電圧計測値 (Measured instantaneous voltages )
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_VOLTAGES = "C8";

	/**
	 * EPC: 計測チャンネル 1 (Measurement channel 1 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_1 = "D0";

	/**
	 * EPC: 計測チャンネル 2 (Measurement channel 2 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_2 = "D1";

	/**
	 * EPC: 計測チャンネル 3 (Measurement channel 3 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_3 = "D2";

	/**
	 * EPC: 計測チャンネル 4 (Measurement channel 4 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_4 = "D3";

	/**
	 * EPC: 計測チャンネル 5 (Measurement channel 5 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_5 = "D4";

	/**
	 * EPC: 計測チャンネル 6 (Measurement channel 6 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_6 = "D5";

	/**
	 * EPC: 計測チャンネル 7 (Measurement channel 7 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_7 = "D6";

	/**
	 * EPC: 計測チャンネル 8 (Measurement channel 8 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_8 = "D7";

	/**
	 * EPC: 計測チャンネル 9 (Measurement channel 9 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_9 = "D8";

	/**
	 * EPC: 計測チャンネル 10 (Measurement channel 10 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_10 = "D9";

	/**
	 * EPC: 計測チャンネル 11 (Measurement channel 11 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_11 = "DA";

	/**
	 * EPC: 計測チャンネル 12 (Measurement channel 12 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_12 = "DB";

	/**
	 * EPC: 計測チャンネル 13 (Measurement channel 13 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_13 = "DC";

	/**
	 * EPC: 計測チャンネル 14 (Measurement channel 14 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_14 = "DD";

	/**
	 * EPC: 計測チャンネル 15 (Measurement channel 15 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_15 = "DE";

	/**
	 * EPC: 計測チャンネル 16 (Measurement channel 16 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_16 = "DF";

	/**
	 * EPC: 計測チャンネル 17 (Measurement channel 17 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_17 = "E0";

	/**
	 * EPC: 計測チャンネル 18 (Measurement channel 18 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_18 = "E1";

	/**
	 * EPC: 計測チャンネル 19 (Measurement channel 19 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_19 = "E2";

	/**
	 * EPC: 計測チャンネル 20 (Measurement channel 20 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_20 = "E3";

	/**
	 * EPC: 計測チャンネル 21 (Measurement channel 21 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_21 = "E4";

	/**
	 * EPC: 計測チャンネル 22 (Measurement channel 22 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_22 = "E5";

	/**
	 * EPC: 計測チャンネル 23 (Measurement channel 23 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_23 = "E6";

	/**
	 * EPC: 計測チャンネル 24 (Measurement channel 24 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_24 = "E7";

	/**
	 * EPC: 計測チャンネル 25 (Measurement channel 25 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_25 = "E8";

	/**
	 * EPC: 計測チャンネル 26 (Measurement channel 26 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_26 = "E9";

	/**
	 * EPC: 計測チャンネル 27 (Measurement channel 27 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_27 = "EA";

	/**
	 * EPC: 計測チャンネル 28 (Measurement channel 28 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_28 = "EB";

	/**
	 * EPC: 計測チャンネル 29 (Measurement channel 29 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_29 = "EC";

	/**
	 * EPC: 計測チャンネル 30 (Measurement channel 30 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_30 = "ED";

	/**
	 * EPC: 計測チャンネル 31 (Measurement channel 31 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_31 = "EE";

	/**
	 * EPC: 計測チャンネル 32 (Measurement channel 32 )
	 */
	public static final String EPC_MEASUREMENT_CHANNEL_32 = "EF";

	/**
	 * EPC: 主幹定格容量 (Master rated capacity)
	 */
	public static final String EPC_MASTER_RATED_CAPACITY = "B0";

	/**
	 * EPC: 計測チャンネル数（片方向） (Number of measurement channels (simplex))
	 */
	public static final String EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX = "B1";

	/**
	 * EPC: 積算電力量計測チャンネル範囲指定（片方向） (Channel range specification for cumulative amount
	 * of electric power consumption measurement (simplex))
	 */
	public static final String EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX = "B2";

	/**
	 * EPC: 積算電力量計測値リスト（片方向） (Measured cumulative amount of electric power
	 * consumption list (simplex))
	 */
	public static final String EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX = "B3";

	/**
	 * EPC: 瞬時電流計測チャンネル範囲指定（片方向） (Channel range specification for instantaneous
	 * current measurement (simplex))
	 */
	public static final String EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX = "B4";

	/**
	 * EPC: 瞬時電流計測値リスト（片方向） (Measured instantaneous current list (simplex))
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX = "B5";

	/**
	 * EPC: 瞬時電力計測チャンネル範囲指定（片方向） (Channel range specification for instantaneous
	 * power consumption measurement (simplex))
	 */
	public static final String EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX = "B6";

	/**
	 * EPC: 瞬時電力計測値リスト（片方向） (Measured instantaneous power consumption list
	 * (simplex))
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX = "B7";

	/**
	 * EPC: 計測チャンネル数（双方向） (Number of measurement channels (duplex))
	 */
	public static final String EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX = "B8";

	/**
	 * EPC: 積算電力量計測チャンネル範囲指定（双方向） (Channel range specification for cumulative amount
	 * of electric power consumption measurement (duplex))
	 */
	public static final String EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX = "B9";

	/**
	 * EPC: 積算電力量計測値リスト（双方向） (Measured cumulative amount of electric power
	 * consumption list (duplex))
	 */
	public static final String EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX = "BA";

	/**
	 * EPC: 瞬時電流計測チャンネル範囲指定（双方向） (Channel range specification for instantaneous
	 * current measurement (duplex))
	 */
	public static final String EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX = "BB";

	/**
	 * EPC: 瞬時電流計測値リスト（双方向） (Measured instantaneous current list (duplex))
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX = "BC";

	/**
	 * EPC: 瞬時電力計測チャンネル範囲指定（双方向） (Channel range specification for instantaneous
	 * power consumption measurement (duplex))
	 */
	public static final String EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX = "BD";

	/**
	 * EPC: 瞬時電力計測値リスト（双方向） (Measured instantaneous power consumption list (duplex))
	 */
	public static final String EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX = "BE";


	/*****************************************
	 * constructor
	 ****************************************/
	/**
	 * Construtor コンストラクター
	 */

	public PowerDistributionBoardMetering() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
//		setReportProcessor(new ReportProcessor());
	}

	/**
	 * Constructor コンストラクター
	 *
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public PowerDistributionBoardMetering(String entityCode) {
		super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void initPorpMaps() {
		super.initPorpMaps();
		// set property maps
		this.addMapSetProps(EPC_OPERATION_STATUS);
		this.addMapSetProps(EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY);
		this.addMapSetProps(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX);
		this.addMapSetProps(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX);
		this.addMapSetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX);
		this.addMapSetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX);
		this.addMapSetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX);
		this.addMapSetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX);

		// get property maps
		this.addMapGetProps(EPC_OPERATION_STATUS);
		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION);
		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION);
		this.addMapGetProps(EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION);
		this.addMapGetProps(EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION);
		this.addMapGetProps(EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_CURRENTS);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_VOLTAGES);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_1);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_2);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_3);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_4);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_5);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_6);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_7);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_8);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_9);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_10);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_11);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_12);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_13);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_14);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_15);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_16);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_17);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_18);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_19);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_20);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_21);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_22);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_23);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_24);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_25);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_26);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_27);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_28);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_29);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_30);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_31);
		this.addMapGetProps(EPC_MEASUREMENT_CHANNEL_32);
		this.addMapGetProps(EPC_MASTER_RATED_CAPACITY);
		this.addMapGetProps(EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX);
		this.addMapGetProps(EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX);

		this.addMapGetProps(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX);
		this.addMapGetProps(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX);
		this.addMapGetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX);
		this.addMapGetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX);
		this.addMapGetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX);
		this.addMapGetProps(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX);

		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX);
		this.addMapGetProps(EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX);
		this.addMapGetProps(EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX);

		// change state announcement property maps
		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void onNewEojFound() {
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewPowerDistributionBoardMeterFound(this);
	}


	/******************************************************
	 * property set, get and inf inner classes
	 ****************************************************/
	/**
	 * Aggregation class for SET processing Set系処理の集約クラス
	 * <p>
	 * 0x60 : プロパティ値書込み要求 （応答不要） Property Value Write Request (no response required)
	 * 0x61 : プロパティ値書込み要求 （応答要） Property Value Write Request (response required)
	 */
	public static class ElSetProps extends DeviceObjectSuperClass.ElSetProps {

		/**
		 * Log output tag ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "PowerDistributionBoardMetering.ElSetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElSetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj,
						  String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetOperationStatus(String edt) {
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, edt));
			return this;
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetDaysForHistoricalDataOfMeasuredCumulativeElectricEnergy(
				String edt) {
			listProperty.add(new ElProp(EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY, edt));
			return this;
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetChannelRangeCumulativeElectricPowerSimplex(String edt) {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX, edt));
			return this;
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetChannelRangeInstantaneousCurrentSimplex(String edt) {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX, edt));
			return this;
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetChannelRangeInstantaneousPowerSimplex(String edt) {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX, edt));
			return this;
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetChannelRangeCumulativeElectricPowerDuplex(String edt) {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX, edt));
			return this;
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetChannelRangeInstantaneousCurrentDuplex(String edt) {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX, edt));
			return this;
		}

		public PowerDistributionBoardMetering.ElSetProps reqSetChannelRangeInstantaneousPowerDuplex(String edt) {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX, edt));
			return this;
		}

	}

	/**
	 * Aggregation class for GET processing Get系処理の集約クラス
	 * <p>
	 * 0x62 : プロパティ値読出し要求 Property value read request 0x63 : プロパティ値通知要求 Property
	 * value notification request
	 */
	public static class ElGetProps extends DeviceObjectSuperClass.ElGetProps {

		/**
		 * Log output tag ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "PowerDistributionBoardMetering.ElGetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElGetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj,
						  String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetOperationStatus() {
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredCumulativeElectricEnergyNormalDirection() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredCumulativeElectricEnergyReverseDirection() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetUnitForCumulativeElectricEnergy() {
			listProperty.add(new ElProp(EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection() {
			listProperty.add(new ElProp(EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection() {
			listProperty.add(new ElProp(EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetDaysForHistoricalDataOfMeasuredCumulativeElectricEnergy() {
			listProperty.add(new ElProp(EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredInstantaneousElectricEnergy() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredInstantaneousCurrents() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_CURRENTS));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredInstantaneousVoltages() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_VOLTAGES));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel1() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_1));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel2() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_2));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel3() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_3));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel4() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_4));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel5() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_5));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel6() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_6));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel7() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_7));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel8() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_8));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel9() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_9));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel10() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_10));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel11() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_11));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel12() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_12));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel13() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_13));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel14() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_14));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel15() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_15));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel16() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_16));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel17() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_17));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel18() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_18));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel19() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_19));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel20() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_20));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel21() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_21));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel22() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_22));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel23() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_23));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel24() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_24));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel25() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_25));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel26() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_26));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel27() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_27));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel28() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_28));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel29() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_29));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel30() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_30));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel31() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_31));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasurementChannel32() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_32));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMasterRatedCapacity() {
			listProperty.add(new ElProp(EPC_MASTER_RATED_CAPACITY));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetNoOfMeasurementChannelSimplex() {
			listProperty.add(new ElProp(EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetNoOfMeasurementChannelDuplex() {
			listProperty.add(new ElProp(EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetChannelRangeCumulativeElectricPowerSimplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetChannelRangeCumulativeElectricPowerDuplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetChannelRangeInstantaneousCurrentSimplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetChannelRangeInstantaneousCurrentDuplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetChannelRangeInstantaneousPowerSimplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetChannelRangeInstantaneousPowerDuplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredCumulativeElectricPowerListSimplex() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredCumulativeElectricPowerListDuplex() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredInstantaneousCurrentListSimplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredInstantaneousCurrentListDuplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredInstantaneousPowerListSimplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElGetProps reqGetMeasuredInstantaneousPowerListDuplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX));
			return this;
		}

	}

	/**
	 * Aggregation class for NOTIFICATION processing 通知系処理の集約クラス
	 * <p>
	 * 0x73 : プロパティ値通知 Property value notification 0x74 : プロパティ値通知（応答要） Property
	 * value notification (response required)
	 */
	public static class ElInformProps extends DeviceObjectSuperClass.ElInformProps {

		/**
		 * Log output tag ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "PowerDistributionBoardMetering.ElInformProps";

		/**
		 * {@inheritDoc}
		 */
		public ElInformProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj,
							 String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfOperationStatus() {
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredCumulativeElectricEnergyNormalDirection() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredCumulativeElectricEnergyReverseDirection() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfUnitForCumulativeElectricEnergy() {
			listProperty.add(new ElProp(EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection() {
			listProperty.add(new ElProp(EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection() {
			listProperty
					.add(new ElProp(EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfDaysForHistoricalDataOfMeasuredCumulativeElectricEnergy() {
			listProperty.add(new ElProp(EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredInstantaneousElectricEnergy() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredInstantaneousCurrents() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_CURRENTS));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredInstantaneousVoltages() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_VOLTAGES));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel1() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_1));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel2() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_2));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel3() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_3));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel4() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_4));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel5() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_5));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel6() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_6));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel7() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_7));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel8() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_8));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel9() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_9));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel10() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_10));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel11() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_11));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel12() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_12));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel13() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_13));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel14() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_14));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel15() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_15));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel16() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_16));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel17() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_17));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel18() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_18));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel19() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_19));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel20() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_20));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel21() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_21));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel22() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_22));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel23() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_23));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel24() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_24));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel25() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_25));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel26() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_26));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel27() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_27));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel28() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_28));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel29() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_29));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel30() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_30));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel31() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_31));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasurementChannel32() {
			listProperty.add(new ElProp(EPC_MEASUREMENT_CHANNEL_32));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMasterRatedCapacity() {
			listProperty.add(new ElProp(EPC_MASTER_RATED_CAPACITY));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfNoOfMeasurementChannelSimplex() {
			listProperty.add(new ElProp(EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfNoOfMeasurementChannelDuplex() {
			listProperty.add(new ElProp(EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfChannelRangeCumulativeElectricPowerSimplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfChannelRangeCumulativeElectricPowerDuplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfChannelRangeInstantaneousCurrentSimplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfChannelRangeInstantaneousCurrentDuplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfChannelRangeInstantaneousPowerSimplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfChannelRangeInstantaneousPowerDuplex() {
			listProperty.add(new ElProp(EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredCumulativeElectricPowerListSimplex() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredCumulativeElectricPowerListDuplex() {
			listProperty.add(new ElProp(EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredInstantaneousCurrentListSimplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredInstantaneousCurrentListDuplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredInstantaneousPowerListSimplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX));
			return this;
		}

		public PowerDistributionBoardMetering.ElInformProps reqInfMeasuredInstantaneousPowerListDuplex() {
			listProperty.add(new ElProp(EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX));
			return this;
		}

	}

	/******************************************
	 * setI properties - all
	 *****************************************/

	public static PowerDistributionBoardMetering.ElSetProps setIAll(ElProcess elProcess) {
		return setIAll(elProcess, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
		return setIAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
		return setIAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	public static PowerDistributionBoardMetering.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new PowerDistributionBoardMetering.ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj,
				new PowerDistributionBoardMetering(instanceCode), ElFrame.ESV_SETI);
	}

	/*****************************************
	 * setC properties - all
	 *****************************************/

	public static PowerDistributionBoardMetering.ElSetProps setCAll(ElProcess elProcess) {
		return setCAll(elProcess, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
		return setCAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
		return setCAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	public static PowerDistributionBoardMetering.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj,
																	String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj,
				new PowerDistributionBoardMetering(instanceCode), ElFrame.ESV_SETC);
	}

	/*****************************************
	 * get properties - all
	 *****************************************/

	public static PowerDistributionBoardMetering.ElGetProps getAll(ElProcess elProcess) {
		return getAll(elProcess, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj) {
		return getAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
		return getAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	public static PowerDistributionBoardMetering.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj,
																   String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj,
				new PowerDistributionBoardMetering(instanceCode), ElFrame.ESV_GET);
	}

	/*****************************************
	 * inf_req properties - all
	 *****************************************/

	public static PowerDistributionBoardMetering.ElInformProps infReqAll(ElProcess elProcess) {
		return infReqAll(elProcess, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj) {
		return infReqAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	public static PowerDistributionBoardMetering.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
		return infReqAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	public static PowerDistributionBoardMetering.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj,
																		 String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj,
				new PowerDistributionBoardMetering(instanceCode), ElFrame.ESV_INF_REQ);
	}

	/*****************************************
	 * setI property
	 *****************************************/

	@Override
	public PowerDistributionBoardMetering.ElSetProps setI() {
		return setI(getLocalNodeProfile(getElProcess()));
	}

	@Override
	public PowerDistributionBoardMetering.ElSetProps setI(ElClassBase seoj) {
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETI);
	}

	/*****************************************
	 * setC property
	 *****************************************/

	@Override
	public PowerDistributionBoardMetering.ElSetProps setC() {
		return setC(getLocalNodeProfile(getElProcess()));
	}

	@Override
	public PowerDistributionBoardMetering.ElSetProps setC(ElClassBase seoj) {
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETC);
	}

	/*****************************************
	 * get property
	 *****************************************/

	@Override
	public PowerDistributionBoardMetering.ElGetProps get() {
		return get(getLocalNodeProfile(getElProcess()));
	}

	@Override
	public PowerDistributionBoardMetering.ElGetProps get(ElClassBase seoj) {
		return new ElGetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_GET);
	}

	/*****************************************
	 * inf_req property
	 *****************************************/

	@Override
	public PowerDistributionBoardMetering.ElInformProps infReq() {
		return this.infReq(getLocalNodeProfile(getElProcess()));
	}

	@Override
	public PowerDistributionBoardMetering.ElInformProps infReq(ElClassBase seoj) {
		return new ElInformProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this,
				ElFrame.ESV_INF_REQ);
	}

	/*****************************************
	 * inf property
	 *****************************************/

	@Override
	public PowerDistributionBoardMetering.ElInformProps inf() {
		return inf(new NodeProfileClass());// INSTANCE_CODE_GENERAL
	}

	@Override
	public PowerDistributionBoardMetering.ElInformProps inf(ElClassBase deoj) {

		boolean isWithMuticalst = false;
		// deojで所属するノードのIPアドレスが取得できない場合は、無条件にマルチキャストとする。
		if (deoj.getNodeBelongsTo() == null 
				|| deoj.getNodeBelongsTo().getIpAddress() == null
				|| deoj.getNodeBelongsTo().getIpAddress().isEmpty()) {
			isWithMuticalst = true;
		}

		// Destination IP
		// 宛先IP
		String sIp = getElProcess().getChannel().getMultiCastAddress();
		ElClassBase objDeoj = deoj;
		if (isWithMuticalst) {
			// DEOJのエンティティコード部分を00に変更する。
			// 下記により、管理下のDEOJオブジェクトを変更せずに新規でEOJを生成する
			// objDeoj = ElClassBase.instanceEojByCode(deoj.getStrClassGroupCode(),
			// deoj.getStrClassCode(), ElClassBase.INSTANCE_CODE_ALL);
		} else {
			sIp = deoj.getNodeBelongsTo().getIpAddress();
		}
		return new ElInformProps(getElProcess(), sIp, this, objDeoj, ElFrame.ESV_INF);
	}

	/*****************************************
	 * infC property
	 *****************************************/

	public PowerDistributionBoardMetering.ElInformProps infC(String remoteIpAddress) {
		return infC(remoteIpAddress, new NodeProfileClass());// INSTANCE_CODE_GENERAL
	}

	@Override
	public PowerDistributionBoardMetering.ElInformProps infC(String remoteIpAddress, ElClassBase deoj) {
		return new ElInformProps(getElProcess(), remoteIpAddress, this, deoj, ElFrame.ESV_INFC);
	}

	/*****************************************
	 * EPC Property Map EPC 名称マップ
	 ****************************************/
	public static Map<String, String> _MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80", "動作状態");
			put("C0", "積算電力量計測値（正方向）");
			put("C1", "積算電力量計測値（逆方向）");
			put("C2", "積算電力量単位");
			put("C3", "積算電力量計測値履歴（正方向）");
			put("C4", "積算電力量計測値履歴（逆方向）");
			put("C5", "積算履歴収集日");
			put("C6", "瞬時電力計測値");
			put("C7", "瞬時電流計測値");
			put("C8", "瞬時電圧計測値");
			put("D0", "計測チャンネル 1");
			put("D1", "計測チャンネル 2");
			put("D2", "計測チャンネル 3");
			put("D3", "計測チャンネル 4");
			put("D4", "計測チャンネル 5");
			put("D5", "計測チャンネル 6");
			put("D6", "計測チャンネル 7");
			put("D7", "計測チャンネル 8");
			put("D8", "計測チャンネル 9");
			put("D9", "計測チャンネル 10");
			put("DA", "計測チャンネル 11");
			put("DB", "計測チャンネル 12");
			put("DC", "計測チャンネル 13");
			put("DD", "計測チャンネル 14");
			put("DE", "計測チャンネル 15");
			put("DF", "計測チャンネル 16");
			put("E0", "計測チャンネル 17");
			put("E1", "計測チャンネル 18");
			put("E2", "計測チャンネル 19");
			put("E3", "計測チャンネル 20");
			put("E4", "計測チャンネル 21");
			put("E5", "計測チャンネル 22");
			put("E6", "計測チャンネル 23");
			put("E7", "計測チャンネル 24");
			put("E8", "計測チャンネル 25");
			put("E9", "計測チャンネル 26");
			put("EA", "計測チャンネル 27");
			put("EB", "計測チャンネル 28");
			put("EC", "計測チャンネル 29");
			put("ED", "計測チャンネル 30");
			put("EE", "計測チャンネル 31");
			put("EF", "計測チャンネル 32");
			put("B0", "主幹定格容量");
			put("B1", "計測チャンネル数（片方向）");
			put("B2", "積算電力量計測チャンネル範囲指定（片方向）");
			put("B3", "積算電力量計測値リスト（片方向）");
			put("B4", "瞬時電流計測チャンネル範囲指定（片方向）");
			put("B5", "瞬時電流計測値リスト（片方向）");
			put("B6", "瞬時電力計測チャンネル範囲指定（片方向）");
			put("B7", "瞬時電力計測値リスト（片方向）");
			put("B8", "計測チャンネル数（双方向）");
			put("B9", "積算電力量計測チャンネル範囲指定（双方向）");
			put("BA", "積算電力量計測値リスト（双方向）");
			put("BB", "瞬時電流計測チャンネル範囲指定（双方向）");
			put("BC", "瞬時電流計測値リスト（双方向）");
			put("BD", "瞬時電力計測チャンネル範囲指定（双方向）");
			put("BE", "瞬時電力計測値リスト（双方向）");
		}
	};

	/*******************************************
	 * TODO Method to get the EDT values from the application TODO
	 * アプリケーションより値を取得するメソッド群
	 *******************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized String getEdtValueFromApp(String epc) {
		String retEdt = super.getEdtValueFromApp(epc);
		if (retEdt != null) {
			return retEdt;
		}
		switch (epc) {
		case EPC_OPERATION_STATUS:
			return this.getFromAppOperationStatus();
		case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
			return this.getFromAppMeasuredCumulativeElectricEnergyNormalDirection();
		case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
			return this.getFromAppMeasuredCumulativeElectricEnergyReverseDirection();
		case EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY:
			return this.getFromAppUnitForCumulativeElectricEnergy();
		case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
			return this.getFromAppHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection();
		case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
			return this.getFromAppHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection();
		case EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY:
			return this.getFromAppDaysForHistoricalDataOfMeasurecCumulativeElectricEnergy();
		case EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY:
			return this.getFromAppMeasuredInstantaneousElectricEnergy();
		case EPC_MEASURED_INSTANTANEOUS_CURRENTS:
			return this.getFromAppMeasuredInstantaneousCurrents();
		case EPC_MEASURED_INSTANTANEOUS_VOLTAGES:
			return this.getFromAppMeasuredInstantaneousVoltages();
		case EPC_MEASUREMENT_CHANNEL_1:
			return this.getFromAppMeasurementChannel1();
		case EPC_MEASUREMENT_CHANNEL_2:
			return this.getFromAppMeasurementChannel2();
		case EPC_MEASUREMENT_CHANNEL_3:
			return this.getFromAppMeasurementChannel3();
		case EPC_MEASUREMENT_CHANNEL_4:
			return this.getFromAppMeasurementChannel4();
		case EPC_MEASUREMENT_CHANNEL_5:
			return this.getFromAppMeasurementChannel5();
		case EPC_MEASUREMENT_CHANNEL_6:
			return this.getFromAppMeasurementChannel6();
		case EPC_MEASUREMENT_CHANNEL_7:
			return this.getFromAppMeasurementChannel7();
		case EPC_MEASUREMENT_CHANNEL_8:
			return this.getFromAppMeasurementChannel8();
		case EPC_MEASUREMENT_CHANNEL_9:
			return this.getFromAppMeasurementChannel9();
		case EPC_MEASUREMENT_CHANNEL_10:
			return this.getFromAppMeasurementChannel10();
		case EPC_MEASUREMENT_CHANNEL_11:
			return this.getFromAppMeasurementChannel11();
		case EPC_MEASUREMENT_CHANNEL_12:
			return this.getFromAppMeasurementChannel12();
		case EPC_MEASUREMENT_CHANNEL_13:
			return this.getFromAppMeasurementChannel13();
		case EPC_MEASUREMENT_CHANNEL_14:
			return this.getFromAppMeasurementChannel14();	
		case EPC_MEASUREMENT_CHANNEL_15:
			return this.getFromAppMeasurementChannel15();
		case EPC_MEASUREMENT_CHANNEL_16:
			return this.getFromAppMeasurementChannel16();
		case EPC_MEASUREMENT_CHANNEL_17:
			return this.getFromAppMeasurementChannel17();
		case EPC_MEASUREMENT_CHANNEL_18:
			return this.getFromAppMeasurementChannel18();
		case EPC_MEASUREMENT_CHANNEL_19:
			return this.getFromAppMeasurementChannel19();
		case EPC_MEASUREMENT_CHANNEL_20:
			return this.getFromAppMeasurementChannel20();
		case EPC_MEASUREMENT_CHANNEL_21:
			return this.getFromAppMeasurementChannel21();
		case EPC_MEASUREMENT_CHANNEL_22:
			return this.getFromAppMeasurementChannel22();
		case EPC_MEASUREMENT_CHANNEL_23:
			return this.getFromAppMeasurementChannel23();
		case EPC_MEASUREMENT_CHANNEL_24:
			return this.getFromAppMeasurementChannel24();
		case EPC_MEASUREMENT_CHANNEL_25:
			return this.getFromAppMeasurementChannel25();
		case EPC_MEASUREMENT_CHANNEL_26:
			return this.getFromAppMeasurementChannel26();
		case EPC_MEASUREMENT_CHANNEL_27:
			return this.getFromAppMeasurementChannel27();
		case EPC_MEASUREMENT_CHANNEL_28:
			return this.getFromAppMeasurementChannel28();
		case EPC_MEASUREMENT_CHANNEL_29:
			return this.getFromAppMeasurementChannel29();
		case EPC_MEASUREMENT_CHANNEL_30:
			return this.getFromAppMeasurementChannel30();
		case EPC_MEASUREMENT_CHANNEL_31:
			return this.getFromAppMeasurementChannel31();
		case EPC_MEASUREMENT_CHANNEL_32:
			return this.getFromAppMeasurementChannel32();
		case EPC_MASTER_RATED_CAPACITY:
			return this.getFromAppMasterRatedCapacity();
		case EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX:
			return this.getFromAppNoOfMeasurementChannelsSimplex();
		case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX:
			return this.getFromAppChannelRangeCumulativeElectricPowerSimplex();
		case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX:
			return this.getFromAppMeasuredCumulativeElectricPowerListSimplex();
		case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX:
			return this.getFromAppChannelRangeInstantaneousCurrentSimplex();
		case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX:
			return this.getFromAppMeasuredInstaneousCurrentListSimplex();
		case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX:
			return this.getFromAppChannelRangeInstantaneousPowerSimplex();
		case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX:
			return this.getFromAppMeasuredInstantaneousPowerListSimplex();
		case EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX:
			return this.getFromAppNoOfMeasurementChannelDuplex();
		case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX:
			return this.getFromAppChannelRangeCumulativeElectricPowerDuplex();
		case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX:
			return this.getFromAppMeasuredCumulativeElectricPowerListDuplex();
		case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX:
			return this.getFromAppChannelRangeInstantaneousCurrentDuplex();
		case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX:
			return this.getFromAppMeasuredInstantaneousCurrentListDuplex();
		case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX:
			return this.getFromAppChannelRangeInstantaneousPowerDuplex();
		case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX:
			return this.getFromAppMeasuredInstantaneousPowerListDuplex();
		default:
			return null;
		}
	}

	protected String getFromAppMeasuredCumulativeElectricEnergyNormalDirection() {return null;}
	protected String getFromAppMeasuredCumulativeElectricEnergyReverseDirection() {return null;}
	protected String getFromAppUnitForCumulativeElectricEnergy() {return null;}
	protected String getFromAppHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection() {return null;}
	protected String getFromAppHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection() {return null;}
	protected String getFromAppDaysForHistoricalDataOfMeasurecCumulativeElectricEnergy() {return null;}
	protected String getFromAppMeasuredInstantaneousElectricEnergy() {return null;}
	protected String getFromAppMeasuredInstantaneousCurrents() {return null;}
	protected String getFromAppMeasuredInstantaneousVoltages() {return null;}
	
	protected String getFromAppMeasurementChannel1() {return null;}
	protected String getFromAppMeasurementChannel2() {return null;}
	protected String getFromAppMeasurementChannel3() {return null;}
	protected String getFromAppMeasurementChannel4() {return null;}
	protected String getFromAppMeasurementChannel5() {return null;}
	protected String getFromAppMeasurementChannel6() {return null;}
	protected String getFromAppMeasurementChannel7() {return null;}
	protected String getFromAppMeasurementChannel8() {return null;}
	protected String getFromAppMeasurementChannel9() {return null;}
	protected String getFromAppMeasurementChannel10() {return null;}
	protected String getFromAppMeasurementChannel11() {return null;}
	protected String getFromAppMeasurementChannel12() {return null;}
	protected String getFromAppMeasurementChannel13() {return null;}
	protected String getFromAppMeasurementChannel14() {return null;}
	protected String getFromAppMeasurementChannel15() {return null;}
	protected String getFromAppMeasurementChannel16() {return null;}
	protected String getFromAppMeasurementChannel17() {return null;}
	protected String getFromAppMeasurementChannel18() {return null;}
	protected String getFromAppMeasurementChannel19() {return null;}
	protected String getFromAppMeasurementChannel20() {return null;}
	protected String getFromAppMeasurementChannel21() {return null;}
	protected String getFromAppMeasurementChannel22() {return null;}
	protected String getFromAppMeasurementChannel23() {return null;}
	protected String getFromAppMeasurementChannel24() {return null;}
	protected String getFromAppMeasurementChannel25() {return null;}
	protected String getFromAppMeasurementChannel26() {return null;}
	protected String getFromAppMeasurementChannel27() {return null;}
	protected String getFromAppMeasurementChannel28() {return null;}
	protected String getFromAppMeasurementChannel29() {return null;}
	protected String getFromAppMeasurementChannel30() {return null;}
	protected String getFromAppMeasurementChannel31() {return null;}
	protected String getFromAppMeasurementChannel32() {return null;}
	
	protected String getFromAppMasterRatedCapacity() {return null;}
	protected String getFromAppNoOfMeasurementChannelsSimplex() {return null;}
	protected String getFromAppChannelRangeCumulativeElectricPowerSimplex() {return null;}
	protected String getFromAppMeasuredCumulativeElectricPowerListSimplex() {return null;}
	protected String getFromAppChannelRangeInstantaneousCurrentSimplex() {return null;}
	protected String getFromAppMeasuredInstaneousCurrentListSimplex() {return null;}
	protected String getFromAppChannelRangeInstantaneousPowerSimplex() {return null;}
	protected String getFromAppMeasuredInstantaneousPowerListSimplex() {return null;}
	protected String getFromAppNoOfMeasurementChannelDuplex() {return null;}
	protected String getFromAppChannelRangeCumulativeElectricPowerDuplex() {return null;}
	protected String getFromAppMeasuredCumulativeElectricPowerListDuplex() {return null;}
	protected String getFromAppChannelRangeInstantaneousCurrentDuplex() {return null;}
	protected String getFromAppMeasuredInstantaneousCurrentListDuplex() {return null;}
	protected String getFromAppChannelRangeInstantaneousPowerDuplex() {return null;}
	protected String getFromAppMeasuredInstantaneousPowerListDuplex() {return null;}
	
	/*******************************************
	 * TODO Methods to verify the validity of the value obtained from the
	 * application TODO アプリケーションから取得した値の妥当性を検証するメソッド群
	 *******************************************/

	protected boolean isValidEdtMeasuredCumulativeElectricEnergyNormalDirection(String edt) { 
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredCumulativeElectricEnergyReverseDirection(String edt) { 
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtUnitForCumulativeElectricEnergy(String edt) { 
		if (edt == null || !(edt.length() == 1 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("00", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "04") <= 0 || 
				ElUtil.compElUnsignedChar("0A",	edt) <= 0 && 
				ElUtil.compElUnsignedChar(edt, "0D") <= 0)) 
			return false;
		return true; 
	}

	protected boolean isValidEdtHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection(String edt) { 
		if (edt == null || !(edt.length() == 194 * 2)) return false;
		if (!checkMeasuredHistoricalDataFormat(edt)) return false;
		return true; 
	}

	protected boolean isValidEdtHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection(String edt) {
		if (edt == null || !(edt.length() == 194 * 2)) return false;
		if (!checkMeasuredHistoricalDataFormat(edt)) return false;
		return true; 
	}

	protected boolean isValidEdtDaysForHistoricalDataOfMeasuredCumulativeElectricEnergy(String edt){
		if (edt == null || !(edt.length() == 1 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("00", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "63") <= 0 || 
				ElUtil.compElUnsignedChar(edt, "FF") == 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredInstantaneousElectricEnergy(String edt) {
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElSignedLong("80000001", edt) <= 0 &&
				ElUtil.compElSignedLong(edt, "7FFFFFFD") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredInstantaneousCurrents(String edt) { 
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredInstantaneousVoltages(String edt) { 
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel1(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel2(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel3(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel4(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel5(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel6(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel7(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel8(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel9(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null	|| !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel10(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel11(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel12(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel13(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel14(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel15(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel16(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel17(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel18(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel19(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel20(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel21(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel22(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel23(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel24(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel25(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel26(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel27(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel28(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel29(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel30(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel31(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasurementChannel32(String edt) { 
		edt =  edt.substring(0, 8);
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMasterRatedCapacity(String edt) { 
		if (edt == null || !(edt.length() == 1 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("00", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "FD") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtNoOfMeasurementChannelsSimplex(String edt) { 
		if (edt == null || !(edt.length() == 1 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("01", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "FC") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtChannelRangeCumulativeElectricPowerSimplex(String edt) { 
		if (edt == null || !(edt.length() == 2 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "05F5E0FF") <= 0))  return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredCumulativeElectricPowerListSimplex(String edt) { 
		if (edt == null || !(edt.length() == 242 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0))  return false;
		return true; 
	}

	protected boolean isValidEdtChannelRangeInstantaneousCurrentSimplex(String edt) { 
		if (edt == null || !(edt.length() == 2 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0))  return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredInstantaneousCurrentListSimplex(String edt) { 
		if (edt == null || !(edt.length() == 242 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0))  return false;
		return true; 
	}

	protected boolean isValidEdtChannelRangeInstantaneousPowerSimplex(String edt) { 
		if (edt == null || !(edt.length() == 2 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredInstantaneousPowerListSimplex(String edt) {
		if (edt == null || !(edt.length() == 242 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtNoOfMeasurementChannelDuplex(String edt) { 
		if (edt == null || !(edt.length() == 1 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("01", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "FC") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtChannelRangeCumulativeElectricPowerDuplex(String edt) { 
		if (edt == null || !(edt.length() == 4 * 2)) return false;
		if (!(ElUtil.compElUnsignedChar("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedChar(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredCumulativeElectricPowerListDuplex(String edt) { 
		if (edt == null || !(edt.length() == 242 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtChannelRangeInstantaneousCurrentDuplex(String edt) { 
		if (edt == null || !(edt.length() == 2 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredInstantaneousCurrentListDuplex(String edt) { 
		if (edt == null || !(edt.length() == 242 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtChannelRangeInstantaneousPowerDuplex(String edt) { 
		if (edt == null || !(edt.length() == 2 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0)) return false;
		return true; 
	}

	protected boolean isValidEdtMeasuredInstantaneousPowerListDuplex(String edt){ 
		if (edt == null || !(edt.length() == 242 * 2)) return false;
		if (!(ElUtil.compElUnsignedLong("00000000", edt) <= 0 &&
				ElUtil.compElUnsignedLong(edt, "05F5E0FF") <= 0))  return false;
		return true; 
	}
	 

	/**
	 * Helper method to validate the historical data of measured cumulative amount
	 * of electric energy
	 */
	private boolean checkMeasuredHistoricalDataFormat(String strEdt) {
		String strDate = strEdt.substring(0, 4); // 1 unsigned short => 2 bytes
		String strValues = strEdt.substring(4); // 48 unsigned long => 96 bytes

		if (!(ElUtil.compElUnsignedShort("0000", strDate) <= 0 && ElUtil.compElUnsignedShort(strDate, "0063") <= 0))
			return false;

		for (int i = 0; i < 48; i++) {
			String strVal = strValues.substring(i * 4 * 2, (i + 1) * 4 * 2);
			if (!(ElUtil.compElUnsignedLong("00000000", strVal) <= 0
					&& ElUtil.compElUnsignedLong(strVal, "05F5E0FF") <= 0
					|| ElUtil.compElUnsignedLong(strVal, "FFFFFFFE") == 0))
				return false;
		}
		return true;
	}


	/***********************************************
	 * report processor for power distribution board metering
	 **********************************************/
	/**
	 * TODO Processing class when receiving response and notification frames
	 * 応答、通知系フレームの受信時の処理用クラス
	 */
	public static class ReportProcessor extends ElClassBase.ReportProcessor {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "PowerDistributionBoardMetering.ReportProcessor";

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfSet(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,
													boolean isSuccess) {
			super.onReceivePropertyReportOfSet(seoj, strTid, strEsv, objProp, isSuccess);
			switch (objProp.getStrEpc()) {
				case EPC_OPERATION_STATUS:
					onSetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY:
					onSetDaysForHistoricalDataOfMeasuredCumulativeElectricEnergy(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX:
					onSetChannelRangeCumulativeElectricPowerSimplex(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX:
					onSetChannelRangeInstantaneousCurrentSimplex(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX:
					onSetChannelRangeInstantaneousPowerSimplex(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX:
					onSetChannelRangeCumulativeElectricPowerDuplex(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX:
					onSetChannelRangeInstantaneousCurrentDuplex(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX:
					onSetChannelRangeInstantaneousPowerDuplex(seoj, strTid, strEsv, objProp, isSuccess);
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
			super.onReceivePropertyReportOfInfC(seoj, strTid, strEsv, objProp);
			switch (objProp.getStrEpc()) {
			case EPC_OPERATION_STATUS:
				onInformOperationStatus(seoj, strTid, strEsv, objProp);
				return;	
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
				onInformMeasuredCumulativeElectricEnergyNormalDirection(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
				onInformMeasuredCumulativeElectricEnergyReverseDirection(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY:
				onInformUnitForCumulativeElectricEnergy(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
				onInformHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
				onInformHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY:
				onInformDaysForHistoricalDataOfMeasurecCumulativeElectricEnergy(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY:
				onInformMeasuredInstantaneousElectricEnergy(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_INSTANTANEOUS_CURRENTS:
				onInformMeasuredInstantaneousCurrents(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_INSTANTANEOUS_VOLTAGES:
				onInformMeasuredInstantaneousVoltages(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_1:
				onInformMeasurementChannelValue1(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_2:
				onInformMeasurementChannelValue2(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_3:
				onInformMeasurementChannelValue3(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_4:
				onInformMeasurementChannelValue4(seoj, strEsv, strEsv, objProp);	
				return;
			case EPC_MEASUREMENT_CHANNEL_5:
				onInformMeasurementChannelValue5(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_6:
				onInformMeasurementChannelValue6(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_7:
				onInformMeasurementChannelValue7(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_8:
				onInformMeasurementChannelValue8(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_9:
				onInformMeasurementChannelValue9(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_10:
				onInformMeasurementChannelValue10(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_11:
				onInformMeasurementChannelValue11(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_12:
				onInformMeasurementChannelValue12(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_13:
				onInformMeasurementChannelValue13(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_14:
				onInformMeasurementChannelValue14(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_15:
				onInformMeasurementChannelValue15(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_16:
				onInformMeasurementChannelValue16(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_17:
				onInformMeasurementChannelValue17(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_18:
				onInformMeasurementChannelValue18(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_19:
				onInformMeasurementChannelValue19(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_20:
				onInformMeasurementChannelValue20(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_21:
				onInformMeasurementChannelValue21(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_22:
				onInformMeasurementChannelValue22(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_23:
				onInformMeasurementChannelValue23(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_24:
				onInformMeasurementChannelValue24(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_25:
				onInformMeasurementChannelValue25(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_26:
				onInformMeasurementChannelValue26(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_27:
				onInformMeasurementChannelValue27(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_28:
				onInformMeasurementChannelValue28(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_29:
				onInformMeasurementChannelValue29(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_30:
				onInformMeasurementChannelValue30(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_31:
				onInformMeasurementChannelValue31(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASUREMENT_CHANNEL_32:
				onInformMeasurementChannelValue32(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MASTER_RATED_CAPACITY:
				onInformMasterRatedCapacity(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX:
				onInformNoOfMeasurementChannelsSimplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX:
				onInformChannelRangeCumulativeElectricPowerSimplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX:
				onInformMeasuredCumulativeElectricPowerListSimplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX:
				onInformChannelRangeInstantaneousCurrentSimplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX:
				onInformMeasuredInstaneousCurrentListSimplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX:
				onInformChannelRangeInstantaneousPowerSimplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX:
				onInformMeasuredInstantaneousPowerListSimplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX:
				onInformNoOfMeasurementChannelDuplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX:
				onInformChannelRangeCumulativeElectricPowerDuplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX:
				onInformMeasuredCumulativeElectricPowerListDuplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX:
				onInformChannelRangeInstantaneousCurrentDuplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX:
				onInformMeasuredInstantaneousCurrentListDuplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX:
				onInformChannelRangeInstantaneousPowerDuplex(seoj, strEsv, strEsv, objProp);
				return;
			case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX:
				onInformMeasuredInstantaneousPowerListDuplex(seoj, strEsv, strEsv, objProp);
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
			super.onReceivePropertyReportOfGetOrInform(seoj, strTid, strEsv, objProp, isSuccess);
			switch (objProp.getStrEpc()) {
				case EPC_OPERATION_STATUS:
					onGetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;	
				case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
					onGetMeasuredCumulativeElectricEnergyNormalDirection(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
					onGetMeasuredCumulativeElectricEnergyReverseDirection(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY:
					onGetUnitForCumulativeElectricEnergy(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
					onGetHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
					onGetHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY:
					onGetDaysForHistoricalDataOfMeasurecCumulativeElectricEnergy(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY:
					onGetMeasuredInstantaneousElectricEnergy(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_CURRENTS:
					onGetMeasuredInstantaneousCurrents(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_VOLTAGES:
					onGetMeasuredInstantaneousVoltages(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_1:
					onGetMeasurementChannelValue1(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_2:
					onGetMeasurementChannelValue2(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_3:
					onGetMeasurementChannelValue3(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_4:
					onGetMeasurementChannelValue4(seoj, strEsv, strEsv, objProp, isSuccess);	
					return;
				case EPC_MEASUREMENT_CHANNEL_5:
					onGetMeasurementChannelValue5(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_6:
					onGetMeasurementChannelValue6(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_7:
					onGetMeasurementChannelValue7(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_8:
					onGetMeasurementChannelValue8(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_9:
					onGetMeasurementChannelValue9(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_10:
					onGetMeasurementChannelValue10(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_11:
					onGetMeasurementChannelValue11(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_12:
					onGetMeasurementChannelValue12(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_13:
					onGetMeasurementChannelValue13(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_14:
					onGetMeasurementChannelValue14(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_15:
					onGetMeasurementChannelValue15(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_16:
					onGetMeasurementChannelValue16(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_17:
					onGetMeasurementChannelValue17(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_18:
					onGetMeasurementChannelValue18(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_19:
					onGetMeasurementChannelValue19(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_20:
					onGetMeasurementChannelValue20(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_21:
					onGetMeasurementChannelValue21(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_22:
					onGetMeasurementChannelValue22(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_23:
					onGetMeasurementChannelValue23(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_24:
					onGetMeasurementChannelValue24(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_25:
					onGetMeasurementChannelValue25(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_26:
					onGetMeasurementChannelValue26(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_27:
					onGetMeasurementChannelValue27(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_28:
					onGetMeasurementChannelValue28(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_29:
					onGetMeasurementChannelValue29(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_30:
					onGetMeasurementChannelValue30(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_31:
					onGetMeasurementChannelValue31(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASUREMENT_CHANNEL_32:
					onGetMeasurementChannelValue32(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MASTER_RATED_CAPACITY:
					onGetMasterRatedCapacity(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX:
					onGetNoOfMeasurementChannelsSimplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX:
					onGetChannelRangeCumulativeElectricPowerSimplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX:
					onGetMeasuredCumulativeElectricPowerListSimplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX:
					onGetChannelRangeInstantaneousCurrentSimplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX:
					onGetMeasuredInstaneousCurrentListSimplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX:
					onGetChannelRangeInstantaneousPowerSimplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX:
					onGetMeasuredInstantaneousPowerListSimplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX:
					onGetNoOfMeasurementChannelDuplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX:
					onGetChannelRangeCumulativeElectricPowerDuplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX:
					onGetMeasuredCumulativeElectricPowerListDuplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX:
					onGetChannelRangeInstantaneousCurrentDuplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX:
					onGetMeasuredInstantaneousCurrentListDuplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX:
					onGetChannelRangeInstantaneousPowerDuplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX:
					onGetMeasuredInstantaneousPowerListDuplex(seoj, strEsv, strEsv, objProp, isSuccess);
					return;
					
				default:
					return;
					
			}
		}
		
		//set
		public void onSetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		public void onSetDaysForHistoricalDataOfMeasuredCumulativeElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		public void onSetChannelRangeCumulativeElectricPowerSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		public void onSetChannelRangeInstantaneousCurrentSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		public void onSetChannelRangeInstantaneousPowerSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		public void onSetChannelRangeCumulativeElectricPowerDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		public void onSetChannelRangeInstantaneousCurrentDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		public void onSetChannelRangeInstantaneousPowerDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp,boolean isSuccess) {}
		
		//get
		public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetMeasuredCumulativeElectricEnergyNormalDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetMeasuredCumulativeElectricEnergyReverseDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetUnitForCumulativeElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetDaysForHistoricalDataOfMeasurecCumulativeElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetMeasuredInstantaneousElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetMeasuredInstantaneousCurrents(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetMeasuredInstantaneousVoltages(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}
		public void onGetMeasurementChannelValue1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue4(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue5(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue6(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue7(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue8(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue9(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue10(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {}	
		public void onGetMeasurementChannelValue11(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue12(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue13(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue14(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue15(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue16(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue17(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue18(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue19(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue20(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};	
		public void onGetMeasurementChannelValue21(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue22(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue23(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue24(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue25(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue26(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue27(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue28(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue29(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue30(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};	
		public void onGetMeasurementChannelValue31(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasurementChannelValue32(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMasterRatedCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetNoOfMeasurementChannelsSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetChannelRangeCumulativeElectricPowerSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasuredCumulativeElectricPowerListSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetChannelRangeInstantaneousCurrentSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasuredInstaneousCurrentListSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetChannelRangeInstantaneousPowerSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasuredInstantaneousPowerListSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetNoOfMeasurementChannelDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetChannelRangeCumulativeElectricPowerDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasuredCumulativeElectricPowerListDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetChannelRangeInstantaneousCurrentDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasuredInstantaneousCurrentListDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};	
		public void onGetChannelRangeInstantaneousPowerDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		public void onGetMeasuredInstantaneousPowerListDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		
		//inform 
		public void onInformOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformMeasuredCumulativeElectricEnergyNormalDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformMeasuredCumulativeElectricEnergyReverseDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformUnitForCumulativeElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformDaysForHistoricalDataOfMeasurecCumulativeElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformMeasuredInstantaneousElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformMeasuredInstantaneousCurrents(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformMeasuredInstantaneousVoltages(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}
		public void onInformMeasurementChannelValue1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue4(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue5(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue6(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue7(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue8(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue9(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue10(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {}	
		public void onInformMeasurementChannelValue11(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue12(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue13(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue14(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue15(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue16(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue17(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue18(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue19(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue20(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};	
		public void onInformMeasurementChannelValue21(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue22(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue23(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue24(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue25(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue26(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue27(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue28(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue29(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue30(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};	
		public void onInformMeasurementChannelValue31(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasurementChannelValue32(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMasterRatedCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformNoOfMeasurementChannelsSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformChannelRangeCumulativeElectricPowerSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasuredCumulativeElectricPowerListSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformChannelRangeInstantaneousCurrentSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasuredInstaneousCurrentListSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformChannelRangeInstantaneousPowerSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasuredInstantaneousPowerListSimplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformNoOfMeasurementChannelDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformChannelRangeCumulativeElectricPowerDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasuredCumulativeElectricPowerListDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformChannelRangeInstantaneousCurrentDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasuredInstantaneousCurrentListDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};	
		public void onInformChannelRangeInstantaneousPowerDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		public void onInformMeasuredInstantaneousPowerListDuplex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized boolean isValidEdtValue(ElProp prop) {
		boolean bResult = super.isValidEdtValue(prop);
		if(bResult == true) {
			return true;
		}
		String epc = prop.getStrEpc();
		String edt = prop.getStrEdt();
		switch (epc) {
			case EPC_OPERATION_STATUS:
				return this.isValidEdtOperationStatus(edt);
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
				return this.isValidEdtMeasuredCumulativeElectricEnergyNormalDirection(edt);
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
				return this.isValidEdtMeasuredCumulativeElectricEnergyReverseDirection(edt);
			case EPC_UNIT_FOR_CUMULATIVE_ELECTRIC_ENERGY:
				return this.isValidEdtUnitForCumulativeElectricEnergy(edt);
			case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_NORMAL_DIRECTION:
				return this.isValidEdtHistoricalDataOfMeasuredCumulativeElectricEnergyNormalDirection(edt);
			case EPC_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY_REVERSE_DIRECTION:
				return this.isValidEdtHistoricalDataOfMeasuredCumulativeElectricEnergyReverseDirection(edt);
			case EPC_DAYS_FOR_HISTORICAL_DATA_OF_MEASURED_CUMULATIVE_ELECTRIC_ENERGY:
				return this.isValidEdtDaysForHistoricalDataOfMeasuredCumulativeElectricEnergy(edt);
			case EPC_MEASURED_INSTANTANEOUS_ELECTRIC_ENERGY:
				return this.isValidEdtMeasuredInstantaneousElectricEnergy(edt);
			case EPC_MEASURED_INSTANTANEOUS_CURRENTS:
				return this.isValidEdtMeasuredInstantaneousCurrents(edt);
			case EPC_MEASURED_INSTANTANEOUS_VOLTAGES:
				return this.isValidEdtMeasuredInstantaneousVoltages(edt);
			case EPC_MEASUREMENT_CHANNEL_1:
				return this.isValidEdtMeasurementChannel1(edt);
			case EPC_MEASUREMENT_CHANNEL_2:
				return this.isValidEdtMeasurementChannel2(edt);
			case EPC_MEASUREMENT_CHANNEL_3:
				return this.isValidEdtMeasurementChannel3(edt);
			case EPC_MEASUREMENT_CHANNEL_4:
				return this.isValidEdtMeasurementChannel4(edt);
			case EPC_MEASUREMENT_CHANNEL_5:
				return this.isValidEdtMeasurementChannel5(edt);
			case EPC_MEASUREMENT_CHANNEL_6:
				return this.isValidEdtMeasurementChannel6(edt);
			case EPC_MEASUREMENT_CHANNEL_7:
				return this.isValidEdtMeasurementChannel7(edt);
			case EPC_MEASUREMENT_CHANNEL_8:
				return this.isValidEdtMeasurementChannel8(edt);
			case EPC_MEASUREMENT_CHANNEL_9:
				return this.isValidEdtMeasurementChannel9(edt);
			case EPC_MEASUREMENT_CHANNEL_10:
				return this.isValidEdtMeasurementChannel10(edt);
			case EPC_MEASUREMENT_CHANNEL_11:
				return this.isValidEdtMeasurementChannel11(edt);
			case EPC_MEASUREMENT_CHANNEL_12:
				return this.isValidEdtMeasurementChannel12(edt);
			case EPC_MEASUREMENT_CHANNEL_13:
				return this.isValidEdtMeasurementChannel13(edt);
			case EPC_MEASUREMENT_CHANNEL_14:
				return this.isValidEdtMeasurementChannel14(edt);	
			case EPC_MEASUREMENT_CHANNEL_15:
				return this.isValidEdtMeasurementChannel15(edt);
			case EPC_MEASUREMENT_CHANNEL_16:
				return this.isValidEdtMeasurementChannel16(edt);
			case EPC_MEASUREMENT_CHANNEL_17:
				return this.isValidEdtMeasurementChannel17(edt);
			case EPC_MEASUREMENT_CHANNEL_18:
				return this.isValidEdtMeasurementChannel18(edt);
			case EPC_MEASUREMENT_CHANNEL_19:
				return this.isValidEdtMeasurementChannel19(edt);
			case EPC_MEASUREMENT_CHANNEL_20:
				return this.isValidEdtMeasurementChannel20(edt);
			case EPC_MEASUREMENT_CHANNEL_21:
				return this.isValidEdtMeasurementChannel21(edt);
			case EPC_MEASUREMENT_CHANNEL_22:
				return this.isValidEdtMeasurementChannel22(edt);
			case EPC_MEASUREMENT_CHANNEL_23:
				return this.isValidEdtMeasurementChannel23(edt);
			case EPC_MEASUREMENT_CHANNEL_24:
				return this.isValidEdtMeasurementChannel24(edt);
			case EPC_MEASUREMENT_CHANNEL_25:
				return this.isValidEdtMeasurementChannel25(edt);
			case EPC_MEASUREMENT_CHANNEL_26:
				return this.isValidEdtMeasurementChannel26(edt);
			case EPC_MEASUREMENT_CHANNEL_27:
				return this.isValidEdtMeasurementChannel27(edt);
			case EPC_MEASUREMENT_CHANNEL_28:
				return this.isValidEdtMeasurementChannel28(edt);
			case EPC_MEASUREMENT_CHANNEL_29:
				return this.isValidEdtMeasurementChannel29(edt);
			case EPC_MEASUREMENT_CHANNEL_30:
				return this.isValidEdtMeasurementChannel30(edt);
			case EPC_MEASUREMENT_CHANNEL_31:
				return this.isValidEdtMeasurementChannel31(edt);
			case EPC_MEASUREMENT_CHANNEL_32:
				return this.isValidEdtMeasurementChannel32(edt);
			case EPC_MASTER_RATED_CAPACITY:
				return this.isValidEdtMasterRatedCapacity(edt);
			case EPC_NO_OF_MEASUREMENT_CHANNELS_SIMPLEX:
				return this.isValidEdtNoOfMeasurementChannelsSimplex(edt);
			case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_SIMPLEX:
				return this.isValidEdtChannelRangeCumulativeElectricPowerSimplex(edt);
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_SIMPLEX:
				return this.isValidEdtMeasuredCumulativeElectricPowerListSimplex(edt);
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_SIMPLEX:
				return this.isValidEdtChannelRangeInstantaneousCurrentSimplex(edt);
			case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_SIMPLEX:
				return this.isValidEdtMeasuredInstantaneousCurrentListSimplex(edt);
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_SIMPLEX:
				return this.isValidEdtChannelRangeInstantaneousPowerSimplex(edt);
			case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_SIMPLEX:
				return this.isValidEdtMeasuredInstantaneousPowerListSimplex(edt);
			case EPC_NO_OF_MEASUREMENT_CHANNEL_DUPLEX:
				return this.isValidEdtNoOfMeasurementChannelDuplex(edt);
			case EPC_CHANNEL_RANGE_CUMULATIVE_ELECTRIC_POWER_DUPLEX:
				return this.isValidEdtChannelRangeCumulativeElectricPowerDuplex(edt);
			case EPC_MEASURED_CUMULATIVE_ELECTRIC_POWER_LIST_DUPLEX:
				return this.isValidEdtMeasuredCumulativeElectricPowerListDuplex(edt);
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_CURRENT_DUPLEX:
				return this.isValidEdtChannelRangeInstantaneousCurrentDuplex(edt);
			case EPC_MEASURED_INSTANTANEOUS_CURRENT_LIST_DUPLEX:
				return this.isValidEdtMeasuredInstantaneousCurrentListDuplex(edt);
			case EPC_CHANNEL_RANGE_INSTANTANEOUS_POWER_DUPLEX:
				return this.isValidEdtChannelRangeInstantaneousPowerDuplex(edt);
			case EPC_MEASURED_INSTANTANEOUS_POWER_LIST_DUPLEX:
				return this.isValidEdtMeasuredInstantaneousPowerListDuplex(edt);
			default:
				return false;			
				
		}
	}

	
}
