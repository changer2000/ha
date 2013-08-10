package com.etech.ha.peer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.etech.system.bean.BaseDomain;

@Entity
@Table(name="t_attendance_status")
public class AttendanceStatusPeer extends BaseDomain {

	private static final long serialVersionUID = -7310928382710559939L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
	
	@Column(name="atndnc_name")
	private String atndnc_name;

	public String getAtndnc_name() {
		return atndnc_name;
	}

	public void setAtndnc_name(String atndnc_name) {
		this.atndnc_name = atndnc_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
