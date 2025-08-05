package dev.dloztor.tinygameengine.graphics.primitives;

import dev.dloztor.tinygameengine.graphics.core.Shader;
import dev.dloztor.tinygameengine.resources.ShaderLoader;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class ColoredQuad extends Quad{
    private static Shader shader = new ShaderLoader().loadShader("shaders/default/coloredquad/ColoredQuad.vert",
            "shaders/default/coloredquad/ColoredQuad.frag");
    private Vector3f color;

    public ColoredQuad(Vector3f color) {
        this.color = color;
    }

    @Override
    public void draw() {
        bindVAO();
        shader.bind();
        shader.setUniformVec3f("vertexColor", color);
        glDrawArrays(GL_QUADS, 0, 4);
        glBindVertexArray(0);
        shader.unbind();
    }
}
