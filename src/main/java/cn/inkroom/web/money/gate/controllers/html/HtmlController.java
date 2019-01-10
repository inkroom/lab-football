package cn.inkroom.web.money.gate.controllers.html;

import cn.inkroom.web.money.gate.beans.common.HtmlBean;
import cn.inkroom.web.money.gate.daos.jdbc.html.HtmlDao;
import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.services.html.HtmlService;
import cn.inkroom.web.money.gate.utils.ParseUtil;
import cn.inkroom.web.money.gate.utils.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/html/")
public class HtmlController {
//    @RequestMapping(value = {"partyAndUnion"})
//    public String home(){
//
//    }


    private static final HashMap<String, Integer> keys = new HashMap<>();

    public HtmlController() {
        keys.put("partyAndUnion", 1);
        keys.put("OSdata", 2);
        keys.put("recruit", 3);
        keys.put("goInto", 4);
        keys.put("aboutUs", 5);
    }


    private Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HtmlService service;

    @RequestMapping({"partyAndUnion", "OSdata", "recruit", "goInto", "aboutUs"})
    public String home() {
        List<HtmlBean> unions = null;

        String lastUrl = RequestUtil.getLastUrl(request);
        log.info("获取到的" + lastUrl);
        log.info("存储" + keys.toString());

        log.info("获取" + keys.get(lastUrl));
        unions = service.getAllTypeHtml(keys.getOrDefault(lastUrl, -1));

        request.setAttribute("lastUrl", lastUrl);

//        if (request.getRequestURI().contains("partyAndUnion")) {
//            unions = service.getAllTypeHtml(1);
//        } else if (request.getRequestURI().contains("OSdata")) {
//            unions = service.getAllTypeHtml(2);
//        }else if (request.getRequestURI().contains("recruit")){
//            unions = service.getAllTypeHtml(3);
//        }

        if (unions == null) {

        }
        log.info("获取的数据" + unions.toString());
        request.setAttribute("unions", unions);


//        return lastUrl + "/" + lastUrl;
        return "common/html";
//        if (request.getRequestURI().contains("partyAndUnion"))
//            return "partyAndUnion/partyAndUnion";
//        else if (request.getRequestURI().contains("OSdata"))
//            return "OSdata/OSdata";
//        else if (request.getRequestURI().contains("recruit")){
//            return "OSdata/OSdata";
//        }
//        return "partyAndUnion/partyAndUnion";
    }


    @RequestMapping("/html")
    @ResponseBody
    public MessageDto html(String id) {

        Integer i_id = ParseUtil.parseInt(id);
        if (i_id == null) {
            return new MessageDto(Result.FAIL, "错误请求");
        }

        HtmlBean bean = service.getHtml(i_id);
        if (bean == null) {
            return new MessageDto(Result.FAIL, "没有对应的数据");
        }
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("union", bean);


        return dto;
    }

    @RequestMapping("/get")
    @ResponseBody
    public MessageDto getHtml(String owner, String type) {

        log.info("开始getHtml"+System.currentTimeMillis());
        Integer i_owner = ParseUtil.parseInt(owner);
        Integer i_type = ParseUtil.parseInt(type);
        if (i_owner == null || i_type == null) {
            return new MessageDto(Result.FAIL, "错误请求");
        }

        HtmlBean bean = service.getHtml(i_owner, i_type);
        if (bean == null) {
            return new MessageDto(Result.FAIL, "没有对应的数据");
        }
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("html", bean.getContent());
        log.info("结束getHtml"+System.currentTimeMillis());
        return dto;
    }

    @RequestMapping("/set")
    @ResponseBody
    public MessageDto setHtml(String owner, String type, String content, String title, String url,String test) {

        // TODO: 18-3-6 xss清理


        log.info("开始");
        Integer i_owner = ParseUtil.parseInt(owner);
        Integer i_type = ParseUtil.parseInt(type);
        if (i_owner == null || i_type == null) {
            return new MessageDto(Result.FAIL, "错误请求");
        }
        if (content.length() > 50000) {
            return new MessageDto(Result.FAIL, "内容过多");
        }

        try {
            boolean bean = service.setHtml(i_owner, i_type, content, title, url,test);
            if (!bean) {
                return new MessageDto(Result.FAIL, "保存失败，请重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDto(Result.FAIL, "系统错误，保存失败，请联系管理人员");
        }
        log.info("结束了");
        MessageDto dto = new MessageDto(Result.SUCCESS);
        return dto;
    }
}
