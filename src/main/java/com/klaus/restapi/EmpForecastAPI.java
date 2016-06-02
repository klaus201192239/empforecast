package com.klaus.restapi;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.annotations.Param;

import com.klaus.apiservice.EmpForecastService;
import com.klaus.bean.Forecast;
import com.klaus.factory.MyBeansFactory;

@Path("/forecast")
public class EmpForecastAPI {

	@Path("/uploadscore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,String> uploadExcel(@Context HttpServletRequest request) {

		EmpForecastService service = (EmpForecastService) MyBeansFactory.getBeans("empforecastserviceimpl");
		
		return service.uploadExcel(request);

	}
	
	@Path("/checkstate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,String> checkState(@Param("productid") String productId) {

		EmpForecastService service = (EmpForecastService) MyBeansFactory.getBeans("empforecastserviceimpl");
		
		return service.checkState(productId);

	}
	
	@Path("/result")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Forecast> getResult(@Param("productid") String productId) {

		EmpForecastService service = (EmpForecastService) MyBeansFactory.getBeans("empforecastserviceimpl");
		
		return service.getResult(productId);

	}
	
	
}
