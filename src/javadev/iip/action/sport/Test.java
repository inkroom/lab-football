package javadev.iip.action.sport;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.chain.commands.servlet.RequestNoCache;
import javadev.iip.service.ServiceManager;
import javadev.iip.action.BaseAction;

public class Test extends BaseAction {
	
	private ServiceManager serviceManager ;
	
	private String name ;
	
	
	
	
	




	public String execute(){
		name = "adsf";
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		serviceManager.getSportServiceTest().getSportData(map);
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}




	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}
}
