package com.etech.ha.mst.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.etech.validator.constraints.SingleByte;

public class UserListSearchBean implements Serializable {

	private static final long serialVersionUID = -859808416407252200L;
	
	@SingleByte
	private String empe_num;
	
	@Length(max=100)
	private String empe_name;

	@Length(max=11)
	private String mobile;
	
	@Length(max=50)
	private String tel_no;
	
	@Email
	private String email;
	
	
	public String getEmpe_num() {
		return empe_num;
	}
	public void setEmpe_num(String empe_num) {
		this.empe_num = empe_num;
	}
	public String getEmpe_name() {
		return empe_name;
	}
	public void setEmpe_name(String empe_name) {
		this.empe_name = empe_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	
}
