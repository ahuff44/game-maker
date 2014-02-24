package gameMaker.objects;

import gameMaker.utilities.MiscUtilities;

import java.awt.Point;

/**
 * Represents the position and speed of an object.
 * 
 * Also stores the previous position of the object.
 * 
 * @author Andrew
 */
public class Motion {
		
	private Point position;
	private Point positionBuffer;
	private Point previousPosition;
	
	private double speed;//these four variables update each other when one is changed
	private double direction;//these top two are "polar movements"
	private double hspeed;//these bottom two are "cartesian movements"
	private double vspeed;

	public Motion(Point pos){		
		position = (Point) pos.clone();
		positionBuffer = (Point) position.clone();
		previousPosition = (Point) position.clone();
		
		speed = 0;
		direction = 0;
		hspeed = 0;
		vspeed = 0;
	}
	
	/**
	 * Call this method to move an instance to a new position.
	 * @param position the position to set
	 */
	public void setPosition(Point position) {
		positionBuffer = (Point) position.clone();
	}
	
	/**
	 * Call this method to move an instance to a new position.
	 * @param position the position to set
	 */
	public void setPosition(int x, int y) {
		setPosition(new Point(x, y));
	}

	/**
	 * Changes this object's position by posChange. eg changePosition(new Point(-8, 2)); moves this object 8 pixels to the left and 2 pixels down
	 * @param posChange the relative position to move to, in pixels
	 */
	public void changePosition(Point posChange){
		setPosition(getX() + posChange.x, getY() + posChange.y);
	}

	/**
	 * Changes this object's position by xChange and yChange. eg changePosition(-8, 2); moves this object 8 pixels to the left and 2 pixels down
	 * @param posChange the relative position to move to, in pixels
	 */
	public void changePosition(int xChange, int yChange){
		setPosition(getX() + xChange, getY() + yChange);
	}
	
	/**
	 * Sets the x-coordinate to a new value.
	 * @param x the x-coordinate to set
	 */
	public void setX(int x) {
		setPosition(x, getY());
	}
	
	/**
	 * Sets the y-coordinate to a new value.
	 * @param y the y-coordinate to set
	 */
	public void setY(int y) {
		setPosition(getX(), y);
	}

	/**
	 * Adds xChange to the x-coordinate of this instance
	 * @param xChange
	 */
	public void changeX(int xChange) {
		changePosition(xChange, 0);
	}

	/**
	 * Adds yChange to the y-coordinate of this instance
	 * @param yChange
	 */
	public void changeY(int yChange) {
		changePosition(0, yChange);
	}

	/**
	 * You should NOT call this method. 
	 * It updates the position from <code>positionBuffer</code>.
	 */
	public void passPositionThroughBuffer(){
		position = (Point) positionBuffer.clone();
	}

	/**
	 * A shortcut method that moves this instance to its previous position.
	 * @param posChange the relative position to move to, in pixels
	 */
	public void gotoPreviousPosition(){
		setPosition(getPreviousPosition());
	}

	/**
	 * @param speed the speed to set, in pixels per second
	 */
	public void setSpeed(double spd) {
		speed = spd;
		updateCartesianMovements();
	}

	/**
	 * Adds sChange to this instance's speed
	 * @param sChange
	 */
	public void changeSpeed(double sChange){
		setSpeed(getSpeed() + sChange);
	}

	/**
	 * Adds sChange to this instance's speed
	 * @param sChange
	 */
	public void scaleSpeed(double sScalar){
		setSpeed(getSpeed() * sScalar);
	}

	/**
	 * @param direction the direction to set, in degrees
	 */
	public void setDirection(double dir) {
		direction = MiscUtilities.mod(dir, 360);
		updateCartesianMovements();
	}
	
	/**
	 * Adds dChange to this instance's direction
	 * @param dChange
	 */
	public void changeDirection(double dChange){
		setDirection(getDirection() + dChange);
	}

	/**
	 * Adds dChange to this instance's direction
	 * @param dChange
	 */
	public void scaleDirection(double dScalar){
		setDirection(getDirection() * dScalar);
	}

	/**
	 * @param hspeed the horizontal speed to set, in pixels per second
	 */
	public void setHspeed(double hspeed) {
		this.hspeed = hspeed;
		updatePolarMovements();
	}
	
	/**
	 * Adds hChange to this instance's hSpeed
	 * @param hChange
	 */
	public void changeHspeed(double hChange) {
		setHspeed(getHspeed() + hChange);
	}

	/**
	 * Adds hChange to this instance's hSpeed
	 * @param hChange
	 */
	public void scaleHspeed(double hScalar) {
		setHspeed(getHspeed() * hScalar);
	}

	/**
	 * @param hspeed the horizontal speed to set, in pixels per second
	 */
	public void setVspeed(double vspeed) {
		this.vspeed = vspeed;
		updatePolarMovements();
	}
	
	/**
	 * Adds vChange to this instance's vSpeed
	 * @param vChange
	 */
	public void changeVspeed(double vChange){
		setVspeed(getHspeed() + vChange);
	}

	/**
	 * Adds vChange to this instance's vSpeed
	 * @param vChange
	 */
	public void scaleVspeed(double vScalar){
		setVspeed(getHspeed() * vScalar);
	}

	/**
	 * Updates vspeed and hspeed (the "cartesian movements") according to speed and direction (the "polar movements")
	 */
	private void updateCartesianMovements() {
		hspeed = speed * Math.cos(Math.toRadians(direction));
		vspeed = -speed * Math.sin(Math.toRadians(direction));//negative because of java's coordinate system's orientation
	}
	
	/**
	 * Updates speed and direction (the "polar movements") according to hspeed and vspeed (the "cartesian movements")
	 */
	private void updatePolarMovements() {
		speed = Math.hypot(hspeed, -vspeed);//negative vspeed because of java's coordinate system's orientation
		direction = Math.toDegrees( Math.atan2(-vspeed, hspeed) );//negative vspeed because of java's coordinate system's orientation
		direction = MiscUtilities.mod(direction, 360);//put direction in the proper range
	}
	
	/**@return this instance's position.*/ public Point getPosition() { return (Point) position.clone(); }
	/**@return ***Don't call this. It's almost always the same as getPosition(), and it might introduce weird bugs*/ public Point getPositionBuffer() { return (Point) positionBuffer.clone(); }
	/**@return this instance's previous position*/ public Point getPreviousPosition() { return (Point) previousPosition.clone(); }

	/**@return this instance's speed*/ public double getSpeed() { return speed; }
	/**@return this instance's direction*/ public double getDirection() { return direction; }
	/**@return this instance's horizontal speed*/ public double getHspeed() { return hspeed; }
	/**@return this instance's vertical speed*/ public double getVspeed() { return vspeed; }

	/**A convenience method, equivalent to <code>getPosition().x</code>*/ public int getX() { return position.x; }
	/**A convenience method, equivalent to <code>getPosition().y</code>*/ public int getY() { return position.y; }
	
	/**
	 * A convenience method, equivalent to <code>getPreviousPosition().x</code>
	 */
	public int getXprevious() {
		return previousPosition.x;
	}
	
	/**
	 * A convenience method, equivalent to <code>getPreviousPosition().y</code>
	 */
	public int getYprevious() {
		return previousPosition.y;
	}
	
	/**
	 * Returns the point relative to this object, or:<br>
	 * new Point(getX() + rx, getY() + ry);
 	 * <br>This does not change this position
	 * @param rx
	 * @param ry
	 * @return
	 */
	public Point relativePoint(int rx, int ry) {
		return new Point(getX() + rx, getY() + ry);
	}

	/**
	 * Returns the point relative to this object, or:<br>
 	 * new Point(getX() + p.x, getY() + p.y);
 	 * <br>This does not change this position
	 * @param p
	 * @return
	 */
	public Point relativePoint(Point p) {
		return new Point(getX() + p.x, getY() + p.y);
	}

	/**
	 * This is a background processing method that updates the position of the instance according to its speed and direction.
	 * 
	 * DON'T CALL THIS METHOD.
	 */
	public void updatePosition(){
		setPosition(getNextPosition());
	}
	
	/**
	 * Calculates the next position this instance will move to, based on hspeed and vspeed / speed and direction. 
	 * @see updatePosition()
	 * @return the next position this instance will move to
	 */
	public Point getNextPosition(){
		return new Point((int)(getX() + hspeed),
						 (int)(getY() + vspeed));
	}

	/**
	 * This is a background processing method that gets called near the end of each step, updating the value of previousPosition.
	 * 
	 * DON'T CALL THIS METHOD.
	 */
	public void updatePreviousPosition(){
		setPreviousPosition(getPosition());
	}
	
	/**
	 * You should NOT call this method.
	 * @param position the position to set as the previous position
	 */
	public void setPreviousPosition(Point prevPos) {
		previousPosition = (Point) prevPos.clone();
	}

}
