package com.klaus.workserviceimpl;

import java.util.List;

import com.kaus.ssdutinfo.SsdutEmpDetails;
import com.kaus.ssdutinfo.SsdutEmpPaser;
import com.klaus.bean.SsdutEmpInfo;
import com.klaus.dao.SsdutEmpInfoDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.workservice.WorkerService;

public class SsdutEmpWorkerServiceImpl implements WorkerService {
	
	
	public static void main(String[] args) {
		
		SsdutEmpWorkerServiceImpl SsdutEmpWorkerServiceImpl=new SsdutEmpWorkerServiceImpl();
		
		SsdutEmpWorkerServiceImpl.start();
		
	}
	

	public void start() {
		// TODO Auto-generated method stub
		
		try{
			
			SsdutEmpDetails details=new SsdutEmpDetails();
			
			SsdutEmpPaser paser=new SsdutEmpPaser(1040);
			
			List<SsdutEmpInfo> list=paser.infoList();
			
			
			SsdutEmpInfoDAO ssdutEmpInfoDao=(SsdutEmpInfoDAO)MyBeansFactory.getBeans("ssdutempinfodao");
			
			if(null!=list&&list.size()!=0){
				
				
				//System.out.println("111111111111");
				
				for(int i=0;i<list.size();i++){
					
					ssdutEmpInfoDao.insertEmpInfo(list.get(i));
					
					details.buildHtml(list.get(i).getHtmlurl(), list.get(i).getId(), list.get(i).getTitle());
					
					
				}
				
			}
			
			list=null;
			
			paser.settClassId(1041);
			
			list=paser.infoList();
			
			if(null!=list&&list.size()!=0){
				
				for(int i=0;i<list.size();i++){

					//SsdutEmpInfoDAO ssdutEmpInfoDao=(SsdutEmpInfoDAO)MyBeansFactory.getBeans("ssdutempinfodao");
					
					ssdutEmpInfoDao.insertEmpInfo(list.get(i));
					
					details.buildHtml(list.get(i).getHtmlurl(), list.get(i).getId(), list.get(i).getTitle());
					
				}
				
			}
			
			
			list=null;
			
			paser.settClassId(1042);
			
			list=paser.infoList();
			
			if(null!=list&&list.size()!=0){
				
				//System.out.println("333333333333");
				
				for(int i=0;i<list.size();i++){

					//SsdutEmpInfoDAO ssdutEmpInfoDao=(SsdutEmpInfoDAO)MyBeansFactory.getBeans("ssdutempinfodao");
					
					ssdutEmpInfoDao.insertEmpInfo(list.get(i));
					
					
					details.buildHtml(list.get(i).getHtmlurl(), list.get(i).getId(), list.get(i).getTitle());
					
				}
				
			}
			
			
		}catch(Exception e){
			
			
			e.printStackTrace();
			
		}
		
		
		
	}

}
