package de.paettyb.engine;

import de.paettyb.engine.display.Display;
import de.paettyb.engine.input.KeyManager;
import de.paettyb.engine.input.MouseManager;

import java.awt.*;
import java.awt.image.BufferStrategy;


public abstract class Engine {
    
    protected Display display;
    protected Graphics g;
    protected BufferStrategy bs;
    
    protected MouseManager mouseManager;
    
    protected Thread run;
    protected boolean running = false;
    
    public Engine(String name, int width, int height) {
        display = new Display(name, width, height);
        mouseManager = new MouseManager();
        display.getCanvas().addKeyListener(new KeyManager(this));
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
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
    
    @SuppressWarnings("unused")
    public synchronized void start() {
        if (running)
            return;
        running = true;
        run = new Thread("MainEngineThread") {
            public void run() {
                
                long lastTime = System.nanoTime();
                int fps = 60;
                double timePerTick = 1000000000 / (double)fps;
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
                        mouseManager.update();
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
