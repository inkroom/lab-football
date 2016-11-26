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
	public ArrayList<StudentInfoEntity> excelToList(String URL){
		
		ArrayList<StudentInfoEntity> list = new ArrayList<>();
		//需要解析的Excel文件
		File file = new File(URL);
		//创建Excel，读取文件内容
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//读取默认第一个工作表sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		//获取sheet中最后一行行号
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			//设置当前行最后单元格列号为2(为了避免因为我设置的提示而导致的错误)
			int lastCellNum = 1;
			//循环单元格列号
			ArrayList<String> list2 = new ArrayList<>();
			int index=0;
			for (int j = 0; j <=lastCellNum; j++) {
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

	


	/**
	 * 添加考试信息
	 */
	public int addExamInfo(ExamInfoEntity examInfo) {
		//判断考试名字是否已经存在，不允许重复的考试名称存在
		//通过教师邮箱帐号获取当前已经创建的考试信息
		ArrayList<ExamInfoEntity> list = this.getExamInfoByTeacherEmail(examInfo.getTeacherId());
		//遍历该教师的考试信息
		for(int i = 0; i < list.size();i++){
			ExamInfoEntity entity = list.get(i);
			//如果找到与传入的考试名称相同的数据，则返回false
			if (entity.getExamName().equals(examInfo.getExamName())) {
				return -1;
			}
		}
		//如果创建成功返回1
		if (examInfoDao.addExamInfo(examInfo)) {
			return 1;
		}
			//创建失败返回0
		return 0;
	}

	/**
	 * 
	 * <p>覆盖的getExamInfoByTeacherEmail函数
	 * 通过教师邮箱帐号获取该教师创建的所有考试信息
	 * </p>
	 * @param teacherEmail
	 * @return
	 * @see cn.nsu.ccl.teacher.service.ExamService#getExamInfoByTeacherEmail(java.lang.String)
	 */
	public ArrayList<ExamInfoEntity> getExamInfoByTeacherEmail(String teacherEmail) {
		List<Map<String, Object>> listMap = examInfoDao.getExamInfo();
		ArrayList<ExamInfoEntity> list = new ArrayList<>();
		for(int i = 0;i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			ExamInfoEntity examInfo = new ExamInfoEntity();
			//设置考试id
			examInfo.setExamId(Integer.parseInt((map.get("examId")).toString()));
			//设置考试名称
			examInfo.setExamName(map.get("examName").toString());
			//设置题库id
			examInfo.setQuestionListNumber(Integer.parseInt(map.get("libraryId").toString()));
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
				if (teacherEmail.equals(examInfo.getTeacherId())) {
					list2.add(examInfo);
				}
			}
				return list2;
		}
		return null;
	}
	
	/**
	 * 
	 * <p>addStudentInfo方法的描述---通过考试id存储该场考试的学生信息</p>
	 * @Title: ExamServiceImpl的addStudentInfo方法
	 * @Description: TODO
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月23日 下午2:37:04
	 * @param studentInfoEntity
	 * @param examId
	 * @return
	 */
	public boolean addStudentInfo(StudentInfoEntity studentInfoEntity,String teacherEmail,String examName){
		//声明一个变量
		int examId = -1;
		//根据教师邮箱获取考试信息
		ArrayList<ExamInfoEntity> list = this.getExamInfoByTeacherEmail(teacherEmail);
		for(int i = 0; i < list.size();i++){
			//遍历集合，找到与传入的考试名字匹配的数据
			ExamInfoEntity examInfoEntity = list.get(i);
			if (examInfoEntity.getExamName().equals(examName)) {
				examId = examInfoEntity.getExamId();
			}
		}
		//调用dao层存储数据
		return studentDao.addStudentInfo(examId,teacherEmail,studentInfoEntity);
	}
	
	/**
	 * 删除考试信息By暴沸
	 */
	public boolean deleteExamInfoByExamId(int examId){
		try {
			return examInfoDao.deleteExamInfo(examId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * <p>覆盖的updateExamInfo函数--更新考试信息</p>
	 * @param examInfo
	 * @return
	 * @see cn.nsu.ccl.teacher.service.ExamService#updateExamInfo(cn.nsu.ccl.teacher.entity.ExamInfoEntity)
	 */
	public boolean updateExamInfo(ExamInfoEntity examInfo) {
		return examInfoDao.updateExamInfoByExamId(examInfo);
	}




	/**
	 * <p>覆盖的isStudentInfoExistByExamId函数
	 * 通过考试id去判断该场考试是否有考生信息
	 * </p>
	 * @param examId
	 * @return
	 * @see cn.nsu.ccl.teacher.service.ExamService#isStudentInfoExistByExamId(int)
	 */
	public boolean isStudentInfoExistByExamId(int examId) {
		//通过考试id（examId）去获取该场考试的学生信息
		List<Map<String, Object>> listMap = studentDao.getStudentInfo(examId);
		//如果listMap.size()等于0则代表该场考试没有考生信息
		if (listMap.size()==0) {
			return false;
		}
		return true;
	}
	
}


