package ai1.squares.model;

import java.util.LinkedList;
import java.util.List;

import ai1.search.Node;

/** Represents a static puzzle state and offers movement to neighbor states. */
public class PuzzleNode extends Node {
	/** Puzzle digits. */
	private String puzzleDigits;

	/** Move direction. */
	private MoveDirection moveDirection;
	
	/** Heuristic cache value. Use carefully */
	private int cacheValue = -1; 
	
	/** Constructor. */
	public PuzzleNode(String puzzleDigits, PuzzleNode parentNode, MoveDirection moveDirection) {
		this.puzzleDigits = puzzleDigits;
		this.parentNode = parentNode;
		this.moveDirection = moveDirection;
		this.pathCost = (parentNode == null) ? 0 : parentNode.getPathCost() + 1;
	}
	
	public String getAction() {
		return (moveDirection == null) ? "Start:" : "" + moveDirection;
	}
	
	public List<Node> getSuccessorNodes() {
		List<Node> successorNodes = new LinkedList<Node>();
		
		// Loop through moves from this node.
		for (MoveDirection moveDirection : MoveDirection.values()) {
			// Make move to next puzzle node.
			PuzzleNode nextPuzzleNode = move(moveDirection);
			// Check for move to illegal state.
			if (nextPuzzleNode == null) {
				continue;
			}

			successorNodes.add(nextPuzzleNode);
		}
		
		return successorNodes;
	}
	
	/** Make a puzzle move in the input direction; return null if impossible move. */
	private PuzzleNode move(MoveDirection moveDirection) {
		int spaceLoc = puzzleDigits.indexOf('0');
		switch(moveDirection) {
		case DOWN:
			// Top row?
			if (spaceLoc / 3 == 0) {
				return null;
			}
			return new PuzzleNode(swapDigits(spaceLoc, spaceLoc - 3), this, MoveDirection.DOWN );
		case UP:
			// Bottom row?
			if (spaceLoc / 3 == 2) {
				return null;
			}
			return new PuzzleNode(swapDigits(spaceLoc, spaceLoc + 3), this, MoveDirection.UP);
		case RIGHT:
			// Left column?
			if (spaceLoc % 3 == 0) {
				return null;
			}
			return new PuzzleNode(swapDigits(spaceLoc, spaceLoc - 1), this, MoveDirection.RIGHT);
		case LEFT:
			// Right column?
			if (spaceLoc % 3 == 2) {
				return null;
			}
			return new PuzzleNode(swapDigits(spaceLoc, spaceLoc + 1), this, MoveDirection.LEFT);
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
	public int getCacheValue() {
		return cacheValue;
	}

	/** Cache value mutator. */
	public void setCacheValue(int cacheValue) {
		this.cacheValue = cacheValue;
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
		PuzzleNode other = (PuzzleNode) obj;
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
		String dir = (moveDirection == null) ? "Start:" : "" + moveDirection + ':';
		return String.format("%1$-7s", dir) + puzzleDigits;
	}

}
