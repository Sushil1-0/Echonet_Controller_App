package jp.co.smartsolar.smartedge.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import jp.co.smartsolar.smartedge.log.Log;

public class I2CBusDataRetriever implements Closeable {
    public static byte PCF8574P_ADDRESS = (byte)(0x04);		// (byte)(0x08);
    public static final int MAX_RESOLUTION = 12;
    public static final int MAX_RAW = (int) Math.pow(2, MAX_RESOLUTION);
    public static final int MAX_VOLTAGE_MILLIVOLTS = 3300;
    public static final int MAX_VOLTAGE_VOLTS = MAX_VOLTAGE_MILLIVOLTS / 1000;

    // 0x10 ~ 0x17: ADC raw data
    public static final byte REG_RAW_START = (byte)(0x10);
    // 0x20 ~ 0x27: input voltage
    public static final byte REG_INPUT_VOLTAGE_START = (byte)(0x20);
    // 0x30 ~ 0x37: input voltage / output voltage
    public static final byte REG_RATIO_VOLTAGE_START = (byte)(0x30);

    public static final int PIN_PORT_A0 = 0;
    public static final int PIN_PORT_A1 = 1;
    public static final int PIN_PORT_A2 = 2;
    public static final int PIN_PORT_A3 = 3;
    public static final int PIN_PORT_A4 = 4;
    public static final int PIN_PORT_A5 = 5;
    public static final int PIN_PORT_A6 = 6;
    public static final int PIN_PORT_A7 = 7;

    private I2CBus bus;
    private I2CDevice device;
    protected final static Logger log = LoggerFactory.getLogger(I2CBusDataRetriever.class);

    public I2CBusDataRetriever() throws IOException, UnsupportedBusNumberException {
        bus = I2CFactory.getInstance(I2CBus.BUS_1);
        byte busAddr = readAdcAddress();
        if (busAddr != 0) {
        	PCF8574P_ADDRESS = busAddr;			
		}
        log.info("I2CBusDataRetriever() PCF8574P_ADDRESS : " + PCF8574P_ADDRESS);
        device = bus.getDevice(PCF8574P_ADDRESS);
    }

    private static byte readAdcAddress() {
    	byte address = 0;
    	String[] cmd = new String[] {"bash", "-c", "sudo i2cdetect -y 1"};
    	List<String> ver = CommandExecute.exeCommand(cmd, true);
    	log.info("readAdcAddress ver size : " + ver.size());
    	if (ver.size() > 1) {
    		try {
    			String arr = ver.get(1);
    			if (arr.indexOf("04") >= 0) {
    				address = 0x04;
    			} else if(arr.indexOf("08") >= 0) {
    				address = 0x08;
    			} 
    		} catch (Exception e) {
    			log.info("readAdcAddress exception : " + e.getMessage());
    		}
    	}
    	log.info("I2CBusDataRetriever() readAdcAddress : " + address);
    	return address;
    }

	@Override
    public void close() throws IOException {
        bus.close();
    }

    public int getRawData(int pin) throws IOException {
        return getData(REG_RAW_START, pin);
    }

    // milliVolts
    public int getInputVoltageData(int pin, Log log) throws IOException {
//        this.log = log;
        return getData(REG_INPUT_VOLTAGE_START, pin);
    }

    public int getRatioVoltageData(int pin) throws IOException {
        return getData(REG_RATIO_VOLTAGE_START, pin);
    }

    public int getOutputVoltageData(int pin, int inputVoltageData, int ratioVoltageData) throws IOException {
        return inputVoltageData / ratioVoltageData;
    }

    private int getData(int regStart, int pin) throws IOException {
        if (pin < PIN_PORT_A0 || pin > PIN_PORT_A7) {
            throw new IllegalArgumentException("pin should be between 1-7");
        }

        byte[] data = new byte[2];
        device.read(regStart + pin, data, 0, data.length);
//        if(log != null) {
//            log.info( String.format("I2CBusDataRetriever: data0: Data : "+ data[0] +" hex 0x"+Integer.toHexString((data[0] & 0xff)) ) );
//            log.info( String.format("I2CBusDataRetriever: data1: Data : "+ data[1] +" hex 0x"+Integer.toHexString((data[1] & 0xff)) ) );
//        }
        return bytesToInt(data[1], data[0]);
    }

    private int bytesToInt(int high, int low) {
//        return ((high & 0xFF) << 8) | (low & 0xFF);
        //int ret = high << 8 | low;
    	if (low<0) {
    		low = 255 +  low;
    	}
    	int ret = high * 256 + low;
        //if(ret>2047) ret=ret-4096;
//        log.info("High : " + high + "  low : " + low  +"  ret : " + ret);
        return ret;
    }
}
