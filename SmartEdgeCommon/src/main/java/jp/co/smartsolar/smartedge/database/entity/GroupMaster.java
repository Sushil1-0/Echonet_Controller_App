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
 * The persistent class for the group_master database table.
 *
 */
@Entity
@Table(name = "group_master")
@NamedQuery(name = "GroupMaster.findAll", query = "SELECT g FROM GroupMaster g")
@NamedQuery(name = "GroupMaster.findById", query = "SELECT g FROM GroupMaster g WHERE g.id = :id")
public class GroupMaster implements Serializable {
    private static final long   serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer             id;

    @Column(name = "display_name")
    private String              displayName;

    @Column(name = "groupe_name")
    private String              groupeName;

    //bi-directional many-to-one association to AppMaster
    @OneToMany(mappedBy = "groupMaster")
    private List<AppMaster>     appMasters;

    //bi-directional many-to-one association to ConfigInfo
    @OneToMany(mappedBy = "groupMaster")
    private List<ConfigInfo>    configInfos;

    //bi-directional many-to-one association to SqlUpdateInfo
    @OneToMany(mappedBy = "groupMaster")
    private List<SqlUpdateInfo> sqlUpdateInfos;

    public GroupMaster() {
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

    public String getGroupeName() {
        return this.groupeName;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

    public List<AppMaster> getAppMasters() {
        return this.appMasters;
    }

    public void setAppMasters(List<AppMaster> appMasters) {
        this.appMasters = appMasters;
    }

    public AppMaster addAppMaster(AppMaster appMaster) {
        getAppMasters().add(appMaster);
        appMaster.setGroupMaster(this);

        return appMaster;
    }

    public AppMaster removeAppMaster(AppMaster appMaster) {
        getAppMasters().remove(appMaster);
        appMaster.setGroupMaster(null);

        return appMaster;
    }

    public List<ConfigInfo> getConfigInfos() {
        return this.configInfos;
    }

    public void setConfigInfos(List<ConfigInfo> configInfos) {
        this.configInfos = configInfos;
    }

    public ConfigInfo addConfigInfo(ConfigInfo configInfo) {
        getConfigInfos().add(configInfo);
        configInfo.setGroupMaster(this);

        return configInfo;
    }

    public ConfigInfo removeConfigInfo(ConfigInfo configInfo) {
        getConfigInfos().remove(configInfo);
        configInfo.setGroupMaster(null);

        return configInfo;
    }

    public List<SqlUpdateInfo> getSqlUpdateInfos() {
        return this.sqlUpdateInfos;
    }

    public void setSqlUpdateInfos(List<SqlUpdateInfo> sqlUpdateInfos) {
        this.sqlUpdateInfos = sqlUpdateInfos;
    }

    public SqlUpdateInfo addSqlUpdateInfo(SqlUpdateInfo sqlUpdateInfo) {
        getSqlUpdateInfos().add(sqlUpdateInfo);
        sqlUpdateInfo.setGroupMaster(this);

        return sqlUpdateInfo;
    }

    public SqlUpdateInfo removeSqlUpdateInfo(SqlUpdateInfo sqlUpdateInfo) {
        getSqlUpdateInfos().remove(sqlUpdateInfo);
        sqlUpdateInfo.setGroupMaster(null);

        return sqlUpdateInfo;
    }
    
    public GroupMaster(Integer id) {
        this.id = id;
    }

}