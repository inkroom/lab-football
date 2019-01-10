package com.nsu.bean.organization;

/*
 * @Description: TODO(结束比赛的第一名，第二名，第三名)
 * @return     返回类型
 * @throws
 */
public class EndRaceBean {

    private  String COM_ID;

    private Object ORG_ID;


    private String COM_WINNER;

    private String COM_SECOND;

    private String COM_THIRD;

    public EndRaceBean() {
    }

    public String getCOM_ID() {
        return COM_ID;
    }

    public void setCOM_ID(String COM_ID) {
        this.COM_ID = COM_ID;
    }

    public Object getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(Object ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    public String getCOM_WINNER() {
        return COM_WINNER;
    }

    public void setCOM_WINNER(String COM_WINNER) {
        this.COM_WINNER = COM_WINNER;
    }

    public String getCOM_SECOND() {
        return COM_SECOND;
    }

    public void setCOM_SECOND(String COM_SECOND) {
        this.COM_SECOND = COM_SECOND;
    }

    public String getCOM_THIRD() {
        return COM_THIRD;
    }

    public void setCOM_THIRD(String COM_THIRD) {
        this.COM_THIRD = COM_THIRD;
    }


    @Override
    public String toString() {
        return "EndRaceBean{" +
                "COM_ID=" + COM_ID +
                ", ORG_ID=" + ORG_ID +
                ", COM_WINNER='" + COM_WINNER + '\'' +
                ", COM_SECOND='" + COM_SECOND + '\'' +
                ", COM_THIRD='" + COM_THIRD + '\'' +
                '}';
    }
}
