package com.nsu.service.teacher.login.impl;

import com.nsu.dao.teacher.login.LoginDao;
import com.nsu.entity.Account;
import com.nsu.service.teacher.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackageName : com.nsu.service.teacher.login.impl
 * @Author : BuDD
 * @CreateTime : 2017/7/31
 * @Version : 1.0
 * @Description : 登陆的Service层
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public Account login(String username, String passwordEncryption) {
         return this.loginDao.login(username,passwordEncryption);
    }
}
