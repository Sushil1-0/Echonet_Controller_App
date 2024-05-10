package jp.co.smartsolar.smartedge.util;


import java.io.Serializable;
import jp.co.smartsolar.smartedge.util.MUConnectInWifi.NetworkIPMode;

public class WifiData implements Serializable {
    private String ssid;
    private String password;
    private NetworkIPMode networkIPMode;
    private String controllerIP;
    private String muIpAddress;
    private String operation;

    public String getSsid() {
        return ssid;
    }

    public WifiData setSsid(String ssid) {
        this.ssid = ssid;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public WifiData setPassword(String password) {
        this.password = password;
        return this;
    }

    public NetworkIPMode getNetworkIPMode() {
        return networkIPMode;
    }

    public WifiData setNetworkIPMode(NetworkIPMode networkIPMode) {
        this.networkIPMode = networkIPMode;
        return this;
    }

    public String getControllerIP() {
        return controllerIP;
    }

    public WifiData setControllerIP(String controllerIP) {
        this.controllerIP = controllerIP;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public WifiData setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public String getMuIpAddress() {
        return muIpAddress;
    }

    public void setMuIpAddress(String muIpAddress) {
        this.muIpAddress = muIpAddress;
    }

    @Override
    public String toString() {
        return "WifiData{" +
                "ssid='" + ssid + '\'' +
                ", password='" + password + '\'' +
                ", networkIPMode=" + networkIPMode +
                ", controllerIP='" + controllerIP + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
