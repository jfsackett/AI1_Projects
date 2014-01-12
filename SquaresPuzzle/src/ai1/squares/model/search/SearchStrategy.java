package ai1.squares.model.search;

public interface SearchStrategy {

	SearchResult search(PuzzleState startPuzzleState, PuzzleState endPuzzleState);
	
}
