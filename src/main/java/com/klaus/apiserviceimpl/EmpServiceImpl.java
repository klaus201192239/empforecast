package com.klaus.apiserviceimpl;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.klaus.apiservice.EmpService;
import com.klaus.bean.FileUploadStatement;
import com.klaus.dao.FileUploadStatementDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.utils.TimeUtil;

public class EmpServiceImpl implements EmpService {

	public String uploadExcel(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String result="";
		
		String state = String.valueOf(System.currentTimeMillis());
		String id = TimeUtil.getObjectId();
		
		try {

			FileUploadStatementDAO fileUploadStatementDao = (FileUploadStatementDAO) MyBeansFactory.getBeans("fileuploadstatementdao");
			

			FileUploadStatement fileUploadStatement = new FileUploadStatement();
			fileUploadStatement.setId(id);
			fileUploadStatement.setState(state);
			fileUploadStatement.setTag(2);

			fileUploadStatementDao.insertFileState(fileUploadStatement);

			// 文件上传处理工厂
			FileItemFactory factory = new DiskFileItemFactory();

			// 创建文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 开始解析请求信息
			List<?> items = upload.parseRequest(request);

			Iterator<?> iter = items.iterator();

			while (iter.hasNext()) {

				String fileName = String.valueOf(System.currentTimeMillis()) + ".xls";

				FileItem item = (FileItem) iter.next();				
				
				File file = new File(StaticData.StringData.FileJobPath + fileName);

				item.write(file);
				
			}			
			
			fileUploadStatementDao.deleteFileState(id);
			
			
			result="Files have been uploaded!";

		} catch (Exception e) {

			
			FileUploadStatementDAO fileUploadStatementDao=(FileUploadStatementDAO)MyBeansFactory.getBeans("fileuploadstatementdao");
			fileUploadStatementDao.deleteFileState(id);
			
			result="Something is wrong. Please upload file again!";
			
		}

		
		return result;
	}

}
