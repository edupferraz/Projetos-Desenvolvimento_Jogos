package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	public double x,y;
	public int width, height;
	
	public static int score = 0;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	
	public void tick() {
		x += ((Game.ball.x) - x - 6) * 0.2;
		
		if(x + width > Game.Width) {
			x = Game.Width - width;			
		} else if(x + width < 0) {
			x = 0;
		}
		
		

	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
}
