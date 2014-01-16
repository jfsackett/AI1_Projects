package ai1.squares.model;

import ai1.search.HeuristicStrategy;
import ai1.search.Node;

/** Greedy heuristic using number of tiles out of place. Strategy pattern. */ 
public class GreedyMinTilesWrongHeuristic implements HeuristicStrategy {

	/** Goal puzzle node. */
	PuzzleNode goalNode;
	
	/** Constructor. */
	public GreedyMinTilesWrongHeuristic(PuzzleNode goalNode) {
		this.goalNode = goalNode;
	}
	
	/** Estimate the cost to goal from this node. */
	public int estimateCost(Node node) {
		// Safe cast.
		PuzzleNode currNode = (PuzzleNode) node;
		
		// Return number of tiles wrong.
		return countNumTilesWrong(currNode);
	}

	/** Compare to goal by counting the number of tiles out of place. */
	private int countNumTilesWrong(PuzzleNode currNode) {
		int cacheValue = currNode.getCacheValue();
		if (cacheValue >= 0) {
			return cacheValue;
		}
		// Retrieve string representations.
		String currDigits = currNode.getPuzzleDigits();
		String goalDigits = goalNode.getPuzzleDigits();
		// Loop through and count.
		int numTilesWrong = 0;
		for (int ix = 0; ix < goalDigits.length(); ix++) {
			// Count when not space tile and not equal.
			if (currDigits.charAt(ix) != '0' && currDigits.charAt(ix) != goalDigits.charAt(ix)) {
				numTilesWrong++;
			}
		}
		
		currNode.setCacheValue(numTilesWrong);
		return numTilesWrong;
	}
	
}
