package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.SsdutEmpInfo;;

public interface SsdutEmpInfoDAO {

	@Insert("insert into ssdutempinfo(id,calory,title,ptime,htmlurl) values (#{id},#{calory},#{title},#{ptime},#{htmlurl});")
	public void insertEmpInfo(SsdutEmpInfo info);
	
	@Select("select max(id) from ssdutempinfo where calory=#{calory};")
	public int getCurrentIndex(@Param("calory")int calory);
	
	@Select("SELECT a.* from ssdutempinfo a join (SELECT id FROM `ssdutempinfo` ORDER BY id DESC LIMIT #{page},6) b on a.id=b.id;")
	public List<SsdutEmpInfo> getPageList(int page);
	
}
