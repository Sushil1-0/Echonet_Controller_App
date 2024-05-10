package jp.co.smartsolar.smartedge.database.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "schedule_data")
@NamedQuery(name = "OutputControl.findAll", query = "SELECT t FROM OutputControl t")
public class OutputControl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Timestamp datetime;
       
    @Column(name = "control_value")
    private String controlValue;

    public OutputControl() {
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getControlValue() {
		return controlValue;
	}

	public void setControlValue(String controlValue) {
		this.controlValue = controlValue;
	}

	public OutputControl(Timestamp timestamp, String controlValue) {
		this.datetime = timestamp;
		this.controlValue = controlValue;
	}
	
}
