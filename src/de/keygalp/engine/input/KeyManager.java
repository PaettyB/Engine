package de.keygalp.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	public static boolean[] keys = new boolean[1024];
	public static boolean[] cooldowns = new boolean[65536];

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	public static boolean isKeyDown(int keyCode){
		return keys[keyCode];
	}
	
	public static boolean keyWasPressed(int keyCode){
		if(keys[keyCode]){
			if (!cooldowns[keyCode]) {
				cooldowns[keyCode] = true;
				return true;
			} else {
				return false;
			}
		}
		cooldowns[keyCode]  =false;
		return false;
	}
}
