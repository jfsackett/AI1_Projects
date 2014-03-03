package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class MoveKeyAction extends Action {

	public static MoveKeyAction initMoveKeyAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(ai1.key.InProposition.initInProposition("Robot", "Room2"));
		preConditions.add(HoldingProposition.initHoldingProposition("Key"));
		preConditions.add(UnlockedProposition.initUnlockedProposition("Door"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(ai1.key.InProposition.initInProposition("Robot", "Room2"));
		deletePropositions.add(ai1.key.InProposition.initInProposition("Key", "Room2"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(ai1.key.InProposition.initInProposition("Robot", "Room1"));
		addPropositions.add(ai1.key.InProposition.initInProposition("Key", "Room1"));
		
		String name = "MoveKey";
		
		return new MoveKeyAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public MoveKeyAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (MoveKeyAction) super.clone();
	}

	@Override
	public String toString() {
		return "Move Key";
	}
	
}
