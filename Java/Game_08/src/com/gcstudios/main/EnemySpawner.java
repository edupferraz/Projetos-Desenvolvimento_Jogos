package com.gcstudios.main;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.graficos.UI;

public class EnemySpawner {

	public int targetTime = 60 * 2;
	public int curTime = 0;
	
	public void tick() {
		
		if(!UI.gameOver) {
			curTime++;
			if( curTime == targetTime) {
				curTime = 0;
				int yy = 0;
				int xx = Entity.rand.nextInt(Game.WIDTH - 16);
				
				Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2) + 1, Game.spritesheet.getSprite(16, 0, 16, 16));
				Game.entities.add(enemy);
				System.out.println("Eai");
			}
		}
		
	}
}
