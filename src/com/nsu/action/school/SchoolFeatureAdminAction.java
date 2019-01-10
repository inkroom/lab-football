package com.nsu.action.school;

import java.io.IOException;
import java.util.HashMap;
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
import com.nsu.util.base.Clear;
import com.nsu.util.base.PageUtil;
import com.nsu.util.base.ResponseUtil;
import com.nsu.util.base.VerifyUtil;

import com.nsu.util.core.UrlUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/school_admin/")
public class SchoolFeatureAdminAction extends UploadAction {
//    private String myContent;
//    private String content;
//    private String title;
//    private String featureInfo;
//    private String page;
//    private String scId;
//    private String pageCode;
//
//    private List<Map<String, Object>> featureList;
//    private Map<String, Object> featureMap;

    @RequestMapping({"add_feature_view_01", "add_feature_view_02", "add_feature_view_03", "add_feature_view_04", "add_feature_view_05"})
    public String addFeatureViewCommon() {
        try {
            HttpServletRequest request = getRequest();

            request.setAttribute("featureInfo", getSession().getAttribute("featureInfo").toString());
            request.setAttribute("myContent", getSession().getAttribute("myContent").toString());
            request.setAttribute("title", getSession().getAttribute("title").toString());
            getSession().removeAttribute("title");
            getSession().removeAttribute("featureInfo");
            getSession().removeAttribute("myContent");
        } catch (Exception e) {
        }
        return "/school_admin/admin_football/school_admin_feature_add_0" + UrlUtil.getNumber(getRequest().getRequestURI());
    }


    @RequestMapping({"update_feature_view_01", "update_feature_view_02", "update_feature_view_03", "update_feature_view_04", "update_feature_view_05"})
    public String updateFeatureViewCommon(String scId) {
        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        Map<String, Object> featureMap = null;
        if (VerifyUtil.isNotEmpty(scId)) {
            try {
                getRequest().setAttribute("featureMap", getServiceManager().getSchoolFeatureAdminService().getFeatureMap(schoolUrl, scId));
//                featureMap = getServiceManager().getSchoolFeatureAdminService().getFeatureMap(schoolUrl, scId);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
            getSession().removeAttribute("featureInfo");
            return "/school_admin/admin_match/school_admin_feature_update_0" + UrlUtil.getNumber(getRequest().getRequestURI());
        } else if (getSession().getAttribute("scId") != null) {
            scId = getSession().getAttribute("scId").toString();
            getSession().removeAttribute("scId");
            try {
//                getRequest().setAttribute("featureMap",getServiceManager().getSchoolFeatureAdminService().getFeatureMap(schoolUrl, scId));
                featureMap = getServiceManager().getSchoolFeatureAdminService().getFeatureMap(schoolUrl, scId);
                getRequest().setAttribute("featureMap", featureMap);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
//                featureInfo = getSession().getAttribute("featureInfo").toString();
//                myContent = getSession().getAttribute("myContent").toString();
//                title = getSession().getAttribute("title").toString();

                getRequest().setAttribute("featureInfo", getSession().getAttribute("featureInfo").toString());
                featureMap.put("title", getSession().getAttribute("title").toString());
                featureMap.put("content", getSession().getAttribute("myContent").toString());

                getSession().removeAttribute("featureInfo");
                getSession().removeAttribute("myContent");
                getSession().removeAttribute("title");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                try {
                    featureMap = getServiceManager().getSchoolFeatureAdminService().getFeatureMap(schoolUrl, scId);
                    getRequest().setAttribute("featureMap", featureMap);
                } catch (Exception e1) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                    e1.printStackTrace();
                }
            }
            return "/school_admin/admin_match/school_admin_feature_update_0" + UrlUtil.getNumber(getRequest().getRequestURI());
        }
        return null;
    }
    @Autowired
    private PropertiesPlaceholder placeholder;

    @RequestMapping({"update_feature_01", "update_feature_02", "update_feature_03", "update_feature_04", "update_feature_05"})
    public String updateFeatureCommon(String title, String myContent, String scId, String content, MultipartFile file) {
        if (!VerifyUtil.isNotEmpty(title) || title.length() == 0 || title.length() > 26) {
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("featureInfo", "标题范围：0-26字");
            getSession().setAttribute("scId", scId);
            return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        if (!VerifyUtil.isNotEmpty(myContent) || myContent.length() == 0 || myContent.length() > 1900) {
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("featureInfo", "内容范围：0-1900字");
            getSession().setAttribute("scId", scId);
            return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        title = Clear.clearXSS(title);
        myContent = Clear.cleraJs(myContent);
        content = Clear.clearAll(content);
        String content_text = null;
        String pic = null;
        if (content.length() > 100) {
            content_text = content.substring(0, 100) + "........";
        } else {
            content_text = content.substring(0, content.length()) + "........";
        }
        String featureInfo = null;
        try {
            pic = save(file,placeholder.getValue(Constants.UPLOAD_BASE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            featureInfo = "添加失败，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            getSession().setAttribute("scId", scId);
            return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (ExtensionsException e) {
            e.printStackTrace();
            featureInfo = "请选择图片类型文件！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            getSession().setAttribute("scId", scId);
            return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (LengthException e) {
            e.printStackTrace();
            featureInfo = "图片过大，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            getSession().setAttribute("scId", scId);
            return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (SizeException e) {
            e.printStackTrace();
            featureInfo = "图片尺寸过大，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            getSession().setAttribute("scId", scId);
            return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (NullFileParamException e) {
            e.printStackTrace();
        }
        try {
            if (getServiceManager().getSchoolFeatureAdminService().updateFeature(title, content_text, myContent, pic, getSchoolUrl(), scId)) {
                getSession().setAttribute("scId", scId);
                getSession().setAttribute("featureInfo", "修改成功！");
                return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            } else {
                featureInfo = "修改失败，请重试！";
                getSession().setAttribute("featureInfo", featureInfo);
                getSession().setAttribute("myContent", myContent);
                getSession().setAttribute("title", title);
                return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return "redirect:/school_admin/update_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }

    }

    @RequestMapping({"delele_feature_01", "delele_feature_02", "delele_feature_03", "delele_feature_04", "delele_feature_05"})
    @ResponseBody
    public void deleteFeatureCommon(String scId) throws IOException {
        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        JSONObject jsonObject = new JSONObject();
        if (VerifyUtil.isNotEmpty(scId)) {
            try {
                if (getServiceManager().getSchoolFeatureAdminService().deleteFeature(schoolUrl, scId)) {
                    jsonObject.put("success", true);
                    jsonObject.put("token", getSession().getAttribute("token"));
                } else {
                    jsonObject.put("success", false);
                    jsonObject.put("token", getSession().getAttribute("token"));
                    jsonObject.put("errorInfo", "操作失败，请重试！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        } else {
            jsonObject.put("success", false);
            jsonObject.put("token", getSession().getAttribute("token"));
            jsonObject.put("errorInfo", "非法操作！！");
        }
        ResponseUtil.write(getResponse(), jsonObject);
    }

    /**
     * 添加不知道设么
     *
     * @return
     */
    @RequestMapping({"add_feature_01", "add_feature_02", "add_feature_03", "add_feature_04", "add_feature_05"})
    public String addFeature(String title, String myContent, String content,MultipartFile file) {
        if (!VerifyUtil.isNotEmpty(title) || title.length() == 0 || title.length() > 26) {
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("featureInfo", "标题范围：0-26字");
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        if (!VerifyUtil.isNotEmpty(myContent) || myContent.length() == 0 || myContent.length() > 1900) {
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("featureInfo", "内容范围：0-1900字");
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        String featureInfo = null;
        title = Clear.clearXSS(title);
        myContent = Clear.cleraJs(myContent);
        content = Clear.clearAll(content);
        String content_text = null;
        String pic = null;
//保存图片
        try {
            pic = save(file,placeholder.getValue(Constants.UPLOAD_BASE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            featureInfo = "添加失败，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (ExtensionsException e) {
            e.printStackTrace();
            featureInfo = "请选择图片类型文件！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (LengthException e) {
            e.printStackTrace();
            featureInfo = "图片过大，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (SizeException e) {
            e.printStackTrace();
            featureInfo = "图片尺寸过大，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (NullFileParamException e) {
            e.printStackTrace();
            featureInfo = "获取文件失败，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        try {
            if (getServiceManager().getSchoolFeatureAdminService().addFeature(title, content_text, myContent, pic, UrlUtil.getNumber(getRequest().getRequestURI()), getSchoolUrl())) {
                getSession().setAttribute("featureInfo", "1");
                //成功
                return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            } else {
                featureInfo = "添加失败，请重试！";
                getSession().setAttribute("featureInfo", featureInfo);
                getSession().setAttribute("myContent", myContent);
                getSession().setAttribute("title", title);
                return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            featureInfo = "添加失败，请重试！";
            getSession().setAttribute("featureInfo", featureInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/add_feature_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
    }

    @RequestMapping({"get_feature_list_01", "get_feature_list_02", "get_feature_list_03", "get_feature_list_04", "get_feature_list_05"})
    public String getFeatureListAction(String page, String pageCode) {
        int pageSize = 10;
        int currPage = 1;
        try {
            currPage = Integer.parseInt(page);
        } catch (Exception e) {
            currPage = 1;
        }
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("pageStart", (currPage - 1) * pageSize);
        pageMap.put("pageEnd", pageSize);
        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        long peopleTatol = 0;
        try {
            List<Map<String, Object>> featureList = getServiceManager().getSchoolFeatureAdminService().getPeopleList(schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()), pageMap);
            peopleTatol = getServiceManager().getSchoolFeatureAdminService().getPeopleTATOL(schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()));

            getRequest().setAttribute("featureList",featureList);
            getRequest().setAttribute("peopleTatol",peopleTatol);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        pageCode = PageUtil.genPagination("get_feature_list_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action", peopleTatol, currPage, pageSize, ("token=" + getSession().getAttribute("token")));
        getRequest().setAttribute("pageCode",pageCode);
        return "/school_admin/admin_match/school_admin_feature_list_0" + UrlUtil.getNumber(getRequest().getRequestURI());
    }

    @Override
    protected UploadType getUploadType() {
        return UploadType.User_Picture_SchoolFeature;
    }


}
