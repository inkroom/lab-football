package com.nsu.service.index;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.ImgBean;
import com.nsu.bean.index.LinkBean;
import com.nsu.bean.index.PolicyBean;

import java.util.List;
import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IIndexService
 * @Package com.nsu.service.index
 * @Description:
 * @date 5/5/17
 */
public interface IIndexService {
    /**
     * 获取 各个角色的注册量
     * @return
     */
    public String getCountAll() throws Exception;

    /**
     * 获取 链接
     * @param linkType 链接类型
     * @return
     */
    public String getLink(String linkType)throws Exception;

    /**
     * 获取主页上最新的 7 个政策
     * @return
     */
    public String getPolicyLink()throws Exception;

    /**
     * 获取主页轮播图片
     * @return
     * @throws Exception
     */
    public List<ImgBean> getImgList() throws Exception;

    /**
     * 获取单个政策的详细信息
     * @param id 主键
     * @return
     * @throws Exception
     */
    public PolicyBean getPolicyById(String id) throws Exception;


    /**
     *  分页
     * @param pageNum
     * @return
     * @throws Exception
     */
    public PageInfo<PolicyBean> getPolicyList(Integer pageNum, Integer pageSize) throws Exception;
}
