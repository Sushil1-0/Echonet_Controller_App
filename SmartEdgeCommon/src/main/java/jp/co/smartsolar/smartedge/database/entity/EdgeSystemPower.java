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
 * The persistent class for the edge_system_power database table.
 *
 */
@Entity
@Table(name = "edge_system_power")
@NamedQuery(name = "EdgeSystemPower.findAll", query = "SELECT e FROM EdgeSystemPower e")
@NamedQuery(name = "EdgeSystemPower.findId", query = "SELECT e FROM EdgeSystemPower e WHERE e.id = :id")
@NamedQuery(name = "EdgeSystemPower.findByDate", query = "SELECT e FROM EdgeSystemPower e WHERE e.cycleDatetime >= :Start AND e.cycleDatetime <= :End")
public class EdgeSystemPower implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    @Column(name = "cycle_datetime")
    private Timestamp         cycleDatetime;

    private Boolean           direction;

    @Column(name = "electric_vol")
    private double            electricVol;

    //bi-directional many-to-one association to SmartmeterMaster
    @ManyToOne
    @JoinColumn(name = "smartmeter_master_id")
    private SmartmeterMaster  smartmeterMaster;

    public EdgeSystemPower() {
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

    public Boolean getDirection() {
        return this.direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    public double getElectricVol() {
        return this.electricVol;
    }

    public void setElectricVol(double electricVol) {
        this.electricVol = electricVol;
    }

    public SmartmeterMaster getSmartmeterMaster() {
        return this.smartmeterMaster;
    }

    public void setSmartmeterMaster(SmartmeterMaster smartmeterMaster) {
        this.smartmeterMaster = smartmeterMaster;
    }

}