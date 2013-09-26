package com.etech.ha.peer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.etech.system.bean.BaseDomain;

@Entity
@Table(name="t_attendance_info")
public class AttendanceInfoPeer extends BaseDomain {

	private static final long serialVersionUID = -6566958266253993754L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	
	//private Long user_id; or EmpePeer
	
	@Column(name="work_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date work_date;
	
	
	//private AttendanceStatusPeer  attendanceStatusPeer;
	
	@ManyToOne
	@JoinColumn(name="hldy_list_id")
	private HolidayListPeer holidayListPeer;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getWork_date() {
		return work_date;
	}


	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}


	public HolidayListPeer getHolidayListPeer() {
		return holidayListPeer;
	}


	public void setHolidayListPeer(HolidayListPeer holidayListPeer) {
		this.holidayListPeer = holidayListPeer;
	}
	
	
}
