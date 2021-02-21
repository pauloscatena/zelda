package com.skat.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;

public class Weapon extends Entity {

	public Weapon(double x, double y, int width, int height, Spritesheet sprite) {
		super(x, y, width, height, sprite);
		this.setMyImage(sprite.getSprite(7 * 16, 0, 16, 16));
	}
}
