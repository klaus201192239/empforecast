package com.klaus.workserviceimpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.klaus.bean.Ability;
import com.klaus.bean.AbilityStander;
import com.klaus.bean.CourseAbility;
import com.klaus.bean.StuCourse;
import com.klaus.bean.StudentAbility;
import com.klaus.dao.AbilityDAO;
import com.klaus.dao.CourseAbilityDAO;
import com.klaus.dao.StuCourseDAO;
import com.klaus.dao.StudentAbilityDAO;
import com.klaus.dao.WorkerDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.workservice.WorkerService;

public class StuAbilityWorkerServiceImpl implements WorkerService {

	Map<String, String> abilityMap = new HashMap<String, String>();

	public void start() {

		
		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.StuAbilityWorker);

		if (flag == 1) {

			return;

		}
		
		
		// ScoreExcel have not finished ;then we should wait
		if (finishScoreExcel() == 1) {

			return;

		}

		
		System.out.println("1111111111111111");
		
		service.startWork(StaticData.StringData.StuAbilityWorker);
		
		//List<StudentAbility> list = new ArrayList<StudentAbility>();

		getAbilityInfo();

		StuCourseDAO stuCourse = (StuCourseDAO) MyBeansFactory.getBeans("stucoursedao");
		List<String> students = stuCourse.getStudentCourseTop10();

		int tag = 1;

		
		System.out.println("22222222222222222");
		
		
		while (tag != 0) {

			
			
			System.out.println("33333333333333333");
			
			if (students==null||students.size() == 0) {
				
				service.finishWork(StaticData.StringData.StuAbilityWorker);
				
				return;
			}

			for (int i = 0; i < students.size(); i++) {

				
				System.out.println("insert");
				
				insertStuAbility(students.get(i));

			}
			
			
			
			System.out.println("4444444444444444");

			if (students.size() < 10) {

				service.finishWork(StaticData.StringData.StuAbilityWorker);
				
				return;

			} else {

				String studentTop = new String(students.get(students.size() - 1));

				students.clear();
				
				
				System.out.println("55555555555555");
				

				students = stuCourse.getStudentCourseLimit10(studentTop);

			}

		}

	}


	private int finishScoreExcel() {

		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.ScoreWorker);

		return flag;

	}

	private void insertStuAbility(String stuid) {
		
		
		List<AbilityStander> listAbilityStander=new ArrayList<AbilityStander>();
		double sum=0.0;
		

		StuCourseDAO stuCourseDao = (StuCourseDAO) MyBeansFactory.getBeans("stucoursedao");
		List<StuCourse> list = stuCourseDao.getStudentCourseByStuId(stuid);

		CourseAbilityDAO courseAbilityDao = (CourseAbilityDAO) MyBeansFactory.getBeans("courseabilitydao");
		

		//double abilityA = 0, abilityB = 0, abilityC = 0, abilityD = 0, abilityE = 0, abilityF = 0;

		for (int i = 0; i < list.size(); i++) {

			StuCourse stuCourse = list.get(i);

			List<CourseAbility> courseabilityList = courseAbilityDao.getMappingByCourseId(stuCourse.getCourseId());

			for (int j = 0; j < courseabilityList.size(); j++) {

				CourseAbility courseability = courseabilityList.get(j);

				String abilityName = abilityMap.get(courseability.getAbilityId());

				double score = ScoreTransaction(stuCourse.getScore());

				
				sum=sum+courseability.getScore();
				

				AbilityStander abilityStander=new AbilityStander(abilityName,score,courseability.getScore());
				listAbilityStander.add(abilityStander);

			}

		}
		

		try {
			
			
			System.out.println("aa11111111111111");
			
			StudentAbility abi = new StudentAbility();
			abi.setStuId(stuid);

			Class<?> a =abi.getClass();		
			
			
			System.out.println("aa222222222222222222");
			
			for (int j = 0; j < listAbilityStander.size(); j++) {
				
				AbilityStander ab=listAbilityStander.get(j);

				double xMapping=ab.getMapping()/sum;
				double xScore=ab.getScore()*xMapping;
				
				
			//	System.out.println("aa3333333333333");
				
				Field f = a.getField(ab.getAbility());
				
				Double tempX = (Double)f.get(abi);
				
				f.set(abi, new Double(tempX+xScore));

				
			//	System.out.println("aa444444444444444444");
				
			}
			
			
			
			
			StudentAbilityDAO studentAbilityDao = (StudentAbilityDAO) MyBeansFactory.getBeans("studentabilitydao");
			
			
			
			System.out.println("aa555555555555555555");
			
			studentAbilityDao.insertStudentAbility(abi);
			studentAbilityDao.insertStudentAbilityAll(abi);
			
			
			System.out.println("right ;"+stuid);
			
			
			//stuCourseDao.deleteStudentCourseByStuId(stuid);
			
			
		} catch (Exception e) {
			
			System.out.println("wrong :  "+stuid);
			
		}

	}

	private void getAbilityInfo() {

		AbilityDAO abilityDao = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

		List<Ability> list = abilityDao.getAllInfo();

		for (int i = 0; i < list.size(); i++) {

			abilityMap.put(list.get(i).getId(), list.get(i).getName());

		}

	}

	private double ScoreTransaction(String score) {

		if (null == score) {

			return 0;

		}

		double tempDouble = 0.0;

		try {

			tempDouble = Double.parseDouble(score);

		} catch (Exception e) {

			tempDouble = -1.0;
		}

		if (tempDouble == -1.0) {

			if ("通过".equals(score)) {

				return 60.0;

			} else {

				if (score.contains("补1")) {

					String s = score.replace("补1", "");

					return Double.parseDouble(s) * 0.8;

				} else {

					if (score.contains("补2")) {

						String s = score.replace("补2", "");

						return Double.parseDouble(s) * 0.6;

					} else {

						if (score.contains("重1")) {

							String s = score.replace("重1", "");

							return Double.parseDouble(s) * 0.4;

						} else {

							if (score.contains("重2")) {

								String s = score.replace("重2", "");

								return Double.parseDouble(s) * 0.2;

							} else {

								if (score.contains("取消资格")) {

									return 0.0;

								} else {

									if (score.contains("旷")) {

										return 0.0;

									} else {

									}

								}

							}

						}

					}

				}

			}

			return tempDouble;

		} else {

			return tempDouble;

		}

	}

}
