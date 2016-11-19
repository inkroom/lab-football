package cn.nsu.ccl.teacher.service;

import java.util.ArrayList;

import cn.nsu.ccl.teacher.entity.TeacherEntity;

public interface TeacherService {
	public ArrayList<TeacherEntity> getTeachers();
	public boolean login(String name,String password);
}
