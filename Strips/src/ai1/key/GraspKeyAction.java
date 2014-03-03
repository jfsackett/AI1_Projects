package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class GraspKeyAction extends Action {

	public static GraspKeyAction initGraspKeyAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(ai1.key.InProposition.initInProposition("Robot", "Room2"));
		preConditions.add(ai1.key.InProposition.initInProposition("Key", "Room2"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(HoldingProposition.initHoldingProposition("Key"));
		
		String name = "GraspKey";
		
		return new GraspKeyAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public GraspKeyAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (GraspKeyAction) super.clone();
	}

	@Override
	public String toString() {
		return "Grasp Key";
	}
	
}
