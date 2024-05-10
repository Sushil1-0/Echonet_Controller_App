package jp.co.smartsolar.smartedge.database.controller;

import java.util.Date;
import java.util.List;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jp.co.smartsolar.smartedge.database.entity.MeasSystemPower;
import jp.co.smartsolar.smartedge.database.entity.SmartmeterMaster;

/**
 * 系統電力量テーブル操作クラス (Edgeモジュール).
 *
 * @author ubiq
 *
 */
public class MeasSystemPowerController extends BaseTableController<MeasSystemPower> {
	 protected final static Logger log = LoggerFactory.getLogger(MeasSystemPowerController.class);
    public MeasSystemPowerController() {
        super();
    }

    public void deleteOldData(Date date) {
        getEntityManager().createNamedQuery("MeasSystemPower.deleteOldData").setParameter("oldDate", date).executeUpdate();
    }

    public List<MeasSystemPower> findByMasterId(SmartmeterMaster masterId) {
        return getEntityManager().createNamedQuery("MeasSolarPower.findByMasterId", MeasSystemPower.class).setParameter("id", masterId)
                .getResultList();
    }


    /**
     * 最後に保存されたレコードを取得する。
     *
     * @return
     */
//    public MeasSystemPower getLatestData() {
//    	 log.info("MeasSystemPower getLatestData" );
////      TypedQuery<MeasSystemPower> tq = getEntityManager().createNamedQuery("MeasSystemPower.getLatestData", MeasSystemPower.class).setMaxResults(1);
//        TypedQuery<MeasSystemPower> tq = getEntityManager().createNamedQuery("MeasSystemPower.getLatestData", MeasSystemPower.class);
//        log.info("MeasSystemPower getLatestData() : " + tq.getFirstResult());
//        log.info("MeasSystemPower getLatestData() 1 : " + (tq == null));
//        if(tq != null) {
//            int res = tq.getFirstResult();
//            log.info("MeasSystemPower res :" + res);
//            if(res > 0) {
//                tq.setMaxResults(1);
//                log.info("MeasSystemPower setMax  " );
//            }
//        }
//        log.info("check getLatestData :tq :::: " + tq);
//        int res = tq.getFirstResult();
//        log.info("res value : " + res);
//        log.info("MeasSystemPower getLatestData() 2: " + (tq == null));
//        
//        if(tq != null && res > 0 ) {
//        	log.info("tq.getSingleResult():: " + tq.getSingleResult());
//            return tq.getSingleResult();
//        }
//        return null;
//    }
   
    public MeasSystemPower getLatestData() {
    	TypedQuery<MeasSystemPower> tq = getEntityManager().createNamedQuery("MeasSystemPower.getLatestData", MeasSystemPower.class);
    	if(tq != null) {
    		TypedQuery<MeasSystemPower> res = tq.setMaxResults(1);      
    		List<MeasSystemPower> ans = res.getResultList();
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
    public void insertQuery(MeasSystemPower entity) {
        getEntityManager().persist(entity);
    }
}
