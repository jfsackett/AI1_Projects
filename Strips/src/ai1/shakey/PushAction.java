package ai1.shakey;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class PushAction extends Action {

	public static PushAction initPushAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable("BOX", null));
		variables.add(new Variable("FROM", null));
		variables.add(new Variable("TO", null));
		variables.add(new Variable("ROOM", null));
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(BoxProposition.initBoxPropositionVariable("BOX"));
		preConditions.add(LocationProposition.initLocationPropositionVariable("FROM"));
		preConditions.add(LocationProposition.initLocationPropositionVariable("TO"));
		preConditions.add(InProposition.initInPropositionVariable("FROM", "ROOM"));
		preConditions.add(InProposition.initInPropositionVariable("TO", "ROOM"));
		preConditions.add(AtProposition.initAtPropositionVariable("BOX", "FROM"));
		preConditions.add(AtProposition.initShakeyAtPropositionVariable("FROM"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(AtProposition.initAtPropositionVariable("BOX", "FROM"));
		deletePropositions.add(AtProposition.initShakeyAtPropositionVariable("FROM"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(AtProposition.initAtPropositionVariable("BOX", "TO"));
		addPropositions.add(AtProposition.initShakeyAtPropositionVariable("TO"));
		
		String name = "Push";
		
		return new PushAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public PushAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (PushAction) super.clone();
	}

	@Override
	public String toString() {
		StringBuilder result = new  StringBuilder();
		String boxValue = null;
		String fromValue = null;
		String toValue = null;
		for (Variable variable : variables) {
			if ("BOX".equalsIgnoreCase(variable.getName())) {
				boxValue = variable.getValue();
			}
			if ("FROM".equalsIgnoreCase(variable.getName())) {
				fromValue = variable.getValue();
			}
			else if ("TO".equalsIgnoreCase(variable.getName())) {
				toValue = variable.getValue();
			}
		}
		result.append("Shakey pushes box: ").append(boxValue);
		result.append("  from: ").append(fromValue);
		result.append("  to: ").append(toValue);
		
		return result.toString();
	}
	
}
