package cn.nsu.edu.web.four.services.organization.impl;

import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.daos.jdbc.organization.OrganizationDao;
import cn.nsu.edu.web.four.services.organization.OrganizationService;
import cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author :暗石-王新璋
 * @Date  2018/3/21 9:19
 * @Description:机构注册登录业务层
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService{


    @Autowired
    private OrganizationDao organizationDao;

    public int updateOrganizationBySc(Organization organization){
        try {
            String password=organization.getPassword();
            String[] result= Md5EncryptUtil.parseMd5WithSalt(password);
            organization.setPassword(result[0]);
            organization.setSalt(result[1]);
            return organizationDao.updateOrganizationBySc(organization);
        }catch (Exception e) {
            e.printStackTrace();
            //最好使用该异常进行封装
            throw new RuntimeException(e);
        }
    }

    @Override
    public Organization selectOrganizationById(Integer idOrg) {
        try {
            return organizationDao.selectOrganizationById(idOrg);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //检验机构代码
    @Override
    public Organization selectOrganizationBySc(Integer schoolCode) {
        try {
            return organizationDao.selectOrganizationBySc(schoolCode);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //通过mybatis动态查询机构名称和编号
    @Override
    public List<Organization> selectOrganizationByPCV(Organization organization) {
        try {
            return organizationDao.selectOrganizationByPCV(organization);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
