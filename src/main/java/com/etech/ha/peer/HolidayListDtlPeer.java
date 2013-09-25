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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.etech.system.bean.BaseDomain;

@Entity
@Table(name="t_holiday_list_dtl")
public class HolidayListDtlPeer extends BaseDomain {

	private static final long serialVersionUID = -7618400354270986944L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="hldy_start")
	@Temporal(TemporalType.DATE)	//XXX
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="{error.not.be.null}")
	private Date hldy_start;
	
	@Column(name="hldy_end")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="{error.not.be.null}")
	private Date hldy_end;
	
	@ManyToOne
	@JoinColumn(name="hldy_list_id")
	private HolidayListPeer hldyListPeer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public HolidayListPeer getHldyListPeer() {
		return hldyListPeer;
	}

	public void setHldyListPeer(HolidayListPeer hldyListPeer) {
		this.hldyListPeer = hldyListPeer;
	}
	
}
