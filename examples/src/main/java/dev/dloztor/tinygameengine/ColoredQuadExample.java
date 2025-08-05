package dev.dloztor.tinygameengine;

import dev.dloztor.tinygameengine.core.Window;
import dev.dloztor.tinygameengine.graphics.primitives.ColoredQuad;
import org.joml.Vector3f;

public class ColoredQuadExample {
    private static class MyWindow extends Window {
        ColoredQuad cq;
        public MyWindow(int width, int height, String title) {
            super(width, height, title);
        }
        @Override
        protected void onInit(){
            cq = new ColoredQuad(new Vector3f(1, 1, 0));
        }
        @Override
        protected void onUpdateFrame(){
            cq.draw();
        }
    }

    public static void main(String[] args) {
        new MyWindow(400, 400, "Colored quad showcase").run();
    }
}