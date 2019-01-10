package cn.inkroom.web.money.gate.services.backstage;

import cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean;
import cn.inkroom.web.money.gate.enums.Result;

import java.util.ArrayList;

public interface IContactEditService {

    public ArrayList<ContactUsBean> getContacts();

    ContactUsBean getContact(int contact_id);

    Result EditContact(ContactUsBean bean);

    Result DeleteContact(int contact_id);

    Result AddContact(ContactUsBean bean);
}
