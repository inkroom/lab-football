package com.nsu.action;

import java.io.IOException;

import com.nsu.action.fileupload.ExtensionsException;
import com.nsu.action.fileupload.LengthException;
import com.nsu.action.fileupload.NullFileParamException;
import com.nsu.action.fileupload.SizeException;
import com.nsu.action.fileupload.UploadAction;
import com.nsu.action.fileupload.UploadType;
import com.nsu.common.Constants;
import com.nsu.common.TokenImmune;
import com.nsu.handlers.PropertiesPlaceholder;
import com.nsu.util.base.ResponseUtil;
import com.nsu.util.base.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/edit/")
public class EditUpload extends UploadAction {
    private String type;
    @Autowired
    private PropertiesPlaceholder placeholder;

    @TokenImmune
    @RequestMapping("uploadpic")
    @ResponseBody
    public void editUpload(String type, MultipartFile file) {
        if (!VerifyUtil.isNotEmpty(type)) {
            return;
        }
        this.type = type;
        String s;
        try {
            String path = getServletContext().getAttribute("picPath").toString();
            s = path + save(file, placeholder.getValue(Constants.UPLOAD_BASE_PATH));
        } catch (IOException e) {
            s = "error|" + "系统错误";
        } catch (ExtensionsException | LengthException | SizeException | NullFileParamException e) {
            s = "error|" + e.getMessage();
        }
        try {
            ResponseUtil.write(getResponse(), s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected UploadType getUploadType() {
        switch (type) {
            case "1":
                return UploadType.User_Picture_Edit_SchoolInfo;
            case "2":
                return UploadType.User_Picture_Edit_SchoolNews;
            case "3":
                return UploadType.User_Picture_Edit_SchoolPeople;
            case "4":
                return UploadType.User_Picture_Edit_SchoolFeature;
        }
        return null;
    }
}
