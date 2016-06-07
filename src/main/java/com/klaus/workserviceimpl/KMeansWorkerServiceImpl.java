package com.klaus.workserviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.klaus.bean.Ability;
import com.klaus.bean.Means;
import com.klaus.bean.StudentAbility;
import com.klaus.dao.AbilityDAO;
import com.klaus.dao.MeansDAO;
import com.klaus.dao.StudentAbilityDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.Kmeans;
import com.klaus.utils.TimeUtil;
import com.klaus.workservice.WorkerService;

public class KMeansWorkerServiceImpl implements WorkerService {

	private List<StudentAbility> list=new ArrayList<StudentAbility>();
	
	Map<String, String> abilityMap = new HashMap<String, String>();
	
	public void start() {
		
		MeansDAO meansDao=(MeansDAO)MyBeansFactory.getBeans("meansdao");
		
		meansDao.clearAll();
		
		getAbilityInfo();
		
		initData();
		
		initAbilityAMean();
		
	}
	
	private void initData(){
		
		StudentAbilityDAO studentAbilityDao=(StudentAbilityDAO )MyBeansFactory.getBeans("studentabilitydao");
		
		list=studentAbilityDao.getAllInfo();
		
	}
	
	private void initAbilityAMean(){
		
		int len=list.size();
		
		double[] data=new double[len];
		
		for(int i=0;i<len;i++){
		
			data[i]=list.get(i).getAbilityA();
			
		}
		
		Kmeans kmeans=new Kmeans(data);
		
		List<Map<String,Double>> li= kmeans.classifyData();
		
		MeansDAO meansDao=(MeansDAO)MyBeansFactory.getBeans("meansdao");
		
		for(int j=0;j<li.size();j++){
			
			Means mean=new Means();
			mean.setId(TimeUtil.getObjectId());
			mean.setAbilityId(abilityMap.get("abilityA"));
			mean.setRealValue(li.get(j).get("maxk"));
			mean.setMeanValue(li.get(j).get("meank"));
			
			
			Means mean1=new Means();
			mean1.setId(TimeUtil.getObjectId());
			mean1.setAbilityId(abilityMap.get("abilityA"));
			mean1.setRealValue(li.get(j).get("mink"));
			mean1.setMeanValue(li.get(j).get("meank"));
			
			meansDao.insertKMeans(mean);
			meansDao.insertKMeans(mean1);
			
		}
		
	}
	
	private void getAbilityInfo() {

		AbilityDAO abilityDao = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

		List<Ability> list = abilityDao.getAllInfo();

		for (int i = 0; i < list.size(); i++) {

			abilityMap.put(list.get(i).getName(),list.get(i).getId());

		}

	}


}
