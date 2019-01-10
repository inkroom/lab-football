package com.nsu.service.organization.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.dao.organization.OrgLoginDao;
import com.nsu.service.BaseService;
import com.nsu.service.organization.OrgLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgLoginServiceImpl extends BaseService implements OrgLoginService {


    @Resource
    private OrgLoginDao dao;

    // 返回信息
    private final String successCode = "200";
    private final String errorCode = "404";
    private final String systemError = "查询失败，请稍后查看！";


    @Override
    public Map<String, Object> findUser(Map<String, Object> map) throws Exception {

        Map<String, Object> user = dao.findUser(map);
        return user;

    }

    @Override
    public void updateTime(Map<String, Object> map) {
        dao.insertTime(map);
    }


    /*
     * @Description: TODO(用户查询成功，返回机构名,电话，赛事，赛程)
     * @return     返回类型
     * @throws
     */
    @Override
    public Map getUserInfo(String username, String pushInfo, String deviceInfo) {

        try {
            Map<String, Object> mobile = new HashMap<String, Object>();
            mobile.put("pushInfo", pushInfo);
            mobile.put("deviceInfo", deviceInfo);
            mobile.put("username", username);



            log.info("deviceInfo====---------------"+deviceInfo);
             /*更新手机信息*/
            dao.updateMobileInfo(mobile);


            /*机构名，机构电话*/
            Map<String, Object> map = dao.findMobileInfo(username);

//            /*1.得倒机构ID查询所有赛事*/
//            List<Map<String, Object>> list = dao.mobileFindAllComp(username);
//
            Map temp_map = new HashMap();
            temp_map.put("OrgPhoneAndName", map);
//            temp_map.put("saishi", list);


            log.info("hello world------> "+temp_map);
            return temp_map;



         //   pageInfo = new PageInfo<Map<String,Object>>(list);
         /*2.手机查询赛事下有哪些赛程*/
            /*for (int i = 0; i <pageInfo.getList().size(); i++) {
                List<Map<String, Object>> com_list = new ArrayList<>();
                List<Map<String, Object>> saicheng = dao.mobileFindRaceDetails(pageInfo.getList().get(i).get("COM_ID"), username);
                List<Map<String, Object>> saicheng1 = new ArrayList<>();

                for (int j = 0; j < saicheng.size(); j++) {
                    Map<String, Object> temp1 = new HashMap<String, Object>();
                    Object r_h_team_name = dao.findTeamName(saicheng.get(j).get("R_H_TEAM_ID"));
                    Object r_v_team_name = dao.findTeamName( saicheng.get(j).get("R_V_TEAM_ID"));
                    temp1.put("R_H_NAME",r_h_team_name);
                    temp1.put("R_V_NAME",r_v_team_name);
                    temp1.put("R_H_TIME",saicheng.get(j).get("R_START_TIME"));
                    temp1.put("R_V_TIME",saicheng.get(j).get("R_END_TIME"));
                    saicheng1.add(j,temp1);
                }

                pageInfo.getList().get(i).put("saicheng", saicheng1);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
