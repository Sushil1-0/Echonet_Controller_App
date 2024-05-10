package jp.co.smartsolar.smartedge.echonetlite.channel;

import java.io.IOException;

import jp.co.smartsolar.smartedge.echonetlite.ElFrame;

/**
 * 下位レイヤーの通信を行うための共通インターフェース
 * 実装クラスにてUDP/IP、TCP/IPなど複数の通信プロセスを記述する。
 */
public interface ElChannel {


	/**
	 * チャンネルで使用するマルチキャストを返す
	 * @return マルチキャストアドレス
	 */
	public String getMultiCastAddress();

	/**
	 * 初期化処理
	 * ソケットのオープンなど、通信に必要な初期処理を実施
	 * @throws Exception 任意の例外
	 */
	public void open() throws Exception;

	/**
	 * 終了処理
	 * ソケットクローズ等、通信資源の終了処理を実施
	 * @throws IOException 入出力例外
	 */
	public void close() throws Exception;

	/**
	 * 送信処理
	 * @param frame オブジェクト。送信対象のフレームオブジェクト
 	 * @throws IOException 入出力例外
	 */
	public void send(ElFrame frame) throws IOException;

	/**
	 * 受信用スレッドの開始
	 */
	public void startSocketRcvThread();


	/**
	 * 受信用スレッドの停止
	 */
	public void stopSocketRcvThread();

}
