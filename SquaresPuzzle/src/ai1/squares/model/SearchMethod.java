package ai1.squares.model;

/** Solution Search Method. */
public enum SearchMethod {

	BREADTH_FIRST("Breadth First"),
	DEPTH_FIRST("Depth First"),
	ITERATIVE_DEEPENING("Iterative Deepening"),
	GREEDY_MIN_TILES_WRONG("Greedy Min Wrong"),
	GREEDY_MIN_DISTANCE("Greedy Min Distance"),
	ASTAR_MIN_TILES_WRONG("A* Min Wrong"),
	ASTAR_MIN_DISTANCE("A* Min Distance"),
	ASTAR_ITERATIVE_DEEPENING("A* Iterative Deepening");
	
	/** Search Method name. */
	private String name;
	
	/** Constructor. */
	SearchMethod(String name) {
        this.name = name;
    }
	
	/** Accessor. */
    public String getName() { 
    	return name; 
    }
    
    /** To string. */
	@Override
	public String toString() {
		return getName();
	}
}
