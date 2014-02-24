package gameMaker.visuals;

//TODO add custom sprites, using Shape


import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Represents an Animation, combined with metadata including the object's bounding box, center point, and depth in the field of view
 * 
 * TODO: This still needs work. For instance, different frames of an Animation should be able to have different bounding boxes (probably?) but that's currently impossible
 * TODO: Clearly define the difference between Sprites and Animations
 * 
 * @author Andrew
 */
public class Sprite {

	
	//fields
	
	
	private Animation animation;
	private Rectangle bounds;
	private Point center;
	
	/**
	 * How "deep" the image is in the Room. Objects with low depths will be drawn on top of deeper objects
	 */
	private int depth;
		
	//accessors
	
	public Animation getAnimation(){
		return animation;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public Point getCenter(){
		return center;
	}

	public int getDepth() {
		return depth;
	}

	//modifiers
	
	public void setAnimation(Animation newAnimation){
		animation = newAnimation;
	}

	/** a convenience method*/
	public void setImage(Image img) {
		setAnimation(new Animation(img));
	}

	public void setBounds(Rectangle newBounds){
		bounds = newBounds;
	}
	
	public void setCenter(Point newCenter){
		center = newCenter;
	}
	
	public void setDepth(int newDepth){
		depth = newDepth;
	}
	
	
	//constructors
	//TODO make TONS of constructors that take lots of various combinations of arguments
	
	
	//Image constructors
	public Sprite(Image img){
		this(new Animation(img), new Point(0, 0), 0);
	}
	
	public Sprite(Image img, int drawDepth){
		this(new Animation(img), new Point(0, 0), drawDepth);
	}

	public Sprite(Image img, Point sprCenter){
		this(new Animation(img), sprCenter, 0);
	}
	
	public Sprite(Image img, Point sprCenter, int drawDepth){
		this(new Animation(img), sprCenter, drawDepth);
	}
	
	
	//Animation constructors
	public Sprite(Animation anim){
		this(anim, new Point(0, 0), 0);
	}
	
	public Sprite(Animation anim, int drawDepth){
		this(anim, new Point(0, 0), drawDepth);
	}

	public Sprite(Animation anim, Point sprCenter){
		this(anim, sprCenter, 0);
	}
	
	
	//mother constructor
	public Sprite(Animation anim, Point sprCenter, int drawDepth){
		animation = anim;
		center = (Point) sprCenter.clone();
		bounds = new Rectangle(getSize());
		depth = drawDepth;
	}
	
	
	//static methods
	
	
	public static Sprite getEmptySprite() {
		return new Sprite(Animation.getEmptyAnimation());
	}
	
	
	//instance methods

	
	/**
	 * Gets the current image from this Sprite's Animation
	 */
	public Image getCurrentImage() {
		return animation.getImageAndIncrement();
	}
	
	public final Dimension getSize(){
		if (animation != null)
			return new Dimension(animation.getWidth(), animation.getHeight());
		else
			return new Dimension(0, 0);
	}
	
	/**
	 * Returns the point where the top left corner of this Sprite should be drawn. This may be different than the top left corner of the collision bounds
	 * @param position the position
	 * @return the collision mask to use
	 */
	public Point getImagePosition(Point position){
		int x = position.x - center.x;
		int y = position.y - center.y;
		return new Point(x, y);
	}
	
	/**
	 * Builds and returns a collision mask by analyzing this Sprite's properties in conjunction with the position passed in (usually the position of the Game Object this Sprite is attached to)
	 * @param position the position
	 * @return the collision mask to use
	 */
	public Rectangle getMask(Point position){
		int x = bounds.x + position.x - center.x;
		int y = bounds.y + position.y - center.y;
		return new Rectangle(x, y, bounds.width, bounds.height);
	}
	
	/**
	 * Moves ONLY the left boundary of the collision bounds to the left by the specified amount (incl. negative amounts) of pixels
	 * @param pixels
	 */
	public void stretchLeft(int pixels){
		bounds.x -= pixels;
		bounds.width += pixels; 
	}
	
	/**
	 * Moves ONLY the right boundary of the collision bounds to the right by the specified amount (incl. negative amounts) of pixels
	 * @param pixels
	 */
	public void stretchRight(int pixels){
		bounds.width += pixels; 
	}
	
	/**
	 * Moves ONLY the top boundary of the collision bounds up by the specified amount (incl. negative amounts) of pixels
	 * @param pixels
	 */
	public void stretchTop(int pixels){
		bounds.y -= pixels;
		bounds.height += pixels; 
	}
	
	/**
	 * Moves ONLY the bottom boundary of the collision bounds down by the specified amount (incl. negative amounts) of pixels
	 * @param pixels
	 */
	public void stretchBottom(int pixels){
		bounds.height += pixels; 
	}

}
