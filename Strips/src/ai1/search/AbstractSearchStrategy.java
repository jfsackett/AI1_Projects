package ai1.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Base abstract implementation of a search strategy. */
public abstract class AbstractSearchStrategy implements SearchStrategy {

	/** Unexpanded nodes on the frontier. */
	protected ArrayList<Node> frontierNodes = new ArrayList<Node>();
	
	/** Cache of unexpanded nodes on the frontier. */
	protected Set<Node> frontierNodeSet = new HashSet<Node>();
	
	/** Expanded nodes already visited. */
	protected Set<Node> expandedNodes = new HashSet<Node>();
	
	/** More nodes on frontier? */
	public boolean hasMoreNodes() {
		return !frontierNodes.isEmpty();
	}
	
	/** Get next node from frontier. */
	public abstract Node getNext();
	
	/** Add node to frontier. */
	public abstract void add(Node node);
	
	/** Add nodes to frontier. Implemented. */
	public void add(List<Node> nodes) {
		for (Node node : nodes) {
			this.add(node);
		}
	}
	
}
