package com.klaus.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.FileUploadStatement;

public interface FileUploadStatementDAO {	
	
	@Insert("insert into fileuploadstatement(id,tag,state) values (#{id},#{tag},#{state});")
	public void insertFileState(FileUploadStatement fileUploadStatement);
	
	@Delete("delete from fileuploadstatement where id=#{id}")
	public void deleteFileState(String id);

	@Select("select count(*) from fileuploadstatement where tag=#{tag}")
	public int getFilesCount(int tag);
	
}
