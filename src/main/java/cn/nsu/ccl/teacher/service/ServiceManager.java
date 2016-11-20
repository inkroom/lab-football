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

import cn.nsu.ccl.teacher.service.impl.TeacherServiceImpl;

/**
 * <p>ServiceManager类的描述</p>
 * @ClassName: ServiceManager
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月19日 下午2:45:52
 */
@Service
public class ServiceManager {
	@Autowired
	private TeacherServiceImpl teacherService;
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
}