package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;

import jp.co.smartsolar.smartedge.database.entity.AppMaster;
import jp.co.smartsolar.smartedge.database.entity.GroupMaster;

/**
 * APPマスター操作クラス.
 *
 * @author ubiq
 *
 */
public class AppMasterTableController extends BaseTableController<AppMaster> {

    public AppMasterTableController() {
        super();
    }

    public List<AppMaster> selectAll() throws SQLException {
        return getEntityManager().createNamedQuery("AppMaster.findAll", AppMaster.class).getResultList();
    }

    //TODO:実行できない可能性大
//    public List<AppMaster> findKey(String key, String val) {
//        return getEntityManager().createNamedQuery("AppMaster.findByKeyName", AppMaster.class).setParameter("key", "a."+key).setParameter("val", val).getResultList();
//    }

    public AppMaster findByAppName(String keyName) {
        return getEntityManager().createNamedQuery("AppMaster.findByAppName", AppMaster.class).setParameter("key", keyName).getSingleResult();
    }

    public List<AppMaster> findByModuleTypeList(GroupMaster grouoMaster) {
        return getEntityManager().createNamedQuery("AppMaster.findByModuleType", AppMaster.class).setParameter("key", grouoMaster).getResultList();
    }

    /**
     * 更新済みフラグを更新する.
     *
     * @param entity 更新対象のentity
     */
    public void updateUpdatedFlag(AppMaster entity, boolean flag) throws SQLException {

    }

    /**
     * 適用済みフラグを全てTRUEに更新する.
     *
     * @param flag :
     * @throws SQLException :
     */
    public void updateAllAppliedFlag(boolean flag) throws SQLException {

    }

    /**
     * FWアプリ更新するデータ更新する.
     *
     * @param entity 更新対象のentity
     */
    public void updateFwAppData(AppMaster entity) throws SQLException {
    }
}
