package jp.co.smartsolar.smartedge.database.controller;

import jp.co.smartsolar.smartedge.database.entity.OutputControl;
import jp.co.smartsolar.smartedge.database.entity.PowerControlResult;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OutputControlController extends BaseTableController<OutputControl> {
	
	public OutputControlController() {
		super();
	}
	public List<OutputControl> selectAll() throws SQLException {
		return getEntityManager().createNamedQuery("OutputControl.selectAll",OutputControl.class).getResultList();
	}
	public List<OutputControl> findFirstByDatetimeOrderByIdDesc(Timestamp datetime) throws SQLException{
		return getEntityManager().createNamedQuery("OutputControl.findFirstByDatetimeOrderByIdDesc",OutputControl.class).getResultList();
	}
	
}
