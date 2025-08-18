package dev.dloztor.tinygameengine.resources;

import dev.dloztor.tinygameengine.resources.loaders.ShaderLoader;

public class ResourceManager {
    private final ShaderLoader shaderLoader;

    public ResourceManager(ShaderLoader shaderLoader) {
        this.shaderLoader = shaderLoader;
    }

    public ShaderLoader getShaderLoader() {
        return shaderLoader;
    }
}
