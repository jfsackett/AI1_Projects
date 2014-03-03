package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class LockDoorAction extends Action {

	public static LockDoorAction initLockDoorAction() {
		// Variables
		List<Variable> variables = new ArrayList<Variable>();
		// Preconditions
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(HoldingProposition.initHoldingProposition("Key"));
		preConditions.add(UnlockedProposition.initUnlockedProposition("Door"));
		// Delete propositions.
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(UnlockedProposition.initUnlockedProposition("Door"));
		// Add propositions.
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(LockedProposition.initLockedProposition("Door"));
		
		String name = "LockDoor";
		
		return new LockDoorAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public LockDoorAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (LockDoorAction) super.clone();
	}

	@Override
	public String toString() {
		return "Lock Door";
	}
	
}
