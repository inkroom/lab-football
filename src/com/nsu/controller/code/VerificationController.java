package com.nsu.controller.code;

import java.awt.Graphics2D;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nsu.common.Constants;

@Controller
@RequestMapping(value = "/verification")
public class VerificationController extends BaseCodeController {

    private String data = "0123456789abcdefghijkmnpqrstuvwxyz";//验证码的数据库

    /*
     * 验证码输入到页面上
     *设置响应格式   禁止图片缓存
     * response.setContentType("image/jpeg");
     * response.setHeader("Cache-Control", "no-cache");
     * response.setHeader("Expires", "-1");
     * response.setHeader("Pragma", "no-cache");
     */
    @RequestMapping(value = "/get_code")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {

        super.length = 5;
        super.fontSize = (int) (height * 0.8);

        String data = getCodeString();
        session.setAttribute(Constants.RANDOM_CODE, data);

        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-type", "image/jpeg");
//		例：为了指示IE浏览器（客户端）不要缓存页面，服务器端的jsp程序可以编写如下：
//		response.setHeader(“Cache-Control”, “no-cache”);
//		//response.setHeader(“Pragma”, “no-cache”);作用相当于上行代码，通常两者合用
        ImageIO.write(createImage(data), "JPEG", response.getOutputStream());

    }

    /*
     * 生成验证码的值
     */
    private String getCodeString() {
        StringBuilder code = new StringBuilder();
        ;
        for (int i = 0; i < length; i++) {
            code.append(data.charAt(rand.nextInt(data.length())));
        }
        return code.toString();
    }


    /*
     * 生成不旋转的普通验证码
     */
    protected void getGeneralCode(Graphics2D graphics, String str, int x, int y) {
        graphics.setColor(getColor());
        graphics.setFont(getFont());
        graphics.drawString(str, x, y);
    }


}