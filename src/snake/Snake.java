package snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameMaker.*;
import gameMaker.objects.*;
import gameMaker.objects.eventInterfaces.HasAlarmEvent;
import gameMaker.objects.eventInterfaces.HasKeyEvent;
import gameMaker.objects.eventInterfaces.HasStepEvent;
import gameMaker.utilities.MiscUtilities;
import gameMaker.visuals.GraphicsController;


public class Snake extends GameObject implements HasKeyEvent, HasAlarmEvent, HasStepEvent{
	
	
	
	//fields
	

	
	private static final Image imgRight = GraphicsController.getImage("head\\right.png");
	private static final Image imgUp = GraphicsController.getImage("head\\up.png");
	private static final Image imgLeft = GraphicsController.getImage("head\\left.png");
	private static final Image imgDown = GraphicsController.getImage("head\\down.png");
	
	private int dir;
	
	/**
	 * A buffer. Without this, if you press buttons fast enough, it's possible to run into your neck
	 */
	private int nextDir;
	private ArrayList<Body> body;
	private AlarmController alarmController;
		
	//constructors
	
	

	public Snake(Point p, Body tail){
		super(true, p, imgRight);
		dir = 0;
		nextDir = 0;
		body = new ArrayList<Body>();
		body.add(tail);
	}
	
	
	
	//methods
	
	
	private void new_food(){
		Point pt = MiscUtilities.randomPoint(GameController.getRoomRectangle());
		new Food(MiscUtilities.snapToGrid(pt));
	}
	
	public void move(){
		moveHead();
		moveBody();
	}
	
	public void moveHead(){
		int dx = 0;
		int dy = 0;
		Image img = null;
	    switch (dir){
	    case 0:
		    dx = 16;
		    img = imgRight;
	        break;
	    case 90:
			dy = -16;
	        img = imgUp;
	        break;
	    case 180:
	        dx = -16;
	        img = imgLeft;
	    	break;
	    case 270:
		    dy = 16;
		    img = imgDown;
	    	break;
	    }
	    getMotion().changePosition(dx, dy);
	    getSprite().setImage(img);
	}
	
	public void grow(){
		for (Body b : body)
			b.pause();
		body.add(0, new Body(getMotion().getPosition()));
	}
	
	public void moveBody(){
		for (int i = body.size() - 1; i > 0; i--){//move all but the first body segments to the next one's position
			Body b = body.get(i);
			if (!b.getPaused()){
				Point newP = body.get(i - 1).getMotion().getPosition();
				b.getMotion().setPosition(newP);
			} else
				b.unpause();
		}
		Body b = body.get(0);
		if (!b.getPaused())
			b.getMotion().setPosition(getMotion().getPosition());//move the first body segment to the snake's position
	}
	
	public void drawBody(Graphics g){
		GameObject front;
		if (body.size() > 1)
			front = body.get(body.size() - 2);
		else
			front = this;
		Body tail = body.get(body.size() - 1);
		tail.drawTail(g, front);

		if (body.size() > 1){
			for (int i = body.size() - 2; i > 0; i--){//move all but the first body segments to the next one's position
				Body b = body.get(i);
				b.drawBody(g, body.get(i + 1), body.get(i - 1));
			}
	
			Body neck = body.get(0);
			neck.drawBody(g, body.get(1), this);
		}
	}
	
	
	//Overridden methods

	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Food.class, Body.class};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Body){
			if (other != body.get(0)){ // if it's not the neck segment
				destroy();
			}
		} else if (other instanceof Food){
			other.destroy();
			grow();
			new_food();
		}
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		drawBody(g);
	}

	@Override
	public void keyDown(Integer keyCode){
		switch (keyCode){
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			if (dir != 0)
				nextDir = 180;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			if (dir != 180)
				nextDir = 0;
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			if (dir != 270)
				nextDir = 90;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			if (dir != 90)
				nextDir = 270;
			break;
		default:
			return;
		}
	}
	
	@Override
	public void keyPressed(Integer keyCode) {}
	
	@Override
	public void keyReleased(Integer keyCode) {}
	
	@Override
	public void intersectBoundaryEvent() {
		getMotion().gotoPreviousPosition();
	}
	
	@Override
	public void outsideRoomEvent() {
		destroy();
	}
	
	@Override
	public void createEvent() {
		new_food();
		
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 3); //move
	}
	
	@Override
	public void destroyEvent() {}
	
	@Override
	public AlarmController getAlarmController(){
		return alarmController;
	}
	
	@Override
	public void alarmEvent(int alarmId){
		switch (alarmId){
		case 0:
			move();
			alarmController.resetAlarm(alarmId);
			break;
		}
	}
	
	@Override
	public void beginStepEvent(){
		dir = nextDir;
	}

	@Override
	public void stepEvent() {}

	@Override
	public void endStepEvent() {}

}
