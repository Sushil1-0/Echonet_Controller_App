package jp.co.smartsolar.smartedge.database.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EdgeBattyeryCumulativeCharge database table.
 *
 */
@Entity
@Table(name="edge_battyery_cumulative_charge")
@NamedQuery(name="EdgeBattyeryCumulativeCharge.findAll", query="SELECT e FROM EdgeBattyeryCumulativeCharge e")
public class EdgeBattyeryCumulativeCharge implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="cycle_datetime")
    private Timestamp cycleDatetime;

    @Column(name="charge_amount")
    private Long chargeAmount;

    @Column(name="discharge_amount")
    private double dischargeAmount;

    //bi-directional many-to-one association to DeviceMaster
    @ManyToOne
    @JoinColumn(name="device_master_id")
    private DeviceMaster deviceMaster;

    public EdgeBattyeryCumulativeCharge() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCycleDatetime() {
        return this.cycleDatetime;
    }

    public void setCycleDatetime(Timestamp cycleDatetime) {
        this.cycleDatetime = cycleDatetime;
    }

    public Long getChargeAmount() {
        return this.chargeAmount;
    }

    public void setChargeAmount(Long chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public double getDischargeAmount() {
        return this.dischargeAmount;
    }

    public void setDischargeAmount(double dischargeAmount) {
        this.dischargeAmount = dischargeAmount;
    }

    public DeviceMaster getDeviceMaster() {
        return this.deviceMaster;
    }

    public void setDeviceMaster(DeviceMaster deviceMaster) {
        this.deviceMaster = deviceMaster;
    }

}