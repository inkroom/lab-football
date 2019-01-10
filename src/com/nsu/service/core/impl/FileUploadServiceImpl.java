package com.nsu.service.core.impl;

import com.nsu.bean.common.FileUploadBean;
import com.nsu.component.PropertiesReader;
import com.nsu.service.core.IFileUploadService;
import com.nsu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Title: FileUploadServiceImpl
* @Package com.nsu.service.impl.core
* @Description:
* @author 梅谢兵
* @date 上午10:58 17/4/11
* @version V1.0
*/
@Service(value = "fileUploadService")
public class FileUploadServiceImpl implements IFileUploadService {

    @Autowired
    private PropertiesReader reader;

    /**
     *
     * @param role
     * @param fileType
     * @return
     */
    @Override
    public FileUploadBean getFileUploadPath(String role, String fileType) {
        if (role == null || role.trim().equals("")||fileType == null || fileType.trim().equals("")){
            return null;
        }
        String dir = role + "_" + fileType;
        FileUploadBean fileUploadBean = new FileUploadBean();
        String value = reader.getValue(dir);
        String[] values = value.trim().split(",");
        fileUploadBean.setDir(dir);
        fileUploadBean.setFileType(StringUtils.stringToSet(values));
        return fileUploadBean;
    }
}
