package dev.dloztor.tinygameengine.resources;

import dev.dloztor.tinygameengine.graphics.core.Shader;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ShaderLoader {
    public Shader loadShader(String vertexPath, String fragmentPath){
        return new Shader(loadSource(vertexPath), loadSource(fragmentPath));
    }

    private String loadSource(String path) {
        try (InputStream input = getClass().getResourceAsStream(path);
             Scanner scanner = new Scanner(input, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load shader: " + path, e);
        }
    }
}
