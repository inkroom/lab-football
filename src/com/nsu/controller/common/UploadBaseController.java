package com.nsu.controller.common;

import com.nsu.bean.common.FileUploadBean;
import com.nsu.controller.BaseController;
import com.nsu.exception.upload.ExtensionsException;
import com.nsu.exception.upload.LengthException;
import com.nsu.exception.upload.NullFileParamException;
import com.nsu.exception.upload.SizeException;
import com.nsu.service.common.IFileUpoadService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MeiXiebing on 3/30/17.
 */
public class UploadBaseController extends BaseController {

    public static final long FILE_SIEZ = 1024;

    private static final String WEB_URL_PATH = "file"+File.separator;

    @Resource
    private IFileUpoadService fileUpoadService;

    private FileUploadBean fileUploadBean;

    /**
     *
     * @param file
     * @param fileUploadBean
     * @param targetFileName
     * @return
     * @throws SizeException
     * @throws LengthException
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws IOException
     */
    public String baseUpload(File file, FileUploadBean fileUploadBean,String targetFileName)throws SizeException, LengthException, ExtensionsException, NullFileParamException, IOException {
        //获取文件名
        String fileOrigName = file.getName();

        //检验文件是否为空
        validateFileIsNull(file);
        //获取文件后缀
        String fileOrigExtension = getFileExtension(fileOrigName);
        //检验是否为图片
        if (isPicture(fileOrigExtension)){
            //检验图片的长宽是否超过尺寸
            validateSize(file);
        }
        //检验大小
        validateLength(file);

        //检验该文件是否允许上传
        validateExtension(fileUploadBean,fileOrigExtension);

        //获取根目录 支持 linux windows mac
        String basePath = getBasePath();

        //获取上传中间目录
        String middlePath = getMiddlePath();


        //获取该角色上传的路径
        String path = fileUploadBean.getDir().replaceAll("/",File.separator) + File.separator ;

        if (targetFileName == null || targetFileName.trim().equals("")){
            // 设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            targetFileName = (sdf.format(new Date()));
        }
        //文件名命名
        String name =  targetFileName + "." +fileOrigExtension;

        File targetFile = new File(basePath + middlePath + path, name);

        //判断文件是否存在
        if (!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        // 上传
        try {
            FileUtils.copyFile(file,targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WEB_URL_PATH+middlePath+path+name;
    }

    public String baseUpload(MultipartFile file, FileUploadBean fileUploadBean,String targetFileName)throws SizeException, LengthException, ExtensionsException, NullFileParamException, IOException {
        //检验文件是否为空
        validateFileIsNull(file);

        //获取文件名
        String fileOrigName = file.getOriginalFilename();

        //获取文件后缀
        String fileOrigExtension = getFileExtension(fileOrigName);

        //检验是否为图片
        if (isPicture(fileOrigExtension)){
            //检验图片的长宽是否超过尺寸
            validateSize(file);
        }

        //检验大小
        validateLength(file);

        //检验该文件是否允许上传
        validateExtension(fileUploadBean,fileOrigExtension);

        //获取根目录 支持 linux windows mac
        String basePath = getBasePath();

        //获取上传中间目录
        String middlePath = getMiddlePath();

        //获取该角色上传的路径
        String path = fileUploadBean.getDir().replaceAll("/",File.separator) + File.separator ;
        // 设置时间格式
        if (targetFileName == null || targetFileName.trim().equals("")){
            // 设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            targetFileName = (sdf.format(new Date()));
        }
        //文件名命名
        String name =  targetFileName + "." +fileOrigExtension;

        File targetFile = new File(basePath + middlePath + path, name);
        //判断文件是否存在
        if (!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        // 上传
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  WEB_URL_PATH+middlePath+path+name;
    }


    /**
     *
     * @param file 文件
     * @param role 角色类型
     * @param fileType 文件类型
     * @param userId 该用户的id 按照每个人来分文件夹
     * @return
     * @throws SizeException
     * @throws LengthException
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws IOException
     */
	public String fileUpload(File file, String role , String fileType, String userId) throws SizeException, LengthException, ExtensionsException, NullFileParamException, IOException {
        //获取文件bean
        fileUploadBean = fileUpoadService.getFileUploadPath(role,fileType);
        fileUploadBean.setDir(fileUploadBean.getDir()+File.separator+userId);
        // 返回上传之后的路径
        return  baseUpload(file,fileUploadBean,null);

    }


    /**
     *
     * @param file 文件
     * @param role 角色类型
     * @param fileType 文件类型
     * @param targetFileName 文件名，可以使文件覆盖存在的文件。
     * @return
     * @throws SizeException
     * @throws LengthException
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws IOException
     */
    public String fileUploadRepace(File file, String role , String fileType, String targetFileName) throws SizeException, LengthException, ExtensionsException, NullFileParamException, IOException {

        //获取文件bean
        fileUploadBean = fileUpoadService.getFileUploadPath(role,fileType);
        // 返回上传之后的路径
        return  baseUpload(file,fileUploadBean,targetFileName);

    }

    /**
     *
     * @param file 文件
     * @param role 角色类型
     * @param fileType 文件类型
     * @param userId 该用户的id 按照每个人来分文件夹
     * @param targetFileName 文件名，可以使文件覆盖存在的文件。
     * @return
     * @throws SizeException
     * @throws LengthException
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws IOException
     */
    public String fileUploadReplace(File file, String role , String fileType, String userId, String targetFileName) throws SizeException, LengthException, ExtensionsException, NullFileParamException, IOException {

        //获取文件bean
        fileUploadBean = fileUpoadService.getFileUploadPath(role,fileType);
        fileUploadBean.setDir(fileUploadBean.getDir()+File.separator+userId);
        // 返回上传之后的路径
        return  baseUpload(file,fileUploadBean,targetFileName);

    }





    /**
     *
     * 中间加入路径
     *
     * @param file 文件
     * @param role 角色名
     * @param fileType 文件类型
     * @param userId 中间加入路径,例如：userID
     * @return 返回文件路径，不包括 desktop 或者 home
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws SizeException
     * @throws LengthException
     */
    public String fileUpload(MultipartFile file, String role , String fileType , String userId) throws ExtensionsException, NullFileParamException, SizeException, LengthException, IOException {
        //获取文件bean
        fileUploadBean = fileUpoadService.getFileUploadPath(role,fileType);
        fileUploadBean.setDir(fileUploadBean.getDir()+File.separator+userId);
        // 返回上传之后的路径
        return  baseUpload(file,fileUploadBean,null);
    }


    /**
     * 自定文件名
     * @param file 文件
     * @param role 角色名
     * @param fileType 文件类型
     * @param targetFileName 保存时的文件名
     * @return
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws SizeException
     * @throws LengthException
     */
    public String fileUploadReplace(MultipartFile file, String role , String fileType , String targetFileName) throws ExtensionsException, NullFileParamException, SizeException, LengthException, IOException {

        //获取文件bean
        fileUploadBean = fileUpoadService.getFileUploadPath(role,fileType);
        // 返回上传之后的路径
        return  baseUpload(file,fileUploadBean,targetFileName);
    }


    /**
     * 自定文件名
     * @param file 文件
     * @param role 角色名
     * @param fileType 文件类型
     * @param targetFileName 保存时的文件名
     * @param userId 中间加入路径,例如：userID
     * @return
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws SizeException
     * @throws LengthException
     */
    public String fileUploadReplace(MultipartFile file, String role , String fileType , String userId , String targetFileName) throws ExtensionsException, NullFileParamException, SizeException, LengthException, IOException {

        //获取文件bean
        fileUploadBean = fileUpoadService.getFileUploadPath(role,fileType);
        fileUploadBean.setDir(fileUploadBean.getDir()+File.separator+userId);
        // 返回上传之后的路径
        return  baseUpload(file,fileUploadBean,targetFileName);
    }

    /**
     * 自定文件名
     * 文件大小限制
     * @param file 文件
     * @param role 角色名
     * @param fileType 文件类型
     * @param name 保存时的文件名
     * @param userId 中间加入路径,例如：userID
     * @param fileSize 上传文件最大值 单位b
     * @author ljl
	 * @createDate 2017-04-20 21:35:22
     * @return 返回map的key为error的为null时文件上传成功否则为错误提示；文件上传路径存在key为path中
     * @throws ExtensionsException
     * @throws NullFileParamException
     * @throws SizeException
     * @throws LengthException
     */
    public Map<String, Object> fileUpload(MultipartFile file, String role , String fileType , String userId , String name, long fileSize) throws ExtensionsException,NullFileParamException,SizeException,LengthException{

    	Map<String, Object> map = new HashMap<String, Object>();

        String fileOrigName = file.getOriginalFilename();

        String fileOrigExtension = getFileExtension(fileOrigName);

        if (isPicture(fileOrigExtension)){
            validateSize(file);
        }

//        validateLength(file);
        long length = file.getSize();
        if (length > fileSize) {
        	map.put("error", "上传文件最大为"+fileSize+",当前文件大小为："+length);
        	log.debug( "上传文件最大为"+fileSize+",当前文件大小为："+length);
        	return map;
        }
    	if (file == null || file.getSize() == 0){
    		 map.put("error", "请选择上传文件");
    		 log.debug( "上传文件为空");
    		 return map;
    	}

        fileUploadBean = fileUpoadService.getFileUploadPath(role,fileType);
        try{
        	validateExtension(fileUploadBean,fileOrigExtension);
        }catch(Exception e){
        	e.printStackTrace();
        	 map.put("error", "上传文件格式错误");
        	log.debug( "上传文件格式错误");
        	return map;
        }

        String basePath = getBasePath();

        String middlePath = getMiddlePath();

        String path = fileUploadBean.getDir().replaceAll("/",File.separator) + File.separator + userId + File.separator;

        File targetFile = new File(basePath + middlePath + path, name);

        if (!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }

        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "上传文件失败");
        	log.debug( "存储文件发生异常");
        	return map;
        }
        map.put("error", null);
        map.put("path", WEB_URL_PATH+middlePath+path+name);
        return  map;
    }

    private boolean validateFileIsNull(MultipartFile file) throws NullFileParamException{
        if (file == null || file.getSize() == 0){
            throw new NullFileParamException("请选择要上传的文件");
        }
        return true;
    }


    private boolean validateFileIsNull(File file) throws NullFileParamException {
        if (file == null || file.length() == 0){
            throw new NullFileParamException("请选择要上传的文件");
        }
        return true;
    }

    /**
     * 检验文件类型是否支持
     * @param fileUploadBean
     * @return
     */
    private boolean validateExtension(FileUploadBean fileUploadBean, String fileOrigExtension) throws ExtensionsException {
        Set<String> extension = fileUploadBean.getFileType();
        if (extension.contains(fileOrigExtension)){
            return true;
        }else {
            throw new ExtensionsException("文件格式不正确");
        }
    }

    /**
     * 获取文件后缀
     * @param fileOrigName
     * @return
     */
    private String getFileExtension(String fileOrigName) {
        return fileOrigName.substring(fileOrigName.lastIndexOf(".") + 1).toLowerCase();
    }


    /**
     * 功能描述：验证图片尺寸是否允许上传，详细规则待定，现在只是测试
     *
     * @return 验证通过返回true
     * @throws SizeException
     *             验证不通过抛出异常
     * @author RenQiang Create Date: 2016-04-18
     */
    private boolean validateSize(MultipartFile file) throws SizeException {

        CommonsMultipartFile cf= (CommonsMultipartFile)file;

        DiskFileItem fi = (DiskFileItem)cf.getFileItem();

        File newfile = fi.getStoreLocation();
        BufferedImage bi = null ;
        int width = 0;
        int height = 0;
        try {
            bi = ImageIO.read(newfile);
            width = bi.getWidth();
            height = bi.getHeight();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        if (width > 1920 || height > 1080) {
            throw new SizeException("图片尺寸过大，不能超过1920*1080");
        }
            return  true;

    }


    /**
     * 功能描述：验证图片尺寸是否允许上传，详细规则待定，现在只是测试
     *
     * @return 验证通过返回true
     * @throws SizeException
     *             验证不通过抛出异常
     * @author RenQiang Create Date: 2016-04-18
     */
    private boolean validateSize(File file) throws SizeException {

        BufferedImage bi = null ;
        int width = 0;
        int height = 0;
        try {
            bi = ImageIO.read(file);
            width = bi.getWidth();
            height = bi.getHeight();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        if (width > 1920 || height > 1080) {
            throw new SizeException("图片尺寸过大，不能超过1920*1080");
        }
        return  true;

    }


    /**
     * 检验是不是图片
     * @param fileOrigExtens
     * @return
     */
    private boolean isPicture(String fileOrigExtens) {
        Set<String> set = new HashSet<String>();

        set.add("TGA");
        set.add("tga");

        set.add("EPS");
        set.add("eps");

        set.add("EMF");
        set.add("emf");

        set.add("WMF");
        set.add("wmf");


        set.add("bmp");
        set.add("BMP");


        set.add("jpg");
        set.add("JPG");

        set.add("jpeg");
        set.add("JPEG");

        set.add("png");
        set.add("PNG");

        if (set.contains(fileOrigExtens)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 功能描述：验证文件大小是否允许上传
     *
     * @return 验证通过返回true
     * @throws LengthException
     *             验证不通过抛出异常
     * @author RenQiang Create Date: 2016-04-18
     */
    private boolean validateLength(MultipartFile file) throws LengthException {
        long length = file.getSize()/1024;

        if (length > FILE_SIEZ) {
            throw new LengthException("文件过大，当前" + length + "KB，不能超过" + FILE_SIEZ + "KB");
        }

        return true;
    }


    /**
     * 功能描述：验证文件大小是否允许上传
     *
     * @return 验证通过返回true
     * @throws LengthException
     *             验证不通过抛出异常
     * @author RenQiang Create Date: 2016-04-18
     */
    private boolean validateLength(File file) throws LengthException {
        long maxLength = 1024;
        long length = file.length()/1024;

        if (length > maxLength) {
            throw new LengthException("文件过大，当前" + length + "KB，不能超过" + maxLength + "KB");
        }

        return true;
    }

    private String getBasePath(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("mac")){

            return com.getPath()+File.separator + "Desktop" + File.separator;

        }if (os.toLowerCase().startsWith("linux")){

            return File.separator+"home"+ File.separator;

        }if (os.toLowerCase().startsWith("win")){

            return  com.getPath() + File.separator;

        }

        return  "/upload";

    }


    public String getMiddlePath(){
        return "upload" + File.separator;
    }






}
