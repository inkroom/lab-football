package cn.nsu.ccl.teacher.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONObject;

@Controller
public class LoginController {
	@Autowired
	private ServiceManager service;

	@RequestMapping(value="teacher")
	public String toLogin(){
		return "teacher/login";
	}
	@RequestMapping(value="teacherLogin",method=RequestMethod.POST)
	public void login(String teacherName,String teacherPassword,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if (service.getTeacherService().login(teacherName, teacherPassword)) {
			jsonObject.put("state", "success");
		}else{
			jsonObject.put("state", "fail");
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="teacherIndex")
	public String toIndex(){
		return "teacher/index";
	}
	@RequestMapping(value="teacherWelcome")
	public String welcome(){
		return "teacher/welcome";
	}
	
}
