package com.skat.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;

public class Enemy extends Entity {

	public Enemy(double x, double y, int width, int height, Spritesheet sprite) {
		super(x, y, width, height, sprite);
		this.setMyImage(sprite.getSprite(7 * 16, 16, 16, 16));
	}
}
