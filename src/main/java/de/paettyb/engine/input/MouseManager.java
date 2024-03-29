package de.paettyb.engine.input;

import de.paettyb.engine.utils.Vec2i;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

public class MouseManager implements MouseListener, MouseMotionListener {
    
    private static final int NUM_KEYS = 5;
    
    private static final boolean[] keys = new boolean[NUM_KEYS];
    private static final boolean[] keysPressed = new boolean[NUM_KEYS];
    private static final boolean[] lastFrameKeys = new boolean[NUM_KEYS];
    private static final boolean[] cooldowns = new boolean[NUM_KEYS];
    private static final boolean[] clicked = new boolean[NUM_KEYS];
    private static final boolean[] resetMask = new boolean[NUM_KEYS];
    
    private static boolean dragging = false;
    
    private int prevX = 0, prevY = 0;
    public static int mouseX = 0, mouseY = 0;
    private static final Vec2i movement = new Vec2i();
    
    public MouseManager() {
    
    }
    
    public void update() {
        movement.x = mouseX - prevX;
        movement.y = mouseY - prevY;
        prevX = mouseX;
        prevY = mouseY;
        for (int i = 0; i < NUM_KEYS; i++){
            keysPressed[i] = updateKeyPressed(i);
        }
    
        
        
        for(int i = 0; i < NUM_KEYS; i++) {
            if(clicked[i])
                resetMask[i] = true;
        }
    }
    
    public void cleanup() {
        for(int i = 0; i < NUM_KEYS; i++) {
            if(resetMask[i])
                clicked[i] = false;
        }
        Arrays.fill(resetMask, false);
        for (int i = 0; i < NUM_KEYS; i++){
            lastFrameKeys[i] = keys[i];
        }
    }
    
    
    public static Vec2i getMovement() {
        return movement;
    }
    
    public static boolean isDragging() {
        return dragging;
    }
    
    public static void setDragging(boolean dragging) {
        MouseManager.dragging = dragging;
    }
    
    public static boolean keyWasPressed(int  keyCode) {
        return keysPressed[keyCode];
    }
    
    public static boolean keyWasReleased(int keyCode) {
//        System.out.println(lastFrameKeys[keyCode] + "," + keys[keyCode]);
        return (lastFrameKeys[keyCode] && !keys[keyCode]);
    }
    
    public static boolean keyWasClicked(int  keyCode) {
        return clicked[keyCode];
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        clicked[e.getButton()] = true;
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
    
    private boolean updateKeyPressed(int keyCode) {
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
