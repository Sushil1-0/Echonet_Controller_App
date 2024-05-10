package jp.co.smartsolar.smartedge.database.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the edge_ct_sensor_data database table.
 *
 */

@Entity
@Table(name = "edge_ct_power")
@NamedQuery(name = "EdgeCtSensorData.findAll", query = "SELECT c FROM EdgeCtSensorData c")
@NamedQuery(name = "EdgeCtSensorData.getLatestData", query = "SELECT c FROM EdgeCtSensorData c ORDER BY c.acquisitionTime DESC")
@NamedQuery(name = "EdgeCtSensorData.findByChannelId", query = "SELECT c FROM EdgeCtSensorData c WHERE c.ctSensorMaster.channelId = :channelId")
@NamedQuery(name = "EdgeCtSensorData.findByDate", query = "SELECT c FROM EdgeCtSensorData c WHERE c.acquisitionTime >= :start AND c.acquisitionTime <= :end ORDER BY c.ctSensorMaster.channelId ASC, c.acquisitionTime ASC")
@NamedQuery(name = "EdgeCtSensorData.getRecordsByChannelId", query = "SELECT c FROM EdgeCtSensorData c WHERE c.ctSensorMaster.channelId = :channelId ORDER BY c.acquisitionTime DESC")
public class EdgeCtSensorData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "acquisition_time")
	private Timestamp acquisitionTime;

	@ManyToOne
	@JoinColumn(name = "channel_id")
	private CtSensorMaster ctSensorMaster;

	@Column(name = "power")
	private Double power;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getAcquisitionTime() {
		return acquisitionTime;
	}

	public void setAcquisitionTime(Timestamp acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}

	public CtSensorMaster getCtSensorMaster() {
		return ctSensorMaster;
	}

	public void setCtSensorMaster(CtSensorMaster ctSensorMaster) {
		this.ctSensorMaster = ctSensorMaster;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public EdgeCtSensorData() {

	}

}