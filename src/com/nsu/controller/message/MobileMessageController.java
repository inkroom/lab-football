/**
 * @Title: MobileMessageController.java 
 * @Package com.nsu.controller.message  
 * @Description: 手机消息中心
 * @author 朱明民 
 * @date 2017年4月19日 上午9:49:25
 * @version V1.0 
 */
package com.nsu.controller.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.message.MessageBean;
import com.nsu.bean.message.MessagePersonBean;
import com.nsu.bean.message.MessageReadBean;
import com.nsu.bean.player.ResultJson;
import com.nsu.controller.BaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.util.RSAencrypt.MobileToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: MobileMessageController
 * @Description: 手机消息中心
 * @date 2017年4月19日 上午9:49:25
 * @author 朱明民
 * 
 */
@Controller
@RequestMapping(value = "/mobile")
public class MobileMessageController extends BaseController {

	private final String successCode = "200";
	private final String errorCode = "404";
	private final String systemError = "查询失败，请稍后查看！";
	List<Object> result = new ArrayList<>();
	List<MessageBean> result2 = new ArrayList<>();
	int number = 0;
	
	@Resource(name = "SelectMessage")
	private ISelectMessageService iSelectMessageService;

	@ResponseBody
	@RequestMapping(value = "/message")
	public ResultJson messageAll(@RequestParam("token")String token) {
		JSONObject info = new JSONObject();
		try {
			Map<String, Object> messageInfo = MobileToken.getTokenMap(token);
			int A_ID = Integer.parseInt(messageInfo.get("A_ID").toString());
			info.put("showUnreadMessage", JSONArray.fromObject(showUnreadMessage(A_ID)));
			info.put("showReadMessage", JSONArray.fromObject(showReadMessage(A_ID)));
			info.put("showUnreadMessagePerson", JSONArray.fromObject(showUnreadMessagePerson(A_ID)));
			number = showUnreadMessage(A_ID).size()+showReadMessage(A_ID).size()+showUnreadMessagePerson(A_ID).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultJson(errorCode, systemError);
		}
		return new ResultJson(successCode, info, String.valueOf(number));
	}

	/**
	 * 获取系统未读消息
	 * 
	 * @param session
	 * @param model
	 */
	public List<MessageBean> showUnreadMessage(int A_ID) {
		// 获取系统未读消息
		List<MessageBean> messageUnreadList = null;
		try {
			messageUnreadList = iSelectMessageService.getUnreadMessageById(A_ID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageUnreadList;

	}

	/**
	 * 获取个人未读消息
	 * @param A_ID
	 * @return
	 */
	public List<MessagePersonBean> showUnreadMessagePerson(int A_ID){
		List<MessagePersonBean> messagePersonList = null;
		try {
			messagePersonList = iSelectMessageService.getPersonMsgByID(A_ID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return messagePersonList;
	}
	
	/**
	 * 获取已读消息
	 * 
	 * @param session
	 * @param model
	 */
	public List<MessageReadBean> showReadMessage(int A_ID) {
		// 获取未读消息信息
		List<MessageReadBean> messageReadList = null;
		try {
			messageReadList = iSelectMessageService.getMessageReadById(A_ID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageReadList;

	}

}
