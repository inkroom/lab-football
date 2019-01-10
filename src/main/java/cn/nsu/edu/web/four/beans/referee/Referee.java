package cn.nsu.edu.web.four.beans.referee;

import cn.nsu.edu.web.four.beans.match.Match;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.schedule.Schedule;

import java.util.Date;

//裁判
public class Referee {
    //裁判id
    private Integer idReferee;

    //赛程外键id
    private Integer idSch;

    private String username;

    private String password;

    private Date expireTime;

    private Integer status;

    private String phone;

    private String name;

    private String idcard;

    private String salt;

    private Schedule schedule;

    private Match match;

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getIdReferee() {
        return idReferee;
    }

    public void setIdReferee(Integer idReferee) {
        this.idReferee = idReferee;
    }

    public Integer getIdSch() {
        return idSch;
    }

    public void setIdSch(Integer idSch) {
        this.idSch = idSch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Referee() {
    }

    @Override
    public String toString() {
        return "Referee{" +
                "idReferee=" + idReferee +
                ", idSch=" + idSch +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", expireTime=" + expireTime +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", salt='" + salt + '\'' +
                ", schedule=" + schedule +
                ", match=" + match +
                ", player=" + player +
                '}';
    }
}