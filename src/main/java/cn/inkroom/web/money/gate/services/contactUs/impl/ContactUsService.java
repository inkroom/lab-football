package cn.inkroom.web.money.gate.services.contactUs.impl;

import cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean;
import cn.inkroom.web.money.gate.daos.jdbc.ContactUs.ContactUsDAO;
import cn.inkroom.web.money.gate.services.contactUs.IContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ContactUsService implements IContactUsService{
    @Autowired
    private ContactUsDAO dao;

    public ArrayList<ContactUsBean> getContactUs() {
        ArrayList<ContactUsBean> beans = dao.getContactUs();
        return beans;
    }
}
