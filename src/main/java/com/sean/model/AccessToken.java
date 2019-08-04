package com.sean.model;

import java.io.Serializable;

/**
 * accessToken实体类
 * Created by Sean on 2019-8-4.
 */
public class AccessToken implements Serializable{
    private String accessToken;     //accessToken
    private long expireTime;       //剩余毫秒数

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public AccessToken(String accessToken, String expireIn) {
        super();
        this.accessToken = accessToken;
        this.expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
    }

    //查看是否过期
    public boolean isExpired(){
        return System.currentTimeMillis()>expireTime;
    }
}
