package com.nsu.dao.teacher.improveinformation;

import com.nsu.entity.Account;
import com.nsu.entity.LoginInformation;
import com.nsu.entity.Teacher;
import com.nsu.entity.UploadFiles;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @PackageName : com.nsu.dao.teacher.improveinformation
 * @Author : BuDD
 * @CreateTime : 2017/7/17
 * @Version : 1.0
 * @Description : 完善信息持久层
 */
@Repository
@Mapper
public interface ImproveInformationDao {

    @Insert(value =
            "insert into account" +
                    "(USERNAME,PASSWORD,RECENT_LOGIN,TYPE,STATUS,CREATE_DATE) values" +
                    "(#{username},#{password},#{recentLogin},#{type},#{status},#{createDate})")
    @Options(useGeneratedKeys = true, keyProperty = "aId")
    /**
     * 完善账户表并返回这个Account的主键ID
     * TODO 还未填写第三方id的字段
     * @param account
     */
    void improveAccount(Account account);

    @Update("update account set CREATE_BY=#{aId} where A_ID = #{aId}")
    /**
     * 更新Account创建者
     * @param aId
     */
    void updateAccountCreateBy(Long aId);

    @Insert("insert into login_information" +
            "(I_ID,T_NAME,T_SEX,STUDY_GRADE,CH_STATUS,STATUS,CREATE_DATE,A_ID)" +
            "values(" +
            "#{iId},#{tName},#{tSex},#{studyGrade},#{chStatus},#{status},#{createDate},#{aId})")
    @Options(useGeneratedKeys = true, keyProperty = "iId")
    /**
     * 完善登陆信息表并返回这个LoginInformation的主键ID
     * @param loginInformation
     */
    void improveInformation(LoginInformation loginInformation);

    @Update("update login_information set CREATE_BY=#{iId} where I_ID = #{iId}")
    /**
     * 更新LoginInformation创建者
     * @param iId
     */
    void updateLoginInformationCreateBy(Long iId);

    @Insert("insert into upload_files(SAVE_PATH,FILE_TYPE,CERTIFICATE,STATUS,CREATE_DATE,CREATE_BY,A_ID)" +
            "values(#{savePath},#{fileType},#{certificate},#{status},#{createDate},#{createBy},#{aId})")
    /**
     * 完善文件上传的资料
     * @param uploadFiles
     */
    void improveUploadFiles(UploadFiles uploadFiles);
}
