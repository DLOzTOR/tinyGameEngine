package dev.dloztor.tinygameengine.graphics.gl.core.window;

import dev.dloztor.tinygameengine.graphics.core.Window;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class OpenGLWindow extends Window {

    /**
     * window handle
     */
    private long windowHandle;

    /**
     * initial window width
     */
    private final int initialWidth;

    /**
     * initial window height
     */
    private final int initialHeight;

    public OpenGLWindow(int width, int height, String title) {
        super(title);
        this.initialWidth = width;
        this.initialHeight = height;
    }

    @Override
    public void init(){
        windowHandle = new WindowInitializer().initializeWindow(title, initialWidth, initialHeight);
        setClearWindowColor();
        onInit();
    }


    protected void onInit() {/*default implementation ignored*/}

    //TODO: Add possibility to configure clear color
    /**
     * for override purposes
     */
    protected void setClearWindowColor(){
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }


    public void updateFrame() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        onUpdateFrame();
        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwSwapBuffers(windowHandle); // swap the color buffers
        glfwPollEvents();
        updateShouldClose();
    }

    private void updateShouldClose() {
        shouldClose = glfwWindowShouldClose(windowHandle);
    }

    protected void onUpdateFrame(){/*default implementation ignored*/}

    private void freeResources(){
        freeWindowResources();
        onFreeResources();
    }

    protected void onFreeResources() {/*default implementation ignored*/}

    private void freeWindowResources(){
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(windowHandle);
        glfwDestroyWindow(windowHandle);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    @Override
    public void dispose() {
        freeResources();
    }
}