package com.skat.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.skat.main.Game;

public class UI {
	public void Render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(8, 4, 50, 8);

		g.setColor(Color.green);
		g.fillRect(8, 4, (int)((Game.player.life/Game.player.maxLife)*50), 8);
		
		g.setColor(Color.white);
		g.setFont(new Font("Sans Serif", Font.BOLD, 7));
		g.drawString((int)Game.player.life + "/" + (int)Game.player.maxLife, 33, 11);
	}
}
