package com.nsu.action.school.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.action.BaseAction;
import com.nsu.action.fileupload.ExtensionsException;
import com.nsu.action.fileupload.LengthException;
import com.nsu.action.fileupload.NullFileParamException;
import com.nsu.action.fileupload.SizeException;
import com.nsu.action.fileupload.UploadAction;
import com.nsu.action.fileupload.UploadType;
import com.nsu.util.base.Clear;
import com.nsu.util.base.PageUtil;
import com.nsu.util.base.ResponseUtil;
import com.nsu.util.base.VerifyUtil;

import com.nsu.util.core.UrlUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/school_admin/")
public class SchoolNewsAdminAction extends BaseAction {
//    private String myContent;
//    private String content;
//    private String title;
//    private String newsInfo;
//    private String page;
//    private String scId;
//    private String pageCode;
//
//    private List<Map<String, Object>> newsList;
//    private Map<String, Object> newsMap;

    @RequestMapping({"add_news_view_01", "add_news_view_02", "add_news_view_03", "add_news_view_04",
            "add_news_view_05", "add_news_view_06", "add_news_view_07", "add_news_view_08", "add_news_view_09"})
    public String addNewsViewCommon() {
        try {
            String newsInfo = getSession().getAttribute("newsInfo").toString();
            String myContent = getSession().getAttribute("myContent").toString();
            String title = getSession().getAttribute("title").toString();

            getRequest().setAttribute("newsInfo",newsInfo);
            getRequest().setAttribute("myContent",myContent);
            getRequest().setAttribute("myContent",myContent);

            getSession().removeAttribute("title");
            getSession().removeAttribute("newsInfo");
            getSession().removeAttribute("myContent");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsView(UrlUtil.getNumber(getRequest().getRequestURI()),true);
    }

    @RequestMapping({"add_news_01", "add_news_02", "add_news_03", "add_news_04",
            "add_news_05", "add_news_06", "add_news_07", "add_news_08", "add_news_09"})
    public String addNews(String title, String myContent, String content) {
        if (!VerifyUtil.isNotEmpty(title) || title.length() == 0 || title.length() > 26) {
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("newsInfo", "标题范围：0-26字");
            return "redirect:/school_admin/add_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        if (!VerifyUtil.isNotEmpty(myContent) || myContent.length() == 0 || myContent.length() > 1900) {
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("newsInfo", "内容范围：0-1900字");
            return "redirect:/school_admin/add_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }

        title = Clear.clearXSS(title);
        myContent = Clear.cleraJs(myContent);
        content = Clear.clearAll(content);

        String content_text = null;
        if (content.length() > 100) {
            content_text = content.substring(0, 100) + "........";
        } else {
            content_text = content.substring(0, content.length()) + "........";
        }

        user = getLoginUser();
        String schoolUrl = user.get("school_url").toString();
        String newsInfo = null;
        try {
            if (getServiceManager().getSchoolNewsAdminService().addNews(title, content_text, myContent, UrlUtil.getNumber(getRequest().getRequestURI()),
                    schoolUrl)) {
                getSession().setAttribute("newsInfo", 1);
                return "redirect:/school_admin/add_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            } else {
                newsInfo = "添加失败，请重试！";
                getSession().setAttribute("newsInfo", newsInfo);
                getSession().setAttribute("myContent", myContent);
                getSession().setAttribute("title", title);
                return "redirect:/school_admin/add_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            newsInfo = "添加失败，请重试！";
            getSession().setAttribute("newsInfo", newsInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/add_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
    }

    private String newsView(String number,boolean isAdd) {
        switch (number) {
            case "1":
            case "2":
            case "3":
            case "4":
                return "/school_admin/admin_news/school_admin_news_"+(isAdd?"add":"list")+"_0" + number;
            case "5":
            case "6":
            case "7":
                return "/school_admin/admin_study/school_admin_news_"+(isAdd?"add":"list")+"_0" + number;
            case "8":
            case "9":
                return "/school_admin/admin_invite/school_admin_news_"+(isAdd?"add":"list")+"_0" + number;
        }
        return "";
    }


    @RequestMapping({"update_news_view_01", "update_news_view_02", "update_news_view_03", "update_news_view_04",
            "update_news_view_05", "update_news_view_06", "update_news_view_07", "update_news_view_08", "update_news_view_09"})
    public String updateNewsViewCommon(String scId) {
        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        if (VerifyUtil.isNotEmpty(scId)) {
            try {
                Map<String, Object> newsMap = getServiceManager().getSchoolNewsAdminService().getNewsMap(schoolUrl, scId);
                getRequest().setAttribute("newsMap", newsMap);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
            getSession().removeAttribute("newsInfo");
            return "/school_admin/admin_news/school_admin_news_update_0" + UrlUtil.getNumber(getRequest().getRequestURI());
        } else if (getSession().getAttribute("scId") != null) {
            scId = getSession().getAttribute("scId").toString();
            getSession().removeAttribute("scId");
            Map<String, Object> newsMap = null;
            try {
                newsMap = getServiceManager().getSchoolNewsAdminService().getNewsMap(schoolUrl, scId);
                getRequest().setAttribute("newsMap", newsMap);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                getRequest().setAttribute("newsInfo", getSession().getAttribute("newsInfo").toString());
//                getRequest().setAttribute("myContent",getSession().getAttribute("myContent").toString());
//                getRequest().setAttribute("title",getSession().getAttribute("title").toString());
//
//                myContent = getSession().getAttribute("myContent").toString();
//
//                title = getSession().getAttribute("title").toString();

                newsMap.put("title", getSession().getAttribute("title").toString());
                newsMap.put("content", getSession().getAttribute("myContent").toString());
                getSession().removeAttribute("newsInfo");
                getSession().removeAttribute("myContent");
                getSession().removeAttribute("title");

                getRequest().setAttribute("newsMap", newsMap);
                return "/school_admin/admin_news/school_admin_news_update_0" + UrlUtil.getNumber(getRequest().getRequestURI());
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                try {
                    newsMap = getServiceManager().getSchoolNewsAdminService().getNewsMap(schoolUrl, scId);
                    getRequest().setAttribute("newsMap", newsMap);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    log.error(e1.getMessage());
                }
                return "/school_admin/admin_news/school_admin_news_update_0" + UrlUtil.getNumber(getRequest().getRequestURI());
            }
        }
        return null;
    }

    @RequestMapping({"update_news_01", "update_news_02", "update_news_03", "update_news_04", "update_news_05", "update_news_06", "update_news_07", "update_news_08", "update_news_09"})
    public String updateNewsCommon(String title, String scId, String myContent, String content) {
        if (!VerifyUtil.isNotEmpty(title) || title.length() == 0 || title.length() > 26) {
            getSession().setAttribute("scId", scId);
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("newsInfo", "标题范围：0-26字");
            return "redirect:/school_admin/update_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        if (!VerifyUtil.isNotEmpty(myContent) || myContent.length() == 0 || myContent.length() > 1900) {
            getSession().setAttribute("scId", scId);
            getSession().setAttribute("title", title);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("newsInfo", "内容范围：0-1000字");
            return "redirect:/school_admin/update_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }

        title = Clear.clearXSS(title);
        myContent = Clear.cleraJs(myContent);
        System.out.println(content);
        content = Clear.clearAll(content);
        System.out.println(content);
        String content_text = null;
        if (content.length() > 100) {
            content_text = content.substring(0, 100) + "........";
        } else {
            content_text = content.substring(0, content.length()) + "........";
        }
        user = getLoginUser();
        String schoolUrl = user.get("school_url").toString();
        String newsInfo = null;
        try {
            if (getServiceManager().getSchoolNewsAdminService().updateNews(title, content_text, myContent, schoolUrl,
                    scId)) {
                getSession().setAttribute("scId", scId);
                getSession().setAttribute("newsInfo", "修改成功！");
                return "redirect:/school_admin/update_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            } else {
                newsInfo = "修改失败，请重试！";
                getSession().setAttribute("newsInfo", newsInfo);
                getSession().setAttribute("myContent", myContent);
                getSession().setAttribute("title", title);
                getSession().setAttribute("scId", scId);
                return "redirect:/school_admin/update_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            newsInfo = "添加失败，请重试！";
            getSession().setAttribute("newsInfo", newsInfo);
            getSession().setAttribute("myContent", myContent);
            getSession().setAttribute("title", title);
            return "redirect:/school_admin/update_news_view_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }

    }

    @RequestMapping({"delele_news_01", "delele_news_02", "delele_news_03", "delele_news_04", "delele_news_05", "delele_news_06", "delele_news_07", "delele_news_08", "delele_news_09"})
    @ResponseBody
    public void deleteNewsCommon(String scId) throws IOException {
        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        JSONObject jsonObject = new JSONObject();
        if (VerifyUtil.isNotEmpty(scId)) {
            try {
                if (getServiceManager().getSchoolNewsAdminService().deleteNews(schoolUrl, scId)) {
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


    @RequestMapping({"get_news_list_01", "get_news_list_02", "get_news_list_03", "get_news_list_04",
            "get_news_list_05", "get_news_list_06", "get_news_list_07", "get_news_list_06", "get_news_list_09"})
    public String getNewsListAction(String page) {
        int pageSize = 10;
        int currPage = 1;
        try {
            currPage = Integer.parseInt(page);
        } catch (Exception e) {
            // TODO: handle exception
            currPage = 1;
        }
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("pageStart", (currPage - 1) * pageSize);
        pageMap.put("pageEnd", pageSize);
        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        List<Map<String, Object>> newsList = null;
        try {
            newsList = getServiceManager().getSchoolNewsAdminService().getNewsList(schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()), pageMap);
            getRequest().setAttribute("newsList", newsList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        long newsTatol = 0;
        try {
            newsTatol = getServiceManager().getSchoolNewsAdminService().getNewsTATOL(schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()));
            getRequest().setAttribute("newsTatol", newsTatol);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        StringBuffer pageParam = new StringBuffer();
        pageParam.append("token=" + getSession().getAttribute("token"));
        String pageCode = PageUtil.genPagination("get_feature_list_02.action", newsTatol, currPage, pageSize,
                pageParam.toString());
        getRequest().setAttribute("pageCode", pageCode);
        return newsView( UrlUtil.getNumber(getRequest().getRequestURI()),false);
//        return "/school_admin/admin_news/school_admin_news_list_0" + UrlUtil.getNumber(getRequest().getRequestURI());
    }

}
