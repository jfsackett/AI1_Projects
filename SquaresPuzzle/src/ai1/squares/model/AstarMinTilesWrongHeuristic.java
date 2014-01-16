package ai1.squares.model;

import ai1.search.Node;

/** A* heuristic using cost to current node + number of tiles out of place. Strategy pattern. */ 
public class AstarMinTilesWrongHeuristic extends GreedyMinTilesWrongHeuristic {

	/** Constructor. */
	public AstarMinTilesWrongHeuristic(PuzzleNode goalNode) {
		super(goalNode);
	}
	
	/** Estimate the cost to goal from this node. */
	public int estimateCost(Node node) {
		// Safe cast.
		PuzzleNode currNode = (PuzzleNode) node;

		// Return moves so far + number of tiles wrong from superclass.
		return currNode.getPathCost() + super.estimateCost(currNode);
	}

}
