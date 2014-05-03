package com.etech.assertthat;

public class User {
	
	public User(String userName, int credits) {
		this.userName = userName;
		this.credits = credits;
	}
	
	private String userName;
	private int credits;
	
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
