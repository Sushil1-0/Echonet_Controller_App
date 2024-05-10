package jp.co.smartsolar.smartedge.echonetlite;

/**
 * EPC,PDC,EDTのプロパティセットを表現
 */
public class ElProp {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElProp";

	/**
	 * EPCの値（16進数文字列表記）
	 */
	private String strEpc;

	/**
	 * PDCの値（16進数文字列表記）
	 */
	private String strPdc;

	/**
	 * EDTの値（16進数文字列表記）
	 */
	private String strEdt;

	/**
	 * コンストラクタ
	 * @param epc EPCの値(16進数文字列表記)
	 * @param edt EDTの値(16進数文字列表記)
	 */
	public ElProp(String epc, String edt) {
		this.setStrEpc(epc);
		this.setStrEdt(edt);
	}
	/**
	 * コンストラクタ
	 * @param epc EPCの値(16進数文字列表記)
	 */
	public ElProp(String epc) {
		this.setStrEpc(epc);
		this.setStrEdt("");
	}

	/**
	 * EPCの値を取得
	 * @return ECPの値（16進数文字列）
	 */
	public String getStrEpc() {
		return strEpc;
	}

	/**
	 * EPCの値を設定
	 * @param strEpc EPCの値（16進数文字列）
	 */
	public void setStrEpc(String strEpc) {
		this.strEpc = strEpc;
	}

	/**
	 * PDCの値を取得
	 * @return PDC値（16進数文字列）
	 */
	public String getStrPdc() {
		return strPdc;
	}

	/**
	 * PDCの値を設定
	 * @param strPdc PDC値（16進数文字列）
	 */
	private void setStrPdc(String strPdc) {
		this.strPdc = strPdc;
	}

	/**
	 * EDT値の取得
	 * @return EDT値（16進数文字列）
	 */
	public String getStrEdt() {
		return strEdt;
	}

	/**
	 * EDT値の設定
	 * @param strEdt EDT値（16進数文字列）
	 */
	public void setStrEdt(String strEdt) {
		// 数値の0やNULL文字の場合は0x00という１バイト分のデータを表す16進数文字列"00"を受け取ることになる。
		if(strEdt == null)strEdt = ""; // データ無しの場合は、16進数文字列としてはブランク
		this.strEdt = strEdt;
		this.setStrPdc(this.calcPdc(strEdt)); // EDTを後から変更する場合はPDCも連動して再計算する
	}

	/**
	 * このオブジェクトのコピーを作成
	 * @return プロパティオブジェクト
	 */
	public ElProp copy() {
		return new ElProp(this.strEpc, this.strEdt);
	}

	/**
	 * フレーム内のプロパティ部（EPC+PDC+EDT）の16進数文字列表記を取得
	 * @return String 16進数文字列表記
	 */
	public String getString() {
		String strRet = "";
		strRet +=this.getStrEpc();
		strRet +=this.getStrPdc();
		strRet +=this.getStrEdt();
		return strRet;
	}

	/**
	 * エコープロパティ部（EPC+PDC+EDT）の16進数文字列表記([]付)
	 * @return String 16進数文字列表記
	 */
	public String getPropStructureFormat(){
		String strRet = "";
		strRet += "[" + this.getStrEpc() + "]";
		strRet += "[" + this.getStrPdc() + "]";
		strRet += "[" + this.getStrEdt() + "]";
		return strRet;
	}

	/**
	 * エコープロパティ部（EPC+PDC+EDT）の16進数文字列表記(見出し付、[]付)
	 * @return String 16進数文字列表記
	 */
	public String getPropStructureFormatWithTitle() {
		String strRet = "";
		strRet += "EPC : [" + this.getStrEpc() + "]";
		strRet += "PDC : [" + this.getStrPdc() + "]";
		strRet += "EDT : [" + this.getStrEdt() + "]";
		return strRet;
	}

	/**
	 * PDC値の算出
	 *  EDTの文字数をカウントし、バイト数を算出、数値文字列として返す。
	 * @param sEdt EDTの値（16進数文字列表記）
	 * @return String PDCの値（16進数文字列表記）
	 */
	public String calcPdc(String sEdt) {

		if (sEdt == null || sEdt.length() == 0 || sEdt.equals("")) {
			return "00";
		}

		Integer iLength = sEdt.length() / 2;

		return String.format("%02X",Integer.valueOf(iLength));

		// ●「整数値を16進数文字列に変換する」
		// http://www.02.246.ne.jp/~torutk/javahow2/integer.html
		// ●「数値をフォーマットして文字列に変換する ( String.format )」
		//http://hensa40.cutegirl.jp/archives/836
	}
}
