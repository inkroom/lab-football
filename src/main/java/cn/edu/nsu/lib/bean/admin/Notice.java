package cn.edu.nsu.lib.bean.admin;

import java.math.BigInteger;

public class Notice {

    private int id;//公告id
    private int lab_id;//实验室id
    private BigInteger publisher; //发布者的id
    private String time;//发布时间
    private String file_name;//文件名字
    private String title;//公告标题
    private String content;//公告内容
    private String file_path;//公告附件路径
    private String publisher_name;//发布人名字
    private String lab_name;

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

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

    public BigInteger getPublisher() {
        return publisher;
    }

    public void setPublisher(BigInteger publisher) {
        this.publisher = publisher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

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

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }
}
