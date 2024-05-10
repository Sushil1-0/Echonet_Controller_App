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
 * The persistent class for the app_master database table.
 *
 */
@Entity
@Table(name = "app_master")
@NamedQuery(name = "AppMaster.findAll", query = "SELECT a FROM AppMaster a")
@NamedQuery(name = "AppMaster.findByKeyName", query = "SELECT a FROM AppMaster a WHERE :key = :val")
@NamedQuery(name = "AppMaster.findByAppName", query = "SELECT a FROM AppMaster a WHERE a.appName = :key")
@NamedQuery(name = "AppMaster.findByModuleType", query = "SELECT a FROM AppMaster a WHERE a.groupMaster = :key")
public class AppMaster implements Serializable {
    private static final long  serialVersionUID          = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer            id;

    @Column(name = "app_name")
    private String             appName;

    @Column(name = "app_update_time")
    private Timestamp          appUpdateTime;

    private Boolean            applied;

    @Column(name = "file_path")
    private String             filePath;

    @Column(name = "job_id")
    private String             jobId;

    @Column(name = "old_version")
    private float              oldVersion;

    @Column(name = "start_date")
    private Timestamp          startDate;

    @Column(name = "update_file_path")
    private String             updateFilePath;

    private Boolean            updated;

    private float              version;

    //bi-directional many-to-one association to GroupMaster
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupMaster        groupMaster;

    public static final String APP_NAME_LED_CTL_APP      = "LedCtlApp";     // LEDアプリ
    public static final String APP_NAME_CLD_COM_APP      = "CldComApp";     // クラウド制御アプリ
    public static final String APP_NAME_WEB_APP          = "WebApp";        // WEBアプリ
    public static final String APP_NAME_EL_APP           = "ELApp";         // ELアプリ
    public static final String APP_NAME_SE_OPE_APP       = "SEOpeApp";      // SmartEdge運用管理アプ リ
    public static final String APP_NAME_SNSR_CTL_APP     = "SnsrCtlApp";    // [測定モジュール] 外部センサ制御アプリ
    public static final String APP_NAME_SNSR_CTL_OPE_APP = "SnsrCtlOpeApp"; // [測定モジュール] 外部センサ制御運用管理アプリ
    public static final String APP_NAME_SNSR_CTL_WEB_APP = "WebApp"; // [測定モジュール] Webアプリ
    public static final String APP_NAME_MEAS_LED_CTL_APP = "LedCtlApp"; // [測定モジュール] LED_CTL_APP

    public AppMaster() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Timestamp getAppUpdateTime() {
        return this.appUpdateTime;
    }

    public void setAppUpdateTime(Timestamp appUpdateTime) {
        this.appUpdateTime = appUpdateTime;
    }

    public Boolean getApplied() {
        return this.applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public float getOldVersion() {
        return this.oldVersion;
    }

    public void setOldVersion(float oldVersion) {
        this.oldVersion = oldVersion;
    }

    public Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getUpdateFilePath() {
        return this.updateFilePath;
    }

    public void setUpdateFilePath(String updateFilePath) {
        this.updateFilePath = updateFilePath;
    }

    public Boolean getUpdated() {
        return this.updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public float getVersion() {
        return this.version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public GroupMaster getGroupMaster() {
        return this.groupMaster;
    }

    public void setGroupMaster(GroupMaster groupMaster) {
        this.groupMaster = groupMaster;
    }

}