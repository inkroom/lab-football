package cn.nsu.edu.web.four.services.organization;

import cn.nsu.edu.web.four.beans.organization.Organization;

import java.util.List;

/**
 * @Author :王新璋
 * @Date2018 03 2018/3/21 9:21
 * @Description:
 **/
public interface OrganizationService {


    //添加机构注册信息
    int updateOrganizationBySc(Organization organization);

    Organization selectOrganizationById(Integer idOrg);

    Organization selectOrganizationBySc(Integer schoolCode);

    List<Organization> selectOrganizationByPCV(Organization organization);
}
