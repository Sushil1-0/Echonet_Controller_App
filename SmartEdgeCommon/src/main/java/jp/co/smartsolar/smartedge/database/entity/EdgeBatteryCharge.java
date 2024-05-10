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
 * The persistent class for the edge_battery_charge database table.
 *
 */
@Entity
@Table(name = "edge_battery_charge")
@NamedQuery(name = "EdgeBatteryCharge.findAll", query = "SELECT e FROM EdgeBatteryCharge e")
@NamedQuery(name = "EdgeBatteryCharge.findByDate", query = "SELECT e FROM EdgeBatteryCharge e WHERE e.cycleDatetime >= :Start AND e.cycleDatetime <= :End ORDER BY e.cycleDatetime")
public class EdgeBatteryCharge implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="cycle_datetime")
    private Timestamp cycleDatetime;

    @Column(name="electric_vol1")
    private Long electricVol1;
    
    //added 20210218
    @Column(name = "charging_vol")
    private Long chargingVol;
    
    @Column(name = "discharging_vol")
    private Long dischargingVol;

    @Column(name="electric_vol3")
    private double electricVol3;

    //bi-directional many-to-one association to DeviceMaster
    @ManyToOne
    @JoinColumn(name="device_master_id")
    private DeviceMaster deviceMaster;

    public EdgeBatteryCharge() {
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

    public Long getElectricVol1() {
        return this.electricVol1;
    }

    public void setElectricVol1(Long electricVol1) {
        this.electricVol1 = electricVol1;
    }
    
    public Long getChargingVol() {
		return chargingVol;
	}

	public void setChargingVol(Long chargingVol) {
		this.chargingVol = chargingVol;
	}

	public Long getDischargingVol() {
		return dischargingVol;
	}

	public void setDischargingVol(Long dischargingVol) {
		this.dischargingVol = dischargingVol;
	}
	
    public double getElectricVol3() {
        return this.electricVol3;
    }

    public void setElectricVol3(double electricVol3) {
        this.electricVol3 = electricVol3;
    }

    public DeviceMaster getDeviceMaster() {
        return this.deviceMaster;
    }

    public void setDeviceMaster(DeviceMaster deviceMaster) {
        this.deviceMaster = deviceMaster;
    }

}