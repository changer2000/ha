package com.etech.ha.login.bean;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class LoginForm {
	
	/*
	 * 千万注意以下的Min/Max的package，如果用hibernate的，就不起作用
	 */
	@NotEmpty
	@Length(message="{error.length}", min=4, max=50)
	private String empe_num;
	
	@NotEmpty
	@Length(message="{error.length}", min=4, max=8)
	private String pwd;
	
	
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
	
}
