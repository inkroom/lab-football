package com.nsu.controller.coach;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.nsu.bean.coach.CoachInfoBean;
import com.nsu.common.Constants;
import com.nsu.controller.common.UploadBaseController;
import com.nsu.exception.ExtensionsException;
import com.nsu.exception.LengthException;
import com.nsu.exception.NullFileParamException;
import com.nsu.exception.SizeException;
import com.nsu.service.coach.CoachInfoService;
import com.nsu.util.Mail;
import com.nsu.util.ResponseUtil;
import com.nsu.util.SystemSeparatorUtil;
import com.nsu.util.V;
import com.nsu.util.VerifyUtil;
import com.nsu.util.jedis.JedisClient;

import sun.misc.BASE64Decoder;


/**
 * 用户个人信息数据处理
 * @author Mark
 *
 */
@Controller
@RequestMapping("/coach")
public class CoachInfoController extends UploadBaseController{
	/**
	 * 获取根路径
	 */
	private String rootPath = SystemSeparatorUtil.getRootPath();
	/**
	 * 获取系统文件路径格式
	 */
	private final String separator = SystemSeparatorUtil.getSystemSeparator();
	/**
	 * 文件上传成功
	 */
	 @Autowired
	private JedisClient jedisClient;
	private final String ROLE = "coach";
	private final String FILE_TYPE = "image";
	private String FILE_UPLOAD_SUCCESS = "ok";
	@Autowired
	private CoachInfoService coachInfoService;
	/**
	 * 获取教练员的基础信息
	 * @return
	 */
	@RequestMapping(value="/baseinfo", method = RequestMethod.GET)
	public ModelAndView getCoachInfoBase(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String coachID = request.getSession().getAttribute(Constants.LOGIN_USER_ID).toString();
			mv.addObject("info", coachInfoService.getCoachInfoBase(coachID));
			mv.addObject("integral", coachInfoService.getCoachintegral(coachID));
			mv.addObject("school",coachInfoService.getCoachSchoolMatch(coachID));
			mv.addObject("country", coachInfoService.getCoachCountryMatch(coachID));
			mv.addObject("city",coachInfoService.getCoachCityMatch(coachID));
			mv.addObject("province",coachInfoService.getCoachProvinceMatch(coachID));
			mv.addObject("other", coachInfoService.getCoachOtherMatch(coachID));
			mv.addObject("wonderful",coachInfoService.getCoachWonderfulPhoto(coachID));
			mv.setViewName("/coach/coach-system/coach_message");
		return mv;
	}
	/**
	 * 编辑教练员信息的时候先回显数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editinfo/get", method = RequestMethod.GET)
	public ModelAndView editCoachInfoGet(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String coachID = request.getSession().getAttribute(Constants.LOGIN_USER_ID).toString();
		mv.addObject("einfo", coachInfoService.getCoachEditInfo(coachID));
		mv.setViewName("/coach/coach-system/coach_edit");
		return mv;
	}
	
	/**
	 * 完善或者修改用户信息
	 * @return
	 */
	@RequestMapping(value="/edit/save", method = RequestMethod.POST)
	public ModelAndView editCoachInfo(HttpServletRequest request
			,CoachInfoBean coachInfoBean,
			@RequestParam(value = "A_EMAIL_CHECK_CODE",required = false)String eCode){
		ModelAndView mv = new ModelAndView();
		String coachID = request.getSession().getAttribute(Constants.LOGIN_USER_ID).toString();
		coachInfoBean.setCoachHeight(coachInfoBean.getCoachHeight().trim());
		coachInfoBean.setCoachWeight(coachInfoBean.getCoachWeight().trim());
		coachInfoBean.setCoachName(coachInfoBean.getCoachName().trim());
		if(!VerifyUtil.isNotEmpty(coachInfoBean.getCoachName())){
			mv.addObject("errmsg","名字不能为空");
			}else if (!VerifyUtil.isNotEmpty(coachInfoBean.getCoachHeight())) {
				mv.addObject("errmsg","身高不能为空");
			}else if (!VerifyUtil.isNotEmpty(coachInfoBean.getCoachWeight())) {
				mv.addObject("errmsg","体重不能为空");
			}else if(!VerifyUtil.isNotEmpty(coachInfoBean.getCoachSex())) {
				mv.addObject("errmsg","性别不能为空");
			}else if(Double.parseDouble(coachInfoBean.getCoachHeight())<10||Double.parseDouble(coachInfoBean.getCoachHeight())>300) {
				mv.addObject("errmsg","请在正确范围内(10-300)填写身高,仅保留一位小数");
			}else if (Double.parseDouble(coachInfoBean.getCoachWeight())<10||Double.parseDouble(coachInfoBean.getCoachWeight())>300) {
				mv.addObject("errmsg","请在正确范围内(10-300)填写体重,仅保留一位小数");
			}else {
				coachInfoBean.setCoachID(coachID);
				Map<String, Object> coachMap = (Map<String, Object>) request.getSession().getAttribute(Constants.LOGIN_USER);
				String coachAccountID = coachMap.get("A_ID").toString();
				if (eCode==null || eCode =="") {
					mv.addObject("errmsg", "邮件验证码错误");
				}else {
					//获得数据库里的邮箱验证码
					String eCodeDB = jedisClient.get(coachInfoBean.getCoachEmail());
					if (eCode.equals(eCodeDB)) {
						coachInfoService.saveCoachEditInfo(coachInfoBean);
						
						request.getSession().setAttribute(Constants.LOGIN_USER, coachInfoService.getCoachInfoBase(coachID));
						mv.addObject("msg", "修改信息成功!");
				}else {
						mv.addObject("errmsg", "邮件验证码错误,修改信息失败");	
					}
				}
			}			
		mv.addObject("einfo", coachInfoService.getCoachEditInfo(coachID));
		mv.setViewName("/coach/coach-system/coach_edit");
		return mv;
	}
	
//	@RequestMapping(value="/sendEmail")
//	public void  emailBand(String email,
//			HttpServletResponse response,HttpSession session){
//		Map<String, Object> map= (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
//		String A_ID = map.get("A_ID").toString();
//		//生成验证码
//		Random ramPwd = new Random();
//		int A_EMAIL_CHECK_CODE = ramPwd.nextInt(100000);
//		JSONObject info = new JSONObject();
//		//将验证码保存
//		try {
//			if (Mail.sendEmail(email, String.valueOf(A_EMAIL_CHECK_CODE))) {
//				coachInfoService.saveEmail(String.valueOf(A_EMAIL_CHECK_CODE), A_ID);
//				info.put("msg", "ok");
//				ResponseUtil.write(response, info);
//				return;
//			}
//			else {
//				info.put("msg", "error");
//				ResponseUtil.write(response, info);
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage());
//
//		}
//		return;
//	}
	/**
	 * 保存用户头像
	 * @param imgStr base64的图片字符串
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/head_pic/upload", method = RequestMethod.POST)
	public String headPicUpload(@RequestParam("image")String imgStr,
			HttpServletRequest request,
			HttpSession session,
			HttpServletResponse response){
		String coachID = session.getAttribute(Constants.LOGIN_USER_ID).toString();
			String headPicPath = rootPath+separator+"resources"+separator+"temp"+separator+"coach_headPic.jpg";
			File file = decBase64ToImg(headPicPath, imgStr);
			try {
				String filePath = fileUploadReplace(file, request, ROLE, FILE_TYPE, coachID, "coach_head_pic"+System.currentTimeMillis()+".jpg");
				if (!coachInfoService.headPicUpload(coachID, filePath)) {
					log.error("保存教练员头像失败");
				}
			} catch (SizeException | LengthException | ExtensionsException | NullFileParamException | IOException e) {
				log.error(e.getMessage());
				log.error("上传教练员头像失败");
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
		@SuppressWarnings("unchecked")
		Map<String, Object> coachMap = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		String coachAccountID = coachMap.get("A_ID").toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		try {
			for (MultipartFile file : files) {
				StringBuffer fileName = new StringBuffer();
				fileName.append(df.format(new Date())).append("_").append(file.getOriginalFilename());
				String filePath = fileUploadReplace(file, request, ROLE, FILE_TYPE, coachAccountID, fileName.toString());
				coachInfoService.updateWonderfulPhoto(coachAccountID, filePath);
			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return "";
	}
	//
	private File decBase64ToImg(String path,String imgStr){
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; i++) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
		}catch (Exception e){
			log.error(e.getMessage());
			log.error("教练员保存头像失败!");
			return  null;
		}
		return new File(path);
	}
	
}
