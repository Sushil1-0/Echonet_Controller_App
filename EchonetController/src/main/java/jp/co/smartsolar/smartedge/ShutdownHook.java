package jp.co.smartsolar.smartedge;

/**
 * @package jp.co.smartsolar.smartedge
 * @Author subohaju
 * @date 5/8/2024
 */

public class ShutdownHook extends Thread {

	interface ShutdownListener {
		void ShutdownNotify();
	}

	/** シャットダウン通知用オブジェクト. */
	private Object notifyObject = new Object();

	/** シャットダウンリスナー. */
	private ShutdownListener listener;

	/** 終了済み. */
	private boolean inFinished = false;

    /**
	 * コンストラクタ
	 * @param listener シャットダウンリスナー
	 */
	public ShutdownHook(ShutdownListener listener) {
		this.listener = listener;
	}

	/**
	 * シャットダウン待ち.
	 * @throws InterruptedException
	 */
	public void shutdownWait() throws InterruptedException {
    	Runtime.getRuntime().addShutdownHook(this);
		synchronized (notifyObject) {
			while (!inFinished) {
    			notifyObject.wait();
    		}
    	}
	}

	@Override
    public void run() {
		listener.ShutdownNotify();

		synchronized (notifyObject) {
			inFinished = true;
    		notifyObject.notifyAll();
    	}
    }
}
