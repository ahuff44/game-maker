package gameMaker;


public class ModifiedKeyEvent {
		
	public Integer keyCode;
	public KeyEventType type;
	
	public ModifiedKeyEvent(Integer code, KeyEventType type){
		keyCode = code;
		this.type = type;
	}
	
	public String toString(){
		return type + ": " + keyCode;
	}
	
	public boolean equals(Object other){
		if (other == null)
			return false;
		ModifiedKeyEvent mke = (ModifiedKeyEvent) other;
		return (mke.keyCode == keyCode && mke.type.equals(type));
	}
	
}
