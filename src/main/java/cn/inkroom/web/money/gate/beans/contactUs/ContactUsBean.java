package cn.inkroom.web.money.gate.beans.contactUs;

public class ContactUsBean {
    /**
     * mybatis下划线不映射
     */
    private int id;
    private String title;
    private String contactname1;
    private String contactway1;
    private String contactname2;
    private String contactway2;
    private String contactname3;
    private String contactway3;
    private String contactname4;
    private String contactway4;
    private String contactname5;
    private String contactway5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContactname1() {
        return contactname1;
    }

    public void setContactname1(String contactname1) {
        this.contactname1 = contactname1;
    }

    public String getContactway1() {
        return contactway1;
    }

    public void setContactway1(String contactway1) {
        this.contactway1 = contactway1;
    }

    public String getContactname2() {
        return contactname2;
    }

    public void setContactname2(String contactname2) {
        this.contactname2 = contactname2;
    }

    public String getContactway2() {
        return contactway2;
    }

    public void setContactway2(String contactway2) {
        this.contactway2 = contactway2;
    }

    public String getContactname3() {
        return contactname3;
    }

    public void setContactname3(String contactname3) {
        this.contactname3 = contactname3;
    }

    public String getContactway3() {
        return contactway3;
    }

    public void setContactway3(String contactway3) {
        this.contactway3 = contactway3;
    }

    public String getContactname4() {
        return contactname4;
    }

    public void setContactname4(String contactname4) {
        this.contactname4 = contactname4;
    }

    public String getContactway4() {
        return contactway4;
    }

    public void setContactway4(String contactway4) {
        this.contactway4 = contactway4;
    }

    public String getContactname5() {
        return contactname5;
    }

    public void setContactname5(String contactname5) {
        this.contactname5 = contactname5;
    }

    public String getContactway5() {
        return contactway5;
    }

    public void setContactway5(String contactway5) {
        this.contactway5 = contactway5;
    }


    @Override
    public String toString() {
        return "ContactUsBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contactname1='" + contactname1 + '\'' +
                ", contactway1='" + contactway1 + '\'' +
                ", contactname2='" + contactname2 + '\'' +
                ", contactway2='" + contactway2 + '\'' +
                ", contactname3='" + contactname3 + '\'' +
                ", contactway3='" + contactway3 + '\'' +
                ", contactname4='" + contactname4 + '\'' +
                ", contactway4='" + contactway4 + '\'' +
                ", contactname5='" + contactname5 + '\'' +
                ", contactway5='" + contactway5 + '\'' +
                '}';
    }
}
