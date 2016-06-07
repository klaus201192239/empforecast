package com.klaus.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.Means;

public interface MeansDAO {
	
	@Insert("insert into means(id,abilityid,realvalue,meanvalue) values (#{id},#{abilityId},#{realValue},#{meanValue});")
	public void insertKMeans(Means mean);
	
	@Delete("delete from means;")
	public void clearAll();
	
	@Select("select meanvalue from means where abilityid=#{abilityid} and realvalue>=#{realvalue} order by realvalue asc limit 1;")
	public Double getAbilityKmeansByMax(@Param("abilityid") String abilityid,@Param("realvalue") double realvalue);
	
	@Select("select meanvalue from means where abilityid=#{abilityid} and realvalue<=#{realvalue} order by realvalue desc limit 1;")
	public Double getAbilityKmeansByMin(@Param("abilityid") String abilityid,@Param("realvalue") double realvalue);
	
	
}
