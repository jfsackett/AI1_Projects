package ai1.squares.model;

/** Puzzle Move Direction. */
public enum MoveDirection {

	UP("Up"),
	DOWN("Down"),
	LEFT("Left"),
	RIGHT("Right");
	
	/** Direction name. */
	private String name;
	
	/** Constructor. */
	MoveDirection(String name) {
        this.name = name;
    }
	
	/** Accessor. */
    public String getName() { 
    	return name; 
    }
    
    /** Return string representation. */
	@Override
	public String toString() {
		return getName();
	}
}
