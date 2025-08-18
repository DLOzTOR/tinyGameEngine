package dev.dloztor.tinygameengine.core;


import dev.dloztor.tinygameengine.graphics.core.Renderer;

/*
 Scene management, game loop, and other core functionalities like window management, input handling, etc.
 */
public class Game {
    protected final Renderer renderer;

    public Game(Renderer renderer) {
        this.renderer = renderer;
    }
}
