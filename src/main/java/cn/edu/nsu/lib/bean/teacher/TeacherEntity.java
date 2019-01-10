package cn.edu.nsu.lib.bean.teacher;
/**
* class_name: 教师实体类
* describe: 
* creat_user:  julse@qq.com
* creat_date: 2017/10/25 creat_time: 
**/


public class TeacherEntity {
    /**
     * 
    * creat_user: julse@qq.com creat_date: 2017/10/25
    **/
    private  String id ;
    private  String gender ;
    private  String name ;
    private  String tel ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
