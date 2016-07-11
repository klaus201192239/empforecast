package com.klaus.workserviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.klaus.bean.AbilityEmp;
import com.klaus.bean.EmpInfo;
import com.klaus.bean.StudentAbility;
import com.klaus.dao.AbilityEmpDAO;
import com.klaus.dao.DBCountDAO;
import com.klaus.dao.EmpInfoDAO;
import com.klaus.dao.StudentAbilityDAO;
import com.klaus.dao.WorkerDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.utils.TimeUtil;
import com.klaus.workservice.WorkerService;

public class MergeWorkerServiceImpl implements WorkerService {

	public void start() {
		// TODO Auto-generated method stub

		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.MergeWorker);

		if (flag == 1) {

			return;

		}
		
		System.out.println("1111111111111");
		
		flag = service.working(StaticData.StringData.EmpWorker);

		if (flag == 1) {

			return;

		}
		
		System.out.println("22222222222222");
		
		flag = service.working(StaticData.StringData.StuAbilityWorker);

		if (flag == 1) {

			return;

		}
		
		
		System.out.println("333333333333");
		
		flag=hasChange();
		
		if (flag == 0) {

			return;

		}
		
		
		System.out.println("44444444444444");
		
		service.startWork(StaticData.StringData.MergeWorker);

		System.out.println("55555555555555555");
		
		StudentAbilityDAO stuAbiDao=(StudentAbilityDAO)MyBeansFactory.getBeans("studentabilitydao");
		List<String> listA=stuAbiDao.getAllUpdateStuId();
		
		
		//StudentDAO studao=(StudentDAO)MyBeansFactory.getBeans("studentdao");
		//List<Student> listA=studao.getAllStudentId();
		
		
		EmpInfoDAO empDao=(EmpInfoDAO)MyBeansFactory.getBeans("empinfodao");
		List<String> listB=empDao.getUpdateStuId();
		
		Map<String,Integer> map=new HashMap<String,Integer>();
		
		
		System.out.println("666666666666666666");
		
		//List<String> listStuID=new ArrayList<String>();
		List<String> liststuid=new ArrayList<String>();
		
		
		for(int i=0;i<listB.size();i++){
			
			map.put(listB.get(i), 1);
			
		}
		
		
		int tag=0;
		
		for(int i=0;i<listA.size();i++){

			Integer x=map.put(listA.get(i), 0);

			
			if(x!=null){
				
				System.out.println("BBBBBBBBBBBB"+tag);
				
				tag++;
				
				liststuid.add(listA.get(i));
				//listStuID.add(listA.get(i).getId());
				
				//list.add(listA.get(i).getId());
			}
			
		}
		
		

		//StudentAbilityDAO stuAbiDao=(StudentAbilityDAO)MyBeansFactory.getBeans("studentabilitydao");
		
		
		if(liststuid.size()>0){
			
			
			AbilityEmpDAO aeDao=(AbilityEmpDAO)MyBeansFactory.getBeans("abilityempdao");
			
			for(int i=0;i<liststuid.size();i++){

				String ss=liststuid.get(i);
				
				
				try{
					
					StudentAbility stuab= stuAbiDao.getInfoByStuId(liststuid.get(i));
					
					
					
					System.out.println("wrong33333333333333:   ");
					
					EmpInfo empInfo=empDao.getInfobyStuId(liststuid.get(i));
					
					
					System.out.println("wrong:000000000000000   ");
					
					AbilityEmp ae=new AbilityEmp();
					
					ae.setId(TimeUtil.getObjectId());
					ae.setStuId(liststuid.get(i));
					ae.setChoose(empInfo.getChoose());
					ae.setApartment(empInfo.getApartment());
					ae.setCity(empInfo.getCity());
					
					
					
					System.out.println("wrong:   ");
					
					ae.setAbilitya(stuab.abilitya);
					ae.setAbilityb(stuab.abilityb);
					ae.setAbilityc(stuab.abilityc);
					ae.setAbilityd(stuab.abilityd);
					ae.setAbilitye(stuab.abilitye);
					ae.setAbilityf(stuab.abilityf);
					ae.setAbilityg(stuab.abilityg);
					ae.setAbilityh(stuab.abilityh);
					ae.setAbilityi(stuab.abilityi);
					ae.setAbilityj(stuab.abilityj);
					ae.setAbilityk(stuab.abilityk);
					
					
					System.out.println("wrong111111111:   ");
					
					ae.setAbilityl(stuab.abilityl);
					ae.setAbilitym(stuab.abilitym);
					ae.setAbilityn(stuab.abilityn);
					ae.setAbilityo(stuab.abilityo);
					ae.setAbilityp(stuab.abilityp);
					ae.setAbilityq(stuab.abilityq);
					ae.setAbilityr(stuab.abilityr);
					ae.setAbilitys(stuab.abilitys);
					ae.setAbilityt(stuab.abilityt);
					ae.setAbilityu(stuab.abilityu);
					ae.setAbilityv(stuab.abilityv);
					ae.setAbilityw(stuab.abilityw);
					ae.setAbilityx(stuab.abilityx);
					ae.setAbilityaa(stuab.abilityaa);
					ae.setAbilityab(stuab.abilityab);
					ae.setAbilityac(stuab.abilityac);
					
					
					
					System.out.println("wrong:2222222222222   ");
					
					ae.setAbilityad(stuab.abilityad);
					ae.setAbilityae(stuab.abilityae);
					ae.setAbilityaf(stuab.abilityaf);
					ae.setAbilityag(stuab.abilityag);
					ae.setAbilityah(stuab.abilityah);
					ae.setAbilityai(stuab.abilityai);
					ae.setAbilityaj(stuab.abilityaj);
					ae.setAbilityak(stuab.abilityak);
					ae.setAbilityal(stuab.abilityal);
					
					System.out.println("5555555555555555555");
					
					aeDao.insertInfo(ae);
					
				}catch(Exception e){
					
					
					//e.printStackTrace();
					
					System.out.println("wrong:   "+ss);
				}
				
				
				
				//stuAbiDao.deleteStudentAbilityById(stuab.getId());
				//empDao.deleteEmpById(empInfo.getId());
				
				
			}
			
			
			//DBCountDAO dbCountDao=(DBCountDAO)MyBeansFactory.getBeans("dbcountdao");			
			//dbCountDao.updateCountByType(aeDao.getInfoCount(), 3);
			
		}

		
		service.finishWork(StaticData.StringData.MergeWorker);
		
	}
	
	private int hasChange(){
		
		DBCountDAO dbCountDao=(DBCountDAO)MyBeansFactory.getBeans("dbcountdao");
		int emp0=dbCountDao.getCountByType(2);
		int ability0=dbCountDao.getCountByType(1);
		
		StudentAbilityDAO stuAbiDao=(StudentAbilityDAO)MyBeansFactory.getBeans("studentabilitydao");
		int ability1=stuAbiDao.getUpdateInfoCount();
		
		EmpInfoDAO empDao=(EmpInfoDAO)MyBeansFactory.getBeans("empinfodao");
		int emp1=empDao.getUpdateInfoCount();

		
		if(emp0==emp1&&ability0==ability1){
			
			return 0;
			
		}else{
			
			
			dbCountDao.updateCountByType(ability1, 1);
			dbCountDao.updateCountByType(emp1, 2);
			return 1;
		}
		
	}

}
