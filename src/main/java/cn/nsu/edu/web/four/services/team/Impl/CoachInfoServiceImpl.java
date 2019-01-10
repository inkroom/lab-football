package cn.nsu.edu.web.four.services.team.Impl;

import cn.nsu.edu.web.four.beans.teams.CoachInfo;
import cn.nsu.edu.web.four.daos.jdbc.team.CoachInfoMapper;
import cn.nsu.edu.web.four.services.team.CoachInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
@Service("coachInfoServiceImpl")
public class CoachInfoServiceImpl implements CoachInfoService {
    @Autowired
    private CoachInfoMapper coachInfoMapper;

    @Override
    public List<CoachInfo> findCoachByName(String coachName,Integer orgId) {
        return coachInfoMapper.findCoachByName(coachName,orgId);
    }

    @Override
    public CoachInfo findCoachById(Integer idCoach) {
        CoachInfo coachInfo = coachInfoMapper.selectCoachById(idCoach);
        return coachInfo;
    }

    @Override
    public List<CoachInfo> findAllCoaches(Integer orgId) {
       return coachInfoMapper.selectAllCoaches(orgId);
    }
}
