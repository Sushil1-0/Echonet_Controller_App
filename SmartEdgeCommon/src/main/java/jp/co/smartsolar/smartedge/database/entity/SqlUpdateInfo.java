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
 * The persistent class for the sql_update_info database table.
 *
 */
@Entity
@Table(name = "sql_update_info")
@NamedQuery(name = "SqlUpdateInfo.findAll", query = "SELECT s FROM SqlUpdateInfo s")
public class SqlUpdateInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    @Column(name = "sql_file_deleted")
    private Boolean           sqlFileDeleted;

    @Column(name = "sql_file_path")
    private String            sqlFilePath;

    @Column(name = "sql_update_time")
    private Timestamp         sqlUpdateTime;

    private Boolean           updated;

    //bi-directional many-to-one association to GroupMaster
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupMaster       groupMaster;

    public static final Integer GROUP_ID_SMARTEDGE = 1; // SmartEdge用
    public static final Integer GROUP_ID_SNSR      = 2; // 測定モジュール用


    public SqlUpdateInfo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSqlFileDeleted() {
        return this.sqlFileDeleted;
    }

    public void setSqlFileDeleted(Boolean sqlFileDeleted) {
        this.sqlFileDeleted = sqlFileDeleted;
    }

    public String getSqlFilePath() {
        return this.sqlFilePath;
    }

    public void setSqlFilePath(String sqlFilePath) {
        this.sqlFilePath = sqlFilePath;
    }

    public Timestamp getSqlUpdateTime() {
        return this.sqlUpdateTime;
    }

    public void setSqlUpdateTime(Timestamp sqlUpdateTime) {
        this.sqlUpdateTime = sqlUpdateTime;
    }

    public Boolean getUpdated() {
        return this.updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public GroupMaster getGroupMaster() {
        return this.groupMaster;
    }

    public void setGroupMaster(GroupMaster groupMaster) {
        this.groupMaster = groupMaster;
    }

}