package jp.co.smartsolar.smartedge.database.controller;

import jp.co.smartsolar.smartedge.database.entity.MeasCtSensorData;

import java.util.List;

import jakarta.persistence.TypedQuery;

public class MeasCtSensorDataTableController extends BaseTableController<MeasCtSensorData> {

	public MeasCtSensorDataTableController() {
	}
	
	public List<MeasCtSensorData> findAll() {
		return getEntityManager().createNamedQuery("MeasCtSensorData.findAll", MeasCtSensorData.class).getResultList();
	}
	
//	public MeasCtSensorData getLatestData() {
//		return getEntityManager().createNamedQuery("MeasCtSensorData.getLatestData", MeasCtSensorData.class)
//				.getResultList().get(0);
//	}
	
	public List<MeasCtSensorData> getLatestData() {
		TypedQuery<MeasCtSensorData> tq  =  getEntityManager().createNamedQuery("MeasCtSensorData.getLatestData", MeasCtSensorData.class);
		if(tq != null) {
			TypedQuery<MeasCtSensorData> res = tq.setMaxResults(5);      
			List<MeasCtSensorData> ans = res.getResultList();
			if (ans.size() > 0) {
				return ans;
			}
		}
		return null;
	}
	
	
	public List<MeasCtSensorData> findByChannelId(Integer channelId) {
		return getEntityManager().createNamedQuery("MeasCtSensorData.findByChannelId", MeasCtSensorData.class)
				.setParameter("channelId", channelId)
				.getResultList();
	}
	
	public void insert(MeasCtSensorData entity) {
		if(entity != null)
			getEntityManager().persist(entity);
	}
	
}
