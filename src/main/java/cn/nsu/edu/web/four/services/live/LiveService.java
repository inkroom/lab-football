package cn.nsu.edu.web.four.services.live;

import cn.nsu.edu.web.four.beans.live.LiveMessageBean;
import cn.nsu.edu.web.four.daos.jdbc.live.LiveDao;
import cn.nsu.edu.web.four.filters.XSSRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LiveService {
    @Value("${upload.image.url.prefix}")
    private String urlFix;

    @Autowired
    private LiveDao liveDao;
    private Pattern pattern = Pattern.compile("<img src=\"(.+)\"");
    private Logger log = LoggerFactory.getLogger(getClass());

    public List<LiveMessageBean> getAllMessage(int schId, boolean desc) {
        try {
            List<LiveMessageBean> result = liveDao.getAllMessage(schId, desc ? desc : null);

            result.forEach(bean -> {
//                //提取图片路径
//                Matcher matcher = pattern.matcher(bean.getContent());
//                if (matcher.find()) {
//                    bean.setImgPath(matcher.group(1));
//                }
                String orgPhone = bean.getPhone();
                //隐藏手机号码
                bean.setPhone(orgPhone.substring(0, 3) + "****" + orgPhone.substring(7, 11));
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LiveMessageBean addMessage(int schId, String phone, String content,String imgPath) {
//        Pattern pattern = Pattern.compile("<img src\"" + urlFix + "/(.+)\"");
        LiveMessageBean bean = new LiveMessageBean();
        bean.setContent(content);
        bean.setPhone(phone);
        bean.setSchId(schId);
        bean.setImgPath(imgPath);
        bean.setTime(new Date());
        //提取图片路径
//        Matcher matcher = pattern.matcher(bean.getContent());
//        if (matcher.find()) {
//            String src = matcher.group(1);
////            log.debug("原始路径= " + src);
////            log.debug("提取出的路径=" + src.substring(src.indexOf(urlFix) + urlFix.length() + 1));
//
//            bean.setImgPath(src.substring(src.indexOf(urlFix) + urlFix.length() + 1));
//        }
//        //去除图片
//        bean.setContent(XSSRequestWrapper.stripXSS(bean.getContent()));
//        //将null置为空串
//        bean.setContent(bean.getContent() == null ? "" : bean.getContent());
        try {
            log.debug(bean.toString());
            if (liveDao.addMessage(bean) == 1) {
                //处理手机号
                String orgPhone = bean.getPhone();
                bean.setPhone(orgPhone.substring(0, 3) + "****" + orgPhone.substring(7, 11));
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
