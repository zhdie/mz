package com.deloitte.hk.common.token;

import java.util.Date;

public class Token {

	private String token;
	
	private String appid;
	
	private String userid;
	
	private String username;
	
	private String password;
	
	private String ibmentryuuid;
	
	/*
	 * 角色组, |隔开, 预留
	 */
	private String roles;
	
	/*
	 * 有效期，毫秒
	 */
	private long expires;
	
	/*
	 * 创建时间
	 */
	private Date createtime;

	public Token(String token, String appid, String userid, long expires) {
		this.token = token;
		this.appid = appid;
		this.userid = userid;
		this.expires = expires;
		this.createtime = new Date();
	}
	
	public Token(String token, String appid, String userid, String username, long expires) {
		this.token = token;
		this.appid = appid;
		this.userid = userid;
		this.username = username;
		this.expires = expires;
		this.createtime = new Date();
	}
	
	public Token(String token, String appid, String userid, String username, String password, long expires) {
		this.token = token;
		this.appid = appid;
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.expires = expires;
		this.createtime = new Date();
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 是否有效
	 * @return
	 */
	public boolean isAvailable() {
		boolean result = false;
		if (null == this.createtime) {
			return result;
		}
		Date now = new Date();
		if (now.getTime() < (this.createtime.getTime() + this.getExpires())) {
			result = true;
		}
		return result;
	}

	public String getIbmentryuuid() {
		return ibmentryuuid;
	}

	public void setIbmentryuuid(String ibmentryuuid) {
		this.ibmentryuuid = ibmentryuuid;
	}
	
	
}
