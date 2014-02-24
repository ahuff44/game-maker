package huntingGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import gameMaker.*;

public class Hunter extends GameObject implements HasMouseEvent, HasKeyEvent, HasAlarmEvent {
	
	private static final Image hunterImg = GraphicsController.getImage("hunter.png");

	private AlarmController alarmController;
	private Point target;
	public static Hunter self;
	
	public Hunter(Point p) {
		super(true, p, hunterImg);
		alarmController = new AlarmController(this);
		target = null;//TODO should this change?
		self = this;
	}
	
	
	
	//GameObject:	
	
	
	
	public void collisionEvent(GameObject other) {
		if (other instanceof Bird || other instanceof Tiger)
			gotoPreviousPosition();
	}

	
	public void createEvent() {
		// TODO Auto-generated method stub

	}

	
	public void destroyEvent() {
		// TODO Auto-generated method stub

	}

	
	public void intersectBoundaryEvent() {
		gotoPreviousPosition();
	}

	
	public void outsideRoomEvent() {
		// TODO Auto-generated method stub

	}
	
	public Point spriteCenter(){
		return new Point(16, 16);
	}
	
	
	
	//HasMouseEvent:
	
	
	
	public void mouseEvent(MouseEvent ev) {
		switch (ev.getID()){
		case MouseEvent.MOUSE_PRESSED:
			alarmController.setAlarm(0, 5);
			target = ev.getPoint();
			shoot();
			break;
		case MouseEvent.MOUSE_RELEASED:
			alarmController.setAlarm(0, 0);
			break;
		}
	}
	
	private void shoot() {
		double dir = Utilities.pointDirection(getPosition(), target);
		if (true){//if it's a shotgun
			final int scatterFactor = 20;
			for (int i = 0; i < 4; i++)
				new Shot(getPosition(), dir + Math.random() * scatterFactor * 2 - scatterFactor);
		}
	}

	

	//HasKeyEvent:
	
	

	@Override
	public void keyDown(Integer code) {
		final int speed = 8;
		switch (code){
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			changeX(-speed);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			changeX(speed);
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			changeY(-speed);
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			changeY(speed);
			break;
		}
	}

	@Override
	public void keyPressed(Integer code) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(Integer code) {
		// TODO Auto-generated method stub
		
	}


	
	//HasAlarmEvent:
	
	

	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0:
			target = Utilities.mousePosition();
			shoot();
			alarmController.resetAlarm(0);
		}
	}

	public void draw(Graphics g){
		super.draw(g);
		Point p = Utilities.mousePosition();
		g.drawRect(p.x - 2, p.y - 2, 5, 5);
	}

	@Override
	public AlarmController getAlarmController() {
		return alarmController;
	}

}
