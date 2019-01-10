/**
 * @Title: SelectMessageServiceImpl.java 
 * @Package com.nsu.service.message.impl  
 * @Description: TODO(描述文件用途) 
 * @author 朱明民 
 * @date 2017年4月13日 下午3:43:06
 * @version V1.0 
 */
package com.nsu.service.message.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.bean.message.MessageBean;
import com.nsu.bean.message.MessagePersonBean;
import com.nsu.bean.message.MessageReadBean;
import com.nsu.dao.message.MessageDao;
import com.nsu.service.message.ISelectMessageService;

/** 
 * @ClassName: SelectMessageServiceImpl   
 * @Description: 查询消息实现类
 * <详细介绍>  
 * @date 2017年4月13日 下午3:43:06   
 * @author 朱明民  
 *   
 */

@Service("SelectMessage")
public class SelectMessageServiceImpl implements ISelectMessageService{

	@Resource
	private MessageDao messageDao;

	/**
	 * 查询系统已读消息
	 */
	public List<MessageReadBean> getMessageReadById(int id) throws Exception {
		// TODO Auto-generated method stub
		List<MessageReadBean> list = messageDao.getMessageReadById(id);
		return list;
	}

	/**
	 * 查询系统未读消息
	 */
	public List<MessageBean> getUnreadMessageById(int id) throws Exception {
		// TODO Auto-generated method stub
		List<MessageBean> list = messageDao.getUnreadMessageById(id);
		return list;
	}

	/**
	 * 查询系统消息内容
	 */
	public Map<String, Object> getMessageContentById(String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> list = messageDao.getMessageContentById(id);
		return list;
	}

	/**
	 * 将未读系统消息标记插入用户消息表
	 */
	public void insertUserMessage(int A_ID, int SM_ID) throws Exception {
		// TODO Auto-generated method stub
		messageDao.insertUserMessage(A_ID, SM_ID);
	}



	/**
	 * 批量已读系统消息
	 */
	@Override
	public void allReadMessage(int A_ID) throws Exception {
		// TODO Auto-generated method stub
		messageDao.allReadMessage(A_ID);
	}

	/**
	 * 查询个人未读消息
	 */
	@Override
	public List<MessagePersonBean> getPersonMsgByID(int id) throws Exception {
		// TODO Auto-generated method stub
		List<MessagePersonBean> list = messageDao.getPersonMsgByID(id);
		return list;
	}

	/**
	 * 将未读个人消息标记插入用户消息表
	 */
	@Override
	public void insertPersonMsg(int A_ID, int PS_ID) throws Exception {
		// TODO Auto-generated method stub
		messageDao.insertPersonMsg(A_ID, PS_ID);
	}

	/**
	 * 批量已读个人消息
	 */
	@Override
	public void allReadMessagePerson(int A_ID) throws Exception {
		// TODO Auto-generated method stub
		messageDao.allReadMessagePerson(A_ID);
	}

	/**
	 * 查询个人消息内容
	 */
	@Override
	public Map<String, Object> getMessageContentPersonById(String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> list = messageDao.getMessageContentPersonById(id);
		return list;
	}

	/**
	 * 插入个人消息(map)
	 * #{PS_RECEIVE}接收人ID, #{PS_SEND_ID}发送人ID, #{PS_TITLE}消息标题, #{PS_TEXT}消息内容
	 */
	@Override
	public void insertPersonMessage(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		messageDao.insertPersonMessage(map);
	}

	/**
	 * 批量插入训练消息(MessagePersonBean)
	 */
	@Override
	public void insertTrainingMessage(List<MessagePersonBean> list) throws Exception {
		// TODO Auto-generated method stub
		messageDao.insertTrainingMessage(list);
	}

	
	

}
