/**
 * 
 */
package com.nsu.controller.coach;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.coach.CoachLoginService;
import com.nsu.util.FormatPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**    
* @Title: CoachLogController.java
* @Package com.nsu.controller.coach 
* @Description:
* @author 潘泳言   * @date 2017年4月11日 上午10:31:59  
* @version V1.0    */
@Controller
@RequestMapping("/coach")
public class CoachLoginController extends BaseController {
	@Autowired
	private CoachLoginService coachService;
	  private final String PHONE_FORMAT_ERROR = "请输入正确的手机号";
	  private final String PASS_FORMAT_ERROR = "请输入正确的密码";
	  
		@RequestMapping(value = "/login_view", method = RequestMethod.GET)
		public String log(HttpSession session){
			 if(session.getAttribute(Constants.SALT_IN_SESSION)==null){
		 			setSaltForSession(session);
		 	  }
			return "/coach/coach_login";
		}
	   /*name账号
	     * pass 密码
	     * code验证码
	     * 
	     * */
    @RequestMapping(value="/login")
	public ModelAndView coachLogin(@RequestParam("username")String name,
			@RequestParam("p")String pass,
			@RequestParam("idecode")String code,HttpServletRequest request,HttpSession session){
    	ModelAndView mv=new ModelAndView();
    	//没有盐添加盐；
    	name=name.trim();
    	 if(session.getAttribute(Constants.SALT_IN_SESSION)==null){
 			setSaltForSession(session);
 	  }
    	 if (!FormatPattern.isChinaPhoneLegal(name)) {//手机格式校验
    		 mv.addObject("errmsg", PHONE_FORMAT_ERROR);
    		 mv.setViewName("/coach/coach_login");	
 			return mv;
 		}
				//获取SESSION里面的验证码
				String icode=request.getSession().getAttribute(Constants.RANDOM_CODE).toString();
			session.removeAttribute(Constants.LOGIN_USER);	
				//判断输入验证码和session里面的验证码是否相同
				if(code.equalsIgnoreCase(icode)){
					//将name和pass传入service层
		
						if(coachService.coachLogin(name, pass,session.getAttribute(Constants.SALT_IN_SESSION).toString())){
							//密码相同进入serviceImpl
							Map<String, Object> map=new HashMap<String,Object>();
							//用个Map来接收教练信息
							map=coachService.coachInfo(name);
							session.setAttribute(Constants.LOGIN_USER, map);
							session.setAttribute(Constants.LOGIN_USER_ID, map.get("COACH_ID").toString());

							try {
								session.setAttribute("SMSPhone",name);
								session.setAttribute("SMSEmail",map.get("A_EMAIL").toString());
							}catch (Exception e){
								log.info("此异常不用管，第一次为空");
							}


							//登录成功进入教练中心页面
							mv.setViewName("redirect:/coach/index.action");
							return mv;
						}else {
							mv.addObject("errmsg", "用户名或密码错误");
						}
					}else {
						mv.addObject("errmsg", "验证码不正确");
					}
				
					//进入登录页面
				mv.setViewName("/coach/coach_login");	
				return mv;
		    	
	    		
	    		
	    	
	    	}
	
    //密码验证
    public boolean checkpassword(String pass){
    	Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,18}$");
		Matcher matcher = pattern.matcher(pass);
	if(matcher.matches()){
		return true;
	}
    	return false;
    }
    /**
     * 登录成功后将页面重定向到教练的index界面
     * @return
     */
    @RequestMapping("/index")
    public String coachIndexPage(HttpSession session){
    	//没有盐添加盐；
   	 if(session.getAttribute(Constants.SALT_IN_SESSION)==null){
			setSaltForSession(session);
	  }
    	if (session.getAttribute(Constants.LOGIN_USER)==null) {
			return "/coach/coach_login";
		}
    	return "/coach/coach_index";
    }
    //登出
    @RequestMapping("/logout")
    public ModelAndView coachLogout(HttpSession session){
    	ModelAndView mv = new ModelAndView();
    	session.removeAttribute(Constants.LOGIN_USER);
    	session.removeAttribute(Constants.LOGIN_USER_ID);
    	mv.setViewName("/coach/coach_login");
    	return mv;
    	
    	
    	
    }
}
