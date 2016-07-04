package com.klaus.apiserviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.klaus.apiservice.AbilityService;
import com.klaus.bean.Ability;
import com.klaus.bean.CourseAbility;
import com.klaus.dao.AbilityDAO;
import com.klaus.dao.CourseAbilityDAO;
import com.klaus.dao.CourseDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.TimeUtil;

public class AbilityServiceImpl implements AbilityService {

	public Map<?, ?> addIndex(List<?> jsonIndexes) {

		// [{"name":"AAAA"},{"name":"BBBBBBB"},{"name":"CCCCCC"},{"name":"DDDDDDDD"}
		// ]

		Map<String, String> result = new HashMap<String, String>();

		try {

			for (int i = 0; i < jsonIndexes.size(); i++) {

				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) jsonIndexes.get(i);

				String name = map.get("name");

				AbilityDAO abilityService = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

				String tag = abilityService.getIdByName(name);

				if (tag == null) {

					Ability ability = new Ability();
					ability.setId(TimeUtil.getObjectId());
					ability.setName(name);

					abilityService.insertAbility(ability);

				}

			}

			result.put("state", "ok");

		} catch (Exception e) {

			result.put("state", "wrong");

		}

		return result;
	}

	public Map<?, ?> addCourseAbility(final List<?> jsonMapping) {

		/*
		   [
 	      {"courseid":"AAAA","ability":[
		                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
		                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
		                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
		                                     .............................
		                                   ]
		       },
		      {"courseid":"BBBB","ability":[
			                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
			                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
			                                     {"abilityid":"BBBBBBB","mapping":"CCCCCC"},
			                                     .............................
			                               ]
			  },
		   ................................
		    ]
		*/   

		
		Map<String, String> result = new HashMap<String, String>();
		//Map<String, Double> scoreList = new HashMap<String,Double>();
		List<CourseAbility> list=new ArrayList<CourseAbility>();

		
		try {

			CourseDAO courseService = (CourseDAO) MyBeansFactory.getBeans("coursedao");
			AbilityDAO abilityService = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");
			CourseAbilityDAO courseAbilityService = (CourseAbilityDAO) MyBeansFactory.getBeans("courseabilitydao");
			
			
			for (int i = 0; i < jsonMapping.size(); i++) {

				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) jsonMapping.get(i);
				
				String courseId=map.get("courseid").toString();

				int courseTag = courseService.getCourseCount(courseId);

				if (courseTag == 0) {

				} else {
					
					List<?> li=(List<?>)map.get("ability");
					
					for(int j=0;j<li.size();j++){
						
						@SuppressWarnings("unchecked")
						Map<String, String> mapmap = (Map<String, String>) li.get(j);
						
						int abilityTag = abilityService.getAbilityCount(mapmap.get("abilityid"));
						
						if (0 == abilityTag) {

						} else {
							
							String a = courseId;
							String b = mapmap.get("abilityid");
							
							String courseAbilityTag = courseAbilityService.getId(a, b);

							if (courseAbilityTag == null) {

								double temp=Double.parseDouble(mapmap.get("score"));
						
								String id=TimeUtil.getObjectId();

								CourseAbility ca = new CourseAbility();
								ca.setId(id);
								ca.setCourseId(courseId);
								ca.setAbilityId(mapmap.get("abilityid"));
								ca.setScore(temp);

								list.add(ca);
								
							/*	Double d=scoreList.get(courseId);
								
								if(d==null){
														
									scoreList.put(courseId, temp);
									
								}else{
				
									d=d+temp;
									scoreList.put(courseId, d);
									
								}*/

							}
							
						}
						
					}
					
				}
			}
			
			
			
			
			for(int j=0;j<list.size();j++){

				CourseAbility ca=list.get(j);
				
				//double sum=scoreList.get(ca.getCourseId());
				
				//double x=ca.getScore();
				
				//ca.setScore(x/sum);
				
				courseAbilityService.insertCourseAbility(ca);

				courseService.deleteCourseById(ca.getCourseId());
				
			}	

			result.put("state", "ok");
			
		} catch (Exception e) {

			e.printStackTrace();

			result.put("state", "wrong");
		}

		return result;

	}

	public List<?> getAbilityList() {

		AbilityDAO abilityDao = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

		List<Ability> listB = abilityDao.getAbilityInfo();

		return listB;
	}

}
