package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

/**
 * WiSunDriverからのイベント受信用interface。
 */
public interface WiSunDriverListener {

	int EVENT_ACTIVE_SCAN_FINISHED = 0x22;

	int EVENT_PANA_SESSION_CONNECT_FAILED = 0x24;

	int EVENT_PANA_SESSION_CONNECTTED = 0x25;

	int EVENT_PANA_SESSION_TERM_REQ = 0x26;

	int EVENT_PANA_SESSION_TERMINATED = 0x27;

	int EVENT_PANA_SESSION_TERM_TIMEOUT = 0x28;

	int EVENT_PANA_SESSION_EXPIRED = 0x29;

	int EVENT_ECHO_REQUEST = 0x5;

	int EVENT_BEACON_RECEIVED = 0x20;

	int EVENT_ARIB108_START = 0x32;

	int EVENT_ARIB108_END = 0x33;

	/**
	 * WiSunDriverからの非同期イベント受信。
	 *
	 * @param event
	 *            イベント
	 * @param sender
	 *            senderオブジェクト
	 */
	void onEvent(int event, String sender);

	/**
	 * UDPパケット受信。
	 *
	 * @param srcIpAddr
	 *            送信元IPアドレス
	 * @param dstIpAddr
	 *            送信先IPアドレス
	 * @param srcPort
	 *            送信元ポート番号
	 * @param dstPort
	 *            送信先ポート番号
	 * @param srcMacAddr
	 *            送信元MACアドレス
	 * @param data
	 *            受信データ
	 */
	void onReceiveUdp(String srcIpAddr, String dstIpAddr, int srcPort, int dstPort, String srcMacAddr, String data);

	void receiveEaddrPackets(int mode, String[] data);

	void receivePan(WiSunPan pan);

	void receiveChannelInfo(ChannelInfo[] info);

	void receivePong(String ipAddr);

	void onCompleteActiveScan(String ipAddr);

	void onCompleteEDScan(String ipAddr);

	void onCompleteSendUdp(String ipAddr, String param);

	void onCatchException(Exception ex);
}
