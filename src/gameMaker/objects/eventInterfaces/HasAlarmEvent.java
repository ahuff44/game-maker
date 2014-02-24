package gameMaker.objects.eventInterfaces;

import gameMaker.objects.AlarmController;

public interface HasAlarmEvent {

	public AlarmController getAlarmController();

	/**
	 * This method is called by AlarmController when one if its alarms reaches zero.
	 * @param alarmId The id of the alarm that was called
	 */
	public void alarmEvent(int alarmId);
	
}
