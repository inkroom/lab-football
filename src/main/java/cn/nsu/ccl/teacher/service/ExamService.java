package cn.nsu.ccl.teacher.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.QuestionListEntity;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;

public interface ExamService {
	
	//生成考生信息模版
	public  File createStudentExcel();				
	//添加考试信息
	public  boolean addExamInfo(ExamInfoEntity examInfo);
	//将上传的学生信息模版转为ArrayList
	public  ArrayList<StudentInfoEntity> excelToList(String URL) throws IOException;
	//获取考试信息
	public  ArrayList<ExamInfoEntity> getExamInfo(String teacherId);		
	public  boolean deleteExamInfo(int examId);
//	public ArrayList <QuestionListEntity> getQuestionList();
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
	public boolean addStudentInfo(StudentInfoEntity studentInfoEntity,String teacherId);
}
