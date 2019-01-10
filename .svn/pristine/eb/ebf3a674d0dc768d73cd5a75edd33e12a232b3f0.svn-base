package com.nsu.controller.student.exam;

import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.entity.UploadFiles;
import com.nsu.service.student.exam.IExamResService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;

/**
 * 考试所需的图片、音频、视频资源控制
 *
 * @author XueLong
 * @version V1.0
 * @ClassName: ExamResController
 * @Package com.nsu.controller.student.exam
 * @Description: 考试资源控制器
 * @date 2017/8/8 16:22
 */
@Controller
@RequestMapping("/student/exam/res")
public class ExamResController extends BaseController implements Anonymous {

    //考试资源服务对象
    @Resource
    IExamResService examResService;

    //资源类型Map
    public static HashMap<String,Integer> RES_TYPE_MAP;
    static {
        RES_TYPE_MAP = new HashMap<>();
        RES_TYPE_MAP.put("image",8);
        RES_TYPE_MAP.put("audio",6);
        RES_TYPE_MAP.put("video",7);
    }

    /**
     * 通过Restful的Url获取资源
     *
     * @param type 资源类型
     * @param id   资源id
     * @Title: getResourceByUrl
     * @Description: 通过Restful的Url获取资源
     * @author XueLong
     * @date 2017 -08-08 17:24:12
     */
    @InterceptorAnno(isRestful = true)
    @RequestMapping("/{type}/{id}")
    public ResponseEntity<byte[]> getResourceByUrl(@PathVariable String type, @PathVariable long id){
        //判断资源请求格式是否错误
        if (RES_TYPE_MAP.get(type) == null){
            return null;
        }
        //获取文件资源实体对象
        UploadFiles uploadFile = examResService.getResource(RES_TYPE_MAP.get(type),id);
        //如果文件未能查询到则返回空数据
        if (uploadFile == null){
            return null;
        }
        //通过地址获取文件对象
        File downloadFile = new File(uploadFile.getSavePath());
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        //读取文件流
        byte[] body = null;
        InputStream is = null;
        HttpHeaders headers = null;
        try {
            is = new FileInputStream(downloadFile);
            body = new byte[is.available()];
            is.read(body);
            //设置报文和状态
            headers = new HttpHeaders();
            headers.add("Content-Type",new MimetypesFileTypeMap().getContentType(downloadFile));
            headers.add("Content-Disposition","attchement;filename=" + downloadFile.getName());
            statusCode = HttpStatus.OK;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        //传输文件流
        return new ResponseEntity<byte[]>(body, headers, statusCode);
    }
}
