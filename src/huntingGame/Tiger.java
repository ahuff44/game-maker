package huntingGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import gameMaker.*;

public class Tiger extends GameObject implements HasStepEvent{

	private static final Image tigerImg = GraphicsController.getImage("tiger.png");

	private AlarmController alarmController;

	private final int SPEED = 8;
	private final int MAX_HEALTH = 10;
	private int health = MAX_HEALTH;
	
	public Tiger(Point position) {
		super(true, position, tigerImg);
		setSpeed(0);
	}
	
	
	
	//GameObject:
	
	
	
	@Override
	public void intersectBoundaryEvent() {
		gotoPreviousPosition();
		changeDirection(90);
	}
	
	@Override
	public void outsideRoomEvent() {
		destroy();
	}
	
	public Point spriteCenter(){
		return new Point(16, 16);
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Bird){
			gotoPreviousPosition();
			other.destroy();
			BirdController.changeScore(-20);
		}
		if (other instanceof Hunter){
			setSpeed(0);
			alarmController.setAlarm(0, 75);
			BirdController.changeScore(-5);
		}
		if (other instanceof Tiger){
			gotoPreviousPosition();
		}
	}

	@Override
	public void createEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyEvent() {
		BirdController.changeScore(100);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.RED);
		g.fillRect(getX() - 20, getY() - 24, (int) (health * 1.0 / MAX_HEALTH * 40), 6);
		g.setColor(Color.BLACK);
		g.drawRect(getX() - 20, getY() - 24, (int) (health * 1.0 / MAX_HEALTH * 40), 6);
	}

	//HasStepEvent:

	
	@Override
	public void beginStepEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endStepEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stepEvent() {
		setSpeed( Utilities.numBounds(getSpeed() + .1, 0, SPEED));
		double dir = Utilities.pointDirection(getPosition(), Hunter.self.getPosition());
		setDirection(dir);
	}
	
	public void hit() {
		health--;
		if (health <= 0)
			destroy();
	}

}
