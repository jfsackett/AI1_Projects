package ai1.squares.model.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
		Queue<PuzzleMove> futurePuzzleMoves = new LinkedList<PuzzleMove>();
		// Add start state.
		futurePuzzleMoves.add(new PuzzleMove(null, null, startPuzzleState));
		
		// Create set of visited states.
		Set<PuzzleState> visitedPuzzleStates = new HashSet<PuzzleState>();
		// Add start state.
		visitedPuzzleStates.add(startPuzzleState);
		
		while (!futurePuzzleMoves.isEmpty())  {
			// Remove head of queue.
			PuzzleMove currPuzzleMove = futurePuzzleMoves.remove();
			PuzzleState currPuzzleState = currPuzzleMove.getDestPuzzleState();
			for (MoveDirection moveDirection : MoveDirection.values()) {
				// Make move to next puzzle state.
				PuzzleState nextPuzzleState = currPuzzleState.move(moveDirection);
				// Check for move to illegal state.
				if (nextPuzzleState == null) {
					continue;
				}
				// Find end state?
				if (nextPuzzleState.equals(endPuzzleState)) {
					List<PuzzleMove> solutionMoves = recursePuzzleMoveLinks(new PuzzleMove(currPuzzleMove, moveDirection, nextPuzzleState), new ArrayList<PuzzleMove>());
					printPuzzleMoves(solutionMoves);
					return new SearchResult(true, 0, 1, solutionMoves);
				}
				// State already visited?
				if (!visitedPuzzleStates.contains(nextPuzzleState)) {
					// Nope, add to visited & future states.
					visitedPuzzleStates.add(nextPuzzleState);
					futurePuzzleMoves.add(new PuzzleMove(currPuzzleMove, moveDirection, nextPuzzleState));
				}
			}
		}

		// Unsuccessful search. "Can't get there from here."
		return new SearchResult();
	}

	private static List<PuzzleMove> recursePuzzleMoveLinks(PuzzleMove puzzleMove, List<PuzzleMove> resultList) {
		if (puzzleMove == null) {
			return resultList;
		}
		resultList = recursePuzzleMoveLinks(puzzleMove.getPriorPuzzleMove(), resultList);
		resultList.add(puzzleMove);
		return resultList;
	}

	public void printPuzzleMoves(List<PuzzleMove> puzzleMoves) {
		for (PuzzleMove puzzleMove : puzzleMoves) {
			System.out.println(puzzleMove);
		}
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
