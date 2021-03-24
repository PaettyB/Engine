package de.keygalp.engine;

public class Launcher {
	private static Engine engine;
	
	public static void main(String[] args) {
		engine = new Engine("Engine", 1280, 720);
		engine.start();
	}
}

