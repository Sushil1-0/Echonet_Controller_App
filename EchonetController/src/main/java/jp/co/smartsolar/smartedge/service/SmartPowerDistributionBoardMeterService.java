package jp.co.smartsolar.smartedge.service;

import jp.co.smartsolar.smartedge.database.controller.DeviceMasterTableController;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElOneNode;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.channel.ElChannelBase;
import jp.co.smartsolar.smartedge.echonetlite.channel.udp.ElUdpChannel;
import jp.co.smartsolar.smartedge.echonetlite.devices.ControllerClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.PowerDistributionBoardMetering;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;
import jp.co.smartsolar.smartedge.eoj.NodeProfileClassImpl;
import jp.co.smartsolar.smartedge.eoj.reportprocessor.PowerDistributionBoardMeterReportProcessor;
import jp.co.smartsolar.smartedge.eoj.reportprocessor.ReportProcessorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @package jp.co.smartsolar.smartedge.service
 * @Author subohaju
 * @date 5/14/2024
 */
public class SmartPowerDistributionBoardMeterService extends ElProcess.ElEventListener{
    private final Logger logger = LoggerFactory.getLogger("jp.co.smartsolar.smartedge.service.LogService");
    DeviceMasterTableController deviceMasterTableController = new DeviceMasterTableController();
    PowerDistributionBoardMetering powerDistributionBoardMetering = new PowerDistributionBoardMetering();
    private ElProcess spdmElProcess;
    PowerDistributionBoardMeterReportProcessor powerDistributionBoardMeterReportProcessor = new PowerDistributionBoardMeterReportProcessor();
    private boolean isConnected;

    private String remoteIpAddress;


    private List<ReportProcessorInterface> reportProcessorList = Collections.synchronizedList(new ArrayList<ReportProcessorInterface>());

    private String localIp;
    private String measIpAddress = "";


    public void setSpdbmRemoteIpAddress(String ipAddress) {
        this.remoteIpAddress = ipAddress;
        logger.info("spdbm this.remoteIpAddress : {}", this.remoteIpAddress);
    }


    public synchronized void connect() throws Exception {
        logger.info("スマート分電盤接続開始");
        if (!isConnected) {

            localIp = findDeviceIP("eth0");
            if ((localIp == null) || localIp.isEmpty()) {
                localIp = findDeviceIP("wlan0");
                logger.info("eth0 is null, use wlan0 : " + localIp);
            }else {
                logger.info("eth0 present, use eth0 : " + localIp);
            }

            logger.info("spdbm check if present, localIp : {}", localIp);
            if ((localIp == null) || localIp.isEmpty()) {
                logger.info("wlan0/eth0 not connected. return");
                return;
            }

            ElChannelBase channel = new ElUdpChannel(localIp);
            String INSTANCE_CODE = "C1";
            spdmElProcess = ElProcess.generate(channel, new NodeProfileClassImpl(), new DeviceObjectSuperClass[] {new ControllerClass(INSTANCE_CODE)});
            spdmElProcess.initEventList().addEventListenerToList(this).addEventListenerToList(new ElProcess.ElEventLogger()).start();
            isConnected = true;
        }
        logger.info("スマート分電盤接続完了");

        // 他ノードへインスタンスリスト通知(0xD5)を要求
        NodeProfileClass.infReqAll(spdmElProcess).reqInfInstanceListNotification().send();
    }

    private void changeActiveProcess( ElProcess elprocess ) {
        this.spdmElProcess = elprocess;
    }

    public synchronized void disconnect() throws Exception {
        if (isConnected) {
            spdmElProcess.stop();
            spdmElProcess.initEventList();
            spdmElProcess = null;
            isConnected = false;
        }
    }

    /**
     * 毎分実行処理.
     */
    public synchronized void excuteByEveryMinute() {
        if (!isConnected) {
            Calendar cal = Calendar.getInstance();
            int min = cal.get(Calendar.MINUTE);
            if((min % 5 == 0)) {
                logger.info("excuteByEveryMinute() spdbm not connected >> send connect()");
                try {
                    connect();
                    return;			// start reading after next minute after it connects.
                } catch (Exception e) {
                    logger.info("excuteByEveryMinute() spdbm Exception : " +  e.getMessage());
                    e.printStackTrace();
                }
            }else {
                logger.info("excuteByEveryMinute() spdbm not connected >> waiting for connect()");
                return;
            }
        }
        LocalDateTime ldt = LocalDateTime.now();
        logger.info("spdbm 毎分実行処理:{}", ldt);

        boolean isFindRemoteNode = false;
        Map<String, ElOneNode> map = spdmElProcess.getRemoteNodeMap();

        if (map.containsKey(remoteIpAddress) && (map.get(remoteIpAddress) != null)) {
            ElOneNode node = map.get(remoteIpAddress);
            logger.info("spdbm Host:[{}]", node.getIpAddress());

            List<DeviceObjectSuperClass> deviceClassList = node.getDeviceEojList();

            if (deviceClassList.size() > 0) {
                isFindRemoteNode = true;

                CountDownLatch doneSignal = new CountDownLatch(deviceClassList.size());
                for (DeviceObjectSuperClass deviceClass : deviceClassList) {

                    try {
                        // 機器クラス毎に送信メッセージをわける
                        switch (deviceClass.getStrClassGroupCode() + deviceClass.getStrClassCode()) {

                            case PowerDistributionBoardMetering.CLASS_GROUP_CODE + PowerDistributionBoardMetering.CLASS_CODE: {
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
//								.reqGetMeasurementChannel6()
//								.reqGetMeasurementChannel7()
//								.reqGetMeasurementChannel8()
//								.reqGetMeasurementChannel9()
//								.reqGetMeasurementChannel10()
//								.reqGetMeasurementChannel11()
//								.reqGetMeasurementChannel12()
//								.reqGetMeasurementChannel13()
//								.reqGetMeasurementChannel14()
//								.reqGetMeasurementChannel15()
//								.reqGetMeasurementChannel16()
//								.reqGetMeasurementChannel17()
//								.reqGetMeasurementChannel18()
//								.reqGetMeasurementChannel19()
//								.reqGetMeasurementChannel20()
//								.reqGetMeasurementChannel21()
//								.reqGetMeasurementChannel22()
//								.reqGetMeasurementChannel23()
//								.reqGetMeasurementChannel24()
//								.reqGetMeasurementChannel25()
//								.reqGetMeasurementChannel26()
//								.reqGetMeasurementChannel27()
//								.reqGetMeasurementChannel28()
//								.reqGetMeasurementChannel29()
//								.reqGetMeasurementChannel30()
//								.reqGetMeasurementChannel31()
//								.reqGetMeasurementChannel32()
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

            }
        }else {
            // isConnected = false;
            Calendar cal = Calendar.getInstance();
            int min = cal.get(Calendar.MINUTE);
            if((min == 0) || (min %2 == 0)) {
                isFindRemoteNode = false;
                try {
                    logger.info("!!! spdbm not found, send D5 map : " + map.keySet());
                    NodeProfileClass.infReqAll(spdmElProcess).reqInfInstanceListNotification().send();
                } catch (Exception e) {
                    logger.info("excuteByEveryMinute spdbm() reqInfInstanceListNotification : "  + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        if (!isFindRemoteNode) {
            // 他ノードが見つからなかったらEL通信網から他ノード取得する
            try {
                logger.info("spdbm isFindRemoteNode send D6, scanning");
                NodeProfileClass.getAll(spdmElProcess)
                        .reqGetSelfNodeInstanceListS()
                        .reqGetIdentificationNumber()
                        // 	.reqGetOperationStatus()
                        .send();
                NodeProfileClass.getAll(spdmElProcess)
                        .reqGetGetPropMap()
                        .send();
            } catch (Exception e) {
                logger.warn("D6 送信エラー:" + e.getMessage());
            }
        }
        logger.info("spdbm 毎分実行処理終了");
    }

    /**
     * 毎時実行処理.
     */
    public synchronized void excuteByEveryHour() throws SQLException {
        if (isConnected) {
//			LocalDateTime ldt = LocalDateTime.now();

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
        logger.info("spdbm 他ノード発見：" + node.getIpAddress());
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public synchronized void onNewPowerDistributionBoardMeterFound(ElClassBase eoj) {
        if (!isTargetNode(eoj.getNodeBelongsTo())) {
            // ignore if not connected
            // 接続対象でなければ無視する.
            return;
        }
        reportProcessorList.add(powerDistributionBoardMeterReportProcessor);
        eoj.setReportProcessor(powerDistributionBoardMeterReportProcessor);
        this.remoteIpAddress = eoj.getNodeBelongsTo().getIpAddress();
        logger.info("spdbm set onNewPowerDistributionBoardMeterFound RemoteIpAddress : " + remoteIpAddress);
    }

    public String findDeviceIP(String networkInterface) {
//		logger.info("start findDeviceIp");
        String ipAddr = "";
        try {
            Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface nif = nifs.nextElement();
                Enumeration<InetAddress> addresses = nif.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address) { 	// only care about IPv4 addresses
                        logger.info("Network Card Interface Name:" + nif.getName() + ", address:" + addr.getHostAddress());
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
