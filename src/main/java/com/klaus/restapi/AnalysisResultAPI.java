package com.klaus.restapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.klaus.bean.Employment;

@Path("/analysis")
public class AnalysisResultAPI {
	
	@Path("/student")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employment singleStudentAnalysis(@QueryParam("stuid") final String stuId){
		
		Employment s= new Employment();
		
		s.setCity("city");
		s.setId(stuId);
		s.setIndustry("ind");
		s.setPosition("po");
		s.setSalary(32.9);
		
		return s;
		
	}
	
	
}
