package cn.edu.nsu.lib.controllers.admin;

import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.handlers.MessageException;
import cn.edu.nsu.lib.handlers.PropertiesPlaceholder;
import cn.edu.nsu.lib.utils.RequestUtil;
import cn.edu.nsu.lib.utils.V;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/15
 * @Time 21:08
 * @Descorption
 */
public class BaseController {
    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected PropertiesPlaceholder placeholder;

    protected Map<String, Object> getLogin(HttpServletRequest request) {
        return RequestUtil.getLogin(request);
    }

    /**
     * 检测表单
     *
     * @param value 表单
     * @return 如果不通过返回false
     */
    protected void checkForm(Object value) throws Exception {
        V.checkForm(value);
//        } catch (Exception e) {
//            e.printStackTrace();
//            message = e.getMessage();
//            return false;
//        }
//        return true;
    }

    /**
     * 检测文件大小
     *
     * @param multipartFile 文件
     * @param type          最大大小
     * @throws MessageException
     */
    protected void checkFileSize(MultipartFile multipartFile, String type) throws MessageException {
        int size = Integer.parseInt(placeholder.getValue(type + ".size"));
        if (multipartFile.getSize() > size || multipartFile.getSize() <= 0) {
            throw new MessageException(Result.FILE_SIZE_NOT_SUIT);
        }
    }

    /**
     * 校验文件后缀名
     *
     * @param multipartFile 文件
     * @param type          文件类型
     * @throws MessageException 不符合
     */
    protected void checkFileType(MultipartFile multipartFile, String type) throws MessageException {
        String allTypes[] = placeholder.getValue(type + ".type").split(" *, *");
        for (String allowType : allTypes) {
            if (allowType.equals(multipartFile.getName().substring(multipartFile.getName().lastIndexOf(".")))) {
                return;
            }
        }
        throw new MessageException(Result.FILE_TYPE_NOT_SUIT);
    }

    /**
     * 获取文件上传路径
     *
     * @param file 文件
     * @param type 类型
     * @return 全路径
     */
    protected String getFilePath(MultipartFile file, String type) {
        return placeholder.getValue(type + ".path") + File.separator + System.currentTimeMillis() + "." + file.getName().substring(file.getName().lastIndexOf("."));
    }

    /**
     * 上传文件，返回文件全路径
     *
     * @param file 文件
     * @param type 文件类型
     * @return 文件全路径
     * @throws IOException io
     */
    protected String uploadFile(MultipartFile file, String type) throws Exception {
        checkFileSize(file, type);
        checkFileType(file, type);

        String path = getFilePath(file, type);
        File target = new File(path);
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }
        file.transferTo(target);
        return target.getAbsolutePath();
    }

}
