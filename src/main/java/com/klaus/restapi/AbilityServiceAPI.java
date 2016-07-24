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
		
		
		// [{"name":"AAAA"},{"name":"BBBBBBB"},{"name":"CCCCCC"},{"name":"DDDDDDDD"} ]
		
		AbilityService service=(AbilityService)MyBeansFactory.getBeans("abilityserviceimpl");
		
		return service.addIndex(jsonIndexes);
		
		
	}
	
	
	@Path("/courseability")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<?, ?> addCourseAbility(final List<?> jsonMapping){
		
		/*
		   [
    	      {"courseid":"AAAA","ability":[
		                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
		                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
		                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
		                                     .............................
		                                   ]
		       },
		      {"courseid":"BBBB","ability":[
			                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
			                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
			                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
			                                     .............................
			                               ]
			  },
		   ................................
		    ]
		*/   
		
		AbilityService service=(AbilityService)MyBeansFactory.getBeans("abilityserviceimpl");
		
		return service.addCourseAbility(jsonMapping);
		
		
	}
	
	
	@Path("/abilitylist")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<?> getAbilityList(){
		
		// [{"id":"AAAA","name":"BBBBBBB"},{"id":"CCCCCC","name":"LLLL"}]
		
		AbilityService service=(AbilityService)MyBeansFactory.getBeans("abilityserviceimpl");
		
		return service.getAbilityList();
			
	}
	
	
}
