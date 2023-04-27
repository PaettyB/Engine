package de.paettyb.engine.display;

import java.awt.*;

import javax.swing.JFrame;

public class Display extends JFrame{
	
	
	public static String NAME;
	public static int WIDTH, HEIGHT;
	private Canvas canvas;

	public Display(String name, int width, int height) {
		NAME = name;
		HEIGHT = height;
		WIDTH = width;
		createDisplay();
		showOnScreen(0, 100, 100);
	}

	private void createDisplay() {
		setTitle(NAME);
		setSize(WIDTH, HEIGHT);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		canvas = new Canvas();
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(canvas);
		pack();
		setVisible(true);
		canvas.requestFocus();
	}
	private void showOnScreen(int screen, int x, int y) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		if (screen > -1 && screen < gd.length) {
			setLocation(gd[screen].getDefaultConfiguration().getBounds().x + x, gd[screen].getDefaultConfiguration().getBounds().y + y);
		} else if (gd.length > 0) {
			setLocation(gd[0].getDefaultConfiguration().getBounds().x + x, gd[0].getDefaultConfiguration().getBounds().y + y);
		} else {
			throw new RuntimeException("No Screens Found");
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
}
