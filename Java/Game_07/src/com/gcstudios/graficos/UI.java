package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class UI {
	
	public static int seconds = 0;
	public static int minutes = 0;
	public static int frames = 0;

	public void render(Graphics g) {
		
		int curLife = (int)((Game.player.life/100) * 180);
		g.setColor(Color.red);
		g.fillRect(Game.WIDTH * Game.SCALE - 200, 10, 180, 30);
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH * Game.SCALE - 200, 10, curLife, 30);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString(Game.player.life + "/" + "100", Game.WIDTH * Game.SCALE - 140, 30);
		
		String formatTime = "";
		if(minutes < 10) {
			formatTime += "0" + minutes + ":";
		} else {
			formatTime += minutes + ":";
		}
		
		if(seconds < 10) {
			formatTime += "0" + seconds;
		} else {
			formatTime += seconds;
		}
		
		g.setFont(new Font("arial", Font.BOLD, 23));
		g.drawString(formatTime, 30, 30);
	}
	
	public void tick() {
		frames++;
		
		if(frames == 60) {
			frames = 0;
			seconds++;
			
			if(seconds == 60) {
				seconds = 0;
				minutes++;
				if(UI.minutes % 2 == 0) {
					World.ciclo++;
					if(World.ciclo > World.noite) {
						World.ciclo = 0;
					}
				}
			}
		}
	}
	
}
