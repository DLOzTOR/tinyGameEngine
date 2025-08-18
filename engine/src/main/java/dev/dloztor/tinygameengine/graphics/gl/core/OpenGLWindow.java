package dev.dloztor.tinygameengine.graphics.gl.core;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class OpenGLWindow extends dev.dloztor.tinygameengine.graphics.Window {

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

    private final String title;

    public OpenGLWindow(int width, int height, String title) {
        super(title);
        this.initialWidth = width;
        this.initialHeight = height;
        this.title = title;
    }


    public void run(){
        init();
        loop();
        freeResources();
    }

    @Override
    public void init(){
        windowHandle = new WindowInitializer().initializeWindow(title, initialWidth, initialHeight);
        setClearWindowColor();
        onInit();
    }


    protected void onInit() {/*default implementation ignored*/}



    private void loop(){
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.


        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(windowHandle)){
            updateFrame();
        }
    }
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