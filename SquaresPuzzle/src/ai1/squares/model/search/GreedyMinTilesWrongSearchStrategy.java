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
public class GreedyMinTilesWrongSearchStrategy implements SearchStrategy {

	/** Perform greedy search by minimizing # tiles out of place and return results info. */
	public SearchResult search(PuzzleState startPuzzleState, PuzzleState goalPuzzleState) {
		// Check for invalid input.
		if (startPuzzleState == null || goalPuzzleState == null) {
			return new SearchResult();
		}
		
		// Create list of frontier puzzle states. Will stay sorted by heuristic.
		LinkedList<PuzzleMove> frontierPuzzleMoves = new LinkedList<PuzzleMove>();
		// Add start state to frontier states.
		frontierPuzzleMoves.add(new PuzzleMove(null, null, startPuzzleState));
		
		// Create set of visited states.
		Set<PuzzleState> visitedPuzzleStates = new HashSet<PuzzleState>();
		// Add start state to visited states.
		visitedPuzzleStates.add(startPuzzleState);
		
		int numStatesVisited = 0;
		Date startTime = new Date();
		
		while (!frontierPuzzleMoves.isEmpty())  {
			numStatesVisited++;
			// Remove the best (first) move.
			PuzzleMove currPuzzleMove = frontierPuzzleMoves.removeFirst();
			PuzzleState currPuzzleState = currPuzzleMove.getDestPuzzleState();
			// End state?
			if (currPuzzleState.equals(goalPuzzleState)) {
				long msecElapsed = new Date().getTime() - startTime.getTime();
				List<PuzzleMove> solutionMoves = reversePuzzleMoveLinks(currPuzzleMove);
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
					// Nope, add to visited & frontier states.
					visitedPuzzleStates.add(nextPuzzleState);
					insertIntoHeuristicSortedLoc(new PuzzleMove(currPuzzleMove, moveDirection, nextPuzzleState), goalPuzzleState, frontierPuzzleMoves);
				}
			}
		}

		// Unsuccessful search. "Can't get there from here."
		long msecElapsed = new Date().getTime() - startTime.getTime();
		return new SearchResult(false, msecElapsed, numStatesVisited, new ArrayList<PuzzleMove>());
	}
	
	/** Insert new puzzle state into frontier list in sorted order by heuristic funtion. */
	private static void insertIntoHeuristicSortedLoc(PuzzleMove newPuzzleMove, PuzzleState goalPuzzleState, LinkedList<PuzzleMove> frontierPuzzleMoves) {
		// Create visitor to iterate over frontier list, looking for insertion location.
		GreedyMinTilesWrongVisitor visitor = new GreedyMinTilesWrongVisitor(newPuzzleMove.getDestPuzzleState(), goalPuzzleState, frontierPuzzleMoves.size());
		// Iterate backward through list because insertion location more likely to be near end.
		Iterator<PuzzleMove> iterFrontierPuzzleMovesReverse = frontierPuzzleMoves.descendingIterator();
		while (iterFrontierPuzzleMovesReverse.hasNext()) {
			PuzzleMove puzzleMove = iterFrontierPuzzleMovesReverse.next();
			visitor.visit(puzzleMove);
			// Found insertion location?
			if (visitor.isFoundLocation()) {
				// Add at found location.
				frontierPuzzleMoves.add(visitor.getInsertIndex(), newPuzzleMove);
				return;
			}
		}
		// Didn't find insertion location so append to beginning of list.
		frontierPuzzleMoves.addFirst(newPuzzleMove);
	}
	
	/** Return list of moves reversed from goal so in order. */
	private static List<PuzzleMove> reversePuzzleMoveLinks(PuzzleMove puzzleMove) {
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

	/** Print puzzle moves in list. */
	public void printPuzzleMoves(List<PuzzleMove> puzzleMoves) {
		for (PuzzleMove puzzleMove : puzzleMoves) {
			System.out.println(puzzleMove);
		}
	}

}
