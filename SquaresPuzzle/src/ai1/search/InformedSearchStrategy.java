package ai1.search;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

/** This provides search utilizing a heuristic. Strategy patteern. */
public class InformedSearchStrategy extends AbstractSearchStrategy {

	/** Unexpanded nodes on the frontier. */
	ArrayList<Node> frontierNodes = new ArrayList<Node>();
	
	/** Expanded nodes already visited. */
	Set<Node> expandedNodes = new HashSet<Node>();
	
	/** Pluggable heuristic strategy. Strategy pattern. */
	HeuristicStrategy heuristicStrategy;
	
	/** Constructor. */
	public InformedSearchStrategy(HeuristicStrategy heuristicStrategy) {
		this.heuristicStrategy = heuristicStrategy;
	}

	/** More nodes on frontier? */
	public boolean hasMoreNodes() {
		return !frontierNodes.isEmpty();
	}
	
	/** Get next node from frontier. */
	public Node getNext() {
		return (hasMoreNodes()) ? frontierNodes.remove(0) : null;
	}
	
	/** Add node to frontier. Must account for duplicate nodes on frontier by keeping only the one with lower estimated cost. */
	public void add(Node newNode) {
//System.out.println("expandedNodes.size()- " + expandedNodes.size() + "  frontierNodes.size()- " + frontierNodes.size());			
		// Already expanded this node? 
		if (expandedNodes.contains(newNode)) {
			return;
		}
		
		// Calculate heuristic estimate for new node.
		int newNodeEstimatedCost = heuristicStrategy.estimateCost(newNode);
		
		// Check for duplicate node in frontier nodes.
		int dupNodeIndex = frontierNodes.indexOf(newNode);
		if (dupNodeIndex > 0) {
			Node dupNode = frontierNodes.get(dupNodeIndex);
			// Keep node with lowest cost.
			if (newNodeEstimatedCost < heuristicStrategy.estimateCost(dupNode)) {
				frontierNodes.remove(dupNodeIndex);
			}
			else {
				// Same node with equal or better cost already on frontier.
				return;
			}
		}
		
		// Add to expanded set.
		expandedNodes.add(newNode);
		
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
	}
	
}
