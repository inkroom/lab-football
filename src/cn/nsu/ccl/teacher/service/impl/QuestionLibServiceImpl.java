/**
 * <p>QuestionLibServiceImpl.java文件的详细描述</p>
 * @Title: QuestionLibServiceImpl.java
 * @Package cn.nsu.ccl.teacher.service.impl
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午8:02:56
 * @version V1.0
 */
package cn.nsu.ccl.teacher.service.impl;


import cn.nsu.ccl.teacher.dao.impl.QuestionLibDaoImpl;
import cn.nsu.ccl.teacher.entity.Question;
import cn.nsu.ccl.teacher.entity.QuestionLibList;
import cn.nsu.ccl.teacher.service.QestionLibService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>QuestionLibServiceImpl类的描述</p>
 * @ClassName: QuestionLibServiceImpl
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午8:02:56
 */
@Service
public class QuestionLibServiceImpl implements QestionLibService {
	@Autowired
	private QuestionLibDaoImpl questionLibDao;
	/**
	 * 
	 * <p>getQuestion方法的描述</p>
	 * @Title: QestionLibService的getQuestion方法
	 * @Description: 通过题库Id获取题库中的题目信息
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:54:20
	 * @param questionLibId
	 * @return
	 */
	public ArrayList<Question> getQuestion(int questionLibId) {
		List<Map<String, Object>> listMap = questionLibDao.getQuestions(questionLibId);
		ArrayList<Question> list = new ArrayList<>();
		for(int i = 0; i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			Question questionEntity = new Question();
			questionEntity.setQuestionId(map.get("questionId").toString());
			questionEntity.setLibraryId(Integer.parseInt(map.get("libraryId").toString()));
			questionEntity.setQuestionContent(map.get("question").toString());
			questionEntity.setChoice(map.get("choice").toString());
			questionEntity.setAnswer(map.get("answer").toString());
		}
		return list;
	}

	/**
	 * 
	 * <p>addQuestions方法的描述</p>
	 * @Title: QestionLibService的addQuestions方法
	 * @Description: 传入一个集合，直接实现创建一个题库（包含所有）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午8:01:29
	 * @param questionEntities
	 * @return
	 */
	public boolean addQuestions(String questionLibName,String teacherId,ArrayList<Question> questionEntities) {
		//在题库列表中创建一个题库信息
		if (this.addQuestionLibList(questionLibName, teacherId)) {
			//在题库列表信息中获取题库的id
			int questionLibId = this.getQuestionLibId(questionLibName, teacherId);
			//最后调用dao层方法将整个题库的所有题库存储数据库
			return questionLibDao.addQuestion(questionEntities, questionLibId);
		}
		return false;
	}

	/**
	 * 
	 * <p>addQuestionLibList方法的描述</p>
	 * @Title: QestionLibService的addQuestionLibList方法
	 * @Description: 传入题库名字和教师id（邮箱）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:59:15
	 * @param questionLibName
	 * @param teacherId
	 * @return
	 */
	public boolean addQuestionLibList(String questionLibName, String teacherId) {
		return questionLibDao.addQuestionLib(questionLibName, teacherId);
	}
	
	/**
	 * <p>覆盖的getQuestionLibList函数</p>
	 * @Description: 通过教师id获取该教师所创建的题库列表信息（题库id，题库名字，单选题个数，多选题个数，判断题个数）
	 * @param teacherId
	 * @return
	 * @see QestionLibService#getQuestionLibList(int)
	 */
	public QuestionLibList getQuestionLibList(String questionLibName,String teacherId) {
		//先获取题库id列表
		int libraryId = this.getQuestionLibId(questionLibName, teacherId);
		//使用题库id查找题库信息
		return this.getQuestionLibListByLibraryId(libraryId);
	}

	/**
	 *
	 * <p>getQuestionList方法的描述</p>
	 * @Title: QestionLibService的getQuestionList方法
	 * @Description: 通过传入的题库信息获取题库的具体信息（题库名字，单选题数量，多选题数量，判断题数量）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:55:16
	 * @param teacherId
	 * @return
	 */
	private QuestionLibList getQuestionLibListByLibraryId(int libraryId) {
		//获得数据库中所有的题库信息（包含题库的id，题库的名字，该题库的单选题个数，多选题各位，判断题个数）
		List<QuestionLibList> list = questionLibDao.getQuestionLibList();
		//遍历所有的题库信息，找到与传入的题库id相等的题库信息
		for(int i = 0; i < list.size();i++){
			if (libraryId == list.get(i).getLibraryId()) {
				return list.get(i);
			}
		}
		return null;
	}
	/**
	 *
	 * <p>deleteQuestionLibList方法的描述</p>
	 * @Title: QestionLibService的deleteQuestionLibList方法
	 * @Description: 通过题库名字和教师邮箱帐号（教师id）删除题库（整个删除）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:57:35
	 * @param questionLibId
	 * @return
	 */
	public boolean deleteQuestionLibList(String questionLibName,String teacherId) {
		//获取题库id
		int questionLibId = this.getQuestionLibId(questionLibName, teacherId);
		//调用dao层实现删除题库的操作
		return questionLibDao.deletQuestionLib(questionLibId);
	}

	/**
	 * <p>覆盖的getQuestionLibId函数--通过教师邮箱和题库名称查找数据库id</p>
	 * @param teacherId
	 * @return
	 * @see QestionLibService#getQuestionLibId(String)
	 */
	public int getQuestionLibId(String questionLibName,String teacherId) {
		return questionLibDao.getQuestionLibId(questionLibName,teacherId);
	}

	/**
	 *
	 * <p>getQuestionLibList方法的描述</p>
	 * @Title: QuestionLibServiceImpl的getQuestionLibList方法
	 * @Description: 获取题库信息（题库id，题库名，单选题个数，多选题个数，判断题个数）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午10:07:45
	 * @return
	 */
	private List<QuestionLibList> getQuestionLibList(){
		//获得数据库中所有的题库信息（包含题库的id，题库的名字，该题库的单选题个数，多选题各位，判断题个数）
		return questionLibDao.getQuestionLibList();
	}


	/**
	 * <p>覆盖的getQuestionLibListByTeacherEmail函数</p>
	 * @param teacherEmail
	 * @Description: 通过教师邮箱获取该教师所创建的题库列表信息（包含题库的id，题库的名字，题库的单选题个数，多选题个数，判断题个数）
	 * @return
	 * @see QestionLibService#getQuestionLibListByTeacherEmail(String)
	 */
	public List<QuestionLibList> getQuestionLibListByTeacherEmail(String teacherEmail) {
		List<QuestionLibList> list = questionLibDao.getQuestionLibByTeacherEmail(teacherEmail);
		return list;
	}
	//检查上传的题库模版是否符合要求
	public boolean checkExcel(String filePath) {
		File file = new File(filePath);
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
	
	/**
	 * 
	 * <p>getQuestionLibListByExcel方法的描述
	 * 将上传的excel文件解析成集合
	 * </p>
	 * @Title: QuestionLibServiceImpl的getQuestionLibListByExcel方法
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月27日 上午11:20:10
	 * @param filePath
	 * @return
	 */
	public ArrayList<Question> getQuestionLibListByExcel(String filePath){
		File file = new File(filePath);
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
		ArrayList<Question> questionList = new ArrayList<Question>();
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
					Question question = new Question();
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
					Question question = new Question();
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
}
