package cn.nsu.edu.web.four.dto.ctv;

import cn.nsu.edu.web.four.enums.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

/**
 * 控制层交给视图层的dto对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageDto {

    private int status;
    private String message;
    @JsonIgnore
    private Logger logger = LoggerFactory.getLogger(getClass());

    @JsonProperty
    private LinkedHashMap<String, Object> data = new LinkedHashMap<>();//封装json的map

    private String token;//新的token



    public MessageDto(Result status) {
        this.status = status.value();
    }

    public MessageDto() {
    }

    public MessageDto(Result status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Result status) {
        this.status = status.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }


    public Object get(String key) {
        return data.get(key);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
