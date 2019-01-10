package cn.inkroom.web.money.gate.controllers.backstage;

import cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean;
import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.services.backstage.impl.ContactEditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ContactEditController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ContactEditService service;
    private Logger log = LoggerFactory.getLogger(getClass());

    //跳转
    @RequestMapping(value = "/contact")
    public String To_contactEdit(){
        ArrayList<ContactUsBean> contacts = service.getContacts();
        for (ContactUsBean bean:
             contacts) {
            log.info(bean.toString());
        }
        request.setAttribute("contacts",contacts);
        return "backstage/contactEdit";
    }

    //获得编辑的信息
    @RequestMapping(value = "/contactEdit", method = RequestMethod.GET)
    @ResponseBody
    public MessageDto contactEdit_info(@RequestParam("contact_id") int contact_id){
        ContactUsBean contactbean = service.getContact(contact_id);
        MessageDto bean = new MessageDto(Result.SUCCESS);
        bean.put("contactbean",contactbean);
        if(contactbean != null) {
            log.info("獲得編輯信息："+contactbean.toString());
            return bean;
        }
        log.info("獲得信息失敗");
        return new MessageDto(Result.FAIL);
    }

    //保存编辑信息
    @RequestMapping(value = "/contactEdit", method = RequestMethod.POST)
    @ResponseBody
    public MessageDto contactEdit(ContactUsBean bean){
        Result result = service.EditContact(bean);
        if(result == Result.SUCCESS){
            log.info("編輯保存成功");
            return new MessageDto(Result.SUCCESS);
        }
        log.info("編輯保存失败");
        return new MessageDto(Result.FAIL);
    }

    //保存添加信息
    @RequestMapping(value = "/contactAdd", method = RequestMethod.POST)
    @ResponseBody
    public MessageDto contactAdd(ContactUsBean bean){
        log.info("保存添加信息："+bean.toString());
        Result result = service.AddContact(bean);
        if(result == Result.SUCCESS){
            log.info("保存添加信息成功");
            return new MessageDto(Result.SUCCESS);
        }
        log.info("保存添加信息失败");
        return new MessageDto(Result.FAIL);
    }

    //删除信息
    @RequestMapping(value = "/contactDelete", method = RequestMethod.POST)
    @ResponseBody
    public MessageDto contactDelete(@RequestParam("contact_id") int contact_id){
        Result result = service.DeleteContact(contact_id);
        if(result == Result.SUCCESS){
            log.info("刪除成功");
            return new MessageDto(Result.SUCCESS);
        }
        log.info("刪除失敗");
        return new MessageDto(Result.FAIL);
    }


}
