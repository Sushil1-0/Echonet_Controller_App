package jp.co.smartsolar.smartedge.database.controller;

import java.util.Date;
import java.util.List;
import jakarta.persistence.TypedQuery;
import jp.co.smartsolar.smartedge.database.entity.DeviceMaster;
import jp.co.smartsolar.smartedge.database.entity.MeasBatteryCharge;

/**
 * 蓄電残量テーブル操作クラス (測定モジュール).
 *
 * @author ubiq
 *
 */
public class MeasBatteryChargeController extends BaseTableController<MeasBatteryCharge> {

    public MeasBatteryChargeController() {
        super();
    }

    public void deleteOldData(Date date) {
        getEntityManager().createNamedQuery("MeasBatteryCharge.deleteOldData").setParameter("oldDate", date).executeUpdate();
    }

    public List<MeasBatteryCharge> findByDeviceMasterId(DeviceMaster deviceMasterId) {
        return getEntityManager().createNamedQuery("MeasBatteryCharge.findByDeviceMasterId", MeasBatteryCharge.class)
                .setParameter("id", deviceMasterId).getResultList();
    }

    
    public MeasBatteryCharge getLatestData() {
        TypedQuery<MeasBatteryCharge> tq = getEntityManager().createNamedQuery("MeasBatteryCharge.getLatestData", MeasBatteryCharge.class);
        if(tq != null) {
    		TypedQuery<MeasBatteryCharge> res = tq.setMaxResults(1);      
    		List<MeasBatteryCharge> ans = res.getResultList();
    		if (ans.size() > 0) {
    			return ans.get(0);
    		}
    	}
        return null;
    }
    
    /**
     * SetQuery()メソッドで使用 定期DB書き込み用 トランザクションの開始とコミットは別で行う.
     *
     * @param em
     * @param entity
     */
    public void insertQuery(MeasBatteryCharge entity) {
        getEntityManager().persist(entity);
    }
}
