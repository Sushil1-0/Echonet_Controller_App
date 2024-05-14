package jp.co.smartsolar.smartedge.eoj.reportprocessor;

import jp.co.smartsolar.smartedge.database.controller.CtSensorMasterTableController;
import jp.co.smartsolar.smartedge.database.controller.EdgeCtSensorDataTableController;
import jp.co.smartsolar.smartedge.database.entity.CtSensorMaster;
import jp.co.smartsolar.smartedge.database.entity.EdgeCtSensorData;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.devices.PowerDistributionBoardMetering.ReportProcessor;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @package jp.co.smartsolar.smartedge.eoj.reportprocessor
 * @Author subohaju
 * @date 5/14/2024
 */
public class PowerDistributionBoardMeterReportProcessor extends ReportProcessor implements ReportProcessorInterface {
    private final Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.LogService");
    CtSensorMasterTableController ctSensorMasterTableController = new CtSensorMasterTableController();
    EdgeCtSensorDataTableController edgeCtSensorDataTableController = new EdgeCtSensorDataTableController();
    private CountDownLatch countDownLatch = null;
    private static double unit = 1;


    private final Map<Long, EdgeCtSensorData> entityMapChannel3 = new LinkedHashMap<>();
    private final Map<Long, EdgeCtSensorData> entityMapChannel4 = new LinkedHashMap<>();
    private final Map<Long, EdgeCtSensorData> entityMapChannel5 = new LinkedHashMap<>();
    private final Map<Long, EdgeCtSensorData> entityMapChannel6 = new LinkedHashMap<>();
    private final Map<Long, EdgeCtSensorData> entityMapChannel7 = new LinkedHashMap<>();

    public PowerDistributionBoardMeterReportProcessor() {
    }

    private CtSensorMaster getCtSensorMasterByChannelId(int channelId) {
        CtSensorMaster ctSensorMaster = ctSensorMasterTableController.findByChannelId(channelId);
        if (ctSensorMaster != null) {
            return ctSensorMaster;
        }
        return null;
    }

    @Override
    public void onGetUnitForCumulativeElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            String edt = objProp.getStrEdt();

            switch (edt) {
                case "00":
                    unit = 1 * 1000;		// kwh -> wh conversion
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


            logger.info("onGetUnitForCumulativeElectricEnergy unit : " + unit);
        } else {
            logger.warn("onGetUnitForCumulativeElectricEnergy, isSuccess: false");
        }
    }

    @Override
    public void onGetMeasurementChannelValue1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        // TODO commented since validation methods of PowerDistributionBoardMeteringClass are not completed yet
        if (isSuccess) {
            String edt = objProp.getStrEdt();

//    		if (smartPdbm) {
            double power = Integer.parseInt(edt.substring(0, 8), 16);
            power = power * unit;
            logger.info("before onGetMeasurementChannelValue1 power: " + power);
//    		logger.info("before onGetMeasurementChannelValue1 unit : " + unit);

            //test
            // commmented for test if division is not necessary
//    			power = (power * (int) unit) / 60;		// divide by 60 for whr -> w
            // = 156 * 0.6 -> watt
//    			 (value * unit * 1000) * 0.6;
//
//    			 power = (value * 0.6);
//    			 power = power * unit
//    		} else {
//    			power = Integer.parseInt(edt, 16);
//    		}

//    		Double powerValue = ((double) power);	//    / 1000
            logger.info("Channel 1 value acquired: [ " + power + " ]");

            Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
            EdgeCtSensorData entity = new EdgeCtSensorData();
            entity.setAcquisitionTime(new Timestamp(now.getTime()));
            entity.setPower(power);
            entity.setCtSensorMaster(getCtSensorMasterByChannelId(3));
            entityMapChannel3.put(now.getTime(), entity);
        } else {
            logger.warn("onGetMeasurementChannelValue1, isSuccess: false");
        }
    }

    @Override
    public void onGetMeasurementChannelValue2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            String edt = objProp.getStrEdt();
            double power = Integer.parseInt(edt.substring(0, 8), 16);
            power = power * unit;
            logger.info("Channel 2 value acquired: [ " + power + " ]");

            Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
            EdgeCtSensorData entity = new EdgeCtSensorData();
            entity.setAcquisitionTime(new Timestamp(now.getTime()));
            entity.setPower(power);
            entity.setCtSensorMaster(getCtSensorMasterByChannelId(4));
            entityMapChannel4.put(now.getTime(), entity);
        } else {
            logger.warn("onGetMeasurementChannelValue2, isSuccess: false");
        }
    }

    @Override
    public void onGetMeasurementChannelValue3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            String edt = objProp.getStrEdt();
            double power = Integer.parseInt(edt.substring(0, 8), 16);
            power = power * unit;
            logger.info("Channel 3 value acquired: [ " + power + " ]");

            Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
            EdgeCtSensorData entity = new EdgeCtSensorData();
            entity.setAcquisitionTime(new Timestamp(now.getTime()));
            entity.setPower(power);
            entity.setCtSensorMaster(getCtSensorMasterByChannelId(5));
            entityMapChannel5.put(now.getTime(), entity);
        } else {
            logger.warn("onGetMeasurementChannelValue3, isSuccess: false");
        }
    }

    @Override
    public void onGetMeasurementChannelValue4(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            String edt = objProp.getStrEdt();
            double power = Integer.parseInt(edt.substring(0, 8), 16);
            power = power * unit;
            logger.info("Channel 4 value acquired: [ " + power + " ]");

            Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
            EdgeCtSensorData entity = new EdgeCtSensorData();
            entity.setAcquisitionTime(new Timestamp(now.getTime()));
            entity.setPower(power);
            entity.setCtSensorMaster(getCtSensorMasterByChannelId(6));
            entityMapChannel6.put(now.getTime(), entity);
        } else {
            logger.warn("onGetMeasurementChannelValue4, isSuccess: false");
        }
    }

    @Override
    public void onGetMeasurementChannelValue5(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            String edt = objProp.getStrEdt();
            double power = Integer.parseInt(edt.substring(0, 8), 16);
            power = power * unit;
            logger.info("Channel 5 value acquired: [ " + power + " ]");

            Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
            EdgeCtSensorData entity = new EdgeCtSensorData();
            entity.setAcquisitionTime(new Timestamp(now.getTime()));
            entity.setPower(power);
            entity.setCtSensorMaster(getCtSensorMasterByChannelId(7));
            entityMapChannel7.put(now.getTime(), entity);
        } else {
            logger.warn("onGetMeasurementChannelValue5, isSuccess: false");
        }
    }

    @Override
    public void writeDatabase() throws SQLException {
//        logger.info("PDBMRepProcessor:: writeDatabase()");
        Collection<EdgeCtSensorData> accumulatedAmountOfPowerChannel3 = entityMapChannel3.values();
        Collection<EdgeCtSensorData> accumulatedAmountOfPowerChannel4 = entityMapChannel4.values();
        Collection<EdgeCtSensorData> accumulatedAmountOfPowerChannel5 = entityMapChannel5.values();
        Collection<EdgeCtSensorData> accumulatedAmountOfPowerChannel6 = entityMapChannel6.values();
        Collection<EdgeCtSensorData> accumulatedAmountOfPowerChannel7 = entityMapChannel7.values();

//        logger.info("PDBMRepProcessor:: channel 3 Value: " + accumulatedAmountOfPowerChannel3);
//        logger.info("PDBMRepProcessor:: channel 4 Value: " + accumulatedAmountOfPowerChannel4);
//        logger.info("PDBMRepProcessor:: channel 5 Value: " + accumulatedAmountOfPowerChannel5);
//        logger.info("PDBMRepProcessor:: channel 6 Value: " + accumulatedAmountOfPowerChannel6);
//        logger.info("PDBMRepProcessor:: channel 7 Value: " + accumulatedAmountOfPowerChannel7);

//        logger.info("PDBMRepProcessor:: Saving Power Values writeDatabase()");
        edgeCtSensorDataTableController.saveAll((List<EdgeCtSensorData>) accumulatedAmountOfPowerChannel3);
        entityMapChannel3.clear();
        edgeCtSensorDataTableController.saveAll((List<EdgeCtSensorData>) accumulatedAmountOfPowerChannel4);
        entityMapChannel4.clear();
        edgeCtSensorDataTableController.saveAll((List<EdgeCtSensorData>) accumulatedAmountOfPowerChannel5);
        entityMapChannel5.clear();
        edgeCtSensorDataTableController.saveAll((List<EdgeCtSensorData>) accumulatedAmountOfPowerChannel6);
        entityMapChannel6.clear();
        edgeCtSensorDataTableController.saveAll((List<EdgeCtSensorData>) accumulatedAmountOfPowerChannel7);
        entityMapChannel7.clear();

    }
}
