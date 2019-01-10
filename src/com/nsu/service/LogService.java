package com.nsu.service;

import org.springframework.stereotype.Service;

@Service
public class LogService extends BaseService {

    private static final String SAVE_LOGS = "insert into log (ip,addr,host,time,system_type,username) values (?,?,?,now(),?,?)";

    public void saveLogs(String ip, String addr, String host, String type, String username) {
        jt.update(SAVE_LOGS, new Object[]{ip, addr, host, type, username});
    }


    private static final String SAVE_COUNTS = "UPDATE school_count SET count_num = count_num + 1, curent_time = now() WHERE school_url = ?";

    public void saveCounts(String username) {
        jt.update(SAVE_COUNTS, username);
    }


}
