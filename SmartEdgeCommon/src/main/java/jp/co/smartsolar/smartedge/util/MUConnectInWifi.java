package jp.co.smartsolar.smartedge.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MUConnectInWifi {
    protected final static Logger log = LoggerFactory.getLogger(MUConnectInWifi.class);

    public enum NetworkIPMode {
        STATIC, DYNAMIC
    }

    public WifiData set(String muIpAddress) {
        WifiData wifiData = new WifiData();
        wifiData.setControllerIP(CommandExecute.findDeviceIP());
        wifiData.setSsid("SmartAI-G-WiFi");
        wifiData.setPassword("2fx4rat94zch9");
        wifiData.setOperation("0");
        wifiData.setNetworkIPMode(NetworkIPMode.STATIC);
        wifiData.setMuIpAddress(muIpAddress);
        return wifiData;
    }

    public WifiData set(String ssid, String password, String operation, String muIpAddress) {
        WifiData wifiData = new WifiData();
        wifiData.setControllerIP(CommandExecute.findDeviceIP());
        wifiData.setSsid(ssid);
        wifiData.setPassword(password);
        wifiData.setOperation(operation);
        wifiData.setNetworkIPMode(NetworkIPMode.DYNAMIC);
        wifiData.setMuIpAddress(muIpAddress);
        return wifiData;
    }

}
