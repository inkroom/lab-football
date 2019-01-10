package cn.nsu.edu.web.four.controllers.live;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.live.LiveMessageBean;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.handler.socket.LiveWebSocket;
import cn.nsu.edu.web.four.handler.spring.PropertiesPlaceholder;
import cn.nsu.edu.web.four.services.common.UploadService;
import cn.nsu.edu.web.four.services.live.LiveService;

import cn.nsu.edu.web.four.services.referee.RefereeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/live/")
public class LiveController {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LiveService service;
    @Autowired
    private RefereeService refereeService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private StringRedisTemplate redis;
    @Autowired
    private PropertiesPlaceholder placeholder;

    @RequestMapping(value = {"/{sch:[1-9]+[0-9]*}/m/index", "/{sch:[1-9]+[0-9]*}/index"})
    @Security
    public String live(@PathVariable int sch) throws Exception {
//        if (sch == null) {
//            return "forward:";
//        }
        // TODO: 18-3-16 获取赛程信息
        log.info("schId = " + sch);

        //获取赛程信息
        ScheduleInformationDto information = (ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION);
//        ScheduleInformationDto information = refereeService.getScheduleInformation(sch);

        if (information == null || information.getScheduleInformation() == null) {
            return "forward:/404";
        }

//        information.setScheduleInformation(refereeService.getScheduleInformation(information.getScheduleInformation().getIdSchedule()));


        request.getSession().setAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION, information);
        request.setAttribute("info", information);
        boolean desc = !request.getRequestURI().contains("m");//顺序，如果是pc端，那么需要时间从后往前排
        //获取直播信息
        List<LiveMessageBean> messages = service.getAllMessage(sch, desc);
        request.setAttribute("messages", messages);
        if (messages.size() > 0)
            request.setAttribute("message", messages.get(0));

        if (request.getRequestURI().contains("m")) {//是否是手机端
            return "/live/liveMobile";
        } else {//pc端
            return "live/live";
        }

    }


    @RequestMapping("{sch:[1-9]+[0-9]*}/send")
//    @SendTo("/live")
    @ResponseBody
    @Security(checkToken = true)
    public MessageDto sendLiveMessage(String content, String imgPath, @PathVariable int sch) {

        if (StringUtils.isEmpty(content) && StringUtils.isEmpty(imgPath)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }

        String phone = (String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_LIVE_PHONE);
        log.info("取出来的" + phone);
        //判断是否完成手机验证
        if (phone != null) {
            LiveMessageBean result = service.addMessage(sch, phone, content == null ? "" : content, imgPath);
            if (result != null) {
//                    log.info("message = "+res);
//                request.getSession().setAttribute("temp_liveMessage", bean);
                MessageDto dto = new MessageDto(Result.SUCCESS);
                dto.put("message", result);
                dto.put(BaseStatic.KEY_SOCKET_LIVE_TYPE, 1);//发送消息
                dto.put(BaseStatic.KEY_SCHEDULE_ID, sch);
                //往socket发送消息
                redis.convertAndSend(placeholder.getValue("redis.topic.socket"), dto.toString());
//                LiveWebSocket.sendMessage(dto.toString());
                return dto;
            } else {
                return new MessageDto(Result.FAIL);
            }
        } else {
            return new MessageDto(Result.NO_AUTHORITY);
        }


    }

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "{sch:[1-9]+[0-9]*}/image")
    @ResponseBody
    @Security(checkToken = true)

    public MessageDto imageUpload(MultipartFile file, @PathVariable int sch) {

        String result = uploadService.upload(file, request, (file1, request) -> {
            return BaseStatic.PREFIX_LIVE_DIRECTORY + sch + File.separator + System.currentTimeMillis() + ".jpeg";
//            Calendar c = Calendar.getInstance();
//            return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-" + sch + File.separator + System.currentTimeMillis() + ".jpeg";
        });
        log.debug(uploadService.getMessage());
        if (result != null) {
            MessageDto dto = new MessageDto(Result.SUCCESS);
            dto.put("path", result);
            return dto;
        }
        return new MessageDto(Result.FAIL);
    }

    @RequestMapping("ssss")
    public String socket() {
        request.setAttribute("sockets", LiveWebSocket.getList());
        return "live/socket";
    }


    @RequestMapping("{sch:[1-9]+[0-9]*}/is")
    @ResponseBody
    @Security(checkToken = true)
    public MessageDto is() {

        return new MessageDto(request.getSession().getAttribute(BaseStatic.KEY_SESSION_LIVE_PHONE) == null ? Result.FAIL : Result.SUCCESS);
    }
}
