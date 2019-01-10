package com.nsu.controller.index;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.AjaxBean;
import com.nsu.bean.index.ImgBean;
import com.nsu.bean.index.PolicyBean;
import com.nsu.common.Anonymous;
import com.nsu.controller.BaseController;
import com.nsu.service.index.IIndexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IndexController
 * @Package com.nsu.controller.index
 * @Description: 主页
 * @date 4/24/17
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController implements Anonymous {

    //注入indexService
    @Resource
    private IIndexService indexService;

    /**
     * 主页的VIEW
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/index/index");
        try {
            List<ImgBean> img = indexService.getImgList();

            modelAndView.addObject("imgList",img);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return modelAndView;
    }

    /**
     * 获取轮播图片 和政策管理界面
     * @return
     */
    @ResponseBody
    @PostMapping("/index_base")
    public AjaxBean getIndexData(){
        AjaxBean ajaxBean = new AjaxBean();
        String count = null;
        String img = null;
        String policy = null;
        try {
            count = indexService.getCountAll();
            ajaxBean.put("count",count);
            ajaxBean.put("img",img);
            policy = indexService.getPolicyLink();
            ajaxBean.put("policy",policy);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        ajaxBean.setSuccess(true);
        ajaxBean.setStatus("200");
        return ajaxBean;
    }


    /**
     * 获取轮播图片 和政策管理界面
     * @return
     */
    @ResponseBody
    @PostMapping("/index_link")
    public AjaxBean getLinkData(String type){
        AjaxBean ajaxBean = new AjaxBean();
        String link = null;
        try {
            if (type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4")){
                link = indexService.getLink(type);
                ajaxBean.setBody("link",link);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        ajaxBean.setSuccess(true);
        ajaxBean.setStatus("200");
        return ajaxBean;
    }

    /**
     * 政策list VIEW
     * @return
     */
    @RequestMapping("/index_second")
    public String indexSecond(){
        return "/index/index_second";
    }

    /**
     * 具体 政策 view
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{id}/index_third")
    public String indexThird(@PathVariable String id, Model model){
        try {
           PolicyBean policyBean = indexService.getPolicyById(id);
           model.addAttribute("policy",policyBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index/index_third";
    }

    //加载的条数
    private final static Integer PAGE_SIZE = 20;

    /**
     * 获取政策页面
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/policy")
    public PageInfo<PolicyBean> getPolicyBean(Integer pageNum){
        AjaxBean ajaxBean = new AjaxBean();
        try {
            return indexService.getPolicyList(pageNum,PAGE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


//select * from (select count(P_ID)  as player from PLAYER )  player,(SELECT count(COACH_ID) as coach FROM COACH) coach,(SELECT count(COM_ID) as com FROM COMPETITION_NOTICE) com


}
