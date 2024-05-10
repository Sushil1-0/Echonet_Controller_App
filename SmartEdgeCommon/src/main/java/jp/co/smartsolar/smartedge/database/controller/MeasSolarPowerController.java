package jp.co.smartsolar.smartedge.database.controller;

import java.util.Date;
import java.util.List;
import jakarta.persistence.TypedQuery;
import jp.co.smartsolar.smartedge.database.entity.DeviceMaster;
import jp.co.smartsolar.smartedge.database.entity.MeasSolarPower;

/**
 * 太陽光発電電力量テーブル操作クラス (測定モジュール).
 *
 * @author ubiq
 *
 */
public class MeasSolarPowerController extends BaseTableController<MeasSolarPower> {

    public MeasSolarPowerController() {
        super();
    }

    public void deleteOldData(Date date) {
        getEntityManager().createNamedQuery("MeasSolarPower.deleteOldData").setParameter("oldDate", date).executeUpdate();
    }

    public List<MeasSolarPower> findByDeviceMasterId(DeviceMaster deviceMasterId) {
        return getEntityManager().createNamedQuery("MeasSolarPower.findByDeviceMasterId", MeasSolarPower.class).setParameter("id", deviceMasterId)
                .getResultList();
    }

    /**
     * 最後に保存されたレコードを取得する。
     *
     * @return
     */
//    public MeasSolarPower getLatestData() {
////      TypedQuery<MeasSystemPower> tq = getEntityManager().createNamedQuery("MeasSystemPower.getLatestData", MeasSystemPower.class).setMaxResults(1);
//        TypedQuery<MeasSolarPower> tq = getEntityManager().createNamedQuery("MeasSolarPower.getLatestData", MeasSolarPower.class);
//        if(tq != null) {
//            int res = tq.getFirstResult();
//            if(res > 0) {
//                tq.setMaxResults(1);
//            }
//        }
//        int res = tq.getFirstResult();
//        if(tq != null && res > 0 ) {
//            return tq.getSingleResult();
//        }
//        return null;
//    }
    
    public MeasSolarPower getLatestData() {
        TypedQuery<MeasSolarPower> tq = getEntityManager().createNamedQuery("MeasSolarPower.getLatestData", MeasSolarPower.class);
        if(tq != null) {
    		TypedQuery<MeasSolarPower> res = tq.setMaxResults(1);      
    		List<MeasSolarPower> ans = res.getResultList();
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
    public void insertQuery(MeasSolarPower entity) {
        getEntityManager().persist(entity);
    }
}
