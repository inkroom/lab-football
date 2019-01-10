package com.nsu.controller.student.datadownload;

import com.nsu.bean.common.AjaxBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.service.student.DataDownload.DataDownloadService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;

/**
 * Data download controller类的描述
 *
 * @author 闫建宏
 * @Description:返回可下载资料的信息
 * @date 2017 /8/2
 */


@Controller
@RequestMapping("student/download/")
public class DataDownloadController implements Anonymous {

    @Resource
    DataDownloadService dataDownloadService;
    @InterceptorAnno(isAjax = true)
    @RequestMapping("view")
    @ResponseBody
    public AjaxBean dataDownloadView(HttpServletRequest request){
        //总数据数
        int totalRecords = dataDownloadService.getDataViewNum();
        //每页要显示多少条数据
        int limitPage = 10;
        //总页数
        int pageSize;
        if(totalRecords%limitPage==0){
            pageSize = totalRecords/limitPage;
        }
        else{
            pageSize = totalRecords/limitPage+1;
        }
        //返回总页数
        //当前页的初始值为1
        int currentPage = 1;
        if(request.getParameter("currentPage")!=null)
            currentPage = Integer.valueOf(request.getParameter("currentPage"));
        //返回该页数的数据
        int startRow = (currentPage-1) * limitPage;  //传给数据库的参数，每页开始是第几条记录
        AjaxBean ajaxBean = new AjaxBean();
        try{
            ajaxBean.setSuccess(true);
            ajaxBean.setStatus("200");
            ajaxBean.put("pageSize",pageSize);
            ajaxBean.put("retData",dataDownloadService.getDataViewByPage(startRow,limitPage));
            return ajaxBean;
        }
        catch (Exception e){
            ajaxBean.setSuccess(false);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("查看资料页面失败");
            return ajaxBean;
        }
    }
    //搜索控制器
    @InterceptorAnno(isAjax = true)
    @RequestMapping("/search")
    @ResponseBody
    public AjaxBean dataDownloadSearch(){
        AjaxBean ajaxBean = new AjaxBean();
        try{
            ajaxBean.setSuccess(true);
            ajaxBean.setStatus("200");
            ajaxBean.put("result",dataDownloadService.SearchData("785702978"));
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT")); //默认是GMT+0800 会自动加上八小时 从数据库拿到本地时间需要再进行时区转换
            ajaxBean.put("date",sdf.format(dataDownloadService.SearchData("785702978").get(0).uploadTime));
            return ajaxBean;
        }
        catch (Exception e){
            ajaxBean.setSuccess(false);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("搜索失败");
            return ajaxBean;
        }
    }
    @InterceptorAnno(isAjax = false)
    @RequestMapping("/download")
    public ResponseEntity<byte[]> dataDownload(Long id) throws IOException {
        String path = dataDownloadService.getPathById(id);
        System.out.println(id);
        File file = new File(path);
        System.out.println(path);
        String fileName = new String(file.getName().getBytes("gb2312"), "iso-8859-1");
        System.out.println(file.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }

    @InterceptorAnno(isAjax = false)
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
