package com.klaus.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DBCountDAO {
	
	@Select("select countn from dbcount where type=#{type};")
	public int getCountByType(int type);
	
	@Update("update dbcount set countn=#{countn} where type=#{type};")
	public void updateCountByType(@Param("countn")int countn,@Param("type")int type);
	
}