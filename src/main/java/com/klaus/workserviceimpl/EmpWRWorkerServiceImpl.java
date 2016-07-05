package com.klaus.workserviceimpl;

import java.io.File;

import com.klaus.dao.FileUploadStatementDAO;
import com.klaus.dao.WorkerDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.workservice.ExcelDBService;
import com.klaus.workservice.WorkerService;

public class EmpWRWorkerServiceImpl implements WorkerService {

	public void start() {
		// TODO Auto-generated method stub
		
		System.out.println("-------EmpWRWorkerServiceImpl设定要指定任务--------");
		
		
		
		File root = new File(StaticData.StringData.FileJobPath);

		String[] fileList = root.list();

		int length = fileList.length;

		if (length == 0) {

			return;

		}
		
		System.out.println("1111111111111111");
		
		
		
		FileUploadStatementDAO fileUploadStatementDao=(FileUploadStatementDAO)MyBeansFactory.getBeans("fileuploadstatementdao");
		
		int count=fileUploadStatementDao.getFilesCount(2);
		
		if(count!=0){
			
			return ;
			
		}
		
		
		System.out.println("222222222222222222");
		
		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.EmpWorker);

		if (flag == 1) {

			return;

		}
		
		System.out.println("3333333333333333");
		
		service.startWork(StaticData.StringData.EmpWorker);

		
		ExcelDBService empExcel = (ExcelDBService) MyBeansFactory.getBeans("empexceldbserviceimpl");
		
		for (int i = 0; i < length; i++) {

			
			System.out.println("55555555555555555555555");
			
			String filePath = StaticData.StringData.FileJobPath + fileList[i];

			File file = new File(filePath);
				
			System.out.println("66666666666666666666");
				
			//	scoreExcel.saveData(filePath);

			empExcel.saveData(filePath);
			
			
			file.delete();
				

			
		}
		
		
		System.out.println("777777777777777777");

		service.finishWork(StaticData.StringData.EmpWorker);
		

	}

}
