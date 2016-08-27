package com.klaus.restapi;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.klaus.apiservice.EmpForecastService;
import com.klaus.factory.MyBeansFactory;

@Path("/forecast")
public class EmpForecastAPI {

	@Path("/uploadscore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,String>> uploadExcel(@Context HttpServletRequest request) {

		EmpForecastService service = (EmpForecastService) MyBeansFactory.getBeans("empforecastserviceimpl");
		
		return service.uploadExcel(request);

	}
	
	
	@Path("/uploadstudentinfo")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,String> uploadStudentInfo(@PathParam("stuid")String stuid,@PathParam("pwd")String pwd) {

		EmpForecastService service = (EmpForecastService) MyBeansFactory.getBeans("empforecastserviceimpl");
		
		return service.uploadScoreInfo(stuid, pwd);

	}
	
}
