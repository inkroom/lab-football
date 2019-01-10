package cn.nsu.ccl.teacher.service;

import cn.nsu.ccl.teacher.entity.Teacher;

import java.util.ArrayList;

/**
 * 
 * <p>实现对于教师用例的操作，具体为教师登录</p>
 * @ClassName: TeacherService
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午5:44:27
 */
public interface TeacherService {
	public ArrayList<Teacher> getTeachers();
	public boolean login(String name, String password);
}
