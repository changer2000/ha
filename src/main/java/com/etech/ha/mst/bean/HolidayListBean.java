package com.etech.ha.mst.bean;

import java.io.Serializable;

public class HolidayListBean implements Serializable {

	private static final long serialVersionUID = 7363164240334706138L;
	
	public HolidayListBean(String name, Long cnt) {
		this.name = name;
		this.cnt = cnt;
	}
	
	private String name;
	private Long cnt;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	
	
}
