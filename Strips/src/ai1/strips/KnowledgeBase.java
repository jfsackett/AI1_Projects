package ai1.strips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai1.search.Node;

/** Represents the know state of predicates; extends search node so graphs of them are searchable. */
public class KnowledgeBase extends Node {

	/** True propositions. */
	private List<Proposition> truePropositions;

	/** All available actions. */
	private List<Action> actions;

	/** Inbound action. */
	private Action inboundAction;
	
	/** Heuristic cache value. Use carefully */
	private int cacheCostEstimate = -1; 
	
	/** Constructor. */
	public KnowledgeBase(List<Proposition> truePropositions, List<Action> actions) {
		this.truePropositions = truePropositions;
		this.actions = actions;
		this.pathCost = 0;
	}
	
	/** Copy Constructor. */
	public KnowledgeBase(KnowledgeBase knowledgeBase, Action inboundAction) {
		truePropositions = new ArrayList<Proposition>();
		for (Proposition proposition : knowledgeBase.truePropositions) {
			truePropositions.add(proposition);
		}
		actions = knowledgeBase.actions;
		this.inboundAction = inboundAction;
		this.parentNode = knowledgeBase;
		this.pathCost = parentNode.getPathCost() + 1;
	}
	
	/** Return list of successor knowledge base nodes traversable to from this node. */
	@Override
	public List<Node> getSuccessorNodes() {
		List<Node> successorNodes = new ArrayList<Node>();
		
		// Loop through actions legal from this knowledge base node.
		for (Action action : actions) {
			// Make move to next knowledge base node.
			List<KnowledgeBase> nextKnowledgeBaseNodes = action.take(this);
			// Check for transition to state where preconditions false.
			if (nextKnowledgeBaseNodes == null) {
				// Can not take this action.
				continue;
			}
			// Add to new knowledge base states to successor nodes.
			for (KnowledgeBase nextKnowledgeBaseNode : nextKnowledgeBaseNodes) {
				successorNodes.add(nextKnowledgeBaseNode);
			}
		}
		
		return successorNodes;
	}

	/** Return action leading to this state. */
	@Override
	public String getAction() {
		if (inboundAction == null) {
			// Initial state.
			return "Initial state.";
		}
		
		return "" + inboundAction;
	}

	public List<Map<String,Variable>> bindPropositionVariables(Proposition proposition) {
		// List of matching variable instance tuples.
		List<Map<String,Variable>> matchingInstances = new ArrayList<Map<String,Variable>>();
		boolean aPropositionMatches = false;
		// Loop through true propositions, looking for matches.
		for(Proposition trueProposition : truePropositions) {
			if (!trueProposition.getName().equalsIgnoreCase(proposition.getName())) {
				// Different proposition, no match.
				continue;
			}
			if (trueProposition.getVariables().size() != proposition.getVariables().size()) {
				// Different size argument list, no match.
				continue;
			}
			// Map of matching variable values for this proposition
			Map<String,Variable> matchingInstance = new HashMap<String,Variable>();
			boolean propositionMatches = true;
			// Loop through proposition's & KB true proposition's variables.
			for (int ix = 0; ix < trueProposition.getVariables().size(); ix++) {
				// Input proposition variable's name. May be null for unattached instance.
				String propositionVariableName = proposition.getVariables().get(ix).getName();
				// Input proposition variable's value. May be null for uninstantiated variable.
				String propositionVariableValue = proposition.getVariables().get(ix).getValue();
				// True proposition variable's value. Must be non-null in KB.
				String truePropositionVariableValue = trueProposition.getVariables().get(ix).getValue();
				// Check for value match.
				if (propositionVariableName == null) {
					// Check for value mismatch.
					if (!propositionVariableValue.equalsIgnoreCase(truePropositionVariableValue)) {
						// Proposition is unsuccessful match.
						propositionMatches = false;
						break;
					}
					// else values match but nothing to do.
				}
				else {
					// Variable matches, instantiate variable & put in map.
					Variable variable = new Variable(propositionVariableName, truePropositionVariableValue);
					matchingInstance.put(propositionVariableName, variable);
				}
			}
			if (propositionMatches) {
				// This true proposition matches.
				aPropositionMatches = true;
				// If any matching variables, place in result list.
				if (!matchingInstance.isEmpty()) {
					matchingInstances.add(matchingInstance);
				}
			}
		}
		
		// No propositions matched, null indicates failed proposition.
		if (!aPropositionMatches) {
			return null;
		}
		
		return matchingInstances;
	}
	
	public void add(List<Proposition> propositions) {
		truePropositions.addAll(propositions);
	}
	
	public void remove(List<Proposition> propositions) {
		truePropositions.removeAll(propositions);
	}

	public boolean isTrue(Proposition proposition) {
		return truePropositions.contains(proposition);
	}

	public int getCacheCostEstimate() {
		return cacheCostEstimate;
	}

	public void setCacheCostEstimate(int cacheCostEstimate) {
		this.cacheCostEstimate = cacheCostEstimate;
	}

	@Override
	public int hashCode() {
		int result = 31;
		if (truePropositions == null) {
			return result;
		}
		for (Proposition trueProposition : truePropositions) {
			result += ((trueProposition == null) ? 0 : trueProposition.hashCode());
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KnowledgeBase other = (KnowledgeBase) obj;
		if (truePropositions == null) {
			if (other.truePropositions != null)
				return false;
		}
		else {
			List<Proposition> testPropositions = new ArrayList<Proposition>();
			// Test for complete subset, other to this.
			testPropositions.addAll(other.truePropositions);
			testPropositions.removeAll(this.truePropositions);
			if (!testPropositions.isEmpty()) {
				return false;
			}
			// Test for complete subset, this to other.
			testPropositions.clear();
			testPropositions.addAll(this.truePropositions);
			testPropositions.removeAll(other.truePropositions);
			if (!testPropositions.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new  StringBuilder();
		result.append("KnowledgeBase [truePropositions=");
		for (Proposition trueProposition : truePropositions) {
			String propositionToString = "" + trueProposition;
			if (propositionToString.length() == 0) {
				continue;
			}
			result.append("\n\t").append(propositionToString);
		}
		result.append("]");
		
		return result.toString();
	}
	
}
