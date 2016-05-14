package com.klaus.service.interfaceservice;

public interface IdentityService {
	
	public String getIdentityRandom(String userName);
	public String logonUser(String userName,String passWord);
	public String registerUser(String userName,String passWord);
	
}
