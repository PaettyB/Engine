package de.paettyb.engine;

import de.paettyb.engine.ui.CanvasButton;
import de.paettyb.engine.ui.DragBox;

import java.awt.*;

public class MockGame extends Engine {
    
    CanvasButton button = new CanvasButton("Button", 50, 50, () -> System.out.println("Test"));
    DragBox dragBox = new DragBox(100, 100, 50, 25);
    
    public MockGame(String name, int width, int height) {
        super(name, width, height);
    }
    
    @Override
    public void tick() {
        button.update();
        dragBox.update();
    }
    
    @Override
    public void render(Graphics g) {
        button.render(g);
        dragBox.render(g);
    }
    
    public static void main(String[] args) {
        new MockGame("Engine", 400, 400).start();
    }
}
