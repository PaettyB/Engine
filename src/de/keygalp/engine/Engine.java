package de.keygalp.engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import de.keygalp.engine.display.Display;
import de.keygalp.engine.input.KeyManager;


public class Engine {
	
	private String name;
	private int width;
	private int height;

	private Display display;
	private Graphics g;
	private BufferStrategy bs;

	private Thread run;
	private boolean running = false;
	
	public Engine(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;

		init();
	}
	
	private void init() {
		display = new Display(name, width, height);
		display.getFrame().addKeyListener(new KeyManager());
	}
	
	private void tick() {
		
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);

		// draw
		// end draw

		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		if (running)
			return;
		running = true;
		run = new Thread("MainEngineThread") {
			public void run() {

				long lastTime = System.nanoTime();
				int fps = 60;
				double timePerTick = 1000000000 / fps;
				double delta = 0;
				long now;
				long timer = 0;
				long ticks = 0;

				while (running) {
					now = System.nanoTime();
					delta += (now - lastTime) / timePerTick;
					timer += now - lastTime;
					lastTime = now;
					if (delta > 1) {
						tick();
						render();
						ticks++;
						delta--;
					}
					
					if(timer >= 1000000000) {
						display.getFrame().setTitle(name+ " | FPS: "+ticks);
						ticks = 0;
						timer = 0;
					}
				}
				
				stopGame();
				System.exit(0);
			}
		};
		run.start();
	}
	
	public synchronized void stopGame() {
		if (!running)
			return;
		running = false;
		try {
			run.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
