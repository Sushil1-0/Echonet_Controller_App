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
 * The persistent class for the pcstype_master database table.
 *
 */
@Entity
@Table(name = "pcstype_master")
@NamedQuery(name = "PcstypeMaster.findAll", query = "SELECT p FROM PcstypeMaster p")
@NamedQuery(name = "PcstypeMaster.findById", query = "SELECT p FROM PcstypeMaster p WHERE p.id = :id")
public class PcstypeMaster implements Serializable {
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer            id;

    @Column(name = "display_name")
    private String             displayName;

    @Column(name = "pcs_type")
    private String             pcsType;

    //bi-directional many-to-one association to DeviceMaster
    @OneToMany(mappedBy = "pcstypeMaster")
    private List<DeviceMaster> deviceMasters;

    public PcstypeMaster() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPcsType() {
        return this.pcsType;
    }

    public void setPcsType(String pcsType) {
        this.pcsType = pcsType;
    }

    public List<DeviceMaster> getDeviceMasters() {
        return this.deviceMasters;
    }

    public void setDeviceMasters(List<DeviceMaster> deviceMasters) {
        this.deviceMasters = deviceMasters;
    }

    public DeviceMaster addDeviceMaster(DeviceMaster deviceMaster) {
        getDeviceMasters().add(deviceMaster);
        deviceMaster.setPcstypeMaster(this);

        return deviceMaster;
    }

    public DeviceMaster removeDeviceMaster(DeviceMaster deviceMaster) {
        getDeviceMasters().remove(deviceMaster);
        deviceMaster.setPcstypeMaster(null);

        return deviceMaster;
    }

}