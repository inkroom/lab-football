package cn.nsu.edu.web.four.controllers.team;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.teams.TeamInfos;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.team.PlayerService;
import cn.nsu.edu.web.four.services.team.TeamInfosToCoachService;
import cn.nsu.edu.web.four.services.team.TeamInfosToPlayerService;
import cn.nsu.edu.web.four.services.team.TeamInfosToTeamService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
public class TeamInfoController {
    @Resource(name = "teamInfosToTeamServiceImpl")
    private TeamInfosToTeamService teamInfosToTeamService;
    @Resource(name = "teamInfosToCoachServiceImpl")
    private TeamInfosToCoachService teamInfosToCoachService;
    @Resource(name = "teamInfosToPlayerServiceImpl")
    private TeamInfosToPlayerService teamInfosToPlayerService;
    @Resource(name = "playerServiceImpl1")
    private PlayerService playerService;

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @author:YuanXin
     * @Description:添加一只球队得基本信息
     * @Date: 10:35 2018/3/22/022
     **/
    @RequestMapping("/addTeamInfo")
    @ResponseBody
    @Security(roles = Role.SCHOOL , checkToken = true)
    public MessageDto addTeamInfo(HttpServletRequest request, TeamInfos teamInfos){
        MessageDto messageDto = new MessageDto(Result.SUCCESS);

        if (StringUtils.isEmpty(teamInfos.getTeamName().trim())||StringUtils.isEmpty(teamInfos.getTeamCoachId())||StringUtils.isEmpty(teamInfos.getTeamClazz())){
            log.error("提交创建表单信息不通过");
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }



        Map<String, Object> map = RequestUtil.getLogin(request);
        Integer orgId = (Integer) map.get(BaseStatic.KEY_ORGANIZATION_ID);
        teamInfos.setOrgId(orgId);
        if (teamInfosToCoachService.findCoachByOrg(teamInfos) ==null){
            return new MessageDto(Result.FAIL);
        }
        //
        //插入球队的信息
        HttpSession session = request.getSession();
        ArrayList<Integer> allID = (ArrayList<Integer>) session.getAttribute("allID");
        //封装ids
        teamInfos.setPlayerIds(allID);
        //判断现在添加的球队是否已经存在,根据球队的名字来查询要插入球队对应的球队ID
        String teamName = teamInfos.getTeamName();
        Integer dbTeamId = teamInfosToTeamService.findTeamInfo(teamName);
       /* if (dbTeamId == null){
            return  new MessageDto(Result.FAIL);
        }*/
        //创建一个零时空间存储不重复的id
        ArrayList<Integer> insertIds = new ArrayList<>();
        //说明是第一次创建该球队
        if (dbTeamId==null){
            //先添加球队信息
            int result = teamInfosToTeamService.addTeamInfo(teamInfos);
            if (result>0){
                //插入教练与team的映射表  教练ID
                Integer row = teamInfosToCoachService.addCoachInfo(teamInfos);
                if (row == 0){
                    return new MessageDto(Result.FAIL);
                }
            }else {
                log.error("添加球队信息失败");
                return new MessageDto(Result.FAIL);
            }
            //插入球员与team的映射表  球员ID
            //allID里面如果没有数据，就不能添加球员
            if (!CollectionUtils.isEmpty(allID)){
                Integer teamId = teamInfos.getTeamId();
                Integer row = teamInfosToPlayerService.addPlayerInfo(allID,teamId);
                if (row ==0){
                    return  new MessageDto(Result.FAIL);
                }
                return messageDto;
            }
        }else {
            //说明该球队已经被创建,把teamid传入teamInfos中，为添加教练这里做判断
//            request.setAttribute("teamDuplication","球队名已存在，如果是要继续添加球员，那么请忽视，否则请重新赋球队名！");
             teamInfos.setTeamId(dbTeamId);
            //查询现在插入的球队已经有的球员
            List<Integer> dbPlayerids = teamInfosToPlayerService.findAllPlayersByTeamId(dbTeamId);
            //删除与要添加的所有球员相同的id   allID是要插入的球员id   dbPlayerids是数据库中已经有的id
            if (!CollectionUtils.isEmpty(allID)){
                for(int i=0;i<allID.size();i++){
                    //如果没有，就插入该球队
                    if (!dbPlayerids.contains(allID.get(i))){
                        insertIds.add(allID.get(i));
                    }
                }
            }
            //如果还有要添加的球员，就插入该队
            if (!CollectionUtils.isEmpty(insertIds)){
                teamInfosToPlayerService.addPlayerInfo(insertIds,dbTeamId);
                return messageDto;
            }
        }
        messageDto.setStatus(Result.SUCCESS);
        return messageDto;
    }
    /**
    * @author:YuanXin
    * @Description:查询数据是否存在该队名
    * @Date: 16:42 2018/3/28/028
    **/

    @RequestMapping("/findTeamName")
    @ResponseBody
    @Security(roles = Role.SCHOOL , checkToken = true)
    public MessageDto findTeamName(String teamName){

        if (StringUtils.isEmpty(teamName.trim())){
                return new MessageDto(Result.FAIL);
        }

        Integer result = teamInfosToTeamService.findTeamInfo(teamName);
        if (result==null){
            return new MessageDto(Result.SUCCESS);
        }else {
            return new MessageDto(Result.FAIL);
        }
    }
}
