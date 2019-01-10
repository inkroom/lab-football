package cn.edu.nsu.lib.bean.admin.form.HandleForm;

import cn.edu.nsu.lib.bean.admin.db.DbNotice;
import cn.edu.nsu.lib.bean.admin.form.Notice_form;
import cn.edu.nsu.lib.bean.admin.form.Utils.FormUtil;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.handlers.MessageException;
import cn.edu.nsu.lib.handlers.PropertiesPlaceholder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 王振科 on 2017/10/8.
 */
public class HandleNotice {
    private Notice_form notice_form;
    private DbNotice notice;
    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected PropertiesPlaceholder placeholder;

    public HandleNotice(Notice_form notice_form){
        this.notice_form = notice_form;
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

    protected String getFileupPath(MultipartFile file) throws Exception {
        //获得文件全部名字
        String fileFileName = file.getOriginalFilename();
        //如果文件名字不为空就以字符串形式取 .后面的类型
        if (!fileFileName.trim().equals("") && fileFileName.length() > 0) {
            String filetype = fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length());
            return uploadFile(file,filetype);
        }
        return null;
    }

//***************************上面是封装了上传文件的方法*********************************************

    public DbNotice getdb_Notice() throws Exception {
        notice = new DbNotice();
        //不传入公告id，数据库有自增
        notice.setLab_id(FormUtil.getInt(notice_form.getLab_id()));
        notice.setPublisher(FormUtil.getBI(notice_form.getPublisher()));
        notice.setTitle(notice_form.getTitle());
        notice.setContent(notice_form.getContent());
        notice.setTime(notice_form.getTime());
        MultipartFile file = notice_form.getFile();
        notice.setFile_name(file.getName());
        notice.setFile_path(getFileupPath(file));

        return notice;
    }

}
