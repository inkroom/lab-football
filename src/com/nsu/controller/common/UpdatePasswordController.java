/**
 * @Title: UpdatePasswordController.java 
 * @Package com.nsu.controller.common  
 * @Description: 修改密码Controller
 * @author 朱明民 
 * @date 2017年4月12日 上午9:31:41
 * @version V1.0 
 */
package com.nsu.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.common.IUpdatePasswordService;
import com.nsu.service.core.IMobileTokenService;
import com.nsu.util.InfoProtUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName: UpdatePasswordController
 * @Description: 修改密码Controller 
 * @date 2017年4月12日 上午9:31:41
 * @author 朱明民
 * 
 */
@Controller
@RequestMapping(value = "/updatePassword")
public class UpdatePasswordController extends BaseController {

	@Resource(name = "UpdatePassword")
	private IUpdatePasswordService iUpdatePasswordService;

	@Resource(name = "mobileTokenService")
	private IMobileTokenService mobileTokenService;

	/**
	 * 修改密码
	 * 
	 * @param A_ID
	 * @param old_password
	 * @param new_password
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update_password", method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String update(@RequestParam(value = "old_password") String old_password,
			@RequestParam(value = "new_password") String new_password,@RequestParam(value = "phone_dynamic_code") String phone_dynamic_code , HttpSession session,
			HttpServletResponse response) {
		JSONObject info = new JSONObject();
		try {
			/*
			 * 获取数据库password值
			 */
			@SuppressWarnings("unchecked")
			Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
			int A_ID = Integer.parseInt(map1.get("A_ID").toString());
			int type = Integer.parseInt(map1.get("A_TYPE").toString());
			
			String password = iUpdatePasswordService.getPasswordById(A_ID);
			/*
			 * 判断密码是否正确
			 */
			if (InfoProtUtil.comparePass(password, session.getAttribute(Constants.SALT_IN_SESSION).toString(),
					old_password.trim() )==false) {
				info.put("error", "原密码错误!");
			}else if(!mobileTokenService.checkCode(map1.get("A_ID").toString(),map1.get("A_TYPE").toString(),phone_dynamic_code)){
				info.put("error", "手机动态码错误!");
			}else {
			
				/*
				 * 将密码与盐存入数据库
				 */
				Map<String, Object> map = new HashMap<>();
				map.put("A_ID", A_ID);
				map.put("PASSWORD", new_password);
				map.put("SALT", session.getAttribute((Constants.SALT_IN_SESSION)).toString());

				/*
				 * 执行修改操作，返回信息
				 */
				iUpdatePasswordService.updatePassword(map);
				session.invalidate();

				info.put("status", type);
				info.put("error", "修改成功！请重新登录！");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return info.toString();
	}

}
