package ai1.squares.model;

/** Represents a static puzzle state and offers movement to neighbor states. */
public class PuzzleState {
	/** Puzzle digits. */
	private String puzzleDigits;
	
	/** Matrix representation of puzzle. */
	private char[][] puzzleMatrix;

	/** Constructor. */
	public PuzzleState(String puzzleDigits) {
		this.puzzleDigits = puzzleDigits;
	}
	
	/** Build matrix representation of puzzle. */
	public void buildPuzzleMatrix() {
		puzzleMatrix = new char[3][3];
		for (int ix = 0; ix < 9; ix++) {
			puzzleMatrix[ix / 3][ix % 3] = puzzleDigits.charAt(ix);
		}
	}
	
	/** Make a puzzle move in the input direction; return null if impossible move. */
	public PuzzleState move(MoveDirection moveDirection) {
		int spaceLoc = puzzleDigits.indexOf('0');
		switch(moveDirection) {
		case DOWN:
			// Top row?
			if (spaceLoc / 3 == 0) {
				return null;
			}
			return new PuzzleState(swapDigits(spaceLoc, spaceLoc - 3));
		case UP:
			// Bottom row?
			if (spaceLoc / 3 == 2) {
				return null;
			}
			return new PuzzleState(swapDigits(spaceLoc, spaceLoc + 3));
		case RIGHT:
			// Left column?
			if (spaceLoc % 3 == 0) {
				return null;
			}
			return new PuzzleState(swapDigits(spaceLoc, spaceLoc - 1));
		case LEFT:
			// Right column?
			if (spaceLoc % 3 == 2) {
				return null;
			}
			return new PuzzleState(swapDigits(spaceLoc, spaceLoc + 1));
		default:
			return null;
		}
	}
	
	/** Swap digits to render puzzle move. */
	private String swapDigits(int loc1, int loc2) {
		char[] digits = puzzleDigits.toCharArray();
		char temp = digits[loc1];
		digits[loc1] = digits[loc2];
		digits[loc2] = temp;
		return new String(digits);
	}
	
	/** Accessor. */
	public String getPuzzleDigits() {
		return puzzleDigits;
	}

	/** Accessor. */
	public char[][] getPuzzleMatrix() {
		return puzzleMatrix;
	}

	/** Hash Code. */
	@Override
	public int hashCode() {
		return 37 * 1 + ((puzzleDigits == null) ? 0 : puzzleDigits.hashCode());
	}

	/** Equals. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleState other = (PuzzleState) obj;
		if (puzzleDigits == null) {
			if (other.puzzleDigits != null)
				return false;
		} else if (!puzzleDigits.equals(other.puzzleDigits))
			return false;
		return true;
	}

	/** To string. */
	@Override
	public String toString() {
		return puzzleDigits;
	}

}
