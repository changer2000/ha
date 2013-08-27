package com.etech.ha.mst.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HolidayListSearchBean implements Serializable {

	private static final long serialVersionUID = 5895352719277065952L;
	
	private Integer hldy_year;
	
	private Long hldy_id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date hldy_start;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date hldy_end;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start_date;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end_date;

	public Integer getHldy_year() {
		return hldy_year;
	}

	public void setHldy_year(Integer hldy_year) {
		this.hldy_year = hldy_year;
	}

	public Long getHldy_id() {
		return hldy_id;
	}

	public void setHldy_id(Long hldy_id) {
		this.hldy_id = hldy_id;
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
	
}
