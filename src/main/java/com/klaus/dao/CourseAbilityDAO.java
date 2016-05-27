package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.CourseAbility;

public interface CourseAbilityDAO {
	
	@Insert("insert into courseability(id,courseid,abilityid,score) values (#{id},#{courseId},#{abilityId},#{score});")
	public void insertCourseAbility(CourseAbility ability);
	
	@Select("select mapping from courseability where courseid=#{courseId} and abilityid=#{abilityId}")
    public double getMapping(@Param("courseId")String courseId,@Param("abilityId")String abilityId);
	
	@Select("select id from courseability where courseid=#{courseid} and abilityid=#{abilityId}")
    public String getId(@Param("courseid") String courseid,@Param("abilityId") String abilityId);
	
	@Select("select * from courseability where courseid=#{courseid}")
    public List<CourseAbility> getMappingByCourseId(@Param("courseid") String courseid);
	
}
