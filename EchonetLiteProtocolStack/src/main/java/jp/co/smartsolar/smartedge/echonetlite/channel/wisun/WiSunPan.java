package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

/** Personal Area Network 情報 */
public interface WiSunPan {
	/**
	 * チャネル番号取得
	 * 
	 * @return チャネル番号
	 */
	int getChannel();

	/**
	 * チャネルページ取得
	 * 
	 * @return チャネルページ
	 */
	int getChannelPage();

	/**
	 * Pan ID取得
	 * 
	 * @return Pan ID
	 */
	int getPanId();

	/**
	 * MACアドレス取得
	 * 
	 * @return MACアドレス
	 */
	String getMacAddress();

	/**
	 * ビーコンの受信RSSI取得
	 * 
	 * @return RSSI
	 */
	int getLqi();

	/**
	 * Pairing ID取得
	 * 
	 * @return Pairing ID
	 */
	String getPairId();

	/**
	 * IPアドレス取得
	 * 
	 * @return IPアドレス
	 */
	String getIpAddress();
}
