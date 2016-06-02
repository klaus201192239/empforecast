package com.klaus.apiservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.klaus.bean.Forecast;

public interface EmpForecastService {
	
	public Map<String,String> uploadExcel(HttpServletRequest req);
	public Map<String,String> checkState(String productId);
	public List<Forecast> getResult(String productId);
	
}
