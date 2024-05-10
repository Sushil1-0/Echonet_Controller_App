package jp.co.smartsolar.smartedge.database.controller;

import java.sql.SQLException;

import jp.co.smartsolar.smartedge.database.entity.PcstypeMaster;

public class PcstypeMasterTableController extends BaseTableController<PcstypeMaster> {

    public static enum ID {
        STORAE_BATTERY(1), // 蓄電池
        SOLAR_POWER(2), // 太陽光
        HIGH_SMARTMETER(3), // 高圧スマメ
        LOW_SMARTMETER(4) // 低圧スマメ
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

    public PcstypeMasterTableController() {
        super();
    }

    public PcstypeMaster findById(int id) throws SQLException {
        return getEntityManager().createNamedQuery("PcstypeMaster.findById", PcstypeMaster.class).setParameter("id", id).getSingleResult();
    }
}
