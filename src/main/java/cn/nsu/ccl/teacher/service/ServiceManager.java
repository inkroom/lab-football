/**
 * <p>ServiceManager.java文件的详细描述</p>
 * @Title: ServiceManager.java
 * @Package cn.nsu.ccl.teacher.service
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月19日 下午2:45:52
 * @version V1.0
 */
package cn.nsu.ccl.teacher.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nsu.ccl.teacher.service.impl.ExamServiceImpl;
import cn.nsu.ccl.teacher.service.impl.ExamingServiceImpl;
import cn.nsu.ccl.teacher.service.impl.MarkServiceImpl;
import cn.nsu.ccl.teacher.service.impl.TeacherServiceImpl;

/**
 * <p>管理所有的service，将service通过spring注解放在此类中，然后通过set和get方法进行调用</p>
 * @ClassName: ServiceManager
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月19日 下午2:45:52
 */
@Service
public class ServiceManager {
	@Autowired
	private TeacherServiceImpl teacherService;
	@Autowired
	private ExamServiceImpl examService;
	@Autowired
	private ExamingServiceImpl examingService;
	@Autowired
<<<<<<< HEAD
	private MarkServiceImpl markServiceImpl;
=======
	private MarkServiceImpl serviceImpl;

>>>>>>> 39a62f519a82e0a4809e03700e32a7fd10a7a652
	
	/**
	 * <p>teacherService的get函数</p>
	 * @return 返回 teacherService 的值
	 */
	public TeacherServiceImpl getTeacherService() {
		return teacherService;
	}
	/**
	 * <p>teacherService的set函数</p>
	 * @param teacherService 要设置的值
	 */
	public void setTeacherService(TeacherServiceImpl teacherService) {
		this.teacherService = teacherService;
	}
	/**
	 * <p>examService的get函数</p>
	 * @return 返回 examService 的值
	 */
	public ExamServiceImpl getExamService() {
		return examService;
	}
	/**
	 * <p>examService的set函数</p>
	 * @param examService 要设置的值
	 */
	public void setExamService(ExamServiceImpl examService) {
		this.examService = examService;
	}
	/**
	 * <p>examingService的get函数</p>
	 * @return 返回 examingService 的值
	 */
	public ExamingServiceImpl getExamingService() {
		return examingService;
	}
	/**
	 * <p>examingService的set函数</p>
	 * @param examingService 要设置的值
	 */
	public void setExamingService(ExamingServiceImpl examingService) {
		this.examingService = examingService;
	}
	/**
	 * <p>markServiceImpl的get函数</p>
	 * @return 返回 markServiceImpl 的值
	 */
	public MarkServiceImpl getMarkServiceImpl() {
		return markServiceImpl;
	}
	/**
	 * <p>markServiceImpl的set函数</p>
	 * @param markServiceImpl 要设置的值
	 */
	public void setMarkServiceImpl(MarkServiceImpl markServiceImpl) {
		this.markServiceImpl = markServiceImpl;
	}
}