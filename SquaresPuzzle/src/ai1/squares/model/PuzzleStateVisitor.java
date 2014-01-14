package ai1.squares.model;

/** Visitor pattern providing iteration hook for analyzing PuzzleStates. 
    PuzzleMove accepts PuzzleStateVisitor because states are wrapped by moves. */
public interface PuzzleStateVisitor {

	/** Visit the PuzzleState's encompassing PuzzleMove. */
	public void visit(PuzzleMove puzzleMove);
	
}
