package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;
import java.util.List;

import jp.co.smartsolar.smartedge.database.entity.ControlMaster;

public class ControlMasterTableController extends BaseTableController<ControlMaster> {

    public static enum ID {
        CHARGE(1), // 充電
        DISCHARGE(2), // 放電
        STAY(3) // 待機
        ;

        // フィールドの定義
        private Integer id;

        // コンストラクタの定義
        private ID(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return this.id;
        }

        public String getStrId() {
            return this.id.toString();
        }
    };

    public ControlMasterTableController() {
        super();
    }

    public List<ControlMaster> findAll() throws SQLException {
        return getEntityManager().createNamedQuery("ControlMaster.findAll", ControlMaster.class).getResultList();
    }

    public List<ControlMaster> findByEchonetCmd(String echonetCmd)
    {
        return getEntityManager().createNamedQuery("ControlMaster.findByEchonetCmd",ControlMaster.class)
                .setParameter("echonetCmd",echonetCmd)
                .getResultList();
    }
}
