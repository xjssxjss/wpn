package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Created by Sean on 2019-8-4.
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{

    @XStreamAlias("Image")
    private Image image;

    public ImageMessage(Map<String, String> requestMap,Image image) {
        super(requestMap);
        super.setMsgType("image");
        this.image = image;
    }
}
