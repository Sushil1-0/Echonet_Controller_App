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
 * The persistent class for the meas_solar_power database table.
 *
 */
@Entity
@Table(name = "meas_solar_power")
@NamedQuery(name = "MeasSolarPower.findAll", query = "SELECT m FROM MeasSolarPower m")
@NamedQuery(name = "MeasSolarPower.deleteOldData", query = "DELETE FROM MeasSolarPower m WHERE m.cycleDatetime <=  :oldDate")
@NamedQuery(name = "MeasSolarPower.getLatestData", query = "SELECT m FROM MeasSolarPower m ORDER BY m.cycleDatetime DESC ")
@NamedQuery(name = "MeasSolarPower.findByDeviceMasterId", query = "SELECT m FROM MeasSolarPower m WHERE m.deviceMaster.id = :id")
public class MeasSolarPower implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    @Column(name = "cycle_datetime")
    private Timestamp         cycleDatetime;

    @Column(name = "electric_vol")
    private double            electricVol;

    //bi-directional many-to-one association to DeviceMaster
    @ManyToOne
    @JoinColumn(name = "device_master_id")
    private DeviceMaster      deviceMaster;

    public MeasSolarPower() {
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

    public double getElectricVol() {
        return this.electricVol;
    }

    public void setElectricVol(double electricVol) {
        this.electricVol = electricVol;
    }

    public DeviceMaster getDeviceMaster() {
        return this.deviceMaster;
    }

    public void setDeviceMaster(DeviceMaster deviceMaster) {
        this.deviceMaster = deviceMaster;
    }

}