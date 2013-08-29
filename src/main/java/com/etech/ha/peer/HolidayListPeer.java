package com.etech.ha.peer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.etech.system.bean.BaseDomain;

@Entity
@Table(name="t_holiday_list")
public class HolidayListPeer extends BaseDomain {

	private static final long serialVersionUID = -252344152667921246L;
	
	public static String[] LOGIC_KEYS=new String[]{"hldy_year", "hldy_id"};
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="hldy_year")
	private Integer hldy_year;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="hldy_id")
	private HolidayPeer holidayPeer;	//XXX
	
	@Column(name="hldy_start")
	@Temporal(TemporalType.DATE)
	private Date hldy_start;
	
	@Column(name="hldy_end")
	@Temporal(TemporalType.DATE)
	private Date hldy_end;
	
	@Column(name="start_dt")
	@Temporal(TemporalType.DATE)
	private Date start_dt;
	
	@Column(name="end_dt")
	@Temporal(TemporalType.DATE)
	private Date end_dt;
	
	@Column(name="init_flg")
	private Integer init_flg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHldy_year() {
		return hldy_year;
	}

	public void setHldy_year(Integer hldy_year) {
		this.hldy_year = hldy_year;
	}

	public HolidayPeer getHolidayPeer() {
		return holidayPeer;
	}

	public void setHolidayPeer(HolidayPeer holidayPeer) {
		this.holidayPeer = holidayPeer;
	}

	public Date getHldy_start() {
		return hldy_start;
	}

	public void setHldy_start(Date hldy_start) {
		this.hldy_start = hldy_start;
	}

	public Date getHldy_end() {
		return hldy_end;
	}

	public void setHldy_end(Date hldy_end) {
		this.hldy_end = hldy_end;
	}

	public Date getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(Date start_dt) {
		this.start_dt = start_dt;
	}

	public Date getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(Date end_dt) {
		this.end_dt = end_dt;
	}

	public Integer getInit_flg() {
		return init_flg;
	}

	public void setInit_flg(Integer init_flg) {
		this.init_flg = init_flg;
	}

}
