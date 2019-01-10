package cn.nsu.edu.web.four.beans.teams;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
public class PlayerCondition {

    private Integer idPlayer;//球员ID
    private Integer grade;//年级
    private Integer classes;//班级
//    private String idPlayer;//球员ID
//    private String grade;//年级
//    private String classes;//班级
    private String name;//球员姓名

//    public String getIdPlayer() {
//        return idPlayer;
//    }

//    public void setIdPlayer(String idPlayer) {
//        this.idPlayer = idPlayer;
//    }
//
//    public String getGrade() {
//        return grade;
//    }
//
//    public void setGrade(String grade) {
//        this.grade = grade;
//    }
//
//    public String getClasses() {
//        return classes;
//    }
//
//    public void setClasses(String classes) {
//        this.classes = classes;
//    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }


    public Integer getClasses() {
        return classes;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
