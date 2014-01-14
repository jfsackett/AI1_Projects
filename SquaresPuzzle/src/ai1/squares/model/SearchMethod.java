package ai1.squares.model;

/** Solution Search Method. */
public enum SearchMethod {

	BREADTH("Breadth First"),
	DEPTH("Depth First"),
	GREEDY_MAX_TILES_RIGHT("Greedy Max Right");
	
	/** Search Method name. */
	private String name;
	
	SearchMethod(String name) {
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
