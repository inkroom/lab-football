package com.nsu.dao.test;

import java.util.List;
import java.util.Map;


/**
* @Title: TestController
* @Package com.nsu.dao.test;
* @Description: 测试 Dao
* @author 梅谢兵
* @date 2017-03-06
* @version V1.0
*/

public interface TestDao {
	
	/**
	 * 获取数据库中用户信息
	 * @param username
	 * @return
	 */
	public Map<String, Object> getUserByName(String username) throws Exception;
	/**
	 * 分页测试
	 * @author 王树浩
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> pagingTest() throws Exception;
}
