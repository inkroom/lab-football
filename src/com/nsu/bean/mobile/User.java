package com.nsu.bean.mobile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nsu.bean.common.JsonCommonBean;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: User
 * @Package com.nsu.bean.mobile
 * @Description:
 * @date 4/23/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    /**
     * 通用的属性
     */
    @JsonProperty("userLoginName")
    private String userLoginName;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("userType")
    private String userType;
    @JsonProperty("userToken")
    private String userToken;



}
