package cn.edu.nsu.lib.services.common;

import cn.edu.nsu.lib.bean.VisitBean;

import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/17
 * @Time 20:04
 * @Descorption
 */
public interface IpService {
    public VisitBean updateIp(String ip);

    public boolean updateCount(String ip, Date now);

//    public VisitBean getVisitBean(String ip){
//        int count = ipDao.updateIp(ip);
//        if (count)
//    }

//    public void deleteIp()

    public int deleteIp(long sec);
}
