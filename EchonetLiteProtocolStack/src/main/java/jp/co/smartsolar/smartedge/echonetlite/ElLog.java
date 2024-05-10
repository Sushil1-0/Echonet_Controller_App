package jp.co.smartsolar.smartedge.echonetlite;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * ログ出力用のクラス<br>
 * 自動付与の改行コードは、デフォルトでLF<br>
 * 改行コードを変更する場合は、本プログラムをコールする冒頭にて、LINE_SEPを任意のコードに変更する<br>
 * 出力を一括で無効化する為には、VERBOSEをfalseに変更する<br>
 * ログレベルは、FATAL,ERROR,WARN,INFO,DEBUGを列挙型にて定義済み<br>
 * static変数のOUTPUT_LEVELにログレベルの列挙型の要素を指定することで同値以上のログレベルが出力対象となる<br>
 * static変数のOUTPUT_LEVELはデフォルト値はDEBUG<br>
 * <br>
 */
public class ElLog {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElLog";

	/**
	 * Log4jオブジェクト
	 */
	private static Logger log4j;

	static {
		URL url = Thread.currentThread().getContextClassLoader().getResource("log4j.properties");
		log4j = Logger.getLogger("operation");
		PropertyConfigurator.configure(url);
	}
	/**
	 * 改行コード
	 */
	private static String LINE_SEP = "\n";

	/**
	 * ログレベルの列挙型
	 */
	public static enum LOG_LEVEL{
		FATAL,
		ERROR,
		WARN,
		INFO,
		DEBUG,
		TRACE,
	}

	/**
	 * ログ出力フラグ<br>
	 * 	ログ出力機能全体の一括指定用フラグ
	 */
	public static boolean VERBOSE = true;

	/**
	 * ログ出力対象レベルの指定
	 */
	public static LOG_LEVEL OUTPUT_LEVEL = LOG_LEVEL.TRACE;

	/**
	 * コンストラクタ<br>
	 * 外部からの単独インスタンスは禁止する。
	 */
	private ElLog () {}

	/**
	 * 出力対象レベルであるか否かを判定
	 * パラメータで受け取ったログレベルと、OUTPUT_LEVELを比較し同値以上である場合出力とする
	 * @param level 比較対象のログレベル
	 * @return 出力判定値（true: 出力対象、false: 出力非対称）
	 */
	private static boolean isOutputLevel(LOG_LEVEL level) {
		if(level.ordinal() <= OUTPUT_LEVEL.ordinal()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * FATAL用ログ出力指定メソッド
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
	 */
	public static void f (String tag, String message, boolean verbose) {
		LOG_LEVEL level = LOG_LEVEL.FATAL;
		if(VERBOSE && verbose && isOutputLevel(level)) {
			output(level, tag, message);
		}
	}
	/**
	 * ERRORログ出力指定メソッド
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
	 */
	public static void e (String tag, String message, boolean verbose) {
		LOG_LEVEL level = LOG_LEVEL.ERROR;
		if(VERBOSE && verbose && isOutputLevel(level)) {
			output(level, tag, message);
		}
	}
	/**
	 * WARNログ出力指定メソッド
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
	 */
	public static void w (String tag, String message, boolean verbose) {
		LOG_LEVEL level = LOG_LEVEL.WARN;
		if(VERBOSE && verbose && isOutputLevel(level)) {
			output(level, tag, message);
		}
	}
	/**
	 * INFOログ出力指定メソッド
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
	 */
	public static void i (String tag, String message, boolean verbose) {
		LOG_LEVEL level = LOG_LEVEL.INFO;
		if(VERBOSE && verbose && isOutputLevel(level)) {
			output(level, tag, message);
		}
	}
	/**
	 * DEBUGログ出力指定メソッド
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
	 */
	public static void d (String tag, String message, boolean verbose) {
		LOG_LEVEL level = LOG_LEVEL.DEBUG;
		if(VERBOSE && verbose && isOutputLevel(level)) {
			output(level, tag, message);
		}
	}
	/**
	 * TRACEログ出力指定メソッド
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
	 */
	public static void t (String tag, String message, boolean verbose) {
		LOG_LEVEL level = LOG_LEVEL.TRACE;
		if(VERBOSE && verbose && isOutputLevel(level)) {
			output(level, tag, message);
		}
	}

	/**
	 * FATALログ出力指定メソッド<br>
	 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 */
	public static void f (String tag, String message) {
		f(tag, message, true);
	}
	/**
	 * ERRORログ出力指定メソッド<br>
	 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 */
	public static void e (String tag, String message) {
		e(tag, message, true);
	}
	/**
	 * WARNログ出力指定メソッド<br>
	 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 */
	public static void w (String tag, String message) {
		w(tag, message, true);
	}
	/**
	 * INFOログ出力指定メソッド<br>
	 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 */
	public static void i (String tag, String message) {
		i(tag, message, true);
	}
	/**
	 * DEBUGログ出力指定メソッド<br>
	 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 */
	public static void d (String tag, String message) {
		d(tag, message, true);
	}
	/**
	 * TRACEログ出力指定メソッド<br>
	 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 */
	public static void t (String tag, String message) {
		t(tag, message, true);
	}


	/**
	 * 行ヘッダの文字列作成用メソッド<br>
	 * 書式：[ログレベル][TAG]
	 * @param level ログレベル
	 * @param tag クラス等コードロケーションを表す文字列
	 * @return String 行ヘッダを表す文字列
	 */
	private static String lineHeader(LOG_LEVEL level, String tag) {
		String line = "";
		if(level != null) {
			line += "[" + level.name() + "]";
		}
		if(tag != null && ! tag.isEmpty()) {
			line += "["+ tag +"]";
		}
		return line;
	}

	/**
	 * ログの出力形式を整形用メソッド
	 * @param level ログレベル
	 * @param tag	クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 * @return String 整形後のログ出力用文字列
	 */
	private synchronized static String lineFormat(LOG_LEVEL level, String tag, String message) {
		String line = "";
		line +=lineHeader(level, tag);
		line += " " + message;

		return line;
	}

	/**
	 * ログの出力実施用メソッド
	 * <p>コンソール出力を実施</p>
	 * @param level ログレベル
	 * @param tag クラス等コードロケーションを表す文字列
	 * @param message ログメッセージ
	 */
	private synchronized static void output(LOG_LEVEL level, String tag, String message) {
		println(level, lineFormat(level, tag, message));
	}

	/**
	 * ログメッセージの出力処理（改行コード付与）
	 * <p>指定した改行コードを付与する</p>
	 * @param level ログレベル
	 * @param message ログメッセージ
	 */
	private synchronized static void println(LOG_LEVEL level, String message) {
		print(level, message + LINE_SEP);
	};

	/**
	 * ログメッセージの出力処理
	 * @param level ログレベル
	 * @param message ログメッセージ
	 */
	private synchronized static void print(LOG_LEVEL level, String message) {
		switch (level) {
			case FATAL:
				log4j.fatal(message);
				break;
			case ERROR:
				log4j.error(message);
				break;
			case WARN:
				log4j.warn(message);
				break;
			case INFO:
				log4j.info(message);
				break;
			case DEBUG:
				log4j.debug(message);
				break;
			case TRACE:
				log4j.trace(message);
				break;
		}
	};

	/**
	 * バッファリング用オブジェクトの取得用メソッド<br>
	 * ログをバッファリングしてから出力する場合は、こちらを使用する。
	 * @return バッファリング管理用オブジェクト
	 */
	public static ElLog.Config getBuilder() {
		return new ElLog.Config();
	}

	/**
	 * バッファリング用オブジェクト生成クラス（ログレベル指定用メソッド群）<br>
	 * ログレベル指定用メソッドを集約しているクラス
	 * ElLog.getBuilder()によって本クラスのインスタンスが返されるので
	 * メソッドチェーンにて、ログレベル指定するメソッドを実施する<br>
	 * 例：ElLog.getBuilder().i(TAG)<br>
	 */
	public static class Config {
		/**
		 * コンストラクタ<br>
		 * 外部からの単独インスタンスは禁止する。
		 * @param なし
		 */
		private Config() {}

		/**
		 * FATAL用ログ出力指定メソッド
		 * @param tag クラス等コードロケーションを表す文字列
		 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
		 * @return メッセージ管理オブジェクト
		 */
		public Builder f (String tag, boolean verbose) {
			return new Builder(LOG_LEVEL.FATAL, tag, verbose);
		}
		/**
		 * ERROR用ログ出力指定メソッド
		 * @param tag クラス等コードロケーションを表す文字列
		 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
		 * @return メッセージ管理オブジェクト
		 */
		public Builder e (String tag, boolean verbose) {
			return new Builder(LOG_LEVEL.ERROR, tag, verbose);
		}
		/**
		 * WARN用ログ出力指定メソッド
		 * @param tag クラス等コードロケーションを表す文字列
		 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
		 * @return メッセージ管理オブジェクト
		 */
		public Builder w (String tag, boolean verbose) {
			return new Builder(LOG_LEVEL.WARN, tag, verbose);
		}
		/**
		 * INFO用ログ出力指定メソッド
		 * @param tag クラス等コードロケーションを表す文字列
		 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
		 * @return メッセージ管理オブジェクト
		 */
		public Builder i (String tag, boolean verbose) {
			return new Builder(LOG_LEVEL.INFO, tag, verbose);
		}
		/**
		 * DEBUG用ログ出力指定メソッド
		 * @param tag クラス等コードロケーションを表す文字列
		 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
		 * @return メッセージ管理オブジェクト
		 */
		public Builder d (String tag, boolean verbose) {
			return new Builder(LOG_LEVEL.DEBUG, tag, verbose);
		}
		/**
		 * TRACE用ログ出力指定メソッド
		 * @param tag クラス等コードロケーションを表す文字列
		 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
		 * @return メッセージ管理オブジェクト
		 */
		public Builder t (String tag, boolean verbose) {
			return new Builder(LOG_LEVEL.TRACE, tag, verbose);
		}

		/**
		 * FATALログ出力指定メソッド<br>
		 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
		 * @param tag クラス等コードロケーションを表す文字列
		 * @return メッセージ管理オブジェクト
		 */
		public Builder f (String tag) {
			return new Builder(LOG_LEVEL.FATAL, tag, true);
		}
		/**
		 * ERRORログ出力指定メソッド<br>
		 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
		 * @param tag クラス等コードロケーションを表す文字列
		 * @return メッセージ管理オブジェクト
		 */
		public Builder e (String tag) {
			return new Builder(LOG_LEVEL.ERROR, tag, true);
		}
		/**
		 * WARNログ出力指定メソッド<br>
		 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
		 * @param tag クラス等コードロケーションを表す文字列
		 * @return メッセージ管理オブジェクト
		 */
		public Builder w (String tag) {
			return new Builder(LOG_LEVEL.WARN, tag, true);
		}
		/**
		 * INFOログ出力指定メソッド<br>
		 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
		 * @param tag クラス等コードロケーションを表す文字列
		 * @return メッセージ管理オブジェクト
		 */
		public Builder i (String tag) {
			return new Builder(LOG_LEVEL.INFO, tag, true);
		}
		/**
		 * DEBUGログ出力指定メソッド<br>
		 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
		 * @param tag クラス等コードロケーションを表す文字列
		 * @return メッセージ管理オブジェクト
		 */
		public Builder d (String tag) {
			return new Builder(LOG_LEVEL.DEBUG, tag, true);
		}
		/**
		 * TRACEログ出力指定メソッド<br>
		 * 同名メソッドのオーバーロード版。verboseをtrue(=出力個別してにて出力する)指定をしている。
		 * @param tag クラス等コードロケーションを表す文字列
		 * @return メッセージ管理オブジェクト
		 */
		public Builder t (String tag) {
			return new Builder(LOG_LEVEL.TRACE, tag, true);
		}
	}

	/**
	 * バッファリング用オブジェクト生成クラス（メッセージ管理用メソッド群）<br>
	 * メッセージの連結と出力を実施するメソッド群<br>
	 * ElLog.getBuilder().i(TAG)等によって本クラスのインスタンスが返されるので
	 * メソッドチェーンにて、メッセージを指定するメソッドを実施、最後にflushメソッドにて出力を実施<br>
	 * 例：ElLog.getBuilder().i(TAG).appendLine("message").flush()<br>
	 */
	public static class Builder {

		/**
		 * ログレベル
		 */
		private LOG_LEVEL level;

		/**
		 * クラス等コードロケーションを表す文字列
		 */
		private String tag = "";

		/**
		  * 個別出力指定フラグ falseの場合はその出力のみ無効となる
		  */
		 private boolean verbose = true;

		 /**
		  * 文字列連結用バッファ
		  */
		private StringBuffer sb = null;

		/**
		 * コンストラクタ
		 * @param level ログレベル
		 * @param tag クラス等コードロケーションを表す文字列
		 * @param verbose 個別出力指定フラグ falseの場合はその出力のみ無効となる
		 */
		private Builder(LOG_LEVEL level , String tag, boolean verbose){
			this.level = level;
			this.tag = tag;
			this.verbose = verbose;
			this.sb = new StringBuffer();
		}

		/**
		 * 行ヘッダをバッファに格納するメソッド
		 * @return メッセージ管理オブジェクト
		 */
		public Builder appendHeader() {
			this.sb.append(lineHeader(level, tag));
			return this;
		}

		/**
		 * 改行コードをバッファに格納するメソッド
		 * @return メッセージ管理オブジェクト
		 */
		public Builder appendNewLine() {
			this.sb.append(LINE_SEP);
			return this;
		}
		/**
		 * メッセージをバッファに格納するメソッド
		 * 同名メソッドのオーバーロード版。行ヘッダを出力を無効にしている。
		 * @param message ログメッセージ
		 * @return メッセージ管理オブジェクト
		 */
		public Builder append(String message) {
			return this.append(message, false);
		}
		/**
		 * メッセージをバッファに格納するメソッド
		 * @param message ログメッセージ
		 * @param header 行ヘッダの出力の有無を指定するフラグ
		 * @return メッセージ管理オブジェクト
		 */
		public Builder append(String message, boolean header) {
			if(header) {
				this.sb.append(lineFormat(level, tag, message));
			} else {
				this.sb.append(message);
			}
			return this;
		}
		/**
		 * メッセージをバッファに格納するメソッド（改行コード自動付与）
		 * @param message ログメッセージ
		 * @return メッセージ管理オブジェクト
		 */
		public Builder appendLine(String message) {
			this.sb.append(lineFormat(level, tag, message) + LINE_SEP);
			return this;
		}
		/**
		 * メッセージを出力する。
		 * 出力後にバッファを初期化することで、連続して作業可能。
		 * verboseがfalseの場合は、出力されない。
		 */
		public void flush() {
			if(VERBOSE && this.verbose && isOutputLevel(this.level)) {
				print(this.level, this.sb.toString());
				//TODO:認証対応用表示、認証後に削除
				System.out.println(this.sb.toString());
			}
			this.sb = new StringBuffer();
		}
	}
}
