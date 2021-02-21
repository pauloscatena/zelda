package com.skat.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;
import com.skat.world.Camera;
import com.skat.world.World;

public class Player extends Entity {

	public boolean right, left, up, down;
	private double speed;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private boolean isMoving;
	private int frame, maxFrames;
	private int spriteIndex, maxSpriteIndex;
	private int direction, last_direction;
	
	public Player(int x, int y, int width, int height, Spritesheet sprite) {
		super(x, y, width, height, sprite);
		this.speed = 1;
		this.maxFrames = 5;
		this.maxSpriteIndex = 3;
		
		this.rightPlayer = new BufferedImage[maxSpriteIndex + 1];
		this.leftPlayer = new BufferedImage[maxSpriteIndex + 1];
		
		for(int i = 0; i < maxSpriteIndex + 1; i++) {
			rightPlayer[i] = sprite.getSprite(32 + (i * width), 0, width, height);
			leftPlayer[i] = sprite.getSprite(32 + (i * width), 16, width, height);
		}		
	}
	
	public void Tick() {
		this.isMoving = false;
		if(right) {
			setX(getX() + speed);
			this.isMoving = true;
			this.last_direction = 1;
		} else if(left) {
			setX(getX() - speed);
			this.isMoving = true;
			this.last_direction = -1;
		}
		
		if(up) {
			setY(getY() - speed);
			this.isMoving = true;
		} else if(down) {
			setY(getY() + speed);
			this.isMoving = true;
		}
		
		if(this.isMoving)
		{
			this.direction = last_direction;
			this.frame++;
			if(this.frame >= this.maxFrames) {
				this.frame = 0;
				this.spriteIndex++;
			}			
		}
		this.checkMaxSprite();
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH *16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT * 16- Game.HEIGHT);
	}
	
	private void checkMaxSprite() {
		
		if(this.spriteIndex > this.maxSpriteIndex) {
			this.spriteIndex = 0;
		}
	}

	public void Render(Graphics g) {
		if(direction >= 0 || (this.isMoving && this.right)) {
			g.drawImage(this.rightPlayer[this.spriteIndex], this.getXCamera(), this.getYCamera(), this.getWidth(), this.getHeight(), null);	
		}
		else if(direction < 0 || (this.isMoving && this.left)) {
			g.drawImage(this.leftPlayer[this.spriteIndex], this.getXCamera(), this.getYCamera(), this.getWidth(), this.getHeight(), null);
		}		
	}

}
