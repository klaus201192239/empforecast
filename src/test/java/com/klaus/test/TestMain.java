package com.klaus.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.klaus.apiservice.AbilityService;
import com.klaus.factory.MyBeansFactory;

public class TestMain {

	public static void main(String[] args) {

		System.out.println("Test Start");
		
		
		List<Map> array = new ArrayList<Map>();

		Map obj = new HashMap();
		obj.put("abilityid", "146425074613610");
		obj.put("score", "0.12");
		array.add(obj);

		Map obj1 = new HashMap();
		obj1.put("abilityid", "146425074621687");
		obj1.put("score", "0.7");
		array.add(obj1);

		
		Map objj = new HashMap();
		objj.put("courseid", "146484706932361");
		objj.put("ability", array);

	
		
		List<Map> array1 = new ArrayList<Map>();

		Map obj11 = new HashMap();
		obj11.put("abilityid", "146425074613610");
		obj11.put("score", "0.12");
		array1.add(obj11);

		Map obj122 = new HashMap();
		obj122.put("abilityid", "146425074621687");
		obj122.put("score", "0.7");
		array1.add(obj122);

		Map objjj = new HashMap();
		objjj.put("courseid", "146484706975472");
		objjj.put("ability", array1);
		
		
		List<Map> ar = new ArrayList<Map>();
		ar.add( objj);
		ar.add( objjj);
		
		System.out.println(ar);

		AbilityService service = (AbilityService) MyBeansFactory.getBeans("abilityserviceimpl");

		service.addCourseAbility(ar);

		System.out.println("Test End");

	}

}
