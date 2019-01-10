package com.nsu.service.common.impl;

import com.nsu.bean.common.FileUploadBean;
import com.nsu.service.common.IFileUpoadService;
import com.nsu.utils.ReadPropertiesUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @Title: FileUploadServiceImpl
* @Package com.nsu.service.impl.core
* @Description:
* @author 梅谢兵
* @date 上午10:58 17/4/11
* @version V1.0
*/
@Service(value = "fileUploadService")
public class FileUploadServiceImpl implements IFileUpoadService {

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
        String value = ReadPropertiesUtil.getPropertiesValue("save.properties",dir);
        String[] values = value.trim().split(",");
        fileUploadBean.setDir(dir);
        fileUploadBean.setFileType(stringToSet(values));
        return fileUploadBean;
    }

    public Set<String> stringToSet(String[] strings){
        List<String> list = Arrays.asList(strings);
        Set<String> set = new HashSet<String>();
        set.addAll(list);
        return set;
    }
}
