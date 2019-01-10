package com.nsu.service.coach.impl;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.dao.coach.CoachRegisterDao;
import com.nsu.service.BaseService;
import com.nsu.service.coach.CoachRegisterService;
import com.nsu.util.InfoProtUtil;


@Service
public class CoachRegisterServiceImpl extends BaseService implements CoachRegisterService{
	@Autowired
	private CoachRegisterDao coachRegisterDao;
    private String birth; 
	@Override
	public boolean checkCoachRegIdNum(String idNum) {
		int i = coachRegisterDao.checkCoachRegIdNum(InfoProtUtil.xorInfo(idNum));
		if (i!=0) {//i不等于0说明此身份证已经有对应的信息了
			return false;
		}
		return true;
	}

	@Override
	public boolean checkCoachRegPhone(String phone) {
		int i = coachRegisterDao.checkCoachRegPhone(InfoProtUtil.xorInfo(phone));
		if (i!=0) {//i不等于0说明此手机号已经有对应的信息了
			return false;
		}
		return true;
	}

	
	@Override
	public boolean insertCoachRegister(String IdNum, String passwd, String phone) {
		try {
			coachRegisterDao.addCoachToAccount(InfoProtUtil.xorInfo(IdNum),passwd, InfoProtUtil.xorInfo(phone));
			birth =getUserBirthday(IdNum);
			coachRegisterDao.addCoachToCoach(coachRegisterDao.getCoachAId(InfoProtUtil.xorInfo(phone)),birth);
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * 通过省份证获取生日
	 * 
	 * @param userId
	 * @return
	 * @throws ParseException
	 */
	public String getUserBirthday(String userId) throws ParseException {
		StringBuffer bir = new StringBuffer();
		Pattern p2 = Pattern.compile("\\d{6}(\\d{8}).*"); // 用于提取出生日字符串
		Pattern p3 = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");// 用于将生日字符串进行分解为年月日
		Matcher matcher = p2.matcher(userId);
		boolean b = matcher.find();
		if (b) {
			String s = matcher.group(1);
			Matcher matcher2 = p3.matcher(s);
			if (matcher2.find()) {
				bir.append(matcher2.group(1)).append("-").append(matcher2.group(2)).append("-")
						.append(matcher2.group(3));

			}
		}
		return bir.toString();
	}
	@Override
	public String selectCoachID(String phone) {
		// TODO Auto-generated method stub
		return coachRegisterDao.selectCoachID(InfoProtUtil.xorInfo(phone));
	}
	@Override
	public void addCoachScoreStauts(String selectCoachID) {
		coachRegisterDao.addCoachScoreStauts(selectCoachID);
	}
}

