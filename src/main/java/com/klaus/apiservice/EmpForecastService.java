package com.klaus.apiservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface EmpForecastService {
	
	public List<Map<String, String>> uploadExcel(HttpServletRequest req);
	
}
