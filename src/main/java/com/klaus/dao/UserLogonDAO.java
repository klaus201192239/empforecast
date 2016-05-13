package com.klaus.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.UserLogon;

public interface UserLogonDAO {
	
	@Insert("insert into userlogon(id,username,shalpwd,timeout) values (#{id},#{userName},#{shalPwd},#{timeout});")
	public void insertLogon(UserLogon logon);
	
	@Delete("delete from userlogon where username=#{userName};")
	public void deleteLogonByUserName(String userName);
	
	@Select("select * from userlogon where username=#{userName}")
    public UserLogon getLogonByUserName(String userName);
	
	
}
