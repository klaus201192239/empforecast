package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.StudentAbility;

public interface StudentAbilityDAO {

	@Insert("insert into studentability(id,stuid,abilitya,abilityb,abilityc,abilityd,abilitye,abilityf) values (#{id},#{stuId},#{abilityA},#{abilityB},#{abilityC},#{abilityD},#{abilityE},#{abilityF});")
	public void insertStudentAbility(StudentAbility stu);
	
	@Insert("insert into studentabilityall(id,stuid,abilitya,abilityb,abilityc,abilityd,abilitye,abilityf) values (#{id},#{stuId},#{abilityA},#{abilityB},#{abilityC},#{abilityD},#{abilityE},#{abilityF});")
	public void insertStudentAbilityAll(StudentAbility stu);
	
	@Delete("delete from studentability where id=#{id}")
    public void deleteStudentAbilityById(String Id);
	
	@Delete("delete from studentability where stuid=#{stuId}")
    public void deleteStudentAbilityByStuId(String stuId);
	
	@Select("select abilitya from studentabilityall;")
    public List<Double> getAllNumberAbilityA();
	
	@Select("select * from studentabilityall;")
    public List<StudentAbility> getAllInfo();
	
}
