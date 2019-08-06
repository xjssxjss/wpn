package com.sean.model.menu;

/**
 * @description: 图片按钮
 * @package_name: com.sean.model.menu
 * @data: 2019-8-5 17:12
 * @author: Sean
 * @version: V1.0
 */
public class ImageButton extends Button{

    private String type = "media_id";
    private String media_id;

    public String getType() {
        return type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public ImageButton(String name, String media_id) {
        super(name);
        this.media_id = media_id;
    }
}
