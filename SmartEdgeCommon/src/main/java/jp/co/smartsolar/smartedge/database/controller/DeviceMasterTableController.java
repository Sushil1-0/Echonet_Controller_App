package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import jp.co.smartsolar.smartedge.database.entity.DeviceMaster;
import jp.co.smartsolar.smartedge.util.OpeUtil;

/**
 * デバイステーブル操作クラス.
 *
 * @author ubiq
 *
 */
public class DeviceMasterTableController extends BaseTableController<DeviceMaster> {

    public DeviceMasterTableController() {
        super();
    }

    public List<DeviceMaster> findAll() throws SQLException{
        return getEntityManager().createNamedQuery("DeviceMaster.findAll", DeviceMaster.class).getResultList();
    }

    public DeviceMaster findById(int id) throws SQLException{
        return getEntityManager().createNamedQuery("DeviceMaster.findById", DeviceMaster.class).setParameter("id", id)
                .getSingleResult();
    }

    public DeviceMaster findByCompanyDevice(int companyId,int pcstypeId) throws SQLException{
        //検索キーがユニークになっていないため。１件にならない可能性あり。
        List<DeviceMaster> ret = getEntityManager().createNamedQuery("DeviceMaster.findByCompanyIdAndPcstypeId", DeviceMaster.class)
                .setParameter("companyId", companyId)
                .setParameter("pcstypeId", pcstypeId)
                .getResultList();
        if( ret.size() > 0 ) {
            return ret.get(0);
        }
        return null;
    }

    public DeviceMaster findByPcsId(String pcsId) throws SQLException{
        return getEntityManager().createNamedQuery("DeviceMaster.findByPcsId", DeviceMaster.class).setParameter("pcsId", pcsId)
                .getSingleResult();
    }

    public DeviceMaster findByOne(String pcsId,int pcstypeId, String modelId) throws SQLException{
        //検索キーがユニークになっていないため。１件にならない可能性あり。
        List<DeviceMaster> ret = getEntityManager().createNamedQuery("DeviceMaster.findByPcsIdAndPcstypeIdAndModelId", DeviceMaster.class)
                .setParameter("pcsId", pcsId)
                .setParameter("modelId", modelId)
                .setParameter("pcstypeId", pcstypeId)
                .getResultList();
        if( ret.size() > 0 ) {
            return ret.get(0);
        }
        return null;
    }

    public void updateId(int oldId, int newId) throws SQLException{
//        Query query = getEntityManager().createNamedQuery("DeviceMaster.updateId", DeviceMaster.class);
//        query.setParameter("id", oldId);
//        query.executeUpdate();

//        getEntityManager().createNamedQuery("DeviceMaster.updateId", DeviceMaster.class)
//            .setParameter(1, oldId)
//            .executeUpdate();

//            .setParameter("nid", String.valueOf(newId))
//            .setParameter("id", oldId)
//            .executeUpdate();
//        getEntityManager().createQuery("UPDATE DeviceMaster d SET id = "+newId+" WHERE d.id = "+oldId)
//            .executeUpdate();

        //TODO:JPAでIDをUPDATEが動かないので苦肉の策
        String fpath = "/opt/smartedge/bat/sql_update.sh";
//        String cmd = String.format("%s sedb postgres %d %d", fpath, oldId, newId);
        OpeUtil.exeCommand(new String[] {
                fpath, "sedb", "device_master", "postgres", "postgres", String.valueOf(oldId), String.valueOf(newId) });
    }

    public void updatePcsIdModId(int id, String pcsId, String modelId) throws SQLException{
        int ret = getEntityManager().createNamedQuery("DeviceMaster.updatePcsIdModelId", DeviceMaster.class)
                .setParameter("id", id)
                .setParameter("pcsId", pcsId)
                .setParameter("modelId", modelId)
                .executeUpdate();
        System.out.println("update ret:"+ret);
  }

    public void initId() throws SQLException{
        //TODO:JPAでIDをUPDATEが動かないので苦肉の策
        String fpath = "/opt/smartedge/bat/sql_init.sh";
        OpeUtil.exeCommand(new String[] {
                fpath, "sedb", "device_master", "postgres", "postgres" });
    }
}
