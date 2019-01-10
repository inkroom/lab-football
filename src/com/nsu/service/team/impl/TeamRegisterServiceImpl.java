package com.nsu.service.team.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.bean.team.TeamRegisterDO;
import com.nsu.controller.BaseController;
import com.nsu.dao.team.TeamRegisterDao;
import com.nsu.service.team.ITeamRegisterService;
import com.nsu.util.InfoProUtil;
import com.nsu.util.V;

/**
* @ClassName: 球队注册
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 侯润达
* @date 2017年4月10日 下午10:03:38 
*
 */
@Service("teamRegisterService")
public class TeamRegisterServiceImpl extends BaseController implements ITeamRegisterService{
	
	@Resource 
	TeamRegisterDao teamRegisterDao;
	
	@Override
	public String insertRegisterTeamManager(TeamRegisterDO teamUserInfo, String phoneCode) {
		
		String result = checkAllRegisterData(teamUserInfo, phoneCode);
		//校验数据合法信息
		if(result.equals("success") == false){
			return result;
		}else{
			//生成球队编号
			String code = generationTeamNum();
			if(V.checkEmpty(code) == true){
				return "服务器忙，请稍后再试";
			}
			teamUserInfo.setTeamNum(code);
			//存储数据
			try{
				//向account插入数据
				log.info("插入！！！！" + teamUserInfo);
				int num = teamRegisterDao.insertTeamRegisterInfoIntoAccount(teamUserInfo);
				
				if(num==1){
					//向team插入数据
					log.info("teamId: "+ teamUserInfo.getA_ID());
					num = teamRegisterDao.insertTeamTable(teamUserInfo);
					if(num==1){
						//向球队积分表插入数据
						num = teamRegisterDao.insertTeamScoreTable(teamUserInfo.getTEAM_ID(), teamUserInfo.getPhone());
						if(num==1){
							return "success";
						}else{
							return "服务器忙，请稍后再试";
						}
					}else{
						return "服务器忙，请稍后再试";
					}
				}else{
					return "服务器忙，请稍后再试";
				}
			}catch(Exception e){
				
				log.error(e.getMessage());
				return "服务器忙，请稍后再试";
			}
		}
	}
	
	/**
	 * 校验数据是否非法
	 * @author ljl
	 * @createDate 2017-04-11 15:16:26
	 * @param teamUserInfo
	 * @return 返回处理结果
	 */
	public String checkAllRegisterData(TeamRegisterDO teamUserInfo, String phoneCode){
		//验证非法数据
		//身份证
		if(V.checkEmpty(teamUserInfo.getUser())==true || V.checkIDCard(teamUserInfo.getUser())==false ){
			return "无效的身份证";
		}
			//密码是否一致
		if(teamUserInfo.getPasswd().equals(teamUserInfo.getPasswd2()) == false){
			return "两次输入密码不一致";
		}
			//手机号
		if(V.checkPhone(teamUserInfo.getPhone()) == false){
			return "请输入正确的手机号";
		}
//		//手机验证码校验
//		if(phoneCode.equals("0") == false){
//			return "手机验证码错误";
//		}
		
		String phone = InfoProUtil.xorInfo(teamUserInfo.getPhone());
		teamUserInfo.setPhone(phone);
		//验证手机号是否被注册过
		if(findPhoneIsAvailable(teamUserInfo.getPhone()) == false){
			return "手机号已被注册";
		}
		
		String idCard = InfoProUtil.xorInfo(teamUserInfo.getUser());
		teamUserInfo.setUser(idCard);
		//身份证是否被使用过
		if(findTeanIDCardIsAvailable(teamUserInfo.getUser()) == false){
			return "身份证不可用";
		}
		
		return "success";
	}
	
	/**
	 * 校验手机号是否被注册了
	 * @author ljl
	 * @createDate 2017-04-11 18:21:41
	 * @param phone
	 * @return
	 */
	public boolean findPhoneIsAvailable(String phone){
		try{
			int count = teamRegisterDao.findPhoneNum(phone);
			if(count == 0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			
			log.error(e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean findTeanIDCardIsAvailable(String idCard) {
		try{
			int count = teamRegisterDao.findTeamIDCardIsAvailable(idCard);
			if(count == 0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			
			log.error(e.getMessage());
			return false;
		}
	}
	/**
	 * 获取一个唯一的球队编号
	 * @author ljl
	 * @createDate 2017-04-18 17:14:25
	 * @return
	 */
	public String generationTeamNum(){
		String code = null;
		int num = -1;
		int record = 10;
		while(record>0){
			//生成随机的10位字符串
			code = InfoProUtil.getRandomString(10);
			//验证是否别使用
			try{
				num = teamRegisterDao.findIsOnlyCode(code);
				if(num == 0){
					break;
				}
			}catch(Exception e){
				new RuntimeException("生成随机字符失败");
				log.error(e.getMessage());
			}
			record--;
		}
		return code;
	}
	
}
