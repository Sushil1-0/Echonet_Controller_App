package jp.co.smartsolar.smartedge.util;

public class updateFile {
    // バイナリディレクトリ指定
    private static String binDirectoryPath   = "/opt/smartedge/bin/";
    // ライブラリディレクトリ指定
    private static String libDirirectoryPath = "/opt/smartedge/lib/";
    // 対象ファイルダウンロード後格納ディレクトリ指定
    private static String dlDirirectoryPath  = "/opt/smartedge/data/";

//    // 環境情報 (Edge or Meas)
//    private String        envId              = "";

    // ファイルタイプ (Lib or Bin)
    private String        fileType           = "";

    // ディレクトリパス TODO 中身は/で終わる？ 例: "/opt/smartedge/lib/" ,/opt/smartedge/bin/
//    private String dirPath = "";

    // 新ファイル名
    private String        newFileName        = "";

    // 旧ファイル名
    private String        oldFileName        = "";

//    public String getEnvId() {
//        return envId;
//    }

    public String getFileType() {
        return fileType;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setNewFileName(String fileName) {
        this.newFileName = fileName;
    }

    public void setOldFileName(String fileName) {
        this.oldFileName = fileName;
    }

//    public void setEnvId(String envId) {
//        this.envId = envId;
//    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    // バイナリディレクトリパスを返す
    public String getbinDirectoryPath() {
        return binDirectoryPath;
    }

    // ライブラリディレクトリパスを返す
    public String getlibDirirectoryPath() {
        return libDirirectoryPath;
    }

    // 対象ファイルダウンロード後格納ディレクトリパスを返す
    public String dlDirirectoryPath() {
        return dlDirirectoryPath;
    }

    // 旧ファイルのフルパスを返す
    public String getDownloadedFilePath() {
        return dlDirirectoryPath + newFileName;

    }

    // 新ファイルのフルパスを返す
    public String getNewFilePath() {
        if (this.isJarFile()) {
            return binDirectoryPath + newFileName;
        }
        return libDirirectoryPath + newFileName;
    }

    // 旧ファイルのフルパスを返す
    public String getOldFilePath() {
        if (this.isJarFile()) {
            return binDirectoryPath + oldFileName;
        }
        return libDirirectoryPath + oldFileName;

    }


    // 旧ファイルのバックアップフルパスを返す
    public String getBackupFilePath() {
        if (this.isJarFile()) {
            return binDirectoryPath + "back/" + oldFileName;
        }
        return libDirirectoryPath + "back/" + oldFileName;
    }

    // 対象のファイルがjarファイルであればtrueを返す
    public boolean isJarFile() {
        if (this.fileType.equals("Lib")) {
            return true;
        }
        return false;
    }

}
