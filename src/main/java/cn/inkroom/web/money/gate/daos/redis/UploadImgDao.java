package cn.inkroom.web.money.gate.daos.redis;

import cn.inkroom.web.money.gate.utils.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UploadImgDao {
    @Autowired
    private RedisPool pool;


    public Integer getInt(String key){
        return ParseUtil.parseInt(pool.get(key)) ;
    }

    public Long getLong(String key){
        return ParseUtil.parseLong(pool.get(key));
    }

    public String getString(String key){
        return pool.get(key);
    }



}
