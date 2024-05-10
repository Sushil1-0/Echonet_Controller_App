package jp.co.smartsolar.smartedge.component;
import jp.co.smartsolar.smartedge.Main;
import jp.co.smartsolar.smartedge.database.controller.ConfigInfoTableController;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @package jp.co.smartsolar.smartedge.component
 * @Author subohaju
 * @date 5/9/2024
 */

@Getter
@Setter
public class Settings {

    private Logger logger = LoggerFactory.getLogger(Main.class);
    public static String localIpAddress;
//    public static String remoteIpAddress;
    public static String awsIotThing;
    public static String smBrouteId;
    public static String smBroutePwd;
    public static String smBrouteChannel;
    public static String smPanId;
    public static String smIpAddress;
    public static String smWisunSerialPort;
    public static String tgt;
    public static String chargingStart;
    public static String chargingEnd;
    public static String standbyMode;
    public static String forceChargingValue;
    public static String networkMode;
    public static String externalControl;
    public static String contractBreaker;
    public static String sPdbm;
    public static String pTemp;
    public static String pSoc;
    public static int intervalPv;
    public static int intervalSb;


    public void loadsettings(){
        ConfigInfoTableController configInfoTableController = new ConfigInfoTableController();
        awsIotThing  = configInfoTableController.findByKeyName("awsIotThing").getKeyValue();
        smBrouteId = configInfoTableController.findByKeyName("smBrouteId").getKeyValue();
        localIpAddress = configInfoTableController.findByKeyName("measIpaddress").getKeyValue();
//        remoteIpAddress = configInfoTableController.findByKeyName("remoteIpAddress").getKeyValue();
        smBroutePwd = configInfoTableController.findByKeyName("smBroutePwd").getKeyValue();
        smBrouteChannel = configInfoTableController.findByKeyName("smBrouteChannel").getKeyValue();
        smPanId = configInfoTableController.findByKeyName("smPanId").getKeyValue();
        smIpAddress = configInfoTableController.findByKeyName("smIpAddress").getKeyValue();
        smWisunSerialPort = configInfoTableController.findByKeyName("smWisunSerialPort").getKeyValue();
        if(configInfoTableController.findByKeyName("forceChargingValue") != null)
        {
            forceChargingValue = configInfoTableController.findByKeyName("forceChargingValue").getKeyValue();
        }
        else {
            forceChargingValue = "2";
        }
        if(configInfoTableController.findByKeyName("network_mode") != null)
        {
            networkMode = configInfoTableController.findByKeyName("network_mode").getKeyValue();
        }
        else {
            networkMode = "static";
        }
        if(configInfoTableController.findByKeyName("externalControl") != null)
        {
            externalControl = configInfoTableController.findByKeyName("externalControl").getKeyValue();
        }
        else {
            externalControl = "off";
        }
        if(configInfoTableController.findByKeyName("standbyMode") != null)
        {
            standbyMode = configInfoTableController.findByKeyName("standbyMode").getKeyValue();
        }
        else {
            standbyMode = "false";
        }

        if(configInfoTableController.findByKeyName("contractBreaker") != null)
        {
            contractBreaker = configInfoTableController.findByKeyName("contractBreaker").getKeyValue();
        }
        else {
            contractBreaker = "0";
        }
        if(configInfoTableController.findByKeyName("smartPdbm") != null)
        {
            sPdbm = configInfoTableController.findByKeyName("smartPdbm").getKeyValue();
        }
        else {
            sPdbm = "false";
        }

        if(configInfoTableController.findByKeyName("pTemperature") != null)
        {
            pTemp = configInfoTableController.findByKeyName("pTemperature").getKeyValue();
        }
        else {
            pTemp = "1";
        }
        if(configInfoTableController.findByKeyName("pSoc") != null)
        {
            pSoc = configInfoTableController.findByKeyName("pSoc").getKeyValue();
        }
        else {
            pSoc = "15";
        }
        logger.info("Edgeモジュール IP :{}", localIpAddress);

        intervalPv = Integer.parseInt(configInfoTableController.findByKeyName("solerPowerGetInterval").getKeyValue());
        intervalSb = Integer.parseInt(configInfoTableController.findByKeyName("batteryChargeGetInterval").getKeyValue());

    }

}
