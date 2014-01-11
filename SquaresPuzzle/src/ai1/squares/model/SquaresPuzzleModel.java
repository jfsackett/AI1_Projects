package ai1.squares.model;

public class SquaresPuzzleModel {

	/** Puzzle Complexity. */
	private Complexity complexity;

	/** Solution Search Method. */
	private SearchMethod searchMethod;

	public Complexity getComplexity() {
		return complexity;
	}

	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}

	public SearchMethod getSearchMethod() {
		return searchMethod;
	}

	public void setSearchMethod(SearchMethod searchMethod) {
		this.searchMethod = searchMethod;
	}

}
