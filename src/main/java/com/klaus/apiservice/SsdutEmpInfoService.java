package com.klaus.apiservice;

import java.util.List;

import com.klaus.bean.SsdutEmpInfo;

public interface SsdutEmpInfoService {
	
	public List<SsdutEmpInfo> getEmpInfoList(int page);
	public String getEmpInfoDetail(int id);
	
}
