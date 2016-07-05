package com.klaus.restapi;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.klaus.apiservice.EmpService;
import com.klaus.factory.MyBeansFactory;

@Path("/emp")
public class EmpServiceAPI {
	
	@Path("/uploadfile")
	@POST
	public String uploadExcel(@Context HttpServletRequest request) {

		EmpService score = (EmpService) MyBeansFactory.getBeans("empserviceimpl");

		String result = score.uploadExcel(request);

		return result;

	}
	
}
