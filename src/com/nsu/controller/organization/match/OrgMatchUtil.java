package com.nsu.controller.organization.match;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by violetEye on 2017/4/30.
 */
public class OrgMatchUtil extends BaseController{

    /**
     * 得到用户的Map
     * @param session
     * @return
     */
    public Map<String, Object> getUserMap(HttpSession session) {
        Map<String, Object> user = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
        return user;
    }

    /**
     * 判断用户是否存在:true:不存在；false:存在
     * @param session
     * @param redirectAttributes
     * @param userMap
     * @return
     */
    public boolean isUserExist(HttpSession session, RedirectAttributes redirectAttributes,
                                Map<String, Object> userMap) {
        return isMapEmpty(redirectAttributes, getUserMap(session));
    }

    /**
     * 判断用户的Map是否为空:true:不为空；false:为空
     * @param redirectAttributes
     * @param userMap
     * @return
     */
    private boolean isMapEmpty(RedirectAttributes redirectAttributes, Map<String, Object> userMap) {
        boolean mask = false;
        if(userMap == null || userMap.isEmpty()){
            setFlashParameter("modifyMatch_msg","登录失效，请重新登录",redirectAttributes);
            mask = true;
        }
        return mask;
    }
    /**
     * 将赛事相关的信息保存到Map中
     * @param match_name
     * @param person_num
     * @param person_sex
     * @param match_level
     * @param match_startDate
     * @param match_endDate
     * @param enroll_startDate
     * @param enroll_endDate
     * @return
     */
    public Map<String, String> putMatchInfoToMap(String match_name, String person_num, String person_sex,
           String match_big_level, String match_level, String match_startDate, String match_endDate,
           String enroll_startDate, String enroll_endDate) {
        Map<String, String> matchInfo = new HashMap<String, String>();
        //将前台传过来的值转换成数据库中字段对应的值
        person_num = getCorrectPersonNum(person_num);
        person_sex = getCorrectPersonSex(person_sex);
        match_level = getCorrectMatchLevel(match_level);
        match_big_level = getCorrectMatchBigLevel(match_big_level);

        //将这些字段添加到Map中
        matchInfo.put("match_name", match_name);
        matchInfo.put("person_num", person_num);
        matchInfo.put("person_sex", person_sex);
        matchInfo.put("match_big_level", match_big_level);
        matchInfo.put("match_level", match_level);
        matchInfo.put("match_startDate", match_startDate);
        matchInfo.put("match_endDate", match_endDate);
        matchInfo.put("enroll_startDate", enroll_startDate);
        matchInfo.put("enroll_endDate", enroll_endDate);
        return matchInfo;
    }
    /**
     * 将前台传过来的match_big_level转换成数据库中字段对应的值
     * @param match_big_level
     * @return
     */
    private String getCorrectMatchBigLevel(String match_big_level) {
        //赛事级别	1:省; 2:市; 3:县; 4:校; 5:不限
        if(match_big_level.equals("matchBigLevel_no")){
            match_big_level = "5";
        } else if(match_big_level.equals("matchBigLevel_province")){
            match_big_level = "1";
        } else if(match_big_level.equals("matchBigLevel_city")){
            match_big_level = "2";
        } else if(match_big_level.equals("matchBigLevel_county")){
            match_big_level = "3";
        } else if(match_big_level.equals("matchBigLevel_school")){
            match_big_level = "4";
        } else{
            match_big_level = "5";
        }
        return match_big_level;
    }

    /**
     * 将前台传过来的match_level转换成数据库中字段对应的值
     * @param match_level
     * @return
     */
    private String getCorrectMatchLevel(String match_level) {
        //match_level	1:小学; 2:中学; 3:高中; 4:大学; 5:不限
        if(match_level.equals("matchLevel_no")){
            match_level = "5";
        } else if(match_level.equals("matchLevel_primarySchool")){
            match_level = "1";
        } else if(match_level.equals("matchLevel_juniorSchool")){
            match_level = "2";
        } else if(match_level.equals("matchLevel_highSchool")){
            match_level = "3";
        } else if(match_level.equals("matchLevel_university")){
            match_level = "4";
        } else{
            match_level = "5";
        }
        return match_level;
    }

    /**
     * 将前台传过来的person_sex转换成数据库中字段对应的值
     * @param person_sex
     * @return
     */
    private String getCorrectPersonSex(String person_sex) {
        //person_sex	1:男子; 2:女子; 3:不限
        if(person_sex.equals("personSex_no")){
            person_sex = "3";
        } else if(person_sex.equals("personSex_male")){
            person_sex = "1";
        } else if(person_sex.equals("personSex_female")){
            person_sex = "2";
        } else{
            person_sex = "3";
        }
        return person_sex;
    }

    /**
     * 将前台传过来的person_num转换成数据库中字段对应的值
     * @param person_num
     * @return
     */
    private String getCorrectPersonNum(String person_num) {
        //person_num	1: 5人; 2:7人; 3:11人 ; 4:不限
        if(person_num.equals("personNum_no")){
            person_num = "4";
        } else if(person_num.equals("personNum_five")){
            person_num = "1";
        } else if(person_num.equals("personNum_seven")){
            person_num = "2";
        } else if(person_num.equals("personNum_eleven")){
            person_num = "3";
        } else{
            person_num = "4";
        }
        return person_num;
    }

    public Map<String, String> getTransformation(Map<String, String> map) {
        Map<String, String> info = new HashMap<>();
        //格式转化
        String person_num = null;
        if (map.get("person_num").equals("1"))
            person_num = "5人";
        else if (map.get("person_num").equals("2"))
            person_num = "7人";
        else if (map.get("person_num").equals("3"))
            person_num = "11人";
        else if (map.get("person_num").equals("4"))
            person_num = "不限";
        String match_level = null;
        if (map.get("match_level").equals("1"))
            match_level = "小学";
        else if (map.get("match_level").equals("2"))
            match_level = "中学";
        else if (map.get("match_level").equals("3"))
            match_level = "高中";
        else if (map.get("match_level").equals("4"))
            match_level = "大学";
        else if (map.get("match_level").equals("5"))
            match_level = "不限";
        String person_sex = null;
        if (map.get("person_sex").equals("1"))
            person_sex = "男子";
        else if (map.get("person_sex").equals("2"))
            person_sex = "女子";
        else if (map.get("person_sex").equals("3"))
            person_sex = "不限";
        String match_big_level = null;
        if (map.get("match_big_level").equals("1"))
            match_big_level = "省级";
        else if (map.get("match_big_level").equals("2"))
            match_big_level = "市级";
        else if (map.get("match_big_level").equals("3"))
            match_big_level = "县级";
        else if (map.get("match_big_level").equals("4"))
            match_big_level = "校级";
        else if (map.get("match_big_level").equals("5"))
            match_big_level = "不限";
        info.put("person_num", person_num);
        info.put("person_sex",person_sex);
        info.put("match_big_level",match_big_level);
        info.put("match_level",match_level);
        return info;
    }
}
