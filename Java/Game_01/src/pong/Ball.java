package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x,y;
	public int width, height;
	
	public double dx, dy;
	public double speed = 2;
	
	public int scoreEnemy = Enemy.score;
	public int scorePlayer = Player.score;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		
		
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		
		if(x + (dx * speed) + width >= Game.Width || x +(dx*speed) < 0) {
			dx *= -1;
		}
		
		if(y >= Game.Height) {
			//Ponto do inimigo
			System.out.println("O Inimigo Ganhou ! Não foi dessa vez ...");
			
			
			//Não funcionou pois quando o jogo é resetado cria-se um novo jogo e a variável é zerada novamente
			scoreEnemy += 1;
			//System.out.println(scoreEnemy);
			
			if(scoreEnemy == 7) {
				System.out.println("O jogo acabou! Inimigo Wins!");
				System.out.println("Placar: " + scorePlayer + " X " + scoreEnemy);
			} else{
				//Espere 3 segundos
				try { Thread.sleep (3000); } catch (InterruptedException ex) {}
				
				new Game();
				
				return;
				
				
			}
			
			
			
		} else if(y < 0) {
			//Ponto do Jogador
			System.out.println("Você Ganhou Parabéns !!!");
			
			scorePlayer += 1;
			//System.out.println(scorePlayer);
			
			if(scorePlayer == 7) {
				System.out.println("O jogo acabou! Player Wins!");
				System.out.println("Placar: " + scorePlayer + " X " + scoreEnemy);
			} else{
				//Espere 3 segundos
				try { Thread.sleep (3000); } catch (InterruptedException ex) {}
				
				new Game();
				
				return;
			}
		}
		
		
		
		Rectangle bounds = new Rectangle( (int)( x + (dx * speed)),(int)(y + (dy*speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			
			
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			
			if(dy > 0) {
				dy *= -1;
			}
			
			speed += 0.1;
		}
		
		if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			
			
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			
			if(dy < 0) {
				dy *= -1;
			}
		}
		
		x += dx*speed;
		y += dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
	}
}
