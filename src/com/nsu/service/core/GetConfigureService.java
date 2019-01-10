package com.nsu.service.core;

import java.util.Map;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class GetConfigureService extends BaseService {
	public Map<String, Object> getPictureConfigureLinux() throws Exception {
		return jt.queryForMap("select * from configure where item_name = 'pic_root_linux'");
	}
	
	public Map<String, Object> getPictureConfigureMac() throws Exception{
		return jt.queryForMap("select * from configure where item_name = 'pic_root_mac'");
	}
	
	public Map<String, Object> getPictureConfigureWin() throws Exception {
			return jt.queryForMap("select * from configure where item_name = 'pic_root_win'");
	}
	
	public String getIpAddrConfig()throws Exception{
		return jt.queryForObject("select _value from configure where item_name = 'ip_config'", String.class);
	}
	
}
