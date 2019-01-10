package com.nsu.action.school;


import com.nsu.action.BaseAction;
import com.nsu.common.Anonymous;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school/")
public class SchoolInfo extends BaseAction  {
//	private String schoolUrl;
//	private String typeInfo;
//	private Map<String, Object> headMessage;

    @RequestMapping("info")
    public String schoolInfo(String typeInfo, String schoolUrl) {

        if (typeInfo.equals("1") || typeInfo.equals("2") || typeInfo.equals("3") || typeInfo.equals("4")) {
            try {
                getRequest().setAttribute("headMessage", getServiceManager().getSchoolInfoAdminService().getOverviewAdminService(schoolUrl, typeInfo));
            } catch (Exception e) {
                // TODO: handle exception
            }
            return "/school_new/common/info";
//			return SUCCESS;
        }
        return null;
    }


}
