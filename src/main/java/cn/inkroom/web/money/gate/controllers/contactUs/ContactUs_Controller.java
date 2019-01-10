package cn.inkroom.web.money.gate.controllers.contactUs;

import cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean;
import cn.inkroom.web.money.gate.services.contactUs.impl.ContactUsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("contactUs/")
public class ContactUs_Controller {
    @Autowired
    private ContactUsService contactUsService;
    @Autowired
    private HttpServletRequest request;
    protected Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("contactUs")
    public String contactUs(){
        ArrayList<ContactUsBean> beans = contactUsService.getContactUs();
        if(beans == null){
            log.info("beans中信息为空");
        }else{
            for (ContactUsBean bean: beans) {
                log.info(bean.toString());
            }
        }
        request.setAttribute("beans",beans);
        return "contactUs/contactUs";
    }


}
