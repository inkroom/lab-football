package cn.edu.nsu.lib.services.impl;

import cn.edu.nsu.lib.dao.TestADao;
import cn.edu.nsu.lib.dao.TestDao;
import cn.edu.nsu.lib.dao.TestIntDao;
import cn.edu.nsu.lib.dao.TestJdbcDao;
import cn.edu.nsu.lib.services.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/21
 * @Time 10:35
 * @Descorption
 */
@Service
public class TestServiceImpl implements TestService {
    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private TestIntDao testIntDao;
    @Autowired
    private TestADao testADao;
    @Autowired
    private TestDao testDao;
    @Autowired
    private TestJdbcDao testJdbcDao;

    @Override
    public void testDao() throws Exception {
        log.info(" 使用mybatis xml配置的 TestIntDao  的执行结果 = " + testIntDao.sel("GOSICK"));
        log.info(" 使用mybatis 注解配置的TestADao  的执行结果 = " + testADao.sel("GOSICK"));
        log.info(" 使用mybatis 自主实现dao层的TestDao  的执行结果 = " + testDao.sel("GOSICK"));
        log.info(" 使用jdbc 自主实现dao层的TestJdbcDao  的执行结果 = " + testJdbcDao.sel("GOSICK"));
    }
}
