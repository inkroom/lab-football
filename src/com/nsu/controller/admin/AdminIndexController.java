package com.nsu.controller.admin;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.AjaxBean;
import com.nsu.bean.index.ImgBean;
import com.nsu.bean.index.LinkBean;
import com.nsu.bean.index.PolicyBean;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.controller.common.UploadBaseController;
import com.nsu.exception.ExtensionsException;
import com.nsu.exception.LengthException;
import com.nsu.exception.NullFileParamException;
import com.nsu.exception.SizeException;
import com.nsu.service.admin.IAdminIndexService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: AdminIndexController
 * @Package com.nsu.controller.admin
 * @Description:
 * @date 5/11/17
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminIndexController extends UploadBaseController {
    // 日志管理
    protected static final Log log = LogFactory.getLog(AdminIndexController.class);

    // 注入 adminIndexService
    @Resource
    private IAdminIndexService adminIndexService;

    /**
     * 欢迎页面
     *
     * @return
     */
    @RequestMapping(value = "/welcome")
    public String welcome() {
        return "/admin/admin_welcome";
    }

    /**
     * 显示主页轮播的列表
     *
     * @return
     */
    @InterceptorAnno(createToken = true)
    @RequestMapping(value = "/roll_img", method = RequestMethod.GET)
    public ModelAndView adminRollImg() {
        ModelAndView modelAndView = new ModelAndView();
        List<ImgBean> listImg = new ArrayList<ImgBean>();
        try {
            listImg = adminIndexService.getRollImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("/admin/admin_roll_img");
        modelAndView.addObject("listImg", JsonMapper.getInstance().toJson(listImg));
        return modelAndView;
    }

    /**
     * 修改主页轮播图片
     *
     * @param file
     * @param imgId
     * @param request
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @ResponseBody
    @RequestMapping(value = "/roll_update_img", method = RequestMethod.POST)
    public AjaxBean adminUpdateRollImg(@RequestParam(value = "file", required = true) MultipartFile file, @RequestParam(value = "imgId", required = true) String imgId, HttpServletRequest request) {
        AjaxBean ajaxBean = new AjaxBean();
        try {
            String path = fileUpload(file, request, "index", "image");
            try {
                if (adminIndexService.updateRollImg(imgId, path)) {
                    ajaxBean.setMsg("上传成功");
                    ajaxBean.setSuccess(true);
                } else {
                    ajaxBean.setMsg("上传失败，请重试！");
                    ajaxBean.setSuccess(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                ajaxBean.setMsg("上传失败，请重试！");
                ajaxBean.setSuccess(true);
            }

        } catch (ExtensionsException e) {
            e.printStackTrace();
            ajaxBean.setMsg("文件类型错误");
            ajaxBean.setSuccess(false);
        } catch (NullFileParamException e) {
            e.printStackTrace();
            ajaxBean.setMsg("请选择文件");
            ajaxBean.setSuccess(false);
        } catch (SizeException e) {
            e.printStackTrace();
            ajaxBean.setMsg("文件大小不能超过");
            ajaxBean.setSuccess(false);
        } catch (LengthException e) {
            e.printStackTrace();
            ajaxBean.setMsg("图片尺寸不能超过1920*1280");
            ajaxBean.setSuccess(false);
        }

        return ajaxBean;
    }

    /**
     * 编辑器上传文件
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) {
        String s = "";
        try {
            s = request.getContextPath() + "/" + fileUpload(file, request, "index", "image");
        } catch (ExtensionsException e) {
            e.printStackTrace();
            s = "error|" + e.getMessage();
        } catch (LengthException e) {
            e.printStackTrace();
            s = "error|" + e.getMessage();
        } catch (SizeException e) {
            e.printStackTrace();
            s = "error|" + e.getMessage();
        } catch (NullFileParamException e) {
            e.printStackTrace();
            s = "error|" + e.getMessage();
        }
        return s;
    }

    /**
     * 编辑政策的页面
     *
     * @param policyBean
     * @param model
     * @return
     */
    @InterceptorAnno(createToken = true)
    @RequestMapping(value = "/policy_add_view", method = RequestMethod.GET)
    public String AddPolicyView(PolicyBean policyBean, Model model) {

        if (policyBean != null && policyBean.getId() != null) {
            try {
                model.addAttribute("data", adminIndexService.getPolicyById(policyBean));
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }

        return "/admin/admin_policy";
    }

    private static Integer TITLE_LENGTH = 50;

    /**
     * 保存政策
     *
     * @param policyBean
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @ResponseBody
    @RequestMapping(value = "/policy_save", method = RequestMethod.POST)
    public AjaxBean savePolicyView(PolicyBean policyBean) {
        AjaxBean ajaxBean = new AjaxBean();
        if (policyBean.getTitle() == null || policyBean.getTitle().length() > TITLE_LENGTH || policyBean.getTitle().length() < 1) {
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("标题长度为1至" + TITLE_LENGTH + "，请适当减少标题!");
            ajaxBean.setStatus("200");
        } else {
            if (policyBean.getContent() == null || policyBean.getContent().length() > 10000 || policyBean.getTitle().length() < 1) {
                ajaxBean.setSuccess(false);
                ajaxBean.setMsg("内容超过要求，请适当减少内容!");
                ajaxBean.setStatus("200");
            } else {
                return savePolicy(policyBean);
            }
        }

        return ajaxBean;
    }


    private AjaxBean savePolicy(PolicyBean p) {
        AjaxBean ajaxBean = new AjaxBean();
        try {
            if (adminIndexService.savePolicy(p)) {
                ajaxBean.setSuccess(true);
                ajaxBean.setMsg("保存成功!");
                ajaxBean.setStatus("200");
            } else {
                ajaxBean.setSuccess(false);
                ajaxBean.setMsg("添加失败请重试!");
                ajaxBean.setStatus("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("添加失败请重试!");
            ajaxBean.setStatus("200");
        }
        return ajaxBean;
    }

    /**
     * 保存政策
     *
     * @param policyBean
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @ResponseBody
    @RequestMapping(value = "/policy_del", method = RequestMethod.POST)
    public AjaxBean delPolicyView(PolicyBean policyBean) {
        return savePolicy(policyBean);
    }


    /**
     * 显示政策管理
     *
     * @return
     */
    @RequestMapping(value = "/policy_list")
    public String getPolicyAdminView() {
        return "/admin/admin_policy_list";
    }


    // 定义每个分页的大小
    private static Integer PAGE_SIZE = 10;

    /**
     * 获取政策列表
     *
     * @param pageNum
     * @param session
     * @return
     */
    @InterceptorAnno(createToken = true)
    @ResponseBody
    @RequestMapping(value = "/policy_page")
    public AjaxBean getPolicyPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, HttpSession session) {
        //PageInfo<PolicyBean> policyBeanPageInfo = a
        PageInfo<PolicyBean> pageInfo = null;
        try {
            pageInfo = adminIndexService.getPolicyBean(pageNum, PAGE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AjaxBean ajaxBean = new AjaxBean(true, session);
        ajaxBean.setBody("page", pageInfo);
        return ajaxBean;
    }


    /**
     * 编辑链接的页面
     *
     * @param linkBean
     * @param model
     * @return
     */
    @InterceptorAnno(createToken = true)
    @RequestMapping(value = "/link_edit")
    public String editLink(LinkBean linkBean, Model model) {
        try {
            model.addAttribute("link", JsonMapper.toJsonString(adminIndexService.getLinkById(linkBean)));
        } catch (Exception e) {
            e.printStackTrace();
            linkBean = new LinkBean();
            model.addAttribute("link", JsonMapper.toJsonString(linkBean));
        }
        return "/admin/admin_link";
    }


    private static final Integer NAME_LENGTH = 16;
    private static final Integer ADDE_LENGTH = 50;

    /**
     * 链接保存
     *
     * @param linkBean
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @ResponseBody
    @RequestMapping(value = "/link_save")
    public AjaxBean linkSave(LinkBean linkBean) {
        AjaxBean ajaxBean = new AjaxBean();
        if (linkBean.getLinkName() == null || linkBean.getLinkName().length() > NAME_LENGTH || linkBean.getLinkName().length() < 1) {
            ajaxBean.setStatus("200");
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("链接名长度位1到" + NAME_LENGTH + "位，请适当减少");
        } else {
            if (linkBean.getLinkAddr() == null || linkBean.getLinkAddr().length() > ADDE_LENGTH || linkBean.getLinkAddr().length() < 1) {
                ajaxBean.setStatus("200");
                ajaxBean.setSuccess(false);
                ajaxBean.setMsg("链接长度超过1到" + ADDE_LENGTH + "位，请适当减少");
            } else {
                if (linkBean.getLinkType() == null || linkBean.getLinkType().length() != 1) {
                    ajaxBean.setStatus("200");
                    ajaxBean.setSuccess(false);
                    ajaxBean.setMsg("操作异常！");
                } else if (linkBean.getLinkType().equals("1") || linkBean.getLinkType().equals("2") || linkBean.getLinkType().equals("3") || linkBean.getLinkType().equals("4")) {
                    try {
                        if (adminIndexService.saveLink(linkBean)) {
                            ajaxBean.setStatus("200");
                            ajaxBean.setSuccess(true);
                            ajaxBean.setMsg("保存成功");
                        } else {
                            ajaxBean.setStatus("200");
                            ajaxBean.setSuccess(false);
                            ajaxBean.setMsg("保存失败，请重试！");
                        }

                        //ajaxBean = ;
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                        ajaxBean.setStatus("200");
                        ajaxBean.setSuccess(false);
                        ajaxBean.setMsg("保存失败，请重试！");
                    }
                } else {
                    ajaxBean.setStatus("200");
                    ajaxBean.setSuccess(false);
                    ajaxBean.setMsg("操作异常！");
                }

            }
        }

        return ajaxBean;
    }


    /**
     * 显示链接列表的VIEW
     *
     * @return
     */
    @RequestMapping("/link_list")
    public String linkPageView() {
        return "/admin/admin_link_list";
    }

    /**
     * 获取链接列表的JSON
     *
     * @param pageNum
     * @param type
     * @param session
     * @return
     */
    @InterceptorAnno(createToken = true)
    @ResponseBody
    @RequestMapping("/link_page")
    public AjaxBean linkPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, String type, HttpSession session) {
        PageInfo<LinkBean> pageInfo = null;
        try {
            pageInfo = adminIndexService.getLinkBean(pageNum, PAGE_SIZE, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AjaxBean ajaxBean = new AjaxBean(true, session);
        ajaxBean.setBody("page", pageInfo);
        return ajaxBean;
    }


    /**
     * 链接置顶和链接删除
     *
     * @param id
     * @param type
     * @param linkType
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @ResponseBody
    @RequestMapping(value = "/link_update_other")
    public AjaxBean linkUpdateOther(String id, int type, String linkType) {
        AjaxBean ajaxBean = new AjaxBean();
        try {

            if (type == 1) {
                if (adminIndexService.deleteLinkById(id, linkType)) {
                    ajaxBean.setStatus("200");
                    ajaxBean.setSuccess(true);
                    ajaxBean.setMsg("保存成功");
                } else {
                    ajaxBean.setStatus("200");
                    ajaxBean.setSuccess(false);
                    ajaxBean.setMsg("保存失败，请重试！");
                }
            } else if (type == 2) {
                if (adminIndexService.toTopLinkById(id, linkType)) {
                    ajaxBean.setStatus("200");
                    ajaxBean.setSuccess(true);
                    ajaxBean.setMsg("保存成功");
                } else {
                    ajaxBean.setStatus("200");
                    ajaxBean.setSuccess(false);
                    ajaxBean.setMsg("保存失败，请重试！");
                }
            } else {
                ajaxBean.setStatus("200");
                ajaxBean.setSuccess(false);
                ajaxBean.setMsg("操作异常");
            }

            //ajaxBean = ;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            ajaxBean.setStatus("200");
            ajaxBean.setSuccess(true);
            ajaxBean.setMsg("保存失败，请重试！");
        }
        return ajaxBean;
    }


}
