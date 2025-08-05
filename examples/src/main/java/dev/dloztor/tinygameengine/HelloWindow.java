package dev.dloztor.tinygameengine;

import dev.dloztor.tinygameengine.graphics.core.Window;

import static org.lwjgl.opengl.GL11.glClearColor;

public class HelloWindow
{
    public static void main( String[] args )
    {
        Window window = new MyWindow(600, 400, "HelloWindow");
        window.run();
    }
}

class MyWindow extends Window{
    public MyWindow(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    protected void setClearWindowColor(){
        glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
    }
}