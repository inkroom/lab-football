package cn.inkroom.web.money.gate.daos.jdbc.ContactUs;

import cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ContactUsDAO {

    @Select("select * from contactUs")
    ArrayList<ContactUsBean> getContactUs();


}
