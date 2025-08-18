package dev.dloztor.tinygameengine.graphics.core;

import dev.dloztor.tinygameengine.graphics.gl.core.OpenGLRenderer;

public enum Renderers {
    OPENGL("OpenGL") {
        @Override
        public Renderer createRenderer() {
            return new OpenGLRenderer();
        }
    },
    VULKAN("Vulkan") {
        @Override
        public Renderer createRenderer() {
            throw new RuntimeException("Vulkan renderer is not implemented yet.");
        }
    };

    private final String name;

    Renderers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Renderers fromString(String name) {
        for (Renderers renderer : Renderers.values()) {
            if (renderer.name.equalsIgnoreCase(name)) {
                return renderer;
            }
        }
        throw new IllegalArgumentException("No renderer found with name: " + name);
    }

    public abstract Renderer createRenderer();
}
