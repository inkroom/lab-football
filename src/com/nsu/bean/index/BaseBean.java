package com.nsu.bean.index;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: BaseBean
 * @Package com.nsu.bean.index
 * @Description:
 * @date 5/5/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseBean implements Serializable {
    @JsonProperty("id")
    protected String id;

    @JsonProperty("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createDate;

    @JsonProperty("oper_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date operDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }
}
