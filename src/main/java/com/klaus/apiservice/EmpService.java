package com.klaus.apiservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public interface EmpService {

	public String uploadExcel(@Context HttpServletRequest request) ;
	
}
