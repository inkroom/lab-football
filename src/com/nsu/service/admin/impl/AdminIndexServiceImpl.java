package com.nsu.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.ImgBean;
import com.nsu.bean.index.LinkBean;
import com.nsu.bean.index.PolicyBean;
import com.nsu.dao.admin.AdminIndexDao;
import com.nsu.service.admin.IAdminIndexService;
import com.nsu.util.jedis.JedisClient;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: AdminIndexServiceImpl
 * @Package com.nsu.service.admin.impl
 * @Description:
 * @date 5/15/17
 */
@Service(value = "adminIndexService")
public class AdminIndexServiceImpl implements IAdminIndexService {


    // 缓存中  getCount key
    private static final String GET_COUNT_ALL = "_GET_COUNT_ALL";
    // 缓存中 getLink key
    private static final String GET_LINK = "_GET_LINK_";
    // 缓存中 getImgList
    private static final String GET_IMG = "_GET_IMG_";
    // 缓存中 getPolicyList
    private static final String GET_POLICY = "_GET_POLICY_";

    //注入adminIndexDao
    @Resource
    private AdminIndexDao adminIndexDao;


    @Resource
    private JedisClient jedisClientPool;

    /**
     * 获取主页轮播图片
     * @return
     * @throws Exception
     */
    @Override
    public List<ImgBean> getRollImg() throws Exception {
        return adminIndexDao.getRollImg();
    }

    /**
     * 修改主页轮播图片
     * @param imgId
     * @param imgPath
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateRollImg(String imgId, String imgPath) throws Exception {
        jedisClientPool.del(GET_IMG);
        return adminIndexDao.updateRollImg(imgId,imgPath);
    }

    /**
     * 保存政策
     * @param policyBean
     * @return
     * @throws Exception
     */
    @Override
    public boolean savePolicy(PolicyBean policyBean) throws Exception {
        jedisClientPool.del(GET_POLICY);
        return adminIndexDao.savePolicy(policyBean);
    }

    /**
     * 获取政策list
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<PolicyBean> getPolicyBean(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
        List<PolicyBean> list = adminIndexDao.getPolicyAll();//DB操作查询数据
        PageInfo<PolicyBean> pageInfo=new PageInfo<PolicyBean>(list); //将查询到的数据封装到List中
        return pageInfo;
    }

    /**
     * 获取具体的政策
     * @param policyBean
     * @return
     * @throws Exception
     */
    @Override
    public PolicyBean getPolicyById(PolicyBean policyBean) throws Exception {
        return adminIndexDao.getPolicyById(policyBean);
    }

    /**
     * 保存链接
     * @param linkBean
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveLink(LinkBean linkBean) throws Exception {
        jedisClientPool.del(GET_LINK+linkBean.getLinkType()+"_");
        return adminIndexDao.saveLink(linkBean);
    }

    /**
     * 获取链接list
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<LinkBean> getLinkBean(Integer pageNum, Integer pageSize, String type) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        List<LinkBean> linkBeans = adminIndexDao.getLinkPageAll(type);
        PageInfo<LinkBean> pageInfo = new PageInfo<LinkBean>(linkBeans);
        return pageInfo;
    }

    /**
     * 获取具体链接
     * @param linkBean
     * @return
     * @throws Exception
     */
    @Override
    public LinkBean getLinkById(LinkBean linkBean) throws Exception {
        return adminIndexDao.getLinkById(linkBean);
    }

    /**
     * 删除链接
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteLinkById(String id,String linkType) throws Exception {
        return adminIndexDao.deleteLinkById(id);
    }

    /**
     * 链接置顶
     * @param id
     * @param linkType
     * @return
     * @throws Exception
     */
    @Override
    public boolean toTopLinkById(String id,String linkType) throws Exception {
        jedisClientPool.del(GET_LINK+linkType+"_");
        return adminIndexDao.toTopLinkById(id,linkType);
    }


}
