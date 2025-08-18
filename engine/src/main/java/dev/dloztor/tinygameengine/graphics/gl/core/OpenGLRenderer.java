package dev.dloztor.tinygameengine.graphics.gl.core;

import dev.dloztor.tinygameengine.core.scene.Scene;
import dev.dloztor.tinygameengine.graphics.core.Renderer;
import dev.dloztor.tinygameengine.graphics.core.Window;
import dev.dloztor.tinygameengine.graphics.gl.core.window.OpenGLWindow;

public class OpenGLRenderer extends Renderer {
    private Window window;

    @Override
    public void init(int windowWidth, int windowHeight, String title) {
        window = new OpenGLWindow(windowWidth, windowHeight, title);
    }

    @Override
    public void render(Scene scene) {

    }

    @Override
    public boolean shouldClose() {
        return false;
    }

    @Override
    public void dispose() {

    }
}
