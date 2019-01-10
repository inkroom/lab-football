package javadev.core.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

import javadev.core.Constants;

import javax.servlet.http.HttpServletResponse;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 通用匿名Action，可跳过登录判断，可实现页面之间简单跳转
 */
public class CommonAnonymousAction extends CommonBaseAction implements
		Anonymous {
	
	private Random random = new Random();
	

	// 产生登录验证码
	public String getCode() throws Exception {
		System.out.println("#########################################################################");
		Map<String, Object> session = getSession();
		HttpServletResponse response = getResponse();
		response.setHeader("Cache-Control", "no-cache");
		int width = 75; // 图片宽度
		int height = 25; // 图片高度

		// 生成前景和背景颜色，形成反色	
		int red = (int) (Math.random() * 1000 % 64);
		int green = (int) (Math.random() * 1000 % 64);
		int blue = (int) (Math.random() * 1000 % 64);
		//Color backColor = new Color(red, green, blue);
		//底色固定为#F1FCFF
		Color backColor = new Color(0xF1, 0xFC, 0xFF);
		Color fontColor = new Color(255 - red, green, blue);

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.createGraphics();
		graphics.setColor(backColor); // 背景颜色
		graphics.fillRect(0, 0, width, height);

		graphics.setFont(new Font("Sans-Serif", Font.BOLD, 18));
		//固定验证码颜色为绿色
		graphics.setColor(Color.GREEN); // 前景颜色

		// 图片背景上随机生成5条线条，避免通过图片识别破解验证码
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}
		String code = getCodeString();
		session.put(Constants.RANDOM_CODE, code); // 写入session中
		graphics.drawString(code, (int) (width * 0.1), (int) (height * 0.8));
		graphics.dispose();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response
				.getOutputStream());
		encoder.encode(image);
		response.getOutputStream().flush(); // 刷新到页面生成图片
		response.getOutputStream().close(); // 关闭writer
		System.out.println("#########################################################################");
		return null;
	}

	// 产生验证图片上面的随机字符
	protected String getCodeString() {
		String old = "23456789abcdefghijkmnpqrstuvwxy";
		StringBuffer sb = new StringBuffer();
		int j = 0;
		for (int i = 0; i < 5; i++) {
			j = random.nextInt(old.length());
			sb.append(old.substring(j, j + 1));
		}
		return sb.toString();
	}
	
	/**
	 * @author PC-GZR
	 * 暂时放在这里
	 */
	private String refresh;
	private String province;
	private String city;
	private String district;
	private String school;
	/* searchType为选择查询的类型，分为运动员，教练员，球队 */
	private String searchType;
	/*stage代表当前保存的学短信息*/
	private String stage;
	private String Type;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		refresh="0";
		province="null";
		city="null";
		district="null";
		school="null";
		searchType="null";
		stage="null";
		Type="null";
		return SUCCESS;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	
}
