package safari;

import java.awt.*;
import java.awt.event.KeyEvent;

import gameMaker.*;
import gameMaker.objects.AlarmController;
import gameMaker.objects.GameObject;
import gameMaker.objects.eventInterfaces.HasAlarmEvent;
import gameMaker.utilities.MiscUtilities;

public class EnemyController extends GameObject implements HasAlarmEvent{

	private static final String SPRITE_ADDRESS = null;
	
	private AlarmController alarmController;
	private static int score;
	
	public EnemyController() {
		super(false, new Point(0, 0));
		score = 0;
	}
	
	public static void addToScore(int sChange){
		score += sChange;
	}
	
	
	@Override
	public void createEvent() {
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 50);
	}
	
	@Override
	public AlarmController getAlarmController(){
		return alarmController;
	}

	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0: // spawn enemies
			int numTigers = MiscUtilities.choose(new int[] {3,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			int numBirds = MiscUtilities.choose(new int[] {3,3,2,1,1,1,1,1,0});
			for (int ii = 0; ii < numTigers; ++ii){
				new Tiger(MiscUtilities.randomPoint(GameController.getRoomRectangle()));
			}
			for (int ii = 0; ii < numBirds; ++ii){
				new Bird(MiscUtilities.randomPoint(GameController.getRoomRectangle()));
			}
			int timeUntilNextSpawn = alarmController.getLastValue(alarmId) - 1; // The enemies come quicker each time!
			if (timeUntilNextSpawn < 5){
				timeUntilNextSpawn = 5;
			}
			alarmController.setAlarm(0, timeUntilNextSpawn);
			break;
		}
	}

	@Override
	public void draw(Graphics g){
		g.drawString("Score: " + score, 10, 24);
	}
	
	
	
	//unused:
	
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}

	public void collisionEvent(GameObject other) {}
	public void destroyEvent() {}
	public void intersectBoundaryEvent() {}
	public void outsideRoomEvent() {}

}
