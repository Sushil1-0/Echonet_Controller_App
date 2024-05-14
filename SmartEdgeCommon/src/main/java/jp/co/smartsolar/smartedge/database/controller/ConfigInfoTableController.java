package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;

import jp.co.smartsolar.smartedge.database.entity.ConfigInfo;
import jp.co.smartsolar.smartedge.database.entity.GroupMaster;

/**
 * 設定情報テーブル操作クラス.
 *
 * @author ubiq
 *
 */
public class ConfigInfoTableController extends BaseTableController<ConfigInfo> {
    /** 環境定義. */
    public enum Enviroment {
        /** Edgeモジュール側. */
        forEdge(1),
        /** 測定モジュール側. */
        forMeas(2);

        private Integer id;

        Enviroment(Integer id) {
            this.id = id;
        }
        public Integer getId() {
            return this.id;
        }
    }

    public ConfigInfoTableController() {
        super();
    }

    public List<ConfigInfo> findAll() {
        return getEntityManager().createNamedQuery("ConfigInfo.findAll",ConfigInfo.class)
                .getResultList();
    }

    public ConfigInfo findByKeyName( String keyName ) {
        return getEntityManager().createNamedQuery("ConfigInfo.findByKeyName",ConfigInfo.class)
                .setParameter("key", keyName)
                .getSingleResult();
    }

    public ConfigInfo findByKeyName( String keyName, Enviroment env ) {
        GroupMaster groupMaster = new GroupMaster();
        groupMaster.setId(env.getId());
        return getEntityManager().createNamedQuery("ConfigInfo.findByGroupIdAndKeyName",ConfigInfo.class)
                .setParameter("groupMaster",groupMaster)
                .setParameter("keyName", keyName)
                .getSingleResult();
    }
    
    //added 20220519
    public ConfigInfo findByKeyNameEm( EntityManager em, String keyName) {
        return em.createNamedQuery("ConfigInfo.findByKeyName",ConfigInfo.class)
                .setParameter("key", keyName)
                .getSingleResult();
    }
    
    public ConfigInfo findByGroupIdAndKeyName( String keyName, Integer groupId) {
        GroupMaster groupMaster = new GroupMaster();
        groupMaster.setId(groupId);
        return getEntityManager().createNamedQuery("ConfigInfo.findByGroupIdAndKeyName",ConfigInfo.class)
                .setParameter("groupMaster",groupMaster)
                .setParameter("keyName", keyName)
                .getSingleResult();
    }
    
    
    public ConfigInfo findByKeyName( String keyName, Enviroment env, EntityManager em) {
        GroupMaster groupMaster = new GroupMaster();
        groupMaster.setId(env.getId());
        return em.createNamedQuery("ConfigInfo.findByGroupIdAndKeyName",ConfigInfo.class)
                .setParameter("groupMaster",groupMaster)
                .setParameter("keyName", keyName)
                .getSingleResult();
    }

    /**
     * Edgeモジュール用. Get 初回起動完了フラグ
     *
     * @return
     */
    public ConfigInfo getEdgeModuleFirstBootCompleteFlag() {
        GroupMaster groupMaster = new GroupMaster();
        //EDGEモジュール用IDセット
        groupMaster.setId(1);
        return  getEntityManager().createNamedQuery("ConfigInfo.findByGroupIdAndKeyName",ConfigInfo.class)
                .setParameter("groupMaster",groupMaster)
                .setParameter("keyName", "startCompletionFlag")
                .getSingleResult();
    }

    /**
     * 測定モジュール用. Get 初回起動完了フラグ
     *
     * @return
     */
    public ConfigInfo getMeasurementModuleFirstBootCompleteFlag() {
        GroupMaster groupMaster = new GroupMaster();
        //測定モジュール用IDセット
        groupMaster.setId(2);
        return  getEntityManager().createNamedQuery("ConfigInfo.findByGroupIdAndKeyName",ConfigInfo.class)
                .setParameter("groupMaster",groupMaster)
                .setParameter("keyName", "startCompletionFlag")
                .getSingleResult();
    }

    public void updateData(String keyName, String keyValue) throws SQLException {
    }

    public void updateDataList(List<ConfigInfo> ConfigInfoList) throws SQLException {
        getEntityManager();
        for(ConfigInfo s : ConfigInfoList) {
            this.update(s);
        }
    }
    
    public void saveData(ConfigInfo configInfo) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(configInfo);
        entityManager.getTransaction().commit();
    }
    
    public ConfigInfo find(String keyName, int groupId) {
        List<ConfigInfo> configInfos = getEntityManager().createNamedQuery("ConfigInfo.findByGroupIdAndKeyName", ConfigInfo.class)
                .setParameter("keyName", keyName)
                .setParameter("groupMaster", new GroupMaster(groupId))
                .getResultList();
        if (configInfos != null && configInfos.size() > 0) {
            return configInfos.get(0);
        }
        return null;
    }
    
    
}
