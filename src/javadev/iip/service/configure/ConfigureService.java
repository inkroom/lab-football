package javadev.iip.service.configure;

import javadev.iip.service.BaseService;

/**
 * 功能描述：处理Configure表的DB操作
 * 
 * @author 张景录
 * @version 1.0 Create Date: 2016-04-15
 */
public class ConfigureService extends BaseService {

	private static final String SQL_GET_CONFIGURE_BY_NAME = "SELECT VALUE FROM CONFIGURE WHERE ITEM_NAME = ?";

	/**
	 * 功能描述：获得指定name的配置值
	 * 
	 * @param item_name
	 *            配置项名称
	 * @return 值
	 * @author 张景录 Create Date: 2016-04-15
	 */
	public String get(String item_name) {
		// TODO Auto-generated method stub
		return jt.queryForObject(SQL_GET_CONFIGURE_BY_NAME, new Object[] { item_name }, String.class);
	}

}
