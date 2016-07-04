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

import com.klaus.apiservice.ScoreService;
import com.klaus.factory.MyBeansFactory;

@Path("/score")
public class ScoreServiceAPI {

	@Path("/uploadfile")
	@POST
	public String uploadExcel(@Context HttpServletRequest request) {

		ScoreService score = (ScoreService) MyBeansFactory.getBeans("scoreserviceimpl");

		String result = score.uploadExcel(request);

		return result;

	}
	
	@Path("/tempcourse")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,String>> getTempCourse() {

		ScoreService score = (ScoreService) MyBeansFactory.getBeans("scoreserviceimpl");

		return score.getTempCourse();

	}

}
