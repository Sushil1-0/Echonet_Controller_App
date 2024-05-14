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
@Setter
@Getter
@Entity
@Table(name = "power_catalog")
@NamedQuery(name = "PowerCatalog.findAll", query = "SELECT p FROM PowerCatalog p")
@NamedQuery(name = "PowerCatalog.findFirstByDatetimeOrderByIdDesc", query = "SELECT p FROM PowerCatalog p WHERE p.datetime = :datetime ORDER BY p.id DESC")
public class PowerCatalog implements Serializable {
    @Serial
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

}