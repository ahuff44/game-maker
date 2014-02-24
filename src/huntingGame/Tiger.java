package huntingGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import gameMaker.*;

public class Tiger extends GameObject implements HasStepEvent{

	private static final Image tigerImg = GraphicsController.getImage("tiger.png");

	private final int SPEED = 8;
	private final int MAX_HEALTH = 10;
	private int health = MAX_HEALTH;
	
	public Tiger(Point position) {
		super(true, position, tigerImg);
		getMotion().setSpeed(0);
	}
	
	
	
	//GameObject:
	
	
	
	@Override
	public void intersectBoundaryEvent() {
		getMotion().gotoPreviousPosition();
		getMotion().changeDirection(90);
	}
	
	@Override
	public void outsideRoomEvent() {
		destroy();
	}
	
	public Point spriteCenter(){
		return new Point(16, 16);
	}
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Bird.class, Hunter.class, Tiger.class};
		return (Class<? extends GameObject>[]) list;
	}

	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Bird){
			getMotion().gotoPreviousPosition();
			other.destroy();
			EnemyController.changeScore(-20);
		}
		if (other instanceof Hunter){
			other.destroy();
		}
		if (other instanceof Tiger){
			getMotion().gotoPreviousPosition();
		}
	}

	@Override
	public void createEvent() {}

	@Override
	public void destroyEvent() {
		EnemyController.changeScore(100);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.RED);
		g.fillRect(getMotion().getX() - 20, getMotion().getY() - 24, (int) (health * 1.0 / MAX_HEALTH * 40), 6);
		g.setColor(Color.BLACK);
		g.drawRect(getMotion().getX() - 20, getMotion().getY() - 24, (int) (health * 1.0 / MAX_HEALTH * 40), 6);
	}

	//HasStepEvent:

	
	@Override
	public void beginStepEvent() {}

	@Override
	public void endStepEvent() {}

	@Override
	public void stepEvent() {
		getMotion().setSpeed( Utilities.numBounds(getMotion().getSpeed() + .1, 0, SPEED));
		double dir = Utilities.pointDirection(getMotion().getPosition(), Hunter.self.getMotion().getPosition());
		getMotion().setDirection(dir);
	}
	
	public void hit() {
		health--;
		if (health <= 0){
			destroy();
		}
	}

}
