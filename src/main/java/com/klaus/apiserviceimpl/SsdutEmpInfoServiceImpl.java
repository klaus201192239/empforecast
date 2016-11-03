package com.klaus.apiserviceimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.klaus.apiservice.SsdutEmpInfoService;
import com.klaus.bean.SsdutEmpInfo;
import com.klaus.dao.SsdutEmpInfoDAO;
import com.klaus.factory.MyBeansFactory;

public class SsdutEmpInfoServiceImpl implements SsdutEmpInfoService {

	public List<SsdutEmpInfo> getEmpInfoList(int page) {
		// TODO Auto-generated method stub
		
		SsdutEmpInfoDAO ssdutEmpInfoDao=(SsdutEmpInfoDAO)MyBeansFactory.getBeans("ssdutempinfodao");
		
		return ssdutEmpInfoDao.getPageList((page-1)*8);
		
	}

	public String getEmpInfoDetail(int id) {
		// TODO Auto-generated method stub
		
		//String filt="C:/file/"+id+".html";
		
		//System.out.println(filt);
		
		File file=new File("C:/file/"+id+".html");
		
		BufferedReader in=null;
		
		StringBuilder build=new StringBuilder("");
		
		try {
			
			in = new BufferedReader(new InputStreamReader( new FileInputStream(file),"utf-8"));
			
			String line = "";
  
			while ((line = in.readLine()) != null){		     
				   
				build.append(line);
				
				System.out.print(line);
				   
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
		
		
		return build.toString();
		
	}

}
