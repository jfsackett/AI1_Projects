package ai1.strips;

import java.util.List;

/** Compound proposition; composite pattern. */
public class CompositeProposition {

	/** Sub-propositions. */
	private List<Proposition> subPropositions;
	
	/** Return true if all sub-propositions true, else false. */
	public boolean isTrue() {
		for (Proposition subProposition : subPropositions) {
			if (subProposition.isFalse()) {
				return false;
			}
		}
		return true;
	}
	
	/** Return false if any sub-propositions false, else true. */
	public boolean isFalse() {
		return !isTrue();
	}
	
}
