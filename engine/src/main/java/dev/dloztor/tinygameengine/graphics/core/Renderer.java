package dev.dloztor.tinygameengine.graphics.core;

import dev.dloztor.tinygameengine.core.scene.Scene;
import dev.dloztor.tinygameengine.resources.Disposable;

public abstract class Renderer implements Disposable {
    public abstract void init(int windowWidth, int windowHeight, String title);
    public abstract void render(Scene scene);

    public abstract boolean shouldClose();
}
