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

/** Solves puzzle by using heuristic which minimizes tiles out of place. */
public class TilesOutOfPlaceSearchStrategy implements SearchStrategy {

	/** Perform depth first search and return results info. */
	public SearchResult search(PuzzleState startPuzzleState, PuzzleState goalPuzzleState) {
		// Check for invalid input.
		if (startPuzzleState == null || goalPuzzleState == null) {
			return new SearchResult();
		}
		
		// Create list of non-expanded puzzle states.
		List<PuzzleMove> futurePuzzleMoves = new LinkedList<PuzzleMove>();
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
System.out.println("numStatesVisited- " + numStatesVisited + "  futurePuzzleMoves- " + futurePuzzleMoves.size());			
			// Remove find and remove best move.
			PuzzleMove currPuzzleMove = findRemoveBestPuzzleMove(futurePuzzleMoves, goalPuzzleState);
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
					futurePuzzleMoves.add(new PuzzleMove(currPuzzleMove, moveDirection, nextPuzzleState));
				}
			}
		}

		// Unsuccessful search. "Can't get there from here."
		long msecElapsed = new Date().getTime() - startTime.getTime();
		return new SearchResult(false, msecElapsed, numStatesVisited, new ArrayList<PuzzleMove>());
	}
	
	private static PuzzleMove findRemoveBestPuzzleMove(List<PuzzleMove> futurePuzzleMoves, PuzzleState goalPuzzleState) {
		// Create visitor to iterate through and find the best puzzle move.
		TilesOutOfPlaceVisitor visitor = new TilesOutOfPlaceVisitor(goalPuzzleState);
		for (PuzzleMove puzzleMove : futurePuzzleMoves) {
			puzzleMove.accept(visitor);
		}
		// Remove the best move from moves list.
		futurePuzzleMoves.remove(visitor.getBestPuzzleMove());
		
		return visitor.getBestPuzzleMove();
	}

	//TODO Rename this method.
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
