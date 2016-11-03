package com.klaus.test;

import java.util.List;

import com.klaus.bean.SsdutEmpInfo;
import com.klaus.dao.SsdutEmpInfoDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.workserviceimpl.SsdutEmpWorkerServiceImpl;

public class TestMain {

	public static void main(String[] args) {

		System.out.println("Test Start");
		
		
		
	/*	List<String> cc=new ArrayList<String>();
		
		

		AbilityDAO abilityDao = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

		CourseDAO courseDao = (CourseDAO) MyBeansFactory.getBeans("coursedao");

		CourseAbilityDAO caDao = (CourseAbilityDAO) MyBeansFactory.getBeans("courseabilitydao");

		List<Ability> aList = abilityDao.getAllInfo();
		List<Course> cList = courseDao.getCourse();

		Random ra = new Random();

		for (int i = 0; i < cList.size(); i++) {

			
			System.out.println("course "+i+"  "+cList.get(i)  );
			
			for (int j = 0; j < aList.size(); j++) {

			//	if ((i * j) % 9 != 0 || i == 0 || j == 0) {

				String ssss=cList.get(i).getId();
				
				try{
					
					
					CourseAbility ca = new CourseAbility();

					ca.setId(TimeUtil.getObjectId());
					ca.setCourseId(cList.get(i).getId());
					ca.setAbilityId(aList.get(j).getId());
					ca.setScore(ra.nextDouble() * (1) + 0);

					//System.out.println(ca.getScore());

					caDao.insertCourseAbility(ca);
					
					
				}catch(Exception e){
					
					cc.add(ssss);
					
				}


			}

		}
		
		
		
		for (int i = 0; i < cc.size(); i++) {
			
			System.out.println(cc.get(i));
			
		}*/

		// WorkerService worker=(WorkerService)
		// MyBeansFactory.getBeans("scorewrworker");
		// worker.start();

		// System.out.println("insert into studentabilityall(id,stuid");

		/*
		 * for (int i = 0; i < 24; i++) {
		 * 
		 * int x = ('a' + i);
		 * 
		 * System.out.println("ae.setAbility"+(char)x+"(stuab.ability"+(char)x+
		 * ");");
		 * 
		 * }
		 * 
		 * for (int i = 0; i < 12; i++) {
		 * 
		 * int x = ('a' + i);
		 * 
		 * System.out.println("ae.setAbilitya"+(char)x+"(stuab.abilitya"+(char)x
		 * +");");
		 * 
		 * }
		 * 
		 */

		// WorkerService worker=(WorkerService)
		// MyBeansFactory.getBeans("mergeworkerserviceimpl");
		// worker.start();

		
		//WorkerService worker=(WorkerService) MyBeansFactory.getBeans("mergeworkerserviceimpl");
        //worker.start();
		
/*
		
		try{
			
			
			
			String s="",re="";
			
			String str="2,2,2,2,2,2,2,2,20000,21,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2.9878,2,2,2,2,2,200,2,2,2,2,2";
			
			Process process = Runtime.getRuntime().exec("python E:/myproject/python/emp/employ/beta1/rf.py "+str);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			while((s=bufferedReader.readLine()) != null){
				
				//System.out.println(s);
			
				re=re+s;
				
			}
			
			process.waitFor();
			
			//System.out.println(re);
			
			ObjectMapper objectMapper=new ObjectMapper(); 
			
			Map<String, String> map = objectMapper.readValue(re, Map.class);
			
			System.out.println("city:"+map.get("city"));
			System.out.println("apartment:"+map.get("apartment"));
			System.out.println("choose:"+map.get("choose"));



			
			
			
		}catch(Exception e){
			
			
		}*/
		
		
	//	SsdutEmpWorkerServiceImpl emp=new SsdutEmpWorkerServiceImpl();
		
	//	emp.start();
		
		SsdutEmpInfoDAO ssdutEmpInfoDao=(SsdutEmpInfoDAO)MyBeansFactory.getBeans("ssdutempinfodao");
		
		List<SsdutEmpInfo> ll=ssdutEmpInfoDao.getPageList(0);
		
		
		for(int i=0;i<ll.size();i++){
			
			
			System.out.println(ll.get(i).getTitle());
			
		}
		
		System.out.println("Test End");

	}

	

}
