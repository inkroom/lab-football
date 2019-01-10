
//package cn.edu.nsu.lib.controllers.teacher;
//
//import cn.edu.nsu.lib.enums.Result;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class FileHandler {
//    private Result state = Result.SUCCESS;
//    private String path = "通知路径";
//
//    //    FileHandler(CommonsMultipartFile noticeFile, HttpServletRequest request){
//    FileHandler(CommonsMultipartFile noticeFile) {
//        String type = noticeFile.getOriginalFilename().substring(noticeFile.getOriginalFilename().indexOf("."));//取文件格式后缀名
//        String filename = System.currentTimeMillis() + type;//取当前时间戳作为文件名
////                    path = request.getSession().getServletContext().getRealPath("/upload/"+filename);
//        path = filename;
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    FileOutputStream os = new FileOutputStream(path);
//                    InputStream in = noticeFile.getInputStream();
//                    int b = 0;
//                    while ((b = in.read()) != -1) {//读文件
//                        os.write(b);
//                    }
//                    os.flush();
//                    in.close();
//                    os.close();
//                    state = Result.SUCCESS;
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                    state = Result.EXCEPTION;
//                } catch (IOException e) {//输入流异常
//                    e.printStackTrace();
//                    state = Result.EXCEPTION;
//                }
//            }
//        }.start();
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public Result getState() {
//        return state;
//    }
//}
