package com.sean.model.menu;

/**
 * @description: 所有按钮的基类
 * @package_name: com.sean.model.menu
 * @data: 2019-8-5 15:53
 * @author: Sean
 * @version: V1.0
 */
public abstract class Button {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button(String name) {
        this.name = name;
    }
}
