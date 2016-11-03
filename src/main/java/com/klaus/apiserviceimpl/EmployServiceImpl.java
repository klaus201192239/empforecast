package com.klaus.apiserviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.klaus.apiservice.EmployService;
import com.klaus.bean.Interview;
import com.klaus.utils.TimeUtil;

public class EmployServiceImpl implements EmployService {

	public String uploadInterview(Map<?, ?> map) {
		// TODO Auto-generated method stub
		
		
		//{
		//  "interview":{"company":"","place":"","time":"","content":""},
		//  "interviewer":[{"stuid":"","stuname":"","note":""},{"stuid":"","stuname":"","note":""}.....]
		//}
		
		
		@SuppressWarnings("unchecked")
		Map<String,String> interview=(Map<String, String>) map.get("interview");
		String company=interview.get("company");
		String place=interview.get("place");
		String time=interview.get("time");
		String content=interview.get("content");
		
		SimpleDateFormat dateForm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ptime=dateForm.format(new Date());
		
		Interview in=new Interview();
		in.setId(TimeUtil.getObjectId());
		in.setCompany(company);
		in.setContent(content);
		in.setPlace(place);
		in.setPtime(ptime);
		in.setTime(time);
		
		
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list=(List<Map<String,String>>)map.get("interview");
		
		for(int i=0;i<list.size();i++){
			
			//Map<String,String> mapViewer=list.get(i);
			
			
			
		}
		
		
		return null;
	}

}
