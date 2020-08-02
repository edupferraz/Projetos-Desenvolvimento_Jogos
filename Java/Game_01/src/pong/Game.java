package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int Width = 420;
	public static int Height = 220;
	public static int Scale = 3;
	
	public BufferedImage layer = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;

	
	public Game() {
		
		//Tamanho da janela
		this.setPreferredSize(new Dimension(Width * Scale, Height * Scale));
		
		
		this.addKeyListener(this);
		
		
		//Inicializando o player
		player = new Player(100, Height - 15);
		
		//Inicializando o inimigo
		enemy = new Enemy(100, 0 + 5);
		
		//Inicializando a bola
		ball = new Ball(100, Height/2 - 1);
	}
	
	public static void main (String[] args) {
		
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		
		//Não deixar o usuário modificar o tamanho da janela
		frame.setResizable(false);
		
		//Quando fechar a janela terminar a execução do programa
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(game);
		
		frame.pack();
		
		//Não setar localização nenhuma - Ficar no centro da tela
		frame.setLocationRelativeTo(null);
		
		//Aparecer na tela
		frame.setVisible(true);
		
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	
	//Rensposável pela renderização e amostra do jogo
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
	
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		
		//Limpar a tela durante a renderização
		g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);	
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, Width*Scale, Height*Scale, null);
		
		//Mostrar rendereização
		bs.show();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
	}
	
	
	
}
