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
 * 低圧スマート電力量メータクラス
 */
public class LowVoltageSmartMeter extends DeviceObjectSuperClass{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "LowVoltageSmartMeter";


	/**
	 *  クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "02";
	/**
	 *  クラスコード
	 */
	public static final String CLASS_CODE = "88";

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
	 * EPC : 積算電力量有効桁数
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS = "D7";

	/**
	 * EPC : 積算電力量計測値（正方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE = "E0";

	/**
	 * EPC : 積算電力量単位（正方向、逆方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_UNIT = "E1";

	/**
	 * EPC : 積算電力量計測値履歴１（正方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE = "E2";

	/**
	 * EPC : 積算電力量計測値（逆方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE = "E3";

	/**
	 * EPC : 積算電力量計測値履歴１（逆方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE = "E4";

	/**
	 * EPC : 積算履歴収集日１
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1 = "E5";

	/**
	 * EPC : 瞬時電力計測値
	 */
	public static final String EPC_INSTANT_POWER_MEASURED_VALUES = "E7";

	/**
	 * EPC : 瞬時電流計測値
	 */
	public static final String EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES = "E8";

	/**
	 * EPC : 定時積算電力量計測値（正方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE = "EA";

	/**
	 * EPC : 定時積算電力量計測値（逆方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE = "EB";


	/**
	 * EPC : 積算電力量計測値履歴２（正方向、逆方向計測値）
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2 = "EC";

	/**
	 * EPC : 積算履歴収集日２
	 */
	public static final String EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2 = "ED";



	/**
	 * コンストラクタ
	 */
	public LowVoltageSmartMeter() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
	}
	/**
	 * コンストラクタ
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public LowVoltageSmartMeter(String entityCode) {
		super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

		this.addMapGetProps(EPC_OPERATION_STATUS);
//		this.addMapGetProps(EPC_COEFFICIENT_VALUE);
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS);
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE);
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_UNIT);
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE);
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE); // 逆方向機能がある場合は必須
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE); // 逆方向機能がある場合は必須
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1);
		this.addMapGetProps(EPC_INSTANT_POWER_MEASURED_VALUES);
		this.addMapGetProps(EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES);
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE);
		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE); // 逆方向機能がある場合は必須
//		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2);
//		this.addMapGetProps(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2);

//		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
		this.addMapSetProps(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1);
//		this.addMapSetProps(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2); // 必須ではない

		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewLowVoltageSmartMeterFound(this);
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
		private static final String TAG = "LowVoltageSmartMeter.ElSetProps";

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
		 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
			return this;
		}
		/**
		 * 積算履歴収集日１<br>
		 * EPC                 : 0xE5<br>
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
		 * 30 分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63<br>
		 * ( 0～99)<br>
		 * 0:当日 1～99:前日の日数<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElSetProps reqSetIntegralPowerConsumptionLogCollectionDay1(String strEdt){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1, strEdt));
			return this;
		}
		/**
		 * 積算履歴収集日２<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElSetProps reqSetIntegralPowerConsumptionLogCollectionDay2(String strEdt){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2, strEdt));
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
		private static final String TAG = "LowVoltageSmartMeter.ElGetProps";

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
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 係数<br>
		 * EPC                 : 0xD3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量計測値、履歴を実使用量に換算する係数を10 進表記において6 桁で示す。<br>
		 * 0x00000000～0x000F423F(000000～999999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetCoefficientValue(){
			listProperty.add(new ElProp(EPC_COEFFICIENT_VALUE));
			return this;
		}
		/**
		 * 積算電力量有効桁数<br>
		 * EPC                 : 0xD7<br>
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
		 * 積算電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08(1～8)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionSignificantDigits(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS));
			return this;
		}
		/**
		 * 積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF(0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionMeasuredValuesPositive(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE));
			return this;
		}
		/**
		 * 積算電力量単位（正方向、逆方向計測値）<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量計測値、履歴の単位（乗率）を示す。<br>
		 * 0x00：1kWh<br>
		 * 0x01：0.1kWh<br>
		 * 0x02：0.01kWh<br>
		 * 0x03：0.001kWh<br>
		 * 0x04：0.0001kWh<br>
		 * 0x0A：10kWh<br>
		 * 0x0B：100kWh<br>
		 * 0x0C：1000kWh<br>
		 * 0x0D：10000kWh<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionUnit(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_UNIT));
			return this;
		}
		/**
		 * 積算電力量計測値履歴１(正方向計測値)<br>
		 * EPC                 : 0xE2<br>
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
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の正方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionMeasuredValuesLog1Positive(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE));
			return this;
		}
		/**
		 * 積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionMeasuredValuesReverse(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE));
			return this;
		}
		/**
		 * 積算電力量計測値履歴１(逆方向計測値)<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の逆方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionMeasuredValuesLog1Reverse(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE));
			return this;
		}
		/**
		 * 積算履歴収集日１<br>
		 * EPC                 : 0xE5<br>
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
		 * 30 分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63<br>
		 * ( 0～99)<br>
		 * 0:当日 1～99:前日の日数<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionLogCollectionDay1(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1));
			return this;
		}
		/**
		 * 瞬時電力計測値<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力実効値の瞬時値を1W 単位で示す。<br>
		 * 0x80000001～0x7FFFFFFD<br>
		 * (-2,147,483,647～2,147,483,645)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetInstantPowerMeasuredValues(){
			listProperty.add(new ElProp(EPC_INSTANT_POWER_MEASURED_VALUES));
			return this;
		}
		/**
		 * 瞬時電流計測値<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : signed short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 実効電流値の瞬時値を0.1A 単位でR 相T 相を並べて示す。単相2 線式の場合は、T 相に0x7FFE をセット。<br>
		 * 0x8001～0x7FFD（R 相）：0x8001～0x7FFD（T 相）<br>
		 * (-3,276.7～3,276.5):(-3,276.7～3,276.5)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetInstantElectricCurrentMeasuredValues(){
			listProperty.add(new ElProp(EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES));
			return this;
		}
		/**
		 * 定時積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
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
		 * 最新の30 分毎の計測時刻における積算電力量(正方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（正方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量 10 進表記で最大8 桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE));
			return this;
		}
		/**
		 * 定時積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30 分毎の計測時刻における積算電力量(逆方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（逆方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量10 進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE));
			return this;
		}
		/**
		 * 積算電力量計測値履歴２（正方向、逆方向計測値）<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned short＋unsigned char×4＋unsigned char＋(unsigned long＋unsigned long)×(Max)12<br>
		 * サイズ              : Max 103 byte<br>
		 * 単位                : 年月日,時分,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日時、収集コマ数及び積算電力量(最大8 桁)の計測結果履歴を、正・逆30 分毎のデータで過去最大6 時間分示す。<br>
		 * ・積算履歴収集日時<br>
		 *  YYYY:MM:DD:hh:mm<br>
		 * ・収集コマ数<br>
		 * ・積算電力量<br>
		 *  10 進表記において最大8 桁、最大6 時間分<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * 8 バイト目以降：積算電力量計測値(正方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * 積算電力量計測値(逆方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionMeasuredValuesLog2(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2));
			return this;
		}
		/**
		 * 積算履歴収集日２<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElGetProps reqGetIntegralPowerConsumptionLogCollectionDay2(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2));
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
		private static final String TAG = "LowVoltageSmartMeter.ElInformProps";

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
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 係数<br>
		 * EPC                 : 0xD3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量計測値、履歴を実使用量に換算する係数を10 進表記において6 桁で示す。<br>
		 * 0x00000000～0x000F423F(000000～999999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfCoefficientValue(){
			listProperty.add(new ElProp(EPC_COEFFICIENT_VALUE));
			return this;
		}
		/**
		 * 積算電力量有効桁数<br>
		 * EPC                 : 0xD7<br>
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
		 * 積算電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08(1～8)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionSignificantDigits(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS));
			return this;
		}
		/**
		 * 積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF(0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionMeasuredValuesPositive(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE));
			return this;
		}
		/**
		 * 積算電力量単位（正方向、逆方向計測値）<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量計測値、履歴の単位（乗率）を示す。<br>
		 * 0x00：1kWh<br>
		 * 0x01：0.1kWh<br>
		 * 0x02：0.01kWh<br>
		 * 0x03：0.001kWh<br>
		 * 0x04：0.0001kWh<br>
		 * 0x0A：10kWh<br>
		 * 0x0B：100kWh<br>
		 * 0x0C：1000kWh<br>
		 * 0x0D：10000kWh<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionUnit(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_UNIT));
			return this;
		}
		/**
		 * 積算電力量計測値履歴１(正方向計測値)<br>
		 * EPC                 : 0xE2<br>
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
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の正方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionMeasuredValuesLog1Positive(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE));
			return this;
		}
		/**
		 * 積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionMeasuredValuesReverse(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE));
			return this;
		}
		/**
		 * 積算電力量計測値履歴１(逆方向計測値)<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の逆方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionMeasuredValuesLog1Reverse(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE));
			return this;
		}
		/**
		 * 積算履歴収集日１<br>
		 * EPC                 : 0xE5<br>
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
		 * 30 分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63<br>
		 * ( 0～99)<br>
		 * 0:当日 1～99:前日の日数<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionLogCollectionDay1(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1));
			return this;
		}
		/**
		 * 瞬時電力計測値<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力実効値の瞬時値を1W 単位で示す。<br>
		 * 0x80000001～0x7FFFFFFD<br>
		 * (-2,147,483,647～2,147,483,645)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfInstantPowerMeasuredValues(){
			listProperty.add(new ElProp(EPC_INSTANT_POWER_MEASURED_VALUES));
			return this;
		}
		/**
		 * 瞬時電流計測値<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : signed short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 実効電流値の瞬時値を0.1A 単位でR 相T 相を並べて示す。単相2 線式の場合は、T 相に0x7FFE をセット。<br>
		 * 0x8001～0x7FFD（R 相）：0x8001～0x7FFD（T 相）<br>
		 * (-3,276.7～3,276.5):(-3,276.7～3,276.5)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfInstantElectricCurrentMeasuredValues(){
			listProperty.add(new ElProp(EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES));
			return this;
		}
		/**
		 * 定時積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
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
		 * 最新の30 分毎の計測時刻における積算電力量(正方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（正方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量 10 進表記で最大8 桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE));
			return this;
		}
		/**
		 * 定時積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30 分毎の計測時刻における積算電力量(逆方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（逆方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量10 進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE));
			return this;
		}
		/**
		 * 積算電力量計測値履歴２（正方向、逆方向計測値）<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned short＋unsigned char×4＋unsigned char＋(unsigned long＋unsigned long)×(Max)12<br>
		 * サイズ              : Max 103 byte<br>
		 * 単位                : 年月日,時分,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日時、収集コマ数及び積算電力量(最大8 桁)の計測結果履歴を、正・逆30 分毎のデータで過去最大6 時間分示す。<br>
		 * ・積算履歴収集日時<br>
		 *  YYYY:MM:DD:hh:mm<br>
		 * ・収集コマ数<br>
		 * ・積算電力量<br>
		 *  10 進表記において最大8 桁、最大6 時間分<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * 8 バイト目以降：積算電力量計測値(正方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * 積算電力量計測値(逆方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionMeasuredValuesLog2(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2));
			return this;
		}
		/**
		 * 積算履歴収集日２<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * <br>
		 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
		 */
		public LowVoltageSmartMeter.ElInformProps reqInfIntegralPowerConsumptionLogCollectionDay2(){
			listProperty.add(new ElProp(EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2));
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess) {
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new LowVoltageSmartMeter(instanceCode), ElFrame.ESV_SETI);
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess) {
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new LowVoltageSmartMeter(instanceCode), ElFrame.ESV_SETC);
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
	 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess) {
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
	 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
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
	 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new LowVoltageSmartMeter(instanceCode), ElFrame.ESV_GET);
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess) {
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public static LowVoltageSmartMeter.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new LowVoltageSmartMeter(instanceCode), ElFrame.ESV_INF_REQ);
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElSetProps setI(){
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElSetProps setI(ElClassBase seoj){
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElSetProps setC(){
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
	 * @return LowVoltageSmartMeter.ElSetPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElSetProps setC(ElClassBase seoj){
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
	 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElGetProps get(){
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
	 * @return LowVoltageSmartMeter.ElGetPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElGetProps get(ElClassBase seoj){
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElInformProps infReq(){
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElInformProps infReq(ElClassBase seoj){
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElInformProps inf(){
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElInformProps inf(ElClassBase deoj){

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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	public LowVoltageSmartMeter.ElInformProps infC(String remoteIpAddress){
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
	 * @return LowVoltageSmartMeter.ElInformPropsオブジェクト
	 */
	@Override
	public LowVoltageSmartMeter.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
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
		private static final String TAG = "LowVoltageSmartMeter.ReportProcessor";

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
				case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1:
					onSetIntegralPowerConsumptionLogCollectionDay1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2:
					onSetIntegralPowerConsumptionLogCollectionDay2(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS:
					onGetIntegralPowerConsumptionSignificantDigits(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE:
					onGetIntegralPowerConsumptionMeasuredValuesPositive(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_UNIT:
					onGetIntegralPowerConsumptionUnit(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE:
					onGetIntegralPowerConsumptionMeasuredValuesLog1Positive(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE:
					onGetIntegralPowerConsumptionMeasuredValuesReverse(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE:
					onGetIntegralPowerConsumptionMeasuredValuesLog1Reverse(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1:
					onGetIntegralPowerConsumptionLogCollectionDay1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INSTANT_POWER_MEASURED_VALUES:
					onGetInstantPowerMeasuredValues(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES:
					onGetInstantElectricCurrentMeasuredValues(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE:
					onGetIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE:
					onGetIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2:
					onGetIntegralPowerConsumptionMeasuredValuesLog2(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2:
					onGetIntegralPowerConsumptionLogCollectionDay2(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS:
					onInformIntegralPowerConsumptionSignificantDigits(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE:
					onInformIntegralPowerConsumptionMeasuredValuesPositive(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_UNIT:
					onInformIntegralPowerConsumptionUnit(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE:
					onInformIntegralPowerConsumptionMeasuredValuesLog1Positive(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE:
					onInformIntegralPowerConsumptionMeasuredValuesReverse(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE:
					onInformIntegralPowerConsumptionMeasuredValuesLog1Reverse(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1:
					onInformIntegralPowerConsumptionLogCollectionDay1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INSTANT_POWER_MEASURED_VALUES:
					onInformInstantPowerMeasuredValues(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES:
					onInformInstantElectricCurrentMeasuredValues(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE:
					onInformIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE:
					onInformIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2:
					onInformIntegralPowerConsumptionMeasuredValuesLog2(seoj, strTid, strEsv, objProp);
					return;
				case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2:
					onInformIntegralPowerConsumptionLogCollectionDay2(seoj, strTid, strEsv, objProp);
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
		 * 積算履歴収集日１<br>
		 * EPC                 : 0xE5<br>
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
		 * 30 分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63<br>
		 * ( 0～99)<br>
		 * 0:当日 1～99:前日の日数<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIntegralPowerConsumptionLogCollectionDay1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 積算履歴収集日２<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIntegralPowerConsumptionLogCollectionDay2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

		/**
		 * Get関連
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
		 * EPC                 : 0xD3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量計測値、履歴を実使用量に換算する係数を10 進表記において6 桁で示す。<br>
		 * 0x00000000～0x000F423F(000000～999999)<br>
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
		 * 積算電力量有効桁数<br>
		 * EPC                 : 0xD7<br>
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
		 * 積算電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08(1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionSignificantDigits(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF(0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionMeasuredValuesPositive(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算電力量単位（正方向、逆方向計測値）<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量計測値、履歴の単位（乗率）を示す。<br>
		 * 0x00：1kWh<br>
		 * 0x01：0.1kWh<br>
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
		public void onGetIntegralPowerConsumptionUnit(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算電力量計測値履歴１(正方向計測値)<br>
		 * EPC                 : 0xE2<br>
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
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の正方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionMeasuredValuesLog1Positive(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionMeasuredValuesReverse(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算電力量計測値履歴１(逆方向計測値)<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の逆方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionMeasuredValuesLog1Reverse(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算履歴収集日１<br>
		 * EPC                 : 0xE5<br>
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
		 * 30 分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63<br>
		 * ( 0～99)<br>
		 * 0:当日 1～99:前日の日数<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionLogCollectionDay1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 瞬時電力計測値<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力実効値の瞬時値を1W 単位で示す。<br>
		 * 0x80000001～0x7FFFFFFD<br>
		 * (-2,147,483,647～2,147,483,645)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetInstantPowerMeasuredValues(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 瞬時電流計測値<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : signed short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 実効電流値の瞬時値を0.1A 単位でR 相T 相を並べて示す。単相2 線式の場合は、T 相に0x7FFE をセット。<br>
		 * 0x8001～0x7FFD（R 相）：0x8001～0x7FFD（T 相）<br>
		 * (-3,276.7～3,276.5):(-3,276.7～3,276.5)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetInstantElectricCurrentMeasuredValues(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定時積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
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
		 * 最新の30 分毎の計測時刻における積算電力量(正方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（正方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量 10 進表記で最大8 桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 定時積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30 分毎の計測時刻における積算電力量(逆方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（逆方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量10 進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算電力量計測値履歴２（正方向、逆方向計測値）<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned short＋unsigned char×4＋unsigned char＋(unsigned long＋unsigned long)×(Max)12<br>
		 * サイズ              : Max 103 byte<br>
		 * 単位                : 年月日,時分,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日時、収集コマ数及び積算電力量(最大8 桁)の計測結果履歴を、正・逆30 分毎のデータで過去最大6 時間分示す。<br>
		 * ・積算履歴収集日時<br>
		 *  YYYY:MM:DD:hh:mm<br>
		 * ・収集コマ数<br>
		 * ・積算電力量<br>
		 *  10 進表記において最大8 桁、最大6 時間分<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * 8 バイト目以降：積算電力量計測値(正方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * 積算電力量計測値(逆方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionMeasuredValuesLog2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 積算履歴収集日２<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIntegralPowerConsumptionLogCollectionDay2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

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
		 * EPC                 : 0xD3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量計測値、履歴を実使用量に換算する係数を10 進表記において6 桁で示す。<br>
		 * 0x00000000～0x000F423F(000000～999999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformCoefficientValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算電力量有効桁数<br>
		 * EPC                 : 0xD7<br>
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
		 * 積算電力量計測値の有効桁数を示す。<br>
		 * 0x01～0x08(1～8)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionSignificantDigits(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF(0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionMeasuredValuesPositive(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算電力量単位（正方向、逆方向計測値）<br>
		 * EPC                 : 0xE1<br>
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
		 * 積算電力量計測値、履歴の単位（乗率）を示す。<br>
		 * 0x00：1kWh<br>
		 * 0x01：0.1kWh<br>
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
		public void onInformIntegralPowerConsumptionUnit(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算電力量計測値履歴１(正方向計測値)<br>
		 * EPC                 : 0xE2<br>
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
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の正方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionMeasuredValuesLog1Positive(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xE3<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionMeasuredValuesReverse(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算電力量計測値履歴１(逆方向計測値)<br>
		 * EPC                 : 0xE4<br>
		 * データタイプ        : unsigned short＋unsigned long×48<br>
		 * サイズ              : 194 byte<br>
		 * 単位                : kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の逆方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
		 * 1～2 バイト目：積算履歴収集日<br>
		 * 0x0000～0x0063(0～99)<br>
		 * 3 バイト目以降：積算電力量計測値<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionMeasuredValuesLog1Reverse(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算履歴収集日１<br>
		 * EPC                 : 0xE5<br>
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
		 * 30 分毎の計測値履歴データを収集する日を示す。<br>
		 * 0x00～0x63<br>
		 * ( 0～99)<br>
		 * 0:当日 1～99:前日の日数<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionLogCollectionDay1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 瞬時電力計測値<br>
		 * EPC                 : 0xE7<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : W<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電力実効値の瞬時値を1W 単位で示す。<br>
		 * 0x80000001～0x7FFFFFFD<br>
		 * (-2,147,483,647～2,147,483,645)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformInstantPowerMeasuredValues(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 瞬時電流計測値<br>
		 * EPC                 : 0xE8<br>
		 * データタイプ        : signed short×2<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : 0.1 A<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 実効電流値の瞬時値を0.1A 単位でR 相T 相を並べて示す。単相2 線式の場合は、T 相に0x7FFE をセット。<br>
		 * 0x8001～0x7FFD（R 相）：0x8001～0x7FFD（T 相）<br>
		 * (-3,276.7～3,276.5):(-3,276.7～3,276.5)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformInstantElectricCurrentMeasuredValues(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定時積算電力量計測値(正方向計測値)<br>
		 * EPC                 : 0xEA<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
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
		 * 最新の30 分毎の計測時刻における積算電力量(正方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（正方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量 10 進表記で最大8 桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 定時積算電力量計測値(逆方向計測値)<br>
		 * EPC                 : 0xEB<br>
		 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
		 * サイズ              : 11 byte<br>
		 * 単位                : 年月日,時分秒,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 最新の30 分毎の計測時刻における積算電力量(逆方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（逆方向計測値）4 バイトで示す。<br>
		 * ・計測年月日 YYYY:MM:DD<br>
		 * ・計測時刻 hh:mm:ss<br>
		 * ・積算電力量10 進表記で最大8桁<br>
		 * 1～４バイト目：計測年月日<br>
		 *  YYYY:0x0001～0x270F<br>
		 *  (1～9999)<br>
		 *  MM:0x01～0x0C(1～12)<br>
		 *  DD:0x01～0x1F(1～31)<br>
		 * 5～７バイト目：計測時刻<br>
		 *  ｈｈ:0x00～0x17(0～23)<br>
		 *  mm:0x00～0x3B(0～59)<br>
		 *  ss:0x00～0x3B(0～59)<br>
		 * 8～11 バイト目：積算電力量計測値<br>
		 *  0x00000000～0x05F5E0FF<br>
		 *  (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算電力量計測値履歴２（正方向、逆方向計測値）<br>
		 * EPC                 : 0xEC<br>
		 * データタイプ        : unsigned short＋unsigned char×4＋unsigned char＋(unsigned long＋unsigned long)×(Max)12<br>
		 * サイズ              : Max 103 byte<br>
		 * 単位                : 年月日,時分,kWh<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 積算履歴収集日時、収集コマ数及び積算電力量(最大8 桁)の計測結果履歴を、正・逆30 分毎のデータで過去最大6 時間分示す。<br>
		 * ・積算履歴収集日時<br>
		 *  YYYY:MM:DD:hh:mm<br>
		 * ・収集コマ数<br>
		 * ・積算電力量<br>
		 *  10 進表記において最大8 桁、最大6 時間分<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * 8 バイト目以降：積算電力量計測値(正方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * 積算電力量計測値(逆方向)<br>
		 * 0x00000000～0x05F5E0FF<br>
		 * (0～99,999,999)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionMeasuredValuesLog2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 積算履歴収集日２<br>
		 * EPC                 : 0xED<br>
		 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
		 * サイズ              : 7 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
		 * 1～6 バイト目：積算履歴収集日時<br>
		 * YYYY:0x0001～0x270F<br>
		 * (1～9999 年)<br>
		 * MM:0x01～0x0C(1～12 月)<br>
		 * DD:0x01～0x1F(1～31 日)<br>
		 * hh:0x00～0x17(0～23 時)<br>
		 * mm:0x00/0x1E(0/30 分)<br>
		 * 7 バイト目：収集コマ数<br>
		 * 0x01～0x0C(1～12 コマ)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIntegralPowerConsumptionLogCollectionDay2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

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
			case EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS:
				return this.getFromAppIntegralPowerConsumptionSignificantDigits();
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE:
				return this.getFromAppIntegralPowerConsumptionMeasuredValuesPositive();
			case EPC_INTEGRAL_POWER_CONSUMPTION_UNIT:
				return this.getFromAppIntegralPowerConsumptionUnit();
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE:
				return this.getFromAppIntegralPowerConsumptionMeasuredValuesLog1Positive();
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE:
				return this.getFromAppIntegralPowerConsumptionMeasuredValuesReverse();
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE:
				return this.getFromAppIntegralPowerConsumptionMeasuredValuesLog1Reverse();
			case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1:
				return this.getFromAppIntegralPowerConsumptionLogCollectionDay1();
			case EPC_INSTANT_POWER_MEASURED_VALUES:
				return this.getFromAppInstantPowerMeasuredValues();
			case EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES:
				return this.getFromAppInstantElectricCurrentMeasuredValues();
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE:
				return this.getFromAppIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive();
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE:
				return this.getFromAppIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse();
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2:
				return this.getFromAppIntegralPowerConsumptionMeasuredValuesLog2();
			case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2:
				return this.getFromAppIntegralPowerConsumptionLogCollectionDay2();
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
			case EPC_INTEGRAL_POWER_CONSUMPTION_SIGNIFICANT_DIGITS:
				return this.isValidEdtIntegralPowerConsumptionSignificantDigits(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_POSITIVE:
				return this.isValidEdtIntegralPowerConsumptionMeasuredValuesPositive(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_UNIT:
				return this.isValidEdtIntegralPowerConsumptionUnit(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_POSITIVE:
				return this.isValidEdtIntegralPowerConsumptionMeasuredValuesLog1Positive(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_REVERSE:
				return this.isValidEdtIntegralPowerConsumptionMeasuredValuesReverse(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_1_REVERSE:
				return this.isValidEdtIntegralPowerConsumptionMeasuredValuesLog1Reverse(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1:
				return this.isValidEdtIntegralPowerConsumptionLogCollectionDay1(edt);
			case EPC_INSTANT_POWER_MEASURED_VALUES:
				return this.isValidEdtInstantPowerMeasuredValues(edt);
			case EPC_INSTANT_ELECTRIC_CURRENT_MEASURED_VALUES:
				return this.isValidEdtInstantElectricCurrentMeasuredValues(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_POSITIVE:
				return this.isValidEdtIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_AT_FIXED_TIME_REVERSE:
				return this.isValidEdtIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_MEASURED_VALUES_LOG_2:
				return this.isValidEdtIntegralPowerConsumptionMeasuredValuesLog2(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2:
				return this.isValidEdtIntegralPowerConsumptionLogCollectionDay2(edt);
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
			case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_1:
				return this.setToAppIntegralPowerConsumptionLogCollectionDay1(edt);
			case EPC_INTEGRAL_POWER_CONSUMPTION_LOG_COLLECTION_DAY_2:
				return this.setToAppIntegralPowerConsumptionLogCollectionDay2(edt);
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
	 * EPC                 : 0xD3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算電力量計測値、履歴を実使用量に換算する係数を10 進表記において6 桁で示す。<br>
	 * 0x00000000～0x000F423F(000000～999999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppCoefficientValue(){return null;}
	/**
	 * 積算電力量有効桁数<br>
	 * EPC                 : 0xD7<br>
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
	 * 積算電力量計測値の有効桁数を示す。<br>
	 * 0x01～0x08(1～8)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionSignificantDigits(){return null;}
	/**
	 * 積算電力量計測値(正方向計測値)<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
	 * 0x00000000～0x05F5E0FF(0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionMeasuredValuesPositive(){return null;}
	/**
	 * 積算電力量単位（正方向、逆方向計測値）<br>
	 * EPC                 : 0xE1<br>
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
	 * 積算電力量計測値、履歴の単位（乗率）を示す。<br>
	 * 0x00：1kWh<br>
	 * 0x01：0.1kWh<br>
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
	protected String getFromAppIntegralPowerConsumptionUnit(){return null;}
	/**
	 * 積算電力量計測値履歴１(正方向計測値)<br>
	 * EPC                 : 0xE2<br>
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
	 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の正方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2 バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3 バイト目以降：積算電力量計測値<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionMeasuredValuesLog1Positive(){return null;}
	/**
	 * 積算電力量計測値(逆方向計測値)<br>
	 * EPC                 : 0xE3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionMeasuredValuesReverse(){return null;}
	/**
	 * 積算電力量計測値履歴１(逆方向計測値)<br>
	 * EPC                 : 0xE4<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の逆方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2 バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3 バイト目以降：積算電力量計測値<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionMeasuredValuesLog1Reverse(){return null;}
	/**
	 * 積算履歴収集日１<br>
	 * EPC                 : 0xE5<br>
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
	 * 30 分毎の計測値履歴データを収集する日を示す。<br>
	 * 0x00～0x63<br>
	 * ( 0～99)<br>
	 * 0:当日 1～99:前日の日数<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionLogCollectionDay1(){return null;}
	/**
	 * 瞬時電力計測値<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電力実効値の瞬時値を1W 単位で示す。<br>
	 * 0x80000001～0x7FFFFFFD<br>
	 * (-2,147,483,647～2,147,483,645)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppInstantPowerMeasuredValues(){return null;}
	/**
	 * 瞬時電流計測値<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : signed short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 実効電流値の瞬時値を0.1A 単位でR 相T 相を並べて示す。単相2 線式の場合は、T 相に0x7FFE をセット。<br>
	 * 0x8001～0x7FFD（R 相）：0x8001～0x7FFD（T 相）<br>
	 * (-3,276.7～3,276.5):(-3,276.7～3,276.5)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppInstantElectricCurrentMeasuredValues(){return null;}
	/**
	 * 定時積算電力量計測値(正方向計測値)<br>
	 * EPC                 : 0xEA<br>
	 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
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
	 * 最新の30 分毎の計測時刻における積算電力量(正方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（正方向計測値）4 バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算電力量 10 進表記で最大8 桁<br>
	 * 1～４バイト目：計測年月日<br>
	 *  YYYY:0x0001～0x270F<br>
	 *  (1～9999)<br>
	 *  MM:0x01～0x0C(1～12)<br>
	 *  DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 *  ｈｈ:0x00～0x17(0～23)<br>
	 *  mm:0x00～0x3B(0～59)<br>
	 *  ss:0x00～0x3B(0～59)<br>
	 * 8～11 バイト目：積算電力量計測値<br>
	 *  0x00000000～0x05F5E0FF<br>
	 *  (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(){return null;}
	/**
	 * 定時積算電力量計測値(逆方向計測値)<br>
	 * EPC                 : 0xEB<br>
	 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日,時分秒,kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30 分毎の計測時刻における積算電力量(逆方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（逆方向計測値）4 バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算電力量10 進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 *  YYYY:0x0001～0x270F<br>
	 *  (1～9999)<br>
	 *  MM:0x01～0x0C(1～12)<br>
	 *  DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 *  ｈｈ:0x00～0x17(0～23)<br>
	 *  mm:0x00～0x3B(0～59)<br>
	 *  ss:0x00～0x3B(0～59)<br>
	 * 8～11 バイト目：積算電力量計測値<br>
	 *  0x00000000～0x05F5E0FF<br>
	 *  (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(){return null;}
	/**
	 * 積算電力量計測値履歴２（正方向、逆方向計測値）<br>
	 * EPC                 : 0xEC<br>
	 * データタイプ        : unsigned short＋unsigned char×4＋unsigned char＋(unsigned long＋unsigned long)×(Max)12<br>
	 * サイズ              : Max 103 byte<br>
	 * 単位                : 年月日,時分,kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日時、収集コマ数及び積算電力量(最大8 桁)の計測結果履歴を、正・逆30 分毎のデータで過去最大6 時間分示す。<br>
	 * ・積算履歴収集日時<br>
	 *  YYYY:MM:DD:hh:mm<br>
	 * ・収集コマ数<br>
	 * ・積算電力量<br>
	 *  10 進表記において最大8 桁、最大6 時間分<br>
	 * 1～6 バイト目：積算履歴収集日時<br>
	 * YYYY:0x0001～0x270F<br>
	 * (1～9999 年)<br>
	 * MM:0x01～0x0C(1～12 月)<br>
	 * DD:0x01～0x1F(1～31 日)<br>
	 * hh:0x00～0x17(0～23 時)<br>
	 * mm:0x00/0x1E(0/30 分)<br>
	 * 7 バイト目：収集コマ数<br>
	 * 0x01～0x0C(1～12 コマ)<br>
	 * 8 バイト目以降：積算電力量計測値(正方向)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * 積算電力量計測値(逆方向)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionMeasuredValuesLog2(){return null;}
	/**
	 * 積算履歴収集日２<br>
	 * EPC                 : 0xED<br>
	 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
	 * サイズ              : 7 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
	 * 1～6 バイト目：積算履歴収集日時<br>
	 * YYYY:0x0001～0x270F<br>
	 * (1～9999 年)<br>
	 * MM:0x01～0x0C(1～12 月)<br>
	 * DD:0x01～0x1F(1～31 日)<br>
	 * hh:0x00～0x17(0～23 時)<br>
	 * mm:0x00/0x1E(0/30 分)<br>
	 * 7 バイト目：収集コマ数<br>
	 * 0x01～0x0C(1～12 コマ)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIntegralPowerConsumptionLogCollectionDay2(){return null;}

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
	 * EPC                 : 0xD3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算電力量計測値、履歴を実使用量に換算する係数を10 進表記において6 桁で示す。<br>
	 * 0x00000000～0x000F423F(000000～999999)<br>
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
	 * 積算電力量有効桁数<br>
	 * EPC                 : 0xD7<br>
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
	 * 積算電力量計測値の有効桁数を示す。<br>
	 * 0x01～0x08(1～8)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionSignificantDigits(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("01", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "08") <= 0)) return false;
		return true;
	}
	/**
	 * 積算電力量計測値(正方向計測値)<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
	 * 0x00000000～0x05F5E0FF(0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionMeasuredValuesPositive(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(edt , "FFFFFFFE") == 0)) return false;
		return true;
	}
	/**
	 * 積算電力量単位（正方向、逆方向計測値）<br>
	 * EPC                 : 0xE1<br>
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
	 * 積算電力量計測値、履歴の単位（乗率）を示す。<br>
	 * 0x00：1kWh<br>
	 * 0x01：0.1kWh<br>
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
	protected boolean isValidEdtIntegralPowerConsumptionUnit(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "04") <= 0
				|| ElUtil.compElUnsignedChar("0A", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "0D") <= 0)) return false;
		return true;
	}
	/**
	 * 積算電力量計測値履歴１(正方向計測値)<br>
	 * EPC                 : 0xE2<br>
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
	 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の正方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2 バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3 バイト目以降：積算電力量計測値<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionMeasuredValuesLog1Positive(String edt){
		if(edt == null || !(edt.length() == 194 * 2)) return false;
		if(! checkMeasuredLogValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 積算電力量計測値(逆方向計測値)<br>
	 * EPC                 : 0xE3<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算電力量を10 進表記において、最大8 桁で示す。<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionMeasuredValuesReverse(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! (ElUtil.compElUnsignedLong("00000000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(edt , "FFFFFFFE") == 0)) return false;
		return true;
	}
	/**
	 * 積算電力量計測値履歴１(逆方向計測値)<br>
	 * EPC                 : 0xE4<br>
	 * データタイプ        : unsigned short＋unsigned long×48<br>
	 * サイズ              : 194 byte<br>
	 * 単位                : kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日１と該当収集日の24 時間48 コマ分（0 時0 分～23 時30 分）の逆方向の定時積算電力量計測値の履歴データを時系列順に上位バイトからプロパティ値として示す。<br>
	 * 1～2 バイト目：積算履歴収集日<br>
	 * 0x0000～0x0063(0～99)<br>
	 * 3 バイト目以降：積算電力量計測値<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionMeasuredValuesLog1Reverse(String edt){
		if(edt == null || !(edt.length() == 194 * 2)) return false;
		if(! checkMeasuredLogValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 積算履歴収集日１<br>
	 * EPC                 : 0xE5<br>
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
	 * 30 分毎の計測値履歴データを収集する日を示す。<br>
	 * 0x00～0x63<br>
	 * ( 0～99)<br>
	 * 0:当日 1～99:前日の日数<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionLogCollectionDay1(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! (ElUtil.compElUnsignedChar("00", edt ) <= 0 && ElUtil.compElUnsignedChar(edt , "63") <= 0 ||  ElUtil.compElUnsignedChar(edt , "FF") == 0)) return false;
		return true;
	}
	/**
	 * 瞬時電力計測値<br>
	 * EPC                 : 0xE7<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : W<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電力実効値の瞬時値を1W 単位で示す。<br>
	 * 0x80000001～0x7FFFFFFD<br>
	 * (-2,147,483,647～2,147,483,645)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtInstantPowerMeasuredValues(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		return true;
	}
	/**
	 * 瞬時電流計測値<br>
	 * EPC                 : 0xE8<br>
	 * データタイプ        : signed short×2<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : 0.1 A<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 実効電流値の瞬時値を0.1A 単位でR 相T 相を並べて示す。単相2 線式の場合は、T 相に0x7FFE をセット。<br>
	 * 0x8001～0x7FFD（R 相）：0x8001～0x7FFD（T 相）<br>
	 * (-3,276.7～3,276.5):(-3,276.7～3,276.5)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtInstantElectricCurrentMeasuredValues(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		return true;
	}
	/**
	 * 定時積算電力量計測値(正方向計測値)<br>
	 * EPC                 : 0xEA<br>
	 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
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
	 * 最新の30 分毎の計測時刻における積算電力量(正方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（正方向計測値）4 バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算電力量 10 進表記で最大8 桁<br>
	 * 1～４バイト目：計測年月日<br>
	 *  YYYY:0x0001～0x270F<br>
	 *  (1～9999)<br>
	 *  MM:0x01～0x0C(1～12)<br>
	 *  DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 *  ｈｈ:0x00～0x17(0～23)<br>
	 *  mm:0x00～0x3B(0～59)<br>
	 *  ss:0x00～0x3B(0～59)<br>
	 * 8～11 バイト目：積算電力量計測値<br>
	 *  0x00000000～0x05F5E0FF<br>
	 *  (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionMeasuredValuesAtFixedTimePositive(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 定時積算電力量計測値(逆方向計測値)<br>
	 * EPC                 : 0xEB<br>
	 * データタイプ        : unsigned short＋unsigned char×2+unsigned char×3＋unsigned long<br>
	 * サイズ              : 11 byte<br>
	 * 単位                : 年月日,時分秒,kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須(※1:逆方向計測機能がある場合は必須とする。)<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 最新の30 分毎の計測時刻における積算電力量(逆方向計測値)を、計測年月日を4 バイト、計測時刻を3 バイト、積算電力量（逆方向計測値）4 バイトで示す。<br>
	 * ・計測年月日 YYYY:MM:DD<br>
	 * ・計測時刻 hh:mm:ss<br>
	 * ・積算電力量10 進表記で最大8桁<br>
	 * 1～４バイト目：計測年月日<br>
	 *  YYYY:0x0001～0x270F<br>
	 *  (1～9999)<br>
	 *  MM:0x01～0x0C(1～12)<br>
	 *  DD:0x01～0x1F(1～31)<br>
	 * 5～７バイト目：計測時刻<br>
	 *  ｈｈ:0x00～0x17(0～23)<br>
	 *  mm:0x00～0x3B(0～59)<br>
	 *  ss:0x00～0x3B(0～59)<br>
	 * 8～11 バイト目：積算電力量計測値<br>
	 *  0x00000000～0x05F5E0FF<br>
	 *  (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionMeasuredValuesAtFixedTimeReverse(String edt){
		if(edt == null || !(edt.length() == 11 * 2)) return false;
		if(! checkMeasuredValueFormat(edt)) return false;
		return true;
	}
	/**
	 * 積算電力量計測値履歴２（正方向、逆方向計測値）<br>
	 * EPC                 : 0xEC<br>
	 * データタイプ        : unsigned short＋unsigned char×4＋unsigned char＋(unsigned long＋unsigned long)×(Max)12<br>
	 * サイズ              : Max 103 byte<br>
	 * 単位                : 年月日,時分,kWh<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 積算履歴収集日時、収集コマ数及び積算電力量(最大8 桁)の計測結果履歴を、正・逆30 分毎のデータで過去最大6 時間分示す。<br>
	 * ・積算履歴収集日時<br>
	 *  YYYY:MM:DD:hh:mm<br>
	 * ・収集コマ数<br>
	 * ・積算電力量<br>
	 *  10 進表記において最大8 桁、最大6 時間分<br>
	 * 1～6 バイト目：積算履歴収集日時<br>
	 * YYYY:0x0001～0x270F<br>
	 * (1～9999 年)<br>
	 * MM:0x01～0x0C(1～12 月)<br>
	 * DD:0x01～0x1F(1～31 日)<br>
	 * hh:0x00～0x17(0～23 時)<br>
	 * mm:0x00/0x1E(0/30 分)<br>
	 * 7 バイト目：収集コマ数<br>
	 * 0x01～0x0C(1～12 コマ)<br>
	 * 8 バイト目以降：積算電力量計測値(正方向)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * 積算電力量計測値(逆方向)<br>
	 * 0x00000000～0x05F5E0FF<br>
	 * (0～99,999,999)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionMeasuredValuesLog2(String edt){
		if(edt == null || !(edt.length() <= 103 * 2)) return false;
		if(! checkCumurativeMeasuredValueLogFormat(edt)) return false;
		return true;
	}
	/**
	 * 積算履歴収集日２<br>
	 * EPC                 : 0xED<br>
	 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
	 * サイズ              : 7 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
	 * 1～6 バイト目：積算履歴収集日時<br>
	 * YYYY:0x0001～0x270F<br>
	 * (1～9999 年)<br>
	 * MM:0x01～0x0C(1～12 月)<br>
	 * DD:0x01～0x1F(1～31 日)<br>
	 * hh:0x00～0x17(0～23 時)<br>
	 * mm:0x00/0x1E(0/30 分)<br>
	 * 7 バイト目：収集コマ数<br>
	 * 0x01～0x0C(1～12 コマ)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIntegralPowerConsumptionLogCollectionDay2(String edt){
		if(edt == null || !(edt.length() == 7 * 2)) return false;
		if(! checkCumurativeMeasuredValueFormat(edt)) return false;
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
	 * 積算履歴収集日１<br>
	 * EPC                 : 0xE5<br>
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
	 * 30 分毎の計測値履歴データを収集する日を示す。<br>
	 * 0x00～0x63<br>
	 * ( 0～99)<br>
	 * 0:当日 1～99:前日の日数<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIntegralPowerConsumptionLogCollectionDay1(String edt){return false;}
	/**
	 * 積算履歴収集日２<br>
	 * EPC                 : 0xED<br>
	 * データタイプ        : unsigned short＋unsignedchar×4+unsigned char<br>
	 * サイズ              : 7 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 30 分毎の計測値履歴データを収集する日時(30 分単位)、及び30分毎の計測値履歴データを1 コマとし、収集するコマ数を示す。<br>
	 * 1～6 バイト目：積算履歴収集日時<br>
	 * YYYY:0x0001～0x270F<br>
	 * (1～9999 年)<br>
	 * MM:0x01～0x0C(1～12 月)<br>
	 * DD:0x01～0x1F(1～31 日)<br>
	 * hh:0x00～0x17(0～23 時)<br>
	 * mm:0x00/0x1E(0/30 分)<br>
	 * 7 バイト目：収集コマ数<br>
	 * 0x01～0x0C(1～12 コマ)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIntegralPowerConsumptionLogCollectionDay2(String edt){return false;}

	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80", "動作状態");
			put("D3", "係数");
			put("D7", "積算電力量有効桁数");
			put("E0", "積算電力量計測値(正方向計測値)");
			put("E1", "積算電力量単位（正方向、逆方向計測値）");
			put("E2", "積算電力量計測値履歴１(正方向計測値)");
			put("E3", "積算電力量計測値(逆方向計測値)");
			put("E4", "積算電力量計測値履歴１(逆方向計測値)");
			put("E5", "積算履歴収集日１");
			put("E7", "瞬時電力計測値");
			put("E8", "瞬時電流計測値");
			put("EA", "定時積算電力量計測値(正方向計測値)");
			put("EB", "定時積算電力量計測値(逆方向計測値)");
			put("EC", "積算電力量計測値履歴２（正方向、逆方向計測値）");
			put("ED", "積算履歴収集日２");
		}
	};

	/**
	 * Edtにおける積算履歴収集日フォーマットをチェック（年,月,日,時,分,データをチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkCumurativeMeasuredValueLogFormat(String strEdt) {
		String strYmdhhmVal = strEdt.substring(0, 14);
		if(! checkCumurativeMeasuredValueFormat(strYmdhhmVal)) return false;
		String strLeftVals = strEdt.substring(14);
		while(strLeftVals.length() > 0) {
			String strVal = strLeftVals.substring(0, 8);
			if(! (ElUtil.compElUnsignedLong("00000000", strVal ) <= 0 && ElUtil.compElUnsignedLong(strVal , "05F5E0FF") <= 0 || ElUtil.compElUnsignedLong(strVal , "FFFFFFFE") == 0 )) return false;
			strLeftVals = strLeftVals.substring(8);
		}
		return true;
	}


	/**
	 * Edtにおける積算履歴収集日フォーマットをチェック（年,月,日,時,分,データをチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkCumurativeMeasuredValueFormat(String strEdt) {
		String strYmd = strEdt.substring(0, 8);
		String strHm = strEdt.substring(8, 12);
		String strVal = strEdt.substring(12, 14);

		if( ! (ElUtil.checkEdtYmdSetFormat(strYmd) || ElUtil.compElUnsignedLong("FFFFFFFF", strYmd) == 0)) return false;
		if( ! (ElUtil.checkEdtHmFormat(strHm) || ElUtil.compElUnsignedShort("FFFF", strYmd) == 0)) return false;
		if(! (ElUtil.compElUnsignedShort("01", strVal ) <= 0 && ElUtil.compElUnsignedShort(strVal , "0C") <= 0)) return false;
		return true;
	}
}
