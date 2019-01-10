package com.nsu.controller.test;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nsu.common.Anonymous;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;


/**
* @Title: TestController
* @Package com.nsu.controller.test
* @Description: 测试 Controller
* @author 梅谢兵
* @date 2017-03-06
* @version V1.0
*/

@Controller
@RequestMapping(value="/test")
public class JsonTestController implements Anonymous  {
	

	@RestfulUrlAnnotation(refulUrl="/test/*/")
	@RequestMapping(value="/json_view")
	public String JsonTestView(){
		return "/test/jsonTest";
	}

	@RequestMapping(value="/test_view")
	public String testView(){
		return "/test/test_view";
	}
	
	
	@RequestMapping(value="/json")
	public String jsonTest(@RequestParam(value="name")String name,@RequestParam(value="passw")String passw,HttpServletResponse response){
		
		System.out.println("--------------jsonTest-----------------parm1-"+name+"-passw"+passw);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("testKey", "testValue");
	
		try {
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}
}
