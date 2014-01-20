package ai1.search;

import java.util.ArrayList;
import java.util.List;

/** Represents the completion information for a search. */
public class SearchResult {
	/** Success flag. */
	private boolean success;
	/** MSecs to solve or give-up. */
	private long timeTaken;
	/** Number of nodes visited. */
	private long numNodesVisited;
	/** Solution list. */
	List<Node> solutionMoves;

	/** Constructor. */
	public SearchResult() {
		this.success = false;
		this.timeTaken = 0;
		this.numNodesVisited = 0;
		this.solutionMoves = new ArrayList<Node>();
	}

	/** Constructor. */
	public SearchResult(boolean success, long timeTaken, long numNodesVisited, List<Node> solutionMoves) {
		this.success = success;
		this.timeTaken = timeTaken;
		this.numNodesVisited = numNodesVisited;
		this.solutionMoves = solutionMoves;
	}

	/** Accessor. */
	public boolean isSuccess() {
		return success;
	}

	/** Accessor. */
	public long getTimeTaken() {
		return timeTaken;
	}

	/** Accessor. */
	public long getNumNodesVisited() {
		return numNodesVisited;
	}

	/** Accessor. */
	public List<Node> getSolutionMoves() {
		return solutionMoves;
	}

	/** Return string representation. */
	@Override
	public String toString() {
		return (success ? "Success: " + (solutionMoves.size()-1) + " length; ": "Failure: ") +  
				+ numNodesVisited + " visited; " + timeTaken + " msec\n";
	}
	
}
