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
	
	final int FPS = 60;
	
	Keyboard_Handler keyHandler = new Keyboard_Handler();

	Thread gameThread;
	
	
	//Player position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 5;
	
	
	public Game_Window() {
		this.setPreferredSize(new Dimension(max_Screen_Width, max_Screen_Height));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void Start_Game_Thread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {		
		
		double drawInterwal = 1000000000/FPS; //0,016 seconds
		double nextDrawTime = System.nanoTime() + drawInterwal;
		
		while (gameThread != null) {
			
			
			update();
			
			repaint();
			
			
			try {
				double remainingTime  = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; //Convert to miliseconds
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterwal;
				
			} catch (InterruptedException e) {
				
				
				e.printStackTrace();
			}
			
		}
	}
	
	public void update() {
		if(keyHandler.upPressed == true) {
			playerY -= playerSpeed;
		}
		
		if(keyHandler.downPressed == true) {
			playerY += playerSpeed;
		}
		
		if(keyHandler.leftPressed == true) {
			playerX -= playerSpeed;
		}
		
		if(keyHandler.rightPressed == true) {
			playerX += playerSpeed;
		}
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
		
	}

}
