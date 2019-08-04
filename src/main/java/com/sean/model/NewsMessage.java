package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sean on 2019-8-4.
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{
    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articleList = new ArrayList<>();

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public NewsMessage(Map<String,String> requestMap, List<Article> articleList) {
        super(requestMap);
        super.setMsgType("news");
        this.articleCount = articleList.size()+"";
        this.articleList = articleList;
    }
}
