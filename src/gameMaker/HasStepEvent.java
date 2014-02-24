package gameMaker;


public interface HasStepEvent {
	
	/**
	 * The event that is performed at the beginning of every "step" (every 30 milliseconds).
	 */
	void beginStepEvent();
	
	/**
	 * The event that is performed every "step" (every 30 milliseconds).
	 */
	void stepEvent();
	
	/**
	 * The event that is performed at the end of every "step" (every 30 milliseconds).
	 */
	void endStepEvent();
	
}
