package cn.inkroom.web.money.gate.daos.jdbc.cooperator;

import cn.inkroom.web.money.gate.beans.cooperator.CooperatorBean;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface CooperatorDao {
    List<CooperatorBean> getCooperatorList() throws Exception;

    CooperatorBean getCooperator(@Param("id") int id,@Param("status") int status) throws Exception;

    int updateCooperator(@Param("c") CooperatorBean bean) throws Exception;

    int deleteCooperator(@Param("id") int id) throws Exception;

    int addCooperator(@Param("c") CooperatorBean bean) throws Exception;
}
