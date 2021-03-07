package com.dustincode.game2d.renderengine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * This class contains all the methods needed to setup, maintain and close a display window.
 *
 * @author Dustin Ng
 */
public class DisplayManager {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static final String TITLE = "2D Game";

    public static long glfwWindow;

    /**
     * Creates a display window on which we can render our game.
     */
    public static void createDisplay() {
        // Setup error callback with System.err
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Could not initialize GLFW!");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        // We just want to show window after all things are setup, so set visible to false
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        // Enable window resizeable
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        // Enable maximized window position when it's started
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create window
        // It will return the long number which basically is a memory address where window in the memory space
        glfwWindow = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window!");
        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync which basically buffer swapping.
        // Set 1 for no restriction, every single frame there's no wait time between the frames
        glfwSwapInterval(1);

        // Show window after init
        glfwShowWindow(glfwWindow);

        // This line is critical LWJGL's interoperation with GLFW's OpenGL context or any context that is managed
        // externally. LWJGL detects the context that is current in the current thread, create the GLCapabilities
        // instance and makes the OpenGL bindings available for use.
        GL.createCapabilities();

        // Set the color that the function GL11.glClear will clear to.
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Updates the display window at the end of every frame.
     */
    public static void updateDisplay() {
        // Poll events like key events or mouse events
        glfwPollEvents();

        // Before draw something on virtual screen, we need to clear the color on every pixel to the color was set
        // by GL11.glClearColor
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // There are two buffers, one current show (aka actual screen) and one we draw on (aka virtual screen).
        // So every time we use OpenGL to draw something to the screen, we are actually drawing to a hidden virtual screen.
        // To show it to the actual screen we need to swaps all buffer that we draw on virtual screen to actual screen.
        glfwSwapBuffers(glfwWindow);
    }

    /**
     * Close the window display when the game is closed.
     */
    public static void closeDisplay() {
        // Free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * Checks if the display window is closed.
     * @return true in case of the display window is closed
     */
    public static boolean isCloseDisplay() {
        return glfwWindowShouldClose(glfwWindow);
    }
}
