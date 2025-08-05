package dev.dloztor.tinygameengine.graphics.primitives;

import dev.dloztor.tinygameengine.graphics.core.Shader;
import dev.dloztor.tinygameengine.resources.ShaderLoader;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class ColoredQuad extends Quad{
    private static Shader shader;
    private Vector3f color;

    public ColoredQuad(Vector3f color) {
        super();
        if(shader == null){
            shader = new ShaderLoader().loadShader("shaders/default/coloredquad/ColoredQuad.vert",
                    "shaders/default/coloredquad/ColoredQuad.frag");
        }
        this.color = color;
    }

    @Override
    public void draw() {
        bindVAO();
        shader.bind();
        shader.setUniformVec3f("vertexColor", color);
        glDrawArrays(GL_TRIANGLES, 0, 6);
        glBindVertexArray(0);
        shader.unbind();
    }
}
