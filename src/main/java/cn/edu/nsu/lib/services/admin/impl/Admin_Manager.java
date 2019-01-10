package cn.edu.nsu.lib.services.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Service
public class Admin_Manager {
    @Autowired
    private Admin_main_Service admini_service;
    @Autowired
    private Admin_Labman_Service admini_labman_service;
    @Autowired
    private Admin_LabmanC_Service admini_labmanC_service;
    @Autowired
    private Admin_Notice_Service admini_notice_service;
    @Autowired
    private Admin_NoticeC_Service admini_noticeC_service;
    @Autowired
    private Admin_Prize_Service admini_prize_service;

    public Admin_main_Service getAdmini_service() {
        return admini_service;
    }

    public void setAdmini_service(Admin_main_Service admini_service) {
        this.admini_service = admini_service;
    }

    public Admin_Labman_Service getAdmini_labman_service() {
        return admini_labman_service;
    }

    public void setAdmini_labman_service(Admin_Labman_Service admini_labman_service) {
        this.admini_labman_service = admini_labman_service;
    }

    public Admin_LabmanC_Service getAdmini_labmanC_service() {
        return admini_labmanC_service;
    }

    public void setAdmini_labmanC_service(Admin_LabmanC_Service admini_labmanC_service) {
        this.admini_labmanC_service = admini_labmanC_service;
    }

    public Admin_Notice_Service getAdmini_notice_service() {
        return admini_notice_service;
    }

    public void setAdmini_notice_service(Admin_Notice_Service admini_notice_service) {
        this.admini_notice_service = admini_notice_service;
    }

    public Admin_NoticeC_Service getAdmini_noticeC_service() {
        return admini_noticeC_service;
    }

    public void setAdmini_noticeC_service(Admin_NoticeC_Service admini_noticeC_service) {
        this.admini_noticeC_service = admini_noticeC_service;
    }

    public Admin_Prize_Service getAdmini_prize_service() {
        return admini_prize_service;
    }

    public void setAdmini_prize_service(Admin_Prize_Service admini_prize_service) {
        this.admini_prize_service = admini_prize_service;
    }
}
