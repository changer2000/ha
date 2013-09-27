package com.etech.ha.mst.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.etech.ha.mst.bean.UserListSearchBean;
import com.etech.ha.peer.UserPeer;

public class UserListForm implements Serializable {

	private static final long serialVersionUID = -6762518274219654960L;
	
	@Valid
	private UserListSearchBean searchBean;
	
	private List<UserPeer> userList;
	
	private String[] selKey;

	public UserListSearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(UserListSearchBean searchBean) {
		this.searchBean = searchBean;
	}

	public List<UserPeer> getUserList() {
		return userList;
	}

	public void setUserList(List<UserPeer> userList) {
		this.userList = userList;
	}

	public String[] getSelKey() {
		return selKey;
	}

	public void setSelKey(String[] selKey) {
		this.selKey = selKey;
	}
	
}
