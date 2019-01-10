package com.nsu.action.school.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.nsu.action.fileupload.ExtensionsException;
import com.nsu.action.fileupload.LengthException;
import com.nsu.action.fileupload.NullFileParamException;
import com.nsu.action.fileupload.SizeException;
import com.nsu.action.fileupload.UploadAction;
import com.nsu.action.fileupload.UploadType;
import com.nsu.common.Constants;
import com.nsu.handlers.PropertiesPlaceholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/school_admin/")
public class SchoolIndexAdminAction extends UploadAction {
    //    private String errorInfo;
//    private String picNum;
//    private String headPic;
//    public List<Map<String, Object>> indexRollPicture;

    @Autowired
    private PropertiesPlaceholder placeholder;

    @RequestMapping("admin_index_roll_picture")
    public String adminIndexRollPic() {
        String errorInfo;
        try {
            errorInfo = (String) getSession().getAttribute("errorInfo");
            getRequest().setAttribute("errorInfo", errorInfo);
            getSession().removeAttribute("errorInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String schoolUrl = getSchoolUrl();
        try {
            List<Map<String, Object>> indexRollPicture = getServiceManager().getSchoolService().getSchoolIndexRollImage(schoolUrl);
            getRequest().setAttribute("indexRollPicture", indexRollPicture);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return "/school_admin/admin_index/school_admin_index_roll_picture";
    }

    @RequestMapping("update_index_roll_pic")
    public String updateIndexRollPic(MultipartFile file, String picNum) throws IOException, ExtensionsException, LengthException, SizeException, NullFileParamException {
        String errorInfo;
        try {
            String fileDir = save(file, placeholder.getValue(Constants.UPLOAD_BASE_PATH));
            getServiceManager().getSchoolIndexAdminService().UpdateSchoolIndexRollPic(fileDir, getSchoolUrl(), picNum);
            errorInfo = "修改图片成功";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_roll_picture.action";
        } catch (IOException e) {
            e.printStackTrace();
            errorInfo = "1";
            getSession().setAttribute("errorInfo", errorInfo);
        } catch (ExtensionsException e) {
            e.printStackTrace();
            errorInfo = "2";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_roll_picture.action";
        } catch (LengthException e) {
            e.printStackTrace();
            errorInfo = "图片大小不能超过1M";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_roll_picture.action";
        } catch (SizeException e) {
            e.printStackTrace();
            errorInfo = "图片尺寸过大，不能超过1920*1080";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_roll_picture.action";
        } catch (NullFileParamException e) {
            e.printStackTrace();
            errorInfo = "获取文件失败，请重试";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_roll_picture.action";
        }
        return "redirect:/school_admin/admin_index_roll_picture.action";
    }

    @RequestMapping("admin_index_head_picture")
    public String adminIndexHeadPic() {
        String errorInfo;
        try {
            errorInfo = (String) getSession().getAttribute("errorInfo");
            getRequest().setAttribute("errorInfo", errorInfo);
            getSession().removeAttribute("errorInfo");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        String schoolUrl = getSchoolUrl();
        try {
            String headPic = getServiceManager().getSchoolService().getSchoolHeadPic(schoolUrl);
            getRequest().setAttribute("headPic", headPic);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return "/school_admin/admin_index/school_admin_index_head_picture";
    }

    @RequestMapping("update_index_head_pic")
    public String updateIndexHeadPic(MultipartFile file) throws IOException, ExtensionsException, LengthException, SizeException, NullFileParamException {
        String errorInfo;
        try {
            String fileDir = save(file, placeholder.getValue(Constants.UPLOAD_BASE_PATH));
            getServiceManager().getSchoolIndexAdminService().UpdateSchoolIndexHeadPic(fileDir, getSchoolUrl());
            errorInfo = "修改图片成功";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_head_picture.action";
        } catch (IOException e) {
            e.printStackTrace();
            errorInfo = "1";
            getSession().setAttribute("errorInfo", errorInfo);
        } catch (ExtensionsException e) {
            e.printStackTrace();
            errorInfo = "2";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_head_picture.action";
        } catch (LengthException e) {
            e.printStackTrace();
            errorInfo = "图片大小不能超过1M";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_head_picture.action";
        } catch (SizeException e) {
            e.printStackTrace();
            errorInfo = "图片尺寸过大，不能超过1920*1080";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_head_picture.action";
        } catch (NullFileParamException e) {
            e.printStackTrace();
            errorInfo = "获取文件失败，请重试";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/admin_index_head_picture.action";
        }
        return "redirect:/school_admin/admin_index_head_picture.action";
    }


    @Override
    protected UploadType getUploadType() {
        return UploadType.User_Picture_SchoolIndex;
    }

}
