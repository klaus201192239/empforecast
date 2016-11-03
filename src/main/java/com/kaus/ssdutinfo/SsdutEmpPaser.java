package com.kaus.ssdutinfo;

import java.util.ArrayList;
import java.util.List;

import com.klaus.bean.SsdutEmpInfo;
import com.klaus.dao.SsdutEmpInfoDAO;
import com.klaus.factory.MyBeansFactory;

public class SsdutEmpPaser {
	
	private int classId;
	
	public SsdutEmpPaser(int classId){
		
		this.classId=classId;
		
	}

	public void settClassId(int classId) {
		
		this.classId=classId;
		
	}
	
	public int getClassId() {
		return classId;
	}

	
	public List<SsdutEmpInfo> infoList() throws Exception{
		
		List<SsdutEmpInfo> reList=new ArrayList<SsdutEmpInfo>();
		
		
		SsdutEmpList list=new SsdutEmpList(classId);
		
		list.excutor();
		
		
		
		int max=list.getMaxIndex();
		
		int currentIndex=getCurrentIndex();
		
		SsdutEmpSingle single=null;
		
		while(max!=currentIndex){
			
			single=new SsdutEmpSingle(classId, max);
			
			single.excutor();
			
			int temp=max;
			
			///System.out.println("bbbbbbbb");
			
			SsdutEmpInfo info=new SsdutEmpInfo();
			info.setCalory(classId);
			info.setHtmlurl(single.getUrl());
			info.setId(temp);
			info.setPtime(single.getTime());
			info.setTitle(single.getTitle());
			
			max=single.getNextIndex();
			
			//System.out.println("bbbbbbbb");
			
			
			reList.add(info);
			
		}
		
		return reList;
		
		
	}
	
	private int getCurrentIndex(){
		
		
		SsdutEmpInfoDAO ssdutEmpInfoDao=(SsdutEmpInfoDAO)MyBeansFactory.getBeans("ssdutempinfodao");
		
		return ssdutEmpInfoDao.getCurrentIndex(classId);
		
	}
	
	
	
}
