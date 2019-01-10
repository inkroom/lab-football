package com.nsu.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: Mail
 * @Package com.nsu.test
 * @Description:
 * @date 4/25/17
 */
public class Mail {

    protected final static Log log = LogFactory.getLog(Mail.class);

    public static String myEmailAccount0 = "18180517987@163.com";
    public static String myEmailAccount1 = "meixiebingzuishuai@163.com";
    public static String myEmailAccount2 = "meixiebingzuiku@163.com";
    public static String myEmailAccount = "scxyzq@www.scxyty.com";
    public static String myEmailPassword = "f00tba11QWER";
    
    public static String getAccount(){
//    	int tem = (int) (Math.random() * 3) + 1;
//    	if (tem == 1) {
//			return myEmailAccount0;
//		}else if (tem == 2) {
//			return myEmailAccount1;
//		}else if (tem == 3) {
//			return myEmailAccount2;
//		}else {
//			return myEmailAccount0;
//		}
        return myEmailAccount;
    }

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtpdm.aliyun.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "64070915@qq.com";


    private static final String EmailTitle="四川省足球信息化系统";
    private static final String EmailSubject="验证码";
    private static final String EmailContentHead="【四川省校园足球】尊敬的用户，您本次的的验证码为：";
    private static final String EmailContentTail="。请在30分钟内使用，谢谢!";

    public static void main(String[] args) throws Exception {
//    	for (int i = 0; i < 20; i++) {
//    		sendEmail("997242554@qq.com","我是你爸爸"+i);
//		}
//        for (int i = 0; i<100;i++) {
            sendEmail("903464591@qq.com", "测试邮件" );
//        }
    }

    static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static boolean sendEmail(String receiveMailAccount,String randomCode){

        log.info("00000000000000000000000000000000000000000000000000000000000000000000000");
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        props.setProperty("mail.smtp.port","465");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.ssl", "true");
        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)

        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = null;
        try {
        	String accountAdd = getAccount();
            log.info(accountAdd);
            message = createMimeMessage(session, accountAdd, receiveMailAccount ,randomCode);
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(accountAdd, myEmailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();


            log.info(" end ");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            log.info(e.getCause());
            log.info(e.getClass());
            log.info(e.getStackTrace());
            log.info(e.getLocalizedMessage());
            return false;
        }


    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String randomCode) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, EmailTitle, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, EmailSubject, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(EmailSubject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(EmailContentHead+randomCode+EmailContentTail, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}
