package snake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import gameMaker.*;

public class Body extends GameObject {

	private static final Image imgHoriz = GraphicsController.getImage("body\\horizontal.png");
	private static final Image imgVert = GraphicsController.getImage("body\\vertical.png");

	private static final Image imgTailRight = GraphicsController.getImage("tail\\right.png");
	private static final Image imgTailUp = GraphicsController.getImage("tail\\up.png");
	private static final Image imgTailLeft = GraphicsController.getImage("tail\\left.png");
	private static final Image imgTailDown = GraphicsController.getImage("tail\\down.png");

	private static final Image imgTurnRight = GraphicsController.getImage("turn\\right.png");
	private static final Image imgTurnUp = GraphicsController.getImage("turn\\up.png");
	private static final Image imgTurnLeft = GraphicsController.getImage("turn\\left.png");
	private static final Image imgTurnDown = GraphicsController.getImage("turn\\down.png");

	private boolean paused;
	
	public Body(Point p){
		super(true, p, imgHoriz);
		paused = false;
	}


	public boolean getPaused() {
		return paused;
	}
	
	public void pause() {
		paused = true;
	}
	
	public void unpause() {
		paused = false;
	}
	
	public void togglePaused() {
		paused = !paused;
	}
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Body.class};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		
	}

	@Override
	public void createEvent() {
		// TODO Auto-generated method stub
		
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
	
	public void drawTail(Graphics g, GameObject front) {
		Image img = null;
		int x = getMotion().getX();
		int y = getMotion().getY();
		int fx = front.getMotion().getX();
		int fy = front.getMotion().getY();
		if (x == fx){
			if (y > fy)
				img = imgTailUp;
			else if (y > fy)
				img = imgTailDown;
		} else if (y == fy){
			if (x < fx)
				img = imgTailRight;
			else if (x > fx)
				img = imgTailLeft;
		}
		getSprite().setImage(img);
	}
		
	public void drawBody(Graphics g, GameObject back, GameObject front) {
		Image img = null;
		int x = getMotion().getX();
		int y = getMotion().getY();
		int bx = back.getMotion().getX();
		int by = back.getMotion().getY();
		int fx = front.getMotion().getX();
		int fy = front.getMotion().getY();
		if (bx == fx)
		    img = imgVert;
		else if (by == fy)
		    img = imgHoriz;
		else if (x <= bx && x <= fx && y >= by && y >= fy)
		    img = imgTurnRight;
		else if (x >= bx && x >= fx && y >= by && y >= fy)
	        img = imgTurnUp;
		else if (x >= bx && x >= fx && y <= by && y <= fy)
			img = imgTurnLeft;
		else if (x <= bx && x <= fx && y <= by && y <= fy)
		    img = imgTurnDown;
		getSprite().setImage(img);
	}

}
