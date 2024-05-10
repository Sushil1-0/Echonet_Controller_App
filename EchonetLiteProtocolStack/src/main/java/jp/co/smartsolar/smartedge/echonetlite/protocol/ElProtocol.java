package jp.co.smartsolar.smartedge.echonetlite.protocol;

import java.io.IOException;

/**
 * ECHONET Liteプロトコル関連処理インターフェース
 */
public interface ElProtocol {

	/**
	 * ECHONET用ポート番号
	 */
	public static final Integer ECHONET_PORT = 3610;

	/**
	 * ECHONET用マルチキャストアドレス（IPv4）
	 */
	public static final String MULTI_CAST_ADDRESS_V4 = "224.0.23.0";

	/**
	 * ECHONET用マルチキャストアドレス（IPv6,オールノードマルチキャストアドレス）
	 */
	public static final String MULTI_CAST_ADDRESS_V6 = "ff02::1";

	/**
	 * Echonetフレーム受信時の処理用スレッドをキック
	 * ※イベントループモデル採用の為
	 */
	public void startElRcvThread();

	/**
	 * Echonetフレーム受信時の処理用スレッドを停止
	 * ※イベントループモデル採用の為
	 */
	public void stopElRcvThread();

	/**
	 * ECHONET tLiteフレーム受信時の処理を記述
	 */
	public interface ElJob{

		/**
		 * 受信フレームの処理
		 * @throws IOException 入出力例外
		 */
		public void business() throws IOException;
	}

}
