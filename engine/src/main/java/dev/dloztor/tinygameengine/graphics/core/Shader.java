package dev.dloztor.tinygameengine.graphics.core;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL30.*;

public class Shader {
    private int id;

    public Shader(String vertex, String fragment){
        int vertId = compileShader(vertex, GL_VERTEX_SHADER);
        int fragId = compileShader(fragment, GL_FRAGMENT_SHADER);
        id = linkProgram(vertId, fragId);
    }

    public void bind() {
        glUseProgram(id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void delete() {
        glDeleteProgram(id);
        id = 0;
    }

    public void setUniform1f(String name, float value) {
        int location = glGetUniformLocation(id, name);
        if (location != -1) {
            glUniform1f(location, value);
        }
    }

    public void setUniform1i(String name, int value){
        int location = glGetUniformLocation(id, name);
        if(location!= -1){
            glUniform1i(location, value);
        }
    }

    public void setUniformVec2f(String name, Vector2f value){
        int location = glGetUniformLocation(id, name);
        if(location!= -1){
            glUniform2f(location, value.x, value.y);
        }
    }

    public void setUniformVec3f(String name, Vector3f value){
        int location = glGetUniformLocation(id, name);
        if(location!= -1){
            glUniform3f(location, value.x, value.y, value.z);
        }
    }

    public void setUniformMat4(String name, Matrix4f mat) {
        int location = glGetUniformLocation(id, name);
        if (location != -1) {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                FloatBuffer fb = stack.mallocFloat(16);
                mat.get(fb);
                glUniformMatrix4fv(location, false, fb);
            }
        }
    }

    private int compileShader(String source, int type){
        int id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE){
            String log = glGetShaderInfoLog(id);
            throw new RuntimeException("Shader compile error: " + log);
        }
        return id;
    }

    private int linkProgram(int vertId, int fragId){
        int id = glCreateProgram();
        glAttachShader(id, vertId);
        glAttachShader(id, fragId);
        glLinkProgram(id);
        if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE) {
            String log = glGetProgramInfoLog(id);
            throw new RuntimeException("Program link error: " + log);
        }
        glDetachShader(id, vertId);
        glDetachShader(id, fragId);
        glDeleteShader(vertId);
        glDeleteShader(fragId);
        return id;
    }
}
