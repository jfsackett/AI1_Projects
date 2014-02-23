package ai1.search;


/** This provides mechanisms for breadth-first search. Strategy pattern. */
public class BreadthFirstSearchStrategy extends AbstractSearchStrategy {

	/** Get next node from frontier. */
	public Node getNext() {
		// Get front node for expansion for breadth-first.
		Node expandNode = (hasMoreNodes()) ? frontierNodes.remove(0) : null;
		if (expandNode == null) {
			return expandNode;
		}
		
		// Remove from frontier cache.
		frontierNodeSet.remove(expandNode);
		
		// Add to expanded set.
		expandedNodes.add(expandNode);
		
		return expandNode;
	}
	
	/** Add node to frontier if not already there.. Add to the end of list for breadth first. */
	public void add(Node newNode) {
		// Already expanded this node? 
		if (expandedNodes.contains(newNode)) {
			return;
		}
		
		// Duplicate node in frontier nodes?
		if (frontierNodeSet.contains(newNode)) {
			return;
		}
		
		// Add to the end of list.
		frontierNodes.add(newNode);
		
		// Add to frontier cache.
		frontierNodeSet.add(newNode);
	}
	
}
