package com.klaus.restapi;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

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

}
