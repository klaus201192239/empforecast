package com.klaus.restapi;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.klaus.apiservice.AbilityService;
import com.klaus.factory.MyBeansFactory;

@Path("/ability")
public class AbilityServiceAPI {	
	
	@Path("/addindex")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<?, ?> addIndex(final List<?> jsonIndexes){
		
		
		AbilityService service=(AbilityService)MyBeansFactory.getBeans("abilityserviceimpl");
		
		return service.addIndex(jsonIndexes);
		
		
	}
	
}
