package cn.edu.nsu.lib.bean.admin.form;

import cn.edu.nsu.lib.annotions.Valid;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 王振科 on 2017/9/27.
 */
public class Notice_form {

    private String lab_id;//BigInteger
    @Valid( regex = "1[1-9]{10}",minLength = 10,maxLength = 11)
    private String publisher;//int
    private String time;
    private String title;
    private String content;
//    private String file_name;
//    private String file_path;
    //文件
    private MultipartFile file;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getLab_id() {
        return lab_id;
    }

    public void setLab_id(String lab_id) {
        this.lab_id = lab_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

//    public String getFile_name() {
//        return file_name;
//    }
//
//    public void setFile_name(String file_name) {
//        this.file_name = file_name;
//    }
//
//    public String getFile_path() {
//        return file_path;
//    }
//
//    public void setFile_path(String file_path) {
//        this.file_path = file_path;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
