package com.nsu.service.core;

import com.nsu.bean.common.FileUploadBean;

/**
 * Created by MeiXiebing on 3/30/17.
 */
public interface IFileUploadService {
    FileUploadBean getFileUploadPath(String role, String fileType);
}
