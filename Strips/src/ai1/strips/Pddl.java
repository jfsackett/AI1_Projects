package ai1.strips;

import java.util.List;

import ai1.search.HeuristicStrategy;

public abstract class Pddl {

	/** Initial state propositions. */
	private List<Proposition> initialState;
	
	/** Goal state propositions. */
	private List<Proposition> goalState;

	/** Actions. */
	private List<Action> actions;
	
	/** Constructor. */
	public Pddl(List<Proposition> initialState, List<Proposition> goalState, List<Action> actions) {
		this.initialState = initialState;
		this.goalState = goalState;
		this.actions = actions;
	}

	public abstract HeuristicStrategy getHeuristic(); 
	
	/** Accessor. */
	public List<Proposition> getInitialState() {
		return initialState;
	}

	/** Accessor. */
	public List<Proposition> getGoalState() {
		return goalState;
	}

	/** Accessor. */
	public List<Action> getActions() {
		return actions;
	}
	
}
