package cn.inkroom.web.money.gate.services.cooperator;

import cn.inkroom.web.money.gate.beans.cooperator.CooperatorBean;

import java.util.ArrayList;
import java.util.List;

public interface CooperatorService {
     List<CooperatorBean> getCooperatorList();

     CooperatorBean getCooperator(int id);

     int delCooperator(int id);

     int updateCooperator(String name, String content, Integer id);
     int addCooperator(String name, String content);
}
