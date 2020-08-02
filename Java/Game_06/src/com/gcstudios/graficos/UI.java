package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class UI {
	
	public static BufferedImage Heart = Game.spritesheet.getSprite(0, 16, 16, 16);

	public void render(Graphics g) {
		
		for(int i = 0; i < Game.vida; i++) {
			g.drawImage(Heart,  10 + (i * 30), 18, 36, 36, null);
		}

		g.setFont(new Font("arial", Font.BOLD, 19));
		g.setColor(Color.white);
		g.drawString("$" + Game.dinheiro, Game.WIDTH * Game.SCALE - 90, 35);
	}
	
}
