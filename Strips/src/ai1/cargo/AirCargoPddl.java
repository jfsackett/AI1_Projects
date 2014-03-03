package ai1.cargo;

import java.util.List;

import ai1.search.HeuristicStrategy;
import ai1.strips.Action;
import ai1.strips.Pddl;
import ai1.strips.Proposition;

public class AirCargoPddl extends Pddl {

	public AirCargoPddl(List<Proposition> initialState, List<Proposition> goalState, List<Action> actions) {
		super(initialState, goalState, actions);
	}

	public HeuristicStrategy getHeuristic() {
		return null;
	}
	
}
