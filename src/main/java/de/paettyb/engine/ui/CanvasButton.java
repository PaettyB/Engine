package de.paettyb.engine.ui;

import de.paettyb.engine.input.MouseManager;

import java.awt.*;

public class CanvasButton {
    
    private int x, y;
    private int width = 40, height = 20;
    private String label;
    private Color color = Color.CYAN;
    private Font font;
    private int padding = 10;
    private int labelWidth = 0, labelHeight = 0;
    private final Runnable callback;
    
    public CanvasButton(String label, int x, int y, Runnable callback) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.callback = callback;
        font = new Font("Arial", Font.PLAIN, 20);
    }
    
    public void render(Graphics g) {
        g.setFont(font);
        if (labelWidth == 0) {
            labelWidth = g.getFontMetrics().stringWidth(label);
            labelHeight = g.getFontMetrics().getAscent();
            width = padding * 2 + labelWidth;
            height = padding * 2 + labelHeight;
        }
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        g.setColor(Color.BLACK);
        g.drawString(label, x + padding, y + labelHeight + padding);
    }
    
    public boolean update() {
        if (MouseManager.keyWasPressed(1)) {
            if (MouseManager.mouseX >= x && MouseManager.mouseX <= x + width
                    && MouseManager.mouseY >= y && MouseManager.mouseY <= y + height) {
                try {
                    callback.run();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
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
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
        labelWidth = 0;
        labelHeight = 0;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Font getFont() {
        return font;
    }
    
    public void setFont(Font font) {
        this.font = font;
        labelWidth = 0;
        labelHeight = 0;
    }
    
    public int getPadding() {
        return padding;
    }
    
    public void setPadding(int padding) {
        this.padding = padding;
        labelWidth = 0;
        labelHeight = 0;
    }
}
