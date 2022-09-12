package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Game_Window extends JPanel implements Runnable{
	final int basic_Tile_Size = 16;
	final int in_Game_Scale = 3;
	
	final int tileSize = basic_Tile_Size * in_Game_Scale;
	
	final int max_Screen_Column = 16;
	final int max_Screen_Row = 12;
	
	final int max_Screen_Width = tileSize * max_Screen_Column;
	final int max_Screen_Height = tileSize * max_Screen_Row;
	
	Thread gameThread;
	
	
	
	public Game_Window() {
		this.setPreferredSize(new Dimension(max_Screen_Width, max_Screen_Height));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

	public void Start_Game_Thread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		while (gameThread != null) {
			
		}
	}
	
	public void update() {
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(100, 100, tileSize, tileSize);
		
		g2.dispose();
		
	}

}
