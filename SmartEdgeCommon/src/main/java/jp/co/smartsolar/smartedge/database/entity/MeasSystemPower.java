package jp.co.smartsolar.smartedge.database.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the meas_system_power database table.
 *
 */
@Entity
@Table(name = "meas_system_power")
@NamedQuery(name = "MeasSystemPower.findAll", query = "SELECT m FROM MeasSystemPower m")
@NamedQuery(name = "MeasSystemPower.deleteOldData", query = "DELETE FROM MeasSystemPower m WHERE m.cycleDatetime <=  :oldDate")
@NamedQuery(name = "MeasSystemPower.getLatestData", query = "SELECT m FROM MeasSystemPower m ORDER BY m.cycleDatetime DESC ")
@NamedQuery(name = "MeasSystemPower.findByMasterId", query = "SELECT m FROM MeasSystemPower m WHERE m.smartmeterMaster.id = :id")
public class MeasSystemPower implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    @Column(name = "cycle_datetime")
    private Timestamp         cycleDatetime;

    @Column(name = "electric_vol_normal")
    private double            electricVolNormal;

    @Column(name = "electric_vol_reverse")
    private double            electricVolReverse;

    //bi-directional many-to-one association to SmartmeterMaster
    @ManyToOne
    @JoinColumn(name = "smartmeter_master_id")
    private SmartmeterMaster  smartmeterMaster;

    public MeasSystemPower() {
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

    public double getElectricVolNormal() {
        return this.electricVolNormal;
    }

    public void setElectricVolNormal(double electricVolNormal) {
        this.electricVolNormal = electricVolNormal;
    }

    public double getElectricVolReverse() {
        return this.electricVolReverse;
    }

    public void setElectricVolReverse(double electricVolReverse) {
        this.electricVolReverse = electricVolReverse;
    }

    public SmartmeterMaster getSmartmeterMaster() {
        return this.smartmeterMaster;
    }

    public void setSmartmeterMaster(SmartmeterMaster smartmeterMaster) {
        this.smartmeterMaster = smartmeterMaster;
    }

}