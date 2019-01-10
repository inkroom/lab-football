package com.nsu.bean.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: JsonCommonBean
 * @Package com.nsu.bean.common
 * @Description:
 * @date 4/23/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonCommonBean<G> {
    public static final String statusSuccess = "200";
    public static final String statusError = "500";

    @JsonProperty("status")
    private String status;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private G data;


    public static String getStatusSuccess() {
        return statusSuccess;
    }

    public static String getStatusError() {
        return statusError;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public G getData() {
        return data;
    }

    public void setData(G data) {
        this.data = data;
    }
}
