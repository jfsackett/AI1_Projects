package ai1.squares.model.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import ai1.squares.model.MoveDirection;
import ai1.squares.model.PuzzleMove;
import ai1.squares.model.PuzzleState;


public class BreadthFirstSearchStrategy implements SearchStrategy {

	/** Perform breadth first search and return results info. */
	public SearchResult search(PuzzleState startPuzzleState, PuzzleState goalPuzzleState) {
		// Check for invalid input.
		if (startPuzzleState == null || goalPuzzleState == null) {
			return new SearchResult();
		}
		
		// Create queue of non-expanded puzzle states.
		Queue<PuzzleMove> futurePuzzleMoves = new LinkedList<PuzzleMove>();
		// Add start state.
		futurePuzzleMoves.add(new PuzzleMove(null, null, startPuzzleState));
		
		// Create set of visited states.
		Set<PuzzleState> visitedPuzzleStates = new HashSet<PuzzleState>();
		// Add start state.
		visitedPuzzleStates.add(startPuzzleState);
		
		int numStatesVisited = 0;
		Date startTime = new Date();
		
		while (!futurePuzzleMoves.isEmpty())  {
			numStatesVisited++;
			// Remove head of queue.
			PuzzleMove currPuzzleMove = futurePuzzleMoves.remove();
			PuzzleState currPuzzleState = currPuzzleMove.getDestPuzzleState();
			// End state?
			if (currPuzzleState.equals(goalPuzzleState)) {
				long msecElapsed = new Date().getTime() - startTime.getTime();
				List<PuzzleMove> solutionMoves = recursePuzzleMoveLinks(currPuzzleMove, new ArrayList<PuzzleMove>());
				printPuzzleMoves(solutionMoves);
				return new SearchResult(true, msecElapsed, numStatesVisited, solutionMoves);
			}
			// Loop through moves from this state.
			for (MoveDirection moveDirection : MoveDirection.values()) {
				// Make move to next puzzle state.
				PuzzleState nextPuzzleState = currPuzzleState.move(moveDirection);
				// Check for move to illegal state.
				if (nextPuzzleState == null) {
					continue;
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
		long msecElapsed = new Date().getTime() - startTime.getTime();
		return new SearchResult(false, msecElapsed, numStatesVisited, new ArrayList<PuzzleMove>());
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
	
}
