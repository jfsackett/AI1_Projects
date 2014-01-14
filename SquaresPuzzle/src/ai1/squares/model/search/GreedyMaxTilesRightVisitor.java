package ai1.squares.model.search;

import ai1.squares.model.PuzzleMove;
import ai1.squares.model.PuzzleState;
import ai1.squares.model.PuzzleStateVisitor;

/** Visits puzzle states and finds the location to insert new state so list remains sorted by heuristic. */ 
public class GreedyMaxTilesRightVisitor implements PuzzleStateVisitor {
	
	/** Goal puzzle state. */
	private PuzzleState goalPuzzleState;
	
	/** Number of tiles in place in new state. */
	private int numTilesInPlaceInNewPuzzleState;
	
	/** Found insertion location flag. */
	private boolean foundLocation;
	
	/** Location to insert new puzzle state. */
	private int insertIndex;
	
	/** Constructor. */
	public GreedyMaxTilesRightVisitor(PuzzleState newPuzzleState, PuzzleState goalPuzzleState, int numFrontierStates) {
		this.goalPuzzleState = goalPuzzleState;
		numTilesInPlaceInNewPuzzleState = countNumTilesInPlace(newPuzzleState);
		foundLocation = false;
		insertIndex = numFrontierStates;
	}

	/** Visit a PuzzleState and see if this is location to insert into heuristic sorted list. Part of PuzzleStateVisitor interface. */
	public void visit(PuzzleMove puzzleMove) {
		// Get the puzzle move's puzzle state.
		PuzzleState puzzleState = puzzleMove.getDestPuzzleState();
		// Compare to goal by counting the number of tiles in place.
		int numTilesInPlace = countNumTilesInPlace(puzzleState);
		// Is this the correct insertion location?
		if (numTilesInPlaceInNewPuzzleState <= numTilesInPlace) {
			// Yup, set found flag.
			foundLocation = true;
			return;
		}
		// Decrement location for next visit.
		insertIndex--;
	}

	/** Compare to goal by counting the number of tiles in place. */
	public int countNumTilesInPlace(PuzzleState puzzleState) {
		// Retrieve string representations.
		String compareDigits = puzzleState.getPuzzleDigits();
		String goalDigits = goalPuzzleState.getPuzzleDigits();
		// Loop through and count.
		int numTilesInPlace = 0;
		for (int ix = 0; ix < goalDigits.length(); ix++) {
			if (compareDigits.charAt(ix) == goalDigits.charAt(ix)) {
				numTilesInPlace++;
			}
		}
		
		return numTilesInPlace;
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
