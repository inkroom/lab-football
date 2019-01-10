package cn.inkroom.web.money.gate.controllers.cooperator;

import cn.inkroom.web.money.gate.beans.cooperator.CooperatorBean;
import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.services.cooperator.CooperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CooperatorController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CooperatorService service;

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 前台查看合作单位
     * @param id
     * @return
     */
    @RequestMapping("/cooperator/{id:[1-9]*[0-9]+}")
    public String index(@PathVariable int id) {

        CooperatorBean cooperator = service.getCooperator(id);
        if (cooperator == null) {
            return "common/404";
        }

        request.setAttribute("cooperator", cooperator);
        return "cooperator/cooperator";
    }

    /**
     * 后台合作单位总览页
     * @return
     */
    @RequestMapping("/backstage/cooperator")
    public String backIndex() {

        List<CooperatorBean> list = service.getCooperatorList();
        request.setAttribute("coors", list);

        return "backstage/cooperatorIndex";
    }

    /**
     * 删除合作单位
     * @param id
     * @return
     */
    @RequestMapping("/backstage/cooperator/{id:[1-9]*[0-9]+}/del")
    @ResponseBody
    public MessageDto backIndexDel(@PathVariable int id) {

        int result = service.delCooperator(id);
        if (result != -1) {
            return new MessageDto(Result.SUCCESS);
        }
        return new MessageDto(Result.FAIL);
    }

    /**
     * 前往修改或添加合作哦单位页面
     * @param id
     * @return
     */
    @RequestMapping(value = {"/backstage/cooperator/{id:[1-9]*[0-9]+}/update", "backstage/cooperator/add"}, method = RequestMethod.GET)
    public String backIndexUpdate(@PathVariable(required = false) Integer id) {
        if (id != null) {
            CooperatorBean bean = service.getCooperator(id);
            if (bean == null)
                return "common/404";
            request.getSession().setAttribute("coor", bean);
            request.setAttribute("id",id);
        }
        return "backstage/cooperatorEdit";
    }

    /**
     * 修改或添加合作单位
     * @param name
     * @param content
     * @param id
     * @return
     */
    @RequestMapping(value = {"/backstage/cooperator/{id:[1-9]*[0-9]+}/update", "backstage/cooperator/add"}, method = RequestMethod.POST)
    @ResponseBody
    public MessageDto backIndexUpdate(String name, String content, @PathVariable(required = false) Integer id) {

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(content)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        int result;
        if (id == null && request.getRequestURI().contains("add")) {
            result = service.addCooperator(name, content);
        } else {
            request.setAttribute("id",id);
            result = service.updateCooperator(name, content, id);
        }
        if (result != -1) {
            return new MessageDto(Result.SUCCESS);
        }
        return new MessageDto(Result.FAIL);
    }

    /**
     * 获取单个合作单位
     * @param id
     * @return
     */
    @RequestMapping(value = {"/backstage/cooperator/{id:[1-9]*[0-9]+}/get"}, method = RequestMethod.GET)
    @ResponseBody
    public MessageDto backIndexGet(@PathVariable Integer id) {
        CooperatorBean bean = (CooperatorBean) request.getSession().getAttribute("coor");
        if (bean == null) {
            bean = service.getCooperator(id);
            if (bean == null)
                return new MessageDto(Result.FAIL);
        }
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("coor", bean);
        return dto;
    }
}
