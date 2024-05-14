package jp.co.smartsolar.smartedge.database.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the power_control_results database table.
 *
 */
@Entity
@Table(name = "power_control_results")
@NamedQuery(name = "PowerControlResult.findAll", query = "SELECT p FROM PowerControlResult p")
public class PowerControlResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;

    private Timestamp         datetime;

    //bi-directional many-to-one association to ControlMaster
    @ManyToOne
    @JoinColumn(name = "control_id")
    private ControlMaster     controlMaster;

    public PowerControlResult() {
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

    public ControlMaster getControlMaster() {
        return null;
    }

    public void setControlMaster(ControlMaster controlMaster) {
        this.controlMaster = controlMaster;
    }

}