package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jp.co.smartsolar.smartedge.database.entity.GroupMaster;

public class GroupMasterTableController extends BaseTableController<GroupMaster> {

    public GroupMasterTableController() {
        super();
    }

    public List<GroupMaster> selectAll() throws SQLException {
        return getEntityManager().createNamedQuery("GroupMaster.findall", GroupMaster.class).getResultList();
    }

    public GroupMaster findById(int id) throws SQLException {
        return getEntityManager().createNamedQuery("GroupMaster.findById", GroupMaster.class).setParameter("id", id).getSingleResult();
    }

}
