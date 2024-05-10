package jp.co.smartsolar.smartedge.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

import jp.co.smartsolar.smartedge.database.controller.AppMasterTableController;
import jp.co.smartsolar.smartedge.database.controller.ConfigInfoTableController;
import jp.co.smartsolar.smartedge.database.controller.ConfigInfoTableController.Enviroment;
import jp.co.smartsolar.smartedge.database.entity.AppMaster;
import jp.co.smartsolar.smartedge.database.entity.ConfigInfo;
import jp.co.smartsolar.smartedge.database.entity.SqlUpdateInfo;

/**
 * 管理アプリケーション便利クラス.
 *
 * @author ubiq
 *
 */
public class OpeUtil extends BaseUtil {
	  protected final static Logger log = LoggerFactory.getLogger(OpeUtil.class);
    //装置定義
    public enum ModuleType {
        EdgeModule,
        MeasureModule;

        public boolean isEdgeModule() {
            if(this.equals(EdgeModule)) {
                return true;
            }
            return false;
        }
    }

    /**
     * アプリケーション起動.
     *
     * @param targetAppName :
     * @param appMasterList :
     * @throws Exception :
     */
    public static void startTargetApp(String targetAppName, List<AppMaster> appMasterList) throws IOException {
        AppMaster entity = getAppFromAppList(appMasterList, targetAppName);
        if (entity == null) {
            throw new IOException();
        }
        Runtime runtime = Runtime.getRuntime();
//        String[] cmd = { "/bin/sh", "-c", "/usr/bin/sudo java -jar " + entity.getFilePath() }; 入江さん案
        // linux用
        String[] cmd = { entity.getFilePath() };
        // debug windows用
//      String[] cmd = { "cmd.exe /c start"+ " " + entity.getFilePath()};
        runtime.exec(cmd);
        SEUtil.sleepThread(1000);
    }

    /**
     * リブート.
     *
     */
    public static void reboot() {

        // TODO : test debug irie.
        /*
         * while (true) { try { Runtime runtime = Runtime.getRuntime(); String[] cmd = {
         * "/bin/sh", "-c", "/usr/bin/sudo reboot" }; runtime.exec(cmd); } catch
         * (Exception e) { try { Thread.sleep(1000); } catch (Exception ee) { // do
         * nothing. } continue; } break; }
         */

//        while (true) {
//            try {
//                Runtime runtime = Runtime.getRuntime();
//                String[] cmd = {"/bin/sh", "-c", "/usr/bin/sudo reboot now" };
//                runtime.exec(cmd);
//            }
//            catch(Exception e) {
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception ee) {
//                    // donothing.
//                }
//                continue;
//            }
//            break;
//        }

        exeCommand(new String[]{"/bin/sh", "-c", "/usr/bin/sudo /opt/smartedge/bat/reboot.sh" });
    }
    
    public static void dropCache() {
        exeCommand(new String[]{"/bin/sh", "-c", "sudo sync; echo 1 | sudo tee /proc/sys/vm/drop_caches" });
    }
    
    public static void restartAfterOneMin() {
        while (true) {
            try {
                Runtime runtime = Runtime.getRuntime();
                // 強制終了コマンド
                String[] cmd = { "/bin/sh", "-c", "sleep 1; /usr/bin/sudo shutdown -r 1" };
                runtime.exec(cmd);
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    // do nothing.
                }
                continue;
            }
            break;
        }
    }

    /**
     * 強制終了.
     *
     */
    public static void shutDown() {
        while (true) {
            try {
                Runtime runtime = Runtime.getRuntime();
                // 強制終了コマンド
                String[] cmd = { "/bin/sh", "-c", "sleep 1; /usr/bin/sudo shutdown now" };
                runtime.exec(cmd);
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    // do nothing.
                }
                continue;
            }
            break;
        }
    }

    /**
     * LEDシェル実行.
     *
     */
    public static void stopLed() {
        while (true) {
            try {
                Runtime runtime = Runtime.getRuntime();
                // 強制終了コマンド
                String[] cmd = { "/bin/sh", "-c", "/usr/bin/sudo /opt/smartedge/bat/led.sh" };
                runtime.exec(cmd);
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    // do nothing.
                }
                continue;
            }
            break;
        }
    }

    /**
     * アプリケーション更新入れ替え. TODO 仕様変更のため修正必須
     *
     * @param targetAppEntity :
     * @return
     */
////    public static boolean updateTargetApp(AppMaster targetAppEntity) throws Exception {
//      public static boolean updateTargetApp(updateFile updatefile) throws Exception {
//
//          AppMaster targetAppEntity = new AppMaster();  //debug
//        //必ず運用管理アプリ内で対象の停止処理を行う
//        //        stopTargetApp(targetAppEntity.getAppName());
//
//        // 新アプリファイルダウンロード後、格納場所及びファイル存在確認
//        File newAppFile = new File(updatefile.getUpdateFilePath());
//        if (newAppFile == null || !newAppFile.exists() || !newAppFile.isFile()) {
//            throw new Exception("新アプリファイルパスが正しくない");
//        }
//
//        // 旧アプリファイル //ファイルが存在しない場合でも問題ない 追加のみが仕様として存在する
//        File oldAppFile = new File(targetAppEntity.getFilePath());
//        if (oldAppFile == null || !oldAppFile.exists() || !oldAppFile.isFile()) {
//            throw new Exception("旧アプリファイルパスが正しくない");
//        }
//
//        // 新アプリファイルの親ディレクトリ(ファイル置き場)
//        File newAppFileDir = newAppFile.getParentFile();
//        if (newAppFileDir == null || !newAppFileDir.exists() || !newAppFileDir.isDirectory()) {
//            throw new Exception("新アプリファイルのファイル置き場が正しくない");
//        }
//
//        // Backupディレクトリ
//        File backupDir = new File(newAppFileDir, "backup");
//        if (backupDir == null || !backupDir.exists() || !backupDir.isDirectory()) {
//            // Backupディレクトリ作成
//            backupDir.mkdir();
//        }
//
//        // Backupファイル
//        File backupFile = new File(backupDir, newAppFile.getName());
//        if (backupFile == null || !backupFile.exists() || !backupFile.isFile()) {
//            // 旧Backupファイルが存在しないため、Backupファイル削除処理は行わない
//        } else {
//            // 旧Backupファイル削除実施
//            backupFile.delete();
//        }
//
//        // 新Backupファイル作成
//        Files.copy(oldAppFile, backupFile);
//
//        // 旧アプリファイル削除
//        oldAppFile.delete();
//
//        // 新ファイル作成(新ファイル配置) ※ここでアプリファイルの更新完了
//        Files.copy(newAppFile, oldAppFile);
//        return true;
//    }

    /**
     * モジュールアップデートメソッド
     *
     * @param environmentId
     * @return
     * @throws IOException
     */

    public static boolean updateModule(OpeUtil.ModuleType moduleType) throws IOException {

        List<updateFile> updateFileList = readUpdateFileList(moduleType);

        deleteBackupDirectory();

        // ファイル更新
        for (updateFile updatedata : updateFileList) {
            File newAppFile = new File(updatedata.getDownloadedFilePath());
            File oldAppFile = new File(updatedata.getOldFilePath());
            File backupFile = new File(updatedata.getBackupFilePath());

            // 新Backupファイル作成
            Files.copy(oldAppFile, backupFile);

            // 旧アプリファイル削除
            oldAppFile.delete();

            // 新ファイル作成(新ファイル配置) ※ここでアプリファイルの更新完了
            Files.copy(newAppFile, oldAppFile);
            if (!(updatedata.isJarFile())) {
                // パーミッションの変更を行い、実行権限を渡す
                newAppFile.setExecutable(true, false);
            }
        }

        // TODO アップデート完了フラグ更新 (true)  DB内config_infoに項目追加必要
        // TODO アップデート適応フラグ更新？ (false)  DB内config_infoに項目追加必要

        return true;
    }

    /**
     * アップデート用ファイルのリストをCSVファイルから読み込み、作成する
     *
     * @param environmentId
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public static List<updateFile> readUpdateFileList(OpeUtil.ModuleType moduleType) throws IOException {

        String filePath = "";
        if (moduleType.isEdgeModule()) {
            filePath = "/opt/smartedge/data/fw/EdgeModuleUpdate.csv";
        } else{
            filePath = "/opt/smartedge/data/fw/MeasModuleUpdate.csv";
        }

        List<updateFile> returnList = new LinkedList<updateFile>();
        FileInputStream fi = new FileInputStream(filePath);
        InputStreamReader is = new InputStreamReader(fi);
        BufferedReader br = new BufferedReader(is);
        String line;
        String[] splitLine;
        while ((line = br.readLine()) != null) {
            updateFile updateInfo = new updateFile();
            splitLine = line.split(",");
            updateInfo.setFileType(splitLine[0]);
            updateInfo.setNewFileName(splitLine[1]);
            updateInfo.setOldFileName(splitLine[2]);
            returnList.add(updateInfo);
        }
        br.close();
        return returnList;
    }

    /**
     * バックアップファイル削除
     *
     * @throws IOException
     */
    public static void deleteBackupDirectory() throws IOException {
        final String binDirPath = "/opt/smartedge/bin/back";
        final String libDirPath = "/opt/smartedge/lib/back";

        File file = new File(binDirPath);
        recursiveDeleteFile(file);
        file.createNewFile();

        file = new File(libDirPath);
        recursiveDeleteFile(file);
        file.createNewFile();
    }

    /**
     * 対象のファイルオブジェクトの削除を行う ディレクトリの場合は再帰処理を行い、削除する。
     *
     * @param file
     */
    private static void recursiveDeleteFile(final File file) {
        // 存在しない場合は処理終了
        if (!file.exists()) {
            return;
        }
        // 対象がディレクトリの場合は再帰処理
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                recursiveDeleteFile(child);
            }
        }
        // 対象がファイルもしくは配下が空のディレクトリの場合は削除する
        file.delete();
    }

//      public static boolean updateTargetApp(updateFile updatefile) throws Exception {
//          String temporaryPath = "/opt/smartedge/data/";
//
//        //必ず運用管理アプリ内で対象の停止処理を行う
//        //        stopTargetApp(targetAppEntity.getAppName());
//
//        // 新アプリファイルダウンロード後、格納場所及びファイル存在確認
//        File newAppFile = new File(temporaryPath + updatefile.getFileName());
//        if (newAppFile == null || !newAppFile.exists() || !newAppFile.isFile()) {
//            throw new Exception("新アプリファイルパスが正しくない");
//        }
//
//        // 旧アプリファイル //FIXME ファイルが存在しない場合でも問題ない 追加のみが仕様として存在する
//        File oldAppFile = new File(updatefile.getFilePath());
//        if (oldAppFile == null || !oldAppFile.exists() || !oldAppFile.isFile()) {
//            throw new Exception("旧アプリファイルパスが正しくない");
//        }
//
//        // 新アプリファイルの親ディレクトリ(ファイル置き場)
//        File newAppFileDir = new File(updatefile.getDirPath());
//        if (newAppFileDir == null || !newAppFileDir.exists() || !newAppFileDir.isDirectory()) {
//            throw new Exception("新アプリファイルのファイル置き場が正しくない");
//        }
//
//        // Backupディレクトリ
//        File backupDir = new File(updatefile.getBackupDirPath());
//        if (backupDir == null || !backupDir.exists() || !backupDir.isDirectory()) {
//            // Backupディレクトリ作成
//            backupDir.mkdir();
//        }
//
//        // Backupファイル
//        File backupFile = new File(updatefile.getBackupFilePath());
//        if (backupFile == null || !backupFile.exists() || !backupFile.isFile()) {
//            // 旧Backupファイルが存在しないため、Backupファイル削除処理は行わない
//        } else {
//            // 旧Backupファイル削除実施
//            backupFile.delete();
//        }
//
//        // 新Backupファイル作成
//        Files.copy(oldAppFile, backupFile);
//
//        // 旧アプリファイル削除
//        oldAppFile.delete();
//
//        // 新ファイル作成(新ファイル配置) ※ここでアプリファイルの更新完了
//        Files.copy(newAppFile, oldAppFile);
//        return true;
//    }


    /**
     * アプリケーションファイルをBackupファイルと入れ替える. TODO 仕様変更のため修正必須
     *
     * @param app :
     */
    public static void changeAppFileFromBackup(AppMaster app) throws Exception {
        String path = app.getUpdateFilePath()+"restoreEdge.sh";
        if(app.getGroupMaster().getId() == 2 ) {
            path = app.getUpdateFilePath()+"restoreMeas.sh";
        }
        
        if(!"".equals(path)) {
            String cmdMode = "sudo chmod 0766 " + path;
            String cmdRun = "sudo " + path;
            SEUtil.exeCommand(new String[] { "/bin/sh", "-c", cmdMode });
            SEUtil.exeCommand(new String[] { "/bin/sh", "-c", cmdRun });
        }
    }

    /**
     * APPリストからアプリ名を指定して抽出する.
     *
     * @param appMasterList :
     * @param appName       :
     * @return
     */
    public static AppMaster getAppFromAppList(List<AppMaster> appMasterList, String appName) {
        for (AppMaster data : appMasterList) {
            try {
                if (appName.equals(data.getAppName())) {
                    return data;
                }
            } catch (Exception e) {
                // do nothing.
            }
        }
        return null;
    }

    /**
     * APPリストをDBから取得する.
     *
     * @return
     */
    public static List<AppMaster> getAppListFromDB() {
        AppMasterTableController ctrl;
        try {
            ctrl = new AppMasterTableController();
            return ctrl.selectAll();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
        }
        return null;
    }

    /**
     * APPマスターの更新済みフラグをtrueにする.
     *
     * @param entity :
     */
    public static void updateAppMasterUpdatedFlagTrue(AppMaster entity) {
//                SEDatabaseManager sedbm = SEDatabaseManager.getInstance();
//                try (Connection conn = sedbm.getConnection(false); Statement stmt = conn.createStatement()) {
//                    AppMasterTableController ctrl = new AppMasterTableController(stmt);
//                    try {
//                        ctrl.updateUpdatedFlag(entity, true);
//                    } catch (Exception e) {
//                        conn.rollback();
//                        throw e;
//                    }
//                    conn.commit();
//                } catch (Exception e) {
//                    // TODO : error ?
//                }

        AppMasterTableController ctrl;
        try {
            ctrl = new AppMasterTableController();
            ctrl.update(entity);
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
        }
    }

    /**
     * APPマスターテーブルのappliedを全てTRUEにセットする.
     *
     */
    public static void updateAllAppMasterAppliedTrue(boolean isFallBack) {
    	log.info("updateAllAppMasterAppliedTrue start");
    	boolean isEdge = false;
    	try {
    		AppMasterTableController ctrl = new AppMasterTableController();
    		List<AppMaster> list = ctrl.selectAll();
    		for (AppMaster it : list) {
//    			log.info("AppMaster " + it.getAppName() + "Updated : " + it.getUpdated());
    			if (it.getUpdated() == false) {
    				String appName = it.getAppName();
    				log.info("Update appName : " + appName);
    				if (appName.equalsIgnoreCase("CldComApp")) {
    					isEdge = true;
    					log.info("isEdge : true");
    				}else {
    					isEdge = false;
    					log.info("isEdge : false");
    				}
    				break; 
    			}
    		}

    		for( int i=0; i < list.size(); i++ ) {
    			if( !list.get(i).getUpdated() || !list.get(i).getApplied() ) {
    				list.get(i).setUpdated(true);
    				list.get(i).setApplied(true);;
    				
    				DatabaseEntityManager.getEntityManager().getTransaction().begin();
    				ctrl.update(list.get(i));
    				DatabaseEntityManager.getEntityManager().getTransaction().commit();
    			}
    		}
    		log.info("updateAllAppMasterAppliedTrue : OK");
    		
    		//update_status
    		//running: 更新中/awaiting: 更新待機/finished: 終了/failed_temporarily: 一時的な失敗/failed_seriously: 重大な失敗/timeout: タイムアウト
    		String keyVersion = "measVersion";
    		String keyStatus = "measUpdateStatus";
    		String UpdateVersion = "measUpdateVersion";
    		if( isEdge ) {
    			keyVersion = "edgeVersion";
    			keyStatus = "edgeUpdateStatus";
    			UpdateVersion = "edgeUpdateVersion";
    		}
    		
    		if(isFallBack) {
    			updateConfigInfo( keyStatus, "failed_seriously" );
    			log.info("status : failed_seriously");
    		}
    		else {
    			log.info("Update Version and status start");
    			String ver = getConfigInfo(UpdateVersion); //DeviceShadow用ver
    			updateConfigInfo( keyVersion, ver );
    			updateConfigInfo( keyStatus, "finished");
    			log.info("Update Version : "+ ver + " status : finished");
    		}  
    	} catch (SQLException e) {
    		e.printStackTrace();
    		log.info("SQL Exception " + e.getMessage());
    	}
    }

    private static void updateConfigInfo(String keyName, String keyValue) {
        ConfigInfoTableController ciCtrl = new ConfigInfoTableController();
        EntityManager em = DatabaseEntityManager.getNewEntityManager();
        ConfigInfo ci = ciCtrl.findByKeyName(keyName, Enviroment.forEdge, em);
        if(ci != null) {
        	em.getTransaction().begin();
        	ci.setKeyValue(keyValue);
        	try {
        		ciCtrl.update(ci, em);
        		em.getTransaction().commit();
        	} catch (SQLException e) {
        		// TODO 自動生成された catch ブロック
        		e.printStackTrace();
        	} finally {
        		if (em != null) {
        			em.close();
        		}
        	}
        }
        
    }
    
    public static String getConfigInfo(String key) {
        ConfigInfoTableController ctrl = new ConfigInfoTableController();
        ConfigInfo ci = ctrl.findByKeyName(key, Enviroment.forEdge);
        return ci.getKeyValue();
    }

    /**
     * SQLファイルを実行する.
     *
     * @param sqlUpdateInfo :
     */
    public static void executeSqlFile(SqlUpdateInfo sqlUpdateInfo) throws IOException, InterruptedException {
        String[] cmd = { "/bin/sh", "-c", "psql -f" + sqlUpdateInfo.getSqlFilePath() + " -U postgres -h localhost -d sedb " };
        ProcessBuilder builder = new ProcessBuilder(cmd);
        Process process = builder.start();
        process.waitFor();
    }

    /**
     * 対象のファイルを削除する
     *
     * @param FilePath 絶対パス
     * @throws IOException
     * @throws InterruptedException
     */
    public static void deleteTargetFile(String FilePath) throws IOException, InterruptedException {
        String[] cmd = { "/bin/sh", "-c", "sudo rm " + FilePath };
        ProcessBuilder builder = new ProcessBuilder(cmd);
        Process process = builder.start();
        process.waitFor();
    }
}
