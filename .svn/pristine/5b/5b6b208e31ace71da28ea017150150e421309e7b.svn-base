package com.nsu.bean.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nsu.common.mapper.JsonMapper;

import java.util.LinkedHashMap;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: AjaxBean
 * @Package com.nsu.bean.common
 * @Description:
 * @date 7/8/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AjaxBean {
    @JsonProperty("success")
    private boolean success = true;// 是否成功
    @JsonProperty("status")
    private String status = "-1";//错误代码
    @JsonProperty("msg")
    private String msg = "操作成功";// 提示信息
    @JsonProperty("data")
    private LinkedHashMap<String, Object> body = new LinkedHashMap<>();//封装json的map

    public AjaxBean() {
    }

    public AjaxBean(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public AjaxBean(String status) {
        this.status = status;
    }

    public LinkedHashMap<String, Object> getBody() {
        return body;
    }

//    public void setBody(LinkedHashMap<String, Object> body) {
//        this.body = body;
//    }

    public void put(String key, Object value) {//向json中添加属性，在js中访问，请调用data.map.key
        body.put(key, value);
    }

    public void remove(String key) {
        body.remove(key);
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {//向json中添加属性，在js中访问，请调用data.msg
        this.msg = msg;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @JsonIgnore//返回对象时忽略此属性
    public String getJsonStr() {//返回json字符串数组，将访问msg和key的方式统一化，都使用data.key的方式直接访问。

        String json = JsonMapper.getInstance().toJson(this);
        return json;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
