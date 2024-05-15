package jp.co.smartsolar.smartedge.database.entity;

import java.io.Serial;
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
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the power_catalog database table.
 *
 */
@Entity
@Table(name = "power_catalog")
@NamedQuery(name = "PowerCatalog.findAll", query = "SELECT p FROM PowerCatalog p")
public class PowerCatalog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Timestamp datetime;

    @Column(name="target_vol")
    private Long targetVol;

    //bi-directional many-to-one association to ControlMaster
    @ManyToOne
    @JoinColumn(name="control_id")
    private ControlMaster controlMaster;

    public PowerCatalog() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Long getTargetVol() {
        return this.targetVol;
    }

    public void setTargetVol(Long targetVol) {
        this.targetVol = targetVol;
    }

    public ControlMaster getControlMaster() {
        return this.controlMaster;
    }

    public void setControlMaster(ControlMaster controlMaster) {
        this.controlMaster = controlMaster;
    }

}