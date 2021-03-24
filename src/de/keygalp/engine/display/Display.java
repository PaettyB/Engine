package de.keygalp.engine.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	
	private String name;
	private int height;
	private int width;

	private JFrame frame;
	private Canvas canvas;
	//private Game game;

	public Display(String name, int width, int height) {
		this.name = name;
		this.height = height;
		this.width = width;
		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame();
		frame.setTitle(name);
		frame.setSize(width, height);
		
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.requestFocus();
		frame.setVisible(true);

		
		canvas = new Canvas();
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setPreferredSize(new Dimension(width, height));
		frame.add(canvas);
		frame.pack();
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	
	
}
