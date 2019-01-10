package com.nsu.service.demo.impl;


import com.fasterxml.jackson.databind.JavaType;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.service.core.IMobileLoginService;
import com.nsu.util.RSAencrypt.MobileToken;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.demo.JacksonDemoBean;
import com.nsu.service.demo.IDemoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoServiceImplTest {
	private ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext=new ClassPathXmlApplicationContext(new String[] {
				"spring/applicationContext-trans.xml",
				"spring/applicationContext-dao.xml",
				"spring/applicationContext-service.xml"
				});
	}

	/**
	 * 分页
	 */
	@Test
	public void pageinfoTojson() {
	//	MobileTokenService mobileTokenService = (MobileTokenService)applicationContext.getBean("mobileTokenService");
	//	System.out.println(mobileTokenService.getMobileToken("6432?6?0?40","16d7a4fca","1","21678","99000855597219"));
//		IDemoService demoService= (IDemoService) applicationContext.getBean("demoServiceImpl");
//		try {
//			PageInfo<JacksonDemoBean> pageInfo = demoService.findAll();
//			System.out.println(pageInfo.toString());
//			System.out.println( JsonMapper.getInstance().toJson(pageInfo));//转换为Json Bean 需要配置 @JsonProperty
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * list<Bean>
	 */
	@Test
	public void listbeanTojson() {
		IDemoService demoService= (IDemoService) applicationContext.getBean("demoServiceImpl");
		try {
			List<JacksonDemoBean> list=demoService.findAllList();
			System.out.println(list.toString());
			System.out.println( JsonMapper.getInstance().toJson(list));  //转换为Json Bean 需要配置 @JsonProperty

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * jsonToListBean
	 * @throws Exception
	 */
	@Test
	public void jsontoBean(){
		IDemoService demoService= (IDemoService) applicationContext.getBean("demoServiceImpl");
		List<JacksonDemoBean> list= null;
		try {
			list = demoService.findAllList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String  json=JsonMapper.getInstance().toJson(list);
		System.out.println(json);
		JavaType javaType=JsonMapper.getInstance().createCollectionType(List.class,JacksonDemoBean.class);//转换类型
		List<JacksonDemoBean> listBean= JsonMapper.getInstance().fromJson(json , javaType);  //转换为 listBean
		for (JacksonDemoBean jacksonDemoBean : listBean){
			System.out.println(jacksonDemoBean.toString());
		}

	}

	/**
	 * jsonToList<Map>
	 */
	@Test
	public void  jsontoMap(){
		IDemoService demoService= (IDemoService) applicationContext.getBean("demoServiceImpl");
		List<JacksonDemoBean> list= null;
		try {
			list = demoService.findAllList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json=JsonMapper.getInstance().toJson(list);
		List<Map<String, Object>> listBean= JsonMapper.getInstance().fromJson(json , List.class);  //转换为 ListMap

			System.out.println(listBean.toString());
	}

}
