package com.nsu.controller.organization.login;

import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.service.organization.IOrgPerfectInfoService;
import com.nsu.service.organization.OrgLoginService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName:  OrgLoginController
 * @Description: TODO<机构登录>
 * <  判断输入账号、密码、验证码是否正确， 如果正确，跳转至成功页面，如果失败返回登录页面 >
 * @date    17/4/11    下午3:54
 * @author 辜鹏
 * @version:      V1.0
 */

@Controller
@RequestMapping(value = "/org")
public class OrgLoginController extends BaseController {

    /*提示的前台错误信息*/
    private String error;

    /*数据库查询后 Account 表中 单个user 所有 信息*/
    Map<String, Object> userMap = null;

    @Resource
    private OrgLoginService service;
    @Resource
	private IOrgPerfectInfoService iOrgPerfectInfoService;


    /*
     * @Description: TODO(重定向转发到  跳转到登陆页面)
     * @methodName
     * @param     [session]
     * @return    java.lang.String 返回类型
     * @throws
     */
    @RequestMapping("login_view")
    public  String login(HttpSession session){

        if (session.getAttribute(Constants.SALT_IN_SESSION) == null) {
            setSaltForSession(session);
        }
        return "/organization/login/org_login";
    }



    /*
     * @Description: TODO(得到前台数据，进行登录)
     * @methodName
     * @param
     * @return     返回类型
     * @throws
     */

    @RequestMapping(value = "/login")
    public String login_check(HttpServletRequest request, String code, HttpSession session, String username, String p ,
                              RedirectAttributes redirectAttributes, ModelMap model){

        System.out.println(code);



        if ( check_one(username,p,code,session,redirectAttributes,model)){
            if (check_two(username,code,p,session,redirectAttributes,model)){

                /*去除从数据库查找的密码 和加盐信息*/
                userMap.remove("A_SALT");
                userMap.remove("A_PASSWORD");

                setSessionParameter(Constants.LOGIN_USER,userMap , session);
                request.getSession().setAttribute("org_usermap",userMap);
                try {
                    session.setAttribute("SMSPhone",InfoProtUtil.xorInfo(userMap.get("A_PHONE").toString()));
                    session.setAttribute("SMSEmail",userMap.get("A_EMAIL").toString());
                }catch (Exception e){
                    log.info("此异常不用管，第一次为空");
                }



                return  "redirect:/org/org_index.html";
            }
        }
        return "redirect:/org/login_view.html";
    }

     @InterceptorAnno(createToken = true)
     @RequestMapping("org_index")
     public  String orgIndex( HttpSession session){
    	 try {

      		//机构id
              Map<Object,Object> map1 = new HashMap<>();
              map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
              Map<Object, String> map_name = new HashMap<>();

              long org_id = (long)map1.get("ORG_ID");//机构id
              log.info( map1.get("ORG_ID"));
  			 map_name = iOrgPerfectInfoService.getOrganizationMessage(org_id);
  			 if(session.getAttribute("map_name") != null){
  				session.removeAttribute("map_name");
  			}
  			session.setAttribute("map_name", map_name);
  		} catch (Exception e) {
             log.error(e.getMessage());
  		}
        return "/organization/institution_index";
     }



    /*
     * @Description: TODO(验证用户名、密码、验证码是否为空)
     * @methodName
     * @param
     * @return     返回类型
     * @throws
     */
    public boolean check_one(String username, String p,String code, HttpSession session, RedirectAttributes redirectAttributes,ModelMap model) {

        boolean userVer = VerifyUtil.isNotEmpty(username);
        boolean pwVer = VerifyUtil.isNotEmpty(p);
      //  boolean codeVer = VerifyUtil.isNotEmpty(code);

         /*是否为空*/
        if (!userVer){
            error = "用户名不能为空";
            redirectAttributes.addFlashAttribute("error", error);
            return false;
        }
        if (!pwVer){
            error = "密码不能为空";
            redirectAttributes.addFlashAttribute("error", error);
            return false;
        }


  /*      if (!codeVer){
            error = "验证码不能为空";
            redirectAttributes.addFlashAttribute("error", error);
            return false;
        }*/

        return  true;

    }



    /*
     * @Description: TODO()
     * @methodName  check_two
     * @param     [username, code, p, session, redirectAttributes, model]
     * @return    boolean 返回类型
     * @throws
     */
    public boolean check_two(String username, String code,String p, HttpSession session,
                                RedirectAttributes redirectAttributes,ModelMap model) {




        try {
             /*验证码 输入错误时*/



             if (!code.equals(session.getAttribute(Constants.RANDOM_CODE).toString())) {
                error = "验证码输入有误";
                redirectAttributes.addFlashAttribute("error", error);
                return false;

                }



            /*接收页面输入参数的map*/
            Map<String,Object> map=new HashMap<>();
            map.put("username", username);


            userMap = service.findUser(map);




            if (userMap == null) {
                error = "用户名不存在";
                redirectAttributes.addFlashAttribute("error", error);
                return false;
            }

            if (!InfoProtUtil.comparePass(userMap.get("A_PASSWORD").toString(),
                    session.getAttribute((Constants.SALT_IN_SESSION)).toString(), p)){

                error = "用户名或密码错误";
                redirectAttributes.addFlashAttribute("error", error);
                return false;
            }

            service.updateTime(map);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return true;

        }



    /**
     * @Description: TODO(用户注销)
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/logOut" )
    public String logOut2(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("error","用户已成功退出");
        return "redirect:/org/login_view.html";

    }

    /**
     * @Description: TODO(Session 失效了跳转的页面)
     * @return
     */
    @RequestMapping(value = "to_login")
    public  String toLogin(){
        return "/organization/tologin";
    }


}
