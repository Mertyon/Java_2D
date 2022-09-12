package entity;

import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int speed;
	public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
	public String direction;
	
	public int sprite_Counter = 0;
	public int sprite_Num = 1;
}