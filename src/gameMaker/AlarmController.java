package gameMaker;

import java.util.ArrayList;


/**
 * 
 * <p>
 * ***********This is a modified version of AlarmController that has no real power; it ticks when GameController tells it to. This statement takes precedence over the following javadocs that I haven't bothered to update
 * <p>

 * AlarmController is a controller that has a certain number of alarms in it. (This number can only be set once in an AlarmController object)
 * These alarms (with ID's ranging from 0 to the number of alarms - 1))' values are decremented each "step".
 * A "step" is a set amount of milliseconds that pass between each alarm update. 1000 / 40 = 25 is the suggested value for this number. (This number can only be set once in an AlarmController object)
 * When an alarm reaches 0 it triggers a call to executeAlarm in it's owner.
 * The owner is an instance (which implements AlarmInterface) that has a method executeAlarm, which is where you put your code for what you want to happen when an alarm is called. (A switch statement within this method is suggested)
 * <p>
 * Note: After an alarm reaches 0 it continues being decremented each step.
 * 
 * @author Andrew Huff
 */
public class AlarmController{

	
	
	//fields
	
	
	
	private static ArrayList<Alarm> alarms = new ArrayList<Alarm>(32);
	private static ArrayList<Alarm> toRemove = new ArrayList<Alarm>(32);
	
	
	
	//methods
	
	
	
	/**
	 * Sets an alarm to a value
	 * @param alarmId The id of the alarm to set
	 * @param alarmLength What value to set the alarm to (How many steps until the alarm triggers)
	 * @throws IllegalAccessException if add(a) hasn't been previously called
	 */
	public static void setAlarm(Alarm a, int alarmLength){
		if (!alarms.contains(a))
			alarms.add(a);
		a.set(alarmLength);
	}

	public static void remove(Alarm a){
		toRemove.add(a);
	}
	
	public static void decrementAlarms(){
		ArrayList<Alarm> currentAlarms = (ArrayList<Alarm>) alarms.clone();
		for (Alarm a : currentAlarms)
			a.step();
				
		ArrayList<Alarm> currentToRemove= (ArrayList<Alarm>) toRemove.clone();
		for (Alarm a : currentToRemove)
			alarms.remove(a);
	}

}
