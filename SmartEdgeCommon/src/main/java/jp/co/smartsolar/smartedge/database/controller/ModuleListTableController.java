package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jp.co.smartsolar.smartedge.database.entity.ModuleList;

public class ModuleListTableController extends BaseTableController<ModuleList> {

    public ModuleListTableController() {
        super();
    }

    public List<ModuleList> findAll() throws SQLException{
            return getEntityManager().createNamedQuery("ModuleList.findAll", ModuleList.class).getResultList();
    }

    public ModuleList findById(int id) throws SQLException{
        return getEntityManager().createNamedQuery("DeviceMaster.findById", ModuleList.class).setParameter("id", id)
               .getSingleResult();
    }
}
