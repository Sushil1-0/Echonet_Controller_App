package jp.co.smartsolar.smartedge.eoj.reportprocessor;

import jp.co.smartsolar.smartedge.database.controller.*;
import jp.co.smartsolar.smartedge.database.entity.*;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.devices.StorageBatteryClass;
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
 * @date 5/14/2024
 */
public class StorageBatteryReportProcessor extends StorageBatteryClass.ReportProcessor implements ReportProcessorInterface{

    private Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.LogService");

    public EdgeBatteryChargeTableController edgeBatteryChargeTableController;

    public EdgeBatteryCumulativeChargeTableController edgeBatteryCumulativeChargeTableController;

    public DeviceMasterTableController deviceMasterTableController;

    public ControlMasterTableController controlMasterTableController;

    public PowerControlResultTableController powerControlResultTableController = new PowerControlResultTableController();

    public ConfigInfoTableController configInfoTableController;

    /** 同期用カウントダウン. */
    private CountDownLatch countDownLatch = null;

    /**
     * 現在の運転動作状態.
     */
    private String workingOperationStatus = "40";

    /**
     * 現在の運転動作設定.
     */
    private String operationModeSetting = "40";

    /** 現在の積算放電電力量計測値. */
    private long currentRemainingBattyeryCumulativeDischarge;

    /** 現在の積算充電電力量計測値. */
    private long currentRemainingBattyeryCumulativeCharge;

    /** 現在の蓄電残量1. */
    private long currentRemainingStoredElectricity1;

    /** 現在の蓄電残量3. */
    private int currentRemainingStoredElectricity3;

    /**
     * 蓄電池残量map
     */
    private Map<Long, EdgeBatteryCharge> edgeBatteryChargeMap = new ConcurrentHashMap<Long, EdgeBatteryCharge>();

    /**
     * 積算電力量map
     */
    private Map<Long, EdgeBattyeryCumulativeCharge> edgeBattyeryCumulativeChargeMap = new ConcurrentHashMap<Long, EdgeBattyeryCumulativeCharge>();

    /**
     * 動作モード設定map
     */
    private Map<Long, PowerControlResult> operationModeMap = new ConcurrentHashMap<Long, PowerControlResult>();

    /**
     * 現在の運転動作状態取得.
     * @return 現在の運転動作状態
     */
    public synchronized String getWorkingOperationStatus() {
        return workingOperationStatus;
    }

    /**
     * 現在の運転動作設定取得.
     * @return 現在の運転動作設定
     * <ul>
     * <li>急速充電＝0x41
     * <li>充電＝0x42
     * <li>放電＝0x43
     * <li>待機＝0x44
     * <li>テスト=0x45
     * <li>自動＝ 0x46
     * <li>再起動＝0x48
     * <li>実効容量再計算処理＝0x49
     * <li>その他＝0x40
     * </ul>
     */
    public synchronized String getOperationModeSetting() {
        return operationModeSetting;
    }

    public synchronized long getRemainingBattyeryCumulativeDischarge() {
        return currentRemainingBattyeryCumulativeDischarge;
    }

    public synchronized long getRemainingBattyeryCumulativeCharge() {
        return currentRemainingBattyeryCumulativeCharge;
    }

    /**
     * 現在の蓄電池残量1を取得.
     * @return 現在の蓄電池残量1
     */
    public synchronized long getRemainingStoredElectricity1() {
        return currentRemainingStoredElectricity1;
    }

    /**
     * 現在の蓄電池残量3を取得.
     * @return 現在の蓄電池残量3(%)
     */
    public synchronized int getRemainingStoredElectricity3() {
        return currentRemainingStoredElectricity3;
    }


    /**
     * CountDownLatch設定
     * @param latch CountDownLatch
     */
    public synchronized void setCountDownLatch(CountDownLatch latch) {
        countDownLatch = latch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        System.out.println("対向ノードの蓄電池のOperation status の値を取得しました。" + objProp.getStrEdt());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onGetWorkingOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if(isSuccess) {
            workingOperationStatus = objProp.getStrEdt();
            Integer deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            logger.info("蓄電池(" + deviceId + "):運転動作状態=" + workingOperationStatus);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onGetMeasuredCumulativeDischargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            Integer deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            String edt = objProp.getStrEdt();
            Integer edtInt = Integer.parseInt(edt, 16);
            currentRemainingBattyeryCumulativeDischarge = edtInt;
            logger.info("蓄電池(" + deviceId + "):積算放電電力量計測値=" + currentRemainingBattyeryCumulativeDischarge + "[Wh]");

            Long value = Long.valueOf(edtInt); // TODO : 値の変換は必要？
            DeviceMaster deviceMaster = null;
            try {
                deviceMaster = deviceMasterTableController.findById(deviceId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (deviceMaster != null) {
                // 秒以下を0セットした日付を作成
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);

                EdgeBattyeryCumulativeCharge entity = new EdgeBattyeryCumulativeCharge();
                entity.setCycleDatetime(new Timestamp(now.getTime()));
                entity.setDischargeAmount(value); //TODO:ここで充電は取れない。対応必要。
                entity.setChargeAmount(currentRemainingBattyeryCumulativeCharge);

//                if( currentRemainingBattyeryCumulativeCharge != 0 ) {
//                    currentRemainingBattyeryCumulativeCharge = 0;
//                }
                entity.setDeviceMaster(deviceMaster);

                // バッファリング
                edgeBattyeryCumulativeChargeMap.put(now.getTime(), entity);
            }
        } else {
//            logger.warn("onGetMeasuredCumulativeChargingElectricEnergy : isSuccess=false");
            logger.warn("onGetMeasuredCumulativeDischargingElectricEnergy : isSuccess=false");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onGetMeasuredCumulativeChargingElectricEnergy(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            Integer deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            String edt = objProp.getStrEdt();
            Integer edtInt = Integer.parseInt(edt, 16);
            currentRemainingBattyeryCumulativeCharge = edtInt;
            logger.info("蓄電池(" + deviceId + "):積算充電電力量計測値=" + currentRemainingBattyeryCumulativeCharge + "[Wh]");

            Long value = Long.valueOf(edtInt); // TODO : 値の変換は必要？
            DeviceMaster deviceMaster = null;
            try {
                deviceMaster = deviceMasterTableController.findById(deviceId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (deviceMaster != null ) {
                // 秒以下を0セットした日付を作成
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);

                EdgeBattyeryCumulativeCharge entity = new EdgeBattyeryCumulativeCharge();
                entity.setCycleDatetime(new Timestamp(now.getTime()));
                entity.setChargeAmount(value);; //TODO:ここで放電は取れない。対応必要。
                entity.setDischargeAmount(currentRemainingBattyeryCumulativeDischarge);

//                if( currentRemainingBattyeryCumulativeDischarge != 0 ) {
//                    currentRemainingBattyeryCumulativeCharge = 0;
//                }
                entity.setDeviceMaster(deviceMaster);

                // バッファリング
                edgeBattyeryCumulativeChargeMap.put(now.getTime(), entity);
            }
        } else {
            logger.warn("onGetMeasuredCumulativeChargingElectricEnergy : isSuccess=false");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onGetRemainingStoredElectricity1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            Integer deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            String edt = objProp.getStrEdt();
            Integer edtInt = Integer.parseInt(edt, 16);
            currentRemainingStoredElectricity1 = edtInt;
            logger.info("蓄電池(" + deviceId + "):蓄電残量1=" + currentRemainingStoredElectricity1 + "[Wh]");

            Long value = Long.valueOf(edtInt); // TODO : 値の変換は必要？
            DeviceMaster deviceMaster = null;
            try {
                deviceMaster = deviceMasterTableController.findById(deviceId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (deviceMaster != null) {
                // 秒以下を0セットした日付を作成
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);

                EdgeBatteryCharge entity = new EdgeBatteryCharge();
                entity.setCycleDatetime(new Timestamp(now.getTime()));
                entity.setElectricVol1(value); //TODO:ここで蓄電残量１は取れない。対応必要。
                entity.setElectricVol3(currentRemainingStoredElectricity3);
                entity.setChargingVol(currentRemainingBattyeryCumulativeCharge);
                entity.setDischargingVol(currentRemainingBattyeryCumulativeDischarge);
//
//                if( currentRemainingStoredElectricity3 != 0 ) {
//                    currentRemainingStoredElectricity3 = 0;
//                }
                entity.setDeviceMaster(deviceMaster);

                // バッファリング
                edgeBatteryChargeMap.put(now.getTime(), entity);

                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    countDownLatch = null;
                }
            }
        } else {
            logger.warn("onGetRemainingStoredElectricity3 : isSuccess=false");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onGetRemainingStoredElectricity3(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            Integer deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            String edt = objProp.getStrEdt();
            Integer edtInt = Integer.parseInt(edt, 16);
            currentRemainingStoredElectricity3 = edtInt;
            logger.info("蓄電池(" + deviceId + "):蓄電残量3=" + currentRemainingStoredElectricity3 + "[%]");

            Double value = Double.valueOf(edtInt); // TODO : 値の変換は必要？
            DeviceMaster deviceMaster = null;
            try {
                deviceMaster = deviceMasterTableController.findById(deviceId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (deviceMaster != null) {
                // 秒以下を0セットした日付を作成
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);

                EdgeBatteryCharge entity = new EdgeBatteryCharge();
                entity.setCycleDatetime(new Timestamp(now.getTime()));
                entity.setElectricVol1(currentRemainingStoredElectricity1);
                entity.setChargingVol(currentRemainingBattyeryCumulativeCharge);
                entity.setDischargingVol(currentRemainingBattyeryCumulativeDischarge);
//
//                if(currentRemainingStoredElectricity1 != 0) {
//                    //entity.setElectricVol1((long)0); //TODO:ここで蓄電残量１は取れない。対応必要。
//                    currentRemainingStoredElectricity1 = 0;
//                }
                entity.setElectricVol3(value);
                entity.setDeviceMaster(deviceMaster);

                // バッファリング
                edgeBatteryChargeMap.put(now.getTime(), entity);

                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    countDownLatch = null;
                }
            }
        } else {
            logger.warn("onGetRemainingStoredElectricity3 : isSuccess=false");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onGetOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            operationModeSetting = objProp.getStrEdt();

            Integer deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            logger.info("蓄電池({}):運転モード設定={}", deviceId, operationModeSetting);
            ControlMaster controlMaster = (ControlMaster) controlMasterTableController.findByEchonetCmd(operationModeSetting);
            if (controlMaster != null) {

                // 秒以下を0セットした日付を作成
                Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);

                PowerControlResult entity = new PowerControlResult();
                entity.setControlMaster(controlMaster);
                entity.setDatetime(new Timestamp(now.getTime()));

                // バッファリング
                operationModeMap.put(now.getTime(), entity);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onSetOperationModeSetting(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        if (isSuccess) {
            Integer deviceId = Integer.parseInt(seoj.getStrEntityCode(), 16);
            logger.info("蓄電池({}):運転モード設定成功", deviceId);
            StorageBatteryClass device = (StorageBatteryClass) seoj;
            try {
                device.get()
                        .reqGetOperationModeSetting()
                        .send();
            } catch (Exception e) {
                logger.warn("蓄電池({}):運転モード設定取得要求失敗={}", deviceId, e.getMessage());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void writeDatabase() throws SQLException {
        Collection<EdgeBatteryCharge> edgeBatteryCharge = edgeBatteryChargeMap.values();
        edgeBatteryChargeTableController.saveAll((List<EdgeBatteryCharge>) edgeBatteryCharge);
        edgeBatteryChargeMap.clear();

//        Collection<EdgeBattyeryCumulativeCharge> edgeBattyeryCumulativeCharge = edgeBattyeryCumulativeChargeMap.values();
//        edgeBattyeryCumulativeChargeRepository.saveAll(edgeBattyeryCumulativeCharge);
        edgeBattyeryCumulativeChargeMap.clear();

        Collection<PowerControlResult> controlResult = operationModeMap.values();
        powerControlResultTableController.saveAll((List<PowerControlResult>) controlResult);
        operationModeMap.clear();

//        Optional<ConfigInfo> data;
//        data = configInfoRepository.findByKeyNameAndGroupeName("charging_mode", "edge");
//        data.get().setKeyValue(operationModeSetting);
//        configInfoRepository.save( data.get() );
    }
}
