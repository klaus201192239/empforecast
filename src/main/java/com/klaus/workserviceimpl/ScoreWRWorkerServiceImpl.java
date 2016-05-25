package com.klaus.workserviceimpl;

import java.io.File;

import com.klaus.dao.WorkerDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.workservice.WorkerService;

public class ScoreWRWorkerServiceImpl implements WorkerService {

	public void start() {
		// TODO Auto-generated method stub

		System.out.println("-------ScoreWRWorkerServiceImpl设定要指定任务--------");     
		
		
		File root = new File(StaticData.StringData.FileScorePath);
		
		String [] fileList= root.list();
		
		int length=fileList.length;
		
		
		System.out.println("AAAAAAAAAAAAAAA");
		
		if(length==0){
			
			System.out.println("BBBBBBBBBBBBBB");
		
			return ;
			
		}
		
		System.out.println("CCCCCCCCCCCCCCCCC");
		
		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.ScoreWorker);
		
		System.out.println("DDDDDDDDDDDDDDDDDD");
		
		if(flag==1){
			
			return ;
			
		}
		
		System.out.println("EEEEEEEEEEEEEEEEEEE");
		
		service.startWork(StaticData.StringData.ScoreWorker);
		
		
		System.out.println("FFFFFFFFFFFFFFFFFFF");
		
		for(int i=0;i<length;i++){
			
			File file=new File(StaticData.StringData.FileScorePath+fileList[i]);
			
			
			//do the input the data to the databse
			
			
			file.delete();
			
		}
		
		System.out.println("GGGGGGGGGGGGGGGGGGGG");
		
		service.finishWork(StaticData.StringData.ScoreWorker);
		
		
		
	}

}
