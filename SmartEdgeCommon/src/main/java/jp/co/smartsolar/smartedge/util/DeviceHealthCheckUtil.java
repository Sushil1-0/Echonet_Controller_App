package jp.co.smartsolar.smartedge.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Properties;

public class DeviceHealthCheckUtil {

    private static final Logger log = LoggerFactory.getLogger(DeviceHealthCheckUtil.class);

    private final String IP_TO_CHECK_FOR_INTERNET = "8.8.8.8";

    public boolean shouldReboot() {
        Properties properties = readProperty("/opt/tmp/logs.properties");
        
        if (properties.getProperty("date_time_device_shadow_sync") != null && properties.getProperty("date_time_data_sent") != null
                && properties.getProperty("date_time_internet_connection") != null  && properties.getProperty("date_time_readPcsError_independentMode") != null) {
            long deviceShadowSyncSeconds = Long.parseLong(properties.getProperty("date_time_device_shadow_sync"));
            long deviceShadowSyncDifferenceInMinute = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - deviceShadowSyncSeconds) / 60;

            long dataSentSeconds = Long.parseLong(properties.getProperty("date_time_data_sent"));
            long dataSentDifferenceInMinute = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - dataSentSeconds) / 60;

            long internetConnectionSeconds = Long.parseLong(properties.getProperty("date_time_internet_connection"));
            long internetConnectionDifferenceInMinute = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - internetConnectionSeconds) / 60;
            
            long pcsErrorThread = Long.parseLong(properties.getProperty("date_time_readPcsError_independentMode"));
            long pcsErrorThreadDifferenceInMinute = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - pcsErrorThread) / 60;

            log.info("----- Last Data sent: " + dataSentDifferenceInMinute + " Minutes ago.");
            log.info("----- Last Communication (Device Shadow): " + deviceShadowSyncDifferenceInMinute + " Minutes ago.");
            log.info("----- Last Internet connection: " + internetConnectionDifferenceInMinute + " Minutes ago.");
            log.info("----- Last pcsErrorThread run : " + pcsErrorThreadDifferenceInMinute + " Minutes ago.");

            String lastRebootReason = properties.getProperty("last_reboot_reason");
            String currentRebootCount = properties.getProperty("reboot_count");
            int currentRebootCountInt = 0;
            if (currentRebootCount != null) {
                currentRebootCountInt = Integer.parseInt(currentRebootCount);
            }

            //Network Unreachable
            if (internetConnectionDifferenceInMinute >= 59) {
                if (lastRebootReason != null && lastRebootReason.equalsIgnoreCase(RebootReason.NETWORK_UNREACHABLE.name())) {
                    if (currentRebootCountInt >= 24) {
                        log.info("----- The device should auto reboot. Internet issue -----");
                        properties.setProperty("last_reboot_reason", RebootReason.NETWORK_UNREACHABLE.name());
                        properties.setProperty("reboot_count", "1");
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return true;
                    } else {
                        properties.setProperty("reboot_count", String.valueOf(currentRebootCountInt + 1));
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return false;
                    }
                } else {
                    log.info("----- The device should auto reboot. Internet issue -----");
                    properties.setProperty("last_reboot_reason", RebootReason.NETWORK_UNREACHABLE.name());
                    properties.setProperty("reboot_count", "1");
                    writeProperty(properties, "/opt/tmp", "logs.properties");
                    return true;
                }
            }          

            //Device Shadow sync failure
            if (deviceShadowSyncDifferenceInMinute >= 59) {
                if (lastRebootReason != null && lastRebootReason.equalsIgnoreCase(RebootReason.DEVICE_SHADOW_SYNC_FAILURE.name())) {
                    if (currentRebootCountInt >= 24) {
                        log.info("----- The device should auto reboot. Device Shadow sync issue -----");
                        properties.setProperty("last_reboot_reason", RebootReason.DEVICE_SHADOW_SYNC_FAILURE.name());
                        properties.setProperty("reboot_count", "1");
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return true;
                    } else {
                        properties.setProperty("reboot_count", String.valueOf(currentRebootCountInt + 1));
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return false;
                    }
                } else {
                    log.info("----- The device should auto reboot. Device Shadow sync issue -----");
                    properties.setProperty("last_reboot_reason", RebootReason.DEVICE_SHADOW_SYNC_FAILURE.name());
                    properties.setProperty("reboot_count", "1");
                    writeProperty(properties, "/opt/tmp", "logs.properties");
                    return true;
                }
            }

            //Data sending failure
            if (dataSentDifferenceInMinute >= 59) {
                if (lastRebootReason != null && lastRebootReason.equalsIgnoreCase(RebootReason.DATA_SENDING_FAILURE.name())) {
                    if (currentRebootCountInt >= 24) {
                        log.info("----- The device should auto reboot. Data Sending failure issue -----");
                        properties.setProperty("last_reboot_reason", RebootReason.DATA_SENDING_FAILURE.name());
                        properties.setProperty("reboot_count", "1");
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return true;
                    } else {
                        properties.setProperty("reboot_count", String.valueOf(currentRebootCountInt + 1));
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return false;
                    }
                } else {
                    log.info("----- The device should auto reboot. Data Sending failure issue -----");
                    properties.setProperty("last_reboot_reason", RebootReason.DATA_SENDING_FAILURE.name());
                    properties.setProperty("reboot_count", "1");
                    writeProperty(properties, "/opt/tmp", "logs.properties");
                    return true;
                }
            }
            
            //PcsError or IndependentMode thread run failure
            if (pcsErrorThreadDifferenceInMinute >= 59) {
                if (lastRebootReason != null && lastRebootReason.equalsIgnoreCase(RebootReason.PCS_ERROR_OR_INDEPENDENT_MODE_THREAD_RUN_FAILURE.name())) {
                    if (currentRebootCountInt >= 24) {
                        log.info("----- The device should auto reboot. Pcs Error or IndependentMode thread run failure issue -----");
                        properties.setProperty("last_reboot_reason", RebootReason.PCS_ERROR_OR_INDEPENDENT_MODE_THREAD_RUN_FAILURE.name());
                        properties.setProperty("reboot_count", "1");
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return true;
                    } else {
                        properties.setProperty("reboot_count", String.valueOf(currentRebootCountInt + 1));
                        writeProperty(properties, "/opt/tmp", "logs.properties");
                        return false;
                    }
                } else {
                    log.info("----- The device should auto reboot. Pcs Error || IndependentMode thread run failure issue -----");
                    properties.setProperty("last_reboot_reason", RebootReason.PCS_ERROR_OR_INDEPENDENT_MODE_THREAD_RUN_FAILURE.name());
                    properties.setProperty("reboot_count", "1");
                    writeProperty(properties, "/opt/tmp", "logs.properties");
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean hasInternetConnection() {
        try {
            return InetAddress.getByName(IP_TO_CHECK_FOR_INTERNET).isReachable(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean writeProperty(Properties properties, String filePath, String fileName) {
        boolean writeSuccess = false;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath + "/" + fileName);
            properties.store(fileOutputStream, "Epoch UTC date time seconds");
            writeSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return writeSuccess;
    }

    public Properties readProperty(String filePathWithName) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePathWithName);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public String readValueFromProperties(String filePathWithName, String key) {
        return readProperty(filePathWithName).getProperty(key);
    }


    public enum RebootReason {
        NETWORK_UNREACHABLE, DEVICE_SHADOW_SYNC_FAILURE, DATA_SENDING_FAILURE, PCS_ERROR_OR_INDEPENDENT_MODE_THREAD_RUN_FAILURE
    }

    public void saveInternetConnectionTime() {
        //Save communication time with Device Shadow and Power Data sent
        DeviceHealthCheckUtil deviceHealthCheckUtil = new DeviceHealthCheckUtil();
        if (deviceHealthCheckUtil.hasInternetConnection()) {
            log.info("----- saving date time of internet connection. -----");
            Properties properties = deviceHealthCheckUtil.readProperty("/opt/tmp/logs.properties");
            properties.setProperty("date_time_internet_connection", String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
            deviceHealthCheckUtil.writeProperty(properties, "/opt/tmp", "logs.properties");
        } else {
            log.info("----- no internet connection. -----");
        }
    }
}
