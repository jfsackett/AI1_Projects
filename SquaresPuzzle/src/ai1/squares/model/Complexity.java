package ai1.squares.model;

/** Puzzle Complexity. */
public enum Complexity {

	EASY("Easy"),
	MEDIUM("Medium"),
	HARD("Hard"),
	CUSTOM("Custom");
	
	/** Complexity name. */
	private String name;
	
	Complexity(String name) {
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
