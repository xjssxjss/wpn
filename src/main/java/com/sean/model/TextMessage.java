package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Created by Sean on 2019-8-4.
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage{

    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String,String> requestMap,String content){
        //调用父类构造方法
        super(requestMap);
        //设置文本消息的msgType为text
        super.setMsgType("text");
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
