package ai1.squares.model;

/** This represents a move to a puzzle state. */
public class PuzzleMove {
	/** Prior puzzle move. */
	private PuzzleMove priorPuzzleMove;
	
	/** Move direction. */
	private MoveDirection moveDirection;
	
	/** Destination puzzle state. */
	private PuzzleState destPuzzleState;

	/** Number of moves to destination state. */
	private int numMovesFromStart;

	/** Constructor. */
	public PuzzleMove(PuzzleMove priorPuzzleMove, MoveDirection moveDirection, PuzzleState destPuzzleState) {
		this.priorPuzzleMove = priorPuzzleMove;
		this.moveDirection = moveDirection;
		this.destPuzzleState = destPuzzleState;
		this.numMovesFromStart = (priorPuzzleMove == null) ? 0 : (priorPuzzleMove.getNumMovesFromStart() + 1);
	}

	/** Visitor pattern accept method. */
	public void accept(PuzzleStateVisitor visitor) {
		visitor.visit(this);
	}

	/** Accessor. */
	public PuzzleMove getPriorPuzzleMove() {
		return priorPuzzleMove;
	}

	/** Accessor. */
	public MoveDirection getMoveDirection() {
		return moveDirection;
	}

	/** Accessor. */
	public PuzzleState getDestPuzzleState() {
		return destPuzzleState;
	}

	/** Accessor. */
	public int getNumMovesFromStart() {
		return numMovesFromStart;
	}

	/** To string. */
	@Override
	public String toString() {
		String dir = (moveDirection == null) ? "Start:" : "" + moveDirection + ':';
		return String.format("%1$-7s", dir) + destPuzzleState;
	}
	
}
