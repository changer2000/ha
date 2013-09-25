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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.method.annotation.FilterEmptyBean;

import com.etech.system.bean.BaseDomain;
import com.etech.validator.group.FirstGroup;
import com.etech.validator.group.SecondGroup;

@GroupSequence({HolidayListPeer.class, SecondGroup.class})
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
	@NotNull(message="{error.not.be.null}")
	private Integer hldy_year;
	
	@ManyToOne(fetch=FetchType.EAGER)	// , cascade={javax.persistence.CascadeType.PERSIST}  <=== 不起作用，仍然会被更新 
	//@Cascade(value={CascadeType.SAVE_UPDATE})	注释掉了后，可能对一些测试代码有影响
	@JoinColumn(name="hldy_id")			//, updatable=false   <=== 不起作用，仍然会被更新
	//@NotNull(message="{error.not.be.null}"), 加在这里根本不起作用，因为就算页面上没有选hldy_id，这个对象也会被new出来。
	//@Valid, 这个加上后，会验证HolidayPeer里的所有需要验证的地方，但我这里只需要验证HolidayPeer.id，其他不需要验证。所以这个方法也不可行
	private HolidayPeer holidayPeer;	//XXX
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="hldyListPeer")
	@Cascade(value={CascadeType.DELETE})
	@FilterEmptyBean
	@Valid
	private List<HolidayListDtlPeer> hldyListDtlList;
		
	@Column(name="start_dt")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="{error.not.be.null}")
	private Date start_dt;
	
	@Column(name="end_dt")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="{error.not.be.null}")
	private Date end_dt;
	
	@Column(name="init_flg")
	private Integer init_flg;
	
	@Transient
	@NotNull(message="{error.not.be.null}")
	private Long holidayPeerId;

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

	public Long getHolidayPeerId() {
		return holidayPeerId;
	}

	public void setHolidayPeerId(Long holidayPeerId) {
		this.holidayPeerId = holidayPeerId;
	}

}
