package cn.edu.nsu.lib.bean.student;

import java.sql.Date;

/**
 * @author ChenGang
 * @Title: StuNotice
 * @Package cn.edu.nsu.lib.bean.student
 * @Description:
 * @date 2017/9/27 0027 上午 10:27
 **/
public class StuNoticeBean{

    int id ; //id
    int lab_id;//实验室id
    long publisher ;//发布人
    Date time;//发布时间
    String file_name;//附件名称
    String file_path;//附件路径
    String title;//附件标题
    String text;//附件内容


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public long getPublisher() {
        return publisher;
    }

    public void setPublisher(long publisher) {
        this.publisher = publisher;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
