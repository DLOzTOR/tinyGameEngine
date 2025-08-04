package dev.dloztor.tinygameengine.graphics.core;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

public class VBO {
    private int id;

    private int target;

    public VBO(Buffer data, int target, int usage){
        id = glGenBuffers();
        this.target = target;
        loadData(data, usage);
    }

    public void bind(){
        glBindBuffer(target, id);
    }

    public void unbind(){
        glBindBuffer(target, 0);
    }

    private void loadData(Buffer data, int usage){
        bind();
        if(data instanceof FloatBuffer){
            glBufferData(target, (FloatBuffer) data, usage) ;
        }
        else if(data instanceof IntBuffer){
            glBufferData(target, (IntBuffer) data, usage) ;
        }
        else if(data instanceof ByteBuffer){
            glBufferData(target, (ByteBuffer) data, usage);
        }
        else {
            throw new RuntimeException("Unsupported buffer type");
        }
        unbind();
    }

    public void delete(){
        glDeleteBuffers(id);
        id = 0;
    }
}
