package com.nsu.service.admin.impl;

import com.nsu.bean.index.UserBean;
import com.nsu.dao.admin.AdminDao;
import com.nsu.service.admin.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: AdminServiceImpl
 * @Package com.nsu.service.admin.impl
 * @Description:
 * @date 5/8/17
 */
@Service("adminService")
public class AdminServiceImpl implements IAdminService {
    //注入
    @Resource
    private AdminDao adminDao;

    /**
     * 获取数据 user
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public UserBean getUserByUsername(String userName)throws Exception {
        return adminDao.getUserByUsername(userName);
    }
}
