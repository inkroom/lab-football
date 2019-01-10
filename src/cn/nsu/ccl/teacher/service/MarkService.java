package cn.nsu.ccl.teacher.service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * <p>实现对学生成绩信息的各项操作</p>
 * @ClassName: MarkService
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午5:43:57
 */
public interface MarkService {
 
	/**
	 * 
	 * 描述：下载考试成绩
	 * 方法名： downloadGrade
	 * 类名：StudentGradeService
	 * 返回值类型：String
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年9月1日 下午4:07:36
	 * @param markEntities
	 * @return
	 */
	public String downloadGrade(int examId, String path, String examName)throws IOException;

	/**
	 *
	 * 描述：计算并存入学生成绩
	 * 方法名： setStudentGrade
	 * 类名：StudentGradeService
	 * 返回值类型：boolean
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年9月1日 下午4:07:44
	 * @param examId
	 * @param teacherId
	 * @return
	 */
	public boolean setStudentGrade(int examId);

	public ArrayList<Integer> getExamId();
	public String getScoreExcel(int examId, String contextPath, String examName) throws IOException;
}
