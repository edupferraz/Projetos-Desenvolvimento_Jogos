package com.gcstudios.world;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Tubo;
import com.gcstudios.main.Game;

public class TuboGenerator {
	
	public int time = 0;
	public int targetTime = 60;

	public void tick() {
		
		time++;
		
		if(time == targetTime) {
			
			int height = Entity.rand.nextInt(50 - 30) + 30;
			
			
			Tubo tubo1 = new Tubo(Game.WIDTH, 0, 20, height, 1, Game.spritesheet.getSprite(16, 0, 16, 16));
			
			Tubo tubo2 = new Tubo(Game.WIDTH, Game.HEIGHT - height, 20, height, 1, Game.spritesheet.getSprite(32, 0, 16, 16) );
			
			Game.entities.add(tubo1);
			Game.entities.add(tubo2);
			
			time = 0;
		}
		
		
	}

}
