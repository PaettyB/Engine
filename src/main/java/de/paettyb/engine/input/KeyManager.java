package de.paettyb.engine.input;

import de.paettyb.engine.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    
    private static final boolean[] keys = new boolean[1024];
    private static final boolean[] cooldowns = new boolean[1024];
    
    private final Engine engine;
    
    public KeyManager(Engine engine){
    	this.engine = engine;
	}
    
    @Override
    public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			engine.stopGame();
			return;
		}
        keys[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
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
}
