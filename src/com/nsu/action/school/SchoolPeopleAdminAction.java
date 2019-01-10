package com.nsu.action.school;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.action.fileupload.*;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/school_admin/")
public class SchoolPeopleAdminAction extends UploadAction {
    //    private String myContent;
//    private String name;
    @Autowired
    private PropertiesPlaceholder placeholder;

    @RequestMapping({"add_people_view_01", "add_people_view_02", "add_people_view_03", "add_people_view_04", "add_people_view_05", "add_people_view_06"})
    public String addPeopleViewCommon() {
        try {
            String peopleInfo = (String) getSession().getAttribute("peopleInfo");
            String myContent = (String) getSession().getAttribute("myContent");
            String name = (String) getSession().getAttribute("name");

            getRequest().setAttribute("peopleInfo", peopleInfo);
            getRequest().setAttribute("myContent", myContent);
            getRequest().setAttribute("name", name);

            getSession().removeAttribute("name");
            getSession().removeAttribute("peopleInfo");
            getSession().removeAttribute("myContent");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/school_admin/admin_overview/school_admin_people_add_0" + UrlUtil.getNumber(getRequest().getRequestURI());
    }

    @RequestMapping({"add_people_01", "add_people_02", "add_people_03", "add_people_04", "add_people_05"
            , "add_people_06"})
    public String addPeople(MultipartFile file, String name, String myContent) {
//        String result = savePic(false, file, name, myContent);
//        if (result.contains("redirect"))
//            return result;
//        return addText(false, name, myContent, result, getSchoolUrl());
        String result = addText(true, name, myContent);
        if (result != null)
            return result;
        name = Clear.clearXSS(name);
        myContent = Clear.cleraJs(myContent);
        return savePic(true, file, name, myContent);

    }

    @RequestMapping({"get_people_list_01", "get_people_list_02", "get_people_list_03", "get_people_list_04",
            "get_people_list_05", "get_people_list_06"})
    public String getPeopleListAction(String page) {
        int pageSize = 10;
        int currPage = 1;
        try {
            currPage = Integer.parseInt(page);
        } catch (Exception e) {
            currPage = 1;
        }
        Map<String, Integer> pageMap = new HashMap<String, Integer>();
        pageMap.put("pageStart", (currPage - 1) * pageSize);
        pageMap.put("pageEnd", pageSize);
        String schoolUrl = getSchoolUrl();
        List<Map<String, Object>> peopleList = getServiceManager().getSchoolPeopleAdminService().getPeopleList(schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()), pageMap);

        getRequest().setAttribute("peopleList", peopleList);

        long peopleTotal = 0;
        try {
            peopleTotal = getServiceManager().getSchoolPeopleAdminService().getPeopleTATOL(schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pageCode = PageUtil.genPagination("get_people_list_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action", peopleTotal, currPage, pageSize, ("token=" + getSession().getAttribute("token")));

        getRequest().setAttribute("pageCode", pageCode);
        return peopleListView(UrlUtil.getNumber(getRequest().getRequestURI()));
    }

    private String peopleListView(String number) {
        switch (number) {
            case "1":
            case "2":
            case "3":
                return "/school_admin/admin_overview/school_admin_people_list_0" + number;
            case "4":
                return "/school_admin/admin_football/school_admin_people_list_04";
            case "5":
                return "/school_admin/admin_match/school_admin_people_list_05";
            case "6":
                return "/school_admin/admin_match/school_admin_people_list_06";
//            case "7":
//                return "";
//            case "8":
//                return "";
        }
        return "";
    }


    @RequestMapping({"update_people_view_01", "update_people_view_02", "update_people_view_03",
            "update_people_view_04", "update_people_view_05", "update_people_view_06"})
    public String updatePeopleViewCommon(String scId) {
        String schoolUrl = getSchoolUrl();
        if (VerifyUtil.isNotEmpty(scId)) {
            try {
                Map<String, Object> peopleMap = getServiceManager().getSchoolPeopleAdminService().getPeopleMap(schoolUrl, scId);
                getRequest().setAttribute("peopleMap", peopleMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            getSession().removeAttribute("peopleInfo");
        } else if (getSession().getAttribute("scId") != null) {
            String name;
            String myContent;
            scId = getSession().getAttribute("scId").toString();
            getSession().removeAttribute("scId");
            Map<String, Object> peopleMap = null;
            try {
                peopleMap = getServiceManager().getSchoolPeopleAdminService().getPeopleMap(schoolUrl, scId);

                String peopleInfo = getSession().getAttribute("peopleInfo").toString();
                myContent = getSession().getAttribute("myContent").toString();

                name = getSession().getAttribute("name").toString();

                getRequest().setAttribute("peopleInfo", peopleInfo);
                getRequest().setAttribute("myContent", myContent);
                getRequest().setAttribute("name", name);

                getSession().removeAttribute("myContent");
                getSession().removeAttribute("peopleInfo");

                getSession().removeAttribute("name");
                peopleMap.put("_name", name);
                peopleMap.put("content", myContent);
            } catch (Exception e) {
                try {
                    peopleMap = getServiceManager().getSchoolPeopleAdminService().getPeopleMap(schoolUrl, scId);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            getRequest().setAttribute("peopleMap", peopleMap);
        }
        return "/school_admin/admin_overview/school_admin_people_update_0" + UrlUtil.getNumber(getRequest().getRequestURI());
    }


    @RequestMapping({"update_people_01", "update_people_02", "update_people_03"
            , "update_people_04", "update_people_05", "update_people_06"})
    public String updatePeopleCommon(MultipartFile file, String name, String myContent) {
//        String result = savePic(false, file, name, myContent);
//        if (result.contains("redirect"))
//            return result;
//
//        return addText(false, name, myContent, result, getSchoolUrl());
        String result = addText(false, name, myContent);
        if (result != null)
            return result;
        name = Clear.clearXSS(name);
        myContent = Clear.cleraJs(myContent);
        return savePic(false, file, name, myContent);
    }

    @RequestMapping({"delete_people_01", "delete_people_02", "delete_people_03", "delete_people_04"
            , "delete_people_05", "delete_people_06"})
    public void deletePeopleCommon(String scId) throws IOException {
        String schoolUrl = getSchoolUrl();
        JSONObject jsonObject = new JSONObject();
        if (VerifyUtil.isNotEmpty(scId)) {
            if (getServiceManager().getSchoolPeopleAdminService().deletePeople(schoolUrl, scId)) {
                jsonObject.put("success", true);
                jsonObject.put("token", getSession().getAttribute("token"));
            } else {
                jsonObject.put("success", false);
                jsonObject.put("token", getSession().getAttribute("token"));
                jsonObject.put("errorInfo", "操作失败，请重试！");
            }
        } else {
            jsonObject.put("success", false);
            jsonObject.put("token", getSession().getAttribute("token"));
            jsonObject.put("errorInfo", "非法操作！！");
        }
        ResponseUtil.write(getResponse(), jsonObject);
    }


    private String savePic(boolean isAdd, MultipartFile file, String name, String myContent) {
        String peopleInfo;
        String picPath;
        try {
            picPath = save(file, placeholder.getValue(Constants.UPLOAD_BASE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            peopleInfo = "添加失败，请重试！";
            getSession().setAttribute("peopleInfo", peopleInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("name", name);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (ExtensionsException e) {
            e.printStackTrace();
            peopleInfo = "请选择图片类型文件！";
            getSession().setAttribute("peopleInfo", peopleInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("name", name);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (LengthException e) {
            e.printStackTrace();
            peopleInfo = "图片过大，请重试！";
            getSession().setAttribute("peopleInfo", peopleInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("name", name);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (SizeException e) {
            e.printStackTrace();
            peopleInfo = "图片尺寸过大，请重试！";
            getSession().setAttribute("peopleInfo", peopleInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("name", name);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (NullFileParamException e) {
            e.printStackTrace();
            peopleInfo = "获取文件失败，请重试！";
            getSession().setAttribute("peopleInfo", peopleInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("name", name);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        //添加到数据库
        try {
            boolean result;
            if (isAdd) {
                result = getServiceManager().getSchoolPeopleAdminService().addPeople(name, myContent, picPath, UrlUtil.getNumber(getRequest().getRequestURI()), getSchoolUrl());
            } else {
                result = getServiceManager().getSchoolPeopleAdminService().updatePeople(name, myContent, picPath, UrlUtil.getNumber(getRequest().getRequestURI()), getSchoolUrl());
            }
            if (result) {//添加成功
                getSession().setAttribute("peopleInfo", (isAdd?"添加成功":"修改成功"));
                return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        peopleInfo = "添加失败，请重试！";
        getSession().setAttribute("peopleInfo", peopleInfo);
        getSession().setAttribute("myContent", myContent);
        getSession().setAttribute("name", name);
        return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";

    }


    private String addText(boolean isAdd, String name, String myContent) {
        if (!VerifyUtil.isNotEmpty(name) || name.length() > 10) {
            getSession().setAttribute("peopleInfo", "请输入10个字符以内的名字");
            getSession().setAttribute("myContent", myContent);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }

        if (!VerifyUtil.isNotEmpty(myContent) || myContent.length() == 0 || myContent.length() > 1900) {
            getSession().setAttribute("peopleInfo", "请输入1000个字符以内的简介");
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("name", name);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }

        if (!VerifyUtil.isNameEC(name)) {
            getSession().setAttribute("peopleInfo", "请输入合法姓名");
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("name", name);
            return "redirect:/school_admin/" + (isAdd ? "add" : "update") + "_people_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        return null;
    }

    @Override
    protected UploadType getUploadType() {
        return UploadType.User_Picture_Edit_SchoolPeople;
    }
}
