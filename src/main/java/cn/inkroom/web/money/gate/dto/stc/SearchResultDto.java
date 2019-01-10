package cn.inkroom.web.money.gate.dto.stc;

import cn.inkroom.web.money.gate.beans.common.SearchResultBean;

import java.util.List;

public class SearchResultDto {

    private String key;
    private List<SearchResultBean> all;
    private List<SearchResultBean> result;

    public int getCount() {
        return all.size();
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<SearchResultBean> getAll() {
        return all;
    }

    public void setAll(List<SearchResultBean> all) {
        this.all = all;
    }

    public List<SearchResultBean> getResult() {
        return result;
    }

    public void setResult(List<SearchResultBean> result) {
        this.result = result;
    }
}
