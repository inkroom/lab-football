package cn.nsu.ccl.teacher.service.impl;



import java.util.ArrayList;
import java.util.Date;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import cn.nsu.ccl.teacher.entity.QuestionEntity;
import cn.nsu.ccl.teacher.service.QuestionService;



/**
 * <p>QuestionServiceImpl.java文件的详细描述--题目的上传下载实现接口</p>
 * @Title: QuestionServiceImpl.java
 * @Package cn.nsu.ccl.teacher.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月25日 上午11:23:49
 * @version V1.0
 */

@Service
public class QuestionServiceImpl  implements QuestionService {
	/**
	 * <p>checkExamExcel方法的描述</p>
	 * @Title: QuestionLibServiceImpl的checkExamExcel方法
	 * @Description: 将数据存储到数据库
	 * @author 2213974854@qq.com
	 * @date 2016年11月24日 下午8:34:59
	 * @param filePath
	 * @return
	 */
	public boolean checkExamExcel(String filePath) {
		return false;
	}
	/**
	 * 功能：根据上传的文件创建临时文件
	 * 方法名：upload
	 * 返回值：File
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-25
	 * @param CommonsMultipartFile file
	 * @return
	 */
	public File upload(CommonsMultipartFile file,String path) {
		File tempFile = null;
		if (!file.isEmpty()) {
			try {
				//获取项目跟路径
				tempFile = new File(path + new Date(0).getTime() + file.getOriginalFilename());
				//打开输入输入流
				FileOutputStream fos = new FileOutputStream(tempFile);
				InputStream in = file.getInputStream();

				byte buffer[] = new byte[2048];
				int len = -1;
				while ((len = in.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				//关闭输入输入流
				fos.flush();
				fos.close();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return tempFile;
	}
	/**
	 * 功能：删除临时文件
	 * 方法名：upload
	 * 返回值：File
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-25
	 * @param File file 临时文件
	 * @return
	 */
	public void delete(File file) {
		if(file.exists()){
			file.delete();
		}
	}
	public ArrayList<QuestionEntity> getQuestions(File file) {
		if (this.checkExcel(file)) {
			return this.scanExcel(file);
		}
		return null;
	}

	/*public boolean submit(String json, int questionLibId) throws Exception {
		ArrayList<QuestionEntity> questionList = analyzeQuestion(json);
	
		return questionLibDao.addQuestion(ArrayList<QuestionEntity> QuestionEntity, int questionLibId);
	}
*/
	public boolean checkExcel(File file) {
		Workbook workBook = null;
		try {
			workBook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (OfficeXmlFileException e) {//2007版本以上
			try {
				workBook = new XSSFWorkbook(file);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (workBook.getNumberOfSheets() < 3) {//工作表数目错误
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<QuestionEntity> scanExcel(File file) {
		Sheet sheet = null;// 工作表
		Workbook workbook = null;
		Cell cell = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {
			// e.printStackTrace();
			try {
				workbook = new XSSFWorkbook(new FileInputStream(file));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// 创建excel，读取文件内容
		ArrayList<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
		int num = 0;
		// 循环3个sheet工作表
		for (int s = 0; s < 3; s++) {
			// 获取第工作表
			sheet = workbook.getSheetAt(s);
			// 读取默认第一个工作表
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			// 前两个sheet工作表
			if (s != 2) {
				for (int i = 2; i < lastRowNum + 1; i++) {
					QuestionEntity question = new QuestionEntity();
					Row row = sheet.getRow(i);
					// 获取当前最后单元格列号
					int lastCellNum = row.getLastCellNum();
					// 题干
					cell = row.getCell(0);
					if (cell == null) {
						continue;
					}
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					question.setQuestionContent(cell.getStringCellValue());
					// 备选答案
					String temp = "";
					for (int j = 1; j < lastCellNum - 1; j++) {
						cell = row.getCell(j);
						if (cell == null) {
							continue;
						}
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (j != lastCellNum - 2) {
							temp += cell.getStringCellValue() + "##";
						} else {
							temp += cell.getStringCellValue();
						}
					}
					question.setChoice(temp);

					// 正确答案
					cell = row.getCell(lastCellNum - 1);
					if (cell == null) {
						continue;
					}
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					StringBuffer sb = new StringBuffer();
					//获取正确答案选项
					boolean flag = false;
					String valued = cell.getStringCellValue();
					for (int x = 0; x < valued.length(); x++) {
						char tempAn = valued.toUpperCase().charAt(x);
						int choiceIndex = tempAn - 'A' + 1;
						Cell tempCell = row.getCell(choiceIndex);
						if (tempCell == null) {
							flag = true;
							break;
						}
						tempCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (x != valued.length() - 1) {
							sb.append(tempCell.getStringCellValue() + "##");
						} else {
							sb.append(tempCell.getStringCellValue());
						}
					}
					if (flag) {//答案不合规范
						continue;
					}
					question.setAnswer(sb.toString());
					if (s == 0) {
						question.setType("单选题");
					} else {
						question.setType("多选题");
					}
					// 编号
					num++;
					String nums = String.valueOf(num);
					question.setQuestionId(nums);
					questionList.add(question);
				}
			} else { // 第三个sheet工作表，判断题表
				for (int i = 2; i < lastRowNum + 1; i++) {
					QuestionEntity question = new QuestionEntity();
					Row row = sheet.getRow(i);
					// 题干
					cell = row.getCell(0);
					if (cell == null) {
						continue;
					}
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					String value = cell.getStringCellValue();
					question.setQuestionContent(value);

					// 答案
					cell = row.getCell(1);
					if (cell == null) {
						continue;
					}
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					String answer = cell.getStringCellValue();
					//答案不合规范
					if("对".equals(answer)||"错".equals(answer)){//正确答案必须是对或错其中之一
						question.setAnswer(answer);
						question.setType("判断题");
						//将选项置为空
						question.setChoice("对##错");
						num++;
						String nums = String.valueOf(num);
						
						question.setQuestionId(nums);
						questionList.add(question);
					}
				}
			}
		}
		return questionList;
	}

	/**
	 * <p>覆盖的submit函数</p>
	 * @param json
	 * @param questionLibId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.service.QuestionService#submit(java.lang.String, int)
	 */
	@Override
	public boolean submit(String json, int questionLibId) throws Exception {
		return false;
	}

}
