package cn.inkroom.web.money.gate.daos.jdbc.backstage;

import cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ContactEditDAO {
    @Select("select * from contactUs")
    public ArrayList<ContactUsBean> getcontacts();

    @Select("select * from contactUs where id = #{id}")
    ContactUsBean getcontact(@Param("id")int contact_id);

    //bean中属性没有下划线
    @Update("update contactUs set title = #{bean.title}" +
            ",contact_name1 = #{bean.contactname1}" +
            ",contact_way1 = #{bean.contactway1}" +
            ",contact_name2 = #{bean.contactname2}" +
            ",contact_way2 = #{bean.contactway2}" +
            ",contact_name3 = #{bean.contactname3}" +
            ",contact_way3 = #{bean.contactway3}" +
            ",contact_name4 = #{bean.contactname4}" +
            ",contact_way4 = #{bean.contactway4}" +
            ",contact_name5 = #{bean.contactname5}" +
            ",contact_way5 = #{bean.contactway5}" +
            "where id = #{bean.id}")
    int editcontact(@Param("bean")ContactUsBean bean);

    @Delete("delete from contactUs where id = #{id}")
    int deletecontact(@Param("id")int contact_id);


    @Insert("insert into contactUs(title" +
            ",contact_name1,contact_way1" +
            ",contact_name2,contact_way2" +
            ",contact_name3,contact_way3" +
            ",contact_name4,contact_way4" +
            ",contact_name5,contact_way5) " +
            " values(#{bean.title}" +
            ",#{bean.contactname1},#{bean.contactway1}" +
            ",#{bean.contactname2},#{bean.contactway2}" +
            ",#{bean.contactname3},#{bean.contactway3}" +
            ",#{bean.contactname4},#{bean.contactway4}" +
            ",#{bean.contactname5},#{bean.contactway5})")
    int addcontact(@Param("bean") ContactUsBean bean);
}
