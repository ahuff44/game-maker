package gameMaker;

//TODO add support for a constructor that you pass one/two array(s) of specific instances; would this be possible??? (well sure, but it take some WORK)
//TODO make the implementation of "reflexive" better; restructure things

import java.util.ArrayList;
import java.util.Arrays;

/** 
 * This class represents groups of GameObjects that should be checked against each other for collisions. 
 * 
 * Having this makes collision detection much more efficient than a naive O(n^2) approach
 */
public class CollisionGroup {
	
	private ArrayList<GameObject> instanceListA, instanceListB;
	private Class<? extends GameObject> classA, classB;
	/**
	 * Whether or not this collision group should check subclasses to determine whether objects fit into this group.
	 */
	private boolean checkSubclasses;
	
	/**
	 * Whether this group represents a collision between instances of the same object
	 */
	private boolean reflexive;
	
	/**
	 * Constructs this collision group between instances of one object.
	 * <p>
	 * Make checkSubclasses false in order to be more efficient, although I'm not sure how much of a performance benefit is gained..
	 * @param classA the class to use in this collision group
	 * @param checkSubclasses whether to check subclasses of classA and classB when checking whether or not objects belong to this collision group
	 */
	public CollisionGroup(Class<? extends GameObject> classA, boolean checkSubclasses) {
		this(classA, classA, checkSubclasses);
	}
	
	/**
	 * Constructs this collision group between instances of different objects.
	 * <p>
	 * Make checkSubclasses false in order to be more efficient, although I'm not sure how much of a performance benefit is gained..
	 * @param classA the first class to use in this collision group
	 * @param classB the second class to use in this collision group
	 * @param checkSubclasses whether to check subclasses of classA and classB when checking whether or not objects belong to this collision group
	 */
	public CollisionGroup(Class<? extends GameObject> classA, Class<? extends GameObject> classB, boolean checkSubclasses) {
		this.classA = classA;
		this.classB = classB;
		reflexive = (classA.equals(classB));
		this.checkSubclasses = checkSubclasses;
		instanceListA = new ArrayList<GameObject>();
		instanceListB = new ArrayList<GameObject>();
		for (GameObject obj : ObjectController.getAllObjects())
			add(obj);
	}
	
	public ArrayList<GameObject> getInstanceListA() {
		return instanceListA;
	}

	public ArrayList<GameObject> getInstanceListB() {
		return instanceListB;
	}
	
	public Class<? extends GameObject> getClassA() {
		return classA;
	}
	
	public Class<? extends GameObject> getClassB() {
		return classB;
	}
	
	public boolean getCheckSubclasses() {
		return checkSubclasses;
	}
	
	/**
	 * Returns whether c "fits" into this collision group, aka if c is either classA, classB, or one of their subclasses (if checkSubclasses == true)
	 * @param c the class to check
	 * @return whether c "fits" into this collision group
	 */
	public boolean fits(Class<? extends GameObject> c){
		if (c == classA || c == classB)
			return true;
		if (checkSubclasses)
			return (FileFinder.isSubclass(c, classA) || FileFinder.isSubclass(c, classB));
		return false;
	}
	
	/**
	 * Returns whether the GameObject obj "fits" into this collision group, aka if the obj is an instance of either classA, classB, or one of their subclasses (if checkSubclasses == true)
	 * @param obj the object to check
	 * @return whether the GameObject obj "fits" into this collision group
	 */
	public boolean fits(GameObject obj){//TODO obj v inst
		return fits(obj.getClass());
		/*if (obj.getClass() == classA || obj.getClass() == classB)
			return true;
		if (checkSubclasses)
			return (isChild(obj.getClass(), classA) || isChild(obj.getClass(), classB));
		return false;*/
	}


	/**
	 * Returns whether c "fits" into this collision group's classA, aka if c is either classA or one of its subclasses (if checkSubclasses == true)
	 * @param c the class to check
	 * @return whether c "fits" into this collision group's classA
	 */
	public boolean fitsClassA(Class<? extends GameObject> c){
		if (c == classA)
			return true;
		if (checkSubclasses)
			return (FileFinder.isSubclass(c, classA));
		return false;
	}
	
	/**
	 * Returns whether the GameObject obj "fits" into this collision group's class A
	 * @param obj the object to check
	 * @return whether the GameObject obj "fits" into this collision group's class A
	 */
	public boolean fitsClassA(GameObject obj){//TODO obj v inst
		return fitsClassA(obj.getClass());
		/*if (obj.getClass() == classA)
			return true;
		if (checkSubclasses)
			return (isChild(obj.getClass(), classA));
		return false;*/
	}
	
	/**
	 * Returns whether c "fits" into this collision group's classB, aka if c is either classB or one of its subclasses (if checkSubclasses == true)
	 * @param c the class to check
	 * @return whether c "fits" into this collision group's classB
	 */
	public boolean fitsClassB(Class<? extends GameObject> c){
		if (c == classB)
			return true;
		if (checkSubclasses)
			return (FileFinder.isSubclass(c, classB));
		return false;
	}

	/**
	 * Returns whether the GameObject obj "fits" into this collision group's class B
	 * @param obj the object to check
	 * @return whether the GameObject obj "fits" into this collision group's class B
	 */
	public boolean fitsClassB(GameObject obj){//TODO obj v inst
		return fitsClassB(obj.getClass());
		/*if (obj.getClass() == classB)
			return true;
		if (checkSubclasses)
			return (isChild(obj.getClass(), classB));
		return false;*/
	}
	
	/**
	 * Adds obj to this collision group, if obj fits into one of the classes in this CollisionGroup.
	 * @param obj the object to add
	 * @return whether the object was added
	 */
	public boolean add(GameObject obj) {
		boolean added = false;
		if (obj.getSolid()){
			if (fitsClassA(obj)) {
				instanceListA.add(obj);
				GameController.debugln("inst "+obj+" added to " + classA.getSimpleName() + " in " + this);
				added = true;//The below comment is also why you can't simply return true here
			}//do NOT put an "else" here; what if the user wants to collide objects with themselves?
			if (fitsClassB(obj)) {
				instanceListB.add(obj);
				GameController.debugln("inst "+obj+" added to " + classA.getSimpleName() + " in " + this);
				added = true;
			}
		}
		return added;
	}	
	
	public boolean remove(GameObject obj) {
		boolean a = instanceListA.remove(obj);
		boolean b = instanceListB.remove(obj);
		return (a || b);
		// This CANNOT be condensed into one line due to short-circuit evaluation
	}
	
	public void checkCollisions() {
		for (GameObject inst1 : instanceListA){
			for (GameObject inst2 : instanceListB){
				if (inst1 != inst2 && inst1.isColliding(inst2)){//yes, != instead of !.equals() b/c it's just making sure it isn't colliding something with itself
					inst1.collisionEvent(inst2);
					if (!reflexive)
						inst2.collisionEvent(inst1);
				}
			}
		}
	}
	
	public String toString(){
		return classA.getSimpleName() + "-" + classB.getSimpleName();
	}
	
	public String deepToString() {
		return this + ":\n" +
					  "  " + classA + ": " + Arrays.toString(instanceListA.toArray()) + "\n" +
					  "  " + classB + ": " + Arrays.toString(instanceListB.toArray());
	}

	/**
	 * NOTE: This method only checks whether the *classes* of each group are equal, not whether the instances contained in each are equal
	 * @param obj
	 * @return whether this object "equals" obj through what types of classes each checks
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		CollisionGroup c = (CollisionGroup) obj;
		return (classA == c.classA && classB == c.classB);
	}
	
}
