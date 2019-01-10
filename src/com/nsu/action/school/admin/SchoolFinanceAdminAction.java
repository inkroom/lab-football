package com.nsu.action.school.admin;

import java.util.HashMap;
import java.util.Map;


import com.nsu.action.BaseAction;
import com.nsu.util.core.NumberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school_admin/")
public class SchoolFinanceAdminAction extends BaseAction {
    Map<String, Object> found;

    private String errorInfo;
    /**
     * 收入
     */
    private String provinceFund;
    private String cityFund;
    private String countyFund;
    private String selfFund;
    private String aveSelfFund;
    private String aidFund;
    private String inFoundTitle;


    private String content8;
    private String content9;
    private String content10;
    private String content11;
    private String content12;
    private String content13;
    private String content14;
    private String content15;
    private String content16;
    private String content17;
    private String content18;
    private String content19;
    private String content20;
    private String content21;
    private String content22;
    private String content23;
    private String outFoundTitle;

    @RequestMapping("update_in_found_view")
    public String inFinanceView() {
        try {
            errorInfo = getSession().getAttribute("inFoundError").toString();
            getSession().removeAttribute("inFoundError");
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            errorInfo = getSession().getAttribute("inFoundSuccess").toString();
            getSession().removeAttribute("inFoundSuccess");
        } catch (Exception e) {
            // TODO: handle exception
        }

        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        found = getServiceManager().getSchoolFoundAdminService().getFound(schoolUrl);
        return "/school_admin/admin_finance/school_admin_in_finance";
    }

    @RequestMapping("update_in_found")
    public String inFinanceSave() {
        try {
            if (inFoundTitle.length() > 20 || inFoundTitle.length() == 0) {
                getSession().setAttribute("inFoundError", "请填写规范的标题！");
                return "redirect:/school_admin/update_in_found_view.action";
            }

            if (!NumberUtil.checkNumber(provinceFund, 10000, 0, 3)) {
                getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
                return "redirect:/school_admin/update_in_found_view.action";
            }


            if (!NumberUtil.checkNumber(cityFund, 10000, 0, 3)) {
                getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
                return "redirect:/school_admin/update_in_found_view.action";
            }


            if (!NumberUtil.checkNumber(countyFund, 10000, 0, 3)) {
                getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
                return "redirect:/school_admin/update_in_found_view.action";
            }

            if (!NumberUtil.checkNumber(selfFund, 10000, 0, 3)) {
                getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
                return "redirect:/school_admin/update_in_found_view.action";
            }

            if (!NumberUtil.checkNumber(aveSelfFund, 10000, 0, 3)) {
                getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
                return "redirect:/school_admin/update_in_found_view.action";
            }

            if (!NumberUtil.checkNumber(aidFund, 10000, 0, 3)) {
                getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
                return "redirect:/school_admin/update_in_found_view.action";
            }

            String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("titileIn", inFoundTitle);
            map.put("content1", provinceFund);
            map.put("content2", cityFund);
            map.put("content3", countyFund);
            map.put("content4", selfFund);
            map.put("content5", aveSelfFund);
            map.put("content6", aidFund);
            map.put("content7", Double.parseDouble(aidFund) + Double.parseDouble(provinceFund) + Double.parseDouble(cityFund) +
                    Double.parseDouble(countyFund) + Double.parseDouble(selfFund));
            map.put("schoolUrl", schoolUrl);

            if (getServiceManager().getSchoolFoundAdminService().updateInFound(map)) {
                getSession().setAttribute("inFoundSuccess", "保存成功!");
                return "redirect:/school_admin/update_in_found_view.action";
            } else {
                getSession().setAttribute("inFoundError", "保存失败，请稍后重试!");
                return "redirect:/school_admin/update_in_found_view.action";
            }

        } catch (Exception e) {
            // TODO: handle exception
            getSession().setAttribute("inFoundError", "保存失败，请稍后重试!");
            e.printStackTrace();
            log.error(e.getMessage());
            return "redirect:/school_admin/update_in_found_view.action";
        }
    }

    @RequestMapping("update_out_found_view")
    public String outFinanceView() {
        try {
            errorInfo = getSession().getAttribute("inFoundError").toString();
            getSession().removeAttribute("inFoundError");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        try {
            errorInfo = getSession().getAttribute("inFoundSuccess").toString();
            getSession().removeAttribute("inFoundSuccess");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        found = getServiceManager().getSchoolFoundAdminService().getFound(schoolUrl);
        return "/school_admin/admin_finance/school_admin_out_finance";
    }

    @RequestMapping("update_out_found")
    public String outFinanceSave() {

        if (outFoundTitle.length() > 20 || outFoundTitle.length() == 0) {
            getSession().setAttribute("inFoundError", "请填写规范的标题！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content8, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }
        if (!NumberUtil.checkNumber(content9, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content10, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content11, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content12, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content13, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content14, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }


        if (!NumberUtil.checkNumber(content15, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }


        if (!NumberUtil.checkNumber(content16, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content17, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content18, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content19, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }
        if (!NumberUtil.checkNumber(content20, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content21, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content22, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        if (!NumberUtil.checkNumber(content23, 10000, 0, 3)) {
            getSession().setAttribute("inFoundError", "请填写0-10000的数值！");
            return "redirect:/school_admin/update_out_found_view.action";
        }

        String schoolUrl = getLoginUser().get("SCHOOL_URL").toString();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("titileOut", outFoundTitle);
        map.put("content8", content8);
        map.put("content9", content9);
        map.put("content10", content10);
        map.put("content11", content11);
        map.put("content12", content12);
        map.put("content13", content13);
        map.put("content14", content14);
        map.put("content15", content15);
        map.put("content16", content16);
        map.put("content17", content17);
        map.put("content18", content18);
        map.put("content19", content19);
        map.put("content20", content20);
        map.put("content21", content21);
        map.put("content22", content22);
        map.put("content23", content23);
        map.put("content24", Double.parseDouble(content8) + Double.parseDouble(content9) + Double.parseDouble(content10) +
                Double.parseDouble(content11) + Double.parseDouble(content12) + Double.parseDouble(content13) + Double.parseDouble(content14) + Double.parseDouble(content15) + Double.parseDouble(content16)
                + Double.parseDouble(content17) + Double.parseDouble(content18) + Double.parseDouble(content19) + Double.parseDouble(content20) + Double.parseDouble(content21) + Double.parseDouble(content22)
                + Double.parseDouble(content23));
        map.put("schoolUrl", schoolUrl);
        if (getServiceManager().getSchoolFoundAdminService().updateOutFound(map)) {
            getSession().setAttribute("inFoundSuccess", "保存成功!");
            return "redirect:/school_admin/update_out_found_view.action";
        } else {
            getSession().setAttribute("inFoundError", "保存失败，请稍后重试!");
            return "redirect:/school_admin/update_out_found_view.action";
        }


    }


    public String getProvinceFund() {
        return provinceFund;
    }


    public void setProvinceFund(String provinceFund) {
        this.provinceFund = provinceFund;
    }


    public String getCityFund() {
        return cityFund;
    }


    public void setCityFund(String cityFund) {
        this.cityFund = cityFund;
    }


    public String getCountyFund() {
        return countyFund;
    }


    public void setCountyFund(String countyFund) {
        this.countyFund = countyFund;
    }


    public String getSelfFund() {
        return selfFund;
    }


    public void setSelfFund(String selfFund) {
        this.selfFund = selfFund;
    }


    public String getAveSelfFund() {
        return aveSelfFund;
    }


    public void setAveSelfFund(String aveSelfFund) {
        this.aveSelfFund = aveSelfFund;
    }


    public String getAidFund() {
        return aidFund;
    }


    public void setAidFund(String aidFund) {
        this.aidFund = aidFund;
    }


    public Map<String, Object> getFound() {
        return found;
    }


    public void setFound(Map<String, Object> found) {
        this.found = found;
    }


    public String getInFoundTitle() {
        return inFoundTitle;
    }


    public void setInFoundTitle(String inFoundTitle) {
        this.inFoundTitle = inFoundTitle;
    }


    public String getContent8() {
        return content8;
    }


    public void setContent8(String content8) {
        this.content8 = content8;
    }


    public String getContent9() {
        return content9;
    }


    public void setContent9(String content9) {
        this.content9 = content9;
    }


    public String getContent10() {
        return content10;
    }


    public void setContent10(String content10) {
        this.content10 = content10;
    }


    public String getContent11() {
        return content11;
    }


    public void setContent11(String content11) {
        this.content11 = content11;
    }


    public String getContent12() {
        return content12;
    }


    public void setContent12(String content12) {
        this.content12 = content12;
    }


    public String getContent13() {
        return content13;
    }


    public void setContent13(String content13) {
        this.content13 = content13;
    }


    public String getContent14() {
        return content14;
    }


    public void setContent14(String content14) {
        this.content14 = content14;
    }


    public String getContent15() {
        return content15;
    }


    public void setContent15(String content15) {
        this.content15 = content15;
    }


    public String getContent16() {
        return content16;
    }


    public void setContent16(String content16) {
        this.content16 = content16;
    }


    public String getContent17() {
        return content17;
    }


    public void setContent17(String content17) {
        this.content17 = content17;
    }


    public String getContent18() {
        return content18;
    }


    public void setContent18(String content18) {
        this.content18 = content18;
    }


    public String getContent19() {
        return content19;
    }


    public void setContent19(String content19) {
        this.content19 = content19;
    }


    public String getContent20() {
        return content20;
    }


    public void setContent20(String content20) {
        this.content20 = content20;
    }


    public String getContent21() {
        return content21;
    }


    public void setContent21(String content21) {
        this.content21 = content21;
    }


    public String getContent22() {
        return content22;
    }


    public void setContent22(String content22) {
        this.content22 = content22;
    }


    public String getContent23() {
        return content23;
    }


    public void setContent23(String content23) {
        this.content23 = content23;
    }


    public String getOutFoundTitle() {
        return outFoundTitle;
    }


    public void setOutFoundTitle(String outFoundTitle) {
        this.outFoundTitle = outFoundTitle;
    }


    public String getErrorInfo() {
        return errorInfo;
    }


    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }


}
