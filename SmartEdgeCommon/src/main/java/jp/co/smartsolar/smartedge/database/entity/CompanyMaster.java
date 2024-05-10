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
 * The persistent class for the company_master database table.
 *
 */
@Entity
@Table(name = "company_master")
@NamedQuery(name = "CompanyMaster.findAll", query = "SELECT c FROM CompanyMaster c")
@NamedQuery(name = "CompanyMaster.findById", query = "SELECT c FROM CompanyMaster c WHERE c.id = :id")
public class CompanyMaster implements Serializable {
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer            id;

    @Column(name = "company_name")
    private String             companyName;

    //bi-directional many-to-one association to DeviceMaster
    @OneToMany(mappedBy = "companyMaster")
    private List<DeviceMaster> deviceMasters;

    public CompanyMaster() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<DeviceMaster> getDeviceMasters() {
        return this.deviceMasters;
    }

    public void setDeviceMasters(List<DeviceMaster> deviceMasters) {
        this.deviceMasters = deviceMasters;
    }

    public DeviceMaster addDeviceMaster(DeviceMaster deviceMaster) {
        getDeviceMasters().add(deviceMaster);
        deviceMaster.setCompanyMaster(this);

        return deviceMaster;
    }

    public DeviceMaster removeDeviceMaster(DeviceMaster deviceMaster) {
        getDeviceMasters().remove(deviceMaster);
        deviceMaster.setCompanyMaster(null);

        return deviceMaster;
    }

}