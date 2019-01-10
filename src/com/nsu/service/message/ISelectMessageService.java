/**
 * @Title: ISelectMessageService.java 
 * @Package com.nsu.service.message.impl  
 * @Description: 查询消息接口 
 * @author 朱明民 
 * @date 2017年4月13日 下午3:37:08
 * @version V1.0 
 */
package com.nsu.service.message;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.message.MessageBean;
import com.nsu.bean.message.MessagePersonBean;
import com.nsu.bean.message.MessageReadBean;

/** 
 * @ClassName: ISelectMessageService   
 * @Description: 查询消息接口 
 * <详细介绍>  
 * @date 2017年4月13日 下午3:37:08   
 * @author 朱明民  
 *   
 */
public interface ISelectMessageService {
	
	/**
	 * 根据用户ID查询已读消息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<MessageReadBean> getMessageReadById(int id) throws Exception;
	
	/**
	 * 根据消息ID查询系统消息内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMessageContentById(String id) throws Exception;
	
	/**
	 * 根据消息ID查询个人消息内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMessageContentPersonById(String id) throws Exception;
	
	/**
	 * 根据用户ID查询未读消息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<MessageBean> getUnreadMessageById(int id) throws Exception;
	
	
	/**
	 * 查询未读个人消息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<MessagePersonBean> getPersonMsgByID(int id) throws Exception;//查询个人未读消息

	/**
	 * 将个人未读消息标记插入
	 * @param A_ID
	 * @param PS_ID
	 * @throws Exception
	 */
	public void insertPersonMsg(int A_ID, int PS_ID) throws Exception;
	
	/**
	 * 将已读消息标记插入用户消息表
	 * @param A_ID
	 * @param SM_ID
	 * @throws Exception
	 */
	public void insertUserMessage(int A_ID, int SM_ID) throws Exception;
	
	/**
	 * 批量已读系统消息
	 * @param A_ID
	 * @throws Exception
	 */
	public void allReadMessage(int A_ID) throws Exception;
	
	/**
	 * 批量已读个人消息
	 * @param A_ID
	 * @throws Exception
	 */
	public void allReadMessagePerson(int A_ID) throws Exception;
	
	
	/**
	 * 插入个人消息
	 * @param map
	 * @throws Exception
	 */
	public void insertPersonMessage(Map<String, Object> map) throws Exception;
	
	/**
	 * 批量插入训练消息
	 * @param map
	 * @throws Exception
	 */
	public void insertTrainingMessage(List<MessagePersonBean> list) throws Exception;
}
