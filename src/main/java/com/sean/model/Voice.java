package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Sean on 2019-8-4.
 */

@XStreamAlias("Voice")
public class Voice {

    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Voice(String mediaId) {
        this.mediaId = mediaId;
    }
}
