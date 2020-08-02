package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean right, left;	
	public int x, y;
	public int width, height;
	
	public static int score = 0;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;

	}
	
	public void tick() {
		
		//Movimentação do Player
		if(right) {
			x += 3;
		} else if(left) {
			x -= 3;
		}
		
		
		//Colisão Lateral com as paredes
		if(x + width > Game.Width) {
			x = Game.Width - width;
		} else if(x < 0) {
			x = 0;
		}
	}
	
	
	//Renderização da forma do PLayer
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}
