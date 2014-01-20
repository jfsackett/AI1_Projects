package ai1.squares.model;

/** Puzzle Complexity. */
public enum Complexity {

	EASY("Easy"),
	MEDIUM("Medium"),
	HARD("Hard"),
	CUSTOM("Custom");
	
	/** Complexity name. */
	private String name;
	
	/** Constructor. */
	Complexity(String name) {
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
