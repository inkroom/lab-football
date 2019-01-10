package cn.inkroom.web.money.gate.controllers.common;

import cn.inkroom.web.money.gate.beans.cooperator.CooperatorBean;
import cn.inkroom.web.money.gate.services.cooperator.CooperatorService;
import cn.inkroom.web.money.gate.services.prodandtech.ProductService;
import cn.inkroom.web.money.gate.services.prodandtech.TechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HeadController {

    @Autowired
    private ProductService proser;
    @Autowired
    private TechnicalService teser;

    @Autowired
    private CooperatorService coser;

    @RequestMapping("head")
    public ModelAndView Head(ModelAndView model) {
        ArrayList<TechnicalSetBean> settech = teser.getSettech();
        ArrayList<ProductSetBean> setprod = proser.getsetpro();
        int te_set = settech.get(0).getTs_id();
        int pr_set = setprod.get(0).getPs_id();
        List<CooperatorBean> cooperatorlist = coser.getCooperatorList();
        model.addObject("settech", settech);
        model.addObject("setprod", setprod);
        model.addObject("colist", cooperatorlist);
        model.addObject("te_set", te_set);
        model.addObject("pr_set", pr_set);
        model.setViewName("common/head");
        return model;

    }
}
