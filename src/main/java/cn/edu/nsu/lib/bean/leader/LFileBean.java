package cn.edu.nsu.lib.bean.leader;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Arrays;

/**
 * @author ChenGang
 * @Title: LFileBean
 * @Package cn.edu.nsu.lib.bean.leader
 * @Description:
 * @date 2017/11/7 0007 下午 9:37
 **/
public class LFileBean {
    private long publisher;

    private CommonsMultipartFile commonsMultipartFile;
    private String title;
    private String content;
    private String file_path;
    private String file_name;
    private String time;
    private long lab_id;
    private int[] lab;

    public int[] getLab() {
        return lab;
    }

    public void setLab(int[] lab) {
        this.lab = lab;
    }

    public long getPublisher() {
        return publisher;
    }

    public void setPublisher(long publisher) {
        this.publisher = publisher;
    }

    public CommonsMultipartFile getCommonsMultipartFile() {
        return commonsMultipartFile;
    }

    public void setCommonsMultipartFile(CommonsMultipartFile commonsMultipartFile) {
        this.commonsMultipartFile = commonsMultipartFile;
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

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getLab_id() {
        return lab_id;
    }

    public void setLab_id(long lab_id) {
        this.lab_id = lab_id;
    }

    @Override
    public String toString() {
        return "LFileBean{" +
                "publisher=" + publisher +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", file_path='" + file_path + '\'' +
                ", file_name='" + file_name + '\'' +
                ", time=" + time +
                ", lab_id=" + lab_id +
                ", lab=" + Arrays.toString(lab) +
                '}';
    }
}
