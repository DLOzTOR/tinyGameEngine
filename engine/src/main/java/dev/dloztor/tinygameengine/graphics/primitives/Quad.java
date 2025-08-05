package dev.dloztor.tinygameengine.graphics.primitives;

import dev.dloztor.tinygameengine.graphics.core.VAO;
import dev.dloztor.tinygameengine.graphics.core.VBO;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
//TODO: Decompose transform
public abstract class Quad {
    private static final VAO QUAD_VAO;

    static {
        float[] meshData = {
                -.5f, .5f,
                .5f, .5f,
                .5f, .5f,
                -.5f, .5f,
        };
        FloatBuffer fb = FloatBuffer.allocate(8);
        fb.put(meshData);
        QUAD_VAO = new VAO();
        QUAD_VAO.addVBO(new VBO(fb, GL_VERTEX_ARRAY, GL_STATIC_DRAW), 2, GL_FLOAT, false, 0, 0);
    }

    private Vector2f position = new Vector2f();
    private Vector2f scale = new Vector2f();
    private Matrix4f transform = new Matrix4f();
    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
        calculateTransformMatrix();
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
        calculateTransformMatrix();
    }

    public abstract void draw();

    protected void bindVAO(){
        QUAD_VAO.bind();
    }

    private void calculateTransformMatrix(){
        Matrix4f transform = new Matrix4f();
        transform.set(0,0 , getScale().x);
        transform.set(1,1 , getScale().y);
        transform.set(4, 0, getPosition().x);
        transform.set(4, 1, getPosition().y);
        this.transform = transform;
    }
}