package com.nsu.controller.code;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.util.V;
/**
 * 数字公式验证码
 * @author ljl
 * @version 1.0
 * @createDate 2017-05-19 14:13:15
 */
@Controller
@RequestMapping(value="/verification")
public class DigitalVerificationController extends BaseCodeController {
	/**
	 * 发送手机验证码的地址
	 */
	private final static String SENDSMSURL = "/sms/getRegisterCode.action";
	
	/*
	 * 验证码输入到页面上
	 *设置响应格式   禁止图片缓存
	 * response.setContentType("image/jpeg");
	 * response.setHeader("Cache-Control", "no-cache");
	 * response.setHeader("Expires", "-1");  
	 * response.setHeader("Pragma", "no-cache");
	 */
	@RequestMapping(value="/get_num_code")
	public void getCode(HttpServletResponse response,HttpSession session) throws IOException{
		super.length = 9;
		super.fontSize = (int)(height*0.7);

		Map<String, String> map = getCodeMap();
		
		String data = map.get("code");
		session.setAttribute("answer", map.get("answer"));
		response.setHeader("Expires", "-1");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma", "no-cache");  
		
        response.setHeader("Content-type", "image/jpeg"); 
		
		ImageIO.write(createImage(data), "JPEG", response.getOutputStream());
		
	}
	
	/**
	 * <p>用于发送手机验证码前的验证</p>
	 * <p>参数说明：</p>
	 * url： 发送手机验证码的相对地址
	 * status为200则验证通过返回发送验证没得地址
	 * @author ljl
	 * @createDate 2017-05-19 16:06:57
	 * @param code
	 * @param session
	 */
	@RequestMapping(value="/verify_code_answer")
	@ResponseBody
	public String verifyCode(String code, HttpSession session, HttpServletRequest request){
		String scode = (String) session.getAttribute("answer");
		JSONObject resJson = new JSONObject();
		String type = request.getParameter("type");
		String url = null;
		int status = 500;
		if(!V.checkEmpty(scode) && !V.checkEmpty(code)
				&& V.checkPositiveInteger(code, 4) && scode.equals(code)){
			url = SENDSMSURL;
			status= 200;
		}
		if (type==null|| Objects.equals(type, "")) {
			resJson.put("type", 0);
		}else if (type.equals("1")) {
			resJson.put("type", 1);
		}else if(type.equals("2")) {
			resJson.put("type", 2);
		}
		resJson.put("status", status);
		resJson.put("url", url);
		return resJson.toString();
	}
	
	/**
	 * 生成数学计算公式验证码
	 * @author ljl
	 * @createDate 2017-05-19 14:17:17
	 * @return
	 */
	private Map<String, String> getCodeMap(){
		Map<String, String> map = new HashMap<String, String>();
		String code = "?";
		int answer = 0;
		//生成一个1~3的随机数
		int num = (int) (Math.random()*100)%3+1;
		//生成2个数字
		Map<String, Integer> numMap = getNumber(num);
		switch(num){
			case 1:
				//问号在中间
				code = numMap.get("numSmall")+" + "+code+"= "+numMap.get("numBig");
				answer = numMap.get("numBig") - numMap.get("numSmall");
				break;
			case 2:
				//问号在左边
				code = code+" + "+numMap.get("numSmall")+"= "+numMap.get("numBig");
				answer = numMap.get("numBig") - numMap.get("numSmall");
				break;
			case 3:
				//问号在右边
				code = numMap.get("numSmall")+" + "+numMap.get("numBig")+"= "+code;
				answer = numMap.get("numBig") + numMap.get("numSmall");
				break;
		}
		log.info("答案："+answer+"*****公式："+code);
		map.put("code", code);
		map.put("answer", answer+"");
		return map;
	}
	
	/**
	 * 获取2个0~99之间的正整数
	 * @author ljl
	 * @createDate 2017-05-19 14:24:38
	 * @param len
	 * @return
	 */
	private Map<String, Integer> getNumber(int type){
		Map<String, Integer> numMap = new HashMap<>();
		int numSmall = 1000, numBig = 0;
		if(type==3){
			//两数相加
			numBig = (int) (Math.random()*90)+10;
			numSmall = (int) (Math.random()*10);
		}else{
			//两数相减
			int tmp = 0;
			numBig = (int) (Math.random()*90)+10;
			while(numSmall>numBig){
				numSmall = (int) (Math.random()*10);
				tmp++;
				//防止死循环
				if(tmp>100){
					numSmall = 9;
					break;
				}
			}
		}
		
		numMap.put("numSmall", numSmall);
		numMap.put("numBig", numBig);
		return numMap;
	}
	

	/*
	 * 生成不旋转的普通验证码
	 */
	protected void getGeneralCode(Graphics2D graphics,String str,int x,int y){
		graphics.setColor(getColor());
		graphics.setFont(getFont());
		graphics.drawString(str, x, y);
	}


}