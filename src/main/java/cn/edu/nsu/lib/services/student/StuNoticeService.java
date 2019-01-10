package cn.edu.nsu.lib.services.student;

import cn.edu.nsu.lib.bean.student.StuNoticeBean;

import java.util.List;

/**
 * @author ChenGang
 * @Title: StuNoticeService
 * @Package cn.edu.nsu.lib.services.student
 * @Description:
 * @date 2017/9/27 0027 上午 10:54
 **/
public interface StuNoticeService {
     List<StuNoticeBean> findNoticeList() throws Exception;
    List<StuNoticeBean> findNoticeListByid(int id) throws Exception;
    int setIdentity(String stuId,String identity) throws Exception;
}
