package ai1.tower;

import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;

public class FlipAction extends Action {

	/** Constructor. */
	public FlipAction(List<Proposition> preConditions, List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(preConditions, deletePropositions, addPropositions);
	}

	
}
