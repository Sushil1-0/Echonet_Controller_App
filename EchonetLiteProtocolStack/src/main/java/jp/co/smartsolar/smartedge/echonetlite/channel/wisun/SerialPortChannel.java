package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

import java.util.Enumeration;
import java.util.concurrent.LinkedBlockingQueue;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.internal.StringUtil;
import jp.co.smartsolar.smartedge.echonetlite.ElLog;
import jp.co.smartsolar.smartedge.lib.purejavacomm.PureJavaCommChannel;
import jp.co.smartsolar.smartedge.lib.purejavacomm.PureJavaCommChannelConfig;
import jp.co.smartsolar.smartedge.lib.purejavacomm.PureJavaCommDeviceAddress;
import purejavacomm.CommPortIdentifier;

public class SerialPortChannel extends SimpleChannelInboundHandler<String> {

	/** ロガー. */
	private static final String TAG  = "SerialPort";

	/**
	 * ボーレート.
	 */
	private static final int BAUD_RATE = 115200;

	/**
	 * シリアルポートデバイス名
	 */
	private String serialPortName;

	/**
	 * シリアル通信用のイベントループグループオブジェクト.
	 */
	private OioEventLoopGroup eventLoopGroup;

	/**
	 * 受信データの蓄積用のキュー<br>
	 * シリアル通信経由で受信したデータ行を蓄積し順次処理を実施していく目的
	 */
	private LinkedBlockingQueue<String> lineQueue = new LinkedBlockingQueue<String>();

	/**
	 * シリアル接続用のチャンネルオブジェクト<br>
     * (io.netty利用の為)
	 */
	private ChannelFuture future;

	/**
	 * コンストラクタ.
	 *
	 * @param portName ポート名
	 */
	public SerialPortChannel(String portName) {
		super();

		if (StringUtil.isNullOrEmpty(portName)) {
			throw new IllegalArgumentException("portName is null or empty.");
		}
		Enumeration<CommPortIdentifier> enPort = CommPortIdentifier.getPortIdentifiers();
		while (enPort.hasMoreElements()) {
			CommPortIdentifier portid = (CommPortIdentifier) enPort.nextElement();
			ElLog.i(TAG, "検出ポート：" + portid.getName());
			if (portName.equals(portid.getName())) {
				serialPortName = portName;
				break;
			}
		}
		if (StringUtil.isNullOrEmpty(serialPortName)) {
			throw new IllegalArgumentException("portName is unknown.");
		}
	}

	/**
	 * シリアルポートオープン.
	 *
	 * @throws InterruptedException
	 */
	public void open() throws InterruptedException {
		if (future == null) {
			eventLoopGroup = new OioEventLoopGroup();

			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(PureJavaCommChannel.class).handler(new ChannelInitializer<PureJavaCommChannel>() {
				@Override
				public void initChannel(PureJavaCommChannel ch) throws Exception {
					ch.config()
					.setBaudrate(BAUD_RATE)
					.setDatabits(PureJavaCommChannelConfig.Databits.DATABITS_8)
					.setParitybit(PureJavaCommChannelConfig.Paritybit.NONE)
					.setStopbits(PureJavaCommChannelConfig.Stopbits.STOPBITS_1);
					ch.pipeline().addLast(new LineBasedFrameDecoder(32768), new ByteArrayEncoder(), new StringDecoder(), SerialPortChannel.this);
				}
		    });
			future = bootstrap.connect(new PureJavaCommDeviceAddress(serialPortName)).sync();
		}
	}

	/**
	 * シリアルポートクローズ.
	 */
	public void close() {
		if (future != null) {
			try {
				future.channel().close().sync();
				eventLoopGroup.shutdownGracefully();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * 読出し.
	 *
	 * @return 読出し文字列
	 * @throws InterruptedException
	 */
	public String readLine() throws InterruptedException {
		String line = lineQueue.take(); // キューが空の場合はここで処理が待ち状態になる。
		if(line == null) {
			line = "";
		}
		return line;
	}

	/**
	 * 書き込み.
	 *
	 * @param msg
	 */
	public void write(Object msg) {
		if (future != null) {
			future.channel().write(msg);
		}
	}

	/**
	 * フラッシュ.
	 */
	public void flush() {
		if (future != null) {
			future.channel().flush();
		}
	}

	/**
	 * 書き込み.
	 *
	 * @param msg
	 */
	public void writeAndFlush(Object msg) {
		if (future != null) {
			future.channel().writeAndFlush(msg);
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		lineQueue.offer(msg);
	}
}

