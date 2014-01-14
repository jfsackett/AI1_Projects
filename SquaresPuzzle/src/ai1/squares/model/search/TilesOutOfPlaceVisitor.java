package ai1.squares.model.search;

import ai1.squares.model.PuzzleMove;
import ai1.squares.model.PuzzleState;
import ai1.squares.model.PuzzleStateVisitor;

/** Visits puzzle states and finds the one with the fewest tiles out of place. */ 
public class TilesOutOfPlaceVisitor implements PuzzleStateVisitor {
	
	/** Goal puzzle state. */
	private PuzzleState goalPuzzleState;
	
	/** Wrapper move for the closest puzzle state to completion found. */
	private PuzzleMove bestPuzzleMove;
	
	/** Closest number of tiles out of place. */
	private int closestNumTilesOutOfPlace = Integer.MAX_VALUE;
	
	/** Constructor. */
	public TilesOutOfPlaceVisitor(PuzzleState goalPuzzleState) {
		this.goalPuzzleState = goalPuzzleState;
	}

	/** Visit a PuzzleState and count the number of tiles out of place. Part of PuzzleStateVisitor interface. */
	public void visit(PuzzleMove puzzleMove) {
		// Get the puzzle move's puzzle state.
		PuzzleState puzzleState = puzzleMove.getDestPuzzleState();
		// Compare to goal by counting the number of tiles out of place.
		int numTilesOutOfPlace = countNumTilesOutOfPlace(puzzleState);
		// Is this the closest so far?
		if (numTilesOutOfPlace < closestNumTilesOutOfPlace) {
			// Remember it.
			bestPuzzleMove = puzzleMove;
			closestNumTilesOutOfPlace = numTilesOutOfPlace;
		}
	}

	/** Compare to goal by counting the number of tiles out of place. */
	public int countNumTilesOutOfPlace(PuzzleState puzzleState) {
		// Retrieve string representations.
		String compareDigits = puzzleState.getPuzzleDigits();
		String goalDigits = goalPuzzleState.getPuzzleDigits();
		// Loop through and count.
		int numTilesOutOfPlace = 0;
		for (int ix = 0; ix < goalDigits.length(); ix++) {
			if (compareDigits.charAt(ix) != goalDigits.charAt(ix)) {
				numTilesOutOfPlace++;
			}
		}
		
		return numTilesOutOfPlace;
	}
	
	/** Accessor. */
	public PuzzleMove getBestPuzzleMove() {
		return bestPuzzleMove;
	}
	
}
