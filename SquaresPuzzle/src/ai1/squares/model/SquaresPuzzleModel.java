package ai1.squares.model;

import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

import ai1.squares.model.search.BreadthFirstSearchStrategy;
import ai1.squares.model.search.PuzzleState;
import ai1.squares.model.search.SearchResult;
import ai1.squares.model.search.SearchStrategy;

/** Main model for Squares Puzzle. */
public class SquaresPuzzleModel {
	// Property change constants.
	public static final String COMPLEXITY = "COMPLEXITY";
	public static final String SEARCH_METHOD = "SEARCH_METHOD";
	
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

	/** Perform the puzzle game search with the search method. */
	public void search() {
		if (complexity == null || searchMethod == null) {
			return;
		}
		
		PuzzleState startPuzzleState = getDefaultPuzzleState();

		PuzzleState endPuzzleState = new PuzzleState(GOAL_PUZZLE);

		SearchStrategy searchStrategy = new BreadthFirstSearchStrategy();
		
		SearchResult searchResult = searchStrategy.search(startPuzzleState, endPuzzleState);
		
		System.out.println(searchResult);
	}
	
	/** Returns the default easy, medium, hard puzzle states. */
	private PuzzleState getDefaultPuzzleState() {
		switch (complexity) {
		case EASY:
			return new PuzzleState(EASY_PUZZLE);
		case MEDIUM:
			return new PuzzleState(MEDIUM_PUZZLE);
		case HARD:
			return new PuzzleState(HARD_PUZZLE);
		default:
			// Should not happen but least return something.
			return new PuzzleState(GOAL_PUZZLE);
		}
	}
	
}
