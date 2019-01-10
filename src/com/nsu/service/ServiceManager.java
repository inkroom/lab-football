package com.nsu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsu.service.admin.school.AdminSchoolService;
import com.nsu.service.school.SchoolAdvertisementService;
import com.nsu.service.admin.business.BusinessAccountService;
import com.nsu.service.admin.business.BusinessAllAdsService;
import com.nsu.service.admin.school.AdminSchoolAccountService;
import com.nsu.service.core.GetConfigureService;
import com.nsu.service.core.RoleService;
import com.nsu.service.school.SchoolAdminAccountService;
import com.nsu.service.school.SchoolFeatureService;
import com.nsu.service.school.SchoolNewsService;
import com.nsu.service.school.SchoolPeopleService;
import com.nsu.service.school.SchoolService;
import com.nsu.service.school.admin.SchoolFeatureAdminService;
import com.nsu.service.school.admin.SchoolFoundAdminService;
import com.nsu.service.school.admin.SchoolIndexAdminService;
import com.nsu.service.school.admin.SchoolInfoAdminService;
import com.nsu.service.school.admin.SchoolNewsAdminService;
import com.nsu.service.school.admin.SchoolPeopleAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceManager {
    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
    private RoleService roleService;
    @Autowired
    private LogService logService;

    /**
     * 管理学校用户
     */
    @Autowired
    private AdminSchoolAccountService adminSchoolAccountService;
    @Autowired
    private AdminSchoolService adminSchoolService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private SchoolAdminAccountService schoolAdminAccountService;
    @Autowired
    private BusinessAllAdsService businessAllAdsService;
    @Autowired
    private BusinessAccountService businessAccountService;
    @Autowired
    private GetConfigureService getConfigureService;
    @Autowired
    private SchoolIndexAdminService schoolIndexAdminService;
    @Autowired
    private SchoolInfoAdminService schoolInfoAdminService;
    @Autowired
    private SchoolPeopleAdminService schoolPeopleAdminService;
    @Autowired
    private SchoolPeopleService schoolPeopleService;
    @Autowired
    private SchoolFeatureAdminService schoolFeatureAdminService;
    @Autowired
    private SchoolFeatureService schoolFeatureService;
    @Autowired
    private SchoolNewsAdminService schoolNewsAdminService;
    @Autowired
    private SchoolNewsService schoolNewsService;
    @Autowired
    private SchoolAdvertisementService schoolAdvertisementService;
    @Autowired
    private SchoolFoundAdminService schoolFoundAdminService;

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public AdminSchoolAccountService getAdminSchoolAccountService() {
        return adminSchoolAccountService;
    }

    public void setAdminSchoolAccountService(AdminSchoolAccountService adminSchoolAccountService) {
        this.adminSchoolAccountService = adminSchoolAccountService;
    }

    public AdminSchoolService getAdminSchoolService() {
        return adminSchoolService;
    }

    public void setAdminSchoolService(AdminSchoolService adminSchoolService) {
        this.adminSchoolService = adminSchoolService;
    }

    public SchoolService getSchoolService() {
        return schoolService;
    }

    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    public SchoolAdminAccountService getSchoolAdminAccountService() {
        return schoolAdminAccountService;
    }

    public void setSchoolAdminAccountService(SchoolAdminAccountService schoolAdminAccountService) {
        this.schoolAdminAccountService = schoolAdminAccountService;
    }

    public GetConfigureService getGetConfigureService() {
        return getConfigureService;
    }

    public void setGetConfigureService(GetConfigureService getConfigureService) {
        this.getConfigureService = getConfigureService;
    }

    public SchoolIndexAdminService getSchoolIndexAdminService() {
        return schoolIndexAdminService;
    }

    public void setSchoolIndexAdminService(SchoolIndexAdminService schoolIndexAdminService) {
        this.schoolIndexAdminService = schoolIndexAdminService;
    }

    public SchoolInfoAdminService getSchoolInfoAdminService() {
        return schoolInfoAdminService;
    }

    public void setSchoolInfoAdminService(SchoolInfoAdminService schoolInfoAdminService) {
        this.schoolInfoAdminService = schoolInfoAdminService;
    }

    public SchoolPeopleAdminService getSchoolPeopleAdminService() {
        return schoolPeopleAdminService;
    }

    public void setSchoolPeopleAdminService(SchoolPeopleAdminService schoolPeopleAdminService) {
        this.schoolPeopleAdminService = schoolPeopleAdminService;
    }

    public SchoolPeopleService getSchoolPeopleService() {
        return schoolPeopleService;
    }

    public void setSchoolPeopleService(SchoolPeopleService schoolPeopleService) {
        this.schoolPeopleService = schoolPeopleService;
    }

    public SchoolFeatureAdminService getSchoolFeatureAdminService() {
        return schoolFeatureAdminService;
    }

    public void setSchoolFeatureAdminService(SchoolFeatureAdminService schoolFeatureAdminService) {
        this.schoolFeatureAdminService = schoolFeatureAdminService;
    }

    public SchoolFeatureService getSchoolFeatureService() {
        return schoolFeatureService;
    }

    public void setSchoolFeatureService(SchoolFeatureService schoolFeatureService) {
        this.schoolFeatureService = schoolFeatureService;
    }

    public SchoolNewsAdminService getSchoolNewsAdminService() {
        return schoolNewsAdminService;
    }

    public void setSchoolNewsAdminService(SchoolNewsAdminService schoolNewsAdminService) {
        this.schoolNewsAdminService = schoolNewsAdminService;
    }

    public SchoolNewsService getSchoolNewsService() {
        return schoolNewsService;
    }

    public void setSchoolNewsService(SchoolNewsService schoolNewsService) {
        this.schoolNewsService = schoolNewsService;
    }

    public BusinessAllAdsService getBusinessAllAdsService() {
        return businessAllAdsService;
    }

    public void setBusinessAllAdsService(BusinessAllAdsService businessAllAdsService) {
        this.businessAllAdsService = businessAllAdsService;
    }

    public BusinessAccountService getBusinessAccountService() {
        return businessAccountService;
    }

    public void setBusinessAccountService(BusinessAccountService businessAccountService) {
        this.businessAccountService = businessAccountService;
    }

    public SchoolAdvertisementService getSchoolAdvertisementService() {
        return schoolAdvertisementService;
    }

    public void setSchoolAdvertisementService(SchoolAdvertisementService schoolAdvertisementService) {
        this.schoolAdvertisementService = schoolAdvertisementService;
    }

    public SchoolFoundAdminService getSchoolFoundAdminService() {
        return schoolFoundAdminService;
    }

    public void setSchoolFoundAdminService(SchoolFoundAdminService schoolFoundAdminService) {
        this.schoolFoundAdminService = schoolFoundAdminService;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }


}
