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
public class SchoolFeature extends BaseAction  {


    @RequestMapping("feature")
    public String schoolFeature(String page, String typeFeature, String schoolUrl) {
        int pageSize = 7;
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
        long featureTotal = 0;
        if (typeFeature.equals("1") || typeFeature.equals("2") || typeFeature.equals("3") || typeFeature.equals("4") || typeFeature.equals("5")) {

            try {
                List<Map<String, Object>> featureList = getServiceManager().getSchoolFeatureService().getFeatureList(schoolUrl, typeFeature, pageMap, getRequest());
                featureTotal = getServiceManager().getSchoolFeatureService().getFeatureTATOL(schoolUrl, typeFeature);
                getRequest().setAttribute("featureList", featureList);
                getRequest().setAttribute("featureTotal", featureTotal);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.error(e.getMessage());
            }
            saveCount(schoolUrl);
            String pageCode = PageUtilForPeople.genPagination(getServletContext().getAttribute("basePath").toString(), featureTotal, currPage, pageSize, schoolUrl, "feature", typeFeature);
            getRequest().setAttribute("pageCode", pageCode);
            getRequest().setAttribute("page", page);
            getRequest().setAttribute("typeFeature", typeFeature);
            return "/school_new/common/feature_item";
//			return SUCCESS;
        }
        return null;
    }

    @RequestMapping("feature_info")
    public String getFeatureInfo(String typeFeature, String schoolUrl, String scId) {
        if (typeFeature.equals("1") || typeFeature.equals("2") || typeFeature.equals("3") || typeFeature.equals("4") || typeFeature.equals("5")) {
            Map<String, Object> featureMap = null;
            try {
                featureMap = getServiceManager().getSchoolFeatureService().getFeatureMap(schoolUrl, typeFeature, scId, getRequest());
            } catch (Exception e) {
                // TODO: handle exception
                log.error(e.getMessage());
            }
            if (featureMap != null) {
                getRequest().setAttribute("featureMap", featureMap);
                return "/school_new/common/feature_detail";
//                return SUCCESS;
            } else {
                return null;
            }
        }
        return null;
    }


}
