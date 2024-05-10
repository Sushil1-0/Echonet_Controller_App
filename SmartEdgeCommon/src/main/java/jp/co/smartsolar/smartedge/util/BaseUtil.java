package jp.co.smartsolar.smartedge.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import jp.co.smartsolar.smartedge.log.Log;

/**
 * 便利クラス.
 *
 * @author ubiq
 *
 */
public class BaseUtil {

    /**
     * OSコマンド結果の標準出力結果をStringで取得する.
     *
     * @param process : java.lang.Process
     * @return String
     */
    public static String getStdoutToString(Process process) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            return "";
        }
        return sb.toString();
    }

    /**
     * スレッドスリープ.
     *
     * @param ms : ミリ秒
     */
    public static void sleepThread(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            // do nothing.
        }
    }

    /**
     * Timestamp to String.
     *
     * @param timestamp
     * @return
     */
    public static String timestampToString(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String timestampString = sdf.format(timestamp);
        return timestampString;
    }

    /**
     * ファイルをzip圧縮する
     *
     * @param inputBaseDir 入力ファイルを指定する際のベース・フォルダ・パス
     * @param inputFiles 圧縮するファイル名。inputBaseDirからの相対パスで指定
     * @param outputFile 作成するzipファイル名
     * @throws Exception
     */
    public static void compressZip(String inputBaseDir, String[] inputFiles, String outputFile) throws Exception {
        // nullチェック
        if (inputBaseDir == null) {
            return;
        }
        if (inputFiles == null) {
            return;
        }
        if (outputFile == null) {
            return;
        }

        // zipファイルの作成
        // try-with-resource構文でファイルcloseしている
        try (
                // 出力ファイル指定
                FileOutputStream out = new FileOutputStream(outputFile);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                ZipOutputStream archive = new ZipOutputStream(out)) {
            // 入力ファイルの数だけエントリーを追加
            for (String fileName : inputFiles) {
                // エントリー1つ分を出力開始
                ZipEntry entry = new ZipEntry(fileName);
                archive.putNextEntry(entry);

                // フォルダの場合は次へ
                if (fileName.endsWith("/")) {
                    archive.closeEntry();
                    continue;
                }

                // 入力ファイル指定
                try (FileInputStream fis = new FileInputStream(inputBaseDir  + fileName);
                        BufferedInputStream bis = new BufferedInputStream(fis)) {
                    // エントリーの中身を出力
                    int size = 0;
                    byte[] buf = new byte[1024];
                    while ((size = bis.read(buf)) > 0) {
                        archive.write(buf, 0, size);
                    }
                } catch(FileNotFoundException fe) {
                    Log log = Log.getInstance();
                    if(log != null) {
                        log.warn("compressZip : ", fe);
                    }
                }

                // エントリー1つ文の出力を終了
                archive.closeEntry();
            }
        }
    }

    /**
     * zip解凍
     *
     * @param inputFile 解凍するzipファイル
     * @param outputDir 解凍先フォルダ
     * @throws Exception
     */
    public static void decompressZip(String inputFile, String outputDir) throws Exception {
        // zipファイルの読込
        // try-with-resource構文でファイルcloseしている
        try (FileInputStream fis = new FileInputStream(inputFile); 
        	ZipInputStream archive = new ZipInputStream(fis)) {
            // エントリーを1つずつファイル・フォルダに復元
            ZipEntry entry = null;
            while ((entry = archive.getNextEntry()) != null) {
                // ファイルを作成
                File file = new File(outputDir + "/" + entry.getName());

                // フォルダ・エントリの場合はフォルダを作成して次へ
                if (entry.isDirectory()) {
                    file.mkdirs();
                    continue;
                }

                // ファイル出力する場合、
                // フォルダが存在しない場合は事前にフォルダ作成
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                // ファイル出力
                try (FileOutputStream fos = new FileOutputStream(file); 
                	BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                    // エントリーの中身を出力
                    int size = 0;
                    byte[] buf = new byte[1024];
                    while ((size = archive.read(buf)) > 0) {
                        bos.write(buf, 0, size);
                    }
                }
            }
        }
    }

    public static List<String> exeCommand(String[] cmd) {
        return exeCommand(cmd, true);
    }

    /**
     * コマンド実行.
     *
     * @param cmd :
     * @throws Exception :
     */
    public static List<String> exeCommand(String[] cmd, boolean isWait) {
        Runtime runtime = Runtime.getRuntime();
        List<String> ret = new ArrayList<String>();
        Process process = null;
        try {
            process = runtime.exec(cmd);
            if( isWait ) {
                process.waitFor();
                InputStream ism = process.getInputStream();
                InputStreamReader reader = new InputStreamReader(ism);
                BufferedReader br = new BufferedReader(reader);
                String rst ="";
                while ((rst = br.readLine()) != null) {
//                    System.out.println(rst);
                    ret.add(rst);
                }
            }

        } catch (IOException IOe) {
            System.out.println("IOException: "+IOe.getMessage());
        } catch (InterruptedException Inte) {
            System.out.println("IOException: "+Inte.getMessage());
        }finally{
            if(process!=null) {
                process.destroy();
            }
        }
        /*
        List<String> ret = new ArrayList<String>();
        Process process = null;
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            process = pb.start();
            process.waitFor();

            InputStream ism = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(ism);
            BufferedReader br = new BufferedReader(reader);
            String rst ="";
            while ((rst = br.readLine()) != null) {
                System.out.println(rst);
                ret.add(rst);
            }
        } catch (IOException IOe) {
            System.out.println("IOException: "+IOe.getMessage());
        } catch (InterruptedException Inte) {
            System.out.println("IOException: "+Inte.getMessage());
        }finally{
            if(process!=null) {
                process.destroy();
            }
        }*/
        return ret;
    }

    /**
     * ファイル名一覧取得.
     *
     * @param tergetPath : 対象ディレクトリ
     * @param searchName : 検索文字列
     * @return List<String> : ファイル名一覧（ディレクトリ名なし）
     */
    public static List<String> getFiles(String tergetPath, String searchName){
        List<String> ret = new ArrayList<String>();
        File dir = new File(tergetPath);
        File[] list = dir.listFiles();
        for(int i=0; i<list.length; i++) {
          if(list[i].getName().contains(searchName)) {
            ret.add(list[i].getName());
          }
        }
        return ret;
    }

    /*
     * ファイル、ディレクトリ削除
     * 子ディレクトリ非対応
     */
    public static boolean deletePath(String path) {
        File file = new File(path);
        if(file.isFile()) {
            if (file.exists()) {
                System.out.print(path);
                if (file.delete()) {
                    System.out.println(" 削除成功");
                    return true;
                } else {
                    System.out.println(" 削除失敗");
                }
            }
        }
        else if(file.isDirectory()) {
            if (file.exists()) {
                System.out.print(path);
                //ディレクトリ内の一覧を取得
                File[] files = file.listFiles();
                for(int i=0; i<files.length; i++) {
                    File f = files[i];
                    if (f.delete()) {
                        System.out.println(f.getName()+" 削除成功");
                    } else {
                        System.out.println(f.getName()+" 削除失敗");
                    }
                }
                if (file.delete()) {
                    System.out.println(path+" 削除成功");
                    return true;
                } else {
                    System.out.println(path+" 削除失敗");
                }
            }
        }
        return false;
    }

}
