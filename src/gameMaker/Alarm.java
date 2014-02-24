package gameMaker;

public abstract class Alarm implements Runnable{

	private int value;
	private int lastValue;
	
	private GameObject owner;
	
	public Alarm(GameObject owner){
		this(owner, 0);
	}
	
	public Alarm(GameObject owner, int value){
		this.owner = owner;
		this.value = value;
	}
	
	public void set(int newValue){//TODO make this only accessible to AlarmController
		value = newValue;
		lastValue = value;
	}
	
	/**
	 * Sets this alarm to its last set value
	 * @param newValue
	 */
	public void reset(){
		value = lastValue;
	}

	public int getValue(){
		return value;
	}

	public int getLastValue(){
		return lastValue;
	}
	
	public void step() {
		if (decrement())
			try {
				performAction();
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
	
	/**
	 * Decrements the value of this alarm and then returns whether it is 0
	 * @return whether this alarm is at 0 and should trigger an event
	 */
	public boolean decrement(){
		return ((--value) == 0);
	}
	
	public void performAction(){
		if (owner.getAlive())
			run();
		else
			destroy();
	}
	
	public GameObject getOwner(){
		return owner;
	}
	
	//TODO this might be able to return a java.util.concurrent.Future<Boolean> instead of being void, but that seems WAY overcomplicated
	public void destroy(){
		AlarmController.remove(this);
	}
	
}
