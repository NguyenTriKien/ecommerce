package com.ecommerce.model;

import com.ecommerce.entity.User;

public class UserInfo {
	
	private String userid;
	private String username;
	private String password;
	private String userRole;
	
	public UserInfo() {
		super();
	}

	public UserInfo(String userid, String username, String password, String userRole) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	
	public UserInfo(User user) {
		this.userid = user.getUserid();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.userRole = user.getUserRole();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
