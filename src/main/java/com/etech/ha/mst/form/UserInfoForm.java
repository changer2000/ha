package com.etech.ha.mst.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class UserInfoForm implements Serializable {

	private static final long serialVersionUID = 7752772155984684231L;
	
	@NotEmpty
	private String empe_num;
	
	@NotEmpty
	private String empe_name;
	
	@NotEmpty
	private String old_pwd;
	
	@NotEmpty
	private String new_pwd;
	
	@NotEmpty
	private String new_pwd_again;
	
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
	public String getOld_pwd() {
		return old_pwd;
	}
	public void setOld_pwd(String old_pwd) {
		this.old_pwd = old_pwd;
	}
	public String getNew_pwd() {
		return new_pwd;
	}
	public void setNew_pwd(String new_pwd) {
		this.new_pwd = new_pwd;
	}
	public String getNew_pwd_again() {
		return new_pwd_again;
	}
	public void setNew_pwd_again(String new_pwd_again) {
		this.new_pwd_again = new_pwd_again;
	}
	
	
}
