package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.EmpInfo;

public interface EmpInfoDAO {

	@Insert("insert into empinfo(id,stuid,stuname,choose,apartment,city) values (#{id},#{stuId},#{stuName},#{choose},#{apartment},#{city});")
	public void insertEmpInfo(EmpInfo info);
	
	@Insert("insert into empinfoall(id,stuid,stuname,choose,apartment,city) values (#{id},#{stuId},#{stuName},#{choose},#{apartment},#{city});")
	public void insertAllEmpInfo(EmpInfo info);
	
	@Select("select * from empinfo;")
	public List<EmpInfo> getInfos();
	
	@Select("select stuid from empinfo;")
	public List<String> getUpdateStuId();
	
	@Select("select count(*) from empinfo;")
	public int getUpdateInfoCount();
	
	@Select("select * from empinfo where stuid=#{stuid};")
	public EmpInfo getInfobyStuId(String stuid);
	
	@Delete("delete from empinfo where id=#{id}")
	public void deleteEmpById(String id);
	
}
