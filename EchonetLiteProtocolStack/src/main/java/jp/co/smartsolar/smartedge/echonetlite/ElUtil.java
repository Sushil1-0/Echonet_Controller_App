package jp.co.smartsolar.smartedge.echonetlite;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.DatatypeConverter;

/**
 * ユーティリティ関連メソッドの集約クラス
 */
public class ElUtil {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElUtil";

	/**
	 * 指定したネットワークインターフェースに紐づく先頭のNICオブジェクトを返す
	 * @param networkIfName ネットワークインターフェース名称
	 * @return NetworkInterface 該当するネットワークインターフェースのオブジェクト
	 */
	public static NetworkInterface getNicByIfName(String networkIfName) {
		try {
			Enumeration<NetworkInterface> enNic = NetworkInterface.getNetworkInterfaces();
			while(enNic.hasMoreElements()) {
				NetworkInterface nic = enNic.nextElement();
				if(networkIfName.equals(nic.getDisplayName())) {
					return nic;
				}
			}
			throw new Exception("名称に該当するネットワークインターフェースが見つかりません。");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定したネットワークインターフェースに紐づく先頭のIPアドレスを返す
	 * @param nic ネットワークインターフェースオブジェクト
	 * @return String ネットワークインターフェースに紐付くIPアドレス
	 */
	public static String getFirstIpAddrOfNic(NetworkInterface nic ) {
		if(nic != null) {
			Enumeration<InetAddress> enIp = nic.getInetAddresses();
			while(enIp.hasMoreElements()) {
				InetAddress ip = enIp.nextElement();
				if (!ip.isLoopbackAddress() && ip instanceof Inet4Address) {
					return ip.getHostAddress();
				}
			}
		}
		return null;
	}

	/**
	 * 指定したネットワークインターフェース名に紐づく先頭のIPアドレスを返す
	 * @param networkIfName ネットワークインターフェース名
	 * @return IPアドレス
	 */
	public static String getIpByIfName(String networkIfName) {
		NetworkInterface nic = getNicByIfName(networkIfName);
		return getFirstIpAddrOfNic(nic);
	}


	/**
	 * byte配列を16進数文字列に変換する
	 * @param bArray バイト配列
	 * @return 16進数文字列表記
	 */
	public static String binsToHexString( byte[] bArray ) {
		String sStr = "";
		for(byte bOne : bArray){
			sStr += String.format("%02X", (bOne & 0xff));
		}
		return sStr;
	}

	/**
	 * 16進数文字列をbyte配列に変換する
	 * @param hexString 16進数文字列表記
	 * @return バイト配列
	 */
	public static byte[] hexStringToBins( String hexString ) {
		byte[] bytes = DatatypeConverter.parseHexBinary(hexString);
		return bytes;
	}

	/**
	 * 16進数文字列を10進数（数値型）に変換する
	 * @param hexString 16進数文字列
	 * @return Integer(10進数の値)
	 */
	public static Integer hexStringToInt( String hexString ) {
		return (int)Long.parseLong(hexString,16);
	}

	/**
	 * 10進数（数値型）を16進数文字列に変換する(1バイト分のみ対応)
	 * @param intValue 10進数の値
	 * @return 16進数文字列
	 */
	public static String intToHexString( Integer intValue ) {
		return String.format("%02X", intValue);
	}

	/**
	 * 10進数（数値型）を16進数文字列に変換する(バイト数指定版)
	 * @param intValue 10進数の値
	 * @param byteNum バイト数を10進数で指定（0埋め）
	 * @return 16進数文字列
	 */
	public static String intToHexString( Integer intValue, Integer byteNum ) {
		String digitOfHexString = String.valueOf(byteNum * 2);
		String format = "%0" + digitOfHexString + "X";
		return String.format(format, intValue);
	}

	/**
	 * プロパティマップの形式変換（Map ⇒ EDT）<br>
	 * プロパティマップを格納したSETオブジェクトのデータから
	 * 応答フレーム用の16進数文字列形式のEDT値に変換する
	 * @param propMapSet プロパティマップを格納しているSETオブジェクト
	 * @return EDT形式値（16進数文字列）
	 */
	public static String convPropMapSetToHexStr(Set<String> propMapSet) {
		String retStr="";

		if(propMapSet == null) {
			return "";
		}

		// プロパティ数16未満の場合
		int intPropNum = propMapSet.size();
		if(propMapSet.size() < 16){
			retStr += intToHexString(intPropNum);
			Iterator<String> ite = propMapSet.iterator();
			while(ite.hasNext()) {
				retStr += ite.next();
			}
		}

		// プロパティ数16以上の場合
		else {
			Iterator<String> ite = propMapSet.iterator();
			byte[] byteProps = new byte[17]; // 0 で初期化される

			while(ite.hasNext()) {
				String hexStr = ite.next();
				byte[] hexVal = hexStringToBins(hexStr);

				byte upperHex = (byte) ((hexVal[0] & 0xf0) >> 4);
				int lowerHex = (int) (hexVal[0] & 0x0f);

				// 00000001 を(上位ビット-8)だけ左へシフトした値を
				// 既存のbyte値とORする。
				byteProps[lowerHex + 1] |= (0x01 << (upperHex - 0x08));

			}
			byteProps[0] = (byte)intPropNum;

			retStr = binsToHexString(byteProps);

		}
		return retStr;
	}





	/**
	 * プロパティマップの16進数文字列形式のEDT値から個々のプロパティ値を取り出し配列に格納する
	 * @param propMapEdt プロパティマップの情報を格納したSETオブジェクト
	 * @return 16進数文字列の配列
	 */
	public static String[] convHexStrToEachPropValues(String propMapEdt) {

		if(propMapEdt == null || propMapEdt.length() == 0) {
			return new String[] {};
		}
		String workPropMapEdt = propMapEdt;
		String strPropNum = workPropMapEdt.substring(0,2);

		Integer intPropNum = hexStringToInt(strPropNum);

		String[] retProps = new String[intPropNum];

		// プロパティ数16未満の場合
		if(intPropNum < 16){
			for(int i = 0 ; i < intPropNum; i++) {
				workPropMapEdt = workPropMapEdt.substring(2); //pop
				String prop = workPropMapEdt.substring(0,2);
				retProps[i] = prop;
			}
		}

		// プロパティ数16以上の場合
		else {
			int propCnt = 0;
			for(int i = 0 ; i < 16; i++) {
				workPropMapEdt = workPropMapEdt.substring(2); //pop
				String element = workPropMapEdt.substring(0,2);
				byte[] byteElems = hexStringToBins(element);
				byte byteElem = byteElems[0];

				byte lowerHex = (byte)(i & 0x0f);

				for(int bit = 0 ; bit < 8; bit++) { // 1bitづつ調査
					if((byteElem & 0x01) == 0x01) { // フラグＯＮの場合
						byte upperHex = (byte)( ((8 + bit) << 4) & 0xf0 );
						byte hexProp = (byte)((upperHex | lowerHex) & 0xff);

						retProps[propCnt++] = binsToHexString(new byte[] {hexProp});
					}
					byteElem = (byte)(byteElem >> 1); // 調査対象bitをシフト
				}
			}
		}
		return retProps;
	}


	/**
	 * 16進数文字列をEchonetLite定義でのunsigned long 値として解釈した場合の大小比較を行う
	 * @param hexStringA 比較対象Ａ
	 * @param hexStringB 比較対象Ｂ
	 * @return Integer -1:Aの方がBより小さい、0:AとBは等しい、1:Aの方がBより大きい
	 */
	public static Integer compElUnsignedLong(String hexStringA, String hexStringB) {
		Integer valA = Integer.parseUnsignedInt(hexStringA,16);
		Integer valB = Integer.parseUnsignedInt(hexStringB,16);
		//System.out.println(Integer.toUnsignedString(valA));
		//System.out.println(Integer.toUnsignedString(valB));
		return Long.compareUnsigned(valA, valB);
	}

	/**
	 * 16進数文字列をEchonetLite定義でのsigned long 値として解釈した場合の大小比較を行う
	 * @param hexStringA 比較対象Ａ
	 * @param hexStringB 比較対象Ｂ
	 * @return Integer -1:Aの方がBより小さい、0:AとBは等しい、1:Aの方がBより大きい
	 */
	public static Integer compElSignedLong(String hexStringA, String hexStringB) {
		int valA = (int)Long.parseLong(hexStringA,16);
		int valB = (int)Long.parseLong(hexStringB,16);
		//System.out.println(valA);
		//System.out.println(valB);

		return Long.compare(valA, valB);
	}


	/**
	 * 16進数文字列をEchonetLite定義でのunsigned short 値として解釈した場合の大小比較を行う
	 * @param hexStringA 比較対象Ａ
	 * @param hexStringB 比較対象Ｂ
	 * @return Integer -1:Aの方がBより小さい、0:AとBは等しい、1:Aの方がBより大きい
	 */
	public static Integer compElUnsignedShort(String hexStringA, String hexStringB) {
		Short valA = (short)Long.parseLong(hexStringA,16);
		Short valB = (short)Long.parseLong(hexStringB,16);
		//System.out.println(Short.toUnsignedLong(valA));
		//System.out.println(Short.toUnsignedLong(valB));
		return Long.compareUnsigned(valA, valB);
	}

	/**
	 * 16進数文字列をEchonetLite定義でのsigned short 値として解釈した場合の大小比較を行う
	 * @param hexStringA 比較対象Ａ
	 * @param hexStringB 比較対象Ｂ
	 * @return Integer -1:Aの方がBより小さい、0:AとBは等しい、1:Aの方がBより大きい
	 */
	public static Integer compElSignedShort(String hexStringA, String hexStringB) {
		short valA = (short)Long.parseLong(hexStringA,16);
		short valB = (short)Long.parseLong(hexStringB,16);
		//System.out.println(valA);
		//System.out.println(valB);
		return Long.compare(valA, valB);
	}


	/**
	 * 16進数文字列をEchonetLite定義でのunsigned char 値として解釈した場合の大小比較を行う
	 * @param hexStringA 比較対象Ａ
	 * @param hexStringB 比較対象Ｂ
	 * @return Integer -1:Aの方がBより小さい、0:AとBは等しい、1:Aの方がBより大きい
	 */
	public static Integer compElUnsignedChar(String hexStringA, String hexStringB) {
		Byte valA = (byte)Long.parseLong(hexStringA,16);
		Byte valB = (byte)Long.parseLong(hexStringB,16);
		//System.out.println(Byte.toUnsignedLong(valA));
		//System.out.println(Byte.toUnsignedLong(valB));
		return Long.compareUnsigned(valA, valB);
	}


	/**
	 * 16進数文字列をEchonetLite定義でのsigned char 値として解釈した場合の大小比較を行う
	 * @param hexStringA 比較対象Ａ
	 * @param hexStringB 比較対象Ｂ
	 * @return Integer -1:Aの方がBより小さい、0:AとBは等しい、1:Aの方がBより大きい
	 */
	public static Integer compElSignedChar(String hexStringA, String hexStringB) {
		byte valA = (byte)Long.parseLong(hexStringA,16);
		byte valB = (byte)Long.parseLong(hexStringB,16);
		//System.out.println(valA);
		//System.out.println(valB);
		return Long.compare(valA, valB);
	}

	/**
	 * Edtにおける年月日の値をチェック（月,日をチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkEdtYmdFormat(String strEdt) {
		//String strEdtYear = strEdt.substring(0, 4);
		String strEdtMonth = strEdt.substring(4, 6);
		String strEdtDate = strEdt.substring(6, 8);
		//System.out.println("月：" + strEdtMonth);
		//System.out.println("日：" + strEdtDate);
		if(!( compElUnsignedChar(strEdtMonth, "00") > 0 && compElUnsignedChar(strEdtMonth , "0C") <= 0)) return false;

		HashMap<String,String> endOfMonthMap = new HashMap<String,String>() {
			{
				put("01","1F"); //1月
				put("02","1C"); //2月
				put("03","1F"); //3月
				put("04","1E"); //4月
				put("05","1F"); //5月
				put("06","1E"); //6月
				put("07","1F"); //7月
				put("08","1F"); //8月
				put("09","1E"); //9月
				put("0A","1F"); //10月
				put("0B","1E"); //11月
				put("0C","1F"); //12月
                put("0a","1F"); //10月
                put("0b","1E"); //11月
                put("0c","1F"); //12月
			}

		};
		if(!( compElUnsignedChar(strEdtDate, "00") > 0 && compElUnsignedChar(strEdtDate , endOfMonthMap.get(strEdtMonth)) <= 0)) return false;
		return true;
	}

	/**
	 * Edtにおける時刻の値をチェック（時,分をチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean true:妥当、false:不正
	 */
	public static boolean checkEdtHmFormat(String strEdt) {
		String strEdtHour = strEdt.substring(0, 2);
		String strEdtMin = strEdt.substring(2, 4);
		//System.out.println("時：" + strEdtHour);
		//System.out.println("分：" + strEdtMin);
		if(!( compElUnsignedChar(strEdtHour, "00") >= 0 && compElUnsignedChar(strEdtHour , "17") <= 0)) return false;
		if(!( compElUnsignedChar(strEdtMin, "00") >= 0 && compElUnsignedChar(strEdtMin , "3B") <= 0)) return false;
		return true;
	}

	/**
	 * Edtにおける時刻の値をチェック（時,分,秒をチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean(true:妥当、false:不正)
	 */
	public static boolean checkEdtHmsFormat(String strEdt) {
		String strEdtHour = strEdt.substring(0, 2);
		String strEdtMin = strEdt.substring(2, 4);
		String strEdtSec = strEdt.substring(4, 6);
		//System.out.println("時：" + strEdtHour);
		//System.out.println("分：" + strEdtMin);
		//System.out.println("秒：" + strEdtSec);
		if(!( compElUnsignedChar(strEdtHour, "00") >= 0 && compElUnsignedChar(strEdtHour , "17") <= 0)) return false;
		if(!( compElUnsignedChar(strEdtMin, "00") >= 0 && compElUnsignedChar(strEdtMin , "3B") <= 0)) return false;
		if(!( compElUnsignedChar(strEdtSec, "00") >= 0 && compElUnsignedChar(strEdtSec , "3B") <= 0)) return false;
		return true;
	}


	/**
	 * Edtにおける年月日の設定値をチェック（年,月,日をチェック）
	 * @param strEdt EDT値（16進数文字列）
	 * @return boolean(true:妥当、false:不正)
	 */
	public static boolean checkEdtYmdSetFormat(String strEdt) {
		String strEdtYear = strEdt.substring(0, 4);
		String strEdtMonth = strEdt.substring(4, 6);
		String strEdtDate = strEdt.substring(6, 8);
		//System.out.println("年：" + strEdtMonth);
		//System.out.println("月：" + strEdtMonth);
		//System.out.println("日：" + strEdtDate);
		if(!( compElUnsignedShort(strEdtYear, "00") > 0 && compElUnsignedShort(strEdtYear , "270F") <= 0)) return false;
		if(!( compElUnsignedChar(strEdtMonth, "00") > 0 && compElUnsignedChar(strEdtMonth , "0C") <= 0)) return false;

		HashMap<String,String> endOfMonthMap = new HashMap<String,String>() {
			{
				put("01","1F"); //1月
				put("02","1C"); //2月
				put("03","1F"); //3月
				put("04","1E"); //4月
				put("05","1F"); //5月
				put("06","1E"); //6月
				put("07","1F"); //7月
				put("08","1F"); //8月
				put("09","1E"); //9月
				put("0A","1F"); //10月
				put("0B","1E"); //11月
				put("0C","1F"); //12月
                put("0a","1F"); //10月
                put("0b","1E"); //11月
                put("0c","1F"); //12月
			}

		};
		if(!( compElUnsignedChar(strEdtDate, "00") > 0 && compElUnsignedChar(strEdtDate , endOfMonthMap.get(strEdtMonth)) <= 0)) return false;
		return true;
	}


	/**
	 * Ipv6のアドレスの整形（全桁数表示：1hextectあたり0埋め4桁）
	 * @param ipv6Address IPv6アドレス
	 * @return Ipv6の文字列表現
	 * @throws UnknownHostException 未知のホストの場合
	 */
	public static String ipv6FullString(String ipv6Address) throws UnknownHostException{
		Inet6Address inet6Address = (Inet6Address)Inet6Address.getByName(ipv6Address);
		return ipv6FullString(inet6Address);
	}

	/**
	 * Ipv6のアドレスの整形（全桁数表示：1hextectあたり0埋め4桁）
	 * @param inet6Addr inet6Addrアドレスオブジェクト
	 * @return Ipv6の文字列表現
	 */
	public static String ipv6FullString(Inet6Address inet6Addr) {

		String strAddress = inet6Addr.getHostAddress();
		String[] elems = strAddress.split(":");
		List<String> arrayList = new ArrayList<String>();

		for(String elem : elems) {
			arrayList.add(String.format("%4s", elem).replace(" ", "0").toUpperCase());
		}

		return String.join(":", arrayList);
	}



	// デバッグ用ドライバ
	public static void main(String[] args) {


		// 家庭用エアコンクラス (0x0130)の例
		Set<String> propSet2 = new HashSet<String>();
		propSet2.add("80");
		propSet2.add("81");
		propSet2.add("82");
		propSet2.add("83");
		propSet2.add("87");
		propSet2.add("88");
		propSet2.add("89");
		propSet2.add("8A");
		propSet2.add("8B");
		propSet2.add("8C");
		propSet2.add("8D");
		propSet2.add("8E");
		propSet2.add("8F");
		propSet2.add("90");
		propSet2.add("9A");
		propSet2.add("9B");
		propSet2.add("9C");
		propSet2.add("9D");
		propSet2.add("9E");
		propSet2.add("9F");
		propSet2.add("B0");
		propSet2.add("B3");

		ElLog.i(TAG, convPropMapSetToHexStr(propSet2));
		// 正解は「160B010109000000010101030303030303」


		// スイッチクラス(0x05FD)の例
		Set<String> propSet1 = new TreeSet<String>();
		propSet1.add("80");
		propSet1.add("81");
		propSet1.add("82");
		propSet1.add("83");
		propSet1.add("88");
		propSet1.add("8A");
		propSet1.add("9D");
		propSet1.add("9E");
		propSet1.add("9F");
		propSet1.add("E0");
		ElLog.i(TAG, convPropMapSetToHexStr(propSet1));
		// 正解は「0A80818283888A9D9E9FE0」


		ElLog.Builder log= ElLog.getBuilder().i(TAG);
		log.appendLine("--------------------------------------------------------");
		String[] props1 = convHexStrToEachPropValues("0A80818283888A9D9E9FE0");
		for(int i = 0 ; i < props1.length; i++) {
			log.appendLine(props1[i]);
		}
		log.appendLine("--------------------------------------------------------");
		String[] props2 = convHexStrToEachPropValues("160B010109000000010101030303030303");
		for(int i = 0 ; i < props2.length; i++) {
			log.appendLine(props2[i]);
		}
		log.flush();
	}
}
