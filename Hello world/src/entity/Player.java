package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game_Window;
import main.Keyboard_Handler;

public class Player extends Entity{
	
	Game_Window game_Window;
	Keyboard_Handler keyHandler;
	
	public Player(Game_Window game_Window, Keyboard_Handler keyHandler) {
		
		this.game_Window = game_Window;
		this.keyHandler = keyHandler;
		
		set_Default_Values();
		get_Player_Image();
	}
	
	public void set_Default_Values() {
		x = 100;
		y = 100;
		speed = 5;
		direction = "down";
	}
	
	public void get_Player_Image() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/U1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/U2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/D1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/D2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/R1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/R2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/L1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/L2.png"));

			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyHandler.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		
		if(keyHandler.downPressed == true) {
			direction = "down";
			y += speed;
		}
		
		if(keyHandler.leftPressed == true) {
			direction = "left";
			x -= speed;
		}
		
		if(keyHandler.rightPressed == true) {
			direction = "right";
			x += speed;
		}
	}
	

	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, game_Window.tileSize, game_Window.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "right":
			image = right1;
			break;
		case "left":
			image = left1;
			break;
		}
		
		g2.drawImage(image, x, y, game_Window.tileSize, game_Window.tileSize, null);
		
	}
}