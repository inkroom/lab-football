package com.nsu.controller.site;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nsu.controller.BaseController;
import com.nsu.service.site.SiteLiveService;

/**
 * 现场管理员主页及子页跳转
 * @author 刘俊
 */
@Controller
@RequestMapping("site")
public class SiteManager extends BaseController {

	@Autowired
	SiteLiveService service;
	
	@RequestMapping("home")
	public String home(){
		return SitePages.REDIRECT_SITE_HOME;
	}
	
	@RequestMapping("message")
	public String message(){
		return SitePages.SITE_MESSAGE;
	}


}
