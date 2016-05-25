package com.klaus.dao;


import org.apache.ibatis.annotations.Update;


public interface WorkerDAO {
	
	@Update("update  workstate from course where courseid=#{courseid}")
    public String getCourseId(String courseid);
	
}
