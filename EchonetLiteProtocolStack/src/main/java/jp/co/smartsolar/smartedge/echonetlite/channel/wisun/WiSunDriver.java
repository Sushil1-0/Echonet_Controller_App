package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jp.co.smartsolar.smartedge.echonetlite.ElLog;

/**
 * Wi-SUN通信用ドライバ
 */
public class WiSunDriver implements Runnable {
	  protected static Logger logger = LoggerFactory.getLogger(WiSunDriver.class);

	/** ロガー. */
	private static final String TAG  = "WiSunDriver";
	private static final String CMD_TAG = "Command";

	/**
	 * 受信イベントコードのメッセージマップ
	 */
	private static Map<String, String> SK_EVENT_MSG = new HashMap<String, String>() {
		{
			put("01","NSを受信しました");
			put("02","NAを受信しました");
			put("05","Echo Request を受信しました");
			put("1F","EDスキャンが完了しました");
			put("20","Beaconを受信しました");
			put("21","UDP送信処理が完了しました");
			put("22","アクティブスキャンが完了しました");
			put("24","PANAによる接続過程でエラーが発生しました（接続は完了しませんでした）");
			put("25","PANAによる接続が完了しました");
			put("26","接続相手からセッション終了要求を受信しました");
			put("27","PANAセッションの終了に成功しました");
			put("28","PANAセッションの終了要求に対する応答がなくタイムアウトしました（セッションが終了）");
			put("29","セッションのライフタイムが経過して期限切れになりました");
			put("32","ARIB108の送信総和時間の制限が発動しました");
			put("33","送信総和時間の制限が解除されました");
		}
	};

	/**
	 * シリアルポート
	 */
	private SerialPortChannel serialPort;

	/**
	 * シリアル経由での待受けスレッドの有効・無効フラグ
	 */
	private boolean isWiSunEnabled;

	/**
	 * シリアル通信の受信データ処理用スレッド
	 */
	private Thread threadSerialLine = null;

	/**
	 * コンストラクタ
	 * @throws InterruptedException
	 */
	public WiSunDriver(String portName) throws InterruptedException {
		isWiSunEnabled = true;
		serialPort = new SerialPortChannel(portName);
		serialPort.open();

		// スレッドの定義
		threadSerialLine = new Thread(this);
		threadSerialLine.setName("WinSunDriver");
		threadSerialLine.start(); // スレッドスタート
		ElLog.i(TAG,"シリアル通信の受信データ処理用スレッドを開始しました。", true);

		// スタック初期化
		ElLog.i(TAG,"run reset", true);
		reset();
		ElLog.i(TAG,"スタック初期化しました。", true);
	}

	/**
	 * ドライバ終了
	 */
	public synchronized void dispose() {

		termSession();

		isWiSunEnabled = false;
		// 待ち受けスレッドの停止
		if (threadSerialLine != null) {

			threadSerialLine.interrupt();
			try {
				threadSerialLine.join();
			} catch (Exception ex) {
				synchronized (listeners) {
					for (WiSunDriverListener listener : listeners) {
						listener.onCatchException(ex);
					}
				}
			}
			threadSerialLine = null;
		}
		ElLog.i(TAG,"シリアル通信の受信データ処理用スレッドを停止しました。", true);

		synchronized (listeners) {
			listeners.clear();
		}
		serialPort.close();
	}

	// ----------------------------------------------------------------------------------------

	/** 仮想レジスタの設定・表示 */
	private static final String CMD_SKSREG = "SKSREG";
	/** 通信設定の表示 */
	private static final String CMD_SKINFO = "SKINFO";
	/** PAA起動 */
	private static final String CMD_SKSTART = "SKSTART";
	/** ネットワーク参加 */
	private static final String CMD_SKJOIN = "SKJOIN";
	/** 再認証シーケンス開始 */
	private static final String CMD_SKREJOIN = "SKREJOIN";
	/** セッション終了要請 */
	private static final String CMD_SKTERM = "SKTERM";
	/** UDP送信 */
	private static final String CMD_SKSENDTO = "SKSENDTO";
	/** TCP接続要求(未サポート) */
	@SuppressWarnings("unused")
	private static final String CMD_SKCONNECT = "SKCONNECT";
	/** TCP送信(未サポート) */
	@SuppressWarnings("unused")
	private static final String CMD_SKSEND = "SKSEND";
	/** TCP切断(未サポート) */
	@SuppressWarnings("unused")
	private static final String CMD_SKCLOSE = "SKCLOSE";
	/** PING送信 */
	private static final String CMD_SKPING = "SKPING";
	/** アクティブ/EDスキャン */
	private static final String CMD_SKSCAN = "SKSCAN";
	/** セキュリティ用IPアドレス登録 */
	private static final String CMD_SKREGDEV = "SKREGDEV";
	/** IPアドレスエントリ削除 */
	private static final String CMD_SKRMDEV = "SKRMDEV";
	/** 暗号キー登録 */
	private static final String CMD_SKSETKEY = "SKSETKEY";
	/** 暗号キー削除 */
	private static final String CMD_SKRMKEY = "SKRMKEY";
	/** セキュリティ有効・無効設定 */
	private static final String CMD_SKSECENABLE = "SKSECENABLE";
	/** PSK登録 */
	private static final String CMD_SKSETPSK = "SKSETPSK";
	/** パスワードによるPSK生成 */
	private static final String CMD_SKSETPWD = "SKSETPWD";
	/** Route-B ID生成 */
	private static final String CMD_SKSETRBID = "SKSETRBID";
	/** アドレス情報ネイバーキャッシュ登録 */
	private static final String CMD_SKADDNBR = "SKADDNBR";
	/** UDP待ち受けポート設定 */
	private static final String CMD_SKUDPPORT = "SKUDPPORT";
	/** TCP待ち受けポート設定(未サポート) */
	@SuppressWarnings("unused")
	private static final String CMD_SKTCPPORT = "SKTCPPORT";
	/** 仮想レジスタ保存 */
	private static final String CMD_SKSAVE = "SKSAVE";
	/** 仮想レジスタ読出 */
	private static final String CMD_SKLOAD = "SKLOAD";
	/** 仮想レジスタ保存領域初期化 */
	private static final String CMD_SKERASE = "SKERASE";
	/** バージョン表示 */
	private static final String CMD_SKVER = "SKVER";
	/** 内部状態初期化 */
	private static final String CMD_SKRESET = "SKRESET";
	/** テーブル内容表示 */
	private static final String CMD_SKTABLE = "SKTABLE";

	/** SKINFOの結果 */
	private static final String EVT_EINFO = "EINFO";
	/** SKSREG(get)の結果 */
	private static final String EVT_ESREG = "ESREG";
	/** SKVERの結果 */
	private static final String EVT_EVER = "EVER";

	private static final String EMPTY_DATA = "0";


	/** 自端末のMACアドレス(R/W). */
	private static final int S01 = 0x01;
	/** 周波数の論理チャネル(R/W). */
	private static final int S02 = 0x02;
	/** PAN ID(R/W). */
	private static final int S03 = 0x03;
	/** MAC層のセキュリティカウンタ(R). */
	private static final int S07 = 0x07;
	/** Pairing ID(R/W). */
	private static final int S0A = 0x0A;
	/** ビーコン応答の制御フラグ. */
	private static final int S15 = 0x15;
	/** PANAセッションライフタイム. */
	private static final int S16 = 0x16;
	/** 自動再認証フラグ. */
	private static final int S17 = 0x17;
	/** MAC層ブロードキャストに対するセキュリティ制御. */
	private static final int SA0 = 0xA0;
	/** 送信出力. */
	private static final int SA2 = 0xA2;
	/** モジュール転送レート. */
	private static final int SA8 = 0xA8;
	/** 送信時間制限中フラグ. */
	private static final int SFB = 0xFB;
	/** 無線送信の積算時間. */
	private static final int SFD = 0xFD;
	/** エコーバックフラグ. */
	private static final int SFE = 0xFE;
	/** オートロード. */
	private static final int SFF = 0xFF;
	/** ルートBIDサイズ */
	private static final int ROUTEBID_LENGTH = 32;


	public static String CRLF  = "\r\n";

	/** コマンドキューサイズ. */
//	private static final int QUEUE_SIZE = 32;
	private static final int QUEUE_SIZE = 1024;
	/** 受信コマンド分割用セパレータ. */
	private static final Pattern SPLIT_PATTERN = Pattern.compile(" ");

	/** コマンドキュー. */
	private final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(QUEUE_SIZE);
	/** イベントリスナー. */
	private List<WiSunDriverListener> listeners = new ArrayList<WiSunDriverListener>();
	/** PAN情報格納エリア. */
	private final List<WiSunPan> listPan = new ArrayList<WiSunPan>();

	public static final String SEND_MULTI_IPADDR = "FF02:0000:0000:0000:0000:0000:0000:0001";


	private boolean isEmpty(String data) {
		return ((data == null) || data.isEmpty());
	}

	/**
	 * 受信したデータを分析する
	 */
    public void run(){
    	logger.info("WiSunDriver thread run start");
		ElLog.t(TAG, "receive started");
		while (isWiSunEnabled) {
			try {
				try {
					String line = serialPort.readLine();
					ElLog.t(CMD_TAG, "READ :" + line, true);
					logger.info("WiSunDriver line -> " + line);
					// UDP受信の場合
					if(line.startsWith("ERXUDP")) {
						reciveUdpPacket(line);
						continue;
					} else if (line.startsWith("ERXTCP") || line.startsWith("ETCP")) {
						// TCPは未サポート
						continue;
					} else if (line.startsWith("EPONG")) {
						receiveEPong(line);
						continue;
					} else if (line.startsWith("EHANDLE")) {
						continue;
					} else if (line.startsWith("EADDR") || line.startsWith("ENEIGHBOR")) {
						StringBuffer sb = new StringBuffer();

						// Retrieve all data until "OK"
						String element = serialPort.readLine();
						while (!element.startsWith("OK")) {
							sb.append(element + "\n");
							element = serialPort.readLine();
						}

						if (sb.toString().isEmpty()) {
							continue;
						}
						int mode = 0;
						if (line.startsWith("ENEIGHBOR")) {
							mode = 1;
						}
						receiveEaddrData(mode, sb.toString().split("\n"));
						continue;
					} else if (line.startsWith("EPANDESC")) {
						StringBuffer sb = new StringBuffer();
						String element = serialPort.readLine();
						int counter = 0;
						while (!isEmpty(element)) {
							sb.append(element.trim() + "\n");
							if (++counter >= 6) {
								break;
							}
							element = serialPort.readLine();
						}
						WiSunPan pan = WiSunPanImpl.rcvEpandesc(sb.toString().split("\n"), true);
						sendPan(pan);
						continue;
					} else if (line.startsWith("EEDSCAN")) {
						String temp = serialPort.readLine();
						final String[] element = temp.split(" ");

						ChannelInfo[] channelInfos = new ChannelInfo[element.length / 2];
						for (int j = 0; j + 1 < element.length; j++) {
							int channel = getIntFromHex(element[j++]);
							int rssi = getIntFromHex(element[j]);
							channelInfos[j / 2] = new ChannelInfo(channel, rssi);
						}

						sendChannelInfo(channelInfos);
						serialPort.readLine(); // read empty line after channel data
						continue;
					} else if (line.startsWith("EVENT")) {
						receiveEvent(line);
						continue;
					} else if (line.startsWith("SKSREG SFE")) {
						// エコーバック設定時のエコーバックは無視
						continue;
					} else if (isEmpty(line)) {
						logger.info("Empty Line");
						continue;
					}
					queue.add(line);
				} catch( InterruptedException ex ) { // 空でデータ投入待ちの状態でスレッドを停止すると生じる例外
					ElLog.w(TAG,"シリアル通信の受信データ処理用キューを強制終了します。", true);
				}
			} catch(Exception ex) {
				logger.info("read error " + ex.getMessage());
				ElLog.w(TAG, "WiSun read error. " + ex.getMessage());
				synchronized (listeners) {
					for (WiSunDriverListener listener : listeners) {
						listener.onCatchException(ex);
					}
				}
			} finally {
			}
		}
    }

	private void sendPan(final WiSunPan pan) {
		new Thread() {
			@Override
			public void run() {
				synchronized (listeners) {
					for (WiSunDriverListener listener : listeners) {
						listener.receivePan(pan);
					}
				}
			}
		}.start();
	}

	private void sendChannelInfo(final ChannelInfo[] info) {
		new Thread() {
			@Override
			public void run() {
				synchronized (listeners) {
					for (WiSunDriverListener listener : listeners) {
						listener.receiveChannelInfo(info);
					}
				}
			}
		}.start();
	}

	/**
	 * パケット受信リスナー登録
	 *
	 * @param listener
	 *            パケット受信リスナー
	 */
	public void addEventListener(WiSunDriverListener listener) {
		synchronized (listeners) {
			if (!listeners.contains(listener)) {
				listeners.add(listener);
			}
		}
	}

	/**
	 * パケット受信リスナー登録解除
	 *
	 * @param listener
	 *            パケット受信リスナー
	 */
	public void removeEventListener(WiSunDriverListener listener) {
		synchronized (listeners) {
			if (listeners.contains(listener)) {
				listeners.remove(listener);
			}
		}
	}

	/**
	 * EPONGデータを受信する。 EPONG + <SENDER><CRLF>
	 */
	private void receiveEPong(final String packet) {
		new Thread() {
			@Override
			public void run() {
				synchronized (listeners) {
					String[] elements = split(packet);
					if (elements.length > 1) {
						for (WiSunDriverListener listener : listeners) {
							listener.receivePong(elements[1]);
						}
					}
				}
			}
		}.start();
	}

	/**
	 * UDPパケット受信処理
	 *
	 * @param packet
	 *            UDP受信データ
	 */
	private void reciveUdpPacket(final String packet) {
		new Thread() {
			@Override
			public void run() {
				synchronized (listeners) {
					String[] elements = split(packet);

					if (elements.length > 9) {
						String srcAddr = elements[1];
						String dstAddr = elements[2];
						int srcPort = Integer.parseInt(elements[3], 16);
						int dstPort = Integer.parseInt(elements[4], 16);
						String srcMacAddr = elements[5];

						ElLog.t(TAG, "reciveUdpPacket : DATALEN=" + Integer.parseInt(elements[8], 16) + " ,data=" + elements[9]);

						for (WiSunDriverListener listener : listeners) {
							listener.onReceiveUdp(srcAddr, dstAddr, srcPort, dstPort, srcMacAddr, elements[9]);
						}
					}
				}
			}
		}.start();
	}

	/**
	 * EADDRデータ受信処理
	 *
	 * @param packet
	 *            EADDR受信データ
	 */
	private void receiveEaddrData(final int mode, final String packet[]) {
		new Thread() {
			@Override
			public void run() {
				synchronized (listeners) {
					for (WiSunDriverListener listener : listeners) {
						if (listener != null) {
							listener.receiveEaddrPackets(mode, packet);
						}
					}
				}
			}
		}.start();
	}

	/**
	 * 受信イベント解析処理。
	 *
	 * @param event
	 *            受信イベント
	 * @return 解析結果(処理終了(true)/要コマンドキュー挿入(false))
	 */
	private boolean receiveEvent(String event) {
		final String[] elements = split(event);
		if (elements.length < 2) {
			return true;
		}
		ElLog.t(TAG, elements[1] + ":" + SK_EVENT_MSG.get(elements[1]));
		if ((elements[1].equalsIgnoreCase("22")) // アクティブスキャン完了
				|| (elements[1].equalsIgnoreCase("1F")) // EDスキャン完了
				|| (elements[1].equalsIgnoreCase("21"))) { // UDP送信処理が完了した
			new Thread() {
				public void run() {
					synchronized (listeners) {
						for (WiSunDriverListener listener : listeners) {
//							ElLog.i(TAG,"test receiveEvent inside if", true);
							ElLog.i(TAG,"test receiveEvent inside if elements[1]:"+Arrays.toString(elements), true);
							try {
								String ipAdr = null;
								String data = null;
								if (elements.length > 2) {
									ipAdr = elements[2];
								}
								if (elements.length > 3) {
									data = elements[3];
								}
								if (elements[1].equalsIgnoreCase("22")) {
									listener.onCompleteActiveScan(ipAdr);
								} else if (elements[1].equalsIgnoreCase("1F")) {
									listener.onCompleteEDScan(ipAdr);
								} else if (elements[1].equalsIgnoreCase("21")) {
									listener.onCompleteSendUdp(ipAdr, data);
									int res = 0;
									if (data != null) {
										res = getIntFromHex(data);
									}
									if ((res == 1) && (mLastSend != null)) {
										mLastSend.execute(serialPort, queue);
										mLastSend = null;
									}
								}
							} catch (NullPointerException e) {
								ElLog.e(TAG, e.getMessage());
							}
						}
					}
				}
			}.start();

			return false;
		} else if ((elements[1].equalsIgnoreCase("24")) // PANAセッション接続失敗
				|| (elements[1].equalsIgnoreCase("25")) // PANAセッション接続完了
				|| (elements[1].equalsIgnoreCase("26")) // 相手側からセッション終了要求を受信した
				|| (elements[1].equalsIgnoreCase("27")) // PANAセッション終了成功
				|| (elements[1].equalsIgnoreCase("28")) // PANAセッション終了要求タイムアウト
				|| (elements[1].equalsIgnoreCase("20")) // Beaconを受信した
				|| (elements[1].equalsIgnoreCase("05")) // Echo Requestを受信した
				|| (elements[1].equalsIgnoreCase("32")) // ARIB108の送信総和時間の制限が発動した。
				|| (elements[1].equalsIgnoreCase("33")) // 送信総和時間の制限が解除された。
				|| (elements[1].equalsIgnoreCase("29"))) { // セッションのライフタイムが経過して期限切れになった
			new Thread() {
				public void run() {
					synchronized (listeners) {
//						ElLog.i(TAG,"test receiveEvent inside elseif", true);
						ElLog.i(TAG,"test receiveEvent inside elseif elements[1]:"+Arrays.toString(elements), true);
						for (WiSunDriverListener listener : listeners) {
							try {
								String ipAdr = null;
								if (elements.length > 2) {
									ipAdr = elements[2];
								}
								listener.onEvent(Integer.parseInt(elements[1], 16), ipAdr);
							} catch (NullPointerException e) {
								ElLog.e(TAG, e.getMessage());
							}
						}
					}
				}
			}.start();
		} else {
			// Nothing to do.
		}
		return true;
	}

	/** 引数無しのSKSREG(get) */
	private static class SKSREG0 extends Command<String> {
		private final int sreg;

		/**
		 * コンストラクタ.
		 *
		 * @param sreg
		 *            レジスタ
		 */
		private SKSREG0(int sreg) {
			super(CMD_SKSREG);
			this.sreg = sreg;
		}

		@Override
		String processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {

			String line = queue.take();
			if (line.startsWith(EVT_ESREG)) {
				String result = queue.take();
				if (result.startsWith("OK")) {
					String[] elements = split(line);
					return elements[1].trim();
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return String.format("%s S%02X", command, sreg);
		}
	}


	/**
	 * デバイスのMACアドレス取得.
	 *
	 * @return String MACアドレス
	 */
	public synchronized String getMacAddress() {
		Command<String> command = new SKSREG0(S01);
		return command.execute(serialPort, queue);
	}

	/**
	 * デバイスに設定されているチャネル番号取得.
	 *
	 * @return チャネル番号(33 ～ 60)
	 */
	public synchronized int getChannel() {
		Command<String> command = new SKSREG0(S02);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return 0;
		}
		return Integer.parseInt(result.trim(), 16);
	}

	/**
	 * デバイスに設定されているPAN ID取得.
	 *
	 * @return int PAN ID(0x0 ～ 0xFFFF)
	 */
	public synchronized int getPANID() {
		Command<String> command = new SKSREG0(S03);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return 0;
		}
		return Integer.parseInt(result.trim(), 16);
	}

	/**
	 * MAC層セキュリティーのフレームカウンタ取得.
	 *
	 * @return MAC層セキュリティーのフレームカウンタ(0x0 ～ 0xFFFFFFFF)
	 */
	public synchronized long getFrameCounter() {
		Command<String> command = new SKSREG0(S07);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return 0;
		}
		return Long.parseLong(command.execute(serialPort, queue).trim(), 16);
	}

	/**
	 * 拡張ビーコン要求に設定するParingIDの設定を取得.
	 *
	 * @return String Paring ID
	 */
	public synchronized String getPairingId() {
		Command<String> command = new SKSREG0(S0A);
		return command.execute(serialPort, queue);
	}

	/**
	 * ビーコン応答制御フラグ取得.
	 *
	 * @return 応答する(true) / 応答しない(false)
	 */
	public synchronized boolean getBeaconFlag() {
		Command<String> command = new SKSREG0(S15);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return false;
		}
		return Integer.parseInt(result, 16) != 0;
	}

	/**
	 * PANAセッションライフタイム取得.
	 *
	 * @return long PANAセッションライフタイム(0x3C ～ 0xFFFFFFFF)
	 */
	public synchronized long getSessionLifeTime() {
		Command<String> command = new SKSREG0(S16);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return 0;
		}
		return Long.parseLong(result, 16);
	}

	/**
	 * MAC層ブロードキャストに対するセキュリティ制御取得.
	 *
	 * @return 暗号化する(true) / 暗号化しない(false)
	 */
	public synchronized boolean getBroadcastSecurityFlag() {
		Command<String> command = new SKSREG0(SA0);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return false;
		}
		return Integer.parseInt(result, 16) != 0;
	}

	/**
	 * 送信出力取得.
	 *
	 * @return 送信出力(0 ～ 4)
	 *         <ul>
	 *         <li>0 1mW
	 *         <li>1 10mW
	 *         <li>2 20mW(実力値:10dBm)
	 *         <li>3 0.1mW
	 *         <li>4 20mW(実力値:12dBm)
	 *         </ul>
	 */
	public synchronized int getTransmitPower() {
		Command<String> command = new SKSREG0(SA2);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return 0;
		}
		return Integer.parseInt(result, 16);
	}

	/**
	 * 送信時間制限中フラグ取得.
	 *
	 * @return 送信制限中(true) / 送信制限中以外(false)
	 */
	public synchronized boolean getTransmissionTimeLimitFlag() {
		Command<String> command = new SKSREG0(SFB);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return false;
		}
		return Integer.parseInt(result, 16) != 0;
	}

	/**
	 * 無線送信積算時間取得.
	 *
	 * @return BigInteger 無線送信積算時間(ms)
	 */
	public synchronized BigInteger getRadioTransimissionIntegrationTime() {
		Command<String> command = new SKSREG0(SFD);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return new BigInteger(EMPTY_DATA, 16);
		}
		return new BigInteger(result, 16);
	}

	/**
	 * エコーバックフラグ取得.
	 *
	 * @return エコーバックする(true) / エコーバックしない(false)
	 */
	public synchronized boolean getEchoBackFlag() {
		Command<String> command = new SKSREG0(SFE);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return false;
		}
		return Integer.parseInt(result, 16) != 0;
	}

	/**
	 * オートロード設定取得.
	 *
	 * @return 有効(true) / 無効(false)
	 */
	public synchronized boolean getAutoLoadFlag() {
		Command<String> command = new SKSREG0(SFF);
		String result = command.execute(serialPort, queue);
		if (isEmpty(result)) {
			return false;
		}
		return Integer.parseInt(result, 16) != 0;
	}


	/** 引数有りのSKSREG(set). */
	private static class SKSREG1 extends Command<Boolean> {
		private final int sreg;
		private final String value;

		/**
		 * コンストラクタ.
		 *
		 * @param sreg
		 *            レジスタ
		 * @param value
		 *            設定値
		 */
		public SKSREG1(int sreg, String value) {
			super(CMD_SKSREG);
			this.sreg = sreg;
			this.value = value;
		}

		@Override
		Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
			String result = queue.take();
			return (result.startsWith("OK") ? true : false);
		}

		@Override
		public String toString() {
			return String.format("%s S%01X %s", command, sreg, value);		//S%02X
		}
	}

	/**
	 * デバイスに設定されているチャネル番号設定.
	 *
	 * @param channel
	 *            チャネル番号(33 ～ 60)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setChannel(int channel) {
		Command<Boolean> command = new SKSREG1(S02, Integer.toHexString(channel));
		return command.execute(serialPort, queue);
	}

	/**
	 * デバイスに設定されているPAN ID設定.
	 *
	 * @param id
	 * 
	 *            PAN ID(0x0 ～ 0xFFFF)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setPANID(int id) {
		String panId = Long.toHexString((id)).toUpperCase();
//		Command<Boolean> command = new SKSREG1(S03, Long.toHexString((id)));
		Command<Boolean> command = new SKSREG1(S03, panId);
		return command.execute(serialPort, queue);
	}

	/**
	 * 拡張ビーコン要求に設定するPairingIDの設定を設定.
	 *
	 * @param id
	 *            Paring ID
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setPairingId(String id) {
		if (isEmpty(id)) {
			return false;
		}
		Command<Boolean> command = new SKSREG1(S0A, id);
		return command.execute(serialPort, queue);
	}

	/**
	 * ビーコン応答制御フラグ設定.
	 *
	 * @param enable
	 *            応答する(true) / 応答しない(false)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setBeaconFlag(boolean enable) {
		Command<Boolean> command = new SKSREG1(S15, enable ? "1" : "0");
		return command.execute(serialPort, queue);
	}

	/**
	 * PANAセッションライフタイム設定.
	 *
	 * @param time
	 *            PANAセッションライフタイム(0x3C ～ 0xFFFFFFFF)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setSessionLifeTime(long time /* sec */) {
		Command<Boolean> command = new SKSREG1(S16, Long.toHexString(time));
		return command.execute(serialPort, queue);
	}

	/**
	 * MAC層ブロードキャストに対するセキュリティ制御設定.
	 *
	 * @param enable
	 *            暗号化する(true) / 暗号化しない(false)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setBroadcastSecurityFlag(boolean enable) {
		Command<Boolean> command = new SKSREG1(SA0, (enable ? "1" : "0"));
		return command.execute(serialPort, queue);
	}

	/**
	 * 送信出力設定.
	 *
	 * @param level
	 *            送信出力(0 ～ 4)
	 *            <ul>
	 *            <li>0 1mW
	 *            <li>1 10mW
	 *            <li>2 20mW(実力値:10dBm)
	 *            <li>3 0.1mW
	 *            <li>4 20mW(実力値:12dBm)
	 *            </ul>
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setTransmitPower(int level) {
		Command<Boolean> command = new SKSREG1(SA2, Integer.toHexString(level));
		return command.execute(serialPort, queue);
	}

	/**
	 * モジュール転送レート設定.
	 *
	 * @param rate
	 *            <ul>
	 *            <li>0 50Kbps
	 *            <li>1 100Kbps
	 *            </ul>
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setRfTransmitRate(int rate) {
		Command<Boolean> command = new SKSREG1(SA8, ((rate == 1) ? "1" : "0"));
		return command.execute(serialPort, queue);
	}

	/**
	 * 無線送信積算時間リセット.
	 *
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean resetRadioTransimissionIntegrationTime() {
		Command<Boolean> command = new SKSREG1(SFD, "0");
		return command.execute(serialPort, queue);
	}

	/**
	 * エコーバック設定。
	 * <p>
	 * コマンド処理のため、エコーバックはドライバ内でOFF設定で運用する。<br>
	 * そのため、本コマンドは外部に公開しない。
	 * </p>
	 *
	 * @param enable
	 *            エコーバックON(true) / OFF(false)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setEchoBackFlag(boolean enable) {
		Command<Boolean> command = new SKSREG1(SFE, (enable ? "1" : "0"));
		return command.execute(serialPort, queue);
	}

	/**
	 * オートロード設定設定.
	 *
	 * @param enable
	 *            有効(true) / 無効(false)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setAutoLoadFlag(boolean enable) {
		Command<Boolean> command = new SKSREG1(SFF, (enable ? "1" : "0"));
		return command.execute(serialPort, queue);
	}

	/**
	 * ローカルアドレスを取得する。
	 *
	 * @return ローカルIPアドレス
	 */
	public synchronized String getInformation() {
		Command<String> command = new Command<String>(CMD_SKINFO) {
			@Override
			String processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String line = queue.take();
				if (line.startsWith(EVT_EINFO)) {
					@SuppressWarnings("unused")
					String result = queue.take();
					return line;
				}
				return "";
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * ローカルアドレスを取得する。
	 *
	 * @return ローカルIPアドレス
	 */
	public synchronized String getLocalAddress() {
		Command<String> command = new Command<String>(CMD_SKINFO) {
			@Override
			String processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String line = queue.take();
					if (line.startsWith(EVT_EINFO)) {
						String[] elements = split(line);
						@SuppressWarnings("unused")
						String result = queue.take();
						return elements[1];
				}
				return "";
			}
		};
		return command.execute(serialPort, queue);
	}
	
	
	/**
	 * PANAセッション接続要求。
	 *
	 * @param address
	 *            接続先のIPアドレス
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean joinClient(final String address) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKJOIN) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %s", command, address);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 再接続要求。
	 *
	 * <p>
	 * 現在接続中の相手に対して、再認証のシーケンスを開始します。
	 * </p>
	 */
	public synchronized boolean rejoinClient() {
		Command<Boolean> command = new Command<Boolean>(CMD_SKREJOIN) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * PANAセッション終了要求。
	 */
	public synchronized boolean termSession() {
		Command<Boolean> command = new Command<Boolean>(CMD_SKTERM) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定したアドレス宛にICMP Echo requestを送信します。
	 *
	 * @param address
	 *            IPv6アドレス
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean ping(final String address) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKPING) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %s", command, address);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定したチャネルに対してアクティブスキャンを実行する。
	 *
	 * @param mask
	 *            スキャンチャネルのマスク指定
	 * @param duration
	 *            スキャン時間 (0.01 sec * (2^duration + 1))
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean startActiveScan(final int mask, final int duration) {
		listPan.clear();
		Command<Boolean> command = new Command<Boolean>(CMD_SKSCAN) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %X %08X %X", command, 3, mask, duration);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * Pairingスキャン開始。
	 *
	 * @param mask
	 *            スキャンチャネルのマスク指定
	 * @param duration
	 *            スキャン時間 (0.01 sec * (2^duration + 1))
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean startPairingScan(final int mask, final int duration) {
		listPan.clear();
		Command<Boolean> command = new Command<Boolean>(CMD_SKSCAN) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %X %08X %X", command, 2, mask, duration);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定したチャネルに対してEDスキャンを実行する。
	 *
	 * @param mask
	 *            スキャンチャネルのマスク指定
	 * @param duration
	 *            スキャン時間 (0.01 sec * (2^duration + 1))
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean startEDScan(final int mask, final int duration) {
		listPan.clear();
		Command<Boolean> command = new Command<Boolean>(CMD_SKSCAN) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s 0 %08X %X", command, mask, duration);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * セキュリティ運用するため、指定されたIPアドレスを登録する。
	 *
	 * @param ipAddress
	 *            IPアドレス
	 * @return true(成功)/false(登録数が上限)
	 */
	public synchronized boolean registDevice(final String ipAddress) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKREGDEV) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %s", command, ipAddress);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定されたIPアドレスをネイバーテーブル、ネイバーキャッシュから削除します。
	 *
	 * @param ipAddress
	 *            IPアドレス
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean removeDevice(final String ipAddress) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKRMDEV) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %s", command, ipAddress);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定されたindexに対する暗号キー(128bit)を登録する。
	 *
	 * @param index
	 *            キーインデックス
	 * @param key
	 *            128bit暗号キー(32文字)
	 * @return true(成功)/false(失敗)
	 */
	public synchronized boolean setCryptographyKey(final int index, final String key) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKSETKEY) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format(Locale.JAPAN, "%s %d %s", command, index, key);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定されたキーインデックスに対する暗号キー(128bit)を削除する。
	 *
	 * @param index
	 *            キーインデックス
	 * @return 成功(true) / 失敗(false)
	 */
	public synchronized boolean removeCryptographyKey(final int index) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKRMKEY) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format(Locale.JAPAN, "%s %d", command, index);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定したIPアドレス、MACアドレスに対するMAC層のセキュリティの有効・無効を指定する。
	 *
	 * @param flag
	 *            セキュリティ設定
	 * @param ipAddress
	 *            IPアドレス
	 * @param macAddress
	 *            MACアドレス
	 * @return true(成功)/false(失敗)
	 */
	public synchronized boolean setSecurityEnable(final boolean flag, final String ipAddress, final String macAddress) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKSECENABLE) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %d %s %s", command, (flag ? 1 : 0), ipAddress, macAddress);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * PANA認証に用いるPSKを登録する。
	 *
	 * @param psk
	 *            PSK(16BYTE)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setPsk(final String psk) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKSETPSK) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				// int length = 0x10; // lenghtは0x10固定
				return String.format("%s %02X %s", command, psk.length(), psk);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定されたパスワードからPSKを生成して登録する。
	 *
	 * @param pwd
	 *            パスワード(1 ～ 32)
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setPassword(final String pwd) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKSETPWD) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				int len = 0;
				if (pwd != null) {
					len = pwd.length();
				}
				return String.format("%s %01X %s", command, len, pwd);		//%01X
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定されたIDからRoute-B IDを生成して登録する。
	 *
	 * @param id
	 *            ID(32桁)
	 */
	public synchronized boolean setRouteBID(final String id) {
		if (isEmpty(id) || (id.length() != ROUTEBID_LENGTH)) {
			return false;
		}

		Command<Boolean> command = new Command<Boolean>(CMD_SKSETRBID) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %s", command, id);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定されたIPアドレスとMACアドレスをネイバーキャッシュに登録する。
	 *
	 * @param ipAddress
	 *            IPアドレス
	 * @param macAddress
	 *            MACアドレス
	 */
	public synchronized boolean registNeighborCacheAddress(final String ipAddress, final String macAddress) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKADDNBR) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %s %s", command, ipAddress, macAddress);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * ファームウェアバージョン取得。
	 *
	 * @return ファームウェアバージョン
	 */
	public synchronized String getFirmwareVersion() {
		Command<String> command = new Command<String>(CMD_SKVER) {
			@Override
			String processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String line = queue.take();
				if (line.startsWith(EVT_EVER)) {
					String[] elements = split(line);
					@SuppressWarnings("unused")
					String result = queue.take();
					return elements[1];
				}
				return "";
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * プロトコルスタックの内部状態を初期化します。
	 */
	public synchronized void reset() {
//		ElLog.i(TAG,"Execute reset 1", true);
		Command<Void> command = new Command<Void>(CMD_SKRESET) {
			@Override
			Void processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				@SuppressWarnings("unused")
				String result = queue.take();
				return null;
			}
		};
//		ElLog.i(TAG,"Execute reset start", true);
		command.execute(serialPort, queue);
//		ElLog.i(TAG,"execute reset end", true);
		// エコーバックはしない
		setEchoBackFlag(false);
	}

	/**
	 * PANAセッションライフタイム設定.
	 *
	 * @param time
	 *            PANAセッションライフタイム(0x3C ～ 0xFFFFFFFF)
	 * @return true(成功)/false(エラー)
	 */

	/**
	 * 自動再認証フラグ設定。
	 *
	 * @param flag
	 *            自動再認証フラグ
	 * @return true(成功)/false(エラー)
	 */
	public synchronized boolean setAutomaticReauthenticationFlag(boolean flag) {
		Command<Boolean> command = new SKSREG1(S17, flag ? "1" : "0");
		return command.execute(serialPort, queue);
	}

	/**
	 * 利用可能IPアドレス一覧取得。
	 */
	public synchronized void getIpAddressList() {
		Command<Void> command = new Command<Void>(CMD_SKTABLE) {
			@Override
			Void processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				// String result = queue.take();
				return null;
			}

			@Override
			public String toString() {
				return String.format("%s %d", command, 1);
			}
		};
		command.execute(serialPort, queue);
	}

	/**
	 * ネイバーキャッシュ取得。
	 */
	public synchronized void getNeighborCacheAddress() {
		Command<Void> command = new Command<Void>(CMD_SKTABLE) {
			@Override
			Void processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				// String result = queue.take();
				return null;
			}

			@Override
			public String toString() {
				return String.format("%s %d", command, 2);
			}
		};
		command.execute(serialPort, queue);
	}

	/**
	 * MACアドレス登録。
	 *
	 * @param macAddr
	 *            MACアドレス
	 * @return boolean true:コマンド受付 false:パラメータエラー
	 */
	public synchronized boolean setMacAddress(String macAddr) {
		if (isEmpty(macAddr)) {
			return false;
		}
		Command<Boolean> command = new SKSREG1(S01, macAddr);
		return command.execute(serialPort, queue);
	}

	/**
	 * 自動再認証フラグ取得。
	 */
	public synchronized boolean getAutomaticReauthenticationFlag() {
		Command<String> command = new SKSREG0(S17);
		return Integer.parseInt(command.execute(serialPort, queue), 16) != 0;
	}

	/**
	 * UDP待ち受けポート設定。
	 *
	 * @param handle
	 *            対するUDPハンドル番号1～6
	 * @param udpPort
	 *            ハンドル番号 に割り当てられる待ち受けポート番号0x0000～0xFFFF
	 *            0を指定した場合、そのハンドルは未使用となりポートは着信しません。 また0xFFFFは予約番号で着信しません。
	 * @return boolean true:コマンド受付 false:パラメータエラー
	 */
	public synchronized boolean setUdpPort(final int handle, final int udpPort) {
		Command<Boolean> command = new Command<Boolean>(CMD_SKUDPPORT) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}

			@Override
			public String toString() {
				return String.format("%s %d %X", command, handle, udpPort);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * PANA認証サーバ動作開始。
	 */
	public synchronized void startServer() {
		Command<Void> command = new Command<Void>(CMD_SKSTART) {
			@Override
			Void processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				@SuppressWarnings("unused")
				String result = queue.take();
				return null;
			}
		};
		command.execute(serialPort, queue);
	}

	/**
	 * 設定クリア。
	 */
	public synchronized boolean eraseRegisterValue() {
		Command<Boolean> command = new Command<Boolean>(CMD_SKERASE) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 設定ロード
	 */
	public synchronized boolean loadRegisterValue() {
		Command<Boolean> command = new Command<Boolean>(CMD_SKLOAD) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}
		};
		return command.execute(serialPort, queue);
	}

	/**
	 * 設定セーブ。
	 */
	public synchronized boolean saveRegisterValue() {
		Command<Boolean> command = new Command<Boolean>(CMD_SKSAVE) {
			@Override
			Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
				String result = queue.take();
				return (result.startsWith("OK") ? true : false);
			}
		};
		return command.execute(serialPort, queue);
	}

	/** SKSENDTO. */
	private static class SKSENDTO extends Command<Boolean> {

		protected byte[] CRLF = { 0x0D, 0x0A };

		private byte[] data;
		private String address;
		private int port;
		private int encrypted;
		private int handle;
		private int length;

		/*
		 *
		 * <HANDLE> <IPADDR> + <PORT> + <SEC> + <DATALEN> + <DATA>
		 */
		private SKSENDTO(int handle, String address, int port, int encrypted, int length, byte[] data) {
			super(CMD_SKSENDTO);
			this.handle = handle;
			this.data = data;
			this.address = address;
			this.port = port;
			this.encrypted = encrypted;
			this.length = length;
		}

		@Override
		Boolean processResult(BlockingQueue<String> queue) throws IOException, InterruptedException {
			String result = queue.take();
			return (result.startsWith("OK") ? true : false);
		}

		@Override
		public Boolean execute(SerialPortChannel channel, BlockingQueue<String> queue) {

			try {
				ElLog.t(CMD_TAG, "WRITE:" + toString());
				channel.write(getBytes());
				channel.write(data);
				channel.write(CRLF);
				channel.flush();
				return processResult(queue);
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		@Override
		public String toString() {
			return String.format(Locale.JAPAN, "%s %d %s %04X %d %04X ", command, handle, address, port, encrypted, length);
		}
	}


	private Command<Boolean> mLastSend = null;
	/**
	 * 指定した宛先にUDPでデータを送信します。
	 *
	 * @param handle
	 *            送信元UDPハンドル
	 * @param address
	 *            宛先IPアドレス
	 * @param sendToPort
	 *            宛先ポート番号
	 * @param sec
	 *            暗号化オプション
	 * @param data
	 *            送信データ
	 * @return
	 */
	public synchronized boolean sendTo(int handle, String address, int sendToPort, int sec, byte[] data) {

		int length = data.length;

		Command<Boolean> command = new SKSENDTO(handle, address, sendToPort, sec, length, data);
		mLastSend = command;
		return command.execute(serialPort, queue);
	}

	/**
	 * 指定した宛先にUDPでデータを送信します。
	 *
	 * @param handle
	 *            送信元UDPハンドル
	 * @param sendPort
	 *            宛先ポート番号
	 * @param sec
	 *            暗号化オプション
	 * @param data
	 *            送信データ
	 * @return
	 */
	public synchronized boolean sendMulti(int handle, int sendPort, int sec, byte[] data) {

		int length = data.length;

		Command<Boolean> command = new SKSENDTO(handle, SEND_MULTI_IPADDR, sendPort, sec, length, data);
//		mLastSend = command;
		return command.execute(serialPort, queue);
	}

	/**
	 * Personal Area Network情報取得
	 *
	 * @return Personal Area Network情報
	 */
	public WiSunPan[] getPans() {
		WiSunPan[] pans = new WiSunPan[listPan.size()];
		logger.info("************************WiSunDriver listPan.size() :" + listPan.size());
		logger.info("************************WiSunDriver getPans[] :" + listPan.toArray(pans));
		for (WiSunPan w : pans) {
			String channel = String.valueOf(w.getChannel());
			String panId = String.valueOf(w.getPanId());
			String remoteIp = w.getIpAddress();
			logger.info("ELWisunChannel connectBRoute channel : "+ channel);
			logger.info("ELWisunChannel connectBRoute panId : "+ panId);
			logger.info("ELWisunChannel connectBRoute remoteIp : "+ remoteIp);
		}
		return listPan.toArray(pans);
	}

	private static String[] split(String line) {
		return SPLIT_PATTERN.split(line);
	}

	/**
	 * 16進数文字列から数値型の値に変換する
	 * @param hex 16進数文字列
	 * @return int 数値型の値
	 */
	public static int getIntFromHex(String hex) {
		int num = 0;
		try {
			num = Integer.parseInt(hex, 16);
		} catch (NumberFormatException e) {
			ElLog.e(TAG, e.getMessage());
		}
		return num;
	}

}

