package cn.nsu.ccl.teacher.service;




import java.io.File;
import java.util.ArrayList;

import cn.nsu.ccl.teacher.entity.QuestionEntity;

/**
描述：实现对题目数据的操作，对excel文件的扫描检查等
 * 类名：QuestionLibService
 * 开发者：墨盒
 * 创建时间：2016-8-28
 */
public interface QuestionService {
	
	/**
	 * 功能：扫描excel表，当数据格式不合格时返回null，否则返回数据
	 * 方法名：getQuestions
	 * 返回值：ArrayList<Question>
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param file excel文件
	 * @return 文件合格返回数据，否则返回null
	 */
	public ArrayList<QuestionEntity> getQuestions(File file);
	/**
	 * 功能：根据json数据上传到数据库
	 * 方法名：submit
	 * 返回值：boolean
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param json 题目数据
	 * @param questionLibId 题库编号
	 * @return 添加失败返回false
	 */
	public boolean submit(String json,int questionLibId)throws Exception;
	/**
	 * 功能：检查excel文件
	 * 方法名：checkExcel
	 * 返回值：boolean
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param file excel文件
	 * @return 文件不合格，返回false
	 */
	public boolean checkExcel(File file);
	/**
	 * 功能：扫描excel文件，必须文件合格
	 * 方法名：scanExcel
	 * 返回值：String 题目json数据
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param file excel文件
	 * @return 返回题目数据
	 */
	public ArrayList<QuestionEntity> scanExcel(File file);
}
