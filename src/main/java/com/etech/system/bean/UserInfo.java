package com.etech.system.bean;

import java.io.Serializable;

import com.etech.ha.peer.UserPeer;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 6929029476550017381L;
	
	private UserPeer userPeer;

	public UserPeer getUserPeer() {
		return userPeer;
	}

	public void setUserPeer(UserPeer userPeer) {
		this.userPeer = userPeer;
	}

}
