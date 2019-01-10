package cn.nsu.edu.web.four.services.common;

import cn.nsu.edu.web.four.annotation.FileRename;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

    @Value("${upload.image.max.size}")
    private long maxSize;
    @Value("${upload.image.min.size}")
    private long minSize;
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

    private String message;

    private Logger log = LoggerFactory.getLogger(getClass());

    public String getMessage() {
        return message;
    }

    private boolean checkFileSize(MultipartFile file) {
        return file.getSize() < minSize || file.getSize() > maxSize;
    }

    private boolean checkFileType(MultipartFile file, HttpServletRequest request) {
        log.debug("contentType=" + request.getContentType());
        return !request.getContentType().contains("multipart/form-data") && !request.getContentType().contains("image");
//        return !(request.getContentType().contains("multipart/form-data")) ||
//                !(request.getContentType().contains("image"));
    }

    /**
     * 自定义重命名方式
     *
     * @param file    文件
     * @param request 请求
     * @param rename  重命名方式
     * @return 相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request, FileRename rename) {
        if (file == null) return null;
        if (checkFileSize(file)) {
            message = "请上传大小在" + minSize + "到" + maxSize + "之间的文件";
            return null;
        }
        if (checkFileType(file, request)) {
            message = "请上传jpeg、png格式文件";
            return null;
        }
        String fileName = path + File.separator + rename.rename(file, request);
        log.debug("凭借的文件路径"+fileName);
        File target = new File(fileName);
        log.debug("绝对路径="+target.getAbsolutePath());
        if (!target.getParentFile().exists())
            target.getParentFile().mkdirs();

        try {
            file.transferTo(target);
            return target.getAbsolutePath().replace(path, "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用默认的重命名方式
     *
     * @param file    文件
     * @param request 请求
     * @return 基础存储路径下的相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request) {
        return upload(file, request, (file1, request1) -> System.currentTimeMillis() + ".jpeg");
    }

    /**
     * 自定义maxSize上传
     *
     * @param file    文集
     * @param request 请求
     * @param maxSize 最大大小
     * @return 基础存储路径下的相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request, long maxSize) {
        long temp = this.maxSize;
        this.maxSize = maxSize;
        String result = upload(file, request);
        this.maxSize = temp;//必须归位，否则下次将使用这一次的maxSize
        return result;
    }

    /**
     * 自定义maxSize上传
     *
     * @param file    文集
     * @param request 请求
     * @param maxSize 最大大小
     * @param rename  文件重命名方式
     * @return 基础存储路径下的相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request, long maxSize, FileRename rename) {
        long temp = this.maxSize;
        this.maxSize = maxSize;
        String result = upload(file, request, rename);
        this.maxSize = temp;//必须归位，否则下次将使用这一次的maxSize
        return result;
    }

    /**
     * 删除文件
     * @param path 调用upload方法返回的值
     * @return 删除结果
     */
    public boolean deleteFile(String path) {
        File file = new File(this.path + path);
        return file.delete();
    }
}
