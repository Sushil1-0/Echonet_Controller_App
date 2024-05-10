package jp.co.smartsolar.smartedge.database.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the ct_sensor_master database table.
 *
 */
@Entity
@Table(name = "ct_sensor_master")
@NamedQuery(name = "CtSensorMaster.findAll", query = "SELECT c FROM CtSensorMaster c")
@NamedQuery(name = "CtSensorMaster.findByChannelId", query = "SELECT c FROM CtSensorMaster c WHERE c.channelId = :channelId")
@NamedQuery(name = "CtSensorMaster.findByCtType", query = "SELECT c FROM CtSensorMaster c WHERE c.ctType = :ctType")
@NamedQuery(name = "CtSensorMaster.getChannelVoltage", query = "SELECT c FROM CtSensorMaster c WHERE c.channelId = :channelId")
public class CtSensorMaster implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "channel_id")
	private Integer channelId;

	@Column(name = "ct_type")
	private Integer ctType;

	@Column(name = "valid_flag")
	private boolean validFlag;

	@ManyToOne
	@JoinColumn(name = "smartmeter_id")
	private SmartmeterMaster smartmeterMaster;

	@Column(name = "description")
	private String description;
	
	@Column(name = "name")
	private String name; 
	
	@Column(name = "voltage")
	private Integer voltage;

	public CtSensorMaster() {
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getCtType() {
		return ctType;
	}

	public void setCtType(Integer ctType) {
		this.ctType = ctType;
	}

	public boolean isValidFlag() {
		return validFlag;
	}

	public void setValidFlag(boolean validFlag) {
		this.validFlag = validFlag;
	}

	public SmartmeterMaster getSmartmeterMaster() {
		return smartmeterMaster;
	}

	public void setSmartmeterMaster(SmartmeterMaster smartmeterMaster) {
		this.smartmeterMaster = smartmeterMaster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVoltage() {
		return voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}
}