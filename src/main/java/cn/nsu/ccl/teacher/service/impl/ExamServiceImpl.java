package cn.nsu.ccl.teacher.service.impl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.nsu.ccl.teacher.dao.impl.ExamDaoImpl;
import cn.nsu.ccl.teacher.dao.impl.StudentDaoImpl;
import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;
import cn.nsu.ccl.teacher.service.ExamService;


@Service
public class ExamServiceImpl implements ExamService{
	
	@Autowired
	private ExamDaoImpl examInfoDao;
	@Autowired
	private StudentDaoImpl studentDao;
	
	/**
	 * 
	 * 描述：excel转list
	 * 方法名： excelToList
	 * 类名：ExamServiceImpl
	 * 返回值类型：ArrayList<StudentInfoEntity>
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年8月30日 下午8:48:17
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public ArrayList<StudentInfoEntity> excelToList(String URL) throws IOException {
		
		
		ArrayList<StudentInfoEntity> list = new ArrayList<>();
		
		//需要解析的Excel文件
		File file = new File(URL);
		//创建Excel，读取文件内容
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
		//读取默认第一个工作表sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		//获取sheet中最后一行行号
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowNum; i++) {
		
			XSSFRow row = sheet.getRow(i);
			//设置当前行最后单元格列号为2(为了避免因为我设置的提示而导致的错误)
			int lastCellNum = 2;
			//循环单元格列号
			ArrayList<String> list2 = new ArrayList<>();
			int index=0;
			for (int j = 0; j < lastCellNum; j++) {
				XSSFCell cell = row.getCell(j);
				if(index<lastCellNum){
					list2.add(cell.getStringCellValue());
				}
			}
			StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
			studentInfoEntity.setStudentName(list2.get(0));
			studentInfoEntity.setStudentId(list2.get(1));
			list.add(studentInfoEntity);
		}
		return list;
	}

	@Override
	public File createStudentExcel() {
		return null;
	}


	/**
	 * 添加考试信息
	 */
	public boolean addExamInfo(ExamInfoEntity examInfo) {
		try {
			return examInfoDao.addExamInfo(examInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取考试信息By暴沸
	 */
	public ArrayList<ExamInfoEntity> getExamInfo(String teacherId) {
		List<Map<String, Object>> listMap = examInfoDao.getExamInfo();
		ArrayList<ExamInfoEntity> list = new ArrayList<>();
		for(int i = 0;i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			ExamInfoEntity examInfo = new ExamInfoEntity();
			examInfo.setExamId(Integer.parseInt((map.get("examId")).toString()));
			examInfo.setExamName(map.get("examName").toString());
			examInfo.setStartTime(map.get("date_format(startTime,'%Y-%c-%d %h:%i:%s')").toString());
			examInfo.setEndTime(map.get("date_format(endTime,'%Y-%c-%d %h:%i:%s')").toString());
			examInfo.setTeacherId(map.get("teacherUsername").toString());
			list.add(examInfo);
		}
		ArrayList<ExamInfoEntity> list2 = new ArrayList<>();
		if(list!=null){
			//遍历整个集合，找到与教师帐号匹配的数据，并add
			for(int i = 0; i < list.size(); i++){
				ExamInfoEntity examInfo = list.get(i);
				if (teacherId.equals(examInfo.getTeacherId())) {
					list2.add(examInfo);
				}
			}
				return list2;
		}
		return null;
	}
	
	/**
	 * 
	 * 描述：存储考试信息中的学生信息
	 * 方法名： addStudentInfo
	 * 类名：ExamServiceImpl
	 * 返回值类型：boolean
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年9月1日 下午12:29:08
	 * @param studentInfoEntity
	 * @return
	 */
	public boolean addStudentInfo(StudentInfoEntity studentInfoEntity,String teacherId){
		
		try {
			return studentDao.addStudentInfo(studentInfoEntity,teacherId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除考试信息By暴沸
	 */
	public boolean deleteExamInfo(int examId){
		try {
			return examInfoDao.deleteExamInfo(examId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


//	/**
//	 * 获取题库信息
//	 */
//	public ArrayList<QuestionListEntity> getQuestionList() {
//		
//		return examInfoDao.getQuestion();
//	}

	
	
}


