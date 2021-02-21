package com.skat.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;

public class Entity {
	private double x;
	private double y;
	private int width;
	private int height;
	
	private Spritesheet sprite;
	
	public Entity(double x, double y, int width, int height, Spritesheet sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
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
	
	public void Tick() {
		
	}
	
	public void Render(Graphics g) {
		//g.drawImage(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
	}
}
