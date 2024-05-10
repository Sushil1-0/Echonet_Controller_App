package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jp.co.smartsolar.smartedge.database.entity.SqlUpdateInfo;

/**
 * SQL更新情報テーブル操作クラス.
 *
 * @author ubiq
 *
 */
public class SqlUpdateInfoTableController extends BaseTableController<SqlUpdateInfo> {

    /**
     * 更新済みフラグが指定されているものと一致するエンティティをリストにして返す
     *
     * @param updateFlag
     * @return
     */
    public List<SqlUpdateInfo> findByupdatedList(boolean updateFlag) {
        return getEntityManager().createNamedQuery("SqlUpdateInfo.findByKeyName", SqlUpdateInfo.class).setParameter("key", updateFlag)
                .getResultList();
    }

    // ------------------------------
    // WHERE
    public static final String WHERE_NEED_UPDATE = " WHERE updated = false and sql_file_deleted = false "; // SQL更新が必要なレコードを抽出
    // ------------------------------

    public SqlUpdateInfoTableController() {
        super();
    }

    /**
     * 更新済みフラグを更新する.
     *
     * @param sqlUpdateInfo
     * @param flag
     */
    public void updateUpdatedFlag(SqlUpdateInfo sqlUpdateInfo, boolean flag) throws SQLException {
        sqlUpdateInfo.setUpdated(flag);
        this.update(sqlUpdateInfo);
    }

    /**
     * SQLファイル削除済みフラグを更新する.
     *
     * @param sqlUpdateInfo
     * @param flag
     * @throws SQLException
     */
    public void updateSqlFileDeleteFlag(SqlUpdateInfo sqlUpdateInfo, boolean flag) throws SQLException {
        sqlUpdateInfo.setSqlFileDeleted(flag);
        this.update(sqlUpdateInfo);
    }

    /**
     * FWアプリ更新するデータ追加する.
     *
     * @param sqlUpdateInfo
     * @param flag
     * @throws SQLException
     */
//    public void insertFwAppData(SqlUpdateInfo sqlUpdateInfo) throws SQLException {
//        //        String query = "INSERT INTO " + TABLE_NAME + " ("
//        //                + String.format("%s,%s,%s,%s,%s", COL_KEY_GROUP_ID, COL_KEY_SQL_FILE_PATH, COL_KEY_UPDATED, COL_KEY_SQL_FILE_DELETED,COL_KEY_SQL_UPDATE_TIME) + ") VALUES ("
//        //                + String.format("%s,'%s',false,false,current_timestamp", sqlUpdateInfo.getGroupId(), sqlUpdateInfo.getSqlFilePath()) + ")";
//        //        stmt.executeQuery(query);
//    }

}
