package dev.dloztor.tinygameengine.resources.loaders;

import dev.dloztor.tinygameengine.graphics.core.Shader;
import dev.dloztor.tinygameengine.resources.descriptors.ShaderDescriptor;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
//TODO: Add hashtable for loaded shaders to avoid loading the same shader multiple times
public class ShaderLoader implements ResourceLoader{
    public Shader load(String vertexPath, String fragmentPath){
        return load(new ShaderDescriptor(vertexPath, fragmentPath));
    }

    public Shader load(ShaderDescriptor descriptor) {
        return new Shader(loadSource(descriptor.vertexShaderPath()), loadSource(descriptor.fragmentShaderPath()));
    }

    private String loadSource(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = loader.getResourceAsStream(path);
             Scanner scanner = new Scanner(input, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load shader: " + path, e);
        }
    }
}
