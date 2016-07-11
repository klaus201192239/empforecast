package com.klaus.workserviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.klaus.bean.EmpInfo;
import com.klaus.dao.EmpInfoDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.SecurityCoder;
import com.klaus.utils.TimeUtil;
import com.klaus.workservice.ExcelDBService;

public class EmpExcelDBServiceImpl implements ExcelDBService {

	private Workbook workbook;
	
	public void saveData(String filePath) {
		// TODO Auto-generated method stub

		try {

			File excelFile = new File(filePath); // 创建文件对象
			FileInputStream is = new FileInputStream(excelFile); // 文件流
			workbook = WorkbookFactory.create(is); // 这种方式 Excel 2003/2007/2010
													// 都是可以处理的

			saveExcel();

		} catch (Exception e) {

		}
		
	}

	private void saveExcel() {
		
		
		
		int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量

		EmpInfoDAO empInfoDao=(EmpInfoDAO)MyBeansFactory.getBeans("empinfodao");
		
		// 遍历每个Sheet
		for (int s = 0; s < sheetCount; s++) {

			Sheet sheet = workbook.getSheetAt(s);

			if (sheet != null) {

				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数

				// 遍历每一行
				for (int r = 1; r < rowCount; r++) {

					Row row = sheet.getRow(r);

					if (row != null) {						
						
						EmpInfo info=new EmpInfo();
						info.setId(TimeUtil.getObjectId());
						
						
						int cellType =0;//= cell.getCellType();

						Cell cell = row.getCell(2);///stuid
						cellType = cell.getCellType();
						String cellValue2 = getCellValues(cellType, cell).replaceAll("\\s*", "");
						info.setStuId(SecurityCoder.encryptSHA(cellValue2));
						

						cell = row.getCell(3);///stuname
						cellType = cell.getCellType();
						String cellValue3 = getCellValues(cellType, cell).replaceAll("\\s*", "");						
						info.setStuName(SecurityCoder.encryptSHA(cellValue3));
						
						
						
						cell= row.getCell(11);//choose
						cellType = cell.getCellType();
						String cellValue11 = getCellValues(cellType, cell).replaceAll("\\s*", "");
						info.setChoose(cellValue11);
						
						
						cell= row.getCell(13);//city
						cellType = cell.getCellType();
						String cellValue13 = getCellValues(cellType, cell).replaceAll("\\s*", "");
						info.setCity(cellValue13);
						
						
						
						cell = row.getCell(14);//apartment
						cellType = cell.getCellType();
						String cellValue14 = getCellValues(cellType, cell).replaceAll("\\s*", "");
						info.setApartment(cellValue14);						
						
						
						
						EmpInfo empp= empInfoDao.getInfobyStuId(SecurityCoder.encryptSHA(cellValue2));
						
						if(empp==null){
							
							empInfoDao.insertAllEmpInfo(info);
							empInfoDao.insertEmpInfo(info);
							
						}
						
						System.out.println(info.getId()+"  "+info.getChoose());
						
					}
				}
			}
		}
		
		
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
				//cellValue = String.valueOf(cell.getNumericCellValue()); // 数字
				
				
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

				cellValue = fmt.format(cell.getDateCellValue()); // 日期型
				
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

		if(cellValue.length()==0||cellValue==null||"----------".equals(cellValue)){
			return "其他";
		}
		
		return cellValue;
	}
	
}
