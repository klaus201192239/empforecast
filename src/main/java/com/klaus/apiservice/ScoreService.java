package com.klaus.apiservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ScoreService {
	
	public String uploadExcel(HttpServletRequest req);
	
	public List<Map<String,String>> getTempCourse();
	
	
}
