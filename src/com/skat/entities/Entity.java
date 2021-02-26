package com.skat.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;
import com.skat.world.Camera;

public abstract class Entity {

	private double x;
	private double y;
	private int width;
	private int height;	
	private Spritesheet sprite;
	private BufferedImage myImage;
	private int maskx, masky, mwidth, mheight;
	
	public Entity(double x, double y, int width, int height, Spritesheet sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.setMask(width/4, height/2, width/2, height/2);
	}
	
	public void setMask(int x, int y, int width, int height) {
		this.maskx = x;
		this.masky = y;
		this.mwidth = width;
		this.mheight = height;
	}
	
	public void setMyImage(BufferedImage img) {
		this.myImage = img;
	}
	
	public Spritesheet getSpritesheet() {
		return this.sprite;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	protected int getXCamera() {
		return (int)(this.getX() - Camera.x);
	}
	
	protected int getYCamera() {
		return (int)(this.getY() - Camera.y);
	}
	
	public void Tick() {
		
	}
	
	// Collision helper
	private boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle((int)(e1.getX() + e1.maskx), (int)(e1.getY() + e1.masky), e1.mwidth, e1.mheight);
		Rectangle e2Mask = new Rectangle((int)(e2.getX() + e2.maskx), (int)(e2.getY() + e2.masky), e2.mwidth, e2.mheight);

		return e1Mask.intersects(e2Mask);
	}
	
	public boolean isColliding(Entity e) {
		return isColliding(this, e);
	}
	
	public void Render(Graphics g) {
		Draw(g, this.myImage);
	}
	
	private void Draw(Graphics g, BufferedImage img) {
		g.drawImage(img, this.getXCamera(), this.getYCamera(), this.getWidth(), this.getHeight(), null);
		g.setColor(Color.red);
		g.fillRect(this.getXCamera() + maskx, this.getYCamera() + masky, mwidth, mheight);
	}
}
