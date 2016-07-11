package com.klaus.restapi;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	
}
