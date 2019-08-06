package com.sean.model.menu;

/**
 * @description: TODO
 * @package_name: com.sean.model.menu
 * @data: 2019-8-5 16:06
 * @author: Sean
 * @version: V1.0
 */
public class ViewButton extends Button{
    private String type = "view";
    private String url;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ViewButton(String name, String url) {
        super(name);
        this.url = url;
    }
}
