package cn.inkroom.web.money.gate.services.backstage.impl;

import cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean;
import cn.inkroom.web.money.gate.daos.jdbc.backstage.ContactEditDAO;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.services.backstage.IContactEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ContactEditService implements IContactEditService {
    @Autowired
    private ContactEditDAO dao;

    @Override
    public ArrayList<ContactUsBean> getContacts() {
        ArrayList<ContactUsBean> beans = dao.getcontacts();
        return beans;
    }

    @Override
    public ContactUsBean getContact(int contact_id) {
        ContactUsBean bean = dao.getcontact(contact_id);
        return bean;
    }

    @Override
    public Result EditContact(ContactUsBean bean) {
        int correct = dao.editcontact(bean);
        if(correct == 1){
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @Override
    public Result DeleteContact(int contact_id) {
        int correct = dao.deletecontact(contact_id);
        if(correct == 1){
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @Override
    public Result AddContact(ContactUsBean bean) {
        int correct = dao.addcontact(bean);
        if(correct == 1){
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }
}
