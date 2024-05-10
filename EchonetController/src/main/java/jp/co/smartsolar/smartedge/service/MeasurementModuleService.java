package jp.co.smartsolar.smartedge.service;

import jp.co.smartsolar.smartedge.component.Settings;
import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.channel.ElChannelBase;
import jp.co.smartsolar.smartedge.echonetlite.channel.udp.ElLocalUdpChannel;
import jp.co.smartsolar.smartedge.echonetlite.channel.udp.ElUdpChannel;
import jp.co.smartsolar.smartedge.echonetlite.devices.ControllerClass;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;
import jp.co.smartsolar.smartedge.eoj.NodeProfileClassImpl;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;

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
    private boolean smartPdbm;

    private final String INSTANCE_CODE = "C1";

    private ElProcess process;
    public synchronized void connect() throws Exception{
        logger.info("測定モジュール接続開始");
        if(!isConnected) {
            System.out.println("here");
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

}
