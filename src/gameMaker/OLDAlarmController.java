package gameMaker;

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

public class OLDAlarmController{

	
	
	//fields
	
	
	
	private int[] alarms;
	private int[] lastValues;
	private int numAlarms;
	private HasAlarmEvent owner;
	
	
	
	//constructors
	
	
	
	/**
	 * Creates an AlarmController with the given parameters.
	 * -numberOfAlarms (The number of alarms to create space for) will be initialized to 16
	 * @param owner The instance that will receive executeAlarm events for this AlarmController 
	 */
	public OLDAlarmController(HasAlarmEvent owner){
		construct(16, owner);
	}
		
	/**
	 * Creates an AlarmController with the given parameters
	 * @param numberOfAlarms The number of alarms to create space for
	 * @param owner The instance that will receive executeAlarm events for this AlarmController 
	 */
	public OLDAlarmController(int numberOfAlarms, HasAlarmEvent owner){
		construct(numberOfAlarms, owner);
	}
	
	/**
	 * Sets the fields in this AlarmController
	 * @param numberOfAlarms The number of alarms to create space for
	 * @param millisecondsPerStep How many milliseconds there are per step in this AlarmController
	 * @param owner The instance that will receive executeAlarm events for this AlarmController 
	 */
	private void construct(int numberOfAlarms, HasAlarmEvent owner){
		this.owner = owner;
		alarms = new int[numberOfAlarms];
		lastValues = new int[numberOfAlarms];
		numAlarms = numberOfAlarms;
	}
	
	
	
	//methods
	
	
	
	/**
	 * Sets an alarm to a value
	 * @param alarmId The id of the alarm to set
	 * @param alarmLength What value to set the alarm to (How many steps until the alarm triggers)
	 */
	public void setAlarm(int alarmId, int alarmLength){
		alarms[alarmId] = alarmLength;
		lastValues[alarmId] = alarmLength;//record the last value that this alarm was set to, for use in resetAlarm()
	}
	
	/**
	 * Returns how many steps are left in alarm <code>alarmId</code>
	 * @param alarmId The id of the alarm
	 * @return how many steps are left in the alarm before it triggers
	 */
	public int getAlarm(int alarmId){
		return alarms[alarmId];
	}

	/**
	 * Returns the last value that alarm <code>alarmId</code> was set to
	 * @param alarmId The id of the alarm
	 * @return the last value that this alarm was set to
	 */
	public int getLastValue(int alarmId){
		return lastValues[alarmId];
	}
	
	/**
	 * Resets an alarm to its most recently set value
	 * @param alarmId The id of the alarm to set
	 */
	public void resetAlarm(int alarmId){
		alarms[alarmId] = lastValues[alarmId];
	}
	
	public void incrementAlarms() {
		for (int i = 0; i < numAlarms; i++){
			alarms[i]--;
			if (alarms[i] == 0)
				owner           .toString();//       .alarmEvent(i);
		}
	}

}
