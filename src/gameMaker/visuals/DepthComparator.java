package gameMaker.visuals;

import gameMaker.objects.GameObject;

import java.util.Comparator;

public class DepthComparator implements Comparator<GameObject> {

	public enum SortOrder {DEEP_FIRST, SHALLOW_FIRST};
	private SortOrder order;
	
	public DepthComparator(SortOrder order){
		this.order = order;
	}
	
	@Override
	public int compare(GameObject obj1, GameObject obj2) {
		int val = obj1.getSprite().getDepth() - obj2.getSprite().getDepth();
		if (order == SortOrder.SHALLOW_FIRST)
			return val;
		else
			return -val;
	}

}
