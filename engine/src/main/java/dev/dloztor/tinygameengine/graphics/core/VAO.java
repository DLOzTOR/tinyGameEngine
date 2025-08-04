package dev.dloztor.tinygameengine.graphics.core;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class VAO {
    private int id;

    private final List<VBO> vbos = new ArrayList<>();

    public VAO(int id) {
        this.id = glGenVertexArrays();
    }

    public void bind(){
        glBindVertexArray(id);
    }

    public void unbind(){
        glBindVertexArray(0);
    }

    public void addVBO(VBO vbo, int size, int type, boolean normalized, int stride, long offset){
        bind();
        vbo.bind();
        int index = vbos.size();
        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, size, type, normalized, stride, offset);
        vbo.unbind();
        unbind();

        vbos.add(vbo);
    }

    public void delete(){
        for(VBO vbo: vbos){
            vbo.delete();
        }
        glDeleteVertexArrays(id);
        id = 0;
    }
}
