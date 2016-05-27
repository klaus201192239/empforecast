package com.klaus.workserviceimpl;

import java.io.File;

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

		WorkerDAO service = (WorkerDAO) MyBeansFactory.getBeans("workerdao");

		int flag = service.working(StaticData.StringData.ScoreWorker);

		if (flag == 1) {

			return;

		}

		service.startWork(StaticData.StringData.ScoreWorker);

		for (int i = 0; i < length; i++) {

			String filePath = StaticData.StringData.FileScorePath + fileList[i];

			File file = new File(filePath);

			ExcelDBService scoreExcel = (ExcelDBService) MyBeansFactory.getBeans("scoreexcelDBServiceimpl");
			scoreExcel.saveData(filePath);

			file.delete();

		}

		service.finishWork(StaticData.StringData.ScoreWorker);

	}

}
