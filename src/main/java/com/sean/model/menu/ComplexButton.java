package com.sean.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 复合类型按钮
 * @package_name: com.sean.model.menu
 * @data: 2019-8-5 17:04
 * @author: Sean
 * @version: V1.0
 */
public class ComplexButton extends Button{
    private List<Button> sub_button = new ArrayList<>();

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }

    public ComplexButton(String name,List<Button> sub_button) {
        super(name);
        this.sub_button = sub_button;
    }
}
