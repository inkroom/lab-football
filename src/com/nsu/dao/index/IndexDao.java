package com.nsu.dao.index;

import com.nsu.bean.index.LinkBean;
import com.nsu.bean.index.PolicyBean;

import java.util.List;
import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IndexDao
 * @Package com.nsu.dao.index
 * @Description:
 * @date 5/26/17
 */
public interface IndexDao {
    /**
     * 获取 各个角色的注册量
     * @return
     */
    public Map<String,Object> getCountAll() throws Exception;

    /**
     * 获取 链接
     * @param linkType 链接类型
     * @return
     */
    public List<LinkBean> getLink(String linkType) throws Exception;

    /**
     * 获取主页上最新的 7 个政策
     * @return
     */
    public List<PolicyBean> getPolicyLink() throws Exception;


    /**
     * 获取单个政策的详细信息
     * @param id 主键
     * @return
     * @throws Exception
     */
    public PolicyBean getPolicyById(String id) throws Exception;

    /**
     * 获取所有政策，为分页用
     * @return
     * @throws Exception
     */
    public List<PolicyBean> getPolicyList() throws Exception;
}
