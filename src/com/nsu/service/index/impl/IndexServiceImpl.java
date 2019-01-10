package com.nsu.service.index.impl;


import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.ImgBean;
import com.nsu.bean.index.PolicyBean;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.dao.admin.AdminIndexDao;
import com.nsu.dao.index.IndexDao;
import com.nsu.service.index.IIndexService;
import com.nsu.util.jedis.JedisClient;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static net.sf.json.JSONArray.getCollectionType;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IndexServiceImpl
 * @Package com.nsu.service.index.impl
 * @Description:
 * @date 5/26/17
 */
@Service("indexService")
public class IndexServiceImpl implements IIndexService {

    // 设置缓存有效时间，单位秒
    private static final Integer CACHE_TIME = 60*60;
    // 缓存中  getCount key
    private static final String GET_COUNT_ALL = "_GET_COUNT_ALL";
    // 缓存中 getLink key
    private static final String GET_LINK = "_GET_LINK_";
    // 缓存中 getImgList
    private static final String GET_IMG = "_GET_IMG_";
    // 缓存中 getPolicyList
    private static final String GET_POLICY = "_GET_POLICY_";


    @Resource
    private IndexDao indexDao;

    @Resource
    private AdminIndexDao adminIndexDao;

    @Resource
    private JedisClient jedisClientPool;

    /**
     * 获取各个角色的注册量
     * @return
     * @throws Exception
     */
    @Override
    public String getCountAll() throws Exception {
        String countAllJson = jedisClientPool.get(GET_COUNT_ALL);
        if (countAllJson == null || countAllJson.equals("")){
            Map<String,Object> countAll = indexDao.getCountAll();
            countAllJson = JsonMapper.toJsonString(countAll);
            jedisClientPool.set(GET_COUNT_ALL,CACHE_TIME, countAllJson);
        }
        return countAllJson;
    }

    /**
     * 获取链接
     * @param linkType 链接类型
     * @return
     * @throws Exception
     */
    @Override
    public String getLink(String linkType) throws Exception {
        String getLinkJson = jedisClientPool.get(GET_LINK+linkType+"_");
        if (getLinkJson == null || getLinkJson.equals("")){
            getLinkJson = JsonMapper.toJsonString(indexDao.getLink(linkType));
            jedisClientPool.set(GET_LINK+linkType+"_",getLinkJson);
        }
        return getLinkJson;
    }

    /**
     * 获取最新的7个发布的政策
     * @return
     * @throws Exception
     */
    @Override
    public String getPolicyLink() throws Exception {
        String policyList = jedisClientPool.get(GET_POLICY);
        if (policyList == null || policyList.equals("")){
            policyList = JsonMapper.toJsonString(indexDao.getPolicyLink());
            jedisClientPool.set(GET_POLICY,policyList);
        }
        return policyList;
    }

    /**
     * 获取主页轮播图片
     * @return
     * @throws Exception
     */
    @Override
    public List<ImgBean> getImgList() throws Exception {
        String imgList = jedisClientPool.get(GET_IMG);
        if (imgList == null || imgList.equals("")){
            imgList = JsonMapper.toJsonString(adminIndexDao.getRollImg());
            jedisClientPool.set(GET_IMG,CACHE_TIME,imgList);
        }
        JavaType javaType =  JsonMapper.getInstance().getTypeFactory().constructParametricType(List.class,ImgBean.class);
        return JsonMapper.getInstance().fromJson(imgList,javaType);
    }

    /**
     * 获取单个政策的详细信息
     * @param id 主键
     * @return
     * @throws Exception
     */
    @Override
    public PolicyBean getPolicyById(String id) throws Exception {
        return indexDao.getPolicyById(id);
    }

    /**
     * 获取所有政策，为分页用
     * @param pageNum
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<PolicyBean> getPolicyList(Integer pageNum,Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        List<PolicyBean> list = adminIndexDao.getPolicyAll();//DB操作查询数据
        PageInfo<PolicyBean> page = new PageInfo<PolicyBean>(list);
        return page;
    }
}
