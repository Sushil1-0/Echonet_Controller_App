package jp.co.smartsolar.smartedge.database.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the meas_ct_sensor_data database table.
 *
 */

@Entity
@Table(name = "meas_ct_power")
@NamedQuery(name = "MeasCtSensorData.findAll", query = "SELECT c FROM MeasCtSensorData c")
@NamedQuery(name = "MeasCtSensorData.getLatestData", query = "SELECT c FROM MeasCtSensorData c ORDER BY c.acquisitionTime DESC")
@NamedQuery(name = "MeasCtSensorData.findByChannelId", query = "SELECT c FROM MeasCtSensorData c WHERE c.ctSensorMaster.channelId = :channelId")
public class MeasCtSensorData implements Serializable {
	
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

	public MeasCtSensorData() {

	}

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

}