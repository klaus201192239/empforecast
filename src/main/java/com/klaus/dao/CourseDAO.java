package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.Course;

public interface CourseDAO {

	@Insert("insert into course(id,courseid,coursename,coursegrade) values (#{id},#{courseId},#{courseName},#{courseGrade});")
	public void insertCourse(Course course);
	
	@Insert("insert into coursetemp(id,courseid,coursename,coursegrade) values (#{id},#{courseId},#{courseName},#{courseGrade});")
	public void insertCourseTemp(Course course);

	@Select("select id from course where courseid=#{courseid}")
	public String getCourseId(String courseid);
	
	@Select("select id from coursetemp where courseid=#{courseid}")
	public String getCourseTempId(String courseid);
	
	@Select("select * from coursetemp;")
	public List<Course> getCoursesTemp();

	@Select("select count(*) from course where id=#{id}")
	public int getCourseCount(String id);

	@Select("select * from course")
	public List<Course> getCourse();
	
	@Delete("delete from coursetemp where id=#{id}")
	public void deleteCourseById(String id);

}
