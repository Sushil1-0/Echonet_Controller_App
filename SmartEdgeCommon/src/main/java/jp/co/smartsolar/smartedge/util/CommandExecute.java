package jp.co.smartsolar.smartedge.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class CommandExecute {
    protected static Logger log = LoggerFactory.getLogger(CommandExecute.class);

    public static List<String> execute(String[] cmd) {

        List<String> cmdResponse = new ArrayList<>();
        Process process = null;
        try {
            log.info(Arrays.toString(cmd));
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(cmd);
            InputStream ism = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(ism);
            BufferedReader br = new BufferedReader(reader);
            String rst = "";
            while (br.ready() && (rst = br.readLine()) != null) {
                cmdResponse.add(rst);
            }

            reader.close();
            ism.close();
            br.close();
            runtime.gc();
        } catch (IOException IOe) {
            log.error(IOe.toString());
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return cmdResponse;
    }

    public static List<String> execute(String cmd) {

        List<String> ipAddress = new ArrayList<>();
        Process process = null;
        try {
            log.info(cmd);
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(cmd);
            InputStream ism = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(ism);
            BufferedReader br = new BufferedReader(reader);
            String rst = "";
            while (br.ready() && (rst = br.readLine()) != null) {
                ipAddress.add(rst);
            }
            reader.close();
            ism.close();
            br.close();
            runtime.gc();
        } catch (IOException IOe) {
            log.error(IOe.toString());
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return ipAddress;
    }


//    public static String findDeviceIP() {
//        boolean ipAction = false;
//        List<String> ipAddress = execute(new String[]{"bash", "-c", "ifconfig"});
//        for (String s : ipAddress) {
//            if (s.contains("wlan0:")) ipAction = true;
//            if (!ipAction) continue;
//            if (s.contains("inet")) {
//                s = s.substring(s.indexOf("inet ") + 5);
//                try {
//                    s = s.substring(0, s.indexOf(" "));
//                } catch (Exception e) {
//                    log.error(e.toString());
//                }
//                return s;
//            }
//        }
//        try {
//            return InetAddress.getLocalHost().getHostAddress();
//        } catch (Exception e) {
//            log.error(e.toString());
//        }
//        return "";
//    }
        
    public static String findDeviceIP() {
    	log.info("start findDeviceIp");
    	String ipAddr = "";
    	try {
    		Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
    		while (nifs.hasMoreElements()) {
    			NetworkInterface nif = nifs.nextElement();
    			Enumeration<InetAddress> addresses = nif.getInetAddresses();
    			
    			while (addresses.hasMoreElements()) {
    				InetAddress addr = addresses.nextElement();    			
    				if (addr instanceof Inet4Address) { 	// only care about IPv4 addresses
    					log.info("Network Card Interface Name:" + nif.getName());
    					log.info("Network card interface address:" + addr.getHostAddress());
    					if (nif.getName().equalsIgnoreCase("wlan0")) {    						
    						ipAddr = addr.getHostAddress();
    						break;
						}
    				}
    			}
    		}
    	} catch (SocketException e) {
    		e.printStackTrace();
    	}
    	log.info("return ipAddr : " + ipAddr);
    	return ipAddr;
    }
    
    
    //外部コマンド実行
    public static List<String> exeCommand(String[] cmd, boolean isWait){
        Runtime runtime = Runtime.getRuntime();
        List<String> ret = new ArrayList<String>();
        Process process = null;
        try {
            process = runtime.exec(cmd);
            if( isWait ) {
                process.waitFor();

                InputStream ism = process.getInputStream();
                InputStreamReader reader = new InputStreamReader(ism);
                BufferedReader br = new BufferedReader(reader);
                String rst ="";
                while ((rst = br.readLine()) != null) {
//                    System.out.println(rst);
                    ret.add(rst);
                }
            }

        } catch (IOException IOe) {
            System.out.println("IOException: "+IOe.getMessage());
        } catch (InterruptedException Inte) {
            System.out.println("IOException: "+Inte.getMessage());
        }finally{
            if(process!=null) {
                process.destroy();
            }
        }
        return ret;
    }
    
}
