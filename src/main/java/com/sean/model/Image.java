package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Sean on 2019-8-4.
 */
@XStreamAlias("Image")
public class Image {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public Image(String mediaId) {
        this.mediaId = mediaId;
    }
}
