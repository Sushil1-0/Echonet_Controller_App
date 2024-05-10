package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

/**
 * チャネル情報クラス.
 */
public class ChannelInfo {

	/**
	 * チャンネル番号
	 */
	public int channel;


	/**
	 * RSSI値（受信信号強度インジケータ）
	 */
	public int rssi;

	/**
	 * コンストラクタ
	 * @param channel チャンネル番号
	 * @param rssi RSSI値（受信信号強度インジケータ）
	 */
	public ChannelInfo(int channel, int rssi) {
		this.channel = channel;
		this.rssi = rssi;
	}

	/**
	 * チャンネル番号の取得
	 * @return int チャンネル番号
	 */
	public int getChannel() {
		return channel;
	}

	/**
	 * RSSI値（受信信号強度インジケータ）の取得
	 * @return rssi値
	 */
	public int getRssi() {
		return rssi;
	}

}
