package ai1.tower;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class MoveAction extends Action {

	public static MoveAction initMoveAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable("DISK", null));
		variables.add(new Variable("FROM", null));
		variables.add(new Variable("TO", null));
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(ClearProposition.initClearPropositionVariable("DISK"));
		preConditions.add(ClearProposition.initClearPropositionVariable("TO"));
		preConditions.add(SmallerProposition.initSmallerPropositionVariable("DISK", "TO"));
		preConditions.add(OnProposition.initOnPropositionVariable("DISK", "FROM"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(OnProposition.initOnPropositionVariable("DISK", "FROM"));
		deletePropositions.add(ClearProposition.initClearPropositionVariable("TO"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(OnProposition.initOnPropositionVariable("DISK", "TO"));
		addPropositions.add(ClearProposition.initClearPropositionVariable("FROM"));
		
		String name = "Move";
		
		return new MoveAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public MoveAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (MoveAction) super.clone();
	}

	@Override
	public String toString() {
		StringBuilder result = new  StringBuilder();
		String diskValue = null;
		String fromValue = null;
		String toValue = null;
		for (Variable variable : variables) {
			if ("DISK".equalsIgnoreCase(variable.getName())) {
				diskValue = variable.getValue();
			}
			else if ("FROM".equalsIgnoreCase(variable.getName())) {
				fromValue = variable.getValue();
			}
			else if ("TO".equalsIgnoreCase(variable.getName())) {
				toValue = variable.getValue();
			}
		}
		result.append("Move Disk: ").append(diskValue);
		result.append("  From: ").append(fromValue);
		result.append("  To: ").append(toValue);
		
		return result.toString();
	}
	
}
