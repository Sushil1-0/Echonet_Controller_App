package jp.co.smartsolar.smartedge.database.controller;

import jp.co.smartsolar.smartedge.database.entity.CompanyMaster;

/**
 * 開発会社マスタテーブル操作クラス.
 *
 * @author ubiq
 *
 */
public class CompanyMasterTableController extends BaseTableController<CompanyMaster> {

    public CompanyMasterTableController() {
        super();
    }

    public CompanyMaster findById(int id) {
        return getEntityManager().createNamedQuery("CompanyMaster.findById", CompanyMaster.class).setParameter("id", id).getSingleResult();
    }
}
