package ai1.squares.model;

import ai1.search.HeuristicStrategy;
import ai1.search.Node;

/** Greedy heuristic using sum Manhattan distances out of place. Strategy pattern. */ 
public class GreedyMinDistanceHeuristic implements HeuristicStrategy {

	/** Goal puzzle node. */
	PuzzleNode goalNode;
	
	/** Constructor. */
	public GreedyMinDistanceHeuristic(PuzzleNode goalNode) {
		this.goalNode = goalNode;
	}
	
	/** Estimate the cost to goal from this node. */
	public int estimateCost(Node node) {
		// Safe cast.
		PuzzleNode currNode = (PuzzleNode) node;
		
		// Return total distance out of place.
		return sumDistanceOutOfPlace(currNode);
	}
	
	/** Compare to goal by summing the total distance out of place. */
	public int sumDistanceOutOfPlace(PuzzleNode currNode) {
		int cacheValue = currNode.getCacheValue();
		if (cacheValue >= 0) {
			return cacheValue;
		}
		// Retrieve char[] representations.
		char[] compareDigits = currNode.getPuzzleDigits().toCharArray();
		char[] goalDigits = goalNode.getPuzzleDigits().toCharArray();
		// Loop through compare digits.
		int sumDistanceOutOfPlace = 0;
		for (int ix = 0; ix < compareDigits.length; ix++) {
			if (compareDigits[ix] == '0') {
				// skip blank.
				continue;
			}
			// Find the compare digit in goal digits.
			for (int iy = 0; iy < compareDigits.length; iy++) {
				if (compareDigits[ix] == goalDigits[iy]) {
					// determine distance.
					sumDistanceOutOfPlace += Math.abs(ix / 3 - iy / 3) + Math.abs(ix % 3 - iy % 3);
					continue;
				}
			}
		}
		
		currNode.setCacheValue(sumDistanceOutOfPlace);
		return sumDistanceOutOfPlace;
	}

}
