package ai1.squares.model.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class BreadthFirstSearchStrategy implements SearchStrategy {

	/** Perform breadth first search and return results info. */
	public SearchResult search(PuzzleState startPuzzleState, PuzzleState endPuzzleState) {
		// Check for invalid input.
		if (startPuzzleState == null || endPuzzleState == null) {
			return new SearchResult();
		}
		
		// Check for start state equaling end state.
		if (startPuzzleState.equals(endPuzzleState)) {
			return new SearchResult(true, 0, 1, new ArrayList<PuzzleMove>());
		}
		
		// Create queue of non-expanded puzzle states.
		Queue<PuzzleState> futurePuzzleStates = new LinkedList<PuzzleState>();
		// Add start state.
		futurePuzzleStates.add(startPuzzleState);
		
		// Create set of visited states.
		Set<PuzzleState> visitedPuzzleStates = new HashSet<PuzzleState>();
		// Add start state.
		visitedPuzzleStates.add(startPuzzleState);
		
		while (!futurePuzzleStates.isEmpty())  {
			// Remove head of queue.
			PuzzleState currPuzzleState = futurePuzzleStates.remove();
			for (MoveDirection moveDirection : MoveDirection.values()) {
				// Make move to next puzzle state.
				PuzzleState nextPuzzleState = currPuzzleState.move(moveDirection);
				// Check for move to illegal state.
				if (nextPuzzleState == null) {
					continue;
				}
				// Find end state?
				System.out.println(nextPuzzleState);
				if (nextPuzzleState.equals(endPuzzleState)) {
					return new SearchResult(true, 0, 1, new ArrayList<PuzzleMove>());
				}
				// State already visited?
				if (!visitedPuzzleStates.contains(nextPuzzleState)) {
					// Nope, add to visited & future states.
					visitedPuzzleStates.add(nextPuzzleState);
					futurePuzzleStates.add(nextPuzzleState);
				}
			}
		}

		// Unsuccessful search. "Can't get there from here."
		return new SearchResult();
	}

	/* Success flag. 
	private boolean success;
	/** MSecs to solve or give-up. 
	private int timeTaken;
	/** Number of puzzle states visited. 
	private int numStatesVisited;
	/** Solution list. 
	List<PuzzleMove> solutionMoves;
	*/
}
