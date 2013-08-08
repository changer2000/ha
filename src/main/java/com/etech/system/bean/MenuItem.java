package com.etech.system.bean;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = -5386621343484249884L;
	
	private String menuName;
	private String url;
	private boolean disabled;
	
	private List<MenuItem> subMenuList;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<MenuItem> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<MenuItem> subMenuList) {
		this.subMenuList = subMenuList;
	}
	
}
