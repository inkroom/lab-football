package cn.nsu.ccl.teacher.service;

import cn.nsu.ccl.teacher.entity.ExamInfo;
import cn.nsu.ccl.teacher.entity.StudentInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>考试试卷的增删查改的实现</p>
 * @ClassName: ExamService
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午5:41:46
 */
public interface ExamService {
	
	//添加考试信息
	public int addExamInfo(ExamInfo examInfo);
	//将上传的学生信息模版转为ArrayList
	public  ArrayList<StudentInfo> excelToList(String URL) throws IOException;
	//通过教师邮箱获取全部的考试信息
	public  List<ExamInfo> getExamInfoByTeacherEmail(String teacherEmail);
	//通过考试id删除考试信息
	public  boolean deleteExamInfoByExamId(int examId);
	//更新考试信息
	public boolean updateExamInfo(ExamInfo examInfo);
	//添加考生信息
	public boolean addStudentInfo(StudentInfo studentInfoEntity, String teacherEmail, String examName);
	//通过考试id去检测该场考试是否存在对应的考试信息
	public boolean isStudentInfoExistByExamId(int examId);
}
