package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jp.co.smartsolar.smartedge.database.entity.DeviceMaster;
import jp.co.smartsolar.smartedge.database.entity.SmartmeterMaster;
import jp.co.smartsolar.smartedge.util.OpeUtil;

public class SmartmeterMasterTableController extends BaseTableController<SmartmeterMaster> {

    public SmartmeterMasterTableController() {
        super();
    }

    public List<SmartmeterMaster> findAll() throws SQLException{
        return getEntityManager().createNamedQuery("SmartmeterMaster.findAll", SmartmeterMaster.class).getResultList();
    }

    public SmartmeterMaster findId(int id) throws SQLException{
        return getEntityManager().createNamedQuery("SmartmeterMaster.findId", SmartmeterMaster.class).setParameter("id", id)
                .getSingleResult();
    }
    
    public SmartmeterMaster findByClassName(String className) throws SQLException{
        return getEntityManager().createNamedQuery("SmartmeterMaster.findByClassName", SmartmeterMaster.class).setParameter("className", className)
                .getSingleResult();
    }
   
    public SmartmeterMaster findByOne(String smartmeterId, String smartmeterType) {
        // 検索キーがユニークになっていないため。１件にならない可能性あり。
        List<SmartmeterMaster> ret = getEntityManager()
                .createNamedQuery("SmartmeterMaster.findBySmartmeterIdAndSmartmeterType", SmartmeterMaster.class)
                .setParameter("smartmeterId", smartmeterId)
                .setParameter("smartmeterType", smartmeterType)
                .getResultList();
        if (ret.size() > 0) {
            return ret.get(0);
        }
        return null;
    }

    public SmartmeterMaster findById(int id) throws SQLException{
        return getEntityManager().createNamedQuery("SmartmeterMaster.findById", SmartmeterMaster.class).setParameter("id", id)
                .getSingleResult();
    }

    public void updateId(int oldId, int newId) throws SQLException{
        //TODO:JPAでIDをUPDATEが動かないので苦肉の策
        String fpath = "/opt/smartedge/bat/sql_update.sh";
        OpeUtil.exeCommand(new String[] {
                fpath, "sedb", "smartmeter_master", "postgres", "postgres", String.valueOf(oldId), String.valueOf(newId) });
    }

    public void initId() throws SQLException{
        //TODO:JPAでIDをUPDATEが動かないので苦肉の策
        String fpath = "/opt/smartedge/bat/sql_init.sh";
        OpeUtil.exeCommand(new String[] {
                fpath, "sedb", "smartmeter_master", "postgres", "postgres" });
    }
}
