package jp.co.smartsolar.smartedge.log;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Log {

    static Logger  logger    = null;
    static Log     log       = null;
    static boolean debugFlag = false;

    /**
     * ログ出力インスタンスを取得する。.
     *
     * @param classObject 出力ファイル名となるアプリのインスタンス
     * @return
     */
    public static Log getInstance(Class<?> classObject) {
        // シングルトンパターンを適用する。
        if (log != null) {
            return log;
        }

        log = new Log();
        String[] tmpList = classObject.getName().split(Pattern.quote("."));
        String appname = "";
        if (tmpList.length > 0) {
            appname = tmpList[tmpList.length - 1];
        }
        System.out.println("classObject.getName():" + classObject.getName());
        System.out.println("appname:" + appname);
        System.setProperty("APPNAME", appname);
        logger = LoggerFactory.getLogger(classObject);
        return log;
    }

    public static Log getInstance() {
        if (log != null) {
            return log;
        }
        return null;
    }

    public void info(String msg) {
        println(msg);
        logger.info(msg);
        logger.debug(msg);
    }

    public void warn(String msg) {
        println(msg);
        logger.warn(msg);
        logger.debug(msg);
    }

    public void warn(String msg, Exception e) {
        println(msg);
        this.warn(msg+e.getMessage());
        logger.debug(e.getMessage(), e);
    }

    public void err(String msg) {
        println(msg);
        logger.error(msg);
        logger.debug(msg);
    }

    public void err(String msg, Exception e) {
        println(msg);
        this.err(msg+e.getMessage());
        logger.debug(e.getMessage(), e);
    }

    private void println(String msg) {
        if (debugFlag) {
            System.out.println(msg);
        }
    }

    public void setDebug(boolean insDebug) {
        debugFlag = insDebug;
    }

    public boolean getDebug() {
        return debugFlag;
    }
}
