package cn.nsu.edu.web.four.controllers.team;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.CoachInfo;
import cn.nsu.edu.web.four.beans.teams.PlayerCondition;
import cn.nsu.edu.web.four.beans.teams.PlayerConditionForm;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.coach.CoachService;
import cn.nsu.edu.web.four.services.team.CoachInfoService;
import cn.nsu.edu.web.four.services.team.PlayerService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
@Controller
@RequestMapping("team")
public class CoachInfoController {
//    private final static ArrayList<Integer> AllIds = new ArrayList<>();//获得所有不重复得数据
//    private static int allIdsSize;
//    private ThreadLocalID threadLocalID = new ThreadLocalID();
//    private ThreadLocal<ThreadLocalID> threadLocal = new ThreadLocal<>();
//    private  ArrayList<Integer> AllIds = new ArrayList<>();//获得所有不重复得数据
//    private  int allIdsSize;
    private static final int SUBSTR_BEGIN=7;//截取字符串的开始位置
    private static final int SUBSTR_BEGIN1=2;
    //考虑到AllIds是实例变量，并发访问会出现数据异常，采用ThreadLocal来避免
    private Logger log = LoggerFactory.getLogger(getClass());


    @Resource(name = "coachInfoServiceImpl")
    private CoachInfoService coachService;
    @Resource(name = "playerServiceImpl1")
    private PlayerService playerService;
    @Autowired
    private CoachService coachServiceid;

/**
* @author:YuanXin
* @Description:根据姓名查询教练的信息
* @Date: 16:11 2018/3/22/022
**/
    @RequestMapping("/findCoachByName")
    @ResponseBody
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto findCoachByName( HttpServletRequest request,String coachName){

        if (!StringUtils.isEmpty(coachName)){

            if (!coachName.matches(RegexStatic.CHINESE)){
                log.debug("中文");
                return new MessageDto(Result.FAIL);
            }
        }

        //字符解码
        if (coachName == null){
            return  new MessageDto(Result.FAIL);
        }
        Map<String, Object> map = RequestUtil.getLogin(request);
        Integer orgId = ParseUtil.parseInt(map.get(BaseStatic.KEY_ORGANIZATION_ID));
        List<CoachInfo> coachInfos = coachService.findCoachByName(coachName,orgId);
        MessageDto messageDto = new MessageDto(Result.FAIL);
        if (coachInfos==null){
            return messageDto;
        }else {
            messageDto.put("coachInfos",coachInfos);
            messageDto.setStatus(Result.SUCCESS);
            return messageDto;
        }
    }
    /**
    * @author:YuanXin
    * @Description:根据ID查询教练
    * @Date: 14:46 2018/3/22/022
    **/
    @RequestMapping("/findCoachById")
    @ResponseBody
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto findCoachById(String idCoach ){
        if (idCoach == null){
            return  new MessageDto(Result.FAIL);
        }
        Integer id = ParseUtil.parseInt(idCoach);
        Coach coachInfo = coachServiceid.getCoach(id);
        MessageDto messageDto = new MessageDto(Result.FAIL);
        if (coachInfo==null){
            return messageDto;
        }else {
            messageDto.put("coachInfo",coachInfo);
            messageDto.setStatus(Result.SUCCESS);
            return messageDto;
        }
    }
    /**
    * @author:YuanXin
    * @Description:根据多个条件查询学生
    * @Date: 16:11 2018/3/22/022
    **/
    @RequestMapping("/findPlayersByconditions")
    @ResponseBody
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto findPlayersByconditions(HttpServletRequest request, PlayerConditionForm playerCondition, Integer page) throws Exception {
        Integer clazz = null;
        Integer grade1 = null;
        String classes = playerCondition.getClasses();
        if (!StringUtils.isEmpty(classes)){
            classes = classes.trim();
        }
        String name = playerCondition.getName();
        if (!StringUtils.isEmpty(name)){
            name = name.trim();
        }
        String grade = playerCondition.getGrade();
        if (!StringUtils.isEmpty(grade)){
            grade = grade.trim();
        }
        if (!StringUtils.isEmpty(classes)){

            if (classes.matches(RegexStatic.NUMBER)){
                clazz = ParseUtil.parseInt(classes);
            }else {
                log.debug("数字");
                return new MessageDto(Result.FAIL);
            }
        }

        if (!StringUtils.isEmpty(name)){

            if (!name.matches(RegexStatic.CHINESE)){
                log.debug("中文");
                return new MessageDto(Result.FAIL);
            }
        }
        if (!StringUtils.isEmpty(grade)){

            if (grade.matches(RegexStatic.NUMBER)){
                grade1 = ParseUtil.parseInt(grade);
            }else {
                log.debug("数字");
                return new MessageDto(Result.FAIL);
            }
        }
        PlayerCondition playerCondition1 = new PlayerCondition();
        playerCondition1.setClasses(clazz);
        Date date = new Date();
        SimpleDateFormat sc = new SimpleDateFormat("yyyy");
        String year = sc.format(date);
        playerCondition1.setGrade(Integer.parseInt(year)-grade1 + 1);
        playerCondition1.setName(name);


        Integer integer = ParseUtil.parseInt(playerCondition.getClasses());
        Map<String, Object> map = RequestUtil.getLogin(request);
        Integer orgId = (Integer) map.get(BaseStatic.KEY_ORGANIZATION_ID);
        List<Player> playeres;
        PageHelper.startPage(page,BaseStatic.TEAMPAGE_FINDPLAYER);
        if (orgId!=null){
             playeres = playerService.findPlayersByconditions(playerCondition1, orgId);
             if (playeres == null){
                 return  new MessageDto(Result.FAIL);
             }
        }else {
            log.error("组织ID没有获取到");
            MessageDto messageDto = new MessageDto();
            messageDto.setStatus(Result.FAIL);
            return messageDto;
        }
        PageInfo<Player> pageInfo =  new PageInfo<Player>(playeres,BaseStatic.TEAMPAGE_SHOWCODE);
        MessageDto messageDto = new MessageDto(Result.FAIL);
        if (CollectionUtils.isEmpty(playeres)){
            messageDto.setStatus(Result.FAIL);
          return messageDto;
        }else {
            messageDto.put("players",playeres);
            messageDto.put("pageInfo",pageInfo);
            messageDto.setStatus(Result.SUCCESS);
            return messageDto;
        }
    }
    /**
    * @author:YuanXin
    * @Description:根据球员ID查询对应的球员
    * @Date: 18:50 2018/3/22/022
    **/
    @RequestMapping("/findPlayerByPlayerId")
    @ResponseBody
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto findPlayerByPlayerId(String idPlayer ){
        Integer id  = null;
        if (!StringUtils.isEmpty(idPlayer)){

            if (idPlayer.matches(RegexStatic.NUMBER)){
                id = ParseUtil.parseInt(idPlayer);
            }else {
                log.debug("数字");
                return new MessageDto(Result.PARAM_NOT_SUIT);
            }
        }
        Player player = playerService.findPlayerByPlayerId(id);
        MessageDto messageDto = new MessageDto();
        if (player!=null){
            messageDto.put("player",player);
            messageDto.setStatus(Result.SUCCESS);
            return messageDto;
        }else {
            messageDto.setStatus(Result.FAIL);
            return messageDto;
        }
    }
    /**
     * @author:YuanXin
     * @Description:获得所有得教练信息
     * @Date: 8:48 2018/3/23/023
     **/
    @RequestMapping("/findAllCoaches")
    @ResponseBody
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto findAllCoaches(HttpServletRequest request) throws Exception {
        MessageDto messageDto = new MessageDto();
        List<CoachInfo> coachInfos;
//        Integer orgId = 10000;
//        coachInfos = coachService.findAllCoaches(orgId);
        Map<String, Object> map = RequestUtil.getLogin(request);
        Integer orgId = ParseUtil.parseInt(map.get(BaseStatic.KEY_ORGANIZATION_ID));
        if (orgId!=null){
            coachInfos = coachService.findAllCoaches(orgId);
        }else {
            log.error("组织ID没有获取到");
            messageDto.setStatus(Result.FAIL);
            return messageDto;
        }
        if (coachInfos!=null){
            messageDto.put("coachInfos",coachInfos);
            messageDto.setStatus(Result.SUCCESS);
            return messageDto;
        }else {
            messageDto.setStatus(Result.FAIL);
            return messageDto;
        }
    }


    @RequestMapping("/getAllPlayersByIds")
    @ResponseBody
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto getAllPlayersByIds(HttpServletRequest request, String playerids, Integer flag){
        if (StringUtils.isEmpty(playerids)||flag == null){
            log.error("传递的参数为空");
            return new MessageDto(Result.FAIL);
        }

        ArrayList<Integer> AllIds = new ArrayList<>();

        HttpSession session = request.getSession();
        if (session != null){
            AllIds  = (ArrayList<Integer>) session.getAttribute("allID");
            if (AllIds == null){
                AllIds = new ArrayList<>();
            }
        }

//        //用threadlocal来锁定ThreadLocalID对象
//        threadLocal.set(threadLocalID);
//        ThreadLocalID threadLocalID = threadLocal.get();
//        List<Integer> AllIds = threadLocalID.getAllIds();
        if (flag==1){
            AllIds.clear();
        }
        //如果前端没有传入球员的id，就返回空
        if (playerids.equals("[]")){
            log.error("前端没有添加球员id");
            return new MessageDto(Result.FAIL);
        }
        //解码过后得数据
        log.info(playerids);
        //存入这次添加的所有id
        ArrayList<Integer> ids = new ArrayList<>();
        //定义一个临时变量来存储每一次多余得id
        ArrayList<Integer> newIds = new ArrayList<>();
        //上次存入list的大小
        int allIdsSize = AllIds.size();
        if (playerids.indexOf("on")>0){
            //删除末尾的]
            int substrEnd = playerids.length() - 2;
            String substring = playerids.substring(SUBSTR_BEGIN,substrEnd);
            //删除字符串中多余的双引号
            substring = substring.replace("\"","");
            String[] split = substring.split(",");
            for (int i=0;i<split.length;i++){
                ids.add(Integer.parseInt(split[i]));
            }
            if (allIdsSize==0){
                AllIds.addAll(ids);
                newIds.addAll(AllIds);
            } else {
                //用newIds来存储与Allids中不同的元素
                for (int j=0;j<ids.size();j++){
                    if (!AllIds.contains(ids.get(j))){
                        newIds.add(ids.get(j));
                    }
                }
            }
            if (allIdsSize>0){
                //当有新的ids出现时，就要添加到ALLIds中
                if (!CollectionUtils.isEmpty(newIds)){
                    for (int j=0;j<newIds.size();j++){
                        AllIds.add(newIds.get(j));
                    }
                }else {
                    //说明没有新添加的ids
                    return new MessageDto(Result.FAIL);
                }
            }else {
                //说明没有新添加的ids
                if (CollectionUtils.isEmpty(newIds)){
                    return new MessageDto(Result.FAIL);
                }
            }
        }else {
            //删除末尾的]
            int substrEnd = playerids.length() - 2;
            String substring = playerids.substring(SUBSTR_BEGIN1,substrEnd);
            //删除字符串中多余的双引号
            substring = substring.replace("\"","");
            String[] split = substring.split(",");
            for (int i=0;i<split.length;i++){
                ids.add(Integer.parseInt(split[i]));
            }
            if (allIdsSize==0){
                AllIds.addAll(ids);
                newIds.addAll(AllIds);
            } else {
                //用newIds来存储与Allids中不同的元素
                    for (int j=0;j<ids.size();j++){
                        if (!AllIds.contains(ids.get(j))){
                            newIds.add(ids.get(j));
                        }
                    }
            }
            if (allIdsSize>0){
                //当有新的ids出现时，就要添加到ALLIds中
                if (!CollectionUtils.isEmpty(newIds)){
                    for (int j=0;j<newIds.size();j++){
                        AllIds.add(newIds.get(j));
                    }
                }else {
                    //说明没有新添加的ids
                    return new MessageDto(Result.FAIL);
                }
            }else {
                    //说明没有新添加的ids
                if (CollectionUtils.isEmpty(newIds)){
                    return new MessageDto(Result.FAIL);
                }

            }

        }
        //使用session来存储AllIds
//        HttpSession session = request.getSession();
        if (session!=null){
            //每次都覆盖之前的AllIds值
            session.setAttribute("allID",AllIds);
        }else {
            session.setAttribute("allID",AllIds);
        }
        List<Player> players = playerService.findPlayerByPlayerIds(newIds);

        MessageDto messageDto = new MessageDto();
        if (players!=null){
            messageDto.put("players",players);
            messageDto.setStatus(Result.SUCCESS);
            return messageDto;
        }else {
            messageDto.setStatus(Result.FAIL);
            return messageDto;
        }
    }
    /**
    * @author:YuanXin
    * @Description:根据id删除球员，更换状态位
    * @Date: 10:39 2018/3/26/026
    **/
    @RequestMapping("/deletePlayerById")
    @ResponseBody
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto deletePlayerById(HttpServletRequest request,String playerid){
        if (playerid == null){
            return new MessageDto(Result.FAIL);
        }
        ArrayList<Integer> AllIds = new ArrayList<>();
        HttpSession session = request.getSession();
        if (session != null){
            AllIds  = (ArrayList<Integer>) session.getAttribute("allID");
            if (AllIds == null){
                AllIds = new ArrayList<>();
            }
        }
//        threadLocal.set(threadLocalID);
//        ThreadLocalID threadLocalID = threadLocal.get();
//        List<Integer> AllIds = threadLocalID.getAllIds();
        try {
            if (!CollectionUtils.isEmpty(AllIds)){
                Integer id = ParseUtil.parseInt(playerid);
                int index = AllIds.indexOf(id);
                if (index!=-1){
                    AllIds.remove(index);
                }
            }
            if (session!=null){
                //每次都覆盖之前的AllIds值
                session.setAttribute("allID",AllIds);
            }else {
                session.setAttribute("allID",AllIds);
            }
        }catch (Exception e){
            log.error("要删除的球员id为空");
            throw e;
        }

        return  new MessageDto(Result.SUCCESS);
    }
}
