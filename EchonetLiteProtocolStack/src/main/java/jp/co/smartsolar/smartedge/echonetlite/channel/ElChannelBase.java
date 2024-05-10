package jp.co.smartsolar.smartedge.echonetlite.channel;

import jp.co.smartsolar.smartedge.echonetlite.protocol.ElProtocolImpl;

/**
 * チャンネル用基底クラス
 */
public abstract class ElChannelBase extends ElProtocolImpl implements ElChannel{

	/**
	 * コンストラクタ
	 * @param ipAddress アサインされたローカルIPアドレス
	 */
	public  ElChannelBase(String ipAddress) {
		super(ipAddress);
	}

	/**
	 * 通信チャンネルが有効化否かをチェック
	 * @return boolean true: 有効、false:無効
	 */
	protected abstract boolean isOpened();

	/**
	 * フレーム受信処理
	 * 受信待ち、受信時の処理
 	 * @throws Exception 任意の例外
	 */
	protected abstract void receive() throws Exception;

}
