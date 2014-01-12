package ai1.squares.model.search;

public class PuzzleMove {
	/** Prior puzzle move. */
	private PuzzleMove priorPuzzleMove;
	
	/** Move direction. */
	private MoveDirection moveDirection;
	
	/** Destination puzzle state. */
	private PuzzleState destPuzzleState;

	public PuzzleMove(PuzzleMove priorPuzzleMove, MoveDirection moveDirection, PuzzleState destPuzzleState) {
		this.priorPuzzleMove = priorPuzzleMove;
		this.moveDirection = moveDirection;
		this.destPuzzleState = destPuzzleState;
	}

	public PuzzleMove getPriorPuzzleMove() {
		return priorPuzzleMove;
	}

	public MoveDirection getMoveDirection() {
		return moveDirection;
	}

	public PuzzleState getDestPuzzleState() {
		return destPuzzleState;
	}

	@Override
	public String toString() {
		String dir = (moveDirection == null) ? "None:" : "" + moveDirection + ':';
		return String.format("%1$-7s", dir) + destPuzzleState;
	}
	
}
