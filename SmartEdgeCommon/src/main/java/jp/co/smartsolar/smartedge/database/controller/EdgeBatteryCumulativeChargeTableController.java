package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import jp.co.smartsolar.smartedge.database.entity.EdgeBatteryCharge;
import jp.co.smartsolar.smartedge.database.entity.EdgeBattyeryCumulativeCharge;

/**
 * 蓄電池積算テーブル操作クラス.
 *
 * @author ubiq
 *
 */
public class EdgeBatteryCumulativeChargeTableController extends BaseTableController<EdgeBattyeryCumulativeCharge> {

    public EdgeBatteryCumulativeChargeTableController() {
        super();
    }

    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public List<EdgeBattyeryCumulativeCharge> findByDate(Timestamp start, Timestamp end) throws SQLException {
        return getEntityManager().createNamedQuery("EdgeBatteryCharge.findByDate", EdgeBattyeryCumulativeCharge.class).setParameter("Start", start)
                .setParameter("End", end).getResultList();
    }

    /**
     * 全てリストで取得する (Order By CycleDatetime).
     *
     * @return
     * @throws SQLException
     */
    public List<EdgeBattyeryCumulativeCharge> selectAllOrderByCycleDatetime() throws SQLException {
        List<EdgeBattyeryCumulativeCharge> list = null;
        return list;
    }

}
