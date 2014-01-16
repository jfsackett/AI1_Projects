package ai1.search;

import java.util.List;

/** Implementations provide different search strategies. Strategy pattern. */ 
public interface SearchStrategy {

	/** More nodes on frontier? */
	boolean hasMoreNodes();
	
	/** Get next node from frontier. */
	Node getNext();
	
	/** Add node to frontier. */
	void add(Node node);
	
	/** Add nodes to frontier. */
	void add(List<Node> nodes);
	
}
