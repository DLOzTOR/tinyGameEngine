package dev.dloztor.tinygameengine.graphics;


import dev.dloztor.tinygameengine.resources.Disposable;

public abstract class Window implements Disposable {

    protected boolean shouldClose = false;

    protected final String title;

    protected Window(String title) {
        this.title = title;
    }

    public abstract void init();

    public abstract void updateFrame();

    public boolean shouldClose() {
        return shouldClose;
    }
}