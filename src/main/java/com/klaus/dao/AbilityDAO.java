package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.Ability;

public interface AbilityDAO {
	
	@Insert("insert into ability(id,name) values (#{id},#{name});")
	public void insertAbility(Ability ability);
	
	@Select("select id from ability where name=#{name}")
    public String getIdByName(String name);
	
	@Select("select name from ability where id=#{id}")
    public String getIdById(String id);
	
	@Select("select * from ability order by id desc")
    public List<Ability> getAllInfo();
	
	@Select("select * from abilitytemp order by id desc")
    public List<Ability> getAbilityInfo();
	
	@Select("select count(*) from ability where id=#{id}")
    public int getAbilityCount(String id);
	
	@Delete("delete from abilityinfo;")
    public void deleteAbilityName();
	
	@Select("select * from abilityinfo order by id desc")
    public List<Ability> getAbilityInfoList();
	
	@Select("select id from ability order by id desc")
    public List<String> getAbilityInfoIdList();
	
}
