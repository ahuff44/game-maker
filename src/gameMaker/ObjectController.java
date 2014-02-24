package gameMaker;

/** 
 * some of the javadocs are outdated. deal.
 */


import java.awt.event.MouseEvent;
import java.lang.reflect.Modifier;
import java.util.ArrayList;


public class ObjectController{
	
	
	
	//fields/modifiers/accessors
	
	
	
	private static CollisionManager collisionManager;// = new CollisionManager();//TODO kill the initializion of this once initializeCollisionManager() works

	/**
	 * An array that contains all instances currently existing in the game.
	 * When an instance is created, there is a slight delay while it is stored in the buffer array <code>objectsToAdd</code>.
	 * This delay is less than a step.
	 * @see objectsToCreate
	 * @see objectsToDestroy
	 */
	private static ArrayList<GameObject> allObjects;
	
	/** 
	 * A buffer array for <code>allObjects</code>, so that concurrent modification errors aren't thrown.
	 * After each for-each in each <code>GameController</code> step, the contents of <code>objectsToCreate</code> are moved into <code>allObjects</code>
	 * @see allObjects
	 */
	private static ArrayList<GameObject> objectsToCreate;
	
	/** 
	 * A buffer array for <code>allObjects</code>, so that concurrent modification errors aren't thrown.
	 * After each for-each in each <code>GameController</code> step, the contents of <code>objectsToDestroy</code> are removed from <code>allObjects</code>
	 * @see allObjects
	 */
	private static ArrayList<GameObject> objectsToDestroy;
	
	
	
	//methods
	
	
	
	/**
	 * Adds all collision groups specified in each <? extends GameObject>'s collisionList, which is initialized it their setCollisionList() methods
	 */
	public static void initialize(){//TODO make this work
		collisionManager = new CollisionManager();
		allObjects = new ArrayList<GameObject>();
		objectsToCreate = new ArrayList<GameObject>();
		objectsToDestroy = new ArrayList<GameObject>();
		/*SubclassFinder finder = new SubclassFinder(GameObject.class);
		ArrayList<Class> classes = finder.find("spaceGame");*/
	}
	
	/**
	 * Moves stuff through three Buffers:
	 * <p>-creates objects in <code>objectsToCreate</code>
	 * <p>-destroys objects in <code>objectsToDestroy</code>
	 * <p>-actually changes the position of all objects to their positionBuffer
	 * @see allObjects
	 * @see objectsToCreate
	 * @see objectsToDestroy
	 * @see GameObject.positionBuffer
	 */
	public static void updateBuffers() {
		while (objectsToCreate.size() > 0){//TODO this should work, but I haven't tested/debugged/entirely thought through this "while" statement
			ArrayList<GameObject> currentObjectsToCreate = (ArrayList<GameObject>) objectsToCreate.clone();//this is necessary b/c some objects will create other objects in their create events
			for(GameObject obj : currentObjectsToCreate){
				collisionManager.addObject(obj);
				objectsToCreate.remove(obj);
				allObjects.add(obj);
				obj.setAlive(true);
				obj.createEvent();
			}
		}

		while (objectsToDestroy.size() > 0){//TODO this should work, but I haven't tested/debugged/entirely thought through this "while" statement
			ArrayList<GameObject> currentObjectsToDestroy = (ArrayList<GameObject>) objectsToDestroy.clone();//this is necessary b/c some objects will destroy other objects in their destroy events
			for(GameObject obj : currentObjectsToDestroy){
				obj.destroyEvent();
				obj.setAlive(false);
				allObjects.remove(obj);
				objectsToDestroy.remove(obj);
				collisionManager.removeObject(obj);
			}
		}
		
		for(GameObject obj : allObjects)
			obj.passPositionThroughBuffer();
	}
	
	public static void addObject(GameObject obj) {//TODO obj should really be inst here; change later
		objectsToCreate.add(obj);
	}
	
	public static void removeObject(GameObject obj) {
		objectsToDestroy.add(obj);
	}
	
	/**
	 * Returns a ***copy*** of an array of all Objects in existence. The array is a copy, the objects are not
	 * @return a copy of an array of all Objects in existence
	 */
	public static ArrayList<GameObject> getAllObjects() {
		return (ArrayList<GameObject>) allObjects.clone();
	}

	
	public static void clearAllObjects() {
		allObjects.clear();
	}
	
	public static void initializeCollisionGroups(Class<? extends GameObject> type, Class<? extends GameObject>[] list){
		boolean b = Modifier.isAbstract(type.getModifiers());
		System.out.println(type + "  w/  " + list + ":\n  is abstract class:" + b);
		for (Class<? extends GameObject> c : list){
			CollisionGroup g = new CollisionGroup(type, c, b);
			System.out.println("******collision group added: " + g + "\n " + addCollisionGroup(g));
		}
	}
	
	/**
	 * Note that this does not actually call the events, it just passes them- the MouseEventHandler will act on them soon, within a step
	 */
	public static void forwardMouseEvent(MouseEvent ev) {
		MouseEventHandler.receiveMouseEvent(ev);
	}
	
	/**
	 * Note that this does not actually call the events, it just passes them- the KeyEventHandler will act on them soon, within a step
	 */
	public static void forwardKeyEvent(ModifiedKeyEvent mke) {
		KeyEventHandler.receiveKeyEvent(mke);
	}
	
	public static boolean addCollisionGroup(CollisionGroup c) {
		GameController.debugln("ObjectController.addCollisionGroup(" + c + ")");
		return collisionManager.addGroup(c);
	}
	
	public static void printCollisionManager(){
		collisionManager.printContents();
	}
	
	
	
	// methods called during GameController's actionPerformed() that call events in all existing objects, which runs the game:
	
	
	
	public static void callBeginStepEvents() {
		for (GameObject obj : allObjects)
			if (obj instanceof HasStepEvent)
				((HasStepEvent) obj).beginStepEvent();
		updateBuffers();
	}

	public static void callAlarmEvents() {
		/* outdated
		for (GameObject obj : allObjects)
			if (obj instanceof HasAlarmEvent)
				((HasAlarmEvent) obj).getAlarmController().decrementAlarms();
		*/
		AlarmController.decrementAlarms();
		updateBuffers();
	}

	public static void callMouseEvents() {
		ArrayList<HasMouseEvent> objects = new ArrayList<HasMouseEvent>();
		for (GameObject obj : allObjects)
			if (obj instanceof HasMouseEvent)
				objects.add((HasMouseEvent) obj);
		MouseEventHandler.performEvents(objects);
		updateBuffers();
	}

	public static void callKeyEvents() {
		ArrayList<HasKeyEvent> objects = new ArrayList<HasKeyEvent>();
		for (GameObject obj : allObjects)
			if (obj instanceof HasKeyEvent)
				objects.add((HasKeyEvent) obj);
		KeyEventHandler.performEvents(objects);
		updateBuffers();
	}

	public static void updatePositions() {
		for (GameObject obj : allObjects)
			obj.getMotion().updatePosition();
		updateBuffers();
	}
	
	public static void callStepEvents() {
		for (GameObject obj : allObjects)
			if (obj instanceof HasStepEvent)
				((HasStepEvent) obj).stepEvent();
		updateBuffers();
	}
	
	public static void checkCollisions(){
		
		//TODO later* find out the collisions/boundary/outside order in GM
		
		collisionManager.checkCollisions();
		updateBuffers();

		for (GameObject obj : allObjects)
			if (obj.isIntersectingBoundary())
				obj.intersectBoundaryEvent();
	
		updateBuffers();

		for (GameObject obj : allObjects)
			if (obj.isOutsideRoom())
				obj.outsideRoomEvent();

		updateBuffers();
	}

	public static void callEndStepEvents() {
		for (GameObject obj : allObjects)
			if (obj instanceof HasStepEvent)
				((HasStepEvent) obj).endStepEvent();
		updateBuffers();
	}

	public static void updatePreviousPositions() {
		for (GameObject obj : allObjects)
			obj.getMotion().updatePreviousPosition();
		updateBuffers();
	}

	/* TODO make these work; use isA during CollisionGroup.fits()?
	 
	 public static int instanceNumber(Class<? extends GameObject> instanceClass, boolean checkSubclasses) {
		int count = 0;
		for (GameObject obj : allObjects)
			if (obj.getClass() == instanceClass || (checkSubclasses && isA(obj, instanceClass)))
				count++;
		return count;
	}
	
	private static boolean isA(GameObject obj, Class<? extends GameObject> instanceClass) {
		Class<? extends GameObject> objClass = obj.getClass();
		while (!(objClass == Object.class || objClass == instanceClass)){
			objClass = objClass.getSuperclass();
		}
		return (objClass != Object.class);

	}*/
	
}
