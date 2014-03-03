package ai1.shakey;

import java.util.List;

import ai1.search.HeuristicStrategy;
import ai1.strips.Action;
import ai1.strips.Pddl;
import ai1.strips.Proposition;

public class ShakeysWorldPddl extends Pddl {

	public ShakeysWorldPddl(List<Proposition> initialState, List<Proposition> goalState, List<Action> actions) {
		super(initialState, goalState, actions);
	}

	public HeuristicStrategy getHeuristic() {
		return null;
	}
	
}
