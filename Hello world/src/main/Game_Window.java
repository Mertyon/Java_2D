package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import enviroment.Tile_Manger;


//Game Settings
public class Game_Window extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	final int basic_Tile_Size = 16;
	final int in_Game_Scale = 3;
	
	public final int tileSize = basic_Tile_Size * in_Game_Scale;
	public final int max_Screen_Column = 22;
	public final int max_Screen_Row = 16;
	public final int max_Screen_Width = tileSize * max_Screen_Column;
	public final int max_Screen_Height = tileSize * max_Screen_Row;
	
	public final int max_World_Col = 100;
	public final int max_World_Row = 100;
	public final int world_Width = tileSize * max_World_Col;
	public final int world_Height = tileSize * max_World_Row;
	
	final int FPS = 60;
	
	
	//Składowe
	Tile_Manger tileM = new Tile_Manger(this);
	Keyboard_Handler keyHandler = new Keyboard_Handler();
	Thread gameThread;
	public Collision_Check collisionC = new Collision_Check(this);
	public Player player = new Player(this, keyHandler);
	
	
	//Okno gry
	public Game_Window() {
		this.setPreferredSize(new Dimension(max_Screen_Width, max_Screen_Height));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	//Game start
	public void Start_Game_Thread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	//FPS interwal na delcie  (bez znaczenia)
	public void run() {
		
		double drawInterwal = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {
			currentTime =System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterwal;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	//Uppdate
	public void update() {
		player.update();
	}

	
	//Draw game
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}
