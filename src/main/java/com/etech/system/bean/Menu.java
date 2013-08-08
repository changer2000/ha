package com.etech.system.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {

	private static final long serialVersionUID = -3148121959115881515L;
	
	private List<MenuItem> menuList = new ArrayList<MenuItem>();

	public List<MenuItem> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuItem> menuList) {
		this.menuList = menuList;
	}
	
	
	
}
