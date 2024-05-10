package jp.co.smartsolar.smartedge.database.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The persistent class for the control_master database table.
 *
 */
@Entity
@Table(name = "control_master")
@NamedQuery(name = "ControlMaster.findAll", query = "SELECT c FROM ControlMaster c")
public class ControlMaster implements Serializable {
    private static final long        serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                  id;

    private String                   cm;

    @Column(name = "display_title")
    private String                   displayTitle;

    @Column(name = "echonet_cmd")
    private String                   echonetCmd;

    //bi-directional many-to-one association to PowerCatalog
    @OneToMany(mappedBy = "controlMaster")
    private List<PowerCatalog>       powerCatalogs;

    //bi-directional many-to-one association to PowerControlResult
    @OneToMany(mappedBy = "controlMaster")
    private List<PowerControlResult> powerControlResults;

    public ControlMaster() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCm() {
        return this.cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getDisplayTitle() {
        return this.displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getEchonetCmd() {
        return this.echonetCmd;
    }

    public void setEchonetCmd(String echonetCmd) {
        this.echonetCmd = echonetCmd;
    }

    public List<PowerCatalog> getPowerCatalogs() {
        return this.powerCatalogs;
    }

    public void setPowerCatalogs(List<PowerCatalog> powerCatalogs) {
        this.powerCatalogs = powerCatalogs;
    }

    public PowerCatalog addPowerCatalog(PowerCatalog powerCatalog) {
        getPowerCatalogs().add(powerCatalog);
        powerCatalog.setControlMaster(this);

        return powerCatalog;
    }

    public PowerCatalog removePowerCatalog(PowerCatalog powerCatalog) {
        getPowerCatalogs().remove(powerCatalog);
        powerCatalog.setControlMaster(null);

        return powerCatalog;
    }

    public List<PowerControlResult> getPowerControlResults() {
        return this.powerControlResults;
    }

    public void setPowerControlResults(List<PowerControlResult> powerControlResults) {
        this.powerControlResults = powerControlResults;
    }

    public PowerControlResult addPowerControlResult(PowerControlResult powerControlResult) {
        getPowerControlResults().add(powerControlResult);
        powerControlResult.setControlMaster(this);

        return powerControlResult;
    }

    public PowerControlResult removePowerControlResult(PowerControlResult powerControlResult) {
        getPowerControlResults().remove(powerControlResult);
        powerControlResult.setControlMaster(null);

        return powerControlResult;
    }

}