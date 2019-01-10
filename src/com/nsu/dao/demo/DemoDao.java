package com.nsu.dao.demo;

import java.util.List;

import com.nsu.bean.demo.JacksonDemoBean;
/**
 * jackson演示dome DAO 层
 * @author yrge
 *
 */
public interface DemoDao {
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	public List<JacksonDemoBean> findAll() throws Exception;
}
