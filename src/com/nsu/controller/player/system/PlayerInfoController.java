package com.nsu.controller.player.system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nsu.util.jedis.JedisClient;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.nsu.bean.player.PlayerInfoBean;
import com.nsu.common.Constants;
import com.nsu.controller.common.UploadBaseController;
import com.nsu.service.player.PlayerInfoService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.Mail;
import com.nsu.util.ResponseUtil;
import com.nsu.util.SystemSeparatorUtil;
import com.nsu.util.VerifyUtil;

import sun.misc.BASE64Decoder;

/**
 * 
 * @Title: PlayerInfoController.java
 * @Package com.nsu.controller.player.system
 * @Description: 球员信息Controller
 * @author 侯松梁
 * @date 2017年4月11日 下午2:57:25
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/player/system")
public class PlayerInfoController extends UploadBaseController {
	/**
	 * 获取系统文件路径格式
	 */
	private final String separator = SystemSeparatorUtil.getSystemSeparator();
	/**
	 * 文件上传成功
	 */
	private final String ROLE = "player";
	private final String FILE_TYPE = "image";
	@Resource
	private PlayerInfoService playerInfoService;
	@Resource
	private JedisClient jedisClient;
	/**
	 * 获取根路径
	 */
	private String rootPath = SystemSeparatorUtil.getRootPath();
	private String FILE_UPLOAD_SUCCESS = "ok";
	// 后台数据存放
	private Map<String, Object> map = null;

	/**
	 * 进去个人中心，显示球员信息
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/player_message")
	public String toMessage(Model model, HttpSession session, HttpServletResponse response) {
		try {
			String A_ID = session.getAttribute(Constants.LOGIN_USER_ID).toString();
			map = playerInfoService.getPlayerInfo(A_ID);
			// 得到省级记录
			model.addAttribute("activity_info_province", playerInfoService.getPlayerActivityInfo(A_ID, "1"));
			// 得到市级记录
			model.addAttribute("activity_info_city", playerInfoService.getPlayerActivityInfo(A_ID, "2"));
			// 得到县级记录
			model.addAttribute("activity_info_country", playerInfoService.getPlayerActivityInfo(A_ID, "3"));
			// 得到校级记录
			model.addAttribute("activity_info_school", playerInfoService.getPlayerActivityInfo(A_ID, "4"));
			// 得到其他记录
			model.addAttribute("activity_info_other", playerInfoService.getPlayerActivityInfo(A_ID, "5"));
			model.addAttribute("player_info", map);
			// 得到学籍信息
			model.addAttribute("stu_info", playerInfoService.getStudentInfo(map.get("A_ID_CARD").toString()));
			// 获取精彩图片
			model.addAttribute("wonderful_pic", playerInfoService.getWdpic(A_ID));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/player/player_system/player_message";
	}

	/**
	 * 进入修改页面
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/player_edit")
	public String toEdit(Model model, HttpSession session) {
		try {
			String A_ID = session.getAttribute(Constants.LOGIN_USER_ID).toString();
			map = playerInfoService.getPlayerInfo(A_ID);
			model.addAttribute("player_info", map);
			// 得到学籍信息
			model.addAttribute("stu_info",
					playerInfoService.getStudentInfo(InfoProtUtil.xorInfo(map.get("A_ID_CARD").toString())));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/player/player_system/player_edit";
	}

	/**
	 * 
	 * @Description (头像上传)
	 * @param image
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload_photo")
	@ResponseBody
	public String uploadPhoto(String image, HttpServletRequest request, HttpSession session) {
		StringBuffer tempPath = new StringBuffer();
		tempPath.append(rootPath).append(separator).append("resources").append(separator).append("temp")
				.append(separator).append("plyer_photo.jpg");
		String userId = session.getAttribute(Constants.LOGIN_USER_ID).toString();
		log.info(userId);
		log.info(tempPath);
		File file = base64ToFile(tempPath.toString(), image);
		log.info(file.getName());
		try {
			String filePath = fileUploadReplace(file, request, ROLE, FILE_TYPE, userId, System.currentTimeMillis()+"plyer_photo.jpg");
			log.info(filePath);
			playerInfoService.updatePhoto(userId, filePath);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return FILE_UPLOAD_SUCCESS;
	}

	/**
	 * 
	 * @Description (精彩时刻图片批量上传)
	 * @param files
	 * @param image
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload_photos")
	@ResponseBody
	public String uploadPhotos(@RequestParam("file") MultipartFile[] files, String image, HttpServletRequest request,
			HttpSession session) {
		log.info("stat upload");
		String userId = session.getAttribute(Constants.LOGIN_USER_ID).toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		try {
			for (MultipartFile file : files) {
				StringBuffer fileName = new StringBuffer();
				fileName.append(df.format(new Date())).append("_").append(file.getOriginalFilename());
				String filePath = fileUploadReplace(file, request, ROLE, FILE_TYPE, userId, fileName.toString());
				playerInfoService.updateWonderfulPhoto(userId, filePath);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

	/**
	 * 将base64还原成文件
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param fullPath
	 * @param base64
	 * @return
	 */
	public File base64ToFile(String fullPath, String base64) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; i++) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(fullPath);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new File(fullPath);
	}

	/**
	 * 修改球员信息
	 * @param playerInfoBean
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/update_info")
	public void updateInfo(PlayerInfoBean playerInfoBean, HttpSession session,
			HttpServletResponse response) {
		Integer A_ID = (Integer) session.getAttribute(Constants.LOGIN_USER_ID);
		playerInfoBean.setA_ID(A_ID);
		//擅长位置处理
		String positionInfo = "";
		for (int i = 0; i < playerInfoBean.getP_POSITION().length; i++) {
			positionInfo += playerInfoBean.getP_POSITION()[i];
			if (i != playerInfoBean.getP_POSITION().length-1)
				positionInfo += ",";
		}
		playerInfoBean.setPositionInfo(positionInfo);
		JSONObject info = new JSONObject();
		try {
			if (playerInfoBean.getA_EMAIL() == null) {
				info.put("error", "邮箱不能为空");
				ResponseUtil.write(response, info);
			}else if (!VerifyUtil.isNotEmpty(String.valueOf(playerInfoBean.getP_PLAYER_HEIGHT()))) {
				info.put("error", "身高不能为空");
				ResponseUtil.write(response, info);
			}else if (!VerifyUtil.isNotEmpty(String.valueOf(playerInfoBean.getP_PLAYER_WEIGHT()))) {
				info.put("error", "体重不能为空");
				ResponseUtil.write(response, info);
			}else if (jedisClient.exists(playerInfoBean.getA_EMAIL())) {
				if (jedisClient.get(playerInfoBean.getA_EMAIL()).equals(playerInfoBean.getA_EMAIL_CHECK_CODE())) {
					// 执行修改操作
					playerInfoService.updatePlayerInfo(playerInfoBean);
					//移除验证码
					jedisClient.del(playerInfoBean.getA_EMAIL());
					info.put("info", "修改信息成功");
					ResponseUtil.write(response, info);
				} else {
					info.put("msg", "验证码错误");
				}
			} else {
				info.put("msg", "验证码错误");
			}	
			ResponseUtil.write(response, info);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	
}
