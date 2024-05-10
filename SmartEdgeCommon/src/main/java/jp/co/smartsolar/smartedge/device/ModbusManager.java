package jp.co.smartsolar.smartedge.device;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.msg.request.ReadHoldingRegistersRequest;
import com.intelligt.modbus.jlibmodbus.msg.request.ReadInputRegistersRequest;
import com.intelligt.modbus.jlibmodbus.msg.request.WriteMultipleRegistersRequest;
import com.intelligt.modbus.jlibmodbus.msg.request.WriteSingleRegisterRequest;
import com.intelligt.modbus.jlibmodbus.msg.response.ReadHoldingRegistersResponse;
import com.intelligt.modbus.jlibmodbus.msg.response.ReadInputRegistersResponse;
import com.intelligt.modbus.jlibmodbus.msg.response.WriteMultipleRegistersResponse;
import com.intelligt.modbus.jlibmodbus.msg.response.WriteSingleRegisterResponse;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;

/**
 * Modbus通信用マネージャー.
 *
 * @author yamada osamu
 *
 */
public class ModbusManager {
    TcpParameters tcpParameters = null;
    InetAddress   iaddr         = null;
    ModbusMaster  mMaster       = null;
    byte[]        remoteIp      = new byte[] { (byte) 192, (byte) 168, 1, (byte) 34 };
    static int modbusPort = 502;
    boolean isReverse = false;
    protected static Logger log = LoggerFactory.getLogger(ModbusManager.class);
    public static boolean pcsFwUpdateRunning = false;		// 20231221 sungrow pcs support, disable modbus connection during pcs fw update

    public ModbusManager(byte[] ipaddrs, boolean isReverse) throws ModbusException {
        this(ipaddrs, modbusPort, isReverse);
    }

    public ModbusManager(byte[] ipaddrs, int port, boolean isReverse) throws ModbusException { 	  
    	log.info("ModbusManager pcsFwUpdateRunning : " + pcsFwUpdateRunning);
    	if (!pcsFwUpdateRunning) {
    		this.isReverse = isReverse;
    		if (ipaddrs != null) {
    			remoteIp = ipaddrs;
    		}
    		if(port > 0) {
    			modbusPort = port;
    		}
    		if (tcpParameters == null) {
    			tcpParameters = new TcpParameters();
    			try {
    				setTcpParameters();
    				setModbusMaster();
    				// mMaster.setResponseTimeout(100);
    			} catch (ModbusException e) {
    				setOff();
    				throw new ModbusException("Modbus例外:" + e.getMessage());
    			}
    		}
    	}
    }

    public ModbusMaster getMaster() {
        return mMaster;
    }

    public void disconnect() {
        try {
            getMaster().disconnect();
        } catch (ModbusIOException e) {
            e.printStackTrace();
        }
    }

    /**
     * setOff.
     */
    public void setOff() {
    	// tcpParameters = null;
    	try {
    		mMaster.disconnect();
    	} catch (ModbusIOException e) {
    		e.printStackTrace();
    	}
    }

    // Tcpパラメーターを設定.
    private boolean setTcpParameters() throws ModbusException {
        try {
            if (remoteIp == null) {
                iaddr = InetAddress.getLocalHost();
            } else {
                iaddr = InetAddress.getByAddress(remoteIp);
            }
            tcpParameters.setHost(iaddr);
            tcpParameters.setKeepAlive(true);
            tcpParameters.setPort(modbusPort);
            return true;
        } catch (UnknownHostException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
    }

    // ModbusMasterの作成
    private boolean setModbusMaster() throws ModbusException {
        try {
            mMaster = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            mMaster.setResponseTimeout(3000);		//7000
            Modbus.setAutoIncrementTransactionId(true);
            for(int i=0; i<3; i++) {		//10
                Thread.sleep(1000);			//2000
                System.out.println("check Modbus isConnected()");
                log.info("setModbusMaster() check Modbus isConnected()");
                if (mMaster.isConnected()) {
                    System.out.println("isConnected(): true!");
                    log.info("setModbusMaster() isConnected(): true!");
                    return true;
                }
            }
        } catch (Exception e) {
        	log.info("setModbusMaster() Exception : " + e.toString());
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
        System.out.println("isConnected(): false!");
        return false;
    }

    private String reverseBit(String strBit) {
        String ret = "";

        for (String bit : strBit.split("")) {
            ret += "1".equals(bit) ? "0" : "1";
        }
        return ret;
    }

    private long getTwosComplement(long val) {
        // 2の補数を求める（再掲: 2の補数 = ビットを反転して1を足した値）
        String invertedBinaryString = Long.toBinaryString(val);
        invertedBinaryString = reverseBit(invertedBinaryString);
        long twosComp = Long.parseLong(invertedBinaryString, 2) + 1;

        // 元のビット列の最上位が1の場合, 2の補数にマイナスを掛ける
        String binaryString = Long.toBinaryString(val);
        if (binaryString.startsWith("1")) {
            twosComp *= -1;
        }
        return twosComp;
    }

    //本システムでは16bit 2つまでしか計算しない
    static final int OVER_FLOW_RANGE = 2;

    // Modbus用int配列をLong型変換
    // isReverse：上位、下位を逆転させるか。SMA：false、Sungrow：true
    private long arrInt2long(int[] list) {
        String ans = "";
        int i = 0;

        if (list.length > OVER_FLOW_RANGE) {
            // TODO :
        }

        for (int value : list) {
            if (list.length > OVER_FLOW_RANGE) {
                // TODO :
            }
            String bin = Integer.toBinaryString(value);
            if (list.length > OVER_FLOW_RANGE) {
                // TODO :
            }
            if (value == 0) {
                if(isReverse) {
                    bin = "00000000";
                }
                else {
                    bin = "0000000000000000";
                }
            }
            if( bin.length() < 8 ) {
                int cnt= 8 - bin.length();
                while(cnt >= 0) {
                    bin = "0"+bin;
                    cnt--;
                }
            }
            else if (bin.length() < 16) {
                int cnt= 16 - bin.length();
                if(isReverse) {
                    while(cnt > 0) {
                        bin = "0"+bin;
                        cnt--;
                    }
                }
                else {
                    while(cnt >= 0) {
                        bin = "0"+bin;
                        cnt--;
                    }
                }

            }
            if(isReverse) {
                ans =bin+ans;
            }
            else {
                ans += bin;
            }
            i++;
        }

        long exp = Long.valueOf(ans, 2);
        String strRange = "";
        for (int j = 0; j < list.length; j++) {
            strRange += "1111111111111111";
        }
        long minRange = Long.parseLong(strRange, 2) / 2;
        if (list.length > OVER_FLOW_RANGE) {
            // TODO :
        }
        if (exp > minRange) {
            exp = getTwosComplement(exp);
        }
        if (list.length > OVER_FLOW_RANGE) {
            // TODO :
        }
        return exp;
    }

    /**
     * HoldingRegisterの値(40001番地から)を配列で取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス(40001を引いた値)
     * @param quantity 取得する量
     * @return レジスタ番号昇順でint配列
     */
    public int[] getHoldingRegisterArray(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        ReadHoldingRegistersResponse response = null;

        ReadHoldingRegistersRequest request = setHoldingRegistersRequest(slaveAddr, startAddr, quantity);
        try {
            response = (ReadHoldingRegistersResponse) mMaster.processRequest(request);
        } catch (ModbusProtocolException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (ModbusIOException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }

        if (quantity > OVER_FLOW_RANGE) {
            // TODO :
        }
        int[] data = new int[quantity];
        int i = 0;
        for (int value : response.getHoldingRegisters()) {
            data[i] = value;
            i++;
        }
        return data;
    }

    /**
     * HoldingRegisterの値(40001番地から)を配列で取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス(40001を引いた値)
     * @param quantity 取得する量
     * @return レジスタ番号昇順でstring配列
     */
    public String[] getHoldingRegisterStringArray(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        ReadHoldingRegistersResponse response = null;

        ReadHoldingRegistersRequest request = setHoldingRegistersRequest(slaveAddr, startAddr, quantity);
        try {
            response = (ReadHoldingRegistersResponse) mMaster.processRequest(request);
        } catch (ModbusProtocolException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (ModbusIOException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }

        if (quantity > OVER_FLOW_RANGE) {
            // TODO :
        }
        String[] data = new String[quantity];
        int i = 0;
        for (long value : response.getHoldingRegisters()) {
            data[i] = String.valueOf(value);
            i++;
        }
        return data;
    }

    /**
     * HoldingRegisterの値(40001番地から)を取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス(40001を引いた値)
     * @param quantity 取得する量
     * @return Singed Long型
     */
    public long getHoldingRegister(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        try {
            int[] data = getHoldingRegisterArray(slaveAddr, startAddr, quantity);
            long ret = arrInt2long(data);
            return ret;
        } catch (ModbusException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
    }

    /**
     * HoldingRegisterのリクエストを作成.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス
     * @param quantity 取得する量
     * @return
     */
    private ReadHoldingRegistersRequest setHoldingRegistersRequest(int slaveAddr, int startAddr, int quantity) throws ModbusException {

        ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest();
        try {
            request.setServerAddress(slaveAddr);
            request.setStartAddress(startAddr);
            request.setQuantity(quantity);
        } catch (ModbusNumberException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
        return request;
    }

    /**
     * getReadInputRegisterの値(30001番地から)を配列で取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス(30001を引いた値)
     * @param quantity 取得する量
     * @return レジスタ番号昇順でint配列
     */
    public int[] getReadInputRegisterArray(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        ReadInputRegistersResponse response = null;
        ReadInputRegistersRequest request = setReadInpuptRegistersRequest(slaveAddr, startAddr, quantity);
        try {
            response = (ReadInputRegistersResponse) mMaster.processRequest(request);
        } catch (ModbusProtocolException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (ModbusIOException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }

        if (quantity > OVER_FLOW_RANGE) {
            // TODO :
        }
        int[] data = new int[quantity];
        int i = 0;
        for (int value : response.getHoldingRegisters()) {
            data[i] = value;
            i++;
        }
        return data;
    }

    /**
     * getReadInputRegisterの値(30001番地から)を配列で取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス(30001を引いた値)
     * @param quantity 取得する量
     * @return レジスタ番号昇順でString配列
     */
    public String[] getReadInputRegisterStringArray(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        ReadInputRegistersResponse response = null;
        ReadInputRegistersRequest request = setReadInpuptRegistersRequest(slaveAddr, startAddr, quantity);
        try {
            response = (ReadInputRegistersResponse) mMaster.processRequest(request);
        } catch (ModbusProtocolException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (ModbusIOException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }

        if (quantity > OVER_FLOW_RANGE) {
            // TODO :
        }
        String[] data = new String[quantity];
        int i = 0;
        for (int value : response.getHoldingRegisters()) {
            data[i] = String.valueOf(value);
            i++;
        }
        return data;
    }

    /**
     * getReadInputRegisterの値(30001番地から)を取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス(30001を引いた値)
     * @param quantity 取得する量
     * @return
     */
    public long getReadInputRegister(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        int[] data = getReadInputRegisterArray(slaveAddr, startAddr, quantity);
        long ret = arrInt2long(data);
        return ret;
    }

    //Use for reading multiple input registers // Reading pcs FW input register // added 20210715
    public int[] getReadInputMultipleRegister(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        int[] data = getReadInputRegisterArray(slaveAddr, startAddr, quantity);
        return data;
    }
    
    //Use for reading multiple holding registers // Reading pcs FW holding register 
    public int[] getReadHoldingMultipleRegister(int slaveAddr, int startAddr, int quantity) throws ModbusException {
        int[] data = getHoldingRegisterArray(slaveAddr, startAddr, quantity);
        return data;
    }
    
    /**
     * setReadInpuptRegistersRequestのリクエストを作成.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス
     * @param quantity 取得する量
     * @return Singed Long型
     */
    private ReadInputRegistersRequest setReadInpuptRegistersRequest(int slaveAddr, int startAddr, int quantity) throws ModbusException {

        ReadInputRegistersRequest request = new ReadInputRegistersRequest();
        try {
            request.setServerAddress(slaveAddr);
            request.setStartAddress(startAddr);
            request.setQuantity(quantity);
        } catch (ModbusNumberException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
        return request;
    }

    /**
     * setWriteMultipleRegisterの値を取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス
     * @param quantity 設定データ
     * @return 結果
     */
    public int setWriteMultipleRegister(int slaveAddr, int startAddr, int[] quantity) throws ModbusException {
        WriteMultipleRegistersRequest request = setWriteMultipleRegisterRequest(slaveAddr, startAddr, quantity);
        WriteMultipleRegistersResponse response = null;
        try {
            response = (WriteMultipleRegistersResponse) mMaster.processRequest(request);
        } catch (ModbusProtocolException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (ModbusIOException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
        return response.getValue();
    }

    /**
     * setWriteMultipleRegisterRequestのリクエストを作成.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス
     * @param quantity 設定データ
     * @return
     */
    private WriteMultipleRegistersRequest setWriteMultipleRegisterRequest(int slaveAddr, int startAddr, int[] quantity) throws ModbusException {

        WriteMultipleRegistersRequest request = new WriteMultipleRegistersRequest();
        try {
            request.setServerAddress(slaveAddr);
            request.setStartAddress(startAddr);
            request.setRegisters(quantity);
        } catch (ModbusNumberException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
        return request;
    }

    /**
     * setWirteSingleRegisterの値を取得する.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス
     * @param quantity 設定データ
     * @return 結果
     */
    public int setWirteSingleRegister(int slaveAddr, int startAddr, int quantity) throws ModbusException {

        WriteSingleRegisterRequest request = setWirteSingleRegisterRequest(slaveAddr, startAddr, quantity);
        WriteSingleRegisterResponse response = null;
        try {
            response = (WriteSingleRegisterResponse) mMaster.processRequest(request);
        } catch (ModbusProtocolException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (ModbusIOException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
        return response.getValue();
    }

    /**
     * settWirteSingleRegisterRequestのリクエストを作成.
     *
     * @param slaveAddr スレーブアドレス
     * @param startAddr スタートアドレス
     * @param quantity 設定データ
     * @return
     */
    private WriteSingleRegisterRequest setWirteSingleRegisterRequest(int slaveAddr, int startAddr, int quantity) throws ModbusException {

        WriteSingleRegisterRequest request = new WriteSingleRegisterRequest();
        try {
            request.setServerAddress(slaveAddr);
            request.setStartAddress(startAddr);
            request.setValue(quantity);
        } catch (ModbusNumberException e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        } catch (Exception e) {
            setOff();
            throw new ModbusException("Modbus例外:" + e.getMessage());
        }
        return request;
    }
}
