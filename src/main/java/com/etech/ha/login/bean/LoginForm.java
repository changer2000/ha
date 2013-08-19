package com.etech.ha.login.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.etech.ha.peer.AttendanceStatusPeer;
import com.etech.validator.constraints.SingleByte;
import com.etech.validator.group.FirstGroup;

@GroupSequence({LoginForm.class, FirstGroup.class})
public class LoginForm {
	
	/*
	 * 千万注意以下的Min/Max的package，如果用hibernate的，就不起作用
	 */
	@NotEmpty
	@Length(message="{error.length}", min=4, max=50, groups=FirstGroup.class)
	@SingleByte(groups=FirstGroup.class)
	private String empe_num;
	
	@NotEmpty
	@Length(message="{error.length}", min=4, max=8, groups=FirstGroup.class)
	private String pwd;
	
	private List<AttendanceStatusPeer> atdncList = new ArrayList<AttendanceStatusPeer>();
	
	
	public String getEmpe_num() {
		return empe_num;
	}
	public void setEmpe_num(String empe_num) {
		this.empe_num = empe_num;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public List<AttendanceStatusPeer> getAtdncList() {
		return atdncList;
	}
	public void setAtdncList(List<AttendanceStatusPeer> atdncList) {
		this.atdncList = atdncList;
	}
	
}
