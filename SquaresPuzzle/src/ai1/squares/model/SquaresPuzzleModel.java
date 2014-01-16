package ai1.squares.model;

import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

import ai1.search.BreadthFirstSearchStrategy;
import ai1.search.DepthFirstSearchStrategy;
import ai1.search.InformedSearchStrategy;
import ai1.search.SearchAgent;
import ai1.search.SearchResult;
import ai1.search.SearchStrategy;

/** Main model for Squares Puzzle. */
public class SquaresPuzzleModel {
	// Property change constants.
	public static final String COMPLEXITY = "COMPLEXITY";
	public static final String SEARCH_METHOD = "SEARCH_METHOD";
	public static final String PUZZLE = "PUZZLE";
	
	// Default puzzle states.
	public static final String EASY_PUZZLE = "134862705";
	public static final String MEDIUM_PUZZLE = "281043765";
	public static final String HARD_PUZZLE = "567408321";
	public static final String GOAL_PUZZLE = "123804765";
	
	/** Change event generator. */
    private SwingPropertyChangeSupport propChangeEventGen;

	/** Puzzle Complexity. */
	private Complexity complexity;

	/** Solution Search Method. */
	private SearchMethod searchMethod;
	
	/** Puzzle to solve. */
	private String puzzle;

	public SquaresPuzzleModel() {
		propChangeEventGen = new SwingPropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propChangeEventGen.addPropertyChangeListener(listener);
	}

	public Complexity getComplexity() {
		return complexity;
	}

	public void setComplexity(Complexity complexity) {
		Complexity priorComplexity = this.complexity;
		this.complexity = complexity;
		setPuzzle(getDefaultPuzzleState());
		propChangeEventGen.firePropertyChange(COMPLEXITY, priorComplexity, complexity);
	}

	public SearchMethod getSearchMethod() {
		return searchMethod;
	}

	public void setSearchMethod(SearchMethod searchMethod) {
		SearchMethod priorSearchMethod = this.searchMethod;
		this.searchMethod = searchMethod;
		propChangeEventGen.firePropertyChange(SEARCH_METHOD, priorSearchMethod, searchMethod);
	}

	public String getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(String puzzle) {
		if (!isValidPuzzle(puzzle)) {
			return;
		}
		this.puzzle = puzzle;
		propChangeEventGen.firePropertyChange(PUZZLE, "", puzzle);
	}
	
	/** Checks whether input is a valid puzzle configuration. */
	public boolean isValidPuzzle(String puzzle) {
		if (puzzle == null || puzzle.length() != 9) {
			return false;
		}
		for (int ix = 0; ix < 9; ix++) {
			if (!puzzle.contains("" + ix)) {
				return false;
			}
		}
		return true;
	}

	/** Checks whether current puzzle is a valid puzzle configuration. */
	public boolean isValidPuzzle() {
		return isValidPuzzle(this.puzzle);
	}

	/** Perform the puzzle game search with the search method. */
	public SearchResult search() {
		if (!isValidPuzzle(puzzle) || searchMethod == null) {
			return null;
		}

		// Echo to console.
		System.out.println("Solve- " + puzzle + " to: " + GOAL_PUZZLE + " using " + searchMethod + '.');

		// Build start & goal puzzle nodes.
		PuzzleNode startPuzzleNode = new PuzzleNode(puzzle, null, null);
		PuzzleNode goalPuzzleNode = new PuzzleNode(GOAL_PUZZLE, null, null);

		// Build search agent with search strategy parameter.
		SearchAgent searchAgent = new SearchAgent(getSearchStrategy(goalPuzzleNode));
		
		// Do search.
		SearchResult searchResult = searchAgent.search(startPuzzleNode, goalPuzzleNode);
		
		// Echo results to console.
		searchAgent.printNodes(searchResult.getSolutionMoves());
		System.out.println(searchResult);

		return searchResult;
	}
	
	/** Returns the default easy, medium, hard puzzle states. */
	private String getDefaultPuzzleState() {
		switch (complexity) {
		case EASY:
			return EASY_PUZZLE;
		case MEDIUM:
			return MEDIUM_PUZZLE;
		case HARD:
			return HARD_PUZZLE;
		case CUSTOM:
			return GOAL_PUZZLE;
		default:
			// Should not happen but least return something.
			return GOAL_PUZZLE;
		}
	}
	
	/** Returns the search strategy based on chosen method. */
	private SearchStrategy getSearchStrategy(PuzzleNode goalPuzzleNode) {
		switch (searchMethod) {
		case BREADTH:
			return new BreadthFirstSearchStrategy();
		case DEPTH:
			return new DepthFirstSearchStrategy();
		case GREEDY_MIN_TILES_WRONG:
			return new InformedSearchStrategy(new GreedyMinTilesWrongHeuristic(goalPuzzleNode));
		case ASTAR_MIN_TILES_WRONG:
			return new InformedSearchStrategy(new AstarMinTilesWrongHeuristic(goalPuzzleNode));
		case ASTAR_MIN_DISTANCE:
			return new InformedSearchStrategy(new AstarMinDistanceHeuristic(goalPuzzleNode));
		default:
			// Should not happen but least return something.
			return new InformedSearchStrategy(new AstarMinTilesWrongHeuristic(goalPuzzleNode));
		}
	}
	
}
