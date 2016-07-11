package com.klaus.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.AbilityEmp;

public interface AbilityEmpDAO {

	@Insert("insert into abilityemp(id,stuid,abilitya,abilityb,abilityc,abilityd,abilitye,abilityf,abilityg,abilityh,abilityi,abilityj,abilityk,abilityl,abilitym,abilityn,abilityo,abilityp,abilityq,abilityr,abilitys,abilityt,abilityu,abilityv,abilityw,abilityx,abilityaa,abilityab,abilityac,abilityad,abilityae,abilityaf,abilityag,abilityah,abilityai,abilityaj,abilityak,abilityal,choose,apartment,city) values (#{id},#{stuId},#{abilitya},#{abilityb},#{abilityc},#{abilityd},#{abilitye},#{abilityf},#{abilityg},#{abilityh},#{abilityi},#{abilityj},#{abilityk},#{abilityl},#{abilitym},#{abilityn},#{abilityo},#{abilityp},#{abilityq},#{abilityr},#{abilitys},#{abilityt},#{abilityu},#{abilityv},#{abilityw},#{abilityx},#{abilityaa},#{abilityab},#{abilityac},#{abilityad},#{abilityae},#{abilityaf},#{abilityag},#{abilityah},#{abilityai},#{abilityaj},#{abilityak},#{abilityal},#{choose},#{apartment},#{city});")
	public void insertInfo(AbilityEmp stu);
	
	@Select("select count(*) from abilityemp;")
	public int getInfoCount();
	
}
