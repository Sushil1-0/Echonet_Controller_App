package jp.co.smartsolar.smartedge.database.entity;

import java.io.Serializable;

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
 * The persistent class for the module_list database table.
 *
 */
@Entity
@Table(name = "module_list")
@NamedQuery(name = "ModuleList.findAll", query = "SELECT m FROM ModuleList m")
public class ModuleList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    @Column(name = "device_type")
    private String            deviceType;

    @Column(name = "mod_id")
    private String            modId;

    //bi-directional many-to-one association to DeviceMaster
    @ManyToOne
    @JoinColumn(name = "device_master_id")
    private DeviceMaster      deviceMaster;

    public ModuleList() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModId() {
        return this.modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public DeviceMaster getDeviceMaster() {
        return this.deviceMaster;
    }

    public void setDeviceMaster(DeviceMaster deviceMaster) {
        this.deviceMaster = deviceMaster;
    }

}