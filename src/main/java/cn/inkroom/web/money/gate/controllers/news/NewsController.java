package cn.inkroom.web.money.gate.controllers.news;

import cn.inkroom.web.money.gate.beans.news.NewsBean;
import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.services.news.NewsService;
import cn.inkroom.web.money.gate.utils.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/news")
@Controller
public class NewsController {
    @Autowired
    private NewsService service;
    @Autowired
    private HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/get")
    @ResponseBody
    public MessageDto get(String id) {
        Integer i_id = ParseUtil.parseInt(id);

        if (i_id == null) {
            return new MessageDto(Result.FAIL, "错误请求");
        }
        if (i_id == -1) {
            return new MessageDto();
        }
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("news", service.getNews(i_id));
        return dto;
    }


    @RequestMapping("/add")
    public String add() {

        return "backstage/addNews";
    }

    @RequestMapping("/set")
    @ResponseBody
    public MessageDto set(String id, String title, String type, String content) {
        if (title == null || content == null || title.isEmpty()||content.isEmpty()) {
            return new MessageDto(Result.PARAM_NOT_SUIT, "新闻标题或内容不能为空");
        }
        Integer i_id = ParseUtil.parseInt(id);
        Integer i_type = ParseUtil.parseInt(type);
        if (i_id == null || i_type == null || i_id < -1 || i_type < 1 || i_type > 3) {
            return new MessageDto(Result.PARAM_NOT_SUIT, "新闻编号或类型不正确");
        }
        NewsBean bean = service.setNews(i_id, i_type, title, content);
        if (bean != null) {
            MessageDto dto = new MessageDto(Result.SUCCESS);
            dto.put("news", bean);
            return dto;
        }
        return new MessageDto(Result.FAIL);
    }


    @RequestMapping("/setFlag")
    @ResponseBody
    public MessageDto set(String id,String flag) {
        Integer i_id = ParseUtil.parseInt(id);
        Integer i_flag = ParseUtil.parseInt(flag);
        log.info(i_id+"");
        log.info(i_flag+"");
        if (i_id == null || i_flag == null || i_id < -1 || i_flag < 0 || i_flag > 1) {
            return new MessageDto(Result.PARAM_NOT_SUIT, "新闻编号或状态不正确");
        }
        boolean bean = service.setFlagById(i_id,i_flag);
        if (bean ) {
            return new MessageDto(Result.SUCCESS);
//            MessageDto dto = new MessageDto(Result.SUCCESS);
//            dto.put("news", bean);
//            return dto;
        }
        return new MessageDto(Result.FAIL);
    }
}