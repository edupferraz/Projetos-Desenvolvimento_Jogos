import java.util.Random;

public class CrabSpawner {
	public int curTime = 0;
	public int targetTime = 60 * 2;
	public Random random;
	
	public CrabSpawner() {
		random = new Random();
	}
	
	public void update() {
		curTime++;
		if(curTime == targetTime) {
			curTime = 0;
			if(random.nextInt(100) < 50) {
				Game.crabs.add(new Crab(random.nextInt(Game.Width - 40), - 40));
			} else {
				Game.crabs.add(new Crab(random.nextInt(Game.Width - 40), Game.Height - 40));
			}
			
		}
	}
}
