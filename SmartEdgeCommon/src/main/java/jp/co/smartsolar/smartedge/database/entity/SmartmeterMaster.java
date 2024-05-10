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
 * The persistent class for the smartmeter_master database table.
 *
 */
@Entity
@Table(name = "smartmeter_master")
@NamedQuery(name = "SmartmeterMaster.findAll", query = "SELECT s FROM SmartmeterMaster s")
@NamedQuery(name = "SmartmeterMaster.findById", query = "SELECT d FROM SmartmeterMaster d WHERE d.id = :id")
@NamedQuery(name = "SmartmeterMaster.findBySmartmeterIdAndSmartmeterType", query = "SELECT s FROM SmartmeterMaster s WHERE smartmeterId = :smartmeterId AND smartmeterType = :smartmeterType")
@NamedQuery(name = "SmartmeterMaster.findByClassName", query = "SELECT d FROM SmartmeterMaster d WHERE d.className = :className")
public class SmartmeterMaster implements Serializable {
    private static final long     serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer               id;

    @Column(name = "class_filepasth")
    private String                classFilepasth;

    @Column(name = "class_name")
    private String                className;

    @Column(name = "smartmeter_id")
    private String                smartmeterId;

    @Column(name = "smartmeter_type")
    private String                smartmeterType;

    //bi-directional many-to-one association to EdgeSystemPower
    @OneToMany(mappedBy = "smartmeterMaster")
    private List<EdgeSystemPower> edgeSystemPowers;

    //bi-directional many-to-one association to MeasSystemPower
    @OneToMany(mappedBy = "smartmeterMaster")
    private List<MeasSystemPower> measSystemPowers;

    public SmartmeterMaster() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassFilepasth() {
        return this.classFilepasth;
    }

    public void setClassFilepasth(String classFilepasth) {
        this.classFilepasth = classFilepasth;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSmartmeterId() {
        return this.smartmeterId;
    }

    public void setSmartmeterId(String smartmeterId) {
        this.smartmeterId = smartmeterId;
    }

    public String getSmartmeterType() {
        return this.smartmeterType;
    }

    public void setSmartmeterType(String smartmeterType) {
        this.smartmeterType = smartmeterType;
    }

    public List<EdgeSystemPower> getEdgeSystemPowers() {
        return this.edgeSystemPowers;
    }

    public void setEdgeSystemPowers(List<EdgeSystemPower> edgeSystemPowers) {
        this.edgeSystemPowers = edgeSystemPowers;
    }

    public EdgeSystemPower addEdgeSystemPower(EdgeSystemPower edgeSystemPower) {
        getEdgeSystemPowers().add(edgeSystemPower);
        edgeSystemPower.setSmartmeterMaster(this);

        return edgeSystemPower;
    }

    public EdgeSystemPower removeEdgeSystemPower(EdgeSystemPower edgeSystemPower) {
        getEdgeSystemPowers().remove(edgeSystemPower);
        edgeSystemPower.setSmartmeterMaster(null);

        return edgeSystemPower;
    }

    public List<MeasSystemPower> getMeasSystemPowers() {
        return this.measSystemPowers;
    }

    public void setMeasSystemPowers(List<MeasSystemPower> measSystemPowers) {
        this.measSystemPowers = measSystemPowers;
    }

    public MeasSystemPower addMeasSystemPower(MeasSystemPower measSystemPower) {
        getMeasSystemPowers().add(measSystemPower);
        measSystemPower.setSmartmeterMaster(this);

        return measSystemPower;
    }

    public MeasSystemPower removeMeasSystemPower(MeasSystemPower measSystemPower) {
        getMeasSystemPowers().remove(measSystemPower);
        measSystemPower.setSmartmeterMaster(null);

        return measSystemPower;
    }

}