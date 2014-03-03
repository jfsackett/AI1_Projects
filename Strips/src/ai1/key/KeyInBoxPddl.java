package ai1.key;

import java.util.List;

import ai1.search.HeuristicStrategy;
import ai1.search.Node;
import ai1.strips.Action;
import ai1.strips.KnowledgeBase;
import ai1.strips.Pddl;
import ai1.strips.Proposition;

public class KeyInBoxPddl extends Pddl {

	public KeyInBoxPddl(List<Proposition> initialState, List<Proposition> goalState, List<Action> actions) {
		super(initialState, goalState, actions);
	}

	public HeuristicStrategy getHeuristic() {
		return null;//new KeyInBoxHeuristicStrategy(goalStateHeuristic);
	}
	
	private static class KeyInBoxHeuristicStrategy implements HeuristicStrategy {
		/** Propositions used for heuristic estimates. */
		private List<Proposition> goalState;
		
		public KeyInBoxHeuristicStrategy(List<Proposition> goalState) {
			this.goalState = goalState;
		}

		/** Heuristic for estimating the number of moves to goal state; weight of disks out of place. */
		public int estimateCost(Node node) {
			// Safe cast.
			KnowledgeBase knowledgeBase = (KnowledgeBase) node;

			return 0;
		}
	}
}
