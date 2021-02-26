package com.skat.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;
import com.skat.world.World;

public class Enemy extends Entity {

	private double speed = 0.6;
	private int frame = 0, maxFrames = 15, spriteIndex = 0, maxSpriteIndex = 1;
	private BufferedImage[] sprites;
	
	public Enemy(double x, double y, int width, int height, Spritesheet sprite) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[2];  
	    sprites[0] = sprite.getSprite(7 * 16, 16, 16, 16);
	    sprites[1] = sprite.getSprite(8 * 16, 16, 16, 16);		
	}
	
	public void Tick() {
		//if(Game.rand.nextInt(100) < 30)
		//{
		if(!this.isColidingPlayer()) {
			if(this.getX() < Game.player.getX() 
					&& World.isFree((int)(this.getX() + speed), this.getY())
					&& !isColiding((this.getX() + speed), this.getY())){
				this.setX(this.getX()+speed);
			} else if(this.getX() > Game.player.getX() 
					&& World.isFree((int)(this.getX() - speed), this.getY())
					&& !isColiding(this.getX() - speed, this.getY())) {
				this.setX(this.getX()-speed);
			}
			
			if(this.getY() > Game.player.getY() 
					&& World.isFree(this.getX(), (int)(this.getY() - speed))
					&& !isColiding(this.getX(), this.getY() - speed)){
				setY(getY()-speed);
			} else if(this.getY() < Game.player.getY() 
					&& World.isFree(this.getX(), (int)(this.getY() + speed))
					&& !isColiding(this.getX(), this.getY() + speed)) {
				setY(getY()+speed);
			}
		}
		else
		{
			if(Game.rand.nextInt(100) < 10) {
				Game.player.LoseLife(1);
				if(Game.player.life <= 0) {
					// Game over
				}
				System.out.println(Game.player.life);
			}
		}
		//}
			
		this.frame++;
		if(this.frame >= this.maxFrames) {
			this.frame = 0;
			this.spriteIndex++;
		}
		
		checkMaxSprite();
	}
	
	private void checkMaxSprite() {
		
		if(this.spriteIndex > this.maxSpriteIndex) {
			this.spriteIndex = 0;
		}
	}
	
	public boolean isColidingPlayer() {
		Rectangle enemyCurrent = new Rectangle((int)this.getX(), (int)this.getY(), 16, 16);
		Rectangle player = new Rectangle((int)Game.player.getX(), (int)Game.player.getY(), 16, 16);
		
		return enemyCurrent.intersects(player);
	}
	
	public boolean isColiding(double xNext, double yNext) {
		Rectangle enemyCurrent = new Rectangle((int)xNext, (int)yNext, 16, 16);
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e != this) {
				Rectangle targetEnemy = new Rectangle((int)e.getX(), (int)e.getY(), 16, 16);
				if(enemyCurrent.intersects(targetEnemy)) {
					return true;
				}
			}				
		}	
		return false;
	}
	
	public void Render(Graphics g) {
		g.drawImage(sprites[spriteIndex], getXCamera(), getYCamera(), null);
	}
}
