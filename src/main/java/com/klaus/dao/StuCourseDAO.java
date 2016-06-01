package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.StuCourse;


public interface StuCourseDAO {
	
	@Insert("insert into stucourse(id,stuid,courseid,score) values (#{id},#{stuId},#{courseId},#{score});")
	public void insertStudentCourse(StuCourse stuC);
	
	@Insert("insert into stucourseall(id,stuid,courseid,score) values (#{id},#{stuId},#{courseId},#{score});")
	public void insertStudentCourseAll(StuCourse stuC);
	
	//@Select("select id from stucourseall where stuid=#{stuId} and courseid=#{courseId}")
    //public String getStudentCourse(@Param("stuId") String stuId,@Param("courseId")String courseId);
	
	@Select("select distinct stuid from stucourse where stuid<#{stuId} order by stuid desc limit 10;")
    public List<String> getStudentCourseLimit10(@Param("stuId") String stuId);
	
	@Select("select distinct stuid from stucourse order by stuid desc limit 10")
    public List<String> getStudentCourseTop10();
	
	@Select("select * from stucourse where stuid=#{stuId}")
    public List<StuCourse> getStudentCourseByStuId(@Param("stuId") String stuId);
	
	@Delete("delete from stucourse where id=#{id}")
    public void deleteStudentCourseById(String Id);
	
	@Delete("delete from stucourse where stuid=#{stuId}")
    public void deleteStudentCourseByStuId(String stuId);
	
}
