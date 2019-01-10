package com.nsu.service.team.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.bean.team.OperMessageBean;
import com.nsu.dao.message.MessageDao;
import com.nsu.dao.team.TeamSendVerificationCodeDao;
import com.nsu.service.BaseService;
import com.nsu.service.team.ITeamSendVerificationCodeService;
import com.nsu.util.InfoProUtil;
import com.nsu.util.Mail;
import com.nsu.util.V;

@Service("teamSendVerificationCodeService")
public class TeamSendVerificationCodeServiceImpl extends BaseService implements ITeamSendVerificationCodeService{

	@Autowired
	TeamSendVerificationCodeDao teamSendVerificationCodeDao;
	@Resource
	private MessageDao messageDao;
	
	@Override
	public String findVerificationCode(String aid, int type) {
		try{
			if(type == 0){
				//邮箱验证码
				return teamSendVerificationCodeDao.findEmailVerificationCode(aid);
			}else{
				//手机验证码
				return "9527";
			}
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public boolean sendEmailCode(String aid, String email) {
		int num = 0;
		String code = InfoProUtil.getRandomString(6);
		log.info("验证码：**********:"+ code);
		try{
			if(Mail.sendEmail(email, code)){
				 num = teamSendVerificationCodeDao.updateTeamVerificationCode(aid, code);
			}
			return num>0;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean sendPhoneCode(String phone) {
		String code = InfoProUtil.getRandomString(6);
		try{
//			int num = teamSendVerificationCodeDao.updateTeamVerificationCode(aid, code);
//			return num>0;
			return true;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public String findTeambindingEmail(String aid) {
		try{
			return teamSendVerificationCodeDao.findTeamBindingEmail(aid);
		}catch(Exception e){
			log.error(e.getMessage());
			return "error";
		}
	}

	@Override
	public void sendOperMessage(OperMessageBean messageInfo) {
		String aid = null;
		if(messageInfo.isSenderIDIsAid() == false){
			//发送者id不是a_id
			aid = findAidByRoleId(messageInfo.getSenderID(), messageInfo.getSenderType());
			if(V.checkEmpty(aid)){
				return ;
			}
			messageInfo.setSenderID(aid);
		}
		if(messageInfo.isReceivederIDIsAid() == false){
			//接收者id不是a_id
			aid = findAidByRoleId(messageInfo.getReceivederID(), messageInfo.getReceivederType());
			if(V.checkEmpty(aid)){
				return ;
			}
			messageInfo.setReceivederID(aid);
		}
		//发送个人消息
		try {
			messageDao.insertPersonMessage(messageInfo.getMap());
			log.info(messageInfo.getSenderID()+" 发送消息成功 "+ messageInfo.getReceivederID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 根据角色主键id查询account的aid
	 * @author ljl
	 * @createDate 2017-06-02 09:08:24
	 * @param id （球队id、球员id、教练员id、机构id、赛事id）
	 * @param type 1.球员id、2.教练员id、3.机构id、4. 球队id 、6.赛事id
	 * @return
	 */
	private String findAidByRoleId(String id, int type){
		String aid = null;
		try{
			aid = teamSendVerificationCodeDao.findAidByRoleId(id, type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return aid;
	}
	
	
}
