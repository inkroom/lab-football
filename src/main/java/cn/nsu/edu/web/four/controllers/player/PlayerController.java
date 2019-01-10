package cn.nsu.edu.web.four.controllers.player;

import cn.nsu.edu.web.four.annotation.FileRename;
import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.common.UploadService;
import cn.nsu.edu.web.four.services.organization.OrganizationService;
import cn.nsu.edu.web.four.services.player.PlayerService;
import cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.http.ResponseUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import cn.nsu.edu.web.four.utils.string.WordCheck;
import cn.nsu.edu.web.four.utils.time.GradeJudge;
import com.google.gson.JsonObject;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :王新璋
 * @Description: 球员的web层
 * @date :18.30 2018/3/15
 */

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    UploadService uploadService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlayerService playerService;

    @Autowired
    private OrganizationService organizationService;

    //首页显示
    @RequestMapping("")
    @Security(createToken = true)
    public String indexPlayer(){
        return "player/index";
    }

    //注册显示
    @RequestMapping("/register")
    @Security(createToken = true)
    public String registerPlayer(){
        return "player/register";
    }


    //登录个人信息
    @RequestMapping(value="/login")
    @Security(checkToken = true)
    @ResponseBody
    public MessageDto userlogin(String username, String password,String imagecode) {
        //检验是否为空
        if ((StringUtils.isEmpty(username))||(StringUtils.isEmpty(password))||(StringUtils.isEmpty(imagecode))) {
            return new MessageDto(Result.PARAM_NOT_EMPTY);
        }
        //检验验证码
        String imagescore=(String)session.getAttribute(BaseStatic.KEY_SESSION_CODE);
        if(session.getAttribute(BaseStatic.KEY_SESSION_CODE)==null){
            return new MessageDto(Result.CODE_NOT_EXISTS);
        }
        log.info("传入的："+imagecode+"///验证码："+imagescore);
        if(!(imagescore.toUpperCase().equals(imagecode.toUpperCase())))
        {
            return new MessageDto(Result.CODE_NOT_CORRECT);
        }
        //用户名检验
        if (!(WordCheck.isNumeric(username,RegexStatic.REGEX)||WordCheck.isNumeric(password,RegexStatic.REGEX)
                ||(WordCheck.checkLength(username)>5&&WordCheck.checkLength(username)<13)||(WordCheck.checkLength(password)>5&&WordCheck.checkLength(username)<21)
                ||(WordCheck.isIllagel(username))||(WordCheck.isIllagel(password))
                )){
            log.info("有非法字符或其他错误");
            return new MessageDto(Result.ILLEGAL_EXIST);
        }
        //删除验证码session
        if(session.getAttribute(BaseStatic.KEY_SESSION_CODE)!=null){
            session.removeAttribute(BaseStatic.KEY_SESSION_CODE);
        }
        //检验数据库时候有
        Player player=playerService.selectPlayerByUserName(username);
        log.info("Player信息："+player);
        if (player==null||player.getStatus()==2){
            return new MessageDto(Result.PLAYER_DEL);
        }
        //检验密码
        String clientpass=Md5EncryptUtil.parseMd5(password,player.getSalt());
        log.info("数据库密码："+player.getPassword()+"加密之后的输入密码："+clientpass);
        if(!(player.getPassword()).equals(clientpass)){
            return new MessageDto(Result.FAIL);
        }
        Map<String, Object> account=new HashMap<>();
        account.put(BaseStatic.KEY_PLAYER_ID,player.getIdPlayer());
        account.put(BaseStatic.KEY_MAP_NAME,player.getName());
        RequestUtil.login(request,account, Role.PLAYER);
        return new MessageDto(Result.SUCCESS);
    }

    //注册球员信息
    @RequestMapping(value="/register1",method= RequestMethod.POST)
    @Security(checkToken = true)
    @ResponseBody
    public MessageDto registerPlayer(@Validated Player player, String imageCode, String schoolCode, BindingResult result) {

        if(result.hasErrors()){
            return new MessageDto(Result.ILLEGAL_EXIST);
        }
        if(!(player.getUserName()!=null||WordCheck.isIllagel(player.getUserName()))){
            return new MessageDto(Result.FAIL);
        }
        //写全
        log.info(schoolCode+"stone:Player信息："+player.toString());
        String imagescore = (String)session.getAttribute(BaseStatic.KEY_SESSION_CODE);
        if(imagescore==null){
            return new MessageDto(Result.CODE_NOT_EXISTS);
        }
        if (!(imageCode.toUpperCase().equals(imagescore.toUpperCase()))) {
            return new MessageDto(Result.CODE_NOT_CORRECT);
        }
        if(playerService.selectPlayerByUserName(player.getUserName())!=null){
            return new MessageDto(Result.PLAYER_EXIST);
        }
        //删除验证码session
        if(session.getAttribute(BaseStatic.KEY_SESSION_CODE)!=null){
            session.removeAttribute(BaseStatic.KEY_SESSION_CODE);
        }
        //班级判断
        log.info("registerPlayer:"+player.toString());
        Organization o=organizationService.selectOrganizationBySc(ParseUtil.parseInt(schoolCode));
        if (o!=null){
            player.setOrgId(o.getIdOrg());
            /*未删除为0*/
            player.setStatus(0);
            if(player.getGrade()>0){
                player.setGrade(GradeJudge.setGradeYear(player.getGrade()));
            }
            int i=playerService.insertPlayer(player);
            if(i==1){
                return new MessageDto(Result.SUCCESS);
            }
        }
        return new MessageDto(Result.FAIL);

    }

    //查看个人信息
    @RequestMapping(value="/list")
    @Security(roles = Role.PLAYER)
    public String getPlayerById(Model model) {

        Map<String, Object> loginMap=RequestUtil.getLogin(request);
        if (loginMap!=null){
            if (loginMap.containsKey(BaseStatic.KEY_PLAYER_ID)){
                Integer idPlayer=ParseUtil.parseInt(loginMap.get(BaseStatic.KEY_PLAYER_ID).toString());
                Player playerlist=playerService.selectPlayerById(idPlayer);
                if(playerlist.getGrade()>0){
                    playerlist.setGrade(GradeJudge.setGradeYear(playerlist.getGrade()));
                }
                log.info("球员信息2"+playerlist);
                String orgname=playerService.selectOrgNameById(idPlayer);
                List<String> teamNames=playerService.selectTeamNameById(idPlayer);
              /*  log.info(teamNames.toString());*/
                model.addAttribute("item", playerlist);
                model.addAttribute("orgname", orgname);
//                teamNames
                //判断是否有球队
                if (teamNames!=null) {
                    model.addAttribute("teamnames", teamNames);
                }else{
                    model.addAttribute("teamnames", "没有球队！");
                }
            }
        }
        return "player/playerlist";
    }

    //更新单个球员信息
    @RequestMapping(value="/update")
    @Security(roles = Role.PLAYER,checkToken = true)
    @ResponseBody
    public  MessageDto editPlayerById(@Validated Player player,String oldPassword,BindingResult result){
        log.info("stone");
        if(result.hasErrors()){
            return new MessageDto(Result.ILLEGAL_EXIST);
        }
        log.info(player.toString());
        if((StringUtils.isEmpty(player.getSex())||StringUtils.isEmpty(oldPassword))){
            return new MessageDto(Result.PARAM_NOT_EMPTY);
        }
        log.info("oldPassword:"+oldPassword+WordCheck.isIllagel(oldPassword)
        +" Sex:"+player.getSex()+WordCheck.isNumeric(Integer.toString(player.getSex()),"[0-1]")
        +"idcard"+player.getIdCard()+WordCheck.isNumeric(player.getIdCard(),RegexStatic.ID_CARD));
        if (!((!WordCheck.isIllagel(oldPassword)))&&WordCheck.isNumeric(player.getIdCard(),RegexStatic.ID_CARD)&&WordCheck.isNumeric(Integer.toString(player.getSex()),"[0-1]")
        ){
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Map<String, Object> loginMap= RequestUtil.getLogin(request);
        if (loginMap!=null){
            if (loginMap.containsKey(BaseStatic.KEY_PLAYER_ID)){
                int idPlayer=ParseUtil.parseInt(loginMap.get(BaseStatic.KEY_PLAYER_ID).toString());
                Player player1=playerService.selectPlayerById(idPlayer);
                //检验密码
                log.info("stone1"+oldPassword);
                String clientpass=Md5EncryptUtil.parseMd5(oldPassword,player1.getSalt());
                log.info("数据库密码："+player1.getPassword()+"加密之后的输入密码："+clientpass);
                log.info("stone1");
                log.info(clientpass+"原密码"+player1.getPassword());
                if(!(player1.getPassword()).equals(clientpass)){
                    return new MessageDto(Result.FAIL);
                }
                log.info("stone2");
                player.setSex(ParseUtil.parseInt(player.getSex()));
                player.setIdPlayer(idPlayer);
                player.setGrade(GradeJudge.setGradeYear(player.getGrade()));
//                player.setOrgId(organization.getIdOrg());
                //密码加密在service层
                log.info("editPlayerById:"+player);
                int i=playerService.updatePlayerById(player);
                if(i==1){
                    return new MessageDto(Result.SUCCESS);
                }
            }
        }
        return new MessageDto(Result.PARAM_NOT_SUIT);
    }



    //修改图片
    @ResponseBody
    @RequestMapping(value = "updatePhoto", method = RequestMethod.POST)
    @Security(roles = Role.PLAYER, checkToken = true)
    public MessageDto updatePhoto(@RequestParam("file") CommonsMultipartFile file, Coach coach) {
        Map<String, Object> logingMap = RequestUtil.getLogin(request);
        Integer idPlayer = ParseUtil.parseInt(logingMap.get(BaseStatic.KEY_PLAYER_ID).toString());
        if (idPlayer != null) {
            String headpic = uploadService.upload(file, request, 204800, new FileRename() {
                @Override
                public String rename(MultipartFile file, HttpServletRequest request) {
                    return "head/" + idPlayer + ".jpeg";
                }
            });
            if (headpic!= null) {
                String headpic1="head/"+ idPlayer + ".jpeg";
                log.info("stone23" + headpic1);
                int i = playerService.updatePlayerImageById(idPlayer, headpic);
                if (i == 1) {
                    MessageDto dto= new MessageDto(Result.SUCCESS);
                    dto.put("src",headpic1);
                    return dto;
                }
            }
        }
        return new MessageDto(Result.FAIL);
    }

}
