package cn.nsu.edu.web.four.filters;

import cn.nsu.edu.web.four.config.BaseStatic;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XSSFormRequest extends DefaultMultipartHttpServletRequest {
    @Override
    public ServletContext getServletContext() {
        return super.getServletContext();
    }

    @Override
    public String getContextPath() {
        String path = getServletContext().getInitParameter("contextPath");
        if (path == null)
            return super.getContextPath();
        else return path;
    }

    public XSSFormRequest(HttpServletRequest request) {
        super(request);
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            Map<String, String[]> multipartParameters = new HashMap<>();
            Map<String, String> multipartParameterContentTypes = new HashMap<>();
            MultiValueMap<String, MultipartFile> multipartFiles = new LinkedMultiValueMap<>();
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {//是字符流

                    String value;
                    value = item.getString(BaseStatic.CHARSET);
                    String[] curParam = multipartParameters.get(item.getFieldName());
                    if (curParam == null) {
                        multipartParameters.put(item.getFieldName(), new String[]{value});
                    } else {
                        String[] newParam = StringUtils.addStringToArray(curParam, value);
                        multipartParameters.put(item.getFieldName(), newParam);
                    }
                    multipartParameterContentTypes.put(item.getFieldName(), item.getContentType());

                } else {
                    CommonsMultipartFile multipartFile = new CommonsMultipartFile(item);
                    multipartFiles.add(multipartFile.getName(), multipartFile);
//                    if (this.logger.isDebugEnabled()) {
//                        this.logger.debug("Found multipart file [" + file.getName() + "] of size " + file.getSize() + " bytes with original filename [" + file.getOriginalFilename() + "], stored " + file.getStorageDescription());
//                    }
                }
            }
            this.setMultipartFiles(multipartFiles);
            this.setMultipartParameters(multipartParameters);
            this.setMultipartParameterContentTypes(multipartParameterContentTypes);
            // TODO: 18-4-4 待测试formData 效果
//            DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = new DefaultMultipartHttpServletRequest(request, multipartFiles, multipartParameters, multipartParameterContentTypes);
//            filterChain.doFilter(defaultMultipartHttpServletRequest, response);
        } catch (FileUploadException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);

        if (name.equals(XSSRequestWrapper.EDITOR_FILTER)) {
            value = XSSRequestWrapper.stripLittleXSS(value);
        } else {
            value = XSSRequestWrapper.stripXSS(value);
        }
        return value;

//        return super.getParameter(name);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);

        return xss(values, name);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (name.equals(XSSRequestWrapper.EDITOR_FILTER)) {
            return XSSRequestWrapper.stripLittleXSS(value);
        }
        return XSSRequestWrapper.stripXSS(value);
    }

    private String[] xss(String[] values, String name) {

        if (name.equals(XSSRequestWrapper.EDITOR_FILTER)) {
            for (int i = 0; i < values.length; i++) {
                values[i] = XSSRequestWrapper.stripLittleXSS(values[i]);
            }
        } else {
            for (int i = 0; i < values.length; i++) {
                values[i] = XSSRequestWrapper.stripXSS(values[i]);
            }
        }

        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = super.getParameterMap();

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String temp = iterator.next();

            String values[] = map.get(temp);
            values = xss(values, temp);
            map.put(temp, values);

        }

        return map;
    }
}
