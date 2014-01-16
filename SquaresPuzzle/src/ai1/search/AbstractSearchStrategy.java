package ai1.search;

import java.util.List;

/** Base abstract implementation of a search strategy. */
public abstract class AbstractSearchStrategy implements SearchStrategy {

	/** More nodes on frontier? */
	public abstract boolean hasMoreNodes();
	
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
