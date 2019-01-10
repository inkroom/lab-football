package com.nsu.action.school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.action.BaseAction;
import com.nsu.common.Anonymous;
import com.nsu.util.base.PageUtilForPeople;
import com.nsu.util.base.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school/")
public class SchoolPeople extends BaseAction  {
//	private String schoolUrl;
//	private String page;
//	private String typePeople;
//	private String pageCode;
//	private String scId;
//
//
//	private List<Map<String, Object>> peopleList;
//	private Map<String, Object> peopleMap;
//	private String schoolName;
//


    @RequestMapping("people")
    public String schoolPeople(String page, String typePeople, String schoolUrl) {
        int pageSize = 9;
        int currPage = 1;
        try {
            currPage = Integer.parseInt(page);
        } catch (Exception e) {
            // TODO: handle exception
            currPage = 1;
        }
        Map<String, Integer> pageMap = new HashMap<String, Integer>();
        pageMap.put("pageStart", (currPage - 1) * pageSize);
        pageMap.put("pageEnd", pageSize);

        long peopleTotal = 0;

        if (typePeople.equals("1") || typePeople.equals("2") || typePeople.equals("3") || typePeople.equals("4") || typePeople.equals("5") || typePeople.equals("6")) {
            try {
                List<Map<String, Object>> peopleList = getServiceManager().getSchoolPeopleService().getPeopleList(schoolUrl, typePeople, pageMap);
                peopleTotal = getServiceManager().getSchoolPeopleService().getPeopleTATOL(schoolUrl, typePeople);

                getRequest().setAttribute("peopleList", peopleList);
                getRequest().setAttribute("peopleTotal", peopleTotal);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.error(e.getMessage());
            }
            saveCount(schoolUrl);
            String pageCode = PageUtilForPeople.genPagination(getServletContext().getAttribute("basePath").toString(), peopleTotal, currPage, pageSize, schoolUrl, "people", typePeople);
            getRequest().setAttribute("pageCode", pageCode);
            getRequest().setAttribute("page", page);
            getRequest().setAttribute("typePeople", typePeople);
            // return SUCCESS;
            return "/school_new/common/people_item";
        }
        return null;
    }

    @RequestMapping("people_info")
    public String getPeopleInfo(String typePeople, String scId, String schoolUrl) {
        if (typePeople.equals("1") || typePeople.equals("2") || typePeople.equals("3") || typePeople.equals("4") || typePeople.equals("5") || typePeople.equals("6")) {
            Map<String, Object> peopleMap = null;
            try {
                peopleMap = getServiceManager().getSchoolPeopleService().getPeopleMap(schoolUrl, typePeople, scId);
            } catch (Exception e) {
                // TODO: handle exception
                log.error(e.getMessage());
            }
            if (peopleMap != null) {
                getRequest().setAttribute("peopleMap", peopleMap);
                return "/school_new/common/people_detail";
//				return SUCCESS;
            } else {
                return null;
            }
        }
        return null;
    }

}
