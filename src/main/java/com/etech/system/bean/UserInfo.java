package com.etech.system.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.etech.ha.peer.UserPeer;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 6929029476550017381L;
	
	private UserPeer userPeer;
	
	private Locale locale;
	
	private Map<String, Object> sessionMap = new HashMap<String, Object>();

	public UserPeer getUserPeer() {
		return userPeer;
	}

	public void setUserPeer(UserPeer userPeer) {
		this.userPeer = userPeer;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
