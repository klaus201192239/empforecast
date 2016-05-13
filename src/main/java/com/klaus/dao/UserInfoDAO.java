package com.klaus.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.UserInfo;

public interface UserInfoDAO {
		
	@Insert("insert into userinfo(id,username,password) values (#{id},#{userName},#{password});")
	public void insertUser(UserInfo user);
	
	@Select("select password from userinfo where username=#{userName}")
    public String getPwdByUserName(String userName);

}
