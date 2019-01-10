package com.nsu.service.demo;


import com.github.pagehelper.PageInfo;
import com.nsu.bean.demo.JacksonDemoBean;

import java.util.List;

public interface IDemoService{
	public PageInfo<JacksonDemoBean> findAll() throws Exception;

	public List<JacksonDemoBean> findAllList() throws  Exception;
}
