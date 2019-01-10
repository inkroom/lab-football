package com.nsu.dao.admin;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.ImgBean;
import com.nsu.bean.index.LinkBean;
import com.nsu.bean.index.PolicyBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: AdminIndexDao
 * @Package com.nsu.dao.admin
 * @Description:
 * @date 5/15/17
 */
public interface AdminIndexDao {
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
    public boolean updateRollImg(@Param("imgId") String imgId,@Param("imgPath") String imgPath) throws Exception;

    /**
     * 保存政策
     * @param policyBean
     * @return
     * @throws Exception
     */
    public boolean savePolicy(PolicyBean policyBean) throws Exception;


    /**
     * 获取政策list
     * @return
     * @throws Exception
     */
    public List<PolicyBean> getPolicyAll()throws Exception;


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
     * @param type
     * @return
     * @throws Exception
     */
    public List<LinkBean> getLinkPageAll(@Param("type")String type)throws Exception;

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
    public boolean deleteLinkById(String id) throws Exception;

    /**
     * 链接置顶
     * @param id
     * @param linkType
     * @return
     * @throws Exception
     */
    public boolean toTopLinkById(@Param("id") String id,@Param("linkType") String linkType) throws Exception;
}
