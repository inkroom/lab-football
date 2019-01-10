package com.nsu.common;

import com.nsu.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/21
 * @Time 14:39
 * @Descorption
 */
@Controller
public class Verification extends BaseAction  {

    private int width = 120;//图片宽度
    private int height = 40;//图片高度
    private int length = 5;//验证码的位数
    private int fontSize = (int) (height * 0.8);//验证码字体大小
    private int distance = 10;//验证码之间的间距
    private int disturbCount = 30;//干扰线的数量
    private int disturbLength = 20;//干扰线的长度
    private int noiseCount = 50;//噪点的数量

    private boolean isDistortLock = true;//是否扭曲图片
    private boolean isdisturLock = true;//是否开启干扰线
    private boolean isNoiseLock = true;//是否开启噪点
    private String defaultFont = "宋体";//默认字体如果为空则随机选择字体

    private String[] fontName = {"华文彩云", "华文琥珀", "宋体", "方正舒体", "华文行楷", "幼圆"
            , "Arial", "Berlin Sans FB", "Century", "Century", "Georgia", "MS Reference Sans Serif",
            "Times New Roman", "Verdana", "Wide Latin"};//字体样式
    private String data = "0123456789abcdefghijkmnpqrstuvwxyz";//验证码的数据库

    private Random rand = new Random();//随机数生成器

    private int createCodeType = 0;
    //生成验证码的方式
    private final static int CREATE_CODE_GENER = 0;//drawString不旋转
    private final static int CREATE_CODE_BY_ROA = 1;//drawString 旋转 推荐此种类型
    private final static int CREATE_CODE_BY_IMG = 2;//createImage旋转 覆盖

    /*
     * 验证码输入到页面上
     *设置响应格式   禁止图片缓存
     * response.setContentType("image/jpeg");
     * response.setHeader("Cache-Control", "no-cache");
     * response.setHeader("Expires", "-1");
     * response.setHeader("Pragma", "no-cache");
     */
    @RequestMapping("random_code")
    public void getCode() throws IOException {
        HttpServletResponse response = getResponse();

        String data = getCodeString();
        log.info("验证码:"+data);
        getSession().setAttribute(Constants.RANDOM_CODE, data);

        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        response.setHeader("Content-type", "image/jpeg");

        ImageIO.write(createImage(data), "JPEG", response.getOutputStream());

//		return Action.NONE;
    }

    /*
     * 生成验证码的值
     */
    private String getCodeString() {
        String code = "";
        for (int i = 0; i < length; i++) {
            code += data.charAt(rand.nextInt(data.length()));
        }
        return code;
    }

    /*
     * 生成随机颜色
     */
    private Color getColor() {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        return new Color(r, g, b);
    }

    /*
     * 生成随机字体
     */
    private Font getFont() {
        String name = fontName[rand.nextInt(fontName.length)];
        return new Font(defaultFont == null ? name : defaultFont, rand.nextBoolean() ? Font.PLAIN : Font.BOLD, fontSize);
    }

    /*
     * 使用添加图片的方式旋转验证码(不推荐使用此方法)
     */
    private BufferedImage getRotateCodeByImg(Color color, String ch) {
        double radins = (rand.nextBoolean() ? 1 : -1) * Math.toRadians(rand.nextInt(50));
        BufferedImage img = new BufferedImage((int) (width / length * 0.9), (int) (height * 0.7), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.rotate(radins, img.getWidth() / 2, img.getHeight() / 2);
        g.setColor(getColor());
        g.setFont(getFont());
        g.drawString(ch, 0, (int) (img.getHeight() * 0.9));
        g.dispose();

        return img;
    }

    /*
     * 生成旋转的验证码（graphics rotate方式）
     */
    private void getRotateCode(Graphics2D graphics, String str, int x, int y) {
        double radins = (rand.nextBoolean() ? 1 : -1) * Math.toRadians(rand.nextInt(20));

        graphics.rotate(radins, width / 2, height / 2);//将图片旋转到指定角度
        graphics.setColor(getColor());
        graphics.setFont(getFont());
        graphics.drawString(str, x, y);
        graphics.rotate(-radins, width / 2, height / 2);//将图片还原成指定角度
    }

    /*
     * 生成不旋转的普通验证码
     */
    private void getGeneralCode(Graphics2D graphics, String str, int x, int y) {
        graphics.setColor(getColor());
        graphics.setFont(getFont());
        graphics.drawString(str, x, y);
    }

    /*
     * 添加干扰线
     */
    private void addDisturb(Graphics2D graphics) {
        for (int i = 0; i < disturbCount; i++) {
            graphics.setColor(getColor());
            graphics.setStroke(new BasicStroke(rand.nextInt(3)));
            int x1 = rand.nextInt(width);
            int y1 = rand.nextInt(height);
            int lineWidth = rand.nextInt(disturbLength);
            int lineHeight = rand.nextInt(disturbLength);
            graphics.drawLine(x1, y1, x1 + lineWidth, y1 + lineHeight);
        }
    }

    /*
     * 添加噪点
     */
    private void addNoise(Graphics2D graphics) {
        for (int i = 0; i < noiseCount; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            graphics.setColor(getColor());
            graphics.fillRect(x, y, 1, 1);
        }
    }

    /*
     * 验证码扭曲
     */
    private void addDistort(Graphics2D graphics) {
        graphics.setTransform(AffineTransform.getShearInstance(rand.nextBoolean() ? 0.3 : -0.3, 0));
    }

    /*
     * 将验证码生成图片
     */
    private BufferedImage createImage(String data) {
        //构造初始image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        //绘制背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        //图片扭曲
        if (isDistortLock)
            addDistort(graphics);

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            //绘制验证码图片
            switch (createCodeType) {
                case CREATE_CODE_GENER:
                    getGeneralCode(graphics, String.valueOf(ch), (int) (width / length * i * 0.9) + distance, (int) (height * (rand.nextBoolean() ? 0.9 : 0.7)));
                    break;
                case CREATE_CODE_BY_ROA:
                    getRotateCode(graphics, String.valueOf(ch), (int) (width / length * i * 0.9) + distance, (int) (height * (rand.nextBoolean() ? 0.9 : 0.7)));
                    break;
                case CREATE_CODE_BY_IMG:
                    graphics.drawImage(getRotateCodeByImg(graphics.getColor(), String.valueOf(ch)), (int) (width / length * i * 0.9) + distance, (int) (height * (rand.nextBoolean() ? 0.1 : 0.3)), null);
                    break;
                default:
                    getGeneralCode(graphics, String.valueOf(ch), (int) (width / length * i * 0.9) + distance, (int) (height * (rand.nextBoolean() ? 0.9 : 0.7)));
                    break;
            }
        }

        //添加噪点
        if (isNoiseLock)
            addNoise(graphics);

        //添加干扰线
        if (isdisturLock)
            addDisturb(graphics);

        //释放graphics资源
        graphics.dispose();

        return image;
    }


    public String initSession() {
        getRequest().getSession().invalidate();
        return "success";
    }
}