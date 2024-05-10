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
 * The persistent class for the config_info database table.
 *
 */
@Entity
@Table(name = "config_info")
@NamedQuery(name = "ConfigInfo.findAll", query = "SELECT c FROM ConfigInfo c")
@NamedQuery(name = "ConfigInfo.findById", query = "SELECT c FROM ConfigInfo c WHERE c.id = :id")
@NamedQuery(name = "ConfigInfo.findByKeyName", query = "SELECT c FROM ConfigInfo c WHERE c.keyName = :key")
@NamedQuery(name = "ConfigInfo.findByGroupIdAndKeyName", query = "SELECT c FROM ConfigInfo c WHERE c.groupMaster = :groupMaster AND c.keyName = :keyName")
public class ConfigInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    @Column(name = "display_name")
    private String            displayName;

    @Column(name = "key_name")
    private String            keyName;

    @Column(name = "key_value")
    private String            keyValue;

    //bi-directional many-to-one association to GroupMaster
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupMaster       groupMaster;

    public ConfigInfo() {
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

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyValue() {
        return this.keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public GroupMaster getGroupMaster() {
        return this.groupMaster;
    }

    public void setGroupMaster(GroupMaster groupMaster) {
        this.groupMaster = groupMaster;
    }

}