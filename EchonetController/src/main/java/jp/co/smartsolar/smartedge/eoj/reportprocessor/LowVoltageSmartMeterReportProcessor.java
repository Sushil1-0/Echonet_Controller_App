package jp.co.smartsolar.smartedge.eoj.reportprocessor;

import jp.co.smartsolar.smartedge.database.controller.ConfigInfoTableController;
import jp.co.smartsolar.smartedge.database.controller.EdgeSystemPowerTableController;
import jp.co.smartsolar.smartedge.database.controller.SmartmeterMasterTableController;
import jp.co.smartsolar.smartedge.database.entity.EdgeSystemPower;
import jp.co.smartsolar.smartedge.database.entity.SmartmeterMaster;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.devices.LowVoltageSmartMeter.ReportProcessor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;


/**
 * @package jp.co.smartsolar.smartedge.eoj.reportprocessor
 * @Author subohaju
 * @date 5/10/2024
 */
@Getter

public class LowVoltageSmartMeterReportProcessor extends ReportProcessor implements ReportProcessorInterface{
    private Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.eoj.LogService");

    private static int cumulativeAmountOfSellPower;
    public static boolean isSmartMeter = false;
    private static double unit = 1;		    //converting to watt

    public SmartmeterMasterTableController smartmeterMasterTableController = new SmartmeterMasterTableController();
    public EdgeSystemPowerTableController edgeSystemPowerTableController = new EdgeSystemPowerTableController();
    private final ConfigInfoTableController configInfoTableController = new ConfigInfoTableController();

    private final Map<Long, EdgeSystemPower> edgeSystemPowerPositiveMap = new ConcurrentHashMap<>();
    private final Map<Long, EdgeSystemPower> edgeSystemPowerReverseMap = new ConcurrentHashMap<>();
    private CountDownLatch countDownLatch = null;

    SmartmeterMaster deviceMaster = null;

    public int getCumulativeAmountOfSellPower() {
        return cumulativeAmountOfSellPower;
    }

    @Override
    public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        logger.info("対向ノードの低圧スマメのOperation status の値を取得しました。{}", objProp.getStrEdt());
        // 配列に保存
    }

    @Override
    public void onGetIntegralPowerConsumptionUnit(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        logger.info("対向ノードの低圧スマメのユニット onGetIntegralPowerConsumptionUnit : {}", objProp.getStrEdt());

        if (isSuccess) {
            String edt = objProp.getStrEdt();

            switch (edt) {
                case "00":
                    unit = 1000;		// kwh -> wh conversion
                    break;

                case "01":
                    unit = 0.1 * 1000;
                    break;

                case "02":
                    unit = 0.01 * 1000;
                    break;

                case "03":		//default value
                    unit = 0.001 * 1000;
                    break;

                case "04":
                    unit = 0.0001 * 1000;
                    break;

                case "0A":
                    unit = 10 * 1000;
                    break;

                case "0B":
                    unit = 100 * 1000;
                    break;

                case "0C":
                    unit = 1000 * 1000;
                    break;

                case "0D":
                    unit = 10000 * 1000;
                    break;

                default:
                    break;
            }

            logger.info("onGetIntegralPowerConsumptionUnit unit : {}", unit);
        } else {
            logger.warn("onGetIntegralPowerConsumptionUnit, isSuccess: false");
        }

    }

    // 積算電力量計測値(正方向計測値)
    @SneakyThrows
    @Override
    public void onGetIntegralPowerConsumptionMeasuredValuesPositive(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        logger.info("対向ノードの低圧スマメの積算電力量計測値(正方向計測値)IntegralPowerConsumptionMeasuredValuesPositive の値を取得しました。{}", objProp.getStrEdt());

        if (isSuccess) {
            int deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            String edt = objProp.getStrEdt();
            double power = Integer.parseInt(edt, 16);
            if (isSmartMeter) {
//            	logger.info("positive before power : " + power);
                power = Math.round(power * unit / 60);
            }
//            cumulativeAmountOfBuyPower = edtInt;

            try {
                deviceMaster = smartmeterMasterTableController.findById(deviceId);
            }
            catch (SQLException e)
            {
                return;
            }
            if(deviceMaster != null)
            {
                // 秒以下を0セットした日付を作成
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
                EdgeSystemPower entity = new EdgeSystemPower();
                entity.setCycleDatetime(new Timestamp(now.getTime()));
                entity.setDirection(true);
                entity.setElectricVol(power);
                entity.setSmartmeterMaster(deviceMaster);
                logger.info("PositiveDirection || ElectricVol: {}", power);
                // バッファリング
                edgeSystemPowerPositiveMap.put(now.getTime(), entity);

                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    countDownLatch = null;
                }

            }
        } else {
            logger.warn("onGetIntegralPowerConsumptionMeasuredValuesPositive : isSuccess=false");
        }
    }

    // 積算電力量計測値(逆方向計測値)
    @SneakyThrows
    @Override
    public void onGetIntegralPowerConsumptionMeasuredValuesReverse(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        logger.info("対向ノードの低圧スマメの積算電力量計測値(逆方向計測値)IntegralPowerConsumptionMeasuredValuesReverse の値を取得しました。{}", objProp.getStrEdt());

        if (isSuccess) {
            int deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            String edt = objProp.getStrEdt();
            int edtInt = Integer.parseInt(edt, 16);
            double power = edtInt;
            if (isSmartMeter) {
//            	logger.info("negative before power : " + power);
                power = Math.round(power * unit / 60);
            }
            //doesn't matters even if numbers don't match, just for checking if selling power
            cumulativeAmountOfSellPower = edtInt;		//added for nightCharging fix 売電
            try {
                deviceMaster = smartmeterMasterTableController.findById(deviceId);
            }
            catch (SQLException e){
                logger.error("Error finding device master by ID: ", e);
                return; // Or continue with alternative logic
            }
            if (deviceMaster != null) {
                // 秒以下を0セットした日付を作成
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);

                EdgeSystemPower entity = new EdgeSystemPower();
                entity.setCycleDatetime(new Timestamp(now.getTime()));
                entity.setDirection(false);
                entity.setElectricVol(power);
                entity.setSmartmeterMaster(deviceMaster);
                logger.info("NegativeDirection || ElectricVol: {}", power);
                // バッファリング
                edgeSystemPowerReverseMap.put(now.getTime(), entity);

                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    countDownLatch = null;
                }
            }
        } else {
            logger.warn("onGetIntegralPowerConsumptionMeasuredValuesReverse : isSuccess=false");
        }
    }

    private int getCtScaling() {
        ConfigInfoTableController configInfoTableController = new ConfigInfoTableController();
        int ret;
        ret = Integer.parseInt(configInfoTableController.findByKeyName("ctScaling").getKeyValue(), 16);
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeDatabase() throws SQLException {
        Collection<EdgeSystemPower> positive = edgeSystemPowerPositiveMap.values();
        edgeSystemPowerTableController.saveAll((List<EdgeSystemPower>) positive);
        edgeSystemPowerPositiveMap.clear();

        Collection<EdgeSystemPower> reverse = edgeSystemPowerReverseMap.values();
        edgeSystemPowerTableController.saveAll((List<EdgeSystemPower>) reverse);
        edgeSystemPowerReverseMap.clear();
    }

    /**
     * CountDownLatch設定
     * @param latch CountDownLatch
     */
    public synchronized void setCountDownLatch(CountDownLatch latch) {
        countDownLatch = latch;
    }

}
