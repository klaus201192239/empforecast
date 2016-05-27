package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.Course;

public interface CourseDAO {

	@Insert("insert into course(id,courseid,coursename,coursegrade) values (#{id},#{courseId},#{courseName},#{courseGrade});")
	public void insertCourse(Course course);

	@Select("select id from course where courseid=#{courseid}")
	public String getCourseId(String courseid);

	@Select("select count(*) from course where id=#{id}")
	public int getCourseCount(String id);

	@Select("select * from course")
	public List<Course> getCourse();

}
