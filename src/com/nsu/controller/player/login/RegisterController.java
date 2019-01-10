package com.nsu.controller.player.login;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.player.login.PlayerLoginService;
import com.nsu.util.FormatPattern;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.VerifyUtil;
import com.nsu.util.jedis.JedisClient;

/**
 * 
 * @ClassName RegisterController
 * @Description (球员注册模块)
 * @author hm
 * @Date 2017年4月10日 下午5:03:42
 * @version 1.0.0
 */
@RequestMapping(value = "/player")
@Controller
public class RegisterController extends BaseController {
	private final String login_page = "/player/login/login";
	private final String userIsEmpty = "身份证号码为空";
	private final String userIsExist = "身份证号码已经注册";
	private final String phoneIsExist = "手机号码已经注册";
	private final String idFormatError = "身份证号码格式错误";
	private final String passwdIsDifferent = "两次密码不一致";
	private final String passwdIsEmpty = "密码为空";
	// private final String passwdFormatError="密码格式错误";
	// private final String phoneFormatError = "手机号码格式错误";
	private final String phoneIsEmpty = "手机号码为空";
	private final String codeError = "验证码错误";
	private final String codeIsEmpty = "验证码为空";
	private final String success = "success";
	private final String typeWeb = "1web";
	private final String codePastDue="验证码已过期,请重新获取";

	@Resource
	PlayerLoginService playerLoginService;
	@Autowired
	private JedisClient jedisClient;

	/**
	 * @Description (进入登录注册页面)
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toRegiste")
	public String toLogin(HttpSession session) {
		if (session.getAttribute(Constants.SALT_IN_SESSION) == null) {
			setSaltForSession(session);
		}
		return login_page;
	}

	/**
	 * 
	 * @Description (球员注册)
	 * @param session
	 * @param user（IdCard）
	 * @param passwd（passwd）
	 * @param phone（phone
	 *            number）
	 * @return
	 */
	@PostMapping(value = "/register", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String registe(HttpSession session, String user, String passwd, String passwd2, String phone,
			String phoneCheck) {
		if (!VerifyUtil.isNotEmpty(phoneCheck)) {
			return codeIsEmpty;
		}
		/*if (!phoneCheck.equals(jedisClient.get(typeWeb + phone))) {
			return codeError;
		}*/
		if(isValidateNumThan5(phone, phoneCheck)){
			return codePastDue;
		}
		if(!validateMobileCode(phone, phoneCheck)){
			return codeError;
		}
		if (!VerifyUtil.isNotEmpty(user)) {
			return userIsEmpty;
		}
		if (!VerifyUtil.isNotEmpty(passwd)) {
			return passwdIsEmpty;
		}
		if (!VerifyUtil.isNotEmpty(passwd2)) {
			return passwdIsEmpty;
		}
		if (!VerifyUtil.isNotEmpty(phone)) {
			return phoneIsEmpty;
		}
		if (!FormatPattern.isValidatedAllIdcard(user)) {
			return idFormatError;
		}
		if (idCardIsExist(InfoProtUtil.xorInfo(user))) {
			return userIsExist;
		}
		if (!passwdCompare(passwd, passwd2)) {
			return passwdIsDifferent;
		}
		if (userIsExist(InfoProtUtil.xorInfo(phone))) {
			return phoneIsExist;
		}
		/*
		 * if (!phoneCheck(phone)) { return phoneFormatError; }
		 */
		try {
			if (playerLoginService.insertUser(InfoProtUtil.xorInfo(user), passwd, InfoProtUtil.xorInfo(phone),
					getUserBirthday(user))) {
				return success;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return success;
	}

	@GetMapping(value = "/register_add_check", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add_check(String phone) {
		if (userIsExist(InfoProtUtil.xorInfo(phone))) {
			return phoneIsExist;
		}
		return "success";
	}

	/**
	 * 
	 * @Description (检测用户是否已经存在)
	 * @param user
	 * @return
	 */
	public Boolean userIsExist(String user) {
		boolean userinfo = false;
		try {
			userinfo = playerLoginService.userIsExist(user);
		} catch (Exception e) {
			log.info(user + " not found");
			log.error(e.getMessage());
			return false;
		}
		return userinfo;
	}

	/**
	 * 
	 * @Description (检测身份证是否已经存在)
	 * @param user
	 * @return
	 */
	public Boolean idCardIsExist(String id_card) {
		boolean userinfo = false;
		try {
			userinfo = playerLoginService.idCardIsExist(id_card);
		} catch (Exception e) {
			log.info(id_card + " not found");
			log.error(e.getMessage());
			return false;
		}
		return userinfo;
	}

	/**
	 * 
	 * @Description (密码比较)
	 * @param passwd
	 * @param passwd2
	 * @return
	 */
	public Boolean passwdCompare(String passwd, String passwd2) {
		return passwd.equals(passwd2);
	}

	/**
	 * @Description (校验手机号码)
	 * @param phone
	 * @return
	 */
	public Boolean phoneCheck(String phone) {
		return FormatPattern.isChinaPhoneLegal(phone);
	}

	/**
	 *
	 * @Description (验证码对比)
	 * @param code
	 * @return
	 */
	public Boolean codeCheck(String code) {
		return null;
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

	/**
	 * 校验手机验证码
	 * @param phone
	 * @param phoneCheck
	 * @return trye:通过   false:不通过
	 */
	public boolean validateMobileCode(String phone, String phoneCheck) {
		StringBuffer str = new StringBuffer();
		str.append(typeWeb).append(phone);
		String key = str.toString();
		String keynum = str.append("num").toString();
		if (phoneCheck.equals(jedisClient.get(key))) {
			return true;
		} else {
			if (jedisClient.get(keynum) != null) {
				jedisClient.incr(keynum);
			}
		}
		return false;
	}

	/**
	 * 判断手机验证码是否错误五次
	 * 
	 * @param phone
	 * @param phoneCheck
	 * @return true:是 false:否
	 */
	public boolean isValidateNumThan5(String phone, String phoneCheck) {
		StringBuffer str = new StringBuffer();
		str.append(typeWeb).append(phone);
		String key = str.toString();
		String keynum = str.append("num").toString();
		if (jedisClient.get(keynum) != null) {
			if (Integer.parseInt(jedisClient.get(keynum)) > 5) {
				jedisClient.del(key);
				jedisClient.del(keynum);
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(InfoProtUtil.xorInfo("62654>1?203"));
	}
}
