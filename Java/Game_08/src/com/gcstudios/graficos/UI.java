package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class UI {
	
	public static boolean gameOver = false;
	
	public static int seconds = 0;
	public static int minutes = 0;
	public static int frames = 0;
	
	public void tick() {
		frames++;
		if(frames == 60) {
			//Passou 1 segundo.
			frames = 0;
			seconds++;
			if(seconds == 60) {
				seconds = 0;
				minutes++;
				if(UI.minutes % 2 == 0) {
					World.CICLO++;
					if(World.CICLO > World.noite) {
						World.CICLO = 0;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		String formatTime = "";
		if(minutes < 10) {
			formatTime+="0"+minutes+":";
		}else {
			formatTime+=minutes+":";
		}
		
		if(seconds < 10) {
			formatTime+="0"+seconds;
		}else {
			formatTime+=seconds;
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 23));
		g.drawString("Score: " + Game.score, 10, 25);
		
		for(int i = 0; i < Game.life; i++) {
			g.drawImage(Game.spritesheet.getSprite(32, 0, 16, 16), Game.WIDTH * Game.SCALE - 130 + 20 * i, 15, null);
		}
		
		if(gameOver) {
			g.setFont(new Font("arial", Font.BOLD, 45));
			g.drawString("Game Over", (Game.WIDTH * Game.SCALE) / 2 - 120, (Game.HEIGHT * Game.SCALE) / 2 - 50);
			g.setFont(new Font("arial", Font.BOLD, 23));
			g.drawString("Your score is " + Game.score, (Game.WIDTH * Game.SCALE) / 2 - 80, (Game.HEIGHT * Game.SCALE) / 2 );
		}
		
		
	}
	
}
