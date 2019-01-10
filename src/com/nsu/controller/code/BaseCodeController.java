package com.nsu.controller.code;

import com.nsu.controller.BaseController;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2018/1/10
 * @Time 11:07
 * @Descorption
 */
public abstract class BaseCodeController extends BaseController {

    protected int length;
    protected int fontSize;

    protected int width = 120;//图片宽度
    protected int height = 40;//图片高度
//    protected int fontSize = (int) (height * 0.7);//验证码字体大小
    protected int distance = 10;//验证码之间的间距
    protected int disturbCount = 10;//干扰线的数量10
    protected int disturbLength = 15;//干扰线的长度15
    protected int noiseCount = 30;//噪点的数量30

    protected boolean isDistortLock = false;//是否扭曲图片
    protected boolean isdisturLock = true;//是否开启干扰线
    protected boolean isNoiseLock = true;//是否开启噪点
    protected String defaultFont = "宋体";//默认字体如果为空则随机选择字体

    protected String[] fontName = {"华文彩云", "华文琥珀", "宋体", "方正舒体", "华文行楷", "幼圆"
            , "Arial", "Berlin Sans FB", "Century", "Century", "Georgia", "MS Reference Sans Serif",
            "Times New Roman", "Verdana", "Wide Latin"};//字体样式
    protected Random rand = new Random();//随机数生成器

    protected int createCodeType = 0;
    //生成验证码的方式
    protected final static int CREATE_CODE_GENER = 0;//drawString不旋转
    protected final static int CREATE_CODE_BY_ROA = 1;//drawString 旋转 推荐此种类型
    protected final static int CREATE_CODE_BY_IMG = 2;//createImage旋转 覆盖

    /*
         * 使用添加图片的方式旋转验证码(不推荐使用此方法)
         */
    protected BufferedImage getRotateCodeByImg(Color color, String ch) {
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
    }/*
     * 生成随机字体
	 */

    protected Font getFont() {
        String name = fontName[rand.nextInt(fontName.length)];
        return new Font(defaultFont == null ? name : defaultFont, rand.nextBoolean() ? Font.PLAIN : Font.BOLD, fontSize);
    }

    /*
     * 添加噪点
     */
    /*
     * 生成随机颜色
     */
    protected Color getColor() {
        int r = rand.nextInt(120) ;
        int g = rand.nextInt(120) ;
        int b = rand.nextInt(120) ;

        return new Color(r, g, b);
    }

    protected void addNoise(Graphics2D graphics) {
        for (int i = 0; i < noiseCount; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            graphics.setColor(getColor());
            graphics.fillRect(x, y, 1, 1);
        }
    }/*
     * 验证码扭曲
	 */

    /*
     * 生成验证码的值
     */
    protected void addDistort(Graphics2D graphics) {
        graphics.setTransform(AffineTransform.getShearInstance(rand.nextBoolean() ? 0.3 : -0.3, 0));
    }
    /*
	 * 将验证码生成图片
	 */
    protected BufferedImage createImage(String data) {
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
    /*
     * 生成旋转的验证码（graphics rotate方式）
	 */
    protected abstract void getGeneralCode(Graphics2D graphics,String str,int x,int y);
    /*
     * 添加干扰线
     */
    protected void addDisturb(Graphics2D graphics) {
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

    protected void getRotateCode(Graphics2D graphics, String str, int x, int y) {
        double radins = (rand.nextBoolean() ? 1 : -1) * Math.toRadians(rand.nextInt(20));

        graphics.rotate(radins, width / 2, height / 2);//将图片旋转到指定角度
        graphics.setColor(getColor());
        graphics.setFont(getFont());
        graphics.drawString(str, x, y);
        graphics.rotate(-radins, width / 2, height / 2);//将图片还原成指定角度
    }
}
