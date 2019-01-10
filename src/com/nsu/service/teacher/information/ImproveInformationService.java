package com.nsu.service.teacher.information;

import com.nsu.entity.Account;
import com.nsu.entity.LoginInformation;
import com.nsu.entity.Teacher;
import com.nsu.entity.UploadFiles;

/**
 * @PackageName : com.nsu.service.teacher.information
 * @Author : BuDD
 * @CreateTime : 2017/7/17
 * @Version : V1.0
 * @Description : 教师完善信息
 */
public interface ImproveInformationService {

    /**
     * 教师第一次登陆完善信息表并且返回Id
     * @param loginInformation
     */
    int improveInformation(LoginInformation loginInformation);

    /**
     * 教师第一次登陆完善账户表，返回这个账户的ID
     * @param account
     */
    int improveAccount(Account account);

    /**
     * 完善所有的信息
     * @param account
     * @param loginInformation
     * @param uploadFiles
     */
    void improveAll(Account account, LoginInformation loginInformation, UploadFiles uploadFiles);


    /**
     * 更新账户的创建者
     * @param aId
     */
    void updateAccountCreateBy(Long aId);

    /**
     * 保存教师上传的信息资料
     * @param uploadFiles
     */
    void improveUpload_files(UploadFiles uploadFiles);

    /**
     * 更新登陆信息完善表的创建者
     * @param iId
     */
    void updateLoginInformationCreateBy(Long iId);

}
