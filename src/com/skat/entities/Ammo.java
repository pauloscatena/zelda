package com.skat.entities;

import java.awt.Graphics;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;

public class Ammo extends Entity {

	public Ammo(double x, double y, int width, int height, Spritesheet sprite) {
		super(x, y, width, height, sprite);
		this.setMyImage(sprite.getSprite(6 * 16, 16, 16, 16));
	}
}
