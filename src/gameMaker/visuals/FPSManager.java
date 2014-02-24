package gameMaker.visuals;

public class FPSManager {
		
	private final int memory = 30;
	private long lastTime;

	private int[] timeData;
	private int pointer;//which index in timeData to write the next time data
	
	public FPSManager(int ideal){
		lastTime = System.currentTimeMillis();
		timeData = new int[memory];
		pointer = 0;
		for (int i = 0; i < memory; i++)
			timeData[i] = ideal;
	}
	
	public void update(){
		timeData[pointer] = (int) (System.currentTimeMillis() - lastTime);
		lastTime = System.currentTimeMillis();
		pointer = (pointer + 1) % memory;
	}
	
	public int getFPS(){
		int sum = 0;
		for (int data : timeData)
			sum += data;
		return (int) (1000 / (sum * 1.0/ memory));
	}
}
