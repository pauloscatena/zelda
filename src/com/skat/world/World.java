package com.skat.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.skat.entities.Ammo;
import com.skat.entities.Enemy;
import com.skat.entities.Life;
import com.skat.entities.Weapon;
import com.skat.main.Game;

public class World {
	private final int ENEMY = 0xFFFF0000;
	private final int WALL = 0xFFFFFFFF;
	private final int PLAYER = 0xFF0026FF;
    private final int AMMO = 0xFFFFD800;
    private final int LIFE = 0XFFFF006E;
    private final int GRASS = 0xFF000000;
    private final int WEAPON = 0XFF7F0000;
    private static Tile[] tiles;
    public static int WIDTH, HEIGHT;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			
			map.getRGB(0,  0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			initializeMap(pixels, map);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initializeMap(int[] pixels, BufferedImage map) {
		for(int x = 0; x < WIDTH; x++)  {
			for(int y = 0; y < HEIGHT; y++) {
				int position = x + (y * WIDTH);
				int item = pixels[position];				
				tiles[position] = new FloorTile(x * 16,y * 16, Game.spritesheet);
				
				if(item == GRASS) {
					tiles[position] = new FloorTile(x * 16,y * 16, Game.spritesheet);
				}else if(item == WALL) {
					tiles[position] = new WallTile(x * 16, y * 16, Game.spritesheet);					
				}else if (item == PLAYER) {
					Game.player.setX(x * 16);
					Game.player.setY(y * 16);
				} else if (item == ENEMY) {
					Enemy e = new Enemy(x * 16, y * 16, 16,16, Game.spritesheet);
					Game.entities.add(e);
					Game.enemies.add(e);
				} else if (item == WEAPON) {
					Game.entities.add(new Weapon(x * 16, y * 16, 16,16, Game.spritesheet));
				} else if (item == LIFE) {
					Game.entities.add(new Life(x * 16, y * 16, 16,16, Game.spritesheet));
				} else if (item == AMMO) {
					Game.entities.add(new Ammo(x * 16, y * 16, 16,16, Game.spritesheet));
				}				
			}
			
		}
	}
	
	public void Render(Graphics g) {
		int xStart = Camera.x >> 4;
		int yStart = Camera.y >> 4;
		int xFinal = xStart + (Game.WIDTH >> 4) + 1;
		int yFinal = yStart + (Game.HEIGHT >> 4) + 1;
		for(int x = xStart; x < xFinal; x++) {
			for(int y = yStart; y < yFinal; y++) {
				if(x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[x + (y * WIDTH)];
				tile.render(g);
			}
		}
	}	
	
	public static boolean isFree(double xpNext, double ypNext) {
		int xNext = (int)xpNext;
		int yNext = (int)ypNext;
		
		int x1 = xNext / 16;
		int y1 = yNext / 16;		
		
		int x2 = (xNext + 15) / 16;
		int y2 = yNext / 16;

		int x3 = xNext / 16;
		int y3 = (yNext + 15) / 16;		
		
		int x4 = (xNext + 15) / 16;
		int y4 = (yNext + 15) / 16;
		
		return  !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
	}
}
