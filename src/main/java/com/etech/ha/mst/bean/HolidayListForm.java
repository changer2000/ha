package com.etech.ha.mst.bean;

import java.io.Serializable;
import java.util.List;

import com.etech.ha.peer.HolidayListPeer;

public class HolidayListForm implements Serializable {

	private static final long serialVersionUID = -3552784362310087805L;

	private HolidayListSearchBean searchBean;
	private List<HolidayListPeer> list;
	
	private String[] selKey;
	
	private List<Long> hldyOptions;
	
	public HolidayListSearchBean getSearchBean() {
		return searchBean;
	}
	public void setSearchBean(HolidayListSearchBean searchBean) {
		this.searchBean = searchBean;
	}
	public List<HolidayListPeer> getList() {
		return list;
	}
	public void setList(List<HolidayListPeer> list) {
		this.list = list;
	}
	public String[] getSelKey() {
		return selKey;
	}
	public void setSelKey(String[] selKey) {
		this.selKey = selKey;
	}
	public List<Long> getHldyOptions() {
		return hldyOptions;
	}
	public void setHldyOptions(List<Long> hldyOptions) {
		this.hldyOptions = hldyOptions;
	}
	
}
