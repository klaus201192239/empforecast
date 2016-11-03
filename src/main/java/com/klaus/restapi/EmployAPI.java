package com.klaus.restapi;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.klaus.apiservice.EmployService;
import com.klaus.factory.MyBeansFactory;


@Path("/employ")
public class EmployAPI {

	
	@Path("/uploadinterview")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String uploadInterview(Map<?,?> map) {

		
		//{
		//  "interview":{"company":"","place":"","time":"","content":""},
		//  "interviewer":[{"stuid":"","stuname":"","note":""},{"stuid":"","stuname":"","note":""}.....]
		//}
		
		EmployService service = (EmployService ) MyBeansFactory.getBeans("employserviceimpl");

		return service.uploadInterview(map);

	}
	
	
}
