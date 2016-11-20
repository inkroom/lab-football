package cn.nsu.ccl.teacher.dao;

import java.util.List;
import java.util.Map;

import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.QuestionListEntity;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;

import java.util.ArrayList;
/**
 * 
 * 描述：与数据库之间关于教师属性类的实现接口
 * 类名：ExamInfoDao
 * 开发人员：暴沸
 * 联系方式：admin@baofeidyz.com
 * 创建时间：2016年8月29日 下午7:13:20
 */
public interface TeacherDao {
	
	/**
	 * <p>getTeachers方法的描述</p>
	 * @Title: TeacherDao的getTeachers方法
	 * @Description: 获取考试信息，教师帐号（String），教师密码（String），教师所属系部（String），真实姓名(String)
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 上午10:21:13
	 * @return
	 */
	public List<Map<String, Object>> getTeachers();

	
}
