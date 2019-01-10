package com.nsu.service.test;

import java.util.Map;

import org.springframework.dao.DataAccessException;

/**
* @Title: ITestService
* @Package com.nsu.dao.test;
* @Description: 测试 ITestService 接口
* @author 梅谢兵
* @date 2017-03-06
* @version V1.0
*/
public interface ITestService {
	/**
	 * @Description: 调用Dao 获取数据库中 User 信息
	 * @param username
	 * @return
	 */
	public Map<String, Object> testService(String username) throws Exception;
}
