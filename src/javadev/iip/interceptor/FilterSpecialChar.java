package javadev.iip.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/*
 * 字符过滤器，过滤有特殊含义的字符如<script>等
 */
public class FilterSpecialChar implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
	public String intercept(ActionInvocation arg0) throws Exception {
		Map<String,String[]> params = ServletActionContext.getRequest().getParameterMap();
		
		Iterator<Entry<String,String[]>> iterator = params.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String,String[]> entry = iterator.next();
			for(int i = 0;i<entry.getValue().length;i++){
				entry.getValue()[i] = filterSpecialChar(entry.getValue()[i]);
			}
			entry.setValue(entry.getValue());
		}
		
		return arg0.invoke();
	}
	
	private String filterSpecialChar(String value){
		String newValue = "";
		for(int i=0;i<value.length();i++){
			switch(value.charAt(i)){
			case '<':
				newValue += "&lt;";
				break;
			case '>':
				newValue += "&gt;";
				break;
			case '&':
				newValue += "&amp;";
				break;
			case '"':
				newValue += "&quot;";
				break;
			default:
				newValue += value.charAt(i);
				break; 
			}
		}
		return newValue;
	}
}
