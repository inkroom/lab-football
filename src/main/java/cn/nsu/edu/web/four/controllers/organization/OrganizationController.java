package cn.nsu.edu.web.four.controllers.organization;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.common.SMSService;
import cn.nsu.edu.web.four.services.organization.OrganizationService;
import cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import cn.nsu.edu.web.four.utils.string.WordCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :王新璋
 * @date ${Date:2018/3/20} ${Time}
 **/
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private SMSService smsService;

    @Autowired
    HttpSession session;


    //登录显示
    @RequestMapping(value = "")
    @Security(createToken = true)
    public String showIndex() {
        return "organization/index";
    }

    //注册显示
    @RequestMapping(value = "/register")
    @Security(createToken = true)
    public String showRegister() {
        return "organization/register";
    }

    //注册显示
    @RequestMapping(value = "/showorg")
    @ResponseBody
    public MessageDto showOrg(String countrycode, String villagecode) {
        MessageDto dto = new MessageDto(Result.SUCCESS);
        log.info("传入的值：" + countrycode + villagecode);
        if (StringUtils.isEmpty(countrycode) && StringUtils.isEmpty(villagecode)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        if (!(WordCheck.isNumeric(countrycode, RegexStatic.NUMBER) || WordCheck.isNumeric(villagecode, RegexStatic.NUMBER))) {
            return new MessageDto(Result.ILLEGAL_EXIST);
        }
        //删除验证码session
        session.removeAttribute(BaseStatic.KEY_SESSION_CODE);
        Organization organization = new Organization();
        organization.setCountryCode(ParseUtil.parseInt(countrycode));
        organization.setVillageCode(ParseUtil.parseInt(villagecode));
        List<Organization> o = organizationService.selectOrganizationByPCV(organization);
        if (o != null) {
            dto.put("OrgList", o);
        }
        return dto;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Security(checkToken = true)
    @ResponseBody
    public MessageDto login(String schoolcode, String password, String imagecode) {
        if (StringUtils.isEmpty(imagecode)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        String imagescore = (String) session.getAttribute(BaseStatic.KEY_SESSION_CODE);
        if (imagescore == null) {
            return new MessageDto(Result.CODE_NOT_EXISTS);
        }
        log.info("session的验证码：" + imagescore + "传入的验证码:" + imagecode);
        if (!(imagescore.equalsIgnoreCase(imagecode))) {
            log.info("session的验证码：" + imagescore.toUpperCase() + "传入的验证码:" + imagecode.toUpperCase());
            return new MessageDto(Result.CODE_NOT_CORRECT);
        }
        //检验是否为空
        if ((StringUtils.isEmpty(schoolcode)) || (StringUtils.isEmpty(password))) {
            return new MessageDto(Result.PARAM_NOT_EMPTY);
        }
        //删除验证码session
        session.removeAttribute(BaseStatic.KEY_SESSION_CODE);
        //检验数据库时候有
        Organization organization = organizationService.selectOrganizationBySc(ParseUtil.parseInt(schoolcode));

        //检验密码
        if (organization == null) {
            return new MessageDto(Result.FAIL);
        }
        log.info(organization.toString());
        if (!(organization.getPassword()).equals(Md5EncryptUtil.parseMd5(password, organization.getSalt()))) {
            log.info("密码验证出错！");
            return new MessageDto(Result.FAIL);
        }
        Map<String, Object> account = new HashMap<>();
        account.put(BaseStatic.KEY_ORGANIZATION_ID, organization.getIdOrg());
        account.put(BaseStatic.KEY_MAP_NAME, organization.getName());
        if (organization.getType() == 0) {
            RequestUtil.login(request, account, Role.ORGANIZATION);
        }
        if (organization.getType() == 1) {
            RequestUtil.login(request, account, Role.SCHOOL);
        }
        return new MessageDto(Result.SUCCESS);
    }

    @RequestMapping(value = "/register1", method = RequestMethod.POST)
    @Security(checkToken = true)
    @ResponseBody
    public MessageDto register(String phone, String password, String schoolcode, String imagecode) {
        if (StringUtils.isEmpty(imagecode) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(schoolcode)) {
            return new MessageDto(Result.PARAM_NOT_EMPTY);
        }
        String imagescore = (String) session.getAttribute(BaseStatic.KEY_SESSION_CODE);
        if (imagescore == null) {
            return new MessageDto(Result.CODE_NOT_EXISTS);
        }
        if (!(imagecode.toUpperCase().equals(imagescore.toUpperCase()))) {
            return new MessageDto(Result.CODE_NOT_CORRECT);
        }
        if (!((WordCheck.isNumeric(phone, RegexStatic.PHONE))
                || (WordCheck.isIllagel(password)) || (WordCheck.isNumeric(schoolcode, RegexStatic.NUMBER))
                || ((WordCheck.checkLength(password) > 5 && WordCheck.checkLength(password) < 21)))) {
            return new MessageDto(Result.ILLEGAL_EXIST);
        }
        Organization o1 = organizationService.selectOrganizationBySc(ParseUtil.parseInt(schoolcode));
        if (o1 != null) {
            if (o1.getStatus() == 1) {
                log.info("已经存在此机构！");
                return new MessageDto(Result.ORG_EXIST);
            } else {
                Organization organization = new Organization();
                organization.setType(2);
                organization.setSchoolCode(ParseUtil.parseInt(schoolcode));
                organization.setPassword(password);
                organization.setPhone(ParseUtil.parseLong(phone));
                organization.setStatus(1);
                int i = organizationService.updateOrganizationBySc(organization);
                if (i == 1) {
                    return new MessageDto(Result.SUCCESS);
                }
            }
        }
        return new MessageDto(Result.FAIL);
    }

    @RequestMapping(value = "/checkphone")
    public void sms(String code) {
        smsService.checkCode(code, request);
        log.info("验证码是" + request.getSession().getAttribute(BaseStatic.KEY_SESSION_PHONE));
    }

    @RequestMapping(value = "/logout")
//    @ResponseBody
    public String logout() throws Exception {
        RequestUtil.logout(request);
//        return new MessageDto(Result.SUCCESS);
        if (request.getRequestURI().contains("player")) {
            return "redirect:/player";
        } else if (request.getRequestURI().contains("referee"))
            return "redirect:/referee/login";
        return "redirect:/organization";
    }

}