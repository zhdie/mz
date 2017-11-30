/**
* TODO
* @Project: DzIsc
* @Title: AuthBean.java
* @Package com.ihere.sso
* @author zhangdie
* @Date 2015-12-16 下午2:16:12 
* @Copyright: Copyright (c) 2015 ZTE Ltd. All Rights Reserved.  
* @Version v2.0.0
*/ 
package com.deloitte.hk.common.bean;

import java.io.Serializable;

public class AuthBean implements Serializable {

	private boolean auth;
	private String service;
	private String role;
	private String isallowedit;
	
	private String token;
	
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getIsallowedit() {
		return isallowedit;
	}
	public void setIsallowedit(String isallowedit) {
		this.isallowedit = isallowedit;
	}
	
}
