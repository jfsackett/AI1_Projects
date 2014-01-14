package ai1.squares.model;

/** Solution Search Method. */
public enum SearchMethod {

	BREADTH("Breadth First"),
	DEPTH("Depth First"),
	GREEDY_MIN_TILES_WRONG("Greedy Min Wrong"),
	ASTAR_MIN_TILES_WRONG("A* Min Wrong"),
	ASTAR_MIN_DISTANCE("A* Min Dist");
	
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
