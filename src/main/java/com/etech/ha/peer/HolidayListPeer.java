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
@Table(name="t_history_list")
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
	
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date end_date;
	
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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Integer getInit_flg() {
		return init_flg;
	}

	public void setInit_flg(Integer init_flg) {
		this.init_flg = init_flg;
	}
}
