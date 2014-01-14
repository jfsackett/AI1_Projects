package ai1.squares.model.search;

import ai1.squares.model.PuzzleMove;
import ai1.squares.model.PuzzleState;
import ai1.squares.model.PuzzleStateVisitor;

/** Visits puzzle states and finds the location to insert new state so list remains sorted by heuristic. */ 
public class AstarMinDistanceVisitor implements PuzzleStateVisitor {
	
	/** Goal puzzle state. */
	private PuzzleState goalPuzzleState;
	
	/** A* heuristic value for new puzzle state. */
	private int heuristicValueForNewPuzzleState;
	
	/** Found insertion location flag. */
	private boolean foundLocation;
	
	/** Location to insert new puzzle state. */
	private int insertIndex;
	
	/** Constructor. */
	public AstarMinDistanceVisitor(PuzzleMove newPuzzleMove, PuzzleState goalPuzzleState, int numFrontierStates) {
		this.goalPuzzleState = goalPuzzleState;
		int sumDistanceOutOfPlaceInNewPuzzleState = sumDistanceOutOfPlace(newPuzzleMove.getDestPuzzleState());
		heuristicValueForNewPuzzleState = newPuzzleMove.getNumMovesFromStart() + sumDistanceOutOfPlaceInNewPuzzleState;
		foundLocation = false;
		insertIndex = numFrontierStates;
	}

	/** Visit a PuzzleState and see if this is location to insert into heuristic sorted list. Part of PuzzleStateVisitor interface. */
	public void visit(PuzzleMove puzzleMove) {
		// Get the puzzle move's puzzle state.
		PuzzleState puzzleState = puzzleMove.getDestPuzzleState();
		// Compare to goal by summing the total distance out of place.
		int sumDistanceOutOfPlace = sumDistanceOutOfPlace(puzzleState);
		// Add to number of moves to get A* heuristic value.
		int heuristicValue = puzzleMove.getNumMovesFromStart() + sumDistanceOutOfPlace;
		// Is this the correct insertion location?
		if (heuristicValueForNewPuzzleState >= heuristicValue) {
			// Yup, set found flag.
			foundLocation = true;
			return;
		}
		// Decrement location for next visit.
		insertIndex--;
	}

	/** Compare to goal by summing the total distance out of place. */
	public int sumDistanceOutOfPlace(PuzzleState puzzleState) {
		// Retrieve string representations.
		char[] compareDigits = puzzleState.getPuzzleDigits().toCharArray();
		char[] goalDigits = goalPuzzleState.getPuzzleDigits().toCharArray();
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
		
		return sumDistanceOutOfPlace;
	}

	/** Accessor. */
	public boolean isFoundLocation() {
		return foundLocation;
	}

	/** Accessor. */
	public int getInsertIndex() {
		return insertIndex;
	}
	
}
