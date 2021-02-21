package com.skat.entities;

import java.awt.Graphics;
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
	
	public Entity(double x, double y, int width, int height, Spritesheet sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
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
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	protected int getXCamera() {
		return this.getX() - Camera.x;
	}
	
	protected int getYCamera() {
		return this.getY() - Camera.y;
	}
	
	public void Tick() {
		
	}
	
	public void Render(Graphics g) {
		Draw(g, this.myImage);
	}
	
	private void Draw(Graphics g, BufferedImage img) {
		g.drawImage(img, this.getXCamera(), this.getYCamera(), this.getWidth(), this.getHeight(), null);
	}
}
