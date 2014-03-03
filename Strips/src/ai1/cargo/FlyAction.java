package ai1.cargo;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class FlyAction extends Action {

	public static FlyAction initFlyAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable("PLANE", null));
		variables.add(new Variable("FROM", null));
		variables.add(new Variable("TO", null));
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(AtAirportProposition.initAtAirportPropositionVariable("PLANE", "FROM"));
		preConditions.add(PlaneProposition.initPlanePropositionVariable("PLANE"));
		preConditions.add(AirportProposition.initAirportPropositionVariable("FROM"));
		preConditions.add(AirportProposition.initAirportPropositionVariable("TO"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(AtAirportProposition.initAtAirportPropositionVariable("PLANE", "FROM"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(AtAirportProposition.initAtAirportPropositionVariable("PLANE", "TO"));
		
		String name = "Fly";
		
		return new FlyAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public FlyAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (FlyAction) super.clone();
	}

	@Override
	public String toString() {
		StringBuilder result = new  StringBuilder();
		String planeValue = null;
		String fromValue = null;
		String toValue = null;
		for (Variable variable : variables) {
			if ("PLANE".equalsIgnoreCase(variable.getName())) {
				planeValue = variable.getValue();
			}
			else if ("FROM".equalsIgnoreCase(variable.getName())) {
				fromValue = variable.getValue();
			}
			else if ("TO".equalsIgnoreCase(variable.getName())) {
				toValue = variable.getValue();
			}
		}
		result.append("Fly: ").append(planeValue);
		result.append("  from: ").append(fromValue);
		result.append("  to: ").append(toValue);
		
		return result.toString();
	}
	
}
