package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TempScoreFileDAO {
	
	@Insert("insert into tempscorefile(filename) values (#{fileName});")
	public void insertFile(String fileName);
	
	@Delete("delete from tempscorefile where filename=#{fileName}")
	public void deleteFile(String fileName);

	@Select("select * from tempscorefile")
	public List<String> getTempFiles();
	
}
