package ai1.squares.model;

/** Puzzle Move Direction. */
public enum MoveDirection {

	UP("Up"),
	DOWN("Down"),
	LEFT("Left"),
	RIGHT("Right");
	
	/** Direction name. */
	private String name;
	
	MoveDirection(String name) {
        this.name = name;
    }
	
    public String getName() { 
    	return name; 
    }
    
	@Override
	public String toString() {
		return getName();
	}
}
