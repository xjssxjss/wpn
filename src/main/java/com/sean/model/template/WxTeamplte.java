package com.sean.model.template;

/**
 * @description: TODO
 * @package_name: com.sean.model.template
 * @data: 2019-8-7 10:13
 * @author: Sean
 * @version: V1.0
 */
public class WxTeamplte {

    private String touser;
    private String template_id;
    private TemplateData data;
    private String url;
    private MiniProgram miniprogram;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public TemplateData getData() {
        return data;
    }

    public void setData(TemplateData data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public WxTeamplte(String touser, String template_id, TemplateData data) {
        this.touser = touser;
        this.template_id = template_id;
        this.data = data;
    }

    public WxTeamplte(String touser, String template_id,String url, TemplateData data) {
        this.url = url;
        this.touser = touser;
        this.template_id = template_id;
        this.data = data;
    }

    public WxTeamplte(String touser, String template_id, TemplateData data, MiniProgram miniprogram) {
        this.touser = touser;
        this.template_id = template_id;
        this.data = data;
        this.miniprogram = miniprogram;
    }

    public WxTeamplte(String touser, String template_id, TemplateData data, String url, MiniProgram miniprogram) {
        this.touser = touser;
        this.template_id = template_id;
        this.data = data;
        this.url = url;
        this.miniprogram = miniprogram;
    }
}
