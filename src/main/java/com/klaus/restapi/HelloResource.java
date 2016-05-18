package com.klaus.restapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import com.klaus.dao.CourseDAO;
import com.klaus.factory.MyBeansFactory;



@Path("/hello")
public class HelloResource {
	
	@GET
	public String sayHello(@QueryParam("klaus") final String klaus) {
	
		CourseDAO courseDao=(CourseDAO) MyBeansFactory.getBeans("coursedao");
		String str=courseDao.getCourseId("105213");
		//System.out.println(str);		
		
		return "hiklaus"+str+klaus;
		
	}
	
}