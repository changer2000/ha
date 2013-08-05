package com.etech.system.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class BaseDomain implements Serializable {
	
	
	private static final long serialVersionUID = 7897062602533451861L;
	
	@Id
	@Column(name="id")
	protected Long id;
	
	@Column(name="del_flg")
	protected Integer del_flg;
	
	//将来所有的peer里都要定义自己的LOGIC_KEYS，以方便按逻辑主键进行的相关操作
	public static String[] LOGIC_KEYS=new String[]{"id"};
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(Integer del_flg) {
		this.del_flg = del_flg;
	}
}
