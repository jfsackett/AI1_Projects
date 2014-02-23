package ai1.strips;

import java.util.List;

public abstract class Action {

	/** Precondition propositions. */
	protected List<Proposition> prePropositions;
	
	/** Delete proposition. */
	protected List<Proposition> deletePropositions;
	
	/** Delete proposition. */
	protected List<Proposition> addPropositions;

	/** Constructor. */
	public Action(List<Proposition> prePropositions, List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		this.prePropositions = prePropositions;
		this.deletePropositions = deletePropositions;
		this.addPropositions = addPropositions;
	}	
	
	/** Take action. */
	public abstract void take();
}
