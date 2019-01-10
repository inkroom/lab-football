package cn.edu.nsu.lib.controllers.admin;

/**
 * Created by 王振科 on 2017/9/26.
 */

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.admin.db.DbPrize;
import cn.edu.nsu.lib.bean.admin.db.DbPrize;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.impl.Admin_Manager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * 该类实现显示实验室获奖信息
 * 1.显示成员获奖信息
 * 2.如果审核情况为false，需要管理员审核
 * 3.如果审核情况为true，审核情况显示已通过
 * 4.审核通过的可以删除，通过该奖项的id
 */
@Controller
@RequestMapping("/PrizeAdministrator")
public class Administrator_Prize_Controller extends BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Admin_Manager service;
    @Autowired
    private HttpSession session;

    /**
     * toprize() 这个方法的描述
     *
     * @ClassName: toprize
     * @Description: 跳转到奖项信息页面
     * @Author: 王振科
     * @Date: 10:17
     * @URL: /PrizeAdministrator/toprize
     */
    @RequestMapping("/toprize")
    @Authority(role = Authority.Role.MANAGER)
    public String toprize() throws Exception {

        //获得实验室id
        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        //通过实验室id获得获奖人的名单
        ArrayList<DbPrize> prizes = service.getAdmini_prize_service().
                toprize(lab_id);
        request.setAttribute("prizes", prizes);

        for (DbPrize prize : prizes) {
            log.info("***************prizeinfo******************");
            log.info(prize.toString());
            log.info("***************prizeinfo******************");
        }

        //标题显示当前实验室的名字
        String lab_name = service.getAdmini_service().
                getLabname_service(lab_id);
        //request存入实验室对象给前端
        request.setAttribute("lab_name", lab_name);

        return "/administrator/toprize";
    }

    /**
     * Passcheck() 这个方法的描述
     *
     * @param prize_id
     * @ClassName: Passcheck
     * @Description: 通过id把审核情况设置为true
     * @Author: 王振科
     * @Date: 11:21
     * @URL: /PrizeAdministrator/Passcheck
     */
    @RequestMapping("/Passcheck")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean Passcheck(int prize_id) throws Exception {

        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        boolean is_checked = true;
        //通过奖项id和实验室id 进行修改通过操作
        Result status = service.getAdmini_prize_service().
                Passcheck_Service(prize_id, lab_id, is_checked);
        if (status == Result.FAIL) {
            return new AjaxBean(Result.FAIL);
        } else {
            return new AjaxBean(Result.SUCCESS);
        }
    }

    /**
     * Nopass() 这个方法的描述
     *
     * @param prize_id
     * @ClassName: Nopass
     * @Description: 通过id直接删除这条获奖信息
     * @Author: 王振科
     * @Date: 11:21
     * @URL: /PrizeAdministrator/Nopass
     */
    @RequestMapping("/Nopass")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean Nopass(int prize_id) throws Exception {

        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        //不通过奖项直接删除这条数据
        Result status = service.getAdmini_prize_service()
                .Nopass_Service(prize_id, lab_id);
        if (status == Result.FAIL) {
            return new AjaxBean(Result.FAIL);
        } else {
            return new AjaxBean(Result.SUCCESS);
        }
    }

    /**
     * Deletewinner() 这个方法的描述
     *
     * @param prize_id
     * @ClassName: Deletewinner
     * @Description: 通过id直接删除这条获奖信息
     * @Author: 王振科
     * @Date: 11:21
     * @URL: /PrizeAdministrator/Deletewinner
     */
    @RequestMapping("/Deletewinner")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean Deletewinner(int prize_id) throws Exception {

        log.info("删除的公告Id为：" + prize_id);
        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        Result status = service.getAdmini_prize_service()
                .Deletewinner_Service(prize_id, lab_id);
        if (status == Result.FAIL) {
            return new AjaxBean(Result.FAIL);
        } else {
            return new AjaxBean(Result.SUCCESS);
        }
    }
}
