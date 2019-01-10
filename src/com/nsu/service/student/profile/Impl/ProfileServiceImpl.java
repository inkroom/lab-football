package com.nsu.service.student.profile.Impl;

import com.nsu.bean.student.compute.ComputeViewBean;
import com.nsu.bean.student.profile.ProfileBean;
import com.nsu.dao.student.profile.ProfileDao;
import com.nsu.service.student.profile.ProfileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ProfileServiceImpl implements ProfileService {

    @Resource
    ProfileDao profileDao;

    @Override
    public ProfileBean getStudentProfileById(long i_id) {
        return profileDao.getStudentProfileById(i_id);
    }

    @Override
    public String getStudentClassNameById(long s_id) {
        return profileDao.getStudentClassNameById(s_id);
    }

    @Override
    public List<ComputeViewBean> getStudentExamById(long s_id) {
        return profileDao.getStudentExamById(s_id);
    }

    @Override
    public List<String> getPastSchoolNameById(long s_id) {
        return profileDao.getPastSchoolNameById(s_id);
    }
}
