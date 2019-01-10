package com.nsu.service.test.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.nsu.dao.test.TestDao;
import com.nsu.service.BaseService;
import com.nsu.service.test.ITestService;


/**
* @Title: ITestService
* @Package com.nsu.dao.test;
* @Description: 测试 ITestService 实现类
* @author 梅谢兵
* @date 2017-03-06
* @version V1.0
*/
@Service("iTestService")
public class TestServiceImpl extends BaseService implements ITestService {
	
	/**
	 * 自动注入testDao
	 */
	@Resource
	private TestDao testDao;
	
	/**
	 * @Description: 调用Dao 获取数据库中 User 信息
	 * @param username
	 * @return
	 */
	@Override
	public Map<String, Object> testService(String username) throws Exception {
		// TODO Auto-generated method stub
		log.debug("这里是：TestServiceIpml.class    注意：日志打印记得使用Log  禁止使用System.out.println");
		return testDao.getUserByName(username);
	}


}
