package com.klaus.workserviceimpl;

import java.io.File;
import java.util.List;

import com.klaus.dao.FileUploadStatementDAO;
import com.klaus.dao.TempScoreFileDAO;
import com.klaus.dao.WorkerDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.workservice.ExcelDBService;
import com.klaus.workservice.WorkerService;

public class ScoreWRWorkerServiceImpl implements WorkerService {

	public void start() {
		// TODO Auto-generated method stub

		System.out.println("-------ScoreWRWorkerServiceImpl设定要指定任务--------");

		File root = new File(StaticData.StringData.FileScorePath);

		String[] fileList = root.list();

		int length = fileList.length;

		if (length == 0) {

			return;

		}
		
		System.out.println("1111111111111111");
		
		
		FileUploadStatementDAO fileUploadStatementDao=(FileUploadStatementDAO)MyBeansFactory.getBeans("fileuploadstatementdao");
		
		int count=fileUploadStatementDao.getFilesCount(1);
		
		if(count!=0){
			
			return ;
			
		}

		
		System.out.println("222222222222222222");
		
		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.ScoreWorker);

		if (flag == 1) {

			return;

		}

		
		System.out.println("3333333333333333");
		
		service.startWork(StaticData.StringData.ScoreWorker);

		
		TempScoreFileDAO tempScoreFileDa=(TempScoreFileDAO)MyBeansFactory.getBeans("tempscorefiledao");
		List<String> files=tempScoreFileDa.getTempFiles();
		
		
		System.out.println("4444444444444444444");
		
		
		
		for (int i = 0; i < length; i++) {

			
			System.out.println("55555555555555555555555");
			
			String filePath = StaticData.StringData.FileScorePath + fileList[i];

			if(files==null||files.size()==0||files.indexOf(fileList[i])==-1){
				
				
				File file = new File(filePath);
				
				System.out.println("66666666666666666666");
				
				//ExcelDBService scoreExcel = (ExcelDBService) MyBeansFactory.getBeans("scoreexcelDBServiceimpl");
				//scoreExcel.saveData(filePath);

				ScoreExcelDBServiceImpl dd=new ScoreExcelDBServiceImpl();
				dd.saveData(filePath);
				
				
				file.delete();
				
			}
			
		}
		
		
		System.out.println("777777777777777777");

		service.finishWork(StaticData.StringData.ScoreWorker);

	}

}
