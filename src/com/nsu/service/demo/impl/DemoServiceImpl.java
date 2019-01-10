package com.nsu.service.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.demo.JacksonDemoBean;
import com.nsu.dao.demo.DemoDao;
import com.nsu.service.demo.IDemoService;
import org.springframework.stereotype.Service;

@Service()
public class DemoServiceImpl implements IDemoService {
	@Autowired
	private DemoDao demoDao;
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.nsu.service.demo.IDemoService#findAll()
	 */
	public PageInfo<JacksonDemoBean> findAll() throws Exception {
		PageHelper.startPage(1,10);
		List<JacksonDemoBean> list=demoDao.findAll();
		PageInfo<JacksonDemoBean> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<JacksonDemoBean> findAllList() throws Exception {
		return demoDao.findAll();
	}


}
