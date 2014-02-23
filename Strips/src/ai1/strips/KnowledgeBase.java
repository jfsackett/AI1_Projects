package ai1.strips;

import java.util.ArrayList;
import java.util.List;

import ai1.search.Node;

public class KnowledgeBase extends Node {

	/** True propositions. */
	private List<Proposition> truePropositions;

	/** All available actions. */
	private List<Action> actions;

	/** Inbound action. */
	private Action inboundAction;
	
	/** Constructor. */
	public KnowledgeBase(List<Proposition> truePropositions, List<Action> actions) {
		this.truePropositions = truePropositions;
		this.actions = actions;
	}
	
	/** Copy Constructor. */
	public KnowledgeBase(KnowledgeBase knowledgeBase, Action inboundAction) {
		truePropositions = new ArrayList<Proposition>();
		for (Proposition proposition : knowledgeBase.truePropositions) {
			truePropositions.add(proposition);
		}
		actions = knowledgeBase.actions;
		this.inboundAction = inboundAction;
	}
	
	/** Return action leading to this state. */
	@Override
	public String getAction() {
		return "" + inboundAction;
	}

	/** Return list of successor knowledge base nodes traversable to from this node. */
	@Override
	public List<Node> getSuccessorNodes() {
		List<Node> successorNodes = new ArrayList<Node>();
		
		// Loop through actions legal from this knowledge base node.
		for (Action action : actions) {
			// Make move to next knowledge base node.
			KnowledgeBase nextKnowledgeBaseNode = action.take(this);
			// Check for transition to state where preconditons false.
			if (nextKnowledgeBaseNode == null) {
				continue;
			}
			successorNodes.add(nextKnowledgeBaseNode);
		}
		
		return successorNodes;
	}

	/** Checks if the input proposition is true in KB. */
	public boolean isTrue(Proposition proposition) {
		for (Proposition trueProposition : truePropositions) {
			if (trueProposition.equals(proposition)) {
				return true;
			}
		}
		return false;
	}
	
	/** Checks if the input proposition is false in KB. */
	public boolean isFalse(Proposition proposition) {
		return !isTrue(proposition);
	}
	
	public void add(List<Proposition> propositions) {
		truePropositions.addAll(propositions);
	}
	
	public void remove(List<Proposition> propositions) {
		truePropositions.removeAll(propositions);
	}

	@Override
	public int hashCode() {
		int result = 31 + ((truePropositions == null) ? 0 : truePropositions.hashCode());
		for (Proposition trueProposition : truePropositions) {
			result += trueProposition.hashCode();
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
	
	
}
