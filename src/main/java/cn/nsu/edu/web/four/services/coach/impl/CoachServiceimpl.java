package cn.nsu.edu.web.four.services.coach.impl;

import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.coach.MTeamCoach;
import cn.nsu.edu.web.four.daos.jdbc.coach.CoachDao;
import cn.nsu.edu.web.four.daos.jdbc.coach.MTeamCoachDao;
import cn.nsu.edu.web.four.services.coach.CoachService;
import cn.nsu.edu.web.four.services.common.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: four
 * @description: 教练增删查改实现
 * @author: ZhuShengpeng
 * @create: 2018-03-21 20:09
 **/

@Service
public class CoachServiceimpl implements CoachService {

    @Autowired
    public CoachDao coachDao;

    @Autowired
    public MTeamCoachDao mTeamCoachDao;

    @Autowired
    public UploadService uploadService;

    @Autowired
    private HttpServletRequest request;


    //添加单个教练
    @Override
    public int addCoach(Coach coach) {
        try {
            coach.setStatus(0);
            return coachDao.addCoach(coach);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    //修改教练的就职状态
    @Override
    public int coachStatus(Integer idCoach) {
        try {
            Coach coach=coachDao.getCoach(idCoach);
            if (coach.getStatus() == 1) {
                coach.setStatus(0);
            } else {
                coach.setStatus(1);
            }
            return coachDao.coachStatus(coach);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    //获取全部教练的信息
    @Override
    public List<Coach> getAllCoach(Integer org_id, int status) {
        try {
            return coachDao.getAllCoach(org_id, status);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //获取单个教练的信息
    @Override
    public Coach getCoach(Integer id_coach) {
        try {
            Coach coach = coachDao.getCoach(id_coach);
            //获取年龄
            String idCard = coach.getIdcard();
            String birth = idCard.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int age = Integer.parseInt(year) - Integer.parseInt(birth);
            coach.setAge(age);
            return coach;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //修改教练的基础信息
    @Override
    public int updateCoach(Coach coach) {
        try {
            return coachDao.updateCoach(coach);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    //修改头像
    @Override
    public int updatePhoto(Coach coach) {
        try {

            return coachDao.updatePhoto(coach);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //获取教练任职信息
    @Override
    public List<MTeamCoach> getAllTeamByCoach(Integer coach_id) {
        try {
            return mTeamCoachDao.getAllTeamByCoach(coach_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
