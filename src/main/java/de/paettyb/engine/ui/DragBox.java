package de.paettyb.engine.ui;

import de.paettyb.engine.Engine;
import de.paettyb.engine.input.MouseManager;

import java.awt.*;

public class DragBox {
    
    protected int x, y;
    protected int width = 40, height = 20;
    protected Color color = Color.pink;
    protected boolean dragged = false;
    protected int borderWidth = 1;
    protected boolean fixedToMouse = false;
    private boolean alive = false;  // Used to ignore the first KeyPressed Event
    private boolean disableEvents = false;
    
    protected Runnable action = () -> {};
    
    int c = 0;
    
    public DragBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public DragBox(int x, int y, int width, int height, boolean fixedToMouse, Runnable action) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fixedToMouse = fixedToMouse;
        this.action = action;
    }
    
    public DragBox(int x, int y, int width, int height, Color color, boolean fixedToMouse, Runnable action) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fixedToMouse = fixedToMouse;
        this.action = action;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
        g.setColor(color);
        g.fillRect(x + borderWidth, y + borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);
    }
    
    protected boolean mouseIntersect() {
        return (MouseManager.mouseX >= x && MouseManager.mouseX <= x + width
                && MouseManager.mouseY >= y && MouseManager.mouseY <= y + height);
    }
    
    public void update() {
        disableEvents = false;
        if(!alive) alive = true;
        else if(MouseManager.keyWasReleased(1) && fixedToMouse) {
            fixedToMouse = false;
            disableEvents = true;
        }
        if(MouseManager.keyWasClicked(1) && !disableEvents && !fixedToMouse && mouseIntersect()){
            action.run();
        }
        if (MouseManager.isDragging() || fixedToMouse) {
            if(fixedToMouse || mouseIntersect()) dragged = true;
            if (dragged) {
                x = x + MouseManager.getMovement().x;
                y = y + MouseManager.getMovement().y;
            }
        } else {
            dragged = false;
        }
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getBorderWidth() {
        return borderWidth;
    }
    
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public Runnable getAction() {
        return action;
    }
    
    public void setAction(Runnable action) {
        this.action = action;
    }
}
