package gameMaker;

//TODO working on intersect boundary, position, position buffer etc. See Fighter in spaceGame
//TODO WHOA! BAD!!! Run the game a few time and shoot instantly- one bullet kills two enemies--> BAAAD
//      this doesn't happen anymore but THE PROBLEM IS STILL THERE
//TODO the enemies shouldn't be clumping...

//TODO make mouse events separate, like keyEvents

//TODO Sprite, Alarm, restarting, better graphics
//TODO go through and make sure objects *clone* incoming objects, not just copying them. this could cause really annoying errors

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class GameObject {

	
	
	//fields
	
	
	
	private Sprite sprite;
		
	/** Whether or not this object generates collision events. There's really no reason to change it after it's been initialized, so please don't.
	 * TODO ***********it won't work, currently; i guess it could be fixed... ...by having a becomeSolid method that trys to add the object to every group, and removeSolid removes it. wouldn't be too big of a deal
	 */
	private /*final*/ boolean solid;
	
	/**
	 * This represents whether this object is "alive" in the game; whether ObjectController has a record of it in allObjects. Do NOT use this as a game developer
	 */
	private boolean alive;
	
	private Motion motion;
	
	/*
	 * A list of all objects that this object has collision events with. It really should have a "final" modifier. Define it in all classes that extend GameObject by overriding <code>setCollsionList()</code>.
	 * If this GameObject has no collision events with other objects, define this to an empty ArrayList.
	 */
	//protected static Class<?>[] collisionList;//TODO does this set it for each individual class, or only for GObj?

	
	
	//modifiers

	
	
	/**
	 * Don't call this. Or I will kill you. It's only used by ObjectController. TODO block this from a game developer
	 * @return the previous state of the boolean variable <code>alive</code>
	 */
	public boolean setAlive(boolean a){
		boolean old = alive;
		alive = a;
		return old;
	}
	
	/**
	 * @param sprite the sprite to set
	 * @return the previous sprite
	 */
	public Sprite setSprite(Sprite newSprite) {
		Sprite old = sprite;
		sprite = newSprite;
		return old;
	}
	
	public Motion setMotion(Motion newMotion){
		Motion old = motion;
		motion = newMotion;
		return old;
	}
	
	
	
	//accessors

	
	
	/**@return this instance's sprite*/ public Sprite getSprite() { return sprite; }
	/**@return this intance's motion*/ public Motion getMotion() { return motion; }
	/**@return whether this instance is solid, aka whether it generates collision events*/ public boolean getSolid() { return solid; }

	/**@return whether this instance is alive, whether ObjectController has a record of it in allObjects*/ public boolean getAlive() { return alive; }
	
	
	
	//constructors
	
	
	
	public GameObject(){
		this(false, new Motion(new Point(0, 0)), Sprite.getEmptySprite());
	}
	
	public GameObject(boolean solid, Point position){
		this(solid, new Motion(position), Sprite.getEmptySprite());
	}
	
	public GameObject(boolean solid, Motion mot){
		this(solid, mot, Sprite.getEmptySprite());
	}
	
	
	public GameObject(boolean solid, Point position, Image img){
		this(solid, new Motion(position), new Sprite(img));
	}
		
	public GameObject(boolean solid, Point position, Animation anim){
		this(solid, new Motion(position), new Sprite(anim));
	}
	
	public GameObject(boolean solid, Point position, Sprite spr){
		this(solid, new Motion(position), spr);
	}

	
	public GameObject(boolean solid, Motion mot, Image img){
		this(solid, mot, new Sprite(img));
	}
	
	public GameObject(boolean solid, Motion mot, Animation anim){
		this(solid, mot, new Sprite(anim));
	}
	
	//mother constructor
	public GameObject(boolean solid, Motion mot, Sprite spr){
		GameController.debugln(this + ".construct()");

		alive = false;//this will become true when ObjectController adds it to the game

		this.solid = solid;
		motion = mot;
		sprite = spr;
		
		ObjectController.addObject(this);
	}
	
	
	
	//methods for the GameObject class
	
	
	
	/**
	 * Returns whether this instance is colliding with another instance, by seeing if there is a collision between the masks. ***If used independently, make sure updateMask() has been called on BOTH GameObject's beforehand, or the collision detection will not work correctly
	 * @return whether this instance is colliding with another instance, by seeing if there is a collision between the masks.
	 */
	public boolean isColliding(GameObject other){
		if (solid && other.solid)//solid is redundant, as it is checked in checkCollisions(), but maybe at some point an object will bypass checkCollisions and go directly to this, so it's here just in case.
			return getMask().intersects(other.getMask());
		else
			return false;
	}
	
	/**
	 * Returns whether this instance is colliding with the specified shape.
	 * @return whether this instance is colliding with the specified shape.
	 */
	public boolean isColliding(Shape s){
		//updateMask();
		if (!solid)
			return false;
		else {
			return s.intersects(getMask());
		}
	}	
	
	/**
	 * 
	 * TODO FIXME for some reason this doesn't work, fix it!!!!!!!!!
	 * 
	 * Returns whether this instance is containing the specified point.
	 * @return whether this instance is containing the specified point.
	 */
	public boolean isContaining(Point p){
		//updateMask();
		return (getMask().contains(p));
	}
	
	/**
	 * Returns whether this instance is intersecting the room boundary.
	 * @return whether this instance is intersecting the room boundary
	 */
	public boolean isIntersectingBoundary(){
		Rectangle m = getMask();
		Rectangle r = m.intersection(GameController.getRoomRectangle());
		return (!r.isEmpty() && !r.equals(m));
	}
	
	/**
	 * Returns whether this instance is fully outside the room boundary.
	 * @return whether this instance is fully outside the room boundary
	 */
	public boolean isOutsideRoom(){
		Rectangle r = getMask().intersection(GameController.getRoomRectangle());
		return (r.isEmpty());
	}
	
	/**
	 * Call this method to destroy an instance. It then performs the destroy event and cleans up the remains.
	 */
	public void destroy(){
		ObjectController.removeObject(this); //this instance will be destroyed shortly; within a step
	}
	
	/**
	 * Returns this GameObject's mask by getting it from its Sprite.
	 */
	public Rectangle getMask(){
		return sprite.getMask(motion.getPosition());
	}
	
	/**
	 * A helpful method that draws this instance's mask
	 * @param g The graphics system to draw in
	 */
	public void drawMask(Graphics g){
		//updateMask();
		Rectangle mask = getMask();
		g.drawRect(mask.x, mask.y, mask.width, mask.height);
	}
	
	/* This is old and outdated, from before the Sprite class.
	/**
	 * A method that moves the instance's mask to its current position.
	 *
	public void updateMask(){
		mask.x = position.x - spriteCenter().x;
		mask.y = position.y - spriteCenter().y;
	}
	*/
	
	/**
	 * You should NOT call this method. 
	 * It updates the position from <code>positionBuffer</code>.
	 */
	public void passPositionThroughBuffer(){
		motion.passPositionThroughBuffer();
	}
	
	
	/*
	/**
	 * 
	 * TODO added much later; should this be in Motion? i haven't thought about it
	 * TODO Is this method useful???<p> it should be in collision manager, and ***make sure to updateMask();***
	 * Calculates the next position this instance will move to, based on hspeed and vspeed / speed and direction. 
	 * @see updatePosition()
	 * @return the next position this instance will move to
	 /
	public boolean aboutToCollide(){
		if (!solid)
			return false;
		
		Point myNextPos = getNextPosition();
		Point otherNextPos;
		Rectangle myNextMask = new Rectangle(myNextPos.x, myNextPos.y, mask.width, mask.height);
		Rectangle otherNextMask;
		for (GameObject other : ObjectController.getAllObjects()){
			otherNextPos = other.getNextPosition();
			otherNextMask = new Rectangle(otherNextPos.x, otherNextPos.y, other.mask.width, other.mask.height);
			
			if (!equals(other) && !other.solid && myNextMask.intersects(otherNextMask)){
				return true;
			}
		}
		return false;
	}
	*/
	
	public String toString(){
		int index = super.toString().indexOf('.');
		return  super.toString().substring(index + 1);
	}
	
	public static void initializeCollisionGroups(Class<? extends GameObject> c) 
	{
		Class<? extends GameObject>[] collisionList;
		try {
			collisionList = (Class<? extends GameObject>[]) c.getDeclaredMethod("getCollisionList", null).invoke(null, new Object[] {});
			System.out.println("adding " + c.getSimpleName() + "'s collision lists");
			
			ObjectController.initializeCollisionGroups(c, collisionList);
		} catch (NoSuchMethodException e) {
			//ignore
			System.out.println("* * * * * * * * * * * ***IGNORING " + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Class<? extends GameObject>[] getCollisionList(){
		throw new IllegalStateException(" GameObject.getCollisionList() should be overidden by its subclasses");
	}
	
	
	
	//methods to override



	/**
	 * The event performed when an instance is destroyed.
	 */
	public abstract void createEvent();
	
	/**
	 * The event performed when an instance is destroyed.
	 */
	public abstract void destroyEvent();
	
	/**
	 * The event performed when this instance collides with another instance. A switch statement is advised.
	 */
	public abstract void collisionEvent(GameObject other);		

	/**
	 * The event that is performed when this instance intersects with the boundary of the room.
	 */
	public abstract void intersectBoundaryEvent();

	/**
	 * The event that is performed when this instance is fully outside of the room.
	 */
	public abstract void outsideRoomEvent();

	/**
	 * A basic drawing method that draws the sprite. If overridden, a call to super.draw(g) can be used to also draw the sprite.
	 * @param g The graphics system to draw in
	 */
	public void draw(Graphics g){
		Point corner = sprite.getImagePosition(motion.getPosition());
		((Graphics2D)g).drawImage(sprite.getCurrentImage(), corner.x, corner.y, null);
		
		//g.setColor(Color.BLUE);
		//drawMask(g);
		
		//g.setColor(Color.CYAN);
		//Utilities.drawDot(g, motion.position());
	}
	
}
