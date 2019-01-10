package com.nsu.service.student.classmanager.impl;

import com.nsu.bean.student.classmanager.ClassBean;
import com.nsu.dao.student.classmanager.ClassManagerDao;
import com.nsu.service.student.classmanager.ClassManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/18
 * @Time 9:52
 * @Descorption
 */
@Service
public class ClassManagerServiceImpl implements ClassManagerService {
    @Resource
    private ClassManagerDao managerDao;

    @Override
    public List<ClassBean> getAllClass(long id) throws Exception {
        return managerDao.getAllClasses(id);
    }

    @Override
    public Boolean joinClass(long id, long classId, long key) throws Exception {
        if (managerDao.checkKey(classId, key) >= 1) {//口令正确
            return managerDao.joinClass(id, classId) >= 1;
        } else {
            return null;
        }
    }

    @Override
    public boolean isStayClass(long id) throws Exception {
        return managerDao.isStayClass(id) == 1;
    }

}
