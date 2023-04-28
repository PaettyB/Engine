package de.paettyb.engine.ui;

import de.paettyb.engine.input.MouseManager;

import java.awt.*;

public class DragBox {
    
    protected int x, y;
    protected int width = 40, height = 20;
    protected Color color = Color.pink;
    protected boolean dragged = false;
    protected int borderWidth = 1;
    
    public DragBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        if (MouseManager.isDragging()) {
            if (dragged || mouseIntersect()) {
                dragged = true;
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
}
