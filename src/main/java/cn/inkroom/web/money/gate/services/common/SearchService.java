package cn.inkroom.web.money.gate.services.common;

import cn.inkroom.web.money.gate.beans.common.SearchResultBean;
import cn.inkroom.web.money.gate.config.BaseStatic;
import cn.inkroom.web.money.gate.daos.jdbc.Common.SearchDao;
import cn.inkroom.web.money.gate.dto.stc.SearchResultDto;
import cn.inkroom.web.money.gate.filters.XSSRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchService {

    @Autowired
    private SearchDao dao;


    private Logger log = LoggerFactory.getLogger(getClass());

    public SearchResultDto search(String key, int page, SearchResultDto lastResult) throws Exception {

// TODO: 18-3-10 翻页功能有漏洞，可以考虑搜索出所有记录，然后排序，再取页码中，可以放到redis中。但是效率值得商榷
        List<SearchResultBean> all;
        List<SearchResultBean> resultBeans;
        //和上次搜索的key相同
        if (lastResult != null && lastResult.getKey().equals(key)) {
            all = lastResult.getAll();
            int next = (page + 1) * BaseStatic.EACH_PAGE_COUNT;
            resultBeans = all.subList(page * BaseStatic.EACH_PAGE_COUNT, next > all.size() ? all.size() : next);
            lastResult.setResult(resultBeans);
            return lastResult;
        }

        //搜索html表
        List<SearchResultBean> htmls = dao.searchHtml(key);
        //搜索新闻
        List<SearchResultBean> news = dao.searchNews(key);
        news.forEach(searchResultBean -> searchResultBean.setUrl("news/news/signel.html?type="+searchResultBean.getType()+"&id=" + searchResultBean.getId()));
        //搜索技术产品
        List<SearchResultBean> pro = dao.searchProATe(key, "pr", "product");
        pro.forEach(bean -> bean.setUrl("ProAndTech/proandte/signel.html?type=1&id=" + bean.getId()));
        List<SearchResultBean> te = dao.searchProATe(key, "te", "technical");
        te.forEach(bean -> bean.setUrl("ProAndTech/proandte/signel.html?type=2&id=" + bean.getId()));
        //搜索联系我们

        //整合并按时间排序
        all = new LinkedList<>();
        all.addAll(htmls);
        all.addAll(news);
        all.addAll(pro);
        all.addAll(te);
        Pattern pattern = Pattern.compile(".{0,10}" + key + ".{0,10}");

        for (int i = all.size() - 1; i >= 0; i--) {
            SearchResultBean bean = all.get(i);
            String temp = XSSRequestWrapper.stripXSS(bean.getContent());
            if (!temp.contains(key)) {
                all.remove(i);
                continue;
            }

            StringBuilder builder = new StringBuilder();
            Matcher matcher = pattern.matcher(temp);
            while (matcher.find() && builder.length() < 200) {
                builder.append(matcher.group());
                builder.append("...");
            }
            int length = builder.length();
            if (length > 0) {
                builder.delete(length - 3, length + 1);
            }
            bean.setContent(builder.toString());

        }

        all.sort((b1, b2) -> {
            return (b1.getCreateTime().before(b2.getCreateTime()) ? 1 : 0);
//                return ;
        });

        //截取指定页码数据
        int next = (page + 1) * BaseStatic.EACH_PAGE_COUNT;
        log.info((next > all.size() ? all.size() : next) + "");
        resultBeans = all.subList(page * BaseStatic.EACH_PAGE_COUNT, next > all.size() ? all.size() : next);

        SearchResultDto dto = new SearchResultDto();
        dto.setAll(all);
        dto.setKey(key);
        dto.setResult(resultBeans);

        return dto;

    }
}
