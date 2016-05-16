package com.klaus.restapi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.klaus.apiservice.IdentityService;
import com.klaus.factory.MyBeansFactory;

@Path("/identity")
public class IdentityVerification {
	
	@GET
	@Path("/identityrandom")
	public String getIdentityRandom(String userName){

		IdentityService service=(IdentityService) MyBeansFactory.getBeans("identityserviceimpl");
		
		return service.getIdentityRandom(userName)+userName;
		
		
	} 
	
	@POST
	@Path("/register")
	public String registerUser(String userName,String password){

		IdentityService service=(IdentityService) MyBeansFactory.getBeans("identityserviceimpl");
		
		return service.registerUser(userName, password);
		
		
	} 
	
}
