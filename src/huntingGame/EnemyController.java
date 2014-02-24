package huntingGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameMaker.*;

public class EnemyController extends GameObject implements HasAlarmEvent, HasKeyEvent{

	private static final String SPRITE_ADDRESS = null;
	
	private AlarmController alarmController;
	private static int score;
	
	public EnemyController() {
		super(false, new Point(0, 0));
		score = 0;
	}
	
	public Point randomPoint(){
		Rectangle r = GameController.getRoomRectangle();
		int x = (int) (Math.random() * (r.width - 64) + 32);
		int y = (int) (Math.random() * (r.height - 64) + 32);
		return new Point(x, y);
	}

	
	public void createEvent() {
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 50);
	}
	
	
	public AlarmController getAlarmController(){
		return alarmController;
	}


	
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0://create a bird
			//if (ObjectController.instanceNumber(Bird.class, false) <= 10);
			{
//				int count = 0;
//				for (GameObject obj : ObjectController.getAllObjects())
//					if (obj instanceof Bird)
//						count++;
//				if (count < 8)
				int numTigers = Utilities.choose(new int[] {3,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
				int numBirds = Utilities.choose(new int[] {3,3,2,1,1,1,1,1,0});
//				System.out.println("Spawning enemies...");
				for (int ii = 0; ii < numTigers; ++ii){
					new Tiger(randomPoint());
				}
				for (int ii = 0; ii < numBirds; ++ii){
					new Bird(randomPoint());
				}
			}
			int timeUntilNextSpawn = alarmController.getLastValue(alarmId) - 1; // The enemies come quicker each time!
			if (timeUntilNextSpawn < 5){
				timeUntilNextSpawn = 5;
			}
			alarmController.setAlarm(0, timeUntilNextSpawn);
			break;
		}
	}

	public void keyPressed(Integer code){
		switch (code){
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_ENTER://restart the game
			GameController.restart();
			break;
		}
	}

	public void draw(Graphics g){
		g.drawString("Score: " + score, 16, 16);
	}
	
	public static void changeScore(int sChange){
		score += sChange;
	}
	
	
	
	//unused:
	
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}

	public void collisionEvent(GameObject other) {
		// TODO Auto-generated method stub

	}

	
	public void destroyEvent() {
		// TODO Auto-generated method stub

	}

	
	public void intersectBoundaryEvent() {
		// TODO Auto-generated method stub

	}

	
	public void outsideRoomEvent() {
		// TODO Auto-generated method stub

	}

	public void keyDown(Integer code) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(Integer code) {
		// TODO Auto-generated method stub
		
	}

}
