package com.dustincode.game2d;

import com.dustincode.game2d.renderengine.DisplayManager;

public class MainGame {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        while(!DisplayManager.isCloseDisplay()) {
            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
