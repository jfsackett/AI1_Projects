package ai1.squares.model;

/** Solution Search Method. */
public enum SearchMethod {

	BREADTH("Breadth First"),
	DEPTH("Depth First");
	
	/** Search Method name. */
	private String name;
	
	SearchMethod(String name) {
        this.name = name;
    }
	
    public String getName() { 
    	return name; 
    }
}
