package com.klaus.apiserviceimpl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.klaus.apiservice.EmpForecastService;
import com.klaus.bean.Forecast;
import com.klaus.bean.ProductState;
import com.klaus.dao.ProductStateDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.utils.TimeUtil;

public class EmpForecastServiceImpl implements EmpForecastService {

	public Map<String, String> uploadExcel(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		String result="wrong";

		try {

			
			String pid=TimeUtil.getObjectId();
			
			int tag=0;
			
			
			// 文件上传处理工厂
			FileItemFactory factory = new DiskFileItemFactory();

			// 创建文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 开始解析请求信息
			List<?> items = null;
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			Iterator<?> iter = items.iterator();
			while (iter.hasNext()) {

				FileItem item = (FileItem) iter.next();

				File file = new File(StaticData.StringData.ForecastScorePath +pid+String.valueOf(tag)+".xls");

				item.write(file);
				
				tag++;

			}
			
			result=pid;
			
			
			ProductState ps=new ProductState();
			ps.setId(result);
			ps.setState(0);
			
			ProductStateDAO productStateDao=(ProductStateDAO)MyBeansFactory.getBeans("productstatedao");
			
			productStateDao.buildState(ps);
			

		} catch (Exception ex) {
			
			result="wrong";
		}
		
		
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("state", result);
		
		return map;
				
	}

	public Map<String, String> checkState(String productId) {
		// TODO Auto-generated method stub
		
		Map<String,String> map=new HashMap<String,String>(); 
		
		ProductStateDAO productStateDao=(ProductStateDAO)MyBeansFactory.getBeans("productstatedao");
		
		ProductState ps= productStateDao.findOneProduct(productId);
		
		if(ps==null){
			
			map.put("state", "nothing");
			
			return map;
			
		}
		
		map.put(ps.getId(), String.valueOf(ps.getState()));
		
		return map;
	}

	public List<Forecast> getResult(String productId) {
		// TODO Auto-generated method stub
		return null;
	}
}
