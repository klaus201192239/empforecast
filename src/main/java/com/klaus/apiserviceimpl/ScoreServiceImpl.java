package com.klaus.apiserviceimpl;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.klaus.apiservice.ScoreService;
import com.klaus.bean.Course;
import com.klaus.dao.CourseDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.StaticData;
import com.klaus.utils.TimeUtil;

public class ScoreServiceImpl implements ScoreService {

	private int tag=0;
	
	public String uploadExcel(HttpServletRequest req) {

		String result="no";

		try {

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
				
				result=paserExcel(item);

				result=saveToLocal(item);

			}

		} catch (Exception ex) {
			return result;
		}

		
		if(tag==1&&result.equals("ok")){
			
			return "HaveCourseAbilityNoMapping";
			
		}
		
		return result;
	}

	private String saveToLocal(FileItem item) {

		try {

			String fileName = item.getName();
			int index = fileName.lastIndexOf("\\");
			fileName = fileName.substring(index + 1);

			File file = new File(StaticData.StringData.FileScorePath + fileName);

			item.write(file);

		} catch (Exception e) {

			return "no";

		}

		return "yes";
	}

	private String paserExcel(FileItem item) {

		try {

			InputStream IS = item.getInputStream();
			Workbook workbook = WorkbookFactory.create(IS);

			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量

			// 遍历每个Sheet
			for (int s = 0; s < sheetCount; s++) {

				Sheet sheet = workbook.getSheetAt(s);

				if (sheet != null) {

					int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数

					// 遍历每一行
					for (int r = 0; r < rowCount; r++) {						
						
						if(r>0&&r<4){
							
							Row row = sheet.getRow(r);

							if (row != null) {

								int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数

								// 遍历每一列
								for (int c = 0; c < cellCount; c++) {

									Cell cell = row.getCell(c);

									if (cell != null) {

										int cellType = cell.getCellType();

										String cellValue = getCellValues(cellType, cell).replaceAll("\\s*", "");

										System.out.print(cellValue + "(" + r + "," + c + ")");
										
										getCousrInfo(r,c,cellValue) ;

									}

								}

								System.out.println();

							}
							
						}

					}

				}

			}

		} catch (Exception e) {

			return "no";

		}
		
		
		CourseDAO courseDao=(CourseDAO)MyBeansFactory.getBeans("coursedao");
		
		for(int i=0;i<listCourse.size();i++){
			
			Course cc=listCourse.get(i) ;

		//	System.out.println(cc.getId());
		//	System.out.println(cc.getCourseName());
		//	System.out.println(cc.getCourseGrade());
		//	System.out.println(cc.getCourseId());

			String id=courseDao.getCourseId(cc.getCourseId());
			
			if(null==id){				
				
				cc.setId(TimeUtil.getObjectId());
				
				courseDao.insertCourse(cc);
				courseDao.insertCourseTemp(cc);
				
				tag=1;
				
			}
			
			
		}		

		return "yes";
	}

	private String getCellValues(int cellType, Cell cell) {

		String cellValue = "";

		switch (cellType) {
		case Cell.CELL_TYPE_STRING: // 文本
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC: // 数字、日期
			if (DateUtil.isCellDateFormatted(cell)) {

				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

				cellValue = fmt.format(cell.getDateCellValue()); // 日期型
			} else {
				cellValue = String.valueOf(cell.getNumericCellValue()); // 数字
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN: // 布尔型
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;

		case Cell.CELL_TYPE_BLANK: // 空白
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_ERROR: // 错误
			cellValue = "错误";
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = "错误";
			break;
		default:
			cellValue = "错误";
		}

		return cellValue;
	}
	
	private List<Course> listCourse = new ArrayList<Course>();
	
	private void getCousrInfo(int r, int c, String cellValue) {

		if (r == 1 && c >= 5) {

			if (cellValue.length() != 0) {

				Course bean = new Course();
				bean.setId(c + "");
				bean.setCourseId(cellValue);

				listCourse.add(bean);

			}

		}

		if (r == 2 && c >= 5) {

			if (cellValue.length() != 0) {
				for (int i = 0; i < listCourse.size(); i++) {

					Course beanOld = listCourse.get(i);

					if (beanOld.getId().equals(c + "")) {

						beanOld.setCourseName(cellValue);

					}

				}
			}
		}

		if (r == 3 && c >= 5) {

			if (cellValue.length() != 0) {

				for (int i = 0; i < listCourse.size(); i++) {

					Course beanOld = listCourse.get(i);

					if (beanOld.getId().equals(c + "")) {

						beanOld.setCourseGrade(Double.parseDouble(cellValue));

					}

				}

			}
		}

	}

}
