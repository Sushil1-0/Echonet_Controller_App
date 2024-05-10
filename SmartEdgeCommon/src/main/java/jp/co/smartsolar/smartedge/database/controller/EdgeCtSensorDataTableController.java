package jp.co.smartsolar.smartedge.database.controller;

import jp.co.smartsolar.smartedge.database.entity.EdgeCtSensorData;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;

public class EdgeCtSensorDataTableController extends BaseTableController<EdgeCtSensorData> {

	public EdgeCtSensorDataTableController() {
	}
	
	public List<EdgeCtSensorData> findAll() {
		return getEntityManager().createNamedQuery("EdgeCtSensorData.findAll", EdgeCtSensorData.class).getResultList();
	}
	
	public EdgeCtSensorData getLatestData() {
		return getEntityManager().createNamedQuery("EdgeCtSensorData.getLatestData", EdgeCtSensorData.class)
				.getResultList().get(0);
	}
	
	public List<EdgeCtSensorData>getRecordsByChannelId(int channelId) {
		return getEntityManager().createNamedQuery("EdgeCtSensorData.getRecordsByChannelId", EdgeCtSensorData.class)
				.setParameter("channelId", channelId)
				.setMaxResults(5)
				.getResultList();
	}
	
	public List<EdgeCtSensorData> getLatestFiveRecords() {
		List<EdgeCtSensorData> list = new ArrayList<>();
		for (int channel = 3; channel <= 7; channel++) {
			list.addAll(getRecordsByChannelId(channel));
		}		
		return list;		
	}
	
	public List<EdgeCtSensorData> findByChannelId(Integer channelId) {
		return getEntityManager().createNamedQuery("EdgeCtSensorData.findByChannelId", EdgeCtSensorData.class)
				.setParameter("channelId", channelId)
				.getResultList();
	}
	
	public List<EdgeCtSensorData> findByDate(Timestamp start, Timestamp end) {
		return getEntityManager().createNamedQuery("EdgeCtSensorData.findByDate", EdgeCtSensorData.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
	}
	
	//added 20210322  
	public List<EdgeCtSensorData> findByDate(Timestamp start, Timestamp end, EntityManager em) throws SQLException {
		return em.createNamedQuery("EdgeCtSensorData.findByDate", EdgeCtSensorData.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
	}
	
	public void insert(EdgeCtSensorData entity) {
		getEntityManager().persist(entity);
	}
	
}
