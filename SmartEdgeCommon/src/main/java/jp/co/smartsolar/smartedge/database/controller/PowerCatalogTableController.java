package jp.co.smartsolar.smartedge.database.controller;

import java.sql.Timestamp;
import java.util.List;
import jp.co.smartsolar.smartedge.database.entity.PowerCatalog;

public class PowerCatalogTableController extends BaseTableController<PowerCatalog> {

    public PowerCatalogTableController() {
        super();
    }

	public List<PowerCatalog> findAll() {
		return getEntityManager().createNamedQuery("PowerCatalog.findAll", PowerCatalog.class).getResultList();
	}
    public List<PowerCatalog> findFirstByDatetimeOrderByIdDesc(Timestamp datetime) {
        return getEntityManager().createNamedQuery("PowerCatalog.findFirstByDatetimeOrderByIdDesc", PowerCatalog.class).getResultList();
    }

}
