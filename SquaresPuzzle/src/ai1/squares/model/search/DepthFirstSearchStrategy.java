package ai1.squares.model.search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ai1.squares.model.MoveDirection;
import ai1.squares.model.PuzzleMove;
import ai1.squares.model.PuzzleState;


public class DepthFirstSearchStrategy implements SearchStrategy {

	/** Perform depth first search and return results info. */
	public SearchResult search(PuzzleState startPuzzleState, PuzzleState goalPuzzleState) {
		// Check for invalid input.
		if (startPuzzleState == null || goalPuzzleState == null) {
			return new SearchResult();
		}
		
		// Create stack of non-expanded puzzle states.
		Deque<PuzzleMove> futurePuzzleMoves = new ArrayDeque<PuzzleMove>();
		// Add start state.
		futurePuzzleMoves.push(new PuzzleMove(null, null, startPuzzleState));
		
		// Create set of visited states.
		Set<PuzzleState> visitedPuzzleStates = new HashSet<PuzzleState>();
		// Add start state.
		visitedPuzzleStates.add(startPuzzleState);
		
		int numStatesVisited = 0;
		Date startTime = new Date();
		
		while (!futurePuzzleMoves.isEmpty())  {
			numStatesVisited++;
			// Remove top of stack.
			PuzzleMove currPuzzleMove = futurePuzzleMoves.pop();
			PuzzleState currPuzzleState = currPuzzleMove.getDestPuzzleState();
			// End state?
			if (currPuzzleState.equals(goalPuzzleState)) {
				long msecElapsed = new Date().getTime() - startTime.getTime();
				List<PuzzleMove> solutionMoves = recursePuzzleMoveLinks(currPuzzleMove);
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
					futurePuzzleMoves.push(new PuzzleMove(currPuzzleMove, moveDirection, nextPuzzleState));
				}
			}
		}

		// Unsuccessful search. "Can't get there from here."
		long msecElapsed = new Date().getTime() - startTime.getTime();
		return new SearchResult(false, msecElapsed, numStatesVisited, new ArrayList<PuzzleMove>());
	}

	private static List<PuzzleMove> recursePuzzleMoveLinks(PuzzleMove puzzleMove) {
		List<PuzzleMove> resultList = new LinkedList<PuzzleMove>();
		if (puzzleMove == null) {
			return resultList;
		}
		// Use stack to reverse puzzle moves.
		Deque<PuzzleMove> puzzleMoves = new ArrayDeque<PuzzleMove>();
		while (puzzleMove != null) {
			puzzleMoves.push(puzzleMove);
			puzzleMove = puzzleMove.getPriorPuzzleMove();
		}
		// Iterate through stack, adding to list.
		Iterator<PuzzleMove> iterPuzzleMoves = puzzleMoves.iterator();
		while(iterPuzzleMoves.hasNext()) {
			resultList.add(iterPuzzleMoves.next());
		}
		
		return resultList;
	}

	public void printPuzzleMoves(List<PuzzleMove> puzzleMoves) {
		for (PuzzleMove puzzleMove : puzzleMoves) {
			System.out.println(puzzleMove);
		}
	}

}
