package com.sean.model.template;

/**
 * @description: 模板跳转小程序
 * @package_name: com.sean.model.template
 * @data: 2019-8-7 10:36
 * @author: Sean
 * @version: V1.0
 */
public class MiniProgram {

    //小程序appid
    private String appid;
    //跳转的小程序路径
    private String pagepath;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
