package cn.inkroom.web.money.gate.services.common;

import cn.inkroom.web.money.gate.config.UploadConfig;
import cn.inkroom.web.money.gate.daos.redis.UploadImgDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 图片上传服务
 */
@Service
public class UploadImgService {
    @Value("${upload.image.min.size}")
    private long minSize;
    @Value("${upload.image.max.size}")
    private long maxSize;
    @Value("${upload.image.max.width}")
    private int maxWidth;
    @Value("${upload.image.min.width}")
    private int minWidth;
    @Value("${upload.image.max.height}")
    private int maxHeight;
    @Value("${upload.image.min.height}")
    private int minHeight;
    @Value("${upload.image.type}")
    private String allowType;
    @Value("${upload.image.base.path}")
    private String path;

    @Autowired
    private UploadImgDao dao;


    private Logger log = LoggerFactory.getLogger(getClass());

    public Long getMinSize() {
        Long temp = null;
        try {
            temp = dao.getLong(UploadConfig.IMAGE_MIN_SIZE);
        } catch (Exception e) {
        }
        return temp != null ? temp : minSize;
    }

    public Long getMaxSize() {
        Long temp = null;
        try {
            temp = dao.getLong(UploadConfig.IMAGE_MAX_SIZE);
        } catch (Exception e) {
        }
        return temp != null ? temp : maxSize;
    }

    public Integer getMaxWidth() {
        Integer temp = null;
        try {
            temp = dao.getInt(UploadConfig.IMAGE_MAX_WIDTH);
        } catch (Exception e) {
        }
        return temp != null ? temp : maxWidth;
    }

    public Integer getMinWidth() {
        Integer temp = null;
        try {
            temp = dao.getInt(UploadConfig.IMAGE_MIN_WIDTH);
        } catch (Exception e) {
        }
        return temp != null ? temp : minWidth;
    }

    public Integer getMaxHeight() {
        Integer temp = null;
        try {
            temp = dao.getInt(UploadConfig.IMAGE_MAX_HEIGHT);
        } catch (Exception e) {
        }
        return temp != null ? temp : maxHeight;
    }

    public Integer getMinHeight() {
        Integer temp = null;
        try {
            temp = dao.getInt(UploadConfig.IMAGE_MIN_HEIGHT);
        } catch (Exception e) {
        }
        return temp != null ? temp : minHeight;
    }

    public String getAllowType() {
        String temp = null;
        try {
            temp = dao.getString(UploadConfig.IMAGE_ALLOW_TYPE);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return temp != null ? temp : allowType;
    }

    public String getPath() {
        String temp = null;
        try {
            temp = dao.getString(UploadConfig.IMAGE_BASE_PATH);
        } catch (Exception e) {
        }
        return temp != null ? temp : path;
    }
}
