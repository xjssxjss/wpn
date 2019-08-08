package com.sean.model.template;

/**
 * @description: 底层Template
 * @package_name: com.sean.model.template
 * @data: 2019-8-7 10:19
 * @author: Sean
 * @version: V1.0
 */
public class Template {
    private String value;
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Template(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
