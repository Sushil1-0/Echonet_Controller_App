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
 * The persistent class for the edge_solar_power database table.
 *
 */
@Entity
@Table(name = "edge_solar_power")
@NamedQuery(name = "EdgeSolarPower.findAll", query = "SELECT e FROM EdgeSolarPower e")
@NamedQuery(name = "EdgeSolarPower.findByDate", query = "SELECT e FROM EdgeSolarPower e WHERE e.cycleDatetime >= :Start AND e.cycleDatetime <= :End")
public class EdgeSolarPower implements Serializable {
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

    public EdgeSolarPower() {
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