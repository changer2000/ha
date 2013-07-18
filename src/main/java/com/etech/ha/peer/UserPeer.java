package com.etech.ha.peer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_user")
public class UserPeer implements Serializable {
	
	private static final long serialVersionUID = 5349810873546597127L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="empe_num")
	private String empe_num;
	
	@Column(name="empe_name")
	private String empe_name;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="tel_no")
	private String tel_no;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dept_cd")
	private String dept_cd;
	
	@Column(name="del_flg")
	private int del_flg;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	
	
}
