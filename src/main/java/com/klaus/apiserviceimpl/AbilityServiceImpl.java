package com.klaus.apiserviceimpl;

import java.util.List;
import java.util.Map;

import com.klaus.apiservice.AbilityService;

public class AbilityServiceImpl implements AbilityService {

	public Map<?, ?> addIndex(List<?> jsonIndexes) {		
		
//		Map<String, String> map = new HashMap<String, String>();
//        map.put("state", "ok");

		@SuppressWarnings("unchecked")
		Map<String, String> map =(Map<String, String>)jsonIndexes.get(0);
		
		return map;
	}

}
