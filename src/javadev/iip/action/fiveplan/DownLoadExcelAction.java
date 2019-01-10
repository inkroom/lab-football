package javadev.iip.action.fiveplan;


import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljp.bean.ExcelBean;
import com.ljp.excel.AnalysisXML;
import com.ljp.excel.WriteExcel;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;

/**
 * 功能描述：用于下载的excel Action
 * @author:彭波
 * @version：1.0
 * create Date ：2016年9月11日08：53：24
 */
public class DownLoadExcelAction extends BaseAction{
	private String is_achieve_play_ground;//新增运动场是否达标
	private String is_add_euqi_gro;//新增体育器材是否达标
	private String is_achieve_music_room ;//音乐专用教室是否达标
	private String is_achieve_music_equi;//音乐器材设备是否达标
	private String is_achieve_draw_room;//美术专用教室是否达标
	private String is_achieve_draw_equi;//美术器材设备是否达标
	//${_LOGIN_USER_ID_?if_exists}


	private String fp_year;
	Map data = (Map) getSession().get(Constants.LOGIN_USER);
	String a_id= data.get("A_ID").toString();
	String o_id= data.get("O_ID").toString();
	public String intoDownloading(){
		
		return "intoDownloading";
	}

	public String download(){

		try{
			//时间转换
			Date date=new Date();
			SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
			Integer time1=Integer.valueOf(time.format(date));
			String fp_year=time1.toString();
			/**
			 * 获取根路径，并对其截取，实现目标路径
			 */
			String uri=this.getClass().getClassLoader().getResource("/").getPath();		
			String url =uri.substring(0, uri.length()-16);
			AnalysisXML analysisXML = new AnalysisXML(url+"resource/excel.xml");

			ExcelBean excelBean = analysisXML.readXML();
			Map map = new HashMap();
			map=getServMgr().getDownLoadExcelService().getAllData(o_id, fp_year);
			/**
			 * 打印时字符的转换，1为是，2为否，3为以达标
			 */
				String is_achieve_play_ground = map.get("is_achieve_play_ground").toString();
				is_achieve_play_ground=isChange(is_achieve_play_ground);
				String is_add_euqi_gro = map.get("is_add_euqi_gro").toString();
				is_add_euqi_gro=isChange(is_add_euqi_gro);
				
				String is_achieve_music_room = map.get("is_achieve_music_room").toString();
				is_achieve_music_room=isChange(is_achieve_music_room);

				String is_achieve_music_equi = map.get("is_achieve_music_equi").toString();
				is_achieve_music_equi=isChange(is_achieve_music_equi);

				String is_achieve_draw_room = map.get("is_achieve_draw_room").toString();
				is_achieve_draw_room=isChange(is_achieve_draw_room);

				String is_achieve_draw_equi = map.get("is_achieve_draw_equi").toString();
				is_achieve_draw_equi=isChange(is_achieve_draw_equi);
				
				map.put("is_achieve_play_ground", is_achieve_play_ground);map.put("is_add_euqi_gro", is_add_euqi_gro);
				map.put("is_achieve_music_room", is_achieve_music_room);map.put("is_achieve_music_equi", is_achieve_music_equi);
				map.put("is_achieve_draw_room", is_achieve_draw_room);map.put("is_achieve_draw_equi", is_achieve_draw_equi);
			WriteExcel write = new WriteExcel(map, excelBean);
			write.excelBuild();
			write.write();

			File file = new File(excelBean.getTargetFile());
			
			
			HttpServletResponse response = getResponse();			
			response.setContentType("UTF-8");
			response.setHeader("Content-type", "application/octet-stream");
			Map umap = new HashMap();
			umap=getServMgr().getDownLoadExcelService().getUserName(o_id);
			String loginUserId = (String) umap.get("O_NAME");
			log.info(loginUserId+"********************************************");
			String excelName=loginUserId+"五年计划报表.xls";
			String filenamedisplay = URLEncoder.encode(excelName, "UTF-8");
			if("FF".equals(getBrowser(getRequest()))){
			    //针对火狐浏览器处理方式不一样了
				filenamedisplay = new String(excelName.getBytes("UTF-8"),"iso-8859-1");
			}
			response.setHeader("Content-Disposition", "attachment;filename="+filenamedisplay);
			response.setHeader("Content-Length", file.length() + "");

			byte[] by = new byte[1024];
			FileInputStream in = new FileInputStream(file);
			int length = 0;
			while ((length = in.read(by)) > 0) {
				response.getOutputStream().write(by, 0, length);
			}
			
			response.getOutputStream().flush();
			response.getOutputStream().close();	
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "loading";
	}
	
	//判断浏览器
	private String getBrowser(HttpServletRequest request){
	    String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
	    if(UserAgent!=null){
	        if (UserAgent.indexOf("msie") >=0 ) return "IE";
	        if (UserAgent.indexOf("firefox") >= 0) return "FF";
	        if (UserAgent.indexOf("safari") >= 0) return "SF";
	    }
	    return null;
	}
	
	private String isChange(Object object){
		String str = object.toString();
		if(str.equals("1")){
			return "是";
		}else if(str.equals("2")){
			return "否";
		}else if(str.equals("3")){
			return "已达标";
		}else{
			return "null";
		}
	}

	public String getIs_achieve_play_ground() {
		return is_achieve_play_ground;
	}

	public void setIs_achieve_play_ground(String is_achieve_play_ground) {
		this.is_achieve_play_ground = is_achieve_play_ground;
	}

	public String getIs_add_euqi_gro() {
		return is_add_euqi_gro;
	}

	public void setIs_add_euqi_gro(String is_add_euqi_gro) {
		this.is_add_euqi_gro = is_add_euqi_gro;
	}

	public String getIs_achieve_music_room() {
		return is_achieve_music_room;
	}

	public void setIs_achieve_music_room(String is_achieve_music_room) {
		this.is_achieve_music_room = is_achieve_music_room;
	}

	public String getIs_achieve_music_equi() {
		return is_achieve_music_equi;
	}

	public void setIs_achieve_music_equi(String is_achieve_music_equi) {
		this.is_achieve_music_equi = is_achieve_music_equi;
	}

	public String getIs_achieve_draw_room() {
		return is_achieve_draw_room;
	}

	public void setIs_achieve_draw_room(String is_achieve_draw_room) {
		this.is_achieve_draw_room = is_achieve_draw_room;
	}

	public String getIs_achieve_draw_equi() {
		return is_achieve_draw_equi;
	}

	public void setIs_achieve_draw_equi(String is_achieve_draw_equi) {
		this.is_achieve_draw_equi = is_achieve_draw_equi;
	}

}
