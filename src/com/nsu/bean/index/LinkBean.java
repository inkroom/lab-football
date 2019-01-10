package com.nsu.bean.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: LinkBean
 * @Package com.nsu.bean.index
 * @Description:
 * @date 5/5/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LinkBean extends BaseBean {

    @JsonProperty("link_name")
    private String linkName;
    @JsonProperty("link_addr")
    private String linkAddr;
    @JsonProperty("link_type")
    private String linkType;
    @JsonProperty("num")
    private String num;


    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
}
