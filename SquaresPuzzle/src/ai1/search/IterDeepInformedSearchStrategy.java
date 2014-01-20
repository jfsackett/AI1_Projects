package ai1.search;


/** This extends heuristic informed search with iterative deepening. Strategy pattern. */
public class IterDeepInformedSearchStrategy extends InformedSearchStrategy {

	/** Search start node. */
	private Node startNode;
	
	/** Depth threshold. */
	private int depthThreshold;
	
	/** Flag indicating there are more nodes below threshold. */
	private boolean moreBelowThreshold;
	
	/** Constructor. */
	public IterDeepInformedSearchStrategy(HeuristicStrategy heuristicStrategy, Node startNode) {
		super(heuristicStrategy);
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
	
	/** Add node to frontier. Must account for duplicate nodes on frontier by keeping only the one with lower estimated cost. */
	public void add(Node newNode) {
		// Already expanded this node? 
		if (expandedNodes.contains(newNode)) {
			return;
		}
		
		// Is this node below depth threshold?
		if (newNode.getPathCost() > depthThreshold) {
			moreBelowThreshold = true;
			return;
		}
		
		// Calculate heuristic estimate for new node.
		int newNodeEstimatedCost = heuristicStrategy.estimateCost(newNode);
		
		// Check for duplicate node in frontier nodes.
		if (frontierNodeSet.contains(newNode)) {
			int dupNodeIndex = frontierNodes.indexOf(newNode);
			if (dupNodeIndex >= 0) {
				Node dupNode = frontierNodes.get(dupNodeIndex);
				// Keep node with lowest cost.
				if (newNodeEstimatedCost < heuristicStrategy.estimateCost(dupNode)) {
					// Remove higher cost node from frontier.
					frontierNodes.remove(dupNodeIndex);
					// Remove from frontier cache.
					frontierNodeSet.remove(newNode);					
				}
				else {
					// Same node with equal or better cost already on frontier.
					return;
				}
			}
		}
		
		// Iterate through list, looking for duplicate nodes and to find insertion point 
		// to keep list sorted by heuristic. Use visitor to calculate heuristic cost.
		int insertLocation = 0;
		for (Node currNode : frontierNodes) {
			// Calculate heuristic estimate for current node.
			int currNodeEstimatedCost = heuristicStrategy.estimateCost(currNode);
			
			// Found insertion location?
			if (newNodeEstimatedCost <= currNodeEstimatedCost) {
				break;
			}
			
			insertLocation++;
		}
		
		// Insert new node.
		frontierNodes.add(insertLocation, newNode);
		
		// Add to frontier cache.
		frontierNodeSet.add(newNode);		
	}
	
}
