package com.skat.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;
import com.skat.world.World;

public class Enemy extends Entity {

	private double speed = 0.8;
	public Enemy(double x, double y, int width, int height, Spritesheet sprite) {
		super(x, y, width, height, sprite);
		this.setMyImage(sprite.getSprite(7 * 16, 16, 16, 16));
	}
	
	public void Tick() {
		if(this.getX() < Game.player.getX() && World.isFree((int)(this.getX() + speed), this.getY())){
			this.setX(this.getX()+speed);
		} else if(this.getX() > Game.player.getX() && World.isFree((int)(this.getX() - speed), this.getY())) {
			this.setX(this.getX()-speed);
		}
		
		if(this.getY() > Game.player.getY() && World.isFree(this.getX(), (int)(this.getY() - speed))){
			setY(getY()-speed);
		} else if(this.getY() < Game.player.getY() && World.isFree(this.getX(), (int)(this.getY() + speed))) {
			setY(getY()+speed);
		}
	}
}
