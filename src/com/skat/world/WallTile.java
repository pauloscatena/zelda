package com.skat.world;

import com.skat.graphics.Spritesheet;
import com.skat.main.Game;

public class WallTile extends Tile {

	public WallTile(int x, int y, Spritesheet sprite) {
		super(x, y, sprite);
		this.setMyImage(sprite.getSprite(16, 0, 16, 16));
	}

}
