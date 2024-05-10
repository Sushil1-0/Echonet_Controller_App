

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
public class ElectricWaterHeaterClass extends DeviceObjectSuperClass{

    /**
     * ログ出力用タグ
     */
    @SuppressWarnings("unused")
    private static final String TAG = "ElectricWaterHeaterClass";

    /**
     *
     *  クラスグループコード
     */
    public static final String CLASS_GROUP_CODE = "02";
    /**
     *  クラスコード
     */
    public static final String CLASS_CODE = "6B";

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


    public static final String EPC_AUTOMATIC_WATER_HEATING_SETTING = "B0";
    public static final String EDT_AUTOMATIC_WATER_HEATING_FUNCTION_USED = "41";
    public static final String EDT_AUTOMATIC_WATER_HEATING_FUNCTION_STOPPED = "43";
    public static final String EDT_NON_AUTOMATIC_WATER_HEATING_FUNCTION_USED = "42";


    public static final String EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING = "B1";
    public static final String EDT_AUTOMATIC_WATER_TEMPERATURE_CONTROL_FUNCTION_USED = "41";
    public static final String EDT_AUTOMATIC_WATER_TEMPERATURE_CONTROL_FUNCTION_NOT_USED = "42";

    public static final String EPC_WATER_HEATER_STATUS = "B2";
    public static final String EDT_WATER_HEATER_STATUS_HEATING = "41";
    public static final String EDT_WATER_HEATER_STATUS_NOT_HEATING = "42";

    public static final String EPC_WATER_HEATING_TEMPERATURE_SETTING = "B3";

    public static final String EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING = "B4";

    public static final String EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF = "B5";

    public static final String EPC_TANK_OPERATION_MODE_SETTING = "B6";
    public static final String EDT_TANK_OPERATION_MODE_SETTING_STANDARD = "41";
    public static final String EDT_TANK_OPERATION_MODE_SETTING_SAVING = "42";
    public static final String EDT_TANK_OPERATION_MODE_SETTING_EXTRA = "43";


    public static final String EPC_DAYTIME_REHEATING_PERMISSION_SETTING = "C0";
    public static final String EDT_DAYTIME_REHEATING_PERMITTED = "41";
    public static final String EDT_DAYTIME_REHEATING_NOT_PERMITTED = "42";

    public static final String EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER = "C1";

    public static final String EPC_ALARM_STATUS = "C2";

    public static final String EPC_HOT_WATER_SUPPLY_STATUS = "C3";
    public static final String EDT_SUPPLYING_HOT_WATER = "41";
    public static final String EDT_NOT_SUPPLYING_HOT_WATER = "42";

    public static final String EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE = "C4";

    public static final String EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING = "D1";

    public static final String EPC_BATH_WATER_TEMPERATURE_SETTING = "D3";

    public static final String EPC_BATH_WATER_VOLUME_SETTING = "E0";

    public static final String EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK = "E1";

    public static final String EPC_TANK_CAPACITY = "E2";

    public static final String EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING = "E3";
    public static final String EDT_AUTOMATIC_BATH_WATER_HEATING_MODE_ON = "41";
    public static final String EDT_AUTOMATIC_BATH_WATER_HEATING_MODE_OFF = "42";

    public static final String EPC_BATHROOM_PRIORITY_SETTING = "E9";
    public static final String EDT_BATHROOM_PRIORITY_SETTING_ON = "41";
    public static final String EDT_BATHROOM_PRIORITY_SETTING_OFF = "42";

    public static final String EPC_BATH_OPERATION_STATUS_MONITOR = "EA";
    public static final String EDT_BATH_OPERATION_STATUS_MONITOR_FILLING_HOT_WATER = "41";
    public static final String EDT_BATH_OPERATION_STATUS_MONITOR_STOPPED = "42";
    public static final String EDT_BATH_OPERATION_STATUS_MONITOR_KEEPING_TEMPERATURE = "43";

    public static final String EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING = "E4";
    public static final String EDT_BATH_REHEATING_ON= "41";
    public static final String EDT_BATH_REHEATING_OFF= "42";

    public static final String EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING = "E5";
    public static final String EDT_ADDITION_OF_HOT_WATER_FUNCTION_ON= "41";
    public static final String EDT_ADDITION_OF_HOT_WATER_FUNCTION_OFF= "42";


    public static final String EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING = "ED";
    public static final String EDT_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_ON = "41";
    public static final String EDT_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_OOF = "42";

    public static final String EPC_WATER_VOLUME_SETTING_1 = "E7";

    public static final String EPC_WATER_VOLUME_SETTING_2 = "E8";

    public static final String EPC_WATER_VOLUME_SETTING_3 = "EE";

    public static final String EPC_WATER_VOLUME_SETTING_4 = "D4";

    public static final String EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL = "D5";

    public static final String EPC_ON_TIMER_RESERVATION_SETTING = "90";
    public static final String EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_ON = "41";
    public static final String EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_OFF = "42";

    public static final String EPC_ON_TIMER_SETTING = "91";

    public static final String EPC_VOLUME_SETTING = "D6";

    public static final String EPC_MUTE_SETTING = "D7";
    public static final String EDT_MUTE_SETTING_MUTE_ON = "30";
    public static final String EDT_MUTE_SETTING_MUTE_OFF = "31";

    public static final String EPC_REMAINING_HOT_WATER_VOLUME = "D8";

    public static final String EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE = "D9";

    public static final String EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME = "DB";

    public static final String EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS = "DC";

    public static final String EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME = "DD";

    public static final String EPC_PARTICIPATION_IN_ENERGY_SHIFT = "C7";

    public static final String EPC_STANDARD_TIME_TO_START_HEATING = "C8";

    public static final String EPC_NUMBER_OF_ENERGY_SHIFTS = "C9";

    public static final String EPC_DAYTIME_HEATING_SHIFT_TIME_1 = "CA";

    public static final String EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1 = "CB";

    public static final String EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1 = "CC";

    public static final String EPC_DAYTIME_HEATING_SHIFT_TIME_2 = "CD";

    public static final String EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2 = "CE";

    public static final String EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2 = "CF";

    public ElectricWaterHeaterClass() {
        super(CLASS_GROUP_CODE, CLASS_CODE);
    }
    /**
     *  電気温水器
     * @param entityCode エンティティコード（16進数文字列）
     */
    public ElectricWaterHeaterClass(String entityCode) {
        super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initPorpMaps(){
        super.initPorpMaps();

        this.addMapGetProps(EPC_OPERATION_STATUS);
        this.addMapGetProps(EPC_AUTOMATIC_WATER_HEATING_SETTING);
        this.addMapGetProps(EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING);
        this.addMapGetProps(EPC_WATER_HEATER_STATUS);
        this.addMapGetProps(EPC_WATER_HEATING_TEMPERATURE_SETTING);
        this.addMapGetProps(EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING);
        this.addMapGetProps(EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF);
        this.addMapGetProps(EPC_TANK_OPERATION_MODE_SETTING);
        this.addMapGetProps(EPC_DAYTIME_REHEATING_PERMISSION_SETTING);
        this.addMapGetProps(EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER);
        this.addMapGetProps(EPC_ALARM_STATUS);
        this.addMapGetProps(EPC_HOT_WATER_SUPPLY_STATUS);
        this.addMapGetProps(EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE);
        this.addMapGetProps(EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING);
        this.addMapGetProps(EPC_BATH_WATER_TEMPERATURE_SETTING);
        this.addMapGetProps(EPC_BATH_WATER_VOLUME_SETTING);
        this.addMapGetProps(EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK);
        this.addMapGetProps(EPC_TANK_CAPACITY);
        this.addMapGetProps(EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING);
        this.addMapGetProps(EPC_BATHROOM_PRIORITY_SETTING);
        this.addMapGetProps(EPC_BATH_OPERATION_STATUS_MONITOR);
        this.addMapGetProps(EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING);
        this.addMapGetProps(EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING);
        this.addMapGetProps(EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING);
        this.addMapGetProps(EPC_WATER_VOLUME_SETTING_1);
        this.addMapGetProps(EPC_WATER_VOLUME_SETTING_2);
        this.addMapGetProps(EPC_WATER_VOLUME_SETTING_3);
        this.addMapGetProps(EPC_WATER_VOLUME_SETTING_4);
        this.addMapGetProps(EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL);
        this.addMapGetProps(EPC_ON_TIMER_RESERVATION_SETTING );
        this.addMapGetProps(EPC_ON_TIMER_SETTING );
        this.addMapGetProps(EPC_VOLUME_SETTING );
        this.addMapGetProps(EPC_MUTE_SETTING );
        this.addMapGetProps(EPC_REMAINING_HOT_WATER_VOLUME );
        this.addMapGetProps(EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE );
        this.addMapGetProps(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME );
        this.addMapGetProps(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS );
        this.addMapGetProps(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME );
        this.addMapGetProps(EPC_PARTICIPATION_IN_ENERGY_SHIFT );
        this.addMapGetProps(EPC_STANDARD_TIME_TO_START_HEATING );
        this.addMapGetProps(EPC_NUMBER_OF_ENERGY_SHIFTS );
        this.addMapGetProps(EPC_DAYTIME_HEATING_SHIFT_TIME_1 );
        this.addMapGetProps(EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1  );
        this.addMapGetProps(EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1  );
        this.addMapGetProps(EPC_DAYTIME_HEATING_SHIFT_TIME_2 );
        this.addMapGetProps(EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2);
        this.addMapGetProps(EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2 );

        this.addMapSetProps(EPC_OPERATION_STATUS);
        this.addMapSetProps(EPC_AUTOMATIC_WATER_HEATING_SETTING);
        this.addMapSetProps(EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING);
        this.addMapSetProps(EPC_WATER_HEATING_TEMPERATURE_SETTING);
        this.addMapSetProps(EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING);
        this.addMapSetProps(EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF);
        this.addMapSetProps(EPC_TANK_OPERATION_MODE_SETTING);
        this.addMapSetProps(EPC_DAYTIME_REHEATING_PERMISSION_SETTING);
        this.addMapSetProps(EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE);
        this.addMapSetProps(EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING);
        this.addMapSetProps(EPC_BATH_WATER_TEMPERATURE_SETTING);
        this.addMapSetProps(EPC_BATH_WATER_VOLUME_SETTING);
        this.addMapSetProps(EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING);
        this.addMapSetProps(EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING);
        this.addMapSetProps(EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING);
        this.addMapSetProps(EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING);
        this.addMapSetProps(EPC_BATH_OPERATION_STATUS_MONITOR);
        this.addMapSetProps(EPC_WATER_VOLUME_SETTING_1);
        this.addMapSetProps(EPC_WATER_VOLUME_SETTING_2);
        this.addMapSetProps(EPC_WATER_VOLUME_SETTING_3);
        this.addMapSetProps(EPC_WATER_VOLUME_SETTING_4);
        this.addMapSetProps(EPC_ON_TIMER_RESERVATION_SETTING );
        this.addMapSetProps(EPC_ON_TIMER_SETTING );
        this.addMapSetProps(EPC_VOLUME_SETTING );
        this.addMapSetProps(EPC_MUTE_SETTING );
        this.addMapSetProps(EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE );
        this.addMapSetProps(EPC_PARTICIPATION_IN_ENERGY_SHIFT );
        this.addMapSetProps(EPC_DAYTIME_HEATING_SHIFT_TIME_1);
        this.addMapSetProps(EPC_DAYTIME_HEATING_SHIFT_TIME_2);



        this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onNewEojFound(){
        super.onNewEojFound();
        getElProcess().getElBulkEventProcessor().onNewElectricWaterHeaterFound(this);
    }

    public static class ElSetProps extends DeviceObjectSuperClass.ElSetProps {

        /**
         * ログ出力用タグ
         */
        @SuppressWarnings("unused")
        private static final String TAG = "ElectricWaterHeaterClass.ElSetProps";

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
         * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
         */
        public ElectricWaterHeaterClass.ElSetProps reqSetOperationStatus(String strEdt){
            listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
            return this;
        }


        public ElectricWaterHeaterClass.ElSetProps reqSetAutomaticWaterHeatingSetting(String strEdt){
            listProperty.add(new ElProp(EPC_AUTOMATIC_WATER_HEATING_SETTING, strEdt));
            return this;
        }


        public ElectricWaterHeaterClass.ElSetProps reqSetAutomaticWaterTemperatureControlSetting(String strEdt){
            listProperty.add(new ElProp(EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetWaterHeatingTemperatureSetting(String strEdt){
            listProperty.add(new ElProp(EPC_WATER_HEATING_TEMPERATURE_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetManualWaterHeatingStopDaysSetting(String strEdt){
            listProperty.add(new ElProp(EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetRelativeTimeSettingValueForManualHeatingOff(String strEdt){
            listProperty.add(new ElProp(EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetTankOperationModeSetting(String strEdt){
            listProperty.add(new ElProp(EPC_TANK_OPERATION_MODE_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetDaytimeReheatingPermissionSetting(String strEdt){
            listProperty.add(new ElProp(EPC_DAYTIME_REHEATING_PERMISSION_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetRelativeTimeSettingForKeepingBathTemperature(String strEdt){
            listProperty.add(new ElProp(EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetTemperatureOfSuppliedWaterSetting(String strEdt){
            listProperty.add(new ElProp(EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetBathWaterTemperatureSetting(String strEdt){
            listProperty.add(new ElProp(EPC_BATH_WATER_TEMPERATURE_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetBathWaterVolumeSetting(String strEdt){
            listProperty.add(new ElProp(EPC_BATH_WATER_VOLUME_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetAutomaticBathWaterHeatingModeSetting(String strEdt){
            listProperty.add(new ElProp(EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetManualBathReheatingOperationSetting(String strEdt){
            listProperty.add(new ElProp(EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetManualBathHotWaterAdditionFunctionSetting(String strEdt){
            listProperty.add(new ElProp(EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING, strEdt));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetManualLukewarmWaterTemperatureLoweringFunctionSetting(String strEdt){
            listProperty.add(new ElProp(EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING, strEdt));
            return this;
        }
        public ElectricWaterHeaterClass.ElSetProps reqSetWaterVolumeSetting1(String strEdt){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_1, strEdt));
            return this;
        }
        public ElectricWaterHeaterClass.ElSetProps reqSetWaterVolumeSetting2(String strEdt){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_2, strEdt));
            return this;
        }
        public ElectricWaterHeaterClass.ElSetProps reqSetWaterVolumeSetting3(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_3));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetWaterVolumeSetting4(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_4));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetOnTimerReaservationSetting(){
            listProperty.add(new ElProp(EPC_ON_TIMER_RESERVATION_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqGetOnTimerSetting(){
            listProperty.add(new ElProp(EPC_ON_TIMER_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetVolumeSetting(){
            listProperty.add(new ElProp(EPC_VOLUME_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetMuteSetting(){
            listProperty.add(new ElProp(EPC_MUTE_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetSurplusElectricEnergyPowerPredictionValue(){
            listProperty.add(new ElProp(EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE ));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetParticipationInEnergyShift(){
            listProperty.add(new ElProp(EPC_PARTICIPATION_IN_ENERGY_SHIFT ));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetDaytimeHeatingShiftTime1(){
            listProperty.add(new ElProp(EPC_DAYTIME_HEATING_SHIFT_TIME_1));
            return this;
        }

        public ElectricWaterHeaterClass.ElSetProps reqSetDaytimeHeatingShiftTime2(){
            listProperty.add(new ElProp(EPC_DAYTIME_HEATING_SHIFT_TIME_2));
            return this;
        }
    }

    public static class ElGetProps extends DeviceObjectSuperClass.ElGetProps  {

        /**
         * ログ出力用タグ
         */
        @SuppressWarnings("unused")
        private static final String TAG = "ElectricWaterHeaterClass.ElGetProps";

        /**
         * {@inheritDoc}
         */
        public ElGetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
            super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetOperationStatus(){
            listProperty.add(new ElProp(EPC_OPERATION_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetAutomaticWaterHeatingSetting(){
            listProperty.add(new ElProp(EPC_AUTOMATIC_WATER_HEATING_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetAutomaticWaterTemperatureControlSetting(){
            listProperty.add(new ElProp(EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetWaterHeaterStatus(){
            listProperty.add(new ElProp(EPC_WATER_HEATER_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetWaterHeatingTemperatureSetting(){
            listProperty.add(new ElProp(EPC_WATER_HEATING_TEMPERATURE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetManualWaterHeatingStopDaysSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetRelativeTimeSettingValueForManualHeatingOff(){
            listProperty.add(new ElProp(EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetTankOperationModeSetting(){
            listProperty.add(new ElProp(EPC_TANK_OPERATION_MODE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetDaytimeReheatingPermissionSetting(){
            listProperty.add(new ElProp(EPC_DAYTIME_REHEATING_PERMISSION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetMeasuredTemperatureOfWaterInWaterHeater(){
            listProperty.add(new ElProp(EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetAlarmStatus(){
            listProperty.add(new ElProp(EPC_ALARM_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetHotWaterSupplyStatus(){
            listProperty.add(new ElProp(EPC_HOT_WATER_SUPPLY_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetRelativeTimeSettingForKeepingBathTemperature(){
            listProperty.add(new ElProp(EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetTemperatureOfSuppliedWaterSetting(){
            listProperty.add(new ElProp(EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetBathWaterTemperatureSetting(){
            listProperty.add(new ElProp(EPC_BATH_WATER_TEMPERATURE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetBathWaterVolumeSetting(){
            listProperty.add(new ElProp(EPC_BATH_WATER_VOLUME_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetMeasuredAmountOfWaterReamingInTank(){
            listProperty.add(new ElProp(EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetTankCapacity(){
            listProperty.add(new ElProp(EPC_TANK_CAPACITY));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetAutomaticBathWaterHeatingModeSetting(){
            listProperty.add(new ElProp(EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetBathroomPrioritySetting(){
            listProperty.add(new ElProp(EPC_BATHROOM_PRIORITY_SETTING));
            return this;
        }

      public ElectricWaterHeaterClass.ElGetProps reqGetBathOperationStatusMonitor(){
            listProperty.add(new ElProp(EPC_BATH_OPERATION_STATUS_MONITOR));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetManualBathReheatingOperationSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetManualBathHotWaterAdditionFunctionSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetManualLukewarmWaterTemperatureLoweringFunctionSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetWaterVolumeSetting1(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_1));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetWaterVolumeSetting2(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_2));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetWaterVolumeSetting3(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_3));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetWaterVolumeSetting4(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_4));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetBathWaterVolumeSetting4MaximumSettableLevel(){
            listProperty.add(new ElProp(EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetOnTimerReaservationSetting(){
            listProperty.add(new ElProp(EPC_ON_TIMER_RESERVATION_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetOnTimerSetting(){
            listProperty.add(new ElProp(EPC_ON_TIMER_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetVolumeSetting(){
            listProperty.add(new ElProp(EPC_VOLUME_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetMuteSetting(){
            listProperty.add(new ElProp(EPC_MUTE_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetRemainingHotWaterVolume(){
            listProperty.add(new ElProp(EPC_REMAINING_HOT_WATER_VOLUME ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetSurplusElectricEnergyPowerPredictionValue(){
            listProperty.add(new ElProp(EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetRatedPowerConsumptionOfHpUnitInWinterTime(){
            listProperty.add(new ElProp(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetRatedPowerConsumptionOfHpUnitInInBetweenSeasons(){
            listProperty.add(new ElProp(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetRatedPowerConsumptionOfHpUnitInSummertime(){
            listProperty.add(new ElProp(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetParticipationInEnergyShift(){
            listProperty.add(new ElProp(EPC_PARTICIPATION_IN_ENERGY_SHIFT ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetStandardTimeToStartHeating(){
            listProperty.add(new ElProp(EPC_STANDARD_TIME_TO_START_HEATING));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetNumberOfEnergyShifts(){
            listProperty.add(new ElProp(EPC_NUMBER_OF_ENERGY_SHIFTS));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetDaytimeHeatingShiftTime1(){
            listProperty.add(new ElProp(EPC_DAYTIME_HEATING_SHIFT_TIME_1));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetExpectedElectricEnergyAtDaytimeHeatingShiftTime1(){
            listProperty.add(new ElProp(EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1 ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetConsumptionofElectricEnergyPerHour1(){
            listProperty.add(new ElProp(EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1 ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetDaytimeHeatingShiftTime2(){
            listProperty.add(new ElProp(EPC_DAYTIME_HEATING_SHIFT_TIME_2));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetExpectedElectricEnergyDaytimeHeatingShiftTime2(){
            listProperty.add(new ElProp(EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2 ));
            return this;
        }

        public ElectricWaterHeaterClass.ElGetProps reqGetConsumptionofElectricPerHour2(){
            listProperty.add(new ElProp(EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2));
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
        private static final String TAG = "ElectricWaterHeaterClass.ElInformProps";

        /**
         * {@inheritDoc}
         */
        public ElInformProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
            super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfOperationStatus(){
            listProperty.add(new ElProp(EPC_OPERATION_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfAutomaticWaterHeatingSetting(){
            listProperty.add(new ElProp(EPC_AUTOMATIC_WATER_HEATING_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfAutomaticWaterTemperatureControlSetting(){
            listProperty.add(new ElProp(EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfWaterHeaterStatus(){
            listProperty.add(new ElProp(EPC_WATER_HEATER_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInftWaterHeatingTemperatureSetting(){
            listProperty.add(new ElProp(EPC_WATER_HEATING_TEMPERATURE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInftManualWaterHeatingStopDaysSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfRelativeTimeSettingValueForManualHeatingOff(){
            listProperty.add(new ElProp(EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfTankOperationModeSetting(){
            listProperty.add(new ElProp(EPC_TANK_OPERATION_MODE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInftDaytimeReheatingPermissionSetting(){
            listProperty.add(new ElProp(EPC_DAYTIME_REHEATING_PERMISSION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfMeasuredTemperatureOfWaterInWaterHeater(){
            listProperty.add(new ElProp(EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfAlarmStatus(){
            listProperty.add(new ElProp(EPC_ALARM_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfHotWaterSupplyStatus(){
            listProperty.add(new ElProp(EPC_HOT_WATER_SUPPLY_STATUS));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfRelativeTimeSettingForKeepingBathTemperature(){
            listProperty.add(new ElProp(EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfTemperatureOfSuppliedWaterSetting(){
            listProperty.add(new ElProp(EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfBathWaterTemperatureSetting(){
            listProperty.add(new ElProp(EPC_BATH_WATER_TEMPERATURE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfBathWaterVolumeSetting(){
            listProperty.add(new ElProp(EPC_BATH_WATER_VOLUME_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfMeasuredAmountOfWaterReamingInTank(){
            listProperty.add(new ElProp(EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfTankCapacity(){
            listProperty.add(new ElProp(EPC_TANK_CAPACITY));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfAutomaticBathWaterHeatingModeSetting(){
            listProperty.add(new ElProp(EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfBathroomPrioritySetting(){
            listProperty.add(new ElProp(EPC_BATHROOM_PRIORITY_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfBathOperationStatusMonitor(){
            listProperty.add(new ElProp(EPC_BATH_OPERATION_STATUS_MONITOR));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfManualBathReheatingOperationSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfManualBathHotWaterAdditionFunctionSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfManualLukewarmWaterTemperatureLoweringFunctionSetting(){
            listProperty.add(new ElProp(EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfWaterVolumeSetting1(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_1));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfWaterVolumeSetting2(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_2));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfWaterVolumeSetting3(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_3));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfWaterVolumeSetting4(){
            listProperty.add(new ElProp(EPC_WATER_VOLUME_SETTING_4));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfBathWaterVolumeSetting4MaximumSettableLevel(){
            listProperty.add(new ElProp(EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfOnTimerReaservationSetting(){
            listProperty.add(new ElProp(EPC_ON_TIMER_RESERVATION_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfOnTimerSetting(){
            listProperty.add(new ElProp(EPC_ON_TIMER_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfVolumeSetting(){
            listProperty.add(new ElProp(EPC_VOLUME_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfMuteSetting(){
            listProperty.add(new ElProp(EPC_MUTE_SETTING ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfRemainingHotWaterVolume(){
            listProperty.add(new ElProp(EPC_REMAINING_HOT_WATER_VOLUME ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfSurplusElectricEnergyPowerPredictionValue(){
            listProperty.add(new ElProp(EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfRatedPowerConsumptionOfHpUnitInWinterTime(){
            listProperty.add(new ElProp(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfRatedPowerConsumptionOfHpUnitInInBetweenSeasons(){
            listProperty.add(new ElProp(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfRatedPowerConsumptionOfHpUnitInSummertime(){
            listProperty.add(new ElProp(EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfParticipationInEnergyShift(){
            listProperty.add(new ElProp(EPC_PARTICIPATION_IN_ENERGY_SHIFT ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfStandardTimeToStartHeating(){
            listProperty.add(new ElProp(EPC_STANDARD_TIME_TO_START_HEATING));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfNumberOfEnergyShifts(){
            listProperty.add(new ElProp(EPC_NUMBER_OF_ENERGY_SHIFTS));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfDaytimeHeatingShiftTime1(){
            listProperty.add(new ElProp(EPC_DAYTIME_HEATING_SHIFT_TIME_1));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfExpectedElectricEnergyAtDaytimeHeatingShiftTime1(){
            listProperty.add(new ElProp(EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1 ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfConsumptionofElectricEnergyPerHour1(){
            listProperty.add(new ElProp(EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1 ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfDaytimeHeatingShiftTime2(){
            listProperty.add(new ElProp(EPC_DAYTIME_HEATING_SHIFT_TIME_2));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfExpectedElectricEnergyDaytimeHeatingShiftTime2(){
            listProperty.add(new ElProp(EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2 ));
            return this;
        }

        public ElectricWaterHeaterClass.ElInformProps reqInfConsumptionofElectricPerHour2(){
            listProperty.add(new ElProp(EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2));
            return this;
        }


    }

    public static ElectricWaterHeaterClass.ElSetProps setIAll(ElProcess elProcess) {
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
        return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ElectricWaterHeaterClass(instanceCode), ElFrame.ESV_SETI);
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElSetProps setCAll(ElProcess elProcess) {
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
        return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ElectricWaterHeaterClass(instanceCode), ElFrame.ESV_SETC);
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
     * @return ElectricWaterHeaterClass.ElGetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElGetProps getAll(ElProcess elProcess) {
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
     * @return ElectricWaterHeaterClass.ElGetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
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
     * @return ElectricWaterHeaterClass.ElGetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
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
     * @return ElectricWaterHeaterClass.ElGetPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
        return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ElectricWaterHeaterClass(instanceCode), ElFrame.ESV_GET);
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElInformProps infReqAll(ElProcess elProcess) {
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    public static ElectricWaterHeaterClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
        return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ElectricWaterHeaterClass(instanceCode), ElFrame.ESV_INF_REQ);
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElSetProps setI(){
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElSetProps setI(ElClassBase seoj){
        return new ElectricWaterHeaterClass.ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETI);
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElSetProps setC(){
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
     * @return ElectricWaterHeaterClass.ElSetPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElSetProps setC(ElClassBase seoj){
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
     * @return ElectricWaterHeaterClass.ElGetPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElGetProps get(){
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
     * @return ElectricWaterHeaterClass.ElGetPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElGetProps get(ElClassBase seoj){
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElInformProps infReq(){
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElInformProps infReq(ElClassBase seoj){
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElInformProps inf(){
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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    @Override
    public ElectricWaterHeaterClass.ElInformProps inf(ElClassBase deoj){

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
     * @return ElectricWaterHeaterClass.ElInformPropsオブジェクト
     */
    public ElectricWaterHeaterClass.ElInformProps infC(String remoteIpAddress){
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
    public ElectricWaterHeaterClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
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
        private static final String TAG = "ElectricWaterHeaterClass.ReportProcessor";

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
                case EPC_AUTOMATIC_WATER_HEATING_SETTING:
                    onSetAutomaticWaterHeatingSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING:
                    onSetAutomaticWaterTemperatureControlSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_HEATING_TEMPERATURE_SETTING:
                    onSettWaterHeatingTemperatureSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING:
                    onSetManualWaterHeatingStopDaysSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF:
                    onSetRelativeTimeSettingValueForManualHeatingOff(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_TANK_OPERATION_MODE_SETTING:
                    onSetTankOperationModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_DAYTIME_REHEATING_PERMISSION_SETTING:
                    onSetDaytimeReheatingPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE:
                    onSetRelativeTimeSettingForKeepingBathTemperature(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING:
                    onSetTemperatureOfSuppliedWaterSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_BATH_WATER_TEMPERATURE_SETTING:
                    onSetBathWaterTemperatureSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_BATH_WATER_VOLUME_SETTING:
                    onSetBathWaterVolumeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING:
                    onSetAutomaticBathWaterHeatingModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING:
                    onSetManualBathReheatingOperationSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING:
                    onSetManualBathHotWaterAdditionFunctionSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING:
                    onSetManualLukewarmWaterTemperatureLoweringFunctionSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_1:
                    onSetWaterVolumeSetting1(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_2:
                    onSetWaterVolumeSetting2(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_3:
                    onSetWaterVolumeSetting3(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_4:
                    onSetWaterVolumeSetting4(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_ON_TIMER_RESERVATION_SETTING :
                    onSetOnTimerReaservationSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_ON_TIMER_SETTING :
                    onSetOnTimerSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_VOLUME_SETTING :
                    onSetVolumeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MUTE_SETTING :
                    onSetMuteSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE :
                    onSetSurplusElectricEnergyPowerPredictionValue(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_PARTICIPATION_IN_ENERGY_SHIFT  :
                    onSetParticipationInEnergyShift(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_DAYTIME_HEATING_SHIFT_TIME_1 :
                    onSetDaytimeHeatingShiftTime1(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_DAYTIME_HEATING_SHIFT_TIME_2 :
                    onSetDaytimeHeatingShiftTime2(seoj, strTid, strEsv, objProp, isSuccess);
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
                case EPC_AUTOMATIC_WATER_HEATING_SETTING:
                    onGetAutomaticWaterHeatingSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING:
                    onGetAutomaticWaterTemperatureControlSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_HEATER_STATUS:
                    onGetWaterHeaterStatus(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_HEATING_TEMPERATURE_SETTING:
                    onGetWaterHeatingTemperatureSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING:
                    onGetManualWaterHeatingStopDaysSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF:
                    onGetRelativeTimeSettingValueForManualHeatingOff(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_TANK_OPERATION_MODE_SETTING:
                    onGetTankOperationModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_DAYTIME_REHEATING_PERMISSION_SETTING:
                    onGetDaytimeReheatingPermissionSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER:
                    onGetMeasuredTemperatureOfWaterInWaterHeater(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_ALARM_STATUS:
                    onGetAlarmStatus(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_HOT_WATER_SUPPLY_STATUS:
                    onGetHotWaterSupplyStatus(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE:
                    onGetRelativeTimeSettingForKeepingBathTemperature(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING:
                    onGetTemperatureOfSuppliedWaterSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_BATH_WATER_TEMPERATURE_SETTING:
                    onGetBathWaterTemperatureSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_BATH_WATER_VOLUME_SETTING:
                    onGetBathWaterVolumeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK:
                    onGetMeasuredAmountOfWaterReamingInTank(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_TANK_CAPACITY:
                    onGetTankCapacity(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING:
                    onGetAutomaticBathWaterHeatingModeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_BATHROOM_PRIORITY_SETTING:
                    onGetBathroomPrioritySetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_BATH_OPERATION_STATUS_MONITOR:
                    onGetBathOperationStatusMonitor(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING:
                    onGetManualBathReheatingOperationSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING:
                    onGetManualBathHotWaterAdditionFunctionSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING:
                    onGetManualLukewarmWaterTemperatureLoweringFunctionSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_1:
                    onGetWaterVolumeSetting1(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_2:
                    onGetWaterVolumeSetting2(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_3:
                    onGetWaterVolumeSetting3(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_WATER_VOLUME_SETTING_4:
                    onGetWaterVolumeSetting4(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL:
                    onGetBathWaterVolumeSetting4MaximumSettableLevel(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_ON_TIMER_RESERVATION_SETTING :
                    onGetOnTimerReaservationSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_ON_TIMER_SETTING :
                    onGetOnTimerSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_VOLUME_SETTING :
                    onGetVolumeSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_MUTE_SETTING :
                    onGetMuteSetting(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_REMAINING_HOT_WATER_VOLUME :
                    onGetRemainingHotWaterVolume(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE :
                    onGetSurplusElectricEnergyPowerPredictionValue(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME  :
                    onGetRatedPowerConsumptionOfHpUnitInWinterTime(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS  :
                    onGetRatedPowerConsumptionOfHpUnitInInBetweenSeasons(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME  :
                    onGetRatedPowerConsumptionOfHpUnitInSummertime(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_PARTICIPATION_IN_ENERGY_SHIFT  :
                    onGetParticipationInEnergyShift(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_STANDARD_TIME_TO_START_HEATING :
                    onGetStandardTimeToStartHeating(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_NUMBER_OF_ENERGY_SHIFTS :
                    onGetNumberOfEnergyShifts(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_DAYTIME_HEATING_SHIFT_TIME_1 :
                    onGetDaytimeHeatingShiftTime1(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1  :
                    onGetExpectedElectricEnergyAtDaytimeHeatingShiftTime1(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1  :
                    onGetConsumptionofElectricEnergyPerHour1(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_DAYTIME_HEATING_SHIFT_TIME_2 :
                    onGetDaytimeHeatingShiftTime2(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2  :
                    onGetExpectedElectricEnergyDaytimeHeatingShiftTime2(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2 :
                    onGetConsumptionofElectricPerHour2(seoj, strTid, strEsv, objProp, isSuccess);
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
                    onInformOperationStatus(seoj, strTid, strEsv, objProp );
                    return;
                case EPC_AUTOMATIC_WATER_HEATING_SETTING:
                    onInformAutomaticWaterHeatingSetting(seoj, strTid, strEsv, objProp );
                    return;
                case EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING:
                    onInformAutomaticWaterTemperatureControlSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_WATER_HEATER_STATUS:
                    onInformWaterHeaterStatus(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_WATER_HEATING_TEMPERATURE_SETTING:
                    onInformWaterHeatingTemperatureSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING:
                    onInformManualWaterHeatingStopDaysSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF:
                    onInformRelativeTimeSettingValueForManualHeatingOff(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_TANK_OPERATION_MODE_SETTING:
                    onInformTankOperationModeSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_DAYTIME_REHEATING_PERMISSION_SETTING:
                    onInformDaytimeReheatingPermissionSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER:
                    onInformMeasuredTemperatureOfWaterInWaterHeater(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_ALARM_STATUS:
                    onInformAlarmStatus(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_HOT_WATER_SUPPLY_STATUS:
                    onInformHotWaterSupplyStatus(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE:
                    onInformRelativeTimeSettingForKeepingBathTemperature(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING:
                    onInformTemperatureOfSuppliedWaterSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_BATH_WATER_TEMPERATURE_SETTING:
                    onInformBathWaterTemperatureSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_BATH_WATER_VOLUME_SETTING:
                    onInformBathWaterVolumeSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK:
                    onInformMeasuredAmountOfWaterReamingInTank(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_TANK_CAPACITY:
                    onInformTankCapacity(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING:
                    onInformAutomaticBathWaterHeatingModeSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_BATHROOM_PRIORITY_SETTING:
                    onInformBathroomPrioritySetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_BATH_OPERATION_STATUS_MONITOR:
                    onInformBathOperationStatusMonitor(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING:
                    onInformManualBathReheatingOperationSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING:
                    onInformManualBathHotWaterAdditionFunctionSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING:
                    onInformManualLukewarmWaterTemperatureLoweringFunctionSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_WATER_VOLUME_SETTING_1:
                    onInformWaterVolumeSetting1(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_WATER_VOLUME_SETTING_2:
                    onInformWaterVolumeSetting2(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_WATER_VOLUME_SETTING_3:
                    onInformWaterVolumeSetting3(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_WATER_VOLUME_SETTING_4:
                    onInformWaterVolumeSetting4(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL:
                    onInformBathWaterVolumeSetting4MaximumSettableLevel(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_ON_TIMER_RESERVATION_SETTING :
                    onInformOnTimerReaservationSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_ON_TIMER_SETTING :
                    onInformOnTimerSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_VOLUME_SETTING :
                    onInformVolumeSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_MUTE_SETTING :
                    onInformMuteSetting(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_REMAINING_HOT_WATER_VOLUME :
                    onInformRemainingHotWaterVolume(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE :
                    onInformSurplusElectricEnergyPowerPredictionValue(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME  :
                    onInformRatedPowerConsumptionOfHpUnitInWinterTime(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS  :
                    onInformRatedPowerConsumptionOfHpUnitInInBetweenSeasons(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME  :
                    onInformRatedPowerConsumptionOfHpUnitInSummertime(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_PARTICIPATION_IN_ENERGY_SHIFT  :
                    onInformParticipationInEnergyShift(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_STANDARD_TIME_TO_START_HEATING :
                    onInformStandardTimeToStartHeating(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_NUMBER_OF_ENERGY_SHIFTS :
                    onInformNumberOfEnergyShifts(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_DAYTIME_HEATING_SHIFT_TIME_1 :
                    onInformDaytimeHeatingShiftTime1(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1  :
                    onInformExpectedElectricEnergyAtDaytimeHeatingShiftTime1(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1  :
                    onInformConsumptionofElectricEnergyPerHour1(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_DAYTIME_HEATING_SHIFT_TIME_2 :
                    onInformDaytimeHeatingShiftTime2(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2  :
                    onInformExpectedElectricEnergyDaytimeHeatingShiftTime2(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2 :
                    onInformConsumptionofElectricPerHour2(seoj, strTid, strEsv, objProp);
                    return;

                default:
                    return;
            }

        }
        /**
         * Set関連
         */
        public void onSetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetAutomaticWaterHeatingSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetAutomaticWaterTemperatureControlSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSettWaterHeatingTemperatureSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetManualWaterHeatingStopDaysSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetRelativeTimeSettingValueForManualHeatingOff(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetTankOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetDaytimeReheatingPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetRelativeTimeSettingForKeepingBathTemperature(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetTemperatureOfSuppliedWaterSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetBathWaterTemperatureSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetBathWaterVolumeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetAutomaticBathWaterHeatingModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetManualBathReheatingOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetManualBathHotWaterAdditionFunctionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetManualLukewarmWaterTemperatureLoweringFunctionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetWaterVolumeSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetWaterVolumeSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetWaterVolumeSetting3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetWaterVolumeSetting4(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetOnTimerReaservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetOnTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetVolumeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetMuteSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetSurplusElectricEnergyPowerPredictionValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetParticipationInEnergyShift(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetDaytimeHeatingShiftTime1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onSetDaytimeHeatingShiftTime2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};


        /**
         * Get 関連
         */

        public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetAutomaticWaterHeatingSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetAutomaticWaterTemperatureControlSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetWaterHeaterStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetWaterHeatingTemperatureSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetManualWaterHeatingStopDaysSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetRelativeTimeSettingValueForManualHeatingOff(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetTankOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetDaytimeReheatingPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetMeasuredTemperatureOfWaterInWaterHeater(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetAlarmStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetHotWaterSupplyStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetRelativeTimeSettingForKeepingBathTemperature(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetTemperatureOfSuppliedWaterSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetBathWaterTemperatureSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetBathWaterVolumeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetMeasuredAmountOfWaterReamingInTank(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetTankCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetAutomaticBathWaterHeatingModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetBathroomPrioritySetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetBathOperationStatusMonitor(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetManualBathReheatingOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetManualBathHotWaterAdditionFunctionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetManualLukewarmWaterTemperatureLoweringFunctionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetWaterVolumeSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetWaterVolumeSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetWaterVolumeSetting3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetWaterVolumeSetting4(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetBathWaterVolumeSetting4MaximumSettableLevel(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetOnTimerReaservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetOnTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetVolumeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetMuteSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetRemainingHotWaterVolume(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetSurplusElectricEnergyPowerPredictionValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetRatedPowerConsumptionOfHpUnitInWinterTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetRatedPowerConsumptionOfHpUnitInInBetweenSeasons(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetRatedPowerConsumptionOfHpUnitInSummertime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetParticipationInEnergyShift(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetStandardTimeToStartHeating(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetNumberOfEnergyShifts(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetDaytimeHeatingShiftTime1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetExpectedElectricEnergyAtDaytimeHeatingShiftTime1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetConsumptionofElectricEnergyPerHour1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetDaytimeHeatingShiftTime2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetExpectedElectricEnergyDaytimeHeatingShiftTime2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

        public void onGetConsumptionofElectricPerHour2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
        /**
         * Inform 関連
         */
       public void onInformOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformAutomaticWaterHeatingSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformAutomaticWaterTemperatureControlSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformWaterHeaterStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformWaterHeatingTemperatureSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformManualWaterHeatingStopDaysSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformRelativeTimeSettingValueForManualHeatingOff(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformTankOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformDaytimeReheatingPermissionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformMeasuredTemperatureOfWaterInWaterHeater(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformAlarmStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformHotWaterSupplyStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformRelativeTimeSettingForKeepingBathTemperature(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformTemperatureOfSuppliedWaterSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformBathWaterTemperatureSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformBathWaterVolumeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformMeasuredAmountOfWaterReamingInTank(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformTankCapacity(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformAutomaticBathWaterHeatingModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformBathroomPrioritySetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformBathOperationStatusMonitor(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformManualBathReheatingOperationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformManualBathHotWaterAdditionFunctionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformManualLukewarmWaterTemperatureLoweringFunctionSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformWaterVolumeSetting1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformWaterVolumeSetting2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformWaterVolumeSetting3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformWaterVolumeSetting4(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformBathWaterVolumeSetting4MaximumSettableLevel(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformOnTimerReaservationSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformOnTimerSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformVolumeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformMuteSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformRemainingHotWaterVolume(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformSurplusElectricEnergyPowerPredictionValue(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformRatedPowerConsumptionOfHpUnitInWinterTime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformRatedPowerConsumptionOfHpUnitInInBetweenSeasons(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformRatedPowerConsumptionOfHpUnitInSummertime(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformParticipationInEnergyShift(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformStandardTimeToStartHeating(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformNumberOfEnergyShifts(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformDaytimeHeatingShiftTime1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformExpectedElectricEnergyAtDaytimeHeatingShiftTime1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformConsumptionofElectricEnergyPerHour1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformDaytimeHeatingShiftTime2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformExpectedElectricEnergyDaytimeHeatingShiftTime2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

        public void onInformConsumptionofElectricPerHour2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

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
            case EPC_AUTOMATIC_WATER_HEATING_SETTING:
                return this.getFromAppAutomaticWaterHeatingSetting();
            case EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING:
                return this.getFromAppAutomaticWaterTemperatureControlSetting();
            case EPC_WATER_HEATER_STATUS:
                return this.getFromAppWaterHeaterStatus();
            case EPC_WATER_HEATING_TEMPERATURE_SETTING:
                return this.getFromAppWaterHeatingTemperatureSetting();
            case EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING:
                return this.getFromAppManualWaterHeatingStopDaysSetting();
            case EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF:
                return this.getFromAppRelativeTimeSettingValueForManualHeatingOff();
            case EPC_TANK_OPERATION_MODE_SETTING:
                return this.getFromAppTankOperationModeSetting();
            case EPC_DAYTIME_REHEATING_PERMISSION_SETTING:
                return this.getFromAppDaytimeReheatingPermissionSetting();
            case EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER:
                return this.getFromAppMeasuredTemperatureOfWaterInWaterHeater();
            case EPC_ALARM_STATUS:
                return this.getFromAppAlarmStatus();
            case EPC_HOT_WATER_SUPPLY_STATUS:
                return this.getFromAppHotWaterSupplyStatus();
            case EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE:
                return this.getFromAppRelativeTimeSettingForKeepingBathTemperature();
            case EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING:
                return this.getFromAppTemperatureOfSuppliedWaterSetting();
            case EPC_BATH_WATER_TEMPERATURE_SETTING:
                return this.getFromAppBathWaterTemperatureSetting();
            case EPC_BATH_WATER_VOLUME_SETTING:
                return this.getFromAppBathWaterVolumeSetting();
            case EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK:
                return this.getFromAppMeasuredAmountOfWaterReamingInTank();
            case EPC_TANK_CAPACITY:
                return this.getFromAppTankCapacity();
            case EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING:
                return this.getFromAppAutomaticBathWaterHeatingModeSetting();
            case EPC_BATHROOM_PRIORITY_SETTING:
                return this.getFromAppBathroomPrioritySetting();
            case EPC_BATH_OPERATION_STATUS_MONITOR:
                return this.getFromAppBathOperationStatusMonitor();
            case EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING:
                return this.getFromAppManualBathReheatingOperationSetting();
            case EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING:
                return this.getFromAppManualBathHotWaterAdditionFunctionSetting();
            case EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING:
                return this.getFromAppManualLukewarmWaterTemperatureLoweringFunctionSetting();
            case EPC_WATER_VOLUME_SETTING_1:
                return this.getFromAppWaterVolumeSetting1();
            case EPC_WATER_VOLUME_SETTING_2:
                return this.getFromAppWaterVolumeSetting2();
            case EPC_WATER_VOLUME_SETTING_3:
                return this.getFromAppWaterVolumeSetting3();
            case EPC_WATER_VOLUME_SETTING_4:
                return this.getFromAppWaterVolumeSetting4();
            case EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL:
                return this.getFromAppBathWaterVolumeSetting4MaximumSettableLevel();
            case EPC_ON_TIMER_RESERVATION_SETTING :
                return this.getFromAppOnTimerReaservationSetting();
            case EPC_ON_TIMER_SETTING :
                return this.getFromAppOnTimerSetting();
            case EPC_VOLUME_SETTING :
                return this.getFromAppVolumeSetting();
            case EPC_MUTE_SETTING  :
                return this.getFromAppMuteSetting();
            case EPC_REMAINING_HOT_WATER_VOLUME :
                return this.getFromAppRemainingHotWaterVolume();
            case EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE :
                return this.getFromAppSurplusElectricEnergyPowerPredictionValue();
            case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME  :
                return this.getFromAppRatedPowerConsumptionOfHpUnitInWinterTime();
            case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS :
                return this.getFromAppPowerConsumptionOfHpUnitInInBetweenSeasons();
            case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME :
                return this.getFromAppRatedPowerConsumptionOfHpUnitInSummertime();
            case EPC_PARTICIPATION_IN_ENERGY_SHIFT  :
                return this.getFromAppParticipationInEnergyShift();
            case EPC_STANDARD_TIME_TO_START_HEATING :
                return this.getFromAppStandardTimeToStartHeating();
            case EPC_NUMBER_OF_ENERGY_SHIFTS :
                return this.getFromAppNumberOfEnergyShifts();
            case EPC_DAYTIME_HEATING_SHIFT_TIME_1 :
                return this.getFromAppDaytimeHeatingShiftTime1();
            case EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1  :
                return this.getFromAppExpectedElectricEnergyAtDaytimeHeatingShiftTime1();
            case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1  :
                return this.getFromAppConsumptionofElectricEnergyPerHour1();
            case EPC_DAYTIME_HEATING_SHIFT_TIME_2 :
                return this.getFromAppDaytimeHeatingShiftTime2();
            case EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2  :
                return this.getFromAppExpectedElectricEnergyDaytimeHeatingShiftTime2();
            case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2 :
                return this.getFromAppConsumptionofElectricPerHour();

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
            case EPC_AUTOMATIC_WATER_HEATING_SETTING:
                return this.isValidEdtAutomaticWaterHeatingSetting(edt);
            case EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING:
                return this.isValidEdtAutomaticWaterTemperatureControlSetting(edt);
            case EPC_WATER_HEATER_STATUS:
                return this.isValidEdtWaterHeaterStatus(edt);
            case EPC_WATER_HEATING_TEMPERATURE_SETTING:
                return this.isValidEdtWaterHeatingTemperatureSetting(edt);
            case EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING:
                return this.isValidEdtManualWaterHeatingStopDaysSetting(edt);
            case EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF:
                return this.isValidEdtRelativeTimeSettingValueForManualHeatingOff(edt);
            case EPC_TANK_OPERATION_MODE_SETTING:
                return this.isValidEdtTankOperationModeSetting(edt);
            case EPC_DAYTIME_REHEATING_PERMISSION_SETTING:
                return this.isValidEdtDaytimeReheatingPermissionSetting(edt);
            case EPC_MEASURED_TEMPERATURE_OF_WATER_IN_WATER_HEATER:
                return this.isValidEdtMeasuredTemperatureOfWaterInWaterHeater(edt);
            case EPC_ALARM_STATUS:
                return this.isValidEdtAlarmStatus(edt);
            case EPC_HOT_WATER_SUPPLY_STATUS:
                return this.isValidEdtHotWaterSupplyStatus(edt);
            case EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE:
                return this.isValidEdtRelativeTimeSettingForKeepingBathTemperature(edt);
            case EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING:
                return this.isValidEdtTemperatureOfSuppliedWaterSetting(edt);
            case EPC_BATH_WATER_TEMPERATURE_SETTING:
                return this.isValidEdtBathWaterTemperatureSetting(edt);
            case EPC_BATH_WATER_VOLUME_SETTING:
                return this.isValidEdtBathWaterVolumeSetting(edt);
            case EPC_MEASURED_AMOUNT_OF_WATER_REMAINING_IN_TANK:
                return this.isValidEdtMeasuredAmountOfWaterReamingInTank(edt);
            case EPC_TANK_CAPACITY:
                return this.isValidEdtTankCapacity(edt);
            case EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING:
                return this.isValidEdtAutomaticBathWaterHeatingModeSetting(edt);
            case EPC_BATHROOM_PRIORITY_SETTING:
                return this.isValidEdtBathroomPrioritySetting(edt);
            case EPC_BATH_OPERATION_STATUS_MONITOR:
                return this.isValidEdtBathOperationStatusMonitor(edt);
            case EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING:
                return this.isValidEdtManualBathReheatingOperationSetting(edt);
            case EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING:
                return this.isValidEdtManualBathHotWaterAdditionFunctionSetting(edt);
            case EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING:
                return this.isValidEdtManualLukewarmWaterTemperatureLoweringFunctionSetting(edt);
            case EPC_WATER_VOLUME_SETTING_1:
                return this.isValidEdtWaterVolumeSetting1(edt);
            case EPC_WATER_VOLUME_SETTING_2:
                return this.isValidEdtWaterVolumeSetting2(edt);
            case EPC_WATER_VOLUME_SETTING_3:
                return this.isValidEdtWaterVolumeSetting3(edt);
            case EPC_WATER_VOLUME_SETTING_4:
                return this.isValidEdtWaterVolumeSetting4(edt);
            case EPC_BATH_WATER_VOLUME_SETTING_4_MAXIMUM_SETTABLE_LEVEL:
                return this.isValidEdtBathWaterVolumeSetting4MaximumSettableLevel(edt);
            case EPC_ON_TIMER_RESERVATION_SETTING :
                return this.isValidEdtOnTimerReaservationSetting(edt);
            case EPC_ON_TIMER_SETTING :
                return this.isValidEdtOnTimerSetting(edt);
            case EPC_VOLUME_SETTING :
                return this.isValidEdtVolumeSetting(edt);
            case EPC_MUTE_SETTING :
                return this.isValidEdtMuteSetting(edt);
            case EPC_REMAINING_HOT_WATER_VOLUME :
                return this.isValidEdtRemainingHotWaterVolume(edt);
            case EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE :
                return this.isValidEdtSurplusElectricEnergyPowerPredictionValue(edt);
            case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_WINTERTIME :
                return this.isValidEdtRatedPowerConsumptionOfHpUnitInWinterTime(edt);
            case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_IN_BETWEEN_SEASONS :
                return this.isValidEdtRatedPowerConsumptionOfHpUnitInInBetweenSeasons(edt);
            case EPC_RATED_POWER_CONSUMPTION_OF_HP_UNIT_IN_SUMMERTIME :
                return this.isValidEdtRatedPowerConsumptionOfHpUnitInSummertime(edt);
            case EPC_PARTICIPATION_IN_ENERGY_SHIFT :
                return this.isValidEdtParticipationInEnergyShift(edt);
            case EPC_STANDARD_TIME_TO_START_HEATING:
                return this.isValidEdtStandardTimeToStartHeating(edt);
            case EPC_NUMBER_OF_ENERGY_SHIFTS:
                return this.isValidEdtNumberOfEnergyShifts(edt);
            case EPC_DAYTIME_HEATING_SHIFT_TIME_1:
                return this.isValidEdtDaytimeHeatingShiftTime1(edt);
            case EPC_EXPECTED_ELECTRIC_ENERGY_AT_DAYTIME_HEATING_SHIFT_TIME_1 :
                return this.isValidEdtExpectedElectricEnergyAtDaytimeHeatingShiftTime1(edt);
            case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_1 :
                return this.isValidEdtConsumptionofElectricEnergyPerHour1(edt);
            case EPC_DAYTIME_HEATING_SHIFT_TIME_2:
                return this.isValidEdtDaytimeHeatingShiftTime2(edt);
            case EPC_EXPECTED_ELECTRIC_ENERGY_DAYTIME_HEATING_SHIF_TTIME_2 :
                return this.isValidEdtExpectedElectricEnergyDaytimeHeatingShiftTime2(edt);
            case EPC_CONSUMPTION_OF_ELECTRIC_ENERGY_PER_HOUR_2:
                return this.isValidEdtConsumptionofElectricPerHour2(edt);
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
            case EPC_AUTOMATIC_WATER_HEATING_SETTING:
                return this.setToAppAutomaticWaterHeatingSetting(edt);
            case EPC_AUTOMATIC_WATER_TEMPERATURE_CONTROL_SETTING:
                return this.setToAppAutomaticWaterTemperatureControlSetting(edt);
            case EPC_WATER_HEATING_TEMPERATURE_SETTING:
                return this.setToAppWaterHeatingTemperatureSetting(edt);
            case EPC_MANUAL_WATER_HEATING_STOP_DAYS_SETTING:
                return this.setToAppManualWaterHeatingStopDaysSetting(edt);
            case EPC_RELATIVE_TIME_SETTING_VALUE_FOR_MANUAL_WATER_HEATING_OFF:
                return this.setToAppRelativeTimeSettingValueForManualHeatingOff(edt);
            case EPC_TANK_OPERATION_MODE_SETTING:
                return this.setToAppTankOperationModeSetting(edt);
            case EPC_DAYTIME_REHEATING_PERMISSION_SETTING:
                return this.setToAppDaytimeReheatingPermissionSetting(edt);
            case EPC_RELATIVE_TIME_SETTING_FOR_KEEPING_BATH_TEMPERATURE:
                return this.setToAppRelativeTimeSettingForKeepingBathTemperature(edt);
            case EPC_TEMPERATURE_OF_SUPPLIED_WATER_SETTING:
                return this.setToAppTemperatureOfSuppliedWaterSetting(edt);
            case EPC_BATH_WATER_TEMPERATURE_SETTING:
                return this.setToAppBathWaterTemperatureSetting(edt);
            case EPC_BATH_WATER_VOLUME_SETTING:
                return this.setToAppBathWaterVolumeSetting(edt);
            case EPC_AUTOMATIC_BATH_WATER_HEATING_MODE_SETTING:
                return this.setToAppAutomaticBathWaterHeatingModeSetting(edt);
            case EPC_MANUAL_BATH_REHEATING_OPERATION_SETTING:
                return this.setToAppManualBathReheatingOperationSetting(edt);
            case EPC_MANUAL_BATH_HOT_WATER_ADDITION_FUNCTION_SETTING:
                return this.setToAppManualBathHotWaterAdditionFunctionSetting(edt);
            case EPC_MANUAL_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_SETTING:
                return this.setToAppManualLukewarmWaterTemperatureLoweringFunctionSetting(edt);
            case EPC_WATER_VOLUME_SETTING_1:
                return this.setToAppWaterVolumeSetting1(edt);
            case EPC_WATER_VOLUME_SETTING_2:
                return this.setToAppWaterVolumeSetting2(edt);
            case EPC_WATER_VOLUME_SETTING_3:
                return this.setToAppWaterVolumeSetting3(edt);
            case EPC_WATER_VOLUME_SETTING_4:
                return this.setToAppWaterVolumeSetting4(edt);
            case EPC_ON_TIMER_RESERVATION_SETTING :
                return this.setToAppOnTimerReaservationSetting(edt);
            case EPC_ON_TIMER_SETTING :
                return this.setToAppOnTimerSetting(edt);
            case EPC_VOLUME_SETTING :
                return this.setToAppVolumeSetting(edt);
            case EPC_MUTE_SETTING :
                return this.setToAppMuteSetting(edt);
            case EPC_SURPLUS_ELECTRIC_ENERGY_POWER_PREDICTION_VALUE :
                return this.setToAppSurplusElectricEnergyPowerPredictionValue(edt);
            case EPC_PARTICIPATION_IN_ENERGY_SHIFT  :
                return this.setToAppParticipationInEnergyShift(edt);
            case EPC_DAYTIME_HEATING_SHIFT_TIME_1 :
                return this.setToAppDaytimeHeatingShiftTime1(edt);
            case EPC_DAYTIME_HEATING_SHIFT_TIME_2 :
                return this.setToAppDaytimeHeatingShiftTime2(edt);
            default:
                return false;
        }
    }

     /* A set of methods to retrieve values from the application
         アプリケーションより値を取得するメソッド群  */

	//protected String getFromAppOperationStatus(){return null;}
    protected String getFromAppAutomaticWaterHeatingSetting(){return null;}
    protected String getFromAppAutomaticWaterTemperatureControlSetting(){return null;}
    protected String getFromAppWaterHeaterStatus(){return null;}
    protected String getFromAppWaterHeatingTemperatureSetting(){return null;}
    protected String getFromAppManualWaterHeatingStopDaysSetting(){return null;}
    protected String getFromAppRelativeTimeSettingValueForManualHeatingOff(){return null;}
    protected String getFromAppTankOperationModeSetting(){return null;}
    protected String getFromAppDaytimeReheatingPermissionSetting(){return null;}
    protected String getFromAppMeasuredTemperatureOfWaterInWaterHeater(){return null;}
    protected String getFromAppAlarmStatus(){return null;}
    protected String getFromAppHotWaterSupplyStatus(){return null;}
    protected String getFromAppRelativeTimeSettingForKeepingBathTemperature(){return null;}
    protected String getFromAppTemperatureOfSuppliedWaterSetting(){return null;}
    protected String getFromAppBathWaterTemperatureSetting(){return null;}
    protected String getFromAppBathWaterVolumeSetting(){return null;}
    protected String getFromAppMeasuredAmountOfWaterReamingInTank(){return null;}
    protected String getFromAppTankCapacity(){return null;}
    protected String getFromAppAutomaticBathWaterHeatingModeSetting(){return null;}
    protected String getFromAppBathroomPrioritySetting(){return null;}
    protected String getFromAppBathOperationStatusMonitor(){return null;}
    protected String getFromAppManualBathReheatingOperationSetting(){return null;}
    protected String getFromAppManualBathHotWaterAdditionFunctionSetting(){return null;}
    protected String getFromAppManualLukewarmWaterTemperatureLoweringFunctionSetting(){return null;}
    protected String getFromAppWaterVolumeSetting1(){return null;}
    protected String getFromAppWaterVolumeSetting2(){return null;}
    protected String getFromAppWaterVolumeSetting3(){return null;}
    protected String getFromAppWaterVolumeSetting4(){return null;}
    protected String getFromAppBathWaterVolumeSetting4MaximumSettableLevel(){return null;}
    protected String getFromAppOnTimerReaservationSetting(){return null;}
    protected String getFromAppOnTimerSetting(){return null;}
    protected String getFromAppVolumeSetting(){return null;}
    protected String getFromAppMuteSetting(){return null;}
    protected String getFromAppRemainingHotWaterVolume(){return null;}
    protected String getFromAppSurplusElectricEnergyPowerPredictionValue(){return null;}
    protected String getFromAppRatedPowerConsumptionOfHpUnitInWinterTime(){return null;}
    protected String getFromAppPowerConsumptionOfHpUnitInInBetweenSeasons(){return null;}
    protected String getFromAppRatedPowerConsumptionOfHpUnitInSummertime(){return null;}
    protected String getFromAppParticipationInEnergyShift(){return null;}
    protected String getFromAppStandardTimeToStartHeating(){return null;}
    protected String getFromAppNumberOfEnergyShifts(){return null;}
    protected String getFromAppDaytimeHeatingShiftTime1(){return null;}
    protected String getFromAppExpectedElectricEnergyAtDaytimeHeatingShiftTime1(){return null;}
    protected String getFromAppConsumptionofElectricEnergyPerHour1(){return null;}
    protected String getFromAppDaytimeHeatingShiftTime2(){return null;}
    protected String getFromAppExpectedElectricEnergyDaytimeHeatingShiftTime2(){return null;}
    protected String getFromAppConsumptionofElectricPerHour(){return null;}

 /* A set of methods that checks validity of EDT @return true: Valid , false Invalid
    EDTの有効性をチェックするメソッドのセット @return true 有効 , false 無効  */

    //protected boolean isValidEdtOperationStatus(String edt){return false;}

    protected boolean isValidEdtAutomaticWaterHeatingSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_AUTOMATIC_WATER_HEATING_FUNCTION_USED)
                && ! edt.equalsIgnoreCase(EDT_AUTOMATIC_WATER_HEATING_FUNCTION_STOPPED)
                && ! edt.equalsIgnoreCase(EDT_NON_AUTOMATIC_WATER_HEATING_FUNCTION_USED)
        ) return false;
        return true;
    }

    protected boolean isValidEdtAutomaticWaterTemperatureControlSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_AUTOMATIC_WATER_TEMPERATURE_CONTROL_FUNCTION_USED)
                && ! edt.equalsIgnoreCase(EDT_AUTOMATIC_WATER_TEMPERATURE_CONTROL_FUNCTION_NOT_USED)
        ) return false;
        return true;
    }

    protected boolean isValidEdtWaterHeaterStatus(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_WATER_HEATER_STATUS_HEATING)
                && ! edt.equalsIgnoreCase(EDT_WATER_HEATER_STATUS_NOT_HEATING)
        ) return false;
        return true;
    }

    protected boolean isValidEdtWaterHeatingTemperatureSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }

    protected boolean isValidEdtManualWaterHeatingStopDaysSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }

    protected boolean isValidEdtRelativeTimeSettingValueForManualHeatingOff(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }

    protected boolean isValidEdtTankOperationModeSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_TANK_OPERATION_MODE_SETTING_STANDARD)
                && ! edt.equalsIgnoreCase(EDT_TANK_OPERATION_MODE_SETTING_SAVING)
                && ! edt.equalsIgnoreCase(EDT_TANK_OPERATION_MODE_SETTING_EXTRA)
        ) return false;
        return true;
    }

    protected boolean isValidEdtDaytimeReheatingPermissionSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_DAYTIME_REHEATING_PERMITTED)
                && ! edt.equalsIgnoreCase(EDT_DAYTIME_REHEATING_NOT_PERMITTED)
        ) return false;
        return true;
    }

    protected boolean isValidEdtMeasuredTemperatureOfWaterInWaterHeater(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_DAYTIME_REHEATING_PERMITTED)
                && ! edt.equalsIgnoreCase(EDT_DAYTIME_REHEATING_NOT_PERMITTED)
        ) return false;
        return true;
    }

    protected boolean isValidEdtAlarmStatus(String edt){
        if(edt == null || !(edt.length() == 4 * 4)) return false;
        return true;
    }

    protected boolean isValidEdtHotWaterSupplyStatus(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_SUPPLYING_HOT_WATER)
                && ! edt.equalsIgnoreCase(EDT_NOT_SUPPLYING_HOT_WATER)
        ) return false;
        return true;
    }

    protected boolean isValidEdtRelativeTimeSettingForKeepingBathTemperature(String edt){
        if(edt == null || !(edt.length() == 2 * 4)) return false;
        return true;
    }

    protected boolean isValidEdtTemperatureOfSuppliedWaterSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }

    protected boolean isValidEdtBathWaterTemperatureSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }

    protected boolean isValidEdtBathWaterVolumeSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }

    protected boolean isValidEdtMeasuredAmountOfWaterReamingInTank(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
        return true;
    }

    protected boolean isValidEdtTankCapacity(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
        return true;
    }

    protected boolean isValidEdtAutomaticBathWaterHeatingModeSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_AUTOMATIC_BATH_WATER_HEATING_MODE_ON)
                && ! edt.equalsIgnoreCase(EDT_AUTOMATIC_BATH_WATER_HEATING_MODE_OFF)
        ) return false;
        return true;
    }

    protected boolean isValidEdtBathroomPrioritySetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_BATHROOM_PRIORITY_SETTING_ON)
                && ! edt.equalsIgnoreCase(EDT_BATHROOM_PRIORITY_SETTING_OFF)
        ) return false;
        return true;
    }

    protected boolean isValidEdtBathOperationStatusMonitor(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_BATH_OPERATION_STATUS_MONITOR_FILLING_HOT_WATER)
                && ! edt.equalsIgnoreCase(EDT_BATH_OPERATION_STATUS_MONITOR_STOPPED)
                && ! edt.equalsIgnoreCase(EDT_BATH_OPERATION_STATUS_MONITOR_KEEPING_TEMPERATURE)
        ) return false;
        return true;
    }

    protected boolean isValidEdtManualBathReheatingOperationSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_BATH_REHEATING_ON)
                && ! edt.equalsIgnoreCase(EDT_BATH_REHEATING_OFF)
        ) return false;
        return true;
    }

    protected boolean isValidEdtManualBathHotWaterAdditionFunctionSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_ADDITION_OF_HOT_WATER_FUNCTION_ON)
                && ! edt.equalsIgnoreCase(EDT_ADDITION_OF_HOT_WATER_FUNCTION_OFF)
        ) return false;
        return true;
    }
    protected boolean isValidEdtManualLukewarmWaterTemperatureLoweringFunctionSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_ON)
                && ! edt.equalsIgnoreCase(EDT_LUKEWARM_WATER_TEMPERATURE_LOWERING_FUNCTION_OOF)
        ) return false;
        return true;
    }
    protected boolean isValidEdtWaterVolumeSetting1(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtWaterVolumeSetting2(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtWaterVolumeSetting3(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtWaterVolumeSetting4(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtBathWaterVolumeSetting4MaximumSettableLevel(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtOnTimerReaservationSetting(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_ON)
                && ! edt.equalsIgnoreCase(EDT_ON_TIMER_RESERVATION_SETTING_RESERVATION_OFF)
        ) return false;
        return true;
    }
    protected boolean isValidEdtOnTimerSetting(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        if(! ElUtil.checkEdtHmFormat(edt)) return false;
        return true;
    }
    protected boolean isValidEdtVolumeSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtMuteSetting(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        if(! edt.equalsIgnoreCase(EDT_MUTE_SETTING_MUTE_ON)
                && ! edt.equalsIgnoreCase(EDT_MUTE_SETTING_MUTE_OFF)
        ) return false;
        return true;
    }
    protected boolean isValidEdtRemainingHotWaterVolume(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "7FFE") <= 0)) return false;
        return true;
    }
    protected boolean isValidEdtSurplusElectricEnergyPowerPredictionValue(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtRatedPowerConsumptionOfHpUnitInWinterTime(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtRatedPowerConsumptionOfHpUnitInInBetweenSeasons(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtRatedPowerConsumptionOfHpUnitInSummertime(String edt){
        if(edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtParticipationInEnergyShift(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtStandardTimeToStartHeating(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtNumberOfEnergyShifts(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtDaytimeHeatingShiftTime1(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtExpectedElectricEnergyAtDaytimeHeatingShiftTime1(String edt){
        if(edt == null || !(edt.length() == 16 * 4)) return false;
        if(! (ElUtil.compElUnsignedLong("0000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "FFFFFFFD") <= 0)) return false;

        return true;
    }
    protected boolean isValidEdtConsumptionofElectricEnergyPerHour1(String edt){
        if(edt == null || !(edt.length() == 8 * 4)) return false;
        if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "FFFD") <= 0)) return false;
        return true;
    }
    protected boolean isValidEdtDaytimeHeatingShiftTime2(String edt){
        if(edt == null || !(edt.length() == 1 * 2)) return false;
        return true;
    }
    protected boolean isValidEdtExpectedElectricEnergyDaytimeHeatingShiftTime2(String edt){
        if(edt == null || !(edt.length() == 12 * 3)) return false;
        if(! (ElUtil.compElUnsignedLong("0000", edt ) <= 0 && ElUtil.compElUnsignedLong(edt , "FFFFFFFD") <= 0)) return false;
        return true;
    }
    protected boolean isValidEdtConsumptionofElectricPerHour2(String edt){
        if(edt == null || !(edt.length() == 6 * 3)) return false;
        if(! (ElUtil.compElUnsignedShort("0000", edt ) <= 0 && ElUtil.compElUnsignedShort(edt , "FFFD") <= 0)) return false;
        return true;
    }

//	protected boolean setToAppOperationStatus(String edt){return false;}
    protected boolean setToAppAutomaticWaterHeatingSetting(String edt){return false;}
    protected boolean setToAppAutomaticWaterTemperatureControlSetting(String edt){return false;}
    protected boolean setToAppWaterHeatingTemperatureSetting(String edt){return false;}
    protected boolean setToAppManualWaterHeatingStopDaysSetting(String edt){return false;}
    protected boolean setToAppRelativeTimeSettingValueForManualHeatingOff(String edt){return false;}
    protected boolean setToAppTankOperationModeSetting(String edt){return false;}
    protected boolean setToAppDaytimeReheatingPermissionSetting(String edt){return false;}
    protected boolean setToAppRelativeTimeSettingForKeepingBathTemperature(String edt){return false;}
    protected boolean setToAppTemperatureOfSuppliedWaterSetting(String edt){return false;}
    protected boolean setToAppBathWaterTemperatureSetting(String edt){return false;}
    protected boolean setToAppBathWaterVolumeSetting(String edt){return false;}
    protected boolean setToAppAutomaticBathWaterHeatingModeSetting(String edt){return false;}
    protected boolean setToAppManualBathReheatingOperationSetting(String edt){return false;}
    protected boolean setToAppManualBathHotWaterAdditionFunctionSetting(String edt){return false;}
    protected boolean setToAppManualLukewarmWaterTemperatureLoweringFunctionSetting(String edt){return false;}
    protected boolean setToAppWaterVolumeSetting1(String edt){return false;}
    protected boolean setToAppWaterVolumeSetting2(String edt){return false;}
    protected boolean setToAppWaterVolumeSetting3(String edt){return false;}
    protected boolean setToAppWaterVolumeSetting4(String edt){return false;}
    protected boolean setToAppOnTimerReaservationSetting(String edt){return false;}
    protected boolean setToAppOnTimerSetting(String edt){return false;}
    protected boolean setToAppVolumeSetting(String edt){return false;}
    protected boolean setToAppMuteSetting(String edt){return false;}
    protected boolean setToAppSurplusElectricEnergyPowerPredictionValue(String edt){return false;}
    protected boolean setToAppParticipationInEnergyShift(String edt){return false;}
    protected boolean setToAppDaytimeHeatingShiftTime1(String edt){return false;}
    protected boolean setToAppDaytimeHeatingShiftTime2(String edt){return false;}
    /**
     * EPC名称マップ
     */
    public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
        {
            put("80", "動作状態 Operation status");
            put("B0", "沸き上げ自動設定 Automatic water heating setting");
            put("B1", "沸き上げ湯温自動設定Automatic water temperature control setting");
            put("B2", "沸き上げ中状態 Water heater status");
            put("B3", "沸き上げ湯温設定値Water heating temperature setting");
            put("B4", "手動沸き上げ停止日数設定値Manual water heating stop days setting");
            put("B5", "手動沸き上げOFFタイマ相対時間設定値Relative time setting value for manual water heating OFF");
            put("B6", "タンク運転モード設定Tank operation mode setting");
            put("C0", "昼間沸き増し許可設定Daytime reheating permission setting");
            put("C1", "温水器湯温計測値Measured temperature of water in water heater");
            put("C2", "警報発生状態 Alarm status");
            put("C3", "給湯中状態Hot water supply status");
            put("C4", "風呂保温運転相対時間設定値Relative time setting for keeping bath temperature");
            put("D1", "給湯温度設定値 Temperature of supplied water setting");
            put("D3", "風呂温度設定値Bath water temperature setting");
            put("E0", "沸き上げ湯量設定値Bath water volume setting");
            put("E1", "残湯量計測値Measured amount of water remaining in tank");
            put("E2", "タンク容量値Tank capacity");
            put("E3", "A風呂自動モード設定utomatic bath water heating mode setting");
            put("E9", "浴室優先設定 Bathroom priority setting");
            put("EA", "風呂動作状態監視Bath operation status monitor");
            put("E4", "手動風呂追い焚き動作設定Manual bath reheating operation setting");
            put("E5", "手動風呂足し湯動作設定Manual bath hot water addition function setting");
            put("E6", "手動風呂ぬるめ動作設定Manual lukewarm water temperature lowering function setting");
            put("E7", "風呂湯量設定１Bath water volume setting 1");
            put("E8", "風呂湯量設定2Bath water volume setting 2");
            put("EE", "風呂湯量設定3Bath water volume setting 3");
            put("D4", "風呂湯量設定4Bath water volume setting 4");
            put("D5", "風呂湯量設定４設定可能最大レベルBath water volume setting 4- Maximum settable level");
            put("90", "ＯＮタイマ予約設定ON timer reservation setting");
            put("91", "ＯＮタイマ時刻設定値ON timer setting");
            put("D6", "音量設定値 Volume setting");
            put("D7", "ミュート設定Mute setting");
            put("D8", "給湯可能湯量値Remaining hot water volume");
            put("D9", "余剰電力量予測値Surplus electric energy power prediction value");
            put("DB", "冬場のH/Pユニットの定格消費電力 Rated power consumption of H/P unit in winter");
            put("DC", "中間期 H/P ユニット定格消費電力Rated power consumption of H/P unit in in-betweenseasons");
            put("DD", "夏季 H/P ユニット定格消費電力Rated power consumption of H/P unit in summertime");
            put("C7", "エネルギーシフト参加状態Participation in energy shift ");
            put("C8", "沸き上げ開始基準時刻Standard time to start heating");
            put("C9", "エネルギーシフト回数Number of energy shifts");
            put("CA", "昼間沸き上げシフト時刻１Daytime heating shift time 1");
            put("CB", "昼間沸き上げシフト時刻１での沸き上げ予測電力量Expected electric energy at daytime heating shift time");
            put("CC", "時間当たり消費電力量 1Consumption of electric energy per hour 1");
            put("CD", "昼間沸き上げシフト時刻 2Daytime heating shift time 2");
            put("CE", "昼間沸き上げシフト時刻2での沸き上げ予測電力Expected electric energy at daytime heating shift time 2");
            put("CF", "時間当たり消費電力量2Consumption of electric energy per hour 2");
        }
    };
    }


