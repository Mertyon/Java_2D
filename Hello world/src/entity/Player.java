package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game_Window;
import main.Keyboard_Handler;

public class Player extends Entity{
	
	Game_Window game_Window;
	Keyboard_Handler keyHandler;
	
	public final int screenX;
	public final int screenY;
	
	public Player(Game_Window game_Window, Keyboard_Handler keyHandler) {
		
		this.game_Window = game_Window;
		this.keyHandler = keyHandler;
		
		screenY = game_Window.max_Screen_Height/2 - (game_Window.tileSize/2);
		screenX = game_Window.max_Screen_Width/2 - (game_Window.tileSize/2);
		
		collider = new Rectangle();
		collider.x = 8;
		collider.y = 16;
		collider.width = 28;
		collider.height = 28;
		
		set_Default_Values();
		get_Player_Image();
	}
	
	public void set_Default_Values() {
		worldX = game_Window.tileSize * 23;
		worldY = game_Window.tileSize * 21;
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
		
		if(keyHandler.upPressed == true || keyHandler.downPressed == true || 	
				keyHandler.rightPressed == true || keyHandler.leftPressed == true) {
			
			if(keyHandler.upPressed == true) {
				direction = "up";
				worldY -= speed;
			}
			
			if(keyHandler.downPressed == true) {
				direction = "down";
				worldY += speed;
			}
			
			if(keyHandler.leftPressed == true) {
				direction = "left";
				worldX -= speed;
			}
			
			if(keyHandler.rightPressed == true) {
				direction = "right";
				worldX += speed;
			}
			
			//check for collision
			collisioner = false;
			game_Window.collisionC.checkTile(this);
			
			//if false play3er can move
		/*(collisioner == false) {
				switch(direction) {
				case "up"
				
				}
			}*/
			
			//sprite counter
			sprite_Counter++;
			
			if(sprite_Counter > 11) {
				if(sprite_Num == 1) {
					sprite_Num = 2;
				}
				else if (sprite_Num == 2) {
					sprite_Num = 1;

				}
				sprite_Counter = 0;
			}
		}
	}

	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if (sprite_Num == 1) {
				image = up1;
			}
			if (sprite_Num == 2) {
				image = up2;
			}
			break;
			
		case "down":
			if (sprite_Num == 1) {
				image = down1;
			}
			if (sprite_Num == 2) {
				image = down2;
			}
			break;
			
		case "right":
			if (sprite_Num == 1) {
				image = right1;
			}
			if (sprite_Num == 2) {
				image = right2;
			}
			break;
			
		case "left":
			if (sprite_Num == 1) {
				image = left1;
			}
			if (sprite_Num == 2) {
				image = left2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, game_Window.tileSize, game_Window.tileSize, null);
	}
}