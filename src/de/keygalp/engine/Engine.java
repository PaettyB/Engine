package de.keygalp.engine;

import de.keygalp.engine.display.Display;
import de.keygalp.engine.input.KeyManager;

import java.awt.*;
import java.awt.image.BufferStrategy;


public abstract class Engine {
    
    private Display display;
    private Graphics g;
    private BufferStrategy bs;
    
    private Thread run;
    private boolean running = false;
    
    public Engine(String name, int width, int height) {
        display = new Display(name, width, height);
        display.getCanvas().addKeyListener(new KeyManager(this));
    }
    
    private void renderSetup() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, Display.WIDTH, Display.HEIGHT);
        // draw
        render(g);
        // end draw
        g.dispose();
        bs.show();
    }
	
	public abstract void tick();
    
    public abstract void render(Graphics g);
    
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
                        renderSetup();
                        ticks++;
                        delta--;
                    }
                    
                    if (timer >= 1000000000) {
                        display.setTitle(Display.NAME + " | FPS: " + ticks);
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
    }
}
