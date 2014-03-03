package ai1.strips;

import java.util.ArrayList;
import java.util.List;

import ai1.search.BreadthFirstSearchStrategy;
import ai1.search.HeuristicStrategy;
import ai1.search.InformedSearchStrategy;
import ai1.search.Node;
import ai1.search.SearchAgent;
import ai1.search.SearchResult;

public class Strips {

	/** Problem definition to solve. */
	private Pddl pddl;
	
	/** Constructor. */
	public Strips(Pddl pddl) {
		this.pddl = pddl;
	}
	
	/** Solve the problem. */
	public List<String> solve() {
		KnowledgeBase initialKnowledgeBase = new KnowledgeBase(pddl.getInitialState(), pddl.getActions());
		KnowledgeBase goalKnowledgeBase = new KnowledgeBase(pddl.getGoalState(), pddl.getActions());
		
		SearchAgent searchAgent;
		// Get heuristic.
		HeuristicStrategy heuristicStrategy = pddl.getHeuristic();
		if (heuristicStrategy != null) {
			searchAgent = new SearchAgent(new InformedSearchStrategy(pddl.getHeuristic()));
		}
		else {
			// If no heuristic, use breadth-first search.
			searchAgent = new SearchAgent(new BreadthFirstSearchStrategy());
		}
		
		SearchResult searchResult = searchAgent.search(initialKnowledgeBase, goalKnowledgeBase);
		
		List<String> solutionSteps = new ArrayList<String>();
		if (searchResult.isSuccess()) {
			solutionSteps.add("Success:");
			for (Node solutionNode : searchResult.getSolutionMoves()) {
				solutionSteps.add(solutionNode.getAction());
			}
		}
		else {
			solutionSteps.add("Failure.");
		}
		
		return solutionSteps;
	}
}
