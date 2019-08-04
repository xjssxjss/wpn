package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Created by Sean on 2019-8-4.
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage{

    @XStreamAlias("Music")
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public MusicMessage(Map<String, String> requestMap, Music music) {
        super(requestMap);
        super.setMsgType("music");
        this.music = music;
    }
}
