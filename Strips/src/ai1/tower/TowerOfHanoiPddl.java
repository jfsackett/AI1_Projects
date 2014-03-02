package ai1.tower;

import java.util.List;

import ai1.search.HeuristicStrategy;
import ai1.search.Node;
import ai1.strips.Action;
import ai1.strips.KnowledgeBase;
import ai1.strips.Pddl;
import ai1.strips.Proposition;

public class TowerOfHanoiPddl extends Pddl {

	/** Propositions used for heuristic estimates. */
	private List<Proposition> goalStateHeuristic;
	
	public TowerOfHanoiPddl(List<Proposition> initialState, List<Proposition> goalState, List<Action> actions, List<Proposition> goalStateHeuristic) {
		super(initialState, goalState, actions);
		this.goalStateHeuristic = goalStateHeuristic;
	}

	public HeuristicStrategy getHeuristic() {
		return new TowerOfHanoiHeuristicStrategy(goalStateHeuristic);
	}
	
	private static class TowerOfHanoiHeuristicStrategy implements HeuristicStrategy {
		/** Propositions used for heuristic estimates. */
		private List<Proposition> goalState;
		
		public TowerOfHanoiHeuristicStrategy(List<Proposition> goalState) {
			this.goalState = goalState;
		}

		/** Heuristic for estimating the number of moves to goal state; weight of disks out of place. */
		public int estimateCost(Node node) {
			// Safe cast.
			KnowledgeBase knowledgeBase = (KnowledgeBase) node;

			int estimate = 0;
			// Check for cached value.
			if ((estimate = knowledgeBase.getCacheCostEstimate()) < 0) {
				estimate = 0;
				for (Proposition proposition : goalState) {
					if (proposition.getClass().equals(OnProposition.class)) {
						OnProposition onProposition = (OnProposition) proposition;
						if (!knowledgeBase.isTrue(onProposition)) {
							estimate += onProposition.getWeight();
						}
					}
				}
				
				// Set cache so recalc is not necessary.
				knowledgeBase.setCacheCostEstimate(estimate);
			}
			
			// Return moves so far + number of tiles wrong from superclass. A*
			return knowledgeBase.getPathCost() + estimate;
		}
	}
}
