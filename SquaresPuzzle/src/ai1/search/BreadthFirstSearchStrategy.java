package ai1.search;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

/** This provides mechanisms for breadth-first search. Strategy pattern. */
public class BreadthFirstSearchStrategy extends AbstractSearchStrategy {

	/** Unexpanded nodes on the frontier. */
	ArrayList<Node> frontierNodes = new ArrayList<Node>();
	
	/** Expanded nodes already visited. */
	Set<Node> expandedNodes = new HashSet<Node>();
	
	/** Constructor. */
	public BreadthFirstSearchStrategy() {
	}

	/** More nodes on frontier? */
	public boolean hasMoreNodes() {
		return !frontierNodes.isEmpty();
	}
	
	/** Get next node from frontier. */
	public Node getNext() {
		return (hasMoreNodes()) ? frontierNodes.remove(0) : null;
	}
	
	/** Add node to frontier. Add to the end of list for breadth first. */
	public void add(Node newNode) {
		// Already expanded this node? 
		if (expandedNodes.contains(newNode)) {
			return;
		}
		
		// Duplicate node in frontier nodes?
		if (frontierNodes.contains(newNode)) {
			return;
		}
		
		// Add to expanded set.
		expandedNodes.add(newNode);
		
		// Add to the end of list for breadth first.
		frontierNodes.add(newNode);
	}
	
}
