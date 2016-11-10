package com.nvgct.menu;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class Menu {
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] buttons) {
        this.button = buttons;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "button=" + Arrays.toString(button) +
                '}';
    }
}
