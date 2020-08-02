import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Crab {
	public double x, y, dx, dy;
	public double spd = 4;
	

	
	public Crab(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		x++;
		y++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 40, 40);
	}
}
