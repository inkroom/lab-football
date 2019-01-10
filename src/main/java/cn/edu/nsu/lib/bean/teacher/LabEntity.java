package cn.edu.nsu.lib.bean.teacher;

public class LabEntity {
    /**
    * class_name: LabEntity
    * describe: 实验室实体类
    * creat_user: 蒋玖宏 julse@qq.com
    * creat_date: 2017/9/28
    * creat_time: 21:19
    **/
    private String id;//编号
    private String name;//名称
    private String describe;//描述
    private String address;//地址
    private String lab_admin;//实验室管理员
    private String avg_fre;//月考勤率（人均考勤次数）
    private String stu_num;//学生人数

//    /**
//     * 将实验室实体类以Jason数组返回
//     * @return
//     */
//    @Override
//    public String toString() {
//        ObjectMapper mapper = new ObjectMapper();
//        HashMap map = new HashMap();
//        map.put("id","123");
//        map.toString();
//        return "id" +id+
//                "name" +name+
//                "describ" +describ+
//                "qq" + qq+
//                "address"+address;
//    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    //
//    public String getQq() {
//        return qq;
//    }
//
//    public void setQq(String qq) {
//        this.qq = qq;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLab_admin() {
        return lab_admin;
    }

    public void setLab_admin(String lab_admin) {
        this.lab_admin = lab_admin;
    }

    public String getAvg_fre() {
        return avg_fre;
    }

    public void setAvg_fre(String avg_fre) {
        this.avg_fre = avg_fre;
    }

    public String getStu_num() {
        return stu_num;
    }

    public void setStu_num(String stu_num) {
        this.stu_num = stu_num;
    }
}
