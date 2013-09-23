package com.etech.ha.peer;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.etech.system.bean.BaseDomain;

@Entity
@Table(name="t_holiday_list", uniqueConstraints={@UniqueConstraint(columnNames={"hldy_year","hldy_id"})})	//XXX
public class HolidayListPeer extends BaseDomain {

	private static final long serialVersionUID = -252344152667921246L;
	
	public static String[] LOGIC_KEYS=new String[]{"hldy_year", "hldy_id"};
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="hldy_year")
	private Integer hldy_year;
	
	@ManyToOne(fetch=FetchType.EAGER)	// , cascade={javax.persistence.CascadeType.PERSIST}  <=== 不起作用，仍然会被更新 
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="hldy_id")			//, updatable=false   <=== 不起作用，仍然会被更新
	private HolidayPeer holidayPeer;	//XXX
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="hldyListPeer")
	@Cascade(value={CascadeType.DELETE})
	private List<HolidayListDtlPeer> hldyListDtlList;
		
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

	public List<HolidayListDtlPeer> getHldyListDtlList() {
		return hldyListDtlList;
	}

	public void setHldyListDtlList(List<HolidayListDtlPeer> hldyListDtlList) {
		this.hldyListDtlList = hldyListDtlList;
	}

}
