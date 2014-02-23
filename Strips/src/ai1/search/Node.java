package ai1.search;

import java.util.List;

/** Represents a node in a search space. Implementations derive from this and the search mechanism is loosely coupled. */
public abstract class Node {
	/** Parent Node from where we arrived or null if initial node. */
	protected Node parentNode;
	
	/** Path cost to this node from initial state or zero if initial state. */
	protected int pathCost;
	
	/** String representation of the action which brought us to this node. */
	public abstract String getAction();
	
	/** Return list of successor nodes traversable to from this node. */
	public abstract List<Node> getSuccessorNodes();
	
	/** Accessor. */
	public Node getParentNode() {
		return parentNode;
	}

	/** Accessor. */
	public int getPathCost() {
		return pathCost;
	}
	
}
