package cn.nsu.edu.web.four.controllers;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.services.common.SMSService;
import cn.nsu.edu.web.four.utils.code.CodeUtil;
import cn.nsu.edu.web.four.utils.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CodeController {

    @Autowired
    private SMSService smsService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 发送手机验证码
     *
     * @param phone 电话号码，仅支持手机号
     * @return
     */
    @RequestMapping("sendPhoneCode")
    @ResponseBody
    @Security(checkToken = true)
    public MessageDto sendCode(String phone) {
        if (!StringUtils.isEmpty(phone) && phone.matches(RegexStatic.PHONE)) {
            Result result = smsService.createCode(phone, request);
            return new MessageDto(result);
        } else {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
    }


    private int width = 149;//定义图片的width
    private int height = 38;//定义图片的height
    private int codeCount = 6;//定义图片上显示验证码的个数
    private int xx = 20;//字体间距
    private int fontHeight = 25;//字体大小
    private int codeY = 25;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/imageCode")
    public void imageCode(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
//      Graphics2D gd = buffImg.createGraphics();
        //Graphics2D gd = (Graphics2D) buffImg.getGraphics();
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);

        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 40; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        int red = 0, green = 0, blue = 0;
        // 得到随机产生的验证码数字。
        String code = CodeUtil.createMixCode(codeCount);
        log.debug("生成=" + code);
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(125);
            green = random.nextInt(125);
            blue = random.nextInt(125);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code.charAt(i) + "", (i + 1) * xx, codeY);

        }
        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute(BaseStatic.KEY_SESSION_CODE, code);//把验证码大写后存放进session
        log.info("session=" + session.getAttribute(BaseStatic.KEY_SESSION_CODE));
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();

    }
}
