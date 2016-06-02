package com.klaus.dao;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface WorkerDAO {
	
	@Update("update worker set workstate=0 where worker=#{worker}")
    public void finishWork(String worker);
	
	@Update("update worker set workstate=1 where worker=#{worker}")
    public void startWork(String worker);
	
	@Select("select workstate from worker where worker=#{worker}")
    public int working(String worker);
	
}
