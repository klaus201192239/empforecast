package com.klaus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.klaus.bean.ProductState;

public interface ProductStateDAO {
	
	@Insert("insert into productstate(id,state) values (#{id},#{state})")
	public void buildState(ProductState ps);
	
	@Update("update productstate set state=1 where id=#{pId};")
	public void startProduct(String pId);
	
	@Update("update productstate set state=2 where id=#{pId};")
	public void finishProduct(String pId);
	
	@Select("select * from productstate where state=0;")
	public List<ProductState> findEmptyProduct();
	
	@Select("select * from productstate where id=#{pId};")
	public ProductState findOneProduct(String pId);
	
}
