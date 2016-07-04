package com.klaus.workserviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.klaus.bean.Ability;
import com.klaus.bean.Course;
import com.klaus.bean.CourseAbility;
import com.klaus.bean.ObjectBean;
import com.klaus.bean.ProductState;
import com.klaus.bean.StudentAbility;
import com.klaus.dao.AbilityDAO;
import com.klaus.dao.CourseAbilityDAO;
import com.klaus.dao.ProductStateDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.SecurityCoder;
import com.klaus.utils.StaticData;
import com.klaus.utils.TimeUtil;
import com.klaus.workservice.WorkerService;

public class ForecastWorkerServiceImpl implements WorkerService {

	private String pId;
	private Workbook workbook;
	private List<Course> listCourse = new ArrayList<Course>();

	public void start() {
		// TODO Auto-generated method stub

		ProductStateDAO productStateDao = (ProductStateDAO) MyBeansFactory.getBeans("productstatedao");

		List<ProductState> list = productStateDao.findEmptyProduct();

		if (list.size() == 0) {

			return;

		}

		pId = list.get(0).getId();

		productStateDao.startProduct(pId);

		File fold = new File(StaticData.StringData.ForecastScorePath);

		String[] fileList = fold.list();

		int len = fileList.length;

		for (int i = 0; i < len; i++) {

			String fileName = fileList[i];

			if (fileName.substring(0, 15).equals(pId)) {

				paserExcel(StaticData.StringData.ForecastScorePath + fileName);

			}

		}

	}

	private void paserExcel(String filePath) {

		try {

			File excelFile = new File(filePath); // 创建文件对象
			FileInputStream is = new FileInputStream(excelFile); // 文件流
			workbook = WorkbookFactory.create(is);

			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量

			// 遍历每个Sheet
			for (int s = 0; s < sheetCount; s++) {

				Sheet sheet = workbook.getSheetAt(s);

				if (sheet != null) {

					int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数

					// 遍历每一行
					for (int r = 0; r < rowCount; r++) {

						Row row = sheet.getRow(r);

						if (row != null) {

							int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数

							// 遍历每一列
							for (int c = 0; c < cellCount; c++) {

								Cell cell = row.getCell(c);

								if (cell != null) {

									int cellType = cell.getCellType();

									String cellValue = getCellValues(cellType, cell).replaceAll("\\s*", "");

									// System.out.print(cellValue +
									// "("+r+","+c+")
									// ");

									getCousrInfo(r, c, cellValue);

									getCourseGrade(r, c, cellValue);

								}

							}

							// System.out.println();

						}

					}

				}
			}

		} catch (Exception e) {

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

	private void getCousrInfo(int r, int c, String cellValue) {

		if (r == 1 && c >= 5) {

			if (cellValue.length() != 0) {

				Course bean = new Course();
				bean.setId(c + "");
				bean.setCourseId(cellValue);
				bean.setCourseName("name");
				bean.setCourseGrade(1.0);

				listCourse.add(bean);

			}

		}
	}

	private String getCourseID(int clounmNumber) {

		for (int i = 0; i < listCourse.size(); i++) {

			Course beanOld = listCourse.get(i);

			if (Integer.parseInt(beanOld.getId()) == clounmNumber) {

				return beanOld.getCourseId();

			}

		}
		return null;

	}

	// private Map<String, String> map = new HashMap<String, String>();
	private List<ObjectBean> listGrade = new ArrayList<ObjectBean>();

	private void getCourseGrade(int r, int c, String cellValue) {

		if (r >= 5) {

			if (c == 1) {

				String str = SecurityCoder.encryptSHA(cellValue);

				// System.out.println(str);

				ObjectBean bean = new ObjectBean("StudentId", str);
				ObjectBean bean1 = new ObjectBean("StudentGrade", cellValue.substring(0, 5).replace(".", ""));

				listGrade.add(bean);
				listGrade.add(bean1);

				// map.put("StudentId", str);

				// map.put("StudentGrade", cellValue.substring(0,
				// 5).replace(".", ""));

			}

			if (c == 4) {

				String str = SecurityCoder.encryptSHA(cellValue);

				ObjectBean bean = new ObjectBean("StudentName", str);
				listGrade.add(bean);

				// map.put("StudentName", str);

			}

			if (c >= 5) {

				String courseID = getCourseID(c);

				if (courseID != null) {

					ObjectBean bean = new ObjectBean("courseID", cellValue);
					listGrade.add(bean);
					// map.put(courseID, cellValue);

				}

				if (listGrade.size() == (listCourse.size() + 3)) {

					// listCourseGrade.add(map);

					getStudentAbility();

					// map = new HashMap<String, String>();
					listGrade = new ArrayList<ObjectBean>();

				}

			}

		}

	}

	private void getStudentAbility() {

		String stuid = "";

		if (abilityMap.size() == 0) {

			getAbilityInfo();

		}

		CourseAbilityDAO courseAbilityDao = (CourseAbilityDAO) MyBeansFactory.getBeans("courseabilitydao");

		double abilityA = 0, abilityB = 0, abilityC = 0, abilityD = 0, abilityE = 0, abilityF = 0;

		for (int i = 0; i < listGrade.size(); i++) {

			ObjectBean bean = listGrade.get(i);

			if ("StudentName".equals(bean.getId()) || "StudentId".equals(bean.getId())
					|| "StudentGrade".equals(bean.getId()) || "-".equals(bean.getName())) {

				if ("StudentId".equals(bean.getId())) {
					stuid = bean.getName();
				}

			} else {

				List<CourseAbility> courseabilityList = courseAbilityDao.getMappingByCourseId(bean.getId());

				for (int j = 0; j < courseabilityList.size(); j++) {

					CourseAbility courseability = courseabilityList.get(j);

					String abilityName = abilityMap.get(courseability.getAbilityId());

					double score = ScoreTransaction(bean.getName());

					if (abilityName.equals("abilityA")) {

						abilityA += score * courseability.getScore();

					} else {

						if (abilityName.equals("abilityB")) {

							abilityB += score * courseability.getScore();

						} else {

							if (abilityName.equals("abilityC")) {

								abilityC = score * courseability.getScore();

							} else {

								if (abilityName.equals("abilityD")) {

									abilityD += score * courseability.getScore();

								} else {

									if (abilityName.equals("abilityE")) {

										abilityE += score * courseability.getScore();

									} else {

										if (abilityName.equals("abilityF")) {

											abilityF += score * courseability.getScore();

										}
									}
								}
							}
						}

					}

				}

			}

		}

	/*	StudentAbility abi = new StudentAbility();
		abi.setId(TimeUtil.getObjectId());
		abi.setStuId(stuid);
		abi.setAbilityA(abilityA);
		abi.setAbilityB(abilityB);
		abi.setAbilityC(abilityC);
		abi.setAbilityD(abilityD);
		abi.setAbilityE(abilityE);
		abi.setAbilityF(abilityF);*/

	}

	Map<String, String> abilityMap = new HashMap<String, String>();

	private void getAbilityInfo() {

		AbilityDAO abilityDao = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

		List<Ability> list = abilityDao.getAllInfo();

		for (int i = 0; i < list.size(); i++) {

			abilityMap.put(list.get(i).getId(), list.get(i).getName());

		}

	}

	private double ScoreTransaction(String score) {

		if (null == score) {

			return 0;

		}

		double tempDouble = 0.0;

		try {

			tempDouble = Double.parseDouble(score);

		} catch (Exception e) {

			tempDouble = -1.0;
		}

		if (tempDouble == -1.0) {

			if ("通过".equals(score)) {

				return 60.0;

			} else {

				if (score.contains("补1")) {

					String s = score.replace("补1", "");

					return Double.parseDouble(s) * 0.8;

				} else {

					if (score.contains("补2")) {

						String s = score.replace("补2", "");

						return Double.parseDouble(s) * 0.6;

					} else {

						if (score.contains("重1")) {

							String s = score.replace("重1", "");

							return Double.parseDouble(s) * 0.4;

						} else {

							if (score.contains("重2")) {

								String s = score.replace("重2", "");

								return Double.parseDouble(s) * 0.2;

							} else {

								if (score.contains("取消资格")) {

									return 0.0;

								} else {

									if (score.contains("旷")) {

										return 0.0;

									} else {

									}

								}

							}

						}

					}

				}

			}

			return tempDouble;

		} else {

			return tempDouble;

		}

	}



}
