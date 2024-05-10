package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.EntityManager;

import jp.co.smartsolar.smartedge.database.entity.EdgeBatteryCharge;

/**
 * 蓄電池残量テーブル操作クラス.
 *
 * @author ubiq
 *
 */
public class EdgeBatteryChargeTableController extends BaseTableController<EdgeBatteryCharge> {

    public EdgeBatteryChargeTableController() {
        super();
    }

    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public List<EdgeBatteryCharge> findByDate(Timestamp start, Timestamp end) throws SQLException {
        return getEntityManager().createNamedQuery("EdgeBatteryCharge.findByDate", EdgeBatteryCharge.class).setParameter("Start", start)
                .setParameter("End", end).getResultList();
    }
    
    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public List<EdgeBatteryCharge> findByDate(Timestamp start, Timestamp end, EntityManager em) throws SQLException {
        return em.createNamedQuery("EdgeBatteryCharge.findByDate", EdgeBatteryCharge.class).setParameter("Start", start)
                .setParameter("End", end).getResultList();
    }

    /**
     * 全てリストで取得する (Order By CycleDatetime).
     *
     * @return
     * @throws SQLException
     */
    public List<EdgeBatteryCharge> selectAllOrderByCycleDatetime() throws SQLException {
        List<EdgeBatteryCharge> list = null;
        return list;
    }

    /**
     * 同日時のデータが存在するか確認.
     *
     * @param entity :
     * @return
     */
    public boolean existDeplicateDatetime(EdgeBatteryCharge entity) throws SQLException {
        //        List<EdgeBatteryCharge> list = this.selectAll(where);
        //        String where = " WHERE " + COL_KEY_DEVICE_MASTER_ID + "=" + entity.getDeviceMasterId() + " AND " + COL_KEY_CYCLE_DATETIME + "=" + "'"
        //                + SEUtil.timestampToString(entity.getCycleDatetime()) + "' ";
        //        if (list == null || list.size() == 0) {
        //            return false;
        //        } else {
        //            return true;
        //        }
        return false;

    }
}
