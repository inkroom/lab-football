package cn.inkroom.web.money.gate.dto.ctv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * 富文本编辑器返回dto
 */
//@JsonInclude
public class EditorDto {


    @JsonIgnore
    private Logger log = LoggerFactory.getLogger(getClass());
    private String state;

    private HashMap<String, Object> map;

    public EditorDto() {
        this("SUCCESS");
    }

    public EditorDto(String state) {
        map = new HashMap<>();
        this.state = state;
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    public void put(String key, Long value) {
        map.put(key, value);
    }

    @Override
    public String toString() {
        JsonObject object = new JsonObject();
        object.addProperty("state", state);
        //遍历map，把对应值放到JsonObject里
        map.forEach((k, v) -> {
            if (v instanceof String) {
                object.addProperty(k, (String) v);
            } else if (v instanceof Long) {
                object.addProperty(k, (Long) v);
            }
        });
//        map.forEach(object::addProperty);
        return object.toString();
    }
}
