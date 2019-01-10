package com.nsu.service.admin;

import com.nsu.bean.index.UserBean;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IAdminService
 * @Package com.nsu.service.admin.impl
 * @Description:
 * @date 5/8/17
 */
public interface IAdminService {
    /**
     * 获取数据 user
     * @param userName
     * @return
     * @throws Exception
     */
    public UserBean getUserByUsername(String userName) throws Exception;
}
