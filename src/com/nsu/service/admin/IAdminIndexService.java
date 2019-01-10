package com.nsu.service.admin;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.ImgBean;
import com.nsu.bean.index.LinkBean;
import com.nsu.bean.index.PolicyBean;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IAdminIndexService
 * @Package com.nsu.service.admin
 * @Description:
 * @date 5/15/17
 */
public interface IAdminIndexService {

    /**
     * 获取主页轮播图片
     * @return
     * @throws Exception
     */
    public List<ImgBean> getRollImg() throws Exception;

    /**
     * 修改主页轮播图片
     * @param imgId
     * @param imgPath
     * @return
     * @throws Exception
     */
    public boolean updateRollImg(String imgId,String imgPath) throws Exception;

    /**
     * 保存政策
     * @param policyBean
     * @return
     * @throws Exception
     */
    public boolean savePolicy(PolicyBean policyBean) throws Exception;

    /**
     * 获取政策list
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageInfo<PolicyBean> getPolicyBean(Integer pageNum, Integer pageSize)throws Exception;

    /**
     * 获取具体的政策
     * @param policyBean
     * @return
     * @throws Exception
     */
    public PolicyBean getPolicyById(PolicyBean policyBean) throws Exception;

    /**
     * 保存链接
     * @param linkBean
     * @return
     * @throws Exception
     */
    public boolean saveLink(LinkBean linkBean) throws Exception;

    /**
     * 获取链接list
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     * @throws Exception
     */
    public PageInfo<LinkBean> getLinkBean(Integer pageNum, Integer pageSize,String type)throws Exception;

    /**
     * 获取具体链接
     * @param linkBean
     * @return
     * @throws Exception
     */
    public LinkBean getLinkById(LinkBean linkBean) throws Exception;

    /**
     * 删除链接
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteLinkById(String id,String linkType) throws Exception;

    /**
     * 链接置顶
     * @param id
     * @param linkType
     * @return
     * @throws Exception
     */
    public boolean toTopLinkById(String id,String linkType) throws Exception;


}
