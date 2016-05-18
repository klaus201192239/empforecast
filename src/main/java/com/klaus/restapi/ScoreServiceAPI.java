package com.klaus.restapi;

import java.io.IOException;
import java.io.InputStream;
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
		
		String result="";
		
		try {
			
			InputStream in = request.getInputStream();
			
			ScoreService score=(ScoreService) MyBeansFactory.getBeans("scoreserviceimpl");
			
			result=score.uploadExcel(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;

	}

}
