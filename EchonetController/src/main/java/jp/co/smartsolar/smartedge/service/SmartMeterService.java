package jp.co.smartsolar.smartedge.service;

import jp.co.smartsolar.smartedge.component.Settings;
import jp.co.smartsolar.smartedge.database.controller.*;
import jp.co.smartsolar.smartedge.database.entity.PowerControlResult;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElOneNode;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.channel.wisun.ChannelInfo;
import jp.co.smartsolar.smartedge.echonetlite.channel.wisun.ElWiSunChannel;
import jp.co.smartsolar.smartedge.echonetlite.channel.wisun.WiSunDriverListener;
import jp.co.smartsolar.smartedge.echonetlite.channel.wisun.WiSunPan;
import jp.co.smartsolar.smartedge.echonetlite.devices.ControllerClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.LowVoltageSmartMeter;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;
import jp.co.smartsolar.smartedge.echonetlite.protocol.ElProtocol;
import jp.co.smartsolar.smartedge.eoj.NodeProfileClassImpl;
import jp.co.smartsolar.smartedge.eoj.reportprocessor.HouseholdSolarPowerGenerationReportProcessor;
import jp.co.smartsolar.smartedge.eoj.reportprocessor.ReportProcessorInterface;
import jp.co.smartsolar.smartedge.eoj.reportprocessor.LowVoltageSmartMeterReportProcessor;


import jp.co.smartsolar.smartedge.eoj.reportprocessor.StorageBatteryReportProcessor;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @package jp.co.smartsolar.smartedge.service
 * @Author subohaju
 * @date 5/10/2024
 */
public class SmartMeterService extends ElProcess.ElEventListener {
    private final Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.LogService");


    private ElProcess process;

    private final String INSTANCE_CODE = "C1";

    private boolean isConnected;
    private String remoteIpAddress;

    private List<ReportProcessorInterface> reportProcessorList = Collections.synchronizedList(new ArrayList<ReportProcessorInterface>());
    public static boolean isSmIdPassPresent = false;

    private WiSunDriverListener wiSunDriverListener = null;

    private static LinkedBlockingQueue<String> responseQueue = new LinkedBlockingQueue<String>();
    private String brouteChannel;
    private static String panId;
    private static String remoteIp;
    private static boolean retryReconnection = false;


    public void scan() throws Exception{
        logger.info("SmartMeterService scan()");
        String wisunSerialPort = "";
        ElWiSunChannel channel = new ElWiSunChannel(wisunSerialPort);
        WiSunDriverListenerForScan listener = new WiSunDriverListenerForScan();
        channel.addWiSunDriverListener(listener);
        channel.scaningSequence();
    }

    public synchronized void connect() throws Exception {
            logger.info("SmartMeter(WiSUN)接続開始");
            if (!isConnected) {
                //TODO:SmartMeterの初期設定の方法が未決定のため未実装
                //[1] brouteId
                //[2] broutePwd
                //[3] brouteChannel
                //[4] panId
                //[5] remoteIp = IPv6

                remoteIpAddress = Settings.smIpAddress;
                // Bルート接続情報をセット
                String brouteId = Settings.smBrouteId;
                String broutePwd = Settings.smBroutePwd;

                // Bルート接続処理
                brouteChannel = Settings.smBrouteChannel;
                panId = Settings.smPanId;
                remoteIp = remoteIpAddress;

                String wisunSerialPort = Settings.smWisunSerialPort;
                String pairingId = "";
                if( "".equals(brouteId) || brouteId == null
                        || "".equals(broutePwd) || broutePwd == null
//            		|| "".equals(brouteChannel) || brouteChannel == null
//            		|| "".equals(panId) || panId == null
//            		|| "".equals(remoteIp) || remoteIp == null
//            		|| "".equals(wisunSerialPort) || wisunSerialPort == null
                ) {
                    logger.info("SmartMeter(WiSUN) 設定情報が不足");
                    return;
                }else {
                    logger.info("SmartMeter(WiSUN) Id and Password present");
                    isSmIdPassPresent = true;
                    LowVoltageSmartMeterReportProcessor.isSmartMeter = true;
                }

                ElProcess.ElEventListener wisunElEventListener = this;
                ElWiSunChannel channel = new ElWiSunChannel(wisunSerialPort);
                WiSunDriverListenerForScan listener = new WiSunDriverListenerForScan();
                channel.addWiSunDriverListener(listener);
                logger.info("sm connect start");
                if (wiSunDriverListener == null) {
                    wiSunDriverListener = new WiSunDriverListener() {
                        @Override
                        public void onEvent(int event, String sender) {
                            if(event == 0x25) {
                                // PANAによる接続が完了した
                                try {
                                    process = ElProcess.generate(channel, new NodeProfileClassImpl(),new DeviceObjectSuperClass[] {new ControllerClass(INSTANCE_CODE)})
                                            .initEventList()
                                            .addEventListenerToList(wisunElEventListener)
                                            .addEventListenerToList(new ElProcess.ElEventLogger())
//                                .addEventListenerToList(eventListenerDefaultLogger) // 送受信のモニタリング用
//                                .addEventListenerToList(eventListenerLoggerForController) // 受信確認用
//                                .addEventListenerToList(eventListenerPresetLogger) // プリセットロガー
                                            .start();

//                                // クライアントからの操作対象としてアクティベート
//                                changeActiveProcess(process);

                                    logger.info("sm event = 25, create sm process and set isConnected = true");
                                    logger.info("SmartMeter(WiSUN)と接続成功");
                                    isConnected = true;

                                    if (process != null) {
                                        NodeProfileClass.infReqAll(process).reqInfInstanceListNotification().send();		//commented for now 20220304
                                        logger.info("************ SmartMeter send reqInfInstanceListNotification");
                                    }

                                    // save to db if connection succeeds TODO
                                    // is reconnection possible without scanning if credentials are known

                                } catch (Exception e) {
                                    logger.info("ElProcess起動失敗!");
                                }
                            } else if (event == 0x24) {
                                isConnected = false;
                                logger.info("PANA接続失敗!");

                                //retry reconnection only once if connection fails
                                if (retryReconnection) {
                                    logger.info("Retry reconnection to smartmeter");
                                    retryReconnection = false;
                                    channel.connectBRoute(brouteChannel, panId, remoteIp, false);
                                }
                            }
                        }

                        @Override
                        public void onReceiveUdp(String srcIpAddr, String dstIpAddr, int srcPort, int dstPort, String srcMacAddr,
                                                 String data) {}
                        @Override
                        public void receiveEaddrPackets(int mode, String[] data) {}
                        @Override
                        public void receivePan(WiSunPan pan) {  }
                        @Override
                        public void receiveChannelInfo(ChannelInfo[] info) {}
                        @Override
                        public void receivePong(String ipAddr) {}
                        @Override
                        public void onCompleteActiveScan(String ipAddr) {}
                        @Override
                        public void onCompleteEDScan(String ipAddr) {}
                        @Override
                        public void onCompleteSendUdp(String ipAddr, String param) {}
                        @Override
                        public void onCatchException(Exception ex) {}
                    };
                }
                channel.addWiSunDriverListener(wiSunDriverListener);

//            broutePwd ="0123456789AB";
                logger.info("sm setBroutePwd : {}", broutePwd);
                channel.setBroutePwd(broutePwd);

//            brouteId ="00112233445566778899AABBCCDDEEFF";
                logger.info("sm setBrouteId : {}", brouteId);
                channel.setBrouteId(brouteId);

                logger.info("sm channel.scaningSequence()");
                channel.scaningSequence();		//startActiveScan

//            WiSunDriverListenerForScan ws = new WiSunDriverListenerForScan();
                int count = 0;
                int scanCount = 0;
                List<List<String>> plist = WiSunDriverListenerForScan.panList;
                logger.info("plist.size() : {}", plist.size());
                while (true) {
                    if(plist.size() != 0) {
                        for (List<String> e : plist) {
                            String s = String.join(",", e);
                            logger.info("WiSunDriverListenerForScan receivePan plist : {}", s);

                            //all values are in hex
                            brouteChannel = e.get(0);		//channel
//            			page = e.get(1);				//page
                            panId = e.get(2);				//panId
//            			macAddr = e.get(3);				//macAddr
//            			rssi = e.get(4);				//rssi ~ lqi
                            pairingId = e.get(5);			//pairingId
                            remoteIp = e.get(6);			//remoteIpAddress IPV6?
//            			remoteIp = "FE80:0000:0000:0000:1007:23FF:FEA0:A1CB";

                            logger.info("brouteChannel : {}", brouteChannel);
                            logger.info("panId : {}", panId);
                            logger.info("remoteIp : {}", remoteIp);
                            logger.info("pairingId : {}", pairingId);
//            			logger.info("macAddr : " + macAddr);
                        }
                        break;
                    }else {
                        // restart scanning sequence
                        count++;
                        if (count%20==0) {
                            channel.scaningSequence();
                            logger.info("restart scanning sequence : " +  scanCount);
                            count = 0;
                            scanCount++;
                        }
                        if (scanCount >= 10) {
                            scanCount = 0;
                            logger.info("Cannot find device, EXIT");
                            return;
                        }

                    }
//            	logger.info("Sleep thread for 1 sec");
                    Thread.sleep(1000);
                }
                logger.info("test list fetch complete plist:{}", plist.size());

                remoteIpAddress = remoteIp;
                channel.connectBRoute(brouteChannel, panId, remoteIp, false);		//remoteIp		pairingId
                retryReconnection = true;

//            channel.connectBRoute(brouteChannel, panId, remoteIp, true);
//          logger.info("sm isConnected = true");
//			isConnected = true;
            }
            logger.info("SmartMeter(WiSUN)接続開始処理完了");

            // 他ノードへインスタンスリスト通知(0xD5)を要求
//		logger.info("process : " + (process != null));
//		if (process != null) {
//			NodeProfileClass.infReqAll(process).reqInfInstanceListNotification().send();		//commented for now 20220304
//			logger.info("SmartMeter send reqInfInstanceListNotification");
//		}

    }
    private void changeActiveProcess( ElProcess elprocess ) {
        this.process = elprocess;
    }


    /**
     * EchonetLite停止
     *
     * @throws Exception
     */
    public synchronized void disconnect() throws Exception {
        if (isConnected) {
            process.stop();
            process.initEventList();
            process = null;
            isConnected = false;
        }
    }

    /**
     * 毎分実行処理.
     */
    public synchronized void excuteByEveryMinute() {
        if (!isConnected) {
            return;
        }
        LocalDateTime ldt = LocalDateTime.now();
        logger.info("SmartMeter 毎分実行処理:{}", ldt);

        boolean isFindRemoteNode = false;
        Map<String, ElOneNode> map = process.getRemoteNodeMap();
//		logger.info("SmartMeter map : " + map);
//		logger.info("SmartMeter remoteIpAddress : " + remoteIpAddress);
//		logger.info("SmartMeter map.containsKey(remoteIpAddress) : " + map.containsKey(remoteIpAddress));

        if (map.containsKey(remoteIpAddress) && (map.get(remoteIpAddress) != null)) {
            ElOneNode node = map.get(remoteIpAddress);
//			logger.info("Host:[" + node.getIpAddress() + "]");

            // 現在のカタログ制御を取得
            Date now = DateUtils.truncate(new Date(), Calendar.MINUTE);
            Timestamp timestamp = new Timestamp(now.getTime());

            List<LowVoltageSmartMeter> vlsmList = new ArrayList<LowVoltageSmartMeter>();
            List<DeviceObjectSuperClass> deviceClassList = node.getDeviceEojList();
            if (deviceClassList.size() > 0) {
                isFindRemoteNode = true;

                CountDownLatch doneSignal = new CountDownLatch(deviceClassList.size());
                for (DeviceObjectSuperClass deviceClass : deviceClassList) {
                    try {
                        // 機器クラス毎に送信メッセージをわける
                        switch (deviceClass.getStrClassCode()) {
                            // 低圧スマートメータ
                            case LowVoltageSmartMeter.CLASS_CODE: {
                                doneSignal.countDown();

                                LowVoltageSmartMeter device = (LowVoltageSmartMeter) deviceClass;

                                LowVoltageSmartMeterReportProcessor reportProcessor  = (LowVoltageSmartMeterReportProcessor) device.getReportProcessor();
                                if (reportProcessor != null) {
                                    vlsmList.add(device);
                                    // CountDownLatchを設定し蓄電残量3と運転動作設定を要求する
                                    reportProcessor.setCountDownLatch(doneSignal);
                                } else {
                                    doneSignal.countDown();
                                }

                                device.get()
                                        .reqGetOperationStatus()		// 0x80
                                        .reqGetIntegralPowerConsumptionUnit()
                                        .reqGetIntegralPowerConsumptionMeasuredValuesPositive() // 積算電力量計測値(正方向計測値)   0xE0
                                        .reqGetIntegralPowerConsumptionMeasuredValuesReverse()  // 積算電力量計測値(逆方向計測値)   0xE3
//								.reqGetIntegralPowerConsumptionMeasuredValuesLog1Positive()		// E2 1single day
//								.reqGetIntegralPowerConsumptionMeasuredValuesLog1Reverse()		// E4 1single day

//								.reqGetIntegralPowerConsumptionLogCollectionDay1()		// E5 get data of different data //set?
                                        .send();
                                break;
                            }

                            default:
                                doneSignal.countDown();
                                break;
                        }
                    } catch (Exception ex) {
                        logger.warn("送信エラー:EOJ({}), e={}", deviceClass.getStrEojCode(), ex.getMessage());
                    }
                }

            }
        }

        if (!isFindRemoteNode) {
            // 他ノードが見つからなかったらEL通信網から他ノード取得する
            try {
                NodeProfileClass.getAll(process)
                        .reqGetOperationStatus()
                        .reqGetIdentificationNumber()
                        .reqGetSelfNodeInstanceListS()
                        .send();
                NodeProfileClass.getAll(process)
                        .reqGetGetPropMap()
                        .send();
            } catch (Exception e) {
                logger.warn("送信エラー:{}", e.getMessage());
            }
        }

    }

    /**
     * 毎時実行処理.
     */
    public synchronized void excuteByEveryHour() throws SQLException {
        if (isConnected) {
            // 1時間事にデータベースに書き込みを行う。		save to db every min
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
        logger.info("Wisun isTargetNode : {}", node.getClass());
        if (remoteIpAddress == null || remoteIpAddress.isEmpty()) {
            return true;
        }
        return node.getIpAddress().equals(remoteIpAddress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onNewNodeFound(ElOneNode node) {
        logger.info("Wisun onNewNodeFound : {}", node.getIpAddress());
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
        logger.info("Wisun onNewHouseholdSolarPowerGenerationFound : {}", eoj.getEojCodeStructureFormatWithTitle());
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
        logger.info("Wisun onNewStorageBatteryFound : {}", eoj.getEojCodeStructureFormatWithTitle());
        if (!isTargetNode(eoj.getNodeBelongsTo())) {
            // 接続対象でなければ無視する.
            return;
        }
        StorageBatteryReportProcessor reportProcessor = new StorageBatteryReportProcessor();
        reportProcessor.controlMasterTableController = new ControlMasterTableController();
        reportProcessor.deviceMasterTableController = new DeviceMasterTableController();
        reportProcessor.edgeBatteryChargeTableController = new EdgeBatteryChargeTableController();
        reportProcessor.powerControlResultTableController = new PowerControlResultTableController();
        reportProcessorList.add(reportProcessor);
        eoj.setReportProcessor(reportProcessor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onNewLowVoltageSmartMeterFound(ElClassBase eoj) {
        logger.info("Wisun onNewLowVoltageSmartMeterFound : {}", eoj.getEojCodeStructureFormatWithTitle());
        if (!isTargetNode(eoj.getNodeBelongsTo())) {
            // 接続対象でなければ無視する.
            return;
        }
        LowVoltageSmartMeterReportProcessor reportProcessor = new LowVoltageSmartMeterReportProcessor();
        reportProcessor.smartmeterMasterTableController = new SmartmeterMasterTableController();
        reportProcessor.edgeSystemPowerTableController = new EdgeSystemPowerTableController();

        reportProcessorList.add(reportProcessor);
        eoj.setReportProcessor(reportProcessor);
        logger.info("Wisun　set reportProcessor LVSM");
    }

    protected static class WiSunDriverListenerForScan implements WiSunDriverListener {

        /** スキャン結果のPAN情報をリストに保持する . */
        private static final List<List<String>> panList = new ArrayList<List<String>>();
        private final Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.LogService");

        private static final Map<Integer, String> eventMap = new HashMap<Integer, String>() {
            {
                put(0x05, "Echo Requestを受信した。");
                put(0x20, "Beaconを受信した。");
                put(0x24, "PANAセッション接続失敗。");
                put(0x25, "PANAセッション接続完了。");
                put(0x26, "相手側からセッション終了要求を受信した。");
                put(0x27, "PANAセッション終了成功。");
                put(0x28, "PANAセッション終了要求タイムアウト。");
                put(0x29, "セッションのライフタイムが経過して期限切れになった。");
                put(0x32, "ARIB108の送信総和時間の制限が発動した。");
                put(0x33, "送信総和時間の制限が解除された。");
            }
        };

        @Override
        public void onEvent(int event, String sender) {
            if (eventMap.containsKey(event)) {
                responseQueue.offer(eventMap.get(event));
                logger.info("WiSunDriverListenerForScan onEvent :" + eventMap.get(event));
            }
        }

        @Override
        public void onReceiveUdp(String srcIpAddr, String dstIpAddr, int srcPort, int dstPort, String srcMacAddr, String data) {
            if (dstPort == ElProtocol.ECHONET_PORT) {
                responseQueue.offer("UDP受信:" + data);
                logger.info("WiSunDriverListenerForScan onEvent :" + "UDP受信:" + data);
            }
        }

        @Override
        public void receiveEaddrPackets(int mode, String[] data) {
        }

        @Override
        public void receivePan(WiSunPan pan) {
            List<String> elems = new ArrayList<String>();
            elems.add(Integer.toHexString(pan.getChannel()).toUpperCase());
            elems.add(Integer.toHexString(pan.getChannelPage()).toUpperCase());
            elems.add(Integer.toHexString(pan.getPanId()).toUpperCase());
            elems.add(pan.getMacAddress());
            elems.add(Integer.toHexString(pan.getLqi()).toUpperCase());
            elems.add(pan.getPairId());
            elems.add(pan.getIpAddress());
            panList.add(elems);// 一覧情報の管理配列に追加
            logger.info("*** Added to panlist ***");

            //remove all below added comments 20220308
//            for (List<String> e : panList) {
//                String s = String.join(",", e);
//                responseQueue.offer("EELPS_RECEIVE_PAN " + s);
//                response.add(s);
//                logger.info("WiSunDriverListenerForScan receivePan : " + s);
//            }
        }

        @Override
        public void receiveChannelInfo(ChannelInfo[] info) {
            logger.info("WiSunDriverListenerForScan receiveChannelInfo : {}", info);
        }

        @Override
        public void receivePong(String ipAddr) {
            responseQueue.offer("ICMP Echo replyを受信した。");
            logger.info("WiSunDriverListenerForScan receivePong ICMP Echo replyを受信した。 : {}", ipAddr);
        }

        @Override
        public void onCompleteActiveScan(String ipAddr) {
            for (List<String> elems : panList) {
                String strPanList = String.join(",", elems);
                responseQueue.offer("EELPS_RECEIVE_PAN " + strPanList);
                logger.info("WiSunDriverListenerForScan onCompleteActiveScan  EELPS_RECEIVE_PAN : {}", strPanList);
            }
            panList.clear();
            responseQueue.offer("アクティブスキャンが完了した。[" + ipAddr + "]");
            logger.info("WiSunDriverListenerForScan onCompleteActiveScan :アクティブスキャンが完了した。[{}]", ipAddr);
        }

        @Override
        public void onCompleteEDScan(String ipAddr) {
            responseQueue.offer("EDスキャンが完了した。[" + ipAddr + "]");
            logger.info("WiSunDriverListenerForScan onCompleteEDScan EDスキャンが完了した。[{}]", ipAddr);
        }

        @Override
        public void onCompleteSendUdp(String ipAddr, String param) {
            if (param != null) {
                if (param.equals("01")) {
                    responseQueue.offer("UDPの送信に失敗。[" + ipAddr + "]");
                    logger.info("UDPの送信に失敗。[{}]", ipAddr);
                } else  if (param.equals("02")) {
                    responseQueue.offer("UDPを送信する代わりにアドレス要請を行った。[" + ipAddr + "]");
                    logger.info("UDPを送信する代わりにアドレス要請を行った。[{}]", ipAddr);
                } else {
                    responseQueue.offer("UDPの送信に成功。[" + ipAddr + "]");
                    logger.info("UDPの送信に成功。[{}]", ipAddr);
                }
//                logger.info("WiSunDriverListenerForScan onCompleteSendUdp param: "+ param + ", ipaddr : [" + ipAddr + "]");
            }
        }

        @Override
        public void onCatchException(Exception ex) {
        }

    }


}
