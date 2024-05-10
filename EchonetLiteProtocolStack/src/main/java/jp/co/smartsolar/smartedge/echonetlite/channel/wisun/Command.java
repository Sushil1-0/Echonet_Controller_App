package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.smartsolar.smartedge.echonetlite.ElLog;

abstract class Command<V> {

	/** ロガー. */
	private final String TAG = "Command";
	protected static Logger logger = LoggerFactory.getLogger(Command.class);

	protected byte[] CRLF = { 0x0D, 0x0A };
	private static final Pattern SPLIT_PATTERN = Pattern.compile(" ");
	protected final String command;

	Command(String command) {
		synchronized (command) {
			this.command = command;
		}
	}

	abstract V processResult(BlockingQueue<String> queue) throws IOException, InterruptedException;

	V execute(SerialPortChannel channel, BlockingQueue<String> queue) {
//		ElLog.i(TAG,"SerialPortChannel start", true);
		logger.info("WRITE :" + toString());
//		logger.info("getBytes :" + getBytes());
		try {
			ElLog.t(TAG, "WRITE:" + toString());
//			ElLog.i(TAG,"SerialPortChannel after write", true);
			channel.writeAndFlush(getBytes());
			channel.writeAndFlush(CRLF);
			ElLog.i(TAG,"SerialPortChannel after write and flush", true);
			return processResult(queue);
		} catch (RuntimeException e) {
			ElLog.e(TAG, e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			ElLog.e(TAG, e.getMessage());
			throw new RuntimeException();
		} catch (Throwable t) {
			throw new RuntimeException();
		}
	}

	@Override
	public String toString() {
		return command;
	}

	protected byte[] getBytes() {
		return toString().getBytes();
	}

	protected static String[] split(String line) {
		return SPLIT_PATTERN.split(line);
	}

}
