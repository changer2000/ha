package com.etech.system.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class BaseDomain implements Serializable {
	
	
	private static final long serialVersionUID = 7897062602533451861L;
	
	@Id
	@Column(name="id")
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
