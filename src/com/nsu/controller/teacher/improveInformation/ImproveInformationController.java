package com.nsu.controller.teacher.improveInformation;

import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.common.FileUploadBean;
import com.nsu.bean.teacher.information.InformationBean;
import com.nsu.common.Role;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.common.UploadBaseController;
import com.nsu.entity.Account;
import com.nsu.entity.LoginInformation;
import com.nsu.entity.Teacher;
import com.nsu.entity.UploadFiles;
import com.nsu.exception.upload.ExtensionsException;
import com.nsu.exception.upload.LengthException;
import com.nsu.exception.upload.NullFileParamException;
import com.nsu.exception.upload.SizeException;
import com.nsu.exception.validate.AnalysisException;
import com.nsu.exception.validate.CustomValidateException;
import com.nsu.exception.validate.DataException;
import com.nsu.exception.validate.IllegalFormatException;
import com.nsu.service.common.IFileUpoadService;
import com.nsu.service.teacher.information.ImproveInformationService;
import com.nsu.utils.InfoProtUtil;
import com.nsu.utils.V;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @PackageName : com.nsu.controller.teacher.improveInformation
 * @Author : BuDD
 * @CreateTime : 2017/7/17
 * @Version : 1.0
 * @Description :完善信息控制层
 */
@Controller
@RequestMapping("/teacher")
public class ImproveInformationController extends UploadBaseController {

    @Autowired
    private ImproveInformationService improveInformationService;
    @Autowired
    private HttpSession session;
    @Autowired
    private IFileUpoadService iFileUpoadService;

    /**
     * 教师第一次登陆需要完善信息
     * 账户信息和完善信息
     *
     * @param informationBean
     */
    @InterceptorAnno(isAjax = true, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/improveInformation", method = RequestMethod.POST)
    public Object improveInformation(@RequestBody InformationBean informationBean) {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name", "教师完善信息");
        try {
            Account account = new Account();
            LoginInformation loginInformation = new LoginInformation();
            UploadFiles uploadFiles = new UploadFiles();
            V.validateByAnnotation(informationBean, "");
            improveMessage(account, loginInformation, uploadFiles, informationBean);
            improveInformationService.improveAll(account, loginInformation, uploadFiles);
            ajaxBean.setStatus("200");
            return ajaxBean;
        } catch (IllegalFormatException e) {
            ajaxBean.setStatus("500");
            ajaxBean.setMsg(e.getMessage());
            log.error(e.getMessage());
            return ajaxBean;
        } catch (AnalysisException e) {
            ajaxBean.setStatus("500");
            ajaxBean.setMsg(e.getMessage());
            log.error(e.getMessage());
            return ajaxBean;
        } catch (CustomValidateException e) {
            ajaxBean.setStatus("500");
            ajaxBean.setMsg(e.getMessage());
            log.error(e.getMessage());
            return ajaxBean;
        } catch (DataException e) {
            ajaxBean.setStatus("500");
            ajaxBean.setMsg(e.getMessage());
            return ajaxBean;
        } catch (Exception e) {
            ajaxBean.setStatus("500");
            ajaxBean.setMsg(e.getMessage());
            log.error(e.getMessage());
            return ajaxBean;
        }
    }

    /**
     * 把数据封装到各自的对象中
     * TODO:密码加密的盐还没有确定，暂时再InfoProUtil里面添加了固定的盐
     *
     * @param account
     * @param loginInformation
     * @param uploadFiles
     * @param informationBean
     */
    public void improveMessage(Account account, LoginInformation loginInformation, UploadFiles uploadFiles, InformationBean informationBean) {
        account.setUsername(informationBean.getUsername());
        account.setPassword(InfoProtUtil.parseStrToMd5L32(informationBean.getPassword()));

        loginInformation.settName(informationBean.gettName());
        loginInformation.settSex(informationBean.gettSex());
        loginInformation.setStudyGrade(informationBean.getStudygrade());
//        TODO 第一次是第三方登陆的，需要传入的是第三方登陆的ID
        try {
            String savePath = fileUpload(informationBean.getFile(), "teacher", informationBean.getFile().getContentType(), Integer.toString(account.getaId()));
            uploadFiles.setSavePath(savePath);
            uploadFiles.setFileType(8);
            uploadFiles.setCertificate(savePath.substring(savePath.lastIndexOf(File.separator)));
        } catch (ExtensionsException e) {
            log.error(e.getMessage());
        } catch (NullFileParamException e) {
            log.error(e.getMessage());
        } catch (SizeException e) {
            log.error(e.getMessage());
        } catch (LengthException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
