package com.etech.ha.peer;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.etech.system.bean.BaseDomain;
import com.etech.validator.group.FirstGroup;

@GroupSequence({HolidayPeer.class, FirstGroup.class})
@Entity
@Table(name="t_holiday")
public class HolidayPeer extends BaseDomain {
	
	private static final long serialVersionUID = 6278078220815116664L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotEmpty
	@Length(min=2, max=100, message="{error.length}", groups=FirstGroup.class)
	private String name;
	
	@Column(name="order_no")
	@NotNull
	@javax.validation.constraints.Min(value=1, message="{error.min}")
	//@org.hibernate.validator.Min(value=1, message="{error.min}") 根本不起作用
	private Long order_no;
	
	@OneToMany(mappedBy="holidayPeer", fetch=FetchType.LAZY)	//XXX Just for sample,fetch默认值就是FetchType.LAZY
	private List<HolidayListPeer> hldyList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HolidayListPeer> getHldyList() {
		return hldyList;
	}

	public void setHldyList(List<HolidayListPeer> hldyList) {
		this.hldyList = hldyList;
	}

	public Long getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	
}
