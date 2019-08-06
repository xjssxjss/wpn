package com.sean.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Menu
 * @package_name: com.sean.model.menu
 * @data: 2019-8-5 16:13
 * @author: Sean
 * @version: V1.0
 */
public class Menu {
    private List<Button> button = new ArrayList<>(0);

    public Menu(List<Button> button) {
        this.button = button;
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
