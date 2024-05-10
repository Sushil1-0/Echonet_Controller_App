package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;

import jp.co.smartsolar.smartedge.database.entity.EdgeSystemPower;

/**
 * 系統電力量テーブル操作クラス (Edgeモジュール).
 *
 * @author ubiq
 *
 */
public class EdgeSystemPowerTableController extends BaseTableController<EdgeSystemPower> {

    public EdgeSystemPowerTableController() {
        super();
    }

    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public List<EdgeSystemPower> findByDate(Timestamp start, Timestamp end) throws SQLException {
        return getEntityManager().createNamedQuery("EdgeSystemPower.findByDate", EdgeSystemPower.class).setParameter("Start", start)
                .setParameter("End", end).getResultList();
    }
    
    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public List<EdgeSystemPower> findByDate(Timestamp start, Timestamp end, EntityManager em) throws SQLException {
        return em.createNamedQuery("EdgeSystemPower.findByDate", EdgeSystemPower.class).setParameter("Start", start)
                .setParameter("End", end).getResultList();
    }

    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public ArrayList<EdgeSystemPower> selectAllOrderByDateAscList(String where) throws SQLException {
        ArrayList<EdgeSystemPower> ret = new ArrayList<>();
        //        String sql = "SELECT id," + getInsertClumnsQuery() + " FROM " + getTableName() + " " + where + " ORDER BY " + COL_KEY_CYCLE_DATETIME + " ASC";
        //        ResultSet rset = stmt.executeQuery(sql);
        //        while (rset.next()) {
        //            ResultSetMetaData meta = rset.getMetaData();
        //            if (meta != null) {
        //                EdgeSystemPower it = new EdgeSystemPower();
        //                it.setId(Integer.parseInt(rset.getString(1)));
        //                Timestamp ts = Timestamp.valueOf(rset.getString(2));
        //                it.setCycleDatetime(ts);
        //                boolean direction = rset.getBoolean(3);
        //                it.setDirection(direction);
        //                double electricVol = Double.parseDouble(rset.getString(4));
        //                it.setElectricVol(electricVol);
        //                ret.add(it);
        //            }
        //        }
        return ret;
    }

    /**
     * 同日時のデータが存在するか確認.
     *
     * @param entity
     * @return
     * @throws SQLException
     */
    public boolean existDeplicateDatetime(EdgeSystemPower entity) throws SQLException {
        //        List<EdgeSystemPower> list = this.selectAll(where);
        //        String where = " WHERE " + COL_KEY_SMARTMETER_MASTER_ID + "=" + entity.getSmartmeterMasterId() + " AND " + COL_KEY_DIRECTION + "=" + entity.isDirection()
        //                + " AND " + COL_KEY_CYCLE_DATETIME + "=" + "'" + SEUtil.timestampToString(entity.getCycleDatetime()) + "' ";
        //        if (list == null || list.size() == 0) {
        //            return false;
        //        } else {
        //            return true;
        //        }
        return false;
    }

}
