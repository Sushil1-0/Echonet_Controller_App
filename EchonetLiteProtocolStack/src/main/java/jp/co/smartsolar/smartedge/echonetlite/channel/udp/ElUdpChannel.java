package jp.co.smartsolar.smartedge.echonetlite.channel.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElLog;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess.ElEventListener;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.channel.ElChannelBase;
import jp.co.smartsolar.smartedge.echonetlite.protocol.ElProtocol;
import jp.co.smartsolar.smartedge.echonetlite.protocol.ElProtocolImpl;

/**
 * UDPプロトコルによる通信チャンネルクラス
 */
public class ElUdpChannel extends ElChannelBase {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElUdpChannel";


	/**
	 * UDPの最大パケットサイズ
	 */
    private static Integer MAX_UDP_PACKET_SIZE = 65507;


    /**
	 * マルチキャストソケット
	 */
	private MulticastSocket multicastSocekt = null;


    /**
     * 受信用パケットホルダ
     */
	DatagramPacket recievePacket = new DatagramPacket(new byte[MAX_UDP_PACKET_SIZE], MAX_UDP_PACKET_SIZE);


	/**
	 * ソケットのオープン・クローズの指示フラグ
	 */
	private boolean isDirectedRunning = false;


    /**
     * UDP受信用のスレッド
     */
    private Thread threadUdp;


    /**
     * コンストラクタ
     * @param ipAddress アサインされたローカルIPアドレス
     * @throws Exception IP
     */
    public ElUdpChannel(String ipAddress) throws Exception{
    	super(ipAddress);
    	this.setMultiCastAddress(ipAddress);
    }

    /**
     * このチャンネルで使用するマルチキャストアドレス<br>
     * 	IPv4,IPv6いずれかのマルチキャストアドレスが登録される前提。
     * デフォルト値として、IPv4を適応。
     */
    private String multiCastAddress = MULTI_CAST_ADDRESS_V4;

	/**
	 * マルチキャストアドレスの設定処理<br>
	 * @param ipAddress IPアドレス
	 * @throws Exception IPアドレスが不正の場合
	 */
	public void setMultiCastAddress(String ipAddress) throws Exception{
		InetAddress address = InetAddress.getByName(ipAddress);
		if(address instanceof Inet4Address) {
			this.multiCastAddress = ElProtocol.MULTI_CAST_ADDRESS_V4;
			ElLog.t(TAG, "IPv4のマルチキャストアドレスを設定しました。");
		} else if(address instanceof Inet6Address) {
			this.multiCastAddress = ElProtocol.MULTI_CAST_ADDRESS_V6;
			ElLog.t(TAG, "IPv6のマルチキャストアドレスを設定しました。");
		} else {
			throw new Exception("IPアドレスが不正です。");
		}
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMultiCastAddress() {
    	return this.multiCastAddress;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void open() throws Exception{

		/**
		 *  NICオブジェクトの取得
		 */
		InetAddress inetAddress = InetAddress.getByName(getLocalIpAddress());
		NetworkInterface objNic = NetworkInterface.getByInetAddress(inetAddress);
		if(objNic == null) {
			throw new Exception("ローカルIPアドレスに合致するNetworkInterfaceが見つかりませんでした。");
		}

		/**
		 * ソケットの初期設定
		 */
		this.isDirectedRunning = true; // 起動が指示されたことを指定
//		InetAddress InetAddr = InetAddress.getByName(ElProtocolImpl.MULTI_CAST_ADDRESS);
		InetAddress InetAddr = InetAddress.getByName(this.getMultiCastAddress());
		this.multicastSocekt = new MulticastSocket(ECHONET_PORT);
		this.multicastSocekt.setNetworkInterface(objNic);
//		this.multicastSocekt.setTimeToLive(3);			//added ttl setting 20220113
//		System.out.println("added setTimeToLive(3)");
		this.multicastSocekt.joinGroup(InetAddr);
		this.multicastSocekt.setLoopbackMode(true);
		this.multicastSocekt.setSoTimeout(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws Exception{
		this.isDirectedRunning = false; // 停止が指示されたことを指定
//		InetAddress InetAddr = InetAddress.getByName(ElProtocolImpl.MULTI_CAST_ADDRESS);
		InetAddress InetAddr = InetAddress.getByName(this.getMultiCastAddress());
		if(this.multicastSocekt != null) {
			try {
				this.multicastSocekt.leaveGroup(InetAddr);
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			if(! this.multicastSocekt.isClosed()) {
				this.multicastSocekt.close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOpened() {
		return (this.multicastSocekt != null && ! this.multicastSocekt.isClosed());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send(ElFrame frame) throws IOException{
		// 送信用のフレーム（16進数文字列）を構成
		String hexStrFrame = frame.buildEchonetFrameHexStr();
		// イベント処理をコール
		getElProcess().getElBulkEventProcessor().onSendFrame(frame);

		// 送信用フレームのバイト配列を作成
		byte[] bMessage = ElUtil.hexStringToBins(hexStrFrame);

		// memo : 宛先が自分自身の場合であっても自ノードで受信される為そのままで問題なし。
		if (this.localIpAddress.equals(frame.getRemoteHostName())) {
			// 自ノード宛てに送信
			sendToLocal(hexStrFrame);
		}

		// 他ノードへの送信処理
		if (this.multicastSocekt != null) {

			DatagramPacket packet = new DatagramPacket(bMessage,
					bMessage.length,
					InetAddress.getByName(frame.getRemoteHostName()),
					ECHONET_PORT);
			this.multicastSocekt.send(packet);
			// 宛先がマルチキャストアドレスの場合
//			if(MULTI_CAST_ADDRESS.equals(frame.getRemoteHostName())) {
//				// 自ノード宛てに送信
//				sendToLocal(hexStrFrame);
//			}
		}
	}

	/**
	 * フレームをローカルノードへの送信を実施
	 * @param sRcvMessage 受信フレームの16進数文字列表記
	 * @return void
	 */
	private void sendToLocal(String sRcvMessage) {
		// 受信したフレームをフレームオブジェクトにセット
		ElJob job = new ElJob(sRcvMessage, this.localIpAddress, this);
		// キューに登録
		this.pushJobToQueue(job);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void receive() throws Exception{
		if( ! this.isOpened()) {
			return ;
		}

		/**
		 * 受信
		 */
		try {
			recievePacket.setLength(recievePacket.getData().length);
			this.multicastSocekt.receive(recievePacket);
		} catch(IOException ex) {
			if( isDirectedRunning ) { // 意図した停止に伴う例外ではない場合
				throw ex;
			}
			return ;
		}

		byte[] bBuf = new byte[recievePacket.getLength()];
		
		ElLog.t(TAG,"受信したUDPパケットの長さは：["+ recievePacket.getLength() +"]",false);
		try {
			System.arraycopy(recievePacket.getData(), 0, bBuf, 0, recievePacket.getLength());
		}catch (Exception e) {
			e.printStackTrace();
		}		
		InetAddress remoteAddrObj = recievePacket.getAddress();
		String strRemoteAddr = remoteAddrObj.getHostAddress();

		// 送信元が自ノードの場合、処理中止。
		if(this.isThisFromLocal(strRemoteAddr)) {
			return ;
		}

		// パケットのサイズがフレーム最小値以下の場合、処理中止。
		if(bBuf.length < ElFrame.MIN_FRAME_BYTE_SIZE) {
			return ;
		}

		String sRcvMessage = ElUtil.binsToHexString(bBuf);
		ElLog.t(TAG, "Recieved message : [" + sRcvMessage + "]");

		// 受信したフレームをフレームオブジェクトにセット
		ElJob job = new ElJob(sRcvMessage, strRemoteAddr, this);
		// キューに登録
		this.pushJobToQueue(job);
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void startSocketRcvThread() {
		threadUdp = new Thread(new Runnable() {
			@Override
			public void run() {
				while (isOpened()) {
					try {
						receive();
					}catch(Exception ex) {
						ElEventListener listener = getElProcess().getElBulkEventProcessor();
						if(listener != null) {
							listener.onCatchException(ex); // リスナが設定されている場合は、メインスレッドに例外オブジェクトを渡す。
						} else {
							ex.printStackTrace();// リスナが無い場合はこの場でスタックトレースを出力する。
						}
					}
				}
			}
		}) ;
		threadUdp.start();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void stopSocketRcvThread() {
		// 待ち受けスレッドの停止
		if (threadUdp != null) {
			threadUdp.interrupt();
			try {
				threadUdp.join();
			} catch (Exception ex) {
				ElEventListener listener = getElProcess().getElBulkEventProcessor();
				if(listener != null) {
					listener.onCatchException(ex); // リスナが設定されている場合は、メインスレッドに例外オブジェクトを渡す。
				} else {
					ex.printStackTrace();// リスナが無い場合はこの場でスタックトレースを出力する。
				}
			}
			threadUdp = null;
		}
		ElLog.w(TAG,"UDP受信用スレッドを停止しました。", true);
	}


	/**
	 * 自ノードのアドレスであるか否かを判定する。
	 * @param strRemoteAddr IPアドレス
	 * @return true:自ノード、false:他ノード
	 */
	public boolean isThisFromLocal(String strRemoteAddr){
		return (this.localIpAddress.equalsIgnoreCase(strRemoteAddr));
	}


	/**
	 * Echonetフレーム受信時の処理を記述（下位レイヤー依存部分の記述）<br>
	 * ジョブの下位プロトコル依存部分を記述する
	 */
	public class ElJob extends ElProtocolImpl.ElJobImpl{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ElUdpChannel.ElJob";

		/**
		 * 通信チャンネルオブジェクト
		 */
		ElUdpChannel channel;

		/**
		 * コンストラクタ
		 * @param recievedMsg 受信メッセージ
		 * @param remoteHostName 対向ノードのホスト名（IPアドレス）
		 * @param channel 通信チャンネルオブジェクト
		 */
		public ElJob(String recievedMsg, String remoteHostName, ElUdpChannel channel) {
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
