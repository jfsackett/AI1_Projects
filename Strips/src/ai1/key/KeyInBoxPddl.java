package ai1.key;

import java.util.List;

import ai1.search.HeuristicStrategy;
import ai1.strips.Action;
import ai1.strips.Pddl;
import ai1.strips.Proposition;

public class KeyInBoxPddl extends Pddl {

	public KeyInBoxPddl(List<Proposition> initialState, List<Proposition> goalState, List<Action> actions) {
		super(initialState, goalState, actions);
	}

	public HeuristicStrategy getHeuristic() {
		return null;
	}
	
}
