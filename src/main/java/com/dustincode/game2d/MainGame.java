package com.dustincode.game2d;

import com.dustincode.game2d.renderengine.DisplayManager;
import com.dustincode.game2d.renderengine.Loader;
import com.dustincode.game2d.renderengine.Model;
import com.dustincode.game2d.renderengine.Renderer;

public class MainGame {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        float[] vertices = {
                -0.5f,  0.5f, 0f,
                -0.5f, -0.5f, 0f,
                 0.5f, -0.5f, 0f,

                 0.5f, -0.5f, 0f,
                 0.5f,  0.5f, 0f,
                -0.5f,  0.5f, 0f,
        };

        Model model = Loader.loadToVAO(vertices);

        while(!DisplayManager.isCloseDisplayRequested()) {

            Renderer.prepare();

            Renderer.render(model);

            DisplayManager.updateDisplay();

        }

        Loader.cleanUp();
        DisplayManager.closeDisplay();
    }

}
