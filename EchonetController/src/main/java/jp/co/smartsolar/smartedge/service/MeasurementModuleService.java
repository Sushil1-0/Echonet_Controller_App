package jp.co.smartsolar.smartedge.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.smartsolar.smartedge.component.Settings;
import jp.co.smartsolar.smartedge.database.controller.*;
import jp.co.smartsolar.smartedge.database.entity.ConfigInfo;
import jp.co.smartsolar.smartedge.database.entity.DeviceMaster;
import jp.co.smartsolar.smartedge.database.entity.OutputControl;
import jp.co.smartsolar.smartedge.database.entity.PowerCatalog;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElOneNode;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.channel.ElChannelBase;
import jp.co.smartsolar.smartedge.echonetlite.channel.udp.ElLocalUdpChannel;
import jp.co.smartsolar.smartedge.echonetlite.channel.udp.ElUdpChannel;
import jp.co.smartsolar.smartedge.echonetlite.devices.*;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;
import jp.co.smartsolar.smartedge.eoj.NodeProfileClassImpl;

import jp.co.smartsolar.smartedge.eoj.reportprocessor.*;


import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * @package jp.co.smartsolar.smartedge.service
 * @Author subohaju
 * @date 5/9/2024
 */


@Getter
@Setter
public class MeasurementModuleService extends ElProcess.ElEventListener
{

    private Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.LogService");

    private boolean isConnected;
    private String locaIpAddress;
    private String predictionTgt;
    private String tgt;
    private String chargingStart;
    private String chargingEnd;
    private String standbyMode;
    private Boolean isSmaPcs = null;
    private String previousChgVol;
    Integer btMin = null;
    private double chargeVolume = 0;
    private String networkMode;
    private String externalControl;
    private int previousBatteryVolume = 0;
    private int batteryVolCounter;
    private double currentBuyPower;
    String previousMode = "";
    private boolean smartPdbm = false;
    private int previousCumulativeAmountOfSellPower;
    private long previousBatteryDischarge;
    private boolean PROTECTION_MODE = false;
    //	private Boolean isTrina = null;
    boolean protectionStandby = false;
    private String pChgVol = "";
    private double pTemperature = 0;
    private double pDschgStopSoc;
    private double pChgStartSoc;
    private double pChgStopSoc;
    private double pChgStartVoltage;
    private double pChgStopVoltage;
    private int readCount;
    private boolean setToTradeMode = false;
    private static boolean isSendChargeVolFailed = false;
    //	private String prevExternalControl = "";



    ConfigInfoTableController configInfoTableController = new ConfigInfoTableController();
    PowerCatalogTableController powerCatalogTableController = new PowerCatalogTableController();
    LowVoltageSmartMeterReportProcessor lowVoltageSmartMeterReportProcessor = new LowVoltageSmartMeterReportProcessor();
    PowerDistributionBoardMeterReportProcessor powerDistributionBoardMeterReportProcessor = new PowerDistributionBoardMeterReportProcessor();
    EdgeSolarPowerTableController edgeSolarPowerTableController = new EdgeSolarPowerTableController();
    DeviceMasterTableController deviceMasterTableController = new DeviceMasterTableController();
    EdgeBatteryChargeTableController edgeBatteryChargeTableController = new EdgeBatteryChargeTableController();
    ControlMasterTableController controlMasterTableController = new ControlMasterTableController();
    PowerControlResultTableController powerControlResultTableController = new PowerControlResultTableController();

    private OutputControlController outputControlController;
    SmartPowerDistributionBoardMeterService smartPowerDistributionBoardMeterService = new SmartPowerDistributionBoardMeterService();
    StorageBatteryReportProcessor storageBatteryReportProcessor = new StorageBatteryReportProcessor();

    private final String INSTANCE_CODE = "C1";
    private String spdbmRemoteIpaddress;
    private String remoteIpAddress;

    private static final String  JSON_CURRENT_MODE              = "current_mode";


    private List<ReportProcessorInterface> reportProcessorList = Collections.synchronizedList(new ArrayList<ReportProcessorInterface>());

    private ElProcess process;
    public synchronized void connect() throws Exception{
        logger.info("測定モジュール接続開始");
        if(!isConnected) {
//            System.out.println("here");
            locaIpAddress = Settings.localIpAddress;
            System.out.println("local ip address :" + locaIpAddress );

            smartPdbm = Boolean.parseBoolean(Settings.sPdbm);
            ElChannelBase channel;
            channel = new ElUdpChannel(Settings.localIpAddress);
            logger.info("measurementModuleService test ElUdpChannel: {}", Settings.localIpAddress);
            logger.info("measurementModuleService : {}", channel);
            process = ElProcess.generate(channel, new NodeProfileClassImpl(), new DeviceObjectSuperClass[]{new ControllerClass(INSTANCE_CODE)});
            process.initEventList().addEventListenerToList(this).addEventListenerToList(new ElProcess.ElEventLogger()).start();
            isConnected = true;
        }
        logger.info("測定モジュール接続完了");
        NodeProfileClass.infReqAll(process).reqInfInstanceListNotification().send();
    }
    public synchronized void disconnect() throws Exception {
        if (isConnected) {
            process.stop();
            process.initEventList();
            process = null;
            isConnected = false;
        }
    }
    @Synchronized
    public void excuteByEveryMinute() throws SQLException {
        if (!isConnected) {
            return;
        }

        // wait for snsrctlapp to fetch data
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e3) {
            logger.info("MeasurementModuleService excuteByEveryMinute Exception thread sleep : " + e3.toString());
            e3.printStackTrace();
        }
        LocalDateTime ldt = LocalDateTime.now();
        logger.info("MeasurementModuleService 毎分実行処理:" + ldt);

        boolean isFindRemoteNode = false;
        Map<String, ElOneNode> map = process.getRemoteNodeMap();
        logger.info("MeasurementModuleService mapKeySets : {}", map.keySet());
        if (map.containsKey(locaIpAddress) && (map.get(locaIpAddress) != null)) {
            ElOneNode node = map.get(locaIpAddress);
            logger.info("MeasurementModuleService　Host:[{}]", node.getIpAddress());

            // 現在のカタログ制御を取得
            Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
            Timestamp timestamp = new Timestamp(now.getTime());

            List<DeviceObjectSuperClass> deviceClassList = node.getDeviceEojList();
            if (deviceClassList.size() > 0) {
                isFindRemoteNode = true;

                List<StorageBatteryClass> storageBatteryList = new ArrayList<StorageBatteryClass>();

                CountDownLatch doneSignal = new CountDownLatch(deviceClassList.size());
                for (DeviceObjectSuperClass deviceClass : deviceClassList) {
                    try {
                        // 機器クラス毎に送信メッセージをわける
                        switch (deviceClass.getStrClassCode()) {

                            // 蓄電池
                            case StorageBatteryClass.CLASS_CODE: {
                                // 欠損データ確認スレッド起動
                                //	                            MissingDataCheckThread missingDataCheckThread = new MissingDataCheckThread(device.getStrEntityCode(), StorageBatteryClass.CLASS_CODE);
                                //	                            missingDataCheckThread.start();
                                StorageBatteryClass device = (StorageBatteryClass) deviceClass;
                                StorageBatteryReportProcessor reportProcessor  = (StorageBatteryReportProcessor) device.getReportProcessor();
                                if (reportProcessor != null) {
                                    storageBatteryList.add(device);
                                    // CountDownLatchを設定し蓄電残量3と運転動作設定を要求する
                                    reportProcessor.setCountDownLatch(doneSignal);
                                } else {
                                    doneSignal.countDown();
                                }

                                device.get()
                                        .reqGetOperationStatus()
                                        //getFromAppMeasuredCumulativeDischargingElectricEnergy
                                        .reqGetMeasuredCumulativeChargingElectricEnergy() //積算放電電力量計測値 addMapGetPropsに追加しないと実行できない		//uncommented 20210218
                                        .reqGetMeasuredCumulativeDischargingElectricEnergy() //積算充電電力量計測値 addMapGetPropsに追加しないと実行できない		//uncommented 20210218
                                        .reqGetWorkingOperationStatus()			// 運転動作状態
                                        .reqGetRemainingStoredElectricity1()    // 蓄電残量1
                                        .reqGetRemainingStoredElectricity3()	// 蓄電残量3
                                        //											.reqGetMeasuredInstantChargeDischargeElectricPower()				//not used
                                        .send();
                                break;
                            }

                            // 太陽光
                            case HouseholdSolarPowerGenerationClass.CLASS_CODE: {
                                doneSignal.countDown();

                                //  欠損データ確認スレッド起動
                                //								                            MissingDataCheckThread missingDataCheckThread = new MissingDataCheckThread(device.getStrEntityCode(), HouseholdSolarPowerGenerationClass.CLASS_CODE);
                                //	                            missingDataCheckThread.start();

                                HouseholdSolarPowerGenerationClass device = (HouseholdSolarPowerGenerationClass) deviceClass;
                                device.get()
                                        .reqGetOperationStatus()
                                        .reqGetOutputLimitSetting1()
                                        .reqGetMeasuredCumulativeAmountOfEnergyGenerated() // 積算発電電力量計測値
                                        .send();
                                break;
                            }

                            // 高圧スマートメータ
                            case HighVoltageSmartMeter.CLASS_CODE: {
                                doneSignal.countDown();

                                HighVoltageSmartMeter device = (HighVoltageSmartMeter) deviceClass;
                                device.get()
                                        .reqGetOperationStatus()
                                        // TODO 電力データ
                                        .send();
                                break;
                            }

                            // 低圧スマートメータ
                            case LowVoltageSmartMeter.CLASS_CODE: {
                                doneSignal.countDown();

                                LowVoltageSmartMeter device = (LowVoltageSmartMeter) deviceClass;
                                device.get()
                                        .reqGetOperationStatus()
                                        .reqGetIntegralPowerConsumptionMeasuredValuesPositive() // 積算電力量計測値(正方向計測値)
                                        .reqGetIntegralPowerConsumptionMeasuredValuesReverse() // 積算電力量計測値(逆方向計測値)
                                        .send();

                                break;
                            }

                            case PowerDistributionBoardMetering.CLASS_CODE: {
                                if (smartPdbm) {
                                    logger.info("smartPdbm used, dont fetch data from meas side");
                                    break;
                                }
                                doneSignal.countDown();
                                // TODO this statement throws "ClassCastException": a parent cannot be cast to child
                                PowerDistributionBoardMetering device = (PowerDistributionBoardMetering) deviceClass;
//							logger.info("PowerDistributionBoardMetering device = (PowerDistributionBoardMetering) deviceClass");
							/*
							TODO since the above statement throws an exception, using the following approach,
							 Downcast DeviceObjectSuperClass to PowerDistributionBoardMetering class
							 STATUS -> RESOLVED
							 */
                                // start comment
							/*
							PowerDistributionBoardMetering device = new PowerDistributionBoardMetering();
							BeanUtils.copyProperties(deviceClass, device);
							if (!isPowerDistributionBoardMeterFound) {
								device.onNewEojFound();
								isPowerDistributionBoardMeterFound = true;
							}
							 */
                                // up to here

//							logger.info("reqGetMeasurementChannel1()");
//							logger.info("reqGetMeasurementChannel2()");
//							logger.info("reqGetMeasurementChannel3()");
//							logger.info("reqGetMeasurementChannel4()");
//							logger.info("reqGetMeasurementChannel5()");
                                device.get()
                                        .reqGetOperationStatus()
                                        .reqGetMeasurementChannel1()
                                        .reqGetMeasurementChannel2()
                                        .reqGetMeasurementChannel3()
                                        .reqGetMeasurementChannel4()
                                        .reqGetMeasurementChannel5()
                                        .send();
                                break;
                            }

                            default:
                                doneSignal.countDown();
                                break;
                        }
                    } catch (Exception ex) {
                        logger.warn("送信エラー:EOJ(" + deviceClass.getStrEojCode() + "), e=" + ex.getMessage());
                    }
                }

                // 蓄電池からの情報が取得できたら蓄電池へ制御を行う。
                try {
                    doneSignal.await(5, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    logger.warn("蓄電池取得待ち中エラー, e=" + ex.getMessage());
                }

                String cmd = "";							// 電力カタログが無い場合は動作モード変更を行わない。
                Long targetVol = null;
                PowerCatalog catalog = (PowerCatalog) powerCatalogTableController.findFirstByDatetimeOrderByIdDesc(timestamp);  //findByDatetime>>findByDatetimeOrderByIdDesc

                String currentMode = configInfoTableController.findByKeyName("dsPcsCurrentMode").getKeyValue();		//fetch current_mode from db  20210629
                if (currentMode == null || currentMode.isEmpty()) {
                    currentMode = "consumption_mode";
                    logger.info("dsPcsCurrentMode not found. Run as consumption_mode");
                }

                Calendar ca = Calendar.getInstance();
                int hh = ca.get(Calendar.HOUR_OF_DAY); //時(24時間)
                int mm = ca.get(Calendar.MINUTE); //分
                String shm = String.format("%d%02d", hh, mm);
                int hm = Integer.valueOf(shm);

                // 6000;1707266310
                predictionTgt = configInfoTableController.findByKeyName("predictionTarget").getKeyValue();
                String[] values = predictionTgt.split(";");
                if (values.length > 0) {
                    predictionTgt = values[0];
                }
                if (predictionTgt == null || predictionTgt.isEmpty()) {
                    predictionTgt = "0";
                }
                logger.info("predictionTgt : {}", predictionTgt);
                if (Integer.valueOf(predictionTgt) > 0) {
                    tgt = predictionTgt;
                }else {
                    tgt = configInfoTableController.findByKeyName("tgt").getKeyValue();
                }
                chargingStart = configInfoTableController.findByKeyName("chargingStart").getKeyValue();
                chargingEnd = configInfoTableController.findByKeyName("chargingEnd").getKeyValue();
                standbyMode = Settings.standbyMode;

                if ((networkMode == null) || networkMode.isEmpty()) {		//LifeAssist用
                    networkMode = Settings.networkMode;
                    logger.info("NetworkMode : {}", networkMode);
                }

                //				if ((externalControl ==  null) || (externalControl.isEmpty())) {		//PacificPower用
                //					externalControl = settings.getExternalControl();
                externalControl = configInfoTableController.findByKeyName("externalControl").getKeyValue();
                logger.info("ExternalControl : {}", externalControl);

                if (externalControl.equalsIgnoreCase("ctrlOn")) {		//Lixil usbToLan in use
                    networkMode = "dynamic";
                    logger.info("Set network_mode to dynamic as ExternalControl : ctrlOn && usbToLan is connected");
                } else if((externalControl == null) || externalControl.isEmpty()) {
                    externalControl = "off";
                    logger.info("ExternalControl is null, set externalControl : {}", externalControl);
                }
                //				}

                if (isSmaPcs == null) {
                    isSmaPcs = checkPcsType();		//check only once
                    logger.info("check checkPcsType once, isSmaPcs : {}", isSmaPcs);
                }

                Long itgt = Long.valueOf(tgt);
                int ichargingStart = Integer.valueOf(chargingStart);
                int ichargingEnd = Integer.valueOf(chargingEnd);
                boolean isStandbyMode = Boolean.parseBoolean(standbyMode);
                int soc = 0;
                long batteryVol = 0L;
                //	boolean isDer = false;
                String chgVol = null;
                boolean sameChargeTime = (ichargingStart == ichargingEnd);
                boolean isSellingPower = false;
//				boolean isBatteryDischarging = false;

                boolean isNightChargingTime = (((ichargingEnd - ichargingStart >= 0) && (hm >= ichargingStart && hm <= ichargingEnd))
                        || ((ichargingEnd - ichargingStart < 0) && (hm >= ichargingStart || hm <= ichargingEnd)));

                //external_control : Off = default(Normal users), ctrlOn = lixil users, ctrlOff = pp device
                if(!(currentMode.equalsIgnoreCase("independent_mode"))) { 	//ctrlOff -> pp enabled
                    if (!(externalControl.equalsIgnoreCase("ctrlOff"))) {
                        logger.info("isIndependentMode : False");
                        if (currentMode.equalsIgnoreCase("disaster_mode")) {
                            logger.info("isDisasterMode : True");
                            cmd = "42";					//充電
                            targetVol = 11800L;			// 100% まで
                            chgVol = configInfoTableController.findByKeyName("forceChargingValue").getKeyValue();

                        } else if (catalog != null) {
                            logger.info("isCatalogPresent : True & Current_Mode : {}", currentMode);
                            //	cmd = catalog.get().getControlMaster().getEchonetCmd();
                            //	targetVol = catalog.get().getTargetVol();

                            //test
                            Integer controlId = catalog.getControlMaster().getId();
                            chgVol = String.valueOf(catalog.getTargetVol());		//target == chgVol(fcv) in case of DER
                            logger.info("controlId : " + controlId);
                            logger.info("chgVol :  " + chgVol);

                            if (controlId == 5) {
                                cmd = "42";
                                targetVol = 11800L;
                                chgVol = String.valueOf((Double.valueOf(chgVol) / 1000));
                                //	isDer = true;

                            } else if(controlId == 6) {
                                cmd = "43";
                                targetVol = 0L;
                                chgVol = String.valueOf((Double.valueOf(chgVol) / 1000));
                                //	isDer = true;

                            }else {
                                cmd = catalog.getControlMaster().getEchonetCmd();
                                targetVol = catalog.getTargetVol();
                                chgVol = Settings.forceChargingValue;
                            }

                        } else if (catalog == null) {
                            logger.info("isCatalogPresent : False & Current_Mode : {}", currentMode);
                            chgVol = configInfoTableController.findByKeyName("forceChargingValue").getKeyValue();
                            switch (currentMode) {
                                case "consumption_mode":
                                    //							if (hm >= ichargingStart &&  hm <= ichargingEnd) {
                                    //							if ((hm >= ichargingStart &&  hm <= ichargingEnd) || (hm >= ichargingStart &&  ichargingStart <= 2359) ||  (hm >= 0000 &&  hm <= ichargingEnd)){

                                    if (isNightChargingTime){
                                        cmd = "42";				//充電
                                        targetVol = itgt;
                                        // 							}else if ((hm >= ichargingEnd &&  hm <= 2359) || (hm >= 0000 && hm <= ichargingStart)) {
                                    }else {
                                        if (!networkMode.equalsIgnoreCase("dynamic")) {
                                            cmd = "46";				//Auto
                                            targetVol = null;
                                            //								targetVol = itgt;		//for customers with tgt 0		//added 20211209
                                            //								targetVol = 0L;		//commented for test with night charging setting
                                        }
                                    }
                                    if(sameChargeTime || itgt == 0) {
                                        if (!networkMode.equalsIgnoreCase("dynamic")) {
                                            targetVol = 0L;
                                            cmd = "46";			//Auto
                                            logger.info("AutoMode : True");
                                        }
                                    }
                                    break;

                                case "trade_mode":
                                    if (!isSmaPcs) {					// Sungrow PCS	~ use Temporarily
                                        if (isNightChargingTime){
                                            cmd = "42";
                                            targetVol = itgt;
                                        }
                                    }else{		//SMA PCS
                                        if (isNightChargingTime){
                                            cmd = "42";
                                            targetVol = itgt;
                                            if(sameChargeTime || targetVol == 0) {
                                                if (!networkMode.equalsIgnoreCase("dynamic")) {
                                                    targetVol = 0L;
                                                    cmd = "46";			//Auto
                                                    logger.info("AutoMode : True");
                                                }
                                            }
                                        }else if (hm >= ichargingEnd &&  hm <= 1759) {
                                            cmd = "44";
                                            //	targetVol = 0L;
                                            //	}else if ((hm >= 1800 &&  hm <= 2359) || (hm >= 0000 && hm <= ichargingStart)) {
                                        }else {
                                            cmd = "46";
                                            //	targetVol = 0L;
                                        }
                                    }
                                    break;

                                default:
                                    break;
                            }

                        }

                        for (StorageBatteryClass storageBatteryClass : storageBatteryList) {
                            try {
                                StorageBatteryReportProcessor reportProcessor  = (StorageBatteryReportProcessor) storageBatteryClass.getReportProcessor();
                                batteryVol = reportProcessor.getRemainingStoredElectricity1();
                                soc = reportProcessor.getRemainingStoredElectricity3();

                                //Echonet >> prevent mode_switching because of missing battery_volume value
                                if (cmd.equals("42")) {
                                    if (previousBatteryVolume == 0) {
                                        previousBatteryVolume = (int) batteryVol;
                                        logger.info("previousBatteryVolume is 0, set value of batteryVol");
                                    }else {
//										logger.info("batteryVol : "+ batteryVol +", previousBatteryVolume : "+ previousBatteryVolume);
                                        if (batteryVol == 0){		//|| (Math.abs(batteryVol - previousBatteryVolume) > 1000)
                                            if (batteryVolCounter < 3) {
                                                batteryVol = previousBatteryVolume;
                                                batteryVolCounter++;
                                                logger.info("batteryVol = previousBatteryVolume, add counter :{}", batteryVolCounter);
                                            }else {
                                                previousBatteryVolume = (int) batteryVol;
                                                batteryVolCounter = 0;
                                                logger.info("previousBatteryVolume = (int) batteryVol, set counter : {}", batteryVolCounter);
                                            }
                                        } else {
                                            previousBatteryVolume = (int) batteryVol;
//											logger.info("else previousBatteryVolume = (int) batteryVol");
                                        }
                                    }
                                }

                                logger.info("targetVol = {}, batteryVol : {}, soc : {}", targetVol, batteryVol, soc);
                                if (cmd.equals("42")) { 			// 充電  charging
                                    if (soc == 100 || targetVol <= batteryVol) {
                                        cmd = "46";					// 100%の場合は自動に設定
                                        if (currentMode.equalsIgnoreCase("disaster_mode")) {		//満充電の場合待機に設定
                                            cmd = "44";				//待機に設定
                                        }
                                    }
                                    if (isNightChargingTime) {
                                        if (currentMode.equalsIgnoreCase("consumption_mode") || currentMode.equalsIgnoreCase("trade_mode") ) {
                                            if ((batteryVol >= (targetVol - 100)) && (batteryVol <= (targetVol + 100))) {
                                                cmd = "44";
                                                logger.info("set Standby Mode after reaching targetVol");
                                            }
                                        }
                                    }
                                } else if(cmd.equals("43")) { 	// 放電
                                    if (soc == 0) {		// || targetVol >= batteryVol
                                        cmd = "46";					// 0%の場合は自動に設定
                                    } else if((batteryVol <= targetVol) || ((batteryVol >= (targetVol - 100)) && (batteryVol <= (targetVol + 100)))) {
                                        cmd = "44";
                                    }
                                }

                                //added for pcs night charging fix 20211111
                                if (isNightChargingTime) {
                                    if (hm == ichargingEnd) {
                                        if (currentMode.equalsIgnoreCase("consumption_mode")) {
                                            logger.info("nightChargingTimeEnd, set 46");
                                            cmd = "46";
                                        }else if(currentMode.equalsIgnoreCase("trade_mode")) {
                                            if (isSmaPcs) {
                                                logger.info("SMA pcs, nightChargingTimeEnd, set 44");
                                                cmd= "44";
                                            }
                                        }
                                    }
                                }

                                //removed standby if soc<5% 20220720
                                //								else {
                                //									if(targetVol == null  || isDer || (targetVol != 0)) {
                                //										if ((cmd == null) || (cmd == "") || !(cmd.equals("42")) ) {
                                //											if(Integer.valueOf(soc) != null)  {
                                //												if (soc != 0) {
                                //													if (soc <= 5) {		//soc less than 5%
                                //														logger.info("soc < 5%");
                                //														if (!reportProcessor.getWorkingOperationStatus().equals("42")) {
                                //															if (isSellingPower && currentMode.equalsIgnoreCase("consumption_mode") ) {
                                //																cmd = "46";
                                //																logger.info("selling power >> set to auto mode");
                                //															} else {
                                //																logger.info("battery low  >> set to standby mode");
                                //																cmd = "44";
                                //															}
                                //														}
                                //													}
                                //												}
                                //											}
                                //										}
                                //									}
                                //								}

                                if (isStandbyMode) {
                                    if (isNightChargingTime) {
                                        if (currentMode.equalsIgnoreCase("consumption_mode") || currentMode.equalsIgnoreCase("trade_mode") ) {
                                            cmd = "44";			//待機に設定
                                            logger.info("StandbyMode : True");
                                        }
                                    }
                                }

                                //to set battery to auto if power sell is available
                                int currentCumulativeAmountOfSellPower = lowVoltageSmartMeterReportProcessor.getCumulativeAmountOfSellPower();
//								logger.info("currentCumulativeAmountOfSellPower : " + currentCumulativeAmountOfSellPower);
                                if (previousCumulativeAmountOfSellPower == 0) {
                                    previousCumulativeAmountOfSellPower = currentCumulativeAmountOfSellPower;
                                }else {
                                    if ((currentCumulativeAmountOfSellPower - previousCumulativeAmountOfSellPower) >= 3) {
                                        previousCumulativeAmountOfSellPower = currentCumulativeAmountOfSellPower;
                                        isSellingPower = true;
                                    } else {
                                        isSellingPower = false;
                                    }
                                }

                                // <--- 20221004　寒冷地向け対応 start --->
                                /**
                                 * implement on Snsrctlapp so no need to implement here
                                 * need to confirm this with team
                                 */
//                                String coldRegionBatterySupport = configInfoTableController.findByKeyName("batteryProtectionMode").getKeyValue();
//                                if (coldRegionBatterySupport.equalsIgnoreCase("true")) {
//                                    // fetch battery temperature
//                                    Double temperature = null;
//                                    Double voltage = null;
//                                    try {
//                                        Registry registry = LocateRegistry.getRegistry(remoteIpAddress);		//  measIpAddress
//                                        ISnsrCtlOpeRmi stub = (ISnsrCtlOpeRmi) registry.lookup(ISnsrCtlOpeRmi.APP_REGISTRY_BIND_NAME);
//                                        Double[] tempVol = stub.fetchBatteryTemperatureVoltage();
//                                        temperature = tempVol[0];
//                                        voltage = tempVol[1];
//
//                                        logger.info("Battery Temperature : "+ temperature);
//                                        logger.info("Battery voltage : "+ voltage);
//                                    } catch (RemoteException | NotBoundException e) {
//                                        logger.info("RemoteException | NotBoundException : "+ e.getMessage());
//                                        e.printStackTrace();
//                                    }
//
//                                    double testTemperature = Double.parseDouble(settings.getpTemp());
//                                    // int psoc = Integer.parseInt(settings.getPsoc());
//
//                                    //test new version 2023-07-18
//                                    if (temperature != null) {
//                                        logger.info("[coldRegionSupport] initial cmd : " + cmd + ", currentMode : " + currentMode);
//
//                                        if (temperature < testTemperature && !PROTECTION_MODE) {
//                                            PROTECTION_MODE = true;
//                                            logger.info("[coldRegionSupport] testTemperature : " + testTemperature + ", PROTECTION_MODE : " + PROTECTION_MODE);
//                                        }
//
//                                        if (PROTECTION_MODE) {
//                                            if(temperature >= (testTemperature +1)){
//                                                PROTECTION_MODE = false;
//                                                setPcsMode(currentMode);
//                                                logger.info("[coldRegionSupport] EXIT, set currentMode : " + currentMode);
//                                            }else {
//                                                PROTECTION_MODE = true;
//                                                if (temperature < (testTemperature - 1)) {
//                                                    cmd = "44";			//standby
//                                                }
//
//                                                if (soc == 100) {
//                                                    cmd = "44";
//                                                }else {
//                                                    cmd = "42";
//                                                    chgVol = "1";
//                                                }
//                                                logger.info("[coldRegionSupport] else testTemperature : " + testTemperature + ", cmd : " + cmd);
//                                            }
//                                        }
//                                    }
//
//                                    logger.info("[coldRegionSupport] final cmd : " + cmd);
//                                    logger.info("[coldRegionSupport] final PROTECTION_MODE : " + PROTECTION_MODE);
//
//                                    try {
//                                        Optional<ConfigInfo> protectionStatus = configInfoRepository.findByKeyNameAndGroupeName("charging_mode", "edge");
//                                        if (!protectionStatus.get().getKeyValue().equalsIgnoreCase(String.valueOf(PROTECTION_MODE))) {
//                                            configInfoRepository.updateConfigInfo("charging_mode", String.valueOf(PROTECTION_MODE));
//                                            logger.info("[coldRegionSupport] update PROTECTION_MODE(charging_mode) : " + PROTECTION_MODE);
//                                        }
//                                    } catch (Exception e) {
//                                        logger.info("Exception updating PROTECTION_MODE : " + e.getMessage());
//                                        e.printStackTrace();
//                                    }
//                                }
                                // <--- 20221004　寒冷地向け対応 end --->

                                // 20220610 adjust chargingVolume in SMA pcs
                                if (isSmaPcs && cmd.equals("42")) {
                                    double currentBatteryCharge = 0;
                                    int contractBreakerVolume = 0;
                                    try {
                                        contractBreakerVolume = (int) Double.parseDouble(Settings.contractBreaker);			//amp
                                    } catch (Exception e) {
                                        contractBreakerVolume = 0;
                                        logger.info("contractBreakerVolume Exception : {}, contractBreakerVolume : {}", e.getMessage(), Settings.contractBreaker);
                                    }

                                    if (contractBreakerVolume != 0) {								//Enter half volume of contract breaker
                                        if (contractBreakerVolume > 5) {
                                            contractBreakerVolume = contractBreakerVolume - 5;		//deduct 10 from contractBreaker for sudden load increase
                                        }
                                        logger.info("contractBreakerVolume = {}", contractBreakerVolume);

                                        /**
                                         * need to confirm
                                         */
//                                        try {
//                                            Registry registry = LocateRegistry.getRegistry(remoteIpAddress);
//                                            ISnsrCtlOpeRmi stub = (ISnsrCtlOpeRmi) registry.lookup(ISnsrCtlOpeRmi.APP_REGISTRY_BIND_NAME);
//                                            if (stub == null) {
//                                                logger.info("stub null, cannot fetch l1/l2 value");
//                                            }
//
//                                            double[] gridAndBatteryCharge = stub.getChargeAdjustValues();
//                                            currentBuyPower = gridAndBatteryCharge[0];
//                                            currentBatteryCharge = gridAndBatteryCharge[1];
//                                            logger.info("Fetched currentBuyPower : " + currentBuyPower +", currentBatteryCharge : " + currentBatteryCharge);
//                                        } catch (RemoteException e1) {
//                                            logger.info("Remote Exception fetch l1/l2 value"+ e1.getMessage());
//                                            e1.printStackTrace();
//                                            currentBuyPower = -1;
//                                            currentBatteryCharge = -1;
//                                        } catch (NotBoundException e2) {
//                                            logger.info("NotBoundException fetch l1/l2 value"+ e2.getMessage());
//                                            e2.printStackTrace();
//                                            currentBuyPower = -1;
//                                            currentBatteryCharge = -1;
//                                        }

                                        if ((currentBatteryCharge >= 0) && (currentBuyPower >= 0)) {
                                            if (currentBuyPower > contractBreakerVolume) {
                                                //  change charging instruction to auto ??
                                                //	cmd = "46";
                                                chgVol = "0.1";
                                                //	chargeVolume = 0;
                                                logger.info("(currentBuyPower > contractBreakerVolume), chgVol = {}", chgVol);
                                            }else if ((hm == ichargingStart) || (hm == checkTime(ichargingStart, 1))) {
                                                chgVol = "0.1";
                                                //	chargeVolume = 0;
                                                logger.info("start first and second charge by chgVol = {}", chgVol);
                                                //	} else if (chargeVolume == 0) {
                                                //	chargeVolume = contractBreakerVolume - currentBuyPower; 	//40-10 = 30
                                                //	logger.info("(chargeVolume == 0), else if chargeVolume :" + chargeVolume);
                                            } else {
                                                double load = 0;
                                                if (currentBuyPower > currentBatteryCharge) {
                                                    load = currentBuyPower - currentBatteryCharge;
                                                }
                                                chargeVolume = contractBreakerVolume - load;
                                                logger.info("else after deducting load, chargeVolume :{}", chargeVolume);  		// can be charged by

                                                if ((chargeVolume >= 0.0) && (chargeVolume < 1)){  		//cannot set to charge by 0w
                                                    chgVol = "0.1";
                                                }else if((chargeVolume > 1) && (chargeVolume <= 3)){
                                                    chgVol = "0.2";
                                                }else if((chargeVolume > 3) && (chargeVolume <= 5)){
                                                    chgVol = "0.6";
                                                }else if((chargeVolume > 5) && (chargeVolume <= 7)){
                                                    chgVol = "1.0";
                                                }else if((chargeVolume > 7) && (chargeVolume <= 9)){
                                                    chgVol = "1.4";
                                                }else if((chargeVolume > 9) && (chargeVolume <= 11)){
                                                    chgVol = "1.8";
                                                }else if((chargeVolume > 11) && (chargeVolume <= 13)){
                                                    chgVol = "2.2";
                                                }else if((chargeVolume > 13) && (chargeVolume <= 15)){
                                                    chgVol = "2.6";
                                                }else if(chargeVolume > 15){
                                                    chgVol = "3.0";
                                                }else {
                                                    chgVol = "0.1";
                                                    logger.info("no case match set chgVol=0.1");
                                                }
                                            }
                                            //	chgVol = String.valueOf((chargeVolume / 1000));		//watt  -> kw
                                            logger.info("calculated chgVol : {}", chgVol);
                                            String dbChgVol = configInfoTableController.findByKeyName("forceChargingValue").getKeyValue();

                                            if (Double.parseDouble(chgVol) > Double.parseDouble(dbChgVol))  {
                                                chgVol = dbChgVol;
                                                logger.info("calculated chgVol > dbChgVol, use dbChgVol : {}", dbChgVol);
                                            } else {
                                                logger.info("Use calculated chgVol : {}", chgVol);
                                            }

                                            logger.info("final chgVol : {}", chgVol);
                                            if (isSendChargeVolFailed) {
                                                previousChgVol = null;
                                                logger.info("isSendChargeVolFailed, resend charge volume to meas");
                                            }
                                        }else {
                                            //	chgVol = "0.1";		//any exceptions, charge by 100w
                                            cmd = "46";
                                            logger.info("currentCumulativeBatteryCharge == 0, set to 46");
                                        }
                                    }

                                    //switching from consumption_mode / trade_mode -> disaster_mode, set initial chgVol = "0.1"
                                    if (currentMode.equalsIgnoreCase("disaster_mode") && (previousMode.equalsIgnoreCase("consumption_mode") || previousMode.equalsIgnoreCase("trade_mode"))) {
                                        chgVol = "0.1";
                                        logger.info("Mode change to disaster_mode. set initial chgVol : "+ chgVol);
                                    }
                                }

                                // return to previous cmd incase of ctrlOn // ctrlOff
                                // switching from disaster_mode -> consumption_mode/ trade_mode, set cmd as well
                                if (externalControl.equalsIgnoreCase("ctrlOn")) {
                                    if (previousMode.equalsIgnoreCase("disaster_mode") && currentMode.equalsIgnoreCase("consumption_mode")) {
                                        cmd = "46";
                                        logger.info("Mode change from disaster_mode to consumption_mode. set cmd : " + cmd );
                                    }else if(previousMode.equalsIgnoreCase("disaster_mode") &&  currentMode.equalsIgnoreCase("trade_mode")) {
                                        if (isSmaPcs) {
                                            cmd = "44";
                                            logger.info("smaPcs, Mode change from disaster_mode to trade_mode. set cmd : " + cmd );
                                        }
                                    }
                                }
                                previousMode = currentMode;

                                // change of externalControl
//								if (!prevExternalControl.equalsIgnoreCase(externalControl)) {
//									if (prevExternalControl.equalsIgnoreCase("off") && externalControl.equalsIgnoreCase("ctrlOn")) {
//										if (currentMode.equalsIgnoreCase("consumption_mode")) {
//											cmd = "46";
//										}else if (currentMode.equalsIgnoreCase("trade_mode")) {
//											cmd = "44";
//										}
//									}
//									logger.info("change of external_control. set cmd :  "+ cmd + ", " + prevExternalControl +" -> " + externalControl );
//									prevExternalControl =  externalControl;
//								}

                                if (chgVol != null) {
                                    if ((previousChgVol == null)  || (!(previousChgVol.equals(chgVol)))) {
                                        logger.info("set previousChgVol : " + previousChgVol + " -> "+ chgVol);
                                        this.previousChgVol = chgVol;
                                        /**
                                         * need to confirm
                                         */
//                                        try {
//                                            logger.info("measIpAddress " + remoteIpAddress);
//                                            Registry registry = LocateRegistry.getRegistry(remoteIpAddress);		//  measIpAddress
//                                            ISnsrCtlOpeRmi stub = (ISnsrCtlOpeRmi) registry.lookup(ISnsrCtlOpeRmi.APP_REGISTRY_BIND_NAME);
//                                            stub.updateNightCharging(chgVol);
//                                            isSendChargeVolFailed = false;
//                                        } catch (RemoteException | NotBoundException e) {
//                                            isSendChargeVolFailed = true;
//                                            logger.info("RemoteException | NotBoundException : "+ e.getMessage());
//                                            e.printStackTrace();
//                                        }
                                    }
                                }

                                // 運転モード設定
                                if(cmd.length() > 0) {
                                    storageBatteryClass.setC()
                                            .reqSetOperationModeSetting(cmd).send();
                                }
                            } catch (Exception ex) {
                                logger.warn("Exception *** {}", ex.toString());
                                logger.warn("送信エラー:EOJ(" + storageBatteryClass.getStrEojCode() + "), e=" + ex.getMessage());
                                ex.printStackTrace();
                            }
                        }


                        //temporary fix to set to consumption_mode/trade_mode after night charging
                        if (currentMode != null) {
                            boolean checkModeSetTime = ((hm > (checkTime(ichargingEnd, 2))) && (hm < (checkTime(ichargingEnd, 5))));
                            logger.info("checkModeSetTime : " + checkModeSetTime);
                            if (checkModeSetTime){
                                configInfoTableController.updateData("predictionTarget", "0");
                                setPcsMode(currentMode);
                            }
                        }
                    } else {		//  CtrlOff
                        //added 20220614 Enable disaster_mode even in ctrlOff
                        chgVol = configInfoTableController.findByKeyName("forceChargingValue").getKeyValue();
                        if (currentMode.equalsIgnoreCase("disaster_mode")) {
                            logger.info("ctrlOff, isDisasterMode : True");
                            cmd = "42";					//充電
                            targetVol = 11800L;			// 100% まで
                        }

                        for (StorageBatteryClass storageBatteryClass : storageBatteryList) {
                            try {
                                StorageBatteryReportProcessor reportProcessor  = (StorageBatteryReportProcessor) storageBatteryClass.getReportProcessor();
                                batteryVol = reportProcessor.getRemainingStoredElectricity1();
                                soc = reportProcessor.getRemainingStoredElectricity3();
                                logger.info("ctrlOff, targetVol = " +targetVol + ", batteryVol : "+  batteryVol + ", soc : " +soc );
                                if (cmd.equals("42")) { 			// 充電  charging
                                    if (soc == 100 || targetVol <= batteryVol) {
                                        cmd = "44";				//待機に設定
                                    }
                                }

                                //add cold region support here
                                //to set battery to auto if power sell is available
                                int currentCumulativeAmountOfSellPower = lowVoltageSmartMeterReportProcessor.getCumulativeAmountOfSellPower();
                                logger.info("currentCumulativeAmountOfSellPower : {}", currentCumulativeAmountOfSellPower);
                                if (previousCumulativeAmountOfSellPower == 0) {
                                    previousCumulativeAmountOfSellPower = currentCumulativeAmountOfSellPower;
                                }else {
                                    if ((currentCumulativeAmountOfSellPower - previousCumulativeAmountOfSellPower) >= 3) {
                                        previousCumulativeAmountOfSellPower = currentCumulativeAmountOfSellPower;
                                        isSellingPower = true;
                                    } else {
                                        isSellingPower = false;
                                    }
                                }

                                // <--- 20230720　寒冷地向け対応 start --->
                                String coldRegionBatterySupport = configInfoTableController.findByKeyName("batteryProtectionMode").getKeyValue();
                                if (coldRegionBatterySupport.equalsIgnoreCase("true")) {
                                    // fetch battery temperature
                                    Double temperature = null;
                                    Double voltage = null;
                                    /**
                                     * need to confirm
                                     */
//                                    try {
//                                        Registry registry = LocateRegistry.getRegistry(remoteIpAddress);		//  measIpAddress
//                                        ISnsrCtlOpeRmi stub = (ISnsrCtlOpeRmi) registry.lookup(ISnsrCtlOpeRmi.APP_REGISTRY_BIND_NAME);
//                                        Double[] tempVol = stub.fetchBatteryTemperatureVoltage();
//                                        temperature = tempVol[0];
//                                        voltage = tempVol[1];
//
//                                        logger.info("ctrlOff, Battery Temperature : "+ temperature);
//                                        logger.info("ctrlOff, Battery voltage : "+ voltage);
//                                    } catch (RemoteException | NotBoundException e) {
//                                        logger.info("ctrlOff, RemoteException | NotBoundException : "+ e.getMessage());
//                                        e.printStackTrace();
//                                    }

                                    double testTemperature = Double.parseDouble(Settings.pTemp);
                                    // int psoc = Integer.parseInt(settings.getPsoc());

                                    //test new version 2023-07-18
                                    if (temperature != null) {
                                        logger.info("ctrlOff, [coldRegionSupport] initial cmd : " + cmd + ", currentMode : " + currentMode);

                                        if (temperature < testTemperature && !PROTECTION_MODE) {
                                            PROTECTION_MODE = true;
                                            logger.info("ctrlOff, [coldRegionSupport] testTemperature : {}, PROTECTION_MODE : {}", testTemperature, PROTECTION_MODE);
                                        }

                                        if (PROTECTION_MODE) {
                                            if(temperature >= (testTemperature +1)){
                                                PROTECTION_MODE = false;
                                                setPcsMode(currentMode);
                                                logger.info("ctrlOff, [coldRegionSupport] EXIT, set currentMode : {}", currentMode);
                                            }else {
                                                PROTECTION_MODE = true;
                                                if (temperature < (testTemperature - 1)) {
                                                    cmd = "44";			//standby
                                                }

                                                if (soc == 100) {
                                                    cmd = "44";
                                                }else {
                                                    cmd = "42";
                                                    chgVol = "1";
                                                }
                                                logger.info("ctrlOff, [coldRegionSupport] else testTemperature : {}, cmd : {}", testTemperature, cmd);
                                            }
                                        }
                                    }

                                    logger.info("ctrlOff, [coldRegionSupport] final cmd : {}", cmd);
                                    logger.info("ctrlOff, [coldRegionSupport] final PROTECTION_MODE : {}", PROTECTION_MODE);

                                    try {
                                        ConfigInfo protectionStatus = configInfoTableController.findByKeyName("charging_mode");
                                        if (!protectionStatus.getKeyValue().equalsIgnoreCase(String.valueOf(PROTECTION_MODE))) {
                                            configInfoTableController.updateData("charging_mode", String.valueOf(PROTECTION_MODE));
                                            logger.info("ctrlOff, [coldRegionSupport] update PROTECTION_MODE(charging_mode) : {}", PROTECTION_MODE);
                                        }
                                    } catch (Exception e) {
                                        logger.info("ctrlOff, Exception updating PROTECTION_MODE : {}", e.getMessage());
                                        e.printStackTrace();
                                    }
                                }
                                // <--- 20230720　寒冷地向け対応 end --->

                                // chgVol Adjust Start
                                // 20220610 adjust chargingVolume in SMA pcs
                                if (isSmaPcs && cmd.equals("42")) {
                                    double currentBatteryCharge = 0;
                                    int contractBreakerVolume = 0;
                                    try {
                                        contractBreakerVolume = (int) Double.parseDouble(Settings.contractBreaker);			//amp
                                    } catch (Exception e) {
                                        contractBreakerVolume = 0;
                                        logger.info("ctrlOff, contractBreakerVolume Exception : " + e.getMessage() + ", contractBreakerVolume : "+ Settings.contractBreaker);
                                    }

                                    if (contractBreakerVolume != 0) {								//Enter half volume of contract breaker
                                        if (contractBreakerVolume > 5) {
                                            contractBreakerVolume = contractBreakerVolume - 5;		//deduct 10 from contractBreaker for sudden load increase
                                        }
                                        logger.info("ctrlOff, contractBreakerVolume = " + contractBreakerVolume);
/**
 * Need to confirm
 */
//                                        try {
//                                            Registry registry = LocateRegistry.getRegistry(remoteIpAddress);
//                                            ISnsrCtlOpeRmi stub = (ISnsrCtlOpeRmi) registry.lookup(ISnsrCtlOpeRmi.APP_REGISTRY_BIND_NAME);
//                                            if (stub == null) {
//                                                logger.info("ctrlOff, stub null, cannot fetch l1/l2 value");
//                                            }
//
//                                            double[] gridAndBatteryCharge = stub.getChargeAdjustValues();
//                                            currentBuyPower = gridAndBatteryCharge[0];
//                                            currentBatteryCharge = gridAndBatteryCharge[1];
//                                            logger.info("ctrlOff, Fetched currentBuyPower : " + currentBuyPower +", currentBatteryCharge : " + currentBatteryCharge);
//                                        } catch (RemoteException | NotBoundException e) {
//                                            logger.info("ctrlOff, Remote|NotBoundException Exception fetch l1/l2 value"+ e.getMessage());
//                                            e.printStackTrace();
//                                            currentBuyPower = -1;
//                                            currentBatteryCharge = -1;
//                                        }

                                        if ((currentBatteryCharge >= 0) && (currentBuyPower >= 0)) {
                                            if (currentBuyPower > contractBreakerVolume) {
                                                //  change charging instruction to auto ??
                                                //	cmd = "46";
                                                chgVol = "0.1";
                                                //	chargeVolume = 0;
                                                logger.info("ctrlOff, (currentBuyPower > contractBreakerVolume), chgVol = {}", chgVol);
                                            } else {
                                                double load = 0;
                                                if (currentBuyPower > currentBatteryCharge) {
                                                    load = currentBuyPower - currentBatteryCharge;
                                                }
                                                chargeVolume = contractBreakerVolume - load;
                                                logger.info("ctrlOff, else after deducting load, chargeVolume :{}", chargeVolume);  		// can be charged by

                                                if ((chargeVolume >= 0.0) && (chargeVolume < 1)){  		//cannot set to charge by 0w
                                                    chgVol = "0.1";
                                                }else if((chargeVolume > 1) && (chargeVolume <= 3)){
                                                    chgVol = "0.2";
                                                }else if((chargeVolume > 3) && (chargeVolume <= 5)){
                                                    chgVol = "0.6";
                                                }else if((chargeVolume > 5) && (chargeVolume <= 7)){
                                                    chgVol = "1.0";
                                                }else if((chargeVolume > 7) && (chargeVolume <= 9)){
                                                    chgVol = "1.4";
                                                }else if((chargeVolume > 9) && (chargeVolume <= 11)){
                                                    chgVol = "1.8";
                                                }else if((chargeVolume > 11) && (chargeVolume <= 13)){
                                                    chgVol = "2.2";
                                                }else if((chargeVolume > 13) && (chargeVolume <= 15)){
                                                    chgVol = "2.6";
                                                }else if(chargeVolume > 15){
                                                    chgVol = "3.0";
                                                }else {
                                                    chgVol = "0.1";
                                                    logger.info("ctrlOff, no case match set chgVol=0.1");
                                                }
                                            }
                                            //	chgVol = String.valueOf((chargeVolume / 1000));		//watt  -> kw
                                            logger.info("ctrlOff, calculated chgVol : {}", chgVol);
                                            String dbChgVol = configInfoTableController.findByKeyName("forceChargingValue").getKeyValue();

                                            if (Double.parseDouble(chgVol) > Double.parseDouble(dbChgVol))  {
                                                chgVol = dbChgVol;
                                                logger.info("ctrlOff, calculated chgVol > dbChgVol, use dbChgVol : " + dbChgVol);
                                            } else {
                                                logger.info("ctrlOff, Use calculated chgVol : " + chgVol);
                                            }

                                            logger.info("ctrlOff, final chgVol : "+ chgVol);
                                            if (isSendChargeVolFailed) {
                                                previousChgVol = null;
                                                logger.info("ctrlOff, isSendChargeVolFailed, resend charge volume to meas");
                                            }
                                        }else {
                                            //	chgVol = "0.1";		//any exceptions, charge by 100w
                                            cmd = "46";
                                            logger.info("ctrlOff, currentCumulativeBatteryCharge == 0, set to 46");
                                        }
                                    }

                                    //switching from consumption_mode / trade_mode -> disaster_mode, set initial chgVol = "0.1"
                                    if (currentMode.equalsIgnoreCase("disaster_mode") && (previousMode.equalsIgnoreCase("consumption_mode") || previousMode.equalsIgnoreCase("trade_mode"))) {
                                        chgVol = "0.1";
                                        logger.info("ctrlOff, Mode change to disaster_mode. set initial chgVol : "+ chgVol);
                                    }

                                    // add chgVol set to meas
                                }


                                if (chgVol != null) {
                                    if ((previousChgVol == null)  || (!(previousChgVol.equals(chgVol)))) {
                                        logger.info("set previousChgVol : " + previousChgVol + " -> "+ chgVol);

                                        this.previousChgVol = chgVol;
                                        /**
                                         * ned to confirm
                                         */
//                                        try {
//                                            logger.info("measIpAddress " + remoteIpAddress);
//                                            Registry registry = LocateRegistry.getRegistry(remoteIpAddress);		//  measIpAddress
//                                            ISnsrCtlOpeRmi stub = (ISnsrCtlOpeRmi) registry.lookup(ISnsrCtlOpeRmi.APP_REGISTRY_BIND_NAME);
//                                            stub.updateNightCharging(chgVol);
//                                            isSendChargeVolFailed = false;
//                                        } catch (RemoteException | NotBoundException e) {
//                                            isSendChargeVolFailed = true;
//                                            logger.info("RemoteException | NotBoundException : "+ e.getMessage());
//                                            e.printStackTrace();
//                                        }
                                    }
                                }

                                // chgVol Adjust End

                                if(cmd.length() > 0) {
                                    storageBatteryClass.setC()
                                            .reqSetOperationModeSetting(cmd).send();
                                }
                            } catch (Exception ex) {
                                logger.warn("ctrlOff, Exception *** {}", ex.toString());
                                logger.warn("送信エラー:EOJ(" + storageBatteryClass.getStrEojCode() + "), e=" + ex.getMessage());
                                ex.printStackTrace();
                            }
                        }


                        if (previousMode.equalsIgnoreCase("disaster_mode") &&  currentMode.equalsIgnoreCase("consumption_mode")) {
                            cmd = "46";
                            logger.info("ctrlOff, Mode change from disaster_mode to consumption_mode. set cmd : {}", cmd);
                        }else if(previousMode.equalsIgnoreCase("disaster_mode") &&  currentMode.equalsIgnoreCase("trade_mode")) {
                            if (isSmaPcs) {
                                cmd = "44";
                                logger.info("ctrlOff, SmaPcs, Mode change from disaster_mode to trade_mode. set cmd : {}", cmd);
                            }
                        }

                        previousMode = currentMode;
                    }
                }else {
                    logger.info("currentMode : {}", currentMode);
                }
            }
        }

        // test spdbm  //run incase of dynamic mode and spdbm == true case // check pdbm class?
        // above pdbm will fetch from ap0 or wlan0, spdbm and meas ip will differ
        if (Settings.sPdbm.equalsIgnoreCase("true") && Settings.networkMode.equalsIgnoreCase("dynamic")) {
            if (map.containsKey(spdbmRemoteIpaddress) && (map.get(spdbmRemoteIpaddress) != null)) {
                ElOneNode node = map.get(spdbmRemoteIpaddress);
                logger.info("measurementModule spdbm Host:[{}]", node.getIpAddress());

                List<DeviceObjectSuperClass> deviceClassList = node.getDeviceEojList();

                if (deviceClassList.size() > 0) {
                    isFindRemoteNode = true;

                    CountDownLatch doneSignal = new CountDownLatch(deviceClassList.size());
                    for (DeviceObjectSuperClass deviceClass : deviceClassList) {

                        try {
                            // 機器クラス毎に送信メッセージをわける
                            switch (deviceClass.getStrClassGroupCode() + deviceClass.getStrClassCode()) {

                                case PowerDistributionBoardMetering.CLASS_GROUP_CODE + PowerDistributionBoardMetering.CLASS_CODE: {
                                    logger.info("inside PowerDistributionBoardMetering spdbm");
                                    doneSignal.countDown();

                                    PowerDistributionBoardMetering device = (PowerDistributionBoardMetering) deviceClass;

                                    device.get()
                                            .reqGetOperationStatus()
                                            .reqGetUnitForCumulativeElectricEnergy()
                                            .reqGetMeasurementChannel1()
                                            .reqGetMeasurementChannel2()
                                            .reqGetMeasurementChannel3()
                                            .reqGetMeasurementChannel4()
                                            .reqGetMeasurementChannel5()
                                            .send();
                                    break;
                                }

                                default:
                                    doneSignal.countDown();
                                    break;
                            }
                        } catch (Exception ex) {
                            logger.warn("送信エラー:EOJ(" + deviceClass.getStrEojCode() + "), e=" + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                }else {
                    logger.info("measurementModule spdbm deviceClassList : {}", deviceClassList);
                }
            }else {
                Calendar cal = Calendar.getInstance();
                int min = cal.get(Calendar.MINUTE);
                if((min == 0) || (min %2 == 0)) {
                    isFindRemoteNode = false;
                    try {
                        logger.info("!!! measurementModule dynamic mode spdbm not found, send D5 map : " + map.keySet());
                        NodeProfileClass.infReqAll(process).reqInfInstanceListNotification().send();
                    } catch (Exception e) {
                        logger.info("measurementModule Service spdbm scanning error : "+ e.getMessage());
                        e.printStackTrace();
                    }
//					logger.info("measurementModule spdbm isFindRemoteNode : " + isFindRemoteNode);
                }
            }
        }

        if (!isFindRemoteNode) {
            // 他ノードが見つからなかったらEL通信網から他ノード取得する
            try {
                logger.info("measurementModule isFindRemoteNode send D6, scanning");
                NodeProfileClass.getAll(process)
                        .reqGetOperationStatus()
                        .reqGetIdentificationNumber()
                        .reqGetSelfNodeInstanceListS()
                        .send();
                NodeProfileClass.getAll(process)
                        .reqGetGetPropMap()
                        .send();
            } catch (Exception e) {
                logger.warn("送信エラー:" + e.getMessage());
            }
        }
        logger.info("MeasurementModuleService 毎分実行処理終了");
    }
    public static int checkTime(int ichargingEnd, int minute) {  //minute range 1~59
        int time = 0;
        if (Integer.valueOf(ichargingEnd) != null ) {
            String addedTime = String.valueOf(ichargingEnd);
            while (addedTime.length() < 4) {
                addedTime  = 0 + addedTime ;
            }
            int firstDigit = Integer.valueOf(addedTime.substring(0,2));
            int lastTwoDigit = Integer.valueOf((addedTime.substring(addedTime.length() - 2)));

            int lastDigit = (lastTwoDigit + minute) % 60;
            firstDigit = (firstDigit + (int)(lastTwoDigit + minute) / 60) % 24;

            time = Integer.valueOf(String.format("%02d", firstDigit) + String.format("%02d", lastDigit));
        }
        return time;
    }


    private void setPcsMode(String currentMode) {
        logger.info("setPcsMode {} start", currentMode);
        List<DeviceMaster> list = null;
        DeviceMaster device = null;
        try {
//            Registry registry = LocateRegistry.getRegistry(remoteIpAddress);
//            ISnsrCtlOpeRmi stub = (ISnsrCtlOpeRmi) registry.lookup(ISnsrCtlOpeRmi.APP_REGISTRY_BIND_NAME);
//            if (stub == null) {
//                logger.info("stub null. setPcsMode to "+currentMode+" Error");
//            }

            list = deviceMasterTableController.findAll();
            if(list != null && list.size() > 0) {
                for( DeviceMaster it : list ) {
                    if( it.getPcstypeMaster().getId() == 1) {
                        device = it;
                    }
                }
            }

            if (device != null) {
                logger.info("fetch device_master complete. setPcsMode : {}", currentMode);
                int companyId = device.getCompanyMaster().getId();
                if (companyId == 2) {
                    int register = 33220; 	//SUNGROW use case only
                    int[] valsSungrow = null;
                    if (currentMode.equalsIgnoreCase("consumption_mode")) {
                        valsSungrow = new int[]{0};
                    }else if( currentMode.equalsIgnoreCase("trade_mode")) {
                        valsSungrow = new int[]{9};
                    }
//                    stub.setRegister(device.getIpAddress(), companyId, device.getId(), register, valsSungrow);
                    logger.info("setPcsMode : "+currentMode+" end");
                }
            }
        } catch (Exception e3) {
            logger.info("Exception setPcsMode {}", e3.getMessage());
            e3.printStackTrace();
        }
    }

    public synchronized void excuteByEveryThirtyMinute() {
        logger.info("Running excuteByEveryThirtyMinute");
        if (!isConnected) {
            return;
        }

        Map<String, ElOneNode> map = process.getRemoteNodeMap();
        if (map.containsKey(locaIpAddress) && (map.get(locaIpAddress) != null)) {
            ElOneNode node = map.get(locaIpAddress);

            Calendar cal = Calendar.getInstance();
            int min = cal.get(Calendar.MINUTE);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            if (min <= 30) {
                cal.set(Calendar.MINUTE, 0);
            }else {
                cal.set(Calendar.MINUTE, 30);
            }
            Date checkTime = cal.getTime();
            Timestamp timestamp = new Timestamp(checkTime.getTime());

            OutputControl oc = null;  //findByDatetime>>findByDatetimeOrderByIdDesc
            try {
                oc = (OutputControl) outputControlController.findFirstByDatetimeOrderByIdDesc(timestamp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String controlValue = "100";

            if (oc != null) {
                controlValue = oc.getControlValue();
                logger.info("OutputControlSchedule : True controlValue : {}", controlValue);
            }

            List<DeviceObjectSuperClass> deviceClassList = node.getDeviceEojList();
            if (deviceClassList.size() > 0) {
                CountDownLatch doneSignal = new CountDownLatch(deviceClassList.size());
                for (DeviceObjectSuperClass deviceClass : deviceClassList) {
                    try {
                        switch (deviceClass.getStrClassCode()) {

                            case HouseholdSolarPowerGenerationClass.CLASS_CODE: {
                                doneSignal.countDown();
                                HouseholdSolarPowerGenerationClass device = (HouseholdSolarPowerGenerationClass) deviceClass;
                                device.setC().reqSetOutputLimitSetting1(controlValue)
                                        .send();
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        logger.warn("送信エラー:EOJ(" + deviceClass.getStrEojCode() + "), e=" + ex.getMessage());
                    }
                }

                //蓄電池からの情報が取得できたら蓄電池へ制御を行う。
                try {
                    doneSignal.await(5, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    logger.warn("蓄電池取得待ち中エラー, e=" + ex.getMessage());
                }
            }
        }
        logger.info("excuteByEveryThirtyMinute end");
    }

    /**
     * 毎時実行処理.
     */

    public synchronized void excuteByEveryHour() throws SQLException {
        if (isConnected) {
            LocalDateTime ldt = LocalDateTime.now();
            logger.info("毎時実行処理:{}", ldt);

            // 1時間事にデータベースに書き込みを行う。

            for (ReportProcessorInterface reportProcessor : reportProcessorList) {
                reportProcessor.writeDatabase();
            }
        }
    }

    /**
     * 接続対象のリモートノードかチェックする.
     *
     * @param node ノード
     * @return 接続対象かどうか
     */
    private boolean isTargetNode(ElOneNode node) {
        if (remoteIpAddress!= null &&!remoteIpAddress.isEmpty()) {
            return node.getIpAddress().equals(remoteIpAddress);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onNewNodeFound(ElOneNode node) {
        if (!isTargetNode(node)) {
            // 接続対象でなければ無視する.
            return;
        }
        logger.info("他ノード発見：{}", node.getIpAddress());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onNewHouseholdSolarPowerGenerationFound(ElClassBase eoj) {
        if (!isTargetNode(eoj.getNodeBelongsTo())) {
            // 接続対象でなければ無視する.
            return;
        }
        HouseholdSolarPowerGenerationReportProcessor reportProcessor = new HouseholdSolarPowerGenerationReportProcessor();
        reportProcessor.deviceMasterTableController = new DeviceMasterTableController();
        reportProcessor.edgeSolarPowerTableController = new EdgeSolarPowerTableController();
        reportProcessorList.add(reportProcessor);
        eoj.setReportProcessor(reportProcessor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onNewStorageBatteryFound(ElClassBase eoj) {
        if (!isTargetNode(eoj.getNodeBelongsTo())) {
            // 接続対象でなければ無視する.
            return;
        }
        StorageBatteryReportProcessor reportProcessor = new StorageBatteryReportProcessor();
        reportProcessor.controlMasterTableController = new ControlMasterTableController();
        reportProcessor.deviceMasterTableController = new DeviceMasterTableController();
        reportProcessor.edgeBatteryChargeTableController = new EdgeBatteryChargeTableController();
        reportProcessor.edgeBatteryCumulativeChargeTableController = new EdgeBatteryCumulativeChargeTableController();
        reportProcessor.powerControlResultTableController = new PowerControlResultTableController();
        reportProcessor.configInfoTableController = new ConfigInfoTableController();
        reportProcessorList.add(reportProcessor);
        eoj.setReportProcessor(reportProcessor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onNewLowVoltageSmartMeterFound(ElClassBase eoj) {
        System.out.println("measurementModule onNewLowVoltageSmartMeterFound");
        if (!isTargetNode(eoj.getNodeBelongsTo())) {
            // 接続対象でなければ無視する.
            return;
        }
        reportProcessorList.add(lowVoltageSmartMeterReportProcessor);
        eoj.setReportProcessor(lowVoltageSmartMeterReportProcessor);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public synchronized void onNewPowerDistributionBoardMeterFound(ElClassBase eoj) {
        logger.info("measurementModule onNewPowerDistributionBoardMeterFound");

        if (Settings.sPdbm.equalsIgnoreCase("true") && Settings.networkMode.equalsIgnoreCase("dynamic")) {
            spdbmRemoteIpaddress = eoj.getNodeBelongsTo().getIpAddress();
            logger.info("measurementModule add onNewPowerDistributionBoardMeterFound spdbmRemoteIpaddress : {}", spdbmRemoteIpaddress);
            reportProcessorList.add(powerDistributionBoardMeterReportProcessor);
            eoj.setReportProcessor(powerDistributionBoardMeterReportProcessor);
            logger.info("measurementModule add onNewPowerDistributionBoardMeterFound test add reportProcessor");
        }
        if (!isTargetNode(eoj.getNodeBelongsTo())) {
            // ignore if not connected
            // 接続対象でなければ無視する.
            logger.info("measurementModule add onNewPowerDistributionBoardMeterFound isNotTargetNode IpAddress : {}", eoj.getNodeBelongsTo().getIpAddress());
            smartPowerDistributionBoardMeterService.setSpdbmRemoteIpAddress(eoj.getNodeBelongsTo().getIpAddress());
            logger.info("measurementModule onNewPowerDistributionBoardMeterFound isNotTargetNode return");
            return;
        }

        logger.info("measurementModule add onNewPowerDistributionBoardMeterFound");
        reportProcessorList.add(powerDistributionBoardMeterReportProcessor);
        eoj.setReportProcessor(powerDistributionBoardMeterReportProcessor);
    }

    private String getCurrentMode() {
        String Modzz = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode top = mapper.readTree(new File("/opt/smartedge/data/resources/CldComApp/deviceShadow.txt"));
            JsonNode edgedevicesNode = top.path("edge_devices").get(0);
            String curr_mod = edgedevicesNode.path("exec_mode").path(JSON_CURRENT_MODE).asText();
            Modzz = curr_mod;
        } catch (IOException e) {
            logger.info("IOException Occured. DeviceShadow file not Found or Unreadable || " + e.getMessage());
        } catch( Exception e2) {
            logger.info("Exception Occured. " + e2.getMessage());
        }
        return Modzz;
    }

    private int getBtMin(String mode) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode top = mapper.readTree(new File("/opt/smartedge/data/resources/CldComApp/deviceShadow.txt"));
            JsonNode pcss = top.path("pcss").get(0);
            if (pcss != null) {
                if (mode.equalsIgnoreCase("consumption_mode")) {
                    btMin = pcss.path("bt_min").path("consumption").asInt();
                    logger.info("btMin consumption : " + btMin);
                }else if (mode.equalsIgnoreCase("trade_mode")) {
                    btMin = pcss.path("bt_min").path("trade").asInt();
                    logger.info("btMin trade : " + btMin);
                }
            }
        } catch (IOException e) {
            logger.info("IOException Occured. DeviceShadow file not Found or Unreadable || " + e.getMessage());
        } catch( Exception e2) {
            logger.info("Exception Occured. " + e2.getMessage());
        }
        return btMin;
    }

    public boolean checkPcsType() throws SQLException {
        List<DeviceMaster> list = null;
        list = deviceMasterTableController.findAll();
        if ((list != null) && (list.size() > 0)) {
            Integer companyId = list.get(1).getCompanyMaster().getId();
            if (companyId == 1 || companyId.equals(1)) {
                logger.info("isSmaPcs : True");
                return true;
            }
        }
        logger.info("isSmaPcs : False");
        return false;
    }

    public String findDeviceIP(String networkInterface) {
        logger.info("start findDeviceIp");
        String ipAddr = "";
        try {
            Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface nif = nifs.nextElement();
                Enumeration<InetAddress> addresses = nif.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address) { 	// only care about IPv4 addresses
                        logger.info("Network Card Interface Name:" + nif.getName());
                        logger.info("Network card interface address:" + addr.getHostAddress());
                        if (nif.getName().equalsIgnoreCase(networkInterface)) {
                            ipAddr = addr.getHostAddress();
                            break;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        logger.info("return ipAddr : " + ipAddr);
        return ipAddr;
    }

}
