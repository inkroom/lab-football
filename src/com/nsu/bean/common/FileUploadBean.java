package com.nsu.bean.common;

import java.util.Set;

/**
 * Created by MeiXiebing on 3/30/17.
 */
public class FileUploadBean {
    private String dir ;
    private Set<String> fileType;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Set<String> getFileType() {
        return fileType;
    }

    public void setFileType(Set<String> fileType) {

        this.fileType = fileType;

    }
}
