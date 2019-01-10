package com.nsu.service.organization;


import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface OrgLoginService {
    public Map<String, Object> findUser(Map<String, Object> map) throws Exception;

    public void updateTime(Map<String, Object> map);

    public Map getUserInfo(String username, String pushInfo, String deviceInfo);
}
