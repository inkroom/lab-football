package cn.nsu.ccl.teacher.service;

import java.util.ArrayList;

import cn.nsu.ccl.teacher.entity.TeacherEntity;

/**
 * 
 * <p>实现对于教师用例的操作，具体为教师登录</p>
 * @ClassName: TeacherService
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午5:44:27
 */
public interface TeacherService {
	public ArrayList<TeacherEntity> getTeachers();
	public boolean login(String name,String password);
}
