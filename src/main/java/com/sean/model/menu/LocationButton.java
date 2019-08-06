package com.sean.model.menu;

/**
 * @description: 地址信息button
 * @package_name: com.sean.model.menu
 * @data: 2019-8-5 16:28
 * @author: Sean
 * @version: V1.0
 */
public class LocationButton extends Button {
    private String type = "location_select";
    private String key;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocationButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
