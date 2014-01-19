package ai1.search;


/** This extends depth-first search with iterative deepening. Strategy pattern. */
public class IterDeepDepthFirstSearchStrategy extends DepthFirstSearchStrategy {

	/** Search start node. */
	private Node startNode;
	
	/** Depth threshold. */
	private int depthThreshold;
	
	/** Flag indicating there are more nodes below threshold. */
	private boolean moreBelowThreshold;
	
	/** Constructor. */
	public IterDeepDepthFirstSearchStrategy(Node startNode) {
		super();
		this.startNode = startNode;
		depthThreshold = 1;
		moreBelowThreshold = false;
	}
	
	/** More nodes on frontier? */
	public boolean hasMoreNodes() {
		if (!frontierNodes.isEmpty()) {
			return true;
		}
		// No more nodes above threshold. Are there any below threshold?
		if (!moreBelowThreshold) {
			// No more anywhere.
			return false;
		}
		
		// More exist below threshold so reset initial search state.
		frontierNodes.add(startNode);
		frontierNodeSet.add(startNode);		
		depthThreshold++;
		moreBelowThreshold = false;
		expandedNodes.clear();
		
		return true;
	}
	
	/** Add node to frontier. Add to the beginning of list for depth first. */
	public void add(Node newNode) {
		// Already expanded this node? 
		if (expandedNodes.contains(newNode)) {
			return;
		}
		
		// Duplicate node in frontier nodes?
		if (frontierNodeSet.contains(newNode)) {
			return;
		}
		
		// Is this node below depth threshold?
		if (newNode.getPathCost() > depthThreshold) {
			moreBelowThreshold = true;
			return;
		}
		
		// Add to the beginning of list for depth first.
		frontierNodes.add(0, newNode);
		
		// Add to frontier cache.
		frontierNodeSet.add(newNode);		
	}
	
}
