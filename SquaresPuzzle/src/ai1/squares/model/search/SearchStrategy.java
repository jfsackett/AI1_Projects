package ai1.squares.model.search;

import ai1.squares.model.PuzzleState;

public interface SearchStrategy {

	SearchResult search(PuzzleState startPuzzleState, PuzzleState goalPuzzleState);
	
}
