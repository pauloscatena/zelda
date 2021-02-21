package com.skat.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;

public abstract class Tile {
	private Spritesheet sprite;
	private int x,y;
	private BufferedImage myImage;
	
	public Tile(int x, int y, Spritesheet sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void setMyImage(BufferedImage img){
		this.myImage = img;
	}
	
	public void render(Graphics g) {
		g.drawImage(this.myImage,  x - Camera.x, y - Camera.y, 16, 16, null);
	}
}
