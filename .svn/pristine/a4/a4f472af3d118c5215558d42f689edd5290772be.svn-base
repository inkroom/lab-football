package com.nsu.service.teacher.information.impl;

import com.nsu.dao.teacher.improveinformation.ImproveInformationDao;
import com.nsu.entity.Account;
import com.nsu.entity.LoginInformation;
import com.nsu.entity.Teacher;
import com.nsu.entity.UploadFiles;
import com.nsu.service.teacher.information.ImproveInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @PackageName : com.nsu.service.teacher.information.impl
 * @Author : BuDD
 * @CreateTime : 2017/7/17
 * @Version : 1.0
 * @Description :完善信息服务类
 */
@Service(value = "improveInformationService")
public class ImproveInformationServiceImpl implements ImproveInformationService {

    @Autowired
    private ImproveInformationDao improveInformationDao;

    @Transactional
    @Override
    public int improveInformation(LoginInformation loginInformation) {
        loginInformation.setCreateDate((Timestamp) new Date());
        loginInformation.setChStatus("2");
        loginInformation.setStatus(1);
        this.improveInformationDao.improveInformation(loginInformation);
        return (int) loginInformation.getiId();
    }



    @Transactional
    @Override
    public int improveAccount(Account account) {
        account.setRecentLogin(new java.sql.Date(new Date().getTime()));
        account.setType(1);
        account.setStatus(1);
        account.setCreateDate((Timestamp) new Date());
        this.improveInformationDao.improveAccount(account);
        return account.getaId();
    }

    @Transactional
    @Override
    public void improveAll(Account account, LoginInformation loginInformation, UploadFiles uploadFiles) {
        loginInformation.setaId((long) improveAccount(account));
        updateAccountCreateBy(loginInformation.getaId());

        updateLoginInformationCreateBy((long) improveInformation(loginInformation));


        uploadFiles.setCreateBy(loginInformation.getaId());
        uploadFiles.setaId(loginInformation.getaId());
        improveUpload_files(uploadFiles);
    }

    @Transactional
    @Override
    public void updateAccountCreateBy(Long aId) {
        improveInformationDao.updateAccountCreateBy(aId);
    }

    @Transactional
    @Override
    public void updateLoginInformationCreateBy(Long iId) {
        improveInformationDao.updateLoginInformationCreateBy(iId);
    }

    @Transactional
    @Override
    public void improveUpload_files(UploadFiles uploadFiles) {uploadFiles.setStatus(1);
        uploadFiles.setCreateDate((Timestamp) new Date());
        this.improveInformationDao.improveUploadFiles(uploadFiles);
    }
}
