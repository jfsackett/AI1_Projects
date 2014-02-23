package ai1.strips;

import java.util.List;

public abstract class Action {

	/** Precondition propositions. */
	private List<Proposition> preConditions;
	
	/** Delete proposition. */
	private List<Proposition> removePropositions;
	
	/** Delete proposition. */
	private List<Proposition> addPropositions;
	
	/** Constructor. */
	public Action(List<Proposition> preConditions, List<Proposition> removePropositions, List<Proposition> addPropositions) {
		this.preConditions = preConditions;
		this.removePropositions = removePropositions;
		this.addPropositions = addPropositions;
	}	
	
	/** Take this action if preconditions met. */
	public KnowledgeBase take(KnowledgeBase knowledgeBaseIn) {
		if (!preConditionsTrue(knowledgeBaseIn)) {
			return null;
		}
		
		KnowledgeBase knowledgeBase = new KnowledgeBase(knowledgeBaseIn, this);
		knowledgeBase.remove(this.removePropositions);
		knowledgeBase.add(this.addPropositions);
		return knowledgeBase;
	}
	
	/** Are all of the preconditions true? */
	public boolean preConditionsTrue(KnowledgeBase knowledgeBase) {
		for (Proposition preProposition : preConditions) {
			if (knowledgeBase.isFalse(preProposition)) {
				return false;
			}
		}
		return true;
	}
	
}
