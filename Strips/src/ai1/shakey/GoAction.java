package ai1.shakey;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class GoAction extends Action {

	public static GoAction initGoAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable("FROM", null));
		variables.add(new Variable("TO", null));
		variables.add(new Variable("ROOM", null));
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(LocationProposition.initLocationPropositionVariable("FROM"));
		preConditions.add(LocationProposition.initLocationPropositionVariable("TO"));
		preConditions.add(InProposition.initInPropositionVariable("FROM", "ROOM"));
		preConditions.add(InProposition.initInPropositionVariable("TO", "ROOM"));
		preConditions.add(AtProposition.initShakeyAtPropositionVariable("FROM"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(AtProposition.initShakeyAtPropositionVariable("FROM"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(AtProposition.initShakeyAtPropositionVariable("TO"));
		
		String name = "Go";
		
		return new GoAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public GoAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (GoAction) super.clone();
	}

	@Override
	public String toString() {
		StringBuilder result = new  StringBuilder();
		String fromValue = null;
		String toValue = null;
		for (Variable variable : variables) {
			if ("FROM".equalsIgnoreCase(variable.getName())) {
				fromValue = variable.getValue();
			}
			else if ("TO".equalsIgnoreCase(variable.getName())) {
				toValue = variable.getValue();
			}
		}
		result.append("Shakey goes from: ").append(fromValue);
		result.append("  to: ").append(toValue);
		
		return result.toString();
	}
	
}
