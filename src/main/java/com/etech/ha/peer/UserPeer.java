package com.etech.ha.peer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.etech.system.bean.BaseDomain;


@Entity
@Table(name="t_user", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class UserPeer extends BaseDomain {
	
	private static final long serialVersionUID = 5349810873546597127L;
	
	public static String[] LOGIC_KEYS=new String[]{"email"};
	
	
	public UserPeer() {
		super();
	}
	
	//要是有如下的构造函数，就一定要加一个如上默认的构造函数，否则，其他地方用的时候会出错
	//下面这个构造函数可以由于定制select列的查询返回对象
	public UserPeer(String empe_num, String empe_name) {
		this.empe_num = empe_num;
		this.empe_name = empe_name;
	}
	
	@Id
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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dflt_atndnc_sts_id")
	private AttendanceStatusPeer attendanceStatusPeer;

	@Column(name="del_flg")
	protected Integer del_flg;
	
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

	public Integer getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(Integer del_flg) {
		this.del_flg = del_flg;
	}
	public AttendanceStatusPeer getAttendanceStatusPeer() {
		return attendanceStatusPeer;
	}
	public void setAttendanceStatusPeer(AttendanceStatusPeer attendanceStatusPeer) {
		this.attendanceStatusPeer = attendanceStatusPeer;
	}
	
	
}
