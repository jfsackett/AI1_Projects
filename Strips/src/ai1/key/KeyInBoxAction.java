package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class KeyInBoxAction extends Action {

	public static KeyInBoxAction initKeyInBoxAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(ai1.key.InProposition.initInProposition("Robot", "Room1"));
		preConditions.add(HoldingProposition.initHoldingProposition("Key"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(ai1.key.InProposition.initInProposition("Key", "Room1"));
		deletePropositions.add(HoldingProposition.initHoldingProposition("Key"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(ai1.key.InProposition.initInProposition("Key", "Box"));
		
		String name = "KeyInBox";
		
		return new KeyInBoxAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public KeyInBoxAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (KeyInBoxAction) super.clone();
	}

	@Override
	public String toString() {
		return "Put Key In Box";
	}
	
}
