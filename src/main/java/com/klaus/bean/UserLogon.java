package com.klaus.bean;

public class UserLogon {
	
	private String id;
	private String userName;
	private String shalPwd;
	private long timeout;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShalPwd() {
		return shalPwd;
	}
	public void setShalPwd(String shalPwd) {
		this.shalPwd = shalPwd;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	
	
}
