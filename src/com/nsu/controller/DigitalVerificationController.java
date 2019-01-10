package com.nsu.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
public class DigitalVerificationController extends BaseController {
	
	private int width = 120;//图片宽度
	private int height = 40;//图片高度
	private int length = 9;//验证码数字的位数
	private int fontSize = (int)(height*0.7);//验证码字体大小
	private int distance = 10;//验证码之间的间距
	private int disturbCount = 10;//干扰线的数量10
	private int disturbLength = 15;//干扰线的长度15
	private int noiseCount = 30;//噪点的数量30
	
	private boolean isDistortLock = false;//是否扭曲图片
	private boolean isdisturLock = true;//是否开启干扰线
	private boolean isNoiseLock = true;//是否开启噪点
	private String defaultFont = "宋体";//默认字体如果为空则随机选择字体

	private String[] fontName = {"华文彩云","华文琥珀","宋体","方正舒体","华文行楷","幼圆"
			,"Arial","Berlin Sans FB","Century","Century","Georgia","MS Reference Sans Serif",
			"Times New Roman","Verdana","Wide Latin"};//字体样式 
	private Random rand = new Random();//随机数生成器
	
	private int createCodeType = 0;
	//生成验证码的方式
	private final static int CREATE_CODE_GENER = 0;//drawString不旋转
	private final static int CREATE_CODE_BY_ROA = 1;//drawString 旋转 推荐此种类型
	private final static int CREATE_CODE_BY_IMG = 2;//createImage旋转 覆盖
	
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
	public void getCode(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
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
		if(V.checkEmpty(scode) == false && V.checkEmpty(code) == false 
				&& V.checkPositiveInteger(code, 4) == true && scode.equals(code) == true){
			url = SENDSMSURL;
			status= 200;
		}
		if (type==null||type=="") {
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
		System.out.println("答案："+answer+"*****公式："+code);
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
	 * 生成随机颜色
	 */
	private Color getColor(){
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		
		return new Color(r,g,b);
	}
	
	/*
	 * 生成随机字体
	 */
	private Font getFont(){
		String name = fontName[rand.nextInt(fontName.length)];
		return new Font(defaultFont==null?name:defaultFont,rand.nextBoolean()?Font.PLAIN:Font.BOLD,fontSize);
	}
	
	/*
	 * 使用添加图片的方式旋转验证码(不推荐使用此方法)
	 */
	private BufferedImage getRotateCodeByImg(Color color,String ch){
		double radins = (rand.nextBoolean()?1:-1)*Math.toRadians(rand.nextInt(50));
		
		BufferedImage img = new BufferedImage((int)(width/length*0.9),(int)(height*0.7),BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.setColor(color);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.rotate(radins, img.getWidth()/2, img.getHeight()/2);
		g.setColor(getColor());
		g.setFont(getFont());
		g.drawString(ch,0,(int)(img.getHeight()*0.9));
		g.dispose();
		
		return img;
	}
	
	/*
	 * 生成旋转的验证码（graphics rotate方式）
	 */
	private void getRotateCode(Graphics2D graphics,String str,int x,int y){
		double radins = (rand.nextBoolean()?1:-1)*Math.toRadians(rand.nextInt(20));
		
		graphics.rotate(radins, width/2, height/2);//将图片旋转到指定角度
		graphics.setColor(getColor());
		graphics.setFont(getFont());
		graphics.drawString(str, x, y);
		graphics.rotate(-radins, width/2, height/2);//将图片还原成指定角度
	}
	
	/*
	 * 生成不旋转的普通验证码
	 */
	private void getGeneralCode(Graphics2D graphics,String str,int x,int y){
		graphics.setColor(getColor());
		graphics.setFont(getFont());
		graphics.drawString(str, x, y);
	}
	
	/*
	 * 添加干扰线
	 */
	private void addDisturb(Graphics2D graphics){
		for(int i = 0;i<disturbCount;i++){
			graphics.setColor(getColor());
			graphics.setStroke(new BasicStroke(rand.nextInt(3)));
			int x1 = rand.nextInt(width);
			int y1 = rand.nextInt(height);
			int lineWidth = rand.nextInt(disturbLength);
			int lineHeight = rand.nextInt(disturbLength);
			graphics.drawLine(x1, y1, x1+lineWidth, y1+lineHeight);
		}
	}
	/*
	 * 添加噪点
	 */
	private void addNoise(Graphics2D graphics){
		for(int i=0;i<noiseCount;i++){
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			graphics.setColor(getColor());
			graphics.fillRect(x, y, 1, 1);
		}
	}
	
	/*
	 * 验证码扭曲
	 */
	private void addDistort(Graphics2D graphics){
		graphics.setTransform(AffineTransform.getShearInstance(rand.nextBoolean()?0.3:-0.3, 0));
	}
	
	/*
	 * 将验证码生成图片
	 */
	private BufferedImage createImage(String data){
		//构造初始image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		
		//绘制背景
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		
		//图片扭曲
		if(isDistortLock)
			addDistort(graphics);
	
		for(int i = 0;i<data.length();i++){
			char ch = data.charAt(i);
			//绘制验证码图片
			switch(createCodeType){
			case CREATE_CODE_GENER:
				getGeneralCode(graphics, String.valueOf(ch), (int)(width/length*i*0.9)+distance, (int)(height*(rand.nextBoolean()?0.9:0.7)));
				break;
			case CREATE_CODE_BY_ROA:
				getRotateCode(graphics, String.valueOf(ch), (int)(width/length*i*0.9)+distance, (int)(height*(rand.nextBoolean()?0.9:0.7)));
				break;
			case CREATE_CODE_BY_IMG:
				graphics.drawImage(getRotateCodeByImg(graphics.getColor(), String.valueOf(ch)), (int)(width/length*i*0.9)+distance, (int)(height*(rand.nextBoolean()?0.1:0.3)), null);
				break;
			default:
				getGeneralCode(graphics, String.valueOf(ch), (int)(width/length*i*0.9)+distance, (int)(height*(rand.nextBoolean()?0.9:0.7)));
				break;
			}
		}
		
		//添加噪点
		if(isNoiseLock)
			addNoise(graphics);
		
		//添加干扰线
		if(isdisturLock)
			addDisturb(graphics);
		
		//释放graphics资源
		graphics.dispose();
		
		return image;
	}
}