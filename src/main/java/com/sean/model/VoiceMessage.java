package com.sean.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Created by Sean on 2019-8-4.
 */

@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{

    @XStreamAlias("Voice")
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public VoiceMessage(Map<String, String> requestMap,Voice voice) {
        super(requestMap);
        super.setMsgType("voice");
        this.voice = voice;
    }
}
