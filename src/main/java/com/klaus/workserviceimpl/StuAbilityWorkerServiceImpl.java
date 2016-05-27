package com.klaus.workserviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.klaus.bean.Ability;
import com.klaus.bean.CourseAbility;
import com.klaus.bean.StuCourse;
import com.klaus.dao.AbilityDAO;
import com.klaus.dao.CourseAbilityDAO;
import com.klaus.dao.StuCourseDAO;
import com.klaus.dao.WorkerDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.workservice.WorkerService;

public class StuAbilityWorkerServiceImpl implements WorkerService {

	Map<String,String> abilityMap=new HashMap<String,String>();
	
	public void start() {

		//ScoreExcel have not finished ;then we should wait
		if(finishScoreExcel()==1){
			
			return ;
			
		}
		
		
		getAbilityInfo();
		
		StuCourseDAO stuCourse=(StuCourseDAO)MyBeansFactory.getBeans("stucoursedao");
		List<String> students= stuCourse.getStudentCourseTop10();
		
		int tag=1;
		
		while(tag!=0){
			
			if(students.size()==0){
				return ;
			}
			
			for(int i=0;i<students.size();i++){
				
				insertStuAbility(students.get(i));

			}	
			
			
			
			if(students.size()<10){
					
				return ;
				
			}else{

				String studentTop=new String(students.get(students.size()-1));
				
				students.clear();
				
				students= stuCourse.getStudentCourseLimit10(studentTop);
				
			}
			
		}	
		
	}
	
	private int finishScoreExcel(){
		
		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.ScoreWorker);
		
		return flag;
		
	}
	
	
	private void insertStuAbility(String stuid){
		
		
		
		StuCourseDAO stuCourseDao=(StuCourseDAO)MyBeansFactory.getBeans("stucoursedao");
		List<StuCourse> list=stuCourseDao.getStudentCourseByStuId(stuid);
		
		
		CourseAbilityDAO courseAbilityDao=(CourseAbilityDAO)MyBeansFactory.getBeans("courseabilitydao");
		
		double abilityA=0,abilityB=0,abilityC=0,abilityD=0,abilityE=0,abilityF=0;
		
		for(int i=0;i<list.size();i++){
			
			StuCourse stuCourse=list.get(i);
			
			List<CourseAbility> courseabilityList= courseAbilityDao.getMappingByCourseId(stuCourse.getCourseId());
			
			for(int j=0;j<courseabilityList.size();j++){
				
				CourseAbility courseability=courseabilityList.get(j);
				
				String abilityName=abilityMap.get(courseability.getAbilityId());
				
				double score=ScoreTransaction(stuCourse.getScore());
				
				if(abilityName.equals("abilityA")){					
					
					abilityA+=score*courseability.getScore();
					
				}else{
					if(abilityName.equals("abilityB")){					
						
						abilityB+=score*courseability.getScore();
						
					}else{
						
						if(abilityName.equals("abilityC")){					
							
							abilityC=score*courseability.getScore();
							
						}else{
							
							if(abilityName.equals("abilityD")){					
								
								abilityD+=score*courseability.getScore();
								
							}else{
								
								if(abilityName.equals("abilityE")){					
									
									abilityE+=score*courseability.getScore();
									
								}else{
									
									if(abilityName.equals("abilityF")){					
										
										abilityF+=score*courseability.getScore();
										
									}
									
								}
								
							}
							
						}
						
					}
					
				}
				
				
			}
			
		}
		
	}
	
	
	
	private void getAbilityInfo(){		

		AbilityDAO abilityDao=(AbilityDAO)MyBeansFactory.getBeans("abilitydao");
		
		List<Ability> list= abilityDao.getAllInfo();
		
		for(int i=0;i<list.size();i++){
			
			abilityMap.put(list.get(i).getId(), list.get(i).getName());
			
		}
		
	}
	
	private double ScoreTransaction(String score){
		
		if(null==score){
			return 0;
		}
		
		double tempDouble=0.0;
		
		try{
			
			tempDouble=Double.parseDouble(score);
			
		}catch(Exception e){
			
			tempDouble=-1.0;
		}
		
		if(tempDouble==-1.0){
			
			if("通过".equals(score)){
				
				
				
			}else{
				
				if(score.contains("补1")){
					
					
					
				}else{
					
					if(score.contains("补2")){
						
						
						
					}else{
						
						if(score.contains("重1")){
							
							
							
						}else{
							
							if(score.contains("重2")){
								
								
								
							}else{
								
								if(score.contains("取消资格")){
									
									
									
								}else{
									
									if(score.contains("旷")){
										
										
										
									}else{
										
										
										
									}
									
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
			return tempDouble;
			
			
		}else{
			
			return tempDouble;
			
		}
		
	}

	
}
