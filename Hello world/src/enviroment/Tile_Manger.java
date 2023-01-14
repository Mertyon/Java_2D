package enviroment;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Game_Window;

public class Tile_Manger {

	Game_Window gw;
	public Enviroment[] enviroment;
	public int map_Tile_Num[][];	
	
	public Tile_Manger(Game_Window gw){
		
		this.gw = gw;
		
		enviroment = new Enviroment[10];
		map_Tile_Num = new int [gw.max_World_Col][gw.max_World_Row];
		
		get_Tile_Image();
		loadMap("/maps/mapV2.txt");
	}
	
	
	public void get_Tile_Image(){
	
		try {
			
			enviroment[0] = new Enviroment();
			enviroment[0].image = ImageIO.read(getClass().getResourceAsStream("/enviroment/Grass.png"));
			
			enviroment[1] = new Enviroment();
			enviroment[1].image = ImageIO.read(getClass().getResourceAsStream("/enviroment/Bricks.png"));
			
			enviroment[2] = new Enviroment();
			enviroment[2].image = ImageIO.read(getClass().getResourceAsStream("/enviroment/Water.png"));
			
			enviroment[3] = new Enviroment();
			enviroment[3].image = ImageIO.read(getClass().getResourceAsStream("/enviroment/Gravel.png"));
			
			enviroment[4] = new Enviroment();
			enviroment[4].image = ImageIO.read(getClass().getResourceAsStream("/enviroment/Tree.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String file_Path) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(file_Path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gw.max_World_Col && row < gw.max_World_Row) {
				
				String line = br.readLine(); 
				
				while(col < gw.max_World_Col) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					map_Tile_Num[col][row] = num;
					col++;
				}
				if(col == gw.max_World_Col) {
					col = 0;
					row++;
					}
				}
			br.close();
		}catch(Exception e) {
		}
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gw.max_Screen_Column && worldRow < gw.max_Screen_Row) {
			
			int tile_Num = map_Tile_Num[worldCol][worldRow];
			
			int worldX = worldCol * gw.tileSize;
			int worldY = worldRow * gw.tileSize;
			int screenX = worldX - gw.player.worldX + gw.player.screenX;
			int screenY = worldY - gw.player.worldY + gw.player.screenY;

			if(worldX + gw.tileSize > gw.player.worldX - gw.player.screenX &&
			   worldX - gw.tileSize < gw.player.worldX + gw.player.screenX &&
			   worldY + gw.tileSize > gw.player.worldY - gw.player.screenY &&
			   worldY - gw.tileSize < gw.player.worldY + gw.player.screenY) {
							g2.drawImage(enviroment[tile_Num].image, screenX, screenY, gw.tileSize, gw.tileSize, null);
			}
			
			worldCol++;
			
			if(worldCol == gw.max_Screen_Column) {
				
				worldCol = 0;
				worldRow++;				
			}
		}
	}
}
