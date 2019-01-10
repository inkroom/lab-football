package cn.edu.nsu.lib.services.impl;

import cn.edu.nsu.lib.bean.VisitBean;
import cn.edu.nsu.lib.services.common.IpService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/17
 * @Time 20:06
 * @Descorption
 */
@Service
public class IpServiceImpl implements IpService {
    // TODO: 2017/9/17 ip操作未实现
    public VisitBean updateIp(String ip) {
        return null;
    }

    public boolean updateCount(String ip, Date now) {
        return false;
    }

    public int deleteIp(long sec) {
        return 0;
    }
}
