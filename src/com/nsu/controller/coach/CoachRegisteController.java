package com.nsu.controller.coach;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.controller.BaseController;
import com.nsu.service.coach.CoachRegisterService;
import com.nsu.util.FormatPattern;
import com.nsu.util.jedis.JedisClient;

/**
 * 教练员注册
 * @author Dengjiawei
 * @since 2017/4/11
 */
@Controller
@RequestMapping("/coach")
public class CoachRegisteController extends BaseController{
	private final String PASSWORD_DIFFRERENT = "两次输入的密码不同";
	private final String PHONE_VALICODE_ERROR = "手机验证码错误";
	private final String ID_NUM_USED = "身份证已被注册";
	private final String PHONE_USED = "手机已被注册";
	private final String PHONE_FORMAT_ERROR = "请输入正确的手机号";
	private final String ID_NUM_FROMAT_ERROR = "请输入正确的身份证号";
	private final String PASSWORD_SIX_EIGHTEEN="密码需要六到十八位";
	private final String WEB_TYPE="2web";
	@Autowired
	private CoachRegisterService coachRegisterServce;
	@Autowired
    private JedisClient jedisClient;
	
	/**
	 * 教练员注册
	 * @param ID_Num
	 * @param pass
	 * @param conPass
	 * @param phone
	 * @param valiCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String coachRegister(@RequestParam(value= "ID_Num",required = false)String IdNum
			,@RequestParam(value = "passwd",required = false)String passwd
			,@RequestParam(value = "conPasswd",required = false)String conPasswd
			,@RequestParam(value = "phone",required = false)String phone
			,@RequestParam(value = "phoneCheck",required = false)String phoneCheckCode
			,HttpServletResponse response){
	//	从jedis中获取手机验证码
		String phoneValiCountStr = WEB_TYPE+"web"+phone+"num";
		String phoneValiCount = jedisClient.get(phoneValiCountStr);
		int count = 0;
		if(phoneValiCount==null || phoneValiCount ==""){
			jedisClient.set("phoneValicount", "0");
		}else {
			count = Integer.parseInt(jedisClient.get(phoneValiCountStr));
		}
		if (count>5) {
			log.info(count+"dasdasda");
			jedisClient.set(phoneValiCountStr,String.valueOf(0));
			jedisClient.del(WEB_TYPE);
			return PHONE_VALICODE_ERROR;
		}else {
			log.info(count+"---------------");
			if (!jedisClient.get(WEB_TYPE+phone).equals(phoneCheckCode)) {
				
				jedisClient.set(phoneValiCountStr, String.valueOf(++count));
				return PHONE_VALICODE_ERROR;
			}
		}
		
		//先进行格式校验
		if (!FormatPattern.validate18Idcard(IdNum)){//身份证格式校验
			return ID_NUM_FROMAT_ERROR;
		}
		if (!FormatPattern.isChinaPhoneLegal(phone)) {//手机格式校验
			return PHONE_FORMAT_ERROR;
		}
			if(!checkpassword(passwd)){//密码6-18位
				if (passwd.equals(conPasswd)) {//两次密码是否相同
					if (coachRegisterServce.checkCoachRegIdNum(IdNum)) {//身份证是否已经被注册
						if (coachRegisterServce.checkCoachRegPhone(phone)) {//手机号是否已经被注册
							if (coachRegisterServce.insertCoachRegister(IdNum, conPasswd, phone)){//向数据库添加数据
								String coa=coachRegisterServce.selectCoachID(phone);
								System.out.println("查出阿来的"+coa);
								coachRegisterServce.addCoachScoreStauts(coa);
								return "注册成功";
							}else return "未知原因注册失败，请重试";
						}else return PHONE_USED;
					}else return ID_NUM_USED;
				}else return PASSWORD_DIFFRERENT;
			}else return PASSWORD_SIX_EIGHTEEN;
	
}
	@RequestMapping(value="/check/regIdNum", method = RequestMethod.GET)
	public void regIdNumVali(@RequestParam("idNum")String idNum){
		coachRegisterServce.checkCoachRegIdNum(idNum);
	}
	   //密码验证6-18位
    public boolean checkpassword(String pass){
    	Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,18}$");
		Matcher matcher = pattern.matcher(pass);
	if(matcher.matches()){
		return true;
	}
    	return false;
    }
}
