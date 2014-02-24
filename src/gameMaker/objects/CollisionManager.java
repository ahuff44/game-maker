package gameMaker.objects;

import gameMaker.GameController;

import java.util.ArrayList;

public class CollisionManager {
	

	
	//fields
	
	
	
	private ArrayList<CollisionGroup> collisionGroups;
	
	
	
	//constructors
	
	
	
	public CollisionManager() {
		collisionGroups = new ArrayList<CollisionGroup>();
	}
	
	
	
	//methods
	
	
	
	public boolean addGroup(CollisionGroup group) {
		if (!collisionGroups.contains(group)){
			collisionGroups.add(group);
			GameController.debugln("collision group added: " + group);
			return true;
		} else
			return false;
	}
	
	public void checkCollisions(){
		for (CollisionGroup group : collisionGroups) {
			group.checkCollisions();
		}
	}
	
	public ArrayList<CollisionGroup> getCollisionGroups() {
        return collisionGroups;
    }
	
    public void removeGroup(CollisionGroup group) {
    	collisionGroups.remove(group);
    }
    
    /**
     * Clears the list of CollisionGroups.
     */
    public void clearGroups() {
		collisionGroups.clear();
    }
    
    public boolean addObject(GameObject obj){
		boolean b = false;
		GameController.debugln("CollisionManager.addObject("+obj+")");
		for (CollisionGroup group : collisionGroups)
			if (group.add(obj))
				b = true;
		return b;
    }
    
	public boolean removeObject(GameObject obj) {
		boolean b = false;
		for (CollisionGroup group : collisionGroups) 
			if (group.remove(obj))
				b = true;
		return b;
	}
	
	public void printContents(){
		System.out.println(this + ":");
		for (CollisionGroup group : collisionGroups) 
			System.out.println(" " + group.deepToString());
	}
    
}
