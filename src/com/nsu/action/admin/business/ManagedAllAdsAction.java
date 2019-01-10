package com.nsu.action.admin.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.action.BaseAction;
import com.nsu.action.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_business/")
public class ManagedAllAdsAction extends BaseAction {
    private String adType;
    private List<Map<String, Object>> SchoolList = new ArrayList<Map<String, Object>>();
    private Map<String, Object> SchoolMap = new HashMap<String, Object>();
    private String searchInput;
    private String s_id;
    private String s_name;

    @RequestMapping("viewForSearch")
    public String viewForSearch() {
        SchoolList = getServiceManager().getBusinessAllAdsService().getAllSchools();
//        return "viewForSearch";
        return "/admin_business/searchForSchools";
    }
    @RequestMapping("searchSchool")
    public String searchSchool() {
        if (searchInput == null || searchInput.equals("")) {
            s_id = "%";
            s_name = "%";
        } else if (Tools.isNumer(searchInput)) {
            s_id = "%" + searchInput + "%";
            s_name = "%";
        } else if (Tools.isChinese(searchInput)) {
            s_id = "%";
            s_name = "%" + searchInput + "%";
        } else {
            s_id = "%";
            s_name = "%";
        }
        SchoolMap = getServiceManager().getBusinessAllAdsService().getSchoolBySid(s_id, s_name);
//        return "searchSchool";
        return "/admin_business/searchForSchools";
    }

    public String viewForManaged() {
        if (adType.equals("1")) {
            return "floatingAds";
        } else if (adType.equals("2")) {
            return "rollingAds";
        } else if (adType.equals("3")) {
            return "textAds";
        } else if (adType.equals("4")) {
            return "dropdownAds";
        }
        return "error";
    }


    //TODO get、set方法
    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public List<Map<String, Object>> getSchoolList() {
        return SchoolList;
    }

    public void setSchoolList(List<Map<String, Object>> schoolList) {
        SchoolList = schoolList;
    }

    public Map<String, Object> getSchoolMap() {
        return SchoolMap;
    }

    public void setSchoolMap(Map<String, Object> schoolMap) {
        SchoolMap = schoolMap;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }
}
