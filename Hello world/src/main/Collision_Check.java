	
	
				//Do zrobienia

package main;

import entity.Entity;


public class Collision_Check {

	Game_Window gw;
	
	public Collision_Check(Game_Window gw) {
		
		this.gw = gw;
		
	}

	
	//Case blokujące poruszanie się postaci w przypadku wejścia na dany tile
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.collider.x;
		int entityRightWorldX = entity.worldX + entity.collider.x + entity.collider.width;
		int entityTopWorldY = entity.worldY + entity.collider.y;
		int entityBottomWorldY = entity.worldY + entity.collider.y + entity.collider.height;
		
		int entityLeftCol = entityLeftWorldX/gw.tileSize;
		int entityRightCol = entityRightWorldX/gw.tileSize;
		int entityTopRow = entityTopWorldY/gw.tileSize;
		int entityBottomRow = entityBottomWorldY/gw.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gw.tileSize;
			tileNum1 = gw.tileM.map_Tile_Num[entityLeftCol][entityTopRow];
			tileNum2 = gw.tileM.map_Tile_Num[entityRightCol][entityTopRow];
			if(gw.tileM.enviroment[tileNum1].collision == true ||
					gw.tileM.enviroment[tileNum2].collision == true) {
				entity.collisioner = true;
				
			}
		break;
		case "down":
			break;
		case "right":
			break;
		case "left":
			break;
		}

	}
}
