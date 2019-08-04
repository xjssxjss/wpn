package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Sean on 2019-8-4.
 */
@XStreamAlias("Music")
public class Music {

    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;
    @XStreamAlias("MusicUrl")
    private String musicUrl;
    @XStreamAlias("HQMusicUrl")
    private String hQMusicUrl;
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public Music() {}

    public Music(String title, String description, String musicUrl, String hQMusicUrl, String thumbMediaId) {
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
    }
}
