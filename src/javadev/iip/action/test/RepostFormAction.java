package javadev.iip.action.test;

import javadev.iip.action.BaseAction;

public class RepostFormAction extends BaseAction{
	
	

	public String execute()
	{
		getSession().remove("error");
		getSession().put("error", "错误");
		
		return "error";
	}
	

	
	
}
