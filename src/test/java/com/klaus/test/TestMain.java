package com.klaus.test;

import com.klaus.factory.MyBeansFactory;
import com.klaus.workservice.WorkerService;

public class TestMain {

	public static void main(String[] args) {

		System.out.println("Test Start");

		/*
		 * AbilityDAO
		 * abilityDao=(AbilityDAO)MyBeansFactory.getBeans("abilitydao");
		 * 
		 * CourseDAO courseDao=(CourseDAO)MyBeansFactory.getBeans("coursedao");
		 * 
		 * CourseAbilityDAO
		 * caDao=(CourseAbilityDAO)MyBeansFactory.getBeans("courseabilitydao");
		 * 
		 * 
		 * 
		 * List<Ability> aList=abilityDao.getAllInfo(); List<Course>
		 * cList=courseDao.getCoursesTemp();
		 * 
		 * 
		 * Random ra =new Random();
		 * 
		 * for(int i=0;i<cList.size();i++){
		 * 
		 * 
		 * for(int j=0;j<aList.size();j++){
		 * 
		 * 
		 * if((i*j)%9!=0||i==0||j==0){
		 * 
		 * CourseAbility ca=new CourseAbility();
		 * 
		 * ca.setId(TimeUtil.getObjectId());
		 * ca.setCourseId(cList.get(i).getId());
		 * ca.setAbilityId(aList.get(j).getId());
		 * ca.setScore(ra.nextDouble()*(1)+0);
		 * 
		 * System.out.println(ca.getScore());
		 * 
		 * caDao.insertCourseAbility(ca);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 */

		// WorkerService worker=(WorkerService)
		// MyBeansFactory.getBeans("scorewrworker");
		// worker.start();

//		System.out.println("insert into studentabilityall(id,stuid");

	/*	for (int i = 0; i < 24; i++) {

			int x = ('a' + i);

			System.out.println("`ability"+(char)x+"` double NOT NULL,");

		}

		for (int i = 0; i < 12; i++) {

			int x = ('a' + i);

			System.out.println("`abilitya"+(char)x+"` double NOT NULL,");

		}


		*/
		

		WorkerService worker=(WorkerService) MyBeansFactory.getBeans("stuabilityworker");
        worker.start();

		System.out.println("Test End");

	}

}
