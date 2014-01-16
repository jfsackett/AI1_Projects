package ai1.squares.model;

import ai1.search.Node;

/** A* heuristic using cost to current node + sum of Manhattan distances out of place. Strategy pattern. */ 
public class AstarMinDistanceHeuristic extends GreedyMinDistanceHeuristic {

	/** Constructor. */
	public AstarMinDistanceHeuristic(PuzzleNode goalNode) {
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
