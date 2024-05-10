package jp.co.smartsolar.smartedge.echonetlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * エコーネット用フレーム定義用クラス
 */
public class ElFrame {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElFrame";

	/**
	 * フレームの最小バイト数
	 */
	public static final int MIN_FRAME_BYTE_SIZE = 12;

	// ヘッダ情報

	/**
	 * ヘッダ１
	 */
	public static final String EHD1 = "10";
	/**
	 * ヘッダ２
	 */
	public static final String EHD2 = "81";

	// TID
	// 「プロパティ値通知など、応答受信を必要としないメッセージのTID の値については特に規定しない。」
	/**
	 * TIDのデフォルト値
	 */
	public static final String DEFAULT_TID = "0000"; // TIDのデフォルト値（Ver.1.13_02 3.2.2より）

	// ESVコード


	//要求用ESVコード


	/**
	 * プロパティ書込み要求（応答不要）
	 * 一斉同報可
	 */
	public static final String ESV_SETI = "60";
	/**
	 * プロパティ書込み要求（応答要）
	 * 一斉同報可
	 */
	public static final String ESV_SETC = "61";
	/**
	 * プロパティ読み出し要求
	 * 一斉同報可
	 */
	public static final String ESV_GET = "62";
	/**
	 * プロパティ値通知要求
	 * 一斉同報可
	 */
	public static final String ESV_INF_REQ = "63";
	/**
	 * プロパティ値書込み・読出し要求
	 * 一斉同報可
	 */
	public static final String ESV_SET_GET = "6E";


	//応答・通知用ESVコード


	/**
	 * プロパティ値書込み応答
	 * 0x61への応答、個別応答
	 */
	public static final String ESV_SET_RES = "71";
	/**
	 * プロパティ値読み出し応答
	 * 0x62への応答、個別応答
	 */
	public static final String ESV_GET_RES = "72";
	/**
	 * プロパティ値通知
	 * 個別通知、一斉同報通知共に可
	 */
	public static final String ESV_INF = "73";
	/**
	 * プロパティ値通知（応答要）
	 * 個別通知
	 */
	public static final String ESV_INFC = "74";
	/**
	 * プロパティ値通知応答
	 * 0x74の応答、個別応答
	 */
	public static final String ESV_INFC_RES = "7A";
	/**
	 * プロパティ値書き込み・読み出し応答
	 * 0x6Eの応答、個別応答
	 */
	public static final String ESV_SET_GET_RES = "7E";


	// 応答不要ESVコード

	/**
	 * プロパティ値書き込み要求不可応答
	 * 0x60の不可応答、個別応答
	 */
	public static final String ESV_SETI_SNA = "50";
	/**
	 * プロパティ値書き込み要求不可応答
	 * 0x61の不可応答、個別応答
	 */
	public static final String ESV_SETC_SNA = "51";
	/**
	 * プロパティ値読み出し不可応答
	 * 0x62の不可応答、個別応答
	 */
	public static final String ESV_GET_SNA = "52";
	/**
	 * プロパティ値通知不可応答
	 * 0x63の不可応答、個別応答
	 */
	public static final String ESV_INF_SNA = "53";
	/**
	 * プロパティ値書き込み・読み出し不可応答
	 * 0x6Eの不可応答、個別応答
	 */
	public static final String ESV_SET_GET_SNA = "5E";

	/**
	 * 応答なし
	 */
	public static final String ESV_NO_RES = "00";


	/**
	 * サービスコードマップ
	 */
	public static Map<String, String> MAP_ESV_NAME = new HashMap<String, String>() {
		{
			put("60", "SetI");
			put("61", "SetC");
			put("62", "Get");
			put("63", "INF_REQ");
			put("6E", "SetGet");

			put("71", "Set_Res");
			put("72", "Get_Res");
			put("73", "INF");
			put("74", "INFC");
			put("7A", "INFC_Res");
			put("7E", "SetGet_Res");

			put("50", "SetI_SNA");
			put("51", "SetC_SNA");
			put("52", "Get_SNA");
			put("53", "INF_SNA");
			put("5E", "SetGet_SNA");
		}
	};





	/**
	 * 対向ノードのホスト名（IPアドレス）
	 * 　受信フレームの場合の送信元ホスト名(IPアドレス)
	 * 　送信フレームの場合の送信先ホスト名(IPアドレス)
	 */
	private String remoteHostName;

	/**
	 * コンストラクタ
	 */
	public ElFrame() {
		this.strTid = DEFAULT_TID; // TIDデフォルト値を設定
		this.lstEchonetProp = new ArrayList<ElProp>();
		this.lstEchonetPropSet = new ArrayList<ElProp>();
		this.lstEchonetPropGet = new ArrayList<ElProp>();
	}

	/** EHD1の値(16進数文字列表現). */
	private String strEHD1;

	/** EHD2の値(16進数文字列表現). */
	private String strEHD2;

	/** TIDの値(16進数文字列表現). */
	private String strTid;

	/** SEOJのグループコード(16進数文字列表現). */
	private String strSrcGroupCd;

	/** SEOJのクラスコード(16進数文字列表現). */
	private String strSrcClassCd;

	/** SEOJのエンティティコード(16進数文字列表現). */
	private String strSrcEntityCd;

	/** DEOJのグループコード(16進数文字列表現). */
	private String strDstGroupCd;

	/** DEOJのクラスコード(16進数文字列表現). */
	private String strDstClassCd;

	/** DEOJのエンティティコード(16進数文字列表現). */
	private String strDstEntityCd;

	/** ESVコード(16進数文字列表現). */
	private String strEsv;

	/** OPCの値(16進数文字列表現). */
	private String strOpc;

	/** プロパティオブジェクト格納用. */
	private List<ElProp> lstEchonetProp;


	/** OPCSetの値(16進数文字列表現). */
	private String strOpcSet;

	/** OPCGetの値(16進数文字列表現). */
	private String strOpcGet;

	/** プロパティオブジェクト格納用(SetGetサービス用、Set部分). */
	private List<ElProp> lstEchonetPropSet;

	/** プロパティオブジェクト格納用(SetGetサービス用、Get部分). */
	private List<ElProp> lstEchonetPropGet;

	/**
	 * EHD1値の取得（受信フレームの解析に使用）
	 * @return EHD1値の16進数文字列表記
	 */
	public String getStrEHD1() {
		return strEHD1;
	}

	/**
	 * EHD1値の設定（受信フレームの解析に使用）
	 * @param strEHD1 EHD1値の16進数文字列表記
	 */
	public void setStrEHD1(String strEHD1) {
		this.strEHD1 = strEHD1;
	}

	/**
	 * EHD2値の取得（受信フレームの解析に使用）
	 * @return EHD2値の16進数文字列表記
	 */
	public String getStrEHD2() {
		return strEHD2;
	}

	/**
	 * EHD1値の設定（受信フレームの解析に使用）
	 * @param strEHD2 EHD2値の16進数文字列表記
	 */
	public void setStrEHD2(String strEHD2) {
		this.strEHD2 = strEHD2;
	}

	/**
	 * TID値の取得
	 * @return TID値の16進数文字列表記
	 */
	public String getStrTid() {
		return strTid;
	}

	/**
	 * TID値の設定
	 * @param strTid TID値の16進数文字列表記
	 */
	public void setStrTid(String strTid) {
		this.strTid = strTid;
	}

	/**
	 * SEOJコード（16進数文字列表記）の取得<br>
	 * クラスグループコード＋クラスコード＋エンティティコード
	 * @return SEOJコード
	 */
	public String getStrSeoj() {
		return this.strSrcGroupCd + this.strSrcClassCd + this.strSrcEntityCd;
	}

	/**
	 * SEOJコードの設定（16進数文字列表記）<br>
	 * 内部で、クラスグループコード＋クラスコード＋エンティティコードに分離して格納
	 * @param strSeoj SEOJコード
	 */
	public void setStrSeoj(String strSeoj) {
		this.strSrcGroupCd = strSeoj.substring(0, 2);
		this.strSrcClassCd = strSeoj.substring(2, 4);
		this.strSrcEntityCd = strSeoj.substring(4, 6);
	}

	/**
	 * DEOJコードの取得（16進数文字列表記）<br>
	 * クラスグループコード＋クラスコード＋エンティティコード
	 * @return DEOJコード
	 */
	public String getStrDeoj() {
		return this.strDstGroupCd + this.strDstClassCd + this.strDstEntityCd;
	}

	/**
	 * DEOJコードの設定（16進数文字列表記）<br>
	 * 内部で、クラスグループコード＋クラスコード＋エンティティコードに分離して格納
	 * @param strDeoj DEOJコード
	 */
	public void setStrDeoj(String strDeoj) {
		this.strDstGroupCd = strDeoj.substring(0, 2);
		this.strDstClassCd = strDeoj.substring(2, 4);
		this.strDstEntityCd = strDeoj.substring(4, 6);
	}

	/**
	 * SEOJのクラスグループコードを取得
	 * @return SEOJのクラスグループコード
	 */
	public String getStrSrcGroupCd() {
		return strSrcGroupCd;
	}

	/**
	 * SEOJのグループコードを設定
	 * @param strRcvSGroupCd クラスグループコード
	 */
	public void setStrSrcGroupCd(String strRcvSGroupCd) {
		this.strSrcGroupCd = strRcvSGroupCd;
	}

	/**
	 * SEOJのクラスコードを取得
	 * @return SEOJのクラスコード（16進数文字列）
	 */
	public String getStrSrcClassCd() {
		return strSrcClassCd;
	}

	/**
	 * SEOJのクラスコードの設定
	 * @param strRcvSClassCd SEOJのクラスコード
	 */
	public void setStrSrcClassCd(String strRcvSClassCd) {
		this.strSrcClassCd = strRcvSClassCd;
	}

	/**
	 * SEOJのエンティティコードを取得
	 * @return SEOJのエンティティコード（16進数文字列）
	 */
	public String getStrSrcEntityCd() {
		return strSrcEntityCd;
	}

	/**
	 * SEOJのエンティティコードを設定
	 * @param strRcvSEntityCd SEOJのエンティティコード
	 */
	public void setStrSrcEntityCd(String strRcvSEntityCd) {
		this.strSrcEntityCd = strRcvSEntityCd;
	}

	/**
	 * DEOJのクラスグループコードを取得
	 * @return DEOJのクラスグループコード（16進数文字列）
	 */
	public String getStrDstGroupCd() {
		return strDstGroupCd;
	}

	/**
	 * DEOJのクラスグループコードを設定
	 * @param strRcvDGroupCd DEOJのクラスグループコード（16進数文字列）
	 */
	public void setStrDstGroupCd(String strRcvDGroupCd) {
		this.strDstGroupCd = strRcvDGroupCd;
	}

	/**
	 * DEOJのクラスコードを取得
	 * @return DEOJのクラスコード（16進数文字列）
	 */
	public String getStrDstClassCd() {
		return strDstClassCd;
	}

	/**
	 * DEOJのクラスコードを設定
	 * @param strRcvDClassCd DEOJのクラスコード（16進数文字列）
	 */
	public void setStrDstClassCd(String strRcvDClassCd) {
		this.strDstClassCd = strRcvDClassCd;
	}

	/**
	 * DEOJのエンティティコードを取得
	 * @return DEOJのエンティティコード（16進数文字列）
	 */
	public String getStrDstEntityCd() {
		return strDstEntityCd;
	}

	/**
	 * DEOJのエンティティコードを設定
	 * @param strRcvDEntityCd DEOJのエンティティコード（16進数文字列）
	 */
	public void setStrDstEntityCd(String strRcvDEntityCd) {
		this.strDstEntityCd = strRcvDEntityCd;
	}

	/**
	 * ESVコードの取得
	 * @return ESVコード（16進数文字列）
	 */
	public String getStrEsv() {
		return strEsv;
	}

	/**
	 * ESVコードの設定
	 * @param strEsv ESVコード（16進数文字列）
	 */
	public void setStrEsv(String strEsv) {
		this.strEsv = strEsv;
	}

	/**
	 * OPC値の取得
	 * @return OPC値（16進数文字列）
	 */
	public String getStrOpc() {
		return strOpc;
	}

	/**
	 * OPC値の設定
	 * @param strOpc OPC値（16進数文字列）
	 */
	private void setStrOpc(String strOpc) {
		this.strOpc = strOpc;
	}

	/**
	 * プロパティオブジェクトのリストの取得
	 * @return プロパティオブジェクトのリスト
	 */
	public List<ElProp> getLstEchonetProp() {
		return lstEchonetProp;
	}

	/**
	 * プロパティオブジェクトのリストの設定
	 * @param lstEchonetProp プロパティオブジェクト
	 */
	public void setLstEchonetProp(List<ElProp> lstEchonetProp) {
		this.lstEchonetProp = lstEchonetProp;
	}

	/**
	 * 対向ノードのリモートホスト名（IPアドレス）の取得
	 * @return リモートホスト名（IPアドレス）
	 */
	public String getRemoteHostName() {
		return remoteHostName;
	}

	/**
	 * 対向ノードのリモートホスト名（IPアドレス）の登録
	 * @param remoteHostName リモートホスト名（IPアドレス）
	 */
	public void setRemoteHostName(String remoteHostName) {
		this.remoteHostName = remoteHostName;
	}


	/**
	 * OPCの値を算出し、16進数文字列として返す
	 * @param lstEchonetProp プロパティオブジェクトのリスト
	 * @return 1バイト分の16進数文字列表記(2文字分)
	 */
	public String countStrOpc(List<ElProp> lstEchonetProp) {

		Integer iSize = lstEchonetProp.size();
		if (iSize.equals(0)) {
			return "00";
		} else {
			return 		String.format("%02X",iSize);
		}

	}


	/**
	 * OPCSet値の取得（SetGetサービス用）
	 * @return OPCSet値（16進数文字列）
	 */
	public String getStrOpcSet() {
		return strOpcSet;
	}

	/**
	 * OPCSet値の設定（SetGetサービス用）
	 * @param strOpcSet OPC値（16進数文字列）
	 */
	public void setStrOpcSet(String strOpcSet) {
		this.strOpcSet = strOpcSet;
	}

	/**
	 * OPCGet値の取得（SetGetサービス用）
	 * @return OPCGet値（16進数文字列）
	 */
	public String getStrOpcGet() {
		return strOpcGet;
	}

	/**
	 * OPCGet値の設定（SetGetサービス用）
	 * @param strOpcGet OPC値（16進数文字列）
	 */
	public void setStrOpcGet(String strOpcGet) {
		this.strOpcGet = strOpcGet;
	}

	/**
	 * プロパティオブジェクトのリストの取得(SetGetサービスのSetプロパティ用)
	 * @return SetGetサービスのSetプロパティオブジェクトのリスト
	 */
	public List<ElProp> getLstEchonetPropSet() {
		return lstEchonetPropSet;
	}

	/**
	 * プロパティオブジェクトのリストの設定(SetGetサービスのSetプロパティ用)
	 * @param lstEchonetPropSet SetGetサービスのSetプロパティオブジェクトのリスト
	 */
	public void setLstEchonetPropSet(List<ElProp> lstEchonetPropSet) {
		this.lstEchonetPropSet = lstEchonetPropSet;
	}

	/**
	 * プロパティオブジェクトのリストの取得(SetGetサービスのGetプロパティ用)
	 * @return SetGetサービスのGetプロパティオブジェクトのリスト
	 */
	public List<ElProp> getLstEchonetPropGet() {
		return lstEchonetPropGet;
	}

	/**
	 * プロパティオブジェクトのリストの設定(SetGetサービスのGetプロパティ用)
	 * @param lstEchonetPropGet SetGetサービスのGetプロパティオブジェクトのリスト
	 */
	public void setLstEchonetPropGet(List<ElProp> lstEchonetPropGet) {
		this.lstEchonetPropGet = lstEchonetPropGet;
	}

	/**
	 * エコーフレームの16進数文字列表記を構築
	 * @return String エコーフレーム（16進数文字列表記）
	 */
	public String buildEchonetFrameHexStr() {
		String sEchoFrame = "";
		sEchoFrame += EHD1;
		sEchoFrame += EHD2;
		sEchoFrame += this.getStrTid();
		sEchoFrame += this.getStrSeoj();
		sEchoFrame += this.getStrDeoj();
		sEchoFrame += this.getStrEsv();

		// SetGet系サービス意外の場合
		if(! this.isSetGetService()) {

			this.setStrOpc(this.countStrOpc(this.getLstEchonetProp())); // プロパティ一覧をカウントしてOPC値に設定
			sEchoFrame += this.getStrOpc();
			for(ElProp prop : this.getLstEchonetProp()) {
				sEchoFrame += prop.getStrEpc();
				sEchoFrame += prop.getStrPdc();
				sEchoFrame += prop.getStrEdt();
			}
		}
		// SetGet系サービスの場合
		else {

			// Set部分
			this.setStrOpcSet(this.countStrOpc(this.getLstEchonetPropSet()));
			sEchoFrame += this.getStrOpcSet();
			for(ElProp prop : this.getLstEchonetPropSet()) {
				sEchoFrame += prop.getStrEpc();
				sEchoFrame += prop.getStrPdc();
				sEchoFrame += prop.getStrEdt();
			}
			// Get部分
			this.setStrOpcGet(this.countStrOpc(this.getLstEchonetPropGet()));
			sEchoFrame += this.getStrOpcGet();
			for(ElProp prop : this.getLstEchonetPropGet()) {
				sEchoFrame += prop.getStrEpc();
				sEchoFrame += prop.getStrPdc();
				sEchoFrame += prop.getStrEdt();
			}

		}
		return sEchoFrame;
	}

	/**
	 * 本フレームオブジェクトのコピーを返す。
	 * @return ElFrame フレームオブジェクト
	 */
	public ElFrame copy() {

		ElFrame newFrame = new ElFrame();
		newFrame.setRemoteHostName(this.getRemoteHostName());
		newFrame.setStrTid(this.getStrTid());
		newFrame.setStrSeoj(this.getStrSeoj());
		newFrame.setStrDeoj(this.getStrDeoj());
		newFrame.setStrEsv(this.getStrEsv());
		newFrame.setStrOpc(this.getStrOpc());

		// プロパティ部分のクローン
		List<ElProp> newPropList = new ArrayList<ElProp>();
		for(ElProp prop : this.getLstEchonetProp()) {
			newPropList.add(prop.copy());
		}
		newFrame.setLstEchonetProp(newPropList);

		return newFrame;
	}

	/**
	 * 受信したフレームの16進数文字列を解析して、フレームオブジェクトを生成する
	 * @param hexStringEchoFrame 16進数文字列形式のフレーム
	 * @return フレームオブジェクト
	 * @throws Exception 受信したフレームが壊れている場合
	 */
	public static ElFrame parseFrame(String hexStringEchoFrame) throws Exception{
		try {
			ElFrame oEf = new ElFrame();

			oEf.setStrEHD1(hexStringEchoFrame.substring(0,2));
			oEf.setStrEHD2(hexStringEchoFrame.substring(2,4));

			oEf.setStrTid(hexStringEchoFrame.substring(4,8));

			oEf.setStrSrcGroupCd(hexStringEchoFrame.substring(8,10));
			oEf.setStrSrcClassCd(hexStringEchoFrame.substring(10,12));
			oEf.setStrSrcEntityCd(hexStringEchoFrame.substring(12,14));

			oEf.setStrDstGroupCd(hexStringEchoFrame.substring(14,16));
			oEf.setStrDstClassCd(hexStringEchoFrame.substring(16,18));
			oEf.setStrDstEntityCd(hexStringEchoFrame.substring(18,20));

			oEf.setStrEsv(hexStringEchoFrame.substring(20,22));

			if(isSetGetService(oEf)) {
				// Set部分解析
				String strHexPropPart = hexStringEchoFrame.substring(22); // OPCSet以降のフレームパーツ
				oEf.setStrOpcSet(strHexPropPart.substring(0,2)); // 先頭１バイトがOPCSet
				strHexPropPart = strHexPropPart.substring(2);

				int iOpcSet = ElUtil.hexStringToInt(oEf.getStrOpcSet());
				while(iOpcSet > 0) {

					ElLog.Builder log= ElLog.getBuilder().t(TAG, true);
					log
					.appendLine("------------------------------------------------------------------------")
					.appendLine("parseFrame")
					.appendLine("------------------------------------------------------------------------")
					.appendLine("property part           : [" + strHexPropPart + "]");

					strHexPropPart = parseProp(oEf.getLstEchonetPropSet(), strHexPropPart);

					log
					.appendLine("remaining property part : [" + strHexPropPart + "]")
					.appendLine("------------------------------------------------------------------------")
					.flush();

					iOpcSet--;
				}

				// Get部分解析
				// この時点でstrHexPropPartにはGet部分が残る。
				oEf.setStrOpcGet(strHexPropPart.substring(0,2)); // 先頭１バイトがOPCSet
				strHexPropPart = strHexPropPart.substring(2);

				int iOpcGet = ElUtil.hexStringToInt(oEf.getStrOpcGet());
				while(iOpcGet > 0) {

					ElLog.Builder log= ElLog.getBuilder().t(TAG, true);
					log
					.appendLine("------------------------------------------------------------------------")
					.appendLine("parseFrame")
					.appendLine("------------------------------------------------------------------------")
					.appendLine("property part           : [" + strHexPropPart + "]");

					strHexPropPart = parseProp(oEf.getLstEchonetPropGet(), strHexPropPart);

					log
					.appendLine("remaining property part : [" + strHexPropPart + "]")
					.appendLine("------------------------------------------------------------------------")
					.flush();

					iOpcGet--;
				}

			} else {
				oEf.setStrOpc(hexStringEchoFrame.substring(22,24));

				// memo: 「処理対象プロパティカウンタが取りうる最小値は１」
				//「SetGet_SNA の場合のみ処理対象プロパティカウンタの値は０となることがある。」
				//「ECHONET-Lite_Ver.1.13_02.pdf」（３．２．６）より

				// プロパティ部分の解析
				String strHexPropPart = hexStringEchoFrame.substring(24);
				while(strHexPropPart.length() > 0) {
					ElLog.Builder log= ElLog.getBuilder().t(TAG, true);
					log
					.appendLine("------------------------------------------------------------------------")
					.appendLine("parseFrame")
					.appendLine("------------------------------------------------------------------------")
					.appendLine("property part           : [" + strHexPropPart + "]");

					strHexPropPart = parseProp(oEf.getLstEchonetProp(), strHexPropPart);

					log
					.appendLine("remaining property part : [" + strHexPropPart + "]")
					.appendLine("------------------------------------------------------------------------")
					.flush();
				}
			}
			return oEf;
		} catch(StringIndexOutOfBoundsException ex) {
			throw new Exception("受信したフレームは壊れています。");
		}
	}

	/**
	 * 受信したフレームのプロパティ部分の16進数文字列を解析して、<br>
	 * プロパティオブジェクトを生成登録する（１プロパティ分を処理）
	 * @param lstEchonetProp フレームオブジェクト。この中に解析したプロパティのオブジェクトを登録する。
	 * @param strHexPropPart フレームのプロパティ部分の16進数文字列
	 * @return フレームのプロパティ部分の16進数文字列の残りの部分
	 * @throws Exception 受信したフレームのプロパティ部分の整合性が取れていない場合
	 */
	private static String parseProp(List<ElProp> lstEchonetProp , String strHexPropPart ) throws Exception{
		try {
			String sEpc = strHexPropPart.substring(0, 2);
			String sPdc = strHexPropPart.substring(2, 4);
			Integer iPdc = ElUtil.hexStringToInt(sPdc);
			Integer idxEndOfEdt = 4; // Edtの終了位置(4をオフセットとする)

			ElProp oEp;
			String sEdt ="";
			if(iPdc == 0) {
				oEp = new ElProp(sEpc);
			} else {
				idxEndOfEdt = 4 +iPdc * 2;
				sEdt = strHexPropPart.substring(4, idxEndOfEdt );// 4文字目から、[iPdc(バイト) * 2](文字)分取得
				oEp = new ElProp(sEpc, sEdt);
			}
			lstEchonetProp.add(oEp);// フレームオブジェクトに格納

			ElLog.Builder log= ElLog.getBuilder().t(TAG, true);
			log
			.appendLine("------------------------------------------------------------------------")
			.appendLine("ParseProp")
			.appendLine("------------------------------------------------------------------------")
			.appendLine(" epc   : [" + sEpc + "]")
			.appendLine(" pdc   : [" + sPdc + "]" + " pdc(decimal) : [" + iPdc.toString() + "]")
			.appendLine(" edt   : [" + sEdt + "]")
			.appendLine("-----------------------------------------------------------------------")
			.flush();
			return strHexPropPart.substring(idxEndOfEdt);
		}catch( StringIndexOutOfBoundsException ex ){
			throw new Exception("受信したフレームのプロパティ部分の整合性が取れていません。");
		}
	}


	/**
	 * 応答用フレームのデフォルトの状態に初期化
	 * @return 応答用フレームオブジェクト
	 */
	public ElFrame generateResponseFrame() {
		ElFrame responseFrame = new ElFrame();
		responseFrame.setStrSeoj(this.getStrDeoj()); // SEOJはリクエストのDEOJ
		responseFrame.setStrDeoj(this.getStrSeoj()); // DEOJはリクエストのSEOJ
		responseFrame.setStrTid(this.getStrTid()); // TIDはリクエストと同じ値をセット
		responseFrame.setRemoteHostName(this.getRemoteHostName()); // リモートのIPはそのまま
		responseFrame.setStrEsv(this.getDefaultResponseEsv()); // デフォルトのESVをセット
		return responseFrame;
	}

	/**
	 * 応答用フレームのデフォルトのESVを返す。
	 * @return 応答用フレームのESV値（16進数文字列）
	 */
	private String getDefaultResponseEsv() {
		String strEsv = ESV_NO_RES;

		switch(this.getStrEsv()) {
			case ESV_SETI:
				strEsv = ESV_NO_RES;
				break;
			case ESV_SETC:
				strEsv = ESV_SET_RES;
				break;
			case ESV_GET:
				strEsv =ESV_GET_RES;
				break;
			case ESV_INF_REQ:
				strEsv = ESV_INF;
				break;
			case ESV_INFC:
				strEsv = ESV_INFC_RES;
				break;
			case ESV_SET_GET:
				strEsv = ESV_SET_GET_SNA;
				break;
			default:
				strEsv = ESV_NO_RES;
				break;
		}
		return strEsv;
	}

	/**
	 * 応答用プロパティの設定
	 * @param strEpc EPCの値（16進数文字列）
	 */
	public void addPropForResponse(String strEpc) {
		addPropForResponse(strEpc, null);
	}

	/**
	 * 応答用プロパティの設定
	 * @param strEpc EPCの値（16進数文字列）
	 * @param strEdt EPCの値（16進数文字列）
	 */
	public void addPropForResponse(String strEpc, String strEdt) {
		ElProp prop = new ElProp(strEpc, strEdt);
		addPropForResponse(prop);
	}

	/**
	 * 応答用プロパティの設定
	 * @param prop プロパティオブジェクト
	 */
	public void addPropForResponse(ElProp prop) {

		this.lstEchonetProp.add(prop);
		switch(this.strEsv) {

			case ESV_NO_RES: case ESV_SETI_SNA:
				if(! "00".equalsIgnoreCase(prop.getStrPdc())) {
					this.strEsv = ESV_SETI_SNA;
				}
				break;
			case ESV_SET_RES: case ESV_SETC_SNA:
				if(! "00".equalsIgnoreCase(prop.getStrPdc())) {
					this.strEsv = ESV_SETC_SNA;
				}
				break;
			case ESV_GET_RES: case ESV_GET_SNA:
				if("00".equalsIgnoreCase(prop.getStrPdc())) {
					this.strEsv = ESV_GET_SNA;
				}
				break;
			case ESV_INF: case ESV_INF_SNA:
				if("00".equalsIgnoreCase(prop.getStrPdc())) {
					this.strEsv = ESV_INF_SNA;
				}
				break;
		}
	}


	/**
	 * SetGet関連サービスのフレームであるか否かを返す
	 * @param oEf フレームオブジェクト
	 * @return boolean(true : SetGet関連サービス、false : 非SetGet関連サービス)
	 */
	public static boolean isSetGetService(ElFrame oEf) {

		if(oEf.getStrEsv().equalsIgnoreCase(ESV_SET_GET)
				 || oEf.getStrEsv().equalsIgnoreCase(ESV_SET_GET_RES)
				 || oEf.getStrEsv().equalsIgnoreCase(ESV_SET_GET_SNA)){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * SetGet関連サービスのフレームであるか否かを返す（本オブジェクト自身）
	 * @return boolean true : SetGet関連サービス、false : 非SetGet関連サービス
	 */
	public boolean isSetGetService() {
		return isSetGetService(this);
	}

	/**
	 * エコーフレームの16進数文字列表記の構成を出力([]付)
	 * @return String エコーフレーム（16進数文字列表記、[]付）
	 */
	public String getFrameStructureFormat() {
		String sEchoFrame = "";
		sEchoFrame += "[" + EHD1 + "]";
		sEchoFrame += "[" + EHD2 + "]";
		sEchoFrame += "[" + this.getStrTid() + "]";
		sEchoFrame += "[" + this.getStrSeoj() + "]";
		sEchoFrame += "[" + this.getStrDeoj() + "]";
		sEchoFrame += "[" + this.getStrEsv() + "]";

		if(! this.isSetGetService()) {
			sEchoFrame += "[" + this.getStrOpc() + "]";
			for(ElProp prop : this.getLstEchonetProp()) {
				sEchoFrame += "[" + prop.getStrEpc() + "]";
				sEchoFrame += "[" + prop.getStrPdc() + "]";
				sEchoFrame += "[" + prop.getStrEdt() + "]";
				sEchoFrame += "";
			}
		} else {
			sEchoFrame += "[" + this.getStrOpcSet() + "]";
			for(ElProp prop : this.getLstEchonetPropSet()) {
				sEchoFrame += "[" + prop.getStrEpc() + "]";
				sEchoFrame += "[" + prop.getStrPdc() + "]";
				sEchoFrame += "[" + prop.getStrEdt() + "]";
				sEchoFrame += "";
			}
			sEchoFrame += "[" + this.getStrOpcGet() + "]";
			for(ElProp prop : this.getLstEchonetPropGet()) {
				sEchoFrame += "[" + prop.getStrEpc() + "]";
				sEchoFrame += "[" + prop.getStrPdc() + "]";
				sEchoFrame += "[" + prop.getStrEdt() + "]";
				sEchoFrame += "";
			}
		}

		return sEchoFrame;
	}

	/**
	 * エコーフレームの16進数文字列表記の構成を出力(見出し付、[]付)
	 * @return String エコーフレーム（16進数文字列表記、見出し付、[]付）
	 */
	public String getFrameStructureFormatWithTitle() {
		String sEchoFrame = "";
		sEchoFrame += " EHD1:[" + EHD1 + "]";
		sEchoFrame += " EHD2:[" + EHD2 + "]";
		sEchoFrame += " TID:[" + this.getStrTid() + "]";
		sEchoFrame += " SEOJ:[" + this.getStrSeoj() + "]";
		sEchoFrame += " DEOJ:[" + this.getStrDeoj() + "]";
		sEchoFrame += " ESV:[" + this.getStrEsv() + "]";
		if(! this.isSetGetService()) {
			Integer i = 0;
			sEchoFrame += " OPC:[" + this.getStrOpc() + "]";
			for(ElProp prop : this.getLstEchonetProp()) {
				i++;
				sEchoFrame += " prop" + i.toString() + ":[";
				sEchoFrame += " EPC:[" + prop.getStrEpc() + "]";
				sEchoFrame += " PDC:[" + prop.getStrPdc() + "]";
				sEchoFrame += " EDT:[" + prop.getStrEdt() + "]";
				sEchoFrame += " ]";
			}
		} else {
			Integer i = 0;
			sEchoFrame += " OPCSet:[" + this.getStrOpcSet() + "]";
			for(ElProp prop : this.getLstEchonetPropSet()) {
				i++;
				sEchoFrame += " prop" + i.toString() + ":[";
				sEchoFrame += " EPC:[" + prop.getStrEpc() + "]";
				sEchoFrame += " PDC:[" + prop.getStrPdc() + "]";
				sEchoFrame += " EDT:[" + prop.getStrEdt() + "]";
				sEchoFrame += " ]";
			}
			i = 0;
			sEchoFrame += " OPCGet:[" + this.getStrOpcGet() + "]";
			for(ElProp prop : this.getLstEchonetPropGet()) {
				i++;
				sEchoFrame += " prop" + i.toString() + ":[";
				sEchoFrame += " EPC:[" + prop.getStrEpc() + "]";
				sEchoFrame += " PDC:[" + prop.getStrPdc() + "]";
				sEchoFrame += " EDT:[" + prop.getStrEdt() + "]";
				sEchoFrame += " ]";
			}
		}

		return sEchoFrame;
	}

	/**
	 * 要求フレームであるか否かの判定
	 * @return boolena ture : 要求系、false : 通知系
	 */
	public boolean isRequesting() {
		if(	this.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETI)
				 ||this.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SETC)
				 ||this.getStrEsv().equalsIgnoreCase(ElFrame.ESV_GET)
				 ||this.getStrEsv().equalsIgnoreCase(ElFrame.ESV_INF_REQ)
				 ||this.getStrEsv().equalsIgnoreCase(ElFrame.ESV_SET_GET)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 開発用ドライバクラス
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {

//		ElFrame oEf = new ElFrame();
//
////		oEf.setStrSeoj("05FF01");
////		oEf.setStrDeoj("029001");
//		oEf.setStrEsv(ESV_SETI);
//
//		// ECP,PDC,EDTのプロパティセット
//		String EPC_OPERATION_STATUS = "80";
//		String EDT_OPERATION_STATUS_OFF = "30";
//		ElProp prop1 = new ElProp(EPC_OPERATION_STATUS, EDT_OPERATION_STATUS_OFF);
//		oEf.getLstEchonetProp().add(prop1);
//
//		String strFrame = oEf.buildEchonetFrameHexStr();
//		ElLog.i(TAG,strFrame);

		try {
		// SetGetフレームの受信の件
		String receiveMsg =  EHD1 + EHD2 + "0001" + "05FF01" + "029001" + ESV_SETI;
		receiveMsg += "00";
		receiveMsg += "70";
		receiveMsg += "01";
		receiveMsg += "30";
		receiveMsg += "71";
		receiveMsg += "01";
		receiveMsg += "31";

		receiveMsg += "00";

		receiveMsg += "81";
		receiveMsg += "00";
		receiveMsg += "";
		receiveMsg += "82";
		receiveMsg += "00";
		receiveMsg += "";

		ElFrame rcvFrame = ElFrame.parseFrame(receiveMsg);
		System.out.println(rcvFrame.getFrameStructureFormatWithTitle());
		}catch (Exception ex) {
			ex.printStackTrace();
		}

//		ESV_SET_GET_RES
//		ESV_SET_GET_SNA
	}
}
