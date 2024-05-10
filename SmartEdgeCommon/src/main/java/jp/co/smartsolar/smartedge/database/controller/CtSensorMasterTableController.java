package jp.co.smartsolar.smartedge.database.controller;

import jp.co.smartsolar.smartedge.database.entity.CtSensorMaster;

import java.sql.SQLException;
import java.util.List;

public class CtSensorMasterTableController extends BaseTableController<CtSensorMaster> {
	
	public CtSensorMasterTableController() {}
	
	public List<CtSensorMaster> findAll () {
		return getEntityManager().createNamedQuery("CtSensorMaster.findAll", CtSensorMaster.class).getResultList();
	}
	
	public CtSensorMaster findByChannelId(Integer channelId) {
		return getEntityManager().createNamedQuery("CtSensorMaster.findByChannelId", CtSensorMaster.class)
				.setParameter("channelId", channelId)
				.getSingleResult();
	}
	
	public List<CtSensorMaster> findByCtType(Integer ctType) {
		return getEntityManager().createNamedQuery("CtSensorMaster.findByCtType", CtSensorMaster.class)
				.setParameter("ctType", ctType)
				.getResultList();
	}
	
	public int getChannelVoltage(int channelId) {
		return getEntityManager().createNamedQuery("CtSensorMaster.getChannelVoltage", CtSensorMaster.class)
				.setParameter("channelId", channelId)
				.getSingleResult()
				.getVoltage();
	}
	
	public void merge(CtSensorMaster ctSensorMaster) {
		try {
			getEntityManager().getTransaction().begin();
			update(ctSensorMaster);
			getEntityManager().getTransaction().commit();
		} catch (SQLException throwables) {
			System.out.println("update error: " + throwables.getMessage());
		}
	}
}