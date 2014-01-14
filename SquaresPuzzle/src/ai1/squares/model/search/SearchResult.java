package ai1.squares.model.search;

import java.util.ArrayList;
import java.util.List;

import ai1.squares.model.PuzzleMove;

/** Represents the completed execution of a puzzle solution. */
public class SearchResult {
	/** Success flag. */
	private boolean success;
	/** MSecs to solve or give-up. */
	private long timeTaken;
	/** Number of puzzle states visited. */
	private long numStatesVisited;
	/** Solution list. */
	List<PuzzleMove> solutionMoves;

	public SearchResult() {
		this.success = false;
		this.timeTaken = 0;
		this.numStatesVisited = 0;
		this.solutionMoves = new ArrayList<PuzzleMove>();
	}

	public SearchResult(boolean success, long timeTaken, long numStatesVisited, List<PuzzleMove> solutionMoves) {
		this.success = success;
		this.timeTaken = timeTaken;
		this.numStatesVisited = numStatesVisited;
		this.solutionMoves = solutionMoves;
	}

	public boolean isSuccess() {
		return success;
	}

	public long getTimeTaken() {
		return timeTaken;
	}

	public long getNumStatesVisited() {
		return numStatesVisited;
	}

	public List<PuzzleMove> getSolutionMoves() {
		return solutionMoves;
	}

	@Override
	public String toString() {
		return (success ? "Success: " + (solutionMoves.size()-1) + " length; ": "Failure: ") +  
				+ numStatesVisited + " visited; " + timeTaken + " msec";
	}
	
}
