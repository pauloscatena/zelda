package com.skat.world;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;

public class FloorTile extends Tile {

	public FloorTile(int x, int y, Spritesheet sprite) {
		super(x, y, sprite);
		this.setMyImage(sprite.getSprite(0, 0, 16, 16));
	}

}
