/**
 * @Title: MessageController.java 
 * @Package com.nsu.controller.message  
 * @Description: 消息Controller
 * @author 朱明民 
 * @date 2017年4月17日 上午8:52:50
 * @version V1.0 
 */
package com.nsu.controller.message;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.message.MessageBean;
import com.nsu.bean.message.MessagePersonBean;
import com.nsu.bean.message.MessageReadBean;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.controller.BaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName: MessageController
 * @Description: 消息Controller
 * @date 2017年4月17日 上午8:52:50
 * @author 朱明民
 * 
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

	@Resource(name = "SelectMessage")
	private ISelectMessageService iSelectMessageService;

	/**
	 * 跳转消息主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/message-all")
	public String messageAll(HttpSession session) {
		return "/message/message-all";
	}

	/**
	 * 跳转消息内容页面
	 * 
	 * @return
	 */
	@InterceptorAnno(isRestful = true)
	@RequestMapping(value = "/message-detail/{id}/{status}")
	@RestfulUrlAnnotation(refulUrl = "/message-detail/*/*.html")
	public String messageDetail(@PathVariable("id") String id, @PathVariable("status") int status, Model model,
			HttpSession session) {
		// 获取消息的内容
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map1.get("A_ID").toString());
		Map<String, Object> messageContent = null;
		try {
			if (status == 3) {
				// 系统消息
				messageContent = iSelectMessageService.getMessageContentById(id);
				// 在用户消息表插入数据标记这消息已读
				iSelectMessageService.insertUserMessage(A_ID, Integer.parseInt(id));
			}
			if (status == 4) {
				// 个人消息
				messageContent = iSelectMessageService.getMessageContentPersonById(id);
				// 在用户消息表插入数据标记这消息已读
				iSelectMessageService.insertPersonMsg(A_ID, Integer.parseInt(id));
			}
			if (status == 1) {
				// 系统消息
				messageContent = iSelectMessageService.getMessageContentById(id);
			}
			if (status == 2) {
				// 个人消息
				messageContent = iSelectMessageService.getMessageContentPersonById(id);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("messageContent", messageContent);
		return "/message/message-detail";
	}

	/**
	 * 分页获取未读系统消息
	 * 
	 * @param session
	 * @param model
	 */
	@RequestMapping(value = "/messageUnread")
	public void showUnreadMessage(Integer pageNum1, HttpSession session, HttpServletResponse response) {
		// 获取未读系统消息
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map1.get("A_ID").toString());
		List<MessageBean> messageUnreadList = null;
		List<MessageBean> messageUnreadList1 = null;
		JSONObject object = new JSONObject();
		try {
			messageUnreadList1 = iSelectMessageService.getUnreadMessageById(A_ID);
			int num;
			if (messageUnreadList1.size() % 5 == 0) {
				num = messageUnreadList1.size() / 5;
			} else {
				num = messageUnreadList1.size() / 5 + 1;
			}
			if (pageNum1 > num) {
				object.put("NumStatus", "1");
				pageNum1 = num;
			} else {
				object.put("NumStatus", "0");
			}

			PageHelper.startPage(pageNum1, 5);
			messageUnreadList = iSelectMessageService.getUnreadMessageById(A_ID);
			PageInfo<MessageBean> pageInfo1 = new PageInfo<MessageBean>(messageUnreadList);
			object.put("number", messageUnreadList1.size());
			object.put("pageInfo1", pageInfo1);
			ResponseUtil.write(response, object);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 分页获取未读个人消息
	 * 
	 * @param session
	 * @param model
	 */
	@RequestMapping(value = "/messageUnreadPerson")
	public void showUnreadMessagePerson(Integer pageNum2, HttpSession session, HttpServletResponse response) {
		// 获取未读个人消息信息
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map1.get("A_ID").toString());
		List<MessagePersonBean> messageUnreadPersonList = null;
		List<MessagePersonBean> messageUnreadPersonList1 = null;
		JSONObject object = new JSONObject();
		try {
			messageUnreadPersonList1 = iSelectMessageService.getPersonMsgByID(A_ID);
			int num;
			if (messageUnreadPersonList1.size() % 5 == 0) {
				num = messageUnreadPersonList1.size() / 5;
			} else {
				num = messageUnreadPersonList1.size() / 5 + 1;
			}
			if (pageNum2 > num) {
				object.put("NumStatus", "1");
				pageNum2 = num;
			} else {
				object.put("NumStatus", "0");
			}
			PageHelper.startPage(pageNum2, 5);
			messageUnreadPersonList = iSelectMessageService.getPersonMsgByID(A_ID);
			PageInfo<MessagePersonBean> pageInfo2 = new PageInfo<MessagePersonBean>(messageUnreadPersonList);
			object.put("number", messageUnreadPersonList1.size());
			object.put("pageInfo2", pageInfo2);
			ResponseUtil.write(response, object);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 系统消息全部设为已读
	 * 
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/allReadMessage", method = RequestMethod.GET)
	@ResponseBody
	public String allReadMessage(HttpSession session, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map1.get("A_ID").toString());
		JSONObject info = new JSONObject();
		try {
			iSelectMessageService.allReadMessage(A_ID);
			info.put("error", "操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info.toString();
	}

	/**
	 * 个人消息全部设为已读
	 * 
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/allReadMessagePerson", method = RequestMethod.GET)
	@ResponseBody
	public String allReadMessagePerson(HttpSession session, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map1.get("A_ID").toString());
		JSONObject info = new JSONObject();
		try {
			iSelectMessageService.allReadMessagePerson(A_ID);
			info.put("error", "操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info.toString();
	}

	/**
	 * 获取未读消息条数
	 * 
	 * @param session
	 * @param model
	 */
	@RequestMapping(value = "/countMessage", method = RequestMethod.GET)
	public String showUnreadMessageCount(HttpSession session, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map1.get("A_ID").toString());
		List<MessageBean> messageUnreadList = null;
		List<MessagePersonBean> messagePersonList = null;
		String countMessage = null;
		JSONObject info = new JSONObject();
		try {

			messageUnreadList = iSelectMessageService.getUnreadMessageById(A_ID);
			messagePersonList = iSelectMessageService.getPersonMsgByID(A_ID);
			countMessage = String.valueOf(messageUnreadList.size() + messagePersonList.size());
			info.put("countMessage", countMessage);
			ResponseUtil.write(response, info);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分页获取已读消息
	 * 
	 * @param session
	 * @param model
	 */
	@RequestMapping(value = "/messageRead")
	public void showReadMessagePage(Integer pageNum, HttpSession session, HttpServletResponse response) {
		// session获取A_ID
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map.get("A_ID").toString());
		List<MessageReadBean> messageReadList = null;
		JSONObject object = new JSONObject();
		// 开始分页
		// 从数据库查询已读消息
		try {
			PageHelper.startPage(pageNum, 5);
			messageReadList = iSelectMessageService.getMessageReadById(A_ID);
			PageInfo<MessageReadBean> pageInfo = new PageInfo<MessageReadBean>(messageReadList);
			object.put("pageInfo", pageInfo);
			ResponseUtil.write(response, object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
