package ai1.cargo;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class UnloadAction extends Action {

	public static UnloadAction initUnloadAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable("CARGO", null));
		variables.add(new Variable("PLANE", null));
		variables.add(new Variable("AIRPORT", null));
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(InPlaneProposition.initInPlanePropositionVariable("CARGO", "PLANE"));
		preConditions.add(AtAirportProposition.initAtAirportPropositionVariable("PLANE", "AIRPORT"));
		preConditions.add(CargoProposition.initCargoPropositionVariable("CARGO"));
		preConditions.add(PlaneProposition.initPlanePropositionVariable("PLANE"));
		preConditions.add(AirportProposition.initAirportPropositionVariable("AIRPORT"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(InPlaneProposition.initInPlanePropositionVariable("CARGO", "PLANE"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(AtAirportProposition.initAtAirportPropositionVariable("CARGO", "AIRPORT"));
		
		String name = "Unload";
		
		return new UnloadAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public UnloadAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (UnloadAction) super.clone();
	}

	@Override
	public String toString() {
		StringBuilder result = new  StringBuilder();
		String cargoValue = null;
		String planeValue = null;
		String airportValue = null;
		for (Variable variable : variables) {
			if ("CARGO".equalsIgnoreCase(variable.getName())) {
				cargoValue = variable.getValue();
			}
			else if ("PLANE".equalsIgnoreCase(variable.getName())) {
				planeValue = variable.getValue();
			}
			else if ("AIRPORT".equalsIgnoreCase(variable.getName())) {
				airportValue = variable.getValue();
			}
		}
		result.append("Unload: ").append(cargoValue);
		result.append("  from: ").append(planeValue);
		result.append("  at: ").append(airportValue);
		
		return result.toString();
	}
	
}
