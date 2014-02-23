package ai1.strips;

import java.util.List;

public abstract class Pddl {

	/** Initial state; could be composite. */
	protected Proposition initialState;
	
	/** Goal state; could be composite. */
	protected Proposition goalState;

	/** Actions. */
	protected List<Action> actions;

	/** Constructor. */
	public Pddl(Proposition initialState, Proposition goalState, List<Action> actions) {
		this.initialState = initialState;
		this.goalState = goalState;
		this.actions = actions;
	}
	
}
