package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.StudentAbility;

public interface StudentAbilityDAO {

	@Insert("insert into studentability(id,stuid,abilitya,abilityb,abilityc,abilityd,abilitye,abilityf,abilityg,abilityh,abilityi,abilityj,abilityk,abilityl,abilitym,abilityn,abilityo,abilityp,abilityq,abilityr,abilitys,abilityt,abilityu,abilityv,abilityw,abilityx,abilityaa,abilityab,abilityac,abilityad,abilityae,abilityaf,abilityag,abilityah,abilityai,abilityaj,abilityak,abilityal) values (#{id},#{stuId},#{abilitya},#{abilityb},#{abilityc},#{abilityd},#{abilitye},#{abilityf},#{abilityg},#{abilityh},#{abilityi},#{abilityj},#{abilityk},#{abilityl},#{abilitym},#{abilityn},#{abilityo},#{abilityp},#{abilityq},#{abilityr},#{abilitys},#{abilityt},#{abilityu},#{abilityv},#{abilityw},#{abilityx},#{abilityaa},#{abilityab},#{abilityac},#{abilityad},#{abilityae},#{abilityaf},#{abilityag},#{abilityah},#{abilityai},#{abilityaj},#{abilityak},#{abilityal});")
	public void insertStudentAbility(StudentAbility stu);
	
	@Insert("insert into studentabilityall(id,stuid,abilitya,abilityb,abilityc,abilityd,abilitye,abilityf,abilityg,abilityh,abilityi,abilityj,abilityk,abilityl,abilitym,abilityn,abilityo,abilityp,abilityq,abilityr,abilitys,abilityt,abilityu,abilityv,abilityw,abilityx,abilityaa,abilityab,abilityac,abilityad,abilityae,abilityaf,abilityag,abilityah,abilityai,abilityaj,abilityak,abilityal) values (#{id},#{stuId},#{abilitya},#{abilityb},#{abilityc},#{abilityd},#{abilitye},#{abilityf},#{abilityg},#{abilityh},#{abilityi},#{abilityj},#{abilityk},#{abilityl},#{abilitym},#{abilityn},#{abilityo},#{abilityp},#{abilityq},#{abilityr},#{abilitys},#{abilityt},#{abilityu},#{abilityv},#{abilityw},#{abilityx},#{abilityaa},#{abilityab},#{abilityac},#{abilityad},#{abilityae},#{abilityaf},#{abilityag},#{abilityah},#{abilityai},#{abilityaj},#{abilityak},#{abilityal});")
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
