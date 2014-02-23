package ai1.search;

/** Implementations provide different heuristic algorithms. Strategy pattern. */ 
public interface HeuristicStrategy {

	/** Estimate the cost to goal from this node. */
	int estimateCost(Node node);
	
}
