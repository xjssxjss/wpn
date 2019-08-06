package com.sean.model.menu;

/**
 * @description: TODO
 * @package_name: com.sean.model.menu
 * @data: 2019-8-5 15:54
 * @author: Sean
 * @version: V1.0
 */
public class ClickButton extends Button {
    private String type = "click";
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

    public ClickButton(String name,String key) {
        super(name);
        this.key = key;
    }
}
