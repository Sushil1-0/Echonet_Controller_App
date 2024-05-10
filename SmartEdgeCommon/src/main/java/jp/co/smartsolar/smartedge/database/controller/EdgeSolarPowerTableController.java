package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;

import jp.co.smartsolar.smartedge.database.entity.EdgeSolarPower;

/**
 * 太陽光発電電力量テーブル操作クラス (Edgeモジュール).
 *
 * @author ubiq
 *
 */
public class EdgeSolarPowerTableController extends BaseTableController<EdgeSolarPower> {

    public EdgeSolarPowerTableController() {
        super();
    }

    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public List<EdgeSolarPower> findByDate(Timestamp start, Timestamp end) throws SQLException {
        return getEntityManager().createNamedQuery("EdgeSolarPower.findByDate", EdgeSolarPower.class).setParameter("Start", start)
                .setParameter("End", end).getResultList();
    }
    
    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public List<EdgeSolarPower> findByDate(Timestamp start, Timestamp end, EntityManager em) throws SQLException {
        return em.createNamedQuery("EdgeSolarPower.findByDate", EdgeSolarPower.class).setParameter("Start", start)
                .setParameter("End", end).getResultList();
    }

    /**
     * 電力データ送信用リスト取得.
     *
     * @param where 条件式
     */
    public ArrayList<EdgeSolarPower> selectAllOrderByDateAscList(String where) throws SQLException {
        ArrayList<EdgeSolarPower> ret = new ArrayList<>();
        //        String sql = "SELECT id," + getInsertClumnsQuery() + " FROM " + getTableName() + " " + where + " ORDER BY " + COL_KEY_CYCLE_DATETIME + " ASC";
        //        ResultSet rset = stmt.executeQuery(sql);
        //        while (rset.next()) {
        //            ResultSetMetaData meta = rset.getMetaData();
        //            if (meta != null) {
        //                EdgeSolarPower it = new EdgeSolarPower();
        //                it.setId(Integer.parseInt(rset.getString(1)));
        //                Timestamp ts = Timestamp.valueOf(rset.getString(2));
        //                it.setCycleDatetime(ts);
        //                double vol = Double.parseDouble(rset.getString(3));
        //                it.setElectricVol(vol);
        //                it.setDeviceMasterId(Integer.parseInt(rset.getString(4)));
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
    public boolean existDeplicateDatetime(EdgeSolarPower entity) throws SQLException {
        //        String where = " WHERE " + COL_KEY_DEVICE_MASTER_ID + "=" + entity.getDeviceMasterId() + " AND " + COL_KEY_CYCLE_DATETIME + "=" + "'"
        //                + SEUtil.timestampToString(entity.getCycleDatetime()) + "' ";
        //        List<EdgeSolarPower> list = this.selectAll(where);
        //        if (list == null || list.size() == 0) {
        //            return false;
        //        } else {
        //            return true;
        //        }
        return false;
    }
}
