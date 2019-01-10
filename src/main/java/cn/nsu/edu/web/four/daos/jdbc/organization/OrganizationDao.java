package cn.nsu.edu.web.four.daos.jdbc.organization;

import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.beans.player.Player;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * @Author : 王新璋
 * @Date : 2018/3/21 9:23
 * @Description :机构的注册登录
 **/
public interface OrganizationDao {


    //添加机构信息：注册使用
    int updateOrganizationBySc(Organization organization) throws Exception;

    //通过主键ID查询机构信息
    Organization selectOrganizationById(@Param("idOrg") Integer id) throws  Exception;

    //通过机构代码查询机构信息
    Organization selectOrganizationBySc(@Param("schoolCode") Integer schoolCode) throws  Exception;

    //通过省市县查询机构名称

    List<Organization> selectOrganizationByPCV(Organization organization) throws Exception;
}
