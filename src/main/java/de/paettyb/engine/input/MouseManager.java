package de.paettyb.engine.input;

import de.paettyb.engine.utils.Vec2i;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
    
    private static final boolean[] keys = new boolean[32];
    private static final boolean[] cooldowns = new boolean[32];
    
    private static boolean dragging = false;
    
    private int prevX = 0, prevY = 0;
    public static int mouseX = 0, mouseY = 0;
    private static Vec2i movement = new Vec2i();
    
    public MouseManager() {
    }
    
    public void update() {
        movement.x = mouseX - prevX;
        movement.y = mouseY - prevY;
        prevX = mouseX;
        prevY = mouseY;
    }
    
    public static Vec2i getMovement() {
        return movement;
    }
    
    public static boolean isDragging() {
        return dragging;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        keys[e.getButton()] = true;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        keys[e.getButton()] = false;
        dragging = false;
    }
    
    public static boolean isKeyDown(int keyCode) {
        return keys[keyCode];
    }
    
    public static boolean keyWasPressed(int keyCode) {
        if (keys[keyCode]) {
            if (!cooldowns[keyCode]) {
                cooldowns[keyCode] = true;
                return true;
            } else {
                return false;
            }
        }
        cooldowns[keyCode] = false;
        return false;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        dragging = true;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
