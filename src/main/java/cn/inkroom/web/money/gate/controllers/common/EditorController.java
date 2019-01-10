package cn.inkroom.web.money.gate.controllers.common;

import cn.inkroom.web.money.gate.dto.ctv.EditorDto;
import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.services.common.UploadImgService;
import cn.inkroom.web.money.gate.utils.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * 上传控制器
 */
@Controller
public class EditorController {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UploadImgService service;
    @Autowired
    private HttpServletRequest request;


    @RequestMapping("/editor")
    public void home(String action, MultipartFile file, HttpServletResponse response) {
        if ("config".equals(action)) {
            try {
                request.getRequestDispatcher("/resources/test.json").forward(request, response);
                return;
            } catch (ServletException | IOException e) {
                try {
                    ResponseUtil.outJson(response, new EditorDto(e.getMessage()).toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
//            return "forward:/resources/test.json";
        } else if ("upload".equals(action)) {
            log.info("上传文件");
            if (file != null) {
                try {
                    String result = upload(file, null);
                    log.info(String.valueOf(result == null));
                    log.info(result);
                    File file1 = new File(result);


                    EditorDto dto = new EditorDto();
                    dto.put("original",file.getOriginalFilename());
                    dto.put("name", "图片未能显示");
                    dto.put("url", request.getContextPath() + "/image/" + file1.getName());
                    dto.put("size", file.getSize());
                    dto.put("type", file1.getName().substring(file1.getName().lastIndexOf(".")));

                    ResponseUtil.outJson(response, dto.toString());
//                    return dto;
//                    String builder = "{\"original\":\"" +
//                            file.getOriginalFilename() +
//                            "\",\"name\":\"图片未能显示" +
//                            "\",\"url\":\"" + request.getContextPath() + "/image/" +
//                            file1.getName() +
//                            "\",\"size\":" +
//                            file.getSize() +
//                            ",\"type\":\"" +
//                            file1.getName().substring(file1.getName().lastIndexOf(".")) +
//                            "\",\"state\":\"SUCCESS\"" +
//                            "}";
                    //                    response.getWriter().print("{\"original\":\"" +
//                            +file.getOriginalFilename() + "\",\"name\":\"" +
//                            +file1.getName() + "\",\"url\":\"" +
//                            +"\\/image\\/" + file1.getName() + "\",\"size\":\"99697\",\"type\":\".jpg\",\"state\":\"SUCCESS\"}");
//                    ResponseUtil.outJson(response, builder);
//                    return null;
                } catch (Exception e) {
                    log.error(e.getMessage());
                    log.info(e.getMessage());
//                    e.printStackTrace();

                    try {
                        ResponseUtil.outJson(response, new EditorDto(e.getMessage()).toString());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    //return new EditorDto(e.getMessage());
                }

            }
//            return "home";
        } else {
            try {
                ResponseUtil.outJson(response, new EditorDto("该功能尚未开放").toString());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
//            return new EditorDto("该功能尚未开放");
//            return "home";
        }
//        return new EditorDto("该功能尚未开放");
    }

        /**
         * 上传文件
         *
         * @param file
         * @param request
         * @return
         */
        public String upload (MultipartFile file, HttpServletRequest request){
            if (checkType(file) && checkSize(file) && checkScreen(file)) {
                //检查通过
                File target = new File(service.getPath(), System.currentTimeMillis()
                        + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
                try {
                    file.transferTo(target);
                    return target.getAbsolutePath();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info("检查未通过");
            return null;
        }


        private boolean checkType (MultipartFile file){
            String oName = file.getOriginalFilename();
            log.info("来自配置" + service.getAllowType());
            log.info("来自前台= " + (oName.substring(oName.lastIndexOf(".") + 1)));
            boolean result = service.getAllowType().contains(oName.substring(oName.lastIndexOf(".") + 1));
            log.info("类型检查结果=" + result);
            return result;
        }

        private boolean checkSize (MultipartFile file){
            log.info("文件大小" + file.getSize() + "   最小大小=" + service.getMinSize() + "   最大大小=" + service.getMaxSize());
            boolean result = (file.getSize() > service.getMinSize() && file.getSize() < service.getMaxSize());
            log.info("大小检查结果=" + result);
            return result;
        }

        /**
         * 获取图片的分辨率
         *
         * @param path
         * @return
         */
        public static Dimension getImageDim (String path){
            Dimension result = null;
            String suffix = getFileSuffix(path);
            //解码具有给定后缀的文件
            Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
            System.out.println(ImageIO.getImageReadersBySuffix(suffix));
            if (iter.hasNext()) {
                ImageReader reader = iter.next();
                try {
                    ImageInputStream stream = new FileImageInputStream(new File(
                            path));
                    reader.setInput(stream);
                    int width = reader.getWidth(reader.getMinIndex());
                    int height = reader.getHeight(reader.getMinIndex());
                    result = new Dimension(width, height);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    reader.dispose();
                }
            }
            return result;
        }

        /**
         * 获得图片的后缀名
         *
         * @param path
         * @return
         */
        private static String getFileSuffix ( final String path){
            String result = null;
            if (path != null) {
                result = "";
                if (path.lastIndexOf('.') != -1) {
                    result = path.substring(path.lastIndexOf('.'));
                    if (result.startsWith(".")) {
                        result = result.substring(1);
                    }
                }
            }
            return result;
        }

        /**
         * 截取Dimension对象获得分辨率
         *
         * @param path
         * @return
         */
        public static String getResolution2 (String path){
            String s = getImageDim(path).toString();
            s = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
            String w = s.substring(s.indexOf("=") + 1, s.indexOf(","));
            String h = s.substring(s.lastIndexOf("=") + 1);
            String result = w + " x " + h;
            System.out.println("getResolution:" + result);
            return result;
        }

        private boolean checkScreen (MultipartFile file){
            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                log.info("前台高度=" + image.getHeight());
                log.info("前台宽度=" + image.getWidth());
                log.info(service.getMinHeight() + "-" + service.getMaxHeight() + "-" + service.getMinWidth() + "-" + service.getMaxWidth());
                return image.getHeight() > service.getMinHeight() && image.getHeight() < service.getMaxHeight()
                        && image.getWidth() > service.getMinWidth() && image.getWidth() < service.getMaxWidth();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

    }
