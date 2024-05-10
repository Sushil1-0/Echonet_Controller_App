package jp.co.smartsolar.smartedge.database.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The persistent class for the device_master database table.
 *
 */
@Entity
@Table(name = "device_master")
@NamedQuery(name = "DeviceMaster.findAll", query = "SELECT d FROM DeviceMaster d")
@NamedQuery(name = "DeviceMaster.findById", query = "SELECT d FROM DeviceMaster d WHERE d.id = :id")
@NamedQuery(name = "DeviceMaster.findByPcsId", query = "SELECT d FROM DeviceMaster d WHERE d.pcsId = :pcsId")
@NamedQuery(name = "DeviceMaster.findByCompanyIdAndPcstypeId", query = "SELECT d FROM DeviceMaster d WHERE d.companyMaster.id = :companyId AND d.pcstypeMaster.id = :pcstypeId")
@NamedQuery(name = "DeviceMaster.findByPcsIdAndPcstypeIdAndModelId", query = "SELECT d FROM DeviceMaster d WHERE d.pcsId = :pcsId AND d.modelId = :modelId AND d.pcstypeMaster.id = :pcstypeId")
@NamedQuery(name = "DeviceMaster.updatePcsIdModelId", query = "UPDATE DeviceMaster d SET pcsId = :pcsId, modelId = :modelId WHERE d.id = :id")
public class DeviceMaster implements Serializable {
    private static final long       serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                 id;

    @Column(name = "class_filepath")
    private String                  classFilepath;

    @Column(name = "class_name")
    private String                  className;

    @Column(name = "ip_address")
    private String                  ipAddress;

    @Column(name = "model_id")
    private String                  modelId;

    @Column(name = "pcs_id")
    private String                  pcsId;

    @Column(name = "register_conf_filepath")
    private String                  registerConfFilepath;

    @Column(name="output_register_filepath", nullable=false, length=256)
    private String outputRegisterFilepath;

    // bi-directional many-to-one association to CompanyMaster
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyMaster           companyMaster;

    // bi-directional many-to-one association to PcstypeMaster
    @ManyToOne
    @JoinColumn(name = "pcstype_id")
    private PcstypeMaster           pcstypeMaster;

    // bi-directional many-to-one association to EdgeBatteryCharge
    @OneToMany(mappedBy = "deviceMaster")
    private List<EdgeBatteryCharge> edgeBatteryCharges;

    // bi-directional many-to-one association to EdgeSolarPower
    @OneToMany(mappedBy = "deviceMaster")
    private List<EdgeSolarPower>    edgeSolarPowers;

    // bi-directional many-to-one association to MeasBatteryCharge
    @OneToMany(mappedBy = "deviceMaster")
    private List<MeasBatteryCharge> measBatteryCharges;

    // bi-directional many-to-one association to MeasSolarPower
    @OneToMany(mappedBy = "deviceMaster")
    private List<MeasSolarPower>    measSolarPowers;

    // bi-directional many-to-one association to ModuleList
    @OneToMany(mappedBy = "deviceMaster")
    private List<ModuleList>        moduleLists;

    public DeviceMaster() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassFilepath() {
        return this.classFilepath;
    }

    public void setClassFilepath(String classFilepath) {
        this.classFilepath = classFilepath;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getPcsId() {
        return this.pcsId;
    }

    public void setPcsId(String pcsId) {
        this.pcsId = pcsId;
    }

    public String getRegisterConfFilepath() {
        return this.registerConfFilepath;
    }

    public void setRegisterConfFilepath(String registerConfFilepath) {
        this.registerConfFilepath = registerConfFilepath;
    }

    public String getOutputRegisterFilepath() {
        return outputRegisterFilepath;
    }

    public void setOutputRegisterFilepath(String outputRegisterFilepath) {
        this.outputRegisterFilepath = outputRegisterFilepath;
    }

    public CompanyMaster getCompanyMaster() {
        return this.companyMaster;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public PcstypeMaster getPcstypeMaster() {
        return this.pcstypeMaster;
    }

    public void setPcstypeMaster(PcstypeMaster pcstypeMaster) {
        this.pcstypeMaster = pcstypeMaster;
    }

    public List<EdgeBatteryCharge> getEdgeBatteryCharges() {
        return this.edgeBatteryCharges;
    }

    public void setEdgeBatteryCharges(List<EdgeBatteryCharge> edgeBatteryCharges) {
        this.edgeBatteryCharges = edgeBatteryCharges;
    }

    public EdgeBatteryCharge addEdgeBatteryCharge(EdgeBatteryCharge edgeBatteryCharge) {
        getEdgeBatteryCharges().add(edgeBatteryCharge);
        edgeBatteryCharge.setDeviceMaster(this);

        return edgeBatteryCharge;
    }

    public EdgeBatteryCharge removeEdgeBatteryCharge(EdgeBatteryCharge edgeBatteryCharge) {
        getEdgeBatteryCharges().remove(edgeBatteryCharge);
        edgeBatteryCharge.setDeviceMaster(null);

        return edgeBatteryCharge;
    }

    public List<EdgeSolarPower> getEdgeSolarPowers() {
        return this.edgeSolarPowers;
    }

    public void setEdgeSolarPowers(List<EdgeSolarPower> edgeSolarPowers) {
        this.edgeSolarPowers = edgeSolarPowers;
    }

    public EdgeSolarPower addEdgeSolarPower(EdgeSolarPower edgeSolarPower) {
        getEdgeSolarPowers().add(edgeSolarPower);
        edgeSolarPower.setDeviceMaster(this);

        return edgeSolarPower;
    }

    public EdgeSolarPower removeEdgeSolarPower(EdgeSolarPower edgeSolarPower) {
        getEdgeSolarPowers().remove(edgeSolarPower);
        edgeSolarPower.setDeviceMaster(null);

        return edgeSolarPower;
    }

    public List<MeasBatteryCharge> getMeasBatteryCharges() {
        return this.measBatteryCharges;
    }

    public void setMeasBatteryCharges(List<MeasBatteryCharge> measBatteryCharges) {
        this.measBatteryCharges = measBatteryCharges;
    }

    public MeasBatteryCharge addMeasBatteryCharge(MeasBatteryCharge measBatteryCharge) {
        getMeasBatteryCharges().add(measBatteryCharge);
        measBatteryCharge.setDeviceMaster(this);

        return measBatteryCharge;
    }

    public MeasBatteryCharge removeMeasBatteryCharge(MeasBatteryCharge measBatteryCharge) {
        getMeasBatteryCharges().remove(measBatteryCharge);
        measBatteryCharge.setDeviceMaster(null);

        return measBatteryCharge;
    }

    public List<MeasSolarPower> getMeasSolarPowers() {
        return this.measSolarPowers;
    }

    public void setMeasSolarPowers(List<MeasSolarPower> measSolarPowers) {
        this.measSolarPowers = measSolarPowers;
    }

    public MeasSolarPower addMeasSolarPower(MeasSolarPower measSolarPower) {
        getMeasSolarPowers().add(measSolarPower);
        measSolarPower.setDeviceMaster(this);

        return measSolarPower;
    }

    public MeasSolarPower removeMeasSolarPower(MeasSolarPower measSolarPower) {
        getMeasSolarPowers().remove(measSolarPower);
        measSolarPower.setDeviceMaster(null);

        return measSolarPower;
    }

    public List<ModuleList> getModuleLists() {
        return this.moduleLists;
    }

    public void setModuleLists(List<ModuleList> moduleLists) {
        this.moduleLists = moduleLists;
    }

    public ModuleList addModuleList(ModuleList moduleList) {
        getModuleLists().add(moduleList);
        moduleList.setDeviceMaster(this);

        return moduleList;
    }

    public ModuleList removeModuleList(ModuleList moduleList) {
        getModuleLists().remove(moduleList);
        moduleList.setDeviceMaster(null);

        return moduleList;
    }

}