package dev.dloztor.tinygameengine;

import dev.dloztor.tinygameengine.graphics.gl.core.OpenGLWindow;
import dev.dloztor.tinygameengine.graphics.gl.primitives.ColoredQuad;
import org.joml.Vector3f;
import org.slf4j.Logger;

public class LoggerExample {
    private static class MyWindow extends OpenGLWindow {
        private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoggerExample.class);
        ColoredQuad cq;

        public MyWindow(int width, int height, String title) {
            super(width, height, title);
        }

        @Override
        protected void onInit() {
            cq = new ColoredQuad(new Vector3f(1, 1, 0));
            LOGGER.info("Test log");
        }

        @Override
        protected void onUpdateFrame() {
            cq.draw();
        }
    }

    public static void main(String[] args) {
        new MyWindow(400, 400, "Logger showcase(See log folder)").run();
    }
}
