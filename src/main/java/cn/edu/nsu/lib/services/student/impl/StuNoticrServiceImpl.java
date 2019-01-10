package cn.edu.nsu.lib.services.student.impl;

import cn.edu.nsu.lib.bean.student.StuNoticeBean;
import cn.edu.nsu.lib.dao.student.StuNoticeDao;
import cn.edu.nsu.lib.services.student.StuNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenGang
 * @Title: StuNoticrServiceImpl
 * @Package cn.edu.nsu.lib.services.student.impl
 * @Description:
 * @date 2017/9/27 0027 上午 10:57
 **/
@Service
public class StuNoticrServiceImpl implements StuNoticeService {
    @Autowired
    StuNoticeDao stuNoticeDao;

    @Override
    public List<StuNoticeBean> findNoticeList() throws Exception {

        return stuNoticeDao.findNoticeList();
    }

    @Override
    public List<StuNoticeBean> findNoticeListByid(int id) throws Exception {
        return stuNoticeDao.findNoticeListByid(id);
    }

    @Override
    public int setIdentity(String stuId, String identity) throws Exception {
        try {
            return stuNoticeDao.setIdentity(stuId, Integer.parseInt(identity));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
