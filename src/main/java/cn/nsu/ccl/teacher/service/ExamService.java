package cn.nsu.ccl.teacher.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;

/**
 * 
 * <p>考试试卷的增删查改的实现</p>
 * @ClassName: ExamService
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午5:41:46
 */
public interface ExamService {
	
	//生成考生信息模版
	public  File createStudentExcel();				
	//添加考试信息
	public  boolean addExamInfo(ExamInfoEntity examInfo);
	//将上传的学生信息模版转为ArrayList
	public  ArrayList<StudentInfoEntity> excelToList(String URL) throws IOException;
	//获取全部的考试信息
	public  ArrayList<ExamInfoEntity> getExamInfo(String teacherId);		
	public  boolean deleteExamInfo(int examId);
//	public ArrayList <QuestionListEntity> getQuestionList();
	//添加考生信息
	public boolean addStudentInfo(StudentInfoEntity studentInfoEntity,String teacherEmail,String examName);
}
