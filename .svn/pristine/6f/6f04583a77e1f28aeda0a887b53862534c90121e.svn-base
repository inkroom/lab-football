package cn.nsu.edu.web.four.utils.time;

import cn.nsu.edu.web.four.utils.string.ParseUtil;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : 王新璋
 * @Date : 2018/4/8 9:18
 * @Description :
 **/
public class GradeJudge {

    //到数据库存年级
    public static Integer setGradeYear(Integer grade){
        if(grade==null){
            return -1;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        Date date=new Date();
        Integer finalgrade=ParseUtil.parseInt(sdf.format(date).toString())+1-grade;
        System.out.println("数据库所存年级："+finalgrade.toString());
        return finalgrade;
    }

    //从数据库取年级
    public static Integer getGradeYear(Integer dbgrade){
        if(dbgrade==null){
            return -1;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        Date date=new Date();
        Integer realgrade=ParseUtil.parseInt(sdf.format(date).toString())+1-dbgrade;
        System.out.println("数据库取出转换后的年级："+realgrade.toString());
        return realgrade;
    }
}
