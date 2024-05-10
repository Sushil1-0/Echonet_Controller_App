package jp.co.smartsolar.smartedge.echonetlite.channel.wisun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.smartsolar.smartedge.echonetlite.ElLog;

public class WiSunPanImpl implements WiSunPan {

	private static final String TAG = "WiSunPanImpl";

	private static final String EPANDESC_CHANNEL = "Channel:";

	private static final String EPANDESC_CHANNEL_PAGE = "Channel Page:";

	private static final String EPANDESC_PAN_ID = "Pan ID:";

	private static final String EPANDESC_ADDR = "Addr:";

	private static final String EPANDESC_LQI = "LQI:";

	private static final String EPANDESC_PAIR_ID = "PairID:";
	  protected static Logger logger = LoggerFactory.getLogger(WiSunPanImpl.class);

	private int channel;
	private int channelPage;
	private int panId;
	private String macAddress;
	private String ipAddress;
	private int lqi;
	private String pairId;

	private WiSunPanImpl() {
		pairId = null;
	}

	static WiSunPan rcvEpandesc(String[] data, boolean withIe) {
		WiSunPanImpl pan = new WiSunPanImpl();
		for (String line : data) {
			if (line == null) {
				break;
			}
			line = line.trim();
			logger.info("WiSunPanImpl rcvEpandesc line:" + line);
			if (line.startsWith(EPANDESC_CHANNEL)) {
				String[] elements = line.split(":");
				pan.setChannel(elements[1]);
			} else if (line.startsWith(EPANDESC_CHANNEL_PAGE)) {
				String[] elements = line.split(":");
				pan.setChannelPage(elements[1]);
			} else if (line.startsWith(EPANDESC_PAN_ID)) {
				String[] elements = line.split(":");
				pan.setPanId(elements[1]);
			} else if (line.startsWith(EPANDESC_ADDR)) {
				String[] elements = line.split(":");
				pan.setAddress(elements[1]);
			} else if (line.startsWith(EPANDESC_LQI)) {
				String[] elements = line.split(":");
				pan.setLqi(elements[1]);
			} else if (withIe) {
				// 拡張ビーコンを受信した場合のみ
				if (line.startsWith(EPANDESC_PAIR_ID)) {
					String[] elements = line.split(":");
					pan.setPairId(elements[1]);
				}
			}
		}
		ElLog.Builder log = ElLog.getBuilder().t(TAG);
		log
		.appendLine("======== rcvEpandesc ============")
		.appendLine("channel:" + pan.getChannel())
		.appendLine("page:" + pan.getChannelPage())
		.appendLine("panId:" + pan.getPanId())
		.appendLine("macAddr:" + pan.getMacAddress())
		.appendLine("rssi:" + pan.getLqi())
		.appendLine("pairingId:" + pan.getPairId())
		.appendLine("====================")
		.flush();
		return pan;
	}

	private void setChannel(String ch) {
		try {
			channel = Integer.parseInt(ch, 16);
		} catch (NumberFormatException e) {
			ElLog.e(TAG, "setChannel error > " + ch);
		}
	}

	private void setChannelPage(String page) {
		try {
			channelPage = Integer.parseInt(page, 16);
		} catch (NumberFormatException e) {
			ElLog.e(TAG, "setChannelPage error > " + page);
		}
	}

	private void setPanId(String id) {
		try {
			panId = Integer.parseInt(id, 16);
		} catch (NumberFormatException e) {
			ElLog.e(TAG, "setPanId error > " + id);
		}
	}

	private void setAddress(String mac) {
		macAddress = mac;
		// MACアドレスからIPアドレス生成
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("FE80:0000:0000:0000:");
			int c = Integer.parseInt(macAddress.substring(0, 2), 16);
			sb.append(String.format("%02X", (c ^ 2)));
			sb.append(macAddress.substring(2, 4)).append(":");
			sb.append(macAddress.substring(4, 8)).append(":");
			sb.append(macAddress.substring(8, 12)).append(":");
			sb.append(macAddress.substring(12));
			ipAddress = sb.toString();
		} catch (Exception e) {
			ElLog.e(TAG, "setAddress error > " + mac);
		}
	}

	private void setLqi(String lqi) {
		try {
			this.lqi = Integer.parseInt(lqi, 16);
		} catch (NumberFormatException e) {
			ElLog.e(TAG, "setLqi error > " + lqi);
		}
	}

	private void setPairId(String id) {
		pairId = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChannel() {
		return channel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChannelPage() {
		return channelPage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPanId() {
		return panId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLqi() {
		return lqi;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPairId() {
		return pairId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIpAddress() {
		return ipAddress;
	}
}
