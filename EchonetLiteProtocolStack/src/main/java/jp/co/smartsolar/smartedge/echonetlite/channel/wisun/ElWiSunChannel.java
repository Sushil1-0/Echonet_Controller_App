package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

import java.io.IOException;
import java.net.InetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElLog;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.channel.ElChannelBase;
import jp.co.smartsolar.smartedge.echonetlite.protocol.ElProtocol;
import jp.co.smartsolar.smartedge.echonetlite.protocol.ElProtocolImpl;

/**
 * UDPプロトコルによる通信チャンネルクラス
 */
public class ElWiSunChannel extends ElChannelBase implements WiSunDriverListener {
	 protected static Logger logger = LoggerFactory.getLogger(ElWiSunChannel.class);
	
	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElWiSunChannel";

	/**
	 * UDP通信ハンドラ（待受けポート番号に紐づく識別番号）
	 */
	public static final int UDP_HANDLE = 1;

    /**
     *  チャンネルマスク.
     */
    private static final int CHANNEL_MASK = 0xFFFFFFFF;
    /**
     * スキャン時間.
     */
    private static final int SEARCH_DURATION = 6;		//8

	/**
	 * ソケットのオープン・クローズの指示フラグ
	 */
	private boolean isDirectedRunning;

	/**
	 * WiSunDriverクラスのオブジェクト
	 */
	private WiSunDriver wsDriver;

	/** UDP送信の暗号化設定. */
	private static int isUnicastEncrypted = 2;

	/** UDP送信の暗号化設定. */
	private static int isMulticastEncrypted = 1;

	/** 受信スレッド動作中. */
	private boolean isRecvThreadRunning;

	/**
     * コンストラクタ
     * @param portName シリアルポートのポート名
	 * @throws Exception
	 */
    public ElWiSunChannel(String portName) throws Exception {
		super(null);

		wsDriver = new WiSunDriver(portName);
    	// ドライバ用イベントリスナの登録
		wsDriver.addEventListener(this);
		// 自身のIPアドレスを取得
		localIpAddress = wsDriver.getLocalAddress();
		logger.info("ElWiSunChannel localIpAddress : "+ localIpAddress );
		isDirectedRunning = true;	
	}

    /**
     * Dispose.
     */
    public void dispose() {
		if (isDirectedRunning) {
			wsDriver.dispose();
			wsDriver = null;
			isDirectedRunning = false;
		}
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getMultiCastAddress() {
    	return ElProtocol.MULTI_CAST_ADDRESS_V6;
    }

	/**
	 * BルートIDを設定する
	 * @param brouteId ＢルートID
	 */
	public synchronized void setBrouteId(String brouteId) {
		if (!isDirectedRunning) return;

		// Bルート PANA認証の設定
		wsDriver.setRouteBID(brouteId);
		ElLog.t(TAG,"BROUTE ID : ["  +  brouteId + "]", false);
	}

	/**
	 * Bルート認証パスワードを設定する
	 * @param broutePwd Ｂルート認証用パスワード
	 */
	public synchronized void setBroutePwd(String broutePwd) {
		if (!isDirectedRunning) return;

		// Bルート PANA認証の設定
		wsDriver.setPassword(broutePwd);
		ElLog.t(TAG,"BROUTE PWD : ["  +  broutePwd + "]", false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void open() throws Exception{
		ElLog.d(TAG, "started.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws Exception{
		ElLog.d(TAG, "stopped.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean isOpened() {
		return isRecvThreadRunning;
	};

	/**
	 * Wi-SUNイベントリスナ登録
	 *
	 * @param listener
	 */
	public synchronized void addWiSunDriverListener(WiSunDriverListener listener) {
		if (!isDirectedRunning) return;

		wsDriver.addEventListener(listener);
	}

	/**
	 * Wi-SUNイベントリスナ解除
	 *
	 * @param listener
	 */
	public synchronized void removeWiSunDriverListener(WiSunDriverListener listener) {
		if (!isDirectedRunning) return;

		wsDriver.removeEventListener(listener);
	}

	/**
	 * ペアリングスキャンのシーケンス
	 */
	public synchronized void scaningSequence() {
		if (!isDirectedRunning) return;

//		 ペアリングスキャン
		wsDriver.startPairingScan(CHANNEL_MASK, SEARCH_DURATION);
	}

	/**
	 * BルートPANA認証のシーケンス<br>
	 * アプリケーションが判断する場合はこの処理をオーバーライドする。
	 * @param channel
	 * @param panId
	 * @param remoteIp
	 * @param macSecurity MAC層ブロードキャストに対するセキュリティ制御
	 * <ul>
	 * <li> true - 全てのIPマルチキャストを暗号化する
	 * <li> false - 全てのIPマルチキャストを暗号化しない
	 * </ul>
	 */
	public synchronized void connectBRoute(String channel, String panId, String remoteIp, boolean macSecurity) {
		if (!isDirectedRunning) return;

		sleep(5000);
		logger.info("test connectBRoute thread sleep and wait for event 22");

		ElLog.d(TAG, "start connectBRoute");
		logger.info("connectBRoute >> start");

		// チャンネル設定
		wsDriver.setChannel(Integer.parseInt(channel, 16));
		
		sleep(1000);
		// PAN IDの設定
		wsDriver.setPANID(Integer.parseInt(panId, 16));
		
		// MAC層セキュリティ制御設定
//		wsDriver.setBroadcastSecurityFlag(macSecurity);

		// 接続開始（SKJOIN）
//		remoteIp = "FE80:0000:0000:0000:1207:23FF:FEA0:A1CB";
		sleep(1000);
		wsDriver.joinClient(remoteIp); // 接続先のIPv6アドレスを入力

		logger.info("connectBRoute >> end");
		ElLog.d(TAG, " end connectBRoute");
	}
	
	public void sleep(int milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			logger.info("Thread sleep exception : " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * PANAセッションの切断.
	 */
	public synchronized void termPanaSession() {
		if (!isDirectedRunning) return;

		wsDriver.termSession();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void send(ElFrame frame) throws IOException{
		if (!isRecvThreadRunning) return;

		//	送信用のフレーム（16進数文字列）を構成
		String hexStrFrame = frame.buildEchonetFrameHexStr();
		//		 イベント処理をコール
		getElProcess().getElBulkEventProcessor().onSendFrame(frame);

		//	 送信用フレームのバイト配列を作成
		byte[] bMessage = ElUtil.hexStringToBins(hexStrFrame);

		String remoteHostName = frame.getRemoteHostName();

		if(InetAddress.getByName(getMultiCastAddress()).equals(InetAddress.getByName(remoteHostName))) {
			//	マルチキャストの場合
			wsDriver.sendMulti(UDP_HANDLE, ECHONET_PORT, isMulticastEncrypted, bMessage);
		} else {
			//	 ユニキャストの場合
			String ipv6Address = ElUtil.ipv6FullString(frame.getRemoteHostName());
			wsDriver.sendTo(UDP_HANDLE, ipv6Address, ECHONET_PORT, isUnicastEncrypted,  bMessage);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void receive() throws Exception{ }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void startSocketRcvThread() {
		if (!isDirectedRunning) return;

		isRecvThreadRunning = true;
		ElLog.w(TAG,"Wi-SUN経由のUDP受信用スレッドを開始しました。", true);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void stopSocketRcvThread() {
		if (!isDirectedRunning) return;

		isRecvThreadRunning = false;
		ElLog.w(TAG,"Wi-SUN経由のUDP受信用スレッドを停止しました。", true);
	}

	@Override
	public void receivePong(String ipAddr) {}
	@Override
	public void receivePan(WiSunPan pan) {}
	@Override
	public void receiveEaddrPackets(int mode, String[] data) {}
	@Override
	public void receiveChannelInfo(ChannelInfo[] info) {}

	@Override
	public synchronized void onReceiveUdp(String srcIpAddr, String dstIpAddr, int srcPort, int dstPort, String srcMacAddr, String data) {
		if (!isRecvThreadRunning) return;

		if (dstPort == ElProtocol.ECHONET_PORT) {
			// 送信元が自ノードの場合、処理中止。
			if (localIpAddress.equalsIgnoreCase(srcIpAddr)) {
				return ;
			}

			// パケットのサイズがフレーム最小値以下の場合、処理中止。
			// この時点で既に16進数文字列である為、そのサイズは、バイトサイズ×２と比較する
			if(data.length() < ElFrame.MIN_FRAME_BYTE_SIZE * 2) {
				return ;
			}

			ElLog.t(TAG, "Recieved message : [" + data + "]");

			// 受信したフレームをフレームオブジェクトにセット
			ElJob job = new ElJob(data, srcIpAddr, this);
			// キューに登録
			pushJobToQueue(job);
		}
	}

	@Override
	public void onEvent(int event, String sender) {
		if (!isRecvThreadRunning) return;

		if(event == 37) {
			try {
				getElProcess().getLocalNode().getNodeProfile().inf().reqInfInstanceListNotification().send();
			} catch(Exception ex) {
			}
		}
	}

	@Override
	public void onCompleteSendUdp(String ipAddr, String param) {}
	@Override
	public void onCompleteEDScan(String ipAddr) {}
	@Override
	public void onCompleteActiveScan(String ipAddr) {}
	@Override
	public void onCatchException(Exception ex) {}

	/**
	 * Echonetフレーム受信時の処理を記述（下位レイヤー依存部分の記述）<br>
	 * ジョブの下位プロトコル依存部分を記述する
	 */
	public class ElJob extends ElProtocolImpl.ElJobImpl{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElWiSunChannel.ElJob";

		/**
		 * 通信チャンネルオブジェクト
		 */
		ElWiSunChannel channel;

		/**
		 * コンストラクタ
		 * @param recievedMsg 受信メッセージ
		 * @param remoteHostName 対向ノードのホスト名（IPアドレス）
		 * @param channel 通信チャンネルオブジェクト
		 */
		public ElJob(String recievedMsg, String remoteHostName, ElWiSunChannel channel) {
			super(recievedMsg, remoteHostName, getElProcess());
			this.channel = channel;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void send(ElFrame resFrame) throws IOException{
			channel.send(resFrame);
		}
	}
}

