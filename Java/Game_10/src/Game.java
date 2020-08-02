import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	//Declaração das Variáveis
	public static int Width = 480;
	public static int Height = 480;
	
	public static List<Crab> crabs;
	
	public CrabSpawner spawner;
	
	public static Spritesheet spritesheet;
	
	public static Rectangle maskBuraco;
	
	public Game() {
		this.setPreferredSize(new Dimension(Width, Height));
		
		spritesheet = new Spritesheet("/spritesheet.png");
		
		crabs = new ArrayList<>();
		
		spawner = new CrabSpawner();
		
		maskBuraco = new Rectangle(Width/2 - 25, Height/2 - 25, 50, 50);
		
	}
	
	public void update() {
		spawner.update();
		for(int i = 0; i < crabs.size(); i++) {
			crabs.get(i).update();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);
		g.setColor(Color.white);
		g.fillOval(Width/2 - 25, Height/2 - 25, 50, 50);
		
		for(int i = 0; i < crabs.size(); i++) {
			crabs.get(i).render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		JFrame frame = new JFrame();
		frame.setTitle("Catch the meteor");
		

		
		//Adicionar janela do jogo
		frame.add(game);
		
		//Deixar estático
		frame.setResizable(false);
		
		frame.pack();
		

		
		//Ficar no centro
		frame.setLocationRelativeTo(null);
		
		//Fechar a instância quando fechar a janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Deixar visível
		frame.setVisible(true);
		
		new Thread(game).start();
		
	}

	
	public void run() {
		
		double fps = 75.0;
		while(true) {
			update();
			render();
			
			try {
				Thread.sleep((int)(1000/fps));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
