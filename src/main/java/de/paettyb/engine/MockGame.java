package de.paettyb.engine;

import de.paettyb.engine.ui.CanvasButton;

import java.awt.*;

public class MockGame extends Engine{
    
    CanvasButton button = new CanvasButton("Button", 50,50, () -> System.out.println("Test"));
    
    public MockGame(String name, int width, int height) {
        super(name, width, height);
    }
    
    @Override
    public void tick() {
        button.update();
    }
    
    @Override
    public void render(Graphics g) {
        button.render(g);
    }
    
    public static void main(String[] args) {
        new MockGame("Engine", 400,400).start();
    }
}
