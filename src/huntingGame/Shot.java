package huntingGame;

import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;

import spaceGame.Enemy;

import gameMaker.*;

public class Shot extends GameObject implements HasAlarmEvent{

	private AlarmController alarmController;
	
	private Point endPos;
	private double myDirection;
	
	public Shot(Point position, double direction) {
		super(true, position);
		myDirection = direction;
		alarmController = new AlarmController(this);
	}	

	
	
	//GameObject:

	
	
	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Bird){
			other.destroy();
		}
		if (other instanceof Tiger){
			((Tiger) other).hit();
		}
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		((Graphics2D)g).drawLine(getPosition().x, getPosition().y, endPos.x, endPos.y);
	}

	@Override
	public void createEvent() {
		Rectangle room = GameController.getRoomRectangle();
		int maxDimension = (int) Math.hypot(room.width, room.height); 
		int x = getX() + (int) (Math.cos(Math.toRadians(myDirection)) * maxDimension);
		int y = getY() - (int) (Math.sin(Math.toRadians(myDirection)) * maxDimension);
		endPos = new Point(x, y);
		Line2D.Double line = new Line2D.Double(getPosition(), endPos);
		for (GameObject obj : ObjectController.getAllObjects())
			if (obj.isColliding(line))
				collisionEvent(obj);
		
		alarmController.setAlarm(0, (int)(Math.random() * 3) + 5);//destroy();
	}

	@Override
	public void destroyEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void intersectBoundaryEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void outsideRoomEvent() {
		// TODO Auto-generated method stub

	}
	
	
	
	//HasAlarmEvent:
	
	

	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0:
			destroy();
			break;
		}
	}

	@Override
	public AlarmController getAlarmController() {
		return alarmController;
	}

}
