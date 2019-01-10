package cn.inkroom.web.money.gate.services.common.impl;

import cn.inkroom.web.money.gate.daos.jdbc.Common.ChangePswDAO;
import cn.inkroom.web.money.gate.services.common.IChangepswService;
import cn.inkroom.web.money.gate.utils.encrypt.Md5EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChangepswService implements IChangepswService{

    @Autowired
    private ChangePswDAO dao;

    @Override
    public void changepsw(String username, String password) {
        //修改密码
        String[] strings = Md5EncryptUtil.parseMd5WithSalt(password);
        dao.changpsw(username,strings[0],strings[1]);
    }
}
