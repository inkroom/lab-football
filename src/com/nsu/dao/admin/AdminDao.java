package com.nsu.dao.admin;

import com.nsu.bean.index.UserBean;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: AdminDao
 * @Package com.nsu.dao.admin
 * @Description:
 * @date 5/8/17
 */
public interface AdminDao  {
    /**
     * 获取数据 user
     * @param userName
     * @return
     * @throws Exception
     */
     UserBean getUserByUsername(String userName) throws Exception;
}
